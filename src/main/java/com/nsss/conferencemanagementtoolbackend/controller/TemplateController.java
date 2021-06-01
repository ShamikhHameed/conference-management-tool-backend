package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.message.ResponseFile;
import com.nsss.conferencemanagementtoolbackend.message.ResponseMessage;
import com.nsss.conferencemanagementtoolbackend.model.Template;
import com.nsss.conferencemanagementtoolbackend.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/access")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @PostMapping("/template/upload")
    public ResponseEntity<ResponseMessage> uploadTemplateFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            templateService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/template/files")
    public ResponseEntity<List<ResponseFile>> getListTemplateFiles() {
        List<ResponseFile> files = templateService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("api/access/template/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/template/files/{id}/download")
    public ResponseEntity<byte[]> downloadTemplateFile(@PathVariable String id) {
        Template template = templateService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + template.getName() + "\"")
                .body(template.getData());
    }
}
