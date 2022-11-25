package com.ho.mgz.nftGalleryMain;
//프사, 아이디, 댓글내용, 날짜

import java.util.Date;

public class ReplyDTO {
	private String photo;
	private String id;
	private String txt;
	private Date date;
	private int no;
	
	public ReplyDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReplyDTO(String photo, String id, String txt, Date date, int no) {
		super();
		this.photo = photo;
		this.id = id;
		this.txt = txt;
		this.date = date;
		this.no = no;
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

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	
}
