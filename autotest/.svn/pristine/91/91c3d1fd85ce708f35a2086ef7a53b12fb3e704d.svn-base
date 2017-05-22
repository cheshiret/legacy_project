package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.StoreInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.VendorBankAccountInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.BankAccountStoreAssignmentFunction;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;

public class BankAccountStoreAssignment extends SetupCase{
	private LoginInfo login = new LoginInfo();
	private StoreInfo storeInfo = new StoreInfo();
	private VendorBankAccountInfo bankAccount = new VendorBankAccountInfo();
	private BankAccountStoreAssignmentFunction BAccountStoreAssig = new BankAccountStoreAssignmentFunction();
	
	public void executeSetup() {
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = storeInfo;
		args[2] = bankAccount;
		BAccountStoreAssig.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		storeInfo.belongedVendorNum = datasFromDB.get("vendorNum");
		storeInfo.storeName = datasFromDB.get("storeName");
		
		bankAccount.accountNum = datasFromDB.get("bankAccountNum");
		if(StringUtil.isEmpty(bankAccount.accountNum)){
			bankAccount.accountNum = "c" + new Random().nextInt(1000) + DateFunctions.getLongTimeStamp();	
		}
		bankAccount.accountType = datasFromDB.get("bankAccountType");
		bankAccount.routingNum = datasFromDB.get("routingNum");
		String bankAccountRegx = datasFromDB.get("bankAccountRegx");
		if(StringUtil.isEmpty(bankAccountRegx)){
			bankAccount.accountRegx = bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccount.accountNum;//.substring(0, 4);
		}else{
			bankAccount.accountRegx =  bankAccount.accountType + " Routing # " + bankAccount.routingNum + " Acct # " + bankAccountRegx+ "";
		}
		bankAccount.effectiveDate = datasFromDB.get("effectiveDate");
		if(StringUtil.isEmpty(bankAccount.effectiveDate)){
			bankAccount.effectiveDate = DateFunctions.getDateAfterToday(1);
		}
		
	}

	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assi_bk_acct_to_store";
		this.queryDataSql = "";
	}
	
}
