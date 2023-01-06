package com.example.demo.src.jpaTest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JPASellerDto {
    private Long id;
    private String company;
    private String ceo;
    private String address;
    private String phone;
    private String email;
    private String number;
}
