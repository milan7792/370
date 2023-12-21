package egovframework.example.ivory.vo;

import org.springframework.web.multipart.MultipartFile;

public class TestVo {
    
    private int testId;
    private String testTitle;
    private String testContent;
    private String testName;
    private String testDate;
    private String fileName;
    private MultipartFile uploadFile;
    private MultipartFile modify_uploadFile;
    
	public MultipartFile getModify_uploadFile() {
		return modify_uploadFile;
	}

	public void setModify_uploadFile(MultipartFile modify_uploadFile) {
		this.modify_uploadFile = modify_uploadFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public int getTestId() {
        return testId;
    }
    public void setTestId(int testId) {
        this.testId = testId;
    }
    public String getTestTitle() {
        return testTitle;
    }
    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }
    public String getTestContent() {
        return testContent;
    }
    public void setTestContent(String testContent) {
        this.testContent = testContent;
    }
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    public String getTestDate() {
        return testDate;
    }
    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }
    
}