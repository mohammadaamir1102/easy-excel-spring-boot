package com.aamir.helper.mapper;

import com.aamir.entity.Student;
import com.aamir.helper.req.StudentDataExcel;

import java.util.List;


public class StudentMapper {

    public static List<Student> dtoToStudent(List<StudentDataExcel> studentDataExcel){
        return studentDataExcel.stream()
                .map(data -> Student.builder()
                        .age(data.getAge())
                        .email(data.getEmail())
                        .name(data.getName())
                        .build())
                .toList();
    }
}
