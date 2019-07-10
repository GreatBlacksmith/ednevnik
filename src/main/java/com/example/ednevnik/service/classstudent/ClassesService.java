package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.classes.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;

import java.util.List;

public interface ClassesService {

    List<Student> getStudentsForClassById(Class aClass);

    List<Subject> getSubjectsForClassById(Class aClass);

    void addStudentsToClass(Class aClass);

    Classes addStudentByIdToClass(Class aClass, Long studentId) throws Exception;

    Classes addSubjectByIdToClass(Class aClass, Long subjectId) throws Exception;

    Classes saveNewClassesForClass(Class aClass);

    Class getClassByStudentAndSubject(Student student, Subject subject);
}
