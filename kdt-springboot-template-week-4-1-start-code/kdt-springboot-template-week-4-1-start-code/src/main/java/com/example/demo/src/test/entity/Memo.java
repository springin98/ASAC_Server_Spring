package com.example.demo.src.test.entity;

import com.example.demo.common.BaseEntity;
import com.example.demo.common.BaseResponse;
import com.example.demo.src.test.model.MemoDto;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Getter
@Entity
@Table(name = "MEMO")
public class Memo extends BaseEntity {
    @Id
    //PK값을 의미한다.
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //자동으로 id를 설정한다.
    private Long id;
    private String memo;

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "memo", fetch = FetchType.LAZY)
    List<Comment> comments = new ArrayList<Comment>();

    public void makeMemo(MemoDto memoDto) {
        this.memo = memoDto.getMemo();
    }

    public void updateMemo(MemoDto memoDto) {
        this.memo = memoDto.getMemo();
    }
}
