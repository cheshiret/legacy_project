/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.OwnerInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.MotorInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @Description: Register Vehicle for fulfill with vehicle details page
 * Includes 'Boat Info', 'Interstate Details', 'Boat Information' ...
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author jwang7
 * @Date  Mar 1, 2012
 */
public class LicMgrRegisterVehicleDetailsPage extends LicMgrCommonTopMenuPage {

	private String vehicle_prefix = "VehicleDetailView-";
	private String attri_prefix = "AttributeValuesWrapper-";
//	private String VEHICLE_BOAT="Boat";
//	private String VEHICLE_MOTOR="Motor";
	
	private static LicMgrRegisterVehicleDetailsPage instance=null;
	
	public static LicMgrRegisterVehicleDetailsPage getInstance(){
		if(instance==null){
			instance=new LicMgrRegisterVehicleDetailsPage();
		}
		return instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id", "vehicle_Info") 
		|| browser.checkHtmlObjectExists(".id",  new RegularExpression(vehicle_prefix+"\\d+\\.vehicleNum", false));
	}
	
	/**
	 * Set boat serial num
	 * @param num
	 */
	public void setBoatSerialNum(String num){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Hull ID/Serial.*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find Serial Num text field for boat.");
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.serialNum", false), num, objs[0]);
		Browser.unregister(objs);
	}
	
