package com.rokin.microservice3.events.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CustomChannel {
    String ORGANIZATION_UPDATES = "organizationUpdates";

    @Output("organizationUpdates")
    MessageChannel outboundOrg();
}
