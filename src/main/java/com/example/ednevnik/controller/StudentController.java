package com.example.ednevnik.controller;

import com.example.ednevnik.model.Student;
import com.example.ednevnik.service.student.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<Student>> getAll() {
        List<Student> allStudents = studentService.getAllStudents();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(allStudents, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getSubject(@PathVariable(value = "id") Long studentId) {
        Student student = studentService.findOneByStudentId(studentId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(student, status);
    }
}
