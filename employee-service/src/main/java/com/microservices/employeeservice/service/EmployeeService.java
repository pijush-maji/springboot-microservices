package com.microservices.employeeservice.service;


import com.microservices.employeeservice.dto.APIResponse;
import com.microservices.employeeservice.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);
    APIResponse getEmployeeById(String id);
}
