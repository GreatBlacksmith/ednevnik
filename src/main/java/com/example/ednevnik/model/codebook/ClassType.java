package com.example.ednevnik.model.codebook;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ClassType {
    PRVI(1L, "Prvi razred."),
    DRUGI(2L, "Drugi razred."),
    TREĆI(3L, "Treći razred."),
    ČETVRTI(4L, "Četvrti razred.");

    private Long code;
    private String description;

    public static ClassType getByCode(Long code) {
        return Arrays.stream(ClassType.values()).filter(type -> type != null && type.code.equals(code)).findFirst()
                .orElse(null);
    }

    public Long getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
