package com.example.ednevnik.service.studentSubject;

import com.example.ednevnik.model.StudentSubject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final MongoTemplate mongoTemplate;

    public StudentSubjectServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public StudentSubject save(StudentSubject studentSubject) {
        return mongoTemplate.save(studentSubject);
    }
}
