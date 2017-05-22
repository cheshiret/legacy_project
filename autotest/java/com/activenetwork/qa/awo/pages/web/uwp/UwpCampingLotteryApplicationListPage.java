package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo.LotteryChoice;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * You can access this page from account panel, click Lottery Applications link.
 * @author vzhao
 */
public class UwpCampingLotteryApplicationListPage extends UwpPage {

	private static UwpCampingLotteryApplicationListPage _instance = null;

	public static UwpCampingLotteryApplicationListPage getInstance() {
		if (null == _instance)
			_instance = new UwpCampingLotteryApplicationListPage();

		return _instance;
	}

	public UwpCampingLotteryApplicationListPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "Lottery Applications",".className",new RegularExpression("items|accountside in", false)); //"accountside in");
	}
	
	/**
	 * Retrieve the lottery order status from lottery list page.
	 * @param resNum - reservation number
	 * @return - order status
	 */
	public String getLotteryOrderStatus(String resNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Lottery Applications",".className","items");
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String cellValue = "";
		int count = 0;
		for(int i=0; i<table.rowCount(); i++) {
			cellValue = table.getCellValue(i, 0);
			count++;
			if(cellValue.indexOf(resNum)!=-1) {
				break;
			}
		}
		
		if(count==table.rowCount()+1) {
			throw new ItemNotFoundException("Order "+resNum+" not found!");
		} else {
			cellValue = cellValue.replaceAll(resNum, "").replace("See Details", "").trim();
		}
		
		Browser.unregister(objs);
		return cellValue;
	}
	
	/**
	 * Verify whether or not the lottery order exists in Current Reservations page.
	 * @param resNum - lottery order number
	 * @return - true - lottery order exists; false - lottery order not found
	 */
	public boolean verifyOrderexists(String resNum){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Lottery Applications",".className","items");
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean toReturn = false;
		
		String cellValue = "";
		int count = 0;
		for(int i=0; i<table.rowCount(); i++) {
			cellValue = table.getCellValue(i, 0);
			count++;
			if(cellValue.indexOf(resNum)!=-1) {
				toReturn = true;
				break;
			}
		}
		
		if(count==table.rowCount()+1) {
			throw new ItemNotFoundException("Order "+resNum+" not found!");
		}
		
		Browser.unregister(objs);
		return toReturn;
	}
	
	public void clickSeeDetails(String appNum, String contractCode) {
		RegularExpression reg = new RegularExpression("(.*reservationNumber=" + appNum + "&contractCode=" + contractCode + "&.*)|(.*contractCode=" + contractCode + "&reservationNumber=" + appNum + ".*)", false);
		browser.clickGuiObject(".text", new RegularExpression("See Detail|See Details", false), ".href", reg, true);
	}
	
	public void setReservationNumber(String resNum){
		browser.setTextField(".id", "reservationNumber", resNum);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".id", "btnfindreservation", ".text", "Search");
	}
	
	public void searchResNum(String resNum){
		this.setReservationNumber(resNum);
		this.clickSearch();
		this.resNumWaitExists(resNum);
	}
	
	public IHtmlObject[] getLotteryApplicationsListTable(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "Lottery Applications");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Lottery Applications' object can't be found.");
		}
		return objs;
	}
	
	public void resNumWaitExists(String resNum) {
		browser.searchObjectWaitExists(".class", "Html.TD", ".text", new RegularExpression("^"+resNum+".*", false));
	}

	/** Get the index of the lottery application order in the list*/
	public int getLotteryAppOrdIndex(String ordNum) {
		IHtmlObject[] objs = this.getLotteryApplicationsListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, new RegularExpression("^"+ordNum+".*", false));
		Browser.unregister(objs);
		return rowIndex;
	}
	
	/**Get the row info by the order number
	 * 0 - Application Number Status
	 * 1 - Lottery Name
	 * 2 - Preferred Choice
	 * 3 - Awarded Choice
	 * 4 - Total Fee:Balance Due
	 * @param ordNum
	 * @return
	 * @author Lesley
	 * @date Dec 17, 2013
	 */
	public List<String> getLotteryAppListTableRowValue(String ordNum) {
		IHtmlObject[] objs = this.getLotteryApplicationsListTable();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, new RegularExpression("^"+ordNum+".*", false));
		if (rowIndex < 0) {
			throw new ErrorOnPageException("Can't finf the order number=" + ordNum);
		}
		List<String> values = new ArrayList<String> ();
		for (int i = 0; i < table.columnCount(); i++) { // Application Number Status, Lottery Name, Preferred Choice, Awarded Choice, Total Fee:Balance Due
			values.add(table.getCellValue(rowIndex, i));
		}
		Browser.unregister(objs);
		return values;
	}
	
	/** Verify the ticket lottery order info in application list page. The order of the column should be:
	 * 0 - Application Number Status
	 * 1 - Lottery Name
	 * 2 - Preferred Choice
	 * 3 - Awarded Choice
	 * 4 - Total Fee:Balance Due
	 * @param ordNum
	 * @param status
	 * @param lotteryName
	 * @param ticket
	 * @author Lesley
	 * @date Dec 17, 2013
	 */
	public void verifyTicketLotteryOrderInfo(String ordNum, String status, String lotteryName, 
			LotteryChoice prefChoice, LotteryChoice awardedChoice, String fee) {
		List<String> values = this.getLotteryAppListTableRowValue(ordNum);
		boolean result = true;
		if (StringUtil.notEmpty(status)) {
			result &= MiscFunctions.containString("Order Status", values.get(0), status);
		}
		if (StringUtil.notEmpty(lotteryName)) {
			result &= MiscFunctions.compareString("Lottery name", lotteryName, values.get(1));
		}
		result &= MiscFunctions.compareString("Preferred Choice", this.generateChoiceInfo(prefChoice), values.get(2));
		result &= MiscFunctions.compareString("Awarded Choice", this.generateChoiceInfo(awardedChoice), values.get(3));
		result &= MiscFunctions.compareString("Total fee and balance due", fee, values.get(4));
		
		if (!result) {
			throw new ErrorOnPageException("The order with num=" + ordNum + " is displayed wrongly in lottery application list page! Check logger error above!");
		}
		logger.info("The order with num=" + ordNum + " is displayed correctly in lottery application list page!");
	}
	
	/** Generate choice info by the format: 
	 * Section: Group A
	 * 1- Adult, 1- Child
	 * Dec 8, 2014
	 * 07:30AM
	 */
	private String generateChoiceInfo(LotteryChoice choice) {
		String info = "";
		if (choice != null) {
			info = "Section: " + choice.tourName + " ";
			for (int i = 0; i < choice.types.length; i++) {
				if (i > 0) {
					info += ", ";
				}
				info += choice.typeNums[i] + "- " + choice.types[i];
			}
			info += " " + DateFunctions.formatDate(choice.tourDate, "MMM d, yyyy");
			info += " " + choice.tourTime;
		}
		return info;
	}
}
