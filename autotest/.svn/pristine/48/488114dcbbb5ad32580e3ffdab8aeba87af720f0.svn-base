package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Prerequsiste	 : make sure there is no fee(Transaction fee) for the tour selected in the script.
 * Description   : Functional Test Script
 * @author vzhao
 * Modified by Jane to avoid case conflict
 */
public class MakeFreeTourReservation extends RecgovTestCase {
	/**
	 * Script Name   : <b>MakeFreeTourReservation</b>
	 * Generated     : <b>Dec 14, 2009 8:41:42 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/12/14
	 * @author vzhao
	 */
  	private String email, pw, url;
	private TicketInfo bd;
	private LoginInfo login = new LoginInfo();
	private FeeScheduleData fee;
	private FinanceManager fm = FinanceManager.getInstance();

	public void execute() {
		//update the fee schedule to 0, both use fee and transaction fee
		fm.loginFinanceManager(login);
		fm.searchToFeeScheduleDetailPg(fee);
		fm.updateUseFeeScheduleDetails(fee, false, true, 1);
		
		fee.feeType = "Transaction Fee";
		fm.searchToFeeScheduleDetailPg(fee);
		fm.updateTransFeeScheduleDetails(fee, true, 1);
		fm.logoutFinanceManager();

		//make a tour reservation which is free
		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.bookTourIntoCart(bd);
		//will verify the success in this keyword and verify whether or not the res is free
		web.checkOutFreeTourCart(); 
		web.signOut();
	}

	public void wrapParameters(Object[] param) {
		//finance manager login paras
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
		login.contract = "NRRS Contract";
		login.location = "Administrator/NRRS";
		// fee shcedule paras
		fee = new FeeScheduleData(true);
		fee.searchType = "Product";
//	  	fee.searchValue = "Snowball Tour";
		fee.searchValue = "Eleanor(Val Kill) Indiv";
	  	fee.feeType = "Ticket Fee";
	  	fee.activeStatus = "Active";
	  	fee.permitType = "";

		// update all rates to 0
	  	fee.nightlyRate = "0";
		fee.monRate = fee.nightlyRate;
		fee.tuesRate = fee.nightlyRate;
		fee.wedRate = fee.nightlyRate;
		fee.thurRate = fee.nightlyRate;
		fee.friRate = fee.nightlyRate;
		fee.satRate = fee.nightlyRate;
		fee.sunRate = fee.nightlyRate;
		
		fee.tranFee = fee.nightlyRate;// for transaction fee schedule

		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
//		bd.tourPark = "Mammoth Cave National Park Tours";
//		bd.tourName = "Snowball Tour";
		bd.park="ROOSEVELT-VANDERBILT NATIONAL HISTORIC SITES";
		bd.tourName="Eleanor(Val Kill) Indiv";
		bd.tourDate = DateFunctions.getDateAfterToday(3);
		bd.ticketNums = "3";
		bd.ticketType = "Adult";
		bd.isUnifiedSearch=isUnifiedSearch();
//		bd.parkId = "77817";
		bd.parkId = "77814";
	}
}
