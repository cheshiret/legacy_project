/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.landowner;

import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:This page class abstract the actions and Filters sections out, and all view list page and edit page should extends from here
 * @author ssong
 * @Date  Mar 24, 2014
 */
public abstract class LicMgrLandownerCountyQtyListCommonPage extends LicMgrCommonTopMenuPage{

	private Property[] addCountyLYQtyBtn(){
		return Property.addToPropertyArray(a(), ".text", "Add County License Year Quantity");
	}
	
	private Property[] changeHistoryBtn(){
		return Property.addToPropertyArray(a(), ".text", "Change History");
	}
	
	private Property[] landownerPrivilegTypeDropDown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("LandownerCountyQtySearchCriteria-\\d+.landownerPrivilegeType", false));
	}
	
	private Property[] licenseYearDropDown(){
		return Property.addToPropertyArray(select(), ".id", new RegularExpression("LandownerCountyQtySearchCriteria-\\d+\\.licenseYear", false));
	}
	
	private Property[] goBtn(){
		return Property.addToPropertyArray(a(), ".text", new RegularExpression("Go", false));
	}
	
	private Property[] errorMsg(){
		return Property.addToPropertyArray(div(), ".id", "NOTSET");
	}
	
	public void clickAddCountyLicenseYearQty(){
		browser.clickGuiObject(addCountyLYQtyBtn());
	}
	
	public void clickChangeHistory(){
		browser.clickGuiObject(changeHistoryBtn());
	}
	
	public void selectLandownerPrivilegeType(String type){
		browser.selectDropdownList(landownerPrivilegTypeDropDown(), type);
	}
	
	public void selectLicenseYear(String ly){
		browser.selectDropdownList(licenseYearDropDown(), ly);
	}
	
	public void clickGo(){
		browser.clickGuiObject(goBtn());
	}
	
	public boolean isAddCountyLicenseYearQtyEnable(){
		return browser.checkHtmlObjectEnabled(addCountyLYQtyBtn());
	}
	
	public boolean isChangeHistoryBtnEnable(){
		return browser.checkHtmlObjectEnabled(changeHistoryBtn());
	}
	
	public boolean isLandownerPrivilegeTypeEnable(){
		return browser.checkHtmlObjectEnabled(landownerPrivilegTypeDropDown());
	}
	
	public boolean isLicenseYearEnable(){
		return browser.checkHtmlObjectEnabled(licenseYearDropDown());
	}
	
	public boolean isGoBtnEnable(){
		return browser.checkHtmlObjectEnabled(goBtn());
	}
	
	public void searchCountyQty(String type,String licenseYear){
		selectLandownerPrivilegeType(type);
		ajax.waitLoading();
		selectLicenseYear(licenseYear);
		clickGo();
		ajax.waitLoading();
		waitLoading();
	}
	
	public String getDefaultPrivlegeType(){
		return browser.getDropdownListValue(landownerPrivilegTypeDropDown(), 0);
	}
	
	public String getDefaultLicenseYear(){
		return browser.getDropdownListValue(licenseYearDropDown(), 0);
	}
	
	public void checkPrivilegeTypeSorting(){
		boolean sorted = checkDropDownOptionsSorting(landownerPrivilegTypeDropDown());
		if(!sorted){
			throw new ErrorOnPageException("Landowner Privilege Type Options Sorting not correct.");
		}
	}
	
	public void checkLicenseYearSorting(){
		boolean sorted = checkDropDownOptionsSorting(licenseYearDropDown());
		if(!sorted){
			throw new ErrorOnPageException("License Year Options Sorting not correct.");
		}
	}
	
	public String getErrorMsg(){
		return browser.getObjectText(errorMsg());
	}
	
}
