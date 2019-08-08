package com.capgemini.dao;

import java.io.IOException;
import java.util.Map;

import com.capgemini.bean.Customer1;

public interface BankDAO {

	Customer1 addAccount(Customer1 c);

	Map<Long, Customer1> showBal(String accnum);

	void deposit(String acnum,double amt);

	void withdraw(String accnum1,double amt1);

	double transfer(String yaccnum, String raccnum, double amt) throws IOException;

}
