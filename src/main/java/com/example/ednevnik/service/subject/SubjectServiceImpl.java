package com.example.ednevnik.service.subject;

import com.example.ednevnik.model.Subject;
import com.example.ednevnik.repository.SubjectRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAll() {
        LOGGER.info("Calling getAll() for Subjects");
        return subjectRepository.findAll();
    }

    @Override
    public Subject findOneById(Long subjectId) {
        LOGGER.info("Calling findOneById() for subject with id: {}", subjectId);
        return subjectRepository.findOneBySubjectId(subjectId);
    }
}
