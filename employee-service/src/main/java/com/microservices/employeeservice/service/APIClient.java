package com.microservices.employeeservice.service;

import com.microservices.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "http://localhost:8080",value ="DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("api/departments/find/{code}")
    DepartmentDto getDepartment(@PathVariable("code") String departmentCode);
}
