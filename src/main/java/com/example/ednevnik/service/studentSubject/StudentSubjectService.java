package com.example.ednevnik.service.studentSubject;

import com.example.ednevnik.model.studentSubject.StudentSubjectDto;
import com.example.ednevnik.model.studentSubject.StudentSubjectRequest;
import com.example.ednevnik.model.subject.SubjectDto;

import java.util.List;

public interface StudentSubjectService {

    void saveSubjectsToStudent(List<Long> subjectIds, Long studentId);

    List<SubjectDto> getSubjectsForStudent(Long studentId);

    List<SubjectDto> getAvailableSubjectsForStudent(Long studentId);

    StudentSubjectDto getStudentSubjectDtoByStudentIdAndSubjectId(Long studentId, Long subjectId);

    StudentSubjectDto addGradeToStudentSubject(StudentSubjectRequest request);

}
