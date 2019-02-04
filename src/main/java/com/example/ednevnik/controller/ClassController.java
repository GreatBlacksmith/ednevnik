package com.example.ednevnik.controller;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.ClassStudents;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.classstudent.ClassStudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/class")
public class ClassController {

    private final ClassService classService;
    private final ClassStudentsService classStudentsService;

    public ClassController(ClassService classService, ClassStudentsService classStudentsService) {
        this.classService = classService;
        this.classStudentsService = classStudentsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable(value = "id") Long classId) {
        Class aClass = classService.getClassByClassId(classId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(aClass, status);
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<ClassStudents> getStudentsForClass(@PathVariable(value = "id") Long classId) {

        ClassStudents classStudents = classStudentsService.getStudentForClassById(classId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(classStudents, status);
    }


    @GetMapping("/insert")
    public void create() {
        classService.insertNewClass();
    }
}
