package com.example.demo.src.jpaTest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostJPASellerDto {
    private Long jpaSellerId;

    private String name;
    private String price;
    private String brand;
    private String img;
    private String detail;
}
