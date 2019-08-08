package com.capgemini.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.capgemini.bean.Customer1;

public interface BankService {

	Customer1 saveCustomer(Customer1 c);

	Map<Long, Customer1> showBalance(String accnum);

	void withdrawAmount(String accnum1,double amt1);

	double fundTransfer(String yaccnum, String raccnum, double amt) throws IOException;

	ArrayList<String> printTransaction(String acc_num);

	void depositAmount(String acnum, double amt);

}
