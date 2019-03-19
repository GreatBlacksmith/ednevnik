package com.example.ednevnik.service.studentSubject;

import com.example.ednevnik.model.StudentSubject;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.model.subject.SubjectDto;
import com.example.ednevnik.repository.StudentSubjectRepository;
import com.example.ednevnik.service.BaseService;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.subject.SubjectService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSubjectServiceImpl extends BaseService implements StudentSubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentSubjectServiceImpl.class);

    private final StudentSubjectRepository repository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public StudentSubjectServiceImpl(ModelMapper modelMapper, StudentSubjectRepository repository, StudentService studentService, SubjectService subjectService) {
        super(modelMapper);
        this.repository = repository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    public StudentSubject save(StudentSubject studentSubject) {
        return repository.save(studentSubject);
    }


    @Override
    public void saveSubjectsToStudent(List<Long> subjectIds, Long studentId) {
        LOGGER.info("Called saveSubjectsToStudent for studentId: {}, and subjectIds: {}", studentId, subjectIds);
        Student student = studentService.findOneByStudentId(studentId);

        subjectIds.forEach(aLong -> {
            Subject subject = subjectService.findOneById(aLong);
            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudent(student);
            studentSubject.setSubject(subject);

            repository.save(studentSubject);
        });
    }

    @Override
    public List<SubjectDto> getSubjectsForStudent(Long studentId) {
        LOGGER.info("Called getSubjectsForStudent() with studentId: {}", studentId);

        Student student = studentService.findOneByStudentId(studentId);

        List<StudentSubject> allByStudentId = repository.findAllByStudent_Id(student.getId());

        return allByStudentId.stream().map(studentSubject -> mapSubjectToDto(studentSubject.getSubject())).collect(Collectors.toList());
    }

    private SubjectDto mapSubjectToDto(Subject subject) {
        SubjectDto subjectDto = mapEntityToDTO(subject, SubjectDto.class);
        subjectDto.setSubjectTypeId(subject.getSubjectType().getCode());
        subject.getClassTypes().forEach(classType -> subjectDto.getClassTypesId().add(classType.getCode()));

        return subjectDto;
    }

    @Override
    public List<SubjectDto> getAvailableSubjectsForStudent(Long studentId) {
        Student student = studentService.findOneByStudentId(studentId);
        List<StudentSubject> allByStudentId = repository.findAllByStudent_Id(student.getId());

        List<Long> subjectIdsForStudent = allByStudentId.stream().map(studentSubject -> studentSubject.getSubject().getSubjectId()).collect(Collectors.toList());

        List<SubjectDto> allByClassType = subjectService.getAllByClassType(student.getClassType());

        List<SubjectDto> availableSubjectsForStudent = allByClassType.stream().filter(subjectDto -> !subjectIdsForStudent.contains(subjectDto.getSubjectId())).collect(Collectors.toList());

        return availableSubjectsForStudent;

    }
}
