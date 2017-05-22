package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.edit;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:Verify edit fields steps expect exist items
 * @Preconditions: None
 * @SPEC:Edit Privilege Display Sub-Category.doc
 * @Task#:AUTO-716
 * 
 * @author eliang
 * @Date  Sep 5, 2011
 */
public class VerifyEditFields extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	private String errorMsg1,errorMsg2,errorMsg3;
	private boolean pass = true;
	
	private LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();

	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		category.id = this.getDisplaySubCategoryId(category.description);
		if("".equalsIgnoreCase(category.id)){
			category.id = lm.addPrivilegeDisplaySubCategory(category, false);
		}
		
		displaySubCategoryConfigPg.clickLink(category.id);
		widget.waitLoading();
		
		widget.setDisplaySubCategoryInfo("", category.displayOrder);
		this.verifyErrorMsg(errorMsg1);
		
		widget.setDisplaySubCategoryInfo(category.description, "");
		this.verifyErrorMsg(errorMsg2);
		
		widget.setDisplaySubCategoryInfo(category.description, "invalid");
		this.verifyErrorMsg(errorMsg3);
		
		widget.clickCancel();
		displaySubCategoryConfigPg.waitLoading();
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();	
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		category.description = "EditSubCategoryFields";
		category.displayOrder = new Long(DateFunctions.getCurrentTime()).toString().substring(9, 13);
		
		errorMsg1 = "The Privilege Display Sub-Category Description is required. Please specify the Description.";
		errorMsg2 = "The Display Order is required. Please specify the Display Order.";
		errorMsg3 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
	}
	
	private String getDisplaySubCategoryId(String description){
		String id=displaySubCategoryConfigPg.getDisplaySubCategoryIdByDescription(description);
		
		return id;
	}
	
	private void verifyErrorMsg(String expectMsg){
		widget.clickOK();
		Object page = browser.waitExists(widget,displaySubCategoryConfigPg);
		ajax.waitLoading();
		if(page == displaySubCategoryConfigPg){
			pass &=false;
			logger.error("Expect Page is Widget Page, but current is display sub category page!");
		}
		String actualMsg = widget.getErrorMsg();
		if(actualMsg == null||!actualMsg.equalsIgnoreCase(expectMsg)){
			pass &= false;
			logger.error("Error Msg Not Correct, Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}

	}
}
