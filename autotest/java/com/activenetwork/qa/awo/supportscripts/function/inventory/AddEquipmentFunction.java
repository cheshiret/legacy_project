package com.activenetwork.qa.awo.supportscripts.function.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentHourAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.keywords.orms.InventoryManager;
import com.activenetwork.qa.awo.supportscripts.FunctionCase;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
/**
 * This function used to add a new equipment
 * @author pchen
 */
public class AddEquipmentFunction extends FunctionCase{
	private InventoryManager im = InventoryManager.getInstance();
	private LoginInfo login = new LoginInfo();
	private String contract = "";
	private String facilityID = "";
	private Data<EquipmentAttr> eq = new Data<EquipmentAttr>();
	private boolean loggedin=false;
	private boolean addAvailHour = false; 

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
		String idOrErr = im.addEquipment(eq);
		if(idOrErr.matches("\\d+")){
			newAddValue = idOrErr;
			if(addAvailHour) {
				im.gotoEquipmentDetailsByID(newAddValue);
				im.gotoEquipmentHourPageFromEquipmentDetailsPage();
				List<Data<EquipmentHourAttr>> equipHours = (ArrayList<Data<EquipmentHourAttr>>)(eq.get(EquipmentAttr.equipmentHours));
				for(int i=0;i<equipHours.size();i++) {
					Data<EquipmentHourAttr> equipHour = equipHours.get(i);
					im.AddEquipmentHour(equipHour);
				}
			}
		}else{
			newAddValue = idOrErr;
			throw new ErrorOnPageException("Add equipment product failed.");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void wrapParameters(Object[] param) {
		login = (LoginInfo)param[0];
		facilityID = (String)param[1];
		eq = (Data<EquipmentAttr>)param[2];
		addAvailHour = (Boolean)param[3];
	}
}