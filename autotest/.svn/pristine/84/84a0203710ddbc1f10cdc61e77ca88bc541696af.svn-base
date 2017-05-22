package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VendorInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddBankAccountsFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang8
 * @Date  Mar 19, 2012
 */
public class AddBankAccounts extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private VendorInfo vendor = new VendorInfo();
	private AddBankAccountsFunction addBankAccFunc = new AddBankAccountsFunction();
	
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = vendor;
		addBankAccFunc.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_bank_accounts";
	}
	
	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		vendor.number = datasFromDB.get("VendorNum");
		vendor.bankAccount.accountType = datasFromDB.get("accountType");
		vendor.bankAccount.routingNum = datasFromDB.get("routingNum");
		vendor.bankAccount.accountNum = datasFromDB.get("accountNum");
		if(vendor.bankAccount.accountNum.length()<1){
			vendor.bankAccount.accountNum = String.valueOf(DateFunctions.getCurrentTime());
		}
		vendor.bankAccount.accountPrenoteStatus = datasFromDB.get("prenoteStatus");
		vendor.bankAccount.accountStatus = datasFromDB.get("accountStatus");
	}
}
