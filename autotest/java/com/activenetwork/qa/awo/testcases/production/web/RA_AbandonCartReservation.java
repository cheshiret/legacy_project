package com.activenetwork.qa.awo.testcases.production.web;

import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.testcases.abstractcases.WebTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.UserStoppedScriptException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class RA_AbandonCartReservation extends WebTestCase {
	/**
	 * Script Name : <b>RA_AbandonCartReservation_CT</b> Generated : <b>Jul 12,
	 * 2009 11:20:27 PM</b> Description : Functional Test Script Original Host :
	 * WinNT Version 5.1 Build 2600 (S)
	 * 
	 * @since 2009/07/13
	 * @author QA
	 */
	private String email, pw, url;
	private String msg = "";
	private String payProcessor;
	
	public void execute() {
		
		boolean signedIn=false;
		int failed=0;
		String id = null;
		for(int i=0;i<dataSet.size();i++) {
			int result=RESULT_NA;//used for each record result
			try {
					Map<String, String> record=dataSet.get(i);
					id=record.get("ID");
					getDataFromDataSet(record);
					logger.info("Testing id#:"+id);
					if(!signedIn) {
						web.invokeURL(url, false);
						web.signIn(email, pw);
						signedIn=true;
					}
					if (bd.contractCode.equalsIgnoreCase("KOA")) {
						logger.info("KOA Test running for id#:" + id);
						web.bookSiteIntoCartForKOA(bd);
						web.abandonCart();
					} else if (bd.contractCode.equalsIgnoreCase("ELS") || bd.contractCode.equalsIgnoreCase("TRLS")) {
						logger.info("ELS/TRLS Test running for id#:" + id);
						web.searchSiteIntoExternalWeb(bd);
						if (!web.checkParkNameDisplayed(bd.park))
							logger.error("The park name display with error in external site for id#:" + id);
					} else {
						web.bookSiteIntoCart(bd, od, false);
						web.abandonCart();
//						//Lesley [20130802]The following codes for testing Payment Processor CyberSource. Remove comment if need to test.
//						if (env.equalsIgnoreCase("live")) {
//							web.abandonCart();
//						} else { // For test CyberSource keys in QA
//							String ordNum = web.checkOutCartToConfirmationPage(pay);
//							pay.amount = UwpConfirmationPage.getInstance().getTotalAmount().toString();
//							Payment payFromDB = web.getPaymentInfoByOrderID(schema, ordNum);
//							this.verifyPayment(payFromDB);
//							this.verifyPaymentProcessor(payProcessor, payFromDB.paymentID);
//						}
						
					}
					msg +="#"+id+": contract "+bd.contractCode+" passed.\n";
					result=RESULT_PASSED;
			} catch (UserStoppedScriptException e) {
				throw e;
			} catch (Exception e) {
				result=RESULT_FAILED;
				failed++;
				logger.error("This test failed with record id# " + id);
				e.printStackTrace();
				signedIn=false;
				msg +="#"+id+": contract "+bd.contractCode+" failed due to "+e.getMessage()+".\n";
			} finally {
				DataBaseFunctions.updateResultForDataTable(dataTableName, id, env, result);
			}
		}
		
		web.signOut();
		
		if(failed>0) {
		  	logger.info("The following pirvate lable site(s) failed:\n"+msg);
		  	throw new TestCaseFailedException("Failed: total "+failed+" of "+(dataSet.size())+" records failed.");
		}
	}

	public void wrapParameters(Object[] param) {
		if (env.equalsIgnoreCase("live")) {
			AwoUtil.loadLiveInformation();
			bd.isProduction = true;
			email = TestProperty.getProperty(env + ".ra.email");
			pw = TestProperty.getProperty(env + ".ra.pw");
		} else {
			email = web.getNextEmail();
			pw = TestProperty.getProperty("web.login.pw");
		}
		
		url = TestProperty.getProperty(env + ".web.ra.url");
		bd.isRange = true;
		dataTableName = "p_ra_abandoncartreservation";
		queryDataSql = "select * from " + dataTableName + " where id in (3)";
		
		pay.salesChanl = "Web";
		pay.customer = "LoadTest, Astra";
		pay.status = "Received";
		payProcessor = "CyberSourceCC";
	}

	private void getDataFromDataSet(Map<String, String> record) {
		bd.park = record.get("PARK");
		bd.contractCode = record.get("CONTRACTCODE");
		bd.siteType = record.get("SITETYPE");
		bd.conType = record.get("CONTYPE");
		bd.adultNum = "2";
		bd.childrenNum = "1";
		bd.freeNum = "0";
		bd.branch = 1;
		String nightNum = record.get("NIGHTNUM");
		if (StringUtil.notEmpty(nightNum)) {
			bd.lengthOfStay = nightNum;
		} else {
			bd.lengthOfStay = "2";
		}
		
		String daysNumAfterToday = record.get("DAYSNUMAFTERTODAY");
		if(StringUtil.notEmpty(daysNumAfterToday)){
			bd.arrivalDate = DateFunctions.getDateAfterToday(Integer.valueOf(daysNumAfterToday));
		}else bd.arrivalDate = DateFunctions.getDateAfterToday(10);
		
		if (bd.contractCode.equalsIgnoreCase("EB")) {// For test Cybersource key
			schema = TestProperty.getProperty(env + ".db.schema.prefix") + "EBAY";
		} else {
			schema = TestProperty.getProperty(env + ".db.schema.prefix") + bd.contractCode; 
		}
	}
	
	/**
	 * The method used to verify new created payment is correct in DB
	 * @param pay1
	 */
	private void verifyPayment(Payment pay1){
		logger.info("Verify Payment "+pay1.paymentID);
		if(!(Math.abs(Double.parseDouble(pay1.amount)-Double.parseDouble(pay.amount))<0.00001)){
			throw new ErrorOnDataException("Payment Amount "+pay1.amount+" Not Correct");
		}
		if(!pay1.status.equalsIgnoreCase(pay.status)){
			throw new ErrorOnDataException("Payment Status "+pay1.status+" not correct.");
		}
		if(!pay1.salesChanl.equalsIgnoreCase(pay.salesChanl)){
			throw new ErrorOnDataException("Payment Channel "+pay1.salesChanl+" not correct.");
		}
		if(!pay1.payType.equalsIgnoreCase(pay.payType)){
			throw new ErrorOnDataException("Payment Type "+pay1.payType+" not correct.");
		}
		logger.info("---Successfully Verify Payment "+pay1.paymentID + ", " + pay1.payType + ", " + pay1.status);
	}
	
	private void verifyPaymentProcessor(String expProcessor, String payID) {
		String actual = web.getPaymentProcessorByPayID(schema, payID);
		if (!expProcessor.equalsIgnoreCase(actual)) {
			logger.info("Payment processor from DB is " + actual + " for payment with id=" + payID);
			throw new ErrorOnDataException("payment with id="+payID+" processor is wrong!", expProcessor, actual);
		}
		logger.info("---Successfully verify payment processor for "+payID+" as "+expProcessor);
	}
}
