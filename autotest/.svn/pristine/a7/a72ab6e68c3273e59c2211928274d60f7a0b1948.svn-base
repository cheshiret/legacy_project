/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.configuration.product.displaysubcategory.add;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrProductConfigurationPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * @Description:This test case verify the steps taken by the User to add a new Privilege Display Sub-Category for the Contract.
 * @Preconditions:
 * @SPEC:Use Case Specification: Add Privilege Display Sub-Category 
 * @Task#:AUTO-713
 * 
 * @author szhou
 * @Date  Aug 18, 2011
 */
public class VerifyAddSuccess extends LicenseManagerTestCase{
	private PrivilegeDisplayCategory category = new PrivilegeDisplayCategory();
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);

		// go to report categories page
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		
		// add privilege display sub category successful
		category.id=lm.addPrivilegeDisplaySubCategory(category, false);
		boolean succ=this.verifyAddDisplaySubCategorySuccess(category);
		
		if (!succ) {
			throw new ActionFailedException(
					"Fail toe add privilege display sub-category...");
		}
		
		lm.logOutLicenseManager();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		String seq = DataBaseFunctions.getSeqNumber("SEQ_NUM");
		category.description="addCategoryForTest"+seq;
		category.displayOrder=seq;
		category.addUser=DataBaseFunctions.getLoginUserName(login.userName);
		category.addLocation=login.location.split("/")[1];
		category.addDate=DateFunctions.formatDate(DateFunctions.getToday(),"EEE MMM dd yyyy");
	}
	
	private boolean verifyAddDisplaySubCategorySuccess(PrivilegeDisplayCategory Info) {
		LicMgrProductConfigurationPage prodConfPg = LicMgrProductConfigurationPage
		.getInstance();
		LicMgrAddOrEditDisplaySubCategoryWidget widget=LicMgrAddOrEditDisplaySubCategoryWidget.getInstance();
		boolean result=true;

		logger.info("Verify add display sub-category success or not ...");

		logger.info("View display sub-category list ...");
		if (this.compareDisplaySubCategoryListInfo(Info)) {
			logger
					.info("Add display sub-category successful on the product configuration page.");
		} else {
			logger.error("Failed to add '" + Info.id
					+ " ' display sub-category on the product configuration page.");
			result= false;
		}

		logger.info("View display sub-category detail information ...");
		prodConfPg.clickLink(Info.id);
		widget.waitLoading();
		if (widget.compareDisplaySubCategoryDetailInfo(Info)) {
			logger
					.info("Add display sub-category successful on the add privilege display sub category page.");
		} else {
			logger.error("Failed to add '" + Info.id
					+ " ' display sub-category on the add privilege display sub-category page.");
			result= false;
		}

		widget.clickOK();
		prodConfPg.waitLoading();
		ajax.waitLoading();

		return result;
	}
	
	private boolean compareDisplaySubCategoryListInfo(PrivilegeDisplayCategory Info){
		LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryConfigPg = LicMgrDisplaySubCategoriesConfigurationPage
		.getInstance();
		boolean result = true;
		
		List<String> values=displaySubCategoryConfigPg.getDisplaySubCategoryInfoById(Info.id);
		if(!values.get(0).equals(Info.id)){
			logger.error("The expected id is not " + Info.description + ".");
			result &= false;
		}
        if(!values.get(1).equals(Info.description)){
        	logger.error("The expected description is not " + Info.description + ".");
			result &= false;
		}
        if(!values.get(2).equals(Info.displayOrder)){
        	logger.error("The expected display order is not " + Info.displayOrder + ".");
			result &= false;
		}

		return result;
	}

}
