package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.reportcategory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify editing an existing report category successfully or not
 * @Preconditions:
 * @SPEC: <<Edit Privilege Report Category.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 29, 2011
 */
public class EditReportCategory extends LicenseManagerTestCase {
	private PrivilegeReportCategory reportCategory = new PrivilegeReportCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoReportCategoryConfigPgFromTopMenu();
		
		//add report category
		reportCategory.id = lm.addReportCategory(reportCategory);
		
		//edit the new added report category
		reportCategory.description = "Basic_Edit-"+DateFunctions.getCurrentTime();
		reportCategory.displayOrder = String.valueOf(Math.abs(new Long(DateFunctions.getCurrentTime()).intValue()));
		reportCategory.groupNum = "14";
		lm.gotoEditPrivilegeReportCategory(reportCategory.id);
		lm.editPrivilegeReportCategory(reportCategory);
		
		//verify new edited report category list and detail info
		this.verifyReportCategoryListInfo(reportCategory);
		this.verifyReportCategoryDetailInfo(reportCategory);
		
		//clean up
		lm.deleteDisplayCategoryFromDB(schema, reportCategory.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		reportCategory.description = "Basic_Add_e-"+DateFunctions.getCurrentTime();
		reportCategory.displayOrder = String.valueOf(Math.abs(new Long(DateFunctions.getCurrentTime()).intValue()));
		reportCategory.groupNum = "10";
		reportCategory.addUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		reportCategory.addLocation = login.location.split("/")[1];
		reportCategory.addDate = DateFunctions.formatDate(DateFunctions.getToday(), "E MMM dd yyyy");
		reportCategory.updateUser = reportCategory.addUser;
		reportCategory.updateLocation = reportCategory.addLocation;
		reportCategory.updateDate = reportCategory.addDate;
	}
	
	/**
	 * Verify whether report category list info are correct or not
	 * @param reportCategory
	 */
	private void verifyReportCategoryListInfo(PrivilegeReportCategory reportCategory) {
		LicMgrReportCategoriesConfigurationPage reportCategoryPg = LicMgrReportCategoriesConfigurationPage.getInstance();
		
		logger.info("Verify privilege Report Category list info.");
		reportCategoryPg.verifyReportCategoryInfo(reportCategory);
	}
	
	/**
	 * Verify whether report category detail info are correct or not
	 * @param reportCategory
	 */
	private void verifyReportCategoryDetailInfo(PrivilegeReportCategory reportCategory) {
		LicMgrReportCategoriesConfigurationPage reportCategoryPg = LicMgrReportCategoriesConfigurationPage.getInstance();
		LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget.getInstance();
		
		logger.info("Verify privielge Report Category detail info.");
		reportCategoryPg.clickLink(reportCategory.id);
		ajax.waitLoading();
		widget.waitLoading();
		boolean result = widget.compareReportDetailInfo(reportCategory);
		if(!result) {
			throw new ErrorOnPageException("Report Category detail info is incorrect.");
		}
		widget.clickOK();
		ajax.waitLoading();
		reportCategoryPg.waitLoading();
	}
}
