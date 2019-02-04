package com.example.ednevnik.service.teacher;

import com.example.ednevnik.model.teacher.Teacher;

public interface TeacherService {

    Teacher getTeacherById(Long teacherNumber);

    void insertTeacher();
}
