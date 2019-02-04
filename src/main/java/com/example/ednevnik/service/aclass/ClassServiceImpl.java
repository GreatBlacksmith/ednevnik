package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.repository.ClassRepository;
import com.example.ednevnik.service.classstudent.ClassStudentsService;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.counterSequence.SequenceKeys;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;
    private final ClassStudentsService classStudentsService;
    private final CounterSequenceService sequenceService;

    public ClassServiceImpl(ClassRepository repository, ClassStudentsService classStudentsService, CounterSequenceService sequenceService) {
        this.repository = repository;
        this.classStudentsService = classStudentsService;
        this.sequenceService = sequenceService;
    }

    @Override
    public Class getClassByClassId(Long classId) {
        return repository.findFirstByClassId(classId);
    }

    @Override
    public void insertNewClass() {
        Class aClass = new Class();
        aClass.setClassType(ClassType.DRUGI);
        aClass.setName("Matematika drugi razred");
        aClass.setTeacherNumber(10L);
        aClass.setClassId(sequenceService.getNextSequenceValue(SequenceKeys.CLASS.getKey()));

        classStudentsService.addStudentsToClass(repository.save(aClass));
    }
}