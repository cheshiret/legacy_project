package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo;
import com.activenetwork.qa.awo.datacollection.legacy.web.TicketInfo.LotteryChoice;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: Ticket lottery application page. Right now the page is only in Rec.gov
 * 
 * @author Lesley Wang
 * @Date  Nov 27, 2013
 */
public class UwpTicketLotteryApplicationPage extends UwpHeaderBar {
	private static UwpTicketLotteryApplicationPage _instance = null;

	private UwpTicketLotteryApplicationPage() {}

	public static UwpTicketLotteryApplicationPage getInstance() {
		if(null == _instance) {
			_instance = new UwpTicketLotteryApplicationPage();
		}

		return _instance;
	}
	
	/** Page Property Begin*/
	protected Property[] mainForm() {
		return Property.concatPropertyArray(this.form(), ".id", "ticketLotteryOrderDetailsForm");
	}
	
	protected Property[] h2() {
		return Property.toPropertyArray(".class", "Html.h2");
	}
	
	protected Property[] lotteryHeaderMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "webspecialmessage");
	}
	
	protected Property[] groupLeaderHiddenField() {
		return Property.concatPropertyArray(this.input("hidden"), ".id", "groupLeader");
	}
	
	protected Property[] trByText(String text) {
		return Property.concatPropertyArray(this.tr(), ".text", new RegularExpression("^"+text+".*", false));
	}
	
	protected Property[] sectionHeaderDiv(String text) {
		return Property.concatPropertyArray(this.div(), ".className", "content shop", ".text", new RegularExpression("^"+text+".*", false));
	}
	
	protected Property[] sectionMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "prefinstructions");
	}
