package com.example.ednevnik.repository;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.Classes;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassesRepository extends MongoRepository<Classes, ObjectId> {

    Classes findOneByAClass(Class aClass);
}
