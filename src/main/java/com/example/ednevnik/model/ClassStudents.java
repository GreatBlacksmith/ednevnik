package com.example.ednevnik.model;

import com.example.ednevnik.model.student.Student;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
public class ClassStudents {

    @Id
    @Setter(AccessLevel.NONE)
    private ObjectId id;

    @DBRef
    private Class aClass;

    @DBRef
    private List<Student> students;
}
