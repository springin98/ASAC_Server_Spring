package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.common.BaseResponse;
import com.example.demo.common.BaseResponseStatus;
import com.example.demo.src.customer.model.GetCustomerRes;
import com.example.demo.src.test.model.*;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;


    /**
     * 로그 테스트 API
     * [GET] /test/log
     * @return String
     */
    @ResponseBody
    @GetMapping("/log")
    public String getAll() {
        System.out.println("테스트");
        return "Success Test";
    }

    /**
     * 메모 생성 API
     * [POST] /test/memos
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("memos")
    public BaseResponse<PostTestRes> createTest(@RequestBody PostTestReq postTestReq) {
        if(postTestReq.getMemo() == null){
            return new BaseResponse<>(TEST_EMPTY_MEMO);
        }
        try{
            PostTestRes postTestRes = testService.createTest(postTestReq);
            return new BaseResponse<>(postTestRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메모 전체 조회
     * [GET] /test/all
     * @return BaseResponse<GetUserRes>
     */
    @ResponseBody
    @GetMapping("/all")
    public BaseResponse<List<GetTestRes>> getTests() {
        try {
            List<GetTestRes> getTestRes = testService.getTest();
            return new BaseResponse<>(getTestRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 메모정보변경 API
     * [PATCH] /app/memos/:userId
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PatchMapping("memos/{testId}")
    public BaseResponse<List<GetTestRes>> modifyTestName(@PathVariable("testId") int testId, @RequestBody Test test){
        try {
            PatchTestReq patchTestReq = new PatchTestReq(testId,test.getMemo());
            testService.modifyTestName(patchTestReq);

            List<GetTestRes> getTestRes = testService.getTest();
            return new BaseResponse<>(getTestRes);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
