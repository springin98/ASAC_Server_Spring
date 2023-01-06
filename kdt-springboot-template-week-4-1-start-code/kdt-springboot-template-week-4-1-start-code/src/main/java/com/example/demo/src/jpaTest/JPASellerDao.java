package com.example.demo.src.jpaTest;

import com.example.demo.src.jpaTest.entity.JPASeller;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.model.GetJPASellerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class JPASellerDao {

    private final EntityManager entityManager;

    public Long createJPASeller(JPASeller jpaSeller){
        if(jpaSeller.getId() == null){
            entityManager.persist(jpaSeller);
           Long nullId = 0L;
            return nullId;
        } else {
            entityManager.merge(jpaSeller);
            Long id = jpaSeller.getId();
            return id;
        }
    }

    public List<JPASeller> checkEmail(String email){
        return entityManager.createQuery("select c from JPASeller c where c.email = :email and c.state = 'ACTIVE'", JPASeller.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<JPASeller> getJPASellers() {
        return entityManager.createQuery("select c from JPASeller c where c.state = 'ACTIVE'", JPASeller.class)
                .getResultList();
    }

    public List<JPASeller> getJPASellerById(Long jsaSellerId) {
//        return entityManager.createQuery("select c from JPASeller c where c.state = 'ACTIVE' and c.id = :jsaSellerId", JPASeller.class)
//                .getResultList();

        return entityManager.createQuery("select c from JPASeller c where c.state = 'ACTIVE' and c.id = :id")
                .setParameter("id", jsaSellerId)
                .getResultList();

    }

    public JPASeller findJPASeller(Long id) {
        //entityManager.find 메서드를 사용하면 어떤 entity의 class를 조회할 건지 말해줘야 한다.
        return entityManager.find(JPASeller.class, id);
    }

}
