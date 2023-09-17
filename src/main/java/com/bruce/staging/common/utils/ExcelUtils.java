package com.bruce.staging.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.bruce.staging.common.constant.ErrorCodeEnum;
import com.bruce.staging.common.exception.BusinessException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExcelUtils {
    /**
     * 导出excel
     *
     * @param file
     * @param sheetName
     * @param list
     * @param clazz
     */
    public static <T> void writeExcel(File file, String sheetName, List<T> list, Class<T> clazz) {
        ExcelWriter excelWriter = EasyExcel.write(file, clazz).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
        excelWriter.write(list, writeSheet);
        excelWriter.finish();
    }

    /**
     * 导出excel
     *
     * @param fileName
     * @param list
     * @param clazz
     */
    public static <T> void exportExcel(String fileName, List<T> list, Class<T> clazz) {
        try {
            String encodeFileName = UriUtils.encode(fileName, "utf-8");
            HttpServletResponse response = ServletUtils.getResponse();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + encodeFileName);
            EasyExcel.write(response.getOutputStream(), clazz).sheet("sheet").doWrite(list);
        } catch (IOException e) {
            throw new BusinessException(ErrorCodeEnum.BAD_REQUEST.getCode(), "导出表格失败");
        }
    }
}
