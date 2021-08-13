package com.example.sample.attribute;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
    TEXT("text"),
    INTEGER("integer"),
    DATE("date");

    private final String name;
    Type(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
