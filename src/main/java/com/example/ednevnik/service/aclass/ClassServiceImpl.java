package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.aClass.ClassDto;
import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.repository.ClassRepository;
import com.example.ednevnik.service.BaseService;
import com.example.ednevnik.service.classstudent.ClassesService;
import com.example.ednevnik.service.counterSequence.CounterSequenceService;
import com.example.ednevnik.service.counterSequence.SequenceKeys;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassServiceImpl extends BaseService implements ClassService {

    private final ClassRepository repository;
    private final ClassesService classesService;
    private final CounterSequenceService sequenceService;

    public ClassServiceImpl(ModelMapper modelMapper, ClassRepository repository, ClassesService classesService, CounterSequenceService sequenceService) {
        super(modelMapper);
        this.repository = repository;
        this.classesService = classesService;
        this.sequenceService = sequenceService;
    }

    @Override
    public Class getClassByClassId(Long classId) {
        return repository.findFirstByClassId(classId);
    }

    @Override
    public Class saveNewClass(ClassDto classDto) {

        Class aClass = mapEntityToDTO(classDto, Class.class);
        aClass.setClassId(sequenceService.getNextSequenceValue(SequenceKeys.CLASS.getKey()));
        aClass.setClassType(ClassType.getByCode(classDto.getClassTypeId()));

        Class savedClass = repository.save(aClass);

        classesService.saveNewClassesForClass(savedClass);

        return savedClass;
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