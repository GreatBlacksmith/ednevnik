package com.example.ednevnik.service.student;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.student.StudentDto;

import java.util.Set;

public interface StudentService {

    Student saveStudent(StudentDto studentDto);

    Student saveStudent(Student student);

    Set<Student> getAllStudents();

    Set<Student> getAllStudentsForClass(Class aClass);

    Student findOneByStudentId(Long studentId);
}
