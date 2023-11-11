package com.wu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.wu.mapper")
public class BsMyAddressBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsMyAddressBookApplication.class, args);
    }
}
