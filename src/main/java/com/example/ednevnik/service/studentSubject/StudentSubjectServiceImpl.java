package com.example.ednevnik.service.studentSubject;

import com.example.ednevnik.model.Grade;
import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.studentSubject.StudentSubject;
import com.example.ednevnik.model.studentSubject.StudentSubjectDto;
import com.example.ednevnik.model.studentSubject.StudentSubjectRequest;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.model.subject.SubjectDto;
import com.example.ednevnik.repository.StudentSubjectRepository;
import com.example.ednevnik.service.BaseService;
import com.example.ednevnik.service.aclass.ClassService;
import com.example.ednevnik.service.classstudent.ClassesService;
import com.example.ednevnik.service.student.StudentService;
import com.example.ednevnik.service.subject.SubjectService;
import com.example.ednevnik.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentSubjectServiceImpl extends BaseService implements StudentSubjectService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentSubjectServiceImpl.class);

    private final StudentSubjectRepository repository;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final ClassesService classesService;
    private final ClassService classService;

    public StudentSubjectServiceImpl(ModelMapper modelMapper, StudentSubjectRepository repository, StudentService studentService, SubjectService subjectService, ClassesService classesService, ClassService classService) {
        super(modelMapper);
        this.repository = repository;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.classesService = classesService;
        this.classService = classService;
    }

    public StudentSubject save(StudentSubject studentSubject) {
        return repository.save(studentSubject);
    }


    @Override
    public void saveSubjectsToStudent(List<Long> subjectIds, Long studentId) {
        LOGGER.info("Called saveSubjectsToStudent for studentId: {}, and subjectIds: {}", studentId, subjectIds);
        Student student = studentService.findOneByStudentId(studentId);

        subjectIds.forEach(aLong -> {
            Subject subject = subjectService.findOneBySubjectId(aLong);
            StudentSubject studentSubject = new StudentSubject();
            studentSubject.setStudent(student);
            studentSubject.setSubject(subject);

            repository.save(studentSubject);
        });
    }

    @Override
    public void saveSubjectToStudent(Long subjectId, Long studentId) {
        LOGGER.info("Called saveSubjectsToStudent for studentId: {}, and subjectId: {}", studentId, subjectId);
        Student student = studentService.findOneByStudentId(studentId);

        Subject subject = subjectService.findOneBySubjectId(subjectId);
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setStudent(student);
        studentSubject.setSubject(subject);
        studentSubject.setTeacherNumber(UserUtil.getTeacherNumber());
        studentSubject.setAClass(student.getACLass());

        repository.save(studentSubject);
    }

    @Override
    public List<SubjectDto> getSubjectsForStudent(Long studentId) {
        LOGGER.info("Called getSubjectsForStudent() with studentId: {}", studentId);

        Student student = studentService.findOneByStudentId(studentId);

        List<StudentSubject> allByStudentId = repository.findAllByStudent_Id(student.getId());

        return allByStudentId.stream().map(studentSubject -> mapSubjectToDto(studentSubject.getSubject())).collect(Collectors.toList());
    }

    private SubjectDto mapSubjectToDto(Subject subject) {

        SubjectDto subjectDto = new SubjectDto();

        if (subject != null) {
            subjectDto = mapEntityToDTO(subject, SubjectDto.class);
            subjectDto.setSubjectTypeId(subject.getSubjectType().getCode());
            for (ClassType classType : subject.getClassTypes()) {
                subjectDto.getClassTypesId().add(classType.getCode());
            }
        }

        return subjectDto;
    }

    @Override
    public List<SubjectDto> getAvailableSubjectsForStudent(Long studentId) {
        Student student = studentService.findOneByStudentId(studentId);
        List<StudentSubject> allByStudentId = repository.findAllByStudent_Id(student.getId());

        List<Long> subjectIdsForStudent = new ArrayList<>();
        for (StudentSubject studentSubject : allByStudentId) {
            if (studentSubject.getSubject() != null) {
                Long subjectId = studentSubject.getSubject().getSubjectId();
                subjectIdsForStudent.add(subjectId);
            }
        }

        List<SubjectDto> allByClassType = subjectService.getAllByClassType(student.getClassType());

        List<SubjectDto> availableSubjectsForStudent = allByClassType.stream().filter(subjectDto -> !subjectIdsForStudent.contains(subjectDto.getSubjectId())).collect(Collectors.toList());

        return availableSubjectsForStudent;

    }

    @Override
    public StudentSubjectDto getStudentSubjectDtoByStudentIdAndSubjectId(Long studentId, Long subjectId) {

        LOGGER.info("Called getStudentSubjectByStudentIdAndSubjectId for studentId: {}, and subjectId: {}", studentId, subjectId);

        StudentSubject studentSubject = getStudentSubjectByStudentIdAndSubjectId(studentId, subjectId);

        if (studentSubject == null) {
            return null;
        }

        return mapToDto(studentSubject);
    }

    @Override
    public StudentSubjectDto addGradeToStudentSubject(StudentSubjectRequest request) {
        StudentSubject studentSubject = getStudentSubjectByStudentIdAndSubjectId(request.getStudentId(), request.getSubjectId());

        if (studentSubject == null) {
            return null;
        }
        Grade grade = new Grade();
        grade.setType(request.getGradeType());
        grade.setGrade(Integer.valueOf(request.getGrade()));
        grade.setDateEarned(LocalDateTime.now());

        studentSubject.getGrades().add(grade);

        Double average = (double) studentSubject.getGrades().stream().mapToInt(Grade::getGrade).sum() / studentSubject.getGrades().size();

        double average2decSpots = new BigDecimal(average.toString()).setScale(2, RoundingMode.HALF_UP).doubleValue();

        studentSubject.setAverage(average2decSpots);

        return mapToDto(repository.save(studentSubject));
    }

    private StudentSubject getStudentSubjectByStudentIdAndSubjectId(Long studentId, Long subjectId) {
        Student student = studentService.findOneByStudentId(studentId);
        Subject subject = subjectService.findOneBySubjectId(subjectId);

        return repository.findAllByStudent_IdAndSubject_Id(student.getId(), subject.getId());
    }

    private StudentSubjectDto mapToDto(StudentSubject studentSubject) {
        Student student = studentSubject.getStudent();
        Subject subject = studentSubject.getSubject();

        Class aClass = classesService.getClassByStudentAndSubject(student, subject);

        StudentSubjectDto studentSubjectDto = mapEntityToDTO(studentSubject, StudentSubjectDto.class);

        studentSubjectDto.setStudentId(student.getStudentId());
        studentSubjectDto.setStudentName(student.getFullName());
        studentSubjectDto.setSubjectId(subject.getSubjectId());
        studentSubjectDto.setSubjectName(subject.getName());
        studentSubjectDto.setClassName(aClass.getName());

        return studentSubjectDto;
    }
}
