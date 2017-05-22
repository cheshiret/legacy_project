package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle.manage;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Vehicle;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 *
 * @author ssong
 * @date Dec 5, 2011
 */
public class LicMgrVehicleDetailPage extends LicMgrCommonTopMenuPage{

	private static LicMgrVehicleDetailPage _instance = null;
	protected LicMgrVehicleDetailPage(){

	}

	public static LicMgrVehicleDetailPage getInstance(){
		if(_instance == null){
			_instance = new LicMgrVehicleDetailPage();
		}
		return _instance;
	}

	@Override
	public boolean exists() {
		return ((browser.checkHtmlObjectExists(".class","Html.DIV",".id","content_VehicleMgrTabs"))
		&&(browser.checkHtmlObjectExists(".id",new RegularExpression("VehicleDetailView-\\d+\\.status",false))));
	}

	public String getVehicleNum(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.vehicleNum",false));
		return text.substring(text.indexOf("Vehicle #")+"Vehicle #".length()).trim();
	}

	public String getStatus(){
		return browser.getDropdownListValue(".id",new RegularExpression("VehicleDetailView-\\d+\\.status",false), 0);
	}

	public String getVehicleType(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.vehicleType\\.name",false));
		return text.substring(text.indexOf("Vehicle Type")+"Vehicle Type".length()).trim();
	}

	public String getRegistrationExpiry(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.registExpiry",false));
		return text.substring(text.indexOf("Registration Expiry")+"Registration Expiry".length()).trim();
	}

	public String getTitleNum(){
		String text = browser.getObjectText(".class","Html.DIV",".text",new RegularExpression("^Title #",false));
		return text.substring(text.indexOf("Title #")+"Title #".length()).trim();
	}

	public String getCreateDate(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.creationDate:DATE",false));
		return text.substring(text.indexOf("Creation Date")+"Creation Date".length()).trim();
	}

	public String getCreateUser(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.createUser\\.name",false));
		return text.substring(text.indexOf("Creation User")+"Creation User".length()).trim();
	}

	public String getHullIdSerialNum(){
		String text = browser.getTextFieldValue(".id",new RegularExpression("VehicleDetailView-\\d+\\.serialNum",false));
//		return text.substring(text.indexOf("Hull ID/Serial #")+"Hull ID/Serial #".length()).trim();
		return text.trim();
	}

	public String getManufacturerName(){
		String text = browser.getTextFieldValue(".id",new RegularExpression("VehicleDetailView-\\d+\\.manufacturerName",false));
//		return text.substring(text.indexOf("Manufacturer Name")+"Manufacturer Name".length()).trim();
		return text.trim();
	}

	public String getManufacturerPrintName(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.manufacturer\\.printName",false));
		return text.substring(text.indexOf("Manufacturer Print Name")+"Manufacturer Print Name".length()).trim();
	}

	public String getModelYear(){
		String text = browser.getTextFieldValue(".id",new RegularExpression("VehicleDetailView-\\d+\\.modelYear",false));
//		return text.substring(text.indexOf("Model Year")+"Model Year".length()).trim();
		return text.trim();
	}
	
	public void setModelYear(String modelYear){
		browser.setTextField(".id",new RegularExpression("VehicleDetailView-\\d+\\.modelYear", false), modelYear);
	}
	

	public String getLength(){
		String ft = browser.getTextFieldValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_feet",false));
		String inch = browser.getTextFieldValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_inches",false));
		return ft+"."+inch;
	}

	public String getHullMaterial(){
		return browser.getDropdownListValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5010\\]",false), 0);
	}

	public String getBoatUse(){
		return browser.getDropdownListValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5012\\]",false), 0);
	}

	public String getPropulsion(){
		return browser.getDropdownListValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5014\\]",false), 0);
	}

	public String getFuelType(){
		return browser.getDropdownListValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5015\\]",false), 0);
	}

	public String getTypeOfBoat(){
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5013\\]",false),0);
	}

	public String getSaltwaterUse(){
		return browser.getDropdownListValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5016\\]:BOOLEAN_YESNO",false), 0);
	}

	public String getTrailerSerialNum(){
		String text = browser.getTextFieldValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5017\\]",false));
