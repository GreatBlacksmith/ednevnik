package com.example.ednevnik.model.studentSubject;

import com.example.ednevnik.model.Grade;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentSubjectDto {

    Long studentId;
    String studentName;

    Long subjectId;
    String subjectName;

    String className;

    List<Grade> grades = new ArrayList<>();

    Double average;
}
