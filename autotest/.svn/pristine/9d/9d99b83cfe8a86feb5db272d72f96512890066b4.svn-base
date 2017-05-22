package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorViewChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify vendor change history info.
 * @Preconditions:
 * @SPEC: View Vendor Info Change History.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Dec 27, 2011
 */
public class ViewVendorInfoChangeHistory extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private List<ChangeHistory> historyList = new ArrayList<ChangeHistory>();
	private LicMgrVendorViewChangeHistoryPage vendorChangeHistoryPg = LicMgrVendorViewChangeHistoryPage.getInstance();

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add vendor with default eft info
		lm.addVendor(vendor);
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		
		this.initialUpdateVendorAndExpectHistoryInfo();
		//update vendor name info
		lm.updateVendorBasicInfo(vendor);
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//go to vendor change history page
		lm.gotoVendorChangeHistoryPg();
		//verify history record
		this.verifyHistoryRecordInfo(historyList);	
		lm.gotoVendorDetailPgFromChageHistoryPg();
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		vendor.appReceivedDate = DateFunctions.getToday();
		vendor.appName = "Basic Test Case";
		vendor.appPhone = "9052867777";
		vendor.appEmail = "12345@activenetwork.com";
		vendor.requestStoreNum = "5";
		vendor.requestStoreEquipmentNum = "2";
		
		vendor.number = "Vendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.status = "Active";
		vendor.ownerName = "Basic Test Case";
		vendor.vendorType = "Government";
		
		vendor.taxID = "000000001";
		vendor.taxIDType = "Federal Tax ID";
		
		vendor.phyAddress = "address";
		vendor.phySuppAddress = "address";
		vendor.phyCityTown = "Simi Valley";
		vendor.phyStateProvince = "California";
		vendor.phyCounty = "Ventura";
		vendor.phyZipPostal = "93063";
		vendor.phyCountry = "United States";
		
		vendor.isMailingAddSameAsPhysicalAdd = true;
		
		vendor.contacts = new ArrayList<Contacts>();
		Contacts contact = new Contacts();
		contact.contactType = "Business Mgr";
		contact.isPrimary = false;
		contact.firstName = "Add";
		contact.midName = "Vendor";
		contact.lastName = "Test";
		contact.suffix = "SR";
		contact.businessPhone = "9052867777";
		contact.homePhone ="905286777";
		contact.mobilePhone = "9052867777";
		contact.fax = "9052867777";
		contact.email = "12345@activenetwork.com";
		contact.website = "www.activenetwor";	
		vendor.contacts.add(contact);
		
		vendor.specifyDefault = "Weekly EFT";
		vendor.isFillValues = true;

		vendor.applicationStatusCheck = new ArrayList<ApplicationStatusCheck>();
		ApplicationStatusCheck appStatusCheck = new ApplicationStatusCheck();
		appStatusCheck.byPassChecked = true;
		appStatusCheck.statusCheck = "Credit Check Run";
		appStatusCheck.dateCompleted = "";
		appStatusCheck.completedBy = "";
		appStatusCheck.comments = "";
		
		ApplicationStatusCheck appStatusCheck1 = new ApplicationStatusCheck();
		appStatusCheck1.byPassChecked = true;
		appStatusCheck1.statusCheck = "Owner Suspensions Check";
		appStatusCheck1.dateCompleted = "";
		appStatusCheck1.completedBy = "";
		appStatusCheck1.comments = "";
		
		ApplicationStatusCheck appStatusCheck2 = new ApplicationStatusCheck();
		appStatusCheck2.byPassChecked = true;
		appStatusCheck2.statusCheck = "Law enforcement A check";
		appStatusCheck2.dateCompleted = "";
		appStatusCheck2.completedBy = "";
		appStatusCheck2.comments = "";
		
		ApplicationStatusCheck appStatusCheck3 = new ApplicationStatusCheck();
		appStatusCheck3.byPassChecked = true;
		appStatusCheck3.statusCheck = "Law Enforcement Background";
		appStatusCheck3.dateCompleted = "";
		appStatusCheck3.completedBy = "";
		appStatusCheck3.comments = "";
		vendor.applicationStatusCheck.add(appStatusCheck);
		vendor.applicationStatusCheck.add(appStatusCheck1);
		vendor.applicationStatusCheck.add(appStatusCheck2);
		vendor.applicationStatusCheck.add(appStatusCheck3);				
	}
	
	private void initialUpdateVendorAndExpectHistoryInfo(){	
		ChangeHistory history=new ChangeHistory();
	    history.object = "Vendor";
	    history.action = "Update";
	    history.user = "Test-Auto, QA-Auto";
	    history.location = login.location.split("/")[1].trim();
	    history.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
	    history.field = "Vendor Name";
	    history.oldValue = vendor.name;
		vendor.name = "Vendor" + DateFunctions.getCurrentTime();
		history.newValue = vendor.name;
		
		ChangeHistory history1=new ChangeHistory();
		history1.object = "Vendor";
		history1.action = "Update";
		history1.user = "Test-Auto, QA-Auto";
		history1.location = login.location.split("/")[1].trim();
		history1.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
		history1.field = "Status";
		history1.oldValue = vendor.status;
		vendor.status = "Inactive - Other";
		history1.newValue = vendor.status;
		
		ChangeHistory history2=new ChangeHistory();
		history2.object = "Vendor";
		history2.action = "Update";
		history2.user = "Test-Auto, QA-Auto";
		history2.location = login.location.split("/")[1].trim();
		history2.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
		history2.field = "Owner Name";
		history2.oldValue = vendor.ownerName;
		vendor.ownerName = "Basic Test Case Edit";
		history2.newValue = vendor.ownerName;
		
		ChangeHistory history3=new ChangeHistory();
		history3.object = "Vendor";
		history3.action = "Update";
		history3.user = "Test-Auto, QA-Auto";
		history3.location = login.location.split("/")[1].trim();
		history3.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
		history3.field = "Vendor Type";
		history3.oldValue = vendor.vendorType;
		vendor.vendorType = "Corporation";
		history3.newValue = vendor.vendorType;
		
		ChangeHistory history4=new ChangeHistory();
		history4.object = "Vendor";
		history4.action = "Update";
		history4.user = "Test-Auto, QA-Auto";
		history4.location = login.location.split("/")[1].trim();
		history4.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
		history4.field = "Attribute - Tax ID";
		history4.oldValue = vendor.taxID;
		vendor.taxID = "000000002";
		history4.newValue = vendor.taxID;
		
		ChangeHistory history5=new ChangeHistory();
		history5.object = "Vendor";
		history5.action = "Update";
		history5.user = "Test-Auto, QA-Auto";
		history5.location = login.location.split("/")[1].trim();
		history5.changeDate = DateFunctions.getToday("E MMM d yyyy");		    
		history5.field = "Attribute - Tax ID Type";
		history5.oldValue = vendor.taxIDType;
		vendor.taxIDType = "SSN";
		history5.newValue = vendor.taxIDType;
		historyList.add(history);	
		historyList.add(history1);
		historyList.add(history2);
		historyList.add(history3);
		historyList.add(history4);
		historyList.add(history5);
	}
	
	private void verifyHistoryRecordInfo(List<ChangeHistory> expectHistoryList){
		List<ChangeHistory> actualHistoryListFromUI= new ArrayList<ChangeHistory>();
		
		logger.info("Verify vehicle change history info.");		
		actualHistoryListFromUI = vendorChangeHistoryPg.getHistoriesInformation();
		
		if(actualHistoryListFromUI.size()<expectHistoryList.size()){
			throw new ErrorOnPageException("History List record size is not correct.");
		}
		
		//get actually history list info by expect history list size
		actualHistoryListFromUI = actualHistoryListFromUI.subList(0, expectHistoryList.size());		
		for(int i=0; i<expectHistoryList.size(); i++){
			for(int j=0; i<actualHistoryListFromUI.size(); j++){
				if(expectHistoryList.get(i).field.equals(actualHistoryListFromUI.get(j).field)){
					if(!actualHistoryListFromUI.get(j).equals(expectHistoryList.get(i))){
						throw new ErrorOnPageException("History record about "+ expectHistoryList.get(i).field + " is wrong.");
					}
					break;
				}else {
					if(j == actualHistoryListFromUI.size()-1){
						throw new ErrorOnPageException("History record about "+ expectHistoryList.get(i).field + " should be exists in UI.");
					}
				}
			}
		}	
	}
}
