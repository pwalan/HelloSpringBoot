package com.github.pwalan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author AlanP
 * @Date 2017/8/21
 */
@RestController
@SpringBootApplication
public class MyApplication {

    @RequestMapping("/")
    String index(){
        return "Hello Spring Boot";
    }

    public static void main(String[] args){
        SpringApplication.run(MyApplication.class,args);
    }

}
