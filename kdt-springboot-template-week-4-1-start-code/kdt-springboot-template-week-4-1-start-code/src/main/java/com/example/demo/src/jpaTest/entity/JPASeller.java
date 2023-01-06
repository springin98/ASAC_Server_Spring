package com.example.demo.src.jpaTest.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.jpaTest.model.JPASellerDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseEntity.State.INACTIVE;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "JPASeller")
public class JPASeller extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String ceo;
    private String address;
    private String phone;
    private String email;
    private String number;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "jpaSeller", fetch = FetchType.LAZY)
    List<JPAProduct> jpaProducts = new ArrayList<JPAProduct>();

    public void makeJPASeller(JPASellerDto jpaSellerDto) {
        this.company = jpaSellerDto.getCompany();
        this.ceo = jpaSellerDto.getCeo();
        this.address = jpaSellerDto.getAddress();
        this.phone = jpaSellerDto.getPhone();
        this.email = jpaSellerDto.getEmail();
        this.number = jpaSellerDto.getNumber();
    }

    public void updateJPASeller(JPASellerDto jpaSellerDto) {
        this.company = jpaSellerDto.getCompany();
        this.ceo = jpaSellerDto.getCeo();
        this.address = jpaSellerDto.getAddress();
        this.phone = jpaSellerDto.getPhone();
        this.email = jpaSellerDto.getEmail();
        this.number = jpaSellerDto.getNumber();
    }

    public void deleteJPASeller() {
        this.state = INACTIVE;
    }
}
