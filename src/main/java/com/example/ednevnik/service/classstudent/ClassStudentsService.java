package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.Classes;

public interface ClassStudentsService {

    Classes getStudentForClassById(Long classId);

    void addStudentsToClass(Class aClass);

    Classes addStudentByIdToClass(Long classId, Long studentId) throws Exception;

    Classes addSubjectByIdToClass(Long classId, Long subjectId) throws Exception;
}
