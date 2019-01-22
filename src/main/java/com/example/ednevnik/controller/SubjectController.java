package com.example.ednevnik.controller;

import com.example.ednevnik.model.Student;
import com.example.ednevnik.model.StudentSubject;
import com.example.ednevnik.model.Subject;
import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.codebook.SubjectType;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.studentSubject.StudentSubjectService;
import com.example.ednevnik.service.subject.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;
    private final StudentService studentService;
    private final StudentSubjectService studentSubjectService;
    private final CounterSequenceService counterSequenceService;

    public SubjectController(SubjectService subjectService, StudentService studentService, StudentSubjectService studentSubjectService, CounterSequenceService counterSequenceService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
        this.studentSubjectService = studentSubjectService;
        this.counterSequenceService = counterSequenceService;
    }

    @GetMapping("/test")
    public ResponseEntity<StudentSubject> test() {

        Subject subject = new Subject();
        subject.setSubjectId(counterSequenceService.getNextSequenceValue("subject"));
        subject.setName("TestName");
        subject.setDescription("Descriptiontest");
        subject.setSubjectType(SubjectType.ZNANSTVENI);

        List<ClassType> classes = new ArrayList<>();
        classes.add(ClassType.TREĆI);
        classes.add(ClassType.ČETVRTI);
        subject.setClassTypes(classes);

        Subject subject1 = subjectService.saveSubject(subject);

        Student student = new Student();
        student.setName("marko");
        student.setLastName("markic");
        student.setStudentNumber(12313131L);
        student.setClassType(ClassType.ČETVRTI);

        Student student1 = studentService.save(student);

        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(student1);
        studentSubject.setSubject(subject1);
        studentSubject.setAverage(3.321);
        studentSubject.getGrades().add(4);
        studentSubject.getGrades().add(3);
        studentSubject.getGrades().add(3);

        StudentSubject studentSubject1 = studentSubjectService.save(studentSubject);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(studentSubject1, status);
    }
}
