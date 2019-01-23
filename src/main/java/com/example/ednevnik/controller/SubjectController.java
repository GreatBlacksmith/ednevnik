package com.example.ednevnik.controller;

import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.model.subject.SubjectDto;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.studentSubject.StudentSubjectService;
import com.example.ednevnik.service.subject.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final StudentSubjectService studentSubjectService;
    private final CounterSequenceService counterSequenceService;

    public SubjectController(SubjectService subjectService, StudentSubjectService studentSubjectService, CounterSequenceService counterSequenceService) {
        this.subjectService = subjectService;
        this.studentSubjectService = studentSubjectService;
        this.counterSequenceService = counterSequenceService;
    }

    @GetMapping()
    public ResponseEntity<List<Subject>> getAll() {
        List<Subject> allSubjects = subjectService.getAll();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(allSubjects, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable(value = "id") Long subjectId) {
        Subject subject = subjectService.findOneById(subjectId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(subject, status);
    }

    @PostMapping("/insert")
    public ResponseEntity<Subject> insertStudent(@RequestBody SubjectDto subjectDto) {

        Subject subject = subjectService.saveSubject(subjectDto);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(subject, status);
    }
}
