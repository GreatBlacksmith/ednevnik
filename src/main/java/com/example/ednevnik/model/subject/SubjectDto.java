package com.example.ednevnik.model.subject;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class SubjectDto {

    private Long subjectId;

    private String name;
    private String description;

    private Long subjectTypeId;
    private List<Long> classTypesId = new ArrayList<>();

}
