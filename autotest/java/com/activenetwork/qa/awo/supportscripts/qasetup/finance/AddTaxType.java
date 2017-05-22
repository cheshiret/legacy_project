package com.activenetwork.qa.awo.supportscripts.qasetup.finance;

import com.activenetwork.qa.awo.datacollection.legacy.Tax;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.finance.AddTaxTypeFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * Description   : Functional Test Script
 * @since  2010/02/24
 * @author Sara Wang
 */
public class AddTaxType extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private Tax tax = new Tax();
	private AddTaxTypeFunction addTaxTypeFunc = new AddTaxTypeFunction();

	public void wrapParameters(Object[] param) {
		dataTableName = "d_fin_tax_type";
		
		String env = TestProperty.getProperty("target_env");	
	  	login.url = AwoUtil.getOrmsURL(env);
	  	login.userName = TestProperty.getProperty("orms.fnm.user");
		login.password = TestProperty.getProperty("orms.fnm.pw");
	}

	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = tax;
		addTaxTypeFunc.execute(args);
	}

	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("contract");
		login.location = datasFromDB.get("location");
		
		tax.feeTypes.clear();
		tax.taxName = datasFromDB.get("taxName");
		tax.taxCode = datasFromDB.get("taxCode");
		tax.taxDescription = datasFromDB.get("taxDescription");
		tax.taxRateType = datasFromDB.get("taxRateType");
		
		tax.feeTypes.add(datasFromDB.get("feeTypes"));
	}
}
