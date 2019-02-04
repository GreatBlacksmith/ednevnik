package com.example.ednevnik.service.aclass;

import com.example.ednevnik.model.Class;

public interface ClassService {

    Class getClassByClassId(Long classId);

    void insertNewClass();
}
