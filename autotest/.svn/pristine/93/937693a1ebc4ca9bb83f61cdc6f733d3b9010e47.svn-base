package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify reconcile POS physical inventory correctly by manual
 * @Preconditions: need an existing POS Product - 4008831831
 * @SPEC: Reconcile POS physical Inventory.UCS [TC:038073]
 * @Task#: AUTO-1142
 * 
 * @author qchen
 * @Date  Jul 30, 2012
 */
public class ReconcileManualPhysicalInventory extends InventoryManagerTestCase {
	private String warehouseName;
	private OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
	private List<String> expectedLog = new ArrayList<String>();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		//check and add POS product
		if(!im.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			im.gotoPosProductSetupPage();
			im.addPOSProduct(pos);
		}
		
		im.gotoPOSInventoryReconciliationPageFromTopMenu();
		//set Physical Qty On Hand identified by POS product name
		String reconciliationID = im.reconcilePOSPhysicalInventory(pos.barcode, pos.product, pos.physicalQtyOnHand);
		int reconciliationProductNums = reconciliationPage.getReconciliationProductNums();
		expectedLog.add(0, reconciliationID);
		expectedLog.add(3, String.valueOf(reconciliationProductNums));
		
		//verify POS Physical Inventory reconciled successfully
		//checkpoint#1: verify the 'Qty On Hand' equals 'Physical Qty On Hand'
		this.verifyQtyOnHand(pos.product, pos.physicalQtyOnHand);
		
		//checkpoint#2: verify the Inventory Reconciliation Log
		im.gotoPOSInventoryReconciliationLogPage();
		this.verifyPOSInventoryReconciliationLogInfo(expectedLog);
		
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		
		warehouseName = "AutoWarehouse";
		
		//POS product info
		pos.barcode = "4008831831";
		pos.product = caseName;//ReconcileManualPhysicalInventory
		pos.productCode = "RMPI";
		pos.productDescription = "Automation Test";
		pos.productGroup = "Agent Fees - Daily";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(95555));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
		
		//expected reconciliation log
		expectedLog.add(DateFunctions.getToday(DataBaseFunctions.getContractTimeZone(schema)));//date & time
		expectedLog.add(login.userName);//user
		expectedLog.add("0");//IMPORTANT: reconciliation exception
	}
	
	private void verifyQtyOnHand(String name, String physicalQty) {
		int actualQty = Integer.parseInt(reconciliationPage.getQtyOnHand(name));
		int expectedQty = Integer.parseInt(physicalQty);
		if(actualQty != expectedQty) {
			throw new ErrorOnPageException("Reconcile POS Physical Inventory failed. Expected Qty is: " + expectedQty + ", but actual is: " + actualQty);
		} else logger.info("Reconcile POS Physical Inventory successfully.");
	}
	
	private void verifyPOSInventoryReconciliationLogInfo(List<String> expected) {
		OrmsPOSInventoryReconciliationLogPage reconciliationLogPage = OrmsPOSInventoryReconciliationLogPage.getInstance();
		
		boolean result = reconciliationLogPage.compareInventoryReconciliationLogInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("POS Inventory Reconciliation Log info is NOT correct.");
		} else logger.info("POS Inventory Reconciliation Log info is correct.");
	}
}
