package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.model.ResearchPaperDetails;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchPaperDetailsRepository;
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
public class ResearchPaperDetailsController {
    @Autowired
    private ResearchPaperDetailsRepository researchpaperDetailsRepository;

    @PostMapping("/researchpaperDetails")
    public ResponseEntity<ResearchPaperDetails> createResearchPaperDetails(@RequestBody ResearchPaperDetails researchpaperDetails) {
        try {
            ResearchPaperDetails _researchpaperDetails = researchpaperDetailsRepository.save(new ResearchPaperDetails(researchpaperDetails.getName(),researchpaperDetails.getInstitute(),researchpaperDetails.getStartDate(),researchpaperDetails.getNoOfDays(),researchpaperDetails.getSpeakers(),researchpaperDetails.getSpeakerInstitute(), false));
            return new ResponseEntity<>(_researchpaperDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/researchpaperDetails")
    public ResponseEntity<List<ResearchPaperDetails>> getAllResearchPaperDetails(@RequestParam(required = false) String name) {
        try {
            List<ResearchPaperDetails> researchpaperDetails = new ArrayList<ResearchPaperDetails>();

            if (name == null)
                researchpaperDetailsRepository.findAll().forEach(researchpaperDetails::add);
            else
                researchpaperDetailsRepository.findByNameContaining(name).forEach(researchpaperDetails::add);

            if (researchpaperDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(researchpaperDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/researchpaperDetails/{id}")
    public ResponseEntity<ResearchPaperDetails> getResearchPaperDetailsById(@PathVariable("id") String id) {
        Optional<ResearchPaperDetails> researchpaperDetailsData = researchpaperDetailsRepository.findById(id);

        if (researchpaperDetailsData.isPresent()) {
            return new ResponseEntity<>(researchpaperDetailsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/researchpaperDetails/{id}")
    public ResponseEntity<ResearchPaperDetails> updateResearchPaperDetails(@PathVariable("id") String id, @RequestBody ResearchPaperDetails researchpaperDetails) {
        Optional<ResearchPaperDetails> researchpaperDetailsData = researchpaperDetailsRepository.findById(id);

        if (researchpaperDetailsData.isPresent()) {
            ResearchPaperDetails _researchpaperDetails = researchpaperDetailsData.get();
            _researchpaperDetails.setName(researchpaperDetails.getName());
            _researchpaperDetails.setInstitute(researchpaperDetails.getInstitute());
            _researchpaperDetails.setStartDate(researchpaperDetails.getStartDate());
            _researchpaperDetails.setNoOfDays(researchpaperDetails.getNoOfDays());
            _researchpaperDetails.setSpeakers(researchpaperDetails.getSpeakers());
            _researchpaperDetails.setSpeakerInstitute(researchpaperDetails.getSpeakerInstitute());

            return new ResponseEntity<>(researchpaperDetailsRepository.save(_researchpaperDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/researchpaperDetails/{id}")
    public ResponseEntity<HttpStatus> deleteResearchPaperDetails(@PathVariable("id") String id) {
        try {
            researchpaperDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
