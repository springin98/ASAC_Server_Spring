package com.example.demo.src.test;

import com.example.demo.common.BaseException;
import com.example.demo.src.test.entity.Comment;
import com.example.demo.src.test.entity.Memo;
import com.example.demo.src.test.model.GetMemoDto;
import com.example.demo.src.test.model.MemoDto;
import com.example.demo.src.test.model.PostCommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.common.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
@Transactional
public class TestService {

    private final TestDao testDao;

    public void createMemo(MemoDto memoDto) throws BaseException {
        Memo memo = new Memo();
        memo.makeMemo(memoDto);

        //중복
        if(checkMemo(memoDto.getMemo()) == 1){
            throw new BaseException(POST_CUSTOMERS_EMPTY_EMAIL);
        }
        try{
            testDao.createMemo(memo); // POINT
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //공통되는 메모 값이 있는지 확인한다. 중복체크
    @Transactional(readOnly = true)
    public int checkMemo(String memo) throws BaseException{
        try{
            List<Memo> memoList = testDao.checkMemo(memo);
            //똑같은 메모의 개수 반환
            return memoList.size();
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetMemoDto> getMemos() throws BaseException{
        try{
            List<Memo> memoList = testDao.getMemos();
            //반복문을 통해서 Memo 형식의 리스트 memoList를 GetMemoDto로 변환해준다.
            List<GetMemoDto> getMemoDtoList = new ArrayList<>();
            for(Memo memo : memoList){
                GetMemoDto dto = new GetMemoDto(memo);
                getMemoDtoList.add(dto);
            }

            return getMemoDtoList;
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //업데이트를 할 때, 날리는 게 아니라 변경감지를 해서 entity 값을 읽고 수정해주면 자동으로 변경해주면 jpa 가 인식하고 업데이트문을 날려준다.
    public void modifyMemo(Long memoId, MemoDto memoDto) throws BaseException{
        try{

            Memo memo = testDao.findMemo(memoId);

            //memo 가 null이면 수정할 게 없음
            if(memo == null){
                throw new BaseException(MODIFY_FAIL_MEMO);
            }

            memo.updateMemo(memoDto);

        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void createComment(PostCommentDto postCommentDto) throws BaseException{
        try {
            //해당 id 메모에 해당하는 커멘트를 저장한다.

            //일단 메모 ID를 조회한다.
            Memo memo = testDao.findMemo(postCommentDto.getMemoId());

            //메모 ID에 해당하는 comment를 만들어준다.
            Comment comment = new Comment();
            //지금 받아온 건 PostCommentDto 이기 때문에 이걸 comment로 바꿔주는 작업을 해줘야 한다.
            //즉, PostCommentDto(memoId, comment가 있다. 얜 임시)의 comment를 받아서 comment(진짜 DB의 Table 형식)의 comment에 넣어준다.
            comment.makeComment(postCommentDto, memo);

            //만들었던 comment를 DB 에 저장한다.
            testDao.createComment(comment);

        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
