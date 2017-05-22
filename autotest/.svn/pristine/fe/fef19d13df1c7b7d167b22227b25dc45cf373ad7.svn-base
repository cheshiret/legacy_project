package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
/**
 * @Description: This function was designed for add slip to marina map
 * @Preconditions:
 * @LinkSetUp: d_assign_feature:id=4442
 * @SPEC:
 * @Task#: Auto-1826 
 * 
 * @author Jane Wang
 * @Date  Aug 13, 2013
 * 
 */

public class AddSlipToMarinaMapFunction extends FunctionCase {

	private LoginInfo login = new LoginInfo();
	private boolean loggedin=false;
	private InventoryManager im = InventoryManager.getInstance();
	private String contract = "";
	private String facilityID = "";
	private String dock = "";
	private String[] slips;
	
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		facilityID = (String)param[1];
		dock = (String)param[2];
		slips = (String[])param[3];
	}

	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		
		InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
		if(!invHmPg.exists()){
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		im.gotoFacilityDetailPageById(facilityID);
		
		im.gotoMarinaMapPg();
		for(int i=0;i<slips.length;i++){
			im.addSlipToMarinaMap(dock, slips[i]);
		}
		im.saveAndCloseMarinaMap();
	}
	
}
