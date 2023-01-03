package com.example.demo.src.test;

import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.GetMemoDto;
import com.example.demo.src.test.model.MemoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestDao {

    private final EntityManager entityManager;
    public void createMemo(Memo memo){
        if(memo.getId() == null){
            entityManager.persist(memo);
        } else {
            entityManager.merge(memo);
        }
    }


    public List<Memo> checkMemo(String memo){
        //memo를 받아서 해당되는 memo가 있는지 확인해야 하기 때문에 JPQL를 이용해야 한다.
        return entityManager.createQuery("select m from Memo m where m.memo = :memo and m.state = 'ACTIVE'", Memo.class)
                .setParameter("memo", memo)
                .getResultList();
    }


    public List<Memo> getMemos() {
//
//        return entityManager.createQuery("select m from Memo m left join fetch m.comments where m.state = 'ACTIVE'", Memo.class)
//                .getResultList();


        return entityManager.createQuery("select m from Memo m where m.state = 'ACTIVE'", Memo.class)
                .getResultList();
    }

    public Memo findMemo(Long id) {
        //entityManager.find 메서드를 사용하면 어떤 entity의 class를 조회할 건지 말해줘야 한다.
        return entityManager.find(Memo.class, id);
    }

    public void createComment(Comment comment) {
        //entity 매니저로 받아와서 메서드를 작성해준다.

        //주의 : 받아온 entity 의 id 값이 null일 수 있다. 그럴 때는 저장하고, null이 아니면 따로 update 갱신을 해준다.

        if(comment.getId() == null){
            //자동으로 저장해준다.
            entityManager.persist(comment);
        } else {
            //업데이트 해준다.
            entityManager.merge(comment);
        }
    }

}
