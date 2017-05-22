/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaycategory;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case used to verify add privilege display category successful
 * @Preconditions:
 * @SPEC:<Add Privilege Display Category>
 * @Task#:Auto-488
 * 
 * @author ssong
 * @Date  Aug 31, 2011
 */
public class VerifyAddSucessful extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoDisplayCategoryConfigPgFromTopMenu();
		
		//add display category
		displayCategory.id = lm.addDisplayCategory(displayCategory);
		
		//verify add display category successful
		this.verifyDisplayCategoryInfo(displayCategory);
		
		lm.logOutLicenseManager();		
	}
	
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		displayCategory.description = "AddTest"+DateFunctions.getCurrentTime();
		displayCategory.displayOrder = Math.abs(new Long(DateFunctions.getCurrentTime()).intValue())+"";
		displayCategory.hiddenInSalesFlow = "Yes";
		
		displayCategory.addUser = DataBaseFunctions.getLoginUserName(login.userName);
		displayCategory.addLocation = login.location.split("/")[1];
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone tz = DataBaseFunctions.getContractTimeZone(schema);
		displayCategory.addDate = DateFunctions.getToday(tz);
	}
	
	/**
	 * This method is used to verify the display category list info in Display Category configuration page and detail info in detail widget
	 * @param displayCategory
	 */
	private void verifyDisplayCategoryInfo(PrivilegeDisplayCategory displayCategory) {
		LicMgrDisplayCategoriesConfigurationPage displayCategortPg = LicMgrDisplayCategoriesConfigurationPage
		.getInstance();
		
		LicMgrAddOrEditDisplayCategoryWidget widget = LicMgrAddOrEditDisplayCategoryWidget
		.getInstance();
		
		displayCategortPg.verifyDisplayCategoryInfo(displayCategory);
		
		lm.gotoDisplayCategoryDetailPg(displayCategory.id);
		widget.verifyDisplayCategoryDetailsInfo(displayCategory);
		widget.clickOK();
		ajax.waitLoading();
		displayCategortPg.waitLoading();
	}
}
