package com.example.demo.src.customer;

import com.example.demo.src.customer.model.GetCustomerRes;
import com.example.demo.src.customer.model.PatchCustomerEmailReq;
import com.example.demo.src.customer.model.PatchCustomerPasswordReq;
import com.example.demo.src.customer.model.PostCustomerReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CustomerDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int createCustomer(PostCustomerReq postCustomerReq){
        String createCustomerQuery = "insert into CUSTOMER (name, email, password, phone, birth) VALUES (?,?,?,?,?)";
        Object[] createCustomerParams = new Object[]{postCustomerReq.getName(), postCustomerReq.getEmail(), postCustomerReq.getPassword(), postCustomerReq.getPhone(), postCustomerReq.getBirth()};
        this.jdbcTemplate.update(createCustomerQuery, createCustomerParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from CUSTOMER where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public List<GetCustomerRes> getCustomers(){
        String getCustomersQuery = "select * from CUSTOMER";
        return this.jdbcTemplate.query(getCustomersQuery,
                (rs,rowNum) -> new GetCustomerRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("birth")
                )
        );
    }

    //이메일로 회원 찾기
    public List<GetCustomerRes> getCustomersByEmail(String email){
        String getCustomersByEmailQuery = "select * from CUSTOMER where email =?";
        String getCustomersByEmailParams = email;
        return this.jdbcTemplate.query(getCustomersByEmailQuery,
                (rs, rowNum) -> new GetCustomerRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("birth")
                ),
                getCustomersByEmailParams);
    }

    public List<GetCustomerRes> getCustomersByPhone(String phone){
        String getCustomersByPhoneQuery = "select * from CUSTOMER where phone =?";
        String getCustomersByPhoneParams = phone;
        return this.jdbcTemplate.query(getCustomersByPhoneQuery,
                (rs, rowNum) -> new GetCustomerRes(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("birth")
                ),
                getCustomersByPhoneParams);
    }

    //회원 이메일 정보 변경
    public int modifyCustomerEmail(PatchCustomerEmailReq patchCustomerEmailReq){
        String modifyCustomerEmailQuery = "update CUSTOMER set email = ? where id = ? ";
        Object[] modifyCustomerEmailParams = new Object[]{patchCustomerEmailReq.getEmail(), patchCustomerEmailReq.getId()};

        return this.jdbcTemplate.update(modifyCustomerEmailQuery,modifyCustomerEmailParams);
    }

    //회원 비밀번호 정보 변경
    public int modifyCustomerPassword(PatchCustomerPasswordReq patchCustomerPasswordReq){
        String modifyCustomerPasswordQuery = "update CUSTOMER set password = ? where id = ? ";
        Object[] modifyCustomerPasswordParams = new Object[]{patchCustomerPasswordReq.getPassword(), patchCustomerPasswordReq.getId()};

        return this.jdbcTemplate.update(modifyCustomerPasswordQuery,modifyCustomerPasswordParams);
    }

}
