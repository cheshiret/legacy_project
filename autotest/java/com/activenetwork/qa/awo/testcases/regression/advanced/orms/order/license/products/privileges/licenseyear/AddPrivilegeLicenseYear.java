package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify warning message when add license year and create license year successful
 * @Preconditions: there must have a privilege exists
 * @SPEC: Add Privilege License Year
 * @Task#: Auto-580
 * 
 * @author ssong
 * @Date  May 26, 2011
 */
public class AddPrivilegeLicenseYear extends LicenseManagerTestCase{

	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	private LicMgrPrivilegeEditLicenseYearWidget editPg = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
	private String addedUser,addedLocation;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilege.purchasingName, "License Year");
		
		// clean up
		lm.deactivateAllPrivilegeLicenseYear();
		
		//add a license year and verify new license year added correct
		gotoAddLicenseYearPg();
		addYearPg.setLicenseYearInfo(ly);
		this.verifyAddSucessful();
		
		//clean up,inactive the license year you added
		inactiveLicenseYear(ly.id);

		lm.logOutLicenseManager();
	}

	private void gotoAddLicenseYearPg(){
		licenseYearPg.clickAddLicenseYear();
		addYearPg.waitLoading();
	}
	
	private void verifyAddSucessful(){
		addYearPg.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(addYearPg,licenseYearPg);
		if(page == addYearPg){
			throw new ErrorOnPageException("Expect Page is License Year List Page.");
		}
		LicenseYear lyear = licenseYearPg.getLicenseYearInfo(ly.locationClass, ly.licYear);
		if(DateFunctions.compareDates(ly.sellFromDate, lyear.sellFromDate)!=0){
			throw new ErrorOnPageException("New added license year sell from date not correct.");
		}
		if(DateFunctions.compareDates(ly.sellToDate, lyear.sellToDate)!=0){
			throw new ErrorOnPageException("New added license year sell to date not correct.");
		}
		if(DateFunctions.compareDates(ly.validFromDate, lyear.validFromDate)!=0){
			throw new ErrorOnPageException("New added license year valid from date not correct.");
		}
		if(DateFunctions.compareDates(ly.validToDate, lyear.validToDate)!=0){
			throw new ErrorOnPageException("New added license year valid to date not correct.");
		}
		ly.id = lyear.id;
		lm.gotoPrivilegeLicenseYearDetailPg(lyear.id);
		if(!editPg.getPrivilegeLicenseYearStatus().equals("Active")){
			throw new ErrorOnPageException("Privilige License Year Status Not Correct.");
		}
		if(!editPg.getPrivilegeLocationClass().equals(ly.locationClass)){
			throw new ErrorOnPageException("Privilige License Year Location Class Not Correct.");
		}
		if(!editPg.getLicenseYear().equals(ly.licYear)){
			throw new ErrorOnPageException("Privilige License Year Not Correct.");
		}
		if(DateFunctions.compareDates(ly.sellFromDate, editPg.getSellFromDate())!=0){
			throw new ErrorOnPageException("New added license year sell from date not correct.");
		}
		if(!editPg.getSellFromTime().equals(ly.sellFromTime)){
			throw new ErrorOnPageException("Privilige License Year sell from time Not Correct.");
		}
		if(!editPg.getSellFromAmPm().equals(ly.sellFromAmPm)){
			throw new ErrorOnPageException("Privilige License Year Sell From AM/PM Not Correct.");
		}
		if(DateFunctions.compareDates(editPg.getSellToDate(),ly.sellToDate)!=0){
			throw new ErrorOnPageException("Privilige License Year Sell To Date Not Correct.");
		}
		if(!editPg.getSellToTime().equals(ly.sellToTime)){
			throw new ErrorOnPageException("Privilige License Year sell to time Not Correct.");
		}
		if(!editPg.getSellToAmPm().equals(ly.sellToAmPm)){
			throw new ErrorOnPageException("Privilige License Year Sell To AM/PM Not Correct.");
		}
		if(DateFunctions.compareDates(ly.validFromDate, editPg.getValidFromDate())!=0){
			throw new ErrorOnPageException("New added license year valid from date not correct.");
		}
		if(!editPg.getValidFromTime().equals(ly.validFromTime)){
			throw new ErrorOnPageException("Privilige License Year valid from time Not Correct.");
		}
		if(!editPg.getValidFromAmPm().equals(ly.validFromAmPm)){
			throw new ErrorOnPageException("Privilige License Year Valid From AM/PM Not Correct.");
		}
		if(DateFunctions.compareDates(editPg.getValidToDate(),ly.validToDate)!=0){
			throw new ErrorOnPageException("Privilige License Year Valid To Date Not Correct.");
		}
		if(!editPg.getValidToTime().equals(ly.validToTime)){
			throw new ErrorOnPageException("Privilige License Year valid to time Not Correct.");
		}
		if(!editPg.getValidToAmPm().equals(ly.validToAmPm)){
			throw new ErrorOnPageException("Privilige License Year Valid To AM/PM Not Correct.");
		}
		if(!editPg.getCreateUser().trim().replaceAll(", ", ",").equals(addedUser.trim().replaceAll(", ", ","))){//vivian[20121212]
			throw new ErrorOnPageException("Create User "+editPg.getCreateUser()+" Not Correct.");
		}
		if(!editPg.getCreateLocation().equals(addedLocation)){
			throw new ErrorOnPageException("Create location "+editPg.getCreateLocation()+" Not Correct.");
		}
		if(!editPg.getCreateTime().matches(DateFunctions.getToday("EEE MMM dd yyyy")+".*")){
			throw new ErrorOnPageException("Privilige License Year Create Date Not Correct.");
		}
		editPg.clickOK();
		licenseYearPg.waitLoading();
	}
	
	private void inactiveLicenseYear(String yearId){
		lm.gotoPrivilegeLicenseYearDetailPg(yearId);
		editPg.selectStatus("Inactive");
		editPg.clickOK();
		ajax.waitLoading();
		licenseYearPg.waitLoading();
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
		
		addedUser = DataBaseFunctions.getLoginUserName(login.userName);
		addedLocation = login.location.split("/")[1];
		
	}

}
