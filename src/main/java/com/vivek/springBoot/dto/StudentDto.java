package com.vivek.springBoot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
