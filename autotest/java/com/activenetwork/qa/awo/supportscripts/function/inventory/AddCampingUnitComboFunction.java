/**
 * 
 */
package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrHomePage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author ssong
 * @Date  May 5, 2014
 */
public class AddCampingUnitComboFunction extends FunctionCase{

	private LoginInfo login=new LoginInfo();
	private CampingUnit unit = new CampingUnit();
	private InventoryManager im=InventoryManager.getInstance();
	private String facilityName;
	private boolean loggedin=false;
	private String contract = "";
	private InvMgrHomePage invHmPg=InvMgrHomePage.getInstance();
	
	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#execute()
	 */
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		if (!invHmPg.exists()) {
			im.gotoInventoryHomePg();
		}
		contract = login.contract;
		 
		im.gotoFacilityDetailsPg(facilityName);
       
		im.gotoCampingUnitPg();
		im.addCampingUnitCombo(unit);
		 
		if(StringUtil.isEmpty(unit.comboID)){
			 throw new ErrorOnPageException("Failed in add new Camping Unit Combo.");
		}
		newAddValue = unit.comboID;
	}

	/* (non-Javadoc)
	 * @see com.activenetwork.qa.testapi.interfaces.testcase.TestCase#wrapParameters(java.lang.Object[])
	 */
	@Override
	public void wrapParameters(Object[] param) {
		this.login = (LoginInfo)param[0];
		this.unit = (CampingUnit)param[1];	
		this.facilityName = (String)param[2];
	}

}
