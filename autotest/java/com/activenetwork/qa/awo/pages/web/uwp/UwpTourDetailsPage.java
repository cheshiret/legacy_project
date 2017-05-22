package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * Only support the single tour
 * @author jchen
 */
public class UwpTourDetailsPage extends UwpPage {

	private static UwpTourDetailsPage _instance = null;

	public static UwpTourDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourDetailsPage();

		return _instance;
	}

	public UwpTourDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".name", "bookTourForm");
	}

	/**
	 * This method goes to select the specified tour time for specified tour
	 * @param objSeq - starts from 0
	 * @param timeSeq - start from 0
	 * @return selected text
	 */
	public String selectTourTime(int objSeq, int timeSeq) {
		IHtmlObject[] objs = browser.getDropdownList(".name", "invId"); //For each child tour drop down list

		if (objSeq < objs.length) {
			int maxItem = Integer.parseInt(objs[objSeq].getProperty(".length").toString());//Get the number of each child tour drop down list
			if (timeSeq < maxItem) {
				((ISelect)objs[objSeq]).select(timeSeq);
			} else if(!objs[objSeq].text().matches("\\d+\\:\\d+ (AM|PM).*")){//Select first option like "May 28 - May 30 --...", not like "7:00 AM --..."
				((ISelect)objs[objSeq]).select(1);
			} else {
				throw new ItemNotFoundException("Only " + maxItem
						+ " items found for tour " + (objSeq + 1));
			}
		} else
			throw new ItemNotFoundException("Only " + objs.length
					+ " tour(s) found.");

		String toReturn = ((ISelect) objs[objSeq]).getSelectedText();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Always select the first tour time for first tour.
	 * @return - selected text
	 */
	public String selectTourTime() {
		return selectTourTime(0, 1);
	}

	/**
	 * Fill in ticket number.
	 * @param tickets - ticket number
	 */
	public void setTicketNum(String tickets) {
		browser.setTextField(".id", "qtyTickets", tickets);// set the first given properties object
	}
	
	/**
	 * fill in ticket number info based on given ticket type.
	 * the acceptable ticket type should be "Adult", "Youth", "Senior" etc..
	 * @param ticketType
	 * @param ticketNum
	 */
	public void setTicketNumByGivenTicketType(String ticketNum, String ticketType){
		IHtmlObject objs[] = browser.getTableTestObject("id", "tourPricing");
		IHtmlObject ticketTypesLable[] = browser.getHtmlObject(".className", "ticketType", objs[0]);
		int index  = 0;
		
		if(ticketType != null && ticketType.length() >0){
			for (int i = 0; i < ticketTypesLable.length; i ++){
				if (ticketTypesLable[i].text().toLowerCase().contains(ticketType.toLowerCase())){
					index = i;
					break;
				} 
			}	
		}
		
		IHtmlObject textObjs[] = browser.getTextField(".id", "qtyTickets");
		((IText)textObjs[index]).setText(ticketNum);
		Browser.unregister(objs, textObjs);		
	}

	/**
	 * Book a combo tour, will select first time in first tour, second time in second tour, etc.
	 * if also broken time between tour rule, then select first time in first tour, third time in second tour, fifth time...
	 * @param tour - tour booking info
	 */
	public void bookComboTour(TicketInfo tour) {
		logger.info("Select tour inventory for each combo child tour.");
		if(null != tour.comboChildTours && tour.comboChildTours.size()>0){
			for(int i=0; i<tour.comboChildTours.size(); i++){
				IHtmlObject[] topObjs = browser.getHtmlObject(".class", "TR", ".text", new RegularExpression("^"+tour.comboChildTours.get(i).tourName + ".*Select Time.*", false));
				if(topObjs.length<1){
					throw new ErrorOnPageException("Could not find any avaliable tour inventory for "+tour.comboChildTours.get(i).tourName);
				}
				this.selectTourTime(tour.comboChildTours.get(i).timeSlot, topObjs[0]);
				Browser.unregister(topObjs);
			}
		}else{
			//Select value from each child tour drop down list
			IHtmlObject[] tObjs = browser.getHtmlObject(".name", "invId"); //Each child tour drop down list
			int num = tObjs.length;
			
			int j = 1;
			for (int i = 0; i < num; i++) {
				this.selectTourTime(i, j);
				j += 1; // avoid the enough time between tours error
			}
			Browser.unregister(tObjs);
		}
		//Set each child tour value	
		IHtmlObject[] labelObj = browser.getHtmlObject("for", "qtyTickets");
		IHtmlObject[] textObj = browser.getTextField(".id", "qtyTickets");

		if(!StringUtil.isEmpty(tour.adultNum) || !StringUtil.isEmpty(tour.childNum)){
			for (int i = 0; i < labelObj.length; i++) {
				String tiType = labelObj[i].getProperty(".text").toString().replaceAll(":", "").trim();
				// need to add the other ticket types loop
				if (tiType.equalsIgnoreCase("Adult")
						&& tour.adultNum.length() > 0) {
					((IText) textObj[i]).setText(tour.adultNum);
				}
				if (tiType.equalsIgnoreCase("Child")
						&& tour.childNum.length() > 0) {
					((IText) textObj[i]).setText(tour.childNum);
				}
				if (tiType.equalsIgnoreCase("Student")
						&& tour.studentNum.length() > 0) {
					((IText) textObj[i]).setText(tour.studentNum);
				}
			}
		}else{
			if (tour.ticketNums.length() > 0) { // if you want to fill in first type, just provide noTickets parameter
				((IText) textObj[0]).setText(tour.ticketNums);
			} 
		}
		
		if(tour.ticketTypes.size()!=tour.ticketTypeNums.size()){
			throw new ErrorOnDataException("size of types and typeNuns should be equal.");
		}
		for(int i=0;i<tour.ticketTypes.size();i++){
			this.setTicketNumByGivenTicketType(tour.ticketTypeNums.get(i), tour.ticketTypes.get(i));
		}
		
		//Select "Delivery Method"
		if(this.checkDeliveryMethodSectionExists()){
			if(!StringUtil.isEmpty(tour.deliveryMethod)){
				this.selectDeliveryMethod(tour.deliveryMethod);
			}else {
				this.selectDeliveryMethod("Will Call");//Set 'Will Call' as default delivery method
			}
		}

		//Click "Book Tour" button
		this.clickOnBookTour();

		UwpTourOrderDetailsPage tourOrderDetailsPg = UwpTourOrderDetailsPage.getInstance();
		UwpShoppingCartPage tourShoppingCart = UwpShoppingCartPage.getInstance();
		browser.waitExists(this, tourOrderDetailsPg, tourShoppingCart);

		if(this.exists()){
			IHtmlObject[] obj = browser.getHtmlObject(".name", "invId");
			if (this.getErrorMsg().matches(
			"^This request does not allow enough time between tours.*")) {
				int k = 1;
				for (int i = 0; i < obj.length; i++) {
					this.selectTourTime(i, k);
					k += 2; // avoid the enough time between tours error
				}
			}
			Browser.unregister(obj);

			this.clickOnBookTour();
			browser.waitExists(this, tourOrderDetailsPg, tourShoppingCart);
			if (this.exists())
				throw new ItemNotFoundException("Please check time rules between each tours");
		}
	}
	
	/**
	 * Check if delivery method section exists
	 * @return
	 */
	public boolean checkDeliveryMethodSectionExists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.radio", ".id", new RegularExpression("(DeliveryMethod_|deliveryMethod-)\\d+", false));
	}
	
	/**
	 * Verify no delivery method displays in tour details page
	 */
	public void verifyNoDeliveryMethodSectionExists(){
		boolean result = this.checkDeliveryMethodExist();
		if(result){
			throw new ErrorOnDataException("Failed to verify 'Delivery method section' doesn't displays in 'Tour Details' page.");
		}
		logger.info("Successfully verify 'Delivery method section' doesn't displays in 'Tour Details' page.");
	}

	public TicketInfo bookTour(String tickets, int timeSeq){ 
		return bookTour(tickets, timeSeq, null);
	}
	
	/**
	 * Book a tour, select first time period.
	 * @param tickets - number of tickets
	 * @param timeSeq - tour time's seq
	 * @param timeSlot - tour time slot(if this parameter was specified, we will select tour time by timeSlot, else we will select tour time by timeSeq)
	 * @return tour date, tour time, ticket type and the ticket quantity
	 */
	public TicketInfo bookTour(String tickets, int timeSeq, String timeSlot) {
		String tourtime = "";
		if(timeSeq<1)
		  	  timeSeq=1;
		if(null != timeSlot && timeSlot.length()>0){
			tourtime = selectTourTime(0, timeSlot);
		}else{
			tourtime = selectTourTime(0, timeSeq);//if timeSeq was invalidate, we will always choose the 1st item
		}
	  	
		TicketInfo tour = new TicketInfo();
		tour.ticketNums = tickets;
		if (tour.ticketNums.length() < 1)
			tour.ticketNums = "3";
		browser.setTextField(".id", "qtyTickets", tour.ticketNums);// set the first given properties object

		Browser.sleep(2);

		//tourdate
		String tourdate = browser.getTextFieldValue(".id", "tourDate");
		String[] strs = tourdate.split(" ");
		String weekday = strs[0];
		String month = strs[1];
		String day = strs[2];
		String year = strs[3];
		if (day.length() == 1)
			day = "0" + day;
		tourdate = weekday + month + day + year;

		//tourtime
		if (tourtime.indexOf("-") != -1)
			tourtime = tourtime.substring(0, tourtime.indexOf("-")).replaceAll(" ", "");
		//tickettype
		IHtmlObject[] objs = browser.getHtmlObject("for", "qtyTickets");
		//		TestObject[] objs=browser.getGuiTestObject(".className", "ticketType"); // can not find this object now
		String tickettype = "unknown";
		if (objs.length > 0) {
			IHtmlObject text_tickettype = (IHtmlObject) objs[0];
			tickettype = (String) text_tickettype.getProperty(".text");
			if (tickettype.indexOf(":") != -1)
				tickettype = tickettype.substring(0, tickettype.indexOf(":"));
		}

		browser.clickGuiObject(".id", "btnbookdates");
		Browser.unregister(objs);
		
		tour.tourDate = tourdate.trim();
		tour.tourTime = tourtime.trim();
		tour.ticketType = tickettype.trim();

		return tour;
	}
	
	/**
	 * select tour time from drop down list by time slot
	 * @param objSeq
	 * @param timeSlot
	 */
	public String selectTourTime(int objSeq, String timeSlot){
		IHtmlObject[] objs = browser.getDropdownList(".name", "invId"); //For each child tour drop down list
		if (objSeq < objs.length) {
			List<String> itemList = ((ISelect)objs[objSeq]).getAllOptions();//Get the option string list
			int count = 0;
			for(String item:itemList){
				if(item.matches("^"+timeSlot+".*")){
					break;
				}
				count++;
			}
			if(count == itemList.size()){//itemList.size()-1
				throw new ItemNotFoundException("Could not find timeSlot:"+timeSlot+" from dropdown list.");
			}
			((ISelect)objs[objSeq]).select(count);
		} else
			throw new ItemNotFoundException("Only " + objs.length + " tour(s) found.");
		
		String toReturn = ((ISelect) objs[objSeq]).getSelectedText();
		Browser.unregister(objs);
		return toReturn;
	}
	
	/**
	 * select tour time from drop down list by time slot
	 * @param objSeq
	 * @param timeSlot
	 */
	public String selectTourTime(String timeSlot, IHtmlObject topObj){
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.SELECT");
		property[1] = new Property(".name", "invId");
		IHtmlObject[] objs = browser.getDropdownList(property, topObj); //For each child tour drop down list
		if (objs.length<1) {
			throw new ErrorOnPageException("Could not find any avaliable tour inventory for time "+timeSlot);
		} 

		List<String> itemList = ((ISelect)objs[0]).getAllOptions();//Get the option string list
		int count = -1;
		for(int i=0;i<itemList.size();i++){
			if(itemList.get(i).matches("^"+timeSlot+".*")){
				count=i;
				break;
			}
		}
		if(count<0){
			throw new ItemNotFoundException("Could not find timeSlot:"+timeSlot+" from dropdown list.");
		}
		((ISelect)objs[0]).select(count);
		
		String toReturn = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);
		return toReturn;
	}
	
	public TicketInfo bookTourByGivenTicketType(String tickets, int timeSeq, String ticketType){
		TicketInfo ticket=new TicketInfo();
		ticket.ticketNums=tickets;
		ticket.ticketTimeSeq=timeSeq;
		ticket.ticketType=ticketType;
		return bookTourByGivenTicketType(ticket);
	}
	
	/**
	 * Select time
	 * @param timeSeq
	 * @param timeSlot
	 * @return
	 */
	public String selectTourTimeFromDropDownList(int timeSeq, String timeSlot){
		String tourtime = "";
		
		if(timeSeq<1)
		  	  timeSeq=1;
		if(null != timeSlot && timeSlot.length()>0){
			tourtime = selectTourTime(0, timeSlot);
		}else{
			tourtime = selectTourTime(0, timeSeq);//if timeSeq was invalidate, we will always choose the 1st item
		}
		
		return tourtime;
	}
	
	/**
	 * Book a tour based on given ticket number and ticket type, if the ticketType is not set in parameter, this method will select the first ticket type by default.
	 * @param tickets - number of tickets
	 * @param timeSeq - tour time's seq
	 * @param ticketType - ticket type for booking
	 * @param timeSlot - tour time slot(if this parameter was specified, we will select tour time by timeSlot, else we will select tour time by timeSeq)
	 * @return tour date, tour time, ticket type and the ticket quantity
	 */
	//String tickets, int timeSeq, String ticketType, String timeSlot, String deliveryMethod
	public TicketInfo bookTourByGivenTicketType(TicketInfo tour) {
		//Select tour time from drop down list
		String tourtime = this.selectTourTimeFromDropDownList(tour.ticketTimeSeq, tour.timeSlot);
	
		
		//Set ticket nums via ticket types
		if (tour.ticketNums.length() < 1)
			tour.ticketNums = "3";
		this.setTicketNumByGivenTicketType(tour.ticketNums, tour.ticketType);

		Browser.sleep(2);

		//Get Tour Date as return info
		String tourdate = browser.getTextFieldValue(".id", "tourDate");
	/*	String[] strs = tourdate.split(" ");
		String weekday = strs[0];
		String month = strs[1];
		String day = strs[2];
		String year = strs[3];
		if (day.length() == 1)
			day = "0" + day;
		tourdate = weekday + month + day + year;*/  //should not change status in keyword

		//Get Tour Time as return info
		if (tourtime.indexOf("-") != -1)
//			tourtime = tourtime.substring(0, tourtime.indexOf("-")).replaceAll(" ", "");
			tourtime = tourtime.substring(0, tourtime.indexOf("-")); //should not change status in keyword
		
		//Get Ticket Type as return info
		IHtmlObject[] objs = browser.getHtmlObject("for", "qtyTickets");
		String tickettype = "unknown";
		if (objs.length > 0) {
			IHtmlObject text_tickettype = (IHtmlObject) objs[0];
			tickettype = (String) text_tickettype.getProperty(".text");
			if (tickettype.indexOf(":") != -1)
				tickettype = tickettype.substring(0, tickettype.indexOf(":"));
		}
		Browser.unregister(objs);
		
		//Select "Delivery Method"
		if(this.checkDeliveryMethodSectionExists()){
			if(!StringUtil.isEmpty(tour.deliveryMethod)){
				this.selectDeliveryMethod(tour.deliveryMethod);
			}else {
				this.selectDeliveryMethod("Will Call");//Set 'Will Call' as default delivery method
			}
		}
		
		clickBookTour();
		
		tour.tourDate = tourdate.trim();
		tour.tourTime = tourtime.trim();
		tour.ticketType = tickettype.trim();

		return tour;
	}
	
	/**
	 * Book tour by given ticket type 
	 * @param ticketNums
	 * @param timeSeq
	 * @param ticketType
	 * @param timeSlot
	 */
	public void bookTourByGivenTicketType(List<String> ticketNums, int timeSeq, List<String> ticketType, String timeSlot, String deliveryMethod) {
		//Select tour time from drop down list
		this.selectTourTimeFromDropDownList(timeSeq, timeSlot);

		//Compare the length of each ticket types and related numbers
		if(ticketNums.size()!=ticketType.size()){
			throw new ErrorOnDataException("The length is different between ticket numbers and ticket types.");
		}

		//Set each ticket type numbers via ticket types
		Property[] p1=null;
		Property[] p2=Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "qtyTickets");
		for(int i=0; i<ticketType.size(); i++){
			p1=Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^"+ticketType.get(i).replace("(", "\\(").replace(")", "\\)")+":.*",false));
			browser.setTextField(Property.atList(p1,p2), ticketNums.get(i));
		}

		//Select "Delivery Method"
		if(this.checkDeliveryMethodSectionExists()){
			if(!StringUtil.isEmpty(deliveryMethod)){
				this.selectDeliveryMethod(deliveryMethod);
			}else {
				this.selectDeliveryMethod("Will Call");//Set 'Will Call' as default delivery method
			}
		}
		if(this.isIgnoreWarningCheckBoxExisting())
        this.selectIgnoreWarning();
		clickBookTour();
	}
	
	/**
	 * 
	 */
	public void selectIgnoreWarning() {
		browser.selectCheckBox(".id", "ignoreWarnings");
	}
	
	public boolean isIgnoreWarningCheckBoxExisting(){
		return browser.checkHtmlObjectExists(".id", "ignoreWarnings");
	}

	public void clickBookTour() {
		browser.clickGuiObject(".id", "btnbookdates");
	}
	
	/**
	 * Get delivery method DIV objects
	 * @return
	 */
	public IHtmlObject[] getDeliveryMethodDivObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".name", "deliveryMethod", ".id", "deliveryMethod");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Delivery method DIV objects can't be found.'");
		}
		return objs;
	}
	
	public boolean checkDeliveryMethodDivObjs(){
		return browser.checkHtmlObjectExists(".name", "deliveryMethod", ".id", "deliveryMethod");
	}

	public boolean checkDeliveryMethodExist(){
		boolean result = this.checkDeliveryMethodDivObjs();
		IHtmlObject[] objs = null; 
		
		if(result){
			objs = this.getDeliveryMethodDivObjs();
			String typeValue = objs[0].getProperty("type");
			if(typeValue.equals("hidden")){
				result = false;
			}
		}
		
		Browser.unregister(objs);
		return result;
	}
	
	/**
	 * Check delivery method selected
	 * @param deliveryMethod
	 * @return
	 */
	public boolean checkDeliveryMethodSelected(String deliveryMethod){
			IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", new  RegularExpression("^" + deliveryMethod, false));
			
			if(labelObjs == null || labelObjs.length < 1) {
				throw new ItemNotFoundException("Can't find Delivery Method - " + deliveryMethod);
			}
			String forValue = labelObjs[0].getProperty("for");
			Browser.unregister(labelObjs);
			
			IHtmlObject[] objs=browser.getRadioButton(".id", forValue);
			boolean selected=((IRadioButton)objs[0]).isSelected();
			Browser.unregister(objs);
			
			return selected;	
	}
	
	/**
	 * Verify delivery method selected or not
	 * @param deliveryMethod
	 * @param selected_Expected  --true: delivery method is selected
	 *                           --false: delivery method isn't selected
	 */
	public void verifyDeliveryMethodSelected(String deliveryMethod, boolean selected_Expected){
		boolean selected_Actual = this.checkDeliveryMethodSelected(deliveryMethod);
		if(selected_Expected!=selected_Actual){
			throw new ErrorOnPageException("'Delivery method - '"+deliveryMethod+" should "+(selected_Expected?"":"not")+" be selected.");
		}
		logger.info("Successfully verify 'Delivery method - '"+deliveryMethod+" is "+(selected_Expected?"":"not")+" selected.");
	}

	/**
	 * Retrieve the first available tour's remain number.
	 * @return - tour remain number
	 */
	public String getAvailableTourNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				new RegularExpression("^C\\d+\\-\\d+", false));

		String content = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);

		String num = content.split(" ")[1];
		return num;
	}

	/**
	 * Click on Book Tour button.
	 */
	public void clickOnBookTour() {
		browser.clickGuiObject(".id", "btnbookdates");
	}

	/**
	 * Retrieve the Maximum and Minimum ticket number from web page.
	 * @return - Max & Min ticket number
	 */
	public String[] getRestrictTicketNum() {
 		IHtmlObject[] objs = browser.getHtmlObject(".id", "tourPricing"); //Min: 2 Tickets,  Max: 7 Tickets

		String ticketNum[] = new String[2];
		String content = objs[0].getProperty(".text").toString();

		ticketNum[0] = content.split("Min: ")[1].split(" ")[0].trim(); // minimum tickets
		ticketNum[1] = content.split("Max: ")[1].split(" ")[0].trim(); // maximum tickets

		Browser.unregister(objs);
		return ticketNum;
	}

	/**
	 * Retrieve the warning or error messages display on page.
	 * @return - message
	 */
	public String getErrorMsg() {
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", "tourStatusMessage");
		String toReturn = "";

		if (objs.length > 0) {
			toReturn = objs[0].getProperty(".text").toString();
		} else
			throw new ItemNotFoundException("No error message displays.");

		Browser.unregister(objs);
		return toReturn;
	}
		
	/**
	 * Click on Find Other Tour link.
	 */
	public void clickFindOtherTour() {
		browser.clickGuiObject(".id", "findOtherCamps");
	}

	/**
	 * Select the first time for each tour in combo tour
	 */
	public void selectEachFirstTimeForComboTour() {
		IHtmlObject[] objs = browser.getDropdownList(".name", "invId");
		
		for (int i = 0; i < objs.length; i++) {
			((ISelect) objs[i]).select(1);
		}
		Browser.unregister(objs);
	}

	/**
	 * Go to shopping cart page by clicking on Items in Cart:..
	 */
	public void gotoShoppingCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Items In Cart.*)|(Check Out Now)", false)); //Lesley[20131115]: Update due to items in cart link changed in REC
	}
	
	/**
	 * Set tour date in tour details page
	 * @param date
	 */
	public void setTourDate(String date) {
	  	browser.setTextField(".id", "tourDate", date);
	}
	
	/**
	 * Set number of tickets to updating tour time
	 * @param num
	 */
	public void setTickets(String num) {
	  	browser.setTextField(".id", "numberOfTicketsSearched", num);
	}
	
	/**Click on update tour times*/
	public void clickUpdateTourTimes() {
	  	browser.clickGuiObject(".type", "submit", ".text", "Update Tour Times");
	}
	
	/**click on next available link.*/
	public void clickNextAvailable() {
	  	browser.clickGuiObject(".id", "resultNext");
	}
	
	/**click on previous available link.*/
	public void clickPreviousAvailable() {
	  	browser.clickGuiObject(".id", "resultPrevious");
	}
	
	/**
	 * Retrieve the tour date.
	 * @return - tour date
	 */
	public String getTourDate() {
	  	return browser.getTextFieldValue(".id", "tourDate");
	}
	
	/**
	 * Get tour times from drop down list
	 * @return
	 */
	public List<String> getToutTimesFromDDList(){
		return browser.getDropdownElements(".className", "timeSelect");
	}
	
	/**
	 * get page down by clicking adult tickets number field
	 */
	public void scrillDownPage(){
	  	browser.clickGuiObject(".id", "qtyTickets");
	}
	
	/**
	 * Method used to get the ticket availability status by verifying whether the error message - 'Not On Sale Yet' exists
	 * @return
	 */
	public boolean getTicketStatus(String tourDate) {
		String filledTourDate = this.getTourDate();
		String formatedTourDate = DateFunctions.formatDate(filledTourDate);
		
		if(DateFunctions.compareDates(formatedTourDate, tourDate) != 0) {
			this.setTourDate(tourDate);
			this.clickUpdateTourTimes();
		}
		this.waitLoading();
		
		String notOnSaleYet = "";
		boolean ticketStatus = true;
		
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "tourStatusMessage");
		if(objs.length > 0) {
			notOnSaleYet = objs[0].getProperty(".text").trim();
			if(notOnSaleYet.matches("Not On Sale Yet")) {
				ticketStatus = false;
			}
		}
		
		Browser.unregister(objs);
		return ticketStatus;
	}
	
	/**
	 * Get the tour name
	 * @return
	 */
	public String getTourName() {
		IHtmlObject objs[] = browser.getHtmlObject(".className", "tourTitle");
		
		String tourName = "";
		if(objs.length > 0) {
			tourName = objs[0].getProperty(".text").split("\\(")[0].trim();
		}
		
		Browser.unregister(objs);
		return tourName;
	}

	/**
	 * select delivery method - Print at Home, Mail Out, Will Call, etc.
	 * @param method
	 */
	public void selectDeliveryMethod(String method) {
		if(method.length() < 1) {
			selectDeliveryMethod(0);
		} else {
			IHtmlObject labelObjs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", new  RegularExpression("^" + method, false));
			
			if(labelObjs.length < 1) {
				throw new ItemNotFoundException("Can't find Delivery Method - " + method);
			}
			String forValue = labelObjs[0].getProperty("for");
			
			Browser.unregister(labelObjs);
			browser.selectRadioButton(".id", forValue);
		}
	}
	
	public void selectDeliveryMethod(int index) {
		browser.selectRadioButton(".id", "deliveryMethod" + index);
	}

	public boolean checkDeliveryMethodRadioBtnExist(){
		return browser.checkHtmlObjectExists( ".id", new RegularExpression("deliveryMethod\\d+",false),".class", "Html.INPUT.radio");
	}
	
	public void verifyNoDeliveryMethodRedioBtnInTourDetailsPg() {
		if(this.checkDeliveryMethodRadioBtnExist()){
			throw new ErrorOnPageException("Failed to verify no Delivery Method radio button existing in 'Tour Details' page.");
		}
		logger.info("Successfully verify no Delivery Method radio button existing in 'Tour Details' page.");
	}
	
	public boolean checkDeliveryMethodRadioBtnExist(String method_name){
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^ ?"+method_name+".*",false));
		IHtmlObject[] topDiv=browser.getHtmlObject(p1);
		IHtmlObject top=null;
		if(topDiv==null||topDiv.length<1){
			top=null;
		}else{
			top=topDiv[topDiv.length-1];
		}
		Property[] p2=Property.toPropertyArray(".name", "deliveryMethod", ".class","Html.INPUT.radio");
		IHtmlObject[] o= browser.getRadioButton(p2,top);
		boolean isExisting;
		if(o==null || o.length<1){
			isExisting=false;
		}else{
			isExisting=true;
		}
		Browser.unregister(o,topDiv);
		return isExisting;
	}
	
	public void verifyDeliveryMethodExistingOrNot(String method,boolean isExisting) {
		logger.info("Verify delivery method:"+method+" is "+(isExisting?"":"not")+" existing");
		if(checkDeliveryMethodExisting(method)!=isExisting){
			throw new ObjectNotFoundException("there should "+(isExisting?"":"not")+" be "+method);
		}
	}
	
	public boolean checkDeliveryMethodExisting(String method){
		return browser.checkHtmlObjectExists(".className", "component", ".text", new RegularExpression("^Delivery Method.*"+method+".*",false));		
	}

	public void verifyDeliveryMethodRedioBtnExistingOrNot(String method_name,boolean isExisting) {
		logger.info("verify there is "+(isExisting?"":"not")+" radio btn for "+method_name);
		if(checkDeliveryMethodRadioBtnExist(method_name)!=isExisting){
			throw new ErrorOnPageException("There should "+(isExisting?"":"not")+" be radio btn for "+method_name);
		}		
	}

	public void verifyTourDate(String tourDate) {
		String tourDateOnPage=this.getTourDate();
		if(DateFunctions.compareDates(tourDate,tourDateOnPage)!=0){
			throw new ErrorOnPageException("Tour Date is Wrong.",tourDate,tourDateOnPage);
		}
	}
	
	public void updateTourDateInTourDetailsPage(String date){
		setTourDate(date);
		clickUpdateTourTimes();
		waitForProgressBarDisapear();
	}
	
	/**
	 * Get tour open available objects
	 * Such as, "Oct 26 - Oct 30 200" button buder tour name
	 * @return
	 */
	public IHtmlObject[] getTourOpenAvailableObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".className", "tourCellOpenAvailable");
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Tour open available objects can't be found.");
		}
		
		return objs;
	}
	
	/**
	 * Get tour open available Value
	 * @return
	 */
	public String[] getTourOpenAvailableValues(){
		IHtmlObject[] objs = this.getTourOpenAvailableObjs();
		String[] tourOpenAvailableValues = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			tourOpenAvailableValues[i] = objs[i].text().trim();
		}
		
		Browser.unregister(objs);
		return tourOpenAvailableValues;
	}
	
	/**
	 * Verify tour open available value format
	 * @param expectedFormat
	 */
	public void verifyTourOpenAvailableValuesFormat(String expectedFormat){
		String[] tourOpenAvailableValues = this.getTourOpenAvailableValues();
		for(int i=0; i<tourOpenAvailableValues.length; i++){
			if(!tourOpenAvailableValues[i].matches(expectedFormat)){
				throw new ErrorOnPageException("'Tour Open Available' value:"+tourOpenAvailableValues[i]+" doesn't match format:"+expectedFormat);
			}
			logger.info("Successfully verify 'Tour Open Available' value:"+tourOpenAvailableValues[i]+" matchs format:"+expectedFormat);
		}
	}
	
	public boolean checkProductPhotoExist(String description){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TD", ".id", "colsidebar");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".title", description);
		Property[] p3 = Property.toPropertyArray(".class", "Html.IMG", ".alt", description);
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)) && browser.checkHtmlObjectExists(Property.atList(p1, p3));
	}
}
