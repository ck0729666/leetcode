package com.ck.demo.java8;

interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}

public class Main {
    public static void main(String[] args) {
        //1.实现该接口的类只需要实现calculate（），sqrt（）可以直接使用
        //2.通过接口创建对象，实现默认方法
        //通过匿名内部类方式访问接口

        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };
        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));
    }
}
/**
 *注:不管是抽象类还是接口，都可以通过匿名内部类的方式访问。
 * 不能通过抽象类或者接口直接创建对象。
 * 对于上面通过匿名内部类方式访问接口，
 * 我们可以这样理解：一个内部类实现了接口里的抽象方法并且返回一个内部类对象，之后我们让接口的引用来指向这个对象。
 */