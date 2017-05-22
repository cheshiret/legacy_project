/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author jdu
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsSiteDetailsPage extends OrmsPage {
	public static final int STATUS_AVAILABLE=1;
	public static final int STATUS_NOT_AVAILABLE=0;

	//		public Vector allTheDays; 
	//		public final GuiTestObject pageMark = link_viewMap();	//link_validateRules();

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSiteDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSiteDetailsPage() {
		//	    	allTheDays = new Vector();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSiteDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSiteDetailsPage();
		}
		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		//			return browser.checkTestObjectExists(".class","Html.A",".text","View Map");
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",
//				"View Map View Reservations Validate Rules");
		return this.checkLastTrailValueIs("Site Detail");
	}

	/** Click the "Add Reservation to Order" link */
	public void clickAddReservToOrder() {
		//3.05 build
		browser.clickGuiObject(".class", "Html.A", ".text","OK", true);
	}

	/** Click the "Add Selected Items to Order" link */
	public void clickSelectedItemsToOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Selected Items to Order", true);
	}

	/** Click the "OK" link */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/**Get Site ID*/
	public String getSiteID() throws PageNotFoundException {
		String siteID = "";
		RegularExpression regx=new RegularExpression("((Update Display)|(View Map))",false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text", regx);
		String s = objs[0].getAttributeValue(".href");
		Browser.unregister(objs);
		//note: href text is different between FM and CM
		//Sample FM href: javascript:void(top.showMapFocusSite("BC6788AC-7C36-C3D7-FDC4-05BD1F22046E","1943"))
		//Sample CM href: javascript:void(top.showParkMap("10341","1943","01ABA115-F266-D6FC-8329-F55C21A80A0E"))
		String[] tokens = RegularExpression.getMatches(s, "(?<=(\"|\\s))\\d+(?=(\"|,))");
		if (tokens.length < 1)
			throw new PageNotFoundException("Failed to get siteID.");
		siteID = tokens[tokens.length - 1];
		siteID = siteID.replaceAll("\"", "");
		logger.info("Get siteID=" + siteID);
		return siteID;
	}

	/**Click Site name*/
	public void clickSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Sites", true);
	}

	public void clickSiteList(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Site List", true);
	}
	
	/**Click Reservations*/
	public void clickReservations() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reservations",true);
	}

	/**Click Home Link*/
	public void clickHome() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Home", true);
	}

	/** Retrieve the number of nights shown in the textbox */
	public String getNumNights() {
		return browser.getTextFieldValue(".id", "row_0_nights");
	}

	/**
	 * Get Night Number
	 * @param num
	 */
	public void setNightNum(String num) {
		browser.setTextField(".id", "row_0_nights", num ,IText.Event.LOSEFOCUS);
	}

	/**
	 * Input arrival Date
	 * @param date
	 */
	public void setArrivalDate(String date) {
		IHtmlObject[] objs = browser.getTextField(".id", "row_0_date_start_ForDisplay");
		if (objs!=null && objs.length > 0 && (objs[0]).isEnabled()) {
			((IText)objs[0]).setText(date, IText.Event.LOSEFOCUS);
		}
		Browser.unregister(objs);
	}

	/**Get Arrival Date*/
	public String getArrivalDate() {
//		return browser.getTextFieldValue(".id", "row_0_date_start_ForDisplay");
		return browser.getTextFieldValue(".id", "startDateSearch_ForDisplay");
	}

	/**Get Departure Date*/
	public void setDepartureDate(String date) {
		IHtmlObject[] objs = browser.getTextField(".id", "row_0_date_end_ForDisplay");
		if (objs!=null && objs.length > 0&& !objs[0].getProperty(".disabled").equalsIgnoreCase("false")){
			((IText)objs[0]).setText(date);
		}
		Browser.unregister(objs);
	}

	/** Add a specified site to the reservation order */
	public void selectSite(String arrivalDate, String dayNightNum) {
		if (arrivalDate != null && arrivalDate.length()>0) {
			this.setArrivalDate(arrivalDate);
		}
		if (dayNightNum != null && dayNightNum.length()>0) {
			this.setNightNum(dayNightNum);
		}
		if (dayNightNum != null && dayNightNum.length()>0) {
			this.setNightNum(dayNightNum);
		}
		clickAddReservToOrder();
	}
	
	/** Add a specified site to the reservation order but don't click the Add Reservation To Cart button*/
	public void selectSiteWithoutClickAddResToOrderButton(String arrivalDate, String dayNightNum) {
		if (arrivalDate != null && arrivalDate.length()>0) {
			this.setArrivalDate(arrivalDate);
		}
		if (dayNightNum != null && dayNightNum.length()>0) {
			this.setNightNum(dayNightNum);
		}
	}
	
	/**
	 * Input arrival date and night number, select site
	 * @param arriveDate
	 * @param nightNum
	 * @throws PageNotFoundException
	 */
	public void selectSite(String arriveDate,String departureDate, int nightNum)throws PageNotFoundException {
		selectSite(arriveDate,nightNum + "");
	}

	/**
	 * input night number and select site
	 * @param nightNum---int
	 * @throws PageNotFoundException
	 */
	public void selectSite(int nightNum) throws PageNotFoundException {
		selectSite("",nightNum + "");
	}
	
	/**
	 * input night nuber and select site
	 * @param nightNum---String
	 * @throws PageNotFoundException
	 */
	public void selectSite(String nightNum) throws PageNotFoundException {
		selectSite("", nightNum);
	}
	
	public void selectSiteWithoutClickAddResToOrderButton(String nightNum) throws PageNotFoundException {
		selectSiteWithoutClickAddResToOrderButton("", nightNum);
	}
	
	public boolean checkWeeklyMonthlyRateExist(){
		return browser.checkHtmlObjectExists(".class","Html.LABEL",".text","Weekly*")&&browser.checkHtmlObjectExists(".class","Html.LABEL",".text","Monthly**");
	}
	
	/**
	 * Check arrival date exist or not
	 * @param fromDay
	 * @param numOfStay
	 * @return
	 */
	public boolean checkAvail(String fromDay, String numOfStay) {
		return checkAvail(fromDay, Integer.parseInt(numOfStay));
	}

	/**Click "Update display"*/
	public void clickUpdateDisplay() {
		browser.clickGuiObject(".text", "Update Display", ".class", "Html.A",true);
	}

