package com.microservices.employeeservice.service.impl;

import com.microservices.employeeservice.dto.EmployeeDto;
import com.microservices.employeeservice.entity.Employee;
import com.microservices.employeeservice.repository.EmployeeRepository;
import com.microservices.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
        Employee savedEmployee = employeeRepository.save(employee);
        EmployeeDto savedEmployeeDto = null ;
        if(savedEmployee!=null){
            savedEmployeeDto = new EmployeeDto(
                    savedEmployee.getId(),
                    savedEmployee.getFirstName(),
                    savedEmployee.getLastName(),
                    savedEmployee.getEmail()
            );
        }
        return savedEmployeeDto;
    }

    @Override
    public EmployeeDto getEmployeeById(String id) {
        Optional<Employee> employeeO = employeeRepository.findById(id);
        EmployeeDto employeeDto = null;
        if(!employeeO.isEmpty()){
            Employee employee = employeeO.get();
            employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail()
            );
        }
        return employeeDto;
    }


}
