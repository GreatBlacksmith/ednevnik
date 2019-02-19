package com.example.ednevnik.model.aClass;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ClassDto {

    private String name;
    private Long classTypeId;
    private Long teacherNumber;
}
