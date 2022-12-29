package com.example.demo.src.product;

import com.example.demo.common.BaseException;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.product.model.PostProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductDao productDao;


    //브랜드명과 판매자 아이디, 상품명이 같을 경우 중복 체크
    public PostProductRes createProduct(PostProductReq postProductReq) throws BaseException {
        //중복
        if(checkName(postProductReq.getName()) ==1 && checkBrand(postProductReq.getBrand()) == 1 && checkSellerId(postProductReq.getSellerId()) == 1){
            throw new BaseException(POST_PRODUCTS_EXISTS_NAME);
        }
        try{
            int productId = productDao.createProduct(postProductReq); // POINT
            return new PostProductRes(productId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkName(String name) throws BaseException{
        try{
            return productDao.checkName(name);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkBrand(String brand) throws BaseException{
        try{
            return productDao.checkBrand(brand);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkSellerId(String sellerId) throws BaseException{
        try{
            return productDao.checkSellerId(sellerId);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProducts() throws BaseException{
        try{
            List<GetProductRes> getProductRes = productDao.getProducts();
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsByName(String name) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsByName(name);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsByBrand(String brand) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsByBrand(brand);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsByBigCategory(String bigCategory) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsByBigCategory(bigCategory);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsByMidCategory(String midCategory) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsByMidCategory(midCategory);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsBySmallCategory(String smallCategory) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsBySmallCategory(smallCategory);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsNotSoldOut() throws BaseException{
        try{
            List<GetProductRes> getProductRes = productDao.getProductsNotSoldOut();
            return getProductRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetProductRes> getProductsByNameNotSoldOut(String name) throws BaseException{
        try{
            List<GetProductRes> getProductsRes = productDao.getProductsByNameNotSoldOut(name);
            return getProductsRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
