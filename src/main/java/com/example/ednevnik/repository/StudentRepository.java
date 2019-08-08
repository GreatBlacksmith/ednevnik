package com.example.ednevnik.repository;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.student.Student;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

public interface StudentRepository extends MongoRepository<Student, ObjectId> {

    Student findOneByStudentId(Long studentId);

    Set<Student> findAllByACLass(Class aClass);
}