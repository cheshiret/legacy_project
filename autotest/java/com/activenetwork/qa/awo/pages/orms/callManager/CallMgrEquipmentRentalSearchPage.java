package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentRental;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class CallMgrEquipmentRentalSearchPage extends CallMgrCommonTopMenuPage {
	private final String prefix = "ReservationCandidateCriteriaView";
	
	static private CallMgrEquipmentRentalSearchPage _instance = null;
	
	static public CallMgrEquipmentRentalSearchPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrEquipmentRentalSearchPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return checkLastTrailValueIs("Equipment Rental Search");
	}
	
	protected Property[] facilityTextField(){
		return Property.toPropertyArray(".id", "facilityInput");
	}
	
	protected Property[] equipPrdCategoryDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("-\\d+\\.equipmentPrdGroup", false));
	}
	
	protected Property[] equipNameTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.equipmentName", false));
	}
	
	protected Property[] startDate(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.startDate_date_ForDisplay", false));
	}
	
	protected Property[] startTime() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.startDate_time", false));
	}
	
	protected Property[] startTimeAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.startDate_ampm", false));
	}
	
	protected Property[] endDate(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.endDate_date_ForDisplay", false));
	}
	
	protected Property[] endTime() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.endDate_time", false));
	}
	
	protected Property[] endTimeAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.endDate_ampm", false));
	}
	
	protected Property[] quantityTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(prefix+"-\\d+\\.quantity", false));
	}
	
	protected Property[] overrideEquipmentHoursCheckBox(){
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression(prefix+"-\\d+\\.overrideEquipmentHours", false));
	}
	
	protected Property[] showAvailableOnlyCheckBox(){
		return Property.concatPropertyArray(this.input("checkbox"), ".id", new RegularExpression(prefix+"-\\d+\\.checkAvailableOnly", false));
	}
	
	protected Property[] searchButton(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^SEARCH$", false));
	}
	
	protected Property[] reserveButton(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Reserve$", false));
	}
	
	protected Property[] addToListButton(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("^Add To List$", false));
	}
	
	protected Property[] equipmentRentalResultsTable() {
		return Property.concatPropertyArray(this.table(), ".id", "equipmentRentalResultsGrid");
	}
	
	protected Property[] errorMsg() {
		return Property.concatPropertyArray(this.div(), ".className", "message msgerror");
	}
	
	public void clickSearch() {
		browser.clickGuiObject(searchButton());
	}
	
	public void clickReserve() {
		browser.clickGuiObject(reserveButton());
	}
	
	public String getErrorMessage() {
		String msg = null;
		IHtmlObject[] objs = browser.getHtmlObject(errorMsg());
		if(objs.length<1)
			return null;
		msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return msg;
	}
	
	public void setFacility(String facility) {
		browser.setTextField(facilityTextField(), facility);
		Property[] p = Property.concatPropertyArray(this.div(), ".className", "ac_results");
		browser.waitExists(p);
		browser.waitExists(OrmsConstants.PAGELOADING_SYNC_TIME);
		Browser.sleep(3);
		browser.clickGuiObject(".class", "Html.LI", ".text", facility, true);
		ajax.waitLoading();
	}
	
	public void selectEquipType(String type) {
		browser.selectDropdownList(equipPrdCategoryDropdownList(), type);
	}
	
	public void setEquipName(String name) {
		browser.setTextField(equipNameTextField(), name);
	}
	
	public void setStartDate(String sDate) {
		browser.setCalendarField(startDate(), sDate);
	}
	
	public void setStartTime(String sTime) {
		browser.setTextField(startTime(), sTime);
	}
	
	public void selectStartTimeAMPM(String ampm) {
		browser.selectDropdownList(startTimeAMPM(), ampm);
	}
	
	public void setEndDate(String eDate) {
		browser.setCalendarField(endDate(), eDate);
	}
	
	public void setEndTime(String eTime) {
		browser.setTextField(endTime(), eTime);
	}
	
	public void selectEndTimeAMPM(String ampm) {
		browser.selectDropdownList(endTimeAMPM(), ampm);
	}
	
	public void setQuantity(String qty) {
		browser.setTextField(quantityTextField(), qty);
	}
	
	public void selectShowAvailableOnly() {
		browser.selectCheckBox(showAvailableOnlyCheckBox());
	}
	
	public void unSelectShowAvailableOnly() {
		browser.unSelectCheckBox(showAvailableOnlyCheckBox());
	}
	
	/**
	 * Below method was used to trigger ajax refresh after set time
	 */
	public void clickEquipmentSearchSpan() {
		browser.clickGuiObject(".class", "Html.TD", ".text", "Equipment Search");
	}
	
	public void setupEquipmentRentalSearchCriteria(Data<EquipmentRental> equipRental) {
		logger.info("Setup equipment rental criteria");
		this.setFacility(equipRental.stringValue(EquipmentRental.facility));
		this.selectEquipType(equipRental.stringValue(EquipmentRental.equipType));
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.equipName)))
			this.setEquipName(equipRental.stringValue(EquipmentRental.equipName));
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.startDate))) {
			this.setStartDate(equipRental.stringValue(EquipmentRental.startDate));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.startTime))) {
			this.setStartTime(equipRental.stringValue(EquipmentRental.startTime));
			this.clickEquipmentSearchSpan();
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.startTimeAMPM))) {
			this.selectStartTimeAMPM(equipRental.stringValue(EquipmentRental.startTimeAMPM));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.endDate))) {
			this.setEndDate(equipRental.stringValue(EquipmentRental.endDate));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.endTime))) {
			this.setEndTime(equipRental.stringValue(EquipmentRental.endTime));
			this.clickEquipmentSearchSpan();
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.endTimeAMPM))) {
			this.selectEndTimeAMPM(equipRental.stringValue(EquipmentRental.endTimeAMPM));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.quantity)))
			this.setQuantity(equipRental.stringValue(EquipmentRental.quantity));
		if(StringUtil.notEmpty(equipRental.stringValue(EquipmentRental.isShowAvailableOnly))) {
			if(equipRental.booleanValue(EquipmentRental.isShowAvailableOnly))
				this.selectShowAvailableOnly();
			else
				this.unSelectShowAvailableOnly();		
		}
	}
	
	public IHtmlObject[] getEquipmentRentalResultsTable() {
		IHtmlObject[] objs = browser.getHtmlObject(equipmentRentalResultsTable());
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find equipment rental table on page.");
		return objs;
	}
	
	public void selectEquipment(String code, String name, String qty) {
		logger.info("select "+qty+" equipment "+code+"-"+name);
		IHtmlObject[] objs = getEquipmentRentalResultsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(table.findColumn(0, "Equipment"), code+"-"+name);
		if(rowNum<0)
			throw new ItemNotFoundException("Could not find equipment "+code+"-"+name);
		browser.selectDropdownList(".id", new RegularExpression("ReservationCandidate-\\d+\\.quantity", false), rowNum-1);
		browser.selectCheckBox(".id", new RegularExpression("ReservationCandidate-\\d+\\.selected", false), rowNum-1);
		Browser.unregister(objs);
	}
	
	public boolean isEquipmentExistedInResultGrid(String code, String name) {
		IHtmlObject[] objs = getEquipmentRentalResultsTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(table.findColumn(0, "Equipment"), code+"-"+name);
		Browser.unregister(objs);
		if(rowNum<0)
			return false;
		return true;
	}
}