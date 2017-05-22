package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.Closure;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityBookingRules.InvMgrClosuresPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

public class AddClosureFunction extends FunctionCase{
	private LoginInfo login=new LoginInfo();
	private Closure closure=new Closure();
//	private String schema;
	private InventoryManager invMgr=InventoryManager.getInstance();
	private String facilityName;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	private InvMgrClosuresPage closurePage = InvMgrClosuresPage.getInstance();
	
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			invMgr.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			invMgr.loginInventoryManager(login);
			loggedin = true;
		}
		if (!invHmPg.exists()) {
		    invMgr.gotoInventoryHomePg();
		}
		contract = login.contract;
		invMgr.gotoFacilityDetailsPg(facilityName);
		invMgr.goToBookingRulePg();
		if(closure.productCategory.equalsIgnoreCase("Slip")){
			closure.closureID = invMgr.addClosureForSlip(closure).trim();
		}else{
			closure.closureID = invMgr.addClosure(closure).trim();
		} 
		if(StringUtil.isEmpty(closure.closureID)){
			 throw new ErrorOnPageException("Failed, Cannot get new closureId product ID...");
		 }else{
			 closurePage.searchClosure(closure.closureID);
			 newAddValue = closure.closureID;
			 if(!closurePage.isClosureActive(closure.closureID)) {
				 throw new ErrorOnPageException("Closure "+closure.closureID+" is not active.");
			 }
		 }
	}
	
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
//		this.schema = (String)param[1];
		this.closure = (Closure)param[1];	
		this.facilityName = (String)param[2];
	}

}
