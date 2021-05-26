package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ResearchFileService {
    @Autowired
    private ResearchFileRepository researchFileRepository;

    public ResearchFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        ResearchFile researchFile = new ResearchFile(fileName, file.getContentType(), file.getBytes());

        return researchFileRepository.save(researchFile);
    }

    public ResearchFile getFile(String id) {
        return researchFileRepository.findById(id).get();
    }

    public Stream<ResearchFile> getAllFiles() {
        return researchFileRepository.findAll().stream();
    }
}
