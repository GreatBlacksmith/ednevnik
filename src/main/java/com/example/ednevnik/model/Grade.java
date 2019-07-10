package com.example.ednevnik.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Grade {

    String type;
    LocalDateTime dateEarned;
    Integer grade;
}
