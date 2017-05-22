package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * 
 * @Description: verify search POS Supplier functionality works correctly with logging Agency level
 * @Preconditions:
 * @SPEC: Search POS Supplier TC037518
 * @Task#: AUTO-1140
 * @author qchen
 * @Date  Jul 24, 2012
 */
public class SearchOnAgencyLevel extends InventoryManagerTestCase {
	private PosSupplier supplier = new PosSupplier();
	
	@Override
	public void execute() {
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//add a POS Supplier
		supplier.id = im.addPosSupplier(supplier);
		
		//verify searching function
		this.verifyPosSupplierSearchResult();
		
		//clean up
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
		supplier.orderAddress.address = "Middle A Street";
		supplier.orderAddress.city = "Xian";
		supplier.orderAddress.state = "Mississippi";
		supplier.orderAddress.zip = "36159";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		supplier.paymentAddress.address = "Middle A Street";
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
	
	private void verifyPosSupplierSearchResult(){
		InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
		
		logger.info("Verify searching POS Supplier functionality works correctly on Agency level.");
		posSupplierSearchPg.searchPosSupplier(supplier);
		posSupplierSearchPg.verifyPosSupplierSearchResult(supplier);
		
		posSupplierSearchPg.clearUpSearchCriteria();
		PosSupplier searchSupplier = new PosSupplier();
		searchSupplier.id = supplier.id;
		posSupplierSearchPg.searchPosSupplier(searchSupplier);
		posSupplierSearchPg.verifyPosSupplierSearchResult(searchSupplier);
		
		posSupplierSearchPg.clearUpSearchCriteria();
		searchSupplier.id = "";
		searchSupplier.name = supplier.name;
		posSupplierSearchPg.searchPosSupplier(searchSupplier);
		posSupplierSearchPg.verifyPosSupplierSearchResult(searchSupplier);
		
		posSupplierSearchPg.clearUpSearchCriteria();
		searchSupplier.name = "";
		searchSupplier.searchByStatus = "Active";
		searchSupplier.status = OrmsConstants.ACTIVE_STATUS;
		posSupplierSearchPg.searchPosSupplier(searchSupplier);
		posSupplierSearchPg.verifyPosSupplierSearchResult(searchSupplier);
	}
}
