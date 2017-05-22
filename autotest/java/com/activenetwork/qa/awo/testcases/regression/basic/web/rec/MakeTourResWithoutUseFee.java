package com.activenetwork.qa.awo.testcases.regression.basic.web.rec;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.Conf;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.config.ShoppingcartProperty;
import com.activenetwork.qa.awo.datacollection.legacy.FeeScheduleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.FinanceManager;
import com.activenetwork.qa.awo.pages.web.uwp.UwpShoppingCartPage;
import com.activenetwork.qa.awo.testcases.abstractcases.RecgovTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.WebConfiguration;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * Prerequisite: there should be an existing Ticket Fee and Transaction fee for the select product. if not, you can run the support script, the data is already there.
 * @author vzhao
 */
public class MakeTourResWithoutUseFee extends RecgovTestCase {
	/**
	 * Script Name   : <b>MakeTourResWithoutUseFee</b>
	 * Generated     : <b>Dec 14, 2009 8:42:46 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/12/14
	 * @author vzhao
	 */
	private String label;
  	private String email, pw, url;
	private TicketInfo bd;
	private LoginInfo login = new LoginInfo();
	private FeeScheduleData fee;
	private FinanceManager fm = FinanceManager.getInstance();
	private UwpShoppingCartPage shoppingCart = UwpShoppingCartPage.getInstance();

	public void execute() {
		//update the ticket fee schedule to 0, and update reservation fee large than 0
		fm.loginFinanceManager(login);
		fm.searchToFeeScheduleDetailPg(fee);
		fm.updateUseFeeScheduleDetails(fee, false, true, 1);
		
		fee.feeType="Transaction Fee";
		fm.searchToFeeScheduleDetailPg(fee);
		fm.updateTransFeeScheduleDetails(fee, true, 1);
		fm.logoutFinanceManager();

		//make a tour reservation which has no use fee only
		web.invokeURL(url);
		web.signIn(email, pw);
		
		web.bookTourIntoCart(bd);
		this.verifyFeesInCart();
		web.checkOutTourCart(pay); // will verify the success in this keyword
		
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
	  	fee.searchValue = "Gothic Avenue Tour";
	  	fee.feeType = "Ticket Fee";
	  	fee.activeStatus = "Active";
	  	
	  	fee.ticketOrpermitCat = " ";
	  	fee.permitType = " ";
	  	fee.season = " ";
	  	fee.state = " ";
	  	fee.salesChannel = " ";

		//update all rates to 0
	  	fee.nightlyRate = "0";
		fee.monRate = fee.nightlyRate;
		fee.tuesRate = fee.nightlyRate;
		fee.wedRate = fee.nightlyRate;
		fee.thurRate = fee.nightlyRate;
		fee.friRate = fee.nightlyRate;
		fee.satRate = fee.nightlyRate;
		fee.sunRate = fee.nightlyRate;

		fee.tranFee = "20";// for transaction fee schedule, not free

		url = TestProperty.getProperty(env + ".web.recgov.url");
		email = web.getNextEmail();
		pw = TestProperty.getProperty("web.login.pw");

		bd = new TicketInfo();
		bd.contractCode = "NRSO";
		bd.park = "MAMMOTH CAVE NATIONAL PARK TOURS";
		bd.parkId = "77817";
		bd.tourName = "Gothic Avenue Tour";
		bd.tourDate = DateFunctions.getDateAfterToday(12);
		bd.ticketNums = "3";
		bd.ticketType="Adult";
		bd.isUnifiedSearch=isUnifiedSearch();
		
//		label=MiscFunctions.getShoppingcartLabel().replace(":", "");
		label=WebConfiguration.getInstance().getPropertiesValue(Conf.Shoppingcart_prop, ShoppingcartProperty.OrderTotal).replace(":", "");
	}
	
	//verify the campimg fee is zero, but reservation fee is not.
	public void verifyFeesInCart() {
	  	String campFee=shoppingCart.getFeeByName(label);
	  	String transFee=shoppingCart.getFeeByName("Total");
	  	
	  	if(campFee==null || campFee.equalsIgnoreCase("0.00")){
	  	  	logger.info(" -- No camping use fee applied.");
	  	}else {
	  	  	throw new ErrorOnPageException("Use fee is not zero!");
	  	}
	  	
	  	if(transFee.equalsIgnoreCase("0.00")){
	  	  	throw new ErrorOnPageException("Transaction fee should not be zero!");
	  	}else {
	  	  	logger.info(" -- Transaction fee apply ok.");
	  	}
	}
}
