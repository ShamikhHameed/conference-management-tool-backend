package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.WorkshopDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WorkshopDetailsRepository extends MongoRepository<WorkshopDetails, String> {
    List<WorkshopDetails> findByTitleContaining(String title);
}