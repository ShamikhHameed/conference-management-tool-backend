package com.nsss.conferencemanagementtoolbackend.controller;

import com.nsss.conferencemanagementtoolbackend.model.WorkshopDetails;
import com.nsss.conferencemanagementtoolbackend.repository.WorkshopDetailsRepository;
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

    @PostMapping("/workshopDetails")
    public ResponseEntity<WorkshopDetails> createWorkshopDetails(@RequestBody WorkshopDetails workshopDetails) {
        try {
            WorkshopDetails _workshopDetails = workshopDetailsRepository.save(new WorkshopDetails(workshopDetails.getName(),workshopDetails.getInstitute(),workshopDetails.getStartDate(),workshopDetails.getNoOfDays(),workshopDetails.getSpeakers(),workshopDetails.getSpeakerInstitute(), false));
            return new ResponseEntity<>(_workshopDetails, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/workshopDetails")
    public ResponseEntity<List<WorkshopDetails>> getAllWorkshopDetails(@RequestParam(required = false) String name) {
        try {
            List<WorkshopDetails> workshopDetails = new ArrayList<WorkshopDetails>();

            if (name == null)
                workshopDetailsRepository.findAll().forEach(workshopDetails::add);
            else
                workshopDetailsRepository.findByNameContaining(name).forEach(workshopDetails::add);

            if (workshopDetails.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshopDetails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/workshopDetails/{id}")
    public ResponseEntity<WorkshopDetails> getWorkshopDetailsById(@PathVariable("id") String id) {
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
            _workshopDetails.setName(workshopDetails.getName());
            _workshopDetails.setInstitute(workshopDetails.getInstitute());
            _workshopDetails.setStartDate(workshopDetails.getStartDate());
            _workshopDetails.setNoOfDays(workshopDetails.getNoOfDays());
            _workshopDetails.setSpeakers(workshopDetails.getSpeakers());
            _workshopDetails.setSpeakerInstitute(workshopDetails.getSpeakerInstitute());

            return new ResponseEntity<>(workshopDetailsRepository.save(_workshopDetails), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
