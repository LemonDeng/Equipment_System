package com.ys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "com.ys.model.dao")
@EnableSwagger2 //自动生成API文档
public class YsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YsApplication.class, args);
    }

}
