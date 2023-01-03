package com.example.demo.src.test.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "COMMENT")
public class Comment extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMO_ID")
    private Memo memo;

    public void makeComment(PostCommentDto postCommentDto, Memo memo) {
        //entity의 Memo를 Comment의 memo로 복붙하고
        this.memo = memo;
        //entity의 PostCommentDto의 POSTMAN에서 입력받은 comment를 여기의 comment로 복붙한다.
        this.comment = postCommentDto.getComment();
    }
}
