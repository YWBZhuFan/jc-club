package com.jichi.wx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.jichi")
public class  WXApplication {
    public static void main(String[] args) {SpringApplication.run(WXApplication.class);}
}
