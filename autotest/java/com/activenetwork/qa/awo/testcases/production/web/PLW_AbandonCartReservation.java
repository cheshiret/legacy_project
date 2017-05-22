package com.activenetwork.qa.awo.testcases.production.web;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
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
	private boolean processcart=false;

	public void execute() {
		int failed =0;
		String id = null;
		
		for(int i=0;i<dataSet.size();i++) {
			int result=RESULT_NA;//used for each record result
			try {
				Map<String, String> record=dataSet.get(i);
				id=record.get("ID");
				getDataFromDataSet(record);
				logger.info("Testing id#:"+id);
				if (url.trim().length() ==0) {	
					logger.info("Ignore testing id# " + id +", b/c there is no URL info setup in config file.");
					result=RESULT_NOT_RUN;
					DataBaseFunctions.updateResultForDataTable(dataTableName, id, env, result);
					continue;
				}
				web.invokeURL(url, false);
				web.signIn(email, pw);
				if (ti.tourName != null && ti.tourName.length()>0) {
					logger.info("Ticket reservation workflow running on id# "+ id);
					web.findTourIntoCart(ti);
				} else {
				  	web.bookSiteIntoCart(bd, od, false);
				}
				
				if(!env.equalsIgnoreCase("live") && processcart) {
					web.checkOutCart(new Payment("Visa"));
				} else {
					web.abandonCart();
				}
				web.signOut();
				result=RESULT_PASSED;
				msg +="id#"+id+": Private Lable "+bd.contractCode+" passed.\n";
			} catch (UserStoppedScriptException e) {
			  	throw e;
			} catch (Exception e) {
				result=RESULT_FAILED;
				String specialFromDate = String.valueOf(DateFunctions.getCurrentYear()) + "/03/31";
				String specialToDate = String.valueOf(DateFunctions.getCurrentYear()) + "/10/20";
				
				if(!bd.contractCode.equals("AN") || 
						(bd.contractCode.equals("AN") && !(DateFunctions.compareDates(ti.tourDate, specialFromDate) >0 && DateFunctions.compareDates(ti.tourDate, specialToDate) <0 ))){
					failed++;
					logger.error("This test failed with data pool id# " + id);
					msg +="id#"+id+": Private Lable "+bd.contractCode+" failed test: "+e.getMessage()+".\n";
					e.printStackTrace();
				}		  	
			} finally {
				DataBaseFunctions.updateResultForDataTable(dataTableName, id, env, result);
			}
		}
		
		if(failed>0) {
		  	logger.info("The following pirvate lable site(s) failed:\n"+msg);
		  	throw new TestCaseFailedException("Failed: total "+failed+" of "+dataSet.size()+" failed for abandonded carts.");
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
		
		bd.arrivalDate=DateFunctions.getDateAfterToday(3);
		bd.lengthOfStay="3";
		ti.tourDate=DateFunctions.getDateAfterToday(7);
		ti.ticketNums = "2";
		ti.deliveryMethod = "Mail Out";
		
		dataTableName = "p_plw_abandoncartreservation";
		queryDataSql = "select * from " + dataTableName + " where id in (14) ";
	}
	
	private void getDataFromDataSet(Map<String, String> record) {
	  	bd.park = record.get("PARK");
		bd.conType= record.get("CONTYPE");
		bd.contractCode = record.get("CONTRACTCODE");
		bd.state = record.get("STATE");
		bd.lengthOfStay=record.get("DAYS");
		
		ti.tourName = record.get("TOURNAME");
		url=TestProperty.getProperty(env+".web."+bd.contractCode.toLowerCase()+".url");
		
		String daysNumAfterToday = record.get("DAYSNUMAFTERTODAY");
		if(StringUtil.notEmpty(daysNumAfterToday)){
			bd.arrivalDate = DateFunctions.getDateAfterToday(Integer.valueOf(daysNumAfterToday));
			ti.tourDate = bd.arrivalDate;
		}
	}
}
