package egovframework.example.ivory.service.impl;
 
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
 
@Service
public class TestServiceImpl implements TestService{
 
    @Autowired
    private TestDao testDao;
    
    @Override
    public List<TestVo> selectTest(Search search) throws Exception {
        return testDao.selectTest(search);
    }
 
    @Override
    public void insertTest(TestVo testVo) throws Exception {
        testDao.insertTest(testVo);
    }
 
    @Override
    public TestVo selectDetail(int testId) throws Exception {
        return testDao.selectDetail(testId);
    }
 
    @Override
    public void updateTest(TestVo testVo) throws Exception {
        testDao.updateTest(testVo);
    }
 
    @Override
    public void deleteTest(int testId) throws Exception {
        testDao.deleteTest(testId);
    }
 
    @Override
    public int getBoardListCnt(Search search) throws Exception {
        return testDao.getBoardListCnt(search);
    }

	@Override
	public void addAttach(String fileName, Integer testId) throws Exception {
		testDao.addAttach(fileName, testId);
	}

	@Override
	public List<String> getAttach(Integer testId) throws Exception {
		return testDao.getAttach(testId);
	}

	@Override
	public void replaceAttach(String fileName, Integer testId) throws Exception {
		testDao.replaceAttach(fileName, testId);
	}

	@Override
	public void deleteAttach(String fileName) throws Exception {
		testDao.deleteAttach(fileName);
	}

	@Override
	public void deleteAllAttach(Integer testId) throws Exception {
		testDao.deleteAllAttach(testId);
	}

	@Override
	public void updateAttachCnt(Integer testId) throws Exception {
		testDao.updateAttachCnt(testId);
	}

	@Override
	public List<TestVo> selectTest(TestVo testVo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
 
}