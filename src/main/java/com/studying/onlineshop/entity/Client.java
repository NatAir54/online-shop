package com.studying.onlineshop.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Builder
@Getter
@Setter
public class Client {
    private int id;
    private String email;
    private String password;
    private String sole;
}
