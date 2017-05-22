package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class UpdateUserAccount_ID extends WebTestCase
{
	/**
	 * Script Name   : <b>UpdateUserAccount_ID</b>
	 * Generated     : <b>Nov 26, 2009 8:42:10 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/26
	 * @author vzhao
	 */
  	private Customer cust;
	private String url;

	public void execute() {
		web.invokeURL(url);

		web.createAccount(cust);
		web.signOut();

		web.signIn(cust.email, cust.password);
		web.updateWebSeq(cust.email);

		//update email
		String newEmail = web.generateNewWebEmail("id", env);
		web.updateEmail(newEmail, cust.password);
		cust.email = newEmail;
		web.signOut();

		web.signIn(cust.email, cust.password);
		web.updateWebSeq(cust.email);

		//update password
		String newPw = web.readQADB("NEW_PWD");
		web.updatePassword(cust.password, newPw);
		web.signOut();

		web.signIn(cust.email, newPw);
		web.updatePassword(newPw, cust.password);
		web.signOut();
		web.signIn(cust.email, cust.password);

		//update profile
		cust.setAlterValuesForWeb(cust.email, cust.password);
		web.updateProfile(cust);
		web.signOut();

		//verify signin
		web.signIn(cust.email, cust.password);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
	  	url = TestProperty.getProperty(env + ".web.id.url");
		String email = web.generateNewWebEmail("id", env); //for private lable
		String pw = web.readQADB("OLD_PWD");

		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);
	}
}
