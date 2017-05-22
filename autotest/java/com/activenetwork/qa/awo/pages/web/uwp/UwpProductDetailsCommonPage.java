package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class UwpProductDetailsCommonPage extends UwpHeaderBar {
	//Lesley[20130906]: update the parent class from UwpPage to UwpHeaderBar due to Items In Cart link changed to Items text and Check Out Now link
	private static UwpProductDetailsCommonPage _instance = null;

	public static UwpProductDetailsCommonPage getInstance() {
		if (null == _instance)
			_instance = new UwpProductDetailsCommonPage();

		return _instance;
	}

	protected UwpProductDetailsCommonPage() {
	}

	protected Property[] siteIconsDiv() {
		return Property.concatPropertyArray(div(), ".id", "siteicons");
	}
	
	protected Property[] petsAllowedImg() {
		return Property.concatPropertyArray(img(), ".title", "Pets Allowed");
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".type","submit",".id", "btnbookdates")&& browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","calendar");
//		return browser.checkHtmlObjectExists(".class","Html.IMG",".title", "'A' marks available dates; your dates higlighted in yellow.")&& browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","calendar");
//		return browser.checkHtmlObjectExists(".classname", "items", ".id", "calendar");
		return browser.checkHtmlObjectExists(".class","Html.DIV",".id","csitecalendar")
				|| browser.checkHtmlObjectExists(".class", "Html.Input.Text", ".id", "arrivaldate"); // update by lesley
	}
	
	/**
	 * Verify whether the access error appears on page, like 'Can not reserved on-line'
	 * @return true or false
	 */
	public boolean accesstypeError() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "contentArea");
		String str = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		if (str.matches(UwpSchConstants.accessTypeError)) {
			return true;
		} else
			return false;
	}
	
	/**
	 * Retrieve the Error or Warnning message displayed on page
	 * @return message on page
	 */
	public String getErrorMsg() {
		IHtmlObject[] contentArea = browser.getHtmlObject(".class",
				"Html.DIV", ".id", "contentArea");
		String text = (String) contentArea[0].getProperty(".text");
		Browser.unregister(contentArea);
		return text;
	}
	
	/**
	 * retrieve the minimum stay from error message displayed on page
	 * @return - length of Minimum stay
	 */
	public String getMinimumLengthOfStay() {
		IHtmlObject[] contentArea = browser.getHtmlObject(".id", "contentArea");
		String text = (String) contentArea[0].getProperty(".text");
		Browser.unregister(contentArea);
		String Days = "";
		if (text.matches(".*Minimum length of stay.+")) {
			int i = text.indexOf("night(s)");
			String newText = text.substring(0, i).trim();
			int k = newText.length() - 1;
			for (int j = 0; j < newText.length(); j++) {
				if (newText.charAt(k) >= '0' && newText.charAt(k) <= '9') {
					String nStr = String.valueOf(newText.charAt(k));
					Days = nStr + Days;
				} else {
					break;// just get the last number of the string, if others exist.
				}
				k--;
			}
		}
		return Days;
	}
	
	/**
	 * click on 'Find other Campsites' link
	 */
	public void gotoFindOtherCampsites() {
		RegularExpression other = new RegularExpression("^Find other .*", false);
		browser.clickGuiObject(".class","Html.A",".text", other);
	}
	
	/**
	 * click on link 'Next week'
	 */
	public void checkNextWeek() {
		browser.clickGuiObject(".id", "nextWeek");

	}
	
	/**
	 * click on link 'Next 2 weeks >'
	 */
	public void clickNext2Weeks() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next 2 weeks >");
	}
	
	/**
	 * click on link '< Previous 2 weeks'
	 */
	public void clickPrevious2Weeks() {
		browser.clickGuiObject(".class", "Html.A", ".text", "< Previous 2 weeks");
	}
	
	/**
	 * Navigate time according to link "Previous 2 weeks" and "Next 2 weeks" link
	 * @param isNextTwoWeeks --true: click "Next 2 weeks" link
	 *                       --false: click "Previous 2 weeks" link 
	 */
	public void navigateTime(boolean isNextTwoWeeks){
		if(isNextTwoWeeks){
			this.clickNext2Weeks();
		}else{
			this.clickPrevious2Weeks();
		}
		this.waitForProgressBarDisapear();
		this.waitLoading();
	}
	
	/**
	 * book an available site from given date during the maximum loop
	 * @param date - date want to book from
	 * @param maxLoop - maximum loop of period
	 * @return - date when site has been booked
	 */
	public String bookdaysAfterGivenDate(String date, int maxLoop) {
		boolean found = false;
		String currentDate = date;
		this.clickBookTheseDays();

		int count = 0; //count of clicking next 2 weeks
		IHtmlObject[] invs=null;
		while (!found) {
			waitForProgressBarDisapear();
			found=!browser.tentativeWaitExists(2, this);
			if (!found){
				RegularExpression reg = new RegularExpression("^(A|A [0-9]+)$",	false);
				invs = browser.getHtmlObject(".class", "Html.A", ".text",reg);
				while (invs.length < 2) {
					Browser.unregister(invs);
					count++;
					if (count > maxLoop)
						throw new ItemNotFoundException(
								"There is no inventory available in " + maxLoop
										* 14 + " days starting from " + date);
					this.clickNext2Weeks();
					this.waitLoading();
					invs = browser.getHtmlObject(".class", "Html.A",
							".text", reg);
				}
				((ILink)invs[1]).click();
				Browser.unregister(invs);
				
				currentDate = this.getArriveDate();
				if (this.getErrorMsg().matches(".*Minimum length of stay.+")) {
					String newLength = this.getMinimumLengthOfStay();
					this.setLengthOfStay(newLength);
				}
				this.clickBookTheseDays();
			} 
		}
		return currentDate;
	}
	
	/**
	 * book a site from date which has been used for site/park searching
	 * @param maxLoop - maximum loop of period
	 * @return - date when site has been booked
	 */
	public String bookAnyDays(int maxLoop) {
		String date = getArriveDate();
		return bookdaysAfterGivenDate(date, maxLoop);
	}
	
	/**
	 * retrieve the arrival date on site details page
	 * @return - arrival date
	 */
	public String getArriveDate() {
		return browser.getTextFieldValue(".id", "arrivaldate");
	}
	
	/**
	 * enter the arrival date.
	 * @param date - arrival date
	 */
	public void setArriveDate(String date) {
		browser.setTextField(".id", "arrivaldate", date);
	}
	
	public void setArriveDateAndMoveFocus(String date) {
		browser.setTextField(".id", "arrivaldate", date, 0, IText.Event.LOSEFOCUS);
	}
	/**
	 * Verify arrival date
	 * @param expectedArrivalDate
	 */
	public void verifyArrivalDate(String expectedArrivalDate){
		String actualArrivalDate = this.getArriveDate();
		if(!expectedArrivalDate.equals(actualArrivalDate)){
			throw new ErrorOnDataException("Arrival Date is wrong!", expectedArrivalDate, actualArrivalDate);
		}
		logger.info("Successfully verify Arrival Date:"+expectedArrivalDate);
	}
	
	public void clickGroundName(){
		browser.clickGuiObject(".id", "cgroundName");
	}
	
	/**
	 * enter length of stay
	 * @param length - length of wanted stay
	 */
	public void setLengthOfStay(String length) {
		browser.setTextField(".id", "lengthOfStay", length);
	}
	
	public String getLengthOfStay(){
		return browser.getTextFieldValue(".id", "lengthOfStay");
	}
	
	/**
	 * Verify length of stay
	 * @param expectedLengthOfStay
	 */
	public void verifyLengthOfStay(String expectedLengthOfStay){
		String actualLengthOfStay = this.getLengthOfStay();
		if(!expectedLengthOfStay.equals(actualLengthOfStay)){
			throw new ErrorOnDataException("Length of stay is wrong!", expectedLengthOfStay, actualLengthOfStay);
		}
		logger.info("Successfully verify length of stay:"+expectedLengthOfStay);
      		
	}
	/**
	 * click on 'Book these Dates' button
	 */
	public void clickBookTheseDays() {
		Property[] properties=new Property[]{
				new Property(".id", "btnbookdates"),
				new Property(".type","submit")
				//below property blocks web basic cases:
				//testCases.regression.basic.web.mix.CrossOverMultiContractClickOK
//				,
//				new Property(".text","Book these Dates")
				};
		browser.clickGuiObject(properties);
	}
	
	public void waitForBookTheseDaysBtn(){
		browser.searchObjectWaitExists(Property.toPropertyArray(".id", "btnbookdates",".type","submit", ".text", "Book these Dates"), LONG_SLEEP);
	}
	/**
	 * Click 'Check Availability' button
	 */
	public void clickCheckAvailabilityBtn() {
		browser.clickGuiObject(Property.toPropertyArray(".id", "btnbookdates", ".type", "submit", ".text", "Check Availability"));
	}
	
	public boolean isCheckAvailabilityBtnExisting(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".id", "btnbookdates", ".type", "submit", ".text", "Check Availability"));
	}
	
	/**
	 * Check availability 
	 * @param arrivalDate
	 * @param lengthOfStay
	 */
	public void checkAvailability(String arrivalDate, String lengthOfStay){
		logger.info("Check availability in site details page.");

//		this.setArriveDate(arrivalDate);
//		this.clickGroundName(); //move focus
		this.setArriveDateAndMoveFocus(arrivalDate);
		this.setLengthOfStay(lengthOfStay);

		this.waitLoading();

		if(this.isCheckAvailabilityBtnExisting()){
			this.clickCheckAvailabilityBtn();
		}
		this.waitForBookTheseDaysBtn();
	}
	
	/**
	 * Book these days
	 */
	public void bookTheseDays(){
		this.clickBookTheseDays();
		this.waitLoading();
	}
	/**
	 * verify whether a Federal park and need to book on RrecGov?
	 * @return true of false
	 */
	public boolean isBookingOnRecGov() {
		boolean toReturn = true;
		String text  = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", "btnbookdates");
		if(objs != null && objs.length >0){
			text = objs[0].getProperty(".text");
		}

		if (!text.equalsIgnoreCase("Book these Dates on Recreation.gov"))
			toReturn = false;

		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * click on 'Make Reservation at' link to book site from external site
	 */
	public void gotoSiteInExternalWebsite() {
		RegularExpression reg = new RegularExpression(".*Make Reservation at.*", false);
		browser.clickGuiObject(".class", "Html.A", ".text", reg);
	}
	
	/**
	 * get the park's max reservation window via camp status text
	 * @return - maximum reservation window
	 */
	public String getMaxResWindow() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
							".id", "campgStatus");
		String statusText = objs[0].getProperty(".text").toString();
		String[] temp = statusText.split("window: ");
		String maxWindow = temp[1].replaceAll(" Later Dates", "");

		Browser.unregister(objs);
		return maxWindow;
	}

	/**
	 * This method goes to retrieve the booking window
	 * @return
	 * 		-- bookWindow[0] stands for Minimum Window;
	 * 		-- bookWindow[1] stands for Maximum Window.
	 */
	public String[] getBookingWindow() {
		String[] bookWindow = new String[2];

		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".id", "reswindow");
		String content = objs[0].getProperty(".text").toString();
		System.out.println("content:"+content);
