package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.ConferenceDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConferenceDetailsRepository extends MongoRepository<ConferenceDetails, String> {
    List<ConferenceDetails> findByNameContaining(String name);
}
