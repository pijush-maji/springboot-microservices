package com.microservices.departmentservice.service.impl;

import com.microservices.departmentservice.dto.DepartmentDto;
import com.microservices.departmentservice.entity.Department;
import com.microservices.departmentservice.mapper.DepartmentMapper;
import com.microservices.departmentservice.repository.DepartmentRepository;
import com.microservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;


    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = DepartmentMapper.mapToDepartment(departmentDto);

        Department savedDepartment = departmentRepository.save(department);
        DepartmentDto saveDepartmentDto = DepartmentMapper.mapToDepartmentDto(savedDepartment);
        return saveDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartment(String departmentCode) {
        Department department = departmentRepository.findByDepartmentCode(departmentCode);
        DepartmentDto departmentDto = null;
        if (department != null) {
            departmentDto = DepartmentMapper.mapToDepartmentDto(department);
        }
        return departmentDto;
    }
}
