package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
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
public class AddPosProductFunction extends FunctionCase {

	private LoginInfo login = new LoginInfo();
	private POSInfo pos = new POSInfo();
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
		im.gotoPosProductSetupPage();
		
		pos.productID = im.addPOSProduct(pos);
		if(StringUtil.isEmpty(pos.productID)||!pos.productID.matches("\\d+")){
			if(pos.productID.matches("The POS Product Code entered already exists.*")){
				pos.productID = im.getProductID("Product Name", pos.product, "", true, DataBaseFunctions.getSchemaName(contract.replaceAll("Contract", ""),env));
			}
			throw new ErrorOnPageException("Add POS product Fail.");
		}
		newAddValue = pos.productID;
		if(!StringUtil.isEmpty(pos.qtyOnHand)){
			im.gotoPOSInventoryManagementPage();
			im.adjustPOSInventory(pos);
			im.gotoPosProductSetupPage();
		}
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		warehouseName = (String)param[1];
		pos = (POSInfo)param[2];
	}
}
