package com.vn.bgshop.boardgameshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.vn.bgshop")
public class BoardgameshopApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BoardgameshopApplication.class);
        app.run(args);

    }
}
