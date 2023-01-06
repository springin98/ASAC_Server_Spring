package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.jpaTest.entity.JPAProduct;
import com.example.demo.src.jpaTest.model.GetJPAProductDto;
import com.example.demo.src.jpaTest.model.GetJPASellerDto;
import com.example.demo.src.jpaTest.model.JPASellerDto;
import com.example.demo.src.jpaTest.model.PostJPASellerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JPAProductController {

    private final JPAProductService jpaProductService;

    /**
     * 상품 생성 API
     * [POST] jpa/products
     * @return BaseResponse<String>
     */
    // Body
    @ResponseBody
    @PostMapping("/products")
    public BaseResponse<String> createJPAProduct(@RequestBody PostJPASellerDto postJPAProductDto) {
        if(postJPAProductDto.getName() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_NAME);
        }
        else if(postJPAProductDto.getPrice() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_PRICE);
        }
        else if(postJPAProductDto.getBrand() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_BRAND);
        }
        else if(postJPAProductDto.getImg() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_IMG);
        }
        else if(postJPAProductDto.getDetail() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_DETAIL);
        }
        try{
            jpaProductService.createJPAProduct(postJPAProductDto);

            String result = "상품을 생성했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 아이디로 상품 조회 API
     * [GET] /jpa/products/{id}
     * @return BaseResponse<List<JPAProductDto>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/products/{id}")
    public BaseResponse<List<GetJPAProductDto>> getJPAProduct(@PathVariable("id") Long jpaProductId) {
        try{
            List<GetJPAProductDto> getJPAProductDtoList = jpaProductService.getJPAProductById(jpaProductId);
            return new BaseResponse<>(getJPAProductDtoList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 리스트 조회 API
     * [GET] /jpa/products
     * @return BaseResponse<List<JPAProductDto>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/products")
    public BaseResponse<List<GetJPAProductDto>> getJPAProducts() {
        try{
            List<GetJPAProductDto> getJPAProductDtoList = jpaProductService.getJPAProducts();
            return new BaseResponse<>(getJPAProductDtoList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 정보 변경 API
     * [PATCH] jpa/products/{id}
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/products/{id}")
    public BaseResponse<List<GetJPAProductDto>> modifyJPAProduct(@PathVariable("id") Long jpaProductId, @RequestBody GetJPAProductDto jpaProductDto){
        try {
             // NULL 값은 확인하지 않는다. 자유롭게 변환 가능하도록
            if(
                    jpaProductDto.getName() == null && jpaProductDto.getPrice() == null && jpaProductDto.getBrand() == null &&
                            jpaProductDto.getImg() == null && jpaProductDto.getDetail() == null
            ) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }
            else if(
                    jpaProductDto.getName().equals("") && jpaProductDto.getPrice().equals("") && jpaProductDto.getBrand().equals("") &&
                            jpaProductDto.getImg().equals("") && jpaProductDto.getDetail().equals("")
            ) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }

            jpaProductService.modifyJPAProduct(jpaProductId, jpaProductDto);

            List<GetJPAProductDto> getJPAProductDtoList = jpaProductService.getJPAProductById(jpaProductId);
            return new BaseResponse<>(getJPAProductDtoList);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 아이디로 상품 삭제
     * [DELETE] /jpa/products/{id}
     * @return BaseResponse<List<JPAProductDto>>
     */
    //Query String
    @ResponseBody
    @DeleteMapping("/products/{id}")
    public BaseResponse<String> deleteJPAProduct(@PathVariable("id") Long jpaProductId) {
        try{
            jpaProductService.deleteJPAProduct(jpaProductId);

            String result = "상품을 삭제했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
