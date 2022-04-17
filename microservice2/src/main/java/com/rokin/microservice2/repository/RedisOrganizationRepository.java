package com.rokin.microservice2.repository;

import com.rokin.microservice2.model.Organization;
import org.springframework.data.repository.CrudRepository;

public interface RedisOrganizationRepository extends CrudRepository<Organization, String> {
}
