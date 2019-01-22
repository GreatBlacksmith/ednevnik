package com.example.ednevnik.service.student;

import com.example.ednevnik.model.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final MongoTemplate mongoTemplate;

    public StudentServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Student save(Student student) {
        return mongoTemplate.save(student);
    }
}
