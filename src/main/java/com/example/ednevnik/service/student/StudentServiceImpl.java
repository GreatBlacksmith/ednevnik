package com.example.ednevnik.service.student;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.student.StudentDto;
import com.example.ednevnik.repository.StudentRepository;
import com.example.ednevnik.service.BaseService;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.counterSequence.SequenceKeys;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends BaseService implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final CounterSequenceService sequenceService;

    public StudentServiceImpl(ModelMapper modelMapper, StudentRepository studentRepository, CounterSequenceService sequenceService) {
        super(modelMapper);
        this.studentRepository = studentRepository;
        this.sequenceService = sequenceService;
    }

    @Override
    public Student saveStudent(StudentDto studentDto) {

        Student student = mapEntityToDTO(studentDto, Student.class);
        student.setClassType(ClassType.getByCode(studentDto.getClassTypeId()));
        student.setStudentId(sequenceService.getNextSequenceValue(SequenceKeys.STUDENT.getKey()));
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
