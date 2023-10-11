package com.able.ableSD.DTO;

public class SolutionFiles {
	
	private String SOLUTION_CD;
	private String FILE_SEQ;
	private String FILE_NAME;
	private String FILE_PATH;
	private String FILE_TYPE;
	
	
	
	public SolutionFiles() {
		super();
	}
	
	public SolutionFiles(String sOLUTION_CD, String fILE_SEQ, String fILE_NAME, String fILE_PATH, String fILE_TYPE) {
		super();
		SOLUTION_CD = sOLUTION_CD;
		FILE_SEQ = fILE_SEQ;
		FILE_NAME = fILE_NAME;
		FILE_PATH = fILE_PATH;
		FILE_TYPE = fILE_TYPE;
	}
	public String getSOLUTION_CD() {
		return SOLUTION_CD;
	}
	public void setSOLUTION_CD(String sOLUTION_CD) {
		SOLUTION_CD = sOLUTION_CD;
	}
	public String getFILE_SEQ() {
		return FILE_SEQ;
	}
	public void setFILE_SEQ(String fILE_SEQ) {
		FILE_SEQ = fILE_SEQ;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getFILE_PATH() {
		return FILE_PATH;
	}
	public void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}
	public String getFILE_TYPE() {
		return FILE_TYPE;
	}
	public void setFILE_TYPE(String fILE_TYPE) {
		FILE_TYPE = fILE_TYPE;
	}
	
	
	

}
