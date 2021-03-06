package com.example.ednevnik.controller;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.aClass.ClassDto;
import com.example.ednevnik.model.classes.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.classstudent.ClassesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;
    private final ClassesService classesService;

    public ClassController(ClassService classService, ClassesService classesService) {
        this.classService = classService;
        this.classesService = classesService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable(value = "id") Long classId) {
        Class aClass = classService.getClassByClassId(classId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(aClass, status);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<Student>> getStudentsForClass(@PathVariable(value = "id") Long classId) {
        Class aClass = null;
        if (classId != null) {
            aClass = classService.getClassByClassId(classId);
        }
        Set<Student> studentsForClass = classesService.getStudentsForClassById(aClass);
        HttpStatus status = HttpStatus.OK;
        if (CollectionUtils.isEmpty(studentsForClass)) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(studentsForClass, status);
    }

    @GetMapping("/{id}/available-students")
    public ResponseEntity<Set<Student>> getAvailableStudentsForClass(@PathVariable(value = "id") Long classId) {
        Class aClass = null;
        if (classId != null) {
            aClass = classService.getClassByClassId(classId);
        }
        Set<Student> studentsForClass = classesService.getAvailableStudentsForClassById(aClass);
        HttpStatus status = HttpStatus.OK;
        if (CollectionUtils.isEmpty(studentsForClass)) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(studentsForClass, status);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<Set<Subject>> getSubjectsForClass(@PathVariable(value = "id") Long classId) {
        Class aClass = null;
        if (classId != null) {
            aClass = classService.getClassByClassId(classId);
        }
        Set<Subject> subjectsForClass = classesService.getSubjectsForClassById(aClass);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(subjectsForClass, status);
    }

    @PostMapping("{id}/add-student")
    public ResponseEntity<Classes> addStudentToClass(@PathVariable(value = "id") String classId, @RequestParam(value = "studentId") Long studentId) {
        HttpStatus status = HttpStatus.OK;
        Classes c;
        if (classId == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        try {
            Class aClass;
            aClass = classService.getClassByClassId(Long.valueOf(classId));
            c = classesService.addStudentByIdToClass(aClass, studentId);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(c, status);
    }

    @PostMapping("/add-subject")
    public ResponseEntity<Classes> addSubjectToClass(@RequestParam(value = "id") String classId, @RequestBody String subjectId) {
        HttpStatus status = HttpStatus.OK;
        Classes c;
        if (classId == null) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        try {
            Class aClass;
            aClass = classService.getClassByClassId(Long.valueOf(classId));
            c = classesService.addSubjectByIdToClass(aClass, Long.valueOf(subjectId));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(c, status);
    }

    @PostMapping("/insert")
    public ResponseEntity<Class> create(@RequestBody ClassDto classDto) {
        Class aClass = classService.saveNewClass(classDto);

        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(aClass, status);
    }
}
