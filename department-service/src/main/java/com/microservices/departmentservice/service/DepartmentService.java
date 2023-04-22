package com.microservices.departmentservice.service;

import com.microservices.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartment(String departmentCode);
}
