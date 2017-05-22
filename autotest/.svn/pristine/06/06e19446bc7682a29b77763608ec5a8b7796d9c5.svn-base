package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.PrivilegePurchaseAuthorization;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.PurchaseAuthorizationFunction;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
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
public class PurchaseAuthorization extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private Customer cust = new Customer();
	private Data<PrivilegePurchaseAuthorization> privPurchaseAuth = new Data<PrivilegePurchaseAuthorization>();
	private PurchaseAuthorizationFunction purcahseAuthorizationFunc = new PurchaseAuthorizationFunction();
	
	@Override
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = cust;
		args[2] = privPurchaseAuth;
		purcahseAuthorizationFunc.execute(args);
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "D_HF_PURCHASE_AUTHORIZATION";	
		ids = "10,20,30";
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("CONTRACT");
		login.location=datasFromDB.get("LOCATION");
		cust.customerClass = datasFromDB.get("CUSTCLASS");
		cust.fName = datasFromDB.get("CUSTFIRSTNAME");
		cust.lName = datasFromDB.get("CUSTLASTNAME");
		privPurchaseAuth.put(PrivilegePurchaseAuthorization.AuthorizationType, datasFromDB.get("AUTHORIZATIONTYPE"));
		privPurchaseAuth.put(PrivilegePurchaseAuthorization.AuthedPrivilege, datasFromDB.get("AUTHEDPRIVILEGE"));
		String numOfYearAfterCurrentYear = datasFromDB.get("NUMOFYEARAFTERCURRENTYEAR");
		if(StringUtil.isEmpty(numOfYearAfterCurrentYear))
			numOfYearAfterCurrentYear = "0";
		privPurchaseAuth.put(PrivilegePurchaseAuthorization.AuthedPrivLicenseYear, String.valueOf(DateFunctions.getYearAfterCurrentYear(Integer.valueOf(numOfYearAfterCurrentYear))));
		privPurchaseAuth.put(PrivilegePurchaseAuthorization.AuthedReason, datasFromDB.get("AUTHEDREASON"));
	}
}
