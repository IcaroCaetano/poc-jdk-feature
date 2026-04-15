package com.myprojecticaro.poc_jdk_feature.features.records;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void shouldCreateUserRecord() {
        User user = new User("Icaro", 30);

        assertEquals("ICARO", user.name());
        assertEquals(30, user.age());
    }

    @Test
    void shouldThrowExceptionWhenAgeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                new User("Icaro", -1)
        );
    }

    @Test
    void shouldCheckIfUserIsAdult() {
        User user = new User("Icaro", 30);

        assertTrue(user.isAdult());
    }

    @Test
    void shouldTransformNameToUpperCase() {
        User user = new User("icaro", 30);

        assertEquals("ICARO", user.name());
    }
}
