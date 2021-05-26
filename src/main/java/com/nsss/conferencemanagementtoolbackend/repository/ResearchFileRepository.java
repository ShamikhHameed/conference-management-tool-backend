package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.ResearchFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResearchFileRepository extends MongoRepository<ResearchFile, String> {
}
