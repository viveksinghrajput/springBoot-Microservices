package com.vivek.department.controller;

import com.vivek.department.dto.DepartmentDto;
import com.vivek.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("saveDepartment")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departDto) {

        DepartmentDto departmentDto = departmentService.saveDepartment(departDto);
        return new ResponseEntity<>(departmentDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") String departmentCode) {

        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

}
