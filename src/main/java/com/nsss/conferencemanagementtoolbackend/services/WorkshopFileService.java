package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.model.WorkshopFile;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchFileRepository;
import com.nsss.conferencemanagementtoolbackend.repository.WorkshopFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class WorkshopFileService {
    @Autowired
    private WorkshopFileRepository workshopFileRepository;

    public WorkshopFile store(MultipartFile file, String user, Boolean approvalStatus) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        WorkshopFile workshopFile = new WorkshopFile(fileName, file.getContentType(), file.getBytes(), user, approvalStatus);

        return workshopFileRepository.save(workshopFile);
    }

    public WorkshopFile update(String id) throws IOException {
        WorkshopFile workshopFile = workshopFileRepository.findById(id).get();

        workshopFile.setApprovalStatus(true);

        return workshopFileRepository.save(workshopFile);
    }

    public WorkshopFile getFile(String id) {
        return workshopFileRepository.findById(id).get();
    }

    public Stream<WorkshopFile> getAllFiles() {
        return workshopFileRepository.findAll().stream();
    }
}
