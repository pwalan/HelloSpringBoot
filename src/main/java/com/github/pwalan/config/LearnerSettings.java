package com.github.pwalan.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 从applicaton.properties配置文件中加载前缀为 learner 的配置项
 * @author AlanP
 * @Date 2017/8/23
 */
@Component
@ConfigurationProperties(prefix = "learner")
public class LearnerSettings {

    private String name;
    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}
