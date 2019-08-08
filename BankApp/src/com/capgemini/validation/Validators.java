package com.capgemini.validation;

public class Validators {

	public boolean name(String str) {
		boolean name_val = str.matches("[A-Z][a-z]*");
		if(name_val)
			return true;
		return false;
	}

	public boolean phnnum(String l1) {
		boolean phnnum = l1.matches("^[0-9]{10}$");
		if(phnnum)
			return true;
		return false;
	}

	public boolean aadnum(String l2) {
		boolean adnum = l2.matches("^[0-9]{12}$");
		if(adnum)
			return true;
		return false;
	}
	
}
