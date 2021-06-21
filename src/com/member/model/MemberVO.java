package com.member.model;

import java.io.Serializable;
import java.sql.Date;

public class MemberVO implements Serializable {
	private Integer memberNo;
	private String account;
	private String password;
	private String name;
	private String idNumber;
	private Date birthDate;
	private String phone;
	private String email;
	private Integer memberState;
	private byte[] memberPic;
	private byte[] liscePic1;
	private byte[] liscePic2;
	private byte[] liscePic3;
	private String lisceName1;
	private String lisceName2;
	private String lisceName3;

	public MemberVO() {
	}

	public Integer getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getMemberState() {
		return memberState;
	}

	public void setMemberState(Integer memberState) {
		this.memberState = memberState;
	}

	public byte[] getMemberPic() {
		return memberPic;
	}

	public void setMemberPic(byte[] memberPic) {
		this.memberPic = memberPic;
	}

	public byte[] getLiscePic1() {
		return liscePic1;
	}

	public void setLiscePic1(byte[] liscePic1) {
		this.liscePic1 = liscePic1;
	}

	public byte[] getLiscePic2() {
		return liscePic2;
	}

	public void setLiscePic2(byte[] liscePic2) {
		this.liscePic2 = liscePic2;
	}

	public byte[] getLiscePic3() {
		return liscePic3;
	}

	public void setLiscePic3(byte[] liscePic3) {
		this.liscePic3 = liscePic3;
	}

	public String getLisceName1() {
		return lisceName1;
	}

	public void setLisceName1(String lisceName1) {
		this.lisceName1 = lisceName1;
	}

	public String getLisceName2() {
		return lisceName2;
	}

	public void setLisceName2(String lisceName2) {
		this.lisceName2 = lisceName2;
	}

	public String getLisceName3() {
		return lisceName3;
	}

	public void setLisceName3(String lisceName3) {
		this.lisceName3 = lisceName3;
	}

}
