package com.ck.demo.config;


import com.ck.demo.bean.UserBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  //bean配置类
public class BeanConfig {

    @Bean
    public UserBean userBean() {
        return new UserBean();
    }
}
