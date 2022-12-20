package com.vivek.department.service;

import com.vivek.department.dto.DepartmentDto;
import com.vivek.department.entity.Department;
import com.vivek.department.exception.DepartmentCodeAlreadyExists;
import com.vivek.department.exception.ResourceNotFoundException;
import com.vivek.department.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public DepartmentDto saveDepartment(DepartmentDto departDto) {

        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departDto.getDepartmentCode());
        if (optionalDepartment.isPresent()){
                throw new DepartmentCodeAlreadyExists("Department Code is already Exists");
        }

        //Convert DepartmentDto to Department JPA Entity
        Department department = modelMapper.map(departDto,Department.class);
        Department saveDepartment =departmentRepository.save(department);

        //Convert Department JPA Entity to DepartmentDto
        DepartmentDto departmentDto =modelMapper.map(saveDepartment,DepartmentDto.class);

        return departmentDto;
    }

    @Override
    public DepartmentDto getDepartmentById(String departmentCode) {


        Optional<Department> optionalDepartment = departmentRepository.findByDepartmentCode(departmentCode);
        if (!optionalDepartment.isPresent()){
            throw new ResourceNotFoundException("Department","ID" ,departmentCode);
        }
            //Convert Department JPA Entity to DepartmentDto
        DepartmentDto departmentDto = modelMapper.map(optionalDepartment,DepartmentDto.class);

        return departmentDto;
    }
}
