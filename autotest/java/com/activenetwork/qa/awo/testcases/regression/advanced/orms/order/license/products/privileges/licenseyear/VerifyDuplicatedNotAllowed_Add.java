/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify system not allow add duplicated license year
 * @Preconditions: there must have a privilege exists
 * @SPEC: Add Privilege License Year
 * @Task#: Auto-580
 * 
 * @author ssong
 * @Date  Sep 1, 2011
 */
public class VerifyDuplicatedNotAllowed_Add extends LicenseManagerTestCase{

	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilege.purchasingName, "License Year");
	
		LicenseYear ly1 = lm.addLicenseYear(ly);

		gotoAddLicenseYearPg();
		addYearPg.setLicenseYearInfo(ly);
		this.verifyErrorMsg("Another active Privilege License Year record "+ly1.id+" already exists for the same Privilege Product with the same License Year and Location Class. Duplicate active records are not allowed.");
		
		addYearPg.clickCancel();
		licenseYearPg.waitLoading();
		
		//clean up,inactive the license year you added
		inactiveLicenseYear(ly1.id);
		
		lm.logOutLicenseManager();
	}
	
	private void gotoAddLicenseYearPg(){
		licenseYearPg.clickAddLicenseYear();
		addYearPg.waitLoading();
	}
	
	private void verifyErrorMsg(String expectMsg){
		addYearPg.clickOK();
		Object page = browser.waitExists(addYearPg,licenseYearPg);
		if(page == licenseYearPg){
			throw new ErrorOnPageException("Expect Page is Widget Page.");
		}
		String actualMsg = addYearPg.getErrorMsg();
		if(actualMsg == null||!actualMsg.contains(expectMsg)){
			throw new ErrorOnPageException("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}
	}
	
	private void inactiveLicenseYear(String yearId){
		lm.gotoPrivilegeLicenseYearDetailPg(yearId);
		editPg.selectStatus("Inactive");
		editPg.clickOK();
		licenseYearPg.waitLoading();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.purchasingName = "NL1";
		ly.licYear = (DateFunctions.getCurrentYear()+4)+"";
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
	}
}
