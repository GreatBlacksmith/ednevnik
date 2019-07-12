package com.example.ednevnik.controller;

import com.example.ednevnik.model.studentSubject.StudentSubjectDto;
import com.example.ednevnik.model.studentSubject.StudentSubjectRequest;
import com.example.ednevnik.service.studentSubject.StudentSubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student-subject")
public class StudentSubjectController {

    private final StudentSubjectService studentSubjectService;

    public StudentSubjectController(StudentSubjectService studentSubjectService) {
        this.studentSubjectService = studentSubjectService;
    }

    @GetMapping("/{studentId}/{subjectId}")
    public ResponseEntity<StudentSubjectDto> getStudent(@PathVariable(value = "studentId") Long studentId, @PathVariable(value = "subjectId") Long subjectId) {
        StudentSubjectDto studentSubjectDto = studentSubjectService.getStudentSubjectDtoByStudentIdAndSubjectId(studentId, subjectId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(studentSubjectDto, status);
    }

    @PostMapping()
    public ResponseEntity<StudentSubjectDto> addGradeToStudentSubject(@RequestBody @Valid StudentSubjectRequest request) {

        StudentSubjectDto studentSubjectDto = studentSubjectService.addGradeToStudentSubject(request);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(studentSubjectDto, status);
    }
}
