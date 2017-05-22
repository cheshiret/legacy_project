package com.activenetwork.qa.awo.testcases.abstractcases;

import com.activenetwork.qa.awo.datacollection.legacy.BookingData;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.OrderDetails;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeCalculationFunctions;
import com.activenetwork.qa.awo.keywords.orms.ormsfee.FeeScheduleInformation;
import com.activenetwork.qa.awo.keywords.web.UWP;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @author QA
 */
public abstract class WebTestCase extends AWOTestCase {
	/**
	 * Script Name   : <b>RAWebTestCase</b>
	 * Generated     : <b>Jan 6, 2009 5:17:46 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 5.1  Build 2600 (S)
	 * 
	 * @since  2009/01/06
	 * @author QA
	 */
	protected LoginInfo login;

	protected UWP web;

	protected Payment pay;
	
	protected BookingData bd;
	
	protected BookingData newBd;
	
	protected OrderDetails od;
	
	protected FeeScheduleInformation feeInfo;
	
	protected FeeCalculationFunctions feeCal;
	
	protected String url = "";
	
	protected String email = "";
	
	protected String pw = "";
	
	protected TicketInfo ticket;
	
	public WebTestCase() {
		super();
		login = new LoginInfo();
		bd = new BookingData();
		newBd = new BookingData();
		od = new OrderDetails();
		web = UWP.getInstance();
		feeInfo=FeeScheduleInformation.getInstance();
		feeCal=FeeCalculationFunctions.getInstance();
		product = "web";
		pay = new Payment("Visa");
		ticket=new TicketInfo();
		
	}
	
	@Override
	public void execute() {
		this.sanityRA_MakeReservation();
	}
	protected boolean clickMapArea=false;//click on Map area from Nation Map page
	protected boolean clickStateName=false;//click on State name from Nation Map page
	protected boolean clickMapFlag=false;//click on Map from State Map page
	protected boolean clickNameLink=false;//click on park name, or See Details button from Map Search/Browse page
	protected boolean bookFromMap=false;//work flow starts from map search
	protected boolean isMapSearch=false;//search the park in Map Search section
	protected boolean signInMiddle=false;//sign in in the middle of work flow
	protected boolean isCancelAvail=true;//cancellation available from web
	protected boolean isSiteTransfer=false;//Change Date/Transfer to Another Site functional is available
	
	protected void sanityRA_MakeReservation() {
		if(isSiteTransfer){
			web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(newBd.lengthOfStay), false, bd.siteIDs);
		} else {
			web.checkAndReleaseInventory(schema, bd.arrivalDate, Integer.parseInt(bd.lengthOfStay), false, bd.siteID);
		}

		web.invokeURL(url);
		if(!signInMiddle) {//sign in before
			web.signIn(email, pw);
		}
		
		if(bookFromMap) {
			if(web.isUnifiedSearch()) {
			//james[20130909]Unified search doesn't have state map
			//james[20140225] turn on bookFromMap work flow
			web.gotoParkList(bd);//search park   
			web.gotoViewAsMapFromViewAsList();
			web.bookSiteFromMapToOrderDetails(bd, clickMapFlag, clickNameLink, isMapSearch);
			
			//if bookFromMap workflow was blocked, uncomment 2 line below to go through regular booking flow.
//			web.bookParkToSiteListPg(bd);//search park
//			web.bookSiteToOrderDetailPg(bd);//search site
			} else {
			//old map work flows
			web.gotoStateMapPg(bd, bd.state, clickMapArea, clickStateName);
			web.bookSiteFromMapToOrderDetails(bd, clickMapFlag, clickNameLink, isMapSearch);				
			}
			
			
		} else {
			web.bookParkToSiteListPg(bd);//search park
			web.bookSiteToOrderDetailPg(bd);//search site
		}
		
		if(signInMiddle) {//middle sign in
			web.signIn(email, pw);
		}
		
