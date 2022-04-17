package com.rokin.microservice2.events.listener;

import com.rokin.microservice2.events.channel.CustomChannel;
import com.rokin.microservice2.events.model.ActionEnum;
import com.rokin.microservice2.events.model.DataChangeModel;
import com.rokin.microservice2.repository.RedisOrganizationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListener {

    @Autowired
    private RedisOrganizationRepository organizationRepository;

    private static final Logger logger = LoggerFactory.getLogger(KafkaListener.class);

    @StreamListener(CustomChannel.ORGANIZATION_UPDATES)
    public void loggerSink(DataChangeModel change) {
        logger.info("Received an {} event for organization id {}", change.getAction(), change.getOrganizationId());

        if (change.getAction() == ActionEnum.UPDATE) {
            logger.info("Removing stale organization '{}' data from redis", change.getOrganizationId());
            organizationRepository.deleteById(change.getOrganizationId());
        }
    }
}
