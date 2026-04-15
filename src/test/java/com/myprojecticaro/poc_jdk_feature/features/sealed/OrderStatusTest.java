package com.myprojecticaro.poc_jdk_feature.features.sealed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderStatusTest {

    @Test
    void shouldCreateDifferentStatuses() {
        OrderStatus status1 = new Pending();
        OrderStatus status2 = new Rejected("Payment failed");

        assertTrue(status2 instanceof Rejected);
    }

    @Test
    void shouldComparePendingCorrectly() {
        Pending p1 = new Pending();
        Pending p2 = new Pending();

        assertEquals(p1, p2);
    }

    @Test
    void shouldCompareApprovedCorrectly() {
        Approved a1 = new Approved();
        Approved a2 = new Approved();

        assertEquals(a1, a2);
    }

    @Test
    void shouldCompareRejectedCorrectly() {
        Rejected r1 = new Rejected("Error");
        Rejected r2 = new Rejected("Error");

        assertEquals(r1, r2);
    }

    @Test
    void shouldNotBeEqualWhenReasonIsDifferent() {
        Rejected r1 = new Rejected("Error 1");
        Rejected r2 = new Rejected("Error 2");

        assertNotEquals(r1, r2);
    }
}
