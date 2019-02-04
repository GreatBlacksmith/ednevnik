package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.ClassStudents;
import com.example.ednevnik.repository.ClassStudentsRepository;
import com.example.ednevnik.service.student.StudentService;
import org.springframework.stereotype.Service;

@Service
public class ClassStudentsServiceImpl implements ClassStudentsService {

    private final ClassStudentsRepository repository;
    private final StudentService studentService;

    public ClassStudentsServiceImpl(ClassStudentsRepository repository, StudentService studentService) {
        this.repository = repository;
        this.studentService = studentService;
    }

    @Override
    public ClassStudents getStudentForClassById(Long classId) {
        return repository.findOneByAClass_ClassId(classId);
    }

    @Override
    public void addStudentsToClass(Class aClass) {
        ClassStudents classStudents = new ClassStudents();
        classStudents.setAClass(aClass);
        classStudents.setStudents(studentService.getAllStudents());

        repository.save(classStudents);
    }
}
