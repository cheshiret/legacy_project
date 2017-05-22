package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: Add privilege report category and verify error messages.
 * @Preconditions:Null
 * @SPEC:Add Privilege Report Category.doc
 * @Task#:AUTO-723
 * 
 * @author eliang
 * @Date Aug 17, 2011
 */
public class VerifyExsitedError extends LicenseManagerTestCase {
	private String message1, message2;
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	private LicMgrReportCategoriesConfigurationPage reportCategoryConfigPg = LicMgrReportCategoriesConfigurationPage.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		String id = this.getCategoryId(report.description);

		if ("".equals(id)) {
			id = lm.addReportCategory(report, false);
		}

		// description existed
		String error = lm.addReportCategory(report, false);
		boolean errormess = this.verifyAddReportCategoryError(error, message1);

		// display order existed
		report.description = "AddReportCatTest"+ DateFunctions.getCurrentTime();
		error = lm.addReportCategory(report, false);
		errormess &= this.verifyAddReportCategoryError(error, message2);

		lm.addReportCategory(report, true);
		
		reportCategoryConfigPg.waitLoading();
		
		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add report category page displayed wrong.");
		}
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		report.description = "AddReportCategoryTest";
		report.displayOrder = "7323";
		report.groupNum = "7323";

		message1 = "The Description entered already exists. The Description must be unique.";
		message2 = "The Display Order entered already exists. The Display Order must be unique.";
	}

	private boolean verifyAddReportCategoryError(String error, String expectMess) {

		logger.info("Verify add privilege display sub category error message.");
		if (error.equals(expectMess)) {
			logger
					.info("The error message on the add report category page displayed correct.");
		} else {
			logger
					.error("The error message on the add report category page should be '"
							+ expectMess + " '" + ", actual is: " + error);
			return false;
		}
		return true;
	}

	private String getCategoryId(String description) {
		String id = reportCategoryConfigPg
				.getReportCategoryIdByDescription(description);

		return id;
	}
}
