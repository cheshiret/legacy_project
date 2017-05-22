package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.product.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.VehicleTypeManufacturer;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.TestCaseFailedException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: Add Vehicle Type Manufacturer 
 * @Preconditions: 
 * 				 A customer,
 * lName = "TEST-RAFee2"; fName = "QA-RAFee2"; dateOfBirth = "Jun 01 1980"; 
 * 				
 * @SPEC:  Add Vehicle Type Manufacturer
 * @Task#: AUTO-997
 * 
 * @author pzhu
 * @Date  Jun 12, 2012
 * 
 */
public class AddVehicleTypeManufacturer extends LicenseManagerTestCase {


	private String schema = "";
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();

	private Customer[] cust = new Customer[1];
	private VehicleTypeManufacturer manufacturer= new VehicleTypeManufacturer();
	private String result="";
	private String MIC="C8A";
	private String VEHICLE_TYPE = "Boat";
	private String NAME="REGRESSION";
	private String PRINT_NAME="PRINT";
	
	
	@Override
	public void execute() {
		
		lm.loginLicenseManager(login);
		//preparation: clear related data in DB
		this.clearOldManufacturerInDB();
		
		
		//Test step 1: add new manufacturer
		result = lm.addVehicleTypeManufacturer(cust[0], manufacturer);

		//check point 1: check UI message.
		if(!StringUtil.isEmpty(result))
		{
			logger.info("Adding manufacturer failt, error msg is -->"+result);
			throw new TestCaseFailedException("Adding manufacturer failt, error msg is -->"+result);
		}
		logger.info("check point 1 passed!!");
		
		//check point 2: check new manufacturer in DB.
		this.checkNewManufactuereInDB();
		logger.info("check point 2 passed!!");
		
		
		//log out LM
		//lm.gotoHomePage();
		lm.logOutLicenseManager();


	}
	
	
	


	private void clearOldManufacturerInDB() {
		
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String sql = "delete from E_VEHICLE_MANUFACTURER where mic='" +this.MIC
				+"' and m_name='" +this.NAME
				+"' and print_name='" +this.PRINT_NAME
				+"'";
		logger.info("Execute delete: " + sql);

		int result = this.db.executeUpdate(sql);

		logger.info("There are "+result+" old records deleted from DB.");
		
	}





	private void checkNewManufactuereInDB() {
		
		this.db.resetSchema(this.schema);
		logger.info("Changed schema to -->>" + this.schema);
		String[] colNames = { "id" };
		String query = "select id from E_VEHICLE_MANUFACTURER where mic='" +this.MIC
				+"' and m_name='" +this.NAME
				+"' and print_name='" +this.PRINT_NAME
				+"'";
		logger.info("Execute query: " + query);

		List<String[]> result = this.db.executeQuery(query, colNames);

		if(result.size()!=1)
		{
			throw new TestCaseFailedException("check new manufacturer in DB failed, number of new records should be 1, but "+result.size()+" records in DB.");
		}		
		
	}





	@Override
	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "HF HQ Role/WAL-MART";
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";

	
		
		cust[0] = new Customer();
		cust[0].lName = "TEST-RAFee5";
		cust[0].fName = "QA-RAFee5";
		cust[0].dateOfBirth = "Jun 01 1980";
		
		manufacturer.setMIC(this.MIC); //MIC must contain exactly 3 numbers and/or letters combined, and must only contain numbers and letters.
		manufacturer.setVehicleType(this.VEHICLE_TYPE);
		manufacturer.setManufacturerName(this.NAME);
		manufacturer.setPrintName(this.PRINT_NAME);
	
	}
}
