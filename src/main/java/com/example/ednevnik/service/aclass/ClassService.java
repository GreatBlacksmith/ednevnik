package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.aClass.ClassDto;

import java.util.List;

public interface ClassService {

    Class getClassByClassId(Long classId);

    List<Class> getClassesByTeacherNumber(Long teacherNumber);

    Class saveNewClass(ClassDto classDto);
}
