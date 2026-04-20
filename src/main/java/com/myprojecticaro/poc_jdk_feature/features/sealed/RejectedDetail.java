package com.myprojecticaro.poc_jdk_feature.features.sealed;

import com.myprojecticaro.poc_jdk_feature.features.patternmatching.ErrorDetail;

public record RejectedDetail(ErrorDetail detail) implements OrderStatus {}