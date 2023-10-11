package com.able.ableSD.DTO;

public class User {

	private String account;
	private String passwd;
	private String sid;
	
	
	
	public User(String account, String passwd, String sid) {
		super();
		this.account = account;
		this.passwd = passwd;
		this.sid = sid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
}
