package com.example.ednevnik.model.classes;

import com.example.ednevnik.model.aClass.Class;
import com.example.ednevnik.model.student.Student;
import com.example.ednevnik.model.subject.Subject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Data
@NoArgsConstructor
public class Classes {

    @Id
    @Setter(AccessLevel.NONE)
    private ObjectId id;

    @DBRef
    private Class aClass;

    @DBRef
    private Set<Student> students;

    @DBRef
    private Set<Subject> subjects;
}
