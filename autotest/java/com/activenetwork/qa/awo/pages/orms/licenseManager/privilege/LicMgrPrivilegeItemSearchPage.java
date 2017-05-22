package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.LicMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrPrivilegeItemSearchPage extends LicMgrCommonTopMenuPage {
	private static LicMgrPrivilegeItemSearchPage _instance = null;

	protected LicMgrPrivilegeItemSearchPage() {
	}

	public static LicMgrPrivilegeItemSearchPage getInstance() {
		if (_instance == null) {
			_instance = new LicMgrPrivilegeItemSearchPage();
		}

		return _instance;
	}

	@Override
	public boolean exists() {
		return browser
				.checkHtmlObjectExists(
						".class",
						"Html.SELECT",
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchType",
								false));
	}

	/**
	 * Select the search type drop down list
	 * 
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		if (searchType.length() > 0) {
			browser.selectDropdownList(".id",new RegularExpression("PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchType",false), searchType);
		} else {
			browser.selectDropdownList(".id",new RegularExpression("PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchType",false), 0);
		}
		ajax.waitLoading();
	}

	public List<String> getSearchTypes(){
		return browser.getDropdownElements(".id",new RegularExpression("PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchType",false));
	}
	
	/**
	 * Set the search type value
	 * 
	 * @param searchValue
	 */
	public void setSearchValue(String searchValue) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.searchTypeValue",
								false), searchValue);
	}

	/**
	 * Select a search date type
	 * 
	 * @param dateType
	 */
	public void selectDateType(String dateType) {
		
		RegularExpression reg = new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.dateType",
				false);
		browser.selectDropdownList(".id", reg, dateType);
		
		if (dateType.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}

	/**
	 * Set the search from date
	 * 
	 * @param fromDate
	 */
	public void setFromDate(String fromDate) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.fromDate_ForDisplay",
								false), fromDate);
	}

	/**
	 * Set the search to date
	 * 
	 * @param toDate
	 */
	public void setToDate(String toDate) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.toDate_ForDisplay",
								false), toDate);
	}

	/**
	 * Select privilege status
	 * 
	 * @param privilegeStatus
	 */
	public void selectPrivilegeStatus(String privilegeStatus) {
		RegularExpression reg = new RegularExpression(	"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.privilegeStatus",false);
		browser	.selectDropdownList(".id",	reg, privilegeStatus);
		
		//To resolve can't select "".
		if (privilegeStatus.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}

	/**
	 * Set product code
	 * 
	 * @param productCode
	 */
	public void setProductCode(String productCode) {
		browser.setTextField(".id", new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.productCode",
				false), productCode);
	}

	/**
	 * Set license year
	 * 
	 * @param licenseYear
	 */
	public void setLicenseYear(String licenseYear) {
		browser.setTextField(".id", new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.licenseYear",
				false), licenseYear);
	}

	/**
	 * Set customer zip code
	 * 
	 * @param zipCode
	 */
	public void setZipCode(String zipCode) {
		browser
				.setTextField(
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.customerZipCode",
								false), zipCode);
	}

	/**
	 * Set customer county
	 * 
	 * @param county
	 */
	public void selectCounty(String county) {
		
		RegularExpression reg =new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.customerCounty",
				false);
		browser.selectDropdownList(".id", reg, county);
		if (county.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}

	/**
	 * Select customer state/province
	 * 
	 * @param state
	 */
	public void selectState(String state) {
		
		RegularExpression reg = new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.customerState",
				false);
		browser.selectDropdownList(".id", reg, state);
		if (state.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
		
	}

	/**
	 * Select customer country
	 * 
	 * @param country
	 */
	public void selectCountry(String country) {
		RegularExpression reg =new RegularExpression(
				"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.customerCountry",
				false);
		browser.selectDropdownList(".id", reg, country);
		if (country.trim().length() == 0){
			browser.selectDropdownList(".id",reg,0);
		}
	}

	/**
	 * Select customer residency status
	 * 
	 * @param residencyStatus
	 */
	public void selectResidencyStatus(String residencyStatus) {
		browser
				.selectDropdownList(
						".id",
						new RegularExpression(
								"PrivilegeInstanceSearchCriteriaUIAdaptor-\\d+\\.customerResidencyStatus",
								false), residencyStatus);
	}

	/**
	 * Click search button
	 */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
	}

	/**
	 * Click privilege number link to goto privilege item detail page.
	 * 
	 * @param privilegeNum
	 */
	public void clickPrivilegeNumber(String privilegeNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", privilegeNum, true);
	}

	/**
	 * This method used to search privilege
	 * 
	 * @param type
	 * @param value
	 */
	public void searchPrivilege(String type, String value) {
		this.selectSearchType(type);
		this.setSearchValue(value);
		this.clickSearch();
		ajax.waitLoading();
	}

	/**
	 * Get privilege number using privilege name and license year in the
	 * privilege search page
	 * 
	 * @param product
	 * @param licYear
	 * @return
	 */
	public List<String> getPrivilegeNumber(String product, String licYear, String status) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"privilegeInstanceList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> privNum = new ArrayList<String>();
		int productCol = table.findColumn(0, "Product");
		int licYearCol = table.findColumn(0, "License Year");
		int privilegeCol = table.findColumn(0, "Privilege Number");
		int statusCol = table.findColumn(0, "Status");
		for (int i = 0; i < table.rowCount(); i++) {
			if (product.equals(table.getCellValue(i, productCol).replaceAll(
					" ", ""))
					&& licYear.equals(table.getCellValue(i, licYearCol))
					&& status.equals(table.getCellValue(i, statusCol))) {
				privNum.add(table.getCellValue(i, privilegeCol));
			}
		}

		return privNum;
	}
	
	/**
	 * Get active privilege number
	 * @param product
	 * @param licYear
	 * @return
	 */
	public List<String> getPrivilegeNumber(String product, String licYear){
		return this.getPrivilegeNumber(product, licYear, "Active");
	}

	/** click customers tab */
	public void clickCustomersTab() {
		Property property[] = new Property[3];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", new RegularExpression("Customers",
				false));
		property[2] = new Property(".href", new RegularExpression(
				"SearchCustomer$", false));
		browser.clickGuiObject(property);
		ajax.waitLoading();
	}
	/**
	 * set the privilege search criteria
	 * @param criteria --  the info of privilege search criteria
	 */
     /**
     * @param criteria
     */
    public void setPrivilegeSearchCriteria(PrivilegeInfo privilege){
    	 if(privilege.searchBy.length()>0){
   		 this.selectSearchType(privilege.searchBy);
    	 }
    	 if(privilege.searchValue.length()>0){
    		 this.setSearchValue(privilege.searchValue);
    	 }
    	 if(privilege.searchDateType.length()>0){
    		 this.selectDateType(privilege.searchDateType);
    		 ajax.waitLoading();
    	 }
    	 if(privilege.validFromDate.length()>0){
    		 this.setFromDate(privilege.validFromDate);
    	 }
    	 if(privilege.validToDate.length()>0){
    		 this.setToDate(privilege.validToDate);
    	 }
    	 if(privilege.status.length()>0){
    		 this.selectPrivilegeStatus(privilege.status);
    	 }
    	 if(privilege.code.length()>0){
    		 this.setProductCode(privilege.code);
    	 }
    	 if(privilege.licenseYear.length()>0){
    		 this.setLicenseYear(privilege.licenseYear);
    	 }
    	 if(privilege.searchZip.length()>0){
    		 this.setZipCode(privilege.searchZip);
   	     }
    	 if(privilege.searchCountry.length()>0){
    		 this.selectCountry(privilege.searchCountry);
    		 ajax.waitLoading();
    		 if(privilege.searchState.length()>0){
    			 this.selectState(privilege.searchState);
        		 ajax.waitLoading();
        		 if(privilege.searchCounty.length()>0){
        			 this.selectCounty(privilege.searchCounty);
            		 ajax.waitLoading();
        		 }
    		 }
    	 }
    	 if(privilege.searchReStatus.length()>0){
    		 this.selectResidencyStatus(privilege.searchReStatus);
    	 }
     }
     /**
      * Search privilege
      * @param criteria -- the info of privilege search criteria
      */
     public void searchPrivilege(PrivilegeInfo privilege){
    	 this.setPrivilegeSearchCriteria(privilege);
    	 this.clickSearch();
    	 ajax.waitLoading();
     }
 	/** 
 	 * Click the next button
 	 * */
 	public boolean clickNext(){
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text",
 				new RegularExpression("Next", false));
 		
 		String isDisable = objs[0].getProperty(".isDisabled");

 		boolean toReturn = false;
 		if (isDisable.equals("true")) {
 			toReturn = true;
 			objs[0].click();
 		}
 		Browser.unregister(objs);
 		ajax.waitLoading();
 		return toReturn;
 	}
     
 	/**
 	 * Get the column index.
 	 */
 	public int getColIndex(String colName) {
 		int colNum = 0;
 		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
 				".id", "privilegeInstanceList_LIST");
 		if (objs.length > 0) {
 			IHtmlTable cusTableGrid = (IHtmlTable) objs[0];
 			//System.out.println(cusTableGrid.getColumnValues(8));
 			colNum = cusTableGrid.findColumn(0, colName);
 		} else
 			throw new ObjectNotFoundException("Object can't find.");

 		Browser.unregister(objs);
 		return colNum;
 	}
 	/**
 	 * Get the privilege list info.
 	 * @return List<List<String>>-- all the searched privilege info.
 	 */
 	public List<List<String>> getPrivilegeListInfo(){
 		List<List<String>> priListInfo = new ArrayList<List<String>>();
 		List<String> priRowInfo = new ArrayList<String>();
 		do{
 			IHtmlObject[] objs = browser.getTableTestObject(".id", "privilegeInstanceList_LIST");
 			if(objs.length<1){
 				throw new ErrorOnDataException(
				"Can't find the specific privilege");
 			}
 			IHtmlTable table =(IHtmlTable)objs[0];
 			if(table.rowCount()>1){
 				for(int i = 1;i<table.rowCount();i++){
 					priRowInfo = table.getRowValues(i);
 					priListInfo.add(priRowInfo);
 				}
 			}else{
 				throw new ErrorOnDataException(
				"No privilege info is retrived!");
 			}
 		   Browser.unregister(objs);
 		}while(this.clickNext());
 		return priListInfo;
 	}
 	/**
 	 * Verify privilege search result.
 	 * @param priListInfo -- the privilege list info.
 	 * @param searchCriteria -- the search criteria.
 	 * @param colName -- the column name.
 	 */
 	public void verifyPrivilegeSearchResult(List<List<String>> priListInfo,String searchCriteria,String colName){
 		List<String> priRowInfo = new ArrayList<String>();
 		if(searchCriteria.length()>0){
 			int colIndex = this.getColIndex(colName);
 			if(priListInfo.size()>=1){
 				for(int i=0;i<priListInfo.size();i++){
 					priRowInfo = priListInfo.get(i);
 					if(colName.equals("Product")||colName.equals("Customer")){
 						if(!priRowInfo.get(colIndex).contains(searchCriteria)){
 							throw new ErrorOnDataException(searchCriteria
 									+ priRowInfo.get(colIndex)
 									+ " doesn't belong to the privilege info");
 						}
 					}else if(colName.equals("Valid From/ Valid To")){
 						if(!DateFunctions.formatDate(priRowInfo.get(colIndex), "EEEE MMM dd yyyy").contains(DateFunctions.formatDate(searchCriteria, "EEEE MMM dd yyyy"))){
 							throw new ErrorOnDataException(searchCriteria
 									+ priRowInfo.get(colIndex)
 									+ " doesn't belong to the privilege info");
 						}
 					}
 					else{
 						String cellText = priRowInfo.get(colIndex).trim();
 						String expectedValue = searchCriteria.trim();
 						if(!cellText.equals(expectedValue)){
 	 						throw new ErrorOnDataException(searchCriteria
 									+ priRowInfo.get(colIndex)
 									+ " doesn't match "+expectedValue+"privilege info");
 	 					}
 					}
 					
 				}
 			}
 		}
 	}
 	
	/**
 	 * Verify privilege search result.
 	 * @param priListInfo -- the privilege list info.
 	 */
 	public void verifyPrivilegeSearchResult(PrivilegeInfo expectedPri) {
 		List<List<String>> priInfo = this.getPrivilegeListInfo();
 		if(expectedPri.searchBy.equals("Privilege #")&&expectedPri.searchValue.length()>0){
 			this.verifyPrivilegeSearchResult(priInfo,expectedPri.searchValue,"Privilege Number");
 		}
 		if(expectedPri.status.length()>0){
 			this.verifyPrivilegeSearchResult(priInfo, expectedPri.status, "Status");
 		}
 		if(expectedPri.licenseYear.length()>0){
 			this.verifyPrivilegeSearchResult(priInfo, expectedPri.licenseYear, "License Year");
 		}
 		if(expectedPri.code.length()>0){
 			this.verifyPrivilegeSearchResult(priInfo, expectedPri.code, "Product");
 		}
 	}
 	/**
 	 * Clear up the search criteria.
 	 */
 	public void clearUpSearchCriteria(){
 		this.selectSearchType("");
 		this.setSearchValue("");
 		this.selectDateType("");
 		ajax.waitLoading();
 		//this.setFromDate("");
 		//this.setToDate("");
 		this.setProductCode("");
 		this.selectPrivilegeStatus("");
 		this.setLicenseYear("");
 		this.setZipCode("");
 		this.selectState("");
 		ajax.waitLoading();
 		this.selectCountry("");
 		ajax.waitLoading();
 		this.selectCounty("");
 		this.selectResidencyStatus("");
 	}
}
