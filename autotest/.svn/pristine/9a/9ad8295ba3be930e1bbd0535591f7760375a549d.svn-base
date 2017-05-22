package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierDetailsPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify adding POS Supplier on Contract level successfully
 * @Preconditions: need 'AddPOSSupplier' feature assigned
 * @SPEC: Add POS Supplier TC037517
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 23, 2012
 */
public class AddOnContractLevel extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		//go to POS Supplier Setup page from IM home page
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		
		//verify the new added supplier list info
		supplierSearchPage.searchPosSupplierById(supplier.id);
		this.verifyPOSSupplierListInfo(supplier);

		//verify the new added supplier details info
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyPOSSupplierDetailsInfo(supplier);
		this.gobackSupplierSearchPage();
		
		//clean up - deactivate this POS Supplier
		im.deactivatePOSSupplier(supplier.id);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.status = OrmsConstants.ACTIVE_STATUS;
//		supplier.location = "All Agencies";
		supplier.location = "STATE PARKS";
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "East A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "East A Street";
		supplier.paymentAddress.city = "Xian";
		supplier.paymentAddress.state = "Mississippi";
		supplier.paymentAddress.zip = "36159";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "Quentin";
		supplier.primPoc.firstName = "Chen";
		supplier.primPoc.phone = "02968685958";
		supplier.primPoc.fax = "02968685958";
		supplier.primPoc.email = "Quentin.Chen@activenetwork.com";
		supplier.otherPoc.lastName = "Shane";
		supplier.otherPoc.firstName = "Song";
		supplier.otherPoc.phone = "02968685958";
		supplier.otherPoc.fax = "02968685958";
		supplier.otherPoc.email = "Shane.Song@activenetwork.com";
		supplier.paymentTerms = "Due on Receipt";
		supplier.paymentMethod = "Credit Card";
		supplier.shippingMethod = "Fedex";
	}
	
	private void verifyPOSSupplierListInfo(PosSupplier expected) {
		logger.info("Verify POS Supplier(ID=" + expected.id + ") list info.");
		
		boolean result = supplierSearchPage.comparePOSSupplierListInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("The actual POS Supplier list info doesn't match with expected.");
		} else logger.info("The actual POS Supplier list info match with expected.");
	}
	
	private void verifyPOSSupplierDetailsInfo(PosSupplier expected) {
		InvMgrPOSSupplierDetailsPage supplierDetailsPage = InvMgrPOSSupplierDetailsPage.getInstance();
		
		logger.info("Verify POS Supplier(ID=" + expected.id + ") details info.");
		boolean result = supplierDetailsPage.compareSupplierDetailsInfo(expected);
		if(!result) {
			throw new ErrorOnPageException("The actual POS Supplier details info doesn't match with expected.");
		} else logger.info("The actual POS Supplier details info match with expected.");
	}
	
	private void gobackSupplierSearchPage() {
		InvMgrPOSSupplierDetailsPage supplierDetailsPage = InvMgrPOSSupplierDetailsPage.getInstance();
		InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
		
		supplierDetailsPage.clickCancel();
		ajax.waitLoading();
		supplierSearchPage.waitLoading();
	}
}
