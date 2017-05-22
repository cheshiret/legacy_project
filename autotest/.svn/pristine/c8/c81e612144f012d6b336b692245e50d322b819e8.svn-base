package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:Verify cancel steps
 * @Preconditions: None
 * @SPEC:Edit Privilege Display Sub-Category.doc
 * @Task#:AUTO-716
 * 
 * @author eliang
 * @Date  Sep 5, 2011
 */
public class VerifyCancel extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private LicMgrDisplaySubCategoriesConfigurationPage configPg = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		category.id = this.getDisplaySubCategoryId(category.description);
		if("".equalsIgnoreCase(category.id)){
			category.id = lm.addPrivilegeDisplaySubCategory(category, false);
		}
		
		logger.debug("category id value is:" + category.id);
		
		configPg.clickLink(category.id);
		ajax.waitLoading();
		widget.waitLoading();
		
		//Verify cancel button
		category.description = "Edit-"+DateFunctions.getCurrentTime();
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		widget.setDisplaySubCategoryInfo(category.description, category.displayOrder);
		boolean cancel = this.verifyCancelButton(category.description);
		
		if (!cancel) {
			throw new ActionFailedException(
					"Fail to cancel edit privilege report category...");
		}
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		category.description = "EditSubCatVerifyCancel";
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
	}
	
	private String getDisplaySubCategoryId(String description){
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPage = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
		String id=displaySubCategoryConfigPage.getDisplaySubCategoryIdByDescription(description);
		
		return id;
	}
	
	private boolean verifyCancelButton(String description){
		logger.info("Verify if the Cancel Button Function Correct.");
		
		widget.clickCancel();
		Object page = browser.waitExists(widget,configPg);
		if(page == widget){
			pass &= false;
			logger.error("Edit display sub category detailed page still displayed, so Cancel button function not correct.");
		}
		if(configPg.checkDisplaySubCategoryExists(description)){
			pass &= false;
			logger.error("the desctription of canceled one is displayed on the config page so the Cancel button function not correct.");
		}
		
		String id=configPg.getDisplaySubCategoryIdByDescription(description);
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
