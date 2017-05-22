/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.BoatInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.LicMgrConfirmCustomerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:License manager customer profile confirm page.
 * @Preconditions:
 * @SPEC:TC 036470 
 * @Task#:AUTO-1106
 * 
 * @author jwang8
 * @Date  Jul 3, 2012
 */
public class LicMgrVehicleCustomerConfirmPage extends LicMgrConfirmCustomerPage{
private static LicMgrVehicleCustomerConfirmPage _instance = null;
	
	protected LicMgrVehicleCustomerConfirmPage() {
		
	}
	
	public static LicMgrVehicleCustomerConfirmPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrVehicleCustomerConfirmPage();
		}
		return _instance;
	}
	/**
	 * get attribute value.
	 * @param attributeName
	 */
	public String getAttributeValueByName(String attributeName){
		String attributeValue = "";
		Property property[] = new Property[3];
		IHtmlObject divObjs[] = null;
		
		property[0] = new Property(".class", "Html.SPAN");
		property[1] = new Property(".className", "inputwithrubylabel");
		property[2] = new Property(".text", new RegularExpression("^" + attributeName, false));
		divObjs = browser.getHtmlObject(property);

		if(divObjs.length > 0){
			if(divObjs[0].getProperty(".text").length()>attributeName.length()){
				attributeValue = divObjs[0].getProperty(".text").split(attributeName)[1].trim();
			}else{
				attributeValue = "";
			}
			
		}
		return attributeValue;
	}
	/**
	 * get vehicle number.
	 */
	public String getBoatVehicleNum(){
		return this.getAttributeValueByName("Vehicle #");
	}
	/**
	 * get boat status.
	 */
	public String getBoatStatus(){
		return this.getAttributeValueById("Select", new RegularExpression("VehicleDetailView-\\d+\\.status", false));
	}
	/**
	 * get vehicle type.
	 */
	public String getVehicleType(){
		return this.getAttributeValueByName("Vehicle Type");
	}
	/**
	 * get creation date.
	 */
	public String getCreationDate(){
		return this.getAttributeValueByName("Creation Date");
	}
	/**
	 * get creation user.
	 */
	public String getCreationUser(){
		return this.getAttributeValueByName("Creation User");
	}
	/**
	 * get hull id.
	 * @return
	 */
	public String getHullId(){
		return this.getAttributeValueByName("Hull ID/Serial #");
	}
	/**
	 * get manufacturer name.
	 * @return
	 */
	public String getManufacturerNmae(){
		return this.getAttributeValueByName("Manufacturer Name");
	}
	/**
	 * get model year.
	 * @return
	 */
	public String getModelYear(){
		return this.getAttributeValueByName("Model Year");
	}
	/**
	 * get hull material.
	 * @return
	 */
	public String getHullMaterial(){
		return this.getAttributeValueById("Select", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5010\\]", false));
	}
	/**
	 * get boat user.
	 * @return
	 */
	public String getBoatUse(){
		return this.getAttributeValueById("Select", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5012\\]", false));
	}
	/**
	 * get propulsion.
	 * @return
	 */
	public String getPropulsion(){
		return this.getAttributeValueById("Select", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5014\\]", false));
	}
	/**
	 * get fuel type.
	 * @return
	 */
	public String getFuelType(){
		return this.getAttributeValueById("Select", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5015\\]", false));
	}
	/**
	 * get type of boat.
	 * @return
	 */
	public String getTypeOfBoat(){
		return this.getAttributeValueById("Select", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5013\\]", false));
	}
	/**
	 * Comapre Boat info
	 * @param vehicleInfo
	 * @return
	 */
	public boolean comareBoatInfo(BoatInfo vehicleInfo){
		boolean pass = true;
		pass &= MiscFunctions.compareResult("Mis numbber", vehicleInfo.registration.miNum, this.getBoatVehicleNum());
		pass &= MiscFunctions.compareResult("Vehicle type", vehicleInfo.type, this.getVehicleType());
		pass &= MiscFunctions.compareResult("Boat status", vehicleInfo.status, this.getBoatStatus());
		pass &= MiscFunctions.compareResult("Creatoion date", vehicleInfo.creationDate, this.getCreationDate());
		pass &= MiscFunctions.compareResult("Creatoion user", vehicleInfo.creationUser, this.getCreationUser());
		pass &= MiscFunctions.compareResult("Hull Id SerialNum", vehicleInfo.hullIdSerialNum, this.getHullId());
		pass &= MiscFunctions.compareResult("Manu facturer", vehicleInfo.manufacturerName, this.getManufacturerNmae());
		pass &= MiscFunctions.compareResult("Model year", vehicleInfo.modelYear, this.getModelYear());
		pass &= MiscFunctions.compareResult("Hull Material", vehicleInfo.hullMaterial, this.getHullMaterial());
		pass &= MiscFunctions.compareResult("Boat Use", vehicleInfo.boatUse, this.getBoatUse());
		pass &= MiscFunctions.compareResult("propulsion", vehicleInfo.propulsion, this.getPropulsion());
		pass &= MiscFunctions.compareResult("Fuel Type", vehicleInfo.fuelType, this.getFuelType());
		pass &= MiscFunctions.compareResult("Type Of Boat", vehicleInfo.typeOfBoat, this.getTypeOfBoat());
		return pass;
	}
	/**
	 * compare BoatAndCustomerInfo.
	 * @param vehicleInfo
	 * @param cus
	 */
	public boolean compareBoatAndCustomerInfo(BoatInfo vehicleInfo, Customer cust){
		boolean pass = true;
		pass &= this.compareCustomerInfo(cust);
		pass &= this.comareBoatInfo(vehicleInfo);
		return pass;
	}
	
	/**
	 * getDisableAttribute
	 * @param reg
	 * @return
	 */
	public boolean getIsEditAttribute(RegularExpression reg){
		IHtmlObject[] objs = browser.getHtmlObject(".id", reg);
		if(objs.length<1){
			throw new ErrorOnDataException("No specific element exist");
		}
		String text = objs[0].getProperty("isTextEdit");
		Browser.unregister(objs);
		return Boolean.parseBoolean(text);
	}
	/**
	 * get vehicle number disable attribute.
	 * @return
	 */
	public boolean getVehicleNumIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.vehicleNum",false);
		return this.getIsEditAttribute(reg);
	}
	/**
	 * get vehicle staus disable attribute.
	 * @return
	 */
	public boolean getVehicleStatusIsEditeAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.status",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle  type disable attribute.
	 * @return
	 */
	public boolean getVehicleTypeIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.vehicleType\\.name",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle registraion expiry disable attribute.
	 * @return
	 */
	public boolean getRegistrationExpiryIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.registExpiry",false);
		return this.getIsEditAttribute(reg);
	}
	/**
	 * get vehicle creation date disable attribute.
	 * @return
	 */
	public boolean getCreationDateIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.creationDate\\:DATE",false);
		return this.getIsEditAttribute(reg);
	}
	/**
	 * get vehicle creation user disable attribute.
	 * @return
	 */
	public boolean getCreationUserIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.createUser\\.name",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle creation user disable attribute.
	 * @return
	 */
	public boolean getHullIdIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.createUser\\.name",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle manufacturer disable attribute.
	 * @return
	 */
	public boolean getManufacturerNameIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.manufacturer\\.name",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle manufacturer print name user disable attribute.
	 * @return
	 */
	public boolean getManuPrintNameIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.manufacturer\\.printName",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle manufacturer print name user disable attribute.
	 * @return
	 */
	public boolean getModelYearIsEditAttribute(){
		RegularExpression reg = new RegularExpression("VehicleDetailView-\\d+\\.modelYear",false);
		return this.getIsEditAttribute(reg);
	}
	/**
	 * get vehicle Material name user disable attribute.
	 * @return
	 */
	public boolean getHullMaterialIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5010\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getBoatUseIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5012\\]",false);
		return this.getIsEditAttribute(reg);
	}
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getPropulsionIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5014\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getFuelTypeIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5015\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getTypeOfBoatIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5013\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getSaltwaterUseIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5016\\]\\:BOOLEAN_YESNO",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getTrailerSerialNumIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5017\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	/**
	 * get vehicle boat use  user disable attribute.
	 * @return
	 */
	public boolean getInventoryNumIsEditAttribute(){
		RegularExpression reg = new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[5018\\]",false);
		return this.getIsEditAttribute(reg);
	}
	
	public boolean checkBoatInfoDisableAttribute(){
		boolean isDisable = true;
		if(this.getVehicleNumIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Number should disable");
		}
		if(this.getVehicleStatusIsEditeAttribute()){
			isDisable &= false;
			logger.error("Vehicle status should disable");
		}
		if(this.getVehicleTypeIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle type should disable");
		}
		if(this.getRegistrationExpiryIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Registration Expiry should disable");
		}
		if(this.getCreationDateIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Creation Date should disable");
		}
		if(this.getCreationUserIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Creation user should disable");
		}
		if(this.getHullIdIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Hull Id should disable");
		}
		if(this.getManufacturerNameIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Manufacturer Name should disable");
		}
		if(this.getManuPrintNameIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Manu Print Name should disable");
		}
		if(this.getModelYearIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Model Year should disable");
		}
		if(this.getHullMaterialIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Hull Material should disable");
		}
		if(this.getBoatUseIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Boat Use should disable");
		}
		if(this.getPropulsionIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Propulsion should disable");
		}
		if(this.getFuelTypeIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Fuel Type should disable");
		}
		if(this.getTypeOfBoatIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Type Of Boat should disable");
		}
		if(this.getSaltwaterUseIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Saltwater Use should disable");
		}
		if(this.getTrailerSerialNumIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Trailer Serial Number Use should disable");
		}
		if(this.getInventoryNumIsEditAttribute()){
			isDisable &= false;
			logger.error("Vehicle Inventory Number Use should disable");
		}
		return isDisable;
	}
	
	
	
	
	
}
