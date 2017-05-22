/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.warehouseinventory;



import java.util.Arrays;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrWarehouseInventoryListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;


/**
 * @Description:Availabe Equipment Report
 * 				Check point: 1. Check column name of the equipment list
 * 							2.Check if assigned feature 'ViewWarehouseInventory', then we can enter into 'Warehouse Inventory List' page properly.
 * @Preconditions: 1. Assign feature 'ViewWarehouseInventory' to role 'HF Administrator'. (D_ASSIGN_FEATURE, ID=2300)
 * 				
 * @SPEC:(PCR2870/DEFECT-32000)
 * @Task#:AUTO-1151
 * 
 * @author pzhu
 * @Date  Aug 6, 2012
 */


public class ViewAvailableEquipment extends LicenseManagerTestCase{
	private AwoDatabase db =(AwoDatabase) AwoDatabase.getInstance();
	private LicMgrWarehouseInventoryListPage equipementList = LicMgrWarehouseInventoryListPage.getInstance();
	private String roleName, feature;
	private String[] tableColumnNames = {"Equipment Description",
										"In Warehouse",
										"Awaiting Return",
										"Assigned",
										"Total"};
	
	private static final int COLUMN_NAME_LINE = 0;
	private String recordPattern = "\\[.*, \\d+, \\d+, \\d+, \\d+\\]";//Example: "[VeriFone 3740 Terminal, 34, 4, 762, 800]"
	
	public void execute() {
		
		this.checkRolesFeatures();
		
		lm.loginLicenseManager(login);
		lm.gotoWarehouseInventoryPageFromHome();
		
		String[][] tableValues = equipementList.getWarehouseInventoryList();
		
		//Check point 1: check all column names
		this.checkColumnNames(tableValues[COLUMN_NAME_LINE]);
		
		//Check point s: check all table lines
		this.checkRecords(tableValues);
						
		lm.logOutLicenseManager();		
	}

	/**
	 * @param tableValues
	 */
	private void checkRecords(String[][] tableValues) {
		logger.info("Checking records of Warehouse Inventory List.");
		int line;
		if(tableValues.length<=1)//No any records.
		{
			throw new ErrorOnPageException("There is no any record in Warehouse Inventory List, Please check again.");
		}
		
		for(line =1; line<tableValues.length; line++)  //table records start from line 1 
		{
			if(!Pattern.matches(this.recordPattern, Arrays.toString(tableValues[line])))
			{
				throw new ErrorOnDataException("Data format of records(line "+line+") is wrong, compare by RegEX", this.recordPattern, Arrays.toString(tableValues[line]));
			}
		}
		logger.info("Check records of Warehouse Inventory List successfully.");
		
	}

	/**
	 * 
	 */
	private void checkColumnNames(String[] names) {
		logger.info("Checking column names of Warehouse Inventory List.");
		if(!Arrays.toString(names).equals(Arrays.toString(this.tableColumnNames)))
		{
			throw new ErrorOnDataException("Column names of Warehouse Inventory List wrong. ", Arrays.toString(this.tableColumnNames), Arrays.toString(names));
		}
		logger.info("Check column names of Warehouse Inventory List successfully.");		
	}

	/**
	 * 
	 */
	private void checkRolesFeatures() {
		logger.info("checking feature("+this.feature+") of role: "+this.roleName);
		
		db.resetSchema(schema);
 		logger.info("Changed schema to -->>"+schema);
 		
 		String sql= "SELECT X_ROLE_FEAT.role_id," +
 				"X_ROLE.NAME as role_name, " +
 				"X_ROLE_FEAT.feat_id, " +
 				"X_ROLE_FEAT.qualifiedname, " +
 				"X_FEAT.app_id, " +
 				"X_FEAT.feat_name, " +
 				"X_FEAT.active " +
 				"FROM X_ROLE_FEAT " +
 				"INNER JOIN X_ROLE ON X_ROLE_FEAT.ROLE_ID=X_ROLE.ID and X_ROLE.NAME='" +
 				this.roleName +
 				"' " +
 				"INNER JOIN X_FEAT  ON X_ROLE_FEAT.FEAT_ID   =X_FEAT.FEAT_ID and X_FEAT.FEAT_NAME LIKE '" +
 				this.feature +
 				"' and X_FEAT.deleted = 0 and X_FEAT.active = 1 ";
 		
 		int num = db.executeQuery(sql).size();
 		logger.info("Find total "+num+" records of Roles/Features("+this.roleName+"/"+this.feature+") assignment.");
 		if(num<1)
 		{
 			logger.info("!!!Attention: Please log into Admin Manager, and assign feature 'ViewWarehouseInventory' to role 'HF Administrator'.");
 			throw new ErrorOnDataException("Cannot find records of Roles/Features("+this.roleName+"/"+this.feature+") assignment.",Integer.toString(1),Integer.toString(num));
 		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		//login info
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		roleName = "HF Administrator - Auto";
		feature = "ViewWarehouseInventory";	
	 
	}
	
	
}
