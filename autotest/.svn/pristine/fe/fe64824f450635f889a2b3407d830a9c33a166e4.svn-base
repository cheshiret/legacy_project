package com.activenetwork.qa.awo.testcases.production.perf;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public class PLW_AbandonCartReservation extends WebTestCase {
	/**
	 * Script Name   : <b>PLWs_AbandonCartReservation</b>
	 * Generated     : <b>Jul 16, 2009 4:53:31 AM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/07/16
	 * @author QA
	 */
	private String email, pw, url;
	private String msg="";
	private TicketInfo ti = new TicketInfo();

	public void execute() {
		int counter = 0;
		int start=0;
		int end=999;
		int failed =0;
		
		while (!dpIter.dpDone()) {
		  	readDataPool();
		  	if(counter < start || counter > end) {
		  	  	counter++;
		  	  	dpIter.dpNext();
		  	  	continue;
		  	}
		  
			try {
				logger.info("Testing dpRow# " + counter);

				web.invokeURL(url, false);
				web.signIn(email, pw);
				if (ti.tourName != null && ti.tourName.length()>0) {
					logger.info("Ticket reservation workflow running on dpRow# "+ counter);

					web.findTourIntoCart(ti);
				} else {
				  	web.bookSiteIntoCart(bd, od, false);
				}
				
				web.abandonCart();
				web.signOut();
				msg +="#"+counter+": Private Lable "+bd.contractCode+" passed.\n";
			} catch (UserStoppedScriptException e) {
			  	throw e;
			} catch (Exception e) {
			  	failed++;
				logger.error("This test failed with data pool row# " + counter);
				msg +="#"+counter+": Private Lable "+bd.contractCode+" failed test: "+e.getMessage()+".\n";
				e.printStackTrace();
			} finally {
				counter++;
				dpIter.dpNext();
			}
		}
		
		if(failed>0) {
		  	logger.info("The following pirvate lable site(s) failed:\n"+msg);
		  	throw new TestCaseFailedException("Failed: total "+failed+" of "+(counter+1)+" failed for abandonded carts.");
		}
	}

	public void wrapParameters(Object[] param) {
	  	if(env.equalsIgnoreCase("live")) {
	  		AwoUtil.loadLiveInformation();
	  	  	bd.isProduction=true;
	  	  	email = TestProperty.getProperty(env+".ra.email");
	  	  	pw = TestProperty.getProperty(env+".ra.pw");
	  	} else {
	  	  	email = web.getNextEmail();
	  	  	pw = TestProperty.getProperty("web.login.pw");
	  	}
	  	
		dpFileName = AwoUtil.generateDatapoolPath(this.getClass());//casePath + "/" + caseName;
		
		bd.arrivalDate=DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay="3";
		ti.tourDate=DateFunctions.getDateAfterToday(7);
		ti.ticketNums = "2";
	}
	
	// get data pool data
	private void readDataPool() {
	  	bd.park = dpIter.dpString("park");
		bd.conType=dpIter.dpString("conType");
		bd.contractCode = dpIter.dpString("contractCode");
		
		ti.tourName = dpIter.dpString("tourName");
		url=TestProperty.getProperty(env+".web."+bd.contractCode.toLowerCase()+".url");
	}
}
