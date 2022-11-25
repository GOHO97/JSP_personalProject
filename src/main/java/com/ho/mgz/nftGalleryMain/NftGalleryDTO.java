package com.ho.mgz.nftGalleryMain;

import java.util.Date;

public class NftGalleryDTO {
	private int no;
	private String owner;
	private String photo;
	private String title;
	private String txt;
	private Date date;
	private String name;
	private String ownerPhoto;
	
	public NftGalleryDTO() {
		// TODO Auto-generated constructor stub
	}

	public NftGalleryDTO(int no, String owner, String photo, String title, String txt, Date date, String name,
			String ownerPhoto) {
		super();
		this.no = no;
		this.owner = owner;
		this.photo = photo;
		this.title = title;
		this.txt = txt;
		this.date = date;
		this.name = name;
		this.ownerPhoto = ownerPhoto;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwnerPhoto() {
		return ownerPhoto;
	}

	public void setOwnerPhoto(String ownerPhoto) {
		this.ownerPhoto = ownerPhoto;
	}
	
	
}
