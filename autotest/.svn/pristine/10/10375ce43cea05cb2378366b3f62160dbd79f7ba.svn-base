package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * @Description: Modify the store status as active.
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  Activate store
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 10, 2012
 */
public class Activate extends LicenseManagerTestCase{
	LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	LicMgrStoreDetailsPage storeDetailPg = LicMgrStoreDetailsPage.getInstance();
	private String vendorNum ="";
	private StoreInfo store = new StoreInfo();

	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		store.storeID = lm.addVendorAgents(store);
		lm.gotoVendorAgentsDetailPg(store.storeID);
		//Update the store status value of active.
		store.status = "Active";
		lm.updateStoreStatus(store.status);
		//Verify the store list info after change the status.
		this.verifyStoreListInfo(store);
		//go to store detail page to verify the status.
		lm.gotoVendorAgentsDetailPg(store.storeID);
		this.VerifyStoreInfo(store);

		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		store.isNewLocation = true;
		store.locationClass = "No Commission Kiosk";
		store.agency = "MSHF";
		store.region = "Central Region";
		store.location = "CA Call Center";
		vendorNum = "auto555";
		store.status = "Inactive-Awaiting Info";
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
		store.isMailSamePhyAddress = true;

		store.createUser = DataBaseFunctions.getLoginUserName(login.userName);

		String today = DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema));
		store.createDate = today;

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
	/**
	 * Verify the store detail and address info.
	 * @param expectedStore - the expected store info.
	 */
	private void VerifyStoreInfo(StoreInfo expectedStore){
		boolean pass = storeDetailPg.compareStoreDetailInfo(expectedStore);
		if(!pass){
			throw new ErrorOnPageException("Activate store status error");
		}else{
			logger.info("Activate store staus success");
		}
	}




}
