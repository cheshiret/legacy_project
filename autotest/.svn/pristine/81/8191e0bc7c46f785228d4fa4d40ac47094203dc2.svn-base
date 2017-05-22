package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.pages.web.common.UwpShoppingCartCommonPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jchen
 */

public class UwpShoppingCartPage extends UwpShoppingCartCommonPage {
	
	public double campingFee, reservationFee, taxes, discount, total, min;
	
	private static UwpShoppingCartPage _instance = null;

	public static UwpShoppingCartPage getInstance() {
		if (null == _instance)
			_instance = new UwpShoppingCartPage();

		return _instance;
	}

	public UwpShoppingCartPage() {
	}

	public Property[] shoppingCartTableProp(){
		return Property.toPropertyArray(".id", "table1");
	}
	
	public Property[] timerProp(){
		return Property.toPropertyArray(".class", "Html.Span", ".id", "countdown");
	}
	
	public Property[] expiredProp(){
		return Property.toPropertyArray(".class", "Html.Span", ".id", "expired");
	}
	
	/**
	 * Click on Find Perfect Campsite link.
	 */
	public void clickFindPerfectSite() {
		browser.clickGuiObject(".text", "Find the Perfect Campsite");
	}
	
	/**
	 * Verify whether the shopping cart is empty.
	 * @return true - empty; false - not empty
	 */
	public boolean isEmpty() {
		boolean empty = false;

		IHtmlObject[] foundTOs = browser.getHtmlObject(".text", "Find the perfect campsite");
		if (foundTOs.length != 0) {
			empty = true;
		}
		Browser.unregister(foundTOs);
		return empty;
	}

	/**
	 * Verify whether the shopping cart is empty.
	 * @return true - empty; false - not empty
	 */
	public boolean isShoppingCartEmpty() {
		boolean empty = false;

		IHtmlObject[] foundTOs = browser.getHtmlObject(".class", "Html.A", ".text", "Items In Cart: 0"); //Sara[8/28/2013]: Fit for Rec.gov and RA (without unified search function)
		if(foundTOs.length<1){
			foundTOs = browser.getHtmlObject(".className", "msg info", ".text", new RegularExpression("This Shopping Cart is empty.*", false)); //RA (with unified search function)
		}
		
		if (foundTOs.length == 1) {
			empty = true;
		}
		Browser.unregister(foundTOs);
		return empty;
	}

	/**
	 * Find Change Details link object.
	 * @return
	 */
	private IHtmlObject[] findChangeDetails() {
		return browser.getHtmlObject(".class","Html.A",".text", "Change Details");
	}

	/**
	 * Get table object when Details Required appears on page.
	 * @return - Table Object
	 */
	private IHtmlObject[] findDetailsRequired() {
		RegularExpression text = new RegularExpression(".*(d|D)etail(s) (r|R)equired.*", false);
		return browser.getHtmlObject(".text", text);
	}
	
	/**
	 * Click on order number link to go to order details page by index.
	 * @param i - item index, start from 1
	 */
	public void gotoOrderDetail(int i) {
		IHtmlObject[] objs = findChangeDetails();
		objs[i - 1].click();
		Browser.unregister(objs);
	}

	/**
	 * Click on order number link to go to order details page by index when there is details required for order.
	 * @param i - item index, start from 1
	 */
	public void gotoNOrderDetail(int i) {
		IHtmlObject[] objs = findDetailsRequired();
		objs[i - 1].click();
		Browser.unregister(objs);
	}

	/**
	 * Go to first order details page.
	 */
	public void gotoOrderDetailFirstItem() {
		gotoOrderDetail(1);
	}

	/**
	 * Go to last order details page.
	 */
	public void gotoOrderDetailLastItem() {
		gotoOrderDetail(numberOfItemsDetails());
	}

	/**
	 * Go to first order details page when page with Details Required.
	 */
	public void gotoNOrderDetailFirstItem() {
		gotoNOrderDetail(1);
	}

	/**
	 * Go to last order details page when page with Details Required.
	 */
	public void gotoNOrderDetailLastItem() {
		gotoNOrderDetail(numberOfItemsNDetails());
	}
	
	/**
	 * Retrieve the number of order items when page with Details Required.
	 * @return - number of order items
	 */
	public int numberOfItemsNDetails() {
		int returnInt = tableData(UwpSchConstants.searchNDetails);

		return returnInt;
	}

