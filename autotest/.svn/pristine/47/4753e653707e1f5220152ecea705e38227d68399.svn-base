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
 * @Date  Aug 17, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{
	private PrivilegeReportCategory report = new PrivilegeReportCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		// go to report categories page
		lm.gotoReportCategoryConfigPgFromTopMenu();
		lm.addReportCategory(report, true);
		
		boolean cancel=this.verifyCancelAddReportCategory(report.description);
		
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
		
		report.description = "Add Report Category Test"+DateFunctions.getCurrentTime();
		report.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		report.groupNum = new Long(DateFunctions.getCurrentTime()).toString().substring(9,13);
	}

	private boolean verifyCancelAddReportCategory(String description) {
		LicMgrReportCategoriesConfigurationPage reportCategoryConfPg = LicMgrReportCategoriesConfigurationPage
				.getInstance();

		logger.info("Verify cancel add reprot category success or not ...");

		String id=reportCategoryConfPg.getReportCategoryIdByDescription(description);
		if ("".equals(id)) {
			logger.info("Cancel add reprot category for privilege successful.");
		} else {
			logger.info("Failed to cancel " + description
					+ " report category for privilege.");
			return false;
		}
		return true;

	}
}
