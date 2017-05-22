/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;
import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddVendorFunction;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

/**  
 * @Description:  
 * @Preconditions:  
 * @SPEC:  
 * @Task#: 
 * @author jwang8  
 * @Date  Feb 17, 2012    
 */
public class AddVendor extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private VendorInfo vendorInfo = new VendorInfo();
	private AddVendorFunction addVendorFunc = new AddVendorFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = vendorInfo;
		addVendorFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_vendor";
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
//		vendorInfo.appReceivedDate = datasFromDB.get("receivedDate");
		vendorInfo.appReceivedDate = "";
		if(vendorInfo.appReceivedDate.trim().length() < 1) {
			vendorInfo.appReceivedDate = DateFunctions.getDateAfterToday(-10);
		}
		vendorInfo.appName = datasFromDB.get("applicationName");
		vendorInfo.appPhone = datasFromDB.get("applicationPhone");
		vendorInfo.appEmail = datasFromDB.get("applicationEmail");
		vendorInfo.requestStoreNum = datasFromDB.get("agentsRequested");
		vendorInfo.requestStoreEquipmentNum = datasFromDB.get("equipAgentRequested");
		vendorInfo.number = datasFromDB.get("vendorNum");
		vendorInfo.name = datasFromDB.get("vendorName");
		vendorInfo.ownerName = datasFromDB.get("ownerName");
		vendorInfo.vendorType = datasFromDB.get("vendorType");
		vendorInfo.taxID = datasFromDB.get("taxId");
		vendorInfo.taxIDType = datasFromDB.get("taxIdType");
		vendorInfo.phyAddress = datasFromDB.get("pAddress");
		vendorInfo.phySuppAddress = datasFromDB.get("pSuppleAddress");
		vendorInfo.phyCityTown = datasFromDB.get("pCity");
		vendorInfo.phyStateProvince = datasFromDB.get("pState");
		vendorInfo.phyCounty = datasFromDB.get("pCounty");
		vendorInfo.phyZipPostal = datasFromDB.get("pZip");
		vendorInfo.phyCountry = datasFromDB.get("pCountry");
		vendorInfo.isValidatePhysicalAdd = Boolean.parseBoolean(datasFromDB.get("isValidatePhysicalAddress"));
		vendorInfo.isMailingAddSameAsPhysicalAdd = Boolean.parseBoolean(datasFromDB.get("isMailingAddrSameAsPhyAddr"));
		vendorInfo.mailingAddress = datasFromDB.get("mAddress");
		vendorInfo.mailingSuppAddress = datasFromDB.get("mSuppleAddress");
		vendorInfo.mailingCityTown = datasFromDB.get("mCity");
		vendorInfo.mailingStateProvince = datasFromDB.get("mState");
		vendorInfo.mailingCounty = datasFromDB.get("mCounty");
		vendorInfo.mailingZipPostal = datasFromDB.get("mZip");
		vendorInfo.mailingCountry = datasFromDB.get("mCountry");
		vendorInfo.isValidateMailingAdd = Boolean.parseBoolean(datasFromDB.get("isValidateMailingAddress"));
		
		vendorInfo.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = datasFromDB.get("contactType");
		contact.firstName = datasFromDB.get("fName");
		contact.midName = datasFromDB.get("mName");
		contact.lastName = datasFromDB.get("lName");
		contact.suffix = datasFromDB.get("suffix");
		contact.businessPhone = datasFromDB.get("bPhone");
		contact.homePhone = datasFromDB.get("hPhone");
		contact.mobilePhone = datasFromDB.get("mPhone");
		contact.fax = datasFromDB.get("fax");
		contact.email = datasFromDB.get("email");
		contact.website = datasFromDB.get("website");
		//Added for check result
		contact.isPrimary = true;
		
		vendorInfo.contacts.add(contact);
		
		vendorInfo.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = Boolean.parseBoolean(datasFromDB.get("isCreditPassCheck"));
		appStatusCheck.statusCheck = datasFromDB.get("creditStatusCheck");
		appStatusCheck.dateCompleted = datasFromDB.get("creditDateCompleted");
		appStatusCheck.completedBy = datasFromDB.get("creditCompletedBy");
		appStatusCheck.comments = datasFromDB.get("creditComments");
		
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked =  Boolean.parseBoolean(datasFromDB.get("isOwnerPassCheck"));
		appStatusCheck1.statusCheck = datasFromDB.get("ownerStatusCheck");
		appStatusCheck1.dateCompleted = datasFromDB.get("ownerDateCompleted");
		appStatusCheck1.completedBy = datasFromDB.get("ownerCompletedBy");
		appStatusCheck1.comments = datasFromDB.get("ownerComments");
		
		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked =  Boolean.parseBoolean(datasFromDB.get("isLawStatusCheck"));
		appStatusCheck2.statusCheck = datasFromDB.get("lawStatusCheck");
		appStatusCheck2.dateCompleted = datasFromDB.get("lawDateCompleted");
		appStatusCheck2.completedBy = datasFromDB.get("lawCompletedBy");
		appStatusCheck2.comments = datasFromDB.get("lawComments");
		
		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked =  Boolean.parseBoolean(datasFromDB.get("isBackgroundPassCheck"));
		appStatusCheck3.statusCheck = datasFromDB.get("backgroundEnforceStatusCheck");
		appStatusCheck3.dateCompleted = datasFromDB.get("backgroundDateCompleted");
		appStatusCheck3.completedBy = datasFromDB.get("backgroundCompletedBy");
		appStatusCheck3.comments = datasFromDB.get("backgroundComments");
		vendorInfo.applicationStatusCheck.add(appStatusCheck);
		vendorInfo.applicationStatusCheck.add(appStatusCheck1);
		vendorInfo.applicationStatusCheck.add(appStatusCheck2);
		vendorInfo.applicationStatusCheck.add(appStatusCheck3);	
		
		vendorInfo.specifyDefault = datasFromDB.get("specifyDefault");
		vendorInfo.isFillValues =  Boolean.parseBoolean(datasFromDB.get("isFillValue"));
		vendorInfo.finConfigInfo.eftType = datasFromDB.get("eftType");
		vendorInfo.finConfigInfo.invoiceFrequency = datasFromDB.get("eftSchedule");//should add a column 'invoice frequency' in data table
		vendorInfo.finConfigInfo.invoiceSchedule = datasFromDB.get("eftSchedule");
		vendorInfo.finConfigInfo.failedEFTEnforcement = datasFromDB.get("failedEftEnfore");
		vendorInfo.finConfigInfo.voidReturnChargeDays = datasFromDB.get("voidDays");
		vendorInfo.finConfigInfo.autoReturnVoidedDoc = datasFromDB.get("autoReturnVoid");
		String reportNotificationStr = datasFromDB.get("reportNotification").trim();
		if(reportNotificationStr.length() > 0) {
			String notificationLabels[] = StringUtil.splitByComma(reportNotificationStr);
			String notificationBooleanValues[] = StringUtil.splitByComma(datasFromDB.get("reportNotifiValue").trim());
			if(notificationLabels.length != notificationBooleanValues.length) {
				throw new ErrorOnDataException("Report notification Name-Value length doesn't match.");
			}
			for(int i = 0; i < notificationLabels.length; i ++) {
				vendorInfo.finConfigInfo.rptNotification.put(notificationLabels[i].trim(), Boolean.valueOf(notificationBooleanValues[i].trim()));
			}
		}
		String reportEmailStr = datasFromDB.get("reportEmail").trim();
		if(reportEmailStr.length() > 0) {
			String reportEmails[] = StringUtil.splitByComma(reportEmailStr);
			for(int i = 0; i < reportEmails.length; i ++) {
				vendorInfo.finConfigInfo.reportEmails.add(reportEmails[i]);
			}
		}
	}
}
