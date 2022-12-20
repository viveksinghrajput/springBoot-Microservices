package com.vivek.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class APIInfoDto {
    private EmployeeDto employeeDto;
    private DepartmentDto departmentDto;

}
