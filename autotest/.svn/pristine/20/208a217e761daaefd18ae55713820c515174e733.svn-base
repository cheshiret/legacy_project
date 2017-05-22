package com.activenetwork.qa.awo.testcases.regression.advanced.orms.order.pos.supplier.inventory.assignPosToSupplier;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.pos.possupplier.InvMgrPOSSupplierProductAssignmentListPage;
import com.activenetwork.qa.awo.testcases.abstractcases.InventoryManagerTestCase;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
/**  
 * @Description: Verify business rule.
 * @Preconditions:  .
 * @SPEC:  assign warehouse POS supplier.
 * @Task#: Auto-972.
 * @author jwang8  
 * @Date  Apr 24, 2012    
 */
public class VerifyBusniessRule extends InventoryManagerTestCase {
    private PosSupplier supplier = new PosSupplier();
    private InvMgrPOSSupplierProductAssignmentListPage posSupplierSetupPg = InvMgrPOSSupplierProductAssignmentListPage.getInstance();
    private ArrayList<String> posTableRule = new ArrayList<String>();
	public void execute() {
		im.loginInventoryManager(login);
		//Add a new POS supplier.
		im.gotoPosSupplierSearchPageFromTopMenu();
		supplier.id = im.addPosSupplier(supplier);
		//go to supplier product setup page.
		im.gotoPOSSupplierDetailsPage(supplier.id);
		im.gotoPosProductSupplierSetupPage();
		boolean pass = this.comparePosBusinesRule(posTableRule);
		if(!pass){
			throw new ErrorOnPageException("Assing pos supplier table business rule error");
		}else{
			logger.info("All the business rule are correct");
		}
		im.logoutInvManager();
	}

	public void wrapParameters(Object[] param) {
		login.url = AwoUtil.getOrmsURL(env);
		login.contract = "MS Contract";
		login.location = "Administrator/AutoWarehouse";
		
		supplier.location = "All Agencies";
		supplier.name = "AddSupp" + DataBaseFunctions.getEmailSequence();
		supplier.description = "AutoTest";
		supplier.orderAddress.address = "ShanXi";
		supplier.orderAddress.city = "ShangLuo";
		supplier.orderAddress.state = "Alabama";
		supplier.orderAddress.zip = "12345";
		supplier.orderAddress.country = "United States";
		supplier.isPaymentAddrSameOrderAddr = true;
		
		posTableRule.add("Assigned");
    	posTableRule.add("Product ID");
    	posTableRule.add("Product Name");
    	posTableRule.add("Product description");
    	posTableRule.add("Product Group");
    	posTableRule.add("Supplier Product Code");
    	posTableRule.add("Supplier Unit Cost");
	}
	
	/**
	 * compare assign POS business rule.
	 */
	private boolean comparePosBusinesRule(ArrayList<String> tableRule){
		boolean pass = true;
		IHtmlTable tables = (IHtmlTable)posSupplierSetupPg.getPosTable()[0];
		List<String> rowInfo = tables.getRowValues(0);
		Browser.unregister(posSupplierSetupPg.getPosTable());
		if(!rowInfo.get(1).equals(tableRule.get(0))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(0)+" but actual is" + rowInfo.get(1));
		}
		if(!rowInfo.get(2).equals(tableRule.get(1))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(1)+" but actual is" + rowInfo.get(2));
		}
		if(!rowInfo.get(3).equals(tableRule.get(2))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(2)+" but actual is" + rowInfo.get(3));
		}
		if(!rowInfo.get(4).equals(tableRule.get(3))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(3)+" but actual is" + rowInfo.get(4));
		}
		if(!rowInfo.get(5).equals(tableRule.get(4))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(4)+" but actual is" + rowInfo.get(5));
		}
		if(!rowInfo.get(6).equals(tableRule.get(5))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(5)+" but actual is" + rowInfo.get(6));
		}
		if(!rowInfo.get(7).equals(tableRule.get(6))){
			pass &= false;
			logger.error("Expected table colnum name is "+tableRule.get(6)+" but actual is" + rowInfo.get(7));
		}
		return pass;
	}
}