//	/**
//	 * Check site status
//	 * @param fromDay
//	 * @param numOfStay
//	 * @param statusCode - now only "Available"
//	 * @return - true for available
//	 */
//	private boolean checkStatus(String fromDay, int numOfStay, int statusCode) {
//		this.setFromDate(fromDay);
//		this.clickUpdateDisplay();
//
//		String pattern = "MMM d, yyyy";
//
//		String idPattern = DateFunctions.formatDate(fromDay, pattern);
//		StringBuffer idPatternBuffer = new StringBuffer();
//		
//
//		for (int i = 1; i < numOfStay; i++) {
//			String date = DateFunctions.getDateAfterGivenDay(fromDay, i);
//			idPatternBuffer.append("|" + DateFunctions.formatDate(date, pattern));
//		}
//		
//		idPattern = idPatternBuffer.toString();
//		System.out.println("-------------idPattern:" + idPattern);
//		RegularExpression reg = new RegularExpression(idPattern, false);
//		HtmlObject[] invs = browser.getHtmlObject(".class", "Html.A", ".id",	reg);
//		System.out.println(invs.length);
//		
//		boolean status = true;
//
//		for (int i = 0; i < invs.length && status==true; i++) {
//			HtmlObject currDay = invs[i];
//			System.out.println("status:----------------" + currDay.getProperty(".text"));
//			if (statusCode == STATUS_AVAILABLE && currDay.getProperty(".text").equals("r")) {
//				status = false;
//			} else if (statusCode == STATUS_NOT_AVAILABLE && !currDay.getProperty(".text").equals("r")) {
//				status = false;
//			}
//		}
//		Browser.unregister(invs);
//
//		return status;
//	}
	
	/**
	 * Check site status
	 * @param fromDay
	 * @param numOfStay
	 * @param statusCode - now only "Available"
	 * @return - true for available
	 */
	public boolean checkStatus(String fromDay, int numOfStay, int statusCode) {
		String filledDate = this.getArrivalDate();
		String formatedDate = DateFunctions.formatDate(filledDate, "M/d/yyyy");
		//update the arrival date if it is necessary
		if(DateFunctions.compareDates(fromDay, formatedDate) != 0){
			this.setFromDate(fromDay);
		}
		this.clickUpdateDisplay();
		this.waitLoading();

		List<String> sitesStatus = new ArrayList<String>();
		
		for (int i = 0; i < numOfStay; i++) {
			String date = DateFunctions.getDateAfterGivenDay(fromDay, i);
			sitesStatus.add(this.getSiteStatus(date));
		}
		
		boolean status = true;
		for (int i = 0; i < numOfStay && status==true; i++) {
			if (statusCode == STATUS_AVAILABLE && sitesStatus.get(i).equals("r")) {
				status = false;
			} else if (statusCode == STATUS_NOT_AVAILABLE && !sitesStatus.get(i).equals("r")) {
				status = false;
			}
		}

		return status;
	}
	
	/**
	 * Get a site's status of a specific day 
	 * @param arrivalDate
	 * @return
	 */
	public String getSiteStatus(String arrivalDate) {
		IHtmlObject objs1[] = browser.getTableTestObject(".text", new RegularExpression("Availability Display", false));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFunctions.parseDateString(arrivalDate));
		
		int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
		String weekDays[] = {"S", "M", "T", "W", "T", "F", "S"};
		
		String dateFlag = arrivalDate.split("/")[1].trim() + weekDays[weekIndex - 1];

		String tableHeader = "";
		int counter = 0;
		IHtmlObject objs2[] = browser.getTableTestObject(".text", ((IHtmlTable)objs1[0]).getCellValue(8, 1));
		IHtmlTable siteCalendarTable = (IHtmlTable)objs2[0];

		for(int i = 0; i < 31; i++) {
			tableHeader = siteCalendarTable.getCellValue(1, 1 + i);
			if(tableHeader.length() > 0 && tableHeader.equalsIgnoreCase(dateFlag)){
				counter = i;
				break;
			}
		}

		String toReturn = siteCalendarTable.getCellValue(2, 1 + counter);
		
		Browser.unregister(objs1);
		Browser.unregister(objs2);
		return toReturn;
	}
	
	/**
	 * Check arrival date whether from some date
	 * @param fromDay
	 * @param numOfStay--int
	 * @return
	 */
	public boolean checkAvail(String fromDay, int numOfStay) {
		return checkStatus(fromDay, numOfStay, STATUS_AVAILABLE);
	}

	/**
	 * Check arrival date whether from some date
	 * @param fromDay
	 * @param numOfStay
	 * @return
	 */
	public boolean checkUnavail(String fromDay, int numOfStay) {
		return checkStatus(fromDay, numOfStay, STATUS_NOT_AVAILABLE);
	}
	/**
	 * Check arrival date whether from some date
	 * @param fromDay
	 * @param numOfStay---String
	 * @return
	 */
	public boolean checkUnavail(String fromDay, String numOfStay) {
		return checkUnavail(fromDay, Integer.parseInt(numOfStay));
	}

	/**
	 * Check the site whether held
	 * @param fromDay
	 * @param numOfStay
	 * @return
	 */
	public boolean checkHeld(String fromDay, int numOfStay) {
		return checkStatus(fromDay, numOfStay, STATUS_NOT_AVAILABLE);
	}

	/**
	 * Check the site whether is booked
	 * @param fromDay
	 * @param numOfStay
	 * @return
	 */
	public boolean checkBooked(String fromDay, int numOfStay) {
		return checkStatus(fromDay, numOfStay, STATUS_NOT_AVAILABLE);
	}

	/**Retrieve Site Name*/
	public String getSiteName() {
		RegularExpression reg = new RegularExpression("View Map.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);

		String temp = ((IHtmlTable)objs[0]).getCellValue(1, 1);
		Browser.unregister(objs);
		
		return StringUtil.getSubstring(temp, "Site#", "Type");
	}
	
	public void setFromDate(String date) {
	  	browser.setTextField(".id","startDateSearch_ForDisplay",date,true);
	}
	
	public int getNumberOfAvailableDates() {
	  	RegularExpression pattern=new RegularExpression("\\w{3} \\d{1,2}, \\d{4}",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".text","c",".id",pattern);
	  	
	  	int num=objs.length;
	  	
	  	Browser.unregister(objs);
	  	
	  	return num;
	}
	
	public String getAvailableDate(int index) {
	  	RegularExpression pattern=new RegularExpression("\\w{3} \\d{1,2}, \\d{4}",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".text","c",".id",pattern);
	  	
	  	String date=null;
	  	
	  	if(index>=objs.length) {
	  	  	throw new ItemNotFoundException("Index "+index+" is out of boundary "+objs.length);
	  	} else {
	  	  	date = objs[index].getProperty(".id").toString();
	  	}
	  	
	  	return date;
	}
	
	public String[] getAvailableDates() {
	  	RegularExpression pattern=new RegularExpression("\\w{3} \\d{1,2}, \\d{4}",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".text","c",".id",pattern);
	  	
	  	String[] date=null;
	  	
	  	
	  	if(objs.length<1) {
	  	  	throw new ItemNotFoundException("No available dates found.");
	  	} else {
		  	date = new String[objs.length];
	  	  	
	  	  	for(int i=0;i<objs.length;i++) {
	  	  	  date[i] = objs[i].getProperty(".id").toString();
	  	  	}
	  	  	
	  	}
	  	
	  	return date;
	}
	
	public boolean checkSelectedAvailableInventory(String nights){
	  	RegularExpression pattern=new RegularExpression("\\w{3} \\d{1,2}, \\d{4}",false);
	  	IHtmlObject[] objs=browser.getHtmlObject(".text","a",".id",pattern);
	  
	  	boolean selectedAvailable =  true;
	  	if(objs.length!=Integer.parseInt(nights)){
	  		selectedAvailable = false;
	  	}
	  	
	  	Browser.unregister(objs);
	  	return selectedAvailable;
	}
	
	public String selectFirstAvailableInventory(String nights) {
	  	boolean found=false;
	  	String[] dates=getAvailableDates();
	  	int size=dates.length;
	  	if(size<Integer.parseInt(nights) || size<1) {
	  	  	throw new ItemNotFoundException("No available inventory for the given nights#"+nights);
	  	}
	  	
	  	
	  	String startDate=null;
	  	for(int i=0;i<dates.length && !found;i++) {
	  	  	String firstDate=dates[i];
	  	  	found=true;
	  	  	for(int j=1;j<Integer.parseInt(nights);j++) {
	  	  	  	if(!DateFunctions.isAfter(firstDate,dates[i+j],j)){
	  	  	  	  	found=false;
	  	  	  	}
	  	  	}
	  	  	
	  	  	if(found) {
	  	  	  	startDate=firstDate;
	  	  	}
	  	}
	  	
	  	if(!found) {
	  	  	throw new ItemNotFoundException("No available inventory for the given nights#"+nights);
	  	}
	  	
	  	this.setArrivalDate(startDate);
	  	this.setNightNum(nights);
	  	clickNightsLable(); //refresh the selection
	  	
	  	return startDate;
	}
	
	public void clickNightsLable() {
	  	browser.clickGuiObject(".class","Html.LABEL",".text","Nights");
	}
	
	public String[][] parseSiteListTable() {
		IHtmlObject[] objs = getSiteDetailTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		int columnSize = table.columnCount();
		int rowSize = table.rowCount();
		String[][] siteTable = new String[rowSize][columnSize];

		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < columnSize; c++) {
				Object value = table.getCellValue(r, c);
				if (value == null || value.toString().length() < 1)
					value = "null";

				siteTable[r][c] = value.toString();
			}
		}
		Browser.unregister(objs);

		return siteTable;
	}
	
	public IHtmlObject[] getSiteDetailTable(){	
//		RegularExpression reg=new RegularExpression("^\\d+\\w \\d+\\w .*",false);
//		HtmlObject[] objs=browser.getHtmlObject(".class", "Html.TABLE", ".text",reg);
		
		IHtmlObject[] tdObjs = browser.getHtmlObject(".class", "Html.TR", ".text", 
				new RegularExpression("^Availability Display.*",false));
		if(tdObjs.length<1){
			throw new ErrorOnPageException("Avaliability Display Row did not found.");
		}
		
		Property[] p = new Property[1];
		p[0] = new Property(".class","Html.TABLE");
		
		IHtmlObject[] objs = browser.getHtmlObject(p, tdObjs[0]);
		
		return objs;
	}
	
	public void selectSpecDate(String date)
	{
	  browser.clickGuiObject(".class","Html.A",".id",date);
	}
	
	public void setUpdateDate(String date){
		browser.setTextField(".id", "startDateSearch_ForDisplay", date);
	}
	
	public String getMiniRuleErrorMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".id",new RegularExpression("V-100002|V-100004",false));
		String miniRuleMessage=objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return miniRuleMessage;
	}
	
	public String getSpecifyStayRuleErrorMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".id","V-100004");
		String miniRuleMessage=objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return miniRuleMessage;
	}
	
	/**
	 * This method used to parsing rate table to return table text object
	 * @return
	 */
	public String retrieveRateTable(){
		RegularExpression rex = new RegularExpression("^Rates Start From.*",false);
		IHtmlObject[] objs = browser.getTableTestObject(".text",rex);
		IHtmlTable grid = (IHtmlTable)objs[0];
		String text = grid.text();
		Browser.unregister(objs);
		return text;
	}
	
	/**
	 * This method used to retrieve daily price flag
	 * @return
	 */
	public String getDailyPriceFlag(){
		String text = this.retrieveRateTable();
		String flag = text.substring(text.indexOf("Daily")+5, text.indexOf("Weekly"));
		return (flag.trim().length()>0?flag.trim():"0");
	}
	
	/**
	 * This method used to retrieve weekly price flag
	 * @return
	 */
	public String getWeeklyPriceFlag(){
		String text = this.retrieveRateTable();
		String flag = text.substring(text.indexOf("Weekly*")+7, text.indexOf("Monthly"));
		return (flag.trim().length()>0?flag.trim():"0");
	}
	
	/**
	 * This method used to check given note message displayed
	 * @param note
	 * @return
	 */
	public boolean checkRateNotesExists(String note){
		return browser.checkHtmlObjectExists(".class","Html.SPAN",".text",note);
	}
	
	/**
	 * This method used to retrieve monthly price flag
	 * @return
	 */
	public String getMonthlyPriceFlag(){
		String text = this.retrieveRateTable();
		String flag = text.substring(text.indexOf("Monthly**")+9);
		flag = flag.substring(0, flag.indexOf("Mon"));
		return (flag.trim().length()>0?flag.trim():"0");
	}
	
	/**
	 * Get the error message for rules' validation
	 * @param frameName - the name of frame
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject frame[] = browser.getFrame("transaction");
		Property property[] = new Property[1];
		property[0] = new Property(".id", "statusMessages");
		IHtmlObject objs[] = browser.getTableTestObject(property, frame[0]);
		String statusMsg = "";
		
		if(objs.length > 0) {
			statusMsg = ((IHtmlTable)objs[0]).getProperty(".text").trim();
		}
		Browser.unregister(objs);
		return statusMsg;
	}
	
	/**Retrieve Electric hoop-up */
	public String getElectricHoopUp(){
		//"Water Hookup","Non Resident Surcharge","Driveway Surface","Driveway Entry","Shade";
//		String[] splitStrings = new String[]{"Water", "Non","Driveway","Driveway","Shade"};
		String[] splitStrings = new String[]{"Water Hookup","Non Resident Surcharge","Driveway Grade", 
				"Driveway Surface","Driveway Entry","Site Length", "Site Width","Shade",};
		String returnElectricHookUp = "";
		RegularExpression reg = new RegularExpression("View Map View Reservations .*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);

		String temp = objs[0].text();
		for(int i=0; i<splitStrings.length; i++){
			String splitString = temp.split("Electricity Hookup")[1].trim();
			if(splitString.contains(splitStrings[i])){
				returnElectricHookUp = splitString.split(splitStrings[i])[0].trim();
				break;
			}
		}

		Browser.unregister(objs);
		return returnElectricHookUp;
	}
	
	public String getState(){
		return browser.getObjectText(".class", "Html.DIV", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
	
	public void clickParkName(String parkName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(parkName, false));
	}
	
	public void clickViewMap() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Map");
	}
	
	public void clickViewReservations() {
		browser.clickGuiObject(".class", "Html.A", ".text", "View Reservations");
	}
	
	public void clickValidateRules() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate Rules");
	}
	
	private String getSpanObjectText(String labelName){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^" + labelName + ".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any span object with label name = " + labelName);
		}
		String text = objs[objs.length-1].text().replaceFirst(labelName, "").trim();
		Browser.unregister(objs);
		return text;			
    }
	
	public String getCheckinTime(){
		return this.getSpanObjectText("Checkin Time");
	}
	
	public String getCheckoutTime(){
		return this.getSpanObjectText("Checkout Time");
	}
}
