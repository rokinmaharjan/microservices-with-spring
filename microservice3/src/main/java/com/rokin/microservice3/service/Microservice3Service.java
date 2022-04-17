package com.rokin.microservice3.service;

import com.rokin.microservice3.events.model.ActionEnum;
import com.rokin.microservice3.events.source.SimpleSourceBean;
import com.rokin.microservice3.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Microservice3Service {

    @Autowired
    private SimpleSourceBean simpleSourceBean;

    public String publishToKafka() {
        simpleSourceBean.publishDataChange(ActionEnum.CREATE, UUID.randomUUID().toString());
        return "Message sent to kafka";
    }

    public Organization getOrganizationById(String organizationId) {
        Organization organization = new Organization();
        organization.setId(organizationId);
        organization.setName("Cool organization");

        return organization;
    }

    public Organization updateOrganization(String id, Organization organization) {
        organization.setId(id);

        simpleSourceBean.publishDataChange(ActionEnum.UPDATE, id);

        return organization;
    }
}
