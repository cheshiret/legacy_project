package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:License Year status is changed to Inactive and no other info changed. Check points, the last update time and the changing status
 * @Preconditions:Add active license year in test case
 * @SPEC:Edit Privilege License Year
 * @Task#:AUTO-588
 * 
 * @author eliang
 * @Date  May 30, 2011
 */
public class ChangeActiveToInactiveWithoutOtherChange extends LicenseManagerTestCase{
	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editYearPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage("NL1", "License Year");
		String existId = lm.addLicenseYear(ly).id;
		this.inactiveLicenseYear(existId);
		
		//Goto inactive license year to check update information
		this.gotoLicenseYearDetail(existId);
		
		//Verify the update information
		this.verifyWarningMsg();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		ly.licYear = Integer.toString(DateFunctions.getCurrentYear());
		ly.locationClass = "05 - Dept of Marine Resources";
		ly.sellFromDate = DateFunctions.getDateAfterToday(2);
		ly.sellToDate = DateFunctions.getDateAfterToday(3);
		ly.sellFromTime = "08:00";
		ly.sellToTime = "11:59";
		ly.validFromDate = DateFunctions.getDateAfterToday(4);
		ly.validToDate = DateFunctions.getDateAfterToday(5);
		ly.validFromTime = "12:00";
		ly.validToTime = "11:59";
		ly.validFromAmPm = "AM";
		ly.validToAmPm = "PM";
	}

	
	public void inactiveLicenseYear(String id){
		licenseYearPg.clickLicenseYear(id);
		ajax.waitLoading();
		editYearPg.selectStatus("Inactive");
		editYearPg.clickOK();
		ajax.waitLoading();
	}
	
	public void gotoLicenseYearDetail(String licId){
		licenseYearPg.unSelectShowCurrentRecordsOnly();
		licenseYearPg.clickGo();
		ajax.waitLoading();
		
		licenseYearPg.clickLicenseYear(licId);
		ajax.waitLoading();
	}
	
	public void verifyWarningMsg(){
		if(editYearPg.getLastUpdateUser().equalsIgnoreCase(DataBaseFunctions.getLoginUserName(login.userName))&& editYearPg.getLastUpdateTime().matches(DateFunctions.getToday())&&editYearPg.getLastUpdateLocation().equalsIgnoreCase(login.location.split("/")[1])){
			throw new ErrorOnDataException("the update information is incorrect");
		}
	}
}
