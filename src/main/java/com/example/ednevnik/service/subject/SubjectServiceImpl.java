package com.example.ednevnik.service.subject;

import com.example.ednevnik.model.Subject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final MongoTemplate mongoTemplate;

    public SubjectServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return mongoTemplate.save(subject);
    }
}
