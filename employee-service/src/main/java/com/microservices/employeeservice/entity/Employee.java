package com.microservices.employeeservice.entity;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "employee")
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    @NonNull
    @Indexed(unique = true)
    private String email;
    private String departmentCode;
}
