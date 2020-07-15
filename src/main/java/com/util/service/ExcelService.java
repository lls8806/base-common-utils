package com.util.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author lis
 */
public interface ExcelService {

    /**
     * 导入excel
     * @param file 文件
     * @return
     */
    String uploadByFile(MultipartFile file) throws IOException;

    /**导出excel
     * @param response
     * @return
     */
    String export(HttpServletResponse response) throws ParseException;
}
