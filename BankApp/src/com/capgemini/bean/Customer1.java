package com.capgemini.bean;

import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer1 {

	@Column(length=20)
	private String fname;
	
	@Column(length=20)
	private String lname;
	
	@Column(length=10)
	private String phnum;
	
	@Column(length=12)
	private long aadharnum;
	
	@Id
	@Column(length=4)
	private long accountnum;
	
	@Column(length=20)
	private double money;
	
	
	public Customer1() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Customer1(String fname, String lname, String phnum, long aadharnum, long accountnum, double money) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.phnum = phnum;
		this.aadharnum = aadharnum;
		this.accountnum = accountnum;
		this.money = money;
	}

	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getPhnum() {
		return phnum;
	}
	public void setPhnum(String l1) {
		this.phnum = l1;
	}


	public long getAadharnum() {
		return aadharnum;
	}
	public void setAadharnum(long aadharnum) {
		this.aadharnum = aadharnum;
	}


	public long getAccountnum() {
		return accountnum;
	}
	
	public void setAccountnum(long accountnum)
	{
		this.accountnum=(long) ((Math.random() * ((9999 - 999) + 1)) + 999); 
		
	}

	public double getMoney() {
		return money;
	}
	public void setMoney(double string) {
		this.money = string;
	}
	
	@Override
	public String toString() {
		return "Customer: \n\t[ First Name=" + fname + "\n\tLast Name=" + lname + "\n\tPhone Number=" + phnum + "\n\tAadhar Number=" + aadharnum
				+ "\n\tAccount Number=" + accountnum + "\n\tMoney=" + money + " ]";
	}


	public static Customer1 toString(String readLine) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
