package com.microservices.organizationservice.repository;

import com.microservices.organizationservice.entity.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends MongoRepository<Organization,String> {
    Organization findByOrganizationCode(String organizationCode);
}
