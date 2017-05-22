package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify reconcile POS Physical Inventory Qty(MULTIPLE records) by importing inventory file successfully within warehouse level
 * @Preconditions: need an existing POS Product - 4008830830
 * Should unassign feature 'HidePOSReconciliationQtyOnHand' for Inventory Manager.
 * @SPEC: Reconcilie POS physical Inventory.UCS [TC:038073]
 * @Task#: AUTO-1142
 * 
 * @author qchen
 * @Date  Jul 30, 2012
 */
public class ReconcileImportPhysicalInventory extends InventoryManagerTestCase {
	private String filePath, fileName, posInventoryLine, warehouseName;
	private OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
	private List<String> expectedLog = new ArrayList<String>();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		if(!im.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			im.gotoPosProductSetupPage();
			im.addPOSProduct(pos);
		}
		im.gotoPOSInventoryReconciliationPageFromTopMenu();
		
		FileUtil.generateAndWriteFile(filePath, posInventoryLine);
		try {
			//clean up
			im.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
			
			//important POS Inventory File
			im.importPOSInventoryFile(filePath);
			
			//reconcile POS Physical Inventory qty
			im.gotoPOSInventoryReconciliationFromImportFilePage();
			String reconciliationID = im.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
			int reconciliationProductNums = reconciliationPage.getReconciliationProductNums();
			expectedLog.add(0, reconciliationID);
			expectedLog.add(3, String.valueOf(reconciliationProductNums));
			
			//verify reconciliation successfully
			//checkpoint#1: verify the 'Qty On Hand' equals 'Physical Qty On Hand'
			reconciliationPage.searchPOSProduct(pos.barcode, pos.product);
			this.verifyQtyOnHand(pos.product, pos.physicalQtyOnHand);
			
			//checkpoint#2: verify the Inventory Reconciliation Log
			im.gotoPOSInventoryReconciliationLogPage();
			this.verifyPOSInventoryReconciliationLogInfo(expectedLog);
			
			im.logoutInvManager();
		} finally {
			FileUtil.deleteFile(filePath);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		warehouseName = "AutoWarehouse";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		TimeZone timeZone = DataBaseFunctions.getContractTimeZone(schema);
		
		//POS product info
		pos.barcode = "4008830830";
		pos.product = caseName;
		pos.productCode = "RIPI";
		pos.productDescription = "Automation Test";
		pos.productGroup = "Agent Fees - Daily";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(456789));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
		
		//Import Inventory File Info
		fileName = pos.productCode + "_" + DateFunctions.getCurrentTime() + ".csv";
		filePath = AwoUtil.CSV_ROOT 
				//+ casePath.replaceAll(OrmsConstants.PACKAGE_PREFIX, "") 
				//file path is too long
				+ "/" + fileName;
		filePath = filePath.replace("/", "\\");
		
		//import inventory records info
		posInventoryLine = pos.barcode + "," + pos.physicalQtyOnHand + "," + DateFunctions.getToday("yyyy/MM/dd", timeZone) + "," + DateFunctions.getCurrentTimeFormated(true, true);
		
		//expected reconciliation log
		expectedLog.add(DateFunctions.getToday(timeZone));//date & time
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
