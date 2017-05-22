package com.activenetwork.qa.awo.testcases.regression.basic.orms.venue.pos.inventory;

import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryFileLogInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSImportInventoryFilePage;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryFileLogPage;
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
 * @Description: verify importing POS inventory file functionality working correctly
 * @Preconditions: POS related features have been assigned to role - 'NRRS - Venue Supervisor'
 * @SPEC: POS Physical Inventory Reconciliation-Import inventory file for Marina, Venue and Permit Manager [TC:042439]
 * 				POS Physical Inventory Reconciliation-File Log for Marina, Venue and Permit Manager [TC:042440]
 * @Task#: AUTO-1247
 * 
 * @author qchen
 * @Date  Oct 8, 2012
 */
public class ImportPOSInventoryFile extends VenueManagerTestCase {
	
	private POSInfo pos = new POSInfo();
	private String filePath, fileName, posInventoryLine;
	private OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
	private POSInventoryFileLogInfo posFileLog = new POSInventoryFileLogInfo();
	
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		vm.gotoPOSProductSetupPage();
		if(!vm.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			vm.addPOSProduct(pos);
		}
		vm.gotoPOSInventoryReconciliationPage();
		
		//generate Inventory File
		FileUtil.generateAndWriteFile(filePath, posInventoryLine);
		try{
			//clean up
			vm.reconcilePOSPhysicalInventory(pos.barcode, pos.product);//to clean up previous dirty qty
			
			//import POS Inventory File
			String importID = vm.importPOSInventoryFile(filePath);
			posFileLog.setFileImportID(importID);
			
			//checkpoint#1. verify the success message after importing
			this.verifySuccessMsgOnImportFilePage(fileName);
			
			//checkpoint#2. verify the Physical Qty On Hand equals to inventory number set in file
			vm.gotoPOSInventoryReconciliationFromImportFilePage();
			reconciliationPage.searchPOSProduct(pos.barcode, pos.product, pos.productGroup);
			this.verifyPhysicalQuantityOnHand(pos.product, pos.physicalQtyOnHand);
			
			//checkpoint#3. verify the Reconciliation Id is null in POS Inventory File Log page
			vm.gotoPOSInventoryFileLogPage();
			this.verifyPOSInventoryFileLogInfo(posFileLog);
			vm.logoutVenueManager();
		}  finally {
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
		pos.barcode = "4008911920";
		pos.product = caseName;
		pos.productCode = "IPIFVM";
		pos.productDescription = "Automation Test";
		pos.productGroup = "POS Pass Sales";
		pos.productRelactionshipType = "Individual";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(999999));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
		pos.unitPrice = "13";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
		
		//Import Inventory File Info
		fileName = pos.productCode + "_" + DateFunctions.getCurrentTime() + ".txt";
		filePath = AwoUtil.CSV_ROOT 
				//+ casePath.replaceAll(OrmsConstants.PACKAGE_PREFIX, "") 
				//file path is too long
				+ "/" + fileName;
		filePath = filePath.replace("/", "\\");
		
		//POS Inventory File Log info
		posFileLog.setDate(DateFunctions.getToday("yyyy/MM/dd", timeZone));
		posFileLog.setTime(DateFunctions.getCurrentTimeFormated(true, true));
		posFileLog.setFileName(fileName);
		posFileLog.setImportUser(login.userName);
		posFileLog.setNumberOfRecordsImports(1);
		posFileLog.setReconciliationID("");
		posFileLog.setExceptions(0);
		
		//import records
		posInventoryLine = pos.barcode + "," + pos.physicalQtyOnHand + "," + posFileLog.getDate() + "," + posFileLog.getTime();
	}
	
	private void verifySuccessMsgOnImportFilePage(String name) {
		OrmsPOSImportInventoryFilePage importPage = OrmsPOSImportInventoryFilePage.getInstance();
		
		String msgOnPage = importPage.getSuccessMsg();
		if(msgOnPage.startsWith("0") || !msgOnPage.contains(name)) {
			throw new ErrorOnPageException("Import POS Inventory File failed.");
		} else logger.info("Import POS Inventory File - " + name + " successfully.");
	}
	
	private void verifyPhysicalQuantityOnHand(String productName, String qty) {
		String actualQty = reconciliationPage.getPhysicalQtyOnHandByName(productName);
		if(Integer.parseInt(qty) != Integer.parseInt(actualQty)) {
			throw new ErrorOnPageException("Physical Qty On Hand of POS - " + productName + " is NOT correct with number set in inventory file.", qty, actualQty);
		}
	}
	
	private void verifyPOSInventoryFileLogInfo(POSInventoryFileLogInfo expected) {
		OrmsPOSInventoryFileLogPage invFileLogPage = OrmsPOSInventoryFileLogPage.getInstance();
		
		boolean result = invFileLogPage.compareInventoryFileLogInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("POS Inventory File Log info is NOT correct.");
		} else logger.info("POS Inventory File Log info is correct.");
	}
}
