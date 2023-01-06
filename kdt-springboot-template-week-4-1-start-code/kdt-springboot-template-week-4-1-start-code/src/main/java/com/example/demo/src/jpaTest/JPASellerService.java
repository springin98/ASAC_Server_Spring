package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.src.jpaTest.entity.JPASeller;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.model.GetJPASellerDto;
import com.example.demo.src.jpaTest.model.JPASellerDto;
import com.example.demo.src.jpaTest.model.PostJPASellerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Transactional
public class JPASellerService {

    private final JPASellerDao jpaSellerDao;

    public Long createJPASeller(JPASellerDto jpaSellerDto) throws BaseException {
        JPASeller jpaSeller = new JPASeller();
        jpaSeller.makeJPASeller(jpaSellerDto);

        //중복
        if(checkEmail(jpaSellerDto.getEmail()) == 1){
            throw new BaseException(DUPLICATED_EMAIL);
        }
        try{
            Long id = jpaSellerDao.createJPASeller(jpaSeller); // POINT
            return id;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //이메일 중복 체크
    @Transactional(readOnly = true)
    public int checkEmail(String email) throws BaseException{
        try{
            List<JPASeller> sellerList = jpaSellerDao.checkEmail(email);
            return sellerList.size();
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //조회
    public List<GetJPASellerDto> getJPASellers() throws BaseException{
        try{
            List<JPASeller> jpaSellerList = jpaSellerDao.getJPASellers();
            List<GetJPASellerDto> getJPASellerDtoList = new ArrayList<>();
            for(JPASeller jpaSeller : jpaSellerList){
                GetJPASellerDto dto = new GetJPASellerDto(jpaSeller);
                getJPASellerDtoList.add(dto);
            }

            return getJPASellerDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //id로 조회
    public List<GetJPASellerDto> getJPASellerById(Long jsaSellerId) throws BaseException{
        try{

            List<JPASeller> jpaSellerById = jpaSellerDao.getJPASellerById(jsaSellerId);

            if(jpaSellerById == null){
                throw new BaseException(MODIFY_FAIL_SELLER);
            }

            List<GetJPASellerDto> getJPASellerDtoList = new ArrayList<>();
            for(JPASeller jpaSeller : jpaSellerById){
                GetJPASellerDto dto = new GetJPASellerDto(jpaSeller);
                getJPASellerDtoList.add(dto);
            }

            return getJPASellerDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //업데이트
    public void modifyJPASeller(Long jpaSellerId, JPASellerDto jpaSellerDto) throws BaseException{
        try{

            JPASeller jpaSeller = jpaSellerDao.findJPASeller(jpaSellerId);

            if(jpaSeller == null){
                throw new BaseException(MODIFY_FAIL_SELLER);
            }

            jpaSeller.updateJPASeller(jpaSellerDto);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //삭제
    public void deleteJPAProduct(Long jpaSellerId) throws BaseException{
        try {
            JPASeller jpaSeller = jpaSellerDao.findJPASeller(jpaSellerId);

            if(jpaSeller == null){
                throw new BaseException(MODIFY_FAIL_SELLER);
            }

            jpaSeller.deleteJPASeller();

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
