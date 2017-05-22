package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.product.displaycategory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: This case is used to verify whether edit an existing display category successfully or not
 * @Preconditions:
 * @SPEC: <<Edit Privilege Display Category.doc>>
 * @Task#: AUTO-842
 * 
 * @author qchen
 * @Date  Dec 28, 2011
 */
public class EditDisplayCategory extends LicenseManagerTestCase {
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoDisplayCategoryConfigPgFromTopMenu();
		
		//add display category
		displayCategory.id = lm.addDisplayCategory(displayCategory);
		
		//edit the new added display category
		displayCategory.description = "Basic_Edit-"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = String.valueOf(Math.abs(new Long(DateFunctions.getCurrentTime()).intValue()));
		displayCategory.hiddenInSalesFlow = "No";
		lm.editDisplayCategory(displayCategory);
		
		//verify the new edited display category list and detail info
		this.verifyDisplayCategoryListInfo(displayCategory);
		this.verifyDisplayCategoryDetailInfo(displayCategory);
		
		//clean up
		lm.deleteDisplayCategoryFromDB(schema, displayCategory.id);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
//		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		
		displayCategory.description = "Basic_Add_e-"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = String.valueOf(Math.abs(new Long(DateFunctions.getCurrentTime()).intValue()));
		displayCategory.hiddenInSalesFlow = "Yes";
		displayCategory.addUser = com.activenetwork.qa.awo.util.DataBaseFunctions.getLoginUserName(login.userName);
		displayCategory.addDate = DateFunctions.getToday();
		displayCategory.addLocation = login.location.split("/")[1];
		displayCategory.lastUpdatedUser = DataBaseFunctions.getLoginUserName(login.userName);
		displayCategory.lastUpdatedLocation = displayCategory.addLocation;
		displayCategory.lastUpdatedDate = DateFunctions.getToday();
	}
	
	/**
	 * This method is used to verify the display category list info in Display Category configuration page
	 * @param displayCategory
	 */
	private void verifyDisplayCategoryListInfo(PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategortPg = LicMgrDisplayCategoriesConfigurationPage
		.getInstance();
		
		logger.info("Verify privilege Display Category list info.");
		
		displayCategortPg.verifyDisplayCategoryInfo(displayCategory);
	}
	
	/**
	 * This method is used to verify the display category detail info in edit widget
	 * @param displayCategory
	 */
	private void verifyDisplayCategoryDetailInfo(PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategortPg = LicMgrDisplayCategoriesConfigurationPage
		.getInstance();
		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
		.getInstance();
		
		lm.gotoDisplayCategoryDetailPg(displayCategory.id);
		widget.verifyDisplayCategoryDetailsInfo(displayCategory);
		widget.clickOK();
		ajax.waitLoading();
		displayCategortPg.waitLoading();
	}
}
