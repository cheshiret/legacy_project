package com.activenetwork.qa.awo.testcases.regression.basic.orms.license.privilegeinventory;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ChangeHistory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.InventoryTypeLicenseYear;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PriInventoryItemInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventory;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.PrivilegeInventoryAllocation;
import com.activenetwork.qa.awo.pages.orms.licenseManager.privilegeinventory.LicMgrPrivilegeInventoryChangeHistoryPage;
import com.activenetwork.qa.awo.testcases.abstractcases.LicenseManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description:This case is used to verify privilege inventory change history
 * 1. create privilege inventory
 * 2. allocate privilege inventory
 * 3. withdraw privilege inventory
 * 5. reinstate privilege inventory
 * 6. reallocate privilege inventory
 * 7. make privilege inventory as returned
 * @Preconditions:
 * @SPEC:View Privilege Inventory Change History.UIS
 * @Task#:Auto-879

 * @author VZhang
 * @Date Mar 22, 2012
 */
public class ViewPriInventoryChangeHistory extends LicenseManagerTestCase{
	private String priInventoryType = ""; 
	private InventoryTypeLicenseYear invTypeLicenseYear = new InventoryTypeLicenseYear();
	private PrivilegeInventory privilegeInventory = new PrivilegeInventory();
	private PrivilegeInventory searchPriInventory = new PrivilegeInventory();
	private PriInventoryItemInfo priInventoryItem = new PriInventoryItemInfo();
	private PrivilegeInventoryAllocation priInventoryAllocation = new PrivilegeInventoryAllocation();
	private List<ChangeHistory> historyList = new ArrayList<ChangeHistory>();
	private TimeZone timeZone;
	LicMgrPrivilegeInventoryChangeHistoryPage priInventoryChangeHistoryPg = LicMgrPrivilegeInventoryChangeHistoryPage.getInstance();

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
		//add privilege inventory type license year
		lm.gotoInventoryTypeLicenseYearPageFromInventoryTypePage();
		lm.addInvTypeLicenseYear(invTypeLicenseYear);
		
		//add privilege inventory type
		lm.gotoPrivilegeInventoryPageFromInventoryTypePage();
		lm.addPrivilegeInventory(privilegeInventory);
		
		//allocate privilege inventory type
		lm.allocatePrivilegeInventory(priInventoryAllocation);
		
		//withdraw inventory
		lm.searchPrivilegeInventory(searchPriInventory);
		lm.withdrawPrivilegeInventoryItem(priInventoryItem.inventoryNum, true);
		
		//reinstate inventory
		lm.reinstatePrivilegeInventoryItem(priInventoryItem.inventoryNum, priInventoryAllocation);
				
		//reallocate inventory
		priInventoryAllocation.agentName = "EFT Invoice";
		priInventoryAllocation.agentID = lm.getAgentID(schema, priInventoryAllocation.agentName);
		lm.reallocatePrivilegeInventoryItem(priInventoryItem.inventoryNum, priInventoryAllocation);
		historyList.get(1).newValue =priInventoryAllocation.agentID + " - " + priInventoryAllocation.agentName;
		
		//return unused inventory
		lm.returnPrivilegeInventoryItem(priInventoryItem.inventoryNum, true);
		
		lm.gotoPrivilegeInventoryChangeHistoryPgFromPrivilegeInventoryPg(priInventoryItem.inventoryNum);
		//verify inventory info
		priInventoryItem.status = "Returned";
		priInventoryItem.agentInfo = priInventoryAllocation.agentID + " - " + priInventoryAllocation.agentName;
		this.verifyPriInventoryInfo(priInventoryItem);
		
		//verify change history info
		this.verifyChangeHistoryInfo(historyList);
		
		//clear up from DB for inventory type license year
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
		
		priInventoryType = "For Pri Inv History";
		invTypeLicenseYear.inventoryType = priInventoryType;
		invTypeLicenseYear.licenseYear = String.valueOf(DateFunctions.getCurrentYear());
		
		privilegeInventory.inventoryType = invTypeLicenseYear.inventoryType;
		privilegeInventory.inventoryTypeStatus = "Active";
		privilegeInventory.licenseYear = invTypeLicenseYear.licenseYear;
		privilegeInventory.inventoryNumFrom = "001";
		privilegeInventory.inventoryNumTo = "001";
		
