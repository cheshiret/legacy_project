package com.activenetwork.qa.awo.pages.orms.inventoryManager.warehouse;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PocInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.WarehouseInfo;
import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrCommonTopMenuPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**  
 * @Description:  ware house details page
 * @Preconditions:  
 * @SPEC:  
 * @Task#: Auto-972
 * @author jwang8  
 * @Date  Mar 26, 2012   
 */
public class InvMgrWarehouseDetailsPage extends InvMgrCommonTopMenuPage{
    
    private static InvMgrWarehouseDetailsPage instance = null;
    
    private InvMgrWarehouseDetailsPage(){};
    
    public static InvMgrWarehouseDetailsPage getInstance(){
    	if(null == instance){
    		instance = new InvMgrWarehouseDetailsPage();
    	}
    	return instance;
    }
    
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("WarehouseView-\\d+\\.name",false));
	}
	
	/**
	 * set warehouse name.
	 * @param name- the name of ware house.
	 */
	public void setWareHouseName(String name){
	    browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.name",false), name);
	}
	/**
	 * set warehouse description.
	 * @param description- the ware house description.
	 */
	public void setDescription(String description){
		 browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.description",false), description);
	}
	
	/**
	 * set mailing address.
	 * @param mailingAddress- the name of mailing address.
	 */
	public void setMailingAddress(String mailingAddress){
		 browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrStreet",false), mailingAddress);
	}
	
	/**
	 * set mailing city.
	 * @param mailingCity- the name of mailing city.
	 */
	public void setMailingCity(String mailingCity){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrCity",false), mailingCity);
	}
	/**
	 * set mailing state.
	 * @param mailingState- the name of mailing state.
	 */
	public void selectMialingState(String mailingState){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrState",false), mailingState);
	}
	
	/**
	 * set mailing zip code.
	 * @param zipCode- the name of mailing zip code.
	 */
	public void setMailingZipCode(String zipCode){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrPostalCode",false), zipCode);
	}
	/**
	 * set mailing country.
	 * @param zipCode- the name of mailing country.
	 */
	public void selectMailingCountry(String country){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrCountry",false), country);
	}
	
	/**
	 * set primary pos last name.
	 * @param primLastName- the name of primary poc last name.
	 */
	public void setPrimLastName(String primLastName){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCLastName",false), primLastName);
	}
	
	/**
	 * set primary pos first name.
	 * @param primLastName- the name of primary poc first name.
	 */
	public void setPrimFirstName(String primFistName){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCFirstName",false), primFistName);
	}
	
	/**
	 * set primary pos phone.
	 * @param primLastName- the  primary poc  phone.
	 */
	public void setPrimPhone(String primPhone){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCPhone",false), primPhone);
	}
	/**
	 * set primary poc fax.
	 * @param primLastName- the  primary poc fax.
	 */
	public void setPrimFax(String primFax){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCFax",false), primFax);
	}
	/**
	 * set primary poc email.
	 * @param primLastName- the  primary poc email.
	 */
	public void setPrimEmail(String primEmail){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCEmail",false), primEmail);
	}
	/**
	 * set primary poc address.
	 * @param primAddress- primary poc address.
	 */
	public void setPrimAddress(String primAddress){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCStreet",false), primAddress);
	}
	
	/**
	 * set primary poc city.
	 * @param primAddress- primary poc city.
	 */
	public void setPrimCity(String primCity){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCCity",false), primCity);
	}
	
	/**
	 * set primary poc state.
	 * @param primState- primary poc state.
	 */
	public void selectPrimState(String primState){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCState",false), primState);
	}
	/**
	 * set primary poc zip code.
	 * @param primZipCode- primary poc zip code.
	 */
	public void setPrimZipCode(String primZipCode){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCPostalCode",false), primZipCode);
	}
	/**
	 * set primary poc country.
	 * @param primCountry- primary poc country.
	 */
	public void selectPrimCountry(String primCountry){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCCountry",false), primCountry);
	}
	/**
	 * set secondary poc last name.
	 * @param primState- secondary poc last name.
	 */
	public void setSecPocLastName(String secPosLastName){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCLastName",false), secPosLastName);
	}
	
	/**
	 * set secondary poc first name.
	 * @param secFirstName- secondary poc first name.
	 */
	public void setSecPosFirstName(String secFirstName){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCFirstName",false), secFirstName);
	}
	/**
	 * set secondary poc phone.
	 * @param phone- secondary poc phone.
	 */
	public void setSecPosPhone(String phone){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCPhone",false), phone);
	}
	/**
	 * set secondary poc fax.
	 * @param secPosFax- secondary poc fax.
	 */
	public void setSecPosFax(String secPosFax){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCFax",false), secPosFax);
	}
	/**
	 * set secondary poc email.
	 * @param secPosEmail- secondary poc email.
	 */
	public void setSecPosEmail(String secPosEmail){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCEmail",false), secPosEmail);
	}
	/**
	 * set secondary poc address.
	 * @param secPosAddress- secondary poc address.
	 */
	public void setSecPosAddress(String secPosAddress){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCStreet",false), secPosAddress);
	}
	/**
	 * set secondary poc city.
	 * @param secPosCity- secondary poc city.
	 */
	public void setSecPosCity(String secPosCity){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCCity",false), secPosCity);
	}
	/**
	 * set secondary poc state.
	 * @param secPosState- secondary poc state.
	 */
	public void selectSecPosState(String secPosState){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCState",false), secPosState);
	}
	/**
	 * set secondary poc zip code.
	 * @param secPosZipCode- secondary poc zip code.
	 */
	public void setSecPosZipCode(String secPosZipCode){
		browser.setTextField(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCPostalCode",false), secPosZipCode);
	}
	
	/**
	 * set secondary poc country.
	 * @param secPosCountry- secondary poc zip country.
	 */
	public void selectSecPosCountry(String secPosCountry){
		browser.selectDropdownList(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCCountry",false), secPosCountry);
	}
	/**
	 * click ok button.
	 */
	public void clickOkButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	/**
	 * click apply button.
	 */
	public void clickApplyButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	/**
	 * click cancel button.
	 */
	public void clickCancelButton(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	/**
	 * set mailling address.
	 * @param maillingAddress
	 */
	public void setMaillingAddress(Address maillingAddress){
		this.setMailingAddress(maillingAddress.address);
		this.setMailingCity(maillingAddress.city);
		this.selectMailingCountry(maillingAddress.country);
		ajax.waitLoading();
		this.selectMialingState(maillingAddress.state);
		this.setMailingZipCode(maillingAddress.zip);
	}
	/**
	 * set primary poc address.
	 * @param primPocAddress
	 */
	public void setPrimPocAddress(Address primPocAddress){
		this.setPrimAddress(primPocAddress.address);
		this.setPrimCity(primPocAddress.city);
		this.selectPrimCountry(primPocAddress.country);
		ajax.waitLoading();
		this.selectPrimState(primPocAddress.state);
		ajax.waitLoading();
		this.setPrimZipCode(primPocAddress.zip);
	}
	/*
	 * set primary poc.
	 */
	public void setPrimPoc(PocInfo poc){
		this.setPrimLastName(poc.getLastName());
		this.setPrimFirstName(poc.getFirstName());
		this.setPrimPhone(poc.getPhone());
		this.setPrimFax(poc.getFax());
		this.setPrimEmail(poc.getEmail());
		this.setPrimPocAddress(poc.getPocAddress());
	}
	/**
	 * set secondary POC info.
	 * @param poc
	 */
	public void setSecPocInfo(PocInfo poc){
		this.setSecPocLastName(poc.getLastName());
		this.setSecPosFirstName(poc.getFirstName());
		this.setSecPosFax(poc.getFax());
		this.setSecPosPhone(poc.getPhone());
		this.setSecPosEmail(poc.getEmail());
		this.setSecPocAddress(poc.getPocAddress());
	}
	/*
	 * set secondary POC address.
	 */
	public void setSecPocAddress(Address address){
		this.setSecPosAddress(address.address);
		this.setSecPosCity(address.city);
		this.selectSecPosCountry(address.country);
		ajax.waitLoading();
		this.selectSecPosState(address.state);
		ajax.waitLoading();
		this.setSecPosZipCode(address.zip);
		
	}
	/**
	 * set ware house details info.
	 * @param warehouseInfo- the info of ware house info.
	 */
	public void setWarehouseDetailsInfo(WarehouseInfo warehouseInfo){
		this.setWareHouseName(warehouseInfo.getName());
		this.setDescription(warehouseInfo.getDesctiption());
		this.setMaillingAddress(warehouseInfo.getMailingAddress());
		this.setPrimPoc(warehouseInfo.getPrimaryPoc());
		this.setSecPocInfo(warehouseInfo.getSecPoc());
	}
	
	 /** Get the error message displayed at the top of page
	 * 
	 * @return String - the error message.
	 */
	public String getErrorMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id",
				"NOTSET");
		String msg = "";
		if (objs.length > 0) {
			msg = objs[0].getProperty(".text").trim();
		}

		Browser.unregister(objs);
		return msg;
	}
	
	public String getWareHouseId(){
		return browser.getTextFieldValue(".id",new RegularExpression("WarehouseView-\\d+\\.id",false));
	}
	/**
	 * get warehouse name.
	 * @return
	 */
	public String getWarehouseName(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.name",false));
	}
	/*
	 * get descrioption.
	 */
	public String getDescription(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.description",false));
	}
	/**
	 * get mailing address
	 * @return
	 */
	public String getMailingAddress(){
		return  browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrStreet",false));
	}
	/**
	 * get mailing city.
	 * @return
	 */
	public String getMailingCity(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrCity",false));
	}
	/**
	 * get mailing state.
	 * @return
	 */
	public String getMailingState(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrState",false));
	}
	/**
	 * get mailing zip.
	 * @return
	 */
	public String getMailingZip(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrPostalCode",false));
	}
	/**
	 * get mailing country.
	 * @return
	 */
	public String getMailingCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.mailAddrCountry",false));
	}
	/**
	 * get primary POC last name.
	 * @return
	 */
	public String getPriPocLName(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCLastName",false));
	}
	/**
	 * get primary POC first name.
	 * @return
	 */
	public String getPriPocFName(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCFirstName",false));
	}
	/**
	 * get primary POC phone.
	 * @return
	 */
	public String getPriPocPhone(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCPhone",false));
	}
	/**
	 * get primary POC fax.
	 * @return
	 */
	public String getPriPocFax(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCFax",false));
	}
	/**
	 * get primary POC email.
	 * @return
	 */
	public String getPriPocEmail(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCEmail",false));
	}
	/**
	 * get primary POC address.
	 * @return
	 */
	public String getPriPocAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCStreet",false));
	}
	/**
	 * get primary poc city.
	 * @return
	 */
	public String getPriPocCity(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCCity",false));
	}
	/**
	 * get primary POC state.
	 * @return
	 */
	public String getPriPocState(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCState",false));
	}
	/**
	 * get primary POC Zip.
	 * @return
	 */
	public String getPriPocZip(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCPostalCode",false));
	}
	/**
	 * get primary POC country.
	 * @return
	 */
	public String getPriPocCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.primPOCCountry",false));
	}
	/**
	 * get secondary POC last name.
	 * @return
	 */
	public String getSecPocLName(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCLastName",false));
	}
	/**
	 * get secondary POC first name.
	 * @return
	 */
	public String getSecPocFName(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCFirstName",false));
	}
	/**
	 * get secondary POC phone.
	 * @return
	 */
	public String getSecPocPhone(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCPhone",false));
	}
	/**
	 * get secondary POC fax.
	 * @return
	 */
	public String getSecPocFax(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCFax",false));
	}
	/**
	 * get secondary POC email.
	 * @return
	 */
	public String getSecPocEmail(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCEmail",false));
	}
	/**
	 * get secondary POC address.
	 * @return
	 */
	public String getSecPocAddress(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCStreet",false));
	}
	/**
	 * get secondary POC city.
	 * @return
	 */
	public String getSecPocCity(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCCity",false));
	}
	/**
	 * get secondary POC state.
	 * @return
	 */
	public String getSecPocState(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCState",false));
	}
	/**
	 * get secondary POC zip.
	 * @return
	 */
	public String getSecPocZip(){
		return browser.getTextFieldValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCPostalCode",false));
	}
	/**
	 * get secondary POC country.
	 * @return
	 */
	public String getSecPocCountry(){
		return browser.getDropdownListValue(".id", new RegularExpression("WarehouseView-\\d+\\.secPOCCountry",false));
	}
	/**
	 * compare ware house info.
	 * @param wareHouse
	 */
	public boolean compareWareHouseInfo(WarehouseInfo wareHouse){
		boolean isEqual = true;
		isEqual = MiscFunctions.compareResult("warehouse id", wareHouse.getId(),this.getWareHouseId());
		isEqual = MiscFunctions.compareResult("warehouse name", wareHouse.getName(), this.getWarehouseName());
		isEqual = MiscFunctions.compareResult("Description", wareHouse.getDesctiption(), this.getDescription());
		isEqual = MiscFunctions.compareResult("Mailing Address", wareHouse.getMailingAddress().address, this.getMailingAddress());
		isEqual = MiscFunctions.compareResult("Mailing City", wareHouse.getMailingAddress().city, this.getMailingCity());
		isEqual = MiscFunctions.compareResult("Mailing State", wareHouse.getMailingAddress().state, this.getMailingState());
		isEqual = MiscFunctions.compareResult("Mailing Zip", wareHouse.getMailingAddress().zip, this.getMailingZip());
		isEqual = MiscFunctions.compareResult("Mailing Country", wareHouse.getMailingAddress().country, this.getMailingCountry());
		isEqual = MiscFunctions.compareResult("Primary Poc Last name", wareHouse.getPrimaryPoc().getLastName(), this.getPriPocLName());
		isEqual = MiscFunctions.compareResult("Primary Poc First name", wareHouse.getPrimaryPoc().getFirstName(), this.getPriPocFName());
		isEqual = MiscFunctions.compareResult("Primary Poc Phone", wareHouse.getPrimaryPoc().getPhone(), this.getPriPocPhone());
		isEqual = MiscFunctions.compareResult("Primary Poc fax", wareHouse.getPrimaryPoc().getFax(), this.getPriPocFax());
		isEqual = MiscFunctions.compareResult("Primary Poc email", wareHouse.getPrimaryPoc().getEmail(), this.getPriPocEmail());
		isEqual = MiscFunctions.compareResult("Primary Poc address", wareHouse.getPrimaryPoc().getPocAddress().address, this.getPriPocAddress());
		isEqual = MiscFunctions.compareResult("Primary Poc city", wareHouse.getPrimaryPoc().getPocAddress().city, this.getPriPocCity());
		isEqual = MiscFunctions.compareResult("Primary Poc state", wareHouse.getPrimaryPoc().getPocAddress().state, this.getPriPocState());
		isEqual = MiscFunctions.compareResult("Primary Poc zip", wareHouse.getPrimaryPoc().getPocAddress().zip, this.getPriPocZip());
		isEqual = MiscFunctions.compareResult("Primary Poc country", wareHouse.getPrimaryPoc().getPocAddress().country, this.getPriPocCountry());
		isEqual = MiscFunctions.compareResult("Secondary Poc address", wareHouse.getSecPoc().getPocAddress().address, this.getSecPocAddress());
		isEqual = MiscFunctions.compareResult("Secondary Poc city", wareHouse.getSecPoc().getPocAddress().city, this.getSecPocCity());
		isEqual = MiscFunctions.compareResult("Secondary Poc state", wareHouse.getSecPoc().getPocAddress().state, this.getSecPocState());
		isEqual = MiscFunctions.compareResult("Secondary Poc zip", wareHouse.getSecPoc().getPocAddress().zip, this.getSecPocZip());
		isEqual = MiscFunctions.compareResult("Secondary Poc country", wareHouse.getSecPoc().getPocAddress().country, this.getSecPocCountry());
		return isEqual;
	}
	/**
	 * verify ware house info.
	 * @param warehouseInfo
	 */
	public void verifyWarehouseInfo(WarehouseInfo warehouseInfo){
	  boolean isPass =	this.compareWareHouseInfo(warehouseInfo);
	  if(!isPass){
		  throw new ErrorOnPageException("Warehouse details info error");
	  }else{
		  logger.info("Ware house info is correct.");
	  }
	}
	/**
	 * click stock transfer receiving locations.
	 */
	public void clickStcTransRecLocaions(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Stock Transfer Receiving Locations");
	}
	/**
	 * get warehouse set up element disable attribute.
	 * @param reg
	 */
	public boolean getDisableAttribute(RegularExpression reg){
		IHtmlObject[] objs = browser.getHtmlObject(".id",reg);
		if(objs.length<1){
			throw new ErrorOnPageException("No specific element exist");
		}
		if("true".equals(objs[0].getProperty(".isDisabled"))){
			return true;
		}
		return false;
	}
	/**
	 * get ware house id disabled attribute.
	 * @return
	 */
	public boolean getWarehouseIdDisabledAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.id",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house name attribute.
	 * @return
	 */
	public boolean getWarehouseNameDisabledAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.name",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse description attribute.
	 * @return
	 */
	public boolean getWarehouseDescriptionDisabledAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.description",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse mailing address disabled attribute.
	 * @return
	 */
	public boolean getWarehouseMailAddressDisabledAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.mailAddrStreet",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house mailing city disable attribute.
	 * @return
	 */
	public boolean getWarehouseMailCityDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.mailAddrCity",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house mailing state disable attribute.
	 * @return
	 */
	public boolean getWarehouseMailStateDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.mailAddrState",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse mailing zip disable attribute.
	 * @return
	 */
	public boolean getWarehouseMailZipDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.mailAddrPostalCode",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse mail country.
	 * @return
	 */
	public boolean getWarehouseMailCountryDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.mailAddrCountry",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse last name disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCLNameDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCLastName",false);
		return this.getDisableAttribute(reg);
	}
	/*
	 * get warehouse first name disable attribute.
	 */
	public boolean getWhousePriPOCFNameDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCFirstName",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse phone disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCPhoneDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCPhone",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse fax disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCFaxDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCFax",false);
		return this.getDisableAttribute(reg);
	}
	/*
	 * get warehouse email disable attribute.
	 */
	public boolean getWhousePriPOCEmailDisableAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCEmail",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house primary POC address disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCAddressDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCStreet",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse primary POC city address disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCCityDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCCity",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse primary POC state disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCStateDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCState",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse primary POC zip disable attribute.
	 * @return
	 */
	public boolean getWhousePriPOCZipDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCPostalCode",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse primary country.
	 * @return
	 */
	public boolean getWhousePriPOCCountryDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.primPOCCountry",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC.
	 * @return
	 */
	public boolean getWhouseSecPOCLNameDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCLastName",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCFNameDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCFirstName",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC phone disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCPhoneDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCPhone",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house secondary POC fax disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCFaxDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCFax",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC email disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCEmailDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCEmail",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC address.
	 * @return
	 */
	public boolean getWhouseSecPOCAddrDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCStreet",false);
		return this.getDisableAttribute(reg);
	}
	/*
	 * get ware house secondary POC city disable attribute.
	 */
	public boolean getWhouseSecPOCCityDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCCity",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house secondary POC state disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCStateDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCState",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get warehouse secondary POC zip disable attribute.
	 * @return
	 */
	public boolean getWhouseSecPOCZipDisAttr(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCPostalCode",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * get ware house Secondary POC country.
	 * @return
	 */
	public boolean getWhouseSecPOCCountry(){
		RegularExpression reg = new RegularExpression("WarehouseView-\\d+\\.secPOCCountry",false);
		return this.getDisableAttribute(reg);
	}
	/**
	 * check warehouse info disable attribute..
	 */
	public boolean checkWhouseInfoDisableAttr(){
		boolean isPass = true;
		if(!this.getWarehouseIdDisabledAttr()){
			isPass &= false;
			logger.error("ware house id shoud disable");
		}if(this.getWarehouseNameDisabledAttr()){
			isPass &= false;
			logger.error("ware house name should enable");
		}if(this.getWarehouseDescriptionDisabledAttr()){
			isPass &= false;
			logger.error("ware house description should enable");
		}if(this.getWarehouseMailAddressDisabledAttr()){
			isPass &= false;
			logger.error("ware house description should enable");
		}if(this.getWarehouseMailCityDisableAttr()){
			isPass &= false;
			logger.error("ware house mailing address should enable");
		}if(this.getWarehouseMailCityDisableAttr()){
			isPass &= false;
			logger.error("ware house mailing city should enable");
		}if(this.getWarehouseMailStateDisableAttr()){
			isPass &= false;
			logger.error("ware house mailing state should enable");
		}if(this.getWarehouseMailZipDisableAttr()){
			isPass &= false;
			logger.error("ware house mailing zip should enable");
		}if(this.getWarehouseMailCountryDisableAttr()){
			isPass &= false;
			logger.error("ware house mailing country should enable");
		}if(this.getWhousePriPOCAddressDisAttr()){
			isPass &= false;
			logger.error("ware house primary POC address should enable");
		}if(this.getWhousePriPOCCityDisAttr()){
			isPass &= false;
			logger.error("ware house primary POC address should enable");
		}if(this.getWhousePriPOCStateDisAttr()){
			isPass &= false;
			logger.error("ware house primary POC state should enable");
		}if(this.getWhousePriPOCZipDisAttr()){
			isPass &= false;
			logger.error("ware house primary POC zip should enable");
		}if(this.getWhousePriPOCCountryDisAttr()){
			isPass &= false;
			logger.error("ware house primary POC country should enable");
		}if(this.getWhousePriPOCLNameDisableAttr()){
			isPass &= false;
			logger.error("ware house primary POC last name should enable");
		}if(this.getWhousePriPOCFNameDisableAttr()){
			isPass &= false;
			logger.error("ware house primary POC first should enable");
		}if(this.getWhousePriPOCPhoneDisableAttr()){
			isPass &= false;
			logger.error("ware house primary POC phone should enable");
		}if(this.getWhousePriPOCFaxDisableAttr()){
			isPass &= false;
			logger.error("ware house primary POC fax should enable");
		}if(this.getWhousePriPOCEmailDisableAttr()){
			isPass &= false;
			logger.error("ware house primary POC email should enable");
		}if(this.getWhouseSecPOCLNameDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC last name should enable");
		}if(this.getWhouseSecPOCFNameDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC first name should enable");
		}if(this.getWhouseSecPOCPhoneDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC phone should enable");
		}if(this.getWhouseSecPOCFaxDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC fax should enable");
		}if(this.getWhouseSecPOCEmailDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC email should enable");
		}if(this.getWhouseSecPOCAddrDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC address should enable");
		}if(this.getWhouseSecPOCCityDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC city should enable");
		}if(this.getWhouseSecPOCStateDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC state should enable");
		}if(this.getWhouseSecPOCZipDisAttr()){
			isPass &= false;
			logger.error("ware house secondary POC zip should enable");
		}if(this.getWhouseSecPOCCountry()){
			isPass &= false;
			logger.error("ware house secondary POC country should enable");
		}
		return isPass;
	}
}
