package com.example.ednevnik.repository;

import com.example.ednevnik.model.Subject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubjectRepository extends MongoRepository<Subject, ObjectId> {

    Subject findOneBySubjectId(Long subjectId);
}
