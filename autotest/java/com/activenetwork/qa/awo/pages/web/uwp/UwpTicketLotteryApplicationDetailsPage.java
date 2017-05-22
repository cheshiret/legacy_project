package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo.LotteryChoice;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Dec 16, 2013
 */
public class UwpTicketLotteryApplicationDetailsPage extends UwpAccountPanel {
	private static UwpTicketLotteryApplicationDetailsPage _instance = null;
	
	private UwpTicketLotteryApplicationDetailsPage() {}
	
	public static UwpTicketLotteryApplicationDetailsPage getInstance() {
		if(null == _instance) {
			_instance = new UwpTicketLotteryApplicationDetailsPage();
		}
		
		return _instance;
	}
	
	/**Page Object Property Begin */
	protected Property[] choiceDiv() {
		return Property.concatPropertyArray(this.div(), ".id", new RegularExpression("choice\\d+", false));
	}
	
	protected Property[] prefLabelTD() {
		return Property.concatPropertyArray(this.td(), ".id", new RegularExpression("altchoice\\d+", false));
	}

	protected Property[] ticketPrefDiv(int index) {
		return Property.concatPropertyArray(this.div(), ".id", "choice"+index);
	}
	
	protected Property[] prefDetailsDiv(String label) {
		return Property.concatPropertyArray(this.div(), ".className", "prefRow", ".text", new RegularExpression("^"+label+".*", false));
	}
	
