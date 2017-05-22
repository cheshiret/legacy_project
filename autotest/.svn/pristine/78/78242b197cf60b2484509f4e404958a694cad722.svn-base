package com.activenetwork.qa.awo.pages.web.recgov;

import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.pages.web.uwp.UwpPermitingPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: It is Entrance List page for Single Trip Itinerary permit type
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Jul 1, 2013
 */
public class RecgovSTIEntranceListPage extends UwpPermitingPage {
	private static RecgovSTIEntranceListPage _instance = null;

	public static RecgovSTIEntranceListPage getInstance() {
		if (null == _instance)
			_instance = new RecgovSTIEntranceListPage();

		return _instance;
	}

	public RecgovSTIEntranceListPage() {}

	/** Elements Properties Define Begin */
	protected Property[] getDetailsMsgPropBefSignIn() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "locationEquipmentListDetails0");
	}
	
	protected Property[] getDetailsMsgPropAftSignIn() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "stiDirectiveContent");//locationEquipmentListDetails
	}
	
	protected Property[] getPermitGridDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "permitGridContainer");
	}

	protected Property[] getAddItineraryBtnProp() {
		return Property.toPropertyArray(".id", "AddItinerary");
	}
	
	protected Property[] getLegendDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "content first");
	}
	
	protected Property[] getPageResultsSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".className", "pageresults");
	}
	
	protected Property[] getPageResultToSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "resultto");
	}
	
	protected Property[] getPageResultTotalSpanProp() {
		return Property.toPropertyArray(".class", "Html.Span", ".id", "resulttotal");
	}
	
	protected Property[] getSTIPermitRowProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "stiPermitRow");
	}
	
	protected Property[] getPreviousLinkProp() {
		return Property.toPropertyArray(".id", "resultPrevious");
	}
	
	protected Property[] getNextLinkProp() {
		return Property.toPropertyArray(".id", "resultNext");
	}
	
	protected Property[] getCalendarMonthTDProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "weeknav month");
	}
	
	protected Property[] getCalendarDateTHProp() {
		return Property.toPropertyArray(".class", "Html.th", ".className", new RegularExpression("calendar( sat)?", false)); //"calendar");
	}
	
	protected Property[] getCalendarTHeadProp() {
		return Property.toPropertyArray(".class", "Html.thead");
	}
	
	protected Property[] getCalendarPre2WeeksTDProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "weeknav week1");
	}
	
	protected Property[] getCalendarNext2WeeksTDProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "weeknav week2");
	}
	
	protected Property[] getCalendar2WeeksLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("^/singleTripPermitCalendar\\.do.*", false));
	}
	
	protected Property[] getCalendarGridStatusATDProp() {
		return Property.toPropertyArray(".class", "Html.td", ".className", "status a");
	}
	
	protected Property[] getCalendarGridStatusALinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("^/entranceDetails.do.*", false));
	}
	
	protected Property[] getEntrancePopupDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "modalPopLite-wrapper2");
	}
	
	protected Property[] getEntrancePopupTitleProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupTitle");
	}
	
	protected Property[] getEntrancePopupDesProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupDescription");
	}
	
	protected Property[] getEntrancePopupZoneProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupZone");
	}
	
	protected Property[] getEntrancePopupTypeProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupType");
	}
	
	protected Property[] getEntrancePopupPhotoProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupPhoto");
	}
	
	protected Property[] getImgProp() {
		return Property.toPropertyArray(".class", "Html.IMG");
	}
	
	protected Property[] getEntrancePopupAttrsProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "popupAttributes");
	}
	
	protected Property[] getEntrancePopupCloseProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "permitTypePopupCloser");
	}
	
	protected Property[] getLogInLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".href", "/secureEntranceSearch.do");
	}
	
	protected Property[] getAvailGridLinkProp(String date) {
		return Property.toPropertyArray(".class", "Html.A", ".href", new RegularExpression("^/entranceDetails\\.do\\?.*arvdate=" + date+".*", false));
	}
	
	protected Property[] getAvailGridTrProp(String entrance) {
		return Property.toPropertyArray(".class", "Html.tr", ".text", new RegularExpression("^" + entrance + ".*", false));
	}

	protected Property[] getWidgetDivProp () {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "pullout-1");
	}
	
	protected Property[] getWidgetClosedProp () {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "pullout-1", ".className", "pullouts side_bottom rounded borders pullout-closed");
	}

	protected Property[] getWidgetOpenedProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "pullout-1", ".className", "pullouts side_bottom rounded borders pullout-opened");
	}
	
	protected Property[] getWidgetExpandDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "pullout-button");
	}
	
	protected Property[] getWidgetExpandIconDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "icon hunts-widget");
	}
	
	protected Property[] getWidgetContentDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "itinerary-permit-widget");
	}
	
	protected Property[] getWidgetDatesThProp() {
		return Property.toPropertyArray(".class", "Html.th", ".className", "widget_date");
	}
	
	protected Property[] getWidgetEntrThProp() {
		return Property.toPropertyArray(".class", "Html.th", ".className", "widget_loc");
	}
	
	protected Property[] getWidgetGroupSizeThProp() {
		return Property.toPropertyArray(".class", "Html.th", ".className", "widget_grpsize");
	}
	
	protected Property[] getWidgetBookBtnProp() {
		return Property.toPropertyArray(".class", "Html.button", ".id", "itineraryBook");
	}
	
	protected Property[] getTableProp() {
		return Property.toPropertyArray(".class", "Html.Table");
	}
	
	protected Property[] getWidgetRemoveLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "singleTrip-removeChoice");
	}
	
	protected Property[] getWidgetRemoveAllLinkProp() {
		return Property.toPropertyArray(".class", "Html.A", ".id", "itineraryRemoveAll");
	}
	
	protected Property[] getTopErrorMsgProp() {
		return Property.toPropertyArray(".class", "Html.Div", ".id", "topErrorID");
	} 
	
	/** Elements Properties Define End */
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(getPermitGridDivProp()) && 
				(browser.checkHtmlObjectExists(getAddItineraryBtnProp()) || browser.checkHtmlObjectDisplayed(getDetailsMsgPropBefSignIn())); 
		//Sara[08/26/2013]: Add itinerary button displays after sign in, doesn't display before sign in, but Log-in link will display if doesn't sign in
	}
	
	public String getDetailsMsgAfterLoginIn() {
		return browser.getObjectText(getDetailsMsgPropAftSignIn());
	}
	
	public String getDetailsMsgBefLoginIn() {
		return browser.getObjectText(getDetailsMsgPropBefSignIn());
	}
	
	public boolean isAddItineraryBtnExist() {
		return browser.checkHtmlObjectExists(getAddItineraryBtnProp());
	}
	
	public boolean isAddItineraryBtnEnabled() {
		return browser.checkHtmlObjectEnabled(getAddItineraryBtnProp());
	}

	public void clickAddToItineraryBtn() {
		browser.clickGuiObject(getAddItineraryBtnProp());
	}
	
	public String getLegendText() {
		return browser.getObjectText(getLegendDivProp());
	}
	
	public String getPageResultsLabel() {
		return browser.getObjectText(getPageResultsSpanProp());
	}
	
	public String getPageResultToNum() {
		return browser.getObjectText(getPageResultToSpanProp());
	}
	
	public String getPageResultTotalNum() {
		return browser.getObjectText(getPageResultTotalSpanProp());
	}
	
	public int getSTIPermitRowNum() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getSTIPermitRowProp());
		int size = objs.length;
		Browser.unregister(objs);
		return size;
	}
	
	public boolean isPreviousLinkEnabled() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getPreviousLinkProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find previous link.");
		}
		boolean enabled = StringUtil.notEmpty(objs[0].getAttributeValue("href"));
		Browser.unregister(objs);
		return enabled;
	}
	
	public boolean isNextLinkEnabled() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getNextLinkProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find next link.");
		}
		boolean enabled = StringUtil.notEmpty(objs[0].getAttributeValue("href"));
		Browser.unregister(objs);
		return enabled;
	}
	
	public void clickNextLink() {
		browser.clickGuiObject(this.getNextLinkProp());
	}
	
	public String getFirstEntrance() {
		return browser.getObjectText(this.getSTIPermitRowProp());
	}
	
	public List<String> getAllEntrances() {
		List<String> entrances = browser.getObjectsText(this.getSTIPermitRowProp());
		while (this.isNextLinkEnabled()) {
			this.clickNextLink();
			this.waitLoading();
			entrances.addAll(browser.getObjectsText(this.getSTIPermitRowProp()));
		}
		return entrances;
	}
	
	public String getCalendarMonth() {
		return browser.getObjectText(this.getCalendarMonthTDProp());
	}
	
	public List<String> getCalendarDays() {
		List<String> days = browser.getObjectsText(this.getCalendarDateTHProp());
		days = days.subList(0, days.lastIndexOf(days.get(0))); // Two Calendar Dates Rows
		return days;
	}
	
	public void clickNext2Weeks() {
		browser.clickGuiObject(Property.atList(this.getCalendarNext2WeeksTDProp(), this.getCalendar2WeeksLinkProp()), true, 0);
	}
	
	public void gotoNext2Weeks() {
		this.clickNext2Weeks();
		Browser.sleep(10);
		this.waitLoading();
	}
	
	public void clickPrevious2Weeks() {
		browser.clickGuiObject(Property.atList(this.getCalendarPre2WeeksTDProp(), this.getCalendar2WeeksLinkProp()), true, 0);
	}
	
	public void gotoPrevious2Weeks() {
		this.clickPrevious2Weeks();
		Browser.sleep(OrmsConstants.SHORT_SLEEP_BEFORE_CHECK);
		this.waitLoading();
	}
	
	public boolean isCalendarGridLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(this.getCalendarGridStatusATDProp(), this.getCalendarGridStatusALinkProp()));
	}
	
	public String getFirstAvailQtyInfo() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getCalendarGridStatusATDProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find calendar grid TD with A status.");
		}
		String info = objs[0].getAttributeValue("onmouseover");
		Browser.unregister(objs);
		return info;
	}
	
	public void clickEntranceNmLink(String entrance) {
		browser.clickGuiObject(".class", "Html.A", ".text", entrance);
	}
	
	public boolean isEntrancePopupDisplay() {
		IHtmlObject[] objs = browser.getHtmlObject(getEntrancePopupDivProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find entrance pop up div.");
		}
		String style = objs[0].style("left");
		return !style.equals("-10000px");
	}
	
	public void waitForEntrancePopupDisplay(boolean isDisplay) {
		int i = 0;
		for (; i < 5; i++) {
			if (isDisplay != this.isEntrancePopupDisplay()) {
				Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
			} else {
				break;
			}
		}
	}
	
	public void waitForEntranceLink(String entrance){
		browser.searchObjectWaitExists(".class", "Html.A", ".text", entrance);
	}
	
	public void openEntrancePopup(String entrance) { 
		this.waitForEntranceLink(entrance);
		this.clickEntranceNmLink(entrance);
		this.waitForEntrancePopupDisplay(true);
	}
	
	public void closeEntranctPopup() {
		this.clickEntrancePopupCloseLink();
		this.waitForEntrancePopupDisplay(false);
	}
	
	public void clickEntrancePopupCloseLink() {
		browser.clickGuiObject(this.getEntrancePopupCloseProp());
	}
	
	public String getEntrancePopupTitle() {
		return browser.getObjectText(this.getEntrancePopupTitleProp());
	}
	
	public String getEntrancePopupDes() {
		return browser.getObjectText(this.getEntrancePopupDesProp());
	}
	
	public String getEntrancePopupZone() {
		String text = browser.getObjectText(this.getEntrancePopupZoneProp());
		return StringUtil.getSubString(text, "Zone:");
	}
	
	public String getEntrancePopupType() {
		String text = browser.getObjectText(this.getEntrancePopupTypeProp());
		return StringUtil.getSubString(text, "Type:");
	}
	
	public String getEntrancePopupPhotoSrc() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.getEntrancePopupPhotoProp(), this.getImgProp()));
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find entrance popup photo image");
		}
		String src = objs[0].getAttributeValue("src");
		Browser.unregister(objs);
		return src;
	}
	
	public String getEntrancePopupAttrs() {
		return browser.getObjectText(this.getEntrancePopupAttrsProp());
	}
	
	public void clickLogIn() {
		browser.clickGuiObject(getLogInLinkProp());
	}
	
	public void selectAvailGrid(String date) {
		browser.clickGuiObject(getAvailGridLinkProp(DateFunctions.formatDate(date, "M/d/yyyy")));
	}
	
	public void waitForAvailGrid(String entrance, String date){
		browser.searchObjectWaitExists(Property.atList(this.getAvailGridTrProp(entrance), this.getAvailGridLinkProp(date)));
	}
	
	public void selectAvailGrid(String entrance, String date) {
		date = DateFunctions.formatDate(date, "M/d/yyyy");
		logger.info("Select available grid for entrance - " + entrance + ", date = " + date);
		browser.clickGuiObject(Property.atList(this.getAvailGridTrProp(entrance), this.getAvailGridLinkProp(date)), true, 0);
		this.waitLoading();
	}
	
	public void selectAvailGrid(String entrance, String date, String lengthOfStay) {
		int length = Integer.valueOf(lengthOfStay);
		for (int i = 0; i < length; i++) {
			this.selectAvailGrid(entrance, DateFunctions.getDateAfterGivenDay(date, i));
		}
	}
	
	public boolean isItineraryWidgetDisplay() {
		return browser.checkHtmlObjectExists(getWidgetDivProp());
	}
	
	public boolean isItineraryWidgetOpen() {
		return browser.checkHtmlObjectExists(getWidgetOpenedProp());
	}
	
	public boolean isItineraryWidgetClose() {
		return browser.checkHtmlObjectExists(getWidgetClosedProp());
	}
	
	public void waitForItineraryWidget(boolean isOpen) {
		int i = 0;
		for (; i < 5; i++) {
			if (isOpen && !this.isItineraryWidgetOpen() || !isOpen && !this.isItineraryWidgetClose()) {
				Browser.sleep(OrmsConstants.DYNAMIC_SLEEP_BEFORE_CHECK);
			} else {
				break;
			}
		}
	}
	
	private IHtmlObject[] getItineraryWidgetDetailsTable() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.getWidgetContentDivProp(), this.getTableProp()));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find itinerary widget details table!");
		}
		return objs;
	}
	
	private int getItineraryWidgetRowIndex(String entrance, String startDate) {
		IHtmlObject[] tables = this.getItineraryWidgetDetailsTable();
		IHtmlTable table = (IHtmlTable)tables[0];
		int index = -1;
		for (int i = 1; i < table.rowCount(); i++) {
			if (entrance.equals(table.getCellValue(i, 1)) && 
					table.getCellValue(i, 0).startsWith(DateFunctions.formatDate(startDate, "MMM dd yyyy"))) {
				index = i;
				break;
			}
		}
		if (index == -1) {
			throw new ItemNotFoundException("Can't find itinerary widget row with entrance=" + entrance + ", start date=" + startDate);
		}
		Browser.unregister(tables);
		return index;
	}
	
	public void clickItineraryWidgetRemoveIcon(String entrance, String startDate) {
		int index = this.getItineraryWidgetRowIndex(entrance, startDate);
		browser.clickGuiObject(this.getWidgetRemoveLinkProp(), index-1);
	}
	
	public String getItineraryWidgetHead() {
		return browser.getObjectText(getWidgetExpandDivProp());
	}
	
	public String getItineraryWidgetDatesColNm() {
		return browser.getObjectText(getWidgetDatesThProp());
	}
	
	public String getItineraryWidgetEntColNm() {
		return browser.getObjectText(getWidgetEntrThProp());
	}
	
	public String getItineraryWidgetGrpSizeColNm() {
		return browser.getObjectText(getWidgetGroupSizeThProp());
	}
	
	public boolean isItineraryWidgetGrpSizeColDisplay() {
		IHtmlObject[] objs = browser.getHtmlObject(getWidgetGroupSizeThProp());
		if (objs.length < 1) {
			throw new ItemNotFoundException("can't find itinerary widget group size TH.");
		}
		boolean isDisplay = !objs[0].style("display").equals("none");
		Browser.unregister(objs);
		return isDisplay;
	}
	
	public boolean isItineraryWidgetBookBtnEnabled() {
		return browser.checkHtmlObjectEnabled(getWidgetBookBtnProp());
	}
	
	public void clickItineraryWidgetExpIcon() {
		browser.clickGuiObject(getWidgetExpandIconDivProp());
	}
	
	public void closeItineraryWidget() {
		if (this.isItineraryWidgetOpen()) {
			this.clickItineraryWidgetExpIcon();
			this.waitForItineraryWidget(false);
		}
	}
	
	public void openItineraryWidget() {
		if (this.isItineraryWidgetClose()) {
			this.clickItineraryWidgetExpIcon();
			this.waitForItineraryWidget(true);
		}
	}
	
	public void addEntrancesToItinerary(EntranceInfo... entrances) {
		for (EntranceInfo entrance : entrances) {
			if (StringUtil.isEmpty(entrance.numOfDays)) {
				this.selectAvailGrid(entrance.entrance, entrance.entryDate);
			} else {
				this.selectAvailGrid(entrance.entrance, entrance.entryDate, entrance.numOfDays);
			}
		}
		this.clickAddToItineraryBtn();
		this.waitLoading();
	}
	
	public void removeEntranceFromItinerary(String entrance, String date) {
		this.clickItineraryWidgetRemoveIcon(entrance, date);
		this.waitLoading();
	}
	
	public void removeEntrancesFromItinerary(EntranceInfo...entrances) {
		this.openItineraryWidget();
		for (EntranceInfo entrance : entrances) {
			this.removeEntranceFromItinerary(entrance.entrance, entrance.entryDate);
		}
	}
	
	public void clickItineraryWidgetRemoveAll() {
		browser.clickGuiObject(getWidgetRemoveAllLinkProp());
	}
	
	public void removeAllEntrancesFromItinerary() {
		this.openItineraryWidget();
		this.clickItineraryWidgetRemoveAll();
		this.waitLoading();
	}
	
	public String getItineraryWidgetRowText(int row) {
		IHtmlObject[] tables = this.getItineraryWidgetDetailsTable();
		IHtmlTable table = (IHtmlTable)tables[0];
		List<String> values = table.getRowValues(row);	
		values.remove(values.size() - 1); // remove the last invisible column value
		Browser.unregister(tables);
		String s = "";
		for (int i = 0; i < values.size(); i++) {
			s += values.get(i);
		}
		return s;
	}
	
	public String generateItineraryDetail(EntranceInfo entrance) {
		String exp = DateFunctions.formatDate(entrance.entryDate, "MMM dd yyyy") + "-"; 
		exp += DateFunctions.generateBookingEndDate(entrance.useType, entrance.entryDate, entrance.numOfDays, "MMM dd yyyy");
		exp += "(" + entrance.numOfDays + " " + entrance.useType + ")" + entrance.entrance;
		exp += (StringUtil.isEmpty(entrance.groupSize) ? "0" : entrance.groupSize) + "x";
		return exp;
	}
	
	public int getItineraryWidgetRemoveIconNum() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getWidgetRemoveLinkProp());
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public int getItineraryWidgetDetailsTableRows() {
		IHtmlObject[] tables = this.getItineraryWidgetDetailsTable();
		IHtmlTable table = (IHtmlTable)tables[0];
		int rowCount = table.rowCount();
		Browser.unregister(tables);
		return rowCount;
	}
	
	public void waitForItineraryWidgetBookBtn(){
		browser.searchObjectWaitExists(this.getWidgetBookBtnProp(), timeout);
	}
	
	public void clickItineraryWidgetBookBtn() {
		browser.clickGuiObject(this.getWidgetBookBtnProp());
	}
	
	public String getTopErrorMsg() {
		return browser.getObjectText(this.getTopErrorMsgProp());
	}
	
	public void verifyTopErrMsg(String exp, boolean regx) {
		String actual = this.getTopErrorMsg();
	    boolean result = true;
		if(regx){
			result = actual.matches(exp);
		}else result = exp.equals(actual); 
		
		if(!result){
			throw new ErrorOnPageException("Top error message is wrong.", exp, actual);
		}else logger.info("Successfully verify top error message:"+exp);
	}
	
	public void verifyTopErrMsg(String exp) {
		verifyTopErrMsg(exp, false);
	}
	
	public void verifyTopRegxErrMsg(String exp) {
		String actual = this.getTopErrorMsg();
		if(!exp.equals(actual)){
			throw new ErrorOnPageException("Top error message is wrong.", exp, actual);
		}
		logger.info("Successfully verify top error message:"+exp);
	}
	
	public String getEntranceTypeIconSrc(String entName) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(
				Property.toPropertyArray(".className", "stiPermitRow", ".text", entName), 
				Property.toPropertyArray(".class", "Html.img")));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find entrance type icon for " + entName);
		}
		String src = objs[0].getAttributeValue("src");
		Browser.unregister(objs);
		return src;
	}
	
	public void verifyItineraryDetails(EntranceInfo...entrances) {
		boolean result = true;
		result &= MiscFunctions.compareResult("Widget Remove Icon Count", entrances.length, getItineraryWidgetRemoveIconNum());
		if (entrances.length < 1) {
			logger.info("verify no permit details info");
			result &= MiscFunctions.compareResult("Widget Details table row count", 1, getItineraryWidgetDetailsTableRows());
		} else {
			for (int i = 0; i < entrances.length; i++) {
				String details = getItineraryWidgetRowText(i+1);
				String exp = generateItineraryDetail(entrances[i]);
				result &= MiscFunctions.compareString("Widget Details for row#" + (i+1), exp, details);
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Itinerary Widget details wrongly!");
		}
		logger.info("-------Successfully verify itinerary widget details!");
	}
}
