package com.example.ednevnik.model.student;

import com.example.ednevnik.model.aClass.Class;
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
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Document
public class Student implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    @JsonIgnore
    ObjectId id;

    Long studentId;

    String name;
    String lastName;
    Long studentNumber;
    ClassType classType;

    @DBRef
    Class aCLass;

    @CreatedDate
    public LocalDateTime createDate;

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;

    public String getFullName() {
        return name + " " + lastName;
    }

}
