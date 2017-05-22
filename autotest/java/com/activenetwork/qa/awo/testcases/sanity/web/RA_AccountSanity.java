package com.activenetwork.qa.awo.testcases.sanity.web;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.RATestCase;
import com.activenetwork.qa.testapi.util.TestProperty;

public class RA_AccountSanity extends RATestCase {
	/**
	 * @since  2010/12/15
	 * @author VZHAO
	 */
	private Customer cust;
	private String url;

	public void execute() {
		web.invokeURL(url);

		//create account
		web.createAccount(cust);
		web.signOut();

		web.signIn(cust.email, cust.password);
		web.updateWebSeq(cust.email);

		//update email
		String newEmail = web.generateNewWebEmail("ra", env);
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
		//roll back password
		web.updatePassword(newPw, cust.password);
		web.signOut();
		web.signIn(cust.email, cust.password);

		//update profile
		cust.setAlterValuesForWeb(cust.email, cust.password);
		web.updateProfile(cust);
		web.signOut();

		//verify login
		web.signIn(cust.email, cust.password);
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		String email = web.generateNewWebEmail("ra", env);
		String pw = web.readQADB("OLD_PWD");

		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);

		url = TestProperty.getProperty(env + ".web.ra.url");
	}
}
