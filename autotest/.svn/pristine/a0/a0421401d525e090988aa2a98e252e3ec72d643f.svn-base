package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.posinventorylistreport.inventory;

import java.util.List;
import java.util.Random;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.inventory.InvMgrPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Print Inventory List in POS Inventory Reconciliation page, it actually is the same with 'POS Inventory List Report'
 * @Preconditions: need an warehouse - AutoWarehouse
 * @SPEC: POS Inventory List Report [TC:038856]
 * @Task#: AUTO-1142	
 * 
 * @author qchen
 * @Date  Aug 1, 2012
 */
public class POSInventoryListReport extends InventoryManagerTestCase {
	private String warehouseName, path;
	private InvMgrPOSInventoryReconciliationPage reconciliationPage = InvMgrPOSInventoryReconciliationPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoWarehousesSearchPg();
		im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		if(!im.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			im.gotoPosProductSetupPage();
			pos.productID = im.addPOSProduct(pos);
		} else {
			pos.productID = im.getProductID("Product Name", pos.product, null, schema);
		}
		im.gotoPOSInventoryReconciliationPageFromTopMenu();
		//reconcile to set Qty on Hand as previous Physical Qty on Hand
		im.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
		//get Qty on Hand
		reconciliationPage.searchPOSProduct(pos.barcode, pos.product);
		pos.qtyOnHand = reconciliationPage.getQtyOnHand(pos.product);
		//set new Physical Qty on Hand
		this.setPhysicalQtyOnHand();
		
		//run report - click 'Print Inventory List'
		String fullPathAndFileName = im.printInventoryList(path);
		List<String> posOnRpt = PDFParser.getPDFContentInRow(fullPathAndFileName, pos.barcode);
		
		//verify the POS info in report is correct with it in System
		this.verifyPOSInfoByContain(pos, posOnRpt);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		warehouseName = "AutoWarehouse";
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "MS";
		path = logger.getLogPath() + "/ComparedFile";
		
		//POS product info
		pos.barcode = "4008111112";
		pos.product = caseName + "_IM";
		pos.productCode = "PILRIM";
		pos.productDescription = "Print Inventory List-IM";
		pos.productGroup = "Agent Fees - Daily";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(666666));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
	}
	
	private void setPhysicalQtyOnHand() {
		reconciliationPage.searchPOSProduct(pos);
		reconciliationPage.setPhysicalQtyOnHand(pos.product, pos.physicalQtyOnHand);
		reconciliationPage.clickApply();

		ajax.waitLoading();
		reconciliationPage.waitLoading();
	}
	
	private void verifyPOSInfo(POSInfo expected, List<String> posLineInfo) {
		logger.info("Verify POS info is correct in 'POS Inventory List Report'.");
		String actual[] = posLineInfo.get(0).split(";");
		boolean result = true;
		result &= MiscFunctions.compareResult("POS Product ID", expected.productID, actual[0]);
		result &= MiscFunctions.compareResult("POS Product Name", expected.product, actual[6]);
		result &= MiscFunctions.compareResult("POS Product Description", expected.productDescription, actual[7]);
		result &= MiscFunctions.compareResult("POS Product Group", expected.productGroup, actual[8]);
		result &= MiscFunctions.compareResult("POS Product Barcode", expected.barcode, actual[2]);
		result &= MiscFunctions.compareResult("POS Product Current Qty. in Hand", expected.qtyOnHand, actual[3]);
		result &= MiscFunctions.compareResult("POS Product Current Physical Qty. in Hand", expected.physicalQtyOnHand, actual[4]);
		
		if(!result) {
			throw new ErrorOnPageException("'POS Inventory List Report' is incorrect.");
		} else logger.info("'POS Inventory List Report' is correct.");
	}
	private void verifyPOSInfoByContain(POSInfo expected, List<String> posLineInfo) {
		logger.info("Verify POS info is correct in 'POS Inventory List Report'.");
		String actual = posLineInfo.get(0).toUpperCase();

		Assert.assertTrue("POS Product ID", actual.contains(expected.productID.toUpperCase()));
		Assert.assertTrue("POS Product Name", actual.contains(expected.product.toUpperCase()));
		Assert.assertTrue("POS Product Description", actual.contains(expected.productDescription.toUpperCase()));
		Assert.assertTrue("POS Product Group", actual.contains(expected.productGroup.toUpperCase()));
		Assert.assertTrue("POS Product Barcode", actual.contains(expected.barcode.toUpperCase()));
		Assert.assertTrue("POS Product Current Qty. in Hand", actual.contains(expected.qtyOnHand.toUpperCase()));
		Assert.assertTrue("POS Product Current Physical Qty. in Hand", actual.contains(expected.physicalQtyOnHand.toUpperCase()));
		
	
		logger.info("'POS Inventory List Report' is correct.");
	}
}
