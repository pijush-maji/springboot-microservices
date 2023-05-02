package com.microservices.organizationservice.controller;

import com.microservices.organizationservice.dto.OrganizationDto;
import com.microservices.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping("api/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;
    @PostMapping("add")
    public ResponseEntity<OrganizationDto> saveOrganization(
            @RequestBody OrganizationDto organizationDto
    ) {
        OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
        return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
    }

    @GetMapping("find/{organizationCode}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(
            @PathVariable String organizationCode
    ) {
        OrganizationDto organizationByCode = organizationService.getOrganizationByCode(organizationCode);
        if(organizationByCode!=null){
            return new ResponseEntity<>(organizationByCode,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
