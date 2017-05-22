package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.inventory;

import java.util.Random;
import java.util.TimeZone;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInventoryFileLogInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSImportInventoryFilePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryFileLogPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.FileUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: verify the importing POS inventory file(MULTIPLE records) function works correctly with warehouse location level
 * @Preconditions: need an existing(production) POS Product - 4008825825
 * @SPEC: 	Import Inventory File.UCS [TC:038066]
 * @Task#: AUTO-1142
 * 
 * @author qchen
 * @Date  Jul 30, 2012
 */
public class ImportPOSInventoryFile extends InventoryManagerTestCase {
	private String filePath, fileName, posInventoryLine1, posInventoryLine2, posInventoryLine3, totalInventoryLines, warehouseName;
	private InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
	private POSInventoryFileLogInfo posFileLog = new POSInventoryFileLogInfo();
	
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
		
		//generate import Inventory file
		FileUtil.generateAndWriteFile(filePath, totalInventoryLines);
		try {
			//clean up
			im.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
			
			//important POS Inventory File
			String importID = im.importPOSInventoryFile(filePath);
			posFileLog.setFileImportID(importID);
			
			//checkpoint#1. verify the success message after importing
			this.verifySuccessMsgOnImportFilePage(fileName);
			
			//checkpoint#2. verify the Physical Qty On Hand equals to inventory number set in file
			im.gotoPOSInventoryReconciliationFromImportFilePage();
			reconciliationPage.searchPOSProduct(pos.barcode, pos.product, pos.productGroup);
			this.verifyPhysicalQuantityOnHand(pos.product, String.valueOf(Integer.parseInt(pos.physicalQtyOnHand) * 3));
			
			//checkpoint#3. verify the Reconciliation Id is null in POS Inventory File Log page
			im.gotoPOSInventoryFileLogPage();
			this.verifyPOSInventoryFileLogInfo(posFileLog);
			
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
		pos.barcode = "4008825825";
		pos.product = caseName;
		pos.productCode = "IPIF";
		pos.productDescription = "Automation Test";
		pos.productGroup = "Agent Fees - Daily";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(9020));//max digits is 6
		POSInfo.BarCode bc1 = pos.new BarCode();
		POSInfo.BarCode bc2 = pos.new BarCode();
		POSInfo.BarCode bc3 = pos.new BarCode();
		bc1.barCode = pos.barcode;
		bc2.barCode = "4008825826";
		bc3.barCode = "4008825827";
		pos.barcodeList.add(bc1);
		pos.barcodeList.add(bc2);
		pos.barcodeList.add(bc3);//the 'Duplicate barcode in import file'
		
		//Import Inventory File Info
		fileName = pos.productCode + "_" + DateFunctions.getCurrentTime() + ".csv";
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
		posFileLog.setNumberOfRecordsImports(3);//IMPORTANT: 3 lines record in file
		posFileLog.setReconciliationID("");
		posFileLog.setExceptions(0);
		
		//import records
		posInventoryLine1 = bc1.barCode + "," + pos.physicalQtyOnHand + "," + posFileLog.getDate() + "," + posFileLog.getTime();
		posInventoryLine2 = bc2.barCode + "," + pos.physicalQtyOnHand + "," + posFileLog.getDate() + "," + posFileLog.getTime();
		posInventoryLine3 = bc3.barCode + "," + pos.physicalQtyOnHand + "," + posFileLog.getDate() + "," + posFileLog.getTime();
		totalInventoryLines = posInventoryLine1 + "\r\n" + posInventoryLine2 + "\r\n" + posInventoryLine3;
	}
	
	private void verifySuccessMsgOnImportFilePage(String name) {
		InvMgrPOSImportInventoryFilePage importPage = InvMgrPOSImportInventoryFilePage.getInstance();
		
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
		InvMgrPOSInventoryFileLogPage invFileLogPage = InvMgrPOSInventoryFileLogPage.getInstance();

		boolean result = invFileLogPage.compareInventoryFileLogInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("POS Inventory File Log info is NOT correct.");
		} else logger.info("POS Inventory File Log info is correct.");
	}
}
