package com.example.demo.src.product;

import com.example.demo.src.product.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository

public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createProduct(PostProductReq postProductReq){
        String createProductQuery = "insert into PRODUCT (name, sellerId, price, brand, img, detail, bigCategory, midCategory, smallCategory) VALUES (?,?,?,?,?,?,?,?,?)";
        Object[] createProductParams = new Object[]{postProductReq.getName(), postProductReq.getSellerId(), postProductReq.getPrice(), postProductReq.getBrand(), postProductReq.getImg(), postProductReq.getDetail(), postProductReq.getBigCategory(), postProductReq.getMidCategory(), postProductReq.getSmallCategory()};
        this.jdbcTemplate.update(createProductQuery, createProductParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

    public int checkName(String name){
        String checkNameQuery = "select exists(select name from PRODUCT where name = ?)";
        String checkNameParams = name;
        return this.jdbcTemplate.queryForObject(checkNameQuery,
                int.class,
                checkNameParams);

    }

    public int checkBrand(String brand){
        String checkBrandQuery = "select exists(select brand from PRODUCT where brand = ?)";
        String checkBrandParams = brand;
        return this.jdbcTemplate.queryForObject(checkBrandQuery,
                int.class,
                checkBrandParams);

    }

    public int checkSellerId(String sellerId){
        String checkSellerIdQuery = "select exists(select sellerId from PRODUCT where sellerId = ?)";
        String checkSellerIdParams = sellerId;
        return this.jdbcTemplate.queryForObject(checkSellerIdQuery,
                int.class,
                checkSellerIdParams);

    }

    //전체 상품
    public List<GetProductRes> getProducts(){
        String getProductsQuery = "select * from PRODUCT";
        return this.jdbcTemplate.query(getProductsQuery,
                (rs,rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                )
        );
    }

    public List<GetProductRes> getProductsById(int id){
        String getProductsByIdQuery = "select * from PRODUCT where id =?";
        int getProductsByIdParams = id;
        return this.jdbcTemplate.query(getProductsByIdQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByIdParams);
    }

    //이름으로 상품 찾기
    public List<GetProductRes> getProductsByName(String name){
        String getProductsByNameQuery = "select * from PRODUCT where name =?";
        String getProductsByNameParams = name;
        return this.jdbcTemplate.query(getProductsByNameQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByNameParams);
    }

    public List<GetProductRes> getProductsByBrand(String brand){
        String getProductsByBrandQuery = "select * from PRODUCT where brand =?";
        String getProductsByBrandParams = brand;
        return this.jdbcTemplate.query(getProductsByBrandQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByBrandParams);
    }

    public List<GetProductRes> getProductsByBigCategory(String bigCategory){
        String getProductsByBigCategoryQuery = "select * from PRODUCT where bigCategory =?";
        String getProductsByBigCategoryParams = bigCategory;
        return this.jdbcTemplate.query(getProductsByBigCategoryQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByBigCategoryParams);
    }

    public List<GetProductRes> getProductsByMidCategory(String midCategory){
        String getProductsByMidCategoryQuery = "select * from PRODUCT where midCategory =?";
        String getProductsByMidCategoryParams = midCategory;
        return this.jdbcTemplate.query(getProductsByMidCategoryQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByMidCategoryParams);
    }

    public List<GetProductRes> getProductsBySmallCategory(String smallCategory){
        String getProductsBySmallCategoryQuery = "select * from PRODUCT where smallCategory =?";
        String getProductsBySmallCategoryParams = smallCategory;
        return this.jdbcTemplate.query(getProductsBySmallCategoryQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsBySmallCategoryParams);
    }

    //품절 제외 전체 상품
    public List<GetProductRes> getProductsNotSoldOut(){
        String getProductsQuery = "select * from PRODUCT where soldOut = 0";
        return this.jdbcTemplate.query(getProductsQuery,
                (rs,rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                )
        );
    }

    //품절 제외 이름으로 상품 찾기
    public List<GetProductRes> getProductsByNameNotSoldOut(String name){
        String getProductsByNameQuery = "select * from PRODUCT where name =? AND soldOut = 0";
        String getProductsByNameParams = name;
        return this.jdbcTemplate.query(getProductsByNameQuery,
                (rs, rowNum) -> new GetProductRes(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sellerId"),
                        rs.getString("price"),
                        rs.getString("brand"),
                        rs.getString("img"),
                        rs.getString("detail"),
                        rs.getString("bigCategory"),
                        rs.getString("midCategory"),
                        rs.getString("smallCategory"),
                        rs.getInt("soldOut")
                ),
                getProductsByNameParams);
    }

    //상품명 정보 변경
    public int modifyProductName(PatchProductNameReq patchProductNameReq){
        String modifyProductNameQuery = "update PRODUCT set name = ? where id = ? ";
        Object[] modifyProductNameParams = new Object[]{patchProductNameReq.getName(), patchProductNameReq.getId()};

        return this.jdbcTemplate.update(modifyProductNameQuery,modifyProductNameParams);
    }

    //상품 이미지 정보 변경
    public int modifyProductImg(PatchProductImgReq patchProductImgReq){
        String modifyProductImgQuery = "update PRODUCT set img = ? where id = ? ";
        Object[] modifyProductImgParams = new Object[]{patchProductImgReq.getImg(), patchProductImgReq.getId()};

        return this.jdbcTemplate.update(modifyProductImgQuery,modifyProductImgParams);
    }

    //상품 품절 여부 정보 변경
    public int modifyProductSoldOut(PatchProductSoldOutReq patchProductSoldOutReq){
        String modifyProductSoldOutQuery = "update PRODUCT set soldOut = ? where id = ? ";
        Object[] modifyProductSoldOutParams = new Object[]{patchProductSoldOutReq.getSoldOut(), patchProductSoldOutReq.getId()};

        return this.jdbcTemplate.update(modifyProductSoldOutQuery,modifyProductSoldOutParams);
    }

    //상품 삭제
    public int deleteProduct(int productId){
        String deleteProductNameQuery = "update PRODUCT set status = ? where id = ? ";
        Object[] deleteProductNameParams = new Object[]{1, productId};

        return this.jdbcTemplate.update(deleteProductNameQuery,deleteProductNameParams);
    }

}