//		String[] temp = content.split("â€“");
//
//		bookWindow[0] = temp[0].replaceAll("Reservation Window: ", "").trim();
//		bookWindow[1] = temp[1].trim();
		List<String> bookWindowList = new ArrayList<String>();
		String regx = "[A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+"; //Tue Jun 18 2013
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(content);
		while(m.find()){
			String s = m.group();
			bookWindowList.add(s.trim());
		}
		bookWindow[0] = bookWindowList.get(0);
		bookWindow[1] = bookWindowList.get(1);
		Browser.unregister(objs);
		return bookWindow;
	}
	
	/**
	 * This method goes to retrieve the lottery inventory booking window
	 * @return
	 * 		-- bookWindow[0] stands for Inventory start date;
	 * 		-- bookWindow[1] stands for Inventory end date.
	 */
	public String[] getLotteryWindow() {
		String[] bookWindow = new String[2];
		List<String> bookWindowList = new ArrayList<String>();
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".id", "lotteryWindow");
		String content = objs[0].getProperty(".text").toString();
		System.out.println("content:"+content);

		
		String regx = "[A-Z][a-z]+ [A-Z][a-z]+ \\d+ \\d+"; //Tue Jun 18 2013
		Pattern p = Pattern.compile(regx);
		Matcher m = p.matcher(content);
		while(m.find()){
			String s = m.group();
			bookWindowList.add(s.trim());
		}
		
		bookWindow[0] = bookWindowList.get(0);
		bookWindow[1] = bookWindowList.get(1);
		
