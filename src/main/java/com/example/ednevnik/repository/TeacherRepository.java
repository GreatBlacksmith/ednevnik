package com.example.ednevnik.repository;

import com.example.ednevnik.model.teacher.Teacher;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, ObjectId> {

    Teacher findOneByTeacherNumber(Long TeacherNumber);
}
