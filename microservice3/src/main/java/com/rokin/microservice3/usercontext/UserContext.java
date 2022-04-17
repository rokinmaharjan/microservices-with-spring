package com.rokin.microservice3.usercontext;


import org.springframework.stereotype.Component;

@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String CUSTOM_HEADER = "custom-header";
    public static final String ORG_ID = "tmx-org-id";

    private static final ThreadLocal<String> correlationId = new ThreadLocal<String>();
    private static final ThreadLocal<String> authToken = new ThreadLocal<String>();
    private static final ThreadLocal<String> userId = new ThreadLocal<String>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<String>();
    private static final ThreadLocal<String> customHeader = new ThreadLocal<String>();


    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }


    public String getCustomHeader() {
        return customHeader.get().concat("_{propagated from microservice2}");
    }

    public void setCustomHeader(String headerid) {
        customHeader.set(headerid);
    }

    public static String getOrgId() {
        return orgId.get();
    }

    public static void setOrgId(String aOrg) {
        orgId.set(aOrg);
    }


}