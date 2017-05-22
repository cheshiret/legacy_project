/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaycategory;

import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify error message and cancel for add action
 * @Preconditions:
 * @SPEC:<Add Privilege Display Category>
 * @Task#:Auto-488
 * 
 * @author ssong
 * @Date  Aug 31, 2011
 */
public class VerifyErrorMsgForAdd extends LicenseManagerTestCase{

	private LicMgrDisplayCategoriesConfigurationPage configPg = LicMgrDisplayCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget.getInstance();
	private String description,displayOrd,hiddenInSale;
	private boolean pass = true;
	private String errorMsg1,errorMsg2,errorMsg3;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoProdConfPgFromTopMenu();
		configPg.switchToDisplayCategoryTab();
		
		//verify error message correct
		this.addDisplayCategory("", displayOrd, hiddenInSale);
		this.verifyErrorMsg(errorMsg1);
		
		widget.setDisplayCategoryInfo(description, "", hiddenInSale);
		this.verifyErrorMsg(errorMsg2);
		
		widget.setDisplayCategoryInfo(description, "Non-Num", hiddenInSale);
		this.verifyErrorMsg(errorMsg3);
		
		widget.setDisplayCategoryInfo(description, "0", hiddenInSale);
		this.verifyErrorMsg(errorMsg3);
		
		widget.setDisplayCategoryInfo(description, "9.0", hiddenInSale);
		this.verifyErrorMsg(errorMsg3);
		
		//verify cancel button function correct
		String temp = new Long(DateFunctions.getCurrentTime()).intValue()+"";
		widget.setDisplayCategoryInfo("@Test&", temp, hiddenInSale);
		this.verifyCancelButton(temp);
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();		
	}

	private void addDisplayCategory(String description,String displayOrd,String hiddenInSale){
		configPg.clickAdd();
		ajax.waitLoading();
		widget.waitLoading();
		widget.setDisplayCategoryInfo(description, displayOrd, hiddenInSale);
	}
	
	private void verifyCancelButton(String displayOrd){
		logger.info("Verify Cancel Button Function Correct.");
		
		widget.clickCancel();
		Object page = browser.waitExists(widget,configPg);
		if(page == widget){
			pass &= false;
			logger.error("Cancel button function not correct.");
		}
		if(configPg.checkDisplayCategoryExists(displayOrd)){
			pass &= false;
			logger.error("Cancel button function not correct.");
		}
	}
	
	private void verifyErrorMsg(String expectMsg){
		widget.clickOK();
		Object page = browser.waitExists(widget,configPg);
		ajax.waitLoading();
		if(page == configPg){
			pass &=false;
			logger.error("Expect Page is Widget Page.");
		}
		String actualMsg = widget.getErrorMsg();
		if(actualMsg == null||!actualMsg.contains(expectMsg)){
			pass &= false;
			logger.error("Error Msg Not Correct,Expect Msg is '"+expectMsg+"',actual Msg is '"+actualMsg);
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		description = "AddTest"+DateFunctions.getCurrentTime();
		displayOrd = new Long(DateFunctions.getCurrentTime()).intValue()+"";
		hiddenInSale = "Yes";
		
		errorMsg1= "The Privilege Display Category Description is required. Please specify the Description.";
		errorMsg2 = "The Display Order is required. Please specify the Display Order.";
		errorMsg3 = "The Display Order entered is not valid. Please enter an integer value greater than 0.";
	}
}
