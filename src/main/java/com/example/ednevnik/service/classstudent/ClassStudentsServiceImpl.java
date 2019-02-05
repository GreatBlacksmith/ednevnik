package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.ClassStudents;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.repository.ClassStudentsRepository;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.student.StudentService;
import org.springframework.stereotype.Service;

@Service
public class ClassStudentsServiceImpl implements ClassStudentsService {

    private final ClassStudentsRepository repository;
    private final StudentService studentService;
    private final ClassService classService;

    public ClassStudentsServiceImpl(ClassStudentsRepository repository, StudentService studentService, ClassService classService) {
        this.repository = repository;
        this.studentService = studentService;
        this.classService = classService;
    }

    @Override
    public ClassStudents getStudentForClassById(Long classId) {
        Class aClass = classService.getClassByClassId(classId);
        return repository.findOneByAClass(aClass);
    }

    @Override
    public void addStudentsToClass(Class aClass) {
        ClassStudents classStudents = new ClassStudents();
        classStudents.setAClass(aClass);
        classStudents.setStudents(studentService.getAllStudents());

        repository.save(classStudents);
    }

    @Override
    public ClassStudents addStudentToCass(Long classId, Long studentId) throws Exception {
        Student student = studentService.findOneByStudentId(studentId);
        Class aClass = classService.getClassByClassId(classId);
        if (aClass == null || student == null) {
            throw new Exception("Dodat custom exception");
        }

        ClassStudents classStudent = repository.findOneByAClass(aClass);

        classStudent.getStudents().add(student);

        return repository.save(classStudent);
    }
}
