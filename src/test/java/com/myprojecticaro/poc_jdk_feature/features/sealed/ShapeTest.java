package com.myprojecticaro.poc_jdk_feature.features.sealed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShapeTest {

    @Test
    void shouldAllowOnlyPermittedClasses() {
        Shape shape = new Circle();

        assertTrue(shape instanceof Circle);
    }
}
