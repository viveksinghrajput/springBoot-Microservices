/*
package com.vivek.springBoot.mapper;

import com.vivek.springBoot.dto.StudentDto;
import com.vivek.springBoot.entity.Student;

public class StudentMapper {

    //Convert Student JPA to Student DTO
    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto =new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
        return studentDto;
    }

    //Convert Student Dto to Student JPA

    public static Student mapToStudent(StudentDto studentDto){
        Student student = new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getEmail()
        );
        return student;
    }
}
*/
