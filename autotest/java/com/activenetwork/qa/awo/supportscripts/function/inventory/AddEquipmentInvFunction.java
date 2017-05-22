package com.activenetwork.qa.awo.supportscripts.function.inventory;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentInvAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment.InvMgrEquipmentInventoryDetailPage;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;

public class AddEquipmentInvFunction extends FunctionCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String facilityID = "";
	private Data<EquipmentInvAttr> eqInv = new Data<EquipmentInvAttr>();
	private boolean loggedin=false;
	private InvMgrEquipmentInventoryDetailPage eqInvDetailPg = InvMgrEquipmentInventoryDetailPage.getInstance();
	@Override
	public void execute() {
		if (!login.contract.equals(contract) && loggedin && isBrowserOpened) {
			im.switchToContract(login.contract, login.location);
		} 
		if (!loggedin || !isBrowserOpened) { // Logged in
			im.loginInventoryManager(login);
			loggedin = true;
		}
		
		contract = login.contract;
		im.gotoFacilityDetailPageById(facilityID);
		im.addEquipmentInv(eqInv);
		String errMsg = "";
		if(eqInvDetailPg.exists()){
			errMsg = eqInvDetailPg.getErrorMsg();
			newAddValue = errMsg;	
			throw new ErrorOnPageException("Add equipment product inventory failed.");
		}else{
			newAddValue = eqInv.stringValue(EquipmentInvAttr.equipment);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		facilityID = (String)param[1];
		eqInv = (Data<EquipmentInvAttr>)param[2];
	}
}
