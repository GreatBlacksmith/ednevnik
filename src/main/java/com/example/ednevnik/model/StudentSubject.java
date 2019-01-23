package com.example.ednevnik.model;

import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class StudentSubject {

    @Id
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    ObjectId id;

    Long studentSubjectId;

    @DBRef
    Student student;

    @DBRef
    Subject subject;

    List<Integer> grades = new ArrayList<>();

    Double average;
}
