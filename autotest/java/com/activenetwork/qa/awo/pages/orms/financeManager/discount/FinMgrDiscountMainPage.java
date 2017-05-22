/*
 * Created on Sep 27, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.discount;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.DiscountData;
import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao
 * You can access this page by selecting 'Discount' from top menu in Finance Manager 
 *
 */
public class FinMgrDiscountMainPage extends FinanceManagerPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrDiscountMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrDiscountMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrDiscountMainPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new FinMgrDiscountMainPage();
		}

		return _instance;
	}


	/** Determine if the FieldManager Order Summary page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add New");
	}

	/**Click Active*/
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	/**Click Deactive*/
	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/**Click GO button*/
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search", false));//Shane: changed to search from 3.05 build
	}

	/**Click Add New button*/
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/**
	 * Click number of discount schedules
	 * if there no schedule for current discount, the link text is 'Add'
	 * else the link text is number of schedules
	 */
	public void clickNumOfSchedules() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Add)$|^(\\d+)$", false));
	}
	
	/**Click Search Type drop down and select one item */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "searchBy", searchType);
	}

	/**
	 * Set search value
	 * @param value
	 */
	public void setSearchValue(String value) {
		browser.setTextField(".id", "searchByValue", value);
	}

	/**
	 * Select Status---Active or Inactive
	 * @param type
	 */
	public void selectStatus(String type) {
		browser.selectDropdownList(".id", "status", type);
	}

	/**Don't select status---NUll*/
	public void deselectStatus() {
		browser.selectDropdownList(".id", "status", 0);
	}

	/**
	 * Select Rate Type
	 * @param type--Flat, Override,Percent
	 */
	public void selectRateType(String type) {
		browser.selectDropdownList(".id", "rateType", type);
	}
	
	/**Don't select any rate type*/
	public void deselectRateType() {
		browser.selectDropdownList(".id", "rateType", 0);
	}

	/**
	 * Select Fee Type 
	 * @param type--Attribute fee, Use Fee, Transaction, Pos Fee
	 */
	public void selectFeeType(String type) {
		browser.selectDropdownList(".id", "feeType", type);
	}

	/**Don't select any fee type*/
	public void deselectFeeType() {
		browser.selectDropdownList(".id", "feeType", 0);
	}

	/**
	 * Select rate apply
	 * @param type---Per Stay, per Unit of stay
	 */
	public void selectRateApply(String type) {
		browser.selectDropdownList(".id", "unitType", type);
	}

	/**Don't select any rate applied*/
	public void deselectRateApply() {
		browser.selectDropdownList(".id", "unitType", 0);
	}

	/**
	 * Select additional discount
	 * @param value---Yes or No
	 */
	public void selectAdditionalDiscount(String value) {
		browser.selectDropdownList(".id", "additional", value);
	}

	/**Don't select any additional discount item*/
	public void deselectAdditionalDiscount() {
		browser.selectDropdownList(".id", "additional", 0);
	}

	/**
	 * click 'Automatic Discount' drop down and select one item
	 * @param value--Yes or No
	 */
	public void selectAutomaticDiscount(String value) {
		browser.selectDropdownList(".id", "automaticDiscount", value);
	}

	/**Don't select any automatic discount*/
	public void deselectAutomaticDiscount() {
		browser.selectDropdownList(".id", "automaticDiscount", 0);
	}

	/**
	 * Click "Display Discount" drop down and select one item
	 * @param value---Yes or No
	 */
	public void selectDisplayDiscount(String value) {
		browser.selectDropdownList(".id", "displayDiscount", value);
	}

	/**Don't select any display discount item*/
	public void deselectDisplayDiscount() {
		browser.selectDropdownList(".id", "displayDiscount", 0);
	}

	/**
	 * Click "PromoCode" drop down and select one item
	 * @param value--With Or Without
	 */
	public void selectPromoCode(String value) {
		browser.selectDropdownList(".id", "withPromoCode", value);
	}

	/**Don't select any rpomocode item*/
	public void deselectPromoCode() {
		browser.selectDropdownList(".id", "withPromoCode", 0);
	}

	/**
	 * Click Schedule drop down and select one item
	 * @param value---With Or without
	 */
	public void selectSchedules(String value) {
		browser.selectDropdownList(".id", "withSchedules", value);
	}

	/**Deselect any schedules item*/
	public void deselectSchedules() {
		browser.selectDropdownList(".id", "withSchedules", 0);
	}

	/**Go to discount schedule Page by click "discount schedules" tab*/
	public void gotoDiscountSchPg() {
		browser.clickGuiObject(".class", "Html.A", ".text","Discount Schedules");
		waitLoading();
	}

	/**
	 * This method used to clear all search criteria
	 *
	 */
	public void clearAllSearchCriteria()
	{
	  	setSearchValue("");
	  	deselectStatus();
	  	deselectRateType();
	  	deselectFeeType();
	  	deselectRateApply();
	  	deselectAdditionalDiscount();
	  	deselectAutomaticDiscount();
	  	deselectDisplayDiscount();
	  	deselectPromoCode();
	  	deselectSchedules();
	}
	
	/**
	 * This method used to search Discount without input any criteria
	 *
	 */
	public void searchDiscountWithoutAnyCriteria() {
	  	logger.info("Search Discount Without Any Criteria.");
	  	
	  	clearAllSearchCriteria();
	  	clickGo();
	  	waitLoading();
	  	RegularExpression rex = new RegularExpression( "^Discount Name Active Rate Type.*", false );
	  	IHtmlObject[]  objs = browser.getTableTestObject(".text",rex);
	  	int num = ((IHtmlTable)objs[0]).rowCount();
	  	Browser.unregister(objs);
	  	if(num<2)
	  	{
	  	  throw new ItemNotFoundException("No Any Discount Found!");
	  	}
	}
	/**
	 * Go to discount detail page by discount name
	 * @param discName
	 */
	public void gotoDiscountDetailPg(String discName) {
		browser.clickGuiObject(".class", "Html.A", ".text", discName);
	}

	/**
	 * Search discount by discount name
	 * @param distName
	 */
	public void searchByDistName(String distName) {
	  	setSearchValue(distName);
	  	selectSearchType("Discount Name");
	  	clickGo();
	  	waitLoading();
	}
	
	public void selectFirstDiscountCheckBox()
	{
	  	browser.selectCheckBox(".id","row_0");
	}
	
	/**
	 * This method used to change specific discount status to given status
	 *
	 */
	  public void changeDiscountStatus(String distName,String status) {
	    logger.info("Start to change discount Status to "+status);
	    
	    searchByDistName(distName);
	    selectFirstDiscountCheckBox();
	    if(status.equalsIgnoreCase("Inactive")){
	      clickDeactive();
	    }else if(status.equalsIgnoreCase("Active")){
	      clickActive();
	    }else{
	  	  throw new ItemNotFoundException("Unkown Status "+status);
	    }
	    waitLoading();
	  }
	
  /**
   * Verify discount status equals given status
   * @param distName
   * @param status
   */
	 public void verifyStatus(String distName,String status) {
	   	logger.info("Start to verify discount "+distName+" status is "+status);
	   	
	   	searchByDistName(distName);
	   	String isActive = "No";
	   	if(status.equalsIgnoreCase("Inactive")){
	      isActive = "No";
	    }else if(status.equalsIgnoreCase("Active")){
	      isActive = "Yes";
	    }else{
	  	  throw new ItemNotFoundException("Unkown Status "+status);
	    }
	   	verifyDiscountInfo("Active",isActive);
	 }
	 
    /**
     * Verify specific column value is the same with given value
     * @param colName column Name
     * @param value
     */
    public void verifyDiscountInfo(String colName, String value ) {
      int colNum = getColNum( colName );
      RegularExpression rex = new RegularExpression( "Discount Name Active Rate Type.*", false );
      IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
      IHtmlTable tableGrid = (IHtmlTable)objs[0];
      if(tableGrid.rowCount()>1){
      	for ( int i = 1; i < tableGrid.rowCount(); i++ ) {
      	  if ( null != tableGrid.getCellValue( i, colNum ) ) {
      	    if ( !tableGrid.getCellValue( i, colNum ).trim().equals( value ) ) {
      	      Browser.unregister( objs );
      	      throw new ItemNotFoundException( tableGrid.getCellValue( i, colNum ) + " is different with given value " + value );
          	}
          }
      	}
      }else{
        Browser.unregister( objs );
        throw new ItemNotFoundException("No Discount Found.");
      }
      Browser.unregister( objs );
  	}

  /**
   * Get Column Number by Column Name
   * @param colName
   * @return Column Number
   */
  public int getColNum( String colName ) {
    RegularExpression rex = new RegularExpression( "Discount Name Active Rate Type.*", false );
    IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
    if ( null != objs && objs.length > 0 ) {
    	 IHtmlTable tableGrid = (IHtmlTable)objs[0];
      int colCounts = tableGrid.columnCount();
      for ( int i = 0; i < colCounts; i++ ) {
        if ( tableGrid.getCellValue( 0, i ).equalsIgnoreCase( colName ) ) {
          Browser.unregister( objs );
          return i;
        }
      }
    }
    Browser.unregister( objs );
    return -1;
  }
  
  /**
	 * verify whether or not the special discount is active status.
	 * @param discountName -discount Name
	 * @return true - active; false - inactive
	 */
	public boolean isDiscountActive(String discountName) {
	  	boolean toReturn=false;
	  	String status = "";
	  	IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^( )?Discount Name( )?Active.*",false));
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

	public boolean checkExist(String discountName) {
		waitLoading();
		searchByDistName(discountName);
		waitExists(OrmsConstants.VERY_LONG_SLEEP + 300);	//ms
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Discount Name Active.*",false));
		IHtmlTable tableGrid = (IHtmlTable)objs[0];
		List<String> values = tableGrid.getColumnValues(1);	// Discount name column
		return values.contains(discountName);
	}
	
	public void waitExists(int timeout) {
		browser.waitExists(timeout);
	}
	 
	 /**
	  * Verify discount info after added.
	  * @param discount
	  * @return
	  */
	 public boolean verifyDiscountInfo(DiscountData discount){
		 this.searchByDistName(discount.name);
	     RegularExpression rex = new RegularExpression( "Discount Name Active Rate Type.*", false );
	     IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
	     IHtmlTable table = (IHtmlTable)objs[0];
        
	     if(table.rowCount()<1){
	    	 throw new ItemNotFoundException("Can't find discount table.");
	     }
	     int row = table.findRow(1, discount.name);
	     if(row < 0){
	    	 throw new ItemNotFoundException("Can't find discount by given name:"+discount.name);
	     }
	     List<String> actualDiscountInfo = table.getRowValues(row);
	     Browser.unregister(objs);
	     String addtionalDis = "No";
	     String automatic = "No";
	     String displayDis = "No";
	     for(int i=0; i< discount.behaviors.size(); i++){
	    	 String behavior = discount.behaviors.get(i);
	   	 	 if(behavior.equalsIgnoreCase("Addtional Discount")){
	   	 		addtionalDis = "Yes";
	     	 }else if(behavior.equalsIgnoreCase("Automatic Discount")){
	     		automatic = "Yes";
	     	 }else if(behavior.equalsIgnoreCase("Display Discount")){
	     		displayDis = "Yes";
	     	 }
	     }
	     String expectFeeType = "";
	     for(String feeType:discount.feeTypes){
	    	 expectFeeType = expectFeeType.concat(feeType+",");
	     }
	     expectFeeType = expectFeeType.substring(0, expectFeeType.length()-1);
	     
	     boolean result = true;
	     result &= MiscFunctions.compareResult("Discount Name", discount.name, actualDiscountInfo.get(1));
	     result &= MiscFunctions.compareResult("Status", "Yes", actualDiscountInfo.get(2));
	     result &= MiscFunctions.compareResult("Rate Type", discount.rateType, actualDiscountInfo.get(3));
	     //result &= MiscFunctions.compareResult("Fee Types", expectFeeType, actualDiscountInfo.get(4));
	     //result &= MiscFunctions.compareResult("Use/Attribute Fee Rates Applies", discount.rateUnit, actualDiscountInfo.get(5));
	     result &= MiscFunctions.compareResult("Additional Discount", addtionalDis, actualDiscountInfo.get(6));
	     result &= MiscFunctions.compareResult("Automatic", automatic, actualDiscountInfo.get(7));
	     result &= MiscFunctions.compareResult("Display", displayDis, actualDiscountInfo.get(8));
	     result &= MiscFunctions.compareResult("Promo Codes", discount.promoCode, actualDiscountInfo.get(10));
	     return result;
	 }
	 
	 public List<String> getAllOptionsForBuyXGetYDisctDropDownList() {
		 return browser.getDropdownElements(".id", "bogo");
	 }
	 
	 public void selectBuyXGetYDisct(String indicator) {
		 browser.selectDropdownList(".id", "bogo", indicator);
	 }
	 
	 public List<String> getAllValuesByColName(String colName) {
		 List<String> values = new ArrayList<String>();
		 IHtmlObject[] listTab = browser.getTableTestObject(".className", "searchResult", ".text", new RegularExpression("^Discount Name.*", false));
		 if(listTab.length<1)
			 throw new ItemNotFoundException("Could not get list table on page.");
		 IHtmlTable tab = (IHtmlTable)listTab[0];
		 values.addAll(tab.getColumnValues(tab.findColumn(0, colName)));
		 values.remove(0);//remove 0th row value
		 Browser.unregister(listTab);
		 return values;
	 }
}

