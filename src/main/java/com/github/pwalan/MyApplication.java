package com.github.pwalan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 入口类
 * @author AlanP
 * @Date 2017/8/21
 */
@RestController
@SpringBootApplication
public class MyApplication {

    @Value("${book.name}")
    private String bookName;
    @Value("${book.learner}")
    private String bookLearner;

    @Autowired
    private LearnerSettings learner;

    @RequestMapping("/")
    String index(){
        String msg="";
        msg+="Welcome to <i>"+bookName+"</i>, "+bookLearner+"!";
        msg+="<br><br>";
        msg+="Learner name is "+learner.getName()+", and age is "+learner.getAge();
        return msg;
    }

    public static void main(String[] args){
        //启动Spring Boot应用项目
        SpringApplication.run(MyApplication.class,args);
    }

}