	protected Property[] prefDetailsLabelDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "prefCol1");
	}
	
	protected Property[] prefDetailsValueDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "prefCol2");
	}
	
	protected Property[] printTicketBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".text", "Print Tickets");
	}
	
	protected Property[] reprintTicketBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".text", "Reprint Tickets");
	}
	
	protected Property[] printPageLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Print this page");
	}
	
	protected Property[] headerMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "webspecialmessage");
	}
	
	protected Property[] applicationDetailsTable() {
		return Property.concatPropertyArray(this.table(), ".className", "formpage");
	}
	
	protected Property[] importantMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "prefinstructions");
	}
	
	protected Property[] deliveryDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "deliveryDiv");
	}
	
	protected Property[] deliveryMethodDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "deliveryMethod");
	}
	
	protected Property[] awardedChoiceTD() {
		return Property.concatPropertyArray(this.td(), ".id", "awardedchoice");
	}
	
	protected Property[] awardedChoiceTR() {
		return Property.concatPropertyArray(this.tr(), ".text", new RegularExpression("^Awarded Choice.*", false));
	}
	
	protected Property[] appNumLink(String num) {
		return Property.concatPropertyArray(this.a(), ".text", num);
	}
	
	protected Property[] trByText(String text) {
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^"+text+".*", false));
	}
	/**Page Object Property End */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.choiceDiv());
	}
	
	public List<String> getTicketPrefLabels() {
		return browser.getObjectsText(prefLabelTD());
	}
	
	private int getTotalNumOfTicketPref() {
		IHtmlObject[] objs = browser.getHtmlObject(this.prefLabelTD());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	private String getTicketPrefDetail(int index, String label) {
		String text = browser.getObjectText(Property.atList(this.ticketPrefDiv(index+1), this.prefDetailsDiv(label)));
		return text.replaceFirst(label, "").trim();
	}
	
	public String getTicketPrefTour(int index) {
		return this.getTicketPrefDetail(index, "Section");
	}
	
	public String getTicketPrefTourDate(int index) {
		return this.getTicketPrefDetail(index, "Date");
	}

	public String getTicketPrefTourTime(int index) {
		return this.getTicketPrefDetail(index, "Time");
	}

	public String getTicketPrefQty(int index, String ticketType) {
		return this.getTicketPrefDetail(index, ticketType);
	}
	
	private String[] getTicketPrefTicketInfo(Property[] topPro, Property[] pro) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(topPro, pro));
		if (objs.length < 3) {
			throw new ObjectNotFoundException("Can't find ticket divs!");
		}
		String[] types = new String[objs.length-3];
		for (int i = 0; i < types.length; i++) {
			types[i] = objs[i+3].text();
		}
		Browser.unregister(objs);
		return types;
	}
	
	public String[] getTicketPrefTypes(int index) {
		return this.getTicketPrefTicketInfo(this.ticketPrefDiv(index+1), this.prefDetailsLabelDiv());
	}
	
	public String[] getTicketPrefQtys(int index) {
		return this.getTicketPrefTicketInfo(this.ticketPrefDiv(index+1), this.prefDetailsValueDiv());
	}
	
	public List<LotteryChoice> getTicketPreferences() {
		List<LotteryChoice> choices = new ArrayList<LotteryChoice>();
		int num = this.getTotalNumOfTicketPref();
		for (int i = 0; i < num; i++) {
			LotteryChoice choice = new LotteryChoice();
			choice.tourName = this.getTicketPrefTour(i);
			choice.tourDate = this.getTicketPrefTourDate(i);
			choice.tourTime = this.getTicketPrefTourTime(i);
			choice.types = this.getTicketPrefTypes(i);
			choice.typeNums = this.getTicketPrefQtys(i);
			choices.add(choice);
		}
		return choices;
	}
	
	public void verifyTicketPreferences(TicketInfo ticket) {
		List<LotteryChoice> actual = this.getTicketPreferences();
		if (!ticket.compareLotteryChoices(actual)) {
			throw new ErrorOnPageException("Ticket Preferences are wrong in Lottery Confirmation page! Check lotter error!");
		}
	}
	
	public boolean isPrintTicketsBtnExist() {
		return browser.checkHtmlObjectExists(this.printTicketBtn());
	}
	
	public void clickPrintTicketsBtn() {
		browser.clickGuiObject(this.printTicketBtn());
	}
	
	public boolean isReprintTicketsBtnExist() {
		return browser.checkHtmlObjectExists(this.reprintTicketBtn());
	}
	
	public boolean isPrintPageLinkExist() {
		return browser.checkHtmlObjectExists(this.printPageLink());
	}
	
	public String getHeaderMsg() {
		return browser.getObjectText(this.headerMsgDiv());
	}
	
	private String getApplicationDetail(String label) {
		IHtmlObject[] objs = browser.getHtmlObject(this.applicationDetailsTable());
		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find application details table!");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(0, label);
		String value = table.getCellValue(rowIndex, 1);
		Browser.unregister(objs);
		return value;
	}
	
	public String getLotteryName() {
		return this.getApplicationDetail("Lottery Name");
	}
	
	public String getApplicationNum() {
		return this.getApplicationDetail("Application #");
	}
	
	public String getStatus() {
		return this.getApplicationDetail("Status");
	}
	
	public boolean isStatusExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.applicationDetailsTable(), this.trByText("Status")));
	}
	
	public String getApplicantName() {
		return this.getApplicationDetail("Applicant Name");
	}
	
	public String getPhone() {
		return this.getApplicationDetail("Phone");
	}
	
	public String getEmail() {
		return this.getApplicationDetail("Email");
	}
	
	public String getMailAddress() {
		return this.getApplicationDetail("Mailing Address");
	}
	
	public boolean isAwardedChoiceExist() {
		return browser.checkHtmlObjectExists(this.awardedChoiceTD());
	}
	
	public String getImportantInfo() {
		return browser.getObjectText(this.importantMsgDiv());
	}

	public boolean isImportantInfoExist() {
		return browser.checkHtmlObjectExists(this.importantMsgDiv());
	}
	
	public String getDeliveryMethod() {
		return browser.getObjectText(Property.atList(this.deliveryDiv(), this.deliveryMethodDiv()));
	}
	
	private String getTicketAwardedDetail(String label) {
		String text = browser.getObjectText(Property.atList(this.awardedChoiceTR(), this.prefDetailsDiv(label)));
		return text.replaceFirst(label, "").trim();
	}
	
	public String getTicketAwardedTourName() {
		return this.getTicketAwardedDetail("Section");
	}
	
	public String getTicketAwardedTourDate() {
		return this.getTicketAwardedDetail("Date");
	}
	
	public String getTicketAwardedTourTime() {
		return this.getTicketAwardedDetail("Time");
	}
	
	public String[] getTicketAwardedTypes() {
		return this.getTicketPrefTicketInfo(this.awardedChoiceTR(), this.prefDetailsLabelDiv());
	}
	
	public String[] getTicketAwardedQtys() {
		return this.getTicketPrefTicketInfo(this.awardedChoiceTR(), this.prefDetailsValueDiv());
	}
	
	public boolean isApplicationNumLinkExist(String appNum) {
		return browser.checkHtmlObjectExists(this.appNumLink(appNum));
	}
}
