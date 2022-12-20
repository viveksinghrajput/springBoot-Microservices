package com.vivek.employee.service;

import com.vivek.employee.dto.APIInfoDto;
import com.vivek.employee.dto.DepartmentDto;
import com.vivek.employee.dto.EmployeeDto;
import com.vivek.employee.entity.Employee;
import com.vivek.employee.exception.EmailAlreadyExistsExceptions;
import com.vivek.employee.exception.ResourceNotFoundException;
import com.vivek.employee.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    /*@Autowired
    private RestTemplate restTemplate;*/
    @Autowired
    private WebClient webClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (optionalEmployee.isPresent()) {
            throw new EmailAlreadyExistsExceptions("Email already exist in database");
        }
        //convert StudentDto to Student JPA Entity
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee saveEmployee = employeeRepository.save(employee);
        //Convert Student JPA Entity to StudentDto
        EmployeeDto saveEmployeeDto = modelMapper.map(saveEmployee, EmployeeDto.class);
        return saveEmployeeDto;
    }

    @Override
    public APIInfoDto getEmployeeById(Long empId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(empId);
        if (!optionalEmployee.isPresent()) {
            throw new ResourceNotFoundException("Employee", "Id", empId);
        }
      /* ResponseEntity<DepartmentDto> responseEntity =
               restTemplate.getForEntity("http://localhost:8080/departments/"+optionalEmployee.get().getDepartmentCode(), DepartmentDto.class);
       DepartmentDto departmentDto =responseEntity.getBody();*/

        DepartmentDto departmentDto= webClient.get().uri("http://localhost:8080/departments/"+optionalEmployee.get().getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        APIInfoDto  apiInfoDto = new APIInfoDto();
        apiInfoDto.setEmployeeDto(modelMapper.map(optionalEmployee,EmployeeDto.class));
        apiInfoDto.setDepartmentDto(departmentDto);

        return apiInfoDto;
    }
}
