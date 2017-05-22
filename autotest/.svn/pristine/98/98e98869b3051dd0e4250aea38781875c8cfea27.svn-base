package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
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
public class UwpTourReservationDetailsPage extends UwpPage {

	private static UwpTourReservationDetailsPage _instance = null;

	public static UwpTourReservationDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourReservationDetailsPage();

		return _instance;
	}

	public UwpTourReservationDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "changedates");
	}

	/**
	 * Retrieve tour total.
	 * 
	 * @return - total
	 */
	public String getTotal() {
		IHtmlObject[] objs = browser.getHtmlObject(".className",
				"spaceBrlineTotal");
		String text = (String) objs[0].getProperty(".text");
		Browser.unregister(objs);
		String[] texts = text.split(" ");

		return texts[2];
	}

	public String getTPAsInfoViaGroup(String tpaGroup,
			int paticipantsDetailsIndex) {
		return getAllTPAsInfoViaGroup(tpaGroup)[paticipantsDetailsIndex];
	}

	public String[] getAllTPAsInfoViaGroup(String tpaGroup) {
		String[] perTicketTPSsInfo = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text",
				new RegularExpression("^"
						+ tpaGroup.replace(".", "\\.").replace("(", "\\(")
								.replace(")", "\\)") + ".*", false));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"The TPA info of 'Per Ticket' for group - " + tpaGroup
							+ " is not found.");
		}

		perTicketTPSsInfo = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			perTicketTPSsInfo[i] = objs[i].text();
		}

		Browser.unregister(objs);
		return perTicketTPSsInfo;
	}

	/**
	 * Get ticket types
	 * 
	 * @param tpaGroupName
	 * @return
	 */
	public List<String> getTicketTypes(String tpaGroupName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text",
				new RegularExpression("^"
						+ tpaGroupName.replace(".", "\\.").replace("(", "\\(")
								.replace(")", "\\)") + ".*", false));
		if (null == objs || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find DIV which text starts with - " + tpaGroupName);
		}

		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.B", objs[0]);
		if (null == objs1 || objs1.length < 1) {
			throw new ObjectNotFoundException("Can't find any sub objects");
		}

		List<String> ticketTypes = new ArrayList<String>();
		for (int i = 1; i < objs1.length; i++) {
			ticketTypes.add(objs1[i].getProperty(".text"));
		}

		Browser.unregister(objs, objs1);
		return ticketTypes;
	}

	/**
	 * Verify ticket types
	 * 
	 * @param tpaGroupName
	 * @param ticketTypes_Expected
	 */
	public void verifyTicketTypes(String tpaGroupName,
			List<String> ticketTypes_Expected) {
		List<String> ticketTypes_Actual = this.getTicketTypes(tpaGroupName);
		if (ticketTypes_Expected.size() != ticketTypes_Actual.size()) {
			throw new ErrorOnPageException(
					"The size of compared two ticket type list is different.",
					String.valueOf(ticketTypes_Expected.size()),
					String.valueOf(ticketTypes_Actual.size()));
		}

		for (int i = 0; i < ticketTypes_Expected.size(); i++) {
			if (!ticketTypes_Expected.get(i).equals(ticketTypes_Actual.get(i))) {
				throw new ErrorOnPageException("Ticket Type info is wrong!",
						ticketTypes_Expected.get(i), ticketTypes_Actual.get(i));
			}
			logger.info("Successfully verify ticket type info - "
					+ ticketTypes_Expected.get(i));
		}
	}

	/**
	 * Verify per ticket TPAs info
	 * @param ticketTypes
	 * @param groupNameAndTpaLabels: List<String> 
	 * groupNameAndTpaLabels.get(0) = group name
	 * groupNameAndTpaLabels.get(0+1|2|3|...) = TPAs labels
	 * @param perTicketTPAsList
	 * @param paticipantsDetailsIndex
	 */
	public void verifyPerTicketTPAsInfo(List<String> ticketTypes,
			List<String> groupNameAndTpaLabels,
			List<List<Map<String, String>>> perTicketTPAsList,
			int paticipantsDetailsIndex) {
		logger.info("Verify the TPA info of 'Per Ticket' for group - "
				+ groupNameAndTpaLabels.get(0) + " is correct or not.");
		int endIndex = groupNameAndTpaLabels.toString().split(
				groupNameAndTpaLabels.get(1))[1].split(", ").length;
		if (endIndex == 0) { // Only one tap label per ticket
			endIndex = 1;
		}

		String perTicketTPAsKey_Expect = groupNameAndTpaLabels.get(0) + " ";
		String perTicketTPAsValue_Expect = "";
		String perTicketTPAsInfo_Expect, perTicketTPAsInfo_Actual;

		// Get all TPAs keys
		for (int i = 1; i <= endIndex; i++) {
			perTicketTPAsKey_Expect += groupNameAndTpaLabels.get(i)
					+ (i == endIndex ? " " : ", ");
		}

		// Get all TPAs values
		for (int i = 0; i < ticketTypes.size(); i++) {
			perTicketTPAsValue_Expect += ticketTypes.get(i) + " ";

			for (int j = 0; j < perTicketTPAsList.get(i).size(); j++) {
				for (int k = 1; k <= endIndex; k++) {
					Set<Map.Entry<String, String>> set = perTicketTPAsList
							.get(i).get(j).entrySet();
					for (Iterator<Map.Entry<String, String>> it = set
							.iterator(); it.hasNext();) {
						Map.Entry<String, String> entry = (Map.Entry<String, String>) it
								.next();
						if (entry.getKey().equalsIgnoreCase(
								groupNameAndTpaLabels.get(k))) {
//							if(entry.getKey().matches(TicketInfo.TPA_LABEL_ANNIVERSARY) ||entry.getKey().matches(TicketInfo.TPA_LABEL_DATEOFBIRTH)){
//								entry.setValue(DateFunctions.formatDate(entry.getValue(), "EEE MMM dd yyyy"));
//							}
							perTicketTPAsValue_Expect += entry.getValue()
									+ (k == endIndex ? " " : ", ");
						}else if (entry.getKey().matches("Arrival(Time)?_Hh")) {
							perTicketTPAsValue_Expect += entry.getValue() + ":";
						} else if (entry.getKey().matches("Arrival(Time)?_Mm")) {
							perTicketTPAsValue_Expect += entry.getValue() + " ";
						} else if (entry.getKey().matches("Arrival(Time)?_Ap")) {
							perTicketTPAsValue_Expect += entry.getValue()
									+ ", ";
						}
					}
				}
			}
		}

		perTicketTPAsInfo_Expect = perTicketTPAsKey_Expect.trim() + " "
				+ perTicketTPAsValue_Expect.trim();
		perTicketTPAsInfo_Actual = this.getTPAsInfoViaGroup(
				groupNameAndTpaLabels.get(0), paticipantsDetailsIndex);
		perTicketTPAsInfo_Actual = perTicketTPAsInfo_Actual.replaceAll("<blank>", "").trim();
		if (!perTicketTPAsInfo_Expect.equals(perTicketTPAsInfo_Actual) ) {
			throw new ErrorOnPageException(
					"TPAs info is incorrect about group:"
							+ groupNameAndTpaLabels.get(0),
					perTicketTPAsInfo_Expect, perTicketTPAsInfo_Actual);
		}
		logger.info("Successfully verify TPAs info about group:"
				+ groupNameAndTpaLabels.get(0) + " like:"
				+ perTicketTPAsInfo_Expect);
	}
    /**
     * this method is used to verify per ticket Tpa info by one group name
     * @param ticketTypes
     * @param groupNameAndTpaLabels
     * @param perTicketTPAsList
     */
	public void verifyPerTicketTPAsInfo(List<String> ticketTypes,
			List<String> groupNameAndTpaLabels,
			List<List<Map<String, String>>> perTicketTPAsList) {
		this.verifyPerTicketTPAsInfo(ticketTypes, groupNameAndTpaLabels,
				perTicketTPAsList, 0);
	}
   /**
    * 
    * @param perInventoryTPAsList
    * @param isLastTAP
    * @return
    */
	public String getArrivalTimeInfo(Map<String, String> perInventoryTPAsList,
			boolean isLastTAP) {
		String arrivalTimeValues, hourKey, minKey, apmKey, am_pm, hour;
		if (perInventoryTPAsList.containsKey(TicketInfo.TPA_LABEL_ARRIVALTIME_HH)) {
			hourKey = TicketInfo.TPA_LABEL_ARRIVALTIME_HH;
			minKey = TicketInfo.TPA_LABEL_ARRIVALTIME_MM;
			apmKey = TicketInfo.TPA_LABEL_ARRIVALTIME_AP;
		} else {
			hourKey = OrmsConstants.TPA_LABEL_ARRIVAL_HH;
			minKey = OrmsConstants.TPA_LABEL_ARRIVAL_MM;
			apmKey = OrmsConstants.TPA_LABEL_ARRIVAL_AP;
		}
		am_pm = perInventoryTPAsList.get(apmKey);
		hour = perInventoryTPAsList.get(hourKey);
		if (hour.startsWith("0")) {
			hour = hour.replaceFirst("0", "");
		}
		arrivalTimeValues =  hour + ":"
					+ perInventoryTPAsList.get(minKey) + " " 
					+ (StringUtil.isEmpty(am_pm) ? "AM" : am_pm);
		return arrivalTimeValues;
	}
	/**
	 * Verify per inventory TPAs info
	 * @param groupNameAndTpaLabels: List<String> 
	 * groupNameAndTpaLabels.get(0) = group name
	 * groupNameAndTpaLabels.get(0+1|2|3|...) = TPAs labels
	 * @param perInventoryTPAsList
	 * @param paticipantsDetailsIndex
	 */
	public void verifyPerInventoryTPAsInfo(
			List<String> groupNameAndTpaLabels,
			Map<String, String> perInventoryTPAsList,
			int paticipantsDetailsIndex) {
		logger.info("Verify the TPA info of 'Per Inventory' for group - "
				+ groupNameAndTpaLabels.get(0) + " is correct or not.");
		int endIndex = groupNameAndTpaLabels.size() - 1;
		String perInventoryTPAsKey_Expect = groupNameAndTpaLabels.get(0)
				+ " ";

		// Get all TPAs keys
		for (int i = 1; i <= endIndex; i++) {
			perInventoryTPAsKey_Expect += groupNameAndTpaLabels.get(i)
					+ (i == endIndex ? "" : ", ");
		}

		String perInventoryTPAsValue_Expect = "";
		String perInventoryTPAsInfo_Expect, perInventoryTPAsInfo_Actual;

		// Get all TPAs values
		for (int i = 1; i <= endIndex; i++) {
			Set<Map.Entry<String, String>> set = perInventoryTPAsList
					.entrySet();
			boolean isArrivalTimeAdded=false;
			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
					.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>) it
						.next();

				if (groupNameAndTpaLabels.get(i).matches(
						"(ArrivalTime|Arrival)")&&!isArrivalTimeAdded) {
					perInventoryTPAsValue_Expect += this.getArrivalTimeInfo(
							perInventoryTPAsList, true)+", ";
					isArrivalTimeAdded=true;
				}

				if (groupNameAndTpaLabels.get(i).equals(entry.getKey())) {
//					if(entry.getKey().matches(TicketInfo.TPA_LABEL_ANNIVERSARY) ||entry.getKey().matches(TicketInfo.TPA_LABEL_DATEOFBIRTH)){
//						entry.setValue(DateFunctions.formatDate(entry.getValue(), "EEE MMM dd yyyy"));
//					}
					if (entry.getKey().matches(TicketInfo.TPA_LABEL_CURRENCY) && !entry.getValue().startsWith("$")) {
						perInventoryTPAsValue_Expect += "$" + entry.getValue() + ", ";
					} else {
						perInventoryTPAsValue_Expect += entry.getValue()+", ";
					}
				}
			}
		}

		perInventoryTPAsValue_Expect=perInventoryTPAsValue_Expect.trim().substring(0, perInventoryTPAsValue_Expect.length()-2);
		perInventoryTPAsInfo_Expect = perInventoryTPAsKey_Expect
				+ " "
				+ perInventoryTPAsValue_Expect;
		perInventoryTPAsInfo_Actual = this.getTPAsInfoViaGroup(
				groupNameAndTpaLabels.get(0), paticipantsDetailsIndex);
		if (!perInventoryTPAsInfo_Expect.equals(perInventoryTPAsInfo_Actual)) {
			throw new ErrorOnPageException(
					"TPAs info is incorrect about group:"
							+ groupNameAndTpaLabels.get(0),
					perInventoryTPAsInfo_Expect, perInventoryTPAsInfo_Actual);
		}
		logger.info("Successfully verify TPAs info about group:"
				+ groupNameAndTpaLabels.get(0) + " like:"
				+ perInventoryTPAsInfo_Expect);
	}

	public void verifyPerInventoryTPAsInfo(
			List<String> groupNameAndTpaLabels,
			Map<String, String> perInventoryTPAsList) {
		this.verifyPerInventoryTPAsInfo(groupNameAndTpaLabels,
				perInventoryTPAsList, 0);
	}

	public String getTourTimeAndTourType(String tourName) {
		String perTicketTPSsInfo = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text",
				new RegularExpression("^"
						+ tourName.replace(".", "\\.").replace("(", "\\(")
								.replace(")", "\\)") + ".*", false));
		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Tour time and tour type object - " + tourName
							+ " is not found.");
		}
		perTicketTPSsInfo = objs[1].text();

		Browser.unregister(objs);
		return perTicketTPSsInfo;
	}

	public void verifyTourTimeAndTourType(String expectTourTimeAndTourType,
			String tourName) {
		String actualTourTimeAndTourType = this
				.getTourTimeAndTourType(tourName);
		expectTourTimeAndTourType = tourName + " Change Details "
				+ expectTourTimeAndTourType;
		if (!expectTourTimeAndTourType.replaceAll("\\s+", "").equals(actualTourTimeAndTourType.replaceAll("\\s+", ""))) {
			throw new ErrorOnPageException(
					"Tour time and tour type info is incorrect.",
					expectTourTimeAndTourType, actualTourTimeAndTourType);
		}
		logger.info("Successfully verify tour time and tour type info:"
				+ expectTourTimeAndTourType);
	}

	public void verifyTourTimeAndTourType(String tourName,
			List<String> expectTourTimeAndTourTypeInfos,
			List<String> ticketTypes, List<String> ticketTypeNums) {
		String actualTourTimeAndTourType = this
				.getTourTimeAndTourType(tourName);
		
		for (int i = 0; i < expectTourTimeAndTourTypeInfos.size(); i++) {
			if(!actualTourTimeAndTourType.replaceAll("\\s*", "").contains(expectTourTimeAndTourTypeInfos.get(i).replaceAll("\\s*", "")))
				throw new ErrorOnPageException("Tour detail info was wrong. '"+expectTourTimeAndTourTypeInfos.get(i)+"' should contain in "+actualTourTimeAndTourType);
		}
		
		String expectTourTimeAndTourType = "";
		for (int j = 0; j < ticketTypes.size(); j++) {
			expectTourTimeAndTourType += ticketTypes.get(j) + " "
					+ ticketTypeNums.get(j)
					+ (j == ticketTypes.size() - 1 ? "" : ", ");
		}
		if(!actualTourTimeAndTourType.contains(expectTourTimeAndTourType))
			throw new ErrorOnPageException("Tour time and tour type info was wrong.",expectTourTimeAndTourType,actualTourTimeAndTourType);
		logger.info("Successfully verify tour time and tour type info:"
				+ expectTourTimeAndTourType);
	}

	public boolean checkChangeDetailslinkExisting() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Change Details");
	}

	public boolean checkTPAShowable() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("Participants Details.*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("Name.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if (objs == null || objs.length < 1) {
			return false;
		}
		Browser.unregister(objs);
		return true;
	}

	public void clickChangeDetailsLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Details");
	}

	public void clickChangeDetailsLink(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Details", index);
	}
	
	public String getAddress(String group) {
		group = group.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)");
		String address  ="\\W?Address, Alerts, Anniversary, ArrivalTime";
		String prefix = "^" + group + address;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".text", new RegularExpression(prefix + ".*", false));
		if (objs == null || objs.length < 1)
			throw new ObjectNotFoundException("Can't find DIV by group:"
					+ group);
		String text = objs[0].text();
		text = text.replaceAll(prefix, "");
		text = text.replaceAll("Address, Alerts, Anniversary, ArrivalTime", "");
		text = text.split(",")[0].trim();
		Browser.unregister(objs);
		return text;
	}
    /**
     * verify per inventory TPA info
     * @param perInventoryTPAs
     * @param gropAndLabelsForPerInv a list that element is another list of group name and related tpa labels  
     */
	public void verifyPerInventoryTPAsInfo(
			Map<String, String> perInventoryTPAs,
			List<List<String>> gropAndLabelsForPerInv) {
		for (List<String> list : gropAndLabelsForPerInv) {
			this.verifyPerInventoryTPAsInfo(list, perInventoryTPAs);
		}
	}
	
	public void verifyPerInventoryTPAsInfo(Map<String, String> perInventoryTPAs,
			List<List<String>> gropAndLabelsForPerInv, 
			int paticipantsDetailsIndex) {
		for (List<String> list : gropAndLabelsForPerInv) {
			this.verifyPerInventoryTPAsInfo(list, perInventoryTPAs, paticipantsDetailsIndex);
		}
	}
	
	public boolean checkPrintTicketsBtnExist(){
		return browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".text", "Print Tickets");
	}
	
	public void clickPrintTicketsBtnExist(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Print Tickets");;
	}
	
	public void clickReprintTicketsBtnExist(){
		browser.clickGuiObject(".class", "Html.BUTTON", ".text", "Reprint Tickets");;
	}
	
	public void verifyPrintTicketsBtn(boolean expectResult, String errorMsg){
		boolean result = this.checkPrintTicketsBtnExist();
		if(expectResult != result){
			throw new ErrorOnPageException(errorMsg);
		} else {
			logger.info("The display of Print Tickets button is as expect.");
		}
	}
	
	public IHtmlObject[] getTourDetailsTable(){
		IHtmlObject[] tables = browser.getTableTestObject(".className", "tourCartSummary");
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find reservation detials table");
		}

		return tables;
	}
	
	/**
	 * Get tour details content
	 * @return
	 */
	public String getTourDetailsContent(){
		IHtmlObject[] objs = this.getTourDetailsTable();
		String content = objs[0].text();
		
		Browser.unregister(objs);
		return content;
	}

	/**
	 * @param method
	 */
	public boolean checkDeliveryMetthodExisting(String method,String tourName) {
		boolean flag=false;
		IHtmlObject[] tables=this.getTourDetailsTable();
		IHtmlTable table=(IHtmlTable)tables[0];
		int row=table.findRow(1, new RegularExpression("^Tour: ?"+tourName.replaceAll("\\(", "\\\\(").replaceAll("\\)", "\\\\)")+".*",false))+3;
		String value=table.getCellValue(row, 1);
		if(StringUtil.isEmpty(value)){
			value=table.getCellValue(row-1, 1);//Rec.gov
		}
		if(value.matches("Delivery Method: ?"+method)){
			flag=true;
		}
		Browser.unregister(tables);
		return flag;
	}
	
	public String getDeliveryMethod(String tourName){
		String val="";
		IHtmlObject[] tables=this.getTourDetailsTable();
		IHtmlTable table=(IHtmlTable)tables[0];
		int row=table.findRow(1, new RegularExpression("^Tour: ?"+tourName+".*",false))+3;
		String value=table.getCellValue(row, 1);
		if(value.matches("Delivery Method:.*")){
			val=value.split(":")[1].trim();
		}else{
			throw new ObjectNotFoundException("There is no Delivery method found!");
		}
		Browser.unregister(tables);
		return val;
	}
	
	public void verifyDeliveryMethodExists(String method,String tourName){
		logger.info("Verify Delivery method:"+method+" for Tour:"+tourName+" exists.");
		if(!this.checkDeliveryMetthodExisting(method, tourName)){
			throw new ErrorOnPageException("Delivery method:"+method+" for Tour:"+tourName+" should exist.");
		}
	}
	
	public void verifyDeliveryMethodExistingOrNot(String method,String tourName, boolean exists){
		logger.info("Verify Delivery method:"+method+" for Tour:"+tourName+" exists.");
		if(exists!=this.checkDeliveryMetthodExisting(method, tourName)){
			throw new ErrorOnPageException("Delivery method:"+method+" for Tour:"+tourName+" should "+(exists?"exist":"not exist")+".");
		}
		logger.info("Successfully verify Delivery method:"+method+" for Tour:"+tourName+(exists?"exists":"doesn't exist")+".");
	}

	
	public void verifyNoDeliveryMethodExists() {
		logger.info("Verify no any delivery method exists.");
		IHtmlObject[] tables=this.getTourDetailsTable();
		IHtmlTable table=(IHtmlTable)tables[0];
		String values=table.text();
		Browser.unregister(tables);
		if(values.matches(".*Delivery Method:.*")){
			throw new ErrorOnPageException("there shouldn't be Delivery Method info.");
		}
		
	}

	/**
	 * @param fee
	 * @param methodname
	 */
	public void verifyDeliveryMethodTransFee(String fee, String methodname) {
		logger.info("Verify Delivery method ");
		double feeOnpage=Double.parseDouble(getDeliveryMethodTransFee(methodname));
		double expectedFee=Double.parseDouble(fee);
		if(expectedFee!=feeOnpage){
			throw new ErrorOnPageException("Can't find Delivery method fee.",String.valueOf(expectedFee),String.valueOf(feeOnpage));
		}
		
	}
	/**
	 * Verify whether there is any Delivery Method Fee existing or not.
	 * @return
	 */
	public boolean checkDeliveryMethodTransactionFeeExists(){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.TR",".text",new RegularExpression("^(Will Call)|(Print at Home)|(Mail Out)"+" ?\\d+\\.\\d{2}",false)));
	}
	public boolean checkDeliveryMethodTransactionFeeExists(String method){
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.TR",".text",new RegularExpression("^"+method+" ?\\d+\\.\\d{2}",false)));
	}
	
	public void verifyNoDeliveryMethodFeeExists(){
		logger.info("Verify there is no any delivery method Fee exists");
		if(this.checkDeliveryMethodTransactionFeeExists()){
			throw new ErrorOnPageException("There should no any Delivery method fee existing.");
		}
	}
	
	public void verifyNoDeliveryMethodFeeExists(String method){
		logger.info("Verify there is no any delivery method Fee exists");
		if(this.checkDeliveryMethodTransactionFeeExists(method)){
			throw new ErrorOnPageException("There should not be method:'"+method+"'");
		}
	}
	
	public String getDeliveryMethodTransFee(String method){
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text",new RegularExpression("^"+method+" ?\\d+\\.\\d{2}",false)));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find delivery method fee info!");
		}
		
		String val=objs[0].text();
		val=val.replaceAll("[A-Za-z]+", "").trim();
		return val;
	}
	
	
    /**
     * Get tour date information
     * @param tourName
     * @return
     */
    public List<String> getTourDates(String tourDateFormat){
    	List<String> tourDates = new ArrayList<String>();
    	String tableContent = this.getTourDetailsContent();	

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
}
