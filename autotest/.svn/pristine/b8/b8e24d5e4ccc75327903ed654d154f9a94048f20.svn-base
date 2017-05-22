package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeDisplayCategory;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrAddOrEditDisplayCategoryWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.product.LicMgrDisplayCategoriesConfigurationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;


public class AddDisplayCategoriesFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private PrivilegeDisplayCategory displayCategory = new PrivilegeDisplayCategory();
	private LicenseManager lm = LicenseManager.getInstance();
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
		
		displayCategory = (PrivilegeDisplayCategory)param[2];
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
		//Add display categories
		lm.gotoDisplayCategoryConfigPgFromTopMenu();
		lm.addDisplayCategory(displayCategory);
		
		//Verify the result
		this.verifyResult();
		
		newAddValue = this.getDisplayCategoryID(displayCategory.description);
		
	}
	
	public void verifyResult(){
		LicMgrAddOrEditDisplayCategoryWidget addDisplayCategoryWidget = LicMgrAddOrEditDisplayCategoryWidget
				.getInstance();    
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();
		if(addDisplayCategoryWidget.exists()){
			String errorMes = addDisplayCategoryWidget.getErrorMsg();
			addDisplayCategoryWidget.clickCancel();
			displayCategoryConfigPg.waitLoading();
			throw new ErrorOnPageException("create display categorys failed:description="+displayCategory.description+",displayOrder="+displayCategory.displayOrder+",hiddenInSalesFlow="+displayCategory.hiddenInSalesFlow+",errorMes="+errorMes);
		}
		displayCategoryConfigPg.verifyDisplayCategoryInfo(displayCategory);
		logger.info("Successfully add display category: description="+displayCategory.description);
	}
	
	private String getDisplayCategoryID(String description) {
		LicMgrDisplayCategoriesConfigurationPage displayCategoryConfigPg = LicMgrDisplayCategoriesConfigurationPage
				.getInstance();
		return displayCategoryConfigPg.getDisplayCategoryId(description);
	}
}
