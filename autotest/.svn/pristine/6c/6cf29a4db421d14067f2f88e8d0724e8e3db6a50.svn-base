package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.configuration.system.vendorStatusReason;

import com.activenetwork.qa.awo.datacollection.legacy.orms.StatusReasonInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system.LicMgrVendorStatusReasonMgtConfigurationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to add vendor status reason
 * @Preconditions:
 * @SPEC:Add Vendor Status Reason.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Dec 27, 2011
 */
public class AddStatusReason extends LicenseManagerTestCase{
	private StatusReasonInfo statusReason = new StatusReasonInfo();
	private LicMgrVendorStatusReasonMgtConfigurationPage vendorStatusReasonConfigPg = LicMgrVendorStatusReasonMgtConfigurationPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoSysConfPgFromTopMenu();
		lm.gotoVendorStatusReasonPgFromSysConfigPg();
		
		//check vendor status reason info whether exists, if exists will delete record
		this.checkAndDelectStatusReason(statusReason.status, statusReason.reason, schema);
		
		//add vendor status reason
		statusReason.id = lm.addVendorStatusReason(statusReason.status, statusReason.reason);
		
		//verify vendor status reason
		vendorStatusReasonConfigPg.verifyVendorStatusReasonInfo(statusReason);
		
		//clear environment
		lm.deleteVendorStatusReasonFromDB(statusReason.id, schema);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		statusReason.status = "Inactive";
		statusReason.reason = "Auto Test Inactive";
		statusReason.systemStatusReason = "No";
		statusReason.applicationCheckID = "";
		statusReason.creationUser = "Test-Auto, QA-Auto";
		statusReason.creationLocation = login.location.split("/")[1];
		statusReason.creationDateTime = DateFunctions.getToday();
	}
	
	private void checkAndDelectStatusReason(String status, String reason, String schema){
		logger.info("Check and delect status reason.");
		String id = vendorStatusReasonConfigPg.getStatusReasonID(status, reason);
		if(id.trim().length()!=0){
			logger.info("There is already an vendor status reason " + id 
					+ " which satus is " + status + ", reason is " + reason);
			lm.deleteVendorStatusReasonFromDB(id, schema);
			lm.gotoVendorStatusReasonPgFromSysConfigPg();
		}		
	}
}
