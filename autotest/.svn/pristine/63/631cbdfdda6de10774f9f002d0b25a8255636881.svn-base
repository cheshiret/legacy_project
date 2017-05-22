/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddStoreFunction;

/**  
 * @Description:  
 * @Preconditions: the dependent vendor is already existed or created by AddVendor support script.
 * @SPEC:  
 * @Task#: 
 * @author fliu 
 * @Date  Feb 24th, 2012    
 */
public class AddStore extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private StoreInfo store = new StoreInfo();
	private AddStoreFunction addStoreFunc = new AddStoreFunction();

	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = store;
		addStoreFunc.execute(args);
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_store";
		this.queryDataSql = "";
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		store.belongedVendorNum = datasFromDB.get("vendorNum");
		
		store.status = datasFromDB.get("storeStatus");
		store.isNewLocation = Boolean.parseBoolean(datasFromDB.get("isNewLocation"));
		store.locationClass = datasFromDB.get("locationClass");
		store.agency = datasFromDB.get("agency");
		store.region = datasFromDB.get("region");
		if(!store.isNewLocation){
			store.location = datasFromDB.get("storeLocation");
		}
		store.storeName = datasFromDB.get("storeName");
		store.timeZone = datasFromDB.get("timeZone");
		store.physicalAddress.address = datasFromDB.get("pAddress");
		store.physicalAddress.supplementalAddr = datasFromDB.get("pSupplementalAddr");
		store.physicalAddress.city = datasFromDB.get("pCity");
		store.physicalAddress.state = datasFromDB.get("pState");
		store.physicalAddress.county = datasFromDB.get("pCounty");
		store.physicalAddress.zip = datasFromDB.get("pZip");
		store.physicalAddress.country = datasFromDB.get("pCountry");
		store.physicalAddress.isValidate = Boolean.parseBoolean(datasFromDB.get("pIsValidate"));
		
		store.isMailSamePhyAddress = Boolean.parseBoolean(datasFromDB.get("isMailSamePhy"));
		store.mailingAddress.address = datasFromDB.get("mAddress");
		store.mailingAddress.supplementalAddr = datasFromDB.get("mSupplementalAddr");
		store.mailingAddress.city = datasFromDB.get("mCity");
		store.mailingAddress.state = datasFromDB.get("mState");
		store.mailingAddress.county = datasFromDB.get("mCounty");
		store.mailingAddress.zip = datasFromDB.get("mZip");
		store.mailingAddress.country = datasFromDB.get("mCountry");
		store.mailingAddress.isValidate = Boolean.parseBoolean(datasFromDB.get("mIsValidate"));

		Contacts contact1 = new Contacts();
		contact1.contactType = datasFromDB.get("cont1Type");
		contact1.isPrimary = Boolean.parseBoolean(datasFromDB.get("cont1IsPrimary"));
		contact1.firstName = datasFromDB.get("cont1FirstName");
		contact1.midName = datasFromDB.get("cont1MidName");
		contact1.lastName = datasFromDB.get("cont1LastName");
		contact1.suffix = datasFromDB.get("cont1Suffix");
		contact1.businessPhone = datasFromDB.get("cont1BusiPhone");
		contact1.homePhone = datasFromDB.get("cont1HomePhone");
		contact1.mobilePhone = datasFromDB.get("cont1MobilePhone");
		contact1.fax = datasFromDB.get("cont1Fax");
		contact1.email = datasFromDB.get("cont1Email");
		contact1.website = datasFromDB.get("cont1Website");
		
		store.contactArray.clear();//*IMPORTANT
		store.contactArray.add(contact1);
		
		if(!datasFromDB.get("cont2Type").trim().equals("")){
			Contacts contact2 = new Contacts();
			contact2.contactType = datasFromDB.get("cont2Type");
			contact2.isPrimary = Boolean.parseBoolean(datasFromDB.get("cont2IsPrimary"));
			contact2.firstName = datasFromDB.get("cont2FirstName");
			contact2.midName = datasFromDB.get("cont2MidName");
			contact2.lastName = datasFromDB.get("cont2LastName");
			contact2.suffix = datasFromDB.get("cont2Suffix");
			contact2.businessPhone = datasFromDB.get("cont2BusiPhone");
			contact2.homePhone = datasFromDB.get("cont2HomePhone");
			contact2.mobilePhone = datasFromDB.get("cont2MobilePhone");
			contact2.fax = datasFromDB.get("cont2Fax");
			contact2.email = datasFromDB.get("cont2Email");
			contact2.website = datasFromDB.get("cont2Website");
			
			store.contactArray.add(contact2);
		}			
	}
}

