package com.example.ednevnik.controller;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.teacher.Teacher;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.teacher.TeacherService;
import com.example.ednevnik.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final ClassService classService;

    public TeacherController(TeacherService teacherService, ClassService classService) {
        this.teacherService = teacherService;
        this.classService = classService;
    }

    @GetMapping()
    public ResponseEntity<Teacher> getTeacherById() {

        Teacher teacher = teacherService.getTeacherById(UserUtil.getTeacherNumber());
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(teacher, status);

    }

    @GetMapping("/classes")
    public ResponseEntity<List<Class>> getClassesForTeacher() {

        List<Class> classesByTeacherNumber = classService.getClassesByTeacherNumber(UserUtil.getTeacherNumber());
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(classesByTeacherNumber, status);
    }

    @GetMapping("/insert")
    public void insertTeacher() {

        teacherService.insertTeacher();
    }
}
