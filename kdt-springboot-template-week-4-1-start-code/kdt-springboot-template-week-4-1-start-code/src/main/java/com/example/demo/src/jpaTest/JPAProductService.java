package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.entity.JPASeller;
import com.example.demo.src.jpaTest.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Transactional
public class JPAProductService {
    private final JPASellerDao jpaSellerDao;
    private final JPAProductDao jpaProductDao;

    //상품 생성
    public void createJPAProduct(PostJPASellerDto postJPAProductDto) throws BaseException {
        try {
            JPASeller jpaSeller = jpaSellerDao.findJPASeller(postJPAProductDto.getJpaSellerId());

            JPAProduct jpaProduct = new JPAProduct();
            jpaProduct.makeJPAProduct(postJPAProductDto, jpaSeller);

            jpaProductDao.createJPAProduct(jpaProduct);

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //전체 조회
    public List<GetJPAProductDto> getJPAProducts() throws BaseException{
        try{
            List<JPAProduct> jpaProductList = jpaProductDao.getJPAProducts();
            List<GetJPAProductDto> getJPAProductDtoList = new ArrayList<>();
            for(JPAProduct jpaProduct : jpaProductList){
                GetJPAProductDto dto = new GetJPAProductDto(jpaProduct);
                getJPAProductDtoList.add(dto);
            }

            return getJPAProductDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    //id로 조회
    public List<GetJPAProductDto> getJPAProductById(Long jsaProductId) throws BaseException{
        try{

            List<JPAProduct> jpaProductById = jpaProductDao.getJPAProductById(jsaProductId);

            if(jpaProductById == null){
                throw new BaseException(MODIFY_FAIL_SELLER);
            }

            List<GetJPAProductDto> getJPAProductDtoList = new ArrayList<>();
            for(JPAProduct jpaProduct : jpaProductById){
                GetJPAProductDto dto = new GetJPAProductDto(jpaProduct);
                getJPAProductDtoList.add(dto);
            }

            return getJPAProductDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    
    //업데이트
    public void modifyJPAProduct(Long jpaProductId, GetJPAProductDto jpaProductDto) throws BaseException{
        try{

            JPAProduct jpaProduct = jpaProductDao.findJPAProduct(jpaProductId);

            if(jpaProduct == null){
                throw new BaseException(MODIFY_FAIL_PRODUCT);
            }

            jpaProduct.updateJPAProduct(jpaProductDto);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //삭제
    public void deleteJPAProduct(Long jpaProductId) throws BaseException{
        try {
            JPAProduct jpaProduct = jpaProductDao.findJPAProduct(jpaProductId);

            if(jpaProduct == null){
                throw new BaseException(MODIFY_FAIL_PRODUCT);
            }

            jpaProduct.deleteJPAProduct();

        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
