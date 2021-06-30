package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.message.ResponseMessage;
import com.nsss.conferencemanagementtoolbackend.model.WorkshopDetails;
import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import com.nsss.conferencemanagementtoolbackend.repository.WorkshopDetailsRepository;
import com.nsss.conferencemanagementtoolbackend.services.FormWorkshopDetailsService;
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
public class WorkshopDetailsController {
    @Autowired
    private WorkshopDetailsRepository workshopDetailsRepository;
    @Autowired
    private FormWorkshopDetailsService formWorkshopDetailsService;

    @PostMapping("/workshopDetails")
    public ResponseEntity<WorkshopDetails> createWorkshopDetails(@RequestBody WorkshopDetails workshopDetails) {
        try {
            WorkshopDetails _workshopDetails = workshopDetailsRepository.save(new WorkshopDetails(workshopDetails.getTitle(),workshopDetails.getTime(),workshopDetails.getPlace(),workshopDetails.getStartDate(),workshopDetails.getNoOfDays(),workshopDetails.getSpeakers(),workshopDetails.getSpeakerInstitutes(), false));
            return new ResponseEntity<>(_workshopDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/workshopDetails")
    public ResponseEntity<List<WorkshopDetails>> getAllWorkshopDetails(@RequestParam(required = false) String title) {
        try {
            List<WorkshopDetails> workshopDetails = new ArrayList<WorkshopDetails>();

            if (title == null)
                workshopDetailsRepository.findAll().forEach(workshopDetails::add);
            else
                workshopDetailsRepository.findByTitleContaining(title).forEach(workshopDetails::add);

            if (workshopDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshopDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/workshopDetails/{id}")
    public ResponseEntity<WorkshopDetails> getWorkshopById(@PathVariable("id") String id) {
        Optional<WorkshopDetails> workshopDetailsData = workshopDetailsRepository.findById(id);

        if (workshopDetailsData.isPresent()) {
            return new ResponseEntity<>(workshopDetailsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/workshopDetails/{id}")
    public ResponseEntity<WorkshopDetails> updateWorkshopDetails(@PathVariable("id") String id, @RequestBody WorkshopDetails workshopDetails) {
        Optional<WorkshopDetails> workshopDetailsData = workshopDetailsRepository.findById(id);

        if (workshopDetailsData.isPresent()) {
            WorkshopDetails _workshopDetails = workshopDetailsData.get();
            _workshopDetails.setTitle(workshopDetails.getTitle());
            _workshopDetails.setTime(workshopDetails.getTime());
            _workshopDetails.setPlace(workshopDetails.getPlace());
            _workshopDetails.setStartDate(workshopDetails.getStartDate());
            _workshopDetails.setNoOfDays(workshopDetails.getNoOfDays());
            _workshopDetails.setSpeakers(workshopDetails.getSpeakers());
            _workshopDetails.setSpeakerInstitutes(workshopDetails.getSpeakerInstitutes());
            _workshopDetails.setApprovalStatus(workshopDetails.isApprovalStatus());

            return new ResponseEntity<>(workshopDetailsRepository.save(_workshopDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/workshopDetails/{id}/approval")
    public ResponseEntity<ResponseMessage> updateFormWorkshopDetailsApproval(@PathVariable String id) {
        WorkshopDetails workshopDetails = formWorkshopDetailsService.getFile(id);

        String message = "";

        try {
            formWorkshopDetailsService.updateApprovalStatus(id);

            message = "Updated workshop details form approval successfully: " + workshopDetails.getTitle();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not update workshop details form approval: " + workshopDetails.getTitle() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @DeleteMapping("/workshopDetails/{id}")
    public ResponseEntity<HttpStatus> deleteWorkshopDetails(@PathVariable("id") String id) {
        try {
            workshopDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
