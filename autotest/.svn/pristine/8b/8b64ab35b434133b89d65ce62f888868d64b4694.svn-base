package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo.ReservationHistory;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author bzhang
 * @since 2010/04/01
 *
 */
public class OrmsTicketOrderHistoryPage extends OrmsPage{
	
	private static OrmsTicketOrderHistoryPage _instance = null;

	private OrmsTicketOrderHistoryPage() {
	}

	public static OrmsTicketOrderHistoryPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsTicketOrderHistoryPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
				new RegularExpression("^Date.*Information at time of Transaction.*", false));
	}
	
	/**
	 * Get order number from ticket order history page
	 * @return
	 */
	public String getOrderNumber(){
		return getStringFromTable("Order #","Order #","Order Status");
	}
	
	/**
	 * Get Tour Name
	 * @return
	 */
	public String getTourName(){
		String tourName = "";
		//To do
		return tourName;			
	}
	

	/**
	 * Get Tour Inventory section Order Item Status
	 * @return
	 */
	public String getOrderItemStatus(){
		String odItemStatus = "";
		//To do
		return odItemStatus;			
	}
	
	 /**
	 * Get Tour Inventory section Tour Date
	 * @return
	 */
	public String getTourDate(){
		String tourDate = "";
		//To do
		return tourDate;
	}
	
	/**
	 * Get Tour Inventory section Tour Time
	 * @return
	 */
	public String getTourTime(){
		String tourTime ="";
		//To do
		return tourTime;
	}
	
	/**
	 * Get Tour Inventory section Ticket Status
	 * @return
	 */
	public String getTicketStatus(){
		String ticketStatus = "";
		//To do
		return ticketStatus;		
	}
	
	/**
	 * Get Tour Inventory section Tour Status
	 * @return
	 */
	public String getTourStatus(){
		String tourStatus = "";
		//To do
		return tourStatus;
	}

	/**
	 * Get ticket order history transaction date/time
	 * @return
	 */
	public String getTicketOrderTraDateTime(){
		String traDateTime = "";
		//To do
		return traDateTime;
	}
	
	/**
	 * Get ticket order history transaction type
	 * @return
	 */
	public String getTicketOrderTranType(){
		String traType = "";
		//To do
		return traType;
	}
	
	/**
	 * Get ticket order history Trans.Occ.
	 * @return
	 */
	public String getTicketOrderTranOcc(){
		String traOcc = "";
		//To do
		return traOcc;
	}
	
	/**
	 * Get ticket order history information at time of transaction.
	 * @return
	 */
	public String getTicketOrderHistoryInfo(){
		String traInfo = "";
		//To do
		return traInfo;
	}
	
	/**
	 * Get ticket order history transaction location.
	 * @return
	 */
	public String getTicketOrderTraLoc(){
		String traLoc = "";
		//To do
		return traLoc;
	}

	/**
	 * Click Return to Order Detail
	 */
	public void clickReturnToOrderDetail(){
		browser.clickGuiObject(".class", "Html.A",".text","Return to Order Detail");
	}
	
	public void clcikCancel(){
		browser.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
	/**
	 * Get required string from table text
	 * @param tableBeginText
	 * @param beforeText
	 * @param endText
	 * @return
	 */
	private String getStringFromTable(String tableBeginText,String beforeText,String endText){
		String tableText ="";
		String reqString = "";
		IHtmlObject[] orderTableinfo = browser.getTableTestObject(".class", "Html.TABLE", ".text",new RegularExpression("^"+tableBeginText+".*", false));
		tableText = orderTableinfo[0].getProperty(".text").toString();
		reqString = tableText.split(beforeText)[1].split(endText)[0].trim();
		Browser.unregister(orderTableinfo);
		return reqString;
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
		
		table = (IHtmlTable)objs[0];
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
}
