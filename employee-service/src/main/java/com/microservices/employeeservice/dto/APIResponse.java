package com.microservices.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class APIResponse {
    private EmployeeDto employee;
    private DepartmentDto department;
}
