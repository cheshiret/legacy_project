/**
 *
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the error message when the user edit an existing Privilege Report Category for the Contract.
 * @Preconditions:existing the report category that can be edited
 * @SPEC:Use Case Specification: Edit Privilege Report Category
 * @Task#:AUTO-714
 *
 * @author szhou
 * @Date  Aug 17, 2011
 */
public class VerifyExistError extends LicenseManagerTestCase{
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	private String message1, message2;

	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();

		// add a report category for edit
		report.id=this.getReportCategoryId(report.description);
		if("".equals(report.id)){
			report.id=lm.addReportCategory(report,false);
		}// report category edit
		String id=report.id;
		this.setReportCategoryInfo();
		report.id=this.getReportCategoryId(report.description);
		if("".equals(report.id)){
			report.id=lm.addReportCategory(report,false);
		}// exist report category

		// go to edit privilege report category
		//lm.gotoEditPrivilegeReportCategory(report.id);

		// edit privilege information and verify the Description already exists using case-insensitive string matching
		lm.gotoEditPrivilegeReportCategory(id);
		lm.editPrivilegeReportCategory(report, false);
		boolean errormess =this.verifyEditReportCategoryError(message1);

		// edit privilege information and verify the Display Order already exists
		report.description=("EditReportForTest"+DateFunctions.getCurrentTime()).substring(0,24);
		lm.editPrivilegeReportCategory(report, false);
		errormess &=this.verifyEditReportCategoryError(message2);

		lm.editPrivilegeReportCategory(report, true);

		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the edit privilege report page displayed wrong.");
		}

		lm.logOutLicenseManager();
	}


	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		message1="The Description entered already exists. The Description must be unique.";
		message2="The Display Order entered already exists. The Display Order must be unique.";

		report.description=("ReportCategoryForAutoTest").substring(0,25);
		report.displayOrder="16129";
		report.groupNum="16129";
	}

	private void setReportCategoryInfo(){
		report.description="EditReportForTestExists";
		report.displayOrder="16087";
		report.groupNum="16087";
	}

	private String getReportCategoryId(String description){
		LicMgrReportCategoriesConfigurationPage reportCategoryConfigPg = LicMgrReportCategoriesConfigurationPage
		.getInstance();

		String id=reportCategoryConfigPg.getReportCategoryIdByDescription(description);

		return id;
	}

	private boolean verifyEditReportCategoryError(String error) {
		LicMgrAddOrEditReportCategoryWidget widget=LicMgrAddOrEditReportCategoryWidget.getInstance();

			String message = widget.getErrorMsg();

		logger.info("Verify edit report category error message.");
		if (error.equals(message)) {
			logger
					.info("The error message on the edit report category page displayed correct.");
		} else {
			logger
					.error("The error message on the edit report category page should be '"
							+ error + " '" + "but not " + message);
			return false;
		}
		return true;
	}

}