	/**
	 * Retrieve the number of items details.
	 * @return - number of details
	 */
	public int numberOfItemsDetails() {
		int returnInt = tableData(UwpSchConstants.searchDetails);
		return returnInt;
	}
	
	public int numberOfTourDate(){
		return tableData(UwpSchConstants.tourDateWithMultiDay);
	}
	
	/**
	 * Verify number of tour date according tour date format: UwpSchConstants.tourDate
	 * @param expectedNum
	 */
	public void verifyNumberOfTourDate(int expectedNum){
		int actualNum = this.numberOfTourDate();
		if(expectedNum!=actualNum){
			throw new ErrorOnPageException("Failed to verify numbr of tour date", expectedNum, actualNum);
		}
		logger.info("Successfully verify number of tour date:"+actualNum);
	}
	
	   /**
     * Get tour date information
     * @param tourName
     * @return
     */
    public List<String> getTourDates(String tourDateFormat){
    	List<String> tourDates = new ArrayList<String>();
    	String tableContent = this.getShoppingCartTableContent();	

    	Pattern p = Pattern.compile(tourDateFormat);
    	Matcher m = p.matcher(tableContent);
    	while(m.find()){
    		tourDates.add(m.group());
    	}
    	return tourDates;
    }
    
    /**
     * Verify tour dates
     * @param tourDateFormat
     * @param expectedTourDates
     */
    public void verifyTourDates(String tourDateFormat, List<String> expectedTourDates){
    	List<String> actualTourDates = this.getTourDates(tourDateFormat);
    	if(actualTourDates.size()!=expectedTourDates.size()){
    		throw new ErrorOnPageException("The length of date rage is wrong.", expectedTourDates.size(), actualTourDates.size());
    	}
    	for(int i=0; i<expectedTourDates.size(); i++){
    		if(!expectedTourDates.get(i).equals(actualTourDates.get(i))){
    			throw new ErrorOnPageException("Tour date is wrong "+i, expectedTourDates.get(i), actualTourDates.get(i));
    		}
    		logger.info("Successfully verify tour date:"+expectedTourDates.get(i));
    	}
    }
	

	/**
	 * Retrieve the number of order items which no longer available.
	 * @return - number of order details
	 */
	public int numberOfItemsNAvail() {
		int returnInt = tableData(UwpSchConstants.searchNAvail);

		return returnInt;
	}

	/**
	 * Retrieve the campping fee, but will only make sense when camping fee is unique
	 * @return - camping fee
	 */
	public String getCampingFee() {
	  	return getFeeByName("Camping Fee");
	}
	
	public String getReservationFee(){
		return getFeeByName("Reservation Fee");
	}
	
	/** 
	 * Retrieve discount amount, will only make sense only when discount is unique.
	 * @return - discount amount
	 */
	public String getDiscount() {
		IHtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
		String text = foundTOs[0].getProperty(".text").toString();
		String temp[] = text.split("Discount: ?");
		String discountFee = temp[1].split(" ")[0].replaceAll("(\\(|\\)|\\$)", "");

		Browser.unregister(foundTOs);
		return discountFee;
	}

	/**
	 * Verify whether there is any discount has been applied in shopping cart
	 * @return true - found; false - not found discount
	 */
	public boolean isContainDiscount(){
		boolean toReturn = false;
		
		IHtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
		String discountName[] = foundTOs[0].getProperty(".text").split("Discount: ?");
		if(discountName.length>1)
			toReturn = true;
		
		Browser.unregister(foundTOs);
		return toReturn;
	}
	
	/**
	 * This methods goes to get a set of discount fees depend on the given discount name
	 * @param disName - discount name vector
	 * @return - discount fees vector
	 */
	public List<String> getSpecifiedDiscount(List<String> disName) {
		List<String> fees = new ArrayList<String>();
		IHtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
		String text = foundTOs[0].getProperty(".text").toString();
		for (int i = 0; i < disName.size(); i++) {
			String temp[] = text.split(disName.get(i) + ": ");
			String discountFee = temp[1].split(" ")[0].replaceAll("(\\(|\\))",
					"");
			fees.add(discountFee);
		}

		Browser.unregister(foundTOs);
		return fees;
	}

