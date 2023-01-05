package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.jpaTest.model.GetJPACustomerDto;
import com.example.demo.src.jpaTest.model.JPACustomerDto;
import com.example.demo.src.jpaTest.model.PostJPAProductDto;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JPACustomerController {

    private final JPACustomerService jpaCustomerService;

    /**
     * 고객 생성 API
     * [POST] /jpa/customer
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/customer")
    public BaseResponse<String> createJPACustomer(@RequestBody JPACustomerDto jpaCustomerDto) {
        if(jpaCustomerDto.getEmail() == null){
            return new BaseResponse<>(POST_CUSTOMERS_EMPTY_EMAIL);
        }
        try{
            jpaCustomerService.createJPACustomer(jpaCustomerDto);

            String result = "생성 성공!!";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 리스트 조회 API
     * [GET] /jpa/customers
     * @return BaseResponse<List<JPACustomerDto>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/customers")
    public BaseResponse<List<GetJPACustomerDto>> getJPACustomers() {
        try{
            List<GetJPACustomerDto> getJPACustomerDtoList = jpaCustomerService.getJPACustomers();
            return new BaseResponse<>(getJPACustomerDtoList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 정보 변경 API
     * [PATCH] jpa/customers/{id}
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/customers/{id}")
    public BaseResponse<String> modifyJPACustomer(@PathVariable("id") Long jpaCustomerId, @RequestBody JPACustomerDto jpaCustomerDto){
        try {
            if(jpaCustomerDto.getEmail() == null || jpaCustomerDto.getEmail().equals("")) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }
            jpaCustomerService.modifyJPACustomer(jpaCustomerId, jpaCustomerDto);

            String result = "수정 성공!!";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 상품 생성 API
     * [POST] jpa/products
     * @return BaseResponse<String>
     */
    // Body
    @ResponseBody
    @PostMapping("/products")
    public BaseResponse<String> createJPAProduct(@RequestBody PostJPAProductDto postJPAProductDto) {
        if(postJPAProductDto.getName() == null){
            return new BaseResponse<>(POST_PRODUCTS_EMPTY_NAME);
        }
        try{
            jpaCustomerService.createJPAProduct(postJPAProductDto);

            String result = "생성 성공!!";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
