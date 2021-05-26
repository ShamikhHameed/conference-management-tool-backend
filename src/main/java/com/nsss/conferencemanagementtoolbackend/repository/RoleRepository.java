package com.nsss.conferencemanagementtoolbackend.repository;

import com.nsss.conferencemanagementtoolbackend.model.ERole;
import com.nsss.conferencemanagementtoolbackend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
