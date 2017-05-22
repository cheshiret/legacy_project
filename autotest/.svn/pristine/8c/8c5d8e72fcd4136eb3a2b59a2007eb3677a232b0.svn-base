package com.activenetwork.qa.awo.pages.orms.common;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class OrmsAddCampingUnitsWidget extends DialogWidget {
	private String campingUnitAttributePatterPrefix="campingInfoContainer_CampingUnit_\\d+_campingunits-row:\\d+_camping_unit_";
	
	protected OrmsAddCampingUnitsWidget(){
		super("Add Camping (Units|Untis)");
	}
	
	private static OrmsAddCampingUnitsWidget _instance = null;
	
	public static OrmsAddCampingUnitsWidget getInstance(){
		if(_instance == null){
			_instance = new OrmsAddCampingUnitsWidget();
		}
		return _instance;
	}
	
	protected Property[] addNewcampingUnitQtyInput() {  
		return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression("^DropdownExt\\-\\d+\\.selectedValue$",false));
	}
	
	protected Property[] typeOfUnitDropdownList() {
		return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits\\-row:\\d+_camping_unit_equipment_type",false));
	}
	
	protected Property[] campingUnitTextFieldAttr(String name) {
		return Property.toPropertyArray(".class","Html.INPUT.text",".id",new RegularExpression(campingUnitAttributePatterPrefix+name,false));
	}
	
	protected Property[] campingUnitDropdownListAttr(String name) {
		return Property.toPropertyArray(".class","Html.SELECT",".id",new RegularExpression(campingUnitAttributePatterPrefix+name,false));
	}
	
	protected  Property[] campingProfileTableAttr() {
		return Property.toPropertyArray(".class","Html.TABLE",".id",new RegularExpression("CampingUnitsCampingInfoDetailRenderDialog_dialog_grid",false));
	}
	
	protected Property[] campingUnitQty() {
		return campingUnitTextFieldAttr("quantity");
	}
	
	protected Property[] campingUnitLength() {
		return campingUnitTextFieldAttr("length");
	}
	
	protected Property[] campingUnitDepth() {
		return campingUnitTextFieldAttr("depth");
	}
	
	protected Property[] campingUnitLicense() {
		return campingUnitTextFieldAttr("license");
	}
	
	protected Property[] campingUnitState() {
		return campingUnitDropdownListAttr("state");
	}
	
	protected Property[] campingUnitMake() {
		return campingUnitDropdownListAttr("make");
	}
	
	protected Property[] campingUnitModel() {
		return campingUnitTextFieldAttr("model");
	}
	
	protected Property[] campingUnitColor() {
		return campingUnitDropdownListAttr("color");
	}
	
	protected Property[] saveToProfileCheckbox() {
		return Property.toPropertyArray(".class","Html.INPUT.checkbox",".id","CampingUnitsSaveToProfileCheckbox");
	}
	
	protected Property[] addCampingUnits() {
		return Property.toPropertyArray(".class","Html.A",".text",new RegularExpression("^OK$",false));
	}
	
	protected Property[] cancel() {
		return Property.toPropertyArray(".class","Html.A",".text",new RegularExpression("^cancel$",false));
	}
	
	protected Property[] errorMsg() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public void setNewCampingUnitsQty(int qty) {
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(widgetProperty,addNewcampingUnitQtyInput()));
		((ISelect)objs[0]).select(Integer.toString(qty));
		Browser.unregister(objs);
	}
	
	public boolean isTypeOfUnitDisplayed() {
		return browser.checkHtmlObjectDisplayed(typeOfUnitDropdownList());
	}

	public void selectTypeOfUnit(String option) {
		browser.selectDropdownList(typeOfUnitDropdownList(), option);
	}
	
	public void selectTypeOfUnit(String option, int objIndex) {
		browser.selectDropdownList(typeOfUnitDropdownList(), option,true,objIndex,null);
	}
	
	public boolean isQtyDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitQty());
	}
	
	public void setQty(int qty) {
		browser.setTextField(campingUnitQty(), Integer.toString(qty));
	}
	
	public void setQty(int qty, int objIndex) {
		browser.setTextField(campingUnitQty(), Integer.toString(qty),true,objIndex);
	}
	
	public boolean isLengthDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitLength());
	}
	
	public void setLength(int length) {
		browser.setTextField(campingUnitLength(), Integer.toString(length));
	}
	
	public void setLength(int length, int objIndex) {
		browser.setTextField(campingUnitLength(), Integer.toString(length),true,objIndex);
	}
	
	public boolean isPlateDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitLicense());
	}
	
	public void setPlate(String plate) {
		setPlate(plate, 0);
	}
	
	public void setPlate(String plate, int objIndex) {
		browser.setTextField(campingUnitLicense(), plate, objIndex);
	}
	
	public boolean isStateDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitState());
	}
	
	public void selectState(String state) {
		selectState(state, 0);
	}

	public void selectState(String state, int index) {
		browser.selectDropdownList(campingUnitState(), state, index);
	}
	
	public boolean isMakeDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitMake());
	}
	
	public void selectMake(String make) {
		selectMake(make, 0);
	}
	
	public void selectMake(String make, int index) {
		browser.selectDropdownList(campingUnitMake(), make, index);
	}
	
	public boolean isModelDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitModel());
	}
	
	public void setModel(String model) {
		setModel(model, 0);
	}
	
	public void setModel(String model, int index) {
		browser.setTextField(campingUnitModel(), model, index);
	}
	
	public boolean isColorDisplayed() {
		return browser.checkHtmlObjectDisplayed(campingUnitColor());
	}
	
	public void selectColor(String color) {
		selectColor(color, 0);
	}
	
	public void selectColor(String color, int index) {
		browser.selectDropdownList(campingUnitColor(), color, index);
	}
	
	public void selectSaveToProfile() {
		browser.selectCheckBox(saveToProfileCheckbox());
	}
	
	public void deselectSaveToProfile() {
		browser.unSelectCheckBox(saveToProfileCheckbox());
	}
	
	public void clickAddCampingUnits() {
		IHtmlObject[] objs = browser.getHtmlObject(addCampingUnits());
		objs[0].click();
	}
	
	public void clickAddCampingUnitsForProfile() {
		IHtmlObject[] objs = browser.getHtmlObject(addCampingUnits());
		objs[1].click();
	}
	
	public void clickAddCampingUnits(int index) {
		browser.clickGuiObject(addCampingUnits(), index);
	}
	
	public void clickCancel() {
		IHtmlObject[] objs = browser.getHtmlObject(cancel());
		objs[1].click();
//		browser.clickGuiObject(cancel());
	}
	
	public void addCampingUnit(ReservationInfo res) {
		this.setNewCampingUnitsQty(1);
		this.clickAddCampingUnits();
		ajax.waitLoading();
		
		this.selectTypeOfUnit(res.site.validCampingUnit);
		if(res.site.validCampingUnitQty > 0) {
			this.setQty(res.site.validCampingUnitQty);
		}
		if(!StringUtil.isEmpty(res.site.vehicleLenth)) {
			this.setLength(Integer.parseInt(res.site.vehicleLenth));
		}
		if(!StringUtil.isEmpty(res.site.campingUnitPlate)) {
			this.setPlate(res.site.campingUnitPlate);
		}
		if(!StringUtil.isEmpty(res.site.unitState)) {
			this.selectState(res.site.unitState);
		}
		if(!StringUtil.isEmpty(res.site.unitMake)) {
			this.selectMake(res.site.unitMake);
		}
		if(!StringUtil.isEmpty(res.site.vehicleModel)) {
			this.setModel(res.site.vehicleModel);
		}
		if(!StringUtil.isEmpty(res.site.unitColor)) {
			this.selectColor(res.site.unitColor);
		}
		clickAddCampingUnits(1);
		ajax.waitLoading();
	}
	
	public void addCampingUnit(int numOfUnits, String unitType, int qty, int length, String plate) {
		this.setNewCampingUnitsQty(numOfUnits);
//		this.clickAddCampingUnits();//Sara[12/4/2013] after select new camping units qty, will have ajax. And then camping unit info displays 
		ajax.waitLoading();
		setCampingUnitInfo(unitType, qty, length, plate, false, 0);
		clickAddCampingUnits(1);
		ajax.waitLoading();
	}
	
	
	public void addRequiredCampingUnit(){//use default value, just make sure test case who not care data could passed required validation
		setNewCampingUnitsQty(1);
		ajax.waitLoading();
		setCampingUnitInfo(StringUtil.EMPTY, 1, 1, StringUtil.EMPTY, false, 0);
		clickAddCampingUnits(1);
		ajax.waitLoading();
	}
	
	public void addCampingUnit(List<CampingUnit> units){
		this.setNewCampingUnitsQty(units.size());
		ajax.waitLoading();
		for(int i=0;i<units.size();i++){
			setCampingUnitInfo(units.get(i).typeOfUnit, Integer.parseInt(units.get(i).quantity), Integer.parseInt(units.get(i).length), StringUtil.EMPTY, false, i);
		}
		clickAddCampingUnits(1);
		ajax.waitLoading();
	}

	public void setCampingUnitInfo(String unitType,int qty,int length, String plate, boolean saveToCust,int index){
		if(StringUtil.notEmpty(unitType)){
			this.selectTypeOfUnit(unitType, index);
		}
		this.setQty(qty, index);
		this.setLength(length, index);
		this.setPlate(plate, index);
		if(saveToCust){
			this.selectSaveToProfile();
		}else{
			deselectSaveToProfile();
		}
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(errorMsg());
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(errorMsg());
	}
	
	/**
	 * This method used to select complete from primary occupant profile check
	 * box
	 */
	public void selectCompleteFromOccProfileBox() {
		IHtmlObject[] objs = browser.getHtmlObject(campingProfileTableAttr());
		IHtmlTable table = (IHtmlTable)objs[0];
		IHtmlObject[] checkBoxes = browser.getCheckBox(Property.toPropertyArray(".value","all"), table);
			((ICheckBox) checkBoxes[0]).select();
		Browser.unregister(objs);
		Browser.unregister(checkBoxes);
	}
	
	public void deleteCampingUnitInfo(int index){
		browser.clickGuiObject(".id", new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:" +index +  "_delete",false), ".class", "Html.A");
	}
}
