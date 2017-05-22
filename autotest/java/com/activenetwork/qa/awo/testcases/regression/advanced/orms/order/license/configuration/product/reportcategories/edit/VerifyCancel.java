/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.reportcategories.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeReportCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrReportCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;

/**
 * @Description:This test case verify the cancel steps when the user edit an existing Privilege Report Category for the Contract.
 * @Preconditions:existing the report category that can be edited
 * @SPEC:Use Case Specification: Edit Privilege Report Category 
 * @Task#:AUTO-714
 * 
 * @author szhou
 * @Date   Aug 17, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{
	private LicMgrReportCategoriesConfigurationPage reportCategoryConfigPage = LicMgrReportCategoriesConfigurationPage.getInstance();
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	
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
		report.description="CancelEditReportCategory";
		lm.gotoEditPrivilegeReportCategory(report.id);
		
		// cancel edit privilege information 
		lm.editPrivilegeReportCategory(report, true);
		boolean cancel=this.verifyCancelEditReportCategory(report.description);
		
		if (!cancel) {
			throw new ActionFailedException(
					"Fail to cancel edit privilege report category...");
		}
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		report.description="EditReportCatForCancel";
		report.displayOrder="999";
		report.groupNum="999";
		
	}
	
	private String getReportCategoryId(String description){
		String id=reportCategoryConfigPage.getReportCategoryIdByDescription(description);
		
		return id;
	}
	
	private boolean verifyCancelEditReportCategory(String description) {
		logger.info("Verify cancel edit reprot category success or not ...");

		String id=reportCategoryConfigPage.getReportCategoryIdByDescription(description);
		if ("".equals(id)) {
			logger.info("Cancel edit reprot category for privilege successful.");
		} else {
			logger.info("Failed to cancel " + description
					+ " report category for privilege.");
			return false;
		}
		return true;

	}

}
