package com.pdd.netty;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
//        List<InnerClass> list = new ArrayList<>();
//        list.add(InnerClass.instance("123"));
//        list.add(InnerClass.instance("231"));
//        list.add(InnerClass.instance("32"));
//        list.add(InnerClass.instance("1"));
//        List<InnerClass> list1 = new ArrayList<>(list);
//        System.out.println(list);
//        System.out.println(list1);
//
//        System.out.println("--------------");
//        list.get(0).setName("pdd");
//        System.out.println(list);
//        System.out.println(list1);


        List<String> list = new ArrayList<>();
        list.add("zz");
        list.add("231");
        list.add("32");
        list.add("1");
        List<String> list1 = new ArrayList<>(list);
        System.out.println(list);
        System.out.println(list1);
        list.set(0, "pdd");
        System.out.println("--------------");
        System.out.println(list);
        System.out.println(list1);
    }

    public static class InnerClass {
        public String name;

        @Override
        public String toString() {
            return "InnerClass{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public InnerClass(String name) {
            this.name = name;
        }

        public static InnerClass instance(String name) {
            InnerClass innerClass = new InnerClass(name);
            return innerClass;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
