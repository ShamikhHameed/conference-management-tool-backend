package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.model.Template;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchFileRepository;
import com.nsss.conferencemanagementtoolbackend.repository.TemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class TemplateService {
    @Autowired
    private TemplateRepository templateRepository;

    public Template store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Template template = new Template(fileName, file.getContentType(), file.getBytes());

        return templateRepository.save(template);
    }

    public Template getFile(String id) {
        return templateRepository.findById(id).get();
    }

    public Stream<Template> getAllFiles() {
        return templateRepository.findAll().stream();
    }
}
