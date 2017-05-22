package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicenseMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

public class AddVendorFunction extends FunctionCase{
	LoginInfo login;
	private LicenseManager lm = LicenseManager.getInstance();
	private VendorInfo vendorInfo = new VendorInfo();
//	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private boolean loggedin=false;
	private String contract = "";
	private LicenseMgrHomePage homePg = LicenseMgrHomePage.getInstance();
	private String location;
	
 
	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login = (LoginInfo)param[0];
		vendorInfo = (VendorInfo)param[1];
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
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
		lm.gotoVendorSearchPg();
		//add vendor.
		lm.addVendor(vendorInfo);
		this.verifyResult();
		newAddValue = vendorInfo.number;
	
}
	
	private void verifyResult(){
//		LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
//		LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();
//		LicMgrVendorFinConfigSubPage vendorFinConfigPg = LicMgrVendorFinConfigSubPage.getInstance();
//		LicMgrVendorDetailAddAndContractsPage vendorAddressAndContactPg = LicMgrVendorDetailAddAndContractsPage.getInstance();
//		LicMgrVendorApplicationPage vendorApplicationPg = LicMgrVendorApplicationPage.getInstance();
//		boolean passed = true;
//		vendorInfo.status = OrmsConstants.ACTIVE_STATUS;
//		vendorInfo.vendorCreationUser = TestProperty.getProperty("orms.fm.user");
//		vendorInfo.vendorCreationDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(TestProperty.getProperty(env + ".db.schema.prefix") + "MS"));
//		vendorInfo.phyAddStatus = "Zip Only";
//		vendorInfo.appCreationDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(TestProperty.getProperty(env + ".db.schema.prefix") + "MS"));
//		if(vendorInfo.isMailingAddSameAsPhysicalAdd){
//			vendorInfo.mailingAddress = vendorInfo.phyAddress;
//			vendorInfo.mailingSuppAddress = vendorInfo.phySuppAddress;
//			vendorInfo.mailingCityTown = vendorInfo.phyCityTown;
//			vendorInfo.mailingStateProvince = vendorInfo.phyStateProvince;
//			vendorInfo.mailingCounty = vendorInfo.phyCounty;
//			vendorInfo.mailingZipPostal = vendorInfo.phyZipPostal;
//			vendorInfo.mailingCountry = vendorInfo.phyCountry;
//			vendorInfo.mailingAddStatus = "Zip Only";
//		}
//		
//		if(!vendorSearchPg.exists()){
//				logger.error("[FAILED]Create privilege product failed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name + ", And failed reason: " + addVendorPg.getWarningMessage());
//		}else{
//			lm.gotoVendorDetailPgFromVendorSearchPg(vendorInfo.number);
//			//Verify the created vendor info
//			passed &= vendorDetailPg.compareVendorBasicInfo(vendorInfo);
//			passed &= vendorAddressAndContactPg.compareVendorAddressAndContactInfo(vendorInfo);
//			lm.gotoVendorFinConfigSubPage();
//			passed &= vendorFinConfigPg.compareFinancialInfo(vendorInfo.finConfigInfo);
//			lm.gotoVendorApplicationPgFromVendorDetailPg();
//			passed &= vendorApplicationPg.compareVendorApplicationInfo(vendorInfo);
//			lm.gotoVendorDetialPgFromVendorApplicationPg();		
//			if(!passed)
//			{
//				logger.error("[FAILED]Create privilege product failed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name + ", And failed reason: information added not correct, please check above log!" );
//			}else{
//				logger.info("[PASSED]Create privilege product passed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name);
//				}
//		}
//		if(!passed){
//			throw new ErrorOnPageException("Vendor added failed, please see the log above!");
//		}
//		//For both failed and passed cases all need to go back to the search list page 
//		vendorDetailPg.clickCancel();
//		vendorSearchPg.waitExists();
		
		String schema = TestProperty.getProperty(env + ".db.schema.prefix") + contract.replace("Contract", "").trim();
		String vendorID=lm.getVendorID(schema, vendorInfo.number, vendorInfo.name);
		if(StringUtil.isEmpty(vendorID))
			logger.error("[FAILED]Create privilege product failed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name);
		
		logger.info("[PASSED]Create privilege product passed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name+", ID = "+vendorID);
		
		//Go back to vendor agent page for both failed and passed cases
		lm.gotoHomePage();
	}

}
