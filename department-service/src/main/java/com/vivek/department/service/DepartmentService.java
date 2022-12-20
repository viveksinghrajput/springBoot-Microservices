package com.vivek.department.service;

import com.vivek.department.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departDto);

    DepartmentDto getDepartmentById(String departmentCode);
}
