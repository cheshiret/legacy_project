/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.edit;

import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @Description:Edit Vendor financial info - change EFT schedule to Invoice schedule
 * @Preconditions:
 * @SPEC:TC:053109
 * @Task#:Auto-1453
 * 
 * @author ssong
 * @Date  Apr 9, 2013
 */
public class EditFinancialConfig_InvoiceSchedule extends LicenseManagerTestCase{

	private String vendorNum,expect_msg;
	
	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#execute()
	 */
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorFinConfigSubPage();
		this.checkInvoiceSchedule();
		
		lm.logOutLicenseManager();		
	}
	
	private void checkInvoiceSchedule(){
		LicMgrVendorFinConfigSubPage finConfigPg = LicMgrVendorFinConfigSubPage
				.getInstance();
		
		if(!finConfigPg.checkInvoiceScheduleLableExists()){
			throw new ErrorOnPageException("Invoice Schedule Lable Not exist in the page.");
		}
		
		finConfigPg.selectBlankInvoiceSchedule();
		ajax.waitLoading();
		finConfigPg.waitLoading();
		finConfigPg.clickSave();
		ajax.waitLoading();
		finConfigPg.waitLoading();
		String msg = finConfigPg.getErrorMessage();
		if(!msg.equalsIgnoreCase(expect_msg)){
			throw new ErrorOnPageException("Error msg not correct",expect_msg,msg);
		}
	}

	/* (non-Javadoc)
	 * @see testAPI.abstractcases.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendorNum = "480";//production exist vendor which is closed
		expect_msg  = "The Invoice Schedule is required. Please select the Invoice Schedule from the list provided";
	}

}