//		return text.substring(text.indexOf("Trailer Serial #")+"Trailer Serial #".length()).trim();
		return text.trim();
	}

	public String getInventoryNum(){
		String text = browser.getTextFieldValue(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5018\\]",false));
//		return text.substring(text.indexOf("Inventory #")+"Inventory #".length()).trim();
		return text.trim();
	}

	public String getCustomerMDWFPNum(){
		return browser.getObjectText(".class","Html.A",".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.customerNumber",false));
	}

	public String getCustomerStatus(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.status:CB_TO_NAME",false));
		return text.substring(text.indexOf("Status")+"Status".length()).trim();
	}

	public String getCustomerClass(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.customerClass\\.name",false));
		return text.substring(text.indexOf("Customer Class")+"Customer Class".length()).trim();
	}

	public String getCustomerFirstName(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.primaryContact\\.firstName",false));
		return text.substring(text.indexOf("First Name")+"First Name".length()).trim();
	}

	public String getCustomerMidName(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.primaryContact\\.middleName",false));
		return text.substring(text.indexOf("Middle Name")+"Middle Name".length()).trim();
	}

	public String getCustomerLastName(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.primaryContact\\.lastName",false));
		return text.substring(text.indexOf("Last Name")+"Last Name".length()).trim();
	}

	public String getCustomerSuffix(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.primaryContact\\.suffix",false));
		return text.substring(text.indexOf("Suffix")+"Suffix".length()).trim();
	}

	public String getCustomerDateOfBirth(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.dateOfBirth",false));
		return text.substring(text.indexOf("Date of Birth")+"Date of Birth".length()).trim();
	}

	public String gethorsePower(){
		String text = browser.getObjectText(".id",new RegularExpression("VehicleDetailView-\\d+\\.horsePower",false));
		return text.substring(text.indexOf("Horsepower")+"Horsepower".length()).trim();
	}
	
	public String getCustomerNum(){
		return browser.getObjectText(".class", "Html.A", ".id", new RegularExpression("VehicleDetailView-\\d+\\.customerProfile\\.customerNumber", false));
	}

	public void clickCoOwnersTab(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Co-Owners(\\([0-9]+\\))?", false));
	}

	public void clickPreOwnersTab(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Previous Owners(\\([0-9]+\\))?", false));
	}

	public void clickTitlesTab(){
		browser.clickGuiObject(".id", new RegularExpression("VehicleDetailsTabs_\\d+", false), ".text",
				new RegularExpression("Titles.*", false));
	}
	
	public void clickRegistrationsTab(){
		Property[] property = new Property[3];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", new RegularExpression("Registrations(\\([0-9]+\\))?", false));
		property[2] = new Property(".id", new RegularExpression("VehicleDetailsTabs_\\d+", false));
		browser.clickGuiObject(property);
	}
	
	public void clickOrderTab(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Orders(\\([0-9]+\\))?", false),1);
		
	}

	public void clickRegistration(){
		browser.clickGuiObject(".class", "Html.A",".text","Registration");
	}
	
	public void clickTitleButton(){
		browser.clickGuiObject(".class", "Html.A",".text","Title", true);
	}
	
	public void clickTransferButton(){
		browser.clickGuiObject(".class", "Html.A",".text","Transfer");
	}

	public void clickReverseTransfer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reverse Transfer", true);
	}
	
	public void clickApply(){
		browser.clickGuiObject(".class", "Html.A",".text","Apply");
	}
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A",".text","OK");
	}

	
	/**select moved to state*/
	public void setMovedToState(String state){
		RegularExpression regx=new RegularExpression("VehicleDetailView-\\d+\\.movedToStateProvince",false);
		browser.selectDropdownList(".id", regx, state);
	}
	
	public String getMovedToState(){
		RegularExpression regx=new RegularExpression("VehicleDetailView-\\d+\\.movedToStateProvince",false);
		return browser.getDropdownListValue(".id", regx, 0);
	}
	
	/**select status*/
	public void setStatus(String status){
		RegularExpression regx=new RegularExpression("VehicleDetailView-\\d+\\.status",false);
		browser.selectDropdownList(".id", regx, status);
	}
	
	public void setLengthFT(String len)
	{
		RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_feet",false);
		 browser.setTextField(".id", regx, len); 
	}
	public void setLengthIN(String len)
	{
		RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_inches",false);
		 browser.setTextField(".id", regx, len); 
	}
	
	public String getLengthFT(){
		RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_feet",false);
		return browser.getTextFieldValue(".id",regx);
	}
	
	public String getLengthIN(){
		RegularExpression regx=new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5011\\]_inches",false);
		return browser.getTextFieldValue(".id",regx);
	}
	
	public void setHullMaterial(String material){
		RegularExpression regx = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5010\\]",false);
		browser.selectDropdownList(".id", regx, material);
	}
	
	public void setBoatUse(String use){
		RegularExpression regx = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5012\\]",false);
		browser.selectDropdownList(".id", regx, use);
		
	}
	
	public void setPropulsion(String value){
		RegularExpression regx = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5014\\]",false);
		browser.selectDropdownList(".id", regx, value);
	}

	public void setFuelType(String value){
		RegularExpression regx =new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5015\\]",false);
		browser.selectDropdownList(".id", regx, value);
	}

	public void setTypeOfBoat(String value){
		RegularExpression regx =new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5013\\]",false);
		browser.selectDropdownList(".id", regx, value);
	}
	
	public void fillVehicleInfo(Vehicle veh){
		if(!StringUtil.isEmpty(veh.status))
		{
			this.setStatus(veh.status);
			ajax.waitLoading();
		}
		
		if(veh instanceof BoatInfo) {
			if(!StringUtil.isEmpty(((BoatInfo)veh).movedToState))
			{
				this.setMovedToState(((BoatInfo)veh).movedToState);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).lenFT))
			{
				this.setLengthFT(((BoatInfo)veh).lenFT);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).lenIN))
			{
				this.setLengthIN(((BoatInfo)veh).lenIN);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).hullMaterial))
			{
				this.setHullMaterial(((BoatInfo)veh).hullMaterial);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).boatUse))
			{
				this.setBoatUse(((BoatInfo)veh).boatUse);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).propulsion))
			{
				this.setPropulsion(((BoatInfo)veh).propulsion);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)veh).fuelType))
			{
				this.setFuelType(((BoatInfo)veh).fuelType);
			}
			if(!StringUtil.isEmpty(((BoatInfo)veh).typeOfBoat))
			{
				this.setTypeOfBoat(((BoatInfo)veh).typeOfBoat);
			}
		}//TODO
	}
	
	public BoatInfo getVehicleInfo(){
		BoatInfo veh = new BoatInfo();
		veh.status = this.getStatus();
		veh.movedToState = this.getMovedToState();
		veh.lenFT = this.getLengthFT();
		veh.lenIN = this.getLengthIN();
		veh.hullMaterial = this.getHullMaterial();
		veh.boatUse = this.getBoatUse();
		veh.propulsion = this.getPropulsion();
		veh.fuelType = this.getFuelType();
		veh.typeOfBoat = this.getTypeOfBoat();
		return veh;
	}
	
	public void clickTab(String tabName){
		browser.clickGuiObject(".id", new RegularExpression("VehicleDetailsTabs_\\d*", false),
				".text",new RegularExpression(tabName+".*", false));
	}
	
	public boolean verifyVehicleCommonInfo(Vehicle vehicle){
		boolean pass = true;
		
		String miNum = getVehicleNum();
		String status = getStatus();
		String type = getVehicleType();
		String creationUser = getCreateUser();
		String creationDate = getCreateDate();
		//below parameters only apply for Boat
		String hullIdSerialNum = "";
		String manufacturerName = "";
		String modelYear = "";
		String feet = "";
		String inches = "";
		String hullMaterial = "";
		String boatUse = "";
		String propulsion = "";
		String fuelType = "";
		String typeOfBoat = "";
		String saltwaterUse = "";
		String trailerSerial = "";
		String invNum = "";
		
		if(vehicle.type.equalsIgnoreCase("Boat")){
			hullIdSerialNum = getHullIdSerialNum();
			manufacturerName = getManufacturerName();
			modelYear = getModelYear();
			feet = getLengthFT();
			inches = getLengthIN();
			hullMaterial = getHullMaterial();
			boatUse = getBoatUse();
			propulsion = getPropulsion();
			fuelType = getFuelType();
			typeOfBoat = getTypeOfBoat();
			saltwaterUse = getSaltwaterUse();
			trailerSerial = getTrailerSerialNum();
			invNum = getInventoryNum();
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).status) && !((BoatInfo)vehicle).hullIdSerialNum.equalsIgnoreCase(status)){
				pass &= false;
				logger.error("Vehicle status was not matched. Expected value:"+vehicle.status+", actual value:"+status);
			}
			
			if(!StringUtil.isEmpty(vehicle.type) && !vehicle.type.equalsIgnoreCase(type)){
				pass &= false;
				logger.error("Vehicle type was not matched. Expected value:"+vehicle.type+", actual value:"+type);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).hullIdSerialNum) && !((BoatInfo)vehicle).hullIdSerialNum.equalsIgnoreCase(hullIdSerialNum)){
				pass &= false;
				logger.error("Vehicle hullIdSerialNum was not matched. Expected value:"+((BoatInfo)vehicle).hullIdSerialNum+", actual value:"+hullIdSerialNum);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).manufacturerName) && !((BoatInfo)vehicle).manufacturerName.equalsIgnoreCase(manufacturerName)){
				pass &= false;
				logger.error("Vehicle manufacturerName was not matched. Expected value:"+((BoatInfo)vehicle).manufacturerName+", actual value:"+manufacturerName);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).modelYear) && !((BoatInfo)vehicle).modelYear.equalsIgnoreCase(modelYear)){
				pass &= false;
				logger.error("Vehicle modelYear was not matched. Expected value:"+((BoatInfo)vehicle).modelYear+", actual value:"+modelYear);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).feet) && !((BoatInfo)vehicle).feet.equalsIgnoreCase(feet)){
				pass &= false;
				logger.error("Vehicle feet was not matched. Expected value:"+((BoatInfo)vehicle).feet+", actual value:"+feet);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).inches) && !((BoatInfo)vehicle).inches.equalsIgnoreCase(inches)){
				pass &= false;
				logger.error("Vehicle inches was not matched. Expected value:"+((BoatInfo)vehicle).inches+", actual value:"+inches);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).hullMaterial) && !((BoatInfo)vehicle).hullMaterial.equalsIgnoreCase(hullMaterial)){
				pass &= false;
				logger.error("Vehicle hullMaterial was not matched. Expected value:"+((BoatInfo)vehicle).hullMaterial+", actual value:"+hullMaterial);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).boatUse) && !((BoatInfo)vehicle).boatUse.equalsIgnoreCase(boatUse)){
				pass &= false;
				logger.error("Vehicle boatUse was not matched. Expected value:"+((BoatInfo)vehicle).boatUse+", actual value:"+boatUse);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).propulsion) && !((BoatInfo)vehicle).propulsion.equalsIgnoreCase(propulsion)){
				pass &= false;
				logger.error("Vehicle propulsion was not matched. Expected value:"+((BoatInfo)vehicle).propulsion+", actual value:"+propulsion);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).propulsion) && !((BoatInfo)vehicle).propulsion.equalsIgnoreCase(propulsion)){
				pass &= false;
				logger.error("Vehicle propulsion was not matched. Expected value:"+((BoatInfo)vehicle).propulsion+", actual value:"+propulsion);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).fuelType) && !((BoatInfo)vehicle).fuelType.equalsIgnoreCase(fuelType)){
				pass &= false;
				logger.error("Vehicle fuelType was not matched. Expected value:"+((BoatInfo)vehicle).fuelType+", actual value:"+fuelType);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).typeOfBoat) && !((BoatInfo)vehicle).typeOfBoat.equalsIgnoreCase(typeOfBoat)){
				pass &= false;
				logger.error("Vehicle typeOfBoat was not matched. Expected value:"+((BoatInfo)vehicle).typeOfBoat+", actual value:"+typeOfBoat);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).saltwaterUse) && !((BoatInfo)vehicle).saltwaterUse.equalsIgnoreCase(saltwaterUse)){
				pass &= false;
				logger.error("Vehicle saltwaterUse was not matched. Expected value:"+((BoatInfo)vehicle).saltwaterUse+", actual value:"+saltwaterUse);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).trailerSerial) && !((BoatInfo)vehicle).trailerSerial.equalsIgnoreCase(trailerSerial)){
				pass &= false;
				logger.error("Vehicle trailerSerial was not matched. Expected value:"+((BoatInfo)vehicle).trailerSerial+", actual value:"+trailerSerial);
			}
			
			if(!StringUtil.isEmpty(((BoatInfo)vehicle).inventory) && !((BoatInfo)vehicle).inventory.equalsIgnoreCase(invNum)){
				pass &= false;
				logger.error("Vehicle inventory was not matched. Expected value:"+((BoatInfo)vehicle).inventory+", actual value:"+invNum);
			}
			
		}
		
		if(!StringUtil.isEmpty(vehicle.registration.miNum) && !vehicle.registration.miNum.equalsIgnoreCase(miNum)){
			pass &= false;
			logger.error("Vehicle miNum was not matched. Expected value:"+vehicle.registration.miNum+", actual value:"+miNum);
		}
		
	
		if(!StringUtil.isEmpty(vehicle.creationUser) && !vehicle.creationUser.equalsIgnoreCase(creationUser)){
			pass &= false;
			logger.error("Vehicle creationUser was not matched. Expected value:"+vehicle.creationUser+", actual value:"+creationUser);
		}
		
		if(!StringUtil.isEmpty(vehicle.creationDate) && DateFunctions.compareDates(vehicle.creationDate, creationDate)!=0){
			pass &= false;
			logger.error("Vehicle creationDate was not matched. Expected value:"+vehicle.creationDate+", actual value:"+creationDate);
		}
		if(pass){
			logger.info("Vehicle common detail info has been verified successfully.");
		}
		return pass;
	}
	
	public boolean verifyVehicleCustomerInfo(Customer cust){
		boolean pass = true;
		
		String custNum = getCustomerNum();
		String custClass = getCustomerClass();
		String custFName = getCustomerFirstName();
		String custLName = getCustomerLastName();
		String custDateOfBirth = getCustomerDateOfBirth();
		
		if(!StringUtil.isEmpty(cust.custNum) && !cust.custNum.equalsIgnoreCase(custNum)){
			pass &= false;
			logger.error("Customer number was not matched. Expected value:"+cust.custNum+", actual value:"+custNum);
		}

		if(!StringUtil.isEmpty(cust.customerClass) && !cust.customerClass.equalsIgnoreCase(custClass)){
			pass &= false;
			logger.error("Customer class was not matched. Expected value:"+cust.customerClass+", actual value:"+custClass);
		}
		
		if(!StringUtil.isEmpty(cust.fName) && !cust.fName.equalsIgnoreCase(custFName)){
			pass &= false;
			logger.error("Customer first Name was not matched. Expected value:"+cust.fName+", actual value:"+custFName);
		}
		
		if(!StringUtil.isEmpty(cust.lName) && !cust.lName.equalsIgnoreCase(custLName)){
			pass &= false;
			logger.error("Customer last name was not matched. Expected value:"+cust.lName+", actual value:"+custLName);
		}
		
		if(!StringUtil.isEmpty(cust.dateOfBirth) && DateFunctions.compareDates(cust.dateOfBirth, custDateOfBirth)!=0){
			pass &= false;
			logger.error("Customer date of birth was not matched. Expected value:"+cust.custNum+", actual value:"+custDateOfBirth);
		}
		if(pass){
			logger.info("Customer detail info has been verified successfully.");
		}
		return pass;
	}
	
	public void clickChangeHistory(){
		browser.clickGuiObject(".class", "Html.A",".text","Change History");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	public void clickDocumentUploadsTab(){
		browser.clickGuiObject(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
	}
	
	public boolean isDocumentUploadsTableExisting(){
		return browser.checkHtmlObjectDisplayed(Property.atList(Property.addToPropertyArray(this.a(),".text",new RegularExpression("Document Uploads.*", false))));
	}
}
