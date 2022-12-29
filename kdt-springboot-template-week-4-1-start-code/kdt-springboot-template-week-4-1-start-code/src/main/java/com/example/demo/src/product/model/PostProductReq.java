package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostProductReq {
    private int id;
    private String name;
    private String sellerId;
    private String price;
    private String brand;
    private String img;
    private String detail;
    private String bigCategory;
    private String midCategory;
    private String smallCategory;

    private int soldOut;
}
