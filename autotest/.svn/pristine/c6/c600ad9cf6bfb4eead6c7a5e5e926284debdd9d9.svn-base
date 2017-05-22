package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.datacollection.datadefinition.web.marina.BoatAttr;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Nov 15, 2013
 */
public class RAAddBoatPage extends UwpHeaderBar{
	public final String LABEL_BOATNAME = "Boat Name";
	public final String LABEL_REGISTRATIONE = "Registration #";
	public final String LABEL_BOATTYPE = "Boat Type";
	public final String LABEL_LENGTH_FT = "Length \\(ft\\)";
	public final String LABEL_WIDTH_FT = "Width \\(ft\\)";
	public final String LABEL_DEPTH_FT = "Depth \\(ft\\)";
	public final String LABEL_BOATCATEGORY = "Boat Category";
	public final String LABEL_CAPACITY = "Capacity";
	public final String LABEL_COLOR = "Color";
	public final String LABEL_HORSEPOWER = "Horse Power";
	public final String LABEL_YEAR = "Year \\(YYYY\\)";
	public final String LABEL_HULLIDENTIFICATION = "Hull Identification";
	public final String LABEL_MODEL = "Model";
	public final String LABEL_MANUFACTURE = "Manufacture";
	public final String LABEL_MOTORMANUFACTURE = "Motor Manufacture";
	public final String LABEL_CONSTRUCTION = "Construction";
	public final String LABEL_TRAILERTYPE = "Trailer Type";
	public final String LABEL_TRAILERLICENSENUM= "Trailer License #";
	
	static class SingletonHolder {
		protected static RAAddBoatPage _instance = new RAAddBoatPage();
	}

	protected RAAddBoatPage() {
	}

	public static RAAddBoatPage getInstance() {
		return SingletonHolder._instance;
	}
	
