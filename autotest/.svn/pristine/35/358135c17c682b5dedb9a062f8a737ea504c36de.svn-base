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

/**
 * @Description:This test case verify the error message when the user edit an existing Privilege Report Category for the Contract.
 * @Preconditions:existing the report category that can be edited
 * @SPEC:Use Case Specification: Edit Privilege Report Category 
 * @Task#:AUTO-714
 * 
 * @author szhou
 * @Date  Aug 17, 2011
 */
public class VerifyEditFields extends LicenseManagerTestCase{
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	private String message1, message2, message3,message4, message5;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		
		// add a report category for edit
		report.id=this.getReportCategoryId(report.description);
		if("".equals(report.id)){
			report.id=lm.addReportCategory(report,false);
		}
		
		// go to edit privilege report category
		this.resetReportCategoryInfo();
		lm.gotoEditPrivilegeReportCategory(report.id);
		
		// edit privilege information and verify the Description is not specified
		lm.editPrivilegeReportCategory(report, false);
		boolean errormess=this.verifyEditReportCategoryError(message1);
		
		// edit privilege information and verify the Display Order is not specified
		report.description=("EditReportCategoryForAutoTest").substring(0,25);
		lm.editPrivilegeReportCategory(report, false);
		errormess &=this.verifyEditReportCategoryError(message2);
		
		// edit privilege information and verify the Display Order is not an integer value greater than 0
		report.displayOrder="0";
		lm.editPrivilegeReportCategory(report, false);
		errormess &=this.verifyEditReportCategoryError(message3);
		
		// edit privilege information and verify the Group Number is not specified
		report.displayOrder="10";
		lm.editPrivilegeReportCategory(report, false);
		errormess &=this.verifyEditReportCategoryError(message4);
		
		// edit privilege information and verify the Group Number is not an integer value greater than 0
		report.groupNum="-1";
		lm.editPrivilegeReportCategory(report, false);
		errormess &=this.verifyEditReportCategoryError(message5);
		
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

		message1="The Privilege Report Category Description is required. Please specify the Description.";
		message2="The Display Order is required. Please specify the Display Order.";
		message3="The Display Order entered is not valid. Please enter an integer value greater than 0.";
		message4="The Group Number is required. Please specify the Group Number.";
		message5="The Group Number entered is not valid. Please enter an integer value greater than 0.";
	
		report.description=("ReportCategoryForTestingEditField").substring(0,25);
		report.displayOrder="229";
		report.groupNum="229";
	}
	
	private void resetReportCategoryInfo(){
		report.description="";
		report.displayOrder="";
		report.groupNum="";
	}
	
	private String getReportCategoryId(String description){
		LicMgrReportCategoriesConfigurationPage reportCategoryConfPg = LicMgrReportCategoriesConfigurationPage
		.getInstance();
		
		String id=reportCategoryConfPg.getReportCategoryIdByDescription(description);
		
		return id;
	}
	
	private boolean verifyEditReportCategoryError(String error) {
		LicMgrAddOrEditReportCategoryWidget widget=LicMgrAddOrEditReportCategoryWidget.getInstance();
		
		String message = widget.getErrorMsg().trim();

		logger.info("Verify edit report category error message.");
		if (error.equals(message)) {
			logger
					.info("The error message on the edit report category page displayed correct.");
		} else {
			logger
					.error("The error message on the edit report category page should be '"
							+ error + " '");
			return false;
		}
		return true;
	}

}
