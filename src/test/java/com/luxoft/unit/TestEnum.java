package com.luxoft.unit;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum TestEnum {
    kid(5,"no"),
    teen(15, "half"),
    teen1(14, "half"),
    teen2(12, "half");

    private int age;
    private String response;

    TestEnum(int age, String response) {
        this.age = age;
        this.response = response;
    }
}
