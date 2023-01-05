package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.src.jpaTest.entity.JPACustomer;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.demo.common.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.common.BaseResponseStatus.MODIFY_FAIL_MEMO;

@RequiredArgsConstructor
@Repository
public class JPACustomerDao {

    private final EntityManager entityManager;

    public Long createJPACustomer(JPACustomer jpaCustomer){
        if(jpaCustomer.getId() == null){
            entityManager.persist(jpaCustomer);
           Long nullId = 0L;
            return nullId;
        } else {
            entityManager.merge(jpaCustomer);
            Long id = jpaCustomer.getId();
            return id;
        }
    }

    public List<JPACustomer> checkEmail(String email){
        return entityManager.createQuery("select c from JPACustomer c where c.email = :email and c.state = 'ACTIVE'", JPACustomer.class)
                .setParameter("email", email)
                .getResultList();
    }

    public List<JPACustomer> getJPACustomers() {
        return entityManager.createQuery("select c from JPACustomer c where c.state = 'ACTIVE'", JPACustomer.class)
                .getResultList();
    }

    public JPACustomer findJPACustomer(Long id) {
        //entityManager.find 메서드를 사용하면 어떤 entity의 class를 조회할 건지 말해줘야 한다.
        return entityManager.find(JPACustomer.class, id);
    }

    public void createJPAProduct(JPAProduct jpaProduct) {
        if(jpaProduct.getId() == null){
            //자동으로 저장해준다.
            entityManager.persist(jpaProduct);
        } else {
            //업데이트 해준다.
            entityManager.merge(jpaProduct);
        }
    }

}
