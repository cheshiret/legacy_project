/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.landowner;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.datadefinition.orms.license.LandownerCountyQuantityAttr;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.datacollection.Data;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author ssong
 * @Date  Mar 25, 2014
 */
public class LicMgrAddCountyLicenseYearQtyWidget extends DialogWidget{

	private static LicMgrAddCountyLicenseYearQtyWidget _instance = null;
	
	protected LicMgrAddCountyLicenseYearQtyWidget() {
		
	}
	
	public static LicMgrAddCountyLicenseYearQtyWidget getInstance () {
		if (null == _instance) {
			_instance = new LicMgrAddCountyLicenseYearQtyWidget();
		}
		
		return _instance;
	}
	
	public boolean exists(){
		return super.exists()&& browser.checkHtmlObjectExists(landownerPrivilegType());
	}
	
	private Property[] landownerPrivilegType(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("LandownerPrivilegeTypeLicenseYearQuantityView-\\d+\\.landownerPrivilegeType",false));
	}
	
	private Property[] licenseYear(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("LandownerPrivilegeTypeLicenseYearQuantityView-\\d+\\.licenseYear",false));
	}
	
	private Property[] copyFromYearRadio(){
		return Property.addToPropertyArray(input("radio"), ".value", "copyfrom");
	}
	
	private Property[] fromYear(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("LandownerPrivilegeTypeLicenseYearQuantityView-\\d+\\.fromLicenseYear",false));
	}
	
	private Property[] enterQuantityRadio(){
		return Property.addToPropertyArray(input("radio"), ".value", "enternew");
	}
	
	private Property[] inputQuantityText(){
		return Property.addToPropertyArray(input("text"), ".id", new RegularExpression("LandownerPrivilegeTypeLicenseYearQuantityView-\\d+\\.qty:ZERO_TO_NULL",false));
	}
	
	public void selectLandownerPrivilegeType(String type){
		browser.selectDropdownList(landownerPrivilegType(), type);
	}
	
	public void selectLicenseYear(String licenseYear){
		browser.selectDropdownList(licenseYear(), licenseYear);
	}
	
	public void selectCopyFromYear(){
		browser.selectRadioButton(copyFromYearRadio(),0);
	}
	
	public void selectFromYear(String year){
		browser.selectDropdownList(fromYear(), year);
	}
	
	public boolean checkFromYearEnable(){
		return browser.checkHtmlObjectEnabled(fromYear());
	}
	
	public void copyFromYear(String year){
		selectCopyFromYear();
		ajax.waitLoading();
		selectFromYear(year);
	}
	
	public void selectEnterQuantity(){
		browser.selectRadioButton(enterQuantityRadio(), 0);
	}
	
	public void setQuantity(String qty){
		browser.setTextField(inputQuantityText(), qty);
	}
	
	public boolean checkQuantityInputEnable(){
		return browser.checkHtmlObjectEnabled(inputQuantityText());
	}
	
	public void enterQuantity(String qty){
		selectEnterQuantity();
		ajax.waitLoading();
		setQuantity(qty);
	}
	
	public void setCountyLicenseYearQty(Data<LandownerCountyQuantityAttr> data){
		selectLandownerPrivilegeType(data.stringValue(LandownerCountyQuantityAttr.lANDOWNER_PRIVILEGE_TYPE));
		ajax.waitLoading();
		selectLicenseYear(data.stringValue(LandownerCountyQuantityAttr.lICENSE_YEAR));
		ajax.waitLoading();
		boolean cpyYear = data.booleanValue(LandownerCountyQuantityAttr.COPY_FROM_YEAR);
		if(cpyYear){
			copyFromYear(data.stringValue(LandownerCountyQuantityAttr.FROM_YEAR));
		}else if(data.booleanValue(LandownerCountyQuantityAttr.ENTER_QUANTITY)){
			enterQuantity(data.stringValue(LandownerCountyQuantityAttr.QUANTITY));
		}else{
			throw new ItemNotFoundException("Need initialize a Quantity.");
		}
	}
	
	public void checkPrivilegeTypeSorting(){
		boolean sorted = checkDropDownOptionsSorting(landownerPrivilegType());
		if(!sorted){
			throw new ErrorOnPageException("Landowner Privilege Type Options Sorting not correct.");
		}
	}
	
	public void checkLicenseYearOptions(){
		int currentYear = DateFunctions.getCurrentYear();
		List<String> expectLY = new ArrayList<>();
		for(int i=0;i<11;i++){
			expectLY.add(String.valueOf(currentYear+i));
		}
		List<String> actualLy = browser.getDropdownElements(licenseYear());
		actualLy.remove(0);
		if(!expectLY.equals(actualLy)){
			throw new ErrorOnPageException("License Year Options not correct,it should start from current year and to 10 years later.");
		}
	}
}
