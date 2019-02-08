package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.repository.ClassesRepository;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.subject.SubjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository repository;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final ClassService classService;

    public ClassesServiceImpl(ClassesRepository repository, StudentService studentService, SubjectService subjectService, ClassService classService) {
        this.repository = repository;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.classService = classService;
    }

    @Override
    public List<Student> getStudentsForClassById(Long classId) {
        Class aClass = classService.getClassByClassId(classId);
        return repository.findOneByAClass(aClass).getStudents();
    }

    @Override
    public List<Subject> getSubjectsForClassById(Long classId) {
        Class aClass = classService.getClassByClassId(classId);
        return repository.findOneByAClass(aClass).getSubjects();
    }

    @Override
    public void addStudentsToClass(Class aClass) {
        Classes classes = new Classes();
        classes.setAClass(aClass);
        classes.setStudents(studentService.getAllStudents());

        repository.save(classes);
    }

    @Override
    public Classes addStudentByIdToClass(Long classId, Long studentId) throws Exception {
        Student student = studentService.findOneByStudentId(studentId);
        Class aClass = classService.getClassByClassId(classId);
        if (aClass == null || student == null) {
            throw new Exception("Dodat custom exception");
        }

        student.setACLass(aClass);
        studentService.saveStudent(student);
        Classes classes = repository.findOneByAClass(aClass);

        if (classes.getStudents() == null) {
            classes.setSubjects(new ArrayList<>());
        }
        classes.getStudents().add(student);

        return repository.save(classes);
    }

    @Override
    public Classes addSubjectByIdToClass(Long classId, Long subjectId) throws Exception {
        Class aClass = classService.getClassByClassId(classId);
        Subject subject = subjectService.findOneById(subjectId);

        if (aClass == null || subject == null) {
            throw new Exception("Dodat custom exception");
        }

        Classes classes = repository.findOneByAClass(aClass);
        if (classes.getSubjects() == null) {
            classes.setSubjects(new ArrayList<>());
        }
        classes.getSubjects().add(subject);

        return repository.save(classes);
    }


}
