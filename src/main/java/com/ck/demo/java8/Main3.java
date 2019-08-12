package com.ck.demo.java8;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) {
        List<String> collection = new ArrayList<>();
        collection.add("ddd2");
        collection.add("aaa2");
        collection.add("bbb1");
        collection.add("aaa1");
        collection.add("bbb3");
        collection.add("ccc");
        collection.add("bbb2");
        collection.add("ddd1");
        //Filter过滤
        //forEach是一个最终操作，不能在forEach之后来执行其他Stream操作
        collection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        //Sorted过滤
        collection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        //Map映射、Match匹配、Count计数、Reduce规约
    }


}



















