package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.vendor.edit;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Verify error message when edit vendor's financial config info.
 * @Preconditions:
 * Feature CreateModifyVendorFinancialConfig should be assigned.
 * @SPEC: Edit Vendor Financial Information [TC:004225]
 * @Task#:Auto-1595
 * 
 * @author nding1
 * @Date  Aug 12 2013

 */
public class EditFinancialConfig_ErrorMessage extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorFinConfigInfo financialInfo = new LicMgrVendorFinConfigInfo();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	private String eftTypeMsg, invoiceFreMsg, invoiceScheduleMsg, failedEFTEnforcement, voidRtnDays1, voidRtnDays2, noNotificationMail;
	
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		lm.searchVendorInfoByVendorName(vendor.name);
		if(!vendorSearchPg.checkVendorInfoIsExistInListPg(vendor.number)){
			lm.addVendor(vendor);
		}
		
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);

		boolean result = true;
		
		// 1. EFT Type is blank
		financialInfo.eftType = StringUtil.EMPTY;
		String actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---EFT Type is blank", eftTypeMsg, actualMsg);
		
		// 2. Invoice Frequency is blank
		financialInfo.eftType = "EFT";
		financialInfo.invoiceFrequency = StringUtil.EMPTY;
		financialInfo.invoiceSchedule = StringUtil.EMPTY;
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---EFT Frequency is blank", invoiceFreMsg, actualMsg);
		
		// 3. Invoice Schedule is blank
		financialInfo.invoiceFrequency = "Daily";
		financialInfo.invoiceSchedule = StringUtil.EMPTY;
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---EFT Schedule is blank", invoiceScheduleMsg, actualMsg);
		
		// 4. Failed EFT Enforcement is blank
		financialInfo.invoiceSchedule = "Daily";
		financialInfo.failedEFTEnforcement = StringUtil.EMPTY;
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---Failed EFT Enforcement is blank", failedEFTEnforcement, actualMsg);
		
		// 5. Void Return Charge Days is blank
		financialInfo.failedEFTEnforcement = "Revoke After 2nd EFT Failure";
		financialInfo.voidReturnChargeDays = StringUtil.EMPTY;
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---Void Return Charge Days is blank", voidRtnDays1, actualMsg);
		
		// 6. Void Return Charge Days is zero
		financialInfo.voidReturnChargeDays = "0";
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---Void Return Charge Days is zero", voidRtnDays2, actualMsg);

		// 7. select report notification, but not set email
		financialInfo.voidReturnChargeDays = "2";
		financialInfo.rptNotification.put("Daily Sales Activity", true);
		financialInfo.reportEmails = new ArrayList<String>();
		actualMsg = editAndGetErrMsg();
		result = MiscFunctions.compareResult("---Not set nofification mail", noNotificationMail, actualMsg);
		
		if(!result){
			throw new ErrorOnPageException("---Errors: Check logs above!");
		}
		lm.logOutLicenseManager();
	}
	
	private String editAndGetErrMsg(){
		LicMgrVendorFinConfigSubPage finConfigPg = LicMgrVendorFinConfigSubPage.getInstance();
		
		logger.info("Edit financial config info.");
		lm.updateVendorFinancialConfigInfo(vendor.number, financialInfo);
		String actualMsg = finConfigPg.getErrorMessage();
		return actualMsg;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vendor.number = "EditVendorFinancialConfigInfo";
		vendor.name = "Edit Vendor Financial Config Info";
		vendor.appReceivedDate = DateFunctions.getDateAfterToday(-1);
		vendor.appName = "Auto Vendor";
		vendor.appPhone = "3342880375";
		vendor.requestStoreNum = "10";
		vendor.requestStoreEquipmentNum = "100";
		vendor.ownerName = "AutoTest";
		vendor.phyAddress = "P.O. Box 110001";
		vendor.phyCityTown = "Montgomery";
		vendor.phyStateProvince = "Alabama";
		vendor.phyCountry = "United States";
		vendor.phyCounty = "Montgomery";
		vendor.phyZipPostal = "36191-0001";
		
		Contacts contacts = new Contacts();
		contacts.contactType = "Business Mgr";
		contacts.firstName = "QA";
		contacts.lastName = "Test";
		contacts.businessPhone = "3342880375";
		
		vendor.contacts = new ArrayList<Contacts>();
		vendor.contacts.add(contacts);

		financialInfo.eftType = "EFT";	
		financialInfo.invoiceFrequency = "Daily";
		financialInfo.invoiceSchedule = "Daily";
		financialInfo.failedEFTEnforcement = "Revoke After 2nd EFT Failure";
		financialInfo.voidReturnChargeDays = "2";
		financialInfo.autoReturnVoidedDoc = "Yes";
		vendor.finConfigInfo = financialInfo;
		
		// error message info
		eftTypeMsg = "The EFT Type is required. Please select the EFT Type from the list provided.";
		invoiceFreMsg = "The Invoice Schedule is required. Please select the Invoice Schedule from the list provided";
		invoiceScheduleMsg = "The Invoice Schedule is required. Please select the Invoice Schedule from the list provided";
		failedEFTEnforcement = "The Failed EFT Deactivation is required. Please select the Failed EFT Deactivation from the list provided";
		voidRtnDays1 = "The Void Document Return Days is required. Please specify your entry.";
		voidRtnDays2 = "The Void Document Return Days entered is not valid. Please enter an integer value greater than 0.";
		noNotificationMail = "The Email Address for report notification is required. Please specify at least one Email Address.";
	}
}
