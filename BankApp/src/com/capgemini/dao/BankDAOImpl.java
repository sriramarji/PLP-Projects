package com.capgemini.dao;

import java.io.IOException;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
//import javax.persistence.Query;
//import javax.persistence.QueryHint;
import javax.persistence.TypedQuery;

import com.capgemini.bean.Customer1;

public class BankDAOImpl implements BankDAO {
	
	static Map<Long,Customer1> accmap = new HashMap<Long,Customer1>();
	
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("BankApp");
	
	EntityManager em=emf.createEntityManager();

	@Override
	public Customer1 addAccount(Customer1 c) {
		em.getTransaction().begin();
		Customer1 ob1= new Customer1();
		
		ob1.setFname(c.getFname());
		ob1.setLname(c.getLname());
		ob1.setPhnum(c.getPhnum());
		ob1.setAadharnum(c.getAadharnum());
		ob1.setAccountnum(c.getAccountnum());
		ob1.setMoney(c.getMoney());
		em.persist(ob1);
		em.getTransaction().commit();
		System.out.println(" Successfully Added ");
	    return null;
	}

	@Override
	public Map<Long, Customer1> showBal(String accnum) {
		em.getTransaction().begin();
		TypedQuery<Customer1> tq = em.createQuery("select c from Customer1 c where c.accountnum= :ac", Customer1.class);
        tq.setParameter("ac", Long.parseLong(accnum));
        tq.getSingleResult();
		Customer1 c= tq.getSingleResult(); 
		em.getTransaction().commit();
		System.out.println(c.getMoney());
		return accmap;
	}

	@Override
	public void deposit(String acnum,double amt) {
	
//		Customer s2=em.find(Customer.class, c.getAccountnum());
//        em.getTransaction().begin();
//        s2.setMoney(c.getMoney());
//        em.merge(s2);
//        em.getTransaction().commit();
		em.getTransaction().begin();
		TypedQuery<Customer1> tq = em.createQuery("select c from Customer1 c where c.accountnum= :ac", Customer1.class);
        tq.setParameter("ac", Long.parseLong(acnum));
     
        double bal = tq.getSingleResult().getMoney();
        
        tq = em.createQuery("update Customer1 c set c.money = :x where c.accountnum= :ac", Customer1.class);
        tq.setParameter("x", bal+amt);
        tq.setParameter("ac", Long.parseLong(acnum));
    
        int i=tq.executeUpdate();
       
        em.getTransaction().commit();
        System.out.println("The balance is :"+(bal+amt));
	}

	@Override
	public void withdraw(String accnum1,double amt1) {
//		Customer s2=em.find(Customer.class, c.getAccountnum());
//        em.getTransaction().begin();
//        s2.setMoney(c.getMoney());
//        em.merge(s2);
//        em.getTransaction().commit();
        
        System.out.println(accnum1);
		TypedQuery<Customer1> tq = em.createQuery("select c from Customer1 c where c.accountnum= :ac", Customer1.class);
        tq.setParameter("ac", Long.parseLong(accnum1));
     
        double bal = tq.getSingleResult().getMoney();
        em.getTransaction().begin();
        tq = em.createQuery("update Customer1 c set c.money = :x where c.accountnum= :ac", Customer1.class);
        tq.setParameter("x", bal-amt1);
        tq.setParameter("ac", Long.parseLong(accnum1));
    
        int i=tq.executeUpdate();
        System.out.println(i);
        em.getTransaction().commit();
        System.out.println("The balance is :"+(bal-amt1));
		
	}

	@Override
	public double transfer(String yaccnum, String raccnum, double amt) throws IOException {
		
		em.getTransaction().begin();
		TypedQuery<Customer1> cust =em.createQuery("SELECT c FROM Customer1 c WHERE c.accountnum= :ac", Customer1.class);
		cust.setParameter("ac", Long.parseLong(yaccnum));
		Customer1 c = cust.getSingleResult();
		double accbal=c.getMoney();

		TypedQuery<Customer1> cust1=em.createQuery("SELECT c FROM Customer1 c WHERE c.accountnum= :ac", Customer1.class);
		cust1.setParameter("ac", Long.parseLong(raccnum));
		Customer1 c1 = cust1.getSingleResult();
		double r_accbal = c1.getMoney();
		
		cust=em.createQuery("UPDATE Customer1 c SET c.money = :money WHERE c.accountnum= :ac", Customer1.class);
		cust.setParameter("money", accbal-amt );
		cust.setParameter("ac", Long.parseLong(yaccnum));
		cust.executeUpdate();
		
		cust1=em.createQuery("UPDATE Customer1 c1 SET c1.money = :money WHERE c1.accountnum= :raccnum", Customer1.class);
		cust1.setParameter("money", r_accbal+amt);
		cust1.setParameter("raccnum", Long.parseLong(raccnum));
		cust1.executeUpdate();
		em.getTransaction().commit();
		return accbal-amt;
	}
	
	/*public double fund(String acc_num, String reciepent_acc_num, double amount) {
    //em.getTransaction().begin();
	//TypedQuery<Banker> bank=(TypedQuery<Banker>) em.createQuery("SELECT b FROM Banker b WHERE b.account_number==acc_num");
	//Banker b=(Banker) bank.getResultList();
	//double acc_bal=b.getAccountBalance();
    em.getTransaction().begin();
	TypedQuery<Banker> bank=em.createQuery("SELECT b FROM Banker b WHERE b.accountNumber= :acc_num", Banker.class);
	bank.setParameter("acc_num", acc_num);
	Banker b=bank.getSingleResult();
	double acc_bal=b.getAccountBalance();

	TypedQuery<Banker> bank1=em.createQuery("SELECT b FROM Banker b WHERE b.accountNumber= :reciepent_acc_num", Banker.class);
	bank1.setParameter("reciepent_acc_num", reciepent_acc_num);
	Banker b1=bank1.getSingleResult();
	double reciepent_acc_bal=b1.getAccountBalance();
	
	bank=em.createQuery("UPDATE Banker b SET b.accountBalance= :money WHERE b.accountNumber= :acc_num", Banker.class);
	bank.setParameter("money", acc_bal-amount);
	bank.setParameter("acc_num", acc_num);
	bank.executeUpdate();
	
	bank1=em.createQuery("UPDATE Banker b1 SET b1.accountBalance= :money WHERE b1.accountNumber= :reciepent_acc_num", Banker.class);
	bank1.setParameter("money", reciepent_acc_bal+amount);
	bank1.setParameter("reciepent_acc_num", reciepent_acc_num);
	bank1.executeUpdate();
	em.getTransaction().commit();

	return acc_bal-amount;

}*/

}