	public boolean exists(){
		return browser.checkHtmlObjectExists(pageTitle()) && super.exists();
	}
	
	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle", ".text", "Equipment Management - Add a Boat");
	}
	
	protected Property[] save(){
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Save");
	}
	
	protected Property[] cancel(){
		return Property.concatPropertyArray(a(), ".className", "cancelLink", ".text", "Cancel");
	}
	
	protected Property[] prevPage(){
		return Property.concatPropertyArray(a(), ".className", "prevPage", ".text", "< Previous Page");
	}
	
	protected Property[] boatElementLabel (String labelReg){
		return Property.concatPropertyArray(label(), ".text", new RegularExpression("^" + labelReg + ".*", false));
	}
	
	protected Property[] errorMes(String errorMesRegx){
		return Property.concatPropertyArray(div(), ".className", new RegularExpression("headspacer msg topofpage error|error_item", false), ".text", new RegularExpression(errorMesRegx, false));
	}
	
	protected Property[] boatElementID (String id){
		return Property.toPropertyArray(".id", id);
	}
	
	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(boatElementLabel(labelReg));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}
	
	public void setBoatName(String boatName) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_BOATNAME)), boatName);
	}
	
	public String getBoatName() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_BOATNAME)));
	}
	
	public void setRegistrationNum(String registrationNum) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_REGISTRATIONE)), registrationNum);
	}
	
	public String getRegistrationNum() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_REGISTRATIONE)));
	}
	
	public void selectBoatType(String boatType) {
		browser.selectDropdownList(boatElementID(this.getObjIDByLabel(LABEL_BOATTYPE)), boatType);
	}
	
	public String getBoatType() {
		return browser.getDropdownListValue(boatElementID(this.getObjIDByLabel(LABEL_BOATTYPE)), 0);
	}
	
	public void setLengthFT(String length) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_LENGTH_FT)), length);
	}
	
	public String getLengthFT() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_LENGTH_FT)));
	}
	
	public void setWidthFT(String width) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_WIDTH_FT)), width);
	}
	
	public String getWidthFT() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_WIDTH_FT)));
	}
	
	public void setDepthFT(String depth) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_DEPTH_FT)), depth);
	}
	
	public String getDepthFT() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_DEPTH_FT)));
	}
	
	public void selectBoatCategory(String boatCategory) {
		browser.selectDropdownList(boatElementID(this.getObjIDByLabel(LABEL_BOATCATEGORY)), boatCategory);
	}
	
	public String getBoatCategory() {
		return browser.getDropdownListValue(boatElementID(this.getObjIDByLabel(LABEL_BOATCATEGORY)), 0);
	}
	
	public void setCapacity(String capacity) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_CAPACITY)), capacity);
	}
	
	public String getCapacity() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_CAPACITY)));
	}
	
	public void setColor(String color) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_COLOR)), color);
	}
	
	public String getColor() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_COLOR)));
	}
	
	public void setHorsePower(String horsePower) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_HORSEPOWER)), horsePower);
	}
	
	public String getHorsePower() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_HORSEPOWER)));
	}
	
	public void setYear(String year) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_YEAR)), year);
	}
	
	public String getYear() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_YEAR)));
	}
	
	public void setHullIdentification(String hullIdentification) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_HULLIDENTIFICATION)), hullIdentification);
	}
	
	public String getHullIdentification() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_HULLIDENTIFICATION)));
	}
	
	public void setModel(String model) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_MODEL)), model);
	}
	
	public String getModel() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_MODEL)));
	}
	
	public void setManufacture(String manufacture) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_MANUFACTURE)), manufacture);
	}
	
	public String getManufacture() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_MANUFACTURE)));
	}
	
	public void setMotorManufacture(String motorManufacture) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_MOTORMANUFACTURE)), motorManufacture);
	}
	
	public String getMotorManufacture() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_MOTORMANUFACTURE)));
	}
	
	public void setConstruction(String construction) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_CONSTRUCTION)), construction);
	}
	
	public String getConstruction() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_CONSTRUCTION)));
	}
	
	public void setTrailerType(String trailerType) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_TRAILERTYPE)), trailerType);
	}
	
	public String getTrailerType() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_TRAILERTYPE)));
	}
	
	public void setTrailerLocenseNum(String trailerLocenseNum) {
		browser.setTextField(boatElementID(this.getObjIDByLabel(LABEL_TRAILERLICENSENUM)), trailerLocenseNum);
	}
	
	public String getTrailerLocenseNum() {
		return browser.getTextFieldValue(boatElementID(this.getObjIDByLabel(LABEL_TRAILERLICENSENUM)));
	}
	
	public void setupBoatInfo(Data<BoatAttr> src){
		setBoatName(BoatAttr.boatName.getStrValue(src));
		setRegistrationNum(BoatAttr.registrationNum.getStrValue(src));
		selectBoatType(BoatAttr.boatType.getStrValue(src));
		setLengthFT(BoatAttr.length_ft.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.width_ft.getStrValue(src)))
			setWidthFT(BoatAttr.width_ft.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.depth_ft.getStrValue(src)))
			setDepthFT(BoatAttr.depth_ft.getStrValue(src));
		selectBoatCategory(BoatAttr.boatCategory.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.capacity.getStrValue(src)))
			setCapacity(BoatAttr.capacity.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.color.getStrValue(src)))
			setColor(BoatAttr.color.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.horsePower.getStrValue(src)))
			setHorsePower(BoatAttr.horsePower.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.year.getStrValue(src)))
			setYear(BoatAttr.year.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.hullIden.getStrValue(src)))
			setHullIdentification(BoatAttr.hullIden.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.model.getStrValue(src)))
			setModel(BoatAttr.model.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.manufacture.getStrValue(src)))
			setManufacture(BoatAttr.manufacture.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.motorManufacture.getStrValue(src)))
			setMotorManufacture(BoatAttr.motorManufacture.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.construction.getStrValue(src)))
			setConstruction(BoatAttr.construction.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.trailerType.getStrValue(src)))
			setTrailerType(BoatAttr.trailerType.getStrValue(src));
		if(StringUtil.notEmpty(BoatAttr.trailerLicense.getStrValue(src)))
			setTrailerLocenseNum(BoatAttr.trailerLicense.getStrValue(src));
	}
	
	public void verifyBoatInfo(Data<BoatAttr> src){
		boolean result = MiscFunctions.compareResult("BoatName", BoatAttr.boatName.getStrValue(src), getBoatName());
		result &= MiscFunctions.compareResult("RegistrationNum", BoatAttr.registrationNum.getStrValue(src), getRegistrationNum());
		result &= MiscFunctions.compareResult("BoatType", BoatAttr.boatType.getStrValue(src), getBoatType());
		result &= MiscFunctions.compareResult("LengthFT", BoatAttr.length_ft.getStrValue(src), getLengthFT());
		result &= MiscFunctions.compareResult("WidthFT", BoatAttr.width_ft.getStrValue(src), getWidthFT());
		result &= MiscFunctions.compareResult("DepthFT", BoatAttr.depth_ft.getStrValue(src), getDepthFT());
		result &= MiscFunctions.compareResult("BoatCategory", BoatAttr.boatCategory.getStrValue(src), getBoatCategory());
		result &= MiscFunctions.compareResult("Capacity", BoatAttr.capacity.getStrValue(src), getCapacity());
		result &= MiscFunctions.compareResult("Color", BoatAttr.color.getStrValue(src), getColor());
		result &= MiscFunctions.compareResult("HorsePower", BoatAttr.horsePower.getStrValue(src), getHorsePower());
		result &= MiscFunctions.compareResult("Year", BoatAttr.year.getStrValue(src), getYear());
		result &= MiscFunctions.compareResult("Hull Identification", BoatAttr.hullIden.getStrValue(src), getHullIdentification());
		result &= MiscFunctions.compareResult("Model", BoatAttr.model.getStrValue(src), getModel());
		result &= MiscFunctions.compareResult("Manufacture", BoatAttr.manufacture.getStrValue(src), getManufacture());
		result &= MiscFunctions.compareResult("Motor Manufacture", BoatAttr.motorManufacture.getStrValue(src), getMotorManufacture());
		result &= MiscFunctions.compareResult("Constrction", BoatAttr.construction.getStrValue(src), getConstruction());
		result &= MiscFunctions.compareResult("TrailerType", BoatAttr.trailerType.getStrValue(src), getTrailerType());
		result &= MiscFunctions.compareResult("TrailerLicense", BoatAttr.trailerLicense.getStrValue(src), getTrailerLocenseNum());
		
		if(!result){
			throw new ErrorOnPageException("Boat info are wrong! Please check the details from previous logs.");
		}
	}
	
	public void clickSave(){
		browser.clickGuiObject(save());
	}
	
	public void clickCancel(){
		browser.clickGuiObject(cancel());
	}
	
	public void clickPrevPage(){
		browser.clickGuiObject(prevPage());
	}
	
	public boolean isErrorMsgExist(String errorMesRegx) {
		return browser.checkHtmlObjectExists(errorMes(errorMesRegx));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
	
	public void verifyErrorMsgExist(String msg){
		verifyErrorMsgExist(msg, true);
	}
	
}
