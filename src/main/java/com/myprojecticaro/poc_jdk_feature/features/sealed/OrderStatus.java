package com.myprojecticaro.poc_jdk_feature.features.sealed;

public sealed interface OrderStatus
        permits Pending, Approved, Rejected, RejectedDetail {}