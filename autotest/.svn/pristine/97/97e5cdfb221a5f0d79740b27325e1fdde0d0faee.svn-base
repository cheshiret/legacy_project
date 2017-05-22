package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class UpdateUserAccount_DE extends WebTestCase
{
	/**
	 * Script Name   : <b>UpdateUserAccount_DE</b>
	 * Generated     : <b>Apr 22, 2010 4:31:19 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2010/04/22
	 * @author QA
	 */
	private Customer cust;
	private String url;

	public void execute() {
		web.invokeURL(url);

		//create a new account
		web.createAccount(cust);
		web.signOut();
		web.signIn(cust.email, cust.password);
		web.updateWebSeq(cust.email);

		//update email
		String newEmail = web.generateNewWebEmail("de", env);
		web.updateEmail(newEmail, cust.password);
		web.signOut();
		cust.email = newEmail;//use new email
		web.signIn(cust.email, cust.password);
		web.updateWebSeq(cust.email);

		//update password
		String newPw = web.readQADB("NEW_PWD");
		web.updatePassword(cust.password, newPw);
		web.signOut();
		cust.password = newPw;//use new psw
		web.signIn(cust.email, cust.password);
		
		//update profile
		cust.setAlterValuesForWeb(cust.email, cust.password);
		web.updateProfile(cust);//will verify the cust info within this keyword
		web.signOut();

		//verify signin after above actions
		web.signIn(cust.email, cust.password);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.de.url");
		String email = web.generateNewWebEmail("de", env); //for private lable
		String pw = web.readQADB("OLD_PWD");

		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);
	}
}

