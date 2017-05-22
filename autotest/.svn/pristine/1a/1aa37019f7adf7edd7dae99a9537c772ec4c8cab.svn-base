/**
 * 
 */
package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.product;

import java.util.Arrays;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.InvMgrPosSetupListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @Description:
 * 				1. Go to IM, Select warehouse 'AutoWarehouse', Go to 'POS Product Setup' page.
 * 				2. Add new POS product.
 * 				3. Search by different conditions, check search result.
 * @Preconditions:
 * 1. the role Administrator has the assigned features: SearchWarehouse
 * 2. make sure the product class 'Auto Test Class' and subclass 'Auto Test SubClass' have been added in MS contract by running sql:
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 4, 'Test Class' );  
 * insert into P_DISPLAY_CATEGORY( id, type_id, description ) values ( get_sequence( 'P_DISPLAY_CATEGORY' ), 5, 'Test SubClass' ); 
 * 
 * @SPEC: TC042835:Search Warehouse POS Product Assignment 
 * @Task#: AUTO-1246
 * Debug log:
 * The total number of search result is in: select value from x_prop where name = 'POSProductSetupMaxProducts'; 
 * If value is 50, it will only display 50 records on the page.
 * 
 * @author pzhu
 * @Date  Sep 11, 2012
 */
public class SearchWarehousePOSAssignment extends InventoryManagerTestCase {
	private String warehouseName, qtyOnHandSearchValue;
	private InvMgrPosSetupListPage posSetUpPg = InvMgrPosSetupListPage.getInstance();
	private String[][][] searchRepository;	
		
	@Override
	public void execute() {
		
		this.preparePrdClass();
		
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosProductSetupPage();
		if(!im.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			im.addPOSProduct(pos);
		}
		pos.productID = im.getProductID("Product Name", pos.product, null, schema);
		
		//Check point 1: search pos product by different conditions.
		this.verifySearchPosProduct();
		
	
		im.logoutInvManager();
	}

	private void verifySearchPosProduct() {
		POSInfo searchCondition;
		List<POSInfo> searchResults;
		for(String[][] cond: searchRepository)
		{
			logger.info("--Try to Search by condition: "+Arrays.toString(cond[0]));
			logger.info("--Try to Search by value    : "+Arrays.toString(cond[1]));
			searchCondition = new POSInfo();
			this.prepareSearchCondition(cond,searchCondition);
			posSetUpPg.searchPOSProduct(searchCondition);
			searchResults = posSetUpPg.getRecordsOnPage();
			
			this.checkSearchResults(searchResults);
			
			logger.info("--Search verify passed!!!");
			
		}
		
	}

	private void checkSearchResults(List<POSInfo> searchResults) {

		boolean result = true;
		boolean found = false;
		for(POSInfo rs: searchResults){
			if(rs.productID.equalsIgnoreCase(pos.productID)){
				result &= MiscFunctions.compareResult("Assign Status", "Yes", rs.assignStatus);
				result &= MiscFunctions.compareResult("Product", pos.product, rs.product);
				result &= MiscFunctions.compareResult("Description", pos.productDescription, rs.productDescription);
				result &= MiscFunctions.compareResult("Product Group", pos.productGroup, rs.productGroup);
				result &= MiscFunctions.compareResult("Product Class", pos.productClass, rs.productClass);
				result &= MiscFunctions.compareResult("Product Sub Class", pos.productSubClass, rs.productSubClass);
				result &= MiscFunctions.compareResult("Inventory Type", pos.inventoryType, rs.inventoryType);
				result &= MiscFunctions.compareResult("Qty On Hand", pos.qtyOnHand, rs.qtyOnHand);
				found = result;
				
				if(!found){
					throw new ErrorOnPageException("POS Product record error on Page(ProductID="+pos.productID+")(ignore Barcode)", StringUtil.ObjToString(pos), StringUtil.ObjToString(rs));
				}
				break;
			}
		}
		
		if(!found){
			throw new ErrorOnPageException("Cannot find any POS product record of which 'POS Product ID' is-->"+pos.productID);
		}
		
		logger.info("Verify POS product record Passed!!!");
	}

	private void prepareSearchCondition(String[][] cond, POSInfo searchCondition) {
		for(int i=0; i<cond[0].length; i++){
			this.setField(cond[0][i],searchCondition , cond[1][i]);
		}
	}
	
	/**Copy value to the field of Object 'to'
	 * @param fieldName
	 * @param Object to
	 * @param value 
	 */
	private void setField(String fieldName, Object to, String value) {
		
		try {
			to.getClass().getDeclaredField(fieldName).set(to,value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
				
	}

	private void preparePrdClass() {
		// insert product class and product subclass into DB if they don't exist
		if (!im.isProductClassExist(schema, pos.productClass)) {
			im.insertProductClass(schema, pos.productClass);
		}
		if (!im.isProductSubClassExist(schema, pos.productSubClass)) {
			im.insertProductSubClass(schema, pos.productSubClass);
		}
		
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/" + TestProperty.getProperty("ms.admin.location");
		
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		warehouseName = "AutoWarehouse";	
		
		// POS info
		pos.productCode = "PETER2012911";
		pos.product = "SearchWarehousePOSAssignment";
		pos.productDescription = pos.product;
		pos.productGroup = "Books";
		pos.productClass = "Auto Test Class"; // Insert it by running sql. See preconditions of the case 
		pos.productSubClass = "Auto Test SubClass"; // Insert it by running sql. See preconditions of the case 
		pos.inventoryType = RESTRICTIVE_INVENTORY_TYPE;
		pos.qtyOnHand = "0";
		qtyOnHandSearchValue = "1";
		// POS location info
		pos.availableLocation = "All Agencies";
		pos.specificLocation = false;
		
		// POS barcode info
		POSInfo.BarCode barcode = pos.new BarCode();
		barcode.barCode = "BAR007111";
		pos.barcodeList.add(barcode);	
		
		searchRepository = new String[][][]{
				// multi-conditions				values
				{{"assignStatus", "productGroup"},{"Assigned Products", pos.productGroup}},
				{{"product"},{pos.product}},
				{{"productGroup"},{pos.productGroup}},
				{{"productClass"},{pos.productClass}},
				{{"productSubClass"},{pos.productSubClass}},
				{{"inventoryType"},{pos.inventoryType}},
				{{"qtyOnHand", "productGroup"},{qtyOnHandSearchValue, pos.productGroup}},
				{{"barcode"},{barcode.barCode}},
				{{"assignStatus","product","productGroup","productClass","productSubClass","inventoryType","qtyOnHand"},
					{"Assigned Products",pos.product,pos.productGroup,pos.productClass,pos.productSubClass, pos.inventoryType, qtyOnHandSearchValue}}
		};
	}
}
