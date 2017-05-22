/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.datacollection.legacy.orms.InspectionInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
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
 * @author szhou
 * @Date Jun 1, 2012
 */
public class LicMgrInspectionVehicleDetailsPage extends LicMgrCommonTopMenuPage {
	private static LicMgrInspectionVehicleDetailsPage instance = null;

	public static LicMgrInspectionVehicleDetailsPage getInstance() {
		if (instance == null) {
			instance = new LicMgrInspectionVehicleDetailsPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Boat Location");
	}
	
	public boolean isCopyPhysicalAddressFromCustomerProfileCheckboxEnabled() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "inputwithrubylabel"), Property.toPropertyArray(".class", "Html.INPUT.checkbox")));
		if(objs.length < 1) {
			return false;
		}
		
		boolean enabled = objs[0].isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	private IHtmlObject[] getCopyPhysicalAddressFromCustomerProfileCheckboxObjects() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "inputwithrubylabel"), Property.toPropertyArray(".class", "Html.INPUT.checkbox")));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find any check box object.");
		}
		
		return objs;
	}
	
	public boolean isCopyPhysicalAddressFromCustomerProfileCheckboxSelected() {
		IHtmlObject objs[] = getCopyPhysicalAddressFromCustomerProfileCheckboxObjects();
		boolean isSelected = ((ICheckBox)objs[0]).isSelected();
		Browser.unregister(objs);
		return isSelected;
	}
	
	public void selectCopyPhysicalAddressFromCustomerProfileCheckbox() {
		IHtmlObject objs[] = getCopyPhysicalAddressFromCustomerProfileCheckboxObjects();
		((ICheckBox)objs[0]).select();
		Browser.unregister(objs);
	}
	
	public void unselectCopyPhysicalAddressFromCustomerProfileCheckbox() {
		IHtmlObject objs[] = getCopyPhysicalAddressFromCustomerProfileCheckboxObjects();
		((ICheckBox)objs[0]).deselect();
		Browser.unregister(objs);
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
		if(isCopyPhysicalAddressFromCustomerProfileCheckboxEnabled()) {
			if(inspection.isCopyPhysicalAddressFromCustomerProfile()) {
				selectCopyPhysicalAddressFromCustomerProfileCheckbox();
				ajax.waitLoading();
			} else if(isCopyPhysicalAddressFromCustomerProfileCheckboxSelected()) {
				unselectCopyPhysicalAddressFromCustomerProfileCheckbox();
				ajax.waitLoading();
				
				if(!StringUtil.isEmpty(inspection.getAddress())) {//TODO setTextField maybe have some issues
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
			
		} else {
			logger.warn("'Copy Physical Address From Customer Profile' is enabled', have to setup 'Boat Location' info maually.");
			setAddress(inspection.getAddress());
			setZip(inspection.getZip());
			if(!StringUtil.isEmpty(inspection.getSuppAddr())) {
				setSupplementalAddress(inspection.getSuppAddr());
			}
			setCity(inspection.getCity());
			selectCounty(inspection.getCounty());
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
}
