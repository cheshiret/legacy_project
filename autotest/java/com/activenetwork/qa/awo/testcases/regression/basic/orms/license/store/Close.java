package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store;

import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreDetailsPage;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: Close the store.
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  Close store
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 10, 2012
 */
public class Close extends LicenseManagerTestCase{
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
		//Select the the status as closure.
		store.status = "Inactive-Request Agent Closure";
		lm.updateStoreStatus(store.status);

		//Updated the expected store status with closed.
		store.status = "Closed";
		this.verifyStoreListInfo(store);
		lm.gotoVendorAgentsDetailPg(store.storeID);
		this.verifyCloseStoreStatus(store);

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
		vendorNum = "auto124";
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
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(TestProperty.getProperty(env + ".db.schema.prefix") + "MS");
		store.createDate = DateFunctions.getToday(timeZone);

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
	private void verifyCloseStoreStatus(StoreInfo expectedStore){
		boolean pass = storeDetailPg.compareStoreDetailInfo(expectedStore);
		if(!pass){
			throw new ErrorOnPageException("Close store status error");
		}else{
			logger.info("Activate store staus success");
		}
	}
}
