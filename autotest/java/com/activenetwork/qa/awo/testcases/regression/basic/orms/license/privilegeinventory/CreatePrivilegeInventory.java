package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify create privilege inventory, and view inventory type license year
 * @Preconditions:
 * @SPEC:Create Privilege Inventory.UIS, View Inventory Type License Year.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 19, 2012
 */
public class CreatePrivilegeInventory extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private PrivilegeInventory privilegeInventory = new PrivilegeInventory();
	private PrivilegeInventory searchPriInventoryInfo = new PrivilegeInventory();
	private TimeZone timeZone;

	@Override
	public void execute() {
		//clear up from DB for inventory type license year
		lm.deletePriInventoryFromDB(schema, invTypeLicenseYear);
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		
		lm.loginLicenseManager(login);
		lm.gotoPrivilegeInventoryTypePgFromTopMenu();
		//check and clear up for inventory, just add once when changed schema
		List<String[]> inventoryTypeInfo = lm.queryPrivilegeInventoryTyeByCode(schema, priInventoryType);
		if(inventoryTypeInfo.size()<1){
			//add privilege inventory type
			lm.addPrivilegeInventoryTypeInfo(priInventoryType);
		}
		lm.gotoInventoryTypeLicenseYearPageFromInventoryTypePage();
		invTypeLicenseYear.id = lm.addInvTypeLicenseYear(invTypeLicenseYear);
			
		lm.gotoPrivilegeInventoryPageFromInventoryTypeLicenseYearPage();
		lm.addPrivilegeInventory(privilegeInventory);
		lm.searchPrivilegeInventory(searchPriInventoryInfo);
		this.verifyInventoryInfoFromUI(privilegeInventory.inventoryInfos);
		this.verifyInventoryCreatedInfoFromDB(schema, privilegeInventory.inventoryInfos, invTypeLicenseYear);
		
		lm.gotoInventoryTypeLicenseYearPageFromPrivilegeInventoryPg();
		lm.gotoPrivilegeInventoryPgThroughClickViewInventory(invTypeLicenseYear.id);
		this.verifyInventoryInfoFromUI(privilegeInventory.inventoryInfos);
		
		lm.deletePriInventoryFromDB(schema, invTypeLicenseYear);
		lm.deleteInvTypeLicenseYearInfoFromDBByInventoryType(schema, invTypeLicenseYear.inventoryType);
		lm.logOutLicenseManager();				
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "HF Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix")+ "MS";
		timeZone= DataBaseFunctions.getContractTimeZone(schema, "1");
		
		priInventoryType = "Type for Creating Pri Inv";
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = "ALL";
		invTypeLicenseYear.issueFromDate = DateFunctions.getDateAfterToday(2, timeZone);
		invTypeLicenseYear.issueToDate =  DateFunctions.getDateAfterToday(30, timeZone);
		invTypeLicenseYear.cost = "12";
		
		privilegeInventory.inventoryType = invTypeLicenseYear.inventoryType;
		privilegeInventory.inventoryTypeStatus = "Active";
		privilegeInventory.licenseYear = invTypeLicenseYear.licenseYear;
		privilegeInventory.issueFromDate = invTypeLicenseYear.issueFromDate;
		privilegeInventory.issueToDate = invTypeLicenseYear.issueToDate;
		privilegeInventory.inventoryNumFrom = "001";
		privilegeInventory.inventoryNumTo = "002";	
		
		PriInventoryItemInfo inventory1 = new PriInventoryItemInfo();
		inventory1.inventoryNum = "001";
		inventory1.status = "Available";
		inventory1.agentInfo = "";
		inventory1.order = "";
		inventory1.privielgeNumber = "";
		inventory1.createUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		inventory1.createLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		inventory1.createDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);	
		
		PriInventoryItemInfo inventory2 = new PriInventoryItemInfo();
		inventory2.inventoryNum = "002";
		inventory2.status = "Available";
		inventory2.agentInfo = "";
		inventory2.order = "";
		inventory2.privielgeNumber = "";
		inventory2.createUser = DataBaseFunctions.getLoginUserID(schema, login.userName);
		inventory2.createLocation = lm.getLocationID(schema, login.location.split("/")[1].trim());
		inventory2.createDate = DateFunctions.getToday("yyyy/MM/dd",timeZone);
		
		privilegeInventory.inventoryInfos = new ArrayList<PriInventoryItemInfo>();
		privilegeInventory.inventoryInfos.add(inventory1);
		privilegeInventory.inventoryInfos.add(inventory2);	
		
		searchPriInventoryInfo.inventoryType = privilegeInventory.inventoryType;
		searchPriInventoryInfo.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		searchPriInventoryInfo.licenseYear = privilegeInventory.licenseYear;
		searchPriInventoryInfo.issueFromDate = privilegeInventory.issueFromDate;
		searchPriInventoryInfo.issueToDate = privilegeInventory.issueToDate;		
	}
	
	private void verifyInventoryInfoFromUI(List<PriInventoryItemInfo> expInventoryLists){
		LicMgrPrivilegeInventoryPage privilegeInventoryPg = LicMgrPrivilegeInventoryPage.getInstance();
		boolean result = true;
		logger.info("Verify inventory info.");
		int actualItemCount = privilegeInventoryPg.getPriInventoryItemRecordsCount();
		if(expInventoryLists.size() != actualItemCount){
			result &= false;
			MiscFunctions.compareResult("Privilege inventory item count is not correct.", expInventoryLists.size(), actualItemCount);
		}
		
		for(int i=0; i<expInventoryLists.size();i++){
			result &= privilegeInventoryPg.verifyPrivilegeInventoryInfo(expInventoryLists.get(i));
		}
		
		if(!result){
			throw new ErrorOnPageException("Inventory list info is not correct, please check error log.");
		}else {
			logger.info("Inventory list info is corret from UI.");
		}		
	}
	
	private void verifyInventoryCreatedInfoFromDB(String schema,List<PriInventoryItemInfo> expInventoryLists,InventoryTypeLicenseYear invTypeLicYear){
		logger.info("Verify inventory info from DB.");
		boolean result = true;
		
		for(int i=0; i<expInventoryLists.size(); i++){
			List<String[]> values = lm.getPriInventoryInfoFromDB(schema, expInventoryLists.get(i).inventoryNum, invTypeLicYear);
			
			if(!values.get(0)[0].equals(expInventoryLists.get(i).createUser)){
				result &= false;
				MiscFunctions.compareResult("Create user is not correct.", expInventoryLists.get(i).createUser, values.get(0)[0]);
			}else {
				logger.info("Create user is correct.");
			}
			
			if(!values.get(0)[1].equals(expInventoryLists.get(i).createLocation)){
				result &= false;
				MiscFunctions.compareResult("Create location is not correct.", expInventoryLists.get(i).createLocation, values.get(0)[1]);
			}else {
				logger.info("Create location is correct.");
			}
			
			if(DateFunctions.compareDates(expInventoryLists.get(i).createDate, values.get(0)[2]) !=0){
				result &= false;
				MiscFunctions.compareResult("Create date is not correct.", expInventoryLists.get(i).createDate, values.get(0)[2]);
			}else {
				logger.info("Create date is correct.");
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("Inventory list info is not correct, please check error log.");
		}else {
			logger.info("Inventory list info is corret from DB.");
		}		
	}
}
