package com.rokin.microservice2.events.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CustomChannel {
    String ORGANIZATION_UPDATES = "organizationUpdates";

    @Input("organizationUpdates")
    SubscribableChannel organizations();
}