	/**
	 * Set motor serial num for multiple motor info in Boat Detail page
	 * @param num
	 * @param i
	 */
	public void setMotorSerialNum(String num, int i){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Serial.*", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find Serial Num text field for motor.");
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.serialNum", false), num, objs[i]);
		Browser.unregister(objs);
	}
	
	/**
	 * Set manufacture name for boat/motor info
	 * @param name
	 */
	public void setManufacturerName(String name){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.manufacturerName", false), name,0,IText.Event.FOCUS);
	}
	
	public void clickManufacturerName(){
		browser.clickGuiObject(".id", new RegularExpression(vehicle_prefix+"\\d+\\.manufacturerName", false));
	}
	
	/**
	 * Set manufacture name for multiple motor info in Boat detail page
	 * @param name
	 * @param i
	 */
	public void setManufacturerName(String name, int i){
		//HtmlObject[] objs = browser.getHtmlObject(".class", "TR", ".text", new RegularExpression("^Motor Details.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("ffui\\d+", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find TR for motor details info.");
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.manufacturerName", false), name, true, i, objs[0]);
		Browser.unregister(objs);
	}
	
	public void setBuiltYear(String builtYear){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.builtYear", false), builtYear);
	}
	
	/**
	 * Set model year for boat/motor
	 * @param year
	 */
	public void setModelYear(String year){
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.modelYear", false), year);
	}
	
	/**
	 * Set model year for multiple motor info in Boat detail page
	 * @param year
	 * @param i
	 */
	public void setModelYear(String year, int i){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("ffui\\d+", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find TR for motor details info.");
		browser.setTextField(".id", new RegularExpression(vehicle_prefix+"\\d+\\.modelYear", false), year, true, i, objs[0]);
		Browser.unregister(objs);
	}
	
	public void setYearBuilt(String yearBuilt) {
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.builtYear", false), yearBuilt);
	}
	
	/**
	 * Select is this boat coming from another State?
	 * @param value
	 */
	public void selectFromAnoterInterstate(String value){
		if(value.equalsIgnoreCase("Yes")){
			browser.selectRadioButton(".class", "Html.INPUT.radio", ".id", new RegularExpression("InterstateInfoView-\\d+\\.fromAnoterInterstate", false), 0);
		}else if(value.equalsIgnoreCase("No")){
			browser.selectRadioButton(".class", "Html.INPUT.radio", ".id", new RegularExpression("InterstateInfoView-\\d+\\.fromAnoterInterstate", false), 1);
		}	
	}
	
	/**
	 * Set inter state vehicle num when interstate is 'Yes'
	 * @param interstateVehicleSerial
	 */
	public void setInterstateVehicleNum(String interstateVehicleSerial){
		browser.setTextField(".id", new RegularExpression("InterstateInfoView-\\d+\\.vehNum", false), interstateVehicleSerial);
	}
	
	/**
	 * Select inter state when interstate is 'Yes'
	 * @param interState
	 */
	public void selectInterState(String interState){
		browser.selectDropdownList(".id", new RegularExpression("InterstateInfoView-\\d+\\.stateProvince", false), interState);
	}
	
	/**
	 * Set boat feet
	 * @param feet
	 */
	public void setFeet(String feet){
		browser.setTextField(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5011\\]_feet", false), feet);
	}
	
	/**
	 * Set boat inch
	 * @param inches
	 */
	public void setInches(String inches){
		browser.setTextField(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5011\\]_inches", false), inches);
	}
	
	/**
	 * Select boat hull material
	 * @param material
	 */
	public void selectHullMaterial(String material){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5010\\]", false), material);
	}
	
	/**
	 * Select boat use
	 * @param boatUse
	 */
	public void selectBoatUse(String boatUse){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5012\\]", false), boatUse);
	}
	
	/**
	 * Select boat propulsion
	 * @param propulsion
	 */
	public void selectPropulsion(String propulsion){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5014\\]", false), propulsion);
	}
	
	/**
	 * Select boat fuel type
	 * @param fuelType
	 */
	public void selectFuelType(String fuelType){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5015\\]", false), fuelType);
	}
	
	/**
	 * Select boat type
	 * @param boatType
	 */
	public void selectBoatType(String boatType){
		browser.selectDropdownList(".id", new RegularExpression(attri_prefix+"\\d+\\.attr\\[5013\\]", false), boatType);
	}
	
	/**
	 * Select boat salt water use
	 * @param saltwaterUse
	 */
	public void selectSaltWaterUse(String saltwaterUse){
		if(saltwaterUse.equalsIgnoreCase("Yes")){
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[5016]:BOOLEAN_YESNO", false), "Yes");
		}else if(saltwaterUse.equalsIgnoreCase("No")){
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[5016]:BOOLEAN_YESNO", false), "No");
		}
	}
	
	/**
	 * Set trailer serial for boat
	 * @param trailerSerial
	 */
	public void setTrailerSerial(String trailerSerial){
		browser.setTextField("", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[5017]", false), trailerSerial);
	}
	
	/**
	 * Set inventory num for boat
	 * @param inventory
	 */
	public void setInventoryNum(String inventory){
		browser.setTextField("", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr[5018]", false), inventory);
	}
	
	/**
	 * Set multiple coowner info
	 * @param fName
	 * @param index
	 */
	public void setCoOwnerFirstName(String fName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.firstName", false), fName, index);
	}

	/**
	 * Set multiple coowner info
	 * @param lName
	 * @param index
	 */
	public void setCoOwnerLastName(String lName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.lastName", false), lName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param mName
	 * @param index
	 */
	public void setCoOwnerMidName(String mName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.middleName", false), mName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param suffix
	 * @param index
	 */
	public void setCoOwnerSuffix(String suffix, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.suffix", false), suffix, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param dateOfBirth
	 * @param index
	 */
	public void setCoOwnerDateOfBirth(String dateOfBirth, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.dateOfBirth_ForDisplay", false), dateOfBirth, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param businessName
	 * @param index
	 */
	public void setCoOwnerBusinessName(String businessName, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.businessName", false), businessName, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param identificationType
	 * @param index
	 */
	public void setCoOwnerIdentificationType(String identificationType, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.identificationType", false), identificationType, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param identification
	 * @param index
	 */
	public void setCoOwnerIdentification(String identification, int index){
		browser.setTextField(".id", new RegularExpression("VehicleCoownerView-\\d+\\.identification", false), identification, index);
	}
	
	/**
	 * Set multiple coowner info
	 * @param index
	 * @return
	 */
	public boolean isCoOwnerIdentificationStateExisted(int index){
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.stateProvince", false));
		int size = objs.length;
		Browser.unregister(objs);
		if(size > index)
			return true;
		return false;
	}
	
	/**
	 * Set multiple coowner info
	 * @param state
	 * @param index
	 */
	public void selectCoOwnerIdentificationState(String state, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleCoownerView-\\d+\\.stateProvince", false), state, index);
	}
	
	/**
	 * select 'Customer's Existing Motors'
	 * @param motor
	 * @param index
	 */
	public void selectExistingMotors(String motor, int index){
		browser.selectDropdownList(".id", new RegularExpression("VehicleDetailView-\\d+\\.id", false), motor, index);
		
	}
	
	
	/**
	 * Set horse power for motor
	 * @param power
	 */
	public void setHorsePower(String power){
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.horsePower", false), power);
	}
	
	/**
	 * Set horse power for multiple motor info on boat detail page 
	 * @param power
	 * @param i
	 */
	public void setHorsePower(String power, int i){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".id", new RegularExpression("ffui\\d+", false));
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find TR for motor details info.");
		browser.setTextField(".id", new RegularExpression("VehicleDetailView-\\d+\\.horsePower", false), power, true, i, objs[0]);
		Browser.unregister(objs);
	}
	
	/**
	 * Select motor fuel for multiple motor info on boat detail page
	 * @param fuel
	 * @param i
	 */
	public void selectMotorFuel(String fuel, int i){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.LABEL", ".text", "Motor Fuel"/*new RegularExpression("^Motor Fuel.*", false)*/);
		if(objs.length<1)
			throw new ErrorOnPageException("Could not find TR for motor details info.");
		String idValue = objs[0].getProperty("for");
		browser.selectDropdownList(".id", idValue, fuel, i);
		Browser.unregister(objs);
	}
	
	
	
	/**
	 * Click Add button for adding co-owner
	 */
	public void clickAddButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add");
	}
	
	/**
	 * Click Add Additional Motor
	 */
	public void clickAdditionalMotor() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Additional Motor");
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	public void clickAddNewManufacturer(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("Manufacturer Name.*", false));
		if(objs.length<1)
		{
			throw new ErrorOnPageException("Could not find Html.TR for adding new manufacturer!");
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add New", false), true, 0,objs[0]);
		Browser.unregister(objs);
	}
	/**
	 * set vehicle VoOwnerInfo.
	 * @param ownerList
	 */
	public void setVehicleCoOwnerInfo(List<OwnerInfo> ownerList){
		for(int i =0;i<ownerList.size();i++){
			this.setVehicleCoOwner(ownerList.get(i),i);
			if(i<ownerList.size()-1){
				clickAddButton();
				ajax.waitLoading();
				waitLoading();
			}
		}
	}
	
	/**
	 * set vehicle coOwner info.
	 * @param coOwner
	 */
	public void setVehicleCoOwner(OwnerInfo coOwner,int index){
		setCoOwnerFirstName(coOwner.firstName, index);
		setCoOwnerLastName(coOwner.lastName,index);
		if("" != coOwner.midName)
			setCoOwnerMidName(coOwner.midName,index);
		if("" != coOwner.suffix)
			setCoOwnerSuffix(coOwner.suffix, index);
		if("" != coOwner.dateOfBirth)
			setCoOwnerDateOfBirth(coOwner.dateOfBirth, index);
		if("" != coOwner.businessName)
			setCoOwnerBusinessName(coOwner.businessName, index);
		if("" != coOwner.identifierType){
			setCoOwnerIdentificationType(coOwner.identifierType, index);
			ajax.waitLoading();
			this.waitLoading();
		}
		if("" != coOwner.identifierNum)
			setCoOwnerIdentification(coOwner.identifierNum, index);
		if(isCoOwnerIdentificationStateExisted(index) && "" != coOwner.identifierState)
			selectCoOwnerIdentificationState(coOwner.identifierState, index);
		
	}
	/**
	 * set vehicle motors info.
	 * @param motor
	 * @param index
	 */
	public void setVehicleMotor(MotorInfo motor,int index){
		if("" != motor.getSerialNum())
			setMotorSerialNum(motor.getSerialNum(), index);
		if("" != motor.getManufacturerName())
			setManufacturerName(motor.getManufacturerName(), index);
		if("" != motor.getModelYear())
			setModelYear(motor.getModelYear(), index);
		if("" != motor.getHorsePower())
			setHorsePower(motor.getHorsePower(), index);
		if("" != motor.getMotorFuel())
			selectMotorFuel(motor.getMotorFuel(), index);
	}
	/**
	 * set vehicle Motors Info.
	 * @param motors
	 */
	public void setVehicleMotorsInfo(List<MotorInfo> motors){
		for(int i =0;i<motors.size();i++){
			this.setVehicleMotor(motors.get(i), i);
			if(i<motors.size()-1){
				clickAdditionalMotor();
				ajax.waitLoading();
				waitLoading();
			}
		}
	}
	
	/**
	 * set existing vehicle Motors Info.
	 * @param motors
	 */
	public void setExistingMotorsInfo(List<String> motors){
		for(int i =0;i<motors.size();i++){
			this.selectExistingMotors(motors.get(i), i);
			if(i<motors.size()){
				clickAdditionalMotor();
				ajax.waitLoading();
				waitLoading();
			}
		}
	}
	
	/**
	 * Set up boat
	 * @param boat
	 */
	public void setupBoatDetails(BoatInfo boat){
		setBoatSerialNum(boat.hullIdSerialNum);
		setManufacturerName(boat.manufacturerName);
		// click this object twice to check the manufacturerName exist
		// James: clicking twice breaks Sanity test: LM_VehicleBoat. It seems the autocomplete will display without clicking now
		// if set ManufacturerName doesn't trigger autocomplete, it is a defect. No extra click is necessary
//		clickManufacturerName();
//		clickManufacturerName();
		
		boolean exists=browser.tentativeWaitExists(2,Property.toPropertyArray(".class", "Html.LI", ".className", new RegularExpression("^ac_", false), ".text", boat.manufacturerName));
		if(!exists) {
			clickAddNewManufacturer();
			ajax.waitLoading();
			LicMgrAddVehicleTypeManufacturerWidget addWidget = LicMgrAddVehicleTypeManufacturerWidget.getInstance();
			addWidget.waitLoading();
			if(StringUtil.isEmpty(boat.manufacturerPrintName)) {
				boat.manufacturerPrintName = boat.manufacturerName;
			}
			addWidget.setupManufacturerInfo(StringUtil.getRandomString(3, false), boat.manufacturerName, boat.manufacturerPrintName);
			addWidget.clickOK();
			ajax.waitLoading();
			browser.waitExists();
			if(addWidget.exists()){
				String errMsg = addWidget.getErrorMsg();
				if(errMsg.matches(".*already exists.*")){
					addWidget.clickCancel();
					ajax.waitLoading();
				}
			}
		}
		
		setBuiltYear(boat.builtYear);
		setModelYear(boat.modelYear);	
		setYearBuilt(boat.yearBuilt);
		if(!StringUtil.isEmpty(boat.interstate)) {
			selectFromAnoterInterstate(boat.interstate);
			ajax.waitLoading();
			this.waitLoading();
		}
		
		if(!StringUtil.isEmpty(boat.interstateVehicleSerial)) {
			setInterstateVehicleNum(boat.interstateVehicleSerial);
		}
		
		if(!StringUtil.isEmpty(boat.interstateState)) {
			selectInterState(boat.interstateState);
		}
		
		if(!StringUtil.isEmpty(boat.feet)) {
			setFeet(boat.feet);
		}
		
		if(!StringUtil.isEmpty(boat.inches)) {
			setInches(boat.inches);
		}
		
		selectHullMaterial(boat.hullMaterial);
		selectBoatUse(boat.boatUse);	
		selectPropulsion(boat.propulsion);
		selectFuelType(boat.fuelType);	
		selectBoatType(boat.typeOfBoat);
		
		if(!StringUtil.isEmpty(boat.saltwaterUse)) {
			selectSaltWaterUse(boat.saltwaterUse);
		}
		if(!StringUtil.isEmpty(boat.trailerSerial)) {
			setTrailerSerial(boat.trailerSerial);
		}
			
		//Add multiple co-owner info
		if(null != boat.coOwners && boat.coOwners.size()>0){
			this.setVehicleCoOwnerInfo(boat.coOwners);
		}
		//Add multiple motor info
		if(null != boat.motors && boat.motors.size()>0){
			this.setVehicleMotorsInfo(boat.motors);
		}
		//add existing motors to vehicle
		if(null != boat.existingMotors && boat.existingMotors.size()>0){
			this.setExistingMotorsInfo(boat.existingMotors);	
		}
	}
}
