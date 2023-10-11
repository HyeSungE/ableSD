package com.able.ableSD.DTO;

import java.util.Date;

public class Notice {
	
	private Date REG_DT;
	private String NOTICE;
	
	public Notice() {
		super();
	
	}
	public Notice(Date rEG_DT, String nOTICE) {
		super();
		REG_DT = rEG_DT;
		NOTICE = nOTICE;
	}
	public Date getREG_DT() {
		return REG_DT;
	}
	public void setREG_DT(Date rEG_DT) {
		REG_DT = rEG_DT;
	}
	public String getNOTICE() {
		return NOTICE;
	}
	public void setNOTICE(String nOTICE) {
		NOTICE = nOTICE;
	}
	
	

}
