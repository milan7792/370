package egovframework.example.ivory.dao.impl;
 
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestMapper;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
 
@Repository
public class TestDaoImpl implements TestDao {
	
	 private static final String NAMESPACE = "egovframework.example.ivory.service.TestMapper";
 
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public List<TestVo> selectTest(Search search) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectTest(search);
    }
 
    @Override
    public void insertTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(int testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.selectDetail(testId);
    }
 
    @Override
    public void updateTest(TestVo testVo) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.updateTest(testVo);
    }
 
    @Override
    public void deleteTest(int testId) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.deleteTest(testId);
    }
 
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        return mapper.getBoardListCnt(search);
    }
    
    // 게시글 첨부파일 추가
    @Override
    public void addAttach(String fileName, Integer testId) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fileName", fileName);
        paramMap.put("testId", testId);
        sqlSession.insert(NAMESPACE + ".addAttach", paramMap);
    }

    // 게시글 첨부파일 조회
    @Override
    public List<String> getAttach(Integer testId) throws Exception {
        return sqlSession.selectList(NAMESPACE + ".getAttach", testId);
    }

    // 게시글 첨부파일 수정
    @Override
    public void replaceAttach(String fileName, Integer testId) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("fileName", fileName);
        paramMap.put("testId", testId);
        sqlSession.insert(NAMESPACE + ".replaceAttach", paramMap);
    }

    // 게시글 첨부파일 삭제
    @Override
    public void deleteAttach(String fileName) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteAttach", fileName);
    }

    // 게시글 첨부파일 일괄 삭제
    @Override
    public void deleteAllAttach(Integer testId) throws Exception {
        sqlSession.delete(NAMESPACE + ".deleteAllAttach", testId);
    }

    // 특정 게시글의 첨부파일 갯수 갱신
    @Override
    public void updateAttachCnt(Integer testId) throws Exception {
        sqlSession.update(NAMESPACE + ".updateAttachCnt", testId);
    }
 
}