package com.vivek.springBoot.controller;

import com.vivek.springBoot.dto.StudentDto;
import com.vivek.springBoot.entity.Student;
import com.vivek.springBoot.exception.ErrorDetails;
import com.vivek.springBoot.exception.ResourceNotFoundException;
import com.vivek.springBoot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/saveStudent")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto student = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<StudentDto>> getAllStudent() {
        List<StudentDto> student = studentService.getAllStudent();
        return ResponseEntity.ok(student);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable("id") Long StudentId) {
        StudentDto st = studentService.getById(StudentId);
        return ResponseEntity.ok(st);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<StudentDto> updateById(@PathVariable("id") Long StudentId, @RequestBody StudentDto studentDto) {
        StudentDto st = studentService.updateStudentById(StudentId, studentDto);
        return new ResponseEntity<>(st, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletStudentById(@PathVariable("id") Long studentId){
            studentService.deleteStudentById(studentId);
            return ResponseEntity.ok("Student has deleted Successfully !!");
    }


}
