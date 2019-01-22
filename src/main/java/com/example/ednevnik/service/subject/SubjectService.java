package com.example.ednevnik.service.subject;

import com.example.ednevnik.model.Subject;

import java.util.List;

public interface SubjectService {

    Subject saveSubject(Subject subject);

    List<Subject> getAll();

    Subject findOneById(Long subjectId);
}
