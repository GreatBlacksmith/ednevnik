package com.example.ednevnik.service.teacher;

import com.example.ednevnik.model.teacher.Teacher;
import com.example.ednevnik.model.teacher.TeacherSubject;
import com.example.ednevnik.repository.TeacherRepository;
import com.example.ednevnik.repository.TeacherSubjectRepository;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.counterSequence.SequenceKeys;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.subject.SubjectService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CounterSequenceService sequenceService;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final SubjectService subjectService;
    private final StudentService studentService;

    public TeacherServiceImpl(TeacherRepository teacherRepository, CounterSequenceService sequenceService, TeacherSubjectRepository teacherSubjectRepository, SubjectService subjectService, StudentService studentService) {
        this.teacherRepository = teacherRepository;
        this.sequenceService = sequenceService;
        this.teacherSubjectRepository = teacherSubjectRepository;
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

    @Override
    public Teacher getTeacherById(Long teacherNumber) {
        return teacherRepository.findOneByTeacherNumber(teacherNumber);
    }

    @Override
    public void insertTeacher() {
        Teacher teacher = new Teacher();

        teacher.setName("Name");
        teacher.setLastName("LastName");
        teacher.setTeacherNumber(sequenceService.getNextSequenceValue(SequenceKeys.TEACHER.getKey()));
        Teacher teachersaved = teacherRepository.save(teacher);


        TeacherSubject teacherSubject = new TeacherSubject();

        teacherSubject.setTeacher(teachersaved);
        teacherSubject.setSubject(subjectService.findOneById(1L));

        teacherSubjectRepository.save(teacherSubject);
    }
}
