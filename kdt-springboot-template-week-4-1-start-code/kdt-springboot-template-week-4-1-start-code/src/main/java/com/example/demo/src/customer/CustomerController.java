package com.example.demo.src.customer;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.customer.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;
import static com.example.demo.utils.ValidationRegex.isRegexPassword;

@RequiredArgsConstructor
@RestController
@RequestMapping("/app/customers")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 회원가입 API
     * [POST] /app/customers/new
     * @return BaseResponse<PostCustomerRes>
     */
    @ResponseBody
    @PostMapping("/new")
    public BaseResponse<PostCustomerRes> createCustomer(@RequestBody PostCustomerReq postCustomerReq) {
        if(postCustomerReq.getEmail() == null){
            return new BaseResponse<>(POST_CUSTOMERS_EMPTY_EMAIL);
        }
        if(postCustomerReq.getPassword() == null){
            return new BaseResponse<>(POST_CUSTOMERS_EMPTY_PASSWORD);
        }
        if(postCustomerReq.getName() == null){
            return new BaseResponse<>(POST_CUSTOMERS_EMPTY_NAME);
        }
        if(postCustomerReq.getPhone() == null){
            return new BaseResponse<>(POST_CUSTOMERS_EMPTY_PHONE);
        }
        //이메일 정규표현
        if(!isRegexEmail(postCustomerReq.getEmail())){
            return new BaseResponse<>(POST_CUSTOMERS_INVALID_EMAIL);
        }
        //비밀번호 정규표현
        if(!isRegexPassword(postCustomerReq.getPassword())){
            return new BaseResponse<>(POST_CUSTOMERS_INVALID_PASSWORD);
        }
        try{
            PostCustomerRes postCustomerRes = customerService.createCustomer(postCustomerReq);
            return new BaseResponse<>(postCustomerRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 전체 조회 API
     * [GET] /app/customers/all
     * 회원 이메일 검색 조회 API
     * [GET] /app/customers/all?Email=
     * 회원 전화번호 검색 조회 API
     * [GET] /app/customers/all?Phone=
     * @return BaseResponse<List<GetCustomerRes>>
     */
    //Query String
    @ResponseBody
    @GetMapping("/all") // (GET) 127.0.0.1:9000/app/customers/all
    public BaseResponse<List<GetCustomerRes>> getCustomers(@RequestParam(required = false) String Email, @RequestParam(required = false) String Phone) {
        try {
            if (Email == null && Phone != null) {
                List<GetCustomerRes> getCustomersRes = customerService.getCustomersByPhone(Phone);
                return new BaseResponse<>(getCustomersRes);
            }
            if (Email != null && Phone == null) {
                List<GetCustomerRes> getCustomersRes = customerService.getCustomersByEmail(Email);
                return new BaseResponse<>(getCustomersRes);
            }
            //Get Customers
            List<GetCustomerRes> getCustomerRes = customerService.getCustomers();
            return new BaseResponse<>(getCustomerRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 이메일 변경 API
     * [PATCH] /app/customers/email/:customerId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("email/{customerId}")
    public BaseResponse<List<GetCustomerRes>> modifyCustomerEmail(@PathVariable("customerId") int customerId, @RequestBody Customer customer){
        if(!isRegexEmail(customer.getEmail())){
            return new BaseResponse<>(POST_CUSTOMERS_INVALID_EMAIL);
        }

        try {
            PatchCustomerEmailReq patchCustomerEmailReq = new PatchCustomerEmailReq(customerId, customer.getEmail());
            customerService.modifyCustomerEmail(patchCustomerEmailReq);

            List<GetCustomerRes> getCustomersRes = customerService.getCustomersById(customerId);
            return new BaseResponse<>(getCustomersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 비밀번호 변경 API
     * [PATCH] /app/customers/password/:customerId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("password/{customerId}")
    public BaseResponse<List<GetCustomerRes>> modifyCustomerPassword(@PathVariable("customerId") int customerId, @RequestBody Customer customer){
        if(!isRegexPassword(customer.getPassword())){
            return new BaseResponse<>(POST_CUSTOMERS_INVALID_PASSWORD);
        }
        try {
            PatchCustomerPasswordReq patchCustomerPasswordReq = new PatchCustomerPasswordReq(customerId, customer.getPassword());
            customerService.modifyCustomerPassword(patchCustomerPasswordReq);

            List<GetCustomerRes> getCustomersRes = customerService.getCustomersById(customerId);
            return new BaseResponse<>(getCustomersRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원정보삭제 API
     * [DELETE] /app/customers/delete/:customerId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @DeleteMapping("delete/{customerId}")
    public BaseResponse<String> deleteCustomer(@PathVariable("customerId") int customerId){
        try {
            customerService.deleteCustomer(customerId);

            String result = "";
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
