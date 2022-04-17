package com.rokin.microservice2.events.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DataChangeModel {
    private String type;
    private ActionEnum action;
    private String organizationId;
    private String correlationId;

    public DataChangeModel() {
        super();
    }

    public DataChangeModel(String type, ActionEnum action, String organizationId, String correlationId) {
        super();
        this.type = type;
        this.action = action;
        this.organizationId = organizationId;
        this.correlationId = correlationId;
    }
}