package com.example.demo.src.jpaTest.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.jpaTest.model.PostJPAProductDto;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JPACustomer_ID")
    private JPACustomer jpaCustomer;

    public void makeJPAProduct(PostJPAProductDto postJPAProductDto, JPACustomer jpaCustomer) {
        this.jpaCustomer = jpaCustomer;
        this.name = postJPAProductDto.getName();
    }

}
