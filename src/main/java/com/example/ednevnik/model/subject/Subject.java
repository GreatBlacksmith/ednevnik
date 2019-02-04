package com.example.ednevnik.model.subject;

import com.example.ednevnik.model.codebook.ClassType;
import com.example.ednevnik.model.codebook.SubjectType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
@Data
@NoArgsConstructor
public class Subject implements Serializable {

    @Id
    @Setter(AccessLevel.NONE)
    private ObjectId id;

    private Long subjectId;

    private String name;
    private String description;

    private SubjectType subjectType;
    private List<ClassType> classTypes = new ArrayList<>();

    @CreatedDate
    public LocalDateTime createDate;

    @LastModifiedDate
    public LocalDateTime lastModifiedDate;


}
