package com.microservices.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepartmentDto {
    private String id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
