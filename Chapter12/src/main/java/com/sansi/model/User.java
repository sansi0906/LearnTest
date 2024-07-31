package com.sansi.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer is_delete;

    @Override
    public String toString() {
        return (
                "{id:"+id+","+
                "userName:" + userName + "," +
                "password:" + password + "," +
                "age:" + age + "," +
                "sex:" + sex + "," +
                "permission:" + permission + "," +
                "is_delete:" + is_delete + "}"
        );
    }
}
