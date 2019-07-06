package com.example.ednevnik.model.studentSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class StudentSubjectDto {

    Long studentId;

    Long subjectId;

    List<Integer> grades = new ArrayList<>();

    Double average;
}
