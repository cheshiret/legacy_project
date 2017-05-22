/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaycategory;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify all error message for edit a privilege display category
 * @Preconditions:
 * @SPEC:<Edit Privilege Display Category>
 * @Task#:Auto-488
 * 
 * @author ssong
 * @Date  Sep 1, 2011
 */
public class VerifyErrorMsgForEdit extends LicenseManagerTestCase{

	private LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget.getInstance();
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	private boolean pass = true;
	private String errorMsg1,errorMsg2,errorMsg3,errorMsg4,errorMsg5;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoProdConfPgFromTopMenu();
		displayCategoryConfigPg.switchToDisplayCategoryTab();
		lm.addDisplayCategory(displayCategory);
		
		String categoryId = displayCategoryConfigPg.getDisplayCategoryInfo(displayCategory.description, "Description").get(0);
		
		displayCategory.description = "Edit-"+DateFunctions.getCurrentTime();
		displayCategory.hiddenInSalesFlow = "No";
		
		//verify error message
		List<String> existCategory = displayCategoryConfigPg.getFirstDisplayCategory();
		lm.gotoDisplayCategoryDetailPg(categoryId);
		
		widget.setDisplayCategoryInfo("", displayCategory.displayOrder, displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg1);
		
		widget.setDisplayCategoryInfo(displayCategory.description, "", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg2);
		
		widget.setDisplayCategoryInfo(displayCategory.description, "Non-Num", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg3);
		
		widget.setDisplayCategoryInfo(displayCategory.description, "0", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg3);
		
		widget.setDisplayCategoryInfo(displayCategory.description, "9.0", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg3);
		
		widget.setDisplayCategoryInfo(existCategory.get(1), "9", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg4);
		
		widget.setDisplayCategoryInfo(displayCategory.description, existCategory.get(2), displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg5);
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		cancelEdit();
		
		lm.logOutLicenseManager();		
	}

	private void cancelEdit(){
		widget.clickCancel();
		ajax.waitLoading();
		displayCategoryConfigPg.waitLoading();
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
			//Test
			throw new ErrorOnPageException("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
			//Test
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		displayCategory.description = "Add-"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = ""+DataBaseFunctions.getEmailSequence();
		displayCategory.hiddenInSalesFlow = "Yes";
		
		errorMsg1= "The Privilege Display Category Description is required. Please specify the Description.";
		errorMsg2 = "The Display Order is required. Please specify the Display Order.";
		errorMsg3 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
		errorMsg4 = "The Description entered already exists. The Description must be unique.";
		errorMsg5 = "The Display Order entered already exists. The Display Order must be unique.";
	}
}
