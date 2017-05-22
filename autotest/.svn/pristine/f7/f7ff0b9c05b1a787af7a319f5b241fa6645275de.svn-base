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
 * @Description:This test case verify the steps taken by the User to cancel when they add a new Privilege Display Sub-Category for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Privilege Display Sub-Category
 * @Task#:AUTO-713
 * 
 * @author szhou
 * @Date   Aug 18, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		// cancel add privilege display sub category
		lm.addPrivilegeDisplaySubCategory(category, true);
		boolean cancel=this.verifyCancelAddDisplaySubCategory(category.description);
		
		if (!cancel) {
			throw new ActionFailedException(
					"Fail to cancel add privilege display sub-category...");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
 
		category.description="AdddisplaySub-categoryForAutomationTestforCancel";
		category.displayOrder="929";
	}
	
	private boolean verifyCancelAddDisplaySubCategory(String description) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();

		logger.info("Verify cancel add display sub category success or not ...");

		String id=displaySubCategoryConfigPg.getDisplaySubCategoryIdByDescription(description);
		if ("".equals(id)) {
			logger.info("Cancel add display sub category for privilege successful.");
		} else {
			logger.info("Failed to cancel " + description
					+ " display sub category for privilege.");
			return false;
		}
		return true;

	}

}
