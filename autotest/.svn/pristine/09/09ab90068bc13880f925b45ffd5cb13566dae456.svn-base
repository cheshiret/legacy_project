package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryQuantityControlDetailsWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries.LicMgrLotteryQuantityControlsPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Oct 10, 2013
 */
public class AddLotteryQuantityControlFunction extends FunctionCase {
	
	private LicenseManager lm = LicenseManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private QuantityControlInfo control = new QuantityControlInfo();
	private boolean isLoggedIn = false;
	private String loggedInContract, loggedInLocation;
	
	@Override
	public void execute() {
		if(isLoggedIn) {
			if(!loggedInContract.equalsIgnoreCase(login.contract)) {
				lm.logOutLicenseManager();
				isLoggedIn = false;
			} else if(loggedInLocation.equalsIgnoreCase(login.location)) {
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		
		if(!isLoggedIn) {
			lm.loginLicenseManager(login);
			isLoggedIn = true;
		}
		
		loggedInContract = login.contract;
		loggedInLocation = login.location;
		
		if(!LicenseMgrHomePage.getInstance().exists()) {
			lm.gotoHomePage();
		}
		
		lm.gotoLotteriesProductListPgFromTopMenu();
		lm.gotoLotteryProductDetailsPageFromProductListPage(control.productCode);
		lm.gotoLotteryProductQuantityControlPage();
		lm.addLotteryQuantityControl(control);
		newAddValue = this.verifyResult();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		control = (QuantityControlInfo)param[1];
	}
	
	private String verifyResult(){
		LicMgrLotteryQuantityControlsPage quantityControlPg = LicMgrLotteryQuantityControlsPage.getInstance();
		LicMgrLotteryQuantityControlDetailsWidget quantityControlWidget = LicMgrLotteryQuantityControlDetailsWidget.getInstance();
		
		String failedMsg = "Add lottery quanitty control failed: " +
		 		"privilege code = "+control.productCode+", locationClass = "+control.locationClass + ".";
		if(quantityControlWidget.exists()) {
			 String errorMsg = quantityControlWidget.getErrorMessage();
			 quantityControlWidget.clickCancel();
			 ajax.waitLoading();
			 quantityControlPg.waitLoading();
			 throw new ErrorOnPageException(failedMsg +	" Due to: " + errorMsg);	 
		 }
		 
		 String id = quantityControlPg.getQuantityControlID(control.locationClass, control.multiSalesAllowance);
		 if (id == null || !id.matches("\\d+")) {
			 throw new ErrorOnPageException(failedMsg);
		 }
		 logger.info("Add Quantity Control successfully.");
		 
		 return id;
	}
}
