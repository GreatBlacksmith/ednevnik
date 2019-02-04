package com.example.ednevnik.repository;

import com.example.ednevnik.model.ClassStudents;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassStudentsRepository extends MongoRepository<ClassStudents, ObjectId> {

    ClassStudents findOneByAClass_ClassId(Long classId);
}
