package com.activenetwork.qa.awo.supportscripts.qasetup.license;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.license.AddInstructorFunction;
/**
 * @Description: Add instructor product
 * @author Phoebe
 * @Date  April 22, 2014
 */
public class AddInstructor extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private Customer cust = new Customer();
	private AddInstructorFunction addInstructorFunc = new AddInstructorFunction();
	@Override
	public void executeSetup() {
		Object[] args = new Object[2];
		args[0] = login;
		args[1] = cust;
		addInstructorFunc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract=datasFromDB.get("contract");
		login.location=datasFromDB.get("location");
		
		cust.fName = datasFromDB.get("FNAME");
		cust.lName = datasFromDB.get("LNAME");
		cust.dateOfBirth = datasFromDB.get("DATEOFBIRTH");

		cust.physicalAddr.address =  datasFromDB.get("ADDRESS");
		cust.physicalAddr.city =  datasFromDB.get("CITY");
		cust.physicalAddr.state =  datasFromDB.get("STATE");
		cust.physicalAddr.county =  datasFromDB.get("COUNTY");
		cust.physicalAddr.zip =  datasFromDB.get("ZIPCODE");
		cust.physicalAddr.country = datasFromDB.get("COUNTRY");
		
		cust.identifier.identifierType = datasFromDB.get("IDENTIFIER_TYPE");
		cust.identifier.identifierNum =  datasFromDB.get("IDENTIFIER_NUM");
		cust.identifier.state =  datasFromDB.get("IDENTIFIER_STATE");
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_hf_add_instructor";
		ids = "40";
	}

}
