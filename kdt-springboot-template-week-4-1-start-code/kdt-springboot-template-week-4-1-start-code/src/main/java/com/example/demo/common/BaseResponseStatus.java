package com.example.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 200 : 요청 성공
     */
    SUCCESS(true, HttpStatus.OK.value(), "요청에 성공하였습니다."),


    /**
     * 400 : Request, Response 오류
     */

    // [POST] /users
    POST_USERS_EMPTY_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일을 입력해주세요."),
    POST_USERS_INVALID_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일 형식을 확인해주세요."),
    POST_USERS_EXISTS_EMAIL(false,HttpStatus.BAD_REQUEST.value(),"중복된 이메일입니다."),
    POST_USERS_EXISTS_PHONE(false,HttpStatus.BAD_REQUEST.value(),"중복된 전화번호입니다."),

    RESPONSE_ERROR(false, HttpStatus.NOT_FOUND.value(), "값을 불러오는데 실패하였습니다."),

    // [POST] /users
    DUPLICATED_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "중복된 이메일입니다."),
    FAILED_TO_LOGIN(false,HttpStatus.NOT_FOUND.value(),"없는 아이디거나 비밀번호가 틀렸습니다."),

    // [POST] /memos
    TEST_EMPTY_MEMO(false,HttpStatus.NOT_FOUND.value(),"메모를 입력해 주세요."),


    //CUSTOMER
    POST_CUSTOMERS_EMPTY_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일을 입력해주세요."),
    POST_CUSTOMERS_EMPTY_PASSWORD(false, HttpStatus.BAD_REQUEST.value(), "비밀번호를 입력해주세요."),
    POST_CUSTOMERS_EMPTY_NAME(false, HttpStatus.BAD_REQUEST.value(), "고객명을 입력해주세요."),
    POST_CUSTOMERS_EMPTY_PHONE(false, HttpStatus.BAD_REQUEST.value(), "전화번호를 입력해주세요."),
    POST_CUSTOMERS_INVALID_EMAIL(false, HttpStatus.BAD_REQUEST.value(), "이메일 형식을 확인해주세요."),
    POST_CUSTOMERS_INVALID_PASSWORD(false, HttpStatus.BAD_REQUEST.value(), "비밀번호 형식을 확인해주세요."),

    //PRODUCT
    POST_PRODUCTS_EMPTY_NAME(false, HttpStatus.BAD_REQUEST.value(), "상품명을 입력해주세요."),
    POST_PRODUCTS_EMPTY_SELLERID(false, HttpStatus.BAD_REQUEST.value(), "상품자 ID를 입력해주세요."),
    POST_PRODUCTS_EMPTY_PRICE(false, HttpStatus.BAD_REQUEST.value(), "상품 가격을 입력해주세요."),
    
    POST_PRODUCTS_EMPTY_BRAND(false, HttpStatus.BAD_REQUEST.value(), "브랜드를 입력해주세요."),
    POST_PRODUCTS_EMPTY_IMG(false, HttpStatus.BAD_REQUEST.value(), "대표 이미지 URL 를 입력해주세요."),
    POST_PRODUCTS_EMPTY_DETAIL(false, HttpStatus.BAD_REQUEST.value(), "상세 이미지 URL 를 입력해주세요."),
    POST_PRODUCTS_EMPTY_BIGCATEGORY(false, HttpStatus.BAD_REQUEST.value(), "상품의 큰 카테고리를 입력해주세요."),

    POST_PRODUCTS_EMPTY_MIDCATEGORY(false, HttpStatus.BAD_REQUEST.value(), "상품의 중간 카테고리를 입력해주세요."),
    POST_PRODUCTS_EMPTY_SMALLCATEGORY(false, HttpStatus.BAD_REQUEST.value(), "상품의 작은 카테고리를 입력해주세요."),

    POST_PRODUCTS_EXISTS_NAME(false, HttpStatus.BAD_REQUEST.value(), "같은 상품이 이미 있습니다."),

    /**
     * 500 :  Database, Server 오류
     */
    DATABASE_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버와의 연결에 실패하였습니다."),

    //[PATCH]
    MODIFY_FAIL_TESTNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"메모 수정 실패"),

    //[PATCH] /users/{userIdx}
    MODIFY_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저네임 수정 실패"),
    DELETE_FAIL_USERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"유저 삭제 실패"),

    //[PATCH] /customers/change/{customerId}
    MODIFY_FAIL_CUSTOMEREMAIL(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"이메일 변경 실패"),
    MODIFY_FAIL_CUSTOMERPASSWORD(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"비밀번호 변경 실패"),
    DELETE_FAIL_CUSTOMERNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"회원 삭제 실패"),


    MODIFY_FAIL_PRODUCTNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"상품명 변경 실패"),
    MODIFY_FAIL_PRODUCTIMG(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"상품 이미지 변경 실패"),
    MODIFY_FAIL_PRODUCTSOLDOUT(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"상품 품절 여부 변경 실패"),
    DELETE_FAIL_PRODUCTNAME(false,HttpStatus.INTERNAL_SERVER_ERROR.value(),"상품 삭제 실패");


    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
