package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.QuantityControlInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeQuantityControlPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddQuantityControl extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String code = "";
	private LicenseManager lm = LicenseManager.getInstance();
	private QuantityControlInfo quantityControl = new QuantityControlInfo();
	LicMgrPrivilegeQuantityControlPage quantityControlPg = LicMgrPrivilegeQuantityControlPage.getInstance();
	
	@Override
	public void execute() {
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn )|| (loggedIn && !quantityControlPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn = true;
		}
		if(!code.equalsIgnoreCase(quantityControl.productCode)) {
			lm.gotoProductSearchListPageFromTopMenu("Privilege");
			lm.gotoPrivilegeDetailsPageFromProductListPage(quantityControl.productCode);
		}
		
		lm.addPrivilegeQuantityControl(quantityControl);
		
		this.verifyResult();
		
		contract = login.contract;
		code = quantityControl.productCode;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 71; // the start point in the data pool
		endpoint = 71; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg = new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "PrivilegeCode";
		logMsg[2] = "LocatinClass";
		logMsg[3] = "Result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		quantityControl.productCode = dpIter.dpString("privilegeCode");
		quantityControl.locationClass = dpIter.dpString("locationClass");
		quantityControl.multiSalesAllowance = dpIter.dpString("multisaleSallowance");
		quantityControl.maxQuantityPerTran = dpIter.dpString("maxQuanPerTrans");
		quantityControl.maxAllowed = dpIter.dpString("MaxAllowed");
		quantityControl.replacementMaxAllowed = dpIter.dpString("replacementMaxAllowed");
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = quantityControl.productCode;
		logMsg[2] = quantityControl.locationClass;
	}

	public void verifyResult(){
		 if(!quantityControlPg.exists()) {
			 logger.error("Add privilege pricing failed:privilege code="+quantityControl.productCode+" exception");
			 logMsg[3] = "Failed";
		 } else {
			 logMsg[3] = "Passed";
		 }
	}
}
