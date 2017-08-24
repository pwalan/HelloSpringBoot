package com.github.pwalan;

import com.github.pwalan.config.LearnerSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * 入口类
 * @author AlanP
 * @Date 2017/8/21
 */
@Controller
@SpringBootApplication
public class MyApplication {

    @Value("${book.name}")
    private String bookName;
    @Value("${book.learner}")
    private String bookLearner;

    @Autowired
    private LearnerSettings learner;

    @RequestMapping("/")
    public String index(Model model){
        //直接显示消息
        /*String msg="";
        msg+="Welcome to <i>"+bookName+"</i>, "+bookLearner+"!";
        msg+="<br><br>";
        msg+="Learner name is "+learner.getName()+", and age is "+learner.getAge();
        return msg;*/

        //返回页面，并附上数据
        Person single = new Person("pw",23);

        List<Person> people=new ArrayList<Person>();
        people.add(new Person("xx",11));
        people.add(new Person("yy",22));
        people.add(new Person("zz",33));

        model.addAttribute("singlePerson", single);
        model.addAttribute("people", people);

        return "person/index";
    }

    public static void main(String[] args){
        //启动Spring Boot应用项目
        SpringApplication.run(MyApplication.class,args);
    }

}