//	
//	protected Property[] preferenceChoiceTable() {
//		return Property.concatPropertyArray(this.table(), ".className", "ticketChoice");
//	}
	
	protected Property[] prefLabelTD() {
		return Property.concatPropertyArray(this.td(), ".id", new RegularExpression("altchoice\\d+", false));
	}

	protected Property[] preferenceChoiceDiv(int index) {
		return Property.concatPropertyArray(this.div(), ".id", "choice"+index);
	}
	
	protected Property[] preferenceChoiceLabel(int index) {
		return Property.concatPropertyArray(this.td(), ".id", "altchoice"+index);
	}
	
	protected Property[] preferenceTourLabel(int index) {
		return Property.concatPropertyArray(this.label(), ".for", "tourid"+index);
	}

	protected Property[] preferenceTourList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "tourid"+index);
	}

	protected Property[] preferenceTourDateLabel(int index) {
		return Property.concatPropertyArray(this.label(), ".for", "tourdateid"+index);
	}

	protected Property[] preferenceTourDateList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "tourdateid"+index);
	}

	protected Property[] preferenceTourTimeLabel(int index) {
		return Property.concatPropertyArray(this.label(), ".for", "tourtimeid"+index);
	}

	protected Property[] preferenceTourTimeList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", "tourtimeid"+index);
	}

	protected Property[] preferenceTicketTypeQtyLabel(int index) {
		return Property.concatPropertyArray(this.label(), ".for", new RegularExpression("ticktypeqty_\\d+id"+index, false));
	}

	protected Property[] preferenceTicketTypeQtyLabel(String ticketType, int index) {
		return Property.concatPropertyArray(this.label(), ".for", new RegularExpression("ticktypeqty_\\d+id"+index, false), 
				".text", ticketType);
	}

	protected Property[] preferenceTicketTypeQtyList(int index) {
		return Property.concatPropertyArray(this.select(), ".id", new RegularExpression("ticktypeqty_\\d+id"+index, false));
	}

	protected Property[] preferenceTicketTypeQtyList(String id) {
		return Property.concatPropertyArray(this.select(), ".id", id);
	}
	
	protected Property[] submitBtn() {
		return Property.toPropertyArray(".class", "Html.button", ".id", "continueshop");
	}
	
	protected Property[] deliveryDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "deliveryDiv");
	}
	
	protected Property[] deliveryLabel() {
		return Property.concatPropertyArray(this.td(), ".className", "pref-choice");
	}
	
	protected Property[] deliveryMethodTextDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "deliveryMethodBig");
	}
	
	protected Property[] deliveryList() {
		return Property.concatPropertyArray(this.select(), ".id", "deliverymethodid");
	}
	
	protected Property[] leftAdPanelDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "leftAdpanel");
	}
	
	protected Property[] spotImg(String src) {
		return Property.concatPropertyArray(this.img(), ".src", "/marketing/spots/" + src);
	}

	protected Property[] labelSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "extra");
	}
	
	protected Property[] topErrorMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".id", "topErrorID", ".className", "msg error");
	}

	protected Property[] errorMsgDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "msg error");
	}
	/** Page Property End*/
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(mainForm());
	}
	
	public void clickSubmit() {
		browser.clickGuiObject(this.submitBtn());
	}
	
	public String getHeaderMsg() {
		return browser.getObjectText(this.lotteryHeaderMsgDiv());
	}
	
	public String getLotteryName() {
		return browser.getObjectText(Property.atList(this.table(), this.h2()));
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
	
	public String getPreferencesSectionMsg() {
		return browser.getObjectText(Property.atList(this.sectionHeaderDiv("Ticket Preferences"), this.sectionMsgDiv()));
	}
	
	public String getPreferenceChoiceLabel(int index) {
		String text = browser.getObjectText(this.preferenceChoiceLabel(index));
		return text.replace("*", "").trim();
	}
	
	public String getPreferenceTourLabel(int index) {
		return browser.getObjectText(this.preferenceTourLabel(index));
	}
	
	public String getPreferenceTourDateLabel(int index) {
		return browser.getObjectText(this.preferenceTourDateLabel(index));
	}

	public String getPreferenceTourTimeLabel(int index) {
		return browser.getObjectText(this.preferenceTourTimeLabel(index));
	}

	public String getPreferenceTicketTypeLabel(int index) {
		return browser.getObjectText(this.preferenceTicketTypeQtyLabel(index));
	}
	
	public String getSelectedPreferenceTour(int index) {
		return browser.getDropdownListValue(this.preferenceTourList(index), 0);
	}
	
	public void selectPreferencesTour(String tour, int index) {
		browser.selectDropdownList(this.preferenceTourList(index), tour);
	}
	
	public void selectPreferencesTour(int tourIndex, int index) {
		browser.selectDropdownList(this.preferenceTourList(index), tourIndex);
	}
	
	public boolean isPreferenceTourDateDisplayed(int index) {
		return browser.checkHtmlObjectDisplayed(this.preferenceTourDateList(index));
	}
	
	public boolean isPreferenceTourDateDisabled(int index) {
		return !browser.checkHtmlObjectEnabled(this.preferenceTourDateList(index));
	}
	
	public String getSelectedPreferenceTourDate(int index) {
		return browser.getDropdownListValue(this.preferenceTourDateList(index), 0);
	}
	
	public void selectPreferencesTourDate(String date, int index) {
		date = DateFunctions.formatDate(date, "MMM d, yyyy");
		browser.selectDropdownList(this.preferenceTourDateList(index), date);
	}

	public void selectPreferencesTourDate(int dateIndex, int index) {
		browser.selectDropdownList(this.preferenceTourDateList(index), dateIndex);
	}
	
	public boolean isPreferenceTourTimeDisplayed(int index) {
		return browser.checkHtmlObjectDisplayed(this.preferenceTourTimeList(index));
	}
	
	public boolean isPreferenceTourTimeDisabled(int index) {
		return !browser.checkHtmlObjectEnabled(this.preferenceTourTimeList(index));
	}
	
	public String getSelectedPreferenceTourTime(int index) {
		return browser.getDropdownListValue(this.preferenceTourTimeList(index), 0);
	}
	
	public void selectPreferencesTourTime(int timeInd, int index) {
		browser.selectDropdownList(this.preferenceTourTimeList(index), timeInd);
	}
	
	public void selectPreferencesTourTime(String time, int index) {
		browser.selectDropdownList(this.preferenceTourTimeList(index), time);
	}

	private String getPreferencesTicketTypeListID(String ticketType, int index) {
		IHtmlObject[] objs = browser.getHtmlObject(this.preferenceTicketTypeQtyLabel(ticketType, index));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the ticket type list for " + ticketType + "with index=" + index);
		}
		String id = objs[0].getAttributeValue("for");
		
		Browser.unregister(objs);
		return id;
	}

	public boolean isPreferenceTicketTypeQtyExist(int index) {
		return browser.checkHtmlObjectExists(this.preferenceTicketTypeQtyList(index));
	}
	
	public String getSelectedPreferenceTicketTypeQty(String ticketType, int index) {
		String id = this.getPreferencesTicketTypeListID(ticketType, index);
		return browser.getDropdownListValue(this.preferenceTicketTypeQtyList(id), 0);
	}

	public List<String> getPreferenceTicketTypeQtys(String ticketType, int index) {
		String id = this.getPreferencesTicketTypeListID(ticketType, index);
		return browser.getDropdownElements(this.preferenceTicketTypeQtyList(id));
	}
	
	public void selectPreferencesTicketTypeQty(String ticketType, String qty, int index) {
		String id = this.getPreferencesTicketTypeListID(ticketType, index);
		browser.selectDropdownList(this.preferenceTicketTypeQtyList(id), qty);
	}
	
	public void clickPreferredChoiceTD() {
		browser.clickGuiObject(this.preferenceChoiceLabel(0));
	}

	public void removeFocus() {
		this.waitLoading();
		browser.clickGuiObject(this.labelSpan());
		this.waitLoading();
	}

	public void setPreferencesChoice(LotteryChoice choice, int index) {
		logger.info("Setup preferences choice "+index);
		if (StringUtil.isEmpty(choice.tourName)) {
			this.selectPreferencesTour(0, index);
			this.removeFocus(); 
		} else {
			this.selectPreferencesTour(choice.tourName, index);
			this.removeFocus(); // to remove focus
			if (StringUtil.notEmpty(choice.tourDate)) {
				this.selectPreferencesTourDate(choice.tourDate, index);
			} else {
				this.selectPreferencesTourDate(0, index);
			}
			this.removeFocus(); 
			if (StringUtil.isEmpty(choice.tourTime)) {
				this.selectPreferencesTourTime(0, index);
			} else {
				this.selectPreferencesTourTime(choice.tourTime, index);
			}
			this.removeFocus(); 
			if (choice.types != null && choice.types.length > 0) {
				if (choice.typeNums != null && choice.typeNums.length > 0) {
					for (int i = 0; i < choice.types.length; i++) {
						this.selectPreferencesTicketTypeQty(choice.types[i], choice.typeNums[i], index);
					}
				}
			} 
		}
	}
	
	public void setPreferencesChoices(List<LotteryChoice> choices) {
		for (int i = 0; i < choices.size(); i++) {
			LotteryChoice choice = choices.get(i);
			this.setPreferencesChoice(choice, i);
		}
	}
	
	public void setPreferencesChoice(TicketInfo ticket, int index) {
		LotteryChoice choice = new LotteryChoice();
		choice.tourName = ticket.tourName;
		choice.tourDate = ticket.tourDate;
		choice.tourTime = ticket.tourTime;
		choice.types = ticket.ticketTypes.toArray(new String[0]);
		choice.typeNums = ticket.ticketTypeNums.toArray(new String[0]);
		
		this.setPreferencesChoice(choice, index);
	}
	
	public void setPreferredChoice(TicketInfo ticket) {
		this.setPreferencesChoice(ticket, 0);
	}
	
	public boolean isDeliveryMethodDisplayed() {
		return browser.checkHtmlObjectDisplayed(this.deliveryDiv());
	}
	
	public String getDeliveryMethodLabel() {
		String label = browser.getObjectText(Property.atList(this.deliveryDiv(), this.deliveryLabel()));
		return label.replace("*", "").trim();
	}
	
	public String getDeliveryMethodText() {
		return browser.getObjectText(this.deliveryMethodTextDiv());
	}
	
	public boolean isDeliveryMethodListDisplayed() {
		return browser.checkHtmlObjectDisplayed(this.deliveryList());
	}
	
	public String getSelectedDeliveryMethod() {
		return browser.getDropdownListValue(this.deliveryList(), 0);
	}
	
	public List<String> getDeliveryMethodOptions() {
		return browser.getDropdownElements(this.deliveryList());
	}
	
	public void selectDeliveryMethod(String method) {
		browser.selectDropdownList(this.deliveryList(), method);
	}
	
	public String getTermsOfUseSectionMsg() {
		return browser.getObjectText(Property.atList(this.sectionHeaderDiv("Terms of Use"), this.sectionMsgDiv()));
	}
	
	public boolean isMarketingSpotExist(String spotSrc) {
		return browser.checkHtmlObjectExists(Property.atList(this.leftAdPanelDiv(), this.spotImg(spotSrc)));
	}
	
	public String getTopErrorMsg() {
		return browser.getObjectText(this.topErrorMsgDiv());
	}
	
	public void verifyTopErrorMsg(String exp) {
		String actual = this.getTopErrorMsg();
		if (!exp.equalsIgnoreCase(actual)) {
			throw new ErrorOnPageException("Top error message is wrong!", exp, actual);
		}
		logger.info("Verify top error message successfully!");
	}
	
	public String getItemErrorMsg(int index) {
		return browser.getObjectText(Property.atList(this.preferenceChoiceDiv(index), this.errorMsgDiv()));
	}
	
	public int getTotalNumOfChoices() {
		IHtmlObject[] objs = browser.getHtmlObject(this.prefLabelTD());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
}
