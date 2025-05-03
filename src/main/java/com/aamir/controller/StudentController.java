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


    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> importStudent(@RequestPart("file") MultipartFile file) {

        List<Student> students;
        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded or file is empty.");
        }
        try {
            List<StudentDataExcel> studentData = EasyExcel.read(file.getInputStream())
                    .head(StudentDataExcel.class)
                    .registerReadListener(new StudentDataImportListener())
                    .sheet(0)
                    .doReadSync();

            if (CollectionUtils.isEmpty(studentData)){
                return ResponseEntity.badRequest().body("The Excel file contains no data.");
            }

            students = StudentMapper.dtoToStudent(studentData);
        } catch (Exception e) {
            log.error("Internal Server Error", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(studentRepo.saveAll(students), HttpStatus.CREATED);
    }
}
