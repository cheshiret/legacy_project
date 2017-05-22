package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.displaysubcategory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify whether adding a display sub-category successfully or not
 * @Preconditions:
 * @SPEC: <<Add Privilege Display Sub-Category.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 29, 2011
 */
public class AddDisplaySubCategory extends LicenseManagerTestCase {
	private PrivilegeDisplayCategory displaySubCategory = new PrivilegeDisplayCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		//add display sub-category
		displaySubCategory.id = lm.addDisplaySubCategory(displaySubCategory.description, displaySubCategory.displayOrder);
		
		//verify the new added display sub-category list and detail info
		this.verifyDisplaySubCategoryListInfo(displaySubCategory);
		this.verifyDisplaySubCategoryDetailInfo(displaySubCategory);
		
		//clean up
		lm.deleteDisplayCategoryFromDB(schema, displaySubCategory.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		displaySubCategory.description = "Basic_Add-"+DateFunctions.getCurrentTime();
		displaySubCategory.displayOrder = String.valueOf(Math.abs(new Long(DateFunctions.getCurrentTime()).intValue()));
		displaySubCategory.addUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		displaySubCategory.addLocation = login.location.split("/")[1];
	}
	
	/**
	 * Verify whether display sub-category list info are correct with expected or not
	 * @param displaySubCategory
	 */
	private void verifyDisplaySubCategoryListInfo(PrivilegeDisplayCategory displaySubCategory) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryPg = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
		
		logger.info("Verify Privilege Display Sub Category list info.");
		displaySubCategoryPg.verifyDisplaySubCategoryInfo(displaySubCategory);
	}
	
	/**
	 * Verify whether display sub-category detail info are correct or not
	 * @param displayCategory
	 */
	private void verifyDisplaySubCategoryDetailInfo(PrivilegeDisplayCategory displaySubCategory) {
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryPg = LicMgrDisplaySubCategoriesConfigurationPage.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget = LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
		
		logger.info("Verify Privilege Display Sub Category detail info.");
		displaySubCategoryPg.clickLink(displaySubCategory.id);
		ajax.waitLoading();
		widget.waitLoading();
		boolean result = widget.compareDisplaySubCategoryDetailInfo(displaySubCategory);
		if(!result) {
			throw new ErrorOnPageException("Display Sub-Category detail info is incorrect.");
		}
		widget.clickOK();
		ajax.waitLoading();
		displaySubCategoryPg.waitLoading();
	}
}
