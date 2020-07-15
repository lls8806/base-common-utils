package com.util.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author lls
 */
public class ExcelImportUtils {
    public ExcelImportUtils() {
    }

    /**从本地导入excel
     * @param filePath  文件路径
     * @param titleRows 标题行
     * @param headerRows 表头行
     * @param pojoClass 接收类
     * @return 类集合
     * @throws IOException
     */
    public static <T> List<T> importExcel(String filePath, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
        if (StringUtils.isBlank(filePath)) {
            return null;
        } else {
            //导入参数
            ImportParams params = new ImportParams();
            //设置标题行
            params.setTitleRows(titleRows);
            //设置表头行
            params.setHeadRows(headerRows);
            //是否保存
            params.setNeedSave(true);
            //保存路径
            params.setSaveUrl("/excel/");

            try {
                //导入
                return ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
            } catch (NoSuchElementException var6) {
                throw new IOException("模板不能为空");
            } catch (Exception var7) {
                throw new IOException(var7.getMessage());
            }
        }
    }


    /**前端传入文件解析
     * @param file 传入文件
     * @param pojoClass 接收类
     * @param <T>
     * @return 接收类集合
     * @throws IOException
     */
    public static <T> List<T> importExcel(MultipartFile file, Class<T> pojoClass) throws IOException {
        return importExcel(file, 1, 1, pojoClass);
    }


    /**
     * @param file  文件
     * @param titleRows 标题行
     * @param headerRows 表头行
     * @param pojoClass 接收类
     * @param <T>
     * @return 接收类集合
     * @throws IOException
     */
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass) throws IOException {
        return importExcel(file, titleRows, headerRows, false, pojoClass);
    }

    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, boolean needVerify, Class<T> pojoClass) throws IOException {
        if (file == null) {
            return null;
        } else {
            try {
                //导入
                return importExcel(file.getInputStream(), titleRows, headerRows, needVerify, pojoClass);
            } catch (Exception var6) {
                throw new IOException(var6.getMessage());
            }
        }
    }

    /**
     * 以流的方式导入
     * @param inputStream 输入流
     * @param titleRows 标题行
     * @param headerRows 表头行
     * @param needVerify 是否需要验证
     * @param pojoClass 接收类
     * @param <T>
     * @return 结果接
     * @throws IOException
     */
    public static <T> List<T> importExcel(InputStream inputStream, Integer titleRows, Integer headerRows, boolean needVerify, Class<T> pojoClass) throws IOException {
        if (inputStream == null) {
            return null;
        } else {
            ImportParams params = new ImportParams();
            params.setTitleRows(titleRows);
            params.setHeadRows(headerRows);
            params.setSaveUrl("/excel/");
            params.setNeedSave(true);
            params.setNeedVerify(needVerify);

            try {
                return ExcelImportUtil.importExcel(inputStream, pojoClass, params);
            } catch (NoSuchElementException var7) {
                throw new IOException("excel文件不能为空");
            } catch (Exception var8) {
                throw new IOException(var8.getMessage());
            }
        }
    }


}
