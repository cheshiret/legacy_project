/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.support.licensemgr;
import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAddVendorPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**  
 * @Description:  
 * @Preconditions:  
 * @SPEC:  
 * @Task#: 
 * @author jwang8  
 * @Date  Feb 17, 2012    
 */
public class AddVendor extends SupportCase{
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private VendorInfo vendorInfo = new VendorInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrVendorSearchListPage vendorSearchPg = LicMgrVendorSearchListPage.getInstance();
	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn ) || (loggedIn && !vendorSearchPg.exists())){
			lm.loginLicenseManager(login);
			loggedIn=true;
			lm.gotoVendorSearchPg();
		}
		//add vendor.
		lm.addVendor(vendorInfo);
		this.verifyResult();
		
		contract=login.contract;
	}

	@Override
	public void wrapParameters(Object[] param) {
		cursor = 0;
		startpoint = 8; // the start point in the data pool
		endpoint = 8; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VendorName";
		logMsg[2] = "VendorNumber";
		logMsg[3] = "Result";
	}
	
	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
//		vendorInfo.appReceivedDate = dpIter.dpString("receivedDate");
		vendorInfo.appReceivedDate = "";
		if(vendorInfo.appReceivedDate.trim().length() < 1) {
			vendorInfo.appReceivedDate = DateFunctions.getDateAfterToday(-10);
		}
		vendorInfo.appName = dpIter.dpString("applicationName");
		vendorInfo.appPhone = dpIter.dpString("applicationPhone");
		vendorInfo.appEmail = dpIter.dpString("applicationEmail");
		vendorInfo.requestStoreNum = dpIter.dpString("agentsRequested");
		vendorInfo.requestStoreEquipmentNum = dpIter.dpString("equipAgentRequested");
		vendorInfo.number = dpIter.dpString("vendorNum");
		vendorInfo.name = dpIter.dpString("vendorName");
		vendorInfo.ownerName = dpIter.dpString("ownerName");
		vendorInfo.vendorType = dpIter.dpString("vendorType");
		vendorInfo.taxID = dpIter.dpString("taxId");
		vendorInfo.taxIDType = dpIter.dpString("taxIdType");
		vendorInfo.phyAddress = dpIter.dpString("pAddress");
		vendorInfo.phySuppAddress = dpIter.dpString("pSuppleAddress");
		vendorInfo.phyCityTown = dpIter.dpString("pCity");
		vendorInfo.phyStateProvince = dpIter.dpString("pState");
		vendorInfo.phyCounty = dpIter.dpString("pCounty");
		vendorInfo.phyZipPostal = dpIter.dpString("pZip");
		vendorInfo.phyCountry = dpIter.dpString("pCountry");
		vendorInfo.isValidatePhysicalAdd = dpIter.dpBoolean("isValidatePhysicalAddress");
		vendorInfo.isMailingAddSameAsPhysicalAdd = dpIter.dpBoolean("isMailingAddressSameAsPhysicalAddress");
		vendorInfo.mailingAddress = dpIter.dpString("mAddress");
		vendorInfo.mailingSuppAddress = dpIter.dpString("mSuppleAddress");
		vendorInfo.mailingCityTown = dpIter.dpString("mCity");
		vendorInfo.mailingStateProvince = dpIter.dpString("mState");
		vendorInfo.mailingCounty = dpIter.dpString("mCounty");
		vendorInfo.mailingZipPostal = dpIter.dpString("mZip");
		vendorInfo.mailingCountry = dpIter.dpString("mCountry");
		vendorInfo.isValidateMailingAdd = dpIter.dpBoolean("isValidateMailingAddress");
		
		vendorInfo.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = dpIter.dpString("contactType");
		contact.firstName = dpIter.dpString("fName");
		contact.midName = dpIter.dpString("mName");
		contact.lastName = dpIter.dpString("lName");
		contact.suffix = dpIter.dpString("suffix");
		contact.businessPhone = dpIter.dpString("bPhone");
		contact.homePhone = dpIter.dpString("hPhone");
		contact.mobilePhone = dpIter.dpString("mPhone");
		contact.fax = dpIter.dpString("fax");
		contact.email = dpIter.dpString("email");
		contact.website = dpIter.dpString("website");
		
		vendorInfo.contacts.add(contact);
		
		vendorInfo.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = dpIter.dpBoolean("isCreditPassCheck");
		appStatusCheck.statusCheck = dpIter.dpString("creditStatusCheck");
		appStatusCheck.dateCompleted = dpIter.dpString("creditDateCompleted");
		appStatusCheck.completedBy = dpIter.dpString("creditCompletedBy");
		appStatusCheck.comments = dpIter.dpString("creditComments");
		
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = dpIter.dpBoolean("isOwnerPassCheck");
		appStatusCheck1.statusCheck = dpIter.dpString("ownerStatusCheck");
		appStatusCheck1.dateCompleted = dpIter.dpString("ownerDateCompleted");
		appStatusCheck1.completedBy = dpIter.dpString("ownerCompletedBy");
		appStatusCheck1.comments = dpIter.dpString("ownerComments");
		
		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked = dpIter.dpBoolean("isLawStatusCheck");
		appStatusCheck2.statusCheck = dpIter.dpString("lawStatusCheck");
		appStatusCheck2.dateCompleted = dpIter.dpString("lawDateCompleted");
		appStatusCheck2.completedBy = dpIter.dpString("lawCompletedBy");
		appStatusCheck2.comments = dpIter.dpString("lawComments");
		
		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked = dpIter.dpBoolean("isBackgroundPassCheck");
		appStatusCheck3.statusCheck = dpIter.dpString("backgroundEnforceStatusCheck");
		appStatusCheck3.dateCompleted = dpIter.dpString("backgroundDateCompleted");
		appStatusCheck3.completedBy = dpIter.dpString("backgroundCompletedBy");
		appStatusCheck3.comments = dpIter.dpString("backgroundComments");
		vendorInfo.applicationStatusCheck.add(appStatusCheck);
		vendorInfo.applicationStatusCheck.add(appStatusCheck1);
		vendorInfo.applicationStatusCheck.add(appStatusCheck2);
		vendorInfo.applicationStatusCheck.add(appStatusCheck3);	
		
		vendorInfo.specifyDefault = dpIter.dpString("specifyDefault");
		vendorInfo.isFillValues = dpIter.dpBoolean("isFillValue");
		vendorInfo.finConfigInfo.eftType = dpIter.dpString("eftType");
		vendorInfo.finConfigInfo.invoiceSchedule = dpIter.dpString("eftSchedule");
		vendorInfo.finConfigInfo.invoiceFrequency = dpIter.dpString("eftSchedule");
		vendorInfo.finConfigInfo.failedEFTEnforcement = dpIter.dpString("failedEftEnfore");
		vendorInfo.finConfigInfo.voidReturnChargeDays = dpIter.dpString("voidDays");
		vendorInfo.finConfigInfo.autoReturnVoidedDoc = dpIter.dpString("autoReturnVoid");
		vendorInfo.finConfigInfo.rptNotification.put(dpIter.dpString("reportNotification"), dpIter.dpBoolean("reportNotifiValue"));
		vendorInfo.finConfigInfo.reportEmails = new ArrayList<String>();
		vendorInfo.finConfigInfo.reportEmails.add(dpIter.dpString("reportEmail"));
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = vendorInfo.name;
		logMsg[2] = vendorInfo.number;
	}
	
	private void verifyResult(){
		LicMgrVendorAddVendorPage addVendorPg = LicMgrVendorAddVendorPage.getInstance();
		if(!vendorSearchPg.exists()){
				logger.error("Create privilege product failed: Vendor Number = " + vendorInfo.number + ", Vendor Name = " + vendorInfo.name + ", And failed reason: " + addVendorPg.getWarningMessage());
				logMsg[3] = "Failed";
		}else{
			logMsg[3] = "Passed";
		}
	}
}
