package com.haoyue.notedemos.eventbus;

/**
 * 作者：chen1 on 2018/3/13 09
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class EvenBusMsg {

    String name;
    String sex;
    String address;
    int age;

    public EvenBusMsg(String name, String sex, String address, int age) {
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.age = age;
    }

    @Override
    public String toString() {
        return "EvenBusMsg{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
