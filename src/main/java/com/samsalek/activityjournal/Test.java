package com.samsalek.activityjournal;

public class Test {

    String name;
    int age;
    float income;
    boolean dumb;
    Test2 test2;

    public Test(String name, int age, float income, boolean dumb) {
        this.name = name;
        this.age = age;
        this.income = income;
        this.dumb = dumb;
        test2 = new Test2(null, 34, 72, false);
    }
}
