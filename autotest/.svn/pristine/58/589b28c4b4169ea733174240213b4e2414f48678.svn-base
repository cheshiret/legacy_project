package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreAddressesAndContactsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description: Edit store
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  Edit Store
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 6, 2012
 * @defect DEFECT-32675
 */
public class Edit extends LicenseManagerTestCase{
	private StoreInfo store = new StoreInfo();
	private String vendorNum ="";
	private int Sequence;
	LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
	LicMgrStoreAddressesAndContactsPage addressAndContactsPg = LicMgrStoreAddressesAndContactsPage.getInstance();
	LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		store.storeID = lm.addVendorAgents(store);
		lm.gotoVendorAgentsDetailPg(store.storeID);

		this.switchEditStoreParameters();
		lm.editVendorStore(store);
		this.verifyStoreListInfo(store);

		lm.gotoVendorAgentsDetailPg(store.storeID);
		this.verifyStoreInfoSuccessful(store);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {

		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		Sequence = DataBaseFunctions.getEmailSequence();
		vendorNum = "auto124";
		store.isNewLocation = true;
		store.locationClass = "No Commission Kiosk";
		store.agency = "MSHF";
		store.region = "Central Region";
		store.location = "CA Call Center";
		store.status = "Inactive-Awaiting Info";
		store.storeName = "Jas"+ Sequence;
		store.timeZone = "Africa/Accra";
		store.physicalAddress.address = "Shanxi";
		store.physicalAddress.supplementalAddr = "Xian";
		store.physicalAddress.city = "Schenectady";
		store.physicalAddress.state = "New York";
		store.physicalAddress.county = "Schenectady";
		store.physicalAddress.zip = "12345-0001";
		store.physicalAddress.country = "United States";
		store.physicalAddress.isValidate = true;

		store.locationClass = "MDWFP Headquarters";
		store.isMailSamePhyAddress = false;
		store.mailingAddress.address = "Sichuan";
		store.mailingAddress.supplementalAddr = "Chengdu";
		store.mailingAddress.city = "Schenectady";
		store.mailingAddress.state = "New York";
		store.mailingAddress.county = "Schenectady";
		store.mailingAddress.zip = "12345-0001";
		store.mailingAddress.country = "United States";
		store.mailingAddress.isValidate = true;
		store.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		store.createDate = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));

		Contacts contact = new Contacts();
		contact.contactType = "Operation Mgr";
		contact.isPrimary = true;
		contact.firstName = "jaswang";
		contact.midName = "dan";
		contact.lastName = "Lily";
		contact.suffix = "JR";
		contact.businessPhone = "4088122485";
		contact.homePhone = "4088125263";
		contact.mobilePhone = "4088121452";
		contact.fax = "02185964";
		contact.email = "jas@sina.com";
		contact.website = "http://www.123";

		store.contactArray.add(contact);
	}
	/**
	 * Switch the edit store info.
	 */
	private void switchEditStoreParameters(){
		store.status = "Inactive-Other";
		store.storeName = "editStore" + Sequence;
//		store.locationClass = "MDWFP Internet";// Location class can't be edit.

		store.physicalAddress.address = "Jiangsu";
		store.physicalAddress.supplementalAddr = "Shanghai";
		store.physicalAddress.city = "Schenectady";
		store.physicalAddress.state = "New York";
		store.physicalAddress.county = "Schenectady";
		store.physicalAddress.zip = "12345-0001";
		store.physicalAddress.country = "United States";
		store.physicalAddress.isValidate = true;

		store.isMailSamePhyAddress = false;
		store.mailingAddress.address = "Gansu";
		store.mailingAddress.supplementalAddr = "Nanzhou";
		store.mailingAddress.city = "Schenectady";
		store.mailingAddress.state = "New York";
		store.mailingAddress.county = "Schenectady";
		store.mailingAddress.zip = "12345-0001";
		store.mailingAddress.country = "United States";
		store.mailingAddress.isValidate = true;

		store.AlterAddress.address = "Shangxi";
		store.AlterAddress.supplementalAddr = "Taiyuan";
		store.AlterAddress.city= "Virginia Beach";
		store.AlterAddress.state = "Virginia";
		store.AlterAddress.county = "Virginia Beach city";
		store.AlterAddress.zip = "23451";
		store.AlterAddress.country = "United States";
		store.AlterAddress.isValidate = true;
		store.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		store.createDate = DateFunctions.getToday();

		store.contactArray.clear();

		Contacts contact1 = new Contacts();
		contact1.contactType = "Operation Mgr";
		contact1.isPrimary = true;
		contact1.firstName = "Huang";
		contact1.midName = "Hua";
		contact1.lastName = "Cai";
		contact1.suffix = "SR";
		contact1.businessPhone = "4088126325";
		contact1.homePhone = "4088121425";
		contact1.mobilePhone = "4088123216";
		contact1.fax = "021854152";
		contact1.email = "jas@sina.com";
		contact1.website = "http://www.123";
		contact1.isRemove = true;

		store.contactArray.add(contact1);
	}

	/**
	 * Verify the store detail and address info.
	 * @param expectedStore - the expected store info.
	 */
	public void verifyStoreInfoSuccessful(StoreInfo expectedStore) {
		boolean pass = storeDetailPg.compareStoreDetailInfo(expectedStore);
		if (!pass) {
			throw new ErrorOnPageException("The store info error");
		} else {
			logger.info("Store info correct");
		}
		pass = addressAndContactsPg.CompareStoreAddAndContactsInfo(expectedStore);
		if (!pass) {
			throw new ErrorOnPageException("store address and contacts info error");
		} else {
			logger.info("store address and contacts info correct");
		}
	}
	/**
	 * Verify the store list info.
	 * @param expectedStore - the expected store info.
	 */
	public void verifyStoreListInfo(StoreInfo expectedStore){
		boolean pass = vendorAgentsPg.compareStoreListInfo(expectedStore);
		 if(!pass){
			   throw new ErrorOnPageException("The added store list info wrong");
		   }else{
			   logger.info("vendor store info correct");
		   }
	}
}
