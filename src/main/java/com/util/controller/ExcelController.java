package com.util.controller;

import com.util.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * @author lis
 */
@Api(tags = "excel上传下载")
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private ExcelService excelService;


    @ApiOperation("excel上传")
    @PostMapping("/uploadByFile")
    public String uploadByFile(@RequestParam("file")MultipartFile file) throws IOException {
        return excelService.uploadByFile(file);
    }


    @ApiOperation("从url中读取excel上传")
    @PostMapping("/uploadByUrl")
    public String uploadByUrl(@RequestParam("urlPath")String urlPath) throws IOException {
        return excelService.uploadByUrl(urlPath);
    }

    @ApiOperation("excel导出")
    @GetMapping("/export")
    public String export(HttpServletResponse response) throws ParseException {
        return excelService.export(response);
    }




}
