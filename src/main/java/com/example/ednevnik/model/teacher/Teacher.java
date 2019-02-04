package com.example.ednevnik.model.teacher;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.user.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher extends ApplicationUser {

    String name;
    String lastName;
    String school;

    @UniqueElements
    Long teacherNumber;

    List<ClassType> classTypes = new ArrayList<>();

    @CreatedDate
    public LocalDateTime createDate;

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;

}
