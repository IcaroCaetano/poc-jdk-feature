package com.myprojecticaro.poc_jdk_feature.features.patternmatching;

import com.myprojecticaro.poc_jdk_feature.features.sealed.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {

    private final OrderProcessor processor = new OrderProcessor();


    @Test
    void shouldProcessPending() {
        assertEquals("Waiting", processor.process(new Pending()));
    }

    @Test
    void shouldProcessApproved() {
        assertEquals("Approved", processor.process(new Approved()));
    }

    @Test
    void shouldProcessRejected() {
        String result = processor.process(new Rejected("Generic error"));

        assertEquals("Rejected", result);
    }

    @Test
    void shouldProcessRejectedDetail() {
        var status = new RejectedDetail(
                new ErrorDetail("001", "Payment failed")
        );

        String result = processor.process(status);

        assertEquals("RejectedDetail: Payment failed", result);
    }


    @Test
    void shouldExtractMessageFromRejectedDetail() {
        var status = new RejectedDetail(
                new ErrorDetail("002", "Card declined")
        );

        String result = processor.extractMessage(status);

        assertEquals("Card declined", result);
    }

    @Test
    void shouldReturnOkForRejected() {
        String result = processor.extractMessage(new Rejected("Error"));

        assertEquals("OK", result);
    }

    @Test
    void shouldReturnOkForApproved() {
        String result = processor.extractMessage(new Approved());

        assertEquals("OK", result);
    }


    @Test
    void shouldAnalyzeRejectedDetailAsPaymentIssue() {
        var status = new RejectedDetail(
                new ErrorDetail("003", "Payment timeout")
        );

        String result = processor.analyze(status);

        assertEquals("Payment issue", result);
    }

    @Test
    void shouldAnalyzeRejectedAsOtherRejection() {
        String result = processor.analyze(new Rejected("Stock unavailable"));

        assertEquals("Other rejection", result);
    }

    @Test
    void shouldAnalyzePending() {
        assertEquals("Pending", processor.analyze(new Pending()));
    }

    @Test
    void shouldAnalyzeApproved() {
        assertEquals("Approved", processor.analyze(new Approved()));
    }


    @Test
    void shouldIdentifyString() {
        assertEquals("String: hello", processor.identify("hello"));
    }

    @Test
    void shouldIdentifyInteger() {
        assertEquals("Integer: 10", processor.identify(10));
    }

    @Test
    void shouldIdentifyNull() {
        assertEquals("Null value", processor.identify(null));
    }

    @Test
    void shouldIdentifyUnknownType() {
        assertEquals("Unknown", processor.identify(3.14));
    }

    @Test
    void shouldProcessAllStatusesPolymorphically() {
        List<OrderStatus> statuses = List.of(
                new Pending(),
                new Approved(),
                new Rejected("Error"),
                new RejectedDetail(new ErrorDetail("004", "Failure"))
        );

        statuses.forEach(status -> {
            String result = processor.process(status);
            assertNotNull(result);
        });
    }


    @Test
    void shouldHandleNullReasonInRejected() {
        Rejected rejected = new Rejected(null);

        assertNull(rejected.reason());
    }

    @Test
    void shouldHandleNullMessageInRejectedDetail() {
        var status = new RejectedDetail(
                new ErrorDetail("005", null)
        );

        String result = processor.process(status);

        assertEquals("RejectedDetail: null", result);
    }

    @Test
    void shouldHandleEmptyMessageInRejectedDetail() {
        var status = new RejectedDetail(
                new ErrorDetail("006", "")
        );

        String result = processor.extractMessage(status);

        assertEquals("", result);
    }

    @Test
    void shouldCompareRejectedCorrectly() {
        assertEquals(
                new Rejected("Error"),
                new Rejected("Error")
        );
    }

    @Test
    void shouldCompareRejectedDetailCorrectly() {
        var r1 = new RejectedDetail(new ErrorDetail("007", "Fail"));
        var r2 = new RejectedDetail(new ErrorDetail("007", "Fail"));

        assertEquals(r1, r2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentReason() {
        assertNotEquals(
                new Rejected("Error 1"),
                new Rejected("Error 2")
        );
    }
}