		web.fillInOrderDetails(od,bd.contractCode);// fill in order details and go to cart
		String resID = web.checkOutCart(pay);
		web.checkReservationExists(schema, resID);//check reservation exist

		web.gotoMyReservationsAccount();
		web.gotoResDetailFromAccount(resID, bd.contractCode, "Confirmed");
		
		if(bd.isUpdateAble){
			web.updateReservationDetails();
			
			//If have "Change Reservation" functional, check if it has "Site Transfer" functional
			if(isSiteTransfer){
				newBd.siteType = DataBaseFunctions.getSiteType(newBd.siteID, schema);
				web.changeDateOrTransferSite(newBd);
			}else{
				web.verifyChangeDateOrSiteTransferLinkNotAvailable();
				
				web.gotoMyReservationsAccount();
				web.gotoResDetailFromAccount(resID, bd.contractCode, "Confirmed");
			}
			
		} else {
			web.verifyChangeLinkNotAvailable();
		}

		if(isCancelAvail) {
			web.cancelReservation(pay);
		} else {
			web.verifyCancelLinkNotAvailable();
		}
		web.signOut();
	}
	
//	public void voidPrmitOrderFromPermitManager(String parkName,String station,String... orderNums){
//		LoginInfo loginpm=new LoginInfo();
//		loginpm.url = AwoUtil.getOrmsURL(env);
//		loginpm.userName = TestProperty.getProperty("orms.pm.user");
//		loginpm.password = TestProperty.getProperty("orms.pm.pw");
//		loginpm.contract = "NRRS Contract";
//		loginpm.location = "Permit Manager Admin/"+parkName;
//		loginpm.station = station;
//		PermitManager pm=PermitManager.getInstance();
//		//Login PM to void reservation
//		pm.loginPermitManager(loginpm);
//		pm.voidPermitOrders("", "", orderNums);
//		pm.logoutPermitManager();
//	}
//	
	/**
	 * Void permit lottery order from permit manager
	 * @param schema
	 * @param parkName
	 * @param station
	 * @param lotteryNumber
	 */
//	public void voidPrmitLotteryOrderFromPermitManager(String schema, String parkName,String station,String lotteryNumber) {
//		LoginInfo loginpm=new LoginInfo();
//		loginpm.url = AwoUtil.getOrmsURL(env);
//		loginpm.userName = TestProperty.getProperty("orms.pm.user");
//		loginpm.password = TestProperty.getProperty("orms.pm.pw");
//		loginpm.contract = schema.substring(schema.lastIndexOf("_")+1)+" Contract";
//		loginpm.location = "Permit Manager Admin/"+parkName;
//		loginpm.station = station;
//		//PermitManager pm = PermitManager.getInstance();
//		
//		logger.info("Void permit lottery order from Permit Manager.");
//		pm.loginPermitManager(loginpm);
//		pm.gotoLotteryApplicationDetailPage(lotteryNumber);
//		pm.voidPermitToCart();
//		pm.processOrderCart(pay);
//		pm.logoutPermitManager();
//	}
	
	/**
	 * 
	 * @param resNum
	 * @param contract
	 */
//	protected void voidTicketOrderFromCM(String resNum,String contractFullName) {
//		this.voidTicketOrderFromCM(contractFullName, new String[]{resNum});
//		
//	}
	
	/**
	 * 
	 * @param resNum
	 * @param contract
	 */
//	protected void voidTicketOrderFromCM(String contractFullName ,String... resNums) {
//		CallManager cm=CallManager.getInstance();
//		LoginInfo login=new LoginInfo();
//		login.url = AwoUtil.getOrmsURL(env);
//		login.userName = TestProperty.getProperty("orms.cm.user");
//		login.password = TestProperty.getProperty("orms.cm.pw");
//		login.contract = contractFullName;
//		
//		cm.loginCallMgr(login);
//		for(String resNum: resNums){
//			cm.voidTicketToCart(resNum);
//			cm.processOrderCart(pay);
//		}
//		cm.logoutCallMgr();
//		
//	}
}
