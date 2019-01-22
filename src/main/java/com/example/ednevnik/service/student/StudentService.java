package com.example.ednevnik.service.student;

import com.example.ednevnik.model.Student;

import java.util.List;

public interface StudentService {

    Student save(Student student);

    List<Student> getAllStudents();

    Student findOneByStudentId(Long studentId);
}
