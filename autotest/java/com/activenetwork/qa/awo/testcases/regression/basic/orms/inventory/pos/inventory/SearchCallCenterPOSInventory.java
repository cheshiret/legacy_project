package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryCallCenterAllocationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description: This test case is for PCR 2956.
 * Search Call Center POS Inventory in Inventory Manager.
 * @Preconditions:
 * Make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * (handle in test case.)
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: TC043072
 * @Task#:AUTO-1245
 * 
 * @author nding1
 * @Date  Sep 17, 2012
 */
public class SearchCallCenterPOSInventory extends InventoryManagerTestCase {
	private InvMgrPOSInventoryCallCenterAllocationPage callCenterAllocationPage = InvMgrPOSInventoryCallCenterAllocationPage.getInstance();
	private String warehouseName;
	private String rangeFrom;
	private String rangeTo;
	private String warningMsg;

	@Override
	public void execute() {
		this.preparePrdClass();

		// go to warehouse details page
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPOSInventoryManagementPageFromTopMenuPage();
		im.gotoPOSInventoryCallCenterAllocationPage();
		
		// Search by Inventory Type (Restrictive Inventory)
		pos.allocateStatus = "";
		pos.productClass = "";
		pos.productSubClass = "";
		pos.inventoryType = OrmsConstants.RESTRICTIVE_INVENTORY_TYPE;
		callCenterAllocationPage.searchPOS(pos);
		// verify research result
		this.verifySearchResult("Inventory Type", pos.inventoryType);

		// Search by Inventory Type (Serialized Inventory)
		pos.inventoryType = SERIALIZED_INVENTORY_TYPE;
		rangeFrom = "1";
		rangeTo = "90";
		callCenterAllocationPage.searchPOS(pos, rangeFrom, rangeTo);
		// verify research result
		this.verifySearchResult("Inventory Type", pos.inventoryType);
		this.verifySearchResult("Serialization Range From", rangeFrom);
		this.verifySearchResult("Serialization Range To", rangeTo);

		// Search by Allocation Status (Allocated)
		pos.inventoryType = "";
		rangeFrom = "";
		rangeTo = "";
		pos.allocateStatus = POS_ALLOCATED;
		callCenterAllocationPage.searchPOS(pos);
		// verify research result
		this.verifySearchResult("Allocation Status", pos.allocateStatus);
		
		
		// Search by Allocation Status (Not Allocated)
		pos.allocateStatus = POS_DEALLOCATED;
		callCenterAllocationPage.searchPOS(pos);
		// verify research result
		this.verifySearchResult("Allocation Status", pos.allocateStatus);
		
		// Search by Product Class and Product Sub-Class
		pos.allocateStatus = "";
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		callCenterAllocationPage.searchPOS(pos);
		// verify research result
		this.verifySearchResult("Product Class", pos.productClass);
		this.verifySearchResult("Product Sub-Class", pos.productSubClass);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";

		warehouseName = "AutoWarehouse";
		warningMsg = "No POS Products/Serialized Ranges found for the search criteria";
	}
	
	private void preparePrdClass() {
		pos.productClass = "Auto Test Class";
		pos.productSubClass = "Auto Test SubClass";
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
	}

	/**
	 * Verify search result.
	 * @param columnName
	 * @param expectValue
	 */
	private void verifySearchResult(String columnName, String expectValue) {
		boolean result = true;
		List<String> resultList = callCenterAllocationPage.getColumnValueByNam(columnName);
		if(null == resultList || resultList.size() < 1){
			if(!warningMsg.equalsIgnoreCase(callCenterAllocationPage.getMessage())) {
				throw new ErrorOnPageException("Should display messsage:"+warningMsg+", but actual message is:"+callCenterAllocationPage.getMessage());
			} else {
				logger.info(warningMsg);
			}
		} else {
			for(int i=0; i<resultList.size(); i++){
				result &= MiscFunctions.compareResult(columnName, expectValue, resultList.get(i));
			}
		}

		if(result){
			logger.info("The search result is correct.");
		} else {
			throw new  ErrorOnPageException("---Check logs above.");
		}
	}
}
