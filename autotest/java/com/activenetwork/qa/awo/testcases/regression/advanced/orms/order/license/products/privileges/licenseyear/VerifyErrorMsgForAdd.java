/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify error message when add license year
 * @Preconditions: there must have a privilege exists
 * @SPEC: Add Privilege License Year
 * @Task#: Auto-580
 * 
 * @author ssong
 * @Date  May 26, 2011
 */
public class VerifyErrorMsgForAdd extends LicenseManagerTestCase{

	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private boolean pass = true;
	private String errorMsg1,errorMsg2,errorMsg3,errorMsg4,errorMsg5,errorMsg6;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilege.purchasingName, "License Year");
	
		//goto add license year page and verify all the error message
		gotoAddLicenseYearPg();
		ly.sellFromDate = "";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg1);
		
		ly.sellFromDate = DateFunctions.getToday();
		ly.sellToDate = "";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg2);
		
		ly.sellFromTime = "";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg3);
		
		ly.sellFromTime = "11:00";
		ly.sellToDate = DateFunctions.getDateAfterToday(-1);
		ly.sellToTime = "";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg4);
		
		ly.sellToTime = "10:00";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg5);
		
		ly.sellFromAmPm = "AM";
		ly.sellToAmPm = "AM";
		ly.sellToDate = DateFunctions.getToday();
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg5);
		
		ly.sellToAmPm = "PM";
		ly.validFromDate = DateFunctions.getDateAfterToday(1);
		ly.validToDate = DateFunctions.getDateAfterToday(-1);
		ly.validFromTime = "12:00";
		ly.validToTime = "12:00";
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg(errorMsg6);
		
		addYearPg.waitLoading();
		addYearPg.clickCancel();
		
		ajax.waitLoading();
		licenseYearPg.waitLoading();
		
		if(!pass){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		
		lm.logOutLicenseManager();
	}

	private void gotoAddLicenseYearPg(){
		licenseYearPg.clickAddLicenseYear();
		addYearPg.waitLoading();
	}
	
	private void verifyErrorMsg(String expectMsg){
		addYearPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(addYearPg,licenseYearPg);
		if(page == licenseYearPg){
			throw new ErrorOnPageException("Expect Page is Widget Page.");
		}
		String actualMsg = addYearPg.getErrorMsg();
		if(actualMsg == null||!actualMsg.contains(expectMsg)){
			pass &= false;
			logger.error("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.purchasingName = "NL1";
		ly.licYear = (DateFunctions.getCurrentYear()+5)+"";
		ly.locationClass = "20 - MDWFP Lifetime";
		ly.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly.sellToDate = DateFunctions.getDateAfterToday(5);
		ly.sellFromTime = "11:00";
		ly.sellToTime = "12:00";
		ly.sellFromAmPm = "AM";
		ly.sellToAmPm = "PM";
		ly.validFromDate = DateFunctions.getDateAfterToday(5);
		ly.validToDate = DateFunctions.getDateAfterToday(8);
		ly.validFromTime = "11:00";
		ly.validToTime = "12:00";
		ly.validFromAmPm = "AM";
		ly.validToAmPm = "PM";
		
		errorMsg1 = "The Sell From Date & Time is required. Please enter the Sell From Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
		errorMsg2 = "The Sell To Date & Time is required. Please enter the Sell To Date & Time using the format Ddd Mmm dd yyyy hh:mm AM/PM in the field provided.";
//		errorMsg1="Sell From Date/Time : Date value required";
//		errorMsg2="Sell To Date/Time : Date value required";
		errorMsg3 = "Sell From Date/Time : Invalid time format, please enter time in format: HH:MM";
		errorMsg4 = "Sell To Date/Time : Invalid time format, please enter time in format: HH:MM";
		errorMsg5 = "The Sell From Date & Time must not be later than the Sell To Date & Time. Please change your entries.";
		errorMsg6 = "The Valid From Date & Time must not be later than the Valid To Date & Time. Please change your entries.";
	}

}
