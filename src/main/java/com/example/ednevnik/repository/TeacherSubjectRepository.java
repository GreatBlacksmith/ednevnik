package com.example.ednevnik.repository;

import com.example.ednevnik.model.teacher.TeacherSubject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherSubjectRepository extends MongoRepository<TeacherSubject, ObjectId> {
}
