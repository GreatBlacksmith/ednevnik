package com.example.ednevnik.repository;

import com.example.ednevnik.model.studentSubject.StudentSubject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StudentSubjectRepository extends MongoRepository<StudentSubject, ObjectId> {

    List<StudentSubject> findAllByStudent_Id(ObjectId id);

    StudentSubject findAllByStudent_IdAndSubject_Id(ObjectId studentId, ObjectId subjectId);
}
