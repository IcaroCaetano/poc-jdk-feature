package com.myprojecticaro.poc_jdk_feature.features.records;

public record User(String name, int age) {

    public User {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }

        name = name.toUpperCase();
    }

    public boolean isAdult() {
        return age >= 18;
    }
}