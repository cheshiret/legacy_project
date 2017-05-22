/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
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
 * @Date  Aug 18, 2011
 */
public class VerifyExistError  extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private String message1, message2;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		category.id=this.getCategoryId(category.description);
		if("".equals(category.id)){
			category.id=lm.addPrivilegeDisplaySubCategory(category, false);
		}
		
		// add privilege display sub category and verify the Description already exists using case-insensitive string matching.
		String error=lm.addPrivilegeDisplaySubCategory(category, false);
		boolean errormess = this.verifyAddDisplaySubCategoryError(error,message1);
		
		// add privilege display sub category and verify the Display Order already exists.
		category.description="addSubCategoryForExist";
		error=lm.addPrivilegeDisplaySubCategory(category, false);
		errormess &= this.verifyAddDisplaySubCategoryError(error,message2);
		
		lm.addPrivilegeDisplaySubCategory(category, true);
		
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

		message1="The Description entered already exists. The Description must be unique.";
		message2="The Display Order entered already exists. The Display Order must be unique.";
		
		category.description="AddSubCategoryForError";
		category.displayOrder="888";

	}
	
	private String getCategoryId(String description){
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage
		.getInstance();
		
		String id=displaySubCategoryConfigPg.getDisplaySubCategoryIdByDescription(description);
		
		return id;
	}

	 private boolean verifyAddDisplaySubCategoryError(String error,String expectMess) {
			
			logger.info("Verify add privilege display sub category error message.");
			if (error.equals(expectMess)) {
				logger
						.info("The error message on the add privilege display sub category page displayed correct.");
			} else {
				logger
						.error("The error message on the add privilege display sub category page should be '"
								+ expectMess + " '" + "but the autual error message is: " + error);
				return false;
			}
			return true;
	}

}
