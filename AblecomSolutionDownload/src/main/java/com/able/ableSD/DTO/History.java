package com.able.ableSD.DTO;

public class History {
	private int RID;
	private String SOLUTION_CD;
	private String FILE_NAME;
	private String ST_DATE;
	private String ST_TIME;
	private String EMP_NO;
	private String WORK_TYPE;
	
	
	public History() {
		super();
	}
	public History(int rID, String sOLUTION_CD, String fILE_NAME, String sT_DATE, String sT_TIME, String eMP_NO,
			String wORK_TYPE) {
		super();
		RID = rID;
		SOLUTION_CD = sOLUTION_CD;
		FILE_NAME = fILE_NAME;
		ST_DATE = sT_DATE;
		ST_TIME = sT_TIME;
		EMP_NO = eMP_NO;
		WORK_TYPE = wORK_TYPE;
	}
	public int getRID() {
		return RID;
	}
	public void setRID(int rID) {
		RID = rID;
	}
	public String getSOLUTION_CD() {
		return SOLUTION_CD;
	}
	public void setSOLUTION_CD(String sOLUTION_CD) {
		SOLUTION_CD = sOLUTION_CD;
	}
	public String getFILE_NAME() {
		return FILE_NAME;
	}
	public void setFILE_NAME(String fILE_NAME) {
		FILE_NAME = fILE_NAME;
	}
	public String getST_DATE() {
		return ST_DATE;
	}
	public void setST_DATE(String sT_DATE) {
		ST_DATE = sT_DATE;
	}
	public String getST_TIME() {
		return ST_TIME;
	}
	public void setST_TIME(String sT_TIME) {
		ST_TIME = sT_TIME;
	}
	public String getEMP_NO() {
		return EMP_NO;
	}
	public void setEMP_NO(String eMP_NO) {
		EMP_NO = eMP_NO;
	}
	public String getWORK_TYPE() {
		return WORK_TYPE;
	}
	public void setWORK_TYPE(String wORK_TYPE) {
		WORK_TYPE = wORK_TYPE;
	}
	
	
}
