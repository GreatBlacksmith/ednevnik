package com.example.ednevnik.service.classstudent;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.classes.Classes;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.repository.ClassesRepository;
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

    public ClassesServiceImpl(ClassesRepository repository, StudentService studentService, SubjectService subjectService) {
        this.repository = repository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @Override
    public List<Student> getStudentsForClassById(Class aClass) {
        Classes classesForClass = repository.findOneByAClass(aClass);

        if (classesForClass == null)
            return null;

        return repository.findOneByAClass(aClass).getStudents();
    }

    @Override
    public List<Subject> getSubjectsForClassById(Class aClass) {
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
    public Classes addStudentByIdToClass(Class aClass, Long studentId) throws Exception {
        Student student = studentService.findOneByStudentId(studentId);
        if (aClass == null || student == null) {
            throw new Exception("Dodat custom exception");
        }

        student.setACLass(aClass);
        studentService.saveStudent(student);
        Classes classes = repository.findOneByAClass(aClass);

        //TODO provjerit da classes nije null

        if (classes.getStudents() == null) {
            classes.setStudents(new ArrayList<>());
        }
        classes.getStudents().add(student);

        return repository.save(classes);
    }

    @Override
    public Classes addSubjectByIdToClass(Class aClass, Long subjectId) throws Exception {
        Subject subject = subjectService.findOneBySubjectId(subjectId);

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

    @Override
    public Classes saveNewClassesForClass(Class aClass) {
        Classes classes = new Classes();
        classes.setAClass(aClass);
        return repository.save(classes);
    }

    @Override
    public Class getClassByStudentAndSubject(Student student, Subject subject) {

        Classes classes = repository.findFirstByStudentsContainsAndSubjectsContains(student, subject);
        return classes.getAClass();
    }
}
