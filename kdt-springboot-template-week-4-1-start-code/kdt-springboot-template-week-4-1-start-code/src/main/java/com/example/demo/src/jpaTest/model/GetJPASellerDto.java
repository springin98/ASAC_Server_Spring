package com.example.demo.src.jpaTest.model;

import com.example.demo.src.jpaTest.entity.JPASeller;
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
public class GetJPASellerDto {
    private Long id;
    private String company;
    private String ceo;
    private String address;
    private String phone;
    private String email;
    private String number;

    private List<String> jpaProductList = new ArrayList<>();
    public GetJPASellerDto(JPASeller jpaSeller){
        this.id = jpaSeller.getId();
        this.company = jpaSeller.getCompany();
        this.ceo = jpaSeller.getCeo();
        this.address = jpaSeller.getAddress();
        this.phone = jpaSeller.getPhone();
        this.email = jpaSeller.getEmail();
        this.number = jpaSeller.getNumber();
        for(JPAProduct jpaProduct : jpaSeller.getJpaProducts()){
            this.jpaProductList.add(jpaProduct.getName());
        }
    }
}
