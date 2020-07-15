package com.util.utils;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.util.config.ExcelStyle;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lls
 */
@Slf4j
public class ExcelExportUtils {
    /**
     * 允许导出的最大条数
     */
    private static final Integer EXPORT_EXCEL_MAX_NUM = 10000;
    /**
     * 日期格式
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmmss";
    /**
     * 标题行高
     */
    private static final Short TITLE_HEIGHT = 15;

    /**
     * 导出Excel
     * @param workbook workbook流
     * @param fileName 文件名
     * @param response 响应
     */
    public static void exportExcel(Workbook workbook, String fileName, HttpServletResponse response) {
        //给文件名拼接上日期
        fileName = fileName + DateFormatUtils.format(new Date(), DATE_FORMAT);
        //输出文件
        try (OutputStream out = response.getOutputStream()) {
            //获取文件名并转码
            String name = URLEncoder.encode(fileName, "UTF-8");
            //设置编码
            response.setCharacterEncoding("UTF-8");
            //设置http响应头告诉浏览器，以下载的形式处理响应的数据
            response.setContentType("application/force-download");
            //设置下载文件的默认名称
            response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
            //输出表格
            //设置标题行高
            workbook.write(out);
        } catch (IOException e) {
            log.error("文件导出异常,详情如下:", e);
        } finally {
            try {
                if (workbook != null) {
                    //关闭输出流
                    workbook.close();
                }
            } catch (IOException e) {
                log.error("文件导出异常,详情如下:", e);
            }
        }
    }

    /**
     * 获取导出的 Workbook对象
     *
     * @param title     大标题
     * @param sheetName 页签名
     * @param object    导出实体
     * @param list      数据集合
     * @return Workbook
     */
    public static Workbook getWorkbook(String title, String sheetName, Class object, List list) {
        //判断导出数据是否为空
        if (list == null) {
            list = new ArrayList<>();
        }
        //判断导出数据数量是否超过限定值
        if (list.size() > EXPORT_EXCEL_MAX_NUM) {
            title = "导出数据行数超过:" + EXPORT_EXCEL_MAX_NUM + "条,无法导出、请添加查询条件后再进行导出!";
            list = new ArrayList<>();
        }
        //导出参数
        ExportParams exportParams = new ExportParams(title, sheetName, ExcelType.XSSF);
        //设置导出样式，可以不设置使用默认的样式，这里用的是自定义样式
        exportParams.setStyle(ExcelStyle.class);
        //设置标题行高
        exportParams.setTitleHeight(TITLE_HEIGHT);
        //输出Workbook流
        return ExcelExportUtil.exportExcel(exportParams, object, list);
    }

    /**
     * 获取导出的 Workbook对象
     *
     * @param path 模板路径
     * @param map  导出内容map
     * @return Workbook
     */
    public static Workbook getWorkbook(String path, Map<String, Object> map) {
        //获取导出模板
        TemplateExportParams params = new TemplateExportParams(path);
        //设置导出样式
        params.setStyle(ExcelStyle.class);
        //输出Workbook流
        return ExcelExportUtil.exportExcel(params, map);
    }
}
