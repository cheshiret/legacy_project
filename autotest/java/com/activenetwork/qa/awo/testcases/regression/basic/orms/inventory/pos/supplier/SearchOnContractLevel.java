package com.activenetwork.qa.awo.testcases.regression.basic.orms.inventory.pos.supplier;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierSearchPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
/**  
 * @Description: Search pos supplier.
 * @Preconditions:  assign SearchPOSSupplier feature to role.
 * @SPEC: Search POS Supplier TC037518
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Mar 29, 2012    
 */
public class SearchOnContractLevel extends InventoryManagerTestCase{
	private PosSupplier supplier = new PosSupplier();
	public void execute() {
		
		im.loginInventoryManager(login);
		im.gotoPosSupplierSearchPageFromTopMenu();
		//Add a new pos suppler.
		supplier.id =im.addPosSupplier(supplier);
		this.verifyPosSupplierSearchResult();
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/Mississippi Department of Wildlife, Fisheries, and Parks";
		
		supplier.name ="AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alaska";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = false;
		supplier.paymentAddress.address = "JiangSu";
		supplier.paymentAddress.city = "HangZhou";
		supplier.paymentAddress.state = "Alabama";
		supplier.paymentAddress.zip = "23456";
		supplier.paymentAddress.country = "United States";
		supplier.primPoc.lastName = "primLast";
		supplier.primPoc.firstName = "primFirst";
		supplier.primPoc.phone = "4088456789";
		supplier.primPoc.fax = "01236985";
		supplier.primPoc.email = "prim@sina.com";
		supplier.otherPoc.lastName = "otherLast";
		supplier.otherPoc.firstName = "otherFirst";
		supplier.otherPoc.phone = "4088748596";
		supplier.otherPoc.fax = "02174856";
		supplier.otherPoc.email = "other@sina.com";
		supplier.paymentTerms = "Due on Receipt";
		supplier.paymentMethod = "Cash";
		supplier.shippingMethod = "UPS";
		supplier.status = OrmsConstants.ACTIVE_STATUS;
		supplier.searchByStatus = "Active";
		
	}
	/**
	 * verify pos supplier search result.
	 */
	private void verifyPosSupplierSearchResult(){
		
		InvMgrPOSSupplierSearchPage posSupplierSearchPg = InvMgrPOSSupplierSearchPage.getInstance();
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
		searchSupplier.status = OrmsConstants.YES_STATUS;
		posSupplierSearchPg.searchPosSupplier(searchSupplier);
		posSupplierSearchPg.verifyPosSupplierSearchResult(searchSupplier);
	}
}
