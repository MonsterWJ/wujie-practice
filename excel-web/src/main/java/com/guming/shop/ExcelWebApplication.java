package com.guming.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Wujie
 * @date 2022/3/2 16:32
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ExcelWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelWebApplication.class,args);
    }
}
