package com.example.demo.src.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PatchCustomerPasswordReq {
    private int id;
    private String password;
}
