package com.sansi.model;

import lombok.Data;

@Data
public class GetUserListCase {
    private Integer id ;
    private String userName;
    private Integer age;
    private Integer sex;
    private Integer permission;
    private Integer is_delete;
    private String expected;
}
