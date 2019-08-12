package com.ck.demo.java8;

import java.util.Arrays;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        /**
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });*/
        //Collections.sort(names, (String a, String b) -> (b.compareTo(a)));
        names.sort((a, b) -> b.compareTo(a));
        for(String name : names) {
            System.out.println(name);
        }

        //Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        //java8允许用::关键字传递方法或构造函数的引用，如下是引用静态方法
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("1234");

        Something something = new Something();
        //引用对象方法
        Converter<String, String> converter1 = something::startsWhith;
        //Person::new来获取Person类构造函数的引用
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }
}
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}
class Something {
    String startsWhith(String s) {
        return String.valueOf(s.charAt(0));
    }
}
class Person {
    String firstName;
    String lastName;
    Person(){}
    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}
//lamda表达式作用域：访问局部变量、访问字段和静态变量、访问默认接口方法、内置函数式接口

