//		String[] temp = content.split("â€“");
//		bookWindow[0] = temp[0].replaceAll("Lottery Window: ", "").trim();
		System.out.println("0:"+bookWindow[0]);
//		bookWindow[1] = temp[1].trim();
		System.out.println("1:"+bookWindow[1]);

		Browser.unregister(objs);
		return bookWindow;
	}
	
	/**
	 * Go to booking window page by clicking Later Dates link
	 */
	public void gotoBookingWindow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Later Dates");
	}
	
	/**
	 * retrieve the remain available site number for NSS park
	 * @return - available number
	 */
	public int getRemainSiteNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
				".text", new RegularExpression("\\w \\d+$", false));

		String sNum = objs[0].getProperty(".text").toString();//.split(" ")[1];
		int num = Integer.parseInt(sNum);

		Browser.unregister(objs);
		return num;
	}
	
//	/**Click on Item In Cart to go to shoping cart page.*/
//	public void gotoShoppingCart() {
//	  	browser.clickGuiObject(".class","Html.A",".text",
//	  	    	new RegularExpression("Items In Cart.*",false));
//	}
	
	/**
	 * Verify whether or not the first date can be reserved.
	 * @return - ture - reservable; false - not reservable
	 */
	public boolean isFirstDateReservable(){
	  	IHtmlObject[] table=browser.getTableTestObject(".id","calendar");
	  	String text = ((IHtmlTable)table[0]).getCellValue(2,0);

	  	Browser.unregister(table);
	  	return text.equalsIgnoreCase("A");
	}
	
	/**wait for calendar to load.*/
	public void transferFocus(){
		browser.clickGuiObject(".className","detail");
	}

	/**
	 * Retrieve the site availability for given date.
	 * @param date - date
	 * @return status, A or L, etc...
	 */
	public String getDateAvailability(String date) {
		IHtmlObject[] table = browser.getTableTestObject(".id","calendar");
		IHtmlTable tableGrid = (IHtmlTable)table[0];
		
		date = DateFunctions.formatDate(date, "M/d/yyyy");//format the date.
		String day = date.split("\\/")[1];
		String status = "";
		int dateColumn = -1;
		for(int j=0; j<tableGrid.columnCount(); j++) {
			if(tableGrid.getCellValue(1, j).split(" ")[0].equalsIgnoreCase(day)) {
				dateColumn = j;
				break;
			}
		}
		
		if(dateColumn ==-1) {
			throw new ErrorOnPageException("Given date not include in current period.");
		} else {
			status = tableGrid.getCellValue(2, dateColumn);
		}
		
		Browser.unregister(table);
		return status;
	}
	
	/**
	 * Get the site number from the camp site detail page.
	 * @return - the site number
	 */
	public String getSiteNumber() {
		//Quentin[20130905]
//		HtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Site, Loop:", false));
		IHtmlObject[] objs = browser.getHtmlObject(".className", "siteTile");
		String siteNum;
		if (objs == null || objs.length < 1) {
//			objs = browser.getTableTestObject(".text", new RegularExpression("^Site:.*\\+.*", false)); // NSS site number
//			siteNum = objs[0].text().split(":")[1].split("\\+")[0].trim();
			//Jane: Add (, Loop)? for rec , Specific-Site
			objs = browser.getTableTestObject(".text", new RegularExpression("^Site(, Loop)?:.*(\\+)?.*", false)); // NSS site number
			String content = objs[0].text().split(":")[1];
			if(content.contains("\\+"))
				siteNum = content.split("\\+")[0].trim();
			else
				siteNum = content.split(",")[0].trim();
		} else {
			siteNum = objs[0].getProperty(".text").split(":")[1].split(",")[0].trim();
		}
		
		Browser.unregister(objs);
		return siteNum;
	}
	
	public String getSiteLoop(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Site, Loop:", false));
		IHtmlObject[] divs = browser.getHtmlObject(".class", "Html.DIV", ".className", "siteTile");
		String siteLoop = null;
		// Lesley[20130912]: update due to object changed on RA.com 
		if (objs != null && objs.length > 0) {
			siteLoop = objs[0].text();
		} else if (divs.length > 0) {
			siteLoop = divs[0].text();
		} else {
			logger.warn("No Site Loop shown!");
		}
		
		if (siteLoop != null && siteLoop.contains("Loop")) {
			siteLoop = siteLoop.split(":")[1].split(",")[1].split("Type")[0].trim();
			siteLoop = siteLoop.replace("Find Similar Sites", "").trim();
		}
		Browser.unregister(objs);
		return siteLoop;
	}
	
	public String getSiteType(){
//		HtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Site(, Loop)?:", false));		
//		String siteType = objs[0].getProperty(".text").split("Type:")[1].trim();		
//		Browser.unregister(objs);
		String siteType = browser.getObjectText(".class", "Html.TR", ".text", new RegularExpression("^Type:.*", false));
		//Lesley[20130912]: update due to object changed after Unified Search is applied to RA.com
		if (StringUtil.isEmpty(siteType)) {
			siteType = browser.getObjectText(".class", "Html.Span", ".text", new RegularExpression("^Type:.*", false));
		}
		siteType = siteType.split("Type:")[1].trim();
		return siteType;
	}
	
	/**
	 * Verify Site Type
	 * @param siteType
	 */
	public void verifySiteType(String siteType){
		if(!this.getSiteType().equals(siteType)){
			throw new ErrorOnPageException("Site Type should be "+siteType);
		}
	}
	
	public void verifySiteLoop(String siteLoop){
		if(!this.getSiteLoop().equals(siteLoop)){
			throw new ErrorOnPageException("Site Loop should be "+siteLoop);
		}
	}
	
	public void verifySiteNum(String siteNum){
		if(!this.getSiteNumber().equals(siteNum)){
			throw new ErrorOnPageException("Site number should be "+siteNum);
		}
	}
	
	/**
	 * Verify Unavailability information
	 */
	public void verifyUnavailabilityMessText(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "msg");
		String unavailabilityInfo = objs[0].text();
		if(!unavailabilityInfo.contains("No suitable availability shown")){
			throw new ErrorOnPageException("No matched Unavailability information is found.");
		}
		Browser.unregister(objs);
	}
	
	public boolean checkUnavailabilityMessExist(){
		return browser.checkHtmlObjectDisplayed(".className", "msg");
	}
	
	public void verifyUnavailabilityMessExist(boolean flag){
		boolean actualResult = this.checkUnavailabilityMessExist();
		if(actualResult!=flag){
			throw new ErrorOnPageException("Unavailability message should "+(flag?"":"not")+" be existed.");
		}
	}
	
	public boolean checkTopErrorMessExist(){
		return browser.checkHtmlObjectExists(".className", "msg1");
	}
	
	public void verifyTopErrorMessExist(boolean flag){
		boolean actualResult = this.checkUnavailabilityMessExist();
		if(actualResult!=flag){
			throw new ErrorOnPageException("Unavailability message should "+(flag?"":"not")+" be existed.");
		}
	}
	
	/**
	 * Get campsite park name and state code
	 * @return
	 * @author Lesley Wang
	 * @date Nov 5, 2012
	 */
	public String getCampsiteParkNameAndStateCode() {
		return browser.getObjectText(".id", "cgroundName");
	}
	/**
	 * Get site basic info from site detail page, including: site park name, site park state code, site code, site loop name, site type
	 * @return
	 * @author Lesley Wang
	 * @date Nov 5, 2012
	 */
	public SiteInfoData getCampsiteBasicInfo() {
		SiteInfoData site = new SiteInfoData();
		String[] parkNameAndStateCode = this.getCampsiteParkNameAndStateCode().split(",");
		site.parkName =parkNameAndStateCode[0].trim();
		site.state = parkNameAndStateCode[1].trim();
		site.loopName = this.getSiteLoop();
		site.siteCode = this.getSiteNumber();
		site.siteType = this.getSiteType();
		return site;
	}
	
	public String getCampsiteAccesibilityNotice() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "accessibilityNotice");
	}
	
	/**
	 * Get the content under the section Site Details, that are about site attributes
	 * @return
	 * @author Lesley Wang
	 * @date Nov 5, 2012
	 */
	public String getCampsiteDetailsInfo() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "sitedetail");
	}
	
	public boolean isCampsiteAlertExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".classname", "msg alertTitle");
	}
	
	public String getCampsiteAlertTitle() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg alertTitle");
	}
	public String getCampsiteAlerts() {
		String alerts = null;
		if (this.isCampsiteAlertExist()) {
			alerts = browser.getObjectText(".class", "Html.DIV", ".text", 
					new RegularExpression("^" + this.getCampsiteAlertTitle() + ".*", false));
		}
		return alerts;
	}
	
	public boolean isCampsiteNoteExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".classname", "msg noteTitle");
	}
	
	public String getCampsiteNoteTitle() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg noteTitle");
	}
	
	public String getCampsiteNotes() {
		return browser.getObjectText(".class", "Html.DIV", ".text", 
				new RegularExpression("^" + this.getCampsiteNoteTitle() + ".*", false));
	}
	
	public IHtmlObject[] getCampsiteImages() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "samplpics");
		IHtmlObject[] images = null;
		if (objs == null || objs.length < 1) {
			logger.warn("No Images are shown!");
		} else {
			images = browser.getHtmlObject(".class", "Html.IMG", objs[0]);
		}
		
		Browser.unregister(objs);
		return images;
	}
	
	public List<String> getCampsiteImagesSrc() {
		IHtmlObject[] objs = this.getCampsiteImages();
		List<String> info = new ArrayList<String>(); 
		if (objs != null) {
			for (int i = 0; i < objs.length; i++) {			
				info.add(objs[i].getAttributeValue("src"));
			}
		}
		
		Browser.unregister(objs);
		return info;
	}
	
	/**
	 * Check whether "noAvailabilityMsg" exists or not
	 * @return
	 */
	public boolean isNoAvailabilityMesExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "noAvailabilityMsg");
	}
	
	/**
	 * Check whether "availabilityMessages" exists or not
	 * @return
	 */
	public boolean isAvailabilityMesExisting(){
		IHtmlObject[] objs = this.getAvailabilityMesObjs();
		if(objs[0].style("display").trim().equals("none")){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * Check whether "msg error" exists or not
	 * @return
	 */
	public boolean isErrorMesExisting(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "msg error");
	}
	
	/**
	 * Check whether "msg" exists or not
	 * @return
	 */
	public boolean isWarningMesExisting(String waringMes){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "msg", ".text", waringMes);
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Verify 'noAvailabilityMsg' message is disappeared
	 */
	public void noAvailabilityMesDisappears(){
		if(isNoAvailabilityMesExisting()){
			throw new ErrorOnDataException("'noAvailabilityMsg' should be disappeared.");
		}
		logger.info("Successfully verify 'noAvailabilityMsg' is disappeared.");
	}
	
	/**
	 * Verify 'noAvailabilityMsg' message is disappeared
	 */
	public void availabilityMesDisappears(){
		if(isAvailabilityMesExisting()){
			throw new ErrorOnDataException("'availabilityMessages' should be disappeared.");
		}
		logger.info("Successfully verify 'availabilityMessages' is disappeared.");
	}
	
	/**
	 * Verify 'msg error' message is disappeared
	 */
	public void errorMesDisappears(){
		if(isErrorMesExisting()){
			throw new ErrorOnDataException("'msg error' should be disappeared.");
		}
		logger.info("Successfully verify 'msg error' is disappeared.");
	}
	
	/**
	 * Verify 'msg' message is disappeared
	 */
	public void warningMesDisappears(String warningMes){
		if(isWarningMesExisting(warningMes)){
			throw new ErrorOnDataException("'msg' should be disappeared.");
		}
		logger.info("Successfully verify 'msg' is disappeared.");
	}
	
	/**
	 * Get "noAvailabilityMsg" objects
	 * @return
	 */
	public IHtmlObject[] getNoAvailabilityMsgObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "noAvailabilityMsg");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'noAvailabilityMsg' objects can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get "availabilityMessages" objects
	 * @return
	 */
	public IHtmlObject[] getAvailabilityMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "availabilityMessages");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'availabilityMessages' objects can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get "mes error" objects
	 * @return
	 */
	public IHtmlObject[] getErrorMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg error");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'mes error' objects can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get "mes" objects
	 * @return
	 */
	public IHtmlObject[] getWaringMesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'mes error' objects can't be found.");
		}
		return objs;
	}
	
	/**
	 * Get "noAvailabilityMsg"
	 * @return
	 */
	public String getNoAvailabilityMsg(){
		IHtmlObject[] objs = this.getNoAvailabilityMsgObjs();
		String mes = objs[0].text().trim();
		
		Browser.unregister(objs);
		return mes;
	}
	
	/**
	 * Get "availabilityMessages" 
	 * @return
	 */
	public String getAvailabilityMes(){
		IHtmlObject[] objs = this.getAvailabilityMesObjs();
		String mes = objs[0].text().trim();
		
		Browser.unregister(objs);
		return mes;
	}
	
	/**
	 * Get "msg error" 
	 * @return
	 */
	public String getErrorMes(){
		IHtmlObject[] objs = this.getErrorMesObjs();
		String mes = objs[0].text().trim();
		
		Browser.unregister(objs);
		return mes;
	}
	
	/**
	 * Get "msg" 
	 * @return
	 */
	public String getWaringMes(){
		IHtmlObject[] objs = this.getWaringMesObjs();
		String mes = objs[0].text().trim();
		
		Browser.unregister(objs);
		return mes;
	}
	
	/**
	 * Verify "noAvailabilityMsg"
	 * @param expectedMes
	 */
	public void verifyNoAvailabilityMes(String expectedMes){
		String actualMes = this.getNoAvailabilityMsg();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("'noAvailabilityMsg' is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify 'noAvailabilityMsg':"+expectedMes);
	}
	
	/**
	 * Verify "availabilityMessages"
	 * @param expectedMes
	 */
	public void verifyAvailabilityMes(String expectedMes){
		String actualMes = this.getAvailabilityMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("'availabilityMessages' is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify 'availabilityMessages':"+expectedMes);
	}
	
	/**
	 * Verify "msg error"
	 * @param expectedMes
	 */
	public void verifyErrorMes(String expectedMes){
		String actualMes = this.getErrorMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("'msg error' is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify 'msg error':"+expectedMes);
	}
	
	/**
	 * Verify "msg"
	 * @param expectedMes
	 */
	public void verifyWarningMes(String expectedMes){
		String actualMes = this.getWaringMes();
		if(!expectedMes.equals(actualMes)){
			throw new ErrorOnDataException("'msg' is wrong!", expectedMes, actualMes);
		}
		logger.info("Successfully verify 'msg':"+expectedMes);
	}
	
	/**
	 * Click 'Create Availability Notification' link
	 */
	public void clickCreateAvailabilityNotificationLink(){
		browser.clickGuiObject(".id", "availNotifLink");
	}
	
	public boolean checkProductPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".id", "samplpics");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
	
	public boolean isPetsAllowedIconExist() {
		return browser.checkHtmlObjectExists(Property.atList(siteIconsDiv(), petsAllowedImg()));
	}
	
	public void verifyPetsAllowedIconExist(boolean exp) {
		String msg = (exp ? " " : " NOT ") + " shown on campsite details page";
		if (exp != this.isPetsAllowedIconExist()) {
			throw new ErrorOnPageException("The pets allowed icon should" + msg);
		}
		logger.info("Verify pets allowed icon does" + msg);
	}
	
	public int getAmenityIconsNum() {
		IHtmlObject[] imgs = browser.getHtmlObject(Property.atList(this.siteIconsDiv(), img()));
		int num = imgs.length;
		Browser.unregister(imgs);
		return num;
	}
	
	public void verifyAmenityIconsNum(int expNum) {
		int actualNum = this.getAmenityIconsNum();
		if (expNum != actualNum) {
			throw new ErrorOnPageException("Amenity icons number is wrong on campsite details page", expNum, actualNum);
		}
		logger.info("Amenity icons number is correct on campsite details page" + " as " + expNum);
	}
}

