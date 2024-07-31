package com.sansi.model;

import lombok.Data;

@Data
public class AddUserCase {
    private String userName;
    private String password;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer is_delete;
    private String expected;
}
