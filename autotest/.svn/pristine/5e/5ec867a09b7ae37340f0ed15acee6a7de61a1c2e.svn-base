/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.POSPassInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;

/**
 * 
 * @author ssong
 * @Date  Jan 24, 2014
 */
public class AddPosPassNumberFunction extends FunctionCase {

	private LoginInfo login = new LoginInfo();
	private POSPassInfo pass = new POSPassInfo();
	private InventoryManager im = InventoryManager.getInstance();
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
    	
		im.addPosPassNum(pass);
		
		
		newAddValue = pass.getPassNum();
	}
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		pass = (POSPassInfo)param[1];
	}
}

