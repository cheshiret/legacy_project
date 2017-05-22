package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.store;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.store.LicMgrStoreChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
/**
 * @Description: View the store change history info.
 * @Preconditions:  The specific vendor is exist.
 * @SPEC:  View store change history info
 * @Task#: Auto-753
 * @author jwang8
 * @Date  Jan 10, 2012  
 * @defect: DEFECT-39923 
 */
public class ChangeHistory extends LicenseManagerTestCase{
	private String vendorNum ="";
	private StoreInfo oldStore = new StoreInfo();
	private StoreInfo newStore = new StoreInfo();
	private LicMgrStoreChangeHistoryPage historyPg = LicMgrStoreChangeHistoryPage.getInstance();
	private List<String> fieldValueList = null;
	private String action;
	@Override
	public void execute() {
		lm.loginLicenseManager(login);
		lm.gotoVendorDetailsPgFromTopMenu(vendorNum);
		lm.gotoVendorAgentsPg();
		//Agent id 1318.
		oldStore.storeID = lm.addVendorAgents(oldStore);//DEFECT-36087
	
		//Update the store info.
		this.switchUpdatedStoreParameters();
		lm.gotoVendorAgentsDetailPg(oldStore.storeID);
		lm.editVendorStore(newStore);
		lm.gotoVendorAgentsDetailPg(oldStore.storeID);

		lm.gotoVendorStoryChangeHistoryPage();
		//Verify the store change history info after editing action.
		
		this.verifyStoreChangeHistoyInfo(action,oldStore, newStore,fieldValueList);
		lm.logOutLicenseManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";

		oldStore.isNewLocation = true;
		oldStore.locationClass = "No Commission Kiosk";
		oldStore.agency = "MSHF";
		oldStore.region = "Central Region";
		oldStore.location = "CA Call Center";
		vendorNum = "auto555";
		oldStore.status = "Inactive-Awaiting Info";
		oldStore.storeName = "Jas"+ DataBaseFunctions.getEmailSequence();
		oldStore.timeZone = "Africa/Accra";
		
		oldStore.physicalAddress.address = "Shanxi";
		oldStore.physicalAddress.supplementalAddr = "XiAn";
		oldStore.physicalAddress.city = "Schenectady";
		oldStore.physicalAddress.state = "New York";
		oldStore.physicalAddress.county = "Schenectady";
		oldStore.physicalAddress.zip = "12345-0001";
		oldStore.physicalAddress.country = "United States";
		oldStore.physicalAddress.isValidate = true;
		oldStore.isMailSamePhyAddress = true;
		oldStore.mailingAddress.address = oldStore.physicalAddress.address;
		oldStore.mailingAddress.supplementalAddr = oldStore.physicalAddress.supplementalAddr;
		oldStore.mailingAddress.city = oldStore.physicalAddress.city;
		oldStore.mailingAddress.state = oldStore.physicalAddress.state;
		oldStore.mailingAddress.county = oldStore.physicalAddress.county;
		oldStore.mailingAddress.zip = oldStore.physicalAddress.zip;
		oldStore.mailingAddress.country = oldStore.physicalAddress.country;
		oldStore.mailingAddress.isValidate = true;
		
		oldStore.createUser = DataBaseFunctions.getLoginUserName(login.userName);
		oldStore.createLocation = login.location.split("/")[1];
		
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

		oldStore.contactArray.add(contact);
		
		action = "Update";
	}

	/**
	 * Switch the store updated info.
	 */
	private void switchUpdatedStoreParameters(){
		newStore.status = "Active";
		newStore.storeName = "UpdateStore" + DataBaseFunctions.getEmailSequence();
		newStore.locationClass = "MDWFP Internet";

		newStore.physicalAddress.address = "Jiangsu";
		newStore.physicalAddress.supplementalAddr = "ShangHai";
		newStore.physicalAddress.city = "Schenectady";
		newStore.physicalAddress.state = "New York";
		newStore.physicalAddress.county = "Schenectady";
		newStore.physicalAddress.zip = "12345-0001";
		newStore.physicalAddress.country = "United States";
		newStore.physicalAddress.isValidate = true;

		newStore.isMailSamePhyAddress = false;
		newStore.mailingAddress.address = "Gansu";
		newStore.mailingAddress.supplementalAddr = "NanZhou";
		newStore.mailingAddress.city = "Schenectady";
		newStore.mailingAddress.state = "New York";
		newStore.mailingAddress.county = "Schenectady";
		newStore.mailingAddress.zip = "12345-0001";
		newStore.mailingAddress.country = "United States";
		newStore.mailingAddress.isValidate = true;

		Contacts contact = new Contacts();
		contact.contactType = "Operation Mgr";
		contact.isPrimary = true;
		contact.firstName = "Huang";
		contact.midName = "Hua";
		contact.lastName = "Cai";
		contact.suffix = "SR";
		contact.businessPhone = "4088126325";
		contact.homePhone = "4088121425";
		contact.mobilePhone = "4088123216";
		contact.fax = "021854152";
		contact.email = "jas@sina.com";
		contact.website = "http://www.123";

		newStore.contactArray.add(contact);
		
		fieldValueList = new ArrayList<String>();
		fieldValueList.add("Agent Name");
		fieldValueList.add("Physical Address-Address");
		fieldValueList.add("Physical Address-ZIP/Postal");
		fieldValueList.add("Physical Address-City/Town");
		fieldValueList.add("Mailing Address-Address");
		fieldValueList.add("Mailing Address-ZIP/Postal");
		fieldValueList.add("Mailing Address-City/Town");
		fieldValueList.add("Operation Mgr-Suffix");
		fieldValueList.add("Operation Mgr-Fax");
		fieldValueList.add("Operation Mgr-Mobile Phone");
		fieldValueList.add("Operation Mgr-Business Phone");
		fieldValueList.add("Operation Mgr-Home Phone");
		fieldValueList.add("Operation Mgr-Middle Name");
		fieldValueList.add("Operation Mgr-Last Name");
		fieldValueList.add("Operation Mgr-First Name");
	}

	/**
	 * Verify the store change history info.
	 * @param expectedOldStore - the old store value
	 * @param expectedNewStore - the new store value.
	 * @param action - the action value.
	 * @param location - the location value.
	 */
	private void verifyStoreChangeHistoyInfo(String action,StoreInfo expectedOldStore, StoreInfo expectedNewStore,List<String> list){

		boolean pass = historyPg.compareStoreChangeHistoryInfo(action, expectedOldStore, expectedNewStore,list);
		if(!pass){
			throw new ErrorOnPageException("Store change historry info error");
		}else{
			logger.info("Store change history info correct");
		}
	}
}
