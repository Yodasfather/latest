package com.example.model;

public class Person {
    public String name;
    public int age;
    public PhoneNumber phone;

    public Person() {}

    public Person(String name, int age, PhoneNumber phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", phone=" + phone + '}';
    }
}
