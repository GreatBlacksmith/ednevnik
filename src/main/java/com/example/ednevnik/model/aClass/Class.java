package com.example.ednevnik.model.aClass;

import com.example.ednevnik.model.codebook.ClassType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Class {

    @Id
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    private ObjectId id;

    private Long classId;

    private String name;

    private ClassType classType;

    private Long teacherNumber;
}
