package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeAddQuantityControlWidget;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddQuantityControlFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	LicMgrPrivilegeAddQuantityControlWidget quantityControlWidget = LicMgrPrivilegeAddQuantityControlWidget.getInstance();
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
		quantityControl = (QuantityControlInfo)param[2];
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
		
		lm.gotoPrivilegeQuantityControlPgFromTopMenue(quantityControl.productCode);
		lm.deactivatePrivilegeQuantityControlRecords();
		lm.addPrivilegeQuantityControl(quantityControl);
		
		newAddValue = this.verifyResult();
	}
	
	public String verifyResult(){
		String failedMsg = "Add privilege quanitty control failed:" +
		 		"privilege code="+quantityControl.productCode+", locationClass="+quantityControl.locationClass + ".";
		if(quantityControlWidget.exists()) {
			 String errorMsg = quantityControlWidget.getErrorMessage();
			 quantityControlWidget.clickCancel();
			 ajax.waitLoading();
			 quantityControlPg.waitLoading();
			 throw new ErrorOnPageException(failedMsg +	" Due to: " + errorMsg);	 
		 }
		 
		 String id = quantityControlPg.getQuantityControlID(quantityControl.locationClass, quantityControl.multiSalesAllowance);
		 if (id == null || !id.matches("\\d+")) {
			 throw new ErrorOnPageException(failedMsg);
		 }
		 logger.info("Successfully Add Quantity Control!");
		 return id;
	}
}
