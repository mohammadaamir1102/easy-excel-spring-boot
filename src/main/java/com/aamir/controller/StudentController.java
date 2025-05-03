package com.aamir.controller;

import com.aamir.entity.Student;
import com.aamir.helper.listener.StudentDataImportListener;
import com.aamir.helper.mapper.StudentMapper;
import com.aamir.helper.req.StudentDataExcel;
import com.aamir.repo.StudentRepo;
import com.alibaba.excel.EasyExcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
@Slf4j
@RestController
public class StudentController {

    private final StudentRepo studentRepo;


    /**
     * API endpoint to import student data from an Excel file.
     * - URL: POST /api/v1/student/import
     * - Accepts: multipart/form-data (an uploaded file)
     * - Returns: JSON response with saved students or error message.
     */

    /**
     * (curl --location 'http://localhost:7861/api/v1/student/import' \--form 'file=@"/C:/Users/LENOVO/Desktop/Student Data.xlsx"')
    **/

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importStudent(@RequestPart("file") MultipartFile file) {

        List<Student> students;

        // - Validation: Check if file is missing or empty.
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded or file is empty.");
        }
        try {

            // - Read the Excel file:
            // - Reads the uploaded file as an InputStream.
            // - Maps each row to the StudentDataExcel class (Excel columns -> Java fields).
            // - Registers a listener (optional: can handle row events during reading).
            // - Reads sheet 0 (the first sheet).
            // - doReadSync() reads synchronously and returns the full list of DTOs.
            List<StudentDataExcel> studentData = EasyExcel.read(file.getInputStream())
                    .head(StudentDataExcel.class)
                    .registerReadListener(new StudentDataImportListener())
                    .sheet(0)
                    .doReadSync();

            // - Validation: Check if Excel file has any data rows.
            if (CollectionUtils.isEmpty(studentData)){
                return ResponseEntity.badRequest().body("The Excel file contains no data.");
            }

            // - Map DTO to Entity:
            // - Convert the list of StudentDataExcel DTOs into List<Student> entities.
            students = StudentMapper.dtoToStudent(studentData);
        } catch (Exception e) {
            log.error("Internal Server Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studentRepo.saveAll(students), HttpStatus.CREATED);
    }
}
