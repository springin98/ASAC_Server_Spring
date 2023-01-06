package com.example.demo.src.jpaTest;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.jpaTest.entity.JPASeller;
import com.example.demo.src.jpaTest.model.GetJPASellerDto;
import com.example.demo.src.jpaTest.model.JPASellerDto;
import com.example.demo.src.jpaTest.model.PostJPASellerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jpa")
public class JPASellerController {

    private final JPASellerService jpaSellerService;

    /**
     * 판매자 생성 API
     * [POST] /jpa/sellers
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("/sellers")
    public BaseResponse<String> createJPASeller(@RequestBody JPASellerDto jpaSellerDto) {
        
        //입력값 확인
        if(jpaSellerDto.getCompany() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_COMPANY);
        }
        else if(jpaSellerDto.getCeo() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_CEO);
        }
        else if(jpaSellerDto.getAddress() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_ADDRESS);
        }
        else if(jpaSellerDto.getPhone() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_PHONE);
        }
        else if(jpaSellerDto.getEmail() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_EMAIL);
        }
        else if(jpaSellerDto.getNumber() == null){
            return new BaseResponse<>(POST_SELLERS_EMPTY_NUMBER);
        }
        //이메일 형식 확인
        else if(!isRegexEmail(jpaSellerDto.getEmail())){
            return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
        }

        try{
            jpaSellerService.createJPASeller(jpaSellerDto);

            String email = jpaSellerDto.getEmail();
            return new BaseResponse<>(email);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 판매자 리스트 조회 API
     * [GET] /jpa/sellers
     * @return BaseResponse<List<JPASellerDto>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/sellers")
    public BaseResponse<List<GetJPASellerDto>> getJPASellers() {
        try{
            List<GetJPASellerDto> getJPASellerDtoList = jpaSellerService.getJPASellers();
            return new BaseResponse<>(getJPASellerDtoList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 아이디로 판매자 조회 API
     * [GET] /jpa/sellers/{id}
     * @return BaseResponse<List<JPASellerDto>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/sellers/{id}")
    public BaseResponse<List<GetJPASellerDto>> getJPASeller(@PathVariable("id") Long jpaSellerId) {
        try{
            List<GetJPASellerDto> getJPASellerDtoList = jpaSellerService.getJPASellerById(jpaSellerId);
            return new BaseResponse<>(getJPASellerDtoList);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 판매자 정보 변경 API
     * [PATCH] jpa/sellers/{id}
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("/sellers/{id}")
    public BaseResponse<List<GetJPASellerDto>> modifyJPASeller(@PathVariable("id") Long jpaSellerId, @RequestBody JPASellerDto jpaSellerDto){
        try {
            /**
            /* NULL 값은 확인하지 않는다. 자유롭게 변환 가능하도록
            if(jpaSellerDto.getEmail() == null || jpaSellerDto.getEmail().equals("")) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }
             */
            if(
                    jpaSellerDto.getCompany() == null && jpaSellerDto.getCeo() == null && jpaSellerDto.getAddress() == null &&
                            jpaSellerDto.getEmail() == null && jpaSellerDto.getNumber() == null && jpaSellerDto.getPhone() == null
            ) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }
            else if(
                    jpaSellerDto.getCompany().equals("") && jpaSellerDto.getCeo().equals("") && jpaSellerDto.getAddress().equals("") &&
                            jpaSellerDto.getEmail().equals("") && jpaSellerDto.getNumber().equals("") && jpaSellerDto.getPhone().equals("")
            ) {
                throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
            }

            jpaSellerService.modifyJPASeller(jpaSellerId, jpaSellerDto);

            List<GetJPASellerDto> getJPASellerDtoList = jpaSellerService.getJPASellerById(jpaSellerId);
            return new BaseResponse<>(getJPASellerDtoList);

        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 아이디로 판매자 삭제
     * [DELETE] /jpa/sellers/{id}
     * @return BaseResponse<List<JPASellerDto>>
     */
    //Query String
    @ResponseBody
    @DeleteMapping("/sellers/{id}")
    public BaseResponse<String> deleteJPASeller(@PathVariable("id") Long jpaSellerId) {
        try{
            jpaSellerService.deleteJPAProduct(jpaSellerId);

            String result = "판매자를 삭제했습니다.";
            return new BaseResponse<>(result);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
