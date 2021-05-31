package com.nsss.conferencemanagementtoolbackend.controller;


import com.nsss.conferencemanagementtoolbackend.model.ConferenceDetails;
import com.nsss.conferencemanagementtoolbackend.repository.ConferenceDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/access")
public class ConferenceDetailsController {
    @Autowired
    private ConferenceDetailsRepository conferenceDetailsRepository;

    @PostMapping("/conferenceDetails")
    public ResponseEntity<ConferenceDetails> createConferenceDetails(@RequestBody ConferenceDetails conferenceDetails) {
        try {
            ConferenceDetails _conferenceDetails = conferenceDetailsRepository.save(new ConferenceDetails(conferenceDetails.getName(),conferenceDetails.getInstitute(),conferenceDetails.getStartDate(),conferenceDetails.getNoOfDays(),conferenceDetails.getSpeakers(),conferenceDetails.getSpeakerInstitute(), false));
            return new ResponseEntity<>(_conferenceDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/conferenceDetails")
    public ResponseEntity<List<ConferenceDetails>> getAllConferenceDetails(@RequestParam(required = false) String name) {
        try {
            List<ConferenceDetails> conferenceDetails = new ArrayList<ConferenceDetails>();

            if (name == null)
                conferenceDetailsRepository.findAll().forEach(conferenceDetails::add);
            else
                conferenceDetailsRepository.findByNameContaining(name).forEach(conferenceDetails::add);

            if (conferenceDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(conferenceDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/conferenceDetails/{id}")
    public ResponseEntity<ConferenceDetails> getConferenceDetailsById(@PathVariable("id") String id) {
        Optional<ConferenceDetails> conferenceDetailsData = conferenceDetailsRepository.findById(id);

        if (conferenceDetailsData.isPresent()) {
            return new ResponseEntity<>(conferenceDetailsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/conferenceDetails/{id}")
    public ResponseEntity<ConferenceDetails> updateConferenceDetails(@PathVariable("id") String id, @RequestBody ConferenceDetails conferenceDetails) {
        Optional<ConferenceDetails> conferenceDetailsData = conferenceDetailsRepository.findById(id);

        if (conferenceDetailsData.isPresent()) {
            ConferenceDetails _conferenceDetails = conferenceDetailsData.get();
            _conferenceDetails.setName(conferenceDetails.getName());
            _conferenceDetails.setInstitute(conferenceDetails.getInstitute());
            _conferenceDetails.setStartDate(conferenceDetails.getStartDate());
            _conferenceDetails.setNoOfDays(conferenceDetails.getNoOfDays());
            _conferenceDetails.setSpeakers(conferenceDetails.getSpeakers());
            _conferenceDetails.setSpeakerInstitute(conferenceDetails.getSpeakerInstitute());

            return new ResponseEntity<>(conferenceDetailsRepository.save(_conferenceDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/conferenceDetails/{id}")
    public ResponseEntity<HttpStatus> deleteConferenceDetails(@PathVariable("id") String id) {
        try {
            conferenceDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
