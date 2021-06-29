package com.nsss.conferencemanagementtoolbackend.services;

import com.nsss.conferencemanagementtoolbackend.model.WorkshopDetails;
import com.nsss.conferencemanagementtoolbackend.repository.WorkshopDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.stream.Stream;

@Service

public class FormWorkshopDetailsService {

        @Autowired
        private WorkshopDetailsRepository workshopDetailsRepository;

        public WorkshopDetails updateApprovalStatus(String id) throws IOException {
            WorkshopDetails workshopDetails = workshopDetailsRepository.findById(id).get();

            workshopDetails.setApprovalStatus(true);

            return workshopDetailsRepository.save(workshopDetails);
        }


        public WorkshopDetails getFile(String id) {
            return workshopDetailsRepository.findById(id).get();
        }

        public Stream<WorkshopDetails> getAllFiles() {
            return workshopDetailsRepository.findAll().stream();
        }
}


