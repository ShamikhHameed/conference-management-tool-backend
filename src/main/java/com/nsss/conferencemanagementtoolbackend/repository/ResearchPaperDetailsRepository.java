package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.ResearchPaperDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ResearchPaperDetailsRepository extends MongoRepository<ResearchPaperDetails, String> {
    List<ResearchPaperDetails> findByNameContaining(String name);
}
