package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.src.customer.model.GetCustomerRes;
import com.example.demo.src.test.model.GetTestRes;
import com.example.demo.src.test.model.PatchTestReq;
import com.example.demo.src.test.model.PostTestReq;
import com.example.demo.src.test.model.PostTestRes;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class TestService {

    private final TestDao testDao;

    //메모 생성
    public PostTestRes createTest(PostTestReq postTestReq) throws BaseException {
        try{
            int testId = testDao.createTest(postTestReq); // POINT
            return new PostTestRes(testId);
        } catch (Exception exception) {
            // exception.printStackTrace();
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //메모 조회
    public List<GetTestRes> getTest() throws BaseException{
        try{
            List<GetTestRes> getTestRes = testDao.getTest();
            return getTestRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //메모 수정
    public void modifyTestName(PatchTestReq patchTestReq) throws BaseException {
        try{
            int result = testDao.modifyTestName(patchTestReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_TESTNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

}
