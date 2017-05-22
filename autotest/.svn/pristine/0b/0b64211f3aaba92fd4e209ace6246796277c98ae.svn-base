package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentInvAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddEquipmentInvFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * This set up for add equipment inventory
 * @author pchen
 */
public class AddEquipmentInv extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String facilityID;
	private Data<EquipmentInvAttr> equipmentInv = new Data<EquipmentInvAttr>();
	private AddEquipmentInvFunction addEqInv = new AddEquipmentInvFunction();

	@Override
	public void executeSetup() {		
		Object[] args = new Object[3];
		args[0] = login;
		args[1] = facilityID;
		args[2] = equipmentInv;
		addEqInv.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("RoleLocation");
		
		facilityID = datasFromDB.get("FacilityID");
		
		equipmentInv.put(EquipmentInvAttr.equipmentType, datasFromDB.get("EQUIPMENT_TYPE"));
		equipmentInv.put(EquipmentInvAttr.equipment, datasFromDB.get("EQUIPMENT"));
		equipmentInv.put(EquipmentInvAttr.startDate, datasFromDB.get("START_DATE").equalsIgnoreCase(StringUtil.EMPTY)?DateFunctions.getDateAfterToday(-1):datasFromDB.get("START_DATE"));
		equipmentInv.put(EquipmentInvAttr.startTime, datasFromDB.get("START_TIME").equalsIgnoreCase(StringUtil.EMPTY)?"01:01":datasFromDB.get("START_TIME"));
		equipmentInv.put(EquipmentInvAttr.startAMPM, datasFromDB.get("START_APM").equalsIgnoreCase(StringUtil.EMPTY)?"AM":datasFromDB.get("START_APM"));
		equipmentInv.put(EquipmentInvAttr.endDate, datasFromDB.get("END_DATE").equalsIgnoreCase(StringUtil.EMPTY)?DateFunctions.getDateAfterToday(500):datasFromDB.get("END_DATE"));
		equipmentInv.put(EquipmentInvAttr.endTime, datasFromDB.get("END_TIME").equalsIgnoreCase(StringUtil.EMPTY)?"11:59":datasFromDB.get("END_TIME"));
		equipmentInv.put(EquipmentInvAttr.endAMPM, datasFromDB.get("END_APM").equalsIgnoreCase(StringUtil.EMPTY)?"PM":datasFromDB.get("END_APM"));
		equipmentInv.put(EquipmentInvAttr.totalQuantity, datasFromDB.get("TOTAL_QUANTITY"));
		equipmentInv.put(EquipmentInvAttr.isActive, Boolean.valueOf(datasFromDB.get("STATUS")));
	}

	@Override
	public void wrapParameters(Object[] param) {
		dataTableName = "d_inv_add_equipment_inv";
		queryDataSql = "select * from d_inv_add_equipment_inv where id>10";

		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
		ids = "10";
	}

}
