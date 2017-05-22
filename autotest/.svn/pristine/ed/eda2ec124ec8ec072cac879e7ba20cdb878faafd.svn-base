package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.edit;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description:Verify exist item steps
 * @Preconditions: None
 * @SPEC:Edit Privilege Display Sub-Category.doc
 * @Task#:AUTO-716
 * 
 * @author eliang
 * @Date  Sep 5, 2011
 */
public class VerifyExistError extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
	private String errorMsg4,errorMsg5;
	private boolean pass = true;
	
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		category.id = this.getDisplaySubCategoryId(category.description);
		if("".equalsIgnoreCase(category.id)){
			category.id = lm.addPrivilegeDisplaySubCategory(category, false);
		}
		logger.debug("category id value is:" + category.id);
		
		displayCategoryConfigPg.clickLink(category.id);
		widget.waitLoading();
		
		List<String> existCategory = displayCategoryConfigPg.getFirstDisplayCategory();
		
		widget.setDisplaySubCategoryInfo(existCategory.get(1), category.displayOrder);
		this.verifyErrorMsg(errorMsg4);
		
		widget.setDisplaySubCategoryInfo(category.description, existCategory.get(2));
		this.verifyErrorMsg(errorMsg5);
		
		widget.clickCancel();
		ajax.waitLoading();
		
		logger.info("widget closed, then sub-category page should be found now!");
		
		//TODO: using subCategory instance replace Category then add getFirstDisplayCategory method for it.
		//displayCategoryConfigPg.waitExists();
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		category.description = "EditSubCategoryTestError";
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		
		errorMsg4 = "The Description entered already exists. The Description must be unique.";
		errorMsg5 = "The Display Order entered already exists. The Display Order must be unique.";
	}
	
	private String getDisplaySubCategoryId(String description) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPage = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
		
		String id=displaySubCategoryConfigPage.getDisplaySubCategoryIdByDescription(description);
		
		return id;
	}
	
	private void verifyErrorMsg(String expectMsg){
		widget.clickOK();
		ajax.waitLoading();
		Object page = browser.waitExists(widget,displayCategoryConfigPg);
		if(page == displayCategoryConfigPg){
			pass &=false;
			logger.error("Expect Page is Widget Page.");
		}
		String actualMsg = widget.getErrorMsg();
		if(actualMsg == null||!actualMsg.equalsIgnoreCase(expectMsg)){
			pass &= false;
			logger.error("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}

	}
}
