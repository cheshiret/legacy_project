package com.activenetwork.qa.awo.testcases.regression.basic.web.mix;

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
public class MembershipTesting_NRRSandPLWs extends WebTestCase
{
	/**
	 * Script Name   : <b>MembershipTesting_NRRSandPLWs</b>
	 * Generated     : <b>Nov 26, 2009 11:55:21 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/11/26
	 * @author vzhao
	 */
	private Customer cust;
	private String url,scUrl, plwUrl, conCode,stateName;

	public void execute() {
		// create one NRRS customer account on Rec.gov
		web.invokeURL(url);
		web.createAccount(cust);
		web.signOut();
		web.signIn(cust.email, cust.password);// verify account has been created
		web.signOut();
		web.updateWebSeq(cust.email);

		// Sign in PLWs with account created in NRRS
		int counter = 0;
		int start=0;
		int end=999;
		int failed=0;

		while (!dpIter.dpDone()) {
			dpTestData(); // get data from data pool
			if(counter < start || counter > end) {
				counter++;
				dpIter.dpNext();
				continue;
			}
			try {
				logger.info("Sign in with NRRS account for state: " + stateName);
				if(!plwUrl.startsWith("els")){ //Sara[6/18/2014] Per Aida, we don't have ELS anymore as part of our PL (private labels)
					web.invokeURL(plwUrl,false,false);
					web.verifySignInFailed(cust.email,cust.password);
					dpIter.dpNext();
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

		// only create one PLW customer account in SC and verify log into NRRS failed
		this.scTestData();
		web.invokeURL(scUrl,false,false);
		web.createAccount(cust);
		web.signOut();
		web.signIn(cust.email, cust.password);
		web.signOut();
		web.updateWebSeq(cust.email);

		// Sign in RA with sc account
		web.invokeURL(url,false,false);
		web.verifySignInFailed(cust.email,cust.password);
	}

	public void wrapParameters(Object[] param) {
		dpFileName = AwoUtil.DATAPOOL_ROOT+"/testcases/regression/basic/web/pl/PrivateLabelSites";
		url = TestProperty.getProperty(env + ".web.recgov.url");
		String email = web.generateNewWebEmail("rec", env); //for RA site
		String pw = web.readQADB("OLD_PWD");
		cust = new Customer();
		cust.setDefaultValuesForWeb(email, pw);
	}

	private void dpTestData(){
		stateName = dpIter.dpString("stateName");
		conCode = dpIter.dpString("contractCode");
		plwUrl = TestProperty.getProperty(env + ".web." + conCode + ".url");
	}

	// provide test data for PL sc
	private void scTestData(){
		scUrl = TestProperty.getProperty(env + ".web.sc.url");// create a sc PLW account in sc
		String email = web.generateNewWebEmail("nrrspl", env);
		String pw = web.readQADB("OLD_PWD");
		cust.setAlterValuesForWeb(email, pw);
	}
}
