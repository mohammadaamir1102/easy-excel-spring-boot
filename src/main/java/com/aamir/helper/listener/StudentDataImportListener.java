package com.aamir.helper.listener;

import com.aamir.helper.req.StudentDataExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentDataImportListener implements ReadListener<StudentDataExcel> {

    @Override
    public void invoke(StudentDataExcel studentDataExcel, AnalysisContext analysisContext) {
        log.debug("Student excel data {}", studentDataExcel);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.debug("Analysis context {}", analysisContext);
    }
}
