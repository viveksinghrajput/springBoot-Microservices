package com.vivek.employee.controller;

import com.vivek.employee.dto.APIInfoDto;
import com.vivek.employee.dto.EmployeeDto;
import com.vivek.employee.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/saveEmployee")
    public ResponseEntity<EmployeeDto> saveEmployees(@RequestBody EmployeeDto employeeDto){
        EmployeeDto empDto = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(empDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<APIInfoDto> getEmployeeById(@PathVariable("id") Long empId){
        APIInfoDto apiInfoDto = employeeService.getEmployeeById(empId);
        return new ResponseEntity<>(apiInfoDto,HttpStatus.OK);
    }
}
