package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddVendorNoChartOfAccount extends LicenseManagerTestCase{
	LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	List<Exception> list = new ArrayList<Exception>();
	
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		if(this.checkAddVendorButtonExit()){
			throw new ErrorOnPageException("The Add Vendor button shouldn't be shown in the page.");
		}
		
		//Clean environment
		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
	}
	
	private boolean checkAddVendorButtonExit(){
		return vendorSearchPg.checkAddVendorExist();
	}
}
