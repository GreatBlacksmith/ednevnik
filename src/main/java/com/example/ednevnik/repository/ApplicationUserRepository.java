package com.example.ednevnik.repository;

import com.example.ednevnik.model.user.ApplicationUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, ObjectId> {

    ApplicationUser findByUsername(String username);
}
