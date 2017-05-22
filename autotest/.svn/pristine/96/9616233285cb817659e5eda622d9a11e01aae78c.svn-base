/**
 *
 */
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
 * @Description: Add a new stor for a vendor
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  Add Store
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 6, 2012
 */
public class Add extends LicenseManagerTestCase{

	private String vendorNum ="";
	private StoreInfo store = new StoreInfo();
	LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
	LicMgrStoreAddressesAndContactsPage addressAndContactsPg = LicMgrStoreAddressesAndContactsPage.getInstance();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		store.storeID = lm.addVendorAgents(store);
		this.VerifyStoreListInfo(store);

		lm.gotoVendorAgentsDetailPg(store.storeID);
		this.updateExpectedContacts();
		this.verifyStoreInfoSuccessful(store);
		
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		//initial login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		vendorNum = "auto125";
		store.status = "Inactive-Awaiting Info";
		store.isNewLocation = true;
		store.storeName = "Jas"+ DataBaseFunctions.getEmailSequence();
		store.timeZone = "Africa/Accra";
		store.physicalAddress.address = "Shanxi";
		store.physicalAddress.supplementalAddr = "XiAn";
		store.physicalAddress.city = "Schenectady";
		store.physicalAddress.state = "New York";
		store.physicalAddress.county = "Schenectady";
		store.physicalAddress.zip = "12345-0001";
		store.physicalAddress.country = "United States";
		store.physicalAddress.isValidate = true;


		store.locationClass = "MDWFP Headquarters";
		store.isMailSamePhyAddress = false;
		store.mailingAddress.address = "SiChuan";
		store.mailingAddress.supplementalAddr = "ChengDu";
		store.mailingAddress.city = "MianZhu";
		store.mailingAddress.state = "New York";
		store.mailingAddress.county = "Schenectady";
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

		Contacts contact2 = new Contacts();
		contact2.contactType = "Finance Mgr";
		contact2.isPrimary = true;
		contact2.firstName = "Lucy";
		contact2.midName = "dan";
		contact2.lastName = "li";
		contact2.suffix = "SR";
		contact2.businessPhone = "4088127894";
		contact2.homePhone = "4088126352";
		contact2.mobilePhone = "4088121111";
		contact2.fax = "02185964";
		contact2.email = "jas@sina.cn";
		contact2.website = "http://www.460";

		store.contactArray.add(contact1);
		store.contactArray.add(contact2);
	}

	private void updateExpectedContacts(){
		store.contactArray.clear();
		Contacts contact = new Contacts();
		contact.contactType = "Finance Mgr";
		contact.isPrimary = true;
		contact.firstName = "Lucy";
		contact.midName = "dan";
		contact.lastName = "li";
		contact.suffix = "SR";
		contact.businessPhone = "4088127894";
		contact.homePhone = "4088126352";
		contact.mobilePhone = "4088121111";
		contact.fax = "02185964";
		contact.email = "jas@sina.cn";
		contact.website = "http://www.460";
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
