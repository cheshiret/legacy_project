package com.activenetwork.qa.awo.testcases.regression.basic.web.pl;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author vzhao
 */
public class AccountExclusiveWithinPLWs extends WebTestCase
{
	/**
	 * Script Name   : <b>AccountExclusiveWithinPLWs</b>
	 * Generated     : <b>Nov 27, 2009 1:15:28 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/27
	 * @author vzhao
	 */
	private Customer cust;
	private String url,scUrl, plwUrl,stateName,conCode;

	public void execute() {
		// create one Colorado customer account 
		web.invokeURL(url);
		web.createAccount(cust);
		web.signOut();
		web.signIn(cust.email, cust.password); // verify account has been created
		web.signOut();
		web.updateWebSeq(cust.email);// update web seq after account created

		int counter = 0;
		int start=0;
		int end=999;
		int failed=0;
		// Sign in PLWs with account created in RA
		while (!dpIter.dpDone()) {
			dpTestData(); // get data from data pool
			if(counter < start || counter > end) {
				counter++;
				dpIter.dpNext();
				continue;
			}

			try {
				logger.info("Sign in " + stateName + " with CO account.");
				if(!plwUrl.startsWith("els")){//Sara[6/18/2014] Per Aida, we don't have ELS anymore	
					web.invokeURL(plwUrl,false,false);
					web.signIn(cust.email, cust.password); // verify whether the new account can sign in other PLWs
					web.signOut();
				}

			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				failed++;
				logger.error("#"+counter+"failed: "+e.getMessage()+".\n");
				e.printStackTrace();
			} finally {
				counter++;
				dpIter.dpNext();
			}
		}
		if(failed>0) {
			throw new TestCaseFailedException("Failed: total "+failed+" of "+(counter+1)+" failed on account verification.");
		} 
		// verify the same info can not sign up in other PLW
		web.invokeURL(scUrl,false,false);
		web.verifyAccountExists(cust);
	}

	public void wrapParameters(Object[] param) {
		dpFileName = AwoUtil.generateDatapoolPath(this.getClass(),"PrivateLabelSites");//use test case CreateNewAccount's data pool file

		url = TestProperty.getProperty(env + ".web.co.url");
		scUrl = TestProperty.getProperty(env + ".web.sc.url");
		String email = web.generateNewWebEmail("pl", env); //for PL site
		String pw = web.readQADB("OLD_PWD");
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);
	}

	private void dpTestData(){
		stateName = dpIter.dpString("stateName");
		conCode = dpIter.dpString("contractCode");
		plwUrl = TestProperty.getProperty(env + ".web." + conCode + ".url");
	}
}
