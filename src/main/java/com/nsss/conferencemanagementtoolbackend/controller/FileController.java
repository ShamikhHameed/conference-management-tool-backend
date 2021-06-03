package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.message.ResponseFile;
import com.nsss.conferencemanagementtoolbackend.message.ResponseMessage;
import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.model.WorkshopFile;
import com.nsss.conferencemanagementtoolbackend.services.ResearchFileService;
import com.nsss.conferencemanagementtoolbackend.services.WorkshopFileService;
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
public class FileController {

    @Autowired
    private ResearchFileService researchFileService;

    @Autowired
    private WorkshopFileService workshopFileService;

    @PostMapping("/rp/upload")
    public ResponseEntity<ResponseMessage> uploadRPFile(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("user") String user,
                                                        @RequestParam("approvalStatus") Boolean approvalStatus,
                                                        @RequestParam("paymentStatus") Boolean paymentStatus) {
        String message = "";
        try {
            researchFileService.store(file, user, approvalStatus, paymentStatus);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/rp/files/{id}/approval")
    public ResponseEntity<ResponseMessage> updateRPFileApproval(@PathVariable String id) {
        ResearchFile researchFile = researchFileService.getFile(id);

        String message = "";

        try {
            researchFileService.updateApprovalStatus(id);

            message = "Updated file approval successfully: " + researchFile.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update file approval: " + researchFile.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/rp/files/{id}/payment")
    public ResponseEntity<ResponseMessage> updateRPFilePayment(@PathVariable String id) {
        ResearchFile researchFile = researchFileService.getFile(id);

        String message = "";

        try {
            researchFileService.updatePaymentStatus(id);

            message = "Updated file payment successfully: " + researchFile.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update file payment: " + researchFile.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/rp/files")
    public ResponseEntity<List<ResponseFile>> getListRPFiles() {
        List<ResponseFile> files = researchFileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("api/access/rp/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getUser(),
                    dbFile.isApprovalStatus(),
                    dbFile.isPaymentStatus());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/rp/files/{id}/download")
    public ResponseEntity<byte[]> downloadRPFile(@PathVariable String id) {
        ResearchFile researchFile = researchFileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + researchFile.getName() + "\"")
                .body(researchFile.getData());
    }

    @GetMapping("/rp/files/{id}/view")
    public ResponseEntity<byte[]> viewRPFile(@PathVariable String id) {
        ResearchFile researchFile = researchFileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + researchFile.getName() + "\"")
                .body(researchFile.getData());
    }

    @PostMapping("/wp/upload")
    public ResponseEntity<ResponseMessage> uploadWPFile(@RequestParam("file") MultipartFile file,
                                                        @RequestParam("user") String user,
                                                        @RequestParam("approvalStatus") Boolean status) {
        String message = "";
        try {
            workshopFileService.store(file, user, status);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @PutMapping("/wp/files/{id}")
    public ResponseEntity<ResponseMessage> updateWPFile(@PathVariable String id) {
        WorkshopFile workshopFile = workshopFileService.getFile(id);

        String message = "";

        try {
            workshopFileService.update(id);

            message = "Updated the file successfully: " + workshopFile.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update the file: " + workshopFile.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/wp/files")
    public ResponseEntity<List<ResponseFile>> getListWPFiles() {
        List<ResponseFile> files = workshopFileService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("api/access/wp/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length,
                    dbFile.getUser(),
                    dbFile.isApprovalStatus());
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/wp/files/{id}/download")
    public ResponseEntity<byte[]> downloadWPFile(@PathVariable String id) {
        WorkshopFile workshopFile = workshopFileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + workshopFile.getName() + "\"")
                .body(workshopFile.getData());
    }

    @GetMapping("/wp/files/{id}/view")
    public ResponseEntity<byte[]> viewWPFile(@PathVariable String id) {
        WorkshopFile workshopFile = workshopFileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + workshopFile.getName() + "\"")
                .body(workshopFile.getData());
    }
}
