package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lis
 */
@SpringBootApplication
@Slf4j
public class UtilCommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(UtilCommonApplication.class,args);
        log.info("》》》》》》》》》》》》》》》》》》》》常用工具类测试启动《《《《《《《《《《《《《《《《《《《《《《《《《");
    }
}
