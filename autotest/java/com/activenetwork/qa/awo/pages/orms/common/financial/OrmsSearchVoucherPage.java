/*
 * Created on Dec 4, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsSearchVoucherPage extends OrmsPage
{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSearchVoucherPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSearchVoucherPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSearchVoucherPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSearchVoucherPage();
		}

		return _instance;
	}
	/** 
	 * Determine if the page object exists 
	 */
  public boolean exists() {
    return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Search");
  }
  
  /**
	 * Select Search Type from drop down list
	 * @param type Search Type
	 */
  public void selectSearchType(String searchType)
  {
    	browser.selectDropdownList(".id", "VoucherSearchRequest.searchBy", searchType);
  }
  /**
	* Input search value
	* @param value 
	*/
  public void setSearchValue(String value)
  {
    browser.setTextField(".id", "VoucherSearchRequest.searchValue", value);
  }
  
  /**
   * Select search date Type from drop down list
   * @param dateType
   */
  public void selectDateType(String dateType)
  {
    	browser.selectDropdownList(".id","VoucherSearchRequest.searchByDate",dateType);
  }
  /**
   * Input from date value
   * @param fromDate
   */
  public void setFromDate(String fromDate)
  {
    	browser.setTextField(".id","VoucherSearchRequest.fromDate_ForDisplay",fromDate);
  }
  /**
   * Input to Date value
   * @param toDate
   */
  public void setToDate(String toDate)
  {
    	browser.setTextField(".id","VoucherSearchRequest.toDate_ForDisplay",toDate);
  }
  /**
   * Select voucher status from drop down list
   * @param status
   */
  public void selectStatus(String status)
  {
    	browser.selectDropdownList(".id","VoucherSearchRequest.status",status);
  }
  /**
   * Click Go Button
   *
   */
  public void clickGo()
  {
    	browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false));
  }
  /**
   * Search Voucher by  Voucher Id
   * @param voucherId
   */
  public void searchVoucherByVoucherId(String voucherId)
  {
    	selectSearchType("Voucher ID");
    	setSearchValue(voucherId);
    	clickGo();
    	waitLoading();
  }
  
  /**
   * Search Voucher by order number 
   * @param ordNum
   */
  public void searchVoucherByOrderNum(String ordNum){
        selectSearchType("Order Number");
        setSearchValue(ordNum);
        clickGo();
        waitLoading();
  }
  
  public void selectFirstVouchers(){
	    RegularExpression rex = new RegularExpression("javascript:invokeAction\\( \"VoucherDetails.do\", \"VoucherDetail\", \"VoucherWorker\".*",false);
	    browser.clickGuiObject(".class", "Html.A", ".href", rex);
  }
  /**
   * Click specific voucher by voucher Id
   * @param voucherId
   */
  public void clickVoucher(String voucherId)
  {
    	browser.clickGuiObject(".class", "Html.A", ".text", voucherId);
  }
  
  /**
   * This method is used to setup all search criteria
   * @param voucher
   */
  public void setUpSearchCriteria(Voucher voucher)
  {
    	clearSearchCriteria();
    	if(voucher.searchType!=null&&!voucher.searchType.equals(""))
    	{
    	  	selectSearchType(voucher.searchType);
    	  	setSearchValue(voucher.searchValue);
    	}
    	
    	if(voucher.dateType!=null&&!voucher.dateType.equals(""))
    	{
    	  	selectDateType(voucher.dateType);
    	  	setFromDate(voucher.fromDate);
    	  	setToDate(voucher.toDate);
    	}
    	
    	if(voucher.voucherStatus!=null&&!voucher.voucherStatus.equals(""))
    	{
    	  	selectStatus(voucher.voucherStatus);
    	}
  }
  
  /**
   * Clear all search area to empty
   *
   */
  public void clearSearchCriteria()
  {
    	setSearchValue("");
    	selectDateType("");
    	setFromDate("");
    	setToDate("");
    	selectStatus("");
  }
  /**
   * This method used to search and verify voucher information
   * @param voucher store many search criteria
   */
  public void searchAndVerifyVoucher(Voucher voucher,boolean goNext)
  {
    	setUpSearchCriteria(voucher);
    	clickGo();
    	waitLoading();
    	verifyGivenVoucherInSearchList(voucher.voucherId);
    	gotoFirstPg();
    	if(voucher.searchType!=null)
    	{
	    	if(voucher.searchType.equalsIgnoreCase("Created Location"))
	      	{
	      	  	verifyVoucherInfo("Created Location",voucher.searchValue,goNext);
	      	}else if(voucher.searchType.equalsIgnoreCase("Customer (Last Name)"))
	      	{
	      	  	verifyVoucherInfo("Customer",voucher.customer,goNext);
	      	}else if(voucher.searchType.equalsIgnoreCase("Order Number"))
	      	{
	      	  	verifyVoucherInfo("Order",voucher.searchValue,goNext);
	      	}else if(voucher.searchType.equalsIgnoreCase("Payment ID"))
	      	{
	      	  	verifyVoucherInfo("Payment ID",voucher.searchValue,goNext);
	      	}else if(voucher.searchType.equalsIgnoreCase("Voucher ID"))
	      	{
	    	  		verifyVoucherInfo("Voucher ID",voucher.searchValue,goNext);
	      	}else if(voucher.searchType.equalsIgnoreCase("Voucher Program Name"))
	      	{
	      	  	verifyVoucherInfo("Voucher Program Name",voucher.searchValue,goNext);
	      	}
    	}
    	
    	
    	if(voucher.dateType!=null&&!voucher.dateType.equals(""))
    	{
    	  	if(voucher.dateType.equalsIgnoreCase("Created Date"))
    	  	{
    	  	  	verifyDateInGivenRange("Created Date",voucher.fromDate,voucher.toDate);
    	  	}else if(voucher.dateType.equalsIgnoreCase("Refunded Date"))
    	  	{
    	  	  	verifyDateInGivenRange("Refunded Date",voucher.fromDate,voucher.toDate);
    	  	}else if(voucher.dateType.equalsIgnoreCase("Voided Date"))
    	  	{
    	  	  	verifyDateInGivenRange("Voided Date",voucher.fromDate,voucher.toDate);
    	  	}
    	}
    	
    	if(voucher.voucherStatus!=null&&!voucher.voucherStatus.equals(""))
    	{
    	  	verifyVoucherInfo("Status",voucher.voucherStatus,goNext);
    	}
  }
  
  /**
   * This method is used to verfiy given column date value is in the given date range
   * @param colName
   * @param fromDate
   * @param toDate
   */
  public void verifyDateInGivenRange(String colName,String fromDate,String toDate)
  {
    int colNum = getColNum(colName);
	RegularExpression rex = new RegularExpression("Voucher ID Status Customer.*", false);
	IHtmlObject[] objs = null;
	do {
	  	objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
	  	IHtmlTable tableGrid = (IHtmlTable) objs[0];
		for (int i = 1; i < tableGrid.rowCount(); i++) {
			if (null != tableGrid.getCellValue(i, colNum)) {
				String date = DateFunctions.formatDate(tableGrid.getCellValue(i, colNum).toString(),"M/d/yyyy");
				int value = DateFunctions.compareDates(fromDate,date)+DateFunctions.compareDates(toDate,date);
				if(value==2||value==-2)
				{
				  	Browser.unregister(objs);
				  	logger.error("Date "+date+" is not in the given date range!");
				  	throw new ItemNotFoundException("Date "+date+" is not in the given date range!");
				}
			}
		}
	} while (gotoNext());

	Browser.unregister(objs);
  }
  
  /**
   * This method is used to verify specific column value equals given value
   * @param colName
   * @param value
   */
  public void verifyVoucherInfo(String colName,String value,boolean goNext)
  {
    	int colNum = getColNum(colName);
    	RegularExpression rex = new RegularExpression("Voucher ID Status Customer.*", false);
    	IHtmlObject[] objs = null;
    	do {
    	  	objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
    	  	IHtmlTable tableGrid = (IHtmlTable) objs[0];
			for (int i = 1; i < tableGrid.rowCount(); i++) {
			
				if (null != tableGrid.getCellValue(i, colNum)) {
					if (!tableGrid.getCellValue(i, colNum).toString().trim().equals(value)) {
						Browser.unregister(objs);
						logger.error("Voucher Info " + tableGrid.getCellValue(i, colNum) + " is not Correct! ");
						throw new ItemNotFoundException("Voucher Info " + tableGrid.getCellValue(i, colNum) + " is not Correct! ");
					}
				}
			}
		} while (goNext&&gotoNext());
	
		Browser.unregister(objs);
    	
  }
  /**
   * Verify given voucher Id in the search List
   * @param voucherId
   */
  public void verifyGivenVoucherInSearchList(String voucherId)
  {
    	logger.info("Verify Given Voucher "+voucherId+" In Search List.");
    	
	    RegularExpression rex = new RegularExpression("Voucher ID Status Customer.*", false);
	    IHtmlObject[] objs = null;
	    boolean foundVoucher = false;
	    do {
	      objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
	      IHtmlTable tableGrid = (IHtmlTable) objs[0];
	      for (int i = 1; i < tableGrid.rowCount(); i++) {
			if (tableGrid.getCellValue(i, 0).toString().equals(voucherId)) {
			  	foundVoucher = true;
				break;
			}
	      }
	      if (foundVoucher) {
		  	logger.info("Voucher "+voucherId+" is in the search result!");
			break;
	      }
	      if (!hasNext() && !foundVoucher) {
			Browser.unregister(objs);
			throw new ItemNotFoundException("Search Voucher Fail!");
	      }
	} while (gotoNext());
	
	Browser.unregister(objs);
  }
  
  /**
   * This method is used to goto first page
   *
   */
  public void gotoFirstPg()
  {
	  IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "First");
	  if (objs.length > 0) {
		//MiscFunctions.clickObject(objs[0]);
		  objs[0].click();
	  }
	  Browser.unregister(objs);

	  this.waitLoading();
  }
  /**
	* Check whether there have next page,if have,click Next Button
	* @return
	*/
  public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next");
		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			//MiscFunctions.clickObject(objs[0]);
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

  public boolean hasNext()
  {
    IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", "Next");
	boolean toReturn = false;
	if (objs.length > 0) {
		toReturn = true;
	}
	Browser.unregister(objs);

	return toReturn;
  }
  
 /**
  * Get Column Number by Column Name
  * @param colName
  * @return Column Number
  */
  public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(
				"Voucher ID Status Customer.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			IHtmlTable tableGrid = (IHtmlTable) objs[0];
					//.getTestData("grid");
			int colCounts = tableGrid.columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (tableGrid.getCellValue(0, i).toString().equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}
  
  /**
   * Click Voucher Programes Tab
   *
   */
  public void clickVoucherProgramTab()
  {
    	browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Voucher Programs", false), true);
  }
  
  public String getStatusForFirstVoucher(){
	  IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".className","searchResult");
	  IHtmlTable table = (IHtmlTable)objs[0];
	  String status = table.getCellValue(1, 1);
	  return status;
  }
}
