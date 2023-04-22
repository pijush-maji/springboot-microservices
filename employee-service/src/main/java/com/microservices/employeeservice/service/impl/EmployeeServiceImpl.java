package com.microservices.employeeservice.service.impl;

import com.microservices.employeeservice.dto.APIResponse;
import com.microservices.employeeservice.dto.DepartmentDto;
import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.repository.EmployeeRepository;
import com.microservices.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient webClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = null ;
        if(savedEmployee!=null){
            savedEmployeeDto = new EmployeeDto(
                    savedEmployee.getId(),
                    savedEmployee.getFirstName(),
                    savedEmployee.getLastName(),
                    savedEmployee.getEmail(),
                    savedEmployee.getDepartmentCode()
            );
        }
        return savedEmployeeDto;
    }

    @Override
    public APIResponse getEmployeeById(String id) {
        Optional<Employee> employeeO = employeeRepository.findById(id);
        APIResponse apiResponse = null;
        if(!employeeO.isEmpty()){
            Employee employee = employeeO.get();
            //Calling department-service over HTTP(Sync calll)
            DepartmentDto departmentDto = webClient.get()
                    .uri("http://localhost:8080/api/departments/find/"+employee.getDepartmentCode())
                    .retrieve()
                    .bodyToMono(DepartmentDto.class)
                    .block();

            EmployeeDto employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartmentCode()
            );
            apiResponse = new APIResponse(employeeDto,departmentDto);
        }
        return apiResponse;
    }


}