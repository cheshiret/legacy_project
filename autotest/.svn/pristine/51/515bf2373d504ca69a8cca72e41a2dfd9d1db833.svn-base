package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.vendor;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorSearchListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify deny vendor
 * @Preconditions:
 * If system did not have pending status, please insert into pending status reason for 'Credit Check Run' application check
 * insert into d_vendor_status_reason
 * values (112,1,'Credit Check Run',null,1,1,'0','0',null,null,null)
 * @SPEC:Deny Vendor.UCS
 * @Task#:Auto-758

 * @author VZhang
 * @Date Jan 4, 2012
 */
public class DenyVendor extends LicenseManagerTestCase{
	private VendorInfo vendor = new VendorInfo();
	private LicMgrVendorSearchListPage vendorSearchListPg = LicMgrVendorSearchListPage.getInstance();
	private LicMgrVendorDetailsPage vendorDetailPg = LicMgrVendorDetailsPage.getInstance();

	@Override
	public void execute() {
		//Login license manager and goto vendor search page
		lm.loginLicenseManager(login);
		lm.gotoVendorSearchPg();
		
		//add vendor with default eft info
		lm.addVendor(vendor);
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		
		vendor.status = "Denied - Other";
		//change vendor status
		lm.changeVendorStauts(vendor.status);
		//search vendor by vendor name
		lm.searchVendorInfoByVendorName(vendor.name);	
		//verify vendor status from vendor list
		vendorSearchListPg.verifyVendorStatusFromVendorList(vendor.status, vendor.number);
		
		//go to vendor detail page
		lm.gotoVendorDetailPgFromVendorSearchPg(vendor.number);
		//verify vendor status from detail page
		vendorDetailPg.verifyVendorStatusFromVendorDetail(vendor.status);	
		
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
		
		vendor.number = "StatusVendor" + DateFunctions.getCurrentTime();
		vendor.name = vendor.number;
		vendor.status = "Pending - Credit Check Run";
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
		vendor.isValidatePhysicalAdd = true;
		vendor.phyAddStatus = "Pending";
		
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
	}
}
