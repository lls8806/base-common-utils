package com.util.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ExcelTarget("userVo")
public class UserVo {


    @Excel(name = "姓名",orderNum = "1",width = 10)
    private String name;

    @Excel(name = "性别",orderNum = "2",replace = {"男_1","女_2"},width = 10)
    private Integer sex;

    @Excel(name = "年龄",orderNum = "3",width = 10)
    private Integer age;

    @Excel(name = "出生日期",orderNum = "4",importFormat = "yyyy-MM-dd",exportFormat="yyyy-MM-dd",width = 10)
    private Date birthday;

    @Excel(name="现金",orderNum = "5",numFormat = "0.00",width = 10)
    private BigDecimal money;




}
