package com.example.ednevnik.controller;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.ClassStudents;
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
    public ResponseEntity<ClassStudents> getStudentsForClass(@PathVariable(value = "id") Long classId) {

        ClassStudents classStudents = classStudentsService.getStudentForClassById(classId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(classStudents, status);
    }

    @PostMapping("/add")
    public ResponseEntity<ClassStudents> addStudentToClass(@RequestParam(value = "id") String classId, @RequestBody String studentId) {
        HttpStatus status = HttpStatus.OK;
        ClassStudents c = null;
        if (classId == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        try {
            c = classStudentsService.addStudentToCass(Long.valueOf(classId), Long.valueOf(studentId));
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
