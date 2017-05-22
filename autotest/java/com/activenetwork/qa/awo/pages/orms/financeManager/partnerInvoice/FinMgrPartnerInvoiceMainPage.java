/*
 * Created on Jan 25, 2010
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
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrPartnerInvoiceMainPage extends FinanceManagerPage {

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrPartnerInvoiceMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrPartnerInvoiceMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrPartnerInvoiceMainPage getInstance() {
		if (null == _instance) {
			_instance = new FinMgrPartnerInvoiceMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Partner Invoice No. Status .*", false));
	}
	
	public void selectSearchBy(String searchBy) {
	  	browser.selectDropdownList(".id", "searchBy", searchBy);
	}
	
	public void setSearchValue(String searchValue) {
	  	browser.setTextField(".id", "criteria", searchValue);
	}
	
	public void selectDateType(String dateType) {
	  	browser.selectDropdownList(".id", "daterange", dateType);
	}
	
	public void setStartDate(String startDate) {
	  	browser.setTextField(".id", "date_start_ForDisplay", startDate);
	}
	
	public void setEndDate(String endDate) {
	  	browser.setTextField(".id", "date_end_ForDisplay", endDate);
	}
	
	public void selectStatus(String status) {
	  	browser.selectDropdownList(".id", "partnerinvoicestatus", status);
	}
	
	public void selectCoverage(String coverage) {
	  	browser.selectDropdownList(".id", "coverage", coverage);
	}
	
	/**
	 * This method used to search partner invoice by invoice number
	 * @param invoiceNum
	 */
	public void searchByInvoiceNo(String invoiceNum) {
	  	this.selectSearchBy("Partner Invoice No.");
	  	this.setSearchValue(invoiceNum);
	  	clickGo();
	  	waitLoading();
	}
	
	public void clickGo() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void clickRunPartInvoiceTab() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Run Partner Invoice");
	}
	
	public void clickUndoPartnerInvoice() {
	  	browser.clickGuiObject(".class", "Html.A", ".text", "Undo Failed Partner Invoice");
	}
	
	/**
	 * Select failed partner invoice radio button
	 * @param invoiceId
	 */
	public void selectPartnerInvoiceRadio(String invoiceId) {
	  	browser.selectRadioButton(".id", "invoice_id", ".value", invoiceId);
	}
	
	/**
	 * Undo Failed Partner Invoice
	 * @param invoiceId
	 */
	public void unDoFailedInvoice(String invoiceId) {
		ConfirmDialogPage confirmDialogPg = ConfirmDialogPage.getInstance();
	  	logger.info("Undo Failed Partner Invoice " + invoiceId);
	  	
	  	selectPartnerInvoiceRadio(invoiceId);
	  	clickUndoPartnerInvoice();
	  	Object page = browser.waitExists(confirmDialogPg,this);
	  	if(page == confirmDialogPg) {
	  		confirmDialogPg.clickOK();
	  	}
	  	waitLoading();
	}
	
	public void clickInvoice(String invoiceId) {
	  	browser.clickGuiObject(".class", "Html.A", ".text", invoiceId);
	}
	
	 /**
	   * Verify specific column value is the same with given value
	   * @param colName column Name
	   * @param value
	   */
	  public void verifyInvoiceInfo( String colName, String value ) {
	    int colNum = getColNum( colName );
	    RegularExpression rex = new RegularExpression("Partner Invoice No. Status.*", false );
	    IHtmlObject[] objs = browser.getTableTestObject(".text", rex );
	    IHtmlTable tableGrid = (IHtmlTable)objs[0];
	    if(tableGrid.rowCount()>1) {
    	  if ( !tableGrid.getCellValue( 1, colNum ).toString().trim().equals( value ) ) {
    	    Browser.unregister( objs );
    	    logger.error( "Invoice Info " + tableGrid.getCellValue( 1, colNum ) + " is not Correct! " );
    	    throw new ItemNotFoundException( tableGrid.getCellValue( 1, colNum ) + " is different with given value " + value );
    	  }
	    } else {
	      Browser.unregister( objs );
	      throw new ItemNotFoundException("No Partner Invoice Info Found.");
	    }
	    Browser.unregister( objs );
	  }
	  
	  /**
	   * Get Column Number by Column Name
	   * @param colName
	   * @return Column Number
	   */
	  public int getColNum( String colName ) {
	    RegularExpression rex = new RegularExpression("Partner Invoice No. Status.*", false );
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
