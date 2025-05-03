package com.aamir.helper.mapper;

import com.aamir.entity.Student;
import com.aamir.helper.req.StudentDataExcel;

import java.util.List;

/**
 * ➔ This class provides mapping methods between the Excel DTO (StudentDataExcel)
 *    and the Student JPA entity.
 *
 * ➔ It is a **mapper utility**: converting imported Excel data into database entity objects.
 */
public class StudentMapper {

    /**
     * Converts a list of StudentDataExcel objects (Excel rows) to a list of Student entities.
     *
     * @param studentDataExcel List of Excel rows represented as StudentDataExcel objects.
     * @return List of Student entities ready to be saved in the database.
     */
    public static List<Student> dtoToStudent(List<StudentDataExcel> studentDataExcel){
        return studentDataExcel.stream()
                // For each Excel row, create a new Student entity using builder pattern.
                .map(data -> Student.builder()
                        .age(data.getAge())
                        .email(data.getEmail())
                        .name(data.getName())
                        .build())
                .toList();
    }
}
