package org.tuyetdang.Enums;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");;
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public static Gender fromValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value);
    }
}
