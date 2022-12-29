package com.example.demo.src.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostCustomerReq {
    private String email;
    private String password;
    private String name;
    private String phone;
    private int birth;
}
