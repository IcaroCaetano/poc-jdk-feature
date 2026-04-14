package com.myprojecticaro.poc_jdk_feature.features.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    void shouldCreateUserRecord() {
        User user = new User("Icaro", 30);

        assertEquals("Icaro", user.name());
        assertEquals(30, user.age());
    }
}
