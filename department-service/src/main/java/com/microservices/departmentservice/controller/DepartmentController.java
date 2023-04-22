package com.microservices.departmentservice.controller;

import com.microservices.departmentservice.dto.DepartmentDto;
import com.microservices.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<DepartmentDto> saveDepartment(
            @RequestBody DepartmentDto departmentDto) {
        DepartmentDto saveDepartmentDto = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartmentDto, HttpStatus.CREATED);
    }

    @GetMapping("/find/{code}")
    public ResponseEntity<DepartmentDto> getDepartment(
            @PathVariable("code") String departmentCode){
        DepartmentDto department = departmentService.getDepartment(departmentCode);
        if(department==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(department,HttpStatus.OK);
    }
}
