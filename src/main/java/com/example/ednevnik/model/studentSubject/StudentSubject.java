package com.example.ednevnik.model.studentSubject;

import com.example.ednevnik.model.Grade;
import com.example.ednevnik.model.aClass.Class;
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

    Long teacherNumber;

    @DBRef
    Student student;

    @DBRef
    Subject subject;

    List<Grade> grades = new ArrayList<>();

    Double average;

    @DBRef
    Class aClass;
}
