package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.VenueManager;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.venueManager.VnuMgrTourInventoryListPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;

public class AddTourInventoryFunction extends FunctionCase{
	
	private LoginInfo login = new LoginInfo();
  	private VenueManager vm = VenueManager.getInstance();
	private InventoryInfo tourInv = new InventoryInfo();
	private VnuMgrTourInventoryListPage tourInvListPg = VnuMgrTourInventoryListPage.getInstance();
	private boolean isTimeSpec, isDateSpec, isNonTimeSpecific;
	private boolean loggedin=false;
	private String contract = "",location = "";
	private VnuMgrHomePage vmHmPg = VnuMgrHomePage.getInstance();

	@Override
	public void execute() {
		if((!contract.equalsIgnoreCase(login.contract) || !location.equals(login.location)) && loggedin && isBrowserOpened){
			vm.logoutVenueManager();
			loggedin =false;
		}
		
		if (!loggedin || !isBrowserOpened) {
			vm.loginVenueManager(login);
			loggedin = true;
			contract = login.contract;
		}
		if (!vmHmPg.exists()) {
			vm.gotoVnuHomePage();
		}	
		//isDeleteExistingInv=false, because there were some tour inventory that need to setup more than one tour inventory, so it will delete tour inventory from previous setup
		//if you need to delete existing tour inventory, please add a column for isDeleteExistingInv, and make note for your data 
		vm.addTourInventory(tourInv, isTimeSpec, isDateSpec, isNonTimeSpecific, false); 
		//log information
	  	if(tourInvListPg.exists()){
	  		logger.info("Create tour invetnory success.");
	  	}else{
	  		throw new ErrorOnPageException("Create Tour Inventory Fail.");
	  	}
	}

	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		
		isTimeSpec = Boolean.parseBoolean(param[1].toString());
		isDateSpec = Boolean.parseBoolean(param[2].toString());
		isNonTimeSpecific = Boolean.parseBoolean(param[3].toString());
		tourInv = (InventoryInfo)param[4];
	}

}
