package com.sansi.model;

import lombok.Data;

@Data
public class GetUserInfoCase {
    private Integer id;
    private Integer userId;
    private String expected;
}
