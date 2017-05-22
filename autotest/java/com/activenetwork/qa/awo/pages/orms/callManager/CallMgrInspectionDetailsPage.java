/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.callManager;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.InspectionInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Jane Wang
 * @Date  Jun 6, 2012
 */
public class CallMgrInspectionDetailsPage extends CallMgrCommonTopMenuPage {
	static private CallMgrInspectionDetailsPage _instance = null;
	
	static public CallMgrInspectionDetailsPage getInstance(){
		if (null == _instance) {
			_instance = new CallMgrInspectionDetailsPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "AddEditVehInspectionContainer");
	}
	
	public void setAddress(String addr){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.address", false), addr);
	}
	
	public void setZip(String zip){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.zipCode", false), zip);
	}
	
	public void setSupplementalAddress(String addr){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.supplemental", false), addr);
	}
	
	public void setCity(String city){
		browser.setTextField(".id", new RegularExpression("AddressView-\\d+\\.city", false), city);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.country", false), country);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.state", false), state);
	}
	
	public void selectCounty(String county){
		browser.selectDropdownList(".id", new RegularExpression("AddressView-\\d+\\.county", false), county);
	}
	
	public void setDayPhone(String phone){
		browser.setTextField(".id", new RegularExpression("VehicleInspectionInstanceView-\\d+\\.vehicleInspection.contactDayPhone", false), phone);
	}
	
	public void setEveningPhone(String phone){
		browser.setTextField(".id", new RegularExpression("VehicleInspectionInstanceView-\\d+\\.vehicleInspection.contactEveningPhone", false), phone);
	}
	
	public void selectReason(String reason){
		browser.selectDropdownList(".id", new RegularExpression("VehicleInspectionInstanceView-\\d+\\.vehicleInspection.requestReason", false), reason);
	}
	
	public void setInspectionDetail(String detail){
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr.*", false), detail);
	}
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickValidate(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");
	}
	
	public void setupInspectionDetailInfo(InspectionInfo inspection){
		if(inspection.isCopyPhysicalAddressFromCustomerProfile() && !this.checkCopyPhysicalAddressIsDisabled()){
			this.selectCopyPhysicalAddressCheckBox();
			ajax.waitLoading();
			this.waitLoading();
		} else {
			if(!StringUtil.isEmpty(inspection.getAddress())) {
				this.setAddress(inspection.getAddress());
			}
			if(!StringUtil.isEmpty(inspection.getZip())) {
				this.setZip(inspection.getZip());
			}
			if(!StringUtil.isEmpty(inspection.getCountry())) {
				this.selectCountry(inspection.getCountry());
			}
			
			if(!StringUtil.isEmpty(inspection.getSuppAddr())) {
				this.setSupplementalAddress(inspection.getSuppAddr());
			}
			if(!StringUtil.isEmpty(inspection.getCity())) {
				this.setCity(inspection.getCity());
			}
			if(!StringUtil.isEmpty(inspection.getState())) {
				this.selectState(inspection.getState());
			}
			if(!StringUtil.isEmpty(inspection.getCounty())) {
				this.selectCounty(inspection.getCounty());
				ajax.waitLoading();
			}
			this.clickValidate();
			ajax.waitLoading();
		}
		
		if(!StringUtil.isEmpty(inspection.getDayPhone())) {
			this.setDayPhone(inspection.getDayPhone());
		}
		if(!StringUtil.isEmpty(inspection.getEveningPhone())) {
			this.setEveningPhone(inspection.getEveningPhone());
		}
		if(!StringUtil.isEmpty(inspection.getInspectionReason())) {
			this.selectReason(inspection.getInspectionReason());
		}
		if(!StringUtil.isEmpty(inspection.getInspectionDetail())) {
			this.setInspectionDetail(inspection.getInspectionDetail());
		}
	}
	
	private String getSpanText(String name){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^" + name + ".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get DIV object, name = " +name );
		}
		String text = objs[0].text().replaceAll(name, "").trim();
		Browser.unregister(objs);
		return text;
	}
	
	public String getCustomerNumber(){
		return this.getSpanText("MDWFP #");
	}
	
	public void selectCopyPhysicalAddressCheckBox(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text",new RegularExpression("Copy Physical Address from Customer Profile",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get SPAN object, Copy Physical Address from Customer Profile");
		}
		
		browser.selectCheckBox(Property.toPropertyArray(".class", "Html.INPUT.checkbox"), 0, objs[0]);
	}
	
	public boolean checkCopyPhysicalAddressIsDisabled(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text",new RegularExpression("Copy Physical Address from Customer Profile",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get DIV object, Copy Physical Address from Customer Profile");
		}
		IHtmlObject[] checkBoxObjs = browser.getHtmlObject(".class", "Html.INPUT.checkbox", objs[0]);
		boolean value = Boolean.parseBoolean(checkBoxObjs[0].getProperty(".disabled"));
		Browser.unregister(objs);
		Browser.unregister(checkBoxObjs);
		
		return value;
	}
	
	public String getAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.address",false));
	}
	
	public String getZip(){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.zipCode",false));
	}
	
	public String getCountry(){
		return browser.getObjectText(".id", new RegularExpression("AddressView-\\d+\\.country",false));
	}
	
	public String getStatus(){
		return this.getSpanText("Status");
	}
	
	public String getSuppAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.supplemental",false));	
	}
	
	public String getCity(){
		return browser.getTextFieldValue(".id", new RegularExpression("AddressView-\\d+\\.city",false));
	}
	
	public String getState(){
		return browser.getObjectText(".id", new RegularExpression("AddressView-\\d+\\.state",false));
	}
	
	public String getCounty(){
		return browser.getDropdownListValue(".id", new RegularExpression("AddressView-\\d+\\.county",false));
	}
	
	public Address getLocationInfo(){
		Address address = new Address();
		address.address = this.getAddress();
		address.zip = this.getZip();
		address.country = this.getCountry();
		address.status = this.getStatus();
		address.supplementalAddr = this.getSuppAddress();
		address.city = this.getCity();
		address.state = this.getState();
		address.county = this.getCounty();
		return address;
	}
	
	public boolean compareLocationInfo(Address expAddress){
		Address actLocationInfo = this.getLocationInfo();

		boolean result = true;
		result &= MiscFunctions.compareResult("Address", expAddress.address, actLocationInfo.address);
		result &= MiscFunctions.compareResult("Zip", expAddress.zip, actLocationInfo.zip);
		result &= MiscFunctions.compareResult("Country", expAddress.country, actLocationInfo.country);
		result &= MiscFunctions.compareResult("Supplemental Address", expAddress.supplementalAddr, actLocationInfo.supplementalAddr);
		result &= MiscFunctions.compareResult("City", expAddress.city, actLocationInfo.city);
		result &= MiscFunctions.compareResult("State", expAddress.state, actLocationInfo.state);
		result &= MiscFunctions.compareResult("County", expAddress.county, actLocationInfo.county);
		
		return result;
	}
	
}
