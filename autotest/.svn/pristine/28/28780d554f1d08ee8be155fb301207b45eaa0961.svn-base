package com.activenetwork.qa.awo.pages.orms.inventoryManager.equipment;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentAttr;
import com.activenetwork.qa.awo.datacollection.datadefinition.orms.equipment.EquipmentInvAttr;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class InvMgrEquipmentInventoryDetailPage extends InvMgrCommonTopMenuPage {
	static private InvMgrEquipmentInventoryDetailPage _instance = null;

	protected InvMgrEquipmentInventoryDetailPage() {
	}

	static public InvMgrEquipmentInventoryDetailPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new InvMgrEquipmentInventoryDetailPage();
		}

		return _instance;
	}

	/**
	 * Check the object exists
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "tab_equipInvSetupAddTab");
	}
	
	protected Property[] equipmentTypeDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("DropdownExt-\\d+\\.selectedValue", false));
	}
	
	protected Property[] equipmentDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.product", false));
	}
	
	protected Property[] startDateTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.startDate_date_ForDisplay", false));
	}
	
	protected Property[] startTimeTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.startDate_time", false));
	}
	
	protected Property[] startAMPMDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.startDate_ampm", false));
	}
	
	protected Property[] endDateTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.endDate_date_ForDisplay", false));
	}
	
	protected Property[] endTimeTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.endDate_time", false));
	}
	
	protected Property[] endAMPMDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.endDate_ampm", false));
	}
	
	protected Property[] totalQuantityTextField(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.quantity", false));
	}
	
	protected Property[] activeStatusRadioBtn(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.status", false), ".value", "Active");
	}
	
	protected Property[] inactiveStatusRadioBtn(){
		return Property.toPropertyArray(".id", new RegularExpression("QuantityPeriodInventoryView-\\d+\\.status", false), ".value", "Inactive");
	}
	
	protected Property[] okBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Ok", false));
	}
	
	protected Property[] cancelBtn(){
		return Property.concatPropertyArray(this.a(), ".text", new RegularExpression("Cancel", false));
	}
	
	public void selectEquipmentType(String type){
		browser.selectDropdownList(this.equipmentTypeDropdownList(), type);
	}
	
	public void selectEquipment(String equipment){
		browser.selectDropdownList(this.equipmentDropdownList(), equipment);
	}
	
	public void setStartDate(String startDate){
		browser.setCalendarField(this.startDateTextField(), startDate);
	}
	
	public void setStartTime(String startTime){
		browser.setCalendarField(this.startTimeTextField(), startTime);
	}
	
	public void selectStartAPM(String amOrpm){
		browser.selectDropdownList(this.startAMPMDropdownList(), amOrpm);
	}
	
	public void setEndDate(String endDate){
		browser.setCalendarField(this.endDateTextField(), endDate);
	}
	
	public void setEndTime(String endTime){
		browser.setCalendarField(this.endTimeTextField(), endTime);
	}
	
	public void selectEndAPM(String amOrpm){
		browser.selectDropdownList(this.endAMPMDropdownList(), amOrpm);
	}
	
	public void setTotalQuantity(String quantity){
		browser.setTextField(this.totalQuantityTextField(), quantity);
	}
	
	public void selectStatus(boolean isActive){
		if(isActive){
			browser.selectRadioButton(this.activeStatusRadioBtn(), 0);
		}else{
			browser.selectRadioButton(this.inactiveStatusRadioBtn(), 0);
		}
	}
	
	public void clickOk(){
		browser.clickGuiObject(this.okBtn());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(this.cancelBtn());
	}
	
	/**
	 * Below method was used to trigger ajax refresh after set time
	 */
	public void clickStatusSpan() {
		browser.clickGuiObject(".class", "Html.TD", ".text", "Status");
	}
	
	
	public void setUpEquipmentInfo(Data<EquipmentInvAttr> eqInv){
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.equipmentType))){
			this.selectEquipmentType(eqInv.stringValue(EquipmentAttr.equipmentType));
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.equipment))){
			this.selectEquipment(eqInv.stringValue(EquipmentInvAttr.equipment));
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.startDate))){
			this.setStartDate(eqInv.stringValue(EquipmentInvAttr.startDate));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.startTime))){
			this.setStartTime(eqInv.stringValue(EquipmentInvAttr.startTime));
			this.clickStatusSpan();
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.startAMPM))){
			this.selectStartAPM(eqInv.stringValue(EquipmentInvAttr.startAMPM));
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.endDate))){
			this.setEndDate(eqInv.stringValue(EquipmentInvAttr.endDate));
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.endTime))){
			this.setEndTime(eqInv.stringValue(EquipmentInvAttr.endTime));
			this.clickStatusSpan();
			ajax.waitLoading();
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.endAMPM))){
			this.selectEndAPM(eqInv.stringValue(EquipmentInvAttr.endAMPM));
		}
		if(StringUtil.notEmpty(eqInv.stringValue(EquipmentInvAttr.totalQuantity))){
			this.setTotalQuantity(eqInv.stringValue(EquipmentInvAttr.totalQuantity));
		}
		this.selectStatus(eqInv.booleanValue(EquipmentInvAttr.isActive));
	}
	
	public String getErrorMsg(){
		String errMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "message msgerror");
		if(objs.length > 0){
			errMsg = objs[0].text();
		}
		return errMsg;
	}
}
