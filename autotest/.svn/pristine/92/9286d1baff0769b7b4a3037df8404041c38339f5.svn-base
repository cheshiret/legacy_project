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
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description: This test case used to verify cancel edit privilege category and edit successful
 * @Preconditions:
 * @SPEC:<Edit Privilege Display Category>
 * @Task#:Auto-488
 * 
 * @author ssong
 * @Date  Sep 1, 2011
 */
public class VerifyEditSuccessful extends LicenseManagerTestCase{

	private LicMgrDisplayCategoriesConfigurationPage configPg = LicMgrDisplayCategoriesConfigurationPage.getInstance();
	private LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget.getInstance();
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	private boolean pass = true;
	private String updatedUser,updatedLocation;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoProdConfPgFromTopMenu();
		configPg.switchToDisplayCategoryTab();
		lm.addDisplayCategory(displayCategory);
		
		String categoryId = configPg.getDisplayCategoryInfo(displayCategory.description, "Description").get(0);
		lm.gotoDisplayCategoryDetailPg(categoryId);
		
		//verify cancel button work as design
		displayCategory.description = "Edit-"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = Math.abs(new Long(DateFunctions.getCurrentTime()).intValue())+"";
		displayCategory.hiddenInSalesFlow = "No";
		widget.setDisplayCategoryInfo(displayCategory.description, displayCategory.displayOrder, displayCategory.hiddenInSalesFlow);
		this.verifyCancelButton();
		
		//verify edit display category function work as design
		lm.gotoDisplayCategoryDetailPg(categoryId);
		widget.setDisplayCategoryInfo(displayCategory.description, displayCategory.displayOrder, displayCategory.hiddenInSalesFlow);
		this.verifyEditedSuccess();
		
		if(!pass){
			throw new ErrorOnPageException("For some check points failed,please check error log.");
		}
		
		lm.logOutLicenseManager();		
	}
	
	private void verifyCancelButton(){
		logger.info("Verify Cancel Button Function Correct.");
		
		widget.clickCancel();
		Object page = browser.waitExists(widget,configPg);
		if(page == widget){
			pass &= false;
			logger.error("Cancel button function not correct.");
		}
		if(configPg.checkDisplayCategoryExists(displayCategory.description)){
			pass &= false;
			logger.error("Cancel button function not correct.");
		}
	}
	
	private void verifyEditedSuccess(){
		logger.info("Verify Edit Category.");
		
		widget.clickOK();
		configPg.waitLoading();
        ajax.waitLoading();
		List<String> rowInfo = configPg.getDisplayCategoryInfo(displayCategory.description, "Description");
		if(rowInfo==null||rowInfo.size()<1){
			pass &= false;
			logger.error("Add Display category Failed.");
		}
		if(!rowInfo.get(0).matches("\\d+")){
			pass &= false;
			logger.error("Display Category ID "+rowInfo.get(0)+" not correct.");
		}
		if(!rowInfo.get(1).equalsIgnoreCase(displayCategory.description)){
			pass &= false;
			logger.error("Display Category Description "+rowInfo.get(1)+" not correct.");
		}
		if(!rowInfo.get(2).equalsIgnoreCase(displayCategory.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+rowInfo.get(2)+" not correct.");
		}
		if(!rowInfo.get(3).equalsIgnoreCase(displayCategory.hiddenInSalesFlow)){
			pass &= false;
			logger.error("Display Category Hidden In Sales Workflow "+rowInfo.get(3)+" not correct.");
		}
		configPg.clickLink(rowInfo.get(0));
		widget.waitLoading();
		if(!widget.getDescription().equalsIgnoreCase(displayCategory.description)){
			pass &= false;
			logger.error("Display Category Description "+widget.getDescription()+" not correct.");
		}
		if(!widget.getDisplayOrder().equalsIgnoreCase(displayCategory.displayOrder)){
			pass &= false;
			logger.error("Display Category Display Order "+widget.getDisplayOrder()+" not correct.");
		}
		if(!widget.getHiddenInSale().equalsIgnoreCase(displayCategory.hiddenInSalesFlow)){
			pass &= false;
			logger.error("Display Category Hiden in Sale "+widget.getHiddenInSale()+" not correct.");
		}
		if(!widget.getUpdatedUser().equalsIgnoreCase(updatedUser)){
			pass &= false;
			logger.error("Updated User "+widget.getUpdatedUser()+" not correct.");
		}
		if(!widget.getUpdatedLocation().equalsIgnoreCase(updatedLocation)){
			pass &= false;
			logger.error("Updated Location "+widget.getUpdatedLocation()+" not correct.");
		}
		if(DateFunctions.diffBetween(DateFunctions.getToday(),widget.getUpdatedDate())!=0){
			pass &= false;
			logger.error("Updated Date "+widget.getUpdatedDate()+" not Correct.");
		}
		widget.clickCancel();
		configPg.waitLoading();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		displayCategory.description = "Add-"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = Math.abs(new Long(DateFunctions.getCurrentTime()).intValue())+"";
		displayCategory.hiddenInSalesFlow = "Yes";
		
		updatedUser = "Test-Auto, QA-Auto";
		updatedLocation = login.location.split("/")[1];
	}
}
