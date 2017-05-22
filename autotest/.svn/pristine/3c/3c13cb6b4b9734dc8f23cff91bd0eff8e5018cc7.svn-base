/*
 * $Id: FinMgrTaxMainPage.java 6480 2008-11-03 20:23:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.orms.financeManager.tax;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author CGuo
 */
public class FinMgrTaxMainPage extends FinanceManagerPage {

	/**
	 * Script Name   : FinMgrTaxMainPage
	 * Generated     : Dec 22, 2004 2:06:48 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/12/22
	 */
  
  	/** A handle to the unique Singleton instance. */
	private static FinMgrTaxMainPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	public static FinMgrTaxMainPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrTaxMainPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrTaxMainPage() {
	}

	/** Check this page is exists */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add New");
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "taxListView_LIST");
	}
	
	/** Click add new tax button */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}
	
	/** Click go Button */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/** Click the First tax */
	public void clickFirstTax() {
		browser.clickGuiObject(".id", "TaxTypeView.name");
	}
	
	/**
	 * Click the tax we wanted
	 * @param TaxName 
	 */
	public void clickTax(String TaxName) {
		browser.clickGuiObject(".id", "TaxTypeView.name", ".text", TaxName);
	}
	
	/**
	 * Search tax by tax name
	 * @param taxName
	 */
	public void searchByTaxName(String taxName) {
	  	searchItem("Tax Name", taxName);
	  	clickGo();
	  	ajax.waitLoading();
	  	browser.waitExists();
	}
	
	public void selectTaxes() {
		//Link_Taxs().click();	
	}

	/** Click Tax Schedule Tab */
	public void clickTaxSchedule() {
//		browser.clickGuiObject(".class", "Html.A", ".text", "Tax Schedules");
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Tax Schedules");//Quentin[20131015] 3.05 UI changes
	}

	/** Click add/num of tax Schedule */
	public void clicknumberOfSchedules() {
		browser.clickGuiObject(".class", "Html.A", ".id", "TaxTypeView.numberOfSchedules");
	}
	
    /** search item */
	public void searchItem(String searchItem, String searchValue) throws PageNotFoundException {
		browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Activate");//added by pzhu
		browser.selectDropdownList(".id", "TaxTypeSearchCriteria.searchBy", searchItem);
		ajax.waitLoading();
		browser.setTextField(".id", "TaxTypeSearchCriteria.searchByValue", searchValue);
	}
	
	/**
	 * search tax by tax name
	 * @param searchItem
	 * @param searchValue
	 */
	public void searchTax(String searchItem,String searchValue) {
		searchItem(searchItem,searchValue);
		clickGo();
		ajax.waitLoading();
	}
	
	/**
	 * Select Search By value
	 * @param searchItem
	 */
	public void selectSearchBy(String searchItem) {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.searchBy", searchItem);
	}
	
	/**
	 * Select show status from drop down list
	 * @param status
	 */
	public void selectShowStatus(String status) {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.active", status);
	}
	
	/**
	 * Select Rate Type from drop down list
	 * @param rateType
	 */
	public void selectRateType(String rateType) {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.rateType", rateType);
	}
	
	/**
	 * Select Fee Type from drop down list
	 * @param feeType
	 */
	public void selectFeeType(String feeType) {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.feeType", feeType);
	}
	
	/** Select Schdules from drop down list  */
	public void selectSchdules(String schedule) {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.withSchedules", schedule);
	}
	
	/** This method used to clear all search criteria */
	public void clearAllSearchCriteria() {
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.searchBy", 0);
	  	setSearchValue("");
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.active", 0);
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.rateType", 0);
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.feeType", 0);
	  	browser.selectDropdownList(".id", "TaxTypeSearchCriteria.withSchedules", 0);
	  	ajax.waitLoading();
	}
	
	/** This method used to search tax without input any criteria */
	public void searchTaxWithoutCriteria() {
	  	clearAllSearchCriteria();
	  	clickGo();
	  	ajax.waitLoading();
	  	waitLoading();
	  	
	  	RegularExpression rex = new RegularExpression( "Name Active Tax Code Tax.*", false );
	  	IHtmlObject[]  objs = browser.getTableTestObject(".text", rex);
	  	IHtmlTable table = (IHtmlTable)objs[0];
	  	int rowSize = table.rowCount();
	  	Browser.unregister(objs);
	  	if(rowSize<2){
	  		throw new ItemNotFoundException("No Tax Found!");
	  	}
	}
	
	/**
	 * Input search value
	 * @param searchValue
	 */
	public void setSearchValue(String searchValue) {
	  	browser.setTextField(".id", "TaxTypeSearchCriteria.searchByValue", searchValue);
	}
	
	/** This method is used to select the first tax check box */
	public void selectFirstTaxCheckBox() {
	  	browser.selectCheckBox(".id", "TaxTypeView.id",true);
	}
	
	/** Click Activate Button */
	public void clickActivate() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}
	
	/** Click Deactivate Button */
	public void clickDeActivate() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}
	
	/**
	 * This method used to verify Activate or deactivate tax correct
	 * @param taxName
	 * @param status
	 */
	public void changeTaxStatus(String taxName, String status) {
	  	logger.info("Start to Change Tax Status!");
	  	
	  	ajax.waitLoading();//added by pzhu
	  	searchByTaxName(taxName);
	  	selectFirstTaxCheckBox();
	  	if(status.equalsIgnoreCase("Yes")){
	  		clickActivate();
	  	}else if(status.equalsIgnoreCase("No")){
	  		clickDeActivate();
	  	}else{
	  		throw new ItemNotFoundException("Unkown Status "+status);
	  	}
	  	
	  	ajax.waitLoading();
	  	browser.waitExists();
	  	searchByTaxName(taxName);
	  	verifyTaxInfo("Active",status);
	  	String log = status.equalsIgnoreCase("Yes") ? "Activate": "Deactivate";
	  	logger.info(log +" Tax "+taxName+" Success!");
	}
	/**
	 * active tax
	 * @param taxName
	 */
	public void activateTax(String taxName){
		this.changeTaxStatus(taxName, "Yes");
	}
	/**
	 * deactivate tax.
	 * @param taxName
	 */
	public void deactivateTax(String taxName){
		this.changeTaxStatus(taxName, "No");
	}
	
	/**
	 * verify whether or not the special tax is active status.
	 * @param taxName - tax name
	 * @return true - active; false - inactive
	 */
	public boolean isTaxActive() {
	  	boolean toReturn=false;
	  	String status = "";
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Name Active.*", false));
	
	  	IHtmlTable tableGrid = (IHtmlTable)objs[0];
	  	if(tableGrid.rowCount()>0){
	  		status = tableGrid.getCellValue(1, 2);
	  	}
	  	
	  	Browser.unregister(objs);
	  	if(status.equalsIgnoreCase("Yes")){
	  	  	toReturn=true;
	  	}
	  	return toReturn;
	}
	
	public boolean isTaxExists(String taxName) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", taxName);
	}
	
	/**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyTaxInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression( "Name Active Tax Code Tax.*", false );
	    IHtmlObject[] objs;// = null;
	    boolean found = false;
	    do {
	      objs = browser.getTableTestObject(".text", rex );
	      IHtmlTable table = (IHtmlTable)objs[0];
	      for ( int i = 1; i < table.rowCount(); i++ ) {

	        if ( null != table.getCellValue( i, colNum ) ) {
	          if ( table.getCellValue( i, colNum ).toString().trim().equals( value ) ) {
	        	  found = true;
	            Browser.unregister( objs );
	            
	          }
	        }
	      }
	      
	    }
	    while ( (!found)&&(gotoNext()) );
	    if(!found)
	    {
	    	logger.error( "'verifyTaxInfo'-->>Tax Info can not be found! In method " );
            throw new ItemNotFoundException( "Tax Info can not be found! " + value );	
	    }
	    Browser.unregister( objs );
	  }
	  /**
	   * Get taxes table.
	   */
	  private IHtmlObject[] getTaxesTable(){
		  RegularExpression rex = new RegularExpression( "Name Active Tax Code Tax.*", false );
		  IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
		  if(objs.length<1){
			  throw new ErrorOnPageException("No specific table exist");
		  }
//		  Browser.unregister(objs);
		  return objs;
	  }
  /**
   * Get Column Number by Column Name
   * @param colName
   * @return Column Number
   */
  public int getColNum( String colName ) {
    RegularExpression rex = new RegularExpression( "Name Active Tax Code Tax.*", false );
    IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
    if ( null != objs && objs.length > 0 ) {
      IHtmlTable table = (IHtmlTable)objs[0];
      int colCounts = table.columnCount();
      for ( int i = 0; i < colCounts; i++ ) {
        if ( table.getCellValue( 0, i ).toString().equalsIgnoreCase( colName ) ) {
          Browser.unregister( objs );
          return i;
        }
      }
    }
    Browser.unregister( objs );
    return -1;
  }
  /**
   * check tax status;
   * @param name
   * @return
   */
  public boolean checkTaxStatus(String name){
	  this.searchByTaxName(name);
	  IHtmlTable table = (IHtmlTable)this.getTaxesTable()[0];
	  if(table.rowCount()<1){
		  throw new ErrorOnPageException("No tax list found");
	  }
	  int row = table.findRow(1, name);
	  if(row<=-1){
		  throw new ErrorOnPageException("No row found");
	  }
	  String status =  table.getCellValue(row, table.findColumn(0, "Active"));
	  if(status.equalsIgnoreCase("Yes")){
		  return true;
	  }else{
		  return false;
	  }
  }
  
  /**
   * Check whether there have next page,if have,click Next Button
   * @return
   */
  public boolean gotoNext() {
    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.A", ".text", "Next" );
    boolean toReturn = false;
    if ( objs.length > 0 ) {
      toReturn = true;
      ILink link = (ILink)objs[0];
      link.click();
      this.waitLoading();
    }
    Browser.unregister( objs );
    return toReturn;
  }
  
	public void goItem(String item) throws PageNotFoundException {
		//		sleep(3);
		//		TestObject[] objVector = getMapObj();
		//		
		//		
		//		for(int i = 0 ; i < allTheTaxs.size() ; i++){
		//			TaxItem ld = (TaxItem) allTheTaxs.get(i);
		//		
		//			if(ld.taxName.equalsIgnoreCase(item)){
		//				ld.taxLink.click();
		//				break;
		//			}
		//		}		
		//		
		//		for (int i = 0; i<  objVector.length; i++ ) {
		//
		//			TestObject map = (TestObject)objVector[i];
		//
		//			map.unregister();
		//
		//		}
	}

	
}
