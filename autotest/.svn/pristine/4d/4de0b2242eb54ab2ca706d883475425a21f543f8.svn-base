package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorFinConfigSubPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:Add a new Vendor, then edit its financial config info.
 * @Preconditions:
 * Feature CreateModifyVendorFinancialConfig should be assigned.
 * @SPEC:Edit Vendor Financial Info.doc & View Vendor Financial Info Change History.doc
 * Edit Vendor Financial Information [TC:004225
 * @Task#:Auto-774
 * 
 * @author nding1
 * @Date  2011-12-15
 */
public class EditVendorFinancialInfo extends LicenseManagerTestCase {
	public LicMgrVendorFinConfigInfo financialInfo1 = new LicMgrVendorFinConfigInfo();
	public LicMgrVendorFinConfigInfo financialInfo2 = new LicMgrVendorFinConfigInfo();
	public VendorInfo vendor = new VendorInfo();
	public int changeNums=0;
	
	public void execute() {
		lm.loginLicenseManager(login);

		// add new Vendor
		lm.gotoVendorSearchPg();
		lm.addVendor(vendor);
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		
		// Edit and verify Financial Config Info
		this.editFinancialConfigInfo();
		
		// clean up vendor
		this.deleteVendor(vendor.number);
		lm.logOutLicenseManager();
	}
	
	private void deleteVendor(String vendorNum){
		AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
		String sql = "update d_vendor set deleted = 1  where vendor_num like '"+vendorNum+"%'";
		db.resetSchema(schema);
		db.executeUpdate(sql);
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		
		vendor.number = "AddVendorForEditFinancialInfo"+DateFunctions.getCurrentTime();
		vendor.name = "Add Vendor For Edit Financial Info";
		vendor.appReceivedDate = DateFunctions.getDateAfterToday(-1);;
		vendor.appName = "Auto Vendor";
		vendor.appPhone = "3342880375";
		vendor.requestStoreNum = "10";
		vendor.requestStoreEquipmentNum = "100";
		vendor.ownerName = "AutoTest";
		vendor.phyCountry = "United States";
		vendor.phyAddress = "P.O. Box 110001";
		vendor.phyCityTown = "Montgomery";
		vendor.phyStateProvince = "Alabama";
		vendor.phyCounty = "Montgomery";
		vendor.phyZipPostal = "36191-0001";
		
		Contacts contacts = new Contacts();
		contacts.contactType = "Business Mgr";
		contacts.firstName = "QA";
		contacts.lastName = "Test";
		contacts.businessPhone = "3342880375";
		
		vendor.contacts = new ArrayList<Contacts>();
		vendor.contacts.add(contacts);

		financialInfo1.eftType = "EFT";	
		financialInfo1.invoiceFrequency = "Daily";
		financialInfo1.invoiceSchedule = "Daily";
		financialInfo1.failedEFTEnforcement = "Revoke After 2nd EFT Failure";
		financialInfo1.voidReturnChargeDays = "2";
		financialInfo1.autoReturnVoidedDoc = "Yes";
		financialInfo1.rptNotification.put("Daily Sales Activity", true);
		financialInfo1.rptNotification.put("EFT Invoice", true);
		financialInfo1.reportEmails = new ArrayList<String>();
		for(int i = 0; i < 1; i++) {
			financialInfo1.reportEmails.add(lm.getNextEmail());
		}
		vendor.finConfigInfo = financialInfo1;
	}

	/**
	 * set up financial config info for edit.
	 */

	private void setUpFinancialConfigInfoForEdit(int mailLength) {
		logger.info("Set up financial config info for edit.");
		LicMgrVendorFinConfigSubPage finConfigPg = LicMgrVendorFinConfigSubPage.getInstance();
		lm.gotoVendorFinConfigSubPage();
		
		financialInfo2.voidReturnChargeDays = "5";
		financialInfo2.invoiceFrequency = "Daily";
		financialInfo2.invoiceSchedule = "Daily";
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
	
		financialInfo2.eftType = "EDI";
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
		
		financialInfo2.invoiceFrequency = "Weekly";
		financialInfo2.invoiceSchedule = financialInfo2.invoiceFrequency;
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
		
		financialInfo2.failedEFTEnforcement = "Revoke After 3rd EFT Failure";
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
		
		financialInfo2.autoReturnVoidedDoc = "No";
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
	
		financialInfo2.rptNotification.put("Daily Sales Activity", true);
		financialInfo2.rptNotification.put("EFT Invoice", false);
		financialInfo2.reportEmails = new ArrayList<String>();
		for(int i = 0; i < mailLength; i++) {
			financialInfo2.reportEmails.add(lm.getNextEmail());
		}
		finConfigPg.editFinancialConfigInfo(financialInfo2);
		finConfigPg.clickSave();
		ajax.waitLoading();
	}
	
	/**
	 * Verify the change history of vendor financial info.
	 * @param changeLog1
	 */
	private void verifyChangeHistory(int changeLog1) {
		logger.info("Vefify the change history of vendor finicial info.");
		lm.gotoVendorChangeHistoryPg();
		LicMgrVendorViewChangeHistoryPage vendorChangeHistoryPg = LicMgrVendorViewChangeHistoryPage.getInstance();
		IHtmlTable table = vendorChangeHistoryPg.getChangeHistoryInfo();
		int changeLog2 = table.rowCount();

		// set up the expect change history list.
		HashMap<String, List<String>> changeHistorylist = getChangeHistoryForVerify(financialInfo1, financialInfo2);
		
		// verify number of change history
		if(!MiscFunctions.compareResult("Number of change history", changeLog2-changeLog1, changeNums)) {
			throw new ErrorOnPageException("The # of change history is wrong! For this time of changing, there are " + changeNums +" new records should be displayed.");
		}
		
		List<List<String>> changeHistoryFromPage = new ArrayList<List<String>>();
		for(int i = 0; i < changeNums; i++) {
			
			// get log except title
			changeHistoryFromPage.add(table.getRowValues(i+1));
		}

		LicMgrVendorViewChangeHistoryPage changeHistoryPg = LicMgrVendorViewChangeHistoryPage.getInstance();
		changeHistoryPg.verifyChangeHistory(changeHistorylist, changeHistoryFromPage);
		
	}
	
	/**
	 * set up the expect change history list.
	 * 
	 * @param financialInfoBefore Old Info
	 * @param financialInfoAfter New Info
	 * @return expect change history list
	 */
	private HashMap<String, List<String>> getChangeHistoryForVerify(LicMgrVendorFinConfigInfo financialInfoBefore, LicMgrVendorFinConfigInfo financialInfoAfter) {

		HashMap<String, List<String>> changeHistorylist = new HashMap<String, List<String>>();
		List<String> changeHistory = new ArrayList<String>();

		if(!financialInfoBefore.reportEmails.equals(financialInfoAfter.reportEmails)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("Notification Emails");
			changeHistory.add(setNotificationEmailsInLog(financialInfoBefore.reportEmails));
			changeHistory.add(setNotificationEmailsInLog(financialInfoAfter.reportEmails));
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}

		if(!financialInfoBefore.rptNotification.equals(financialInfoAfter.rptNotification)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("Notification Name");
			changeHistory.add(setNotificationNameInLog(financialInfoBefore.rptNotification));
			changeHistory.add(setNotificationNameInLog(financialInfoAfter.rptNotification));
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}

		if(!financialInfoBefore.failedEFTEnforcement.equals(financialInfoAfter.failedEFTEnforcement)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("Failed EFT Enforcement");
			changeHistory.add(financialInfoBefore.failedEFTEnforcement);
			changeHistory.add(financialInfoAfter.failedEFTEnforcement);
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}
		
		if(!financialInfoBefore.eftType.equals(financialInfoAfter.eftType)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("EFT Type");
			changeHistory.add(financialInfoBefore.eftType);
			changeHistory.add(financialInfoAfter.eftType);
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}
		
		if(!financialInfoBefore.autoReturnVoidedDoc.equals(financialInfoAfter.autoReturnVoidedDoc)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("Auto Return Voided Documents");
			changeHistory.add(setAutoReturnVoidedDocInLog(financialInfoBefore.autoReturnVoidedDoc));
			changeHistory.add(setAutoReturnVoidedDocInLog(financialInfoAfter.autoReturnVoidedDoc));
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}

		if(!financialInfoBefore.voidReturnChargeDays.equals(financialInfoAfter.voidReturnChargeDays)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("Void Return Charge Days");
			changeHistory.add(financialInfoBefore.voidReturnChargeDays);
			changeHistory.add(financialInfoAfter.voidReturnChargeDays);
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}

		if(!financialInfoBefore.invoiceSchedule.equals(financialInfoAfter.invoiceSchedule)) {
			changeNums++;
			changeHistory = new ArrayList<String>();
			changeHistory.add("Vendor Financial Config");
			changeHistory.add("Update");
			changeHistory.add("EFT Schedule");
			changeHistory.add(financialInfoBefore.invoiceSchedule);
			changeHistory.add(financialInfoAfter.invoiceSchedule);
			changeHistory.add("Test-Auto, QA-Auto");
			changeHistory.add(login.location.split("/")[1]);
			changeHistorylist.put(changeHistory.get(2), changeHistory);
		}

		return changeHistorylist;
	}
	
	/**
	 * When autoReturnVoidedDoc is Yes, set the value is true;
	 * when autoReturnVoidedDoc is No,  set the value is false.
	 * 
	 * @param autoReturnVoidedDoc
	 * @return Auto Return Voided Documents
	 */
	private String setAutoReturnVoidedDocInLog(String autoReturnVoidedDoc) {
		String autoReturnVoidedDocType = "";
		
		if("Yes".equals(autoReturnVoidedDoc)) {
			autoReturnVoidedDocType = "true";
		} else if("No".equals(autoReturnVoidedDoc)) {
			autoReturnVoidedDocType = "false";
		} else {
			autoReturnVoidedDocType = "";
		}
		
		return autoReturnVoidedDocType;
	}

	/**
	 * When eftInvoice is True, add "EFT Invoice" to the value;
	 * when dailySalesActivity is True, add "Daily Sales Activity" to the value;
	 * if both of them need to be add to the value, use ";" to connect.
	 * 
	 * @param eftInvoice
	 * @param dailySalesActivity
	 * @return Notification Name
	 */
	private String setNotificationNameInLog(HashMap<String, Boolean> rptNotification) {

		String newValue = "";
		for(Entry<String, Boolean> e: rptNotification.entrySet()){
			if(e.getValue()){
				newValue = newValue.concat(e.getKey());
				newValue = newValue.concat(";");
			}
		}
		
		if(newValue.endsWith(";")) {
			newValue = newValue.substring(0, newValue.length()-1);
			
		}
		
		return newValue;
	}
	
	/**
	 * Connect all of the email by ";"
	 * 
	 * @param notificationEmails
	 * @return Notification Email
	 */
	private String setNotificationEmailsInLog(List<String> notificationEmails) {
		String newValue = "";
		if(null != notificationEmails) {

			for(int i = 0; i < notificationEmails.size(); i++) {
				newValue = newValue.concat(notificationEmails.get(i));
				
				if(i != (notificationEmails.size() - 1)) {
					newValue = newValue.concat(";");
				}
			}
		}
		
		return newValue;
	}
	
	/**
	 * Verify the edited financial info.
	 * 
	 */
	private void verifyFinancialInfo() {
		LicMgrVendorFinConfigSubPage finConfigPg = LicMgrVendorFinConfigSubPage.getInstance();
		
		lm.gotoVendorDetailPgFromChageHistoryPg();
		lm.gotoVendorFinConfigSubPage();
		finConfigPg.verifyFinancialInfo(financialInfo2);
	}
	
	/**
	 * Edit Financial Config Info
	 * 
	 */
	private void editFinancialConfigInfo() {
		lm.gotoVendorFinConfigSubPage();
		lm.gotoVendorChangeHistoryPg();
		LicMgrVendorViewChangeHistoryPage vendorChangeHistoryPg = LicMgrVendorViewChangeHistoryPage.getInstance();
		
		// get the change history number which already in the page
		int changeNum1 = vendorChangeHistoryPg.getChangeHistoryInfo().rowCount();
		logger.info("The length of change history is:"+changeNum1+" before edit.");
		lm.gotoVendorDetailPgFromChageHistoryPg();
		
		// set up the financial config info
		setUpFinancialConfigInfoForEdit(1);
		
		// verify change history
		this.verifyChangeHistory(changeNum1);
		
		// verify edit content
		this.verifyFinancialInfo();
	}
}
