package com.vivek.employee.service;

import com.vivek.employee.dto.APIInfoDto;
import com.vivek.employee.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIInfoDto getEmployeeById(Long empId);
}
