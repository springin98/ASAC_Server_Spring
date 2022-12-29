package com.example.demo.src.product;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.product.model.GetProductRes;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.product.model.PostProductRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/products")
public class ProductController {

    private final ProductService productService;

    @ResponseBody
    @PostMapping("/new")
    public BaseResponse<PostProductRes> creatProduct(@RequestBody PostProductReq postProductReq) {
        if(postProductReq.getName() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_NAME);
        }
        if(postProductReq.getSellerId() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_SELLERID);
        }
        if(postProductReq.getPrice() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_PRICE);
        }
        if(postProductReq.getBrand() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_BRAND);
        }
        if(postProductReq.getImg() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_IMG);
        }
        if(postProductReq.getDetail() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_DETAIL);
        }
        if(postProductReq.getBigCategory() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_BIGCATEGORY);
        }
        if(postProductReq.getMidCategory() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_MIDCATEGORY);
        }
        if(postProductReq.getSmallCategory() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_SMALLCATEGORY);
        }
        try{
            PostProductRes postProductRes = productService.createProduct(postProductReq);
            return new BaseResponse<>(postProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 전체 조회 API
     * [GET] /app/products/all
     * 상품 이름, 브랜드, 카테고리별 검색 조회 API
     * [GET] /app/products/all?Name=
     * [GET] /app/products/all?Brand=
     * [GET] /app/products/all?BigCategory=
     * [GET] /app/products/all?MidCategory=
     * [GET] /app/products/all?SmallCategory=
     * @return BaseResponse<List<GetCustomerRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/all") // (GET) 127.0.0.1:9000/app/customers/all
    public BaseResponse<List<GetProductRes>> getProducts(
            @RequestParam(required = false) String Name,
            @RequestParam(required = false) String Brand,
            @RequestParam(required = false) String BigCategory,
            @RequestParam(required = false) String MidCategory,
            @RequestParam(required = false) String SmallCategory
    ) {
        try{
            if(Name != null){
                List<GetProductRes> getProductsRes = productService.getProductsByName(Name);
                return new BaseResponse<>(getProductsRes);
            }
            if(Brand != null){
                List<GetProductRes> getProductsRes = productService.getProductsByBrand(Brand);
                return new BaseResponse<>(getProductsRes);
            }
            if(BigCategory != null){
                List<GetProductRes> getProductsRes = productService.getProductsByBigCategory(BigCategory);
                return new BaseResponse<>(getProductsRes);
            }
            if(MidCategory != null){
                List<GetProductRes> getProductsRes = productService.getProductsByMidCategory(MidCategory);
                return new BaseResponse<>(getProductsRes);
            }
            if(SmallCategory != null){
                List<GetProductRes> getProductsRes = productService.getProductsBySmallCategory(SmallCategory);
                return new BaseResponse<>(getProductsRes);
            }
            // Get Products 모든 상품 조회
            List<GetProductRes> getProductRes = productService.getProducts();
            return new BaseResponse<>(getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 품절 제외 상품 전체 조회 API
     * [GET] /app/products/notSoldOut
     * 품절 제외 상품 이름 검색 조회 API
     * [GET] /app/products/notSoldOut?Name=
     * @return BaseResponse<List<GetCustomerRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/notSoldOut") // (GET) 127.0.0.1:9000/app/customers/all
    public BaseResponse<List<GetProductRes>> getProductsNotSoldOut(
            @RequestParam(required = false) String Name
    ) {
        try{
            if(Name != null){
                List<GetProductRes> getProductsRes = productService.getProductsByNameNotSoldOut(Name);
                return new BaseResponse<>(getProductsRes);
            }
            // Get Products 모든 상품 조회
            List<GetProductRes> getProductRes = productService.getProductsNotSoldOut();
            return new BaseResponse<>(getProductRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
