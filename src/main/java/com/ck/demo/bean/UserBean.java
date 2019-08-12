package com.ck.demo.bean;


import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component  //都可以不写
@Scope(BeanDefinition.SCOPE_PROTOTYPE)  //Spring管理Bean对象的生命周期
public class UserBean {
    public UserBean() {
        System.out.println("UserBean实例化");
    }
}
