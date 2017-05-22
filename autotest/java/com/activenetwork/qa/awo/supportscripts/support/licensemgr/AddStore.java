/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.support.licensemgr;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Contacts;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.vendor.LicMgrVendorAgentSubPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;


/**  
 * @Description:  
 * @Preconditions: the dependent vendor is already existed or created by AddVendor support script.
 * @SPEC:  
 * @Task#: 
 * @author fliu 
 * @Date  Feb 24th, 2012    
 */
public class AddStore extends SupportCase {
	private boolean loggedIn = false;// Don't login.
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String vendorNum = "";
	private StoreInfo store = new StoreInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private LicMgrVendorAgentSubPage vendorAgentsPg = LicMgrVendorAgentSubPage.getInstance();
	private String owner = "";//who does own the schedule ? If null, case will ignore the column in datapool;
	private String testSuite = "";//The test suite you choose,If null, case will ignore the column in datapool;

	@Override
	public void execute() {
		//log into license Manger
		if (!contract.equalsIgnoreCase(login.contract) && loggedIn) {
			lm.logOutLicenseManager();
			loggedIn = false;
		}
		if((!loggedIn ) || (loggedIn && !vendorAgentsPg.exists())) {
			lm.loginLicenseManager(login);
			loggedIn=true;
		}
		if(!vendorNum.equalsIgnoreCase(store.belongedVendorNum)) {
			lm.gotoVendorDetailsPgFromTopMenu(store.belongedVendorNum);
			lm.gotoVendorAgentsPg();
		}
		//add store under existed vendor.
		store.storeID = lm.addVendorAgents(store);
		this.verifyResult(store);
		
		contract = login.contract;
		vendorNum = store.belongedVendorNum;
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		startpoint = 0; // the start point in the data pool
		endpoint = 0; // the end point in the data pool
		
		dataSource = casePath + "/" + caseName;
		
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");
		
		logMsg=new String[4];
		logMsg[0] = "Index";
		logMsg[1] = "VendorName";
		logMsg[2] = "StoreName";
		logMsg[3] = "Result";
	}

	@Override
	public void getNextData() {
		login.contract=dpIter.dpString("contract");
		login.location=dpIter.dpString("location");
		
		if(testSuite.trim().length()>0 || owner.trim().length()>0){
			while(!testSuite.equalsIgnoreCase(dpIter.dpString("testSuite"))
					||!owner.equalsIgnoreCase(dpIter.dpString("owner"))){
				dpIter.dpNext();
			}
		}
		
		store.belongedVendorNum = dpIter.dpString("vendorNum");
		
		store.status = dpIter.dpString("storeStatus");
		store.isNewLocation = dpIter.dpBoolean("isNewLocation");
		store.locationClass = dpIter.dpString("locationClass");
		store.agency = dpIter.dpString("agency");
		store.region = dpIter.dpString("region");
		if(!store.isNewLocation){
			store.location = dpIter.dpString("storeLocation");
		}
		store.storeName = dpIter.dpString("storeName");
		store.timeZone = dpIter.dpString("timeZone");
		store.physicalAddress.address = dpIter.dpString("pAddress");
		store.physicalAddress.supplementalAddr = dpIter.dpString("pSupplementalAddr");
		store.physicalAddress.city = dpIter.dpString("pCity");
		store.physicalAddress.state = dpIter.dpString("pState");
		store.physicalAddress.county = dpIter.dpString("pCounty");
		store.physicalAddress.zip = dpIter.dpString("pZip");
		store.physicalAddress.country = dpIter.dpString("pCountry");
		store.physicalAddress.isValidate = dpIter.dpBoolean("pIsValidate");
		
		store.isMailSamePhyAddress = dpIter.dpBoolean("isMailSamePhy");
		store.mailingAddress.address = dpIter.dpString("mAddress");
		store.mailingAddress.supplementalAddr = dpIter.dpString("mSupplementalAddr");
		store.mailingAddress.city = dpIter.dpString("mCity");
		store.mailingAddress.state = dpIter.dpString("mState");
		store.mailingAddress.county = dpIter.dpString("mCounty");
		store.mailingAddress.zip = dpIter.dpString("mZip");
		store.mailingAddress.country = dpIter.dpString("mCountry");
		store.mailingAddress.isValidate = dpIter.dpBoolean("mIsValidate");

		Contacts contact1 = new Contacts();
		contact1.contactType = dpIter.dpString("cont1Type");
		contact1.isPrimary = dpIter.dpBoolean("cont1IsPrimary");
		contact1.firstName = dpIter.dpString("cont1FirstName");
		contact1.midName = dpIter.dpString("cont1MidName");
		contact1.lastName = dpIter.dpString("cont1LastName");
		contact1.suffix = dpIter.dpString("cont1Suffix");
		contact1.businessPhone = dpIter.dpString("cont1BusiPhone");
		contact1.homePhone = dpIter.dpString("cont1HomePhone");
		contact1.mobilePhone = dpIter.dpString("cont1MobilePhone");
		contact1.fax = dpIter.dpString("cont1Fax");
		contact1.email = dpIter.dpString("cont1Email");
		contact1.website = dpIter.dpString("cont1Website");
		
		store.contactArray.clear();//*IMPORTANT
		store.contactArray.add(contact1);
		
		if(!dpIter.dpString("cont2Type").trim().equals("")){
			Contacts contact2 = new Contacts();
			contact2.contactType = dpIter.dpString("cont2Type");
			contact2.isPrimary = dpIter.dpBoolean("cont2IsPrimary");
			contact2.firstName = dpIter.dpString("cont2FirstName");
			contact2.midName = dpIter.dpString("cont2MidName");
			contact2.lastName = dpIter.dpString("cont2LastName");
			contact2.suffix = dpIter.dpString("cont2Suffix");
			contact2.businessPhone = dpIter.dpString("cont2BusiPhone");
			contact2.homePhone = dpIter.dpString("cont2HomePhone");
			contact2.mobilePhone = dpIter.dpString("cont2MobilePhone");
			contact2.fax = dpIter.dpString("cont2Fax");
			contact2.email = dpIter.dpString("cont2Email");
			contact2.website = dpIter.dpString("cont2Website");
			
			store.contactArray.add(contact2);
		}			
		
		logMsg[0] = String.valueOf(cursor);
		logMsg[1] = store.belongedVendorNum;
		logMsg[2] = store.storeName;
	}
	
	private void verifyResult(StoreInfo expectedStore){
		boolean pass = vendorAgentsPg.compareStoreListInfo(expectedStore);
		if(!pass) {
				logger.error("Create store information failed: Vendor Number = " + store.belongedVendorNum + ", Store Name = " + store.storeName);
				logMsg[3] = "Failed";
		}else{
			logMsg[3] = "Pass";
		}
	}
}

