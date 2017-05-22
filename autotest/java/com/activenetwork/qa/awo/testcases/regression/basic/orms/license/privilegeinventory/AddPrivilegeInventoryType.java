package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify add privilege inventory type info
 * @Preconditions:
 * @SPEC:Add Privilege Inventory type.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 9, 2012
 */
public class AddPrivilegeInventoryType extends LicenseManagerTestCase{
	private PrivilegeInventoryType priInventoryType = new PrivilegeInventoryType(); 

	@Override
	public void execute() {
		//check and clear up env
		List<String[]> inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType.inventoryType);
		if(inventoryTypeInfo.size()>0){
			lm.deletePrivilegeInventoryTypeInfoByID(schema, inventoryTypeInfo.get(0)[0]);
		}
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		//add privilege inventory type
		lm.addPrivilegeInventoryTypeInfo(priInventoryType.inventoryType);
		//verify added info
		this.verifyPrivilegeInventoryInfo(schema, priInventoryType);
		//clear up
		lm.deletePrivilegeInventoryTypeInfoByID(schema, priInventoryType.id);	
		
		lm.logOutLicenseManager();		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		TimeZone timeZone= DataBaseFunctions.getContractTimeZone(schema, "1");
		priInventoryType.inventoryType = "Add Inventory Type";
		
		priInventoryType.status = "Active";
		priInventoryType.createUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		priInventoryType.createLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		priInventoryType.createDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);
	}
	
	private void verifyPrivilegeInventoryInfo(String schema, PrivilegeInventoryType expInventoryType){
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage.getInstance();
		List<String[]> inventoryTypeFromDB = lm.queryPrivilegeInventoryTyeByCode(schema, expInventoryType.inventoryType);
		boolean result = true;
		
		logger.info("Verify privilege inventory info.");
		
		if(inventoryTypeFromDB.size() != 1){
			throw new ErrorOnDataException("Expected should have one inventory type info in DB, but actually is not.");
		}
		
		expInventoryType.id = inventoryTypeFromDB.get(0)[0];
		if(!expInventoryType.createUser.equalsIgnoreCase(inventoryTypeFromDB.get(0)[1])){
			result &= false;
			MiscFunctions.compareResult("Create user info is not correct.", expInventoryType.createUser, inventoryTypeFromDB.get(0)[1]);
		}else {
			logger.info("Create user info is correct.");
		}
		
		if(!expInventoryType.createLocation.equalsIgnoreCase(inventoryTypeFromDB.get(0)[2])){
			result &= false;
			MiscFunctions.compareResult("Create location info is not correct.", expInventoryType.createLocation, inventoryTypeFromDB.get(0)[2]);
		}else {
			logger.info("Create location info is correct.");
		}
		
		if(DateFunctions.compareDates(expInventoryType.createDate, inventoryTypeFromDB.get(0)[3]) !=0){
			result &= false;
			MiscFunctions.compareResult("Create Date info is not correct.", expInventoryType.createDate, inventoryTypeFromDB.get(0)[3]);
		}else {
			logger.info("Create date info is correct.");
		}
		
		result &= inventoryTypePg.verifyInventoryTypeInfo(expInventoryType);	
		
		if(!result){
			throw new ErrorOnPageException("Inventory type info is not correct. Please check logger info.");
		}else {
			logger.info("Inventory type info is not correct.");
		}
	}

}
