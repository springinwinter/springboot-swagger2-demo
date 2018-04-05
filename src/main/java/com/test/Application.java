package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * 启动类
 */
@SpringBootApplication
@Controller
public class Application{
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
}