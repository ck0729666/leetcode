package com.ck.demo;

import com.ck.demo.bean.UserBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanConfig.class);
        ApplicationContext context = new AnnotationConfigApplicationContext("com.ck.demo");

        //原始使用对象
        UserBean userBean = new UserBean();

        //把这个App需要使用对象交给spring来创建和管理
        Object bean = context.getBean("userBean");
        System.out.println(bean);

        //解决问题：解耦/减少资源消耗/复用对象



    }
    public boolean panding1(int a) {
        return false;
    }
    public float panding(int a ) {
        return 1F;
    }



















}
