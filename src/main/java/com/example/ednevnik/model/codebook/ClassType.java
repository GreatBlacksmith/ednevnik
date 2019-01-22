package com.example.ednevnik.model.codebook;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ClassType {
    PRVI(1L, "Prvi razred."),
    DRUGI(2L, "Drugi razred."),
    TREĆI(3L, "Treći razred."),
    ČETVRTI(4L, "Četvrti razred.");

    private Long code;
    private String description;

}
