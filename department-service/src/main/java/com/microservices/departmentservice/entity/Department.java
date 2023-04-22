package com.microservices.departmentservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "department")
public class Department {

    @Id
    private String id;
    private String departmentName;
    private String departmentDescription;
    private String departmentCode;
}
