package com.example.ednevnik.service.counterSequence;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SequenceKeys {
    SUBJECT("subject"),
    STUDENT("student"),
    TEACHER("teacher"),
    CLASS("class");

    private String key;

    public String getKey() {
        return key;
    }
}
