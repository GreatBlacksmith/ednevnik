package com.example.ednevnik.controller;

import com.example.ednevnik.model.studentSubject.StudentSubjectDto;
import com.example.ednevnik.service.studentSubject.StudentSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-subject")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("/{studentId}/{subjectId}")
    public ResponseEntity<StudentSubjectDto> getStudent(@PathVariable(value = "studentId") Long studentId, @PathVariable(value = "subjectId") Long subjectId) {
        StudentSubjectDto studentSubjectDto = studentSubjectService.getStudentSubjectByStudentIdAndSubjectId(studentId, subjectId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(studentSubjectDto, status);
    }
}
