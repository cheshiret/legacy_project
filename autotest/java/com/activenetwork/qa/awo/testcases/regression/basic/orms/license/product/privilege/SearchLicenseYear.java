package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify searching license years function works correctly or not
 * @Preconditions: Need an existing privilege which code is QQQ
 * @SPEC: <<View Privilege License Years.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Jan 9, 2012
 */
public class SearchLicenseYear extends LicenseManagerTestCase {
	private LicenseYear licenseYear = new LicenseYear();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(licenseYear.productCode);
		lm.gotoPrivilegeLicenseYearPage();
		
		//add a new privilege License Year record
		licenseYear.id = lm.addLicenseYear(licenseYear).id;
		
		//search license year record by all search criteria
		lm.searchPrivilegeLicenseYearByInfo(licenseYear);
		this.verifyLicenseYearExists(licenseYear);
		
		//search license year record by status: Active
		lm.searchPrivilegeLicenseYearByInfo(licenseYear.status, "", "");
		this.verifyLicenseYearExists(licenseYear);
		
		//search license year record by license year: <current year>
		lm.searchPrivilegeLicenseYearByInfo("", licenseYear.licYear, "");
		this.verifyLicenseYearExists(licenseYear);
		
		//search license year record by location class: "03 - Lakes Offices"
		lm.searchPrivilegeLicenseYearByInfo("", "", licenseYear.locationClass);
		this.verifyLicenseYearExists(licenseYear);
		
		//clean up
		lm.deactivatePrivilegeLicenseYear(licenseYear.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		licenseYear.productCode = "QQQ";
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "03 - Lakes Offices";
//		licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear() + 10);//TODO DEFECT-32648
		licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear() + 9);
		licenseYear.sellFromDate = DateFunctions.getToday();
		licenseYear.sellFromTime = "12:10";
		licenseYear.sellFromAmPm = "AM";
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(1);
		licenseYear.sellToTime = "11:50";
		licenseYear.sellToAmPm = "PM";
		licenseYear.validFromDate = licenseYear.sellFromDate;
		licenseYear.validFromTime = licenseYear.sellFromTime;
		licenseYear.validFromAmPm = licenseYear.sellFromAmPm;
		licenseYear.validToDate = licenseYear.sellToDate;
		licenseYear.validToTime = licenseYear.sellToTime;
		licenseYear.validToAmPm = licenseYear.sellToAmPm;
		licenseYear.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		licenseYear.createLocation = login.location.split("/")[1];
		licenseYear.createTime = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
	}
	
	private void verifyLicenseYearExists(LicenseYear licenseYear) {
		LicMgrPrivilegeLicenseYearPage licenseYearPage = LicMgrPrivilegeLicenseYearPage.getInstance();
		
		logger.info("Verify License Year record(ID#=" + licenseYear.id + ") exists in search result list.");
		boolean result = licenseYearPage.verifyLicenseYearExist(licenseYear);
		if(!result) {
			throw new ErrorOnPageException("License Year record(ID#=" + licenseYear.id + ") should exist in search result list.");
		} else logger.info("License Year record(ID#=" + licenseYear.id + ") really exists in search result list.");
	}
}
