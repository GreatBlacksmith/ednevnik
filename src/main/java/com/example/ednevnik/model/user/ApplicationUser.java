package com.example.ednevnik.model.user;

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
    private ObjectId id;
    private String username;
    private String password;

}
