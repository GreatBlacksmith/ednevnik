package com.example.ednevnik.model.codebook;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SubjectType {

    ZNANSTVENI(1L, "Znanstveni"),
    DRUSTVENI(2L, "Društveni");

    private Long code;
    private String description;
}
