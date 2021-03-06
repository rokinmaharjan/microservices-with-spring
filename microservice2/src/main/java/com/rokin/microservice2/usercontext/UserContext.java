package com.rokin.microservice2.usercontext;

import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String CUSTOM_HEADER = "custom-header";

    private String correlationId = "";
    private String customHeader = "";


    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCustomHeader() {
        return customHeader.concat("_{propagated from microservice2}");
    }

    public void setCustomHeader(String headerid) {
        this.customHeader = headerid;
    }


}
