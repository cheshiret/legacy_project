package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.internal.dtree.ObjectNotFoundException;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * This page is Permit Date Range Availability page in UWP. Right now the page is only shown on Rec.gov and BW sites 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 25, 2013
 */
public class UwpPermitDateRangeAvailablityPage extends UwpPermitingPage {
	private static UwpPermitDateRangeAvailablityPage _instance = null;

	public static UwpPermitDateRangeAvailablityPage getInstance() {
		if (null == _instance)
			_instance = new UwpPermitDateRangeAvailablityPage();

		return _instance;
	}

	protected UwpPermitDateRangeAvailablityPage() {
	}

	/** Elements Properties Define Begin */
	protected Property[] getCalendarAvailGridTDProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "status a");
	}
	
	protected Property[] entranceCol(String entranceCol){
		return Property.toPropertyArray(".className", "entranceCol", ".text", entranceCol);
	}
	
	protected Property[] contentArea(){
		return Property.concatPropertyArray(div(), ".className", "contentArea", ".text", new RegularExpression("^Please specify.*", false));
	}
	
	protected Property[] permitGridContainer(){
		return Property.concatPropertyArray(div(), ".id", "permitGridContainer");
	}
	
	protected Property[] permitGridContainerTable(){
		return Property.concatPropertyArray(table(), ".id", "calendar", ".className", "items");
	}
	
	protected Property[] inpagehelp(){
		return Property.concatPropertyArray(div(), ".className", "inpagehelp");
	}
	
	protected Property[] nextTwoWeeks(){
		return Property.concatPropertyArray(a(), ".id", "nextWeek", ".text", new RegularExpression("^Next 2 weeks.*", false));
	}
	
	protected Property[] previousTwoWeeks(){
		return Property.concatPropertyArray(a(), ".id", "previousWeek", ".text", new RegularExpression(".*Previous 2 weeks$", false));
	}
	
	protected Property[] status(){
		return Property.concatPropertyArray(td(), ".className", new RegularExpression("status [a-z]+", false));
	}
	
	protected Property[] trailAndPermitTypeTD(String entranceCode, String entranceName, String permitType){
		return Property.concatPropertyArray(td(), ".text", new RegularExpression(entranceCode+" "+entranceName+" ?"+permitType, false));
	}
	
	protected Property[] mapPinLink(){
		return Property.concatPropertyArray(img(), ".src", "/images/maps/pin_entrance.png");
	}
	
	protected Property[] trailLink(String contractCode, String parkID, String entranceID, String permitTypeID){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression(".*entranceEntryExitDetails\\.do\\?contractCode="+contractCode+"&parkId="+parkID+"&entranceId="+entranceID+"&permitTypeId="+permitTypeID+"", false));
	}
	/** Elements Properties Define End */
	
	
	/**
	 * Has availability or doesn't have, use two kinds of conditions as page park  
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(contentArea()) || browser.checkHtmlObjectExists(permitGridContainer());
	}

	/**
	 * Get prompt message objs
	 * @return
	 */
	public IHtmlObject[] getPromptMessageObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(inpagehelp());
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Prompt Message objs can't be found.");
		}

		return objs;
	}

	/**
	 * Get prompt error message displayed on the below of Date range matrix.
	 * @return
	 */
	public String getPromptMessage(){
		IHtmlObject[] objs = this.getPromptMessageObjs();
		String errorMsg = objs[0].text();
		Browser.unregister(objs);
		return errorMsg;
	}

	/**
	 * Verify prompt message
	 * @param expectedMessage
	 */
	public void verifyPrompMessage(String expectedMessage){
		String actualMessage = this.getPromptMessage();
		if(!actualMessage.matches(expectedMessage)){
			throw new ErrorOnDataException("Prompt message:"+actualMessage+" doesn't match the expected regx:"+expectedMessage);
		}else logger.info("Successfullly verify prompt message:"+actualMessage+" matches the expected regx:"+expectedMessage);
	} 

	/**
	 * Get availability inventory objs
	 * @return
	 */
	public IHtmlObject[] getAvailabilityInventoryObjs() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("^A$|A [0-9]+", false));
		if(obj.length > 0)
			return obj;

		return null;
	}

	/**
	 * Check if it has availability inventory
	 * @return
	 */
	public boolean isAvailabilityInventory(){
		IHtmlObject[] objs = this.getAvailabilityInventoryObjs();
		if(objs==null || objs.length<1){
			return false;
		}else return true;
	}

	/**
	 * Verify it has availability inventory
	 */
	public void hasAvailabilityInventory(){
		boolean result = this.isAvailabilityInventory();
		if(!result){
			throw new ErrorOnDataException("It should have availability inventory.");
		}
		logger.info("Successfully verify it has availability inventory.");
	}

	/**
	 * Get date range matrix table
	 * @return
	 */
	public IHtmlObject[] getDateRangeMatrixTable(){
		IHtmlObject[] table = browser.getTableTestObject(".id","calendar");
		if(table==null || table.length<1){
			throw new ObjectNotFoundException("Date range matrix table objects can't be found.");
		}
		return table;
	}
	
	/**
	 * get the start date for the date range matrix in the format of "Jul 1, 2011";
	 * @return
	 */
	public String getStartDateForDateRangeMatrix(){
		IHtmlObject[] table = this.getDateRangeMatrixTable();
		IHtmlTable calendarTable = (IHtmlTable)table[0];

		String month = calendarTable.getCellValue(2, 2).trim().substring(0, 3);
		String year = calendarTable.getCellValue(2, 2).trim().split(" ")[1].split("-")[0].trim();//December 2013-January 2014
		String day = calendarTable.getCellValue(3, 1).trim().split(" ")[0].trim(); //get the start date for date range matrix.

		return (month + " " + day + ", " + year);
	}
	
	/**
	 * Select a permit inventory by given index.
	 * @param index: index of inventory, start from 1
	 */
	public void selectAvailablePermitNum(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^A ?\\d*$", false), index - 1);
	}
	
	/**
	 * Get a permit inventory by given index.
	 * @param index: index of inventory, start from 1
	 * @Note sample data: A 400
	 */
	public int getAvailablePermitNum(int index){
		IHtmlObject[] table = this.getDateRangeMatrixTable();
		IHtmlTable calendarTable = (IHtmlTable)table[0];
		int number = Integer.valueOf(calendarTable.getCellValue(7, index).trim().split(" ")[1].trim());
		logger.info("The available permit number is:"+number);
		
		Browser.unregister(table);
		return number;
	}
	
	/**
	 * Get a permit inventory by given booking date.
	 * @param bookDate:
	 * @Note sample data: A 400
	 */
	public int getAvailablePermitNum(String bookDate){
		String startDate = this.getStartDateForDateRangeMatrix();
		int index = DateFunctions.daysBetween(startDate, bookDate); //bookDate>=startDate, so that index=0, 1, 2, 3, 4
		
		IHtmlObject[] table = this.getDateRangeMatrixTable();
		IHtmlTable calendarTable = (IHtmlTable)table[0];
		int number = Integer.valueOf(calendarTable.getCellValue(7, index+1).trim().split(" ")[1].trim());
		logger.info("The available permit number is:"+number);
		
		Browser.unregister(table);
		return number;
	}
	
	/**
	 * Get permit inventory button objs
	 * @return
	 */
	public IHtmlObject[] getPermitInventoryButtonObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(permitGridContainer(), status()));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Permit inventory button objects can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get the string when mouse overs permit inventory button
	 * @return
	 */
	public String getMouseOverPermitInventoryButtonInfo(){
		IHtmlObject[] objs = this.getPermitInventoryButtonObjs();
		String mouseOverString = objs[0].getProperty(".onmouseover");
		
		Browser.unregister(objs);
		return mouseOverString;
	}
	
	/**
	 * Check if availability grid quota number tool tip is shown. 
	 * @return
	 */
	public boolean isAvailGridQuotaNumTooltipExist() {
		String info = this.getMouseOverPermitInventoryButtonInfo();
		return (StringUtil.notEmpty(info) && info.contains("Available Quota"));
	}
	
	/**
	 * Verify if availability grid quota number tool tip is shown or not
	 * @param exp
	 */
	public void verifyAvailGridQuotaNumTooltipExist(boolean exp) {
		boolean actual = this.isAvailGridQuotaNumTooltipExist();
		String msg = exp ? "" : "NOT";
		if (exp != actual) {
			throw new ErrorOnPageException("Availability Grid Quota Number tooltip should " + msg + " exist on Permit Date Range Availibility page!");
		}
		logger.info("---Succesffully verify Availability Grid Quota Number tooltip " + msg + " exist on Permit Date Range Availibility page!");
	}
	
	public void clickNext2Weeks() {
		browser.clickGuiObject(nextTwoWeeks());
	}
	
	public void clickPrevious2Weeks() {
		browser.clickGuiObject(previousTwoWeeks());
	}
	
	/**
	 * @param clickNext2Weeks true: click "Next 2 week" button
	 *                        false: click "Previous 2 week" button
	 */
	public void clickNextPrevious2WeeksLink(boolean clickNext2Weeks){
		if(clickNext2Weeks){
			this.clickNext2Weeks();
		}else{
			this.clickPrevious2Weeks();
		}
		this.waitLoading();
	}
	
	public void clicktEntranceCol(String entranceCol){
		browser.clickGuiObject(entranceCol(entranceCol));
	}
	
	public List<String> getTrailAndPermitTypes(){
		IHtmlObject[] objs = browser.getTableTestObject(permitGridContainerTable());
		if(objs.length<1){
			throw new ObjectNotFoundException("Permit grid container table can't be found.");
		}
		ITable table = (ITable)objs[0];
		List<String> values = new ArrayList<String>();
		for(int i=7; i<table.rowCount(); i++){
			values.add(table.getCellValue(i, 0));
		}
		
		return values;
	}
	
	public void synTrailAndPermitTypes(String expectedValue){
		logger.info("Trail and permit types before click entrance col:"+expectedValue);
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;
		boolean isSame=false;
		Timer time = new Timer();
		String currentValue; 

		do{
			currentValue = this.getTrailAndPermitTypes().toString();
			isSame = expectedValue.equals(currentValue);
			logger.info("Value from UI:"+currentValue);
		}while(time.diffLong() < maxWaitTime && !isSame);
		if(!isSame) {
			throw new ItemNotFoundException("Syn trail and permit types timed out in "+maxWaitTime+" ms");
		}
	}
	
	public void clickTrailLink(String contractCode, String parkID, String entranceID, String permitTypeID){
		browser.clickGuiObject(trailLink(contractCode, parkID, entranceID, permitTypeID));
	}
	
	public void clickMapPinLink(String entranceCode, String entranceName, String permitType){
		browser.clickGuiObject(Property.atList(trailAndPermitTypeTD(entranceCode, entranceName, permitType), mapPinLink()));
	}
	
}
