package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.Class;
import com.example.ednevnik.repository.ClassRepository;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl implements ClassService {

    private final ClassRepository repository;
    //    private final ClassStudentsService classStudentsService;
    private final CounterSequenceService sequenceService;

    public ClassServiceImpl(ClassRepository repository, CounterSequenceService sequenceService) {
        this.repository = repository;
        this.sequenceService = sequenceService;
    }

    @Override
    public Class getClassByClassId(Long classId) {
        return repository.findFirstByClassId(classId);
    }

//    @Override
//    public void insertNewClass() {
//        Class aClass = new Class();
//        aClass.setClassType(ClassType.DRUGI);
//        aClass.setName("Matematika drugi razred");
//        aClass.setTeacherNumber(10L);
//        aClass.setClassId(sequenceService.getNextSequenceValue(SequenceKeys.CLASS.getKey()));
//
//        classStudentsService.addStudentsToClass(repository.save(aClass));
//
//        Class aClass2 = new Class();
//        aClass2.setClassType(ClassType.DRUGI);
//        aClass2.setName("Matematika cetvrti razred");
//        aClass2.setTeacherNumber(11L);
//        aClass2.setClassId(sequenceService.getNextSequenceValue(SequenceKeys.CLASS.getKey()));
//
//        classStudentsService.addStudentsToClass(repository.save(aClass2));
//    }
}