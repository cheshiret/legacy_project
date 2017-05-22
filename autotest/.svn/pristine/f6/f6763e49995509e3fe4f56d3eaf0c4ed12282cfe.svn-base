package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.privilege;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeEditLicenseYearWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeLicenseYearPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify editing license year record successfully or not
 * @Preconditions: Need an existing privilege product which code is QQQ
 * @SPEC: <<Edit Privilege License Year.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Jan 4, 2012
 */
public class EditLicenseYear extends LicenseManagerTestCase {
	private LicenseYear licenseYear = new LicenseYear();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(licenseYear.productCode);
		lm.gotoPrivilegeLicenseYearPage();
		
		//add a new privilege License Year record
		licenseYear.id = lm.addLicenseYear(licenseYear).id;
		String oldLicenseYearID = licenseYear.id;
		
		//edit the new added License Year record
		this.prepareEditingParameters();
		licenseYear.id = lm.editPrivilegeLicenseYear(licenseYear);
		
		//verify license year list info
		this.verifyLicenseYearListInfo(licenseYear);
		
		//verify license year detail info
		this.verifyLicenseYearDetailInfo(licenseYear);
		
		//clean up
		lm.deactivatePrivilegeLicenseYear(licenseYear.id);
		
		//verify the old inactive license year record detail info
		licenseYear.id = oldLicenseYearID;
		this.prepareParametersForInactiveRecord();
		this.wrapParameters(null);
		licenseYear.status = OrmsConstants.INACTIVE_STATUS;
		lm.searchPrivilegeLicenseYearByInfo(licenseYear);//TODO DEFECT-32648
		this.verifyLicenseYearDetailInfo(licenseYear);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		licenseYear.productCode = "QQQ";
		licenseYear.status = OrmsConstants.ACTIVE_STATUS;
		licenseYear.locationClass = "05 - Dept of Marine Resources";
		licenseYear.licYear = String.valueOf(DateFunctions.getCurrentYear() + 10);
		
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(2);
		licenseYear.sellFromTime = "12:02";
		licenseYear.sellFromAmPm = "AM";
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(3);
		licenseYear.sellToTime = "11:57";
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
	
	private void prepareEditingParameters() {
		licenseYear.sellFromDate = DateFunctions.getDateAfterToday(3);
		licenseYear.sellFromTime = "12:05";
		licenseYear.sellFromAmPm = "AM";
		licenseYear.sellToDate = DateFunctions.getDateAfterToday(4);
		licenseYear.sellToTime = "11:55";
		licenseYear.sellToAmPm = "PM";
		licenseYear.validFromDate = licenseYear.sellFromDate;
		licenseYear.validFromTime = licenseYear.sellFromTime;
		licenseYear.validFromAmPm = licenseYear.sellFromAmPm;
		licenseYear.validToDate = licenseYear.sellToDate;
		licenseYear.validToTime = licenseYear.sellToTime;
		licenseYear.validToAmPm = licenseYear.sellToAmPm;
	}
	
	private void prepareParametersForInactiveRecord() {
		licenseYear.status = OrmsConstants.INACTIVE_STATUS;
		licenseYear.lastUpdatedUser = licenseYear.createUser;
		licenseYear.lastUpdatedLocation = licenseYear.createLocation;
		licenseYear.lastUpdatedTime = licenseYear.createTime;
	}
	
	private void verifyLicenseYearListInfo(LicenseYear expectedLicYear) {
		LicMgrPrivilegeLicenseYearPage licenseYearPage = LicMgrPrivilegeLicenseYearPage.getInstance();
		
		logger.info("Verify whether the License Year(ID#=" + expectedLicYear.id + ") list info.");
		boolean result = licenseYearPage.compareLicenseYearRecord(expectedLicYear);
		if(!result) {
			throw new ErrorOnPageException("The License Year record - " + expectedLicYear.id + " list info doesn't match with expected.");
		} else logger.info("The License Year record - " + expectedLicYear.id + " list info match with expected.");
	}
	
	private void verifyLicenseYearDetailInfo(LicenseYear expectedLicYear) {
		LicMgrPrivilegeLicenseYearPage licenseYearPage = LicMgrPrivilegeLicenseYearPage.getInstance();
		LicMgrPrivilegeEditLicenseYearWidget editWidget = LicMgrPrivilegeEditLicenseYearWidget.getInstance();
		
		logger.info("Verify whether the License Year(ID#=" + expectedLicYear.id + ") detail info.");
		licenseYearPage.clickLicenseYear(expectedLicYear.id);
		ajax.waitLoading();
		editWidget.waitLoading();
		boolean result = editWidget.compareLicenseYearRecord(expectedLicYear);
		editWidget.clickCancel();
		ajax.waitLoading();
		licenseYearPage.waitLoading();
		if(!result) {
			throw new ErrorOnPageException("The License Year record - " + expectedLicYear.id + " detail info doesn't match with expected.");
		} else logger.info("The License Year record - " + expectedLicYear.id + " detail info match with expected.");
	}
}
