package com.example.ednevnik.model.student;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentDto {

    Long studentId;
    String name;
    String lastName;
    Long studentNumber;
    Long classTypeId;
    Long classId;
}
