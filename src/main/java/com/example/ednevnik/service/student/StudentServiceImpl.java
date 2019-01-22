package com.example.ednevnik.service.student;

import com.example.ednevnik.model.Student;
import com.example.ednevnik.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        LOGGER.info("Called getAllStudents()");
        return studentRepository.findAll();
    }

    @Override
    public Student findOneByStudentId(Long studentId) {
        LOGGER.info("Called findOneByStudentId() with studentId: {}", studentId);
        return studentRepository.findOneByStudentId(studentId);
    }
}
