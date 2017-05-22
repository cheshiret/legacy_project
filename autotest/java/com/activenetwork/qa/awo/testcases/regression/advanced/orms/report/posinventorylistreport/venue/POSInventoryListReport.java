package com.activenetwork.qa.awo.testcases.regression.advanced.orms.report.posinventorylistreport.venue;

import java.util.List;
import java.util.Random;
import java.util.TimeZone;

import org.junit.Assert;

import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.pages.orms.common.pos.inventorymgt.OrmsPOSInventoryReconciliationPage;
import com.activenetwork.qa.awo.testcases.abstractcases.VenueManagerTestCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.PDFParser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * 
 * @Description: Print Inventory List in POS Inventory Reconciliation page, it actually is the same with 'POS Invenotry List Report'
 * @Preconditions:
 * @SPEC: POS Physical Inventory Reconciliation-Search POS Products for Marina, Venue and Permit Manager [TC:042438] Step6
 * @Task#: AUTO-1247
 * 
 * @author qchen
 * @Date  Oct 9, 2012
 */
public class POSInventoryListReport extends VenueManagerTestCase {
	
	private POSInfo pos = new POSInfo();
	private OrmsPOSInventoryReconciliationPage reconciliationPage = OrmsPOSInventoryReconciliationPage.getInstance();
	private String path;
	
	@Override
	public void execute() {
		vm.loginVenueManager(login);
		vm.gotoPOSProductSetupPage();
		if(!vm.verifyProductExistInSys(schema, pos.productCode, pos.product)) {
			pos.productID = vm.addPOSProduct(pos);
		} else {
			pos.productID = vm.getProductID("Product Name", pos.product, null, schema);
		}
		vm.gotoPOSInventoryReconciliationPage();
		//reconcile to let Qty on Hand as previous Physical Qty on Hand
		vm.reconcilePOSPhysicalInventory(pos.barcode, pos.product);
		//get Qty on Hand
		pos.qtyOnHand = reconciliationPage.getQtyOnHand(pos.product);
		//set Physical Qty on Hand
		this.setPhysicalQtyOnHand();
		
		//run report - click 'Print Inventory List'
		String fullPathAndFileName = vm.printInventoryList(path);
		List<String> posOnRpt = PDFParser.getPDFContentInRow(fullPathAndFileName, pos.barcode);
		
		//verify the POS info in report is correct with it in System
		this.verifyPOSInfoByContain(pos, posOnRpt);
		vm.logoutVenueManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		schema = TestProperty.getProperty(env + ".db.schema.prefix") + "NRRS";
		String facility = vm.getFacilityName("77817", schema);
		TimeZone timeZone = DataBaseFunctions.getParkTimeZone(schema, facility);
		
		login.contract = "NRRS Contract";
		login.location = "NRRS - Venue Supervisor/" + facility;
		
		path = logger.getLogPath() + "/ComparedFile";
		
		//POS product info
		pos.barcode = "4008922920";
		pos.product = caseName + "_VM";
		pos.productCode = "PILRVM";
		pos.productDescription = "Print Inventory List-VM";
		pos.productGroup = "POS Pass Sales";
		pos.productRelactionshipType = "Individual";
		pos.inventoryType = "Non-Restrictive Inventory";
		pos.physicalQtyOnHand = String.valueOf(new Random().nextInt(777777));//max digits is 6
		POSInfo.BarCode bc = pos.new BarCode();
		bc.barCode = pos.barcode;
		pos.barcodeList.add(bc);
		pos.unitPrice = "7";
		pos.effectiveSalesStartDate = DateFunctions.getToday(timeZone);
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
