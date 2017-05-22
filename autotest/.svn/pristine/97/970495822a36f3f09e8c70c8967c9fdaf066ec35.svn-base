package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.pos.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationLogPage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify POS Inventory reconciliation log
 * @Preconditions: POS Inventory Reconciliation related features have been assigned to role - ''
 * @SPEC: POS Physical Inventory Reconciliation-Search POS Products for Marina, Venue and Permit Manager [TC:042438] Step9
 * @Task#: AUTO-1247
 * 
 * @author qchen
 * @Date  Oct 9, 2012
 */
public class ReconcileImportPhysicalInventory extends VenueManagerTestCase {

	private POSInfo pos = new POSInfo();
	private String filePath, fileName, posInventoryLine;
	private OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
	private List<String> expectedLog = new ArrayList<String>();
	
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		vm.gotoPOSProductSetupPage();
		if(!vm.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			vm.addPOSProduct(pos);
		}
		vm.gotoPOSInventoryReconciliationPage();
		
		FileUtil.generateAndWriteFile(filePath, posInventoryLine);
		try {
			//clean up
			vm.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
			
			//important POS Inventory File
			vm.importPOSInventoryFile(filePath);
			
			//reconcile POS Physical Inventory qty
			vm.gotoPOSInventoryReconciliationFromImportFilePage();
			String reconciliationID = vm.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
			int reconciliationProductNums = reconciliationPage.getReconciliationProductNums();
			expectedLog.add(0, reconciliationID);
			expectedLog.add(3, String.valueOf(reconciliationProductNums));
			
			//verify reconciliation successfully
			//checkpoint#1: verify the 'Qty On Hand' equals 'Physical Qty On Hand'
			reconciliationPage.searchPOSProduct(pos.barcode, pos.product);
			this.verifyQtyOnHand(pos.product, pos.physicalQtyOnHand);
			
			//checkpoint#2: verify the Inventory Reconciliation Log
			vm.gotoPOSInventoryReconciliationLogPage();
			this.verifyPOSInventoryReconciliationLogInfo(expectedLog);
			
			vm.logoutVenueManager();
		} finally {
			FileUtil.deleteFile(filePath);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		String facility = vm.getFacilityName("77817", schema);
		TimeZone timeZone = DataBaseFunctions.getParkTimeZone(schema, facility);
		
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/" + facility;
		
		//POS product info
		pos.barcode = "4008911950";
		pos.product = caseName;
		pos.productCode = "RIPIVM";
		pos.productDescription = "Automation Test";
		pos.productGroup = "POS Pass Sales";
		pos.productRelactionshipType = "Individual";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.unitPrice = "26";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(987654));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		
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
