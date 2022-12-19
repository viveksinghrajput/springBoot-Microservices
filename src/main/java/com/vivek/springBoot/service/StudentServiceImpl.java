package com.vivek.springBoot.service;

import com.vivek.springBoot.dto.StudentDto;
import com.vivek.springBoot.entity.Student;
import com.vivek.springBoot.exception.EmailAlreadyExistsExceptions;
import com.vivek.springBoot.exception.ResourceNotFoundException;
import com.vivek.springBoot.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        Optional<Student> optionalStudent = studentRepository.findByEmail(studentDto.getEmail());
        if(optionalStudent.isPresent()){
            throw new EmailAlreadyExistsExceptions("Email already exist in database");
        }

        //convert StudentDto to Student JPA Entity
      //  Student student = StudentMapper.mapToStudent(studentDto);
        Student student = modelMapper.map(studentDto,Student.class);

        Student saveStudent = studentRepository.save(student);
        //Convert Student JPA Entity to StudentDto
       // StudentDto saveStudentDto = StudentMapper.mapToStudentDto(saveStudent);

        StudentDto saveStudentDto = modelMapper.map(saveStudent,StudentDto.class);
        return saveStudentDto;
    }

    @Override
    public List<StudentDto> getAllStudent() {
        List<Student> students = studentRepository.findAll();
       // return students.stream().map(StudentMapper::mapToStudentDto).collect(Collectors.toList());

        return students.stream().map((Student)->modelMapper.map(Student,StudentDto.class)).collect(Collectors.toList());
    }

    @Override
    public StudentDto getById(Long studentId) {

        Student student = studentRepository.findById(studentId).orElseThrow(
                ()-> new ResourceNotFoundException("Student","Id",studentId)
        );
      //  return StudentMapper.mapToStudentDto(student);
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto updateStudentById(Long studentId, StudentDto studentDto) {

       Student existingStudent = studentRepository.findById(studentId).orElseThrow(
               ()->new ResourceNotFoundException("Student","Id",studentId)
       );


        existingStudent.setFirstName(studentDto.getFirstName());
        existingStudent.setLastName(studentDto.getLastName());
        existingStudent.setEmail(studentDto.getEmail());

        Student updatedStudent = studentRepository.save(existingStudent);

      //  return StudentMapper.mapToStudentDto(updatedStudent);

        return modelMapper.map(updatedStudent,StudentDto.class);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student existingStudent = studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student","Id",studentId)
        );
        studentRepository.deleteById(studentId);
    }
}
