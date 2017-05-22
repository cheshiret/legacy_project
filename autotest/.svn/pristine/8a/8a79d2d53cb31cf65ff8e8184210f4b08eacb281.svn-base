package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import java.util.ArrayList;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAvailableHourAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentHourAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class InvMgrAddEquipmentHourWidget extends DialogWidget {
	private static InvMgrAddEquipmentHourWidget _instance = null;
	
	private InvMgrAddEquipmentHourWidget() {
		super("Add Equipment Hours");
	}
	
	public static InvMgrAddEquipmentHourWidget getInstance() {
		if(_instance == null) {
			_instance = new InvMgrAddEquipmentHourWidget();
		}
		return _instance;
	}
	
	protected Property[] startDate(){
		return Property.toPropertyArray(".id", new RegularExpression("ResourceAvailabilityScheduleView-\\d+\\.startDate_ForDisplay", false));
	}
	
	protected Property[] endDate(){
		return Property.toPropertyArray(".id", new RegularExpression("ResourceAvailabilityScheduleView-\\d+\\.endDate_ForDisplay", false));
	}
	
	protected Property[] description(){
		return Property.toPropertyArray(".id", new RegularExpression("ResourceAvailabilityScheduleView-\\d+\\.description", false));
	}
	
	protected Property[] equipmentHoursTable(){
		return Property.concatPropertyArray(this.table(), ".className", new RegularExpression("\\W*equipmentHoursTable$", false));
	}
	
	protected Property[] startTime(){
		return Property.concatPropertyArray(this.input("text") , ".className", "inputwithrubylabel startTimeInput");
	}
	
	protected Property[] endTime(){
		return Property.concatPropertyArray(this.input("text") , ".className", new RegularExpression("\\W*inputwithrubylabel endTimeInput", false));
	}
	
	protected Property[] startTimeAMPM(){
		return Property.concatPropertyArray(this.select() , ".className", "startDropDown");
	}
	
	protected Property[] endTimeAMPM(){
		return Property.concatPropertyArray(this.select() , ".className", "endDropDown");
	}

	protected Property[] closeAllDay(){
		return Property.toPropertyArray(".id", new RegularExpression("FacilityAvailabilityDayScheduleView-\\d+\\.closeAllDay", false));
	} 
	
	protected Property[] addAnother(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("add another", false));
	} 
	
	public void setStartDate(String startDate){
		browser.setCalendarField(startDate(), startDate);
	}
	
	public void setEndDate(String endDate) {
		browser.setCalendarField(endDate(), endDate);
	}
	
	public void setDescription(String des) {
		browser.setTextField(description(), des);
	}
	
	public IHtmlObject[] getEquipHoursTable() {
		IHtmlObject[] objs = browser.getHtmlObject(equipmentHoursTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find equipment hours table on page.");
		return objs;
	}
	
	public void setupAvailableHour(Data<EquipmentHourAttr> equipHour) {
		setStartDate(equipHour.stringValue(EquipmentHourAttr.startDate));
		setEndDate(equipHour.stringValue(EquipmentHourAttr.endDate));
		setDescription(equipHour.stringValue(EquipmentHourAttr.description));
		
		setupAvailableHour("Sun", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.sunAvailableHours)));
		setupAvailableHour("Mon", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.monAvailableHours)));
		setupAvailableHour("Tue", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.tueAvailableHours)));
		setupAvailableHour("Wed", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.wedAvailableHours)));
		setupAvailableHour("Thu", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.thuAvailableHours)));
		setupAvailableHour("Fri", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.friAvailableHours)));
		setupAvailableHour("Sat", (ArrayList<Data<EquipmentAvailableHourAttr>>)(equipHour.get(EquipmentHourAttr.satAvailableHours)));
	}
	
	public void setStartTime(String time, int index, IHtmlObject obj) {
		browser.setCalendarField(startTime(), time, index, obj);
	}
	
	public void setStartTimeAMPM(String ampm, int index, IHtmlObject obj) {
		browser.selectDropdownList(startTimeAMPM(), ampm, true, index, obj);
	}
	
	public void setEndTime(String time, int index, IHtmlObject obj) {
		browser.setCalendarField(endTime(), time, index, obj);
	}
	
	public void setEndTimeAMPM(String ampm, int index, IHtmlObject obj) {
		browser.selectDropdownList(endTimeAMPM(), ampm, true, index, obj);
	}
	
	public void setupAvailableHour(String prefix, ArrayList<Data<EquipmentAvailableHourAttr>> hours) {
		IHtmlObject[] table = getEquipHoursTable();
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+prefix+".*", false), table[0]);
		if(trs.length<1)
			throw new ItemNotFoundException("Could not find any TRs for "+prefix+" in table on page.");
		
		int i=0;
		while(i!=hours.size()) {
			Data<EquipmentAvailableHourAttr> hour = new Data<EquipmentAvailableHourAttr>();
			hour = hours.get(i);
			setStartTime(hour.stringValue(EquipmentAvailableHourAttr.startTime), i, trs[0]);
			setStartTimeAMPM(hour.stringValue(EquipmentAvailableHourAttr.startTimeAMPM), i, trs[0]);
			setEndTime(hour.stringValue(EquipmentAvailableHourAttr.endTime), i, trs[0]);
			setEndTimeAMPM(hour.stringValue(EquipmentAvailableHourAttr.endTimeAMPM), i, trs[0]);
			//TODO for multiply start time and end time for one day, they were not under one TR
			if(i!=hours.size()) {
				browser.clickGuiObject(addAnother(), true, 0, trs[0]);
				ajax.waitLoading();
				this.waitLoading();
				table = getEquipHoursTable();
				trs = browser.getHtmlObject(".className", "Html.TR", ".text", new RegularExpression("^"+prefix+".*", false), table[0]);
				i++;
			}
		}
		Browser.unregister(table);
		Browser.unregister(trs);
	}
	
}