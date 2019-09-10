package com.ops;

import ops.model.X.area.EnableArea;
import ops.model.X.file.system.EnableFileSystem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@MapperScan("com.ops.dao")
@EntityScan("com.ops.entity")
@EnableArea
@EnableFileSystem
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
