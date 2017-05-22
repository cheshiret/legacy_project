package com.activenetwork.qa.awo.supportscripts.qasetup.inventory;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAvailableHourAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentHourAttr;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.supportscripts.SetupCase;
import com.activenetwork.qa.awo.supportscripts.function.inventory.AddEquipmentFunction;
import com.activenetwork.qa.awo.util.AwoUtil;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;
/**
 * This is the script to add equipment in inventory manager
 * @author phoebe
 */
public class AddEquipment extends SetupCase {
	private LoginInfo login = new LoginInfo();
	private String facilityID;
	private Data<EquipmentAttr> equipment = new Data<EquipmentAttr>();
	private AddEquipmentFunction addEqFuc = new AddEquipmentFunction();
	private boolean addAvailHour = false;

	@Override
	public void executeSetup() {		
		Object[] args = new Object[4];
		args[0] = login;
		args[1] = facilityID;
		args[2] = equipment;
		args[3] = addAvailHour;
		addEqFuc.execute(args);
	}

	@Override
	public void readDataFromDatabase() {
		login.contract = datasFromDB.get("Contract");
		login.location = datasFromDB.get("RoleLocation");
		
		facilityID = datasFromDB.get("FacilityID");
		
		equipment.put(EquipmentAttr.code, datasFromDB.get("CODE"));
		equipment.put(EquipmentAttr.name, datasFromDB.get("EQ_NAME"));
		equipment.put(EquipmentAttr.descrition, datasFromDB.get("DESCRIPTION"));
		equipment.put(EquipmentAttr.equipmentType, datasFromDB.get("EQUIPMENT_TYPE"));
		equipment.put(EquipmentAttr.usage, datasFromDB.get("EQ_USAGE"));
		equipment.put(EquipmentAttr.status, datasFromDB.get("STATUS"));
		equipment.put(EquipmentAttr.webVisible, datasFromDB.get("WEB_VISIBLE"));
		equipment.put(EquipmentAttr.allGeneralPublicSales, datasFromDB.get("ALLOW_GENERAL_PUBLIC_SALES"));
		equipment.put(EquipmentAttr.checkOutLagTime, datasFromDB.get("CHECK_OUT_LAG_TIME"));
		equipment.put(EquipmentAttr.checkInLagTime, datasFromDB.get("CHECK_IN_LOG_TIME"));
		//Add equipment available hours
		//hourStartDate:2014-1-1;2014-6-1
		//hourEndDate:2014-5-31;2014-12-31
		//hourDescription:regression test01;regression test02
		//6:00|AM|10:00|AM,2:00|PM|4:00|PM;10:00|AM|4:00|PM
		//6:00|AM|4:00|PM; ;
		if(StringUtil.notEmpty(datasFromDB.get("ADDAVAILHOUR")))
			addAvailHour = Boolean.parseBoolean(datasFromDB.get("ADDAVAILHOUR"));
		if(addAvailHour) {
			String[] startDates = datasFromDB.get("hourStartDate").split(";");
			String[] endDates = datasFromDB.get("hourEndDate").split(";");
			String[] descriptions = datasFromDB.get("hourDescription").split(";");
			String[] sunAvailHours = datasFromDB.get("sunAvailHours").split(";");
			String[] monAvailHours = datasFromDB.get("monAvailHours").split(";");
			String[] tueAvailHours = datasFromDB.get("tueAvailHours").split(";");
			String[] wedAvailHours = datasFromDB.get("wedAvailHours").split(";");
			String[] thuAvailHours = datasFromDB.get("thuAvailHours").split(";");
			String[] friAvailHours = datasFromDB.get("friAvailHours").split(";");
			String[] satAvailHours = datasFromDB.get("satAvailHours").split(";");
			if(startDates.length!=endDates.length || endDates.length!=descriptions.length 
			|| descriptions.length!=sunAvailHours.length || sunAvailHours.length!=monAvailHours.length
			|| monAvailHours.length!=tueAvailHours.length || tueAvailHours.length!=wedAvailHours.length
			|| wedAvailHours.length!=thuAvailHours.length || thuAvailHours.length!=friAvailHours.length
			|| friAvailHours.length!=satAvailHours.length)
				throw new ErrorOnDataException("Incomplete data for available hours. Please check your data.");
			List<Data<EquipmentHourAttr>> equipHours = new ArrayList<Data<EquipmentHourAttr>>();
			for(int i=0;i<startDates.length;i++) {
				Data<EquipmentHourAttr> equipHour = new Data<EquipmentHourAttr>();
				equipHour.put(EquipmentHourAttr.startDate, startDates[i]);
				equipHour.put(EquipmentHourAttr.endDate, endDates[i]);
				equipHour.put(EquipmentHourAttr.description, descriptions[i]);
				equipHour.put(EquipmentHourAttr.sunAvailableHours, getEquipAvailHourAttr(sunAvailHours[i]));
				equipHour.put(EquipmentHourAttr.monAvailableHours, getEquipAvailHourAttr(monAvailHours[i]));
				equipHour.put(EquipmentHourAttr.tueAvailableHours, getEquipAvailHourAttr(tueAvailHours[i]));
				equipHour.put(EquipmentHourAttr.wedAvailableHours, getEquipAvailHourAttr(wedAvailHours[i]));
				equipHour.put(EquipmentHourAttr.thuAvailableHours, getEquipAvailHourAttr(thuAvailHours[i]));
				equipHour.put(EquipmentHourAttr.friAvailableHours, getEquipAvailHourAttr(friAvailHours[i]));
				equipHour.put(EquipmentHourAttr.satAvailableHours, getEquipAvailHourAttr(satAvailHours[i]));
				equipHours.add(equipHour);
			}
			equipment.put(EquipmentAttr.equipmentHours, equipHours);
		}
	}

	@Override
	public void wrapParameters(Object[] param) {
		String env = TestProperty.getProperty("target_env");
		login.url = AwoUtil.getOrmsURL(env);
		
		login.userName = TestProperty.getProperty("orms.im.user");
		login.password = TestProperty.getProperty("orms.im.pw");
		
		dataTableName = "d_inv_add_equipment";
	}
	
	private List<Data<EquipmentAvailableHourAttr>> getEquipAvailHourAttr(String str) {
		List<Data<EquipmentAvailableHourAttr>> hoursAttr = new ArrayList<Data<EquipmentAvailableHourAttr>>();
		if(StringUtil.isEmpty(str))
			return null;
		String[] s = str.split(",");
		for(int i = 0; i<s.length; i++) {
			String t = s[i];
			String[] tArr = t.split("\\|");
			Data<EquipmentAvailableHourAttr> hourAttr = new Data<EquipmentAvailableHourAttr>();
			hourAttr.put(EquipmentAvailableHourAttr.startTime, tArr[0]);
			hourAttr.put(EquipmentAvailableHourAttr.startTimeAMPM, tArr[1]);
			hourAttr.put(EquipmentAvailableHourAttr.endTime, tArr[2]);
			hourAttr.put(EquipmentAvailableHourAttr.endTimeAMPM, tArr[3]);
			hoursAttr.add(hourAttr);
		}
		return hoursAttr;
	}

}