package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.LienCompanyDetailsInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 *
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 *
 * @author qchen
 * @Date  Dec 16, 2011
 */
public class LicMgrLienCompaniesConfigurationPage extends LicMgrSystemConfigurationPage {
	private RegularExpression filtersRegExp = new RegularExpression("LienCompany-\\d+\\.clone", false);
	
	private static LicMgrLienCompaniesConfigurationPage _instance = null;
	
	protected LicMgrLienCompaniesConfigurationPage(){}
	
	public static LicMgrLienCompaniesConfigurationPage getInstance(){
		if(null ==_instance ){
			_instance = new LicMgrLienCompaniesConfigurationPage();
		}
		
		return _instance;
	} 

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "LienCompanyListUI");
	}
	
	public String getLienCompanyAddrID(LienCompanyDetailsInfo lienCompany){
		String lienCompanyAddrID = null;
		IHtmlObject[] objs = browser.getTableTestObject(".id", "lienCompanyList");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found lien company list table object.");
		}
		
		IHtmlTable lienCompanyListTable = (IHtmlTable)objs[0];
		
		for(int i=1; i<lienCompanyListTable.rowCount();i++){
			String lienCompanyName = lienCompanyListTable.getCellValue(i, 1);
			String address = lienCompanyListTable.getCellValue(i, 2);
			String city = lienCompanyListTable.getCellValue(i, 3);
			String state = lienCompanyListTable.getCellValue(i, 4);
			String zip = lienCompanyListTable.getCellValue(i, 5);
			if(lienCompany.lienCompanyName.equals(lienCompanyName) && 
					lienCompany.address.equals(address) &&
					lienCompany.city.equals(city) &&
					lienCompany.state.equals(state) &&
					lienCompany.zip.equals(zip)){
				lienCompanyAddrID = lienCompanyListTable.getCellValue(i, 0);
				break;
			}
		}
		
		Browser.unregister(objs);
		return lienCompanyAddrID;		
	}
	
	public void clickLienCompanyID(String lienCompanyID){
		browser.clickGuiObject(".class", "Html.A",".text",lienCompanyID);
	}
	
	public List<String> getFiltersOptionValues(){
		return browser.getDropdownElements(".id", filtersRegExp);
	}
	
	public boolean checkLienCompanyNameExistingAtFiltersOption(String lienCompanyName){
		logger.info("Check lien company name whether existing at Filters option.");
		
		List<String> filtersOptionElements = this.getFiltersOptionValues();
		if(filtersOptionElements.contains(lienCompanyName)){
			return true;
		}else{
			return false;
		}		
	}
	
	public IHtmlTable getLienCompanyListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "lienCompanyList");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get the lien company list table object.");
		}
		
		IHtmlTable lienCompanyListTable = (IHtmlTable)objs[0];
		
//		Browser.unregister(objs);
		return lienCompanyListTable;
	}
	
	public List<String> getLienCompanyAddrIDColumnValues(){
		return this.getColumnListValues("ID");
	}
	
	public List<String> getLienCompanyNameColumnValues(){
		return this.getColumnListValues("Lien Company Name");
	}
	
	public List<String> getColumnListValues(String columnName){
		IHtmlTable lienCompanyListTable = this.getLienCompanyListTable();
		
		int col = lienCompanyListTable.findColumn(0, columnName);
		List<String> lienCompanyNameColumnValues = lienCompanyListTable.getColumnValues(col);
		
		return lienCompanyNameColumnValues.subList(1, lienCompanyNameColumnValues.size());
	}
	
	public LienCompanyDetailsInfo getLienCompanyInfo(String lienCompanyAddrID){
		IHtmlTable lienCompanyListTable = this.getLienCompanyListTable();
		
		int row = lienCompanyListTable.findRow(0, lienCompanyAddrID);
		if(row<1){
			throw new ItemNotFoundException("Did not get the lien company info, lien company address id = " + lienCompanyAddrID);
		}
		
		LienCompanyDetailsInfo lienCompany = new LienCompanyDetailsInfo();
		lienCompany.lienCompanyAddrID = lienCompanyListTable.getCellValue(row, 0);
		lienCompany.lienCompanyName =  lienCompanyListTable.getCellValue(row, 1);
		lienCompany.address = lienCompanyListTable.getCellValue(row, 2);
		lienCompany.city =  lienCompanyListTable.getCellValue(row, 3);
		lienCompany.state =  lienCompanyListTable.getCellValue(row, 4);
		lienCompany.zip = lienCompanyListTable.getCellValue(row, 5);
		lienCompany.country = lienCompanyListTable.getCellValue(row, 6);
		lienCompany.contactfName = lienCompanyListTable.getCellValue(row, 7);
		lienCompany.contactlName = lienCompanyListTable.getCellValue(row, 8);
		lienCompany.contactPhone = lienCompanyListTable.getCellValue(row, 9);
		lienCompany.contactEmail = lienCompanyListTable.getCellValue(row, 10);
		
		return lienCompany;
	}
	
	public void compareLienCompanyInfo(LienCompanyDetailsInfo expLienCompany){
		logger.info("Compare lien company info from list page.");
		LienCompanyDetailsInfo actLienCompany = this.getLienCompanyInfo(expLienCompany.lienCompanyAddrID);
		boolean result = true;
				
		result &= MiscFunctions.compareResult("Lien Company ID", expLienCompany.lienCompanyAddrID, actLienCompany.lienCompanyAddrID);
		result &= MiscFunctions.compareResult("Lien Company Name", expLienCompany.lienCompanyName, actLienCompany.lienCompanyName);
		result &= MiscFunctions.compareResult("Address", expLienCompany.address, actLienCompany.address);
		result &= MiscFunctions.compareResult("City", expLienCompany.city, actLienCompany.city);
		result &= MiscFunctions.compareResult("State", expLienCompany.state, actLienCompany.state);
		result &= MiscFunctions.compareResult("Zip", expLienCompany.zip, actLienCompany.zip);	
		result &= MiscFunctions.compareResult("Country", expLienCompany.country, actLienCompany.country);
		result &= MiscFunctions.compareResult("Contact First Name", expLienCompany.contactfName, actLienCompany.contactfName);
		result &= MiscFunctions.compareResult("Contact Last Name", expLienCompany.contactlName, actLienCompany.contactlName);
		result &= MiscFunctions.compareResult("Contact Phone", expLienCompany.contactPhone, actLienCompany.contactPhone);	
		result &= MiscFunctions.compareResult("Contact Email", expLienCompany.contactEmail, actLienCompany.contactEmail);
		
		if(!result){
			throw new ErrorOnDataException("Lien Company info at list page is not correct, please check log.");
		}else{
			logger.info("Lien Company info at list page is correct.");
		}

	}
	
	public void selectFilters(String lienCompanyName){
		browser.selectDropdownList(".id", filtersRegExp, lienCompanyName);
	}
	
	public void selectFilters(){
		browser.selectDropdownList(".id", filtersRegExp, 0);
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

}
