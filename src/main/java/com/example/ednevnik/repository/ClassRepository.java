package com.example.ednevnik.repository;

import com.example.ednevnik.model.aClass.Class;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassRepository extends MongoRepository<Class, ObjectId> {

    Class findFirstByClassId(Long classId);

    List<Class> findAllByTeacherNumber(Long teacherNumber);

}
