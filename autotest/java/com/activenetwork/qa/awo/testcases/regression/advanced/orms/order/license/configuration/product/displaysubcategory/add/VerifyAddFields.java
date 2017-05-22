/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * @Description:This test case verify the error message when user add a new Privilege Display Sub-Category for the Contract.
 * @Preconditions: 
 * @SPEC:Use Case Specification: Add Privilege Display Sub-Category
 * @Task#:AUTO-713
 * 
 * @author szhou
 * @Date Aug 18, 2011
 */
public class VerifyAddFields extends LicenseManagerTestCase{
	LicMgrAddOrEditDisplaySubCategoryWidget widget=LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
	LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage.getInstance();
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private String message1, message2, message3;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		// add privilege display sub category and verify the Description is not specified
		String error=lm.addPrivilegeDisplaySubCategory(category, false);
		boolean errormess=this.verifyAddDisplaySubCategoryError(error,message1);
		
		// add privilege display sub category and verify the Display Order is not specified
		category.description="add display sub-category for automation test error";
		error=lm.addPrivilegeDisplaySubCategory(category, false);
		errormess &= this.verifyAddDisplaySubCategoryError(error,message2);
		
		// add privilege display sub category and verify the Display Order is not an integer value greater than 0.
		category.displayOrder="-5";
		error=lm.addPrivilegeDisplaySubCategory(category, false);
		errormess &= this.verifyAddDisplaySubCategoryError(error,message3);
		widget.clickCancel();
		prodConfPg.waitLoading();
		
		if (!errormess) {
			throw new ActionFailedException(
					"The error message on the add privilege display sub-category page displayed wrong.");
		}
		
		lm.logOutLicenseManager();
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		message1="The Privilege Display Sub-Category Description is required. Please specify the Description.";
		message2="The Display Order is required. Please specify the Display Order.";
		message3="The Display Order entered is not valid. Please enter an integer value greater than 0.";
	}
	
	
    private boolean verifyAddDisplaySubCategoryError(String error,String expectMess) {
		
		logger.info("Verify add privilege display sub category error message.");
		if (error.equals(expectMess)) {
			logger
					.info("The error message on the add privilege display sub category page displayed correct.");
		} else {
			logger
					.error("The error message on the add privilege display sub category page should be '"
							+ error + " '");
			return false;
		}
		return true;
	}
}
