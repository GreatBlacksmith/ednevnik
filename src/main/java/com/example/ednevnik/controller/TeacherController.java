package com.example.ednevnik.controller;

import com.example.ednevnik.model.teacher.Teacher;
import com.example.ednevnik.service.teacher.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Teacher> getStudentsForTeacher(@PathVariable(value = "id") Long teacherId) {

        Teacher teacher = teacherService.getTeacherById(teacherId);
        HttpStatus status = HttpStatus.OK;
        return new ResponseEntity<>(teacher, status);

    }

    @GetMapping("/insert")
    public void insertTeacher() {

        teacherService.insertTeacher();
    }
}
