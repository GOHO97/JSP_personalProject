package com.ho.firstWebProjec.home;

import java.util.Date;

public class Member {
	private String photo;
	private String id;
	private String pw;
	private String name;
	private Date birthday;
	private String email;
	private String addr;

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Member(String photo, String id, String pw, String name, Date birthday, String email, String addr) {
		super();
		this.photo = photo;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.addr = addr;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	
}
