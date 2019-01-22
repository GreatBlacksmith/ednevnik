package com.example.ednevnik.repository;

import com.example.ednevnik.model.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {

    Student findOneByStudentId(Long studentId);
}
