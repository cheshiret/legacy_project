package com.activenetwork.qa.awo.supportscripts.qasetup.unused;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InventoryInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrFacilityBookingRulesPage;
import com.activenetwork.qa.awo.supportscripts.SupportCase;
import com.activenetwork.qa.testapi.util.DateFunctions;

/**
 * Description : Functional Test Script
 * 
 * @author QA
 */
public class FacilityClosures extends SupportCase {
	/**
	 * Script Name : <b>FacilityClosures</b> Generated : <b>Mar 22, 2010 4:28:48
	 * AM</b> Description : Functional Test Script Original Host : WinNT Version
	 * 5.1 Build 2600 (S)
	 * 
	 * @since 2010/03/22
	 * @author Sara Wang
	 */
	private InventoryManager im = InventoryManager.getInstance();
	private InventoryInfo inventory = new InventoryInfo();
	private InvMgrFacilityBookingRulesPage invFacBookRulePg = InvMgrFacilityBookingRulesPage
			.getInstance();
	private InvMgrHomePage invHome = InvMgrHomePage.getInstance();
	private InvMgrTopMenuPage invTpMenuPg = InvMgrTopMenuPage.getInstance();
	private InvMgrClosuresPage invClosurePg = InvMgrClosuresPage.getInstance();
	private LoginInfo login = new LoginInfo();
	private Closure closure = new Closure();
	private String contract = "";
	private boolean loggedIn = false;

	public void wrapParameters(Object[] param) {
		startpoint = 11; // the start point in the data pool
		endpoint = 11; // the end point in the data pool

		dataSource = casePath + "/" + "FacilityList";

		login.url = "https://web03.qa.reserveamerica.com:6401/";
		login.location = "any";
		login.userName = "qa-auto-adm";
		login.password = "auto1234";
		login.envType = "QA";
		closure.startDate = DateFunctions.getToday();

		// log information
		logMsg = new String[3];
		logMsg[0] = "contract";
		logMsg[1] = "facilityName";
		logMsg[2] = "result";
	}

	public void execute() {

		// Login Inventory Manager
		if (!login.contractAbbrev.equalsIgnoreCase(contract) || !loggedIn) {
			if (contract.equalsIgnoreCase("LARC"))
				login.contract = "Larimer County Contract";
			else
				login.contract = contract.toUpperCase() + " Contract";
			if (loggedIn) {
				im.logoutInvManager();
			}
			im.loginInventoryManager(login);
			loggedIn = true;
		}

		// goto invFacBookRulePg
		im.gotoFacilityDetailsPg(inventory.facilityName);
		im.goToBookingRulePg();
		invFacBookRulePg.clickClosures();
		invClosurePg.waitLoading();

		// Deactivate all effective closures
		this.deactivateEffectiveClosures();
		if (invClosurePg.exists()) {
			logMsg[2] = "successful";
		}
		// in order to run next data in datapool
		invTpMenuPg.clickHome();
		invHome.waitLoading();

		login.contractAbbrev = contract;
	}

	public void getNextData() {
		contract = dpIter.dpString("contract_abbr");
		inventory.facilityName = dpIter.dpString("park_name");

		logMsg[0] = contract;
		logMsg[1] = inventory.facilityName;
		logMsg[2] = "Fail due to error";
	}

	// Deactivate all effective closures
	public void deactivateEffectiveClosures() {
		invClosurePg.searchClosure("End Date", closure);
		invClosurePg.selectAllClosuresCheckBox();
		invClosurePg.clickDeactivate();
		invClosurePg.waitLoading();
		logger.info("deactive successfully!");
	}
}
