package com.example.demo.src.jpaTest;

import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.entity.JPASeller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JPAProductDao {

    private final EntityManager entityManager;

    public void createJPAProduct(JPAProduct jpaProduct) {
        if(jpaProduct.getId() == null){
            //자동으로 저장해준다.
            entityManager.persist(jpaProduct);
        } else {
            //업데이트 해준다.
            entityManager.merge(jpaProduct);
        }
    }

    public List<JPAProduct> getJPAProducts() {
        return entityManager.createQuery("select p from JPAProduct p where p.state = 'ACTIVE'", JPAProduct.class)
                .getResultList();
    }

    public List<JPAProduct> getJPAProductById(Long jsaProductId) {
        return entityManager.createQuery("select p from JPAProduct p where p.state = 'ACTIVE' and p.id = :id")
                .setParameter("id", jsaProductId)
                .getResultList();

    }

    public JPAProduct findJPAProduct(Long id) {
        //entityManager.find 메서드를 사용하면 어떤 entity의 class를 조회할 건지 말해줘야 한다.
        return entityManager.find(JPAProduct.class, id);
    }

}