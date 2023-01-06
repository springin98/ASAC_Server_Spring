package com.example.demo.src.jpaTest.model;

import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.entity.JPASeller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetJPAProductDto {
    private Long id;

    private String name;
    private String price;
    private String brand;
    private String img;
    private String detail;

    public GetJPAProductDto(JPAProduct jpaProduct){
        this.id = jpaProduct.getId();
        this.name = jpaProduct.getName();
        this.price = jpaProduct.getPrice();
        this.brand = jpaProduct.getBrand();
        this.img = jpaProduct.getImg();
        this.detail = jpaProduct.getDetail();
    }
}
