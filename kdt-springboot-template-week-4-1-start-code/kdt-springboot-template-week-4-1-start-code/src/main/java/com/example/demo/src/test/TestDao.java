package com.example.demo.src.test;

import com.example.demo.src.customer.model.GetCustomerRes;
import com.example.demo.src.test.model.GetTestRes;
import com.example.demo.src.test.model.PatchTestReq;
import com.example.demo.src.test.model.PostTestReq;
import com.example.demo.src.user.model.PatchUserReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class TestDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createTest(PostTestReq postTestReq){
        String createTestQuery = "insert into MEMO (memo) VALUES (?)";
        Object[] createTestParams = new Object[]{postTestReq.getMemo()};
        this.jdbcTemplate.update(createTestQuery, createTestParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public List<GetTestRes> getTest(){
        String getTestQuery = "select * from MEMO";
        return this.jdbcTemplate.query(getTestQuery,
                (rs,rowNum) -> new GetTestRes(
                        rs.getInt("id"),
                        rs.getString("memo"))
        );
    }

    public int modifyTestName(PatchTestReq patchTestReq){
        String modifyTestNameQuery = "update MEMO set memo = ? where id = ? ";
        Object[] modifyTestNameParams = new Object[]{patchTestReq.getMemo(), patchTestReq.getId()};

        return this.jdbcTemplate.update(modifyTestNameQuery,modifyTestNameParams);
    }

}
