package org.tuyetdang.Entity.Enums;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    OTHER("OTHER");
    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
