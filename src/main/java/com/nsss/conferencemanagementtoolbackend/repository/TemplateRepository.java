package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TemplateRepository extends MongoRepository<Template, String> {
}
