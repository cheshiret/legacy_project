package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.keywords.orms.LicenseManager;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AssociateCustWithStoreFunction;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Associate customer with store. It is one of the preconditions for outfitter orders  
 * 
 * @author Lesley Wang
 * @Date  Sep 26, 2013
 */
public class AssociateCustWithStore extends SetupCase {
	private Object[] args = new Object[4];
	private AssociateCustWithStoreFunction func = new AssociateCustWithStoreFunction();
	private Customer cust;
	private LicenseManager lm = LicenseManager.getInstance();
	private String schema;
	
	@Override
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + args[0].toString().split("Contract")[0].trim();
		
		args[2] = datasFromDB.get("storeName");
		
		cust = new Customer();
		cust.fName = datasFromDB.get("fName");
		cust.lName = datasFromDB.get("lName");
		cust.mName = datasFromDB.get("mName");
		cust.businessName = datasFromDB.get("bName");
		cust.custNum = lm.getCustomerNumByCustName(cust.lName, cust.fName, cust.mName, cust.businessName, schema);
		args[3] = cust.custNum;
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_assoc_cust_store";
	}

}
