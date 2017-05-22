package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.licenseyear;

import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddLicYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: this test case is used to verify UI and cancel button when add privilege license year
 * @Preconditions:
 * @SPEC: Add Privilege License Year
 * @Task#: Auto-580
 * 
 * @author ssong
 * @Date  May 20, 2011
 */
public class AddPrivilegeLicenseYear_BusinessRule extends LicenseManagerTestCase{

	private LicenseYear ly = new LicenseYear();
	private LicMgrPrivilegeLicenseYearPage licenseYearPg = LicMgrPrivilegeLicenseYearPage.getInstance();
	private LicMgrPrivilegeAddLicYearWidget addYearPg = LicMgrPrivilegeAddLicYearWidget.getInstance();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		//goto privilege product license year sub-tab page
		lm.gotoPrivilegeSubTabPage(privilege.purchasingName, "License Year");
		
		//verify license year status,license year drop down and location class drop down list about add new license year
		this.checkBusinessRuleForAddLicenseYear();
		
		this.verifyCancelBtn();
		
		lm.logOutLicenseManager();
	}
	
	private void checkBusinessRuleForAddLicenseYear(){
		licenseYearPg.clickAddLicenseYear();
		addYearPg.waitLoading();
		//verify default license year status
		if(!addYearPg.getDefaultStatus().equals("Active")){
			throw new ErrorOnPageException("Default License Year Status Not Correct.");
		}
		//verify license year drop down list is not editable
		if(!addYearPg.checkStatusUnEditable()){
			throw new ErrorOnPageException("License Year Status Should Not be Edited.");
		}
		//verify license year drop down options correct
		checkLicenseYearDropDownList();
		//verify location class drop down options
		checkLocationClassDropDown();
	}
	
	/**
	 * This method used to verify location class drop down list option format is correct and sort by code ascending
	 */
	private void checkLocationClassDropDown(){
		List<String> locClass = addYearPg.getAllLocationClass();
		//verify default location class is All
		if(!locClass.get(0).equalsIgnoreCase("All")){
			throw new ErrorOnPageException("Default Location class not correct.");
		}
		//verify location class format and sorted correct
		for(int i=1;i<locClass.size();i++){
			if(!locClass.get(i).matches("\\d+ - .+")){
				throw new ErrorOnPageException("Location class option format not correct, should be 'code - desc'.");
			}
			String code = locClass.get(i).split("-")[0].trim();
			int mark = -1;
			if(mark>Integer.parseInt(code)){
				throw new ErrorOnPageException("Location class drop down options sorting not correct.");
			}
			mark = Integer.parseInt(code);		
		}
	}
	/**
	 * This method used to check license year drop down list options correct
	 */
	private void checkLicenseYearDropDownList(){
		List<String> years = addYearPg.getAllLicenseYear();
		if(years.size()!=11){
			throw new ErrorOnPageException("License Year Drop down list size is not 11.");
		}
		int currentYear= DateFunctions.getCurrentYear();
		for(int i=0;i<years.size();i++){
			if(currentYear!=Integer.parseInt(years.get(i))){
				throw new ErrorOnPageException("License Year Drop Down list options sorted not correct.");
			}
			currentYear++;
		}
	}
	private void verifyCancelBtn(){
		addYearPg.setLicenseYearInfo(ly);
		addYearPg.clickCancel();
		Object page = browser.waitExists(addYearPg,licenseYearPg);
		if(page == addYearPg){
			throw new ErrorOnPageException("Cancel Button Not work.");
		}
		String id = licenseYearPg.getLicenseYearId(ly.locationClass, ly.licYear);
		if(!id.equals("")){
			throw new ErrorOnPageException("Create License Year sucessful, Cancel button not worked.");
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.purchasingName = "NL1";
		ly.licYear = Calendar.getInstance().get(Calendar.YEAR)+"";
		ly.locationClass = "20 - MDWFP Lifetime";
		ly.sellFromDate = DateFunctions.getDateAfterToday(3);
		ly.sellToDate = DateFunctions.getDateAfterToday(5);
	}

}
