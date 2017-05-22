package com.activenetwork.qa.awo.supportscripts.function.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrCustomerPurchaseAuthorizationPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 12, 2014
 */
public class PurchaseAuthorizationFunction extends FunctionCase {
	private LoginInfo login = new LoginInfo();
	private LicenseManager lm = LicenseManager.getInstance();
	private boolean loggedIn = false;
	private String location = StringUtil.EMPTY;
	private String contract = StringUtil.EMPTY;
	private String custNum = StringUtil.EMPTY;
	private Data<PrivilegePurchaseAuthorization> privPurchaseAuth = new Data<PrivilegePurchaseAuthorization>();
	private LicMgrCustomerPurchaseAuthorizationPage purchaseAuthPg = LicMgrCustomerPurchaseAuthorizationPage.getInstance();
	private Customer cust = new Customer();

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedIn && isBrowserOpened) {
			lm.logOutLicenseManager();
			loggedIn= false;
		}
		if(login.contract.equals(contract) && loggedIn && isBrowserOpened){
			if(!login.location.equals(location)){
				lm.switchLocationInHomePage(login.location.replace("/", "-"));
			}
		}
		
		if (!loggedIn || !isBrowserOpened) { 
			lm.loginLicenseManager(login);
			loggedIn= true;
		}

		contract = login.contract;
		location = login.location;
	

		if(!custNum.equals(cust.custNum)){
			lm.gotoHomePage();
			lm.gotoCustomerDetailFromCustomersQuickSearch(cust);
			lm.gotoCustomerPurchaseAuthorizationPage();
		}
		
		custNum = cust.custNum;
		String errorMsg = lm.addPrivilegePurchaseAuthorization(privPurchaseAuth);
		
		if(StringUtil.isEmpty(errorMsg)){
			privPurchaseAuth.put(PrivilegePurchaseAuthorization.ID, purchaseAuthPg.getActivePurchaseAuthorizationID(privPurchaseAuth));
			newAddValue = privPurchaseAuth.stringValue(PrivilegePurchaseAuthorization.ID);	
		}else {
			newAddValue = errorMsg;
			throw new ErrorOnPageException("Failed to purchase new authorization to customer due to:" + errorMsg);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		cust = (Customer)param[1];
		privPurchaseAuth = (Data<PrivilegePurchaseAuthorization>)param[2];

		String env = TestProperty.getProperty("target_env");
		String schema = DataBaseFunctions.getSchemaName(login.contract.split(" ")[0], env);
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, schema);
		cust.identifier.identifierNum = cust.custNum;
		login.url = AwoUtil.getOrmsURL(env);
		login.userName = TestProperty.getProperty("orms.fm.user");
		login.password = TestProperty.getProperty("orms.fm.pw");

	}

}
