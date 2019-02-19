package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.classes.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;

import java.util.List;

public interface ClassesService {

    List<Student> getStudentsForClassById(Long classId);

    List<Subject> getSubjectsForClassById(Long classId);

    void addStudentsToClass(Class aClass);

    Classes addStudentByIdToClass(Long classId, Long studentId) throws Exception;

    Classes addSubjectByIdToClass(Long classId, Long subjectId) throws Exception;
}
