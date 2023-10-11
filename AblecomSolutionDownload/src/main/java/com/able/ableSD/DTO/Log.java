package com.able.ableSD.DTO;

import java.util.Date;

public class Log {
	private String account;
	private String currentDate;
	private String logType;
	private String fileName;
	
	
	public Log(String account, String currentDate, String logType, String fileName) {
		super();
		this.account = account;
		this.currentDate = currentDate;
		this.logType = logType;
		this.fileName = fileName;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Log [account=" + account + ", currentDate=" + currentDate + ", logType=" + logType + ", fileName="
				+ fileName + "]";
	}
	
	
}
