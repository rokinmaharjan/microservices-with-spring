package com.rokin.microservice3.events.source;

import com.rokin.microservice3.events.model.ActionEnum;
import com.rokin.microservice3.events.model.DataChangeModel;
import com.rokin.microservice3.usercontext.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleSourceBean {
    @Qualifier(CustomChannel.ORGANIZATION_UPDATES)
    @Autowired
    private MessageChannel organizationUpdates;

    private static final Logger logger = LoggerFactory.getLogger(SimpleSourceBean.class);

    public void publishDataChange(ActionEnum action, String organizationId) {
        logger.info("Sending Kafka message {} for Id: {}", action, organizationId);
        DataChangeModel change = new DataChangeModel(
                DataChangeModel.class.getTypeName(),
                action,
                organizationId,
                UserContext.getCorrelationId());

        organizationUpdates.send(MessageBuilder.withPayload(change).build());
    }
}
