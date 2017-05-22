package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.TitleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DealerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:Vehidle title details info page.
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jwang8
 * @Date  Jun, 06,2012
 */
public class LicMgrVehicleTitleDetialsInfoPage extends LicMgrVehicleProductCommonPage{
    private static LicMgrVehicleTitleDetialsInfoPage _Instance = null;
    
    protected LicMgrVehicleTitleDetialsInfoPage(){
    	
    }
    
    public static LicMgrVehicleTitleDetialsInfoPage getInstance(){
    	if(null == _Instance){
    		_Instance = new LicMgrVehicleTitleDetialsInfoPage();
    	}
    	return _Instance;
    }
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".id",new RegularExpression("VehicleDetailsTitleView-\\d+.titleNumber", false));
	}
	/**
	 * click surrender title.
	 */
	public void clickSurrenderTitle(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Surrender Title");
	}
	
	public void clickSetToTransferable(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Set to Transferable");
	}
	
	public void clickReactiveTitle(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Reactivate Title");
	}
	
	public void surrenderTitle(){
		this.clickSurrenderTitle();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void setToTransferable(){
		this.clickSetToTransferable();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void reactiveTitle(){
		this.clickReactiveTitle();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Get attribute value
	 * @param attributeName-- the attribute name
	 * @return the input text value.
	 */
	private String getAttributeValueByName(String attributeName){
		String attributeValue = "";
		Property property[] = new Property[3];
		IHtmlObject divObjs[] = null;
		
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName+".*", false));
		divObjs = browser.getHtmlObject(property);
		String attributeNameAndValue = divObjs[0].getProperty(".text");
		
		if(divObjs.length > 0){
			int arrayLength = attributeNameAndValue.split(attributeName).length;
			if(arrayLength > 1) {
				attributeValue = attributeNameAndValue.split(attributeName)[1].trim();
			} else attributeValue = "";
		}
		return attributeValue;
	}
	/**
	 * get title number
	 * @return
	 */
	public String getTitleNumber(){
		return this.getAttributeValueByName("Title #");
	}
	/**
	 * get title id;
	 * @return
	 */
	public String getTitleId(){
		return this.getAttributeValueByName("Title ID");
	}
	/**
	 * get status
	 * @return
	 */
	public String getStatus(){
		return this.getAttributeValueByName("Status");
	}
	/**
	 * get product.
	 * @return
	 */
	public String getProduct(){
		return this.getAttributeValueByName("Product");
	}
	/**
	 * get dupicates number.
	 * @return
	 */
	public String getDuplicatesNumber(){
		return this.getAttributeValueByName("# Duplicates");
	}
	/**
	 * get corrections
	 * @return
	 */
	public String getCorrections(){
		return this.getAttributeValueByName("# Corrections");
	}
	/*
	 * get creation price.
	 */
	public String getCreationPrice(){
		return this.getAttributeValueByName("Creation Price");
	}
	/**
	 * set boat value.
	 * @param value
	 */
	public void setBoatValue(String value){
		browser.setTextField(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.vehicleValue:CURRENCY_INPUT",false), value);
	}
	/**
	 * get Boat value.
	 * @return
	 */
	public String getBoatValue(){
		String value = browser.getObjectText(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.vehicleValue:CURRENCY",false)).split("Value")[1].replace("$", "").trim();
		return value;
	}
	
	/**
	 * compare the  title info.
	 * @param titleInfo
	 * @return
	 */
	public boolean compareVehicleTitleDetailsInfo(TitleInfo titleInfo){
		boolean pass = true;
		
		logger.info("Compare vehicle title details info.");
		pass &= MiscFunctions.compareResult("Title ID", titleInfo.titleId, getTitleId());
		pass &= MiscFunctions.compareResult("Title Status", titleInfo.status, getStatus());
		pass &= MiscFunctions.compareResult("Product", titleInfo.product.replaceAll("\\s*", ""), getProduct().replaceAll("\\s*", ""));
		pass &= MiscFunctions.compareResult("Num of Duplicates", titleInfo.numOfDuplicates, getDuplicatesNumber());
		pass &= MiscFunctions.compareResult("Num of Corrections", titleInfo.numOfCorrections, getCorrections());
		
		String temp = this.getBoatValue();
		temp = temp.replaceAll("\\$", "");
		String vehicleValue = "";
		if(titleInfo.boatValue.length() > 0) {
			vehicleValue = titleInfo.boatValue;
		} else if(titleInfo.getMotorValue().length() > 0) {
			vehicleValue = titleInfo.getMotorValue();
		} else if(titleInfo.getDealerValue().length()>0){
			vehicleValue = titleInfo.getDealerValue();
		}
		pass &= MiscFunctions.compareResult("Boat/Motor Value", Double.parseDouble(vehicleValue), Double.parseDouble(temp));

		return pass;
	}
	
	
	public String getCustomerStatus() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.customerStatus\\:CB_TO_NAME", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find customer status object.");
		}
		String status = objs[0].getProperty(".text").split("Status")[1].trim();
		
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 * get vehicle customer info
	 * @return
	 */
	public Customer getVehicleCustomerInfo() {
		Customer cust = new Customer();
		cust.custNum = getAttributeValueByName("MDWFP #");
		cust.status = getCustomerStatus();
		cust.customerClass = getAttributeValueByName("Customer Class");
		cust.fName = getAttributeValueByName("First Name");
		cust.mName = getAttributeValueByName("Middle Name");
		cust.lName = getAttributeValueByName("Last Name");
		cust.suffix = getAttributeValueByName("Suffix");
		cust.dateOfBirth = getAttributeValueByName("Date of Birth");
		
		return cust;
	}
	
	/**
	 * compare vehicle customer info
	 * @param expected
	 * @return
	 */
	public boolean compareVehicleCustomerInfo(Customer expected) {
		boolean result = true;
		
		Customer actual = getVehicleCustomerInfo();
		result &= MiscFunctions.compareResult("Customer Num", expected.custNum, actual.custNum);
		result &= MiscFunctions.compareResult("Customer Status", expected.status, actual.status);
		result &= MiscFunctions.compareResult("Customer Class", expected.customerClass, actual.customerClass);
		result &= MiscFunctions.compareResult("Customer First Name", expected.fName, actual.fName);
		result &= MiscFunctions.compareResult("Customer Middle Name", expected.mName, actual.mName);
		result &= MiscFunctions.compareResult("Customer Last Name", expected.lName, actual.lName);
		result &= MiscFunctions.compareResult("Customer Suffix", expected.suffix, actual.suffix);
		result &= MiscFunctions.compareResult("Customer Date of Birth", expected.dateOfBirth, actual.dateOfBirth);
		
		return result;
	}
	
	/**
	 * for Boat
	 * @return
	 */
	public String getVehicleNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.vehicleNum", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find MI # object.");
		}
		String status = objs[0].getProperty(".text").split(DataBaseFunctions.getTranslatableLabelValue("translatable.vehiclenum", this.getContract()))[1].trim();//X_TRANSLATION
		
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 * for Motor, it doesn't need to register so there is no MI#
	 * @return
	 */
	public String getVehicleID() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.vehicleId", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Vehicle ID object.");
		}
		String status = objs[0].getProperty(".text").split("ID")[1].trim();//X_TRANSLATION
		
		Browser.unregister(objs);
		return status;
	}
	
	public String getVehicleStatus() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression("VehicleDetailsTitleView-\\d+\\.vehicleStatus:CB_TO_NAME", false));
		if(objs.length < 1) {
		}
		String status = objs[0].getProperty(".text").split("Status")[1].trim();
		
		Browser.unregister(objs);
		return status;
	}
	
	/**
	 * Get vehicle info, including Boat, Dealer and Motor
	 * @return
	 */
	public BoatInfo getBoatInfo() {
		BoatInfo boat = new BoatInfo();
		
		boat.id = getVehicleNum();
		boat.status = getVehicleStatus();
		boat.hullIdSerialNum = getAttributeValueByName("Serial #");
		boat.manufacturerName = getAttributeValueByName("Manufacturer Name");
		boat.manufacturerPrintName = getAttributeValueByName("Manufacturer Print Name");
		boat.modelYear = getAttributeValueByName("Model Year");
		boat.horsePower = getAttributeValueByName("Horsepower");
		
		return boat;
	}
	
	public MotorInfo getMotorInfo() {
		MotorInfo motor = new MotorInfo();
		
		motor.id = getVehicleID();
		motor.status = getVehicleStatus();
		motor.setSerialNum(getAttributeValueByName("Serial #"));
		motor.setManufacturerName(getAttributeValueByName("Manufacturer Name"));
		motor.setManufacturerPrintName(getAttributeValueByName("Manufacturer Print Name"));
		motor.setModelYear(getAttributeValueByName("Model Year"));
		motor.setHorsePower(getAttributeValueByName("Horsepower"));
		
		return motor;
	}
	
	public DealerInfo getDealerInfo() {
		DealerInfo dealer = new DealerInfo();
		
		dealer.id = getVehicleNum();
		dealer.status = getVehicleStatus();
		//TODO
		
		return dealer;
	}
	
	/**
	 * compare vehicle info, including Boat, Dealer and Motor
	 * @param expected
	 * @return
	 */
	public boolean compareVehicleInfo(Vehicle expected) {
		boolean result = true;
		
		Vehicle actual = null;
		
		if(expected instanceof BoatInfo) {
			actual = getBoatInfo();
			result &= MiscFunctions.compareResult("Boat ID", expected.id, actual.id);
			result &= MiscFunctions.compareResult("Boat Status", expected.status, actual.status);
			result &= MiscFunctions.compareResult("Boat Serial #/Hull ID", ((BoatInfo)expected).hullIdSerialNum, ((BoatInfo)actual).hullIdSerialNum);
			result &= MiscFunctions.compareResult("Boat Manufacturer Name", ((BoatInfo)expected).manufacturerName.toUpperCase(), ((BoatInfo)actual).manufacturerName.toUpperCase());
			result &= MiscFunctions.compareResult("Boat Manufacturer Print Name", ((BoatInfo)expected).manufacturerPrintName.toUpperCase(), ((BoatInfo)actual).manufacturerPrintName.toUpperCase());
			result &= MiscFunctions.compareResult("Boat Model Year", ((BoatInfo)expected).modelYear, ((BoatInfo)actual).modelYear);
			result &= MiscFunctions.compareResult("Boat Horsepower", ((BoatInfo)expected).horsePower, ((BoatInfo)actual).horsePower);
		} else if(expected instanceof MotorInfo) {
			actual = getMotorInfo();
			result &= MiscFunctions.compareResult("Motor ID", expected.id, actual.id);
			result &= MiscFunctions.compareResult("Motor Status", expected.status, actual.status);
			result &= MiscFunctions.compareResult("Motor Serial #", ((MotorInfo)expected).getSerialNum(), ((MotorInfo)actual).getSerialNum());
			result &= MiscFunctions.compareResult("Motor Manufacturer Name", ((MotorInfo)expected).getManufacturerName().toUpperCase(), ((MotorInfo)actual).getManufacturerName().toUpperCase());
			result &= MiscFunctions.compareResult("Motor Manufacturer Print Name", ((MotorInfo)expected).getManufacturerPrintName().toUpperCase(), ((MotorInfo)actual).getManufacturerPrintName().toUpperCase());
			result &= MiscFunctions.compareResult("Motor Model Year", ((MotorInfo)expected).getModelYear(), ((MotorInfo)actual).getModelYear());
			result &= MiscFunctions.compareResult("Motor Horsepower", ((MotorInfo)expected).getHorsePower(), ((MotorInfo)actual).getHorsePower());
		} else if(expected instanceof DealerInfo) {
			actual = getDealerInfo();
//			result &= MiscFunctions.compareResult("Dealer ID", expected.id, actual.id);
			//result &= MiscFunctions.compareResult("MI#", expected.registration.miNum, actual.registration.miNum);
			result &= MiscFunctions.compareResult("Dealer Status", expected.status, actual.status);
		}// else throw new ItemNotFoundException("Unkown Vehicle product type - " + expected.getClass().getName());
		
		return result;
	}
	
    /*
     * click liens table.
     */
	public void clickLiensTab(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Liens.*", false));
	}
	/*
	 *Click history. 
	 */
	public void clickHistoryTab(){
		browser.clickGuiObject(".class", "Html.A", ".text","History");
	}
	/**
	 * edit title
	 * @param boatValue
	 */
	public void editTitle(String boatValue){
		this.setBoatValue(boatValue);
		this.clickApply();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	/**
	 * Compare vehicle title status
	 * @param expStatus
	 * @return
	 * @author Lesley Wang
	 * @date Jun 21, 2012
	 */
	public boolean compareVehTitleStatus(String expStatus) {
		String actStatus = this.getStatus();
		return MiscFunctions.compareResult("Vehicle Title Status", expStatus, actStatus);
	}
	
	public void verifyVehicleTitleStatus(String expected) {
		if(!compareVehTitleStatus(expected)) {
			throw new ErrorOnPageException("Vehicle Title status is WRONG.");
		} else logger.info("Vehicle Title status is correct.");
	}
	
	/**
	 * Compare vehicle title duplicate number
	 */
	public boolean compareVehTitleNumOfDup(String expNum) {
		String actNum = this.getDuplicatesNumber();
		return MiscFunctions.compareResult("Vehicle Title Duplicate Numbers", expNum, actNum);
	}
	
	/**
	 * Compare vehicle title correct number
	 */
	public boolean compareVehTitleNumOfCorrect(String expNum) {
		String actNum = this.getCorrections();
		return MiscFunctions.compareResult("Vehicle Title Duplicate Numbers", expNum, actNum);
	}
}
