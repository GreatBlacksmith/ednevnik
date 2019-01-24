package com.example.ednevnik.service.subject;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.model.subject.SubjectDto;

import java.util.List;

public interface SubjectService {

    Subject saveSubject(SubjectDto subjectDto);

    List<Subject> getAll();

    List<SubjectDto> getAllByClassType(ClassType classType);

    Subject findOneById(Long subjectId);
}
