/*
 * Created on Jan 26, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.partnerInvoice;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrRevenueLocationDetailPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrRevenueLocationDetailPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrRevenueLocationDetailPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrRevenueLocationDetailPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrRevenueLocationDetailPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^RA Fee Order Product Sale.*", false));
	}
	
	public void selectSearchType(String searchType) {
	  	browser.selectDropdownList(".id", "searchBy", searchType);
	}
	
	public void setSearchValue(String searchValue) {
	  	browser.setTextField(".id", "input-criteria", searchValue);
	}
	
	public void clickGo() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	/**
	 * This method used to search details info by order number
	 * @param orderNum
	 */
	public void searchByOrder(String orderNum) {
	  	selectSearchType("Order");
	  	setSearchValue(orderNum);
	  	clickGo();
	  	waitLoading();
	}
	
	/**
	 * This method used to get all invoice detail total rows number
	 * @return-total record number
	 */
	public int getTotalRecordNum() {
	  	RegularExpression rex = new RegularExpression("^RA Fee Order Product Sale Channel.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    int rows = tableGrid.rowCount();
	    Browser.unregister(objs);
	    
	    return rows;
	}
	/**
	 * This method used to check given order exists in partner invoice list
	 * @param orderNum
	 */
	public void checkGivenOrderExists(String orderNum) {
	  	if(!browser.checkHtmlObjectExists(".class", "Html.A", ".text", orderNum)){
	  	  throw new ItemNotFoundException("Run Partner Invoice Fail,not found given order "+orderNum);
	  	}
	  	logger.info("Given Order is in the partner invoice list");
	}
	
	  /**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyPartnerInvoiceInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("RA Fee Order Product Sale Channel.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    if(tableGrid.rowCount()>1){
      	  if ( !tableGrid.getCellValue( 1, colNum ).toString().trim().equals( value ) ) {
      	    Browser.unregister( objs );
      	    logger.error( "Partner Invoice Info " + tableGrid.getCellValue( 1, colNum ) + " is not Correct! " );
      	    throw new ItemNotFoundException( tableGrid.getCellValue( 1, colNum ) + " is different with given value " + value );
      	  }
	    }else{
	      Browser.unregister( objs );
	      throw new ItemNotFoundException("No Partner Invoice Info Found.");
	    }
	    Browser.unregister( objs );
	    
	    logger.info("Partner Invoice Info Correct.");
	  }
	  
	  /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression("^RA Fee Order Product Sale Channel.*", false );
	    IHtmlObject[] objs = browser.getHtmlObject( ".class", "Html.TABLE", ".text", rex );
	    if ( null != objs && objs.length > 0 ) {
	      IHtmlTable tableGrid = (IHtmlTable)objs[0];
	      int colCounts = tableGrid.columnCount();
	      for ( int i = 0; i < colCounts; i++ ) {
	        if ( tableGrid.getCellValue( 0, i ).toString().equalsIgnoreCase( colName ) ) {
	          Browser.unregister( objs );
	          return i;
	        }
	      }
	    }
	    Browser.unregister( objs );
	    return -1;
	  }
}
