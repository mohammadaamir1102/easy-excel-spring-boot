package com.aamir.helper.listener;

import com.aamir.helper.req.StudentDataExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

/**
 * ➔ This is a custom listener for EasyExcel that listens to each row being read from the Excel sheet.
 *
 * ➔ Implements EasyExcel's ReadListener interface, which has two key methods:
 *    invoke(...) - called once for each row of data.
 *    doAfterAllAnalysed(...) - called once after all rows have been processed.
 */
@Slf4j
public class StudentDataImportListener implements ReadListener<StudentDataExcel> {

    /**
     * ➔ Called for **each row** of the Excel file as it's read.
     *
     * @param studentDataExcel This is a single row of data mapped to the StudentDataExcel class.
     * @param analysisContext  Contains metadata about the read process (e.g., current row number, sheet info).
     */
    @Override
    public void invoke(StudentDataExcel studentDataExcel, AnalysisContext analysisContext) {
        log.debug("Student excel data {}", studentDataExcel);
    }

    /**
     * ➔ Called **once after all rows have been read and processed.**
     *
     * @param analysisContext Context info (you can use it to get info about the total rows, sheets, etc.).
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("Analysis context {}", analysisContext);
    }
}
