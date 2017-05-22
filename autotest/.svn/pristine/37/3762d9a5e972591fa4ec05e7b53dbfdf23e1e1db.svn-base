package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.datadefinition.web.pos.ORVPermitPOSAttr;
import com.activenetwork.qa.awo.pages.web.uwp.UwpHeaderBar;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang5
 * @Date  Apr 15, 2014
 */
public class RecgovCapeHattterasORVPermitSalePage extends UwpHeaderBar {
	static class SingletonHolder {
		protected static RecgovCapeHattterasORVPermitSalePage _instance = new RecgovCapeHattterasORVPermitSalePage();
	}

	protected RecgovCapeHattterasORVPermitSalePage() {
	}

	public static RecgovCapeHattterasORVPermitSalePage getInstance() {
		return SingletonHolder._instance;
	}

	public final String LABEL_WEEKLY_ORV_PERMIT = "Weekly ORV Permit \\(\\$\\d+\\)";
	public final String LABEL_ANNUAL_ORV_PERMIT = "\\d+ Annual ORV Permit \\(\\$\\d+\\)";
	public final String LABEL_PICK_UP_AT_PARK = "Pick up at Park";
	public final String LABEL_STANDARD_SHIPPING = "Standard Shipping";
	public final String LABEL_START_DATE = "Start Date:";
	
	/** Page Object Property Definition Begin */
	protected Property[] weeklyORVPermitRadio(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}
	
	protected Property[] weeklyORVPermitRadio(){
		return Property.toPropertyArray(".name", "passrb", ".id", "weeklypass");
	}
	
	protected Property[] annualOrvPermitRadio(){
		return Property.toPropertyArray(".name", "passrb", ".id", "anualpass");
	}
	
	protected Property[] annualOrvPermitRadio(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}
	
	protected Property[] pickUpAtParkRadio(){
		return Property.toPropertyArray(".name", "passrb2", ".id", "parkpickup");
	}
	
	protected Property[] pickUpAtParkRadio(String id){
		return Property.toPropertyArray(".name", "passrb2", ".id", id);
	}
	
	protected Property[] standardShippingRadio(){
		return Property.toPropertyArray(".name", "passrb2", ".id", "standardship");
	}
	
	protected Property[] standardShippingRadio(String id){
		return Property.toPropertyArray(".className", "passrb2", ".id", id);
	}
	
	protected Property[] permitOffice(){
		return Property.toPropertyArray(".id", "issuingstation");
	}
	
	protected Property[] standardShippingNote(){
		return Property.concatPropertyArray(label(), ".text", "(+$6.50 delivery charge. Please allow 14 days for delivery.)");
	}
	
	protected Property[] labelProp(String labelReg){
		return Property.concatPropertyArray(label(), ".text", new RegularExpression(labelReg, false));
	}
	
	protected Property[] startDate(){
		return Property.toPropertyArray(".id", "datepicker");
	}
	
	protected Property[] startDate(String id){
		return Property.toPropertyArray(".id", id);
	}
	
	protected Property[] formFieldWrapper(String text){
		return Property.toPropertyArray(".className", "form_field_wrapper", ".text", new RegularExpression(text, false));
	}
	
	protected Property[] formFieldWrapper(){
		return Property.toPropertyArray(".className", "form_field_wrapper");
	}
	
	protected Property[] driverLicenseNum(){
		return Property.toPropertyArray(".id", "driverlicense");
	}
	
	protected Property[] driverLicenseCountry(){
		return Property.toPropertyArray(".id", "drivercountry");
	}
	
	protected Property[] driverLicenseState(){
		return Property.toPropertyArray(".id", "driverstate");
	}
	
	protected Property[] driverLicenseStateInput(){
		return Property.toPropertyArray(".id", "driverstateInput");
	}
	
	protected Property[] vehiclePlateNum(){
		return Property.toPropertyArray(".id", "vehicleplate");
	}
	
	protected Property[] vehiclePlateCountry(){
		return Property.toPropertyArray(".id", "vehiclecountry");
	}
	
	protected Property[] vehiclePlateState(){
		return Property.toPropertyArray(".id", "vehiclestate");
	}
	
	protected Property[] vehiclePlateStateInput(){
		return Property.toPropertyArray(".id", "vehiclestateInput");
	}
	
	protected Property[] ownerName(){
		return Property.toPropertyArray(".id", "ownername");
	}
	
	protected Property[] vehicleYear(){
		return Property.toPropertyArray(".id", "vehicleyear");
	}
	
	protected Property[] vehicleMake(){
		return Property.toPropertyArray(".id", "vehiclemake");
	}
	
	protected Property[] vehicleMakeInput(){
		return Property.toPropertyArray(".id", "vehiclemakeInput");
	}
	
	protected Property[] vehicleModel(){
		return Property.toPropertyArray(".id", "vehiclemodel");
	}
	
	protected Property[] vehicleColor(){
		return Property.toPropertyArray(".id", "vehiclecolor");
	}
	
