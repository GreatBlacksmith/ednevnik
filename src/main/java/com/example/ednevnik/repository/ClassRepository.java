package com.example.ednevnik.repository;

import com.example.ednevnik.model.aClass.Class;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassRepository extends MongoRepository<Class, ObjectId> {

    Class findFirstByClassId(Long classId);

}
