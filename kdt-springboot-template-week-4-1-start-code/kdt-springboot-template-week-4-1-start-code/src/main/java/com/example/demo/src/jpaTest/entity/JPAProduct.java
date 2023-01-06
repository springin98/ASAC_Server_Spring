package com.example.demo.src.jpaTest.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.jpaTest.model.GetJPAProductDto;
import com.example.demo.src.jpaTest.model.PostJPASellerDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.example.demo.common.BaseEntity.State.INACTIVE;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "JPAProduct")
public class JPAProduct extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String price;
    private String brand;
    private String img;
    private String detail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JPASeller_ID")
    private JPASeller jpaSeller;

    public void makeJPAProduct(PostJPASellerDto postJPAProductDto, JPASeller jpaSeller) {
        this.jpaSeller = jpaSeller;
        this.name = postJPAProductDto.getName();
        this.price = postJPAProductDto.getPrice();
        this.brand = postJPAProductDto.getPrice();
        this.img = postJPAProductDto.getImg();
        this.detail = postJPAProductDto.getDetail();
    }

    public void updateJPAProduct(GetJPAProductDto jpaProductDto) {
        this.name = jpaProductDto.getName();
        this.price = jpaProductDto.getPrice();
        this.brand = jpaProductDto.getBrand();
        this.img = jpaProductDto.getImg();
        this.detail = jpaProductDto.getDetail();
    }

    public void deleteJPAProduct() {
        this.state = INACTIVE;
    }

}
