package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author QA
 * @Date  Mar 5, 2012
 */
public class UwpTourOrderDetailsPage extends TourParticipantDetailsPage {
	public final String PAGE_TITLE = "Tour Order Details";
	public final String CHANGE_PAGE_TITLE = "Change Tour Order Details";
	
	private static UwpTourOrderDetailsPage _instance = null;

	public static UwpTourOrderDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpTourOrderDetailsPage();

		return _instance;
	}

	protected UwpTourOrderDetailsPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "continueshop", ".text", "Continue to Shopping Cart");
	}

	public IHtmlObject[] getTicketTypeTable(){
		return browser.getTableTestObject(".class", "ticketTypeTable");
	}

	public void setTicketTypeInfo(String ticketNum, String[] ticketTypeInfo){
		IHtmlObject[] objs = this.getTicketTypeTable();
		if(objs.length<0){
			throw new ObjectNotFoundException("Ticket type table can't be found.");
		}else{
			String[] s = { "1_[0-9]_20000261", "1_[0-9]_20000262",
			"1_[0-9]_20000263" };

			for (int i = 0; i < s.length; i++) {
				IHtmlObject[] objs1 = browser.getHtmlObject(".class",
						"Html.INPUT.text", ".name", new RegularExpression(s[i], false));

				for (int j = 0; j < objs1.length; j++) {
					switch (i) {
					case 0:
						((IText) objs1[j]).setText(ticketTypeInfo[0]);
						break;
					case 1:
						((IText) objs1[j]).setText(ticketTypeInfo[1]);
						break;
					case 2:
						((IText) objs1[j]).setText(ticketTypeInfo[2]);
						break;
					}
				}

				Browser.unregister(objs1);
			}

		}
		Browser.unregister(objs);
	}		

	public void setParticipantAddress(String participantAddress){
		browser.setTextArea(".id", "20000265", participantAddress);
	}

	public void selectCheckBoxAgreementAccepted() {
		browser.selectCheckBox(".id", "agreement");
	}

	public void deselectCheckBoxAgreementAccepted() {
		browser.unSelectCheckBox(".id", "agreement");
	}

	public void clickContinueToShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
	/**
	 * Verify attribute field error message
	 * @param expectErrorMes
	
	/**
	 * Click "Make More Reservations" link
	 */
    public void clickMakeMoreReservationsLink(){
    	Property[] p = Property.toPropertyArray(".id", "reservemore", ".className", "reservemore", ".text", "Make More Reservations");
    	browser.clickGuiObject(p);
    }

	/**
	 * Set tour order details
	 * @param ticket
	 */
	public void setOrderDetails(TicketInfo ticket) {
		if(ticket.ticketTypes.size()>0 || ticket.perTicketTPAsList.size()>0){
			this.setPerTicketTourParticipantAttributes(ticket.ticketTypes, ticket.perTicketTPAsList, ticket.contractCode);
		}
		if(ticket.perInventoryTPAs.size()>0){
			this.setPerInventoryTourParticipantAttributes(ticket.perInventoryTPAs);
		}
		this.setTicketTypeInfo(ticket.ticketNums, ticket.participantInfo);
		this.setParticipantAddress(ticket.participantAddress);
		this.selectCheckBoxAgreementAccepted();
	}
	
	/**
	 * Verify all field data for "Per Ticket"
	 * @param actualPerTicketTPAsList_Expecte: List<List<Map<String, String>>>
	 */
	public void verifyAllFieldsData_PerTicket(List<List<Map<String, String>>> perTicketTPAsList_Expected, List<String> ticketTypes, List<List<Map<String, String>>> perTicketTPAsList, String contractCode){
		logger.info("Verify all field data for 'Per Ticket'");

		List<List<Map<String, String>>> perTicketTPAsList_Actual= this.getPerTicketTourParticipantAttributes(ticketTypes, perTicketTPAsList, contractCode);

		//Compare the length of outside list
		if(perTicketTPAsList_Expected.size()!=perTicketTPAsList_Actual.size()){
			throw new ErrorOnPageException("The length of compared outsize list are different.", 
					String.valueOf(perTicketTPAsList_Expected.size()), String.valueOf(perTicketTPAsList_Actual.size()));
		}

		//Compare the length of inside list
		for(int i=0; i<perTicketTPAsList_Expected.size(); i++){
			if(perTicketTPAsList_Expected.get(i).size()!=perTicketTPAsList_Actual.get(i).size()){
				throw new ErrorOnPageException("The length of compared inside list are different.", 
						String.valueOf(perTicketTPAsList_Expected.size()), String.valueOf(perTicketTPAsList_Actual.size()));
			}
		}

		Map<String, String> TPA_expected = new HashMap<String, String>();
		//Compare field data
		for(int i=0; i<perTicketTPAsList_Actual.size(); i++){
			for(int j = 0; j < perTicketTPAsList_Actual.get(i).size(); j ++) {
				TPA_expected = perTicketTPAsList_Expected.get(i).get(j);
				for(Map.Entry<String, String> entry: TPA_expected.entrySet()){

					String expectedValue = entry.getValue();
					String actualValue = perTicketTPAsList_Actual.get(i).get(j).get(entry.getKey());
					if(!expectedValue.equals(actualValue)){
						throw new ErrorOnPageException("The tpa value is wrong!", expectedValue, actualValue);
					}
					logger.info("Successfully verify TPA:"+entry.getKey()+" value:"+expectedValue);
				}
				//				Iterator<Map.Entry<String, String>> actualIt = perTicketTPAsList_Actual.get(i).get(j).entrySet().iterator();
				//				TPA_expected = perTicketTPAsList_Expecte.get(i).get(j);
				//
				//				while(actualIt.hasNext()){
				//					Map.Entry<String, String> actualEntry = (Map.Entry<String, String>)actualIt.next();
				//					actualKey = actualEntry.getKey();
				//					actualValue = actualEntry.getValue();
				//
				//					if(!TPA_expected.get(actualKey).equals(actualValue)) {
				//						throw new ErrorOnPageException("The tpa value is wrong!", TPA_expected.get(actualKey), actualValue);
				//					}
				//					logger.info("Successfully verify TPA:"+actualKey+" value:"+TPA_expected.get(actualKey));
				//				}
			}
		}
	}

	/**
	 * Verify all field data for "Per Inventory"
	 * @param perInventoryTPAsList_Expect: Map<String, String>
	 */
	public void verifyAllFieldsData_PerInventory(Map<String, String> perInventoryTPAsList_Expect, Map<String, String> perInventoryTPAs){
		logger.info("Verify all field data for 'Per Inventory'");
		Map<String, String> perInventoryTPAsList_Actual = this.getPerInventoryTourParticipantAttributes(perInventoryTPAs);

		//Compare the length of outside list
		if(perInventoryTPAsList_Expect.size()!=perInventoryTPAsList_Actual.size()){
			throw new ErrorOnPageException("The length of compared outsize list are different.", 
					String.valueOf(perInventoryTPAsList_Expect.size()), String.valueOf(perInventoryTPAsList_Actual.size()));
		}

		for(Map.Entry<String, String> entry: perInventoryTPAsList_Expect.entrySet()){
			String expectedValue = entry.getValue();
			String actualValue = perInventoryTPAsList_Actual.get(entry.getKey());
			if(!expectedValue.equals(actualValue)){
				throw new ErrorOnPageException("The tpa value is wrong!", expectedValue, actualValue);
			}
			logger.info("Successfully verify TPA:"+entry.getKey()+" value:"+expectedValue);
		}
		//		for(int i=0; i<perInventoryTPAsList_Actual.size(); i++){
		//			Iterator<Map.Entry<String, String>> actualIt = perInventoryTPAsList_Actual.entrySet().iterator();
		//
		//			while(actualIt.hasNext()){
		//				Map.Entry<String, String> actualEntry = (Map.Entry<String, String>)actualIt.next();
		//				actualKey = actualEntry.getKey();
		//				actualValue = actualEntry.getValue();
		//
		//				if(!perInventoryTPAsList_Expect.get(actualKey).equals(actualValue)) {
		//					throw new ErrorOnPageException("The tpa value is wrong!", perInventoryTPAsList_Expect.get(actualKey), actualValue);
		//				}
		//				logger.info("Successfully verify TPA:"+actualKey+" value:"+perInventoryTPAsList_Expect.get(actualKey));
		//			}
		//		}
	}
	
	/**
	 * Verify no change for permit inventory TPAs
	 * @param perInventoryGroupNames
	 * @param perInventoryTPAsContents_Expected
	 */
	public void verifyPerInventoryTPAsContent(List<String> perInventoryGroupNames, List<String> perInventoryTPAsContents_Expected){
		if(perInventoryTPAsContents_Expected.size()!=perInventoryGroupNames.size()){
			throw new ErrorOnPageException("The length of group names size and related TPAs contents size is different.",
					String.valueOf(perInventoryTPAsContents_Expected.size()), String.valueOf(perInventoryGroupNames.size()));
		}

		List<String> perInventoryTPAsContents_Actual = this.getPerInventoryTPAsContents(perInventoryGroupNames);

		if(perInventoryTPAsContents_Expected.size()!=perInventoryTPAsContents_Actual.size()){
			throw new ErrorOnPageException("The length of 'Per Inventory' TPAs contents size is different.",
					String.valueOf(perInventoryTPAsContents_Expected.size()), String.valueOf(perInventoryTPAsContents_Actual.size()));
		}

		for(int i=0; i<perInventoryTPAsContents_Expected.size(); i++){
			if(!perInventoryTPAsContents_Expected.get(i).equals(perInventoryTPAsContents_Actual.get(i))){
				throw new ErrorOnPageException("Failed to verify 'Per Inventory' TPAs content with group name - "+perInventoryGroupNames.get(i));
			}
			logger.info("Successfully verify 'Per Inventory' TPAs content with group name - "+perInventoryGroupNames.get(i));
		}
	}

	/**
	 * @param label
	 */
	public void verifyAttrbuteMandatory(String label) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression(label+".*",false));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find attribute by label '"+label+"'");
		}
		for(int i=0;i<objs.length;i++){
			if(!objs[0].text().contains(label+"*")){
				throw new ErrorOnPageException("The attribute '"+label+"' which of index is "+i+" should be Mandatory" );
			}
		}
		Browser.unregister(objs);
	}
	
	/**
	 * Verify the ticket types and related type number indexes
	 * For example
	 * Adult
	 * 1 ...............
	 * 2 ...............
	 * Child
	 * 1 ...............
	 * 2 ...............
	 * 3 ...............
	 * @param ticketTypes
	 * @param ticketTypeNos
	 */
	public void verifyTicketTypesAndRelatedTypeNumIndexes(List<String> ticketTypes, List<String> ticketTypeNos){
		//Get all ticket types and related type number index info from UI
		List<String> ticketTypesInfo_Actual = this.getEachTicketTypeValues();
		List<String> ticketTypesInfo_Expected = new ArrayList<String>();

		//Compare the length of expected ticket types and related type number index
		if(ticketTypes.size()!=ticketTypeNos.size()){
			throw new ErrorOnPageException("The length of ticket types and related type number indexes are different.", String.valueOf(ticketTypes.size()), String.valueOf(ticketTypeNos.size()));
		}

		//Generate the expected ticket types and related type number index
		for(int i=0; i<ticketTypes.size(); i++){
			ticketTypesInfo_Expected.add(ticketTypes.get(i));
			for(int j=0; j<Integer.valueOf(ticketTypeNos.get(i)); j++){
				ticketTypesInfo_Expected.add(String.valueOf(j+1));
			}
		}

		//Compare the length of expected and actual ticket types and related type number index
		if(ticketTypesInfo_Actual.size()!=ticketTypesInfo_Expected.size()){
			throw new ErrorOnPageException("The length of expected and actual ticket types and related type number indexes are different.", String.valueOf(ticketTypesInfo_Expected.size()), String.valueOf(ticketTypesInfo_Actual.size()));
		}

		//Compare the value of expected and actual ticket types and related type numbers
		for(int i=0; i<ticketTypesInfo_Actual.size(); i++){
			if(!ticketTypesInfo_Actual.get(i).startsWith(ticketTypesInfo_Expected.get(i))){
				throw new ErrorOnPageException("The actual value doesn't start with expect:"+ticketTypesInfo_Expected.get(i), ticketTypesInfo_Expected.get(i), ticketTypesInfo_Actual.get(i));
			}
			logger.info("Successfully veirify ticket type and related type number indexes info:"+ticketTypesInfo_Expected.get(i));

		}
	}

	/**
	 * @param string
	 * @return
	 */
	public List<String> getPerTicketTourParticipantAttributeLabels(String type) {
		
		List<String> attrLabelList=new ArrayList<String>();
		IHtmlObject[] objs=browser.getTableTestObject(".className", "attributesSubgroup");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find table for Ticket Types.");
		}
		
		IHtmlTable table=(IHtmlTable)objs[0];
		
		int attrRow=table.findRow(0, type)+1;
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR",table );
		if(trs==null || trs.length<=attrRow){
			throw new ObjectNotFoundException("Can't find TR object for Ticket Type:"+type);
		}
		IHtmlObject[] labels=browser.getHtmlObject(".class", "Html.LABEL", trs[attrRow]);
		for(IHtmlObject o:labels){
			attrLabelList.add(o.text());
		}
		Browser.unregister(labels,trs,objs);
		return attrLabelList;
	}
	
	/**
	 * Get page title
	 */
	public String getPageTitle() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "pagetitle");
	}
	
	/**
	 * Compare page title
	 */
	public boolean comparePageTitle(String expected) {
		return MiscFunctions.compareString("Page Title", expected, this.getPageTitle());
	}
	
	/**
	 * Verify page title
	 */
	public void verifyPageTitle(String expected) {
		if (!this.comparePageTitle(expected)) {
			throw new ErrorOnPageException("Page Title is wrong. Check logger error!");
		}
	}
	
	/**
	 * Get tour common info, including tour park name, contract, tour name, tour date and time, and tour numbers
	 */
	public String getTourCommonInfo() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "campname");
	}
	
	/**
	 * Compare tour info
	 */
	public boolean compareTourInfo(String mes, String tourInfo) {
		String actTourInfo = this.getTourCommonInfo();
		if (!actTourInfo.contains(tourInfo)) {
			logger.error(mes + ":" + tourInfo + " doesn't shown in the Tour Order Details page!");
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Get tour important info
	 */
	public String getImportantInfo(int index) {
		IHtmlObject[] divs = browser.getHtmlObject(".class", "Html.DIV", ".classname", "tour_important_info");
		if (divs.length < index) {
			throw new ErrorOnPageException("Can't find the important info divs.");
		}
		String text = divs[index].text();
		Browser.unregister(divs);
		return text;
	}
	
	/**
	 * Compare tour important info
	 */
	public boolean compareTourImportantInfo(String tourName, String info, int index) {
		return MiscFunctions.compareString("Important Infomation", tourName + " " + info, this.getImportantInfo(index));
	}
	
	/**
	 * Click shopping cart button without checking acknowledge checkbox
	 */
	public void GoToShoppingCartWithoutCheckingAcknowledge() {
		logger.info("Unselect acknowlege checkbox and click go to shopping cart button...");
		this.deselectCheckBoxAgreementAccepted();
		this.clickContinueToShoppingCart();
		this.waitLoading();
	}
	
	/**
	 * Get error message
	 */
	public String getErrorMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg error");
	}
	
	/**
	 * Verify error message
	 */
	public void verifyErrorMsg(String exp) {
		String actual = this.getErrorMsg();
		if (!exp.equals(actual)) {
			throw new ErrorOnPageException("The error message is wrong!", exp, actual);
		}
		logger.info("Error message is correct as " + exp);
	}
}