		priInventoryAllocation.inventoryType = privilegeInventory.inventoryType;
		priInventoryAllocation.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		priInventoryAllocation.licenseYear = privilegeInventory.licenseYear;
		priInventoryAllocation.vendorNum = "Auto555";
		priInventoryAllocation.vendorName = "Auto Vendor";
		priInventoryAllocation.agentName = "WAL-MART";
		priInventoryAllocation.agentID = lm.getAgentID(schema, priInventoryAllocation.agentName);
		priInventoryAllocation.inventoryNumFrom = "001";
		priInventoryAllocation.inventoryNumTo = "001";	
		
		searchPriInventory.inventoryType = privilegeInventory.inventoryType;
		searchPriInventory.inventoryTypeStatus = privilegeInventory.inventoryTypeStatus;
		searchPriInventory.licenseYear = privilegeInventory.licenseYear;	
		
		priInventoryItem.inventoryNum = "001";
		
		ChangeHistory history1 = new ChangeHistory();
		history1.object = "Privilege Inventory";
		history1.action = "Add";
		history1.field = "";		
		history1.oldValue = "";
		history1.newValue = "";
		history1.user = DataBaseFunctions.getLoginUserName(login.userName);
		history1.location = login.location.split("/")[1].trim();
	    history1.changeDate = DateFunctions.getToday("E MMM d yyyy",timeZone);
	    
		ChangeHistory history2 = new ChangeHistory();
		history2.object = "Privilege Inventory";
		history2.action = "Update";
		history2.field = "Store ID";		
		history2.oldValue = "";
		history2.newValue = priInventoryAllocation.agentID + " - " + priInventoryAllocation.agentName;
		history2.user = history1.user;
		history2.location = history1.location;
		history2.changeDate = history1.changeDate;
	    
		ChangeHistory history3 = new ChangeHistory();
		history3.object = "Privilege Inventory";
		history3.action = "Update";
		history3.field = "Status";		
		history3.oldValue = "Available";
		history3.newValue = "Withdrawn";
		history3.user = history1.user;
		history3.location = history1.location;
		history3.changeDate = history1.changeDate;
		
		ChangeHistory history4 = new ChangeHistory();
		history4.object = "Privilege Inventory";
		history4.action = "Update";
		history4.field = "Status";		
		history4.oldValue = "Withdrawn";
		history4.newValue = "Available";
		history4.user = history1.user;
		history4.location = history1.location;
		history4.changeDate = history1.changeDate;
		
		ChangeHistory history5 = new ChangeHistory();
		history5.object = "Privilege Inventory";
		history5.action = "Update";
		history5.field = "Store ID";		
		history5.oldValue = history2.newValue;
		history5.user = history1.user;
		history5.location = history1.location;
		history5.changeDate = history1.changeDate;
		
		ChangeHistory history6 = new ChangeHistory();
		history6.object = "Privilege Inventory";
		history6.action = "Update";
		history6.field = "Status";		
		history6.oldValue = "Available";
		history6.newValue = "Returned";
		history6.user = history1.user;
		history6.location = history1.location;
		history6.changeDate = history1.changeDate;
		
		historyList.add(history6);
		historyList.add(history5);
		historyList.add(history4);
		historyList.add(history3);
		historyList.add(history2);
		historyList.add(history1);
	}
	
	private void verifyPriInventoryInfo(PriInventoryItemInfo expPriInventoryItemInfo){
		logger.info("Verify privilege inventory info.");
		
		boolean result = priInventoryChangeHistoryPg.verifyInventoryInfo(expPriInventoryItemInfo);
		if(!result){
			throw new ErrorOnPageException("Inventory item info is not correct, please check logger info.");
		}else{
			logger.info("Inventory info is correct.");
		}
	}
	
	private void verifyChangeHistoryInfo(List<ChangeHistory> expectHistoryList){
		List<ChangeHistory> actualHistoryListFromUI= new ArrayList<ChangeHistory>();
		logger.info("Verify change history info.");
		
		actualHistoryListFromUI = priInventoryChangeHistoryPg.getHistoriesInformation();
		if(actualHistoryListFromUI.size()!=expectHistoryList.size()){
			throw new ErrorOnPageException("History List record size is not correct.");
		}
		
		//get actually history list info by expect history list size
		actualHistoryListFromUI = actualHistoryListFromUI.subList(0, expectHistoryList.size());		
		for(int i=0; i<expectHistoryList.size(); i++){
			if(!actualHistoryListFromUI.get(i).equals(expectHistoryList.get(i))){
				throw new ErrorOnPageException("History record about "+ expectHistoryList.get(i).field + " is wrong.");
			}else{
				logger.info("History record is correct.");
			}			
		}
	}
}
