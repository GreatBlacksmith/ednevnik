package com.example.ednevnik.controller;

import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.student.StudentDto;
import com.example.ednevnik.model.subject.SubjectDto;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.studentSubject.StudentSubjectService;
import com.example.ednevnik.service.subject.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentSubjectService studentSubjectService;
    private final SubjectService subjectService;

    public StudentController(StudentService studentService, StudentSubjectService studentSubjectService, SubjectService subjectService) {
        this.studentService = studentService;
        this.studentSubjectService = studentSubjectService;
        this.subjectService = subjectService;
    }

    @GetMapping()
    public ResponseEntity<Set<Student>> getAll() {
        Set<Student> allStudents = studentService.getAllStudents();
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(allStudents, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable(value = "id") Long studentId) {
        Student student = studentService.findOneByStudentId(studentId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(student, status);
    }

    @PostMapping("/insert")
    public ResponseEntity<Student> insertStudent(@RequestBody StudentDto studentDto) {

        Student studentSave = studentService.saveStudent(studentDto);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(studentSave, status);
    }

    @PostMapping("/{id}/add-subjects")
    public ResponseEntity<Boolean> addSubjectsToStudent(@PathVariable(value = "id") Long studentId,
                                                        @RequestParam(value = "subjects") List<Long> subjects) {

        studentSubjectService.saveSubjectsToStudent(subjects, studentId);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(true, status);
    }

    @PostMapping("/{id}/add-subject")
    public ResponseEntity<Boolean> addSubjectToStudent(@PathVariable(value = "id") Long studentId,
                                                       @RequestParam(value = "subjectId") Long subjectId) {

        studentSubjectService.saveSubjectToStudent(subjectId, studentId);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(true, status);
    }

    @GetMapping("/{id}/available-subjects")
    public ResponseEntity<List<SubjectDto>> getAvailableSubjectsForStudent(@PathVariable(value = "id") Long studentId) {

        List<SubjectDto> availableSubjectsForStudent = studentSubjectService.getAvailableSubjectsForStudent(studentId);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(availableSubjectsForStudent, status);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<SubjectDto>> getSubjectsForStudent(@PathVariable(value = "id") Long studentId) {

        //TODO add class to!
        List<SubjectDto> subjectsForStudent = studentSubjectService.getSubjectsForStudent(studentId);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(subjectsForStudent, status);
    }
}
