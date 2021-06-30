package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.ResearchPaperDetails;
import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchPaperDetailsRepository;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Service
public class FormResearchPaperDetailsService {
    @Autowired
    private ResearchPaperDetailsRepository researchpaperDetailsRepository;

    public ResearchPaperDetails updateApprovalStatus(String id) throws IOException {
        ResearchPaperDetails researchPaperDetails = researchpaperDetailsRepository.findById(id).get();

        researchPaperDetails.setApprovalStatus(true);

        return researchpaperDetailsRepository.save(researchPaperDetails);
    }


    public ResearchPaperDetails getFile(String id) {
        return researchpaperDetailsRepository.findById(id).get();
    }

    public Stream<ResearchPaperDetails> getAllFiles() {
        return researchpaperDetailsRepository.findAll().stream();
    }
}


