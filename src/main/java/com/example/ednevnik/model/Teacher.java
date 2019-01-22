package com.example.ednevnik.model;

import com.example.ednevnik.model.codebook.ClassType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Document
public class Teacher {

    @Id
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    ObjectId id;

    String name;
    String lastName;
    Long teacherNumber;
    List<ClassType> classTypes = new ArrayList<>();

    @CreatedDate
    public LocalDateTime createDate;

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;
}
