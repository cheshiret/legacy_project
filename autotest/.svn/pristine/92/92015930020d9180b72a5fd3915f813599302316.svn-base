package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.license.products.privileges.getpurchaselist;

import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrOrigPrivSaleAddItemPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * @Description: this test case is used to verify when the available via application only set to 'Yes' the privilege will not display in 
 *               purchase list otherwise the value was set to 'No' the privilege will display in purchase list.
 * @Preconditions:
 * @SPEC:  TC046730
 * @Task#: Auto-1240
 * 
 * @author  Jasmine
 * @Date  Sep 24, 2012
 */
public class AvailableApp extends LicenseManagerTestCase{
    private String switchLocation;
	public void execute() {
		
		//1. update privilege detail info:  Available via Application Only(No)
		lm.loginLicenseManager(login);
		lm.switchLocationInHomePage(switchLocation);
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		privilege.huntsRequired = "No";// Don't change.
		lm.updatePrivilegeDetailsInfo(privilege);
		lm.logOutLicenseManager();

		lm.loginLicenseManager(login);
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		// verify privilege SHOULD exist in add privilege item page.
		this.verifyPriExistInPurchaseList(privilege.purchasingName, privilege.licenseYear);

		//2. update privilege detail info:  Available via Application Only(Yes)
		lm.switchLocationInHomePage(switchLocation);
		lm.gotoPrivilegeListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		privilege.huntsRequired = "Yes";//IMPORTANT, Don't change.
		lm.updatePrivilegeDetailsInfo(privilege);
		lm.logOutLicenseManager();
		
		//Need to clean cache so logout and login once more.
		lm.loginLicenseManager(login);
		lm.gotoAddItemPgFromPrivilegeQuickSearch(cust);
		// verify privilege should NOT exist in add privilege item page.
		this.verifyPriNotExistInPurchaseList(privilege.purchasingName, privilege.licenseYear);
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		login.contract = "MS Contract";
		login.location = "HF HQ Role/"+DataBaseFunctions.getFacilityName("155852", schema);// CLARK CREEK NATURAL AREA
		login.station = "Station 1 AM";
		
		switchLocation = "HF Administrator - Auto-Mississippi Department of Wildlife, Fisheries, and Parks";
		
		privilege.code = "GP4";
		privilege.name = "GetPriPurchaseList";
		privilege.purchasingName = privilege.code +"-"+privilege.name;
		privilege.licenseYear = lm.getFiscalYear(schema);
		
		cust.licenseType = "Green Card";
		cust.licenseNum = "111188";
		cust.dateOfBirth = "Apr 17 1988";
		cust.residencyStatus = "Non Resident";
		cust.customerClass = "Individual";
		
		cust.identifier.identifierType = cust.licenseType;
		cust.identifier.identifierNum = cust.licenseNum;
		cust.identifier.country = "Canada";
		
	}
	/**
	 * verify privilege exist in purchase list when the available via application only was set to 'No'.
	 * @param name
	 * @param licenseYear
	 */
	public void verifyPriExistInPurchaseList(String name,String licenseYear){
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		
		int index = addItemPg.getProductIndex(name, licenseYear);
		if(index<=-1){
			throw new ErrorOnPageException("The product "+name+" should be display in purchase list");
		}else{
			logger.info("The product "+name+" display in purchase list successfully");
		}
	}
	/**
	 * verify privilege not exist in purchase list when the available via application only was set to 'Yes';
	 * @param name
	 * @param licenseYear
	 */
	public void verifyPriNotExistInPurchaseList(String name,String licenseYear){
		LicMgrOrigPrivSaleAddItemPage addItemPg = LicMgrOrigPrivSaleAddItemPage
				.getInstance();
		int index = addItemPg.getProductIndex(name, licenseYear);
		
		if(index>-1){
			throw new ErrorOnPageException("The product "+name+" should not be display in purchase list");
		}else{
			logger.info("The product "+name+" was not display in purchase list successfully");
		}
	}

}
