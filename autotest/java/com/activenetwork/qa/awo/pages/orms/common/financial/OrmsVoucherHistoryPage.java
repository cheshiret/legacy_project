/*
 * Created on Dec 8, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher.VoucherHistory;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
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
public class OrmsVoucherHistoryPage extends OrmsPage
{
  /**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsVoucherHistoryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsVoucherHistoryPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsVoucherHistoryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsVoucherHistoryPage();
		}

		return _instance;
	}
	/** 
	 * Determine if the page object exists 
	 */
	public boolean exists() {
	  return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Return to Voucher Details");
	}
	
	/**
	 * This method used to verify Voucher history information
	 * @param trans
	 * @param balance
	 * @param location
	 * @param user
	 */
	public void verifyVoucherHistory(String[] trans,String[] balance,String location,String user,String orderNum)
	{
	  	RegularExpression rex = new RegularExpression("^Date & Time Transaction Information.*", false);
		IHtmlObject[] objs =  browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		
		if(!getOrderNum().equals(orderNum))
		{
		  	logger.error("Order Number "+getOrderNum()+" not Correct!");
	  	  	throw new ItemNotFoundException("Order Number should be "+orderNum);
		}
		
		for(int i = 1; i < tableGrid.rowCount(); i++)
		{
		  	if(!tableGrid.getCellValue(i,1).toString().equalsIgnoreCase(trans[i-1]))
		  	{
		  	  	logger.error("Transaction "+tableGrid.getCellValue(i,1).toString()+" not Correct!");
		  	  	throw new ItemNotFoundException("Transaction "+tableGrid.getCellValue(i,1).toString()+" not Correct!");
		  	}
		  	if(!tableGrid.getCellValue(i,3).toString().equalsIgnoreCase(balance[i-1]))
		  	{
		  	  	logger.error("Balance "+tableGrid.getCellValue(i,3).toString()+" not Correct!");
		  	  	throw new ItemNotFoundException("Balance "+tableGrid.getCellValue(i,3).toString()+" not Correct!");
		  	}
		  	if(!tableGrid.getCellValue(i,4).toString().equalsIgnoreCase(location))
		  	{
		  	  	logger.error("Transaction Location "+tableGrid.getCellValue(i,4).toString()+" not Correct!");
		  	  	throw new ItemNotFoundException("Transaction Location "+tableGrid.getCellValue(i,4).toString()+" not Correct!");
		  	}
		  	if(!tableGrid.getCellValue(i,5).toString().equalsIgnoreCase(user))
		  	{
		  	  	logger.error("User "+tableGrid.getCellValue(i,5).toString()+" not Correct!");
		  	  	throw new ItemNotFoundException("User "+tableGrid.getCellValue(i,5).toString()+" not Correct!");
		  	}
		}
		Browser.unregister(objs);
		
	}
	
	/**
	 * This method is used to get order number
	 * @return order number
	 */
	public String getOrderNum()
	{
	  	String orderNum = "";
	  	RegularExpression rex = new RegularExpression("\\d-\\d+", false);
	  	IHtmlObject[] objs = browser.getHtmlObject(".class","Html.A",".text",rex);
	  	orderNum = objs[0].getProperty(".text").toString();
	  	Browser.unregister(objs);
	  	
	  	return orderNum;
	}
	
	/**
	 * Click Return to Details page
	 *
	 */
	public void clickReturnToDetailPg()
	{
	  	browser.clickGuiObject(".class","Html.A",".text","Return to Voucher Details");
	}
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<VoucherHistory> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<VoucherHistory> records = new ArrayList<VoucherHistory>();
		int rows;
		int columns;
		VoucherHistory info;
	  	RegularExpression rex = new RegularExpression("^Date & Time Transaction Information.*", false);
		
		
		
		objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find voucher history table object.");
				}
		
		table = (IHtmlTable)objs[0];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page OrmsVoucherHistoryPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new VoucherHistory();
			info.dateTime = table.getCellValue(i, table.findColumn(0, "Date & Time"));
			info.transaction = table.getCellValue(i, table.findColumn(0, "Transaction"));
			info.info = table.getCellValue(i, table.findColumn(0, "Information at time of transaction"));
			info.voucherBalance = table.getCellValue(i, table.findColumn(0, "Voucher Balance"));
			info.transLocation = table.getCellValue(i, table.findColumn(0, "Transaction Location"));
			info.user = table.getCellValue(i, table.findColumn(0, "User"));	
			
			
			records.add(info);			
			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
}
