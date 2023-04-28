package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanPropietiesImplement implements MyBeanPropieties {

    private String name;
    private String surname;
    public MyBeanPropietiesImplement(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String function() {
        return name + "-" + surname;
    }
}
