package com.myprojecticaro.poc_jdk_feature.features.patternmatching;

import com.myprojecticaro.poc_jdk_feature.features.sealed.*;

public class OrderProcessor {

    public String process(OrderStatus status) {
        return switch (status) {
            case Pending p -> "Waiting";
            case Approved a -> "Approved";
            case Rejected j -> "Rejected";
            case RejectedDetail r -> "RejectedDetail: " + r.detail().message();
        };
    }

    public String extractMessage(OrderStatus status) {
        if (status instanceof RejectedDetail(ErrorDetail(String code, String message))) {
            return message;
        }
        return "OK";
    }

    public String analyze(OrderStatus status) {
        return switch (status) {
            case RejectedDetail j ->
                    "Payment issue";
            case Rejected r ->
                    "Other rejection";
            case Pending p ->
                    "Pending";
            case Approved a ->
                    "Approved";
        };
    }

    public String identify(Object obj) {
        return switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "Null value";
            default -> "Unknown";
        };
    }
}