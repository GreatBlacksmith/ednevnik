package com.example.ednevnik.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {

    @Id
    @JsonIgnore
    private ObjectId id;
    private String username;
    @JsonIgnore
    private String password;

}
