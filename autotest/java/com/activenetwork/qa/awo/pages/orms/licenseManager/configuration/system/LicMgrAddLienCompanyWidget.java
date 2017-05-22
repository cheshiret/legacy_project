package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddLienCompanyWidget extends DialogWidget {
	private static LicMgrAddLienCompanyWidget _instance = null;
	
	protected LicMgrAddLienCompanyWidget(){}
	
	public static LicMgrAddLienCompanyWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddLienCompanyWidget();
		}
		return _instance;
	}
	
	public boolean exists(){
		return super.exists() && browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", "Add Lien Company");
	}
	
	private void selectRadioButton(String value){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text",new RegularExpression("^(| )" + value, false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found radio button with name = " + value);
		}
		
		browser.selectRadioButton(Property.toPropertyArray(".class", "Html.INPUT.radio"), true, 0, objs[0]);
		Browser.unregister(objs);		
	}
	
	public void selectSelectLienCompanyNameRadioButton(){
		this.selectRadioButton("Select Lien Company Name");
	}
	
	public void selectNewLienCompanyNameRadioButton(){
		this.selectRadioButton("New Lien Company Name");		
	}
	
	public void selectLienCompanyName(String lienCompanyName){
		browser.selectDropdownList(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.lienCompanyName",false), lienCompanyName);
	}
	
	public void setLienCompanyName(String lienCompanyName){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.lienCompanyName",false), lienCompanyName);
	}
	
	public void setAddress(String address){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.address",false), address);
	}
	
	public void setCityTown(String cityTown){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.city",false), cityTown);
	}
	
	public void selectState(String state){
		browser.selectDropdownList(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.state",false), state);
	}
	
	public void setZip(String zip){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.zip",false), zip);
	}
	
	public void selectCountry(String country){
		browser.selectDropdownList(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.country",false), country);
	}
	
	public void setContactFirstName(String firstName){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.contactFirstName",false), firstName);
	}
	
	public void setContactLastName(String lastName){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.contactLastName",false), lastName);
	}
	
	public void setContactPhone(String phone){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.contactPhone",false), phone);
	}
	
	public void setContactEmail(String email){
		browser.setTextField(".id", 
				new RegularExpression("LienCompanyView-\\d+\\.contactEmail",false), email);
	}
	
	public String getMessage(){
		return browser.getObjectText(".id", "NOTSET");
	}
	
	public void setLienCompanyInfo(LienCompanyDetailsInfo lienCompany, boolean isNewLienCompanyName){
//		if(lienCompany.selectLienCompanyName){
//			this.selectSelectLienCompanyNameRadioButton();
//			ajax.waitLoading();
//			this.selectLienCompanyName(lienCompany.lienCompanyName);
//			
//		}else{
//			this.selectNewLienCompanyNameRadioButton();
//			ajax.waitLoading();
//			this.setLienCompanyName(lienCompany.lienCompanyName);		
//		}
		
		if(isNewLienCompanyName){
			this.selectNewLienCompanyNameRadioButton();
			ajax.waitLoading();
			this.setLienCompanyName(lienCompany.lienCompanyName);
		}else{
			this.selectSelectLienCompanyNameRadioButton();
			ajax.waitLoading();
			this.selectLienCompanyName(lienCompany.lienCompanyName);
		}
		
		this.setAddress(lienCompany.address);		
		this.setCityTown(lienCompany.city);
		this.selectState(lienCompany.state);
		this.setZip(lienCompany.zip);
		this.selectCountry(lienCompany.country);
		
		if(null != lienCompany.contactfName && !"".equals(lienCompany.contactfName)){
			this.setContactFirstName(lienCompany.contactfName);
		}
		
		if(null != lienCompany.contactlName && !"".equals(lienCompany.contactlName)){
			this.setContactLastName(lienCompany.contactlName);
		}
		
		this.setContactPhone(lienCompany.contactPhone);
		
		if(null != lienCompany.contactEmail && !"".equals(lienCompany.contactEmail)){
			this.setContactEmail(lienCompany.contactEmail);
		}
		
	}	
	
}