	/**
	 * Retrieve the special discount amount by discount name.
	 * @param disName - discount name
	 * @return - discount amount
	 */
	public String getSpecifiedDiscount(String disName) {
		IHtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
		String text = foundTOs[0].getProperty(".text").toString();
		String temp[] = text.split(disName + ": ");
		String discountFee = temp[1].split(" ")[0].replaceAll("(\\(|\\))", "");

		Browser.unregister(foundTOs);
		return discountFee;
	}
	
	/**
	 * Retrieve the special POS amount by given POS name.
	 * @param name - POS name
	 * @return POS amount
	 */
	public String getPOSAmountByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(shoppingCartTableProp());
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in cart!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in cart!");
		}
		
		String amount=table.getCellValue(posRowNum, 3).split(":")[1].trim();
		amount = amount.replaceAll("[a-zA-Z]", "").trim();
		Browser.unregister(obj);
		
		return amount;
	}
	
	public String getPOSReservationFeeByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(shoppingCartTableProp());
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in cart!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in cart!");
		}
		
		String str=table.getCellValue(posRowNum, 3).trim();
		String fee = "";
		if (str.contains("Reservation Fee")) {
			fee = str.substring(str.indexOf("Reservation Fee"));
		} else if (str.contains("Mailing Fee")) {
			fee = str.substring(str.indexOf("Mailing Fee"));
		}
//		String fee = str.substring(str.indexOf("Reservation Fee"));
		fee = fee.split(":")[1].trim();
		fee = fee.replaceAll("[a-zA-Z]", "").trim();
		Browser.unregister(obj);
		
		return fee;
	}
	
	/**
	 * Get POS name row
	 * @param posName
	 * @return
	 */
	public int getPosRowNum(String posName){
		IHtmlObject[] obj = browser.getTableTestObject(shoppingCartTableProp());
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(posName+".*")) {
				posRowNum = i;
				break;
			}
		}
		
		return posRowNum;
	}
	
	public String getPOSQuantityByName(String name) {
		IHtmlObject[] obj = browser.getTableTestObject(shoppingCartTableProp());
		IHtmlTable table = (IHtmlTable) obj[0];
		
		int posRowNum = -1;
		for(int i=0; i<table.rowCount(); i++) {
			String nameRow = table.getCellValue(i, 1);
			if(null !=nameRow && nameRow.matches(name+".*")) {
				posRowNum = i;
				break;
			}
			if(i==table.rowCount()) {
				throw new ItemNotFoundException("POS name not found in cart!");
			}
		}
		if(posRowNum == -1) {
			throw new ItemNotFoundException("POS name not found in cart!");
		}
		
		String quantity=table.getCellValue(posRowNum, 2).split(":")[1].trim();
		Browser.unregister(obj);
		
		return quantity;
	}
	
//	/**
//	 * Get number of order items
//	 * @return
//	 */
//	public int getNumOfOrderItems(){
//		Property[] p = Property.toPropertyArray(".id", "ckitems");
//		HtmlObject[] objs = browser.getCheckBox(p);
//		if(null==objs || objs.length<1){
//			throw new ObjectNotFoundException("Checked Shopping Cart Items can't be found.");
//		}
//		
//		int numOfOrderItems = objs.length;
//		
//		Browser.unregister(objs);
//		return numOfOrderItems;
//	}
	
	/**
	 * Verify the number of order items
	 *
	 */   
	public void verifyNumOfOrder(int numOfOrderItems_Expected){
		int numOfOrderItems_Actual = this.getNumOfOrderItems();
		if(numOfOrderItems_Expected!=numOfOrderItems_Actual){
			throw new ErrorOnPageException("The number of order items is different.", String.valueOf(numOfOrderItems_Expected), String.valueOf(numOfOrderItems_Actual));
		}
		logger.info("Successfully verify the number of order items ----- "+numOfOrderItems_Expected);
	}
	
//	public HtmlObject[] getShoppingCartTable(){
//		HtmlObject[] foundTOs = browser.getTableTestObject(shoppingCartTableProp());
//		if(foundTOs==null || foundTOs.length<1){
//			throw new ObjectNotFoundException("Shopping cat table can't be found.");
//		}
//		
//		return foundTOs;
//	}
	
