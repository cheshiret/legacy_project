package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo.LotteryChoice;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Ticket lottery submission confirmation page. Right now the page is only in Rec.gov
 *
 * @author Lesley Wang
 * @Date  Nov 29, 2013
 */
public class UwpTicketLotteryConfirmationPage extends UwpHeaderBar {
	private static UwpTicketLotteryConfirmationPage _instance = null;

	private UwpTicketLotteryConfirmationPage() {}

	public static UwpTicketLotteryConfirmationPage getInstance() {
		if(null == _instance) {
			_instance = new UwpTicketLotteryConfirmationPage();
		}

		return _instance;
	}
	
	/** Page Property Begin*/
	protected Property[] h1() {
		return Property.toPropertyArray(".class", "Html.h1");
	}
	
	protected Property[] gotoHomeBtn() {
		return Property.toPropertyArray(".id", "backhome");
	}
	
	protected Property[] ticketChoiceTable() {
		return Property.concatPropertyArray(this.table(), ".className", "ticketChoice");
	}
	
	protected Property[] leftAdPanelDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "leftAdpanel");
	}
	
	protected Property[] spotImg(String src) {
		return Property.concatPropertyArray(this.img(), ".src", "/marketing/spots/" + src);
	}
	
	protected Property[] aplNumDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "aplNumberCol2");
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
	
	protected Property[] prefLabelTD() {
		return Property.concatPropertyArray(this.td(), ".id", new RegularExpression("altchoice\\d+", false));
	}
	
	protected Property[] subConfirmMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "confirmationMessage");
	}
	
	protected Property[] trByText(String text) {
		return Property.concatPropertyArray(this.tr(), ".text", new RegularExpression("^"+text+".*", false));
	}
	
	protected Property[] sectionHeadDiv(String header) {
		return Property.concatPropertyArray(this.div(), ".className", "contenthdr", ".text", header);
	}
	
	protected Property[] deliveryDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "deliveryDiv");
	}
	
	protected Property[] deliveryLabel() {
		return Property.concatPropertyArray(this.td(), ".className", "pref-choice");
	}
	
	protected Property[] deliveryMethodDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "deliveryMethod");
	}
	/** Page Property End*/
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(gotoHomeBtn()) &&
				browser.checkHtmlObjectExists(ticketChoiceTable());
	}
	
	public boolean isMarketingSpotExist(String spotSrc) {
		return browser.checkHtmlObjectExists(Property.atList(this.leftAdPanelDiv(), this.spotImg(spotSrc)));
	}
	
	public String getOrdNum() {
		return browser.getObjectText(this.aplNumDiv());
	}

	public boolean isApplicationNumExist() {
		return browser.checkHtmlObjectExists(this.aplNumDiv());
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

	private String[] getTicketPrefTicketInfo(int index, Property[] pro) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.ticketPrefDiv(index+1), pro));
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
		return this.getTicketPrefTicketInfo(index, this.prefDetailsLabelDiv());
	}
	
	public String[] getTicketPrefQtys(int index) {
		return this.getTicketPrefTicketInfo(index, this.prefDetailsValueDiv());
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
	
	public String getSubConfirmMsg() {
		return browser.getObjectText(this.subConfirmMsgDiv());
	}
	
	public String getPageHeader() {
		return browser.getObjectText(Property.atList(this.form(), this.h1()));
	}
	
	public boolean isLotteryNameExist(String name) {
		return browser.checkHtmlObjectExists(".class", "Html.td", ".text", name);
	}
	
	public String getApplicantName() {
		String text = browser.getObjectText(this.trByText("Name"));
		return text.replace("Name", "").trim();
	}
	
	public String getApplicantMailAddress() {
		String mailLabel = "Mailing Address";
		String mailAddr = browser.getObjectText(trByText(mailLabel));
		return mailAddr.replace(mailLabel, StringUtil.EMPTY).trim();
	}
	
	public boolean isSectionHeaderExist(String header) {
		return browser.checkHtmlObjectExists(this.sectionHeadDiv(header));
	}
	
	public String getDeliveryMethodLabel() {
		return browser.getObjectText(Property.atList(this.deliveryDiv(), this.deliveryLabel()));
	}

	public String getDeliveryMethod() {
		return browser.getObjectText(Property.atList(this.deliveryDiv(), this.deliveryMethodDiv()));
	}
	
	public boolean isMsgExist(String msg) {
		return browser.checkHtmlObjectExists(Property.concatPropertyArray(this.div(), 
				".text", new RegularExpression(msg+".*", false)));
	}
	
	public void clickContinueToHome() {
		browser.clickGuiObject(this.gotoHomeBtn());
	}
}
