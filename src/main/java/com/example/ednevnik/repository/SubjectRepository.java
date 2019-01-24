package com.example.ednevnik.repository;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.subject.Subject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<Subject, ObjectId> {

    Subject findOneBySubjectId(Long subjectId);

    List<Subject> findAllByClassTypesContains(ClassType classType);
}
