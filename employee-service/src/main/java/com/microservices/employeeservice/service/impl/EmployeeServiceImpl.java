package com.microservices.employeeservice.service.impl;

import com.microservices.employeeservice.dto.APIResponse;
import com.microservices.employeeservice.dto.DepartmentDto;
import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.dto.OrganizationDto;
import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.mapper.EmployeeMapper;
import com.microservices.employeeservice.repository.EmployeeRepository;
import com.microservices.employeeservice.service.APIClient;
import com.microservices.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final WebClient webClient;
    private final APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = null ;
        if(savedEmployee!=null){
            savedEmployeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee);
        }
        return savedEmployeeDto;
    }

    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Override
    public APIResponse getEmployeeById(String id) {
        Optional<Employee> employeeO = employeeRepository.findById(id);
        APIResponse apiResponse = null;
        if(!employeeO.isEmpty()){
            Employee employee = employeeO.get();
            //Calling department-service over HTTP(Sync calll)
//            DepartmentDto departmentDto = webClient.get()
//                    .uri("http://localhost:8080/api/departments/find/"+employee.getDepartmentCode())
//                    .retrieve()
//                    .bodyToMono(DepartmentDto.class)
//                    .block();
            //Calling department-service through Feign API client
            DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
            OrganizationDto organizationDto = webClient.get()
                    .uri("http://localhost:8083/api/organizations/find/" + employee.getOrganizationCode())
                    .retrieve()
                    .bodyToMono(OrganizationDto.class)
                    .block();

            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
            apiResponse = new APIResponse(employeeDto,departmentDto,organizationDto);
        }
        return apiResponse;
    }

    public APIResponse getDefaultDepartment(String id, Exception exception) {
        Optional<Employee> employeeO = employeeRepository.findById(id);
        APIResponse apiResponse = null;
        if(!employeeO.isEmpty()){
            Employee employee = employeeO.get();

            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartmentName("Default");
            departmentDto.setDepartmentDescription("Default circuit breaker response");
            departmentDto.setDepartmentCode("DFLT");
            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee);
            apiResponse = new APIResponse(employeeDto,departmentDto,null);
        }
        return apiResponse;
    }


}
