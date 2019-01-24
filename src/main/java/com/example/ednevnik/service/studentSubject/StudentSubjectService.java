package com.example.ednevnik.service.studentSubject;

import com.example.ednevnik.model.subject.SubjectDto;

import java.util.List;

public interface StudentSubjectService {

    void saveSubjectsToStudent(List<Long> subjectIds, Long studentId);

    List<SubjectDto> getSubjectsForStudent(Long studentId);

}
