package com.aamir.helper.req;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDataExcel {
    @ExcelProperty(value = "Name", index = 0)
    private String name;
    @ExcelProperty(value = "Email", index = 1)
    private String email;
    @ExcelProperty(value = "Age", index = 2)
    private Integer age;
}
