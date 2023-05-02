package com.microservices.employeeservice.controller;

import com.microservices.employeeservice.dto.APIResponse;
import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity<EmployeeDto> saveEmployee(
            @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        if (savedEmployee == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

    }

    @GetMapping("/find/{id}")
    public ResponseEntity<APIResponse> getEmployee(
            @PathVariable String id
    ) {
        APIResponse apiResponse = employeeService.getEmployeeById(id);
        if (apiResponse != null) {
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
