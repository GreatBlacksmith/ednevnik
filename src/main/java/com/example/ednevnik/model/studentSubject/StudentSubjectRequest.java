package com.example.ednevnik.model.studentSubject;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class StudentSubjectRequest {

    @NotNull
    Long studentId;
    @NotNull
    Long subjectId;
    @NotNull
    String gradeType;
    @NotNull
    String grade;

}
