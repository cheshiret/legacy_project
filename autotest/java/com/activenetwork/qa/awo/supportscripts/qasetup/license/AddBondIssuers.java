package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicMgrBondIssuerInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddBondIssuerFunction;

/**
 * @Description: Support scripts for adding bond issuers. 
 * 1. If there is an existing bond issuer with the same business name, the case will go to edit the detail of the bond issuer.
 * 2. The business name should be required. If there are no other info for one record in datapool, the case will use the default values.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Apr 17, 2012
 */
public class AddBondIssuers extends SetupCase{
	private LicMgrBondIssuerInfo bondIssuerInfo = new LicMgrBondIssuerInfo();
	private Object[] args = new Object[3];
	private AddBondIssuerFunction func = new AddBondIssuerFunction();
	
	public void executeSetup() {
		func.execute(args);
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_bond_issuers";
	}
	
	@Override
	public void readDataFromDatabase() {
		args[0] = datasFromDB.get("contract");
		args[1] = datasFromDB.get("location");
		
		bondIssuerInfo.businessNm = datasFromDB.get("businessName");
		bondIssuerInfo.contactAddress = datasFromDB.get("contactAddress");
		bondIssuerInfo.cityOrTown = datasFromDB.get("cityTown");
		bondIssuerInfo.state = datasFromDB.get("state");
		bondIssuerInfo.zipCd = datasFromDB.get("zip");
		bondIssuerInfo.country = datasFromDB.get("country");
		bondIssuerInfo.firstName = datasFromDB.get("contactFName");
		bondIssuerInfo.lastName = datasFromDB.get("contactLName");
		bondIssuerInfo.phone = datasFromDB.get("contactPhone");
		bondIssuerInfo.email = datasFromDB.get("contactEmail");
		
		// Set the default value for the bond issuer info except the business name
		// ToDo: need a common address 
		if ("".equals(bondIssuerInfo.contactAddress)) {
			bondIssuerInfo.contactAddress = "P.O. Box";
			logger.info("The contact address is empty. Set the default contact address: " + bondIssuerInfo.contactAddress);	
		}
		if ("".equals(bondIssuerInfo.cityOrTown)) {
			bondIssuerInfo.cityOrTown = "Montgomery";
			logger.info("The city or town is empty. Set the default city or town: " + bondIssuerInfo.cityOrTown);	
		}
		if ("".equals(bondIssuerInfo.state)) {
			bondIssuerInfo.state = "Mississippi";
			logger.info("The state is empty. Set the default state: " + bondIssuerInfo.state);
		}
		if ("".equals(bondIssuerInfo.zipCd)) {
			bondIssuerInfo.zipCd = "10001";
			logger.info("The zip code is empty. Set the default zip code: " + bondIssuerInfo.zipCd);
		}
		if ("".equals(bondIssuerInfo.country)) {
			bondIssuerInfo.country = "United States";
			logger.info("The country is empty. Set the default country: " + bondIssuerInfo.country);
		}
		if ("".equals(bondIssuerInfo.firstName)) {
			bondIssuerInfo.firstName = "QA";
			logger.info("The contact first name is empty. Set the default first name: " + bondIssuerInfo.firstName);
		}
		if ("".equals(bondIssuerInfo.lastName)) {
			bondIssuerInfo.lastName = "Tester";
			logger.info("The contact last name is empty. Set the default last name: " + bondIssuerInfo.lastName);
		}
		if ("".equals(bondIssuerInfo.phone)) {
			bondIssuerInfo.phone = "9999999999";
			logger.info("The contact phone is empty. Set the default contact phone: " + bondIssuerInfo.phone);
		}
		if ("".equals(bondIssuerInfo.email)) {
			bondIssuerInfo.email = "supportscriptforbondissuers@reserveamerica.com";
			logger.info("The contact email is empty. Set the default contact: " + bondIssuerInfo.email);
		}	
		args[2] = bondIssuerInfo;
	}
}
