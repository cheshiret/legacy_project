package com.activenetwork.qa.awo.supportscripts.support.adminmgr;

import com.activenetwork.qa.awo.datacollection.legacy.MinimumStayRuleData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.AdminManager;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrMinStayRuleDetailPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrRuleListPage;
import com.activenetwork.qa.awo.pages.orms.adminManager.AdmMgrSelectLocationPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class AddMinimumStayRule extends SupportCase {

	/**
	 * Script Name   : AddUseFee
	 * Generated     : Oct 21, 2004 8:02:46 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/21
	 */

	public MinimumStayRuleData minStayData = new MinimumStayRuleData();
	public LoginInfo login = new LoginInfo();;
	public AdmMgrHomePage amHmPg = AdmMgrHomePage.getInstance();
	public AdminManager admgr=AdminManager.getInstance();
	public AdmMgrMinStayRuleDetailPage amMinStayRuleDetailPg = AdmMgrMinStayRuleDetailPage.getInstance();
	public AdmMgrRuleListPage admRuleLstPg=AdmMgrRuleListPage.getInstance();
	public AdmMgrSelectLocationPage admLocPg=AdmMgrSelectLocationPage.getInstance();
	
	public String ruleConditionID = "";
	public String ruleType = "";
	public boolean loggedin = false;
	public String contract = "";

	public void wrapParameters(Object[] param) {
		
		startpoint = 0; //the start point in the datapool
		endpoint = 99; // the end point in the datapool
	    //specify the rule to be created, which is also the datapool name
	    ruleType="Minimum Stay";
	    dataSource = casePath + "/RuleDataPool/" + ruleType.replaceAll("[/]"," ").trim();
		
		String env = TestProperty.getProperty("target_env");	
	  	login.url = AwoUtil.getOrmsURL(env);
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
        //login.location = "Administrator/South Carolina State Park Service";
		
		 //log informaiton
		logMsg = new String[14];
		logMsg[0] = "cursor";
		logMsg[1] = "disName";
		logMsg[2] = "disSchId";
		logMsg[3] = "disFeeTypes";
		logMsg[4] = "disBehaviors";
		logMsg[5] = "location";
		logMsg[6] = "disSchFeeType";
		logMsg[7] = "disScheffectiveDate";
		logMsg[8] = "disSchstartDate";
		logMsg[9] = "discountSchendDate";
		logMsg[10] = "disSchrate";
		
		logMsg[11]="DisActiveOrInactive";
		logMsg[12]="DisSchActiveOrInactive";
	    logMsg[13]="result";
	}
	
	public void execute() {
		 //Login admin manager
	  	  if(!login.contractAbbrev.equalsIgnoreCase(contract) || !loggedin) {
	  	  	  login.contract=contract.toUpperCase()+" Contract";
	  	  	  login.contractAbbrev=contract;
	  	  	  if(loggedin) {
	  	  	    admgr.logoutAdminMgr();
	  	    	}
	  	      admgr.loginAdminMgr(login);
	  	  	  loggedin=true;
	  	  	  
	  	      //Select contract rule
	  	  	  amHmPg.selectContractRules();
	  	      admRuleLstPg.waitLoading();
	  	      admRuleLstPg.enterRuleDetail(ruleType);
	  	      admLocPg.waitLoading();
	  	      
	  	      //Select location
	  	      admLocPg.searchLocation(minStayData.locationCategory,minStayData.location);
	  	      admLocPg.clickSelectButton();
			  amMinStayRuleDetailPg.waitLoading();
	  	  }
	  	  //add a new rule
		if (amMinStayRuleDetailPg.exists()) {
			ruleConditionID = amMinStayRuleDetailPg.addNewRule(minStayData);
			amMinStayRuleDetailPg.waitLoading();

			if (!amMinStayRuleDetailPg.checkLocation(minStayData.location)) {
				amMinStayRuleDetailPg.clickFindRules();
			}
		}
		
		if(!amMinStayRuleDetailPg.isErrorMsgExist()){
			 logMsg[5] = "Successful";
		}
	}

	public void getNextData() {
		contract = dpIter.dpString("contract_abbr");
		minStayData.location = dpIter.dpString("LocationName");
		minStayData.locationCategory = dpIter.dpString("LocationCategory");
		minStayData.status = dpIter.dpString("Active");
		minStayData.productCategory = dpIter.dpString("ProductCategory");
		minStayData.ticketCategory = dpIter.dpString("TicketCategory");
		minStayData.productGroup = dpIter.dpString("ProductGroup");
		minStayData.loop = dpIter.dpString("AreaLoop");
		minStayData.product = dpIter.dpString("Product");
		minStayData.salesChannel = dpIter.dpString("SalesChannel");
		minStayData.customerType = dpIter.dpString("CustomerType");
		minStayData.season = dpIter.dpString("SeasonType");
		minStayData.customerPassType = dpIter.dpString("CustomerPassType");
		minStayData.outOfState = dpIter.dpString("OutOfState");
		minStayData.paymentType = dpIter.dpString("PaymentType");
		minStayData.customerMember = dpIter.dpString("CustomerMember");
		minStayData.associatedParty = dpIter.dpString("AssociatedParty");
		minStayData.transactionOccurrence = dpIter.dpString("TransactionOccurrence");
		minStayData.comments = dpIter.dpString("Comments");
		minStayData.startDate = dpIter.dpString("StartDate");
		minStayData.endDate = dpIter.dpString("EndDate");
		//minStayData.effectiveDate = dpIter.dpString("EffectiveDate");
		minStayData.effectiveDate = DateFunctions.getToday();
		minStayData.numberOfMinimumStay = dpIter.dpString("Minimumstay");
		minStayData.msMonday = dpIter.dpString("MinimumStayMon");
		minStayData.msTuesday = dpIter.dpString("MinimumStayTue");
		minStayData.msWednesday = dpIter.dpString("MinimumStayWed");
		minStayData.msThursday = dpIter.dpString("MinimumStayThu");
		minStayData.msFriday = dpIter.dpString("MinimumStayFri");
		minStayData.msSaturday = dpIter.dpString("MinimumStaySat");
		minStayData.msSunday = dpIter.dpString("MinimumStaySun");
		minStayData.minimumStayHoliday = dpIter.dpString("MinimumStayHoliday");
		minStayData.unit = dpIter.dpString("Unit");
		minStayData.mutiplesOnly = dpIter.dpString("MultiplesOnly");
		minStayData.includeStartDay = dpIter.dpString("MinimumStayRequiredWhenStayIncludesStartDay");
		minStayData.combineOverlappingSeason = dpIter.dpString("MinimumStayCombineOverlappingSeasons");
		
		 //log informaiton
		  logMsg = new String[6];
		  logMsg[0] = "index";
		  logMsg[1] = minStayData.location;
		  logMsg[2] = minStayData.locationCategory;
		  logMsg[3] = contract;
		  logMsg[4] = ruleType;
		  logMsg[5] = "Fail";
		
	}

}
