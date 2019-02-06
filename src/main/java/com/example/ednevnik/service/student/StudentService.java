package com.example.ednevnik.service.student;

import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.student.StudentDto;

import java.util.List;

public interface StudentService {

    Student saveStudent(StudentDto studentDto);

    Student saveStudent(Student student);

    List<Student> getAllStudents();

    Student findOneByStudentId(Long studentId);
}
