# SpringBoot-JPA

## 목표
- 회원과 상품 정보를 추가, 조회, 수정, 삭제할 수 있다.

## 개발 기간
- 2022.12.1~2023.1.6

## 멤버
- 개인 프로젝트

## 개발 환경
- Spring Boot, JPA

## 주요 기능
- 회원 정보 관리
  - POST : 회원 가입
  - GET : 회원 전체 조회, 이메일로 회원 조회, 전화번호로 회원 조회
  - PATCH : 회원 이메일 변경, 비밀번호 변경
  - DEL : 회원 정보 삭제
- 상품 정보 관리
  - POST : 상품 등록
  - GET : 상품 전체 조회, 상품명 조회, 브랜드 조회, 카테고리(대,중,소) 조회, 품절 제외 조회
  - PATCH : 상품명, 상품 이미지, 상품 품절 여부 변경
  - DEL : 상품 삭제
  
  ## POSTMAN 이미지
  ![image](https://user-images.githubusercontent.com/114986489/228476088-b50dcb5e-c9dd-4db2-8b1f-1d81d641e8ac.png)
