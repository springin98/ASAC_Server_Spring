package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.src.jpaTest.entity.JPACustomer;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.model.GetJPACustomerDto;
import com.example.demo.src.jpaTest.model.JPACustomerDto;
import com.example.demo.src.jpaTest.model.PostJPAProductDto;
import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Transactional
public class JPACustomerService {

    private final JPACustomerDao jpaCustomerDao;

    public Long createJPACustomer(JPACustomerDto jpaCustomerDto) throws BaseException {
        JPACustomer jpaCustomer = new JPACustomer();
        jpaCustomer.makeJPACustomer(jpaCustomerDto);

        //중복
        if(checkEmail(jpaCustomerDto.getEmail()) == 1){
            throw new BaseException(DUPLICATED_EMAIL);
        }
        try{
            Long id = jpaCustomerDao.createJPACustomer(jpaCustomer); // POINT
            return id;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //이메일 중복 체크
    @Transactional(readOnly = true)
    public int checkEmail(String email) throws BaseException{
        try{
            List<JPACustomer> customerList = jpaCustomerDao.checkEmail(email);
            return customerList.size();
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //조회
    public List<GetJPACustomerDto> getJPACustomers() throws BaseException{
        try{
            List<JPACustomer> jpaCustomerList = jpaCustomerDao.getJPACustomers();
            List<GetJPACustomerDto> getJPACustomerDtoList = new ArrayList<>();
            for(JPACustomer jpaCustomer : jpaCustomerList){
                GetJPACustomerDto dto = new GetJPACustomerDto(jpaCustomer);
                getJPACustomerDtoList.add(dto);
            }

            return getJPACustomerDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //업데이트
    public void modifyJPACustomer(Long jsaCustomerId, JPACustomerDto jpaCustomerDto) throws BaseException{
        try{

            JPACustomer jpaCustomer = jpaCustomerDao.findJPACustomer(jsaCustomerId);

            if(jpaCustomer == null){
                throw new BaseException(MODIFY_FAIL_MEMO);
            }

            jpaCustomer.updateJPACustomer(jpaCustomerDto);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //상품 생성
    public void createJPAProduct(PostJPAProductDto postJPAProductDto) throws BaseException{
        try {
            JPACustomer jpaCustomer = jpaCustomerDao.findJPACustomer(postJPAProductDto.getJpaCustomerId());

            JPAProduct jpaProduct = new JPAProduct();
            jpaProduct.makeJPAProduct(postJPAProductDto, jpaCustomer);

            jpaCustomerDao.createJPAProduct(jpaProduct);

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
