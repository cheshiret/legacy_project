package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryType;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilege.LicMgrPrivilegeProductDetailsPage;
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
 * @Description:This case is used to verify Deactivate privilege inventory type info which assigned privilege product
 * Blocked by DEFECT-33674
 * @Preconditions:
 * @SPEC:Deactivate Privilege Inventory type.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 12, 2012
 */
public class DeactivatePriInvType_HasPrivilege extends LicenseManagerTestCase{
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
		//assign privilege to inventory type
		lm.assignPrivilegeProductToInventoryType(priInventoryType.id, priInventoryType.privilegeProducts);
		//deactivate privilege inventory type
		lm.deactivatePrivilegeInventoryType(priInventoryType.id);
		
		priInventoryType.status = "Inactive";
		priInventoryType.privilegeProducts.clear();
		//verify deactivate inventory type info
		this.verifyDeactivateInventoryTypeInfo(schema, priInventoryType);
		
		//go to privilege detail page
		lm.gotoPrivilegeSearchListPageFromTopMenu();
		lm.gotoPrivilegeDetailsPageFromProductListPage(privilege.code);
		//verify inventory type info from privilege detail page
		priInventoryType.inventoryType = "None";
		this.verifyInventoryTypeFromPrivilegeDetailPage(priInventoryType.inventoryType);

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
		priInventoryType.inventoryType = "Deactivate Inv Type Has";
		priInventoryType.status = "Active";
		priInventoryType.privilegeProducts = new ArrayList<String>();
		priInventoryType.privilegeProducts.add("144 - 3DAYALLGAME/ARCHPRIM/CROS");
		priInventoryType.lastUpdateUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		priInventoryType.lastUpdateLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		priInventoryType.lastUpdateDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);	

		privilege.code = "144";	
	}
	
	private void verifyDeactivateInventoryTypeInfo(String schema, PrivilegeInventoryType expInventoryType){
		LicMgrPrivilegeInventoryTypePage inventoryTypePg = LicMgrPrivilegeInventoryTypePage.getInstance();
		boolean result = true;
		logger.info("Verify Deactivate inventory type info.");
		
		List<String[]> inventoryTypeFromDB = lm.queryPrivilegeInventoryTyeByCode(schema, expInventoryType.inventoryType);
		result &= inventoryTypePg.verifyInventoryTypeInfo(expInventoryType);	
		
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
		
		if(!result){
			throw new ErrorOnPageException("Inventory type info is not correct. Please check logger info.");
		}else {
			logger.info("Inventory type info is not correct.");
		}
	}
	
	private void verifyInventoryTypeFromPrivilegeDetailPage(String expectInventoryType){
		LicMgrPrivilegeProductDetailsPage privilegeDetailsPg = LicMgrPrivilegeProductDetailsPage.getInstance();
		
		logger.info("Verify inventory type info from privilege detail page.");
		String actualValue = privilegeDetailsPg.getInventoryType();
		if(!actualValue.equals(expectInventoryType)){
			throw new ErrorOnDataException("Inventory type info is not correct in privilege detail page.",
					expectInventoryType, actualValue);
		}else {
			logger.info("Inventory type info is correct in privilege detail page.");
		}
	}

}
