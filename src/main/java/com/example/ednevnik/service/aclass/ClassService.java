package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.aClass.ClassDto;

public interface ClassService {

    Class getClassByClassId(Long classId);

    Class saveNewClass(ClassDto classDto);
}
