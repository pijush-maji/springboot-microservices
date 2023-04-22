package com.microservices.departmentservice.repository;

import com.microservices.departmentservice.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department, String> {

    Department findByDepartmentCode(String departmentCode);
}
