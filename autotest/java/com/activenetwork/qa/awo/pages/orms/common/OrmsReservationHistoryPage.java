package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo.ReservationHistory;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsReservationHistoryPage extends OrmsPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsReservationHistoryPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReservationHistoryPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationHistoryPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationHistoryPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {                                             
		 return browser.checkHtmlObjectExists(".class","Html.A",".text", new RegularExpression("Return to Reservation Details",false));
	}
	
	public void clickReturnToResDetailsBtn(){
		browser.clickGuiObject(".class","Html.A",".text", new RegularExpression("Return to Reservation Details",false));
	}
	
	public String getHistoryInfo(){
		
		String history="";
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.TABLE",".text",new RegularExpression("Date/Time Transaction Type Trans.Occ. Information at time of Transaction. Transaction Location User.*",false));
		history=objs[1].text();
		Browser.unregister(objs);
		return history;
	}
	
	/***
	 * Get the "Change Collection Status" record
	 * @param preCondtion - Transaction type
	 * @param col - the column number which information you want to retrieve.
	 * @return the information of the cell
	 */
	public String getResHistoryByTransType(String transType, int col){
		String record = "";
		int toReturn = 0;
		int controller = 0;
		IHtmlObject[] objs = browser.getTableTestObject(".text",new RegularExpression("^Date/Time",true));
		IHtmlTable table = (IHtmlTable)objs[objs.length-1];
		for(int row = 0; row < table.rowCount(); row++ ){
			if(table.getCellValue(row, 1).equalsIgnoreCase(transType)) {
				controller++;
				toReturn = row;
				break;
			}
		}
		
		if(controller == 0) {
			throw new ItemNotFoundException("The given transaction type can not be found!");
		}
		
		record = table.getCellValue(toReturn, col);
		Browser.unregister(objs);
		return record;
	}
	
	
	
	
	
	/**
	 * get total all records on the page.
	 * @param
	 * @return List of records.
	 */
	public List<ReservationHistory> getAllRecordsOnPage() {
		IHtmlObject objs[] = null;
		IHtmlTable table = null;
		List<ReservationHistory> records = new ArrayList<ReservationHistory>();
		int rows;
		int columns;
		ReservationHistory info;
		RegularExpression rex = new RegularExpression("^Date/Time Transaction Type.*", false);
		
		
		objs = browser.getHtmlObject(".class", "Html.TABLE", ".text",rex);
		
		if(objs.length < 1) {
					throw new ItemNotFoundException("Can't find reservation history table object.");
				}
		
		table = (IHtmlTable)objs[1];
		rows = table.rowCount();
		columns = table.columnCount();
		logger.info("Find record on page OrmsReservationHistoryPage, "+rows+" rows, "+columns+" columns.");
		
		for(int i = 1; i < rows; i ++) {
			info = new ReservationHistory();
			info.dateTime = table.getCellValue(i, table.findColumn(0, "Date/Time"));
			info.transType = table.getCellValue(i, table.findColumn(0, "Transaction Type"));
			info.transOcc = table.getCellValue(i, table.findColumn(0, "Trans.Occ."));
			info.info = table.getCellValue(i, table.findColumn(0, "Information at time of Transaction."));
			info.transLocation = table.getCellValue(i, table.findColumn(0, "Transaction Location"));
			info.user = table.getCellValue(i, table.findColumn(0, "User"));	
						
			records.add(info);			
			
		}
		
		
		Browser.unregister(objs);
		return records;
	}
	
	public void clickVoucherLink(String transType, String voucherID)
	{
		IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD", ".text", transType);
		IHtmlObject tr = (IHtmlObject)tds[0].getParent();
		browser.clickGuiObject(".class", "Html.A", ".text", voucherID, true, 0 , tr);
		
		Browser.unregister(tds);
	}
	
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
