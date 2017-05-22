/**
 * 
 */
package com.activenetwork.qa.awo.testcases.temp.orms;

import java.util.Date;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeBusinessRule;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddBusinessRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeBusinessRulePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditRuleWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegesListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @ScriptName LM_PrivilegeAgeRules.java
 * @Date:Mar 3, 2011
 * @Description:
 * @author asun
 */
public class LM_PrivilegeAgeRules extends LicenseManagerTestCase {

	private PrivilegeInfo privilege=new PrivilegeInfo();
	private PrivilegeBusinessRule prule1=new PrivilegeBusinessRule();
	private PrivilegeBusinessRule prule2=new PrivilegeBusinessRule();
	private String today=DateFunctions.getToday();
	private String tomorrow=DateFunctions.getDateAfterToday(1);
	private String yesterday=DateFunctions.getDateAfterToday(-1);
	private String adminLocation=null;
	private String sellLocation=null;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//Set up the 1st privilege for test
		this.setUpPrivilegeForTest(privilege);
		lm.switchLocationInHomePage(sellLocation);
		//verify Customer must be AT LEAST 18 years old age on the date of the privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(tomorrow, -12*18);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today, -12*18);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		//verify Customer must be AT MOST 60 years old age on the date of privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today, -12*60);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(yesterday, -12*60);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		
		//Set up the 2nd Privilege which start date is in the future (one time setup to 6 months later) for test
		privilege.licYear.validFromDate=DateFunctions.getDateAfterGivenMonth(today, 6);
		privilege.rules[0].name="Customer must be AT LEAST parameter age on the valid date of the privilege purchased";
		privilege.rules[1].name="Customer must be AT MOST parameter age on the valid date of the privilege purchased";
		lm.switchLocationInHomePage(adminLocation);
		this.setUpPrivilegeForTest(privilege);
		lm.switchLocationInHomePage(sellLocation);
		//verify Customer must be AT LEAST 18 years old age on the date of the privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today,-18*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(tomorrow,-18*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		//verify Customer must be AT MOST 60 years old age on the date of privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today,-60*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(yesterday,-60*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		
		//Set up the 3rd Privilege which start date is in the future (one time setup to 6 months later) for test
		privilege.licYear.validFromDate=today;
		privilege.rules[0].name="Customer must be AT LEAST parameter age on the parameter date";
		privilege.rules[0].paramValue+=",Date:"+DateFunctions.getDateAfterGivenMonth(today, 6);
		privilege.rules[1].name="Customer must be AT MOST parameter age on the parameter date";
		privilege.rules[1].paramValue+=",Date:"+DateFunctions.getDateAfterGivenMonth(today, 6);
		lm.switchLocationInHomePage(adminLocation);
		this.setUpPrivilegeForTest(privilege);
		lm.switchLocationInHomePage(sellLocation);
		//verify Customer must be AT LEAST 18 years old age on the date of the privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today,-18*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(tomorrow,-18*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		//verify Customer must be AT MOST 60 years old age on the date of privilege purchase
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(today,-60*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, true);
		cust.dateOfBirth=DateFunctions.getDateAfterGivenMonth(yesterday,-60*12+6);
		this.verifyPrivilegeAgeRule(cust, privilege.purchasingName, false);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "LM - Chart of Accounts/WAL-MART";
		
		adminLocation="LM - Chart of Accounts-WAL-MART";
		sellLocation="LM - Facility-WAL-MART";
		
		privilege.code="jan";
		privilege.name="SanityTest";
		privilege.purchasingName="jan-SanityTest";
		privilege.status="Active";
		privilege.invType="None";
		
		//Bussiness Rule Infomation
		prule1.ruleCategory="Customer Demographic";
	    prule1.name="Customer must be AT LEAST parameter age on the date of privilege purchase";
		prule1.paramValue="Age:18";
		prule1.status="Active";
		prule1.effectiveDate=DateFunctions.getDateAfterToday(-1);
		prule2.ruleCategory="Customer Demographic";
		prule2.name="Customer must be AT MOST parameter age on the date of privilege purchase";
		prule2.paramValue="Age:60";
		prule2.status="Active";
		prule2.effectiveDate=DateFunctions.getDateAfterToday(-1);
		privilege.rules=new PrivilegeBusinessRule[]{prule1,prule2};
		
