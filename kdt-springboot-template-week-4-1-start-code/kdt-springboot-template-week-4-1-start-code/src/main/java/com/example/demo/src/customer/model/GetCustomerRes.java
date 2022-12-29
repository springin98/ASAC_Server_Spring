package com.example.demo.src.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetCustomerRes {
    private int id;
    private String email;
    private String password;
    private String name;
    private String phone;
    private String birth;
}
