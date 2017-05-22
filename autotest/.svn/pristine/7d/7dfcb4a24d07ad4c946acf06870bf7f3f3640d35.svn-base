package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplaySubCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplaySubCategoriesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddSubDisplayCategoryFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrDisplaySubCategoriesConfigurationPage displaySubCategoryPage = LicMgrDisplaySubCategoriesConfigurationPage
			.getInstance();
	private String description, disOrd;
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
	
	@Override
	public void wrapParameters(Object[] param) {				
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		login.contract = (String)param[0];
		login.location = (String)param[1];
		description = (String)param[2];
		disOrd = (String)param[3];
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedin= false;
		}
		if(login.contract.equals(contract) && loggedin && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		if (!loggedin || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedin= true;
		}
		if (!homePg.exists()) {
			lm.gotoHomePage();
		}
		contract = login.contract;
		location = login.location;
		
		//Add sub display categories
		lm.gotoDisplaySubCategoryConfigPgFromTopMenu();
		lm.addDisplaySubCategory(description, disOrd);

		//Verify the result
		this.verifyResult();
		
		newAddValue = this.getDisplayCategoryID(description);
	
	}
	
	public void verifyResult(){
		LicMgrAddOrEditDisplaySubCategoryWidget addDisplaySubCategoryWidget = LicMgrAddOrEditDisplaySubCategoryWidget
				.getInstance();
		LicMgrDisplaySubCategoriesConfigurationPage configPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		if(addDisplaySubCategoryWidget.exists()){
			String errorMes = addDisplaySubCategoryWidget.getErrorMsg();
			
			addDisplaySubCategoryWidget.clickCancel();
			displaySubCategoryPage.waitLoading();
			throw new ErrorOnPageException("create sub display categorys failed:description="+description+",displayOrder="+disOrd+",errorMes="+errorMes);
		}
		if (!configPg.compareDisplaySubCategoryInfo(description, disOrd)) {
			throw new ErrorOnPageException("Failed. The new Sub Category info is not correct!");
		}
		logger.info("Successfully Add New Sub Display Category!");
	}
	
	private String getDisplayCategoryID(String description) {
		LicMgrDisplaySubCategoriesConfigurationPage configPg = LicMgrDisplaySubCategoriesConfigurationPage
				.getInstance();
		return configPg.getDisplaySubCategoryIdByDescription(description);
	}
}