	protected Property[] vehicleColorInput(){
		return Property.toPropertyArray(".id", "vehiclecolorInput");
	}
	
	protected Property[] alertMsg(){
		return Property.concatPropertyArray(div(), ".className", "msg alert");
	}
	
	protected Property[] backToPreviousPage(){
		return Property.concatPropertyArray(a(), ".className", "CampName", ".text", "Back to Previous Page");
	}
	
	protected Property[] addToshoppingCart(){
		return Property.toPropertyArray(".id", "continueshop");
	}
	
	protected Property[] player(){
		return Property.toPropertyArray(".id", "player", ".title", "YouTube video player", ".src", new RegularExpression("http://www\\.youtube\\.com.*", false));
	}
	
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(weeklyORVPermitRadio());
	}
	
	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(labelProp(labelReg));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}
	
	public void clickWeeklyORVPermitRadio(){
		browser.clickGuiObject(weeklyORVPermitRadio(getObjIDByLabel(LABEL_WEEKLY_ORV_PERMIT)));
	}
	
	public void setWeeklyORVPermitStartDate(String startDate){
		browser.setTextField(startDate(), startDate);
	}
	
	public void clickAnnualOrvPermitRadio(){
		browser.clickGuiObject(annualOrvPermitRadio(getObjIDByLabel(LABEL_ANNUAL_ORV_PERMIT)));
	}
	
	public void clickPickUpAtParkRadio(){
		browser.clickGuiObject(pickUpAtParkRadio(getObjIDByLabel(LABEL_PICK_UP_AT_PARK)));
	}
	
	public void clickStandardShippingRadio(){
		browser.clickGuiObject(standardShippingRadio(getObjIDByLabel(LABEL_STANDARD_SHIPPING)));
	}
	
	public List<String> getPermitOffices(){
		return browser.getDropdownElements(permitOffice());
	}
	
	public String getPermitOffice(){
		return browser.getDropdownListValue(permitOffice(), 0);
	}
	
	public boolean isStandardShippingNoteExist(){
		return browser.checkHtmlObjectExists(standardShippingNote());
	}
	
	public String getPageTitle(){
		return browser.getObjectText(pageTitleDiv());
	}
	
	public boolean isStartDateDisabled(){
		return !browser.checkHtmlObjectEnabled(startDate(getObjIDByLabel(LABEL_START_DATE)));
	}
	
	public boolean isFormFieldWrapperExist(String text){
		IHtmlObject[] objs = browser.getHtmlObject(formFieldWrapper());
		for(int i=0; i<objs.length; i++){
			System.out.println(objs[i].text());
		}
		return browser.checkHtmlObjectExists(formFieldWrapper(text));
	}
	
	public void setDriverLicenseNum(String driverLicenseNum){
		browser.setTextField(driverLicenseNum(), driverLicenseNum);
	}
	
	public String getDriverLicenseNum(){
		return browser.getTextFieldValue(driverLicenseNum());
	}
	
	public void selectDriverLicenseCountry(String driverLicenseCountry){
		browser.selectDropdownList(driverLicenseCountry(), driverLicenseCountry);
	}
	
	public String getDriverLicenseCountry(){
		return browser.getDropdownListValue(driverLicenseCountry(), 0);
	}
	
	public List<String> getDriverLicenseCountries(){
		return browser.getDropdownElements(driverLicenseCountry());
	}

	public void selectDriverLicenseState(String driverLicenseState){
		browser.selectDropdownList(driverLicenseState(), driverLicenseState);
	}
	
	public String getDriverLicenseState(){
		return browser.getDropdownListValue(driverLicenseState(), 0);
	}
	
	public List<String> getDriverLicenseStates(){
		return browser.getDropdownElements(driverLicenseState());
	}
	
	public void setDriverLicenseStateInput(String driverLicenseStateInput){
		browser.setTextField(driverLicenseStateInput(), driverLicenseStateInput);
	}
	
	public String getDriverLicenseStateInput(){
		return browser.getTextFieldValue(driverLicenseStateInput());
	}
	
	public boolean isTextFieldExist(Property[] p){
		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find test field.");
		}
		boolean block = objs[0].style("display").trim().equalsIgnoreCase("block");
		Browser.unregister(objs);
		if(block){
			return true;
		}else return false;
	}
	
	public boolean isDriverLicenseStateInputExist(){
		return isTextFieldExist(driverLicenseStateInput());
	}
	
	public void setVehiclePlateNum(String vehiclePlateNum){
		browser.setTextField(vehiclePlateNum(), vehiclePlateNum);
	}
	
	public String getVehiclePlateNum(){
		return browser.getTextFieldValue(vehiclePlateNum());
	}
	
	public void selectVehiclePlateCountry(String vehiclePlateCountry){
		browser.selectDropdownList(vehiclePlateCountry(), vehiclePlateCountry);
	}
	
	public String getVehiclePlateCountry(){
		return browser.getDropdownListValue(vehiclePlateCountry(), 0);
	}
	
	public List<String> getVehiclePlateCountries(){
		return browser.getDropdownElements(vehiclePlateCountry());
	}
	
	public void selectVehiclePlateState(String vehiclePlateState){
		browser.selectDropdownList(vehiclePlateState(), vehiclePlateState);
	}
	
	public String getVehiclePlateState(){
		return browser.getDropdownListValue(vehiclePlateState(), 0);
	}
	
	public List<String> getVehiclePlateStates(){
		return browser.getDropdownElements(vehiclePlateState());
	}
	
	public void setVehiclePlateStateInput(String vehiclePlateStateInput){
		browser.setTextField(vehiclePlateStateInput(), vehiclePlateStateInput);
	}
	
	public String getVehiclePlateStateInput(){
		return browser.getTextFieldValue(vehiclePlateStateInput());
	}
	
	public boolean isVehiclePlateStateInputExist(){
		return isTextFieldExist(vehiclePlateStateInput());
	}
	
	public void setOwnerName(String ownerName){
		browser.setTextField(ownerName(), ownerName);
	}
	
	public String getOwnerName(){
		return browser.getTextFieldValue(ownerName());
	}
	
	public void setVehicleYear(String vehicleYear){
		browser.setTextField(vehicleYear(), vehicleYear);
	}
	
	public String getVehicleYear(){
		return browser.getTextFieldValue(vehicleYear());
	}
	
	public void selectVehicleMake(String make){
		browser.selectDropdownList(vehicleMake(), make);
	}
	
	public String getVehicleMake(){
		return browser.getDropdownListValue(vehicleMake(), 0);
	}
	
	public List<String> getVehicleMakes(){
		return browser.getDropdownElements(vehicleMake());
	}
	
	public void setVehicleMakeInput(String makeInput){
		browser.setTextField(vehicleMakeInput(), makeInput);
	}
	
	public String getVehicleMakeInput(){
		return browser.getTextFieldValue(vehicleMakeInput());
	}
	
	public boolean isVehicleMakeInputExist(){
		return isTextFieldExist(vehicleMakeInput());
	}
	
	public void setVehicleModel(String vehicleModel){
		browser.setTextField(vehicleModel(), vehicleModel);
	}
	
	public String getVehicleModel(){
		return browser.getTextFieldValue(vehicleModel());
	}

	public void selectVehicleColor(String vehicleColor){
		browser.selectDropdownList(vehicleColor(), vehicleColor);
	}
	
	public String getVehicleColor(){
		return browser.getDropdownListValue(vehicleColor(), 0);
	}
	
	public List<String> getVehicleColors(){
		return browser.getDropdownElements(vehicleColor());
	}
	
	public void setVehicleColorInput(String vehicleColorInput){
		browser.setTextField(vehicleColorInput(), vehicleColorInput);
	}
	
	public String getVehicleColorInput(){
		return browser.getTextFieldValue(vehicleColorInput());
	}
	
	public boolean isVehicleColorInputExist(){
		return isTextFieldExist(vehicleColorInput());
	}
	
	public String getAlertMsg(){
		return browser.getObjectText(alertMsg());
	}
	
	public void clickAlertMsgToRefreshPg(){
		browser.clickGuiObject(alertMsg());
	}
	
	public void clickBackToPreviousPage(){
		browser.clickGuiObject(backToPreviousPage());
	}
	
	public void clickAddToShoppingCart(){
		browser.clickGuiObject(addToshoppingCart());
	}
	
	public boolean isAddToShoppingCartEnabled(){
		return browser.checkHtmlObjectEnabled(addToshoppingCart());
	}
	
	public void clickPlayer(){
		browser.clickGuiObject(player(), true);
	}
	
	public void makeVideo(){
		boolean isAddToShoppingCartEnabled = false;
		long maxWaitTime=(OrmsConstants.FILE_DIALOG_LONG_SLEEP*2)*60*7;
		Timer time = new Timer();

		clickPlayer();
		browser.inputKey(KeyInput.get(KeyInput.ARROW_RIGHT));
		do{
			isAddToShoppingCartEnabled = isAddToShoppingCartEnabled();
		}while(time.diffLong() < maxWaitTime && !isAddToShoppingCartEnabled);
		if(!isAddToShoppingCartEnabled) {
			throw new ItemNotFoundException("Add to shopping cart button is disabled in "+maxWaitTime+" ms");
		}
	}
	
	public void setupCapeHatterasORVPermitPosInfo(Data<ORVPermitPOSAttr> permitPOS){
		if(ORVPermitPOSAttr.typeOfPermit.getStrValue(permitPOS).equalsIgnoreCase("Weekly")){
			clickWeeklyORVPermitRadio();
		}else clickAnnualOrvPermitRadio();
//		setBoatName(BoatAttr.boatName.getStrValue(src));
	}
}
