package com.example.ednevnik.model.codebook;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum SubjectType {

    ZNANSTVENI(1L, "Znanstveni"),
    DRUSTVENI(2L, "Društveni");

    private Long code;
    private String description;

    public static SubjectType getByCode(Long code) {
        return Arrays.stream(SubjectType.values()).filter(type -> type != null && type.code.equals(code)).findFirst()
                .orElse(null);
    }
}
