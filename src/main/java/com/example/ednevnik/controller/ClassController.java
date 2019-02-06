package com.example.ednevnik.controller;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.Classes;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.classstudent.ClassStudentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Classes> getStudentsForClass(@PathVariable(value = "id") Long classId) {

        Classes classes = classStudentsService.getStudentForClassById(classId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(classes, status);
    }

    @PostMapping("/add-student")
    public ResponseEntity<Classes> addStudentToClass(@RequestParam(value = "id") String classId, @RequestBody String studentId) {
        HttpStatus status = HttpStatus.OK;
        Classes c = null;
        if (classId == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        try {
            c = classStudentsService.addStudentByIdToClass(Long.valueOf(classId), Long.valueOf(studentId));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(c, status);
    }

    @PostMapping("/add-subject")
    public ResponseEntity<Classes> addSubjectToClass(@RequestParam(value = "id") String classId, @RequestBody String subjectId) {
        HttpStatus status = HttpStatus.OK;
        Classes c = null;
        if (classId == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        try {
            c = classStudentsService.addSubjectByIdToClass(Long.valueOf(classId), Long.valueOf(subjectId));
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(status);
        }
        return new ResponseEntity<>(c, status);
    }

//    @GetMapping("/insert")
//    public void create() {
//        classService.insertNewClass();
//    }
}
