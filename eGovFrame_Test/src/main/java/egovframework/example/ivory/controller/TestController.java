package egovframework.example.ivory.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import egovframework.example.ivory.commons.util.UploadFileUtils;
import egovframework.example.ivory.dao.TestDao;
import egovframework.example.ivory.service.TestService;
import egovframework.example.ivory.vo.Search;
import egovframework.example.ivory.vo.TestVo;
 
@Controller
public class TestController {
    
    @Autowired
    private TestService testService;
    private MultipartFile uploadFile;
    
    //글 상세페이지
    @RequestMapping(value="testDetail.do")
    public String viewForm(Model model, HttpServletRequest request) throws Exception{
        int testId = Integer.parseInt(request.getParameter("testId"));
        
        TestVo testVo = testService.selectDetail(testId);
        model.addAttribute("vo", testVo);
        
        return "test/testDetail";
    }
    
    //글 작성페이지
    @RequestMapping(value="/testRegister.do")
    public String testRegister(){
        return "test/testRegister";
    }
    
    //글쓰기
    @RequestMapping(value="/insertTest.do")
    public String write(@ModelAttribute("testVo") TestVo testVo) throws Exception {
     
    	// 파일 업로드 처리
        String fileName = null;
        MultipartFile uploadFile = testVo.getUploadFile();
        if (!uploadFile.isEmpty()) {
            String originalFileName = uploadFile.getOriginalFilename();
            String ext = FilenameUtils.getExtension(originalFileName); // 확장자 구하기
            UUID uuid = UUID.randomUUID(); // UUID 구하기
            fileName = uuid + "." + ext;
            uploadFile.transferTo(new File("C:\\upload\\" + fileName));
        }
        testVo.setFileName(fileName);
 
        System.out.println(testVo.getFileName());
 
        testService.insertTest(testVo);
        return "redirect:testList.do";
    }
    
    //글 수정
    @RequestMapping(value="/updateTest.do")
    public String updateTest(@ModelAttribute("testVo") TestVo testVo, HttpServletRequest request, MultipartHttpServletRequest req) throws Exception {
    	req.getFile("uploadFile");
    	
    	String testTitle = (String)request.getAttribute("testTitle");
    	System.out.println(testTitle);
    	
    	MultipartFile reqFile;
    	
    	reqFile = req.getFile("uploadFile");
    	
    	reqFile.getName();
    	
    	reqFile.getOriginalFilename();
    	
    	// 파일 수정
    	uploadFile = testVo.getUploadFile();
    	if (!uploadFile.isEmpty()) {
    	    // 기존 파일 삭제
    	    String existingFileName = testVo.getFileName();
    	    if (existingFileName != null) {
    	        String existingFilePath = "C:\\upload\\" + existingFileName;
    	        File existingFile = new File(existingFilePath);
    	        if (existingFile.exists()) {
    	            existingFile.delete();
    	        }
    	    }
    	    
    	    // 새로운 파일 업로드
    	    String originalFileName = uploadFile.getOriginalFilename();
    	    String ext = FilenameUtils.getExtension(originalFileName);
    	    UUID uuid = UUID.randomUUID();
    	    String newFileName = uuid + "." + ext;
    	    String newFilePath = "C:\\upload\\" + newFileName;
    	    File newFile = new File(newFilePath);
    	    uploadFile.transferTo(newFile);
    	    
    	    // 파일명 업데이트
    	    testVo.setFileName(newFileName);
    	}

    	testService.updateTest(testVo);
    	return "redirect:testDetail.do?testId=" + testVo.getTestId();
}
  
    // 파일 수정
 /*   @ResponseBody
    @RequestMapping(value = "/delete/{testId}", method = RequestMethod.POST)
    public String */
    

    //글 삭제
    @RequestMapping(value="/deleteTest.do")
    public String deleteTest(HttpServletRequest request) throws Exception {
        int testId = Integer.parseInt(request.getParameter("testId"));
        testService.deleteTest(testId);
        return "redirect:testList.do";
    }
    
    //글목록페이지,페이징,검색
    @RequestMapping(value="/testList.do")
    public String testListDo(Model model
            ,@RequestParam(required=false,defaultValue="1")int page
            ,@RequestParam(required=false,defaultValue="1")int range
            ,@RequestParam(required=false,defaultValue="testTitle")String searchType
            ,@RequestParam(required=false)String keyword
            ,@ModelAttribute("search")Search search) throws Exception{
        
        //검색
        model.addAttribute("search", search);
        search.setSearchType(searchType);
        search.setKeyword(keyword);
        
        //전체 게시글 개수
        int listCnt = testService.getBoardListCnt(search);
        
        //검색 후 페이지
        search.pageInfo(page, range, listCnt);
        //페이징
        model.addAttribute("pagination", search);
        //게시글 화면 출력
        model.addAttribute("list", testService.selectTest(search));
        
//        model.addAttribute("list", testService.selectTest(testVo));
        
        return "test/testList";
    }

}
