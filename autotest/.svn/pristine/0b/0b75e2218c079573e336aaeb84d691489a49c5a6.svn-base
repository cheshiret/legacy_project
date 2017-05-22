package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PosSupplier;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Nov 22, 2012
 */
public class AddPosSupplierFunction extends FunctionCase {
	
	private LoginInfo login=new LoginInfo();
	private PosSupplier supplier = new PosSupplier();
	private InventoryManager im = InventoryManager.getInstance();
	private String warehouseName;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();

	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
    	im.gotoWarehousesSearchPg();
		im.searchWarehouse("Warehouse Name", warehouseName);
    	im.gotoWarehouseDetailPgThroughWarehouseName(warehouseName);
		im.gotoPosSupplierSearchPageFromTopMenu();

		supplier.id = im.addPosSupplier(supplier);

		if(StringUtil.isEmpty(supplier.id)||!supplier.id.matches("\\d+")){
			throw new ErrorOnPageException("Add POS Supplier Fail.");
		}else{
			newAddValue = supplier.id;
		}
		
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		warehouseName = (String)param[1];
		supplier = (PosSupplier)param[2];
	}
}
