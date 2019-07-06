package com.example.ednevnik.service.subject;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.codebook.SubjectType;
import com.example.ednevnik.model.subject.Subject;
import com.example.ednevnik.model.subject.SubjectDto;
import com.example.ednevnik.repository.SubjectRepository;
import com.example.ednevnik.service.BaseService;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.counterSequence.SequenceKeys;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl extends BaseService implements SubjectService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectServiceImpl.class);
    private final SubjectRepository subjectRepository;
    private final CounterSequenceService counterSequenceService;

    public SubjectServiceImpl(ModelMapper modelMapper, SubjectRepository subjectRepository, CounterSequenceService counterSequenceService) {
        super(modelMapper);
        this.subjectRepository = subjectRepository;
        this.counterSequenceService = counterSequenceService;
    }

    @Override
    public Subject saveSubject(SubjectDto subjectDto) {

        Subject subject = mapEntityToDTO(subjectDto, Subject.class);
        subject.setSubjectId(counterSequenceService.getNextSequenceValue(SequenceKeys.SUBJECT.getKey()));
        subject.setSubjectType(SubjectType.getByCode(subjectDto.getSubjectTypeId()));

        subject.setClassTypes(subjectDto.getClassTypesId().stream().map(ClassType::getByCode).collect(Collectors.toList()));

        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAll() {
        LOGGER.info("Called getAll() for Subjects");
        return subjectRepository.findAll();
    }

    @Override
    public List<SubjectDto> getAllByClassType(ClassType classType) {
        LOGGER.info("Called getAllByClassType() for Subjects with classType: {}", classType);
        List<Subject> subjects = subjectRepository.findAllByClassTypesContains(classType);

        return subjects.stream().map(subject -> mapEntityToDTO(subject, SubjectDto.class)).collect(Collectors.toList());
    }

    @Override
    public Subject findOneBySubjectId(Long subjectId) {
        LOGGER.info("Called findOneBySubjectId() for subject with id: {}", subjectId);
        return subjectRepository.findOneBySubjectId(subjectId);
    }
}
