package egovframework.example.ivory.dao;
 
import java.util.List;
 
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
 
public interface TestDao {
 
    public List<TestVo> selectTest(Search search) throws Exception;
 
    public void insertTest(TestVo testVo) throws Exception;
 
    public TestVo selectDetail(int testId)throws Exception;
 
    public void updateTest(TestVo testVo) throws Exception;
 
    public void deleteTest(int testId) throws Exception;
 
    public int getBoardListCnt(Search search) throws Exception;
    
    // 게시글 첨부파일 추가
    public void addAttach(String fileName, Integer testId) throws Exception;

    // 게시글 첨부파일 조회
    public List<String> getAttach(Integer testId) throws Exception;

    // 게시글 첨부파일 수정
    public void replaceAttach(String fileName, Integer testId) throws Exception;

    // 게시글 첨부파일 삭제
    public void deleteAttach(String fileName) throws Exception;

    // 게시글 첨부파일 일괄 삭제
    public void deleteAllAttach(Integer testId) throws Exception;

    // 특정 게시글의 첨부파일 갯수 갱신
    public void updateAttachCnt(Integer testId) throws Exception;
    
}