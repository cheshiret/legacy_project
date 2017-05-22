package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

public abstract class TourParticipantDetailsPage extends UwpPage {
	
	public static String topMessage="We need you to correct or provide more information. Please see each marked section below.";
	
	public void setParticipantAddress(String participantAddress){
		browser.setTextArea(".id", "20000265", participantAddress);
	}

	/**
	 * Get tour participant attribute for per inventory objects string list
	 * @param attributeName
	 * @return
	 */
	
	private IHtmlObject[] getTourParticipantAttributeForPerInventoryObjects(String attributeName){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", 
				".className", new RegularExpression("^attributeField.*", false),
				".text", new RegularExpression("^"+attributeName+".*", false));

		IHtmlObject[] objs = browser.getHtmlObject(p);
		if(null==objs || objs.length<0){
			throw new ObjectNotFoundException("Can't find the object with the value:"+attributeName);
		}

		return objs;
	}
	
	public void unSelectAgreementCheckBox(){
		browser.unSelectCheckBox(".id", "agreement");
	}

	/**
	 * 
	 */
	public void selectAgreementCheckBox() {
		browser.selectCheckBox(".id", "agreement");
	}
	
	/**
	 * Set tour participant attribute for per inventory
	 * @param key
	 * @param value
	 * @param propertyclassName: the value of ".className" for child object 
	 */
	private void setPerInventoryTourParticipantAttribute(String key, String value, String propertyclassName, boolean isTextField) {
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", 
				".className", new RegularExpression("^attributeField.*", false),
				".text", new RegularExpression("^"+key+".*", false));
		IHtmlObject[] topObjes = browser.getHtmlObject(p);
		if(topObjes==null || topObjes.length<1){
			throw new ObjectNotFoundException("Can't find top objest, which starts with:"+key);
		}

		if(isTextField){
			IHtmlObject[] subObjes = browser.getHtmlObject(".class", "Html.INPUT.text", topObjes[0]);
			if(subObjes==null || subObjes.length<1){
				throw new ObjectNotFoundException("Can't find sub objest, which starts with:"+key);
			}
			if(!subObjes[0].getProperty(".disabled").equals("true")){
				((IText)subObjes[0]).setText(value);
			}
		}else{
			browser.setTextArea(".className", propertyclassName, value, true, 0, topObjes[0]);
		}
	}
	
	protected String getPerInventoryTourParticipantAttribute(String key, String propertyclassName, boolean isTextField) {
		String perInventoryTPAValue = "";
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", 
				".className", new RegularExpression("^attributeField.*", false),
				".text", new RegularExpression("^"+key+".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find top objest, which starts with:"+key);
		}
		
		Property[] p2 = null;
		if(isTextField){
			p2 = Property.toPropertyArray(".class", "Html.INPUT.text");
				perInventoryTPAValue = browser.getTextFieldValue(p2, objs[0]);
		}else{
			p2 = Property.toPropertyArray(".className", new RegularExpression(propertyclassName, false));
			perInventoryTPAValue = browser.getTextAreaValue(p2, objs[0]);
		}
		
		return perInventoryTPAValue;
	}

	/**
	 * Select tour participant attribute for per inventory
	 * @param key
	 * @param value
	 */
	public void selectPerInventoryTPAsFromCheckBox(String key, String value) {
		IHtmlObject[] topObject_1 = this.getTourParticipantAttributeForPerInventoryObjects(key);
		if(null==topObject_1 || topObject_1.length<1){
			throw new ObjectNotFoundException("Object which starts with:"+key+" can't be found.");
		}
		IHtmlObject[] topObject_2 = browser.getHtmlObject(".class", "Html.DIV", ".text", value, topObject_1[0]);
		if(null==topObject_2 || topObject_2.length<1){
			throw new ObjectNotFoundException("Object which has value:"+value+" can't be found.");
		}
		Property[] p = Property.toPropertyArray(".class", "Html.INPUT.checkbox");
		browser.selectCheckBox(p, 0, topObject_2[0]);
		Browser.unregister(topObject_1, topObject_2);
	}
	
	/**
	 * UnSelect tour participant attribute for per inventory
	 * @param key
	 * @param value
	 */
	public void unSelectAllPerInventoryAlertsCheckBoxes(String key) {
		IHtmlObject[] topObjes = this.getTourParticipantAttributeForPerInventoryObjects(key);
		if(null==topObjes || topObjes.length<1){
			throw new ObjectNotFoundException("Top Object which starts with:"+key+" can't be found.");
		}
		Property[] p = Property.toPropertyArray(".class", "Html.INPUT.checkbox");
		IHtmlObject[] subObjes = browser.getCheckBox(p, topObjes[0]);
		if(null==subObjes || subObjes.length<0){
			throw new ObjectNotFoundException("SubObjects 'Alerts' object can't be found.");
		}
		for(int i=0; i<subObjes.length; i++){
			((ICheckBox)subObjes[i]).deselect();
		}
		Browser.unregister(topObjes, subObjes);
	}
	
	public boolean isPerInventoryTPAsCheckBoxSelected(String key, String value) {
		IHtmlObject[] topObject_1 = this.getTourParticipantAttributeForPerInventoryObjects(key);
		if(null==topObject_1 || topObject_1.length<1){
			throw new ObjectNotFoundException("Object which starts with:"+key+" can't be found.");
		}
		IHtmlObject[] topObject_2 = browser.getHtmlObject(".class", "Html.DIV", ".text", value, topObject_1[0]);
		if(null==topObject_2 || topObject_2.length<1){
			throw new ObjectNotFoundException("Object which has value:"+value+" can't be found.");
		}
		Property[] p = Property.toPropertyArray(".class", "Html.INPUT.checkbox");
		boolean isCheckBoxSelected = browser.isCheckBoxSelected(p,topObject_2[0]);
		Browser.unregister(topObject_1, topObject_2);
		
		return isCheckBoxSelected;
	}
	
	/**
	 * 
	 */
	public void setTextFeildByLabel(String val,String label) {
		IHtmlObject[] tops=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField",".text",new RegularExpression("^"+label+".*",false)));
	    if(tops==null||tops.length<1){
	    	throw new ObjectNotFoundException("Can't find the top div for label"+label);
	    }
	    
	    browser.setTextField(".class", "Html.INPUT.text", val, tops[0]);
	    Browser.unregister(tops);
	}
	
	public String getTextFeildValByLabel(String label){
		IHtmlObject[] tops=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField",".text",new RegularExpression("^"+label+".*",false)));
	    if(tops==null||tops.length<1){
	    	throw new ObjectNotFoundException("Can't find the top div for label"+label);
	    }
	    String val= browser.getTextFieldValue(Property.toPropertyArray(".class", "Html.INPUT.text"), tops[0]);
	    Browser.unregister(tops);
	    return val;
	}

	/**
	 * Select "Per inventory" TPAs from drop down list
	 * @param key
	 * @param value
	 * @param classNameValue
	 */
	public void selectPerInventoryTPAsFromDropDownList(String key, String value, String classNameValue) {
		if(key.startsWith("ArrivalTime")){
			key = "ArrivalTime";
		}else{
			if(key.startsWith("Arrival")){
				key = "Arrival";
			}
		}
		IHtmlObject[] topObject = this.getTourParticipantAttributeForPerInventoryObjects(key);
		if(null==topObject || topObject.length<1){
			throw new ObjectNotFoundException("Object which starts with:"+key+" can't be found.");
		}
		Property[] p = Property.toPropertyArray(".className", classNameValue);
		if(!StringUtil.isEmpty(value)){
			browser.selectDropdownList(p, value, true, topObject[0]);
		}else{
			browser.selectDropdownList(p, 0, true, topObject[0]);
		}
		
		Browser.unregister(topObject);
	}
	
	/**
	 * Get drop down TPAs value for "per Inventory"
	 * @param key
	 * @param classNameValue
	 * @return
	 */
	public String getPerInventoryTPAsDropDownListValue(String key, String classNameValue) {
		if(key.startsWith("ArrivalTime")){
			key = "ArrivalTime";
		}else{
			if(key.startsWith("Arrival")){
				key = "Arrival";
			}
		}
		IHtmlObject[] topObject = this.getTourParticipantAttributeForPerInventoryObjects(key);
		if(null==topObject || topObject.length<1){
			throw new ObjectNotFoundException("Object which starts with:"+key+" can't be found.");
		}
		Property[] p = Property.toPropertyArray(".className", new RegularExpression(classNameValue, false));
		String perInventoryDropDownListValue = browser.getDropdownListValue(p, 0, topObject[0]);
		Browser.unregister(topObject);
		
		return perInventoryDropDownListValue;
	}

	/**
	 * Convert attribute 
	 * @param from
	 * @param contractCode
	 * @return
	 */
	private String convertAttribute(String from, String contractCode) {
		String to = "";
		String attributeStrs[] = null;
		String attributeIDs[] = null;
		
		if(contractCode.equals("NRSO")){
			 attributeStrs = new String[] {"First Name", "Gender",  "Last Name", "Visited Times", "Date of Birth", "Ever Visit?", "EverVisit", "ArrivalTime_Hh", "ArrivalTime_Mm", "ArrivalTime_Ap", "Address"};
			 attributeIDs = new String[] { "2000003",  "2000010", "2000005",  "2000008", "2000001", "2000004", "2000004", "2000006", "2000006", "2000006", "2000002"};
		}else{
			 attributeStrs = new String[] {"First Name",  "Gender", "Last Name",  "Visited Times", "First Visit?", "Date of Birth", "Power", "Names", "Alerts", "Anniversary"};
			 attributeIDs = new String[] {"300000001", "300000002", "300000013",  "300000006", "300000008", "300000004", "300000008", "300000001", "300000003", "300000005"};
// Check the attribute display name and id by "select * from d_attr where attr_cat='Tour Participant';"
		}

		if(from.matches("[0-9]+")) {
			for(int i = 0; i < attributeIDs.length; i ++) {
				if(from.equals(attributeIDs[i])) {
					to = attributeStrs[i];
					break;
				}
			}
		} else {
			for(int i = 0; i < attributeStrs.length; i ++) {
				if(from.equals(attributeStrs[i])) {
					to = attributeIDs[i];
					break;
				}
			}
		}
		if(to.length() < 1) {
			throw new ItemNotFoundException("Unkown TPA attribute - " + from);
		}

		return to;
	}

	public IHtmlObject[] getPerTicketTourParticipantAttributesTable(){
		return browser.getTableTestObject(".className", "attributesSubgroup");
	}
	
	/**
	 * Get each ticket types values
	 * @return
	 */
	public List<String> getEachTicketTypeValues(){
		List<String> ticketTypeValue = new ArrayList<String>();
		Property[] P1 = Property.toPropertyArray(".className", "attributesSubgroup");
		Property[] P2 = Property.toPropertyArray(".class", "Html.TR");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(P1, P2));
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Can't find any TR objects under attribute sub group table.");
		}
		
		for(int i=0; i<objs.length; i++){
			ticketTypeValue.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return ticketTypeValue;
	}
	
	private IHtmlObject[] getAttributesTable(){
		return browser.getTableTestObject(".id", "attributesTable");
	}
	
   /**
    * common set TPA text field value for 'Per Ticket' identified by TicketType, AttributeID...
    * @param ticketType
    * @param attributeID
    * @param idPropertyIndex
    * @param trIndex
    * @param text
    */
	private void commonSetTPATextField(String ticketType, String attributeID, int idPropertyIndex, int trIndex, String text) {
		IHtmlObject[] objs = this.getPerTicketTPAsObjects();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found TPA object, index = " + idPropertyIndex + ". Ticket Type = " + ticketType);
		}
		browser.setTextField(".id", new RegularExpression("A" + idPropertyIndex + "_" + attributeID, false), text, objs[trIndex]);
		Browser.unregister(objs);
	}
	
	/**
	 * Get TPA text field value
	 * @param ticketType
	 * @param attributeID
	 * @param idPropertyIndex
	 * @param trIndex
	 * @param text
	 * @return
	 */
	private String commonGetTPATextField(String ticketType, String attributeID, int idPropertyIndex, int trIndex, String text) {
		IHtmlObject[] objs = this.getPerTicketTPAsObjects();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found TPA object, index = " + idPropertyIndex + ". Ticket Type = " + ticketType);
		}
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("A" + idPropertyIndex + "_" + attributeID, false));
		String tpasValue = browser.getTextFieldValue(p, objs[trIndex]);
		Browser.unregister(objs);
		return tpasValue;
	}

	/**
	 * Select TPA drop down list
	 * @param index
	 * @param ticketType
	 * @param attributeID
	 * @param value
	 */
	private void commonSelectTPADropdownList(String ticketType, String attributeID, int idPropertyIndex, int trIndex, String value) {
		IHtmlObject[] objs = this.getPerTicketTPAsObjects();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found TPA object, index = " + idPropertyIndex + ". Ticket Type = " + ticketType);
		}
		if(!StringUtil.isEmpty(value)){
			browser.selectDropdownList(".id", new RegularExpression("A" + idPropertyIndex + "_" + attributeID, false), value, true, objs[trIndex]);
		}else{
			browser.selectDropdownList(".id", new RegularExpression("A" + idPropertyIndex + "_" + attributeID, false), 0, true, objs[trIndex]);
		}
		Browser.unregister(objs);
	}
	
	private String commonGetTPADropdownList(String ticketType, String attributeID, int idPropertyIndex, int trIndex, String value) {
		IHtmlObject[] objs = this.getPerTicketTPAsObjects();
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found TPA object, index = " + idPropertyIndex + ". Ticket Type = " + ticketType);
		}
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("A" + idPropertyIndex + "_" + attributeID, false));
		String tpaValue = browser.getDropdownListValue(p,0, objs[trIndex]);
		Browser.unregister(objs);
		return tpaValue;
	}
	
	/**
	 * Check top page error message exists
	 * @return
	 */
	public boolean checkTopPageErrorMesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "msg topofpage error");
	}
	
	public void verifyTopPageErrorMesExist(boolean existed){
		logger.info("Start to verify top page error message "+(existed?"exists.":"doesn't exist."));
		if(this.checkTopPageErrorMesExist()!=existed){
			throw new ErrorOnPageException("Top page error message should "+(existed?"be existed.":" not be existed."));
		}
		logger.info("Successfully verify top page error message "+(existed?"exists.":"doesn't exist."));
	}
	
	/**
	 * Get top page error message
	 * @return
	 */
	public String getTopPageErrorMes(){
		String errorMes = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg topofpage error");
		if(objs.length<1){
			throw new ItemNotFoundException("Top error message object can't be found.");
		}

		errorMes = objs[0].text();
		Browser.unregister(objs);
		return errorMes;
	}
	
	/**
	 * Verify top page error message
	 * @param expectErrorMes
	 */
	public void verifyTopPageErrorMes(String expectErrorMes){
		String actualErrorMes = this.getTopPageErrorMes();

		if(!expectErrorMes.equals(actualErrorMes)){
			throw new ErrorOnPageException("Top page error message is wrong!", expectErrorMes, actualErrorMes);
		}
		logger.info("Successfully verify top page error message:"+expectErrorMes);
	}
	

	/**
	 * Verify top page error message
	 * @param expectErrorMes
	 */
	public void verifyTopPageErrorMes(){
		this.verifyTopPageErrorMes(topMessage);
	}

	public boolean checkTPAsErrorMesExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "group_errors");
	}
	
	public void verifyTPAsErrorMesExist(boolean existed){
		logger.info("Start to verify TPAs error message "+(existed?"exists.":"doesn't exist."));
		if(this.checkTPAsErrorMesExist()!=existed){
			throw new ErrorOnPageException("TPAs error message should "+(existed?"be existed.":" not be existed."));
		}
		logger.info("Successfully verify TPAs error message "+(existed?"exists.":"doesn't exist."));
	}
	
	/**
	 * Get froup error messages
	 * @return
	 */
	public List<String> getGroupErrorMeses(){
		List<String> errorMesItems = new ArrayList<String>();
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "group_errors");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "error_item");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(null==objs || objs.length<1){
			throw new ItemNotFoundException("No group error message items object can't be found.");
		}

		for(int i=0; i<objs.length; i++){
			errorMesItems.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return errorMesItems;
	}
	
	/**
	 * Verify group error message
	 * @param expectErrorMes
	 */
	public void verifyGroupErrorMeses(String... expectErrorMes){
		List<String> actualErrorMes = this.getGroupErrorMeses();
		if(actualErrorMes.size()!=expectErrorMes.length){
			throw new ErrorOnDataException("The length of group error message is wrong!", String.valueOf(expectErrorMes.length), String.valueOf(actualErrorMes.size()));
		}
		
		for(int i=0; i<actualErrorMes.size(); i++){
			if(!expectErrorMes[i].equals(actualErrorMes.get(i))){
				throw new ErrorOnPageException("Group error message is wrong!", expectErrorMes[i], actualErrorMes.get(i));
			}
			logger.info("Successfully verify group error message:"+expectErrorMes[i]);
		}
	}
	
	/**
	 * Get attribute field error messagees
	 * @return
	 */
	public List<String> getAttributeFieldErrorMeses(){
		List<String> errorMesItems = new ArrayList<String>();
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField  error");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs.length<1){
			throw new ItemNotFoundException("No attribute field error message items object can't be found.");
		}

		for(int i=0; i<objs.length; i++){
			errorMesItems.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return errorMesItems;
	}
	
	/**
	 * Verify attribute field error message
	 * @param expectErrorMes
	 */
	public void verifyAttributeFieldErrorMeses(String[] expectErrorMes){
		List<String> actualErrorMes = this.getAttributeFieldErrorMeses();
		if(actualErrorMes.size()!=expectErrorMes.length){
			throw new ErrorOnDataException("The length of attribute field error messages are wrong!", String.valueOf(expectErrorMes.length), String.valueOf(actualErrorMes.size()));
		}
		
		for(int i=0; i<actualErrorMes.size(); i++){
			if(!expectErrorMes[i].equals(actualErrorMes.get(i))){
				throw new ErrorOnPageException("Attribute field error message is wrong!", expectErrorMes[i], actualErrorMes.get(i));
			}
			logger.info("Successfully verify attribute field error message:"+expectErrorMes[i]);
		}
	}
	
	/**
	 * Click the "Items In Cart: " link at the left of this page
	 */
	public void clickViewShoppingCartLink(){
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart: \\d+", false));
		//Quentin[20130905] 'Items In Cart:' has been changed as 'Items:' and not clickable and replaced with 'Check Out Now'
		Property oldProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Items In Cart: \\d+", false));
		Property newProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", "Check Out Now");
		if(browser.checkHtmlObjectExists(oldProperty)) {
			browser.clickGuiObject(oldProperty);
		} else if(browser.checkHtmlObjectExists(newProperty)) {
			browser.clickGuiObject(newProperty);
		}
	}

	/**
	 * Set TPAs value for each ticket
	 * @param ticketType
	 * @param idPropertyIndex
	 * @param trIndex
	 * @param map
	 * @param contractCode
	 * @return
	 */
	public void setTPAsForEachTicket(String ticketType, int idPropertyIndex, int trIndex, Map<String, String> map, String contractCode) {
		String attributeID = "";
		String key = "";
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			key = entry.getKey();

			attributeID = convertAttribute(key, contractCode);
			//use corresponding method to set TPA values
			if(contractCode.equals("NRSO")){
				switch(Integer.parseInt(attributeID)) {
				case 2000001://Date of Birth
					commonSetTPATextField(ticketType, attributeID, idPropertyIndex, trIndex, DateFunctions.formatDate(entry.getValue(), "MM/dd/yyyy"));
					break;
				case 2000003://First Name
				case 2000008://Visited Times
				case 2000005://Last Name
					commonSetTPATextField(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue());
					break;
				case 2000010://Gender
				case 2000004://Ever Visit?
				case 2000006:{
					if(key.equals("ArrivalTime_Hh")){
						attributeID = "2000006_hh";
					}else if(key.equals("ArrivalTime_Mm")){
						attributeID = "2000006_mm";
					}else if(key.equals("ArrivalTime_Ap")){
						attributeID = "2000006_ap";
					}
				}
				commonSelectTPADropdownList(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue());
				}
			}else{
				switch(Integer.parseInt(attributeID)) {
				case 300000004://Date of Birth
				case 2000001://Date of Birth
				case 300000001://First Name
				case 2000003://First Name
				case 300000006://Visited Times
				case 2000008://VisitedTime
				case 300000013://Last Name
				case 2000005://Last Name
				case 300000005: //Anniversary	
					commonSetTPATextField(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue());
					break;
				case 300000002://Gender
				case 2000010://Gender
				case 300000008://First Visit?
				case 2000004://Ever Visit?
					commonSelectTPADropdownList(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue());
					break;

				default:
				}
			}

		}
	}
	
	/**
	 * Get TPAs value for each ticket
	 * @param ticketType
	 * @param idPropertyIndex
	 * @param trIndex
	 * @param map
	 * @param contractCode
	 * @return
	 */
	public Map<String, String> getTPAsForEachTicket(String ticketType, int idPropertyIndex, int trIndex, Map<String, String> map, String contractCode) {
		Map<String, String> returnMap = new HashMap<String, String>();
		String attributeID = "";
		String key = "";
		Set<Map.Entry<String, String>> set = map.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			key = entry.getKey();

			attributeID = convertAttribute(key, contractCode);
			//use corresponding method to set TPA values
			if(contractCode.equals("NRSO")){
				switch(Integer.parseInt(attributeID)) {
				case 2000001://Date of Birth
				case 2000003://First Name
				case 2000008://VisitedTime
				case 2000005://Last Name
					returnMap.put(key, commonGetTPATextField(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue()));
					break;
				case 2000010://Gender
				case 2000004://Ever Visit?
				case 2000006:{
					if(key.equals("ArrivalTime_Hh")){
						attributeID = "2000006_hh";
					}else if(key.equals("ArrivalTime_Mm")){
						attributeID = "2000006_mm";
					}else if(key.equals("ArrivalTime_Ap")){
						attributeID = "2000006_ap";
					}
				}
				returnMap.put(key, commonGetTPADropdownList(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue()));
				}
			}else{
				switch(Integer.parseInt(attributeID)) {
				case 300000004://Date of Birth
				case 2000001://Date of Birth
				case 300000001://First Name
				case 2000003://First Name
				case 300000006://Visited Times
				case 2000008://VisitedTime
				case 300000013://Last Name
				case 2000005://Last Name
				case 300000005: //Anniversary	 	
					returnMap.put(key, commonGetTPATextField(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue()));
					break;
				case 300000002://Gender
				case 2000010://Gender
				case 300000008://First Visit?
				case 2000004://Ever Visit?
					returnMap.put(key, commonGetTPADropdownList(ticketType, attributeID, idPropertyIndex, trIndex, entry.getValue()));
					break;

				default:
				}
			}
		}
		
		return returnMap;
	}

	public IHtmlObject[] getPerTicketTPAsObjects(){
		Property[] p1 = Property.toPropertyArray(".className", "attributesSubgroup");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TR");
		return browser.getHtmlObject(Property.atList(p1, p2));
	}

	/**
	 * Get the number of per ticket tour participant attributes for specific ticket type
	 * @param ticketType
	 * @return
	 */
	public int getTheNumOfPerTPAsForSpecificTicketType(String ticketType){
		int numOfPerTicketTPAs = 0;

		IHtmlObject[] subObjs = getPerTicketTPAsObjects();	
		if(null==subObjs || subObjs.length<1){
			throw new ItemNotFoundException("Can't find any TPA TR object(s) under ticket type - " + ticketType);
		}

		for(int i=0; i<subObjs.length; i++){
			if(subObjs[i].text().equalsIgnoreCase(ticketType)){
				for(int m=i+1; m<subObjs.length; m++){
					if(subObjs[m].text().matches("\\d+.*")){
						numOfPerTicketTPAs++;
					}else{
						break;
					}
				}
			}
		}

		Browser.unregister(subObjs);
		return numOfPerTicketTPAs;
	}

	/**
	 * Get specific ticket type row
	 * @param ticketType
	 * @return
	 */
	public int getSpecificTicketTypeRow(String ticketType){
		int row = 0;

		IHtmlObject[] subObjs = getPerTicketTPAsObjects();	
		if(null==subObjs || subObjs.length<1){
			throw new ItemNotFoundException("Can't find any TPA TR object(s) under ticket type - " + ticketType);
		}

		for(int i=0; i<subObjs.length; i++){
			if(subObjs[i].text().equalsIgnoreCase(ticketType)){
				row=i;
				break;
			}
		}

		Browser.unregister(subObjs);
		return row;
	}

	/**
	 * Set TPAs values for 'Per Ticket' identified by TicketType
	 * For each permit type, it maybe includes more than one item TPAs 
	 * @param ticketType - Such as: Adult, Child
	 * @param tpasList - Specific TPAs values for each permit type
	 */
	public List<Map<String, String>> getTourParticipantAttributesForPerTicket(String ticketType, List<Map<String, String>>list, int idPropertyIndex, int trIndex, String contractCode) {
		List<Map<String, String>> returnTpas = new ArrayList<Map<String, String>>();
		IHtmlObject trObjs[] = this.getPerTicketTPAsObjects();
		int countOnPage = this.getTheNumOfPerTPAsForSpecificTicketType(ticketType);
		int expectedCount = list.size();
		if( countOnPage != expectedCount) {
			throw new ErrorOnPageException("There should be " + expectedCount + " 'Per Ticket' TPAs for these tickets, but actual is " + countOnPage);
		}
		for(int i = 0; i < list.size(); i ++) {
			returnTpas.add(getTPAsForEachTicket(ticketType, idPropertyIndex+i, trIndex+i, list.get(i), contractCode));
		}

		Browser.unregister(trObjs);
		return returnTpas;
	}
	
	/**
	 * Set TPAs values for 'Per Ticket' identified by TicketType
	 * For each permit type, it maybe includes more than one item TPAs 
	 * @param ticketType - Such as: Adult, Child
	 * @param tpasList - Specific TPAs values for each permit type
	 */
	public void setTourParticipantAttributesForPerTicket(String ticketType, List<Map<String, String>>list, int idPropertyIndex, int trIndex, String contractCode) {
		IHtmlObject trObjs[] = this.getPerTicketTPAsObjects();
		int countOnPage = this.getTheNumOfPerTPAsForSpecificTicketType(ticketType);
		int expectedCount = list.size();
		if( countOnPage != expectedCount) {
			throw new ErrorOnPageException("There should be " + expectedCount + " 'Per Ticket' TPAs for these tickets, but actual is " + countOnPage);
		}
		for(int i = 0; i < list.size(); i++) {
			setTPAsForEachTicket(ticketType, idPropertyIndex+i, trIndex+i, list.get(i), contractCode);
		}

		Browser.unregister(trObjs);
	}

	/**
	 * Set TPAs value for 'Per Ticket' identified by TicketTypes
	 * For each permit type, it maybe includes more than one item TPAs 
	 * @param ticketType - Such as: Adult, Child
	 * @param tpasList - Specific TPAs values for each permit type
	 */
	public void setPerTicketTourParticipantAttributes(List<String> ticketTypes, List<List<Map<String, String>>> tpasList, String contractCode) {
		int idPropertyIndex = 0;
		int trTAPsIndex = 0;

		if(ticketTypes.size()!=tpasList.size()){
			throw new ErrorOnDataException("The length of ticket types and related TPAs is different.", String.valueOf(ticketTypes.size()), String.valueOf(tpasList.size()));
		}
		
		for(int i=0; i<ticketTypes.size(); i++){
			trTAPsIndex = this.getSpecificTicketTypeRow(ticketTypes.get(i));
			if(i>0){
				idPropertyIndex = trTAPsIndex+1-i;
			}else{
				idPropertyIndex = i+1;
			}
			this.setTourParticipantAttributesForPerTicket(ticketTypes.get(i), tpasList.get(i), idPropertyIndex, trTAPsIndex+1, contractCode);
		}
	}
	
	/**
	 * Get TPAs values for "Per Ticket"
	 * @param ticketTypes
	 * @param tpasList
	 * @param contractCode
	 * @return
	 */
	public List<List<Map<String, String>>> getPerTicketTourParticipantAttributes(List<String> ticketTypes, List<List<Map<String, String>>> tpasList, String contractCode){
		List<List<Map<String, String>>> tpas = new ArrayList<List<Map<String, String>>>();
		int idPropertyIndex = 0;
		int trTAPsIndex = 0;
		
		if(ticketTypes.size()!=tpasList.size()){
			throw new ErrorOnDataException("The length of ticket types and related TPAs is different.", String.valueOf(ticketTypes.size()), String.valueOf(tpasList.size()));
		}

		for(int i=0; i<ticketTypes.size(); i++){
			trTAPsIndex = this.getSpecificTicketTypeRow(ticketTypes.get(i));
			if(i>0){
				idPropertyIndex = trTAPsIndex+1-i;
			}else{
				idPropertyIndex = i+1;
			}
			tpas.add(this.getTourParticipantAttributesForPerTicket(ticketTypes.get(i), tpasList.get(i), idPropertyIndex, trTAPsIndex+1, contractCode));
		}
		
		return tpas;
	}

	/**
	 * Set TPAs values for "Per Inventory"
	 * @param perInventoryTPAs
	 * @return
	 */
	public void setPerInventoryTourParticipantAttributes(Map<String, String> perInventoryTPAs) {
		Set<Map.Entry<String, String>> set = perInventoryTPAs.entrySet();
		for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
			if(entry.getKey().equalsIgnoreCase("Address")){
				this.setPerInventoryTourParticipantAttribute(entry.getKey(), entry.getValue(), "TextAreaRenderer", false);
			}else if(entry.getKey().startsWith("Alert")){
				this.unSelectAllPerInventoryAlertsCheckBoxes(entry.getKey());
				this.selectPerInventoryTPAsFromCheckBox(entry.getKey(), entry.getValue());
			}else if(entry.getKey().equalsIgnoreCase("Anniversary")){
				this.setPerInventoryTourParticipantAttribute(entry.getKey(), entry.getValue(), "DateOfBirthRenderer placeholder", true);
			}else if(entry.getKey().matches("Arrival(Time)?+_Hh")){
				this.selectPerInventoryTPAsFromDropDownList(entry.getKey(), entry.getValue(), "hh");
			}else if(entry.getKey().matches("Arrival(Time)?+_Mm")){
				this.selectPerInventoryTPAsFromDropDownList(entry.getKey(), entry.getValue(), "mm");
			}else if(entry.getKey().matches("Arrival(Time)?+_Ap")){
				this.selectPerInventoryTPAsFromDropDownList(entry.getKey(), entry.getValue(), "ap");
			}else if(entry.getKey().equalsIgnoreCase("Currency")){
				this.setCurrency(entry.getValue());
			}else if(entry.getKey().equalsIgnoreCase("To") || entry.getKey().equalsIgnoreCase("Ca_WishPrice2") || entry.getKey().equalsIgnoreCase("WishPriceTo")){
				this.setPerInventoryTourParticipantAttribute(entry.getKey(), entry.getValue(), "DecimalBoxRenderer", true);
			}else if(entry.getKey().matches("Wish( )?Price(| From)")){
				this.setPerInventoryTourParticipantAttribute(entry.getKey(), entry.getValue(), "DecimalBoxRenderer", true);
			}else if(entry.getKey().matches("Visited Times")){
				this.setPerInventoryTourParticipantAttribute(entry.getKey(), entry.getValue(), "NumberBoxRenderer", true);
			}else if(entry.getKey().matches("Power")){
				this.selectPerInventoryTPAsFromDropDownList(entry.getKey(), entry.getValue(), "TagSelectorRenderer");
			}else{
				throw new ErrorOnPageException("No matched tour perticipant has key:"+entry.getKey()+" can be found.");
			}
		}
	}
	
	/**
	 * Get TPAs values for "Per Inventory"
	 * @param perInventoryTPAs
	 * @return
	 */
	public Map<String, String> getPerInventoryTourParticipantAttributes(Map<String, String> perInventoryTPAs) {
		Map<String, String> returnMap = new HashMap<String, String>();
		for(int i=0; i<perInventoryTPAs.size(); i++){
			Set<Map.Entry<String, String>> set = perInventoryTPAs.entrySet();
			for(Iterator<Map.Entry<String, String>> it = set.iterator(); it.hasNext();) {
				Map.Entry<String, String> entry = (Map.Entry<String, String>)it.next();
				if(entry.getKey().equalsIgnoreCase("Address")){
					returnMap.put(entry.getKey(), this.getPerInventoryTourParticipantAttribute(entry.getKey(), "TextAreaRenderer", false));
				}else if(entry.getKey().startsWith("Alert")){
					returnMap.put(entry.getKey(), this.getSelectedAlertLabel() );
				}else if(entry.getKey().equalsIgnoreCase("Anniversary")){
					returnMap.put(entry.getKey(), this.getPerInventoryTourParticipantAttribute(entry.getKey(), "DateOfBirthRenderer placeholder", true));
				}else if(entry.getKey().matches("Arrival(Time)?+_Hh")){
					returnMap.put(entry.getKey(), this.getPerInventoryTPAsDropDownListValue(entry.getKey(), "hh( placeholder)?"));
				}else if(entry.getKey().matches("Arrival(Time)?+_Mm")){
					returnMap.put(entry.getKey(), this.getPerInventoryTPAsDropDownListValue(entry.getKey(), "mm"));
				}else if(entry.getKey().matches("Arrival(Time)?+_Ap")){
					returnMap.put(entry.getKey(), this.getPerInventoryTPAsDropDownListValue(entry.getKey(), "ap"));
				}else if(entry.getKey().equalsIgnoreCase("Currency")){
					returnMap.put(entry.getKey(), this.getPerInventoryTourParticipantAttribute(entry.getKey(),"CurrencyBoxRenderer|inputwithrubylabel", true));
				}else if(entry.getKey().equalsIgnoreCase("To") || entry.getKey().equalsIgnoreCase("Ca_WishPrice2") || entry.getKey().equalsIgnoreCase("WishPriceTo")){
					returnMap.put(entry.getKey(), this.getPerInventoryTourParticipantAttribute(entry.getKey(), "DecimalBoxRenderer", true));
				}else if(entry.getKey().matches("Wish( )?Price(| From)")){
					returnMap.put(entry.getKey(), this.getPerInventoryTourParticipantAttribute(entry.getKey(), "DecimalBoxRenderer", true));
				}else if(entry.getKey().matches("Power")){
					returnMap.put(entry.getKey(), this.getPerInventoryTPAsDropDownListValue(entry.getKey(), "TagSelectorRenderer"));
				}else{
					throw new ErrorOnPageException("No matched tour perticipant has key:"+entry.getKey()+" can be found.");
				}
			}
		}
		
		return returnMap;
	}

	
	
	/**
	 * 
	 */
	private String getSelectedAlertLabel() {
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "attributeField allLayoutCol",".text",new RegularExpression("^Alert.*",false)));
		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("Can't find Alert DIV");
		}
		IHtmlObject[] divs=browser.getHtmlObject(".class", "Html.DIV", ".className", "CheckboxGroupRenderer",objs[0]);
		if(divs==null || divs.length<1){
			throw new ObjectNotFoundException("Can't find Check box goup Div.");
		}
		
		IHtmlObject[] checkBoxDivs=browser.getHtmlObject(".class", "Html.LABEL", divs[0]);
		
		if(checkBoxDivs==null || checkBoxDivs.length<1){
			throw new ObjectNotFoundException("Can't find DIV for check box.");
		}
		
		String list="";
		String id=null;
		for(IHtmlObject o:checkBoxDivs){
			id=o.getProperty(".for");
			if(browser.isCheckBoxSelected(".id", id)){
				list+=o.text().trim()+",";
			}
		}
		
		Browser.unregister(checkBoxDivs,divs,objs);
		if(!StringUtil.isEmpty(list) && list.contains(",")){
			list=list.substring(0, list.lastIndexOf(","));
		}
		
		return list;
	}

	/**
	 * Get tour time and ticket types info
	 * @return
	 */
	public List<String> getTourTimeAndToutTypeInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", "tour_abstract_info");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("tour_abstract_info object can't be found.");
		}
		
		List<String> tourTimeAndTourTypeInfos = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			tourTimeAndTourTypeInfos.add(objs[i].text());
		}
		
		Browser.unregister(objs);
		return tourTimeAndTourTypeInfos;
	}
	
	public List<String> getPerTicketGroupNamesAndRalatedLabels(){
		return getTPAGroupsAndRelatedTPALabels().get(0);
	}
	
	public List<List<String>> getPerInvGroupNamesAndRalatedLabels(){
		List<List<String>> list=getTPAGroupsAndRelatedTPALabels();
		list.remove(0);
		return list;
	}
	
	/**
	 * Get TPA groups and related TPAs
	 * @return List<List<String>>: the first List<String> is group name, others are related TPAs Labels
	 */
	public List<List<String>> getTPAGroupsAndRelatedTPALabels(){
		List<List<String>> tpasGroupsAndAssociatedTPAs = new ArrayList<List<String>>();
		List<String> tpasGroupAndAssociatedTPAs = null;

		//Get the whole TPAs table object
		IHtmlObject[] topObjs = this.getAttributesTable();
		if(topObjs==null || topObjs.length<1){
			throw new ObjectNotFoundException("TPAs table objects can't be found.");
		}

		//Get all the Group objects under previous table
		IHtmlObject[] groupsObjs = browser.getHtmlObject(".class", "Html.TD", ".className", "r", topObjs[0]);
		if(groupsObjs==null || groupsObjs.length<1){
			throw new ObjectNotFoundException("TPAs group objects can't be found.");
		}
		
		//Get all TPAs Data sections via previous group name
		for(int i=0; i<groupsObjs.length; i++){
			
			IHtmlObject[] tpaDataSectionObjs = browser.getHtmlObject(".class", "Html.TR", ".text", 
					new RegularExpression("^"+groupsObjs[i].text().replace(".", "\\.").replace("(", "\\(").replace(")", "\\)")+".*", false));
			if(tpaDataSectionObjs==null || tpaDataSectionObjs.length<1){
				throw new ObjectNotFoundException("TPAs data section objects can't be found.");
			}
			
			//Get all TPAs label under previous TPAs Data section
			IHtmlObject[] tpaSpanObjs = browser.getHtmlObject(".class", "Html.SPAN", ".className","extra",tpaDataSectionObjs[0]);
			tpasGroupAndAssociatedTPAs = new ArrayList<String>();
			
			//The first one is group name
			tpasGroupAndAssociatedTPAs.add(groupsObjs[i].text());
			//If "Per Inventory" attribute, doesn't have Group Name, only have label, it will display label as "Group" name, no TPA label displays
			if(tpaSpanObjs !=null && tpaSpanObjs.length>0){
				for(int j=0; j<tpaSpanObjs.length; j++){
					String styleValue = tpaSpanObjs[j].style("display").trim();
					if(StringUtil.isEmpty(styleValue) || !styleValue.equals("none")){
						String spanObjectValue = tpaSpanObjs[j].text();
						if(!StringUtil.isEmpty(spanObjectValue) && !spanObjectValue.equals("null") && !spanObjectValue.equals("*") && !spanObjectValue.matches("\\d+")){
							tpasGroupAndAssociatedTPAs.add(spanObjectValue);
						}
					}
				}
			}
			tpasGroupsAndAssociatedTPAs.add(tpasGroupAndAssociatedTPAs);
			if(tpaSpanObjs!=null && tpaSpanObjs.length>0){
				Browser.unregister(tpaSpanObjs);
			}
		}

		Browser.unregister(topObjs, groupsObjs);
		
		return tpasGroupsAndAssociatedTPAs;
	}
	
	/**
	 * Get "Attribute table" content
	 * @return
	 */
	public String getAttributesTableContent(){
		String attributeTableContent = "";
		
		IHtmlObject[] objs = this.getAttributesTable();
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Attributes table can't be found.");
		}
		attributeTableContent = objs[0].text();
		
		Browser.unregister(objs);
		return attributeTableContent;
	}
	
	/**
	 * Get 'Date of Birth' value
	 * @return
	 */
	public String getDateOfBirthValue(){
		String dateOfBirth = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("A\\d+_(2000001|300000004)", false));
		if(null == objs || objs.length<1){
			throw new ObjectNotFoundException("'Date of Birth' object can't be found.");
		}
//		dateOfBirth = objs[0].getProperty(".value");
		dateOfBirth = objs[0].getProperty(".placeholder");//Lesley[20140317] update due to object property changed in IE9


		Browser.unregister(objs);
		return dateOfBirth;
	}
	
	/**
	 * Verify the value of 'Date of Birth'
	 * @param expectedDateOfBirth
	 */
	public void verifyDateOfBirthValue(String expectedDateOfBirth){
		String actualDateOfBirth = this.getDateOfBirthValue();
		if(!expectedDateOfBirth.equals(actualDateOfBirth)){
			throw new ErrorOnPageException("'Date of Birth' default value is wrong!", expectedDateOfBirth, actualDateOfBirth);
		}
		logger.info("Successfully verify 'Date of Birth'---"+expectedDateOfBirth);
	}
	
	/**
	 * Get 'Anniversary' value
	 * @return
	 */
	public String getAnniversaryValue(){
		String anniversary = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("A\\d_(2000009|300000005)", false));
		if(null == objs || objs.length<1){
			throw new ObjectNotFoundException("'Anniversary' object can't be found.");
		}
//		anniversary = objs[0].getProperty(".value");
		anniversary = objs[0].getProperty(".placeholder"); //Lesley[20140317] update due to object property changed in IE9


		Browser.unregister(objs);
		return anniversary;
	}
	
	/**
	 * Verify the value of 'Anniversary'
	 * @param expectedAnniversary
	 */
	public void verifyAnniversaryValue(String expectedAnniversary){
		String actualAnniversary = this.getAnniversaryValue();
		if(!expectedAnniversary.equals(actualAnniversary)){
			throw new ErrorOnPageException("'Anniversary' default value is wrong!", expectedAnniversary, actualAnniversary);
		}
		logger.info("Successfully verify 'Anniversary'---"+expectedAnniversary);
	}
	
	/**
	 * Get 'Address' value
	 * @return
	 */
	public String getAddressValue(){
		String address = "";
		
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("A\\d_(2000002|300000009)", false));
		if(null == objs || objs.length<1){
			throw new ObjectNotFoundException("'Address' object can't be found.");
		}
		address = objs[0].getProperty(".value");


		Browser.unregister(objs);
		return address;
	}
	
	/**
	 * Verify the value of 'Address'
	 * @param expectedAddress
	 */
	public void verifyAddressValue(String expectedAddress){
		String actualAddress = this.getAddressValue();
		if(!expectedAddress.equals(actualAddress)){
			throw new ErrorOnPageException("'Address' default value is wrong!", expectedAddress, actualAddress);
		}
		logger.info("Successfully verify 'Anniversary'---"+expectedAddress);
	}
	
	/**
	 * Get Attribute field content via gave TPA label
	 * @param tpaLabel
	 * @return
	 */
	public String getAttributeFieldContent(String tpaLabel){
		String attributeContent = "";

		IHtmlObject[] objs = browser.getHtmlObject(".className", "attributeField", 
				".text", new RegularExpression("^"+tpaLabel+".*", false));
		if(null == objs || objs.length<1){
			throw new ObjectNotFoundException("'The attribute field object with label:"+tpaLabel+" can't be found.");
		}
		attributeContent = objs[0].text();


		Browser.unregister(objs);
		return attributeContent;	
	}
	
	/**
	 * Get per ticket group name
	 * @return
	 */
	public String getPerTicketGroupName(){
		return this.getTPAGroupsAndRelatedTPALabels().get(0).get(0);
	}
	
	/**
	 * Get per inventory group names
	 * @return
	 */
	public List<String> getPerInventoryGroupNames(){
		List<String> PerInventoryGroupNames = new ArrayList<String>();
		List<List<String>> tpasGroupsAndAssociatedTPAs = this.getTPAGroupsAndRelatedTPALabels();
		for(int i=1; i<tpasGroupsAndAssociatedTPAs.size(); i++){
			PerInventoryGroupNames.add(tpasGroupsAndAssociatedTPAs.get(i).get(0));
		}
		return PerInventoryGroupNames;
	}
	
	/**
	 * Get specific "per inventory" TPAs fields content via gave group name
	 * @param perInventoryGroupName: String
	 * @return
	 */
	public String getPerInventoryTPAsContent(String perInventoryGroupName){
		String perInventoryTPAsContent = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^"+perInventoryGroupName.replace("(", "\\(").replace(")", "\\)")+".*", false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("TR objct which text starts with - "+perInventoryGroupName+" can't be found.");
		}
		
		perInventoryTPAsContent = objs[0].text();
		Browser.unregister(objs);
		return perInventoryTPAsContent;
	}
	/**
	 * 
	 */
	public void setCurrency(String value) {
		this.setPerInventoryTourParticipantAttribute(TicketInfo.TPA_LABEL_CURRENCY, value, "CurrencyBoxRenderer", true);
	}

	/**
	 * @return
	 */
	public String getCurrency() {
		return this.getPerInventoryTourParticipantAttribute(TicketInfo.TPA_LABEL_CURRENCY,"CurrencyBoxRenderer", true);
	}
	/**
	 * Get all "per inventory" TPAs fields content via gave group names
	 * @param perInventoryGroupNames: List<String>
	 * @return
	 */
	public List<String> getPerInventoryTPAsContents(List<String> perInventoryGroupNames){
		List<String> perInventoryTPAsContents = new ArrayList<String>();
		for(int i=0; i<perInventoryGroupNames.size(); i++){
			perInventoryTPAsContents.add(this.getPerInventoryTPAsContent(perInventoryGroupNames.get(i)));
		}
		
		return perInventoryTPAsContents;
	}
	
	public void verifyPerTicketTPAsInfo(List<String> ticketTypes, List<List<Map<String, String>>> perTicketTPAsList, String contractCode) {
		List<List<Map<String, String>>> perTicketInfo = this.getPerTicketTourParticipantAttributes(
				ticketTypes, perTicketTPAsList, contractCode);
        for (int i = 0; i < perTicketInfo.size(); i++) {
			for (int j = 0; j < perTicketInfo.get(i).size(); j++) {
				for (Map.Entry<String, String> item : perTicketInfo.get(i).get(j).entrySet()) {
					String expectedVal=perTicketTPAsList.get(i).get(j).get(item.getKey());
					if (item.getKey().equals(TicketInfo.TPA_LABEL_DATEOFBIRTH) || 
							item.getKey().equals(TicketInfo.TPA_LABEL_ANNIVERSARY)) {
						if (DateFunctions.compareDates(item.getValue(), expectedVal) != 0) {
							throw new ErrorOnPageException("value for "+item.getKey()+" is wrong.", expectedVal,item.getValue());
						}
					} else if (!item.getValue().equals(expectedVal)) {
                        throw new ErrorOnPageException("value for "+item.getKey()+" is wrong.", expectedVal,item.getValue());
					}
				}
			}
		}
        logger.info("Successfully verify per ticket Tpa attrs in Change Tour Reservation Details page.");
	}
	
	public void verifyPerInventoryTPAsInfo(Map<String, String> perInventoryTPAs) {
		Map<String, String> actualPerInventoryTPAs = this.getPerInventoryTourParticipantAttributes(perInventoryTPAs);
		for (Map.Entry<String, String> item : actualPerInventoryTPAs.entrySet()) {
			String expectedVal=perInventoryTPAs.get(item.getKey());
			if (item.getKey().equals(TicketInfo.TPA_LABEL_CURRENCY) && !expectedVal.startsWith("$")) {
				expectedVal = "$" + expectedVal;
			}
			if (!item.getValue().equals(expectedVal)) {
                throw new ErrorOnPageException("value for "+item.getKey()+" is wrong.", expectedVal,item.getValue());
			}
		}
		 logger.info("Successfully verify per inventory Tpa attrs in Change Tour Reservation Details page.");
	}
}
