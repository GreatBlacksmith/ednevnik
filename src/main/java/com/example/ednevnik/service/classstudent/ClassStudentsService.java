package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.ClassStudents;

public interface ClassStudentsService {

    ClassStudents getStudentForClassById(Long classId);

    void addStudentsToClass(Class aClass);
}
