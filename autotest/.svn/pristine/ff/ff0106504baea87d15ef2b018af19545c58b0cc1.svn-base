/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaycategory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify system not allow add duplicated category
 * @Preconditions:
 * @SPEC:<Add Privilege Display Category>
 * @Task#:Auto-488
 * 
 * @author ssong
 * @Date  Aug 31, 2011
 */
public class VerifyDuplicatedNotAllow_Add extends LicenseManagerTestCase{

	private LicMgrProductConfigurationPage configPg = LicMgrProductConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget.getInstance();
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	private boolean pass = true;
	private String errorMsg1,errorMsg2;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoProdConfPgFromTopMenu();
		configPg.switchToDisplayCategoryTab();
		
		lm.addDisplayCategory(displayCategory);
		
		this.addDisplayCategory(displayCategory.description.toLowerCase(), "2999947", displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg1);
		
		widget.setDisplayCategoryInfo("@Test&", displayCategory.displayOrder, displayCategory.hiddenInSalesFlow);
		this.verifyErrorMsg(errorMsg2);
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		cancelAdd();
		
		lm.logOutLicenseManager();		
	}

	private void cancelAdd(){
		widget.clickCancel();
		configPg.waitLoading();
	}
	
	private void addDisplayCategory(String description,String displayOrd,String hiddenInSale){
		configPg.clickAdd();
		ajax.waitLoading();
		widget.waitLoading();
		widget.setDisplayCategoryInfo(description, displayOrd, hiddenInSale);
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
		
		displayCategory.description = "AddTest"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = Math.abs(new Long(DateFunctions.getCurrentTime()).intValue())+"";
		displayCategory.hiddenInSalesFlow = "Yes";
		
		errorMsg1 = "The Description entered already exists. The Description must be unique.";
		errorMsg2 = "The Display Order entered already exists. The Display Order must be unique.";
	}
}
