package com.activenetwork.qa.awo.datacollection.legacy.orms.license;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ApplicationStatusCheck;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrVendorFinConfigInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;


/**
 * ScriptName: VendorInfo
 * Description:
 * @date: Mar 29, 2011-4:12:49 AM
 * @author QA-qchen
 *
 */
public class VendorInfo {
	public String appReceivedDate = "";
	
	public String appName = "";
	
	public String appPhone = "";
	
	public String appEmail = "";
	
	public String appCreationDate = "";
	
	public String appCreationUser = "";
	
	public String requestStoreNum = "";
	
	public String requestStoreEquipmentNum = "";
	
	public String number = "";
	
	public String name = "";
	
	public String status = "";
	
	public String ownerName = "";
	
	public String vendorType = "";
	
	public String vendorSearchBy = "";
	
	public String vendorSearchByValue = "";
	
	/* taxID belong to vendor attribute info, and it belong to Vendor Details group*/
	public String taxID = "";
	
	/* taxID type belong to vendor attribute info, and it belong to Vendor Details group*/
	public String taxIDType = "";
	
	public String vendorCreationUser = "";
	
	public String vendorCreationDate = "";
	
	public String[] addressContacts;
	
	public String phyAddress="";
	
	public String phySuppAddress = "";
	
	public String  phyCityTown = "";
	
	public String  phyStateProvince = "";
	
	public String  phyCounty = "";
	
	public String  phyZipPostal = "";
	
	public String  phyCountry = "";
	
	public String phyAddStatus = "";
	
	public boolean isValidatePhysicalAdd = false;
	
	public boolean isMailingAddSameAsPhysicalAdd = true;
	
	public String mailingAddress = "";
	
	public String mailingSuppAddress = "";
	
	public String mailingCityTown = "";
	
	public String mailingStateProvince = "";
	
	public String mailingCounty = "";
	
	public String mailingZipPostal = "";
	
	public String mailingCountry = "";
	
	public String mailingAddStatus = "";
	
	public boolean isValidateMailingAdd = false;
	
	public String alterAddress="";
	
	public String alterSuppAddress = "";
	
	public String  alterCityTown = "";
	
	public String  alterStateProvince = "";
	
	public String  alterCounty = "";
	
	public String  alterZipPostal = "";
	
	public String  alterCountry = "";
	
	public String alterAddStatus = "";
	
	public boolean isValidateAlterAdd = false;
	
	public String contactType = "";
	
	public String contactFirstName = "";
	
	public String contactLastName = "";
	
	public String contactBusinessPhone = "";
	
	public String contactFax = "";
	
	public String specifyDefault = "";
	
	public boolean isFillValues = false;
	
	public LicMgrVendorFinConfigInfo finConfigInfo;
	
	public String contactEmail = "";
	
	public String bypassCheck = "";
	
	public String dateCompleted = "";

	public String completedBy = "";
	
	public String applicationCheckComment = "";
	
	public String[] notesAndAlerts;
	
	public String[] stores;
	
	public VendorBankAccountInfo bankAccount;
	
	public String[] bonds;
	
	public String[] users;
	
	public String translation;
	
	public List<Contacts> contacts;
	
	public List<Contacts> removedContacts;
	
	public List<String> removeRepNotifiEmails;
	
	public List<ApplicationStatusCheck> applicationStatusCheck;
	
	public VendorInfo() {
		bankAccount = new VendorBankAccountInfo();
		finConfigInfo = new LicMgrVendorFinConfigInfo();
	}
	
	public String invoiceFrequency= "";
}
