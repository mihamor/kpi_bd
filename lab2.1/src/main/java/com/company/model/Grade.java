package com.company.model;

import java.util.HashMap;
import java.util.Map;

public enum Grade {
    GOOD("GOOD"),
    OK("OK"),
    BAD("BAD");

    private String value;

    Grade(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Grade fromString(String text) {
        for (Grade b : Grade.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
