package com.vivek.springBoot.service;

import com.vivek.springBoot.dto.StudentDto;

import java.util.List;

public interface StudentService {
    StudentDto saveStudent(StudentDto student);

    List<StudentDto> getAllStudent();

    StudentDto getById(Long studentId);

    void deleteStudentById(Long studentId);

    StudentDto updateStudentById(Long studentId, StudentDto studentDto);
}
