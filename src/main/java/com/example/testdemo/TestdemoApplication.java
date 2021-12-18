package com.example.testdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.example.testdemo.mybatis.mapper")
@EnableTransactionManagement
public class TestdemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TestdemoApplication.class, args);
    }
    
}
