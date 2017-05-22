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
 * @Description: verify editing POS Supplier functions works correctly
 * @Preconditions: Agency location - 'MSHF' should be assigned to role - 'Administrator'
 * @SPEC: Edit POS Supplier TC037521
 * @Task#: AUTO-1140
 * 
 * @author qchen
 * @Date  Jul 25, 2012
 */
public class EditOnAgencyLevel extends InventoryManagerTestCase {

	private PosSupplier supplier = new PosSupplier();
	private InvMgrPOSSupplierSearchPage supplierSearchPage = InvMgrPOSSupplierSearchPage.getInstance();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		//go to POS Supplier Setup page from IM home page
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		
		//edit POS Supplier
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.initialEditingSupplierInfo();
		supplier.id = im.editPosSupplier(supplier);
		
		//verify the edited supplier list info
		supplierSearchPage.searchPosSupplierById(supplier.id);
		this.verifyPOSSupplierListInfo(supplier);

		//verify the edited supplier details info
		im.gotoPOSSupplierDetailsPage(supplier.id);
		this.verifyPOSSupplierDetailsInfo(supplier);
		im.gotoPOSSupplierSearchPageFromDetailsPage();
		
		//clean up - deactivate this POS Supplier
		im.deactivatePOSSupplier(supplier.id);
		im.logoutInvManager();
	}

	@Override
	public void wrapParameters(Object[] param) {
		login.contract = "MS Contract";
		login.location = "Administrator/MSHF";
		
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplier.location = "MSHF";
		supplier.name = this.caseName + DateFunctions.getCurrentTime();
		supplier.description = "Automation Regression Test";
		supplier.orderAddress.address = "West A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "West A Street";
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
	
	private void initialEditingSupplierInfo() {
		String suffix = "_Edit";
		supplier.location = "MSHF";
		supplier.name = supplier.name + suffix;
		supplier.description = "Automation Regression Test" + suffix;
		supplier.orderAddress.address = "West A Street" + suffix;
		supplier.orderAddress.city = "Xian" + suffix;
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "36160";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = false;
		supplier.paymentAddress.address = "North A Street" + suffix;
		supplier.paymentAddress.city = "Xian" + suffix;
		supplier.paymentAddress.state = "Alaska";
		supplier.paymentAddress.zip = "36161";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "Quentin" + suffix;
		supplier.primPoc.firstName = "Chen" + suffix;
		supplier.primPoc.phone = "02988888888";
		supplier.primPoc.fax = "02988888888";
		supplier.primPoc.email = "Quentin.Chen@reserveamerica.com";
		supplier.otherPoc.lastName = "Shane" + suffix;
		supplier.otherPoc.firstName = "Song" + suffix;
		supplier.otherPoc.phone = "02999999999";
		supplier.otherPoc.fax = "02999999999";
		supplier.otherPoc.email = "Shane.Song@reserveamerica.com";
		supplier.paymentTerms = "Pre-Paid";
		supplier.paymentMethod = "Cheque";
		supplier.shippingMethod = "UPS";
	}
}
