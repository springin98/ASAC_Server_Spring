package com.example.demo.src.jpaTest.model;

import com.example.demo.src.jpaTest.entity.JPACustomer;
import com.example.demo.src.jpaTest.entity.JPAProduct;
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
public class GetJPACustomerDto {
    private Long id;
    private String email;

    private List<String> jpaProductList = new ArrayList<>();
    public GetJPACustomerDto(JPACustomer jpaCustomer){
        this.id = jpaCustomer.getId();
        this.email = jpaCustomer.getEmail();
        for(JPAProduct jpaProduct : jpaCustomer.getJpaProducts()){
            this.jpaProductList.add(jpaProduct.getName());
        }
    }
}
