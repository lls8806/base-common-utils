package com.util.service.impl;

import com.util.domain.UserVo;
import com.util.service.ExcelService;
import com.util.utils.ExcelExportUtils;
import com.util.utils.ExcelImportUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public String uploadByFile(MultipartFile file)  {
        try {
            List<UserVo>  userVos = ExcelImportUtils.importExcel(file, UserVo.class);
            userVos.stream().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
            return "导入失败";
        }
        return "导入成功";
    }

    @Override
    public String export(HttpServletResponse response) throws ParseException {
        List<UserVo> list=new ArrayList<>();
        UserVo userVo1 =new UserVo();
        userVo1.setName("zzzzz");
        userVo1.setSex(1);
        userVo1.setAge(18);
        userVo1.setBirthday(DateUtils.parseDate("2002-05-15","yyyy-MM-dd"));
        userVo1.setMoney(new BigDecimal("56.23"));
        list.add(userVo1);

        UserVo userVo2 =new UserVo();
        userVo2.setName("xxxxx");
        userVo2.setSex(2);
        userVo2.setAge(18);
        userVo2.setBirthday(DateUtils.parseDate("2002-03-15","yyyy-MM-dd"));
        userVo2.setMoney(new BigDecimal("69"));
        list.add(userVo2);

        UserVo userVo3 =new UserVo();
        userVo3.setName("ccccc");
        userVo3.setSex(1);
        userVo3.setAge(26);
        userVo3.setBirthday(DateUtils.parseDate("1994-05-15","yyyy-MM-dd"));
        userVo3.setMoney(new BigDecimal("11000.36"));
        list.add(userVo3);

        UserVo userVo4 =new UserVo();
        userVo4.setName("xxxxx");
        userVo4.setSex(1);
        userVo4.setAge(30);
        userVo4.setBirthday(DateUtils.parseDate("1990-01-15","yyyy-MM-dd"));
        userVo4.setMoney(new BigDecimal("69996.2"));
        list.add(userVo4);

        Workbook wb = ExcelExportUtils.getWorkbook("导出测试", "", UserVo.class, list);
        ExcelExportUtils.exportExcel(wb,"导出测试",response);
        return "导出成功";
    }
}
