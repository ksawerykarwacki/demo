package com.example.sample.attribute;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    KEY("key"),
    ATTRIBUTE("attribute");

    private final String name;
    Role(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
