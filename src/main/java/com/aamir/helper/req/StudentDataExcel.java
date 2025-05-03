package com.aamir.helper.req;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a single row of student data from the Excel file.
 *
 * ➔ It is used as a **DTO (Data Transfer Object)** for mapping Excel columns to Java fields
 *    using Alibaba EasyExcel's @ExcelProperty annotation.
 *
 * Excel File Expected Columns:
 * | Name | Email | Age |
 *
 * Lombok:
 * - @Getter: Generates getter methods.
 * - @Setter: Generates setter methods.
 */
@Getter
@Setter
public class StudentDataExcel {
    /**
     * Maps the "Name" column (first column - index 0) from the Excel to this field.
     */
    @ExcelProperty(value = "Name", index = 0)
    private String name;

    /**
     * Maps the "Email" column (second column - index 1) from the Excel to this field.
     */
    @ExcelProperty(value = "Email", index = 1)
    private String email;

    /**
     * Maps the "Age" column (third column - index 2) from the Excel to this field.
     */
    @ExcelProperty(value = "Age", index = 2)
    private Integer age;
}
