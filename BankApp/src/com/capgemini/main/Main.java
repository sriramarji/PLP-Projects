package com.capgemini.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.capgemini.bean.Customer1;
import com.capgemini.service.BankService;
import com.capgemini.service.BankServiceImpl;
import com.capgemini.validation.Validators;

public class Main {
	
public static void main(String[] args) throws IOException {
	
	
		BankService service = new BankServiceImpl();
		Validators val = new Validators();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to XYZ Bank!!!!\n");
		String ch = "yes";
		while(ch.equals("Yes") || ch.equals("yes"))
		{
			Customer1 c1 = new Customer1();
			System.out.println("Please enter your choice\n\n1. Create Bank Account\n2. Show Balance\n3. Deposit\n4. Withdraw\n5. Fund Transfer");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1 : 
				
				System.out.println("Enter First Name:");
				String str = sc.next();
				if(val.name(str))
					c1.setFname(str);
				else {
					System.out.println("Please start your First Name with capital letter");
					System.exit(0);
				}
				System.out.println("Enter Last Name:");
				String str1 = sc.next();
				if(val.name(str1))
					c1.setLname(str1);
				else {
					System.out.println("Please start your Last Name with capital letter");
					System.exit(0);
				}
				System.out.println("Enter Phone Number:");
				String l1 =sc.next();
				if(val.phnnum(l1))
					c1.setPhnum(l1);
				else {
					System.out.println("Please enter valid 10 digit Phone Number");
					System.exit(0);
				}
				System.out.println("Enter Aadhar Number:");
				String l2 = sc.next();
				if(val.aadnum(l2))
					c1.setAadharnum(Long.parseLong(l2));
				else {
					System.out.println("Please enter valid 12 digit Aadhar Number");
					System.exit(0);
				}
				System.out.println("Enter money to be deposited:");
				double l3 = sc.nextDouble();
				c1.setMoney(l3);
				if(l3 == 0) {
					System.out.println("Minimum balance should be maintained!!!");
				}
				service.saveCustomer(c1);
				break;
			case 2 :
				System.out.println("Enter Account Number:");
				String accnum = sc.next();
				System.out.println("The Account details are:\n"+service.showBalance(accnum));
				//service.showBalance(accnum);
				break;
			case 3 :
				System.out.println("Enter Account Number:");
				String acnum = sc.next();
				System.out.println("Enter Amount to Deposit:");
				Double amt=sc.nextDouble();
				service.depositAmount(acnum,amt);
				break;
			case 4 :
				System.out.println("Enter Account Number:");
				String accnum1 = sc.next();
				System.out.println("Enter Amount to Withdraw:");
				Double amt1=sc.nextDouble();
				service.withdrawAmount(accnum1,amt1);
				break;
			case 5 :
				System.out.println("Enter your Account Number:");
				String yaccnum = sc.next();
				System.out.println("Enter recievers Account Number:");
				String raccnum = sc.next();
				System.out.println("Enter ammount to be transfered:");
				double amt2 =sc.nextDouble();
				System.out.print(service.fundTransfer(yaccnum, raccnum, amt2));
				break;
			case 6 :
				System.out.println("Enter Account Number");
				ArrayList<String> list = new ArrayList<String>();
				String acc_num = sc.next();
				list = service.printTransaction(acc_num);
				Iterator<String> itr = list.iterator();
				while(itr.hasNext())
				{
					System.out.println(itr.next());
				}
				break;
			default :
				System.out.println("Please enter valid choice");
				break;
	
			}
			System.out.println("Do you want to continue: Yes or No");
			ch=sc.next();
		}
	}

}
