package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.message.ResponseMessage;
import com.nsss.conferencemanagementtoolbackend.model.ResearchPaperDetails;
import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.repository.ResearchPaperDetailsRepository;
import com.nsss.conferencemanagementtoolbackend.services.FormResearchPaperDetailsService;
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
    @Autowired
    private FormResearchPaperDetailsService formResearchPaperDetailsService;

    @PostMapping("/researchPaperDetails")
    public ResponseEntity<ResearchPaperDetails> createResearchPaperDetails(@RequestBody ResearchPaperDetails researchPaperDetails) {
        try {
            ResearchPaperDetails _researchPaperDetails = researchpaperDetailsRepository.save(new ResearchPaperDetails(researchPaperDetails.getName(),researchPaperDetails.getTitle(),researchPaperDetails.getResearchArea(),researchPaperDetails.getPublishedDate(),researchPaperDetails.getCountry(),researchPaperDetails.getAuthor(), researchPaperDetails.getContributors(),researchPaperDetails.getContributorTitle(), false));
            return new ResponseEntity<>(_researchPaperDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/researchPaperDetails")
    public ResponseEntity<List<ResearchPaperDetails>> getAllResearchPaperDetails(@RequestParam(required = false) String name) {
        try {
            List<ResearchPaperDetails> researchPaperDetails = new ArrayList<ResearchPaperDetails>();

            if (name == null)
                researchpaperDetailsRepository.findAll().forEach(researchPaperDetails::add);
            else
                researchpaperDetailsRepository.findByNameContaining(name).forEach(researchPaperDetails::add);

            if (researchPaperDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(researchPaperDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/researchPaperDetails/{id}")
    public ResponseEntity<ResearchPaperDetails> getResearchPaperDetailsById(@PathVariable("id") String id) {
        Optional<ResearchPaperDetails> researchPaperDetailsData = researchpaperDetailsRepository.findById(id);

        if (researchPaperDetailsData.isPresent()) {
            return new ResponseEntity<>(researchPaperDetailsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/researchPaperDetails/{id}")
    public ResponseEntity<ResearchPaperDetails> updateResearchPaperDetails(@PathVariable("id") String id, @RequestBody ResearchPaperDetails researchPaperDetails) {
        Optional<ResearchPaperDetails> researchPaperDetailsData = researchpaperDetailsRepository.findById(id);

        if (researchPaperDetailsData.isPresent()) {
            ResearchPaperDetails _researchPaperDetails = researchPaperDetailsData.get();
            _researchPaperDetails.setName(researchPaperDetails.getName());
            _researchPaperDetails.setTitle(researchPaperDetails.getTitle());
            _researchPaperDetails.setResearchArea(researchPaperDetails.getResearchArea());
            _researchPaperDetails.setPublishedDate(researchPaperDetails.getPublishedDate());
            _researchPaperDetails.setCountry(researchPaperDetails.getCountry());
            _researchPaperDetails.setAuthor(researchPaperDetails.getAuthor());
            _researchPaperDetails.setContributors(researchPaperDetails.getContributors());
            _researchPaperDetails.setContributorTitle(researchPaperDetails.getContributorTitle());
            _researchPaperDetails.setApprovalStatus(researchPaperDetails.isApprovalStatus());

            return new ResponseEntity<>(researchpaperDetailsRepository.save(_researchPaperDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/researchPaperDetails/{id}/approval")
    public ResponseEntity<ResponseMessage> updateFormResearchPaperDetailsApproval(@PathVariable String id) {
        ResearchPaperDetails researchPaperDetails = formResearchPaperDetailsService.getFile(id);

        String message = "";

        try {
            formResearchPaperDetailsService.updateApprovalStatus(id);

            message = "Updated Research details form approval successfully: " + researchPaperDetails.getName();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update Research details form approval: " + researchPaperDetails.getName() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/researchPaperDetails/{id}")
    public ResponseEntity<HttpStatus> deleteResearchPaperDetails(@PathVariable("id") String id) {
        try {
            researchpaperDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
