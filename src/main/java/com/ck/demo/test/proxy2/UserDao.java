package com.ck.demo.test.proxy2;

public class UserDao implements  IUserDao{
    @Override
    public void save() {
        System.out.print("保存数据");
    }
}
