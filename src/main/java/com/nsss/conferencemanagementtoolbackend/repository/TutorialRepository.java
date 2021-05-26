package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.Tutorial;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TutorialRepository extends MongoRepository<Tutorial, String> {
    List<Tutorial> findByTitleContaining(String title);
    List<Tutorial> findByPublished(boolean published);
}
