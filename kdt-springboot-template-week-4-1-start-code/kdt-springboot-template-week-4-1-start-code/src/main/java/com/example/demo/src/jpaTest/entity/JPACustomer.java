package com.example.demo.src.jpaTest.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.jpaTest.model.JPACustomerDto;
import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.model.MemoDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "JPACustomer")
public class JPACustomer extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "jpaCustomer", fetch = FetchType.LAZY)
    List<JPAProduct> jpaProducts = new ArrayList<JPAProduct>();

    public void makeJPACustomer(JPACustomerDto jpaCustomerDto) {
        this.email = jpaCustomerDto.getEmail();
    }

    public void updateJPACustomer(JPACustomerDto jpaCustomerDto) {
        this.email = jpaCustomerDto.getEmail();
    }
}
