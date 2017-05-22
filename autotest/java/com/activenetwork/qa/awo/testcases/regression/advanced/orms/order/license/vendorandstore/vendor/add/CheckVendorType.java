package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.add;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description: This test case to test if the vendor type is shown, check it's necessary.
 * @Preconditions: If you want to make the Vendor Type displayed, you should execute some SQL sentence firstly.
 * Sample: 
 * insert into all_d_ref_system_code ( contract, notactive_cntr, id,
    active_ind,cd,dscr,name,classname,type)
    values ('MS','0','5104',1,'VendorType','AUTO REGRESSION TESTING','AUTO REGRESSION TESTING',
    'com.reserveamerica.system.vendor.configurable.VendorType',null) ;
 * @SPEC:Add Vendor
 * @Task#:AUTO-518
 * 
 * @author eliang
 * @Date  Jun 20, 2011
 */
public class CheckVendorType extends LicenseManagerTestCase{
	LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
	List<String> list = new ArrayList<String>();
	
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		this.gotoAddVendorPage();
		
		this.VerifyVendorTypeWithoutBlank();

		lm.logOutLicenseManager();
	}

	public void wrapParameters(Object[] param) {
		//login information
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
	}
	
	public void gotoAddVendorPage(){
		vendorSearchPg.clickAddVendor();
		ajax.waitLoading();
		addVendorPg.waitLoading();
	}
	
	public void VerifyVendorTypeWithoutBlank(){
		list = addVendorPg.getVendorTypes();
		
		if(null ==list.get(0) || list.get(0).length()==0){
			throw new ErrorOnDataException("The blank record shouldn't be shown in the Vendor Type list.");
		}
	}
}
