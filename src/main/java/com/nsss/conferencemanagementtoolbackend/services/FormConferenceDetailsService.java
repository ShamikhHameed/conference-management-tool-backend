package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.ConferenceDetails;
import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.repository.ConferenceDetailsRepository;
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
public class FormConferenceDetailsService {
    @Autowired
    private ConferenceDetailsRepository conferenceDetailsRepository;

//    public ConferenceDetails store(MultipartFile file, String name, String institute, Date startDate, int noOfDays, List<String> speakers, List<String> speakerInstitutes, boolean approvalStatus) throws IOException {
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        ConferenceDetails conferenceDetails = new ConferenceDetails(fileName, );
//
//        return conferenceDetailsRepository.save(conferenceDetails);
//    }

    public ConferenceDetails updateApprovalStatus(String id) throws IOException {
        ConferenceDetails conferenceDetails = conferenceDetailsRepository.findById(id).get();

        conferenceDetails.setApprovalStatus(true);

        return conferenceDetailsRepository.save(conferenceDetails);
    }


    public ConferenceDetails getFile(String id) {
        return conferenceDetailsRepository.findById(id).get();
    }

    public Stream<ConferenceDetails> getAllFiles() {
        return conferenceDetailsRepository.findAll().stream();
    }
}


