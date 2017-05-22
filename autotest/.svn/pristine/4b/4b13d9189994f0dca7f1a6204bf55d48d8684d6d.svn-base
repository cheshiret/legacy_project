package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.vendorandstore.store.add;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddressesAndContactsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description:This case is used to verify error message when add store address info(physical address and mailing address)
 * @SPEC:[TC:044747]Add Store/Edit Store - Zip/Postal Minimum 
 * @Task#:Auto-1861
 * @Preconditions:
 * need to script in Canada 1 and Australia 14 into table, please referred to "http://wiki.reserveamerica.com/display/dev/Vendor+and+Store+Setup"
 * insert into d_entity_country(id, entity_type_id, country_id) values (get_sequence('d_entity_country'), 2, 1);
 * insert into d_entity_country(id, entity_type_id, country_id) values (get_sequence('d_entity_country'), 2, 14);
 * commit;
 * @author Phoebe
 * @Date September 14, 2013
 */
public class VerifyMessage_Address extends LicenseManagerTestCase{
	private String vendorNum ="";
	private StoreInfo store = new StoreInfo();
	LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
	LicMgrStoreAddressesAndContactsPage addressAndContactsPg = LicMgrStoreAddressesAndContactsPage.getInstance();
	LicMgrStoreAddPage storeAddPg = LicMgrStoreAddPage.getInstance();
	private boolean result = true;
	private String expMsg_Australia, expMsg_Canada;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		
		//Select country as Australia,verify error message
		store.physicalAddress.country = "Australia";
		store.physicalAddress.zip = "*";
		lm.addVendorAgents(store);
		this.verifyErrorMessage(expMsg_Australia);
		
		//Select country as Canada,verify error message
		store.physicalAddress.country = "Canada";
		store.physicalAddress.zip = "23455";
		storeAddPg.setAddress(store.physicalAddress, 0);
		lm.addVendorAgents(store);
		this.verifyErrorMessage(expMsg_Canada);
			
		if(!result){
			throw new ErrorOnPageException("Error Message Not Correct,please check Error Log.");
		}
		
		lm.logOutLicenseManager();		
	}

	private void verifyErrorMessage(String expMessage){
		logger.info("Verify Error Message.");
		String actuMessage = storeAddPg.getErrorMessage();
		if(!actuMessage.equals(expMessage)){
			result &= false;
			logger.error("Expect error message should be " + expMessage 
					+ ", but actually is " + actuMessage);
		}else {
			logger.info("Error Message is correct.");
		}
	}

	
	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vendorNum = "auto125";
		store.status = "Inactive-Awaiting Info";
		store.isNewLocation = true;
		store.storeName = "AddressFormat"+ DataBaseFunctions.getEmailSequence();
		store.timeZone = "Africa/Accra";
		store.physicalAddress.address = "Shanxi";
		store.physicalAddress.supplementalAddr = "XiAn";
		store.physicalAddress.city = "Schenectady";
		store.physicalAddress.zip = "12345-0001";
		store.physicalAddress.country = "United States";
		store.physicalAddress.isValidate = true;


		store.locationClass = "MDWFP Headquarters";
		store.isMailSamePhyAddress = false;
		store.mailingAddress.address = "SiChuan";
		store.mailingAddress.supplementalAddr = "ChengDu";
		store.mailingAddress.city = "MianZhu";
		store.mailingAddress.zip = "25361";
		store.mailingAddress.country = "United States";
		store.mailingAddress.isValidate = true;

		store.createDate = DateFunctions.getToday();


		Contacts contact1 = new Contacts();
		contact1.contactType = "Operation Mgr";
		contact1.isPrimary = true;
		contact1.firstName = "jaswang";
		contact1.midName = "dan";
		contact1.lastName = "Lily";
		contact1.suffix = "JR";
		contact1.businessPhone = "4088122485";
		contact1.homePhone = "4088125263";
		contact1.mobilePhone = "4088121452";
		contact1.fax = "02185964";
		contact1.email = "jas@sina.com";
		contact1.website = "http://www.123";

		store.contactArray.add(contact1);
		
		//Added just for error of zip when country is "Australia"
		expMsg_Australia = "ZIP/Postal Code must contain at least 1 number or letter, and must only contain numbers, letters, a single dash, or a single space. Please change your entries for Address Type \"Physical Address\".";
		//Added just for error of zip when country is "Canada"
		expMsg_Canada = "Postal Code must contain exactly 6 numbers and letters combined, and contain only the following characters: number, letter, single dash, single space in one of the following formats: A#A #A# or A#A#A#, " +
				"or A#A-#A# where A is any alphabetic character and # is a numeric digit from 0 to 9.Please change your entries for Address Type Physical Address.";
	}

	public void VerifyStoreListInfo(StoreInfo expectedStore){
	   boolean pass = vendorAgentsPg.compareStoreListInfo(expectedStore);
	   if(!pass){
		   throw new ErrorOnPageException("The store list info error");
	   }else {
			logger.info("Store list info correct");
		}
	}

	public void verifyStoreInfoSuccessful(StoreInfo expectedStore) {
		boolean pass = storeDetailPg.compareStoreDetailInfo(expectedStore);
		if (!pass) {
			throw new ErrorOnPageException("The store info error");
		} else {
			logger.info("Store info correct");
		}
		pass = addressAndContactsPg.compareStoreDetailInfo(expectedStore);
		if (!pass) {
			throw new ErrorOnPageException("store address info error");
		} else {
			logger.info("store address info correct");
		}
	}

}
