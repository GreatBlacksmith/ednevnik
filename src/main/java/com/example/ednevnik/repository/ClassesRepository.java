package com.example.ednevnik.repository;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.classes.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassesRepository extends MongoRepository<Classes, ObjectId> {

    Classes findOneByAClass(Class aClass);

    Classes findFirstByStudentsContainsAndSubjectsContains(Student student, Subject subject);

    Classes findFirstByStudentsContainsAndSubjectsContains(ObjectId student, ObjectId subject);
}
