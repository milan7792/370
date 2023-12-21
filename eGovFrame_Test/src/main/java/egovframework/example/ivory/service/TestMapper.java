package egovframework.example.ivory.service;
 
import java.util.List;
 
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
//Mapper namespace 와 ID를 연결할 Interface 를 두어서 interface를 호출하는 방법.
//Mybatis 매핑XML에 기재된 SQL을 호출하기 위한 인터페이스이다.
//SQL id는 인터페이스에 정의된 메서드명과 동일하게 작성한다
public interface TestMapper {
 
    public List<TestVo> selectTest(Search search) throws Exception;
 
    public void insertTest(TestVo testVo) throws Exception;
 
    public TestVo selectDetail(int testId) throws Exception;
 
    public void updateTest(TestVo testVo) throws Exception;
 
    public void deleteTest(int testId) throws Exception;
 
    public int getBoardListCnt(Search search) throws Exception;
 
}