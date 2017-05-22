package com.activenetwork.qa.awo.pages.web.bw;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.PermitEmergencyContact;
import com.activenetwork.qa.awo.datacollection.legacy.PermitGroupMembers;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public class BwReservationDetailsPage extends UwpPage {

	private RegularExpression pageMark = new RegularExpression(
			"(Permit Reservation|Lottery Application) Details .*", false);

	private static BwReservationDetailsPage _instance = null;

	public static BwReservationDetailsPage getInstance() {
		if (null == _instance)
			_instance = new BwReservationDetailsPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",	pageMark);
	}
	
	 /**
	 * Verify \ufffdRePrint Permit\ufffd button exist.
	 */
	public void verifyReprintPermitButtonExist() {
		logger.info("Verify \ufffdRePrint Permit\ufffd button exist.");
		if(!isReprintBtnExisting()){
			throw new ErrorOnPageException("Can't find 'RePrint Permit' button.");
		}
	}
	
	/**
	 * Verify \ufffdPrint Permit\ufffd button exist in Reservation Details page
	 */
	public void verifyPrintPermitButtonExist() {
		logger.info("Verify \ufffdPrint Permit\ufffd button exist.");
		if(!isPrintBtnExisting()){
			throw new ErrorOnPageException("Can't find 'Print Permit' button.");
		}
	}

	/**
	 * Verify whether the reservation is a lottery.
	 * @return
	 */
	public boolean isLottery() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", pageMark);
		String text = objs[0].getProperty(".text").toString();
		return text.indexOf("Lottery Application Details") > 0;
	}

	/**
	 * Cancel a reservation.
	 */
	public void clickCancelReservation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Reservation");
	}

	/**
	 * update reservation
	 */
	public void clickUpdateReservation() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Update Reservation");
	}
	
	public boolean checkUpdateReservationLinkExit(){
	     return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Update Reservation");
	}
	
	public void verifyUpdateReservationLinkExit(boolean existed_Extected){
		boolean existed_Actual = this.checkUpdateReservationLinkExit();
		if(!MiscFunctions.compareResult("Update Reservation link", existed_Extected, existed_Actual)){
			throw new ErrorOnPageException("Update Reservation link should "+(existed_Extected?"":"not ")+"exist.");
		}
	}
	
	/**
	 * Click on link to go to change permit details page.
	 */
	public void clickChangePermitDetails() {
		browser.clickGuiObject(".class", "Html.A", ".id", "changethis");//".text","Change Group Size/ Permit Details");
	}

	/**
	 * Verify the order number, status, fees the same with the given data.
	 * @param vData - given compare data
	 * @throws ItemNotFoundException
	 */
	public void verifyOrderData(String vData) throws ItemNotFoundException {
		String data = "";
		String delimit = ":";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "entrancedetail");
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		//get order number and status
		String[] s = text.split(" ", 6);
		data += s[2] + delimit + s[4] + delimit;

		//get fees and payment infos
		objs = browser.getTableTestObject(".id", "paymentdetail");
		text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		s = RegularExpression.getMatches(text, "[0-9]+\\.[0-9][0-9]");
		data += Double.parseDouble(s[2]) + delimit + Double.parseDouble(s[3]);
		//compare data
		
		if (!data.equalsIgnoreCase(vData))
			throw new ItemNotFoundException(
					"The reservation details infor is not correct.");
	}

	/**
	 * Retrieve the order status.
	 * @return - order status
	 */
	public String getStatus() {
		String toReturn;
		IHtmlObject[] objs;
		if (isLottery()) {
			RegularExpression regex = new RegularExpression("Application \\#: 6-[0-9]+ .*", false);
			objs = browser.getTableTestObject(".text", regex);
			String text = objs[0].getProperty(".text").toString();
			int i = text.indexOf("Status");
			String[] s = text.substring(i).split(" ", 3);
			toReturn = s[1];
		} else {
			objs = browser.getTableTestObject(".id", new RegularExpression("entrancedetail|resvDetails", false)); //Sara[8/14/2013] for Itinerary permit
			String text = objs[0].getProperty(".text").toString();
			int lIndex = text.indexOf("Status:") + 7;
			int rIndex = text.indexOf("Permit Type");
			if(lIndex<=rIndex){
				toReturn = text.substring(lIndex, rIndex).trim();
			}else toReturn = text.split("Status:")[1].trim().split(" ")[0].trim();
		}
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Retrieve the order number.
	 * @return - order number
	 */
	public String getOrderNumber() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "entrancedetail");
		String text = objs[0].getProperty(".text").toString();
		String[] s = text.split(" ", 6);

		Browser.unregister(objs);
		return s[2];
	}

	/**
	 * Verify current order status is the same as expected.
	 * @param status - expect status.
	 * @throws ItemNotFoundException
	 */
	public void verifyStatus(String status) throws ItemNotFoundException {
		String curStatus = getStatus();
		if (curStatus.indexOf(status) < 0)
			throw new ItemNotFoundException("The current status " + curStatus
					+ " is not expected " + status);
	}
 
	/**
	 * Click on Undo issue link.
	 */
	public void clickUndoIssuance() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo Issuance");
	}
	
	public void clickIssuePermit() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Issue Permit");
	}
	
	private String getEntranceDetailContent(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("(entrancedetail|resvDetails)", false), ".className", "contable splitPanel");
		IHtmlTable table = (IHtmlTable) objs[0];
		
		IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TR", ".text", 
				new RegularExpression("^Permit #:", false), table);
		
		String text = tds[0].text();
		Browser.unregister(objs, tds);
		return text;
	}
	
	private String getEntranceDetailsElement(String regx, String permitInfoTitle){
		String tableContent = this.getEntranceDetailContent();
		return RegularExpression.getMatches(tableContent.split(permitInfoTitle+":")[1], regx)[0];
	}
	
	public void verifyEntranceDetailsElementExist(String permitInfoTitle, boolean existed){
		String tableContent = this.getEntranceDetailContent();
		if(!MiscFunctions.compareResult(permitInfoTitle+" exists oor not", existed, tableContent.contains(permitInfoTitle))){
			throw new ErrorOnPageException();
		}
	}
	
	public String getEntryDate(){
		return getEntranceDetailsElement("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Entry Date");
	}

	public void verifyEntryDateExisted(boolean existed){
		verifyEntranceDetailsElementExist("Entry Date", existed);
	}
	
	public String getExitDate(){
		return getEntranceDetailsElement("[A-Za-z]{3} [A-Za-z]{3} \\d+ \\d{4}", "Exit Date");
	}
	
	public void verifyExitDateExisted(boolean existed){
		verifyEntranceDetailsElementExist("Exit Date", existed);
	}
	
	public void verifyEntranceExisted(boolean existed){
		verifyEntranceDetailsElementExist("Entrance", existed);
	}
	
	
	
	public String getLengthOfStay(){
		return getEntranceDetailsElement("\\d+ (Day|Night)\\(s\\)", "Length Of Stay");
	}
	public void verifyLengthOfStay(String expectedValue){
		String actualValue = this.getLengthOfStay();
		if(!expectedValue.equals(expectedValue)){
			throw new ErrorOnPageException("Length of stay is wrong in 'Permit Reservation Details' page!", expectedValue, actualValue);	
		}
		logger.info("Successfully verify 'Length of stay' as '"+expectedValue+"' in 'Permit Reservation Details' page.");
	}
	
	public String getGroupSizeNum() {
		IHtmlObject[] objs=browser.getTableTestObject(".id", "permitdetail");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find entrance details table..");
		}
		IHtmlTable table=(IHtmlTable)objs[0];
		int row=table.findRow(0, new RegularExpression("Group Size:.*",false));
		
		String content=table.getCellValue(row, 0);
		
		String groupSize=content.split(":")[1].split("\\(")[0].trim();
		Browser.unregister(objs);
		return groupSize;
	}
	
	public void clickPrintPermit() {
		browser.clickGuiObject(".id", "printbtn", ".text", "Print Permit");
	}

	public boolean isPrintBtnExisting(){
		return browser.checkHtmlObjectExists(".id", "printbtn", ".text", "Print Permit");
	}
	
	public void verifyPrintBtnExist(boolean flag){
		boolean actualExisted = this.isPrintBtnExisting();
		if(actualExisted!=flag){
			throw new ErrorOnDataException("Print Permit button should "+(flag?"":"not")+" be existed");
		}
	}
	
	
	public boolean isReprintBtnExisting() {
		return browser.checkHtmlObjectExists(".id", "printbtn", ".text", "Reprint Permit");
	}

	public void clickReprintPermit() {
		browser.clickGuiObject(".id", "printbtn", ".text", "Reprint Permit");
	}

	public void clickRequestConfirmationLetterLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Confirmation Letter");
	}
	
	public String getConfirmationMessage(){
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.DIV",".className",new RegularExpression("msg.*",false));
		
		if(objs==null || objs.length<1){
			return "";
		}
		String msg=objs[0].text();
		Browser.unregister(objs);
		return msg;
	}
	
	public String getPermitResDetailsElement(String htmlProperty, String startRegu){
		RegularExpression pattern = new RegularExpression("^"+startRegu+":.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html."+htmlProperty, ".text", pattern);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("The object with startRegu="+startRegu+" and htmlProperty="+htmlProperty);
		}
		String valueString = objs[0].text().split(startRegu+":")[1].trim();
		
		Browser.unregister(objs);
		return valueString;
	}
	
	public boolean checkPermitResDetailsElementExist(String parentObjID, String htmlProp, String startRegu){
		RegularExpression pattern = new RegularExpression("^"+startRegu+":.*", false);
		Property[] parentP = Property.toPropertyArray(".id", parentObjID);
		Property[] childP = Property.toPropertyArray(".class", "Html."+htmlProp, ".text", pattern);
		if(StringUtil.notEmpty(parentObjID)){
			return browser.checkHtmlObjectExists(Property.atList(parentP, childP));
		}
		return  browser.checkHtmlObjectExists(childP);
	}
	
	public void verifyVerifyPermitResDetailsElementExist(String parentP, String htmlProperty, String startRegu, boolean existed){
		boolean actualResult = checkPermitResDetailsElementExist(parentP, htmlProperty, startRegu);
		if(actualResult!=existed){
			throw new ErrorOnPageException("Permit reservation details element starting with '"+startRegu+"' should "+(existed?"":"not ")+"exist.");
		}
		logger.info("Permit reservation details element starting with '"+startRegu+"' is "+(existed?"":"not ")+"existing.");
	}
	
	public void verifyPermitResDetailsElement(String htmlProperty, String startRegu, String expectedValue){
		String actualValue = getPermitResDetailsElement(htmlProperty, startRegu);
		if(!actualValue.equals(expectedValue)){
			throw new ErrorOnPageException(startRegu +" is wrong.", expectedValue, actualValue);
		}
		logger.info(startRegu+" is right as:"+actualValue);
	}
	
	public String getPermitType(){
		return this.getPermitResDetailsElement("LI", "Permit Type");
	}
	
	public void verifyPermitType(String expectedValue){
		verifyPermitResDetailsElement("LI", "Permit Type", expectedValue);
	}
	
	public String getOrderStatus(){
		return this.getPermitResDetailsElement("LI", "Status");
	}
	
	public String getGuideTrip(){
		return this.getPermitResDetailsElement("LI", "Type of Guided Trip");
	}
	
	/**
	 * Sample data: Launch Point: 02-Boundary Creek - Take Out Point: 02-Boundary Creek
	 * @return
	 */
	public String getLaunchPoint(){
		String lauchPoint = this.getPermitResDetailsElement("LI", "Launch Point");
		if(lauchPoint.contains("- Take Out Point:")){
			lauchPoint = lauchPoint.split("- Take Out Point:")[0].trim();
		}
		return lauchPoint.replace("-", " ");
	}
	
	public String getTakeOutPoint(){
		String takeOutPoint = this.getPermitResDetailsElement("LI", "Launch Point");
		if(!takeOutPoint.contains("Take Out Point:"))
			takeOutPoint = this.getPermitResDetailsElement("LI", "Take Out Point");
		else
			takeOutPoint = takeOutPoint.split("Take Out Point:")[1].trim();
		return takeOutPoint.replace("-", " ");
	}
	
	public String getTripItinerary(){
		return this.getPermitResDetailsElement("TD", "Trip Itinerary");
	}
	
	public String getTravelMethod(){
		return this.getPermitResDetailsElement("LI", "Travel Method");
	}
	
	public String getDeliveryMethod(){
		return this.getPermitResDetailsElement("LI", "Permit Delivery Method");
	}
	
    public String getNumOfWaterCraft(){
    	return this.getPermitResDetailsElement("LI", "Number of Watercraft");
    }	
    
	public String getNumberOfStock(){
		return this.getPermitResDetailsElement("LI", "Number of stock");
	}
	
	public String getSpecificStockNum(String stockName){
		return this.getPermitResDetailsElement("UL", stockName);
	}
	
	public String getNumberOfPets(){
		return this.getPermitResDetailsElement("LI", "Number of Pets");
	}
	
	public String getSpecificPetNum(String petName){
		return this.getPermitResDetailsElement("UL", petName);
	}
	
	public String getSpecificWatercraftNum(String watercraftName){
		return this.getPermitResDetailsElement("UL", watercraftName);
	}
	
	public String getPermitIssuingStation(){
		return this.getPermitResDetailsElement("LI", "Permit Issuing Station");
	}
	
	public String getGroupSize(){
//		return this.getPermitResDetailsElement("LI", "Group Size").replaceAll("\\(.*\\)", "").trim();
		return this.getPermitResDetailsElement("LI", "Group Size");
	}
	
	//Verify no group size in Permit Details section
	public void verifyGroupSizeExist(boolean existed){
		verifyVerifyPermitResDetailsElementExist("permitdetail", "LI", "Group Size", existed);
	}
	
	public String getPersonTypeAndNums(String firstPersonType){
		return firstPersonType +": "+ this.getPermitResDetailsElement("TR", firstPersonType);
	}
	
	public String getUseFee(){
		return this.getPermitResDetailsElement("LI", "Use Fee").split("\\$")[1];
	}
	
	public void verifyUseFeeAmount(String useFee){
		String useFeeFromUI = getUseFee();
		if(!useFee.equals(useFeeFromUI)){
			throw new ErrorOnPageException("Use fee abount is wrong.", useFee, useFeeFromUI);
		}
		logger.info("Use fee amount is correct:"+useFee);
		
	}
	public String getReservationFee(){
		return this.getPermitResDetailsElement("LI", "Reservation Fee").split("\\$")[1];
	}
	
	public String getTotalPrice(){
		return this.getPermitResDetailsElement("LI", "Total");
	}
	
	public String getTotal(){
		return this.getPermitResDetailsElement("LI", "Total").split("\\$")[1];
	}
	
	public String getTotalPayment(){
		return this.getPermitResDetailsElement("LI", "Total Payment");
	}
	
	public String getTotalPaymentAmount(){
		return this.getPermitResDetailsElement("LI", "Total Payment").split("\\$")[1];
	}
	
	public String getBalance(){
		String balance = this.getPermitResDetailsElement("LI", "Balance").split("\\$")[1];
		if(balance.contains("Pay Balance")){
			balance = balance.split("Pay Balance")[0].trim();
		}
		
		return balance;
	}
	public String getBalancePrice(){
		String balance = this.getPermitResDetailsElement("LI", "Balance");
		if(balance.contains("Pay Balance")){
			balance = balance.split("Pay Balance")[0].trim();
		}
		
		return balance;
	}
	
	public boolean isGropMembers(){
		RegularExpression pattern = new RegularExpression("^Group Members*", false);
		return browser.checkHtmlObjectExists(".className", "groupTable", ".text", pattern);
	}
	
	public String getGroupMembers(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "groupTable");
		String valueString = objs[0].text().split("Group Members")[1].trim();
		Browser.unregister(objs);
		return valueString;
	}
	
	public String getEmergencyContacts(String emergencyContactLable){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "groupTable");
		String valueString = objs[0].text().split(emergencyContactLable)[1].trim();
		Browser.unregister(objs);
		return valueString;
	}
	
	public boolean isAlternateLeader(){
		RegularExpression pattern = new RegularExpression("^Alternate Leader Information.*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", pattern);
	}
	
	public String getAlternateLeaderInfo(){
		RegularExpression pattern = new RegularExpression("^Alternate Leader Information.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", pattern);
		String valueString = objs[0].text().split("Alternate Leader Information")[1].trim();
		Browser.unregister(objs);
		return valueString;
	}
	
	/**
	 * Get emergency contracts info
	 * Lottery permit order: Html.TR
	 * Permit Order: Html.TABLE
	 * @param label
	 * @return
	 */
	public String getEmergencyContractsInfo(String label){
		String regx = label.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		RegularExpression pattern = new RegularExpression("^"+regx+".*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", pattern);
		if(objs==null|| objs.length<1){
			objs= browser.getHtmlObject(".class", "Html.TR", ".text", pattern);
			if(objs==null || objs.length<1){
				throw new ObjectNotFoundException("Can't found object for label:"+label);
			}
		}
		String valueString = objs[0].text().split(regx)[1].trim();
		Browser.unregister(objs);
		return valueString;
	}
	
	public boolean isEmergencyContracts(String label){
		RegularExpression pattern = new RegularExpression("^"+label.replace("(", "\\(").replace(")", "\\)")+".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", pattern);
		
	}
	
	public void verifyPermitOrderStatus(String permitResStatus){
		String actualOrderStatusString = this.getOrderStatus();
		if(!permitResStatus.equals(actualOrderStatusString)){
			throw new ErrorOnDataException("The order status is incorrect. " +
					"Actual one:"+actualOrderStatusString+", Expect one:"+permitResStatus);
		}
	}
	
	public void clickMakeAnotherReservationLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Make Another Reservation");
	}
	
	public boolean checkConfirmPermitResButtonExist(){
		return browser.checkHtmlObjectExists(".id", "confirmbtn");
	}
	
	public boolean checkConfirmPermitResLinkExist(){
		return browser.checkHtmlObjectExists(".id", "confirmPermitReservation");
	}
	
	public void clickConfirmPermitResButton(){
		browser.clickGuiObject(".id", "confirmbtn");
	}
	
	public void clickConfirmPermitResLink(){
		browser.clickGuiObject(".id", "confirmPermitReservation");
	}

	/**
	 * Verify whether confirmation permit reservation button exist or not
	 * @param flag  --true: button exists
	 *              --false: button doesn't exist
	 */
	public void verifyConfirmPermitResButtonExist(boolean flag){
		boolean actualExisted = this.checkConfirmPermitResButtonExist();
		if(actualExisted!=flag){
			throw new ErrorOnDataException("The \"Confirm Permit Reservation\" button should "+(flag?"be":"not be")+" existed.");
		}else{
			logger.info("Successfully verify the \"Confirm Permit Reservation\" button "+(flag?"be":"not be")+" existed.");
		}
	}

	/**
	 * Verify whether confirmation permit reservation link exist or not
	 * @param flag  --true: link exists
	 *              --false: link doesn't exist
	 */
	public void verifyConfirmPermitResLinkExist(boolean flag){
		boolean actualExisted = this.checkConfirmPermitResLinkExist();
		if(actualExisted!=flag){
			throw new ErrorOnDataException("The \"Confirm Permit Reservation\" link should "+(flag?"be":"not be")+" existed.");
		}else{
			logger.info("Successfully verify the \"Confirm Permit Reservation\" link "+(flag?"be":"not be")+" existed.");
		}
	}
	
	public String getPreferredChoiceContent(){
		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text",  new RegularExpression("^Preferred Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String preferredChoiceContent = objs[0].text();
		
		Browser.unregister(objs);
		return preferredChoiceContent;
	}
	
	public String getAlternativeChoiceContent(){
		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text",  new RegularExpression("^Alternative Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String alternativeChoiceContent = objs[0].text();
		
		Browser.unregister(objs);
		return alternativeChoiceContent;
	}
	
	public void verifyLotteryPreferredChoice(PermitInfo choicePermit){
		logger.info("Verify 'Preferred Choice' permit infomation.");
		boolean result = true;
		
		String choiceContent = this.getPreferredChoiceContent();
		logger.info("Lottery Preferred choice content:"+choiceContent);
		String choiceValue = "";
		
		choiceValue = "Permit Type: "+choicePermit.permitType;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred permit type! Expected Result:"+choiceValue);
		}

		choiceValue = "Entry Date: "+DateFunctions.formatDate(choicePermit.entryDate, "E MMM dd yyyy");
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred entry date! Expected Result:"+choiceValue);
		}

		choiceValue = "Entrance: "+choicePermit.entrance; //"Trail/Zone: "+
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred Trail/Zone! Expected Result:"+choiceValue);
		}

		choiceValue = "Group Size:"+choicePermit.groupSize;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred Group size! Expected Result:"+choiceValue);
		}

		choiceValue = "Exit Date: "+DateFunctions.formatDate(choicePermit.exitDate, "E MMM dd yyyy");
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred exit date! Expected Result:"+choiceValue);
		}

		choiceValue = "Exit Point: "+choicePermit.exitPoint;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify preferred exit Point! Expected Result:"+choiceValue);
		}
		
		if(!result){
			throw new ErrorOnDataException("Failed to verify 'Preferred Choice' permit infomation. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all 'Preferred Choice' permit infomation.");
	}
	
	public void verifyLotteryAlternativeChoice(PermitInfo choicePermit){
		logger.info("Verify 'Alternative Choice' permit infomation.");
	    boolean result = true;
		
		String choiceContent = this.getAlternativeChoiceContent();
		logger.info("Lottery Alternative choice content:"+choiceContent);
		String choiceValue = "";

		choiceValue = "Permit Type: "+choicePermit.alternativePermitType;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative permit type! Expected Result:"+choiceValue);
		}

		choiceValue = "Entry Date: "+DateFunctions.formatDate(choicePermit.alternativeEntryDate, "E MMM dd yyyy"); //Tue Dec 04 2012
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative Entry date! Expected Result:"+choiceValue);
		}
		
		choiceValue = "Entrance: "+choicePermit.alternativeEntrance;//"Trail/Zone: "+
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative Entrance! Expected Result:"+choiceValue);
		}

		choiceValue = "Group Size:"+choicePermit.alternativeGroupSize;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative group size! Expected Result:"+choiceValue);
		}

		choiceValue = "Exit Date: "+DateFunctions.formatDate(choicePermit.alternativeExitDate, "E MMM dd yyyy"); //Tue Dec 04 2012
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative Exit Date! Expected Result:"+choiceValue);
		}

		choiceValue = "Exit Point: "+choicePermit.alternativeExitPoint;
		if(!choiceContent.contains(choiceValue)){
			result &= false;
			logger.info("Failed to verify alternative Exit point! Expected Result:"+choiceValue);
		}
		
		if(!result){
			throw new ErrorOnDataException("Failed to verify 'alternative Choice' permit infomation. Please check details info from previous logs.");
		}
		logger.info("Successfully verify all 'alternative Choice' permit infomation.");
	}
	
	public void verifyLotteryPreferencesInfo(PermitInfo choicePermit){
		this.verifyLotteryPreferredChoice(choicePermit);
		this.verifyLotteryAlternativeChoice(choicePermit);
	}
	
	//Launch Point: "02-Boundary Creek" displays in UI
	public void verifyLaunchPoint(String lauchPoint){
		String actualLauchPoint = this.getLaunchPoint();
		if(!MiscFunctions.compareResult("Lauch Point", lauchPoint, actualLauchPoint)){
			throw new ErrorOnPageException("Lauch Point is wrong!");
		}
	}
	
	//Take out point: "02-Boundary Creek" displays in UI
	public void verifyTakeOutPoint(String takeOutPoint){
		String actualTakeOutPoint = this.getTakeOutPoint();
		if(!MiscFunctions.compareResult("Take Out Point", takeOutPoint, actualTakeOutPoint)){
			throw new ErrorOnPageException("Take Out Point is wrong!");
		}
	}
	
	//Entry Date
	public void verifyEntryDate(String entryDate){
		String actualEntryDate = this.getEntryDate();
		if(!MiscFunctions.compareResult("Entry Date", entryDate, actualEntryDate)){
			throw new ErrorOnPageException("Entry Date is wrong!");
		}
	}
	
	//Exit Date
	public void verifyExitDate(String exitDate){
		String actualExitDate = this.getExitDate();
		if(!MiscFunctions.compareResult("Exit Date", exitDate, actualExitDate)){
			throw new ErrorOnPageException("Exit Date is wrong!");
		}
	}
	
	//Type of Guided Trip
	public void verifyGuidedTrip(String guildTrip){			
		String actualGuildTrip = this.getGuideTrip();
		if(!MiscFunctions.compareResult("Guided Trip", guildTrip, actualGuildTrip)){
			throw new ErrorOnPageException("Guided Trip is wrong!", guildTrip, actualGuildTrip);
		}
		logger.info("Successfully verify 'Guided Trip': "+guildTrip);
	}
	
	//One Trip Itinerary record
	public void verifyTripItinerary(String entryDate, String tripItineraryLocation){
		String actualTripItinerary = this.getTripItinerary();
		String expectedTripItinerary = "";
		expectedTripItinerary += DateFunctions.formatDate(entryDate, "E MMM dd yyyy")+": "+tripItineraryLocation;
		if(!MiscFunctions.compareResult("Single Guided Trip", expectedTripItinerary, actualTripItinerary)){
			throw new ErrorOnPageException("Single Guided Trip is wrong!", expectedTripItinerary, actualTripItinerary);
		}
		logger.info("Successfully verify Single 'Trip Itinerary':"+expectedTripItinerary);
	}	
	
	//More than one Trip Itinerary records
	public void verifyTripItinerary(String entryDate, String[] tripItineraryLocations){
		String actualTripItinerary = this.getTripItinerary();
		String expectedTripItinerary = "";
		for(int i=0; i<tripItineraryLocations.length; i++){
			String tripItineraryDate = DateFunctions.getDateAfterGivenDay(entryDate, i);
			expectedTripItinerary += DateFunctions.formatDate(tripItineraryDate, "E MMM dd yyyy")+": "+tripItineraryLocations[i]+" ";
		}
		expectedTripItinerary=expectedTripItinerary.trim();
		if(!MiscFunctions.compareResult("Mutiple 'Trip Itinerary'", expectedTripItinerary, actualTripItinerary)){
			throw new ErrorOnPageException("Multiple Guided Trip is wrong!", expectedTripItinerary, actualTripItinerary);
		}
		logger.info("Successfully verify Mutiple 'Trip Itinerary':"+expectedTripItinerary);
	}

	//Member types and Member numbers
	public void verifyMemberTypesAndNumbers(String[] memberTypes, String[] memeberNums){
		//Max member numbers
		int expectMemberNum = 0;
		for(int i=0; i<memeberNums.length; i++){
			expectMemberNum += Integer.parseInt(memeberNums[i]);
		}

		String actualGroupSize = this.getGroupSize();
		String expectGroupSize = String.valueOf(expectMemberNum)+" (";
		for(int i=0; i<memberTypes.length; i++){
			if(i==memberTypes.length-1){
				expectGroupSize += memberTypes[i]+" "+memeberNums[i]+")";
			}else{
				expectGroupSize += memberTypes[i]+" "+memeberNums[i]+", ";
			}
		}
		if(!MiscFunctions.compareResult("Member Type and Num", expectGroupSize, actualGroupSize)){
			throw new ErrorOnPageException("Member Type and Num is wrong!", expectGroupSize, actualGroupSize);
		}
		logger.info("Successfully verify Member Type and Num:"+expectGroupSize);
	}
	
	/**
	 * Check if person types and numbers include entry date and exit 
	 * @param memberType
	 * @return
	 */
	public boolean isPersonTypesAndNumsIncludeEntryAndExitDate(String memberType){
		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^"+memberType+":.*", false));
		return browser.checkHtmlObjectExists(p);
	}
	
	/**
	 * Verify Person Types And Numbers With Entry And Exit Date
	 * @param memberTypes
	 * @param memeberNums
	 * @param entryDate
	 * @param exitDate
	 * Sample data:
	 * Group Size:3
     * Adult/Youth: 2:(Wed Jul 18 2012 - Wed Jul 18 2012)
     * Interagency Access Pass:1:(Wed Jul 18 2012 - Wed Jul 18 2012) 
	 */
	public void verifyPerPersonPerDayPersonTypesInfo(String[] memberTypes, String[] memeberNums, String entryDate, String exitDate){
		//Max member numbers
		int expectMemberNum = 0;
		for(int i=0; i<memeberNums.length; i++){
			expectMemberNum += Integer.parseInt(memeberNums[i]);
		}

		String actualGroupSize = this.getGroupSize()+" "+this.getPersonTypeAndNums(memberTypes[0]).replaceAll("\\s+", " ");
		
		entryDate = DateFunctions.formatDate(entryDate, "E MMM dd yyyy");
		exitDate = DateFunctions.formatDate(exitDate, "E MMM dd yyyy");
		
		String expectGroupSize = String.valueOf(expectMemberNum)+" ";
		for(int i=0; i<memberTypes.length; i++){
				expectGroupSize += memberTypes[i]+": "+memeberNums[i]+": ("+entryDate+" - "+exitDate+") ";
		}
		if(!MiscFunctions.compareResult("Member Type and Num", expectGroupSize.trim(), actualGroupSize)){
			throw new ErrorOnPageException("Member Type and Num is wrong!", expectGroupSize, actualGroupSize);
		}
		logger.info("Successfully verify Member Type and Num:"+expectGroupSize);
	}
	
	//Group size format:Group Size: 3 (All 3) if no specific person type 
	public void verifyGroupSize(String groupSize){
		String actualGroupSize = this.getGroupSize();
		String regex = " \\(All "+groupSize+"\\)";
		if(!actualGroupSize.matches(groupSize + "(" + regex + ")?")) {
			throw new ErrorOnPageException("'Group Size' is wrong!", regex, actualGroupSize);
		}
		logger.info("Successfully verify Group Size:"+regex);
	}
	
	/**
	 * 
	 * @param groupSize Such as: 4 (All 4)
	 */
	public void verifyGroupSizeWithAllOption(String groupSize){
		String actualValue = this.getGroupSize();
		String expectedRegx = groupSize + " \\(All "+groupSize+"\\)";
		if(!actualValue.matches(expectedRegx)) {
			throw new ErrorOnPageException("'Group Size' is wrong!", expectedRegx, actualValue);
		}
		logger.info("Successfully verify Group Size:"+expectedRegx);
	}
	
	//Travel Method
	public void verifyTravelMethod(String travelMethod){
		String actualTravelMethod = this.getTravelMethod();
		if(!MiscFunctions.compareResult("Traverl Method", travelMethod, actualTravelMethod)){
			throw new ErrorOnPageException("'Traverl Method' is wrong!", travelMethod, actualTravelMethod);
		}
		logger.info("Successfully verify 'Traverl Method':"+travelMethod);
	}
	
	//Number of stock
	public void verifyNumOfStocks(String[] stockTypes, String[] stockNums){
		//Total number of stock
		int expectNumOfStock = 0;
		for(int i=0; i<stockNums.length; i++){
			expectNumOfStock += Integer.parseInt(stockNums[i]);
		}
		String actualNumOfStock = this.getNumberOfStock();
		if(!MiscFunctions.compareResult("number of Stock", String.valueOf(expectNumOfStock), actualNumOfStock)){
			throw new ErrorOnPageException("The number of Stock is wrong!", String.valueOf(expectNumOfStock), String.valueOf(actualNumOfStock));
		}
		logger.info("Successfully verify number of Stock:"+expectNumOfStock);

		//Specific stock type and number
		for(int i=0; i<stockTypes.length; i++){
			String specificStockNum = this.getSpecificStockNum(stockTypes[i]);
			if(!MiscFunctions.compareResult("number of Stock for each stock type", stockNums[i], specificStockNum)){
				throw new ErrorOnPageException("The stock number is wrong about Stock("+stockTypes[i]+")", stockNums[i], specificStockNum);
			}
			logger.info("Successfully verify the numberof  Stock("+stockTypes[i]+") as:"+stockNums[i]);
		}
	}
	
	//Number of Pets
	public void verifyNumOfPets(String[] petTypes, String[] petNums){
		//Total Number of Pets
		int expectedNumOfPets = 0;
		for(int i=0; i<petTypes.length; i++){
			expectedNumOfPets += Integer.parseInt(petNums[i]);
		}
		String actualNumOfPet = this.getNumberOfPets();
		if(!MiscFunctions.compareResult("number of Pet", String.valueOf(expectedNumOfPets), actualNumOfPet)){
			throw new ErrorOnPageException("The number of Pet is wrong!", String.valueOf(expectedNumOfPets), String.valueOf(actualNumOfPet));
		}
		logger.info("Successfully verify number of Pet:"+expectedNumOfPets);

		//Specific pet type and number
		for(int i=0; i<petTypes.length; i++){
			String specificPetNum = this.getSpecificStockNum(petTypes[i]);
			if(!MiscFunctions.compareResult("number of Pet for each pert", petNums[i], specificPetNum)){
				throw new ErrorOnPageException("The pet number is wrong about Pet("+petTypes[i]+")", petNums[i], specificPetNum);
			}
			logger.info("Successfully verify the numberof Pet("+petTypes[i]+") as:"+petNums[i]);
		}
	}
	
	//Number of Watercraft
	public void verifyNumOfWaterCraft(Map<String,String> waterCraftInfo){
		//Total Number of Watercraft
		int expectedNumOfWaterCraft = 0;
		Set<Map.Entry<String, String>> set = waterCraftInfo.entrySet();
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			expectedNumOfWaterCraft += Integer.valueOf(entry.getValue());
		}
		String actualNumOfWatercraft = this.getNumOfWaterCraft();
		if(!MiscFunctions.compareResult("Total number of watercraft", expectedNumOfWaterCraft, Integer.parseInt(actualNumOfWatercraft))){
			throw new ErrorOnPageException("'Total number of watercraft' is wrong.");
		}
		
		//Specific Watercraft type and number
		for (Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			String specificWatercraftNum = this.getSpecificWatercraftNum(entry.getKey());
			if(!MiscFunctions.compareResult("The number of Watercraft:"+entry.getValue(), entry.getValue(), specificWatercraftNum)){
				throw new ErrorOnPageException("The Watercraft number is wrong.");
			}
			logger.info("Successfully verify the numberof Watercraft("+entry.getKey()+") as:"+entry.getValue());
		}
	}
	
	//Permit Delivery Method
	public void verifyDeliveryMethod(String deliveryMethod){
		String actualDeliveryMethod = this.getDeliveryMethod();
		if(!MiscFunctions.compareResult("Delivery Method", deliveryMethod, actualDeliveryMethod)){
			throw new ErrorOnPageException("Delivery Method is wrong!", deliveryMethod, actualDeliveryMethod);
		}
		logger.info("Successfully verify 'Delivery Method':"+deliveryMethod);
	}
	
	//Group Members
    public void verifyGroupMember(PermitGroupMembers groupMembers, String[] fNames, String[] lNames){
    	String actualGroupMember = this.getGroupMembers();
    	String expectGroupMember = "";
    	for(int i=0; i<groupMembers.getSize(); i++){
    		expectGroupMember += i+1+". "+groupMembers.getfNames()[i]+" "+groupMembers.getlNames()[i]+" ";
    	}
    	if(!MiscFunctions.compareResult("Group Member", expectGroupMember.trim(), actualGroupMember)){
    		throw new ErrorOnPageException("Group Member is wrong!", expectGroupMember.trim(), actualGroupMember);
    	}
    	logger.info("Successfully verify 'Group Member':"+expectGroupMember.trim());
    }	
    
	//Alternate Leader info
	public void verifyAlternateLeader(String[]fNames, String[] lNames, String[] phones){
		String actualAlternateLeader = this.getAlternateLeaderInfo();
		String expectAlternateLeader = "";
		for(int i=0; i<fNames.length; i++){
			expectAlternateLeader += fNames[i]+" "+lNames[i]+" ("+phones[i]+") ";
		}
		if(!MiscFunctions.compareResult("Alternate Leader", expectAlternateLeader.trim(), actualAlternateLeader)){
			throw new ErrorOnPageException("'Alternate Leader' is wrong!", expectAlternateLeader.trim(), actualAlternateLeader);
		}
		logger.info("Successfully verify 'Alternate Leader':"+expectAlternateLeader.trim());
	}
	
	/**
	 * Verify Emergency Contracts info
	 * @param PermitEmergencyContracts emergencyContracts_expected
	 * label: the label of emergency contracts info, which is "Display Label" in Inventory Manager
	 * size
	 * fNames
	 * lNames
	 * phones
	 * Example: qa1 test1 - 1234567890
	 */
	public void verifyEmergencyContracts(PermitEmergencyContact emergencyContracts_expected){
		logger.info("Verify Emergency Contracts info.");
		String actualEmergencyContracts = this.getEmergencyContractsInfo(emergencyContracts_expected.getEmergencyContractLabel());
		actualEmergencyContracts=actualEmergencyContracts.replaceAll("  ", "");
		String expectedEmergencyContracts = "";
		for(int i=0; i<emergencyContracts_expected.getSize(); i++){
			expectedEmergencyContracts += emergencyContracts_expected.getfNames()[i]+" "+emergencyContracts_expected.getlNames()[i]+" - "+emergencyContracts_expected.getPhones()[i]+" ";
		}
		//Replace all spaces to "", only check expected values
		if(!MiscFunctions.compareResult("Emergency Contracts", expectedEmergencyContracts.replaceAll("\\s+", ""), actualEmergencyContracts.replaceAll("\\s+", ""))){
			throw new ErrorOnPageException("'Emergency Contracts' is wrong!", expectedEmergencyContracts.replaceAll("\\s+", ""), actualEmergencyContracts.replaceAll("\\s+", ""));
		}
		logger.info("Successfully verify 'Emergency Contracts':"+expectedEmergencyContracts);
	}
	
	/**
	 * Verify Emergency contracts info exists or not
	 * @param emergencyContractsLabel
	 * @param existed_Expected
	 */
	public void verifyEmergencyContractsExist(String emergencyContractsLabel, boolean existed_Expected){
		logger.info("Start to verify Emergency Contract should "+(existed_Expected?"":"not")+" exist.");
		
		boolean existed_Actual = this.isEmergencyContracts(emergencyContractsLabel);
		if(!MiscFunctions.compareResult("Emergency Contracts", existed_Expected, existed_Actual)){
			throw new ErrorOnPageException("Emergency Contracts info should be existed.");
		}
	}
	
	//Issue Station
	public void verifyIssueStation(String issueStation){
		String actualIssueStation = this.getPermitIssuingStation();
		if(!MiscFunctions.compareResult("Issue station", issueStation, actualIssueStation)){
			throw new ErrorOnPageException("Issue station is wrong!", issueStation, actualIssueStation);
		}
		logger.info("Successfully verify 'Issue station':"+issueStation);
	}

	/**
	 * Verify the info for "Permit Detail" section
	 * @param permit
	 */
	public void verifyPermitDetailInfo(PermitInfo permit){
		//Type of Guided Trip
		if(!StringUtil.isEmpty(permit.typeOfGuidedTrip)){
			this.verifyGuidedTrip(permit.typeOfGuidedTrip);
		}

		//One Trip Itinerary record
		if(!StringUtil.isEmpty(permit.tripItineraryLocation)){
			this.verifyTripItinerary(permit.entryDate, permit.tripItineraryLocation);
		}

		//More than one Trip Itinerary records
		if(null!=permit.tripItineraryLocations && permit.tripItineraryLocations.length>0){
			this.verifyTripItinerary(permit.entryDate, permit.tripItineraryLocations);
		}

		//Member types and Member numbers
		if(null!=permit.personTypes && permit.personTypes.length>0){
			if(this.isPersonTypesAndNumsIncludeEntryAndExitDate(permit.personTypes[0])){
				this.verifyPerPersonPerDayPersonTypesInfo(permit.personTypes, permit.personNums, permit.entryDate, permit.exitDate);
			}else{
				this.verifyMemberTypesAndNumbers(permit.personTypes, permit.personNums);
			}
		}else{
			//Group Size
			this.verifyGroupSize(permit.groupSize);
		}

		//Travel Method
		if(!StringUtil.isEmpty(permit.travelMethod)){
			this.verifyTravelMethod(permit.travelMethod);
		}

		//Number of stock
		if(null!=permit.stockTypes && permit.stockTypes.length>0){
			this.verifyNumOfStocks(permit.stockTypes, permit.stockNums);
		}

		//Number of Pets
		if(null!=permit.petTypes&& permit.petTypes.length>0){
			this.verifyNumOfPets(permit.petTypes, permit.petNums);
		}

		//Permit Delivery Method
		if(!StringUtil.isEmpty(permit.deliveryMethod)){
			this.verifyDeliveryMethod(permit.deliveryMethod);
		}
		
		//Number of Watercraft
		if(permit.waterCraftInfo.size()>0){
			this.verifyNumOfWaterCraft(permit.waterCraftInfo);
		}

		//Group Members
		if(this.isGropMembers() && null!=permit.groupMembers){
			this.verifyGroupMember(permit.groupMembers, permit.groupMembers.getfNames(), permit.groupMembers.getlNames());
		}

		//Alternate Leader info
		if(this.isAlternateLeader() && null!=permit.alterLeaders){
			this.verifyAlternateLeader(permit.alterLeaders.getfNames(), permit.alterLeaders.getlNames(), permit.alterLeaders.getPhones());
		}

		//Issue Station
		if(!StringUtil.isEmpty(permit.issueStation)){
			this.verifyIssueStation(permit.issueStation);
		}
		
		//Emergency contacts
		if(permit.emergencyContrats!=null && permit.emergencyContrats.getfNames()!=null && StringUtil.notEmpty(permit.emergencyContrats.getfNames()[0])){//Sara[8/29/2013]
			verifyEmergencyContracts(permit.emergencyContrats);
		}
	}
	
	public boolean checkSuccessMesExist(){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "msg success");
		return browser.checkHtmlObjectExists(p);
	}
	
	public void verifySuccessMesExist(boolean existed_Expected){
		boolean existed_Actual = this.checkSuccessMesExist();
		if(!MiscFunctions.compareResult("Success Mes", existed_Expected, existed_Actual)){
			throw new ErrorOnPageException("Success message should "+(existed_Expected?"":"not ") +"exist.");
		}
	}
	
	/**
	 * Wait for successful message, such as 
	 * Confirmation Letter will be sent to alt_12345@reserveamerica.com within the next 15 minutes.
	 */
	public void waitForSuccessfulMes(){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".className", "msg success");
		browser.waitExists(p);
	}
	
	public void clickPrintTicketsAndPermits() {
		browser.clickGuiObject(".class", "Html.A", ".id", "printAtHome"); //"Print Tickets & Permits");
	}
	
	/**
	 * Verify Group Size number in permit reservation details page
	 * @param expGrpSize
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void verifyGroupSizeNum(String expGrpSize) {
		String groupSizeOnPage = this.getGroupSizeNum();
		if(!groupSizeOnPage.equals(expGrpSize)) {
			throw new ErrorOnPageException("Group Size is wrong on Permit Reservation Details Page!",
					expGrpSize, groupSizeOnPage);
		}
		logger.info("Group Size number is correct on Permit Reservation Details Page!");
	}
	
	/**
	 * Get itinerary detail info: Dates of Stay, Accomodation and Group Size
	 * @return
	 */
	public List<String[]> getItineraryDetailInfo(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "stiTable");
		if(objs.length<1){
			throw new ObjectNotFoundException("Itinerary detail table can't be found in permit reservation details page.");
		}
		
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String[]> records = new ArrayList<String[]>();
		String[] record = null;
		
		for(int i=1; i<table.rowCount(); i++){
			record = new String[table.columnCount()];
			for(int j=0; j<record.length; j++){
				record[j] = table.getCellValue(i, j);

			}
			records.add(record);
		}
		
		Browser.unregister(objs);
		return records;
	}
	
	public void verifyItineraryDetailInfo(EntranceInfo... entrances){
		boolean hasGroupSize = StringUtil.notEmpty(entrances[0].groupSize);
		boolean passed = true;
		String depatureDate = "";
		
		List<String[]> records = getItineraryDetailInfo();
		if(entrances.length!=records.size()){
			throw new ErrorOnPageException("Itinerary records length is wrong!", entrances.length, records.size());
		}
		
		for(int i=0; i<records.size(); i++){
			String[] record = records.get(i);
			if(entrances[i].useType.startsWith("night")){
				depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)), "E MMM dd yyyy");
			}else{
				depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)-1), "E MMM dd yyyy");
			}
			passed &= MiscFunctions.compareResult("Dates of Stay", DateFunctions.formatDate(entrances[i].entryDate, "E MMM dd yyyy")+"-"+depatureDate+" ("+entrances[i].numOfDays+" "+entrances[i].useType+")", record[0]);
		    passed &= MiscFunctions.compareResult("Entrance code and name", entrances[i].entranceCode+"-"+entrances[i].entranceName, record[1]);
		    if(hasGroupSize){
		    	passed &= MiscFunctions.compareResult("Group size", entrances[i].groupSize, record[2]);
		    }
		    if(!passed){
		    	throw new ErrorOnPageException("Failed to verify itinerary detail for entrance, code="+entrances[i].entranceCode+", name="+entrances[i].entranceName+" in permit reservation details page.");
		    }
		    logger.info(i+" - Itinerary details info is correct in permit reservation details page.");
		}
	}
}
