package com.example.demo.src.test.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentDto {

    //메모의 id
    private Long memoId;
    //comment의 내용
    private String comment;

}
