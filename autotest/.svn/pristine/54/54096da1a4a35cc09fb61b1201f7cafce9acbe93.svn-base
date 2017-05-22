package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditReportCategoryWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description: Add new privilege report category.
 * @Preconditions:Null
 * @SPEC:Add Privilege Report Category.doc
 * @Task#:AUTO-723
 * 
 * @author eliang
 * @Date  Aug 17, 2011
 */

public class VerifyAddFields extends LicenseManagerTestCase{
	private String message1, message2, message3,message4,message5;
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	private LicMgrAddOrEditReportCategoryWidget widget = LicMgrAddOrEditReportCategoryWidget.getInstance();
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		
		//Verify no description
		String error = lm.addReportCategory(report, false);
		boolean errormsg = this.verifyAddReportCategoryError(error, message1);
		
		//Verify no display order
		report.description = "Add Report Category Test"+DateFunctions.getCurrentTime();
		error = lm.addReportCategory(report, false);
		errormsg &= this.verifyAddReportCategoryError(error, message2);
		
		//Verify display order is not an integer value great than 0
		report.displayOrder = "-1";
		error = lm.addReportCategory(report, false);
		errormsg &= this.verifyAddReportCategoryError(error, message3);
		
		//Verify no group number
		report.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		error = lm.addReportCategory(report, false);
		errormsg &= this.verifyAddReportCategoryError(error, message4);
		
		//Verify invalid group number
		report.groupNum = "a";
		error = lm.addReportCategory(report, false);
		errormsg &= this.verifyAddReportCategoryError(error, message5);
		
		if (!errormsg) {
			throw new ActionFailedException(
					"The error message on the add report category page displayed wrong.");
		}
		widget.clickCancel();
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
//		report.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
//		report.groupNum = new Long(DateFunctions.getCurrentTime()).toString().substring(9,13);
		
		message1 = "The Privilege Report Category Description is required. Please specify the Description.";
		message2 = "The Display Order is required. Please specify the Display Order.";
		message3 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
		message4 = "The Group Number is required. Please specify the Group Number.";
		message5 = "The Group Number entered is not valid. Please enter an integer value greater than 0.";
	}
	
    private boolean verifyAddReportCategoryError(String error,String expectMess) {
		
		logger.info("Verify add privilege display sub category error message.");
		if (error.equals(expectMess)) {
			logger
					.info("The error message on the add report category page displayed correct.");
		} else {
			logger
					.error("The error message on the add report category page should be '"
							+ error + " '");
			return false;
		}
		return true;
	}
}
