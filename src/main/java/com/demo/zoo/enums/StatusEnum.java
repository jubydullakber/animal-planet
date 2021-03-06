package com.demo.zoo.enums;

public enum StatusEnum {
    ACTIVE('Y'),
    INACTIVE('N');
    private char status;

    StatusEnum(char status) {
        this.status = status;
    }

    public char getStatus() {
        return status;
    }
}
