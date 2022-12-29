package com.example.demo.src.customer;

import com.example.demo.common.BaseException;
import com.example.demo.src.customer.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerDao customerDao;

    //POST
    public PostCustomerRes createCustomer(PostCustomerReq postCustomerReq) throws BaseException {
        //중복
        if(checkEmail(postCustomerReq.getEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }
        if(checkPhone(postCustomerReq.getPhone()) == 1){
            throw new BaseException(POST_USERS_EXISTS_PHONE);
        }
        try{
            int customerId = customerDao.createCustomer(postCustomerReq); // POINT
            return new PostCustomerRes(customerId);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public int checkEmail(String email) throws BaseException{
        try{
            return customerDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    public int checkPhone(String phone) throws BaseException{
        try{
            return customerDao.checkPhone(phone);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCustomerRes> getCustomers() throws BaseException{
        try{
            List<GetCustomerRes> getCustomerRes = customerDao.getCustomers();
            return getCustomerRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCustomerRes> getCustomersByEmail(String email) throws BaseException{
        try{
            List<GetCustomerRes> getCustomersRes = customerDao.getCustomersByEmail(email);
            return getCustomersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCustomerRes> getCustomersByPhone(String phone) throws BaseException{
        try{
            List<GetCustomerRes> getCustomersRes = customerDao.getCustomersByPhone(phone);
            return getCustomersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetCustomerRes> getCustomersById(int customerId) throws BaseException{
        try{
            List<GetCustomerRes> getCustomersRes = customerDao.getCustomersById(customerId);
            return getCustomersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //회원 이메일 변경
    public void modifyCustomerEmail(PatchCustomerEmailReq patchCustomerReq) throws BaseException {
        //이메일 중복 확인
        if(checkEmail(patchCustomerReq.getEmail()) == 1){
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
        }

        try{
            int result = customerDao.modifyCustomerEmail(patchCustomerReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_CUSTOMEREMAIL);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //회원 비밀번호 변경
    //회원 정보 변경
    public void modifyCustomerPassword(PatchCustomerPasswordReq patchUserReq) throws BaseException {
        try{
            int result = customerDao.modifyCustomerPassword(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_CUSTOMERPASSWORD);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //회원 정보 삭제
    public void deleteCustomer(int customerId) throws BaseException {
        try{
            int result = customerDao.deleteCustomer(customerId);
            if(result == 0){
                throw new BaseException(DELETE_FAIL_CUSTOMERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
