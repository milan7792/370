package egovframework.example.ivory.controller;
 
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
 
@Controller
public class FileDownloadController {
 
    @RequestMapping(value = "fileDownload.do")
    public void fileDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
 
        String filename = request.getParameter("fileName");
        String realFilename = "";
        System.out.println(filename);
 
        try {
 
            String browser = request.getHeader("User-Agent");
            // 파일 인코딩
            if (browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")) {
                filename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
            } else {
                filename = new String(filename.getBytes("UTF-8"), "ISO-8859-1");
            }
 
        } catch (UnsupportedEncodingException e) {
 
            System.out.println("UnsupportedEncodingException 발생");
 
        }
 
        realFilename = "C:\\upload\\" + filename;
        System.out.println(realFilename);
 
        File file = new File(realFilename);
        if (!file.exists()) {
            return;
        }
 
        // 파일명 지정
        response.setContentType("application/octer-stream");
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
 
        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(realFilename);
 
            int cnt = 0;
            byte[] bytes = new byte[512];
 
            while ((cnt = fis.read(bytes)) != -1) {
                os.write(bytes, 0, cnt);
            }
 
            fis.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
    }
    
   /* // 게시글 첨부파일 출력
    @RequestMapping(value = "/display", method = RequestMethod.GET)
    public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request) throws Exception {

        HttpHeaders httpHeaders = UploadFileUtils.getHttpHeaders(fileName); // Http 헤더 설정 가져오기
        String rootPath = UploadFileUtils.getRootPath(fileName, request); // 업로드 기본경로 경로

        ResponseEntity<byte[]> entity = null;

        // 파일데이터, HttpHeader 전송
        try (InputStream inputStream = new FileInputStream(rootPath + fileName)) {
            entity = new ResponseEntity<>(IOUtils.toByteArray(inputStream), httpHeaders, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }*/
    
    
    /*@ResponseBody
    public ResponseEntity<String> deleteFile(String fileName, String type) {
    	File file;
    	try {
    		file = new File("C:\\upload\\" + URLDecoder.decode(fileName, "UTF-8"));
    		file.delete();
    		if(type.equals("image")) {
    			String largeFileName = file.getAbsolutePath().replace("s_", "");
    			file = new File(largeFileName);
    			file.delete();
    		}
    	} catch(UnsupportedEncodingException e) {
    		e.printStackTrace();
    		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);    		
    	}
    	return new ResponseEntity<String>("deleted", HttpStatus.OK);
    }*/
 
}