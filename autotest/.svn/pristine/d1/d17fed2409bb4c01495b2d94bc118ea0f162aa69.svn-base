package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryTypePage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify Activate privilege inventory type info
 * Blocked by DEFECT-33674
 * @Preconditions:
 * @SPEC:Activate Privilege Inventory type.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 9, 2012
 */
public class ActivatePrivilegeInventoryType extends LicenseManagerTestCase{
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
		inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType.inventoryType);
		priInventoryType.id = inventoryTypeInfo.get(0)[0];
		//deactivate privilege inventory type
		lm.deactivatePrivilegeInventoryType(priInventoryType.id);
		
		//activate privielge inventory type
		lm.activatePrivilegeInventoryType(priInventoryType.id);
		priInventoryType.status = "Active";
		//verify activate privilege inventory info
		this.verifyActivateInventoryTypeInfo(schema, priInventoryType);
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
		priInventoryType.inventoryType = "Activate Inventory Type";
		priInventoryType.lastUpdateUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		priInventoryType.lastUpdateLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		priInventoryType.lastUpdateDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);			
	}
	
	private void verifyActivateInventoryTypeInfo(String schema, PrivilegeInventoryType expInventoryType){
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage.getInstance();
		logger.info("Verify Activate inventory type info.");
		
		List<String[]> inventoryTypeFromDB = lm.queryPrivilegeInventoryTyeByCode(schema, expInventoryType.inventoryType);
		boolean result = true;
		
		if(!expInventoryType.lastUpdateUser.equalsIgnoreCase(inventoryTypeFromDB.get(0)[4])){
			result &= false;
			MiscFunctions.compareResult("Last update user info is not correct.", expInventoryType.lastUpdateUser, inventoryTypeFromDB.get(0)[4]);
		}else {
			logger.info("Last update user info is correct.");
		}
		
		if(!expInventoryType.lastUpdateLocation.equalsIgnoreCase(inventoryTypeFromDB.get(0)[5])){
			result &= false;
			MiscFunctions.compareResult("Last update location info is not correct.", expInventoryType.lastUpdateLocation, inventoryTypeFromDB.get(0)[5]);
		}else {
			logger.info("Last update location info is correct.");
		}
		
		if(DateFunctions.compareDates(expInventoryType.lastUpdateDate, inventoryTypeFromDB.get(0)[6]) !=0){
			result &= false;
			MiscFunctions.compareResult("Last update date info is not correct.", expInventoryType.createDate, inventoryTypeFromDB.get(0)[6]);
		}else {
			logger.info("Last update date info is correct.");
		}
		
		expInventoryType.id = inventoryTypeFromDB.get(0)[0];
		result &= inventoryTypePg.verifyInventoryTypeInfo(expInventoryType);	
		
		if(!result){
			throw new ErrorOnPageException("Inventory type info is not correct. Please check logger info.");
		}else {
			logger.info("Inventory type info is not correct.");
		}
	}

}
