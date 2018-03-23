package com.feizi.dubbo.dao.model;

import java.io.Serializable;

/**
 * Created by feizi on 2018/3/8.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -8680646970920704841L;
    private Integer id;
    private String name;
    private Integer age;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
