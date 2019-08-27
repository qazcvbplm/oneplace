package com.ops;

import ops.model.X.area.EnableArea;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ops.dao")
@EnableArea
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