//	/**
//	 * Get shopping cat table content
//	 * @return
//	 */
//	public String getShoppingCartTableContent(){
//		HtmlObject[] foundTOs = this.getShoppingCartTable();
//		String text = foundTOs[0].getProperty(".text");
//		
//		Browser.unregister(foundTOs);
//		return text;
//	}
	
	/**
	 * Get ticket type numbers
	 * @param ticketTypes
	 * @return
	 */
	public List<String> getSingleItemTicketQuantity(List<String> ticketTypes) {
		List<String> ticketQuantity = new ArrayList<String>();
		int matchedTicketTypeCount = 0;

		Property[] p2 = Property.toPropertyArray(".class", "Html.TR");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(shoppingCartTableProp(), p2));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Can't find any TR objects.");
		}

		for(int i=0; i<ticketTypes.size(); i++){
			for(int j=0; j<objs.length; j++){
				//Adult 3 200.00
				//Adult (16-61) 3 @ 20.00 60.00
				//Adult 3 @ 20.00 60.00
				if(objs[j].text().matches(ticketTypes.get(i).replace("(", "\\(").replace(")", "\\)")+" \\d+ (@ |)\\d+\\.\\d+.*")){
					if(objs[j].text().contains("@")){
						ticketQuantity.add((objs[j].text().split("@")[0].split(ticketTypes.get(i).replace("(", "\\(").replace(")", "\\)"))[1]).trim());
					}else{
						ticketQuantity.add((objs[j].text().split(" ")[1].trim()));
					}
					matchedTicketTypeCount++;
					break;
				}
			}
		}
		
		if(matchedTicketTypeCount!=ticketTypes.size()){
			throw new ErrorOnPageException("The length of actual ticket type numbers is different with the expected ticket types",
					String.valueOf(ticketTypes.size()), String.valueOf(matchedTicketTypeCount));
		}
		
		Browser.unregister(objs);
		return ticketQuantity;
	}
	
    public List<String> getMultiItemsTicketQuantity(List<String> ticketTypes){
    	List<String> ticketQuantity = new ArrayList<String>();
		String text = this.getShoppingCartTableContent();
		
		for(int i=0; i<ticketTypes.size(); i++){
			String aa = ticketTypes.get(i).replace("(", "\\(").replace(")", "\\)")+" \\d+ (@ |)\\d+\\.\\d+(| \\d+\\.\\d+)";
			Pattern p = Pattern.compile(aa);
			Matcher m = p.matcher(text);
			while(m.find()){
				String s = m.group();
				if(s.contains("@")){
					ticketQuantity.add((s.split("@")[0].split(ticketTypes.get(i).replace("(", "\\(").replace(")", "\\)"))[1]).trim());
				}else{
					ticketQuantity.add((s.split(" ")[1].trim()));
				}
			}
		}
		
		return ticketQuantity;
    }
	
    /**
     * Get tour date information
     * @param tourName
     * @return
     */
    public List<String> getTourDate(String tourDateFormat){
    	List<String> tourDates = new ArrayList<String>();
    	String tableContent = this.getShoppingCartTableContent();	

    	Pattern p = Pattern.compile(tourDateFormat);
    	Matcher m = p.matcher(tableContent);
    	while(m.find()){
    		tourDates.add(m.group());
    	}
    	return tourDates;
    }
    
	/**
	 * Verify ticket quantity
	 * @param ticketTypes
	 * @param ticketTypeQuantity
	 */
	public void verifyTicketQuantity(List<String> ticketTypes, List<String> ticketTypeQuantity_Expected) {
		logger.info("Start to verify ticket quantity in 'Shopping cart' page.");
		
		List<String> ticketTypeQuantity_Actual = new ArrayList<String>();
			ticketTypeQuantity_Actual = this.getSingleItemTicketQuantity(ticketTypes);
		
		if(ticketTypeQuantity_Expected.size()!=ticketTypeQuantity_Actual.size()){
			throw new ErrorOnPageException("The length of actual ticket type numbers is different with the expected ticket type number",
					String.valueOf(ticketTypeQuantity_Expected.size()), String.valueOf(ticketTypeQuantity_Actual.size()));
		}
		
		for(int i=0; i<ticketTypeQuantity_Expected.size(); i++){
			if(!ticketTypeQuantity_Expected.get(i).equals(ticketTypeQuantity_Actual.get(i))){
				throw new ErrorOnPageException("ticket type number is wrong for ticket type - "+ticketTypes.get(i), 
						ticketTypeQuantity_Expected.get(i), ticketTypeQuantity_Actual.get(i));
			}
			logger.info("Successfully verify ticket type number - "+ticketTypeQuantity_Expected.get(i)+" for ticket type - "+ticketTypes.get(i));
		}
	}
	
	public void verifyMultipleItemsTicketQuantity(List<String> ticketTypes_Search, String[] ticketTypes_expected, List<String> ticketTypeQuantity_Expected) {
		List<String> ticketTypeQuantity_Actual = new ArrayList<String>();
			ticketTypeQuantity_Actual = this.getMultiItemsTicketQuantity(ticketTypes_Search);
		
			if(ticketTypes_expected.length!=ticketTypeQuantity_Expected.size()){
				throw new ErrorOnPageException("The length of expected ticket types is different with the expected ticket types numbers",
						String.valueOf(ticketTypes_expected.length), String.valueOf(ticketTypeQuantity_Expected.size()));
			}
			
		if(ticketTypeQuantity_Expected.size()!=ticketTypeQuantity_Actual.size()){
			throw new ErrorOnPageException("The length of actual ticket type numbers is different with the expected ticket type number",
					String.valueOf(ticketTypeQuantity_Expected.size()), String.valueOf(ticketTypeQuantity_Actual.size()));
		}
		
		for(int i=0; i<ticketTypeQuantity_Expected.size(); i++){
			if(!ticketTypeQuantity_Expected.get(i).equals(ticketTypeQuantity_Actual.get(i))){
				throw new ErrorOnPageException("ticket type number is wrong for ticket type - "+ticketTypes_expected[i], 
						ticketTypeQuantity_Expected.get(i), ticketTypeQuantity_Actual.get(i));
			}
			logger.info("Successfully verify ticket type number - "+ticketTypeQuantity_Expected.get(i)+" for ticket type - "+ticketTypes_expected[i]);
		}
	}
	
	public void clickDetailsRequiredLink(){
		browser.clickGuiObject(".class", "Html.A",".text", "Details Required");
	}
	
	public void clickDetailsRequiredLink(String tourID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Details Required", ".href", new RegularExpression("\\/tourOrderDetails\\.do\\?tourId="+tourID+".*", false));
		browser.clickGuiObject(p);
	}
	
	public void clickChangeDetailsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Details");	
	}
	
	public void clickChangeDetailsLink(String tourID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Change Details", ".href", new RegularExpression("\\/tourOrderDetails\\.do\\?tourId="+tourID+".*", false));
		browser.clickGuiObject(p);	
	}
	
	/**
	 * Check 'Details Required' link exist 
	 * @param tourID
	 * @return
	 */
	public boolean checkDetailsRequiredLinkExist(String tourID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Details Required", ".href", new RegularExpression("\\/tourOrderDetails\\.do\\?tourId="+tourID+".*", false));
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Verify 'Details Required' link exist or not
	 * @param tourID
	 * @return
	 */
	public void verifyDetailsRequiredLinkExist(String tourID, boolean existed_Expected){
		boolean existed_Actual = this.checkDetailsRequiredLinkExist(tourID);
		if(existed_Expected!=existed_Actual){
			throw new ErrorOnPageException("'Details Required' link should "+(existed_Expected?" ":"not")+" be existed.");
		}
		logger.info("Successfully verify 'Details Required' link "+(existed_Expected?"":"not")+" exists for tour ID--"+tourID);
	}
	
	/**
	 * Check 'Change Details' link exist 
	 * @param tourID
	 * @return
	 */
	public boolean checkChangeDetailsLinkExist(String tourID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Change Details", ".href", new RegularExpression("\\/tourOrderDetails\\.do\\?tourId="+tourID+".*", false));
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Get number of "Change Details" link
	 * @param tourID
	 * @return
	 */
	public int getNumOfChangeDetailsLink(String tourID){
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text", "Change Details", ".href", new RegularExpression("\\/tourOrderDetails\\.do\\?tourId="+tourID+".*", false));
        IHtmlObject[] objs = browser.getHtmlObject(p);
        if(objs==null || objs.length<1){
        	throw new ItemNotFoundException("'Change Details' objects can't be found for tour id ----- "+tourID);
        }
        
        int numOfChangeDetailsLink = objs.length;
        
        Browser.unregister(objs);
        return numOfChangeDetailsLink;
	}
	
	/**
	 * Verify the number of "Change Details" link
	 * @param tourID
	 * @param numOfChangeDetailsLink_Expected
	 */
	public void verifyNumOfChangeDetailsLink(String tourID, int numOfChangeDetailsLink_Expected){
		int numOfChangeDetailsLink_Actual = this.getNumOfChangeDetailsLink(tourID);
		if(numOfChangeDetailsLink_Actual!=numOfChangeDetailsLink_Expected){
			throw new ErrorOnPageException("The number of 'Change Details' link is wrong!", 
					String.valueOf(numOfChangeDetailsLink_Expected), String.valueOf(numOfChangeDetailsLink_Actual));
		}
		logger.info("Successfully verify the number of 'Change Details' link is:"+numOfChangeDetailsLink_Expected);
	}
	
	/**
	 * Verify 'Change Details' link exist or not
	 * @param tourID
	 * @return
	 */
	public void verifyChangeDetailsLinkExist(String tourID, boolean existed_Expected){
		boolean existed_Actual = this.checkChangeDetailsLinkExist(tourID);
		if(existed_Expected!=existed_Actual){
			throw new ErrorOnPageException("'Change Details' link should "+(existed_Expected?"":"not")+" be existed.");
		}
		logger.info("Successfully verify 'Change Details' link "+(existed_Expected?"":"not")+" exists for tour ID--"+tourID);
	}
	
	/**
	 * Click "Make More Reservations" link
	 */
    public void clickMakeMoreReservationsLink(){
    	browser.clickGuiObject(".id", "reservemore", ".text", "Make More Reservations");
    }
	
//    public boolean checkContinueShoppingExist(){
//    	return browser.checkHtmlObjectExists(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
//    }
    
//	/**
//	 * Click "Continue shopping" link
//	 */
//    public void clickContinueShoppinLink(){
//    	browser.clickGuiObject(".id", "reservemore", ".text", new RegularExpression("Continue shopping", false));
//    }
    
    /**
     * Get delivery method via gave tour name
     * @param tourName
     * @return
     */
    public String getDeliveryMethod(String tourName){
    	String deliveryMethod = "";

    	IHtmlObject[] objs = browser.getTableTestObject(shoppingCartTableProp());
    	if(objs==null || objs.length<1){
    		throw new ObjectNotFoundException("No related TR objects can be found.");
    	}

    	IHtmlTable table = (IHtmlTable)objs[0];
    	for(int i=1; i<table.rowCount(); i++){
    		if(!StringUtil.isEmpty(table.getCellValue(i, 2)) && table.getCellValue(i, 2).contains(tourName)){
    			deliveryMethod = table.getCellValue(i+1, 1).replace("(", "").replace(")", "").trim();
    		}
    	}

    	if(StringUtil.isEmpty(deliveryMethod)){
    		throw new ErrorOnPageException("No 'Delivery Method' can be found.");
    	}

    	Browser.unregister(objs);
    	return deliveryMethod;
    }
    
    /**
     * Verify delivery method
     * @param tourName
     * @param deliveryMethod_Excepted
     */
    public void verifyDeliveryMethod(String tourName, String deliveryMethod_Excepted){
    	String deliveryMethod_Actual = this.getDeliveryMethod(tourName);
    	if(!deliveryMethod_Excepted.equals(deliveryMethod_Actual)){
    		throw new ErrorOnPageException("Delivery Method is wrong!", deliveryMethod_Excepted, deliveryMethod_Actual);
    	}
    	logger.info("Successfully verify delivery method - "+deliveryMethod_Excepted);
    }
    
    /**
     * Check timer is shown or not 
     * @return
     */
    public boolean isTimershown(){
    	return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "bestbefore");
    }
    
    /**
     * Verify timer is shown or not
     * @param existing
     */
    public void verifyTimerExistingOrNot(boolean existing){
    	boolean actualResult = this.isTimershown();
    	if(actualResult!=existing){
    		throw new ErrorOnDataException("Timer should"+(existing?"":" not")+" shown.");
    	}
    	logger.info("Successfully verify timer is"+(existing?"":" not")+" shown.");
    }
    
    public boolean isTimerExist(){
    	return browser.checkHtmlObjectExists(timerProp()) || browser.checkHtmlObjectExists(expiredProp());
    }
    
    /**
     * Get timer objects
     * @return
     */
    
    public IHtmlObject[] getTimerObjs(){
//    	HtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "bestbefore");
    	IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.Span", ".id", "countdown"); // Lesley[20130905]: update due to timer UI changed on RA.com
    	if(objs.length<1){
    		objs = browser.getHtmlObject(".id", "timer");
    	}
    	if(objs.length<1 ){
    		logger.warn("Timer object can't be found. Hold Expired");
    		objs = browser.getHtmlObject(".class", "Html.Span", ".id", "expired");
    	}
    	return objs;
    }
    
    /**
     * Get timer
     * @return
     */
    public String getTimer(){
    	Browser.sleep(OrmsConstants.PAGELOADING_SYNC_TIME);
    	IHtmlObject[] objs = this.getTimerObjs();
    	String timer = objs[0].text(); 
    	Browser.unregister(objs);
    	logger.info("Timer:"+timer);
    	return timer;
    }
    
    /**
     * Verify timer value
     * @param timer
     */
    public void verifyTimer(int timer){
    	int actualTimer = this.getRemainingTime();
    	if(actualTimer > timer || actualTimer < timer){//Sara[12/2/2013] actual timer may be logger or shorter than expected based on network speed 
    		logger.info("Successfully verify Timer:"+timer);
    	}else throw new ErrorOnDataException("Timer is wrong!", timer, actualTimer);
    }
    
    public void verifyTimerFormat(String format){
    	String timerFromUI = getTimer();
    	if(!timerFromUI.matches(format)){
    		throw new ErrorOnPageException("Timer format is wrong!", format, timerFromUI);
    	}
    	logger.info("Timer format is right!");
    }
    
    /** Get remaining time from timer */
    public int getRemainingTime() {
    	String timer = this.getTimer();
    	int totalSec = 0;
    	String[] time = RegularExpression.getMatches(timer, "\\d+");
    	if (time.length == 2) { // include minute and second
    		totalSec = Integer.valueOf(time[0]) * 60 + Integer.valueOf(time[1]);
    	} else if (time.length == 1){
    		totalSec = Integer.valueOf(time[0]); // only second
    	} else {
    		logger.info("Hold expired!");//Sara[8/20/2013]
    	}
    	return totalSec;
    }
    
    /** Verify timer count down start */
    public void verifyTimerStart() {
    	int time1 = this.getRemainingTime();
    	Browser.sleep(1);
    	int time2 = this.getRemainingTime();
    	if (time1 <= time2) {
    		throw new ErrorOnPageException("Shopping cart timer should start!");
    	}
    	logger.info("Verify Shopping cart timer start correctly!");
    }
    
    /** verify time restart. */
    public void verifyTimeRestart(int totalTime) {
    	Browser.sleep(1); // for timer loading
    	int time = this.getRemainingTime();
    	if (time > totalTime || time < totalTime-30) {
    		throw new ErrorOnPageException("Time should be restart! total time is " + totalTime + "; actual remaining time is " + time);
    	}
    	logger.info("Verify time restart correctly!");
    }
    
    public void clickItemsInCartLink(){
    	Property[] p1 = Property.toPropertyArray(".id", "cartLink");
    	Property[] p2 = Property.toPropertyArray(".href", new RegularExpression("/viewShoppingCart\\.do", false));
    	browser.clickGuiObject(Property.atList(p1, p2), true, 0);
    }
    
    public void waitForNoLongerOnHoldMes(){
    	browser.searchObjectWaitExists(".class", "Html.SPAN", ".text", "No Longer on Hold");
    }
    
    public void synNoLongerOnHoldMesAfterClickItemsInCartLink(){
    	clickItemsInCartLink();
    	waitForNoLongerOnHoldMes();
    }
    
	/**
	 * in shopping cart page, for each order item, get results that if each item contains expected message
	 * @param parks
	 * @param noLongerOnHoldMes
	 * @return
	 */
	public boolean[] areExpectedMesesDisplayedByPark(String[] parks, String noLongerOnHoldMes) {
		IHtmlObject[] obj = browser.getTableTestObject(shoppingCartTableProp());
		IHtmlTable table = (IHtmlTable) obj[0];
		boolean[] results = new boolean[parks.length];
		
		for(int k=0; k<parks.length; k++){
			for(int i=0; i<table.rowCount(); i++) {
				String nameRow = table.getCellValue(i, 1);
				if(null !=nameRow && nameRow.contains(parks[k])) {
					results[k]  = nameRow.contains(noLongerOnHoldMes);
				}
				if(results.length!=k+1) {
					throw new ItemNotFoundException("Park name:"+parks[k]+" not found in cart!");
				}
			}
		}
		
		Browser.unregister(obj);
		Browser.unregister(table);
		return results;
	}
}