		LicenseYear ly=new LicenseYear();
		ly.licYear=DateFunctions.getToday().split("/")[2];
		ly.status="Active";
		ly.locationClass="All";
		ly.sellFromDate=DateFunctions.getDateAfterToday(-1);
		ly.sellFromTime="00:00AM";
		ly.sellToDate=DateFunctions.getDateAfterToday(300);
		ly.sellToTime="00:00AM";
		ly.validFromDate=DateFunctions.getToday();
		ly.validFromTime="00:00AM";
		ly.validToDate=DateFunctions.getDateAfterToday(300);
		ly.validToTime="00:00AM";
		privilege.licYear=ly;
		
		cust.residencyStatus="";
		cust.customerClass="Individual";
		cust.identifier.identifierType="NON-US DL Number";
		cust.identifier.identifierNum="Auto123456";
		cust.dateOfBirth="";
		cust.fName="";
		cust.lName="";
		
	}
	/**
	 * verify privilege bussiness age rule 
	 * @param cust
	 * @param firstDate
	 * @param secondDate
	 * @Return void
	 * @Throws
	 */
	public void verifyPrivilegeAgeRule(Customer cust,String privilegeName,boolean isExist){		 
		LicMgrCustomerDetailsPage profilePg = LicMgrCustomerDetailsPage.getInstance();
		LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
		LicMgrOrigPrivSaleAddItemPage page=LicMgrOrigPrivSaleAddItemPage.getInstance();
		boolean flag=false;
		
		logger.info("Verify Privilege bussiness rule.....");
		lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
		profilePg.setDateOfBirth(cust.dateOfBirth);
		profilePg.clickApply();
		ajax.waitLoading();
		lm.gotoAddItemPgFromCustDetailPg(cust.residencyStatus);
		flag=page.isThisPrivilegeExist(privilegeName);
		if(flag!=isExist){
			throw new ErrorOnPageException("The privilege name="+privilegeName+" should "+(isExist?" ":"not "+"existing"));
		}
		page.clickHome();
		homePg.waitLoading();
		logger.info("verify sucessfully.....");
	}
	
	/**
	 * set up privilege info for verification
	 * @param rule
	 * @Return void
	 * @Throws
	 */
	public void setUpPrivilegeForTest(PrivilegeInfo privilege){
		LicMgrPrivilegesListPage privPage=LicMgrPrivilegesListPage.getInstance();
		LicMgrPrivilegeProductDetailsPage editPage=LicMgrPrivilegeProductDetailsPage.getInstance();
		
		logger.info("Set up privilege info for test........");
		boolean isPrivilegeExist=lm.isThisPriviledgeExist(privilege.code, "Active","Inactive");	
		if(!isPrivilegeExist){
			throw new ObjectNotFoundException("The privilege that code is"+privilege.code+" is not existing.");
		}
		privPage.clickPrivilegeCode(privilege.code);
		editPage.waitLoading();
		
		if(!editPage.getStatus().equalsIgnoreCase(privilege.status)){
			editPage.selectPrivilegeStatus(privilege.status);
			ajax.waitLoading();
		}
		editPage.setName(privilege.name);
		editPage.selectInventoryType(privilege.invType);
		editPage.clickApply();
		ajax.waitLoading();
		this.setUpLicenseYear(privilege.licYear);
	    this.setUpBussinessRule(privilege.rules);
	    editPage.clickOk();
	    privPage.waitLoading();
	}
	
	/**
	 * set up bussiness rule to verify bussiness rule,Work flow start from EditPrivilegeDetails 
	 * Page,End in PrivilegeLicenseYear Page.
	 * @param rule
	 * @Return void
	 * @Throws
	 */
	public void setUpBussinessRule(PrivilegeBusinessRule... rules){
		LicMgrPrivilegeProductDetailsPage editPage=LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPrivilegeBusinessRulePage bRulePage=LicMgrPrivilegeBusinessRulePage.getInstance();
		LicMgrPrivilegeEditRuleWidget editRulePage=LicMgrPrivilegeEditRuleWidget.getInstance();
		LicMgrPrivilegeAddBusinessRuleWidget addRulePage=LicMgrPrivilegeAddBusinessRuleWidget.getInstance();
		
		logger.info("set up for bussiness rule..... ");
		if(!bRulePage.exists()){
			editPage.clickBusinessRuleTab();
			ajax.waitLoading();
			bRulePage.waitLoading();
		}
		bRulePage.selectActiveCheckBox();
		bRulePage.clickGo();
		ajax.waitLoading();
		List<String> ruleIds=bRulePage.getRuleIdsByStatus(true);
		for(String id:ruleIds){
			lm.changeBussinessRuleStatus(id, false);
		}
		bRulePage.selectActiveCheckBox();
		bRulePage.selectInactiveCheckBox();
		bRulePage.clickGo();
		ajax.waitLoading();
		
		for(PrivilegeBusinessRule rule:rules){
			boolean isExisting = bRulePage.isRuleExisting(rule.name);
			if(isExisting){
				PrivilegeBusinessRule ruleOnpage=bRulePage.getRuleInfoFromBusinessRulePg(rule,0);
				Date effDate=DateFunctions.parseDateString(ruleOnpage.effectiveDate, "MM/dd/yyyy HH:mm:SS");
			    Date today=new Date();
			    if(today.compareTo(effDate)<0 || !(ruleOnpage.paramValue.equals(rule.paramValue))
			    		||!(ruleOnpage.status.equals(rule.status))){
			    	rule.id=bRulePage.getRuleInfoFromBusinessRulePg(rule,0).id;
			    	bRulePage.clickRuleId(rule.id);
			    	editRulePage.waitLoading();
			    	editRulePage.setRuleInfo(rule);
			    	editRulePage.clickOK();
			    	ajax.waitLoading();
			    }
			}else{
				bRulePage.clickAddBusinessRule();
				addRulePage.waitLoading();
				addRulePage.setRuleInfo(rule);
				addRulePage.clickOK();
				ajax.waitLoading();
			}
		}
		
	}
	
	/**
	 * set up for License Year,Work flow start from EditPrivilegeDetails 
	 * Page,End in PrivilegeLicenseYear Page.
	 * @Return void
	 * @Throws
	 */
	public void setUpLicenseYear(LicenseYear licYear){
		LicMgrPrivilegeProductDetailsPage editPage=LicMgrPrivilegeProductDetailsPage.getInstance();
		LicMgrPrivilegeLicenseYearPage licYearPage=LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editYearPage=LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		LicMgrPrivilegeAddLicYearWidget addLicYear=LicMgrPrivilegeAddLicYearWidget.getInstance();
		
		logger.info("set up for License Year....");
		if(!licYearPage.exists()){
			editPage.clickLicenseYearTab();
			ajax.waitLoading();
			licYearPage.waitLoading();
		}
		licYearPage.unSelectShowCurrentRecordsOnly();
		ajax.waitLoading();
		licYearPage.selectStatus("Active");
		licYearPage.clickGo();
		ajax.waitLoading();
		boolean licYearExist=licYearPage.isThisYearExist(licYear.licYear);	
		if(licYearExist){
		   LicenseYear ly=licYearPage.getLicenseYearInfo(licYear.locationClass,licYear.licYear);
	       licYearPage.clickLicenseYear(ly.id);
	       editYearPage.waitLoading();
	       editYearPage.setLicenseYearInfo(licYear);
	       editYearPage.clickOK();
		}else{
			licYearPage.clickAddLicenseYear();
			addLicYear.waitLoading();
			addLicYear.setLicenseYearInfo(licYear);
			addLicYear.clickOK();
		}
		 ajax.waitLoading();
	}

}
