package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.WorkshopFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkshopFileRepository extends MongoRepository<WorkshopFile, String> {
}
