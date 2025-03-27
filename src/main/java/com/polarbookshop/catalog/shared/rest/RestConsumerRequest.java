package com.polarbookshop.catalog.shared.rest;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serial;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Getter
@ToString
@Builder
@SuppressWarnings("squid:S1948")
public class RestConsumerRequest<I> implements IConsumerRequest, ISiemTransactionable {

    @Serial
    private static final long serialVersionUID = 4484096421966932540L;

    private I request;

    @JsonFilter("ignoreHeaders")
    private Map<String, Object> headerParams;

    private Map<String, Object> queryParams;

    private Map<String, Object> pathParams;

    private Map<String, Object> formData;

    @Builder.Default
    @Getter(AccessLevel.NONE)
    private Boolean enableTransactionLog = true;

    @Override
    public Boolean isEnableTransactionLog() {
        return enableTransactionLog;
    }

    @Override
    public String getSiemTransactionData() {
        return ofNullable(request).map(Object::toString).orElse("");
    }
}
