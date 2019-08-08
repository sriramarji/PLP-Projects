package com.capgemini.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import com.capgemini.bean.Customer1;
import com.capgemini.dao.BankDAO;
import com.capgemini.dao.BankDAOImpl;
import com.capgemini.validation.Validators;

public class BankServiceImpl implements BankService {
	
	BankDAO dao = new BankDAOImpl();
	Validators val = new Validators();

	@Override
	public Customer1 saveCustomer(Customer1 c) {
		
		return dao.addAccount(c);
		
	}

	@Override
	public Map<Long, Customer1> showBalance(String accnum) {
		
			return dao.showBal(accnum);
	}

	@Override
	public void depositAmount(String acnum,double amt) {
		
		
			dao.deposit(acnum, amt);
		
	}

	@Override
	public void withdrawAmount(String accnum1,double amt1) {
		
		Customer1 c = new Customer1();
		double new_balance= (c.getMoney())-amt1;
		if(new_balance<1000 ||  (amt1)<0)
		{
			new_balance= c.getMoney();
		}
			dao.withdraw(accnum1, amt1);
		
	}

	@Override
	public double fundTransfer(String yaccnum, String raccnum, double amt) throws IOException {
			return dao.transfer(yaccnum,raccnum, amt);
	}

	@Override
	public ArrayList<String> printTransaction(String acc_num) {
		// TODO Auto-generated method stub
		return null;
	}

}
