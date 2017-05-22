package com.activenetwork.qa.awo.pages.web.hf;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PrivilegeInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HuntInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Swang
 * @Date  May 9, 2013
 */
public class HFOrderHistoryPage extends HFHeaderBar {
	private static HFOrderHistoryPage _instance = null;

	public static HFOrderHistoryPage getInstance() {
		if (null == _instance)
			_instance = new HFOrderHistoryPage();

		return _instance;
	}
	
	protected HFOrderHistoryPage() {
	}
	
	private String orderHistoryItemAttrsPropRgx = "OrderHistoryKit_\\d+_attrs";
	
	protected Property[] orderHistoryAttrs(String orderNum){
		return Property.concatPropertyArray(div(), ".id", new RegularExpression(orderHistoryItemAttrsPropRgx, false), ".text", new RegularExpression(".*"+orderNum+".*", false));
	}
	
	protected Property[] orderItemTitle(){
		return Property.concatPropertyArray(div(), ".className", "order_item_title");
	}
	
	protected Property[] orderItemTitle(String licenceName, String licenceNum){
		return Property.concatPropertyArray(div(), ".className", "order_item_title", ".text", new RegularExpression("^"+licenceName+".*"+licenceNum+"$", false));
	}
	
	protected Property[] enteredHunts(){
		return Property.concatPropertyArray(div(), ".className", "enteredHunts");
	}
	
	protected Property[] hiddenEnteredHuntsText(){
		return Property.concatPropertyArray(div(), ".className", "hiddenText", ".id", new RegularExpression("enteredHuntsText_\\d+", false));
	}
	
	protected Property[] showEnteredHuntsText(){
		return Property.concatPropertyArray(div(), ".className", "showHuntsText", ".id", new RegularExpression("enteredHuntsText_\\d+", false));
	}
	
	protected Property[] showEnteredHuntsLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Show Entered (WMZs|Draws)", false));
	}
	
	protected Property[] showGroupMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", "Show Group Members");
	}
	
	protected Property[] showEnteredHuntsAndMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Show Entered (WMZs|Draws) and Members", false));
	}
	
	protected Property[] hiddenEnteredHuntsLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Hide Entered (WMZs|Draws)", false));
	}
	
	protected Property[] hiddenEnteredHuntsAndMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Hide Entered (WMZs|Draws) and Members", false));
	}
	
	protected Property[] hiddenGroupMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", "Hide Group Members");
	}
	
	protected Property[] msg(){
		return Property.concatPropertyArray(div(), ".className", "msgImportant");
	}
	
	protected Property[] msg(String msg){
		return Property.concatPropertyArray(div(), ".className", "msgImportant", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] acceptDeclineAward(String msg){
		return Property.concatPropertyArray(div(), ".className", "acceptDeclineAward", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] orderHistoryDiv(){
		return Property.concatPropertyArray(div(), ".id", "orderHistory", ".text", new RegularExpression("^Order History.*", false));
	}
	
	protected Property[] orderHistoryForm(){
		return Property.concatPropertyArray(form(), ".id", "OrderHistoryKit_form");
	}
	
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(orderHistoryForm());
	}
	
	public boolean isShowEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), showEnteredHuntsLink()));
	}
	
	public boolean isShowGroupMumberLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), showGroupMembersLink()));
	}
	
	public boolean isShowEnteredHuntsLinkAndMembersExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public void clickShowEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), showEnteredHuntsLink()));
	}
	
	public void clickShowEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public void clickShowGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), showGroupMembersLink()));
	}
	
	public boolean isHiddenEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public boolean isHiddenEnteredHuntsAndMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public boolean isHiddenGroupMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(orderHistoryAttrs(orderNum), hiddenGroupMembersLink()));
	}
	
	public void clickHiddenEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public void clickHiddenEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public void clickHiddenGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(orderHistoryAttrs(orderNum), hiddenGroupMembersLink()));
	}
	
	public String getEnteredHuntsText(String orderNum){
		return browser.getObjectText(Property.atList(orderHistoryAttrs(orderNum), showEnteredHuntsText()));
	}
	
	public void verifyEnteredHuntsText(String orderNum, String regx){
		this.clickShowEnteredHuntsLink(orderNum);
		String resultFromUI = getEnteredHuntsText(orderNum);
		if(resultFromUI.matches(regx)){
			logger.info("Successfully verify entered hunts text.");
		}else throw new ErrorOnPageException("Entered hunts text is wrong!", regx, resultFromUI);
	}
	
	
	public IHtmlObject[] getOrderHistItmeObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "orderHist_item");
		if(objs.length<1){
			throw new ObjectNotFoundException("Recent order items objects can't be found.");
		}
		return objs;
	}
	
	public IHtmlObject[] getOrderItmeObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "orderItem", ".id", "orderItem");
		if(objs.length<1){
			throw new ObjectNotFoundException("Recent order number objects can't be found.");
		}
		return objs;
	} 
	
	public String[] getAllOrderNums(){
		IHtmlObject[] objs = this.getOrderHistItmeObjs();
		IHtmlObject[] childObjs = null;
		String[] recentOrderNum = new String[objs.length];
		
		for(int i=0; i<objs.length; i++){
			childObjs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN"), objs[i]);
			recentOrderNum[i] = childObjs[0].text().split(":")[1].trim(); //Licence Order #: 8-201 
			Browser.unregister(childObjs);
		}
		
		Browser.unregister(objs);
		return recentOrderNum;
	}
	
	public String[] getAllLicensesNameAndYear(){
		IHtmlObject[] objs = this.getOrderItmeObjs();
		IHtmlObject[] childObjs = null;
		String[] values = new String[objs.length];
		
		for(int i=0; i<objs.length; i++){
			childObjs = browser.getHtmlObject(Property.toPropertyArray(".className", "order_item_title"), objs[i]);
			values[i] = childObjs[0].text().trim(); //HFSK License002 (2013)
			Browser.unregister(childObjs);
		}
		Browser.unregister(objs);
		return values;
	}
	
	public String[] getAllLicencesNum(){
		IHtmlObject[] objs = this.getOrderItmeObjs();
		IHtmlObject[] childObjs = null;
		String[] values = new String[objs.length];
		
		for(int i=0; i<objs.length; i++){
			childObjs = browser.getHtmlObject(Property.toPropertyArray(".className", "hf_license"), objs[i]);
			values[i] = childObjs[0].text().split(":")[0].split("#")[1].trim(); //Licence # 439919 : Tue May 21 2013 12:48 AM
			Browser.unregister(childObjs);
		}
		Browser.unregister(objs);
		return values;
	}
	
	public String getHistoryOrderItemNotice(String orderNum){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Licence Order #: "+orderNum+".*", false));
		Property[] p2 = Property.toPropertyArray(".className", "order_item_notice");
		return browser.getObjectText(Property.atList(p1, p2)).trim();
	}
	
	public String getHistoryOrderItemText(String orderNum){
		Property[] p1 = Property.toPropertyArray(".id", new RegularExpression(orderHistoryItemAttrsPropRgx, false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Licence Order #: "+orderNum+".*", false));
		return browser.getObjectText(Property.atList(p1, p2)).trim();
	}
	
	public String[] getHistoryOrderItemsText(String[] orderNums){
		String[] texts = new String[orderNums.length];
		for(int i=0; i<orderNums.length; i++){
			texts[i] = getHistoryOrderItemText(orderNums[i]);
		}
		return texts;
	}
	
	public String getOrderListText() {
		return browser.getObjectText(".id", "orderHistoryList");
	}
	
	public String getPageTitleAndCaption() {
		return browser.getObjectText(".id", "pagetitle");
	}
	
	public String getNoResultsMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "noresults");
	}
	
	public boolean isNoResultsMsgExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "noresults");
	}
	
	public boolean isDateFilterListExist() {
		return browser.checkHtmlObjectExists(".id", "ordersDateRange");
	}
	
	public boolean isPrdTypeFilterListExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "filterCategory INLINE selectable");
	}
	
	public boolean isPageControlExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "listControl_hdr");
	}
	
	public String getDateRangeFilterLabel() {
		return browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "hfDropdown_row"), 
				Property.toPropertyArray(".class", "Html.Span")));
	}
	
	public String getSelectedDateRange() {
		return browser.getDropdownListValue(".id", "ordersDateRange");
	}
	
	public List<String> getDateRangeFilterOpts() {
		return browser.getDropdownElements(".id", "ordersDateRange");
	}

	public void selectDateRangeFilter(String opt) {
		browser.selectDropdownList(".id", "ordersDateRange", opt);
	}
	
	public void filterByDateRange(String opt) {
		this.selectDateRangeFilter(opt);
		this.waitLoading();
	}
	
	public String getSelectedPrdTypeFilter() {
		return browser.getObjectText(".class", "Html.li", ".className", new RegularExpression("^filterItem.*selected", false));
	}
	
	public String getLicencePrdTypeFilter() {
		return browser.getObjectText(".class", "Html.A", ".title", "Licence");
	}
	
	private String getOrdNumInPrdTypeFilter(String title) {
		String num = browser.getObjectText(Property.atList(Property.toPropertyArray(".class", "Html.A", ".title", title), 
				Property.toPropertyArray(".class", "Html.Span", ".className", "dim")));
		return RegularExpression.getMatches(num, "\\d+")[0];
	}
	
	public String getLicNumInPrdTypeFilter() {
		return this.getOrdNumInPrdTypeFilter("Licence");
	}
	
	public String getAllNumInPrdTypeFilter() {
		return this.getOrdNumInPrdTypeFilter("All");
	}
	
	public void clickPrdTypeFilter(String title) {
		browser.clickGuiObject(".class", "Html.A", ".title", title);
	}
	
	public void clickLicPrdTypeFilter() {
		this.clickPrdTypeFilter("Licence");
	}
	
	public void filterByLicPrdType() {
		this.clickLicPrdTypeFilter();
		this.waitLoading();
	}
	
	public void clickAllPrdTypeFilter() {
		this.clickPrdTypeFilter("All");
	}
	
	public void clickLicenseNum(String licNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(".*" + licNum + "$", false));
	}
	
	public void clickFirstLicenseNum() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Licence # \\d+", false));
	}
	
	public String getPageControlText() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "listControl_hdr");
	}
	
	public String getFooterPageControlText() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "listControl_fdr");
	}
	
	public String getNumOfAllResults() {
		String text = this.getPageControlText(); // Previous | Next 1-7 of 7
		if (text != null)
			text = text.split("of")[1].trim();
		else
			text = "0";
		return text;
	}
	
	public boolean isPagePickerExist() {
		return browser.checkHtmlObjectExists(".id", "LOrderHistoryKit_orderHistoryList_selector_hdr");
	}
	
	public boolean isFooterPagePickerExist() {
		return browser.checkHtmlObjectExists(".id", "LOrderHistoryKit_orderHistoryList_selector_ftr");
	}
	
	public boolean isPagePreviousLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_hdr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_previous")));
	}
	
	public boolean isFooterPagePreviousLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_ftr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_previous")));
	}
	
	public boolean isPageNextLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_hdr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_next")));
	}
	
	public boolean isFooterPageNextLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_ftr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_next")));
	}
	
	public void clickPagePreviousLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_hdr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_previous")), false, 0);
	}
	
	public void clickFooterPagePreviousLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_ftr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_previous")), false, 0);
	}
	
	public void clickPageNextLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_hdr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_next")), false, 0);
	}
	
	public void clickFooterPageNextLink() {
		browser.clickGuiObject(Property.atList(Property.toPropertyArray(".class", "Html.DIV", ".className", "listControl_ftr"), 
				Property.toPropertyArray(".id", "LOrderHistoryKit_orderHistoryList_next")), false, 0);
	}
	
	public void gotoNextPage(boolean fromHeader) {
		if (fromHeader) {
			this.clickPageNextLink();
		} else {
			this.clickFooterPageNextLink();
		}
		this.waitLoading();
	}
	
	public void gotoPreviousPage(boolean fromHeader) {
		if (fromHeader) {
			this.clickPagePreviousLink();
		} else {
			this.clickFooterPagePreviousLink();
		}
		this.waitLoading();
	}
	
	public void selectPagePicker(String value) {
		browser.selectDropdownList(".id", "LOrderHistoryKit_orderHistoryList_selector_hdr", value);
	}
	
	public void selectFooterPagePicker(String value) {
		browser.selectDropdownList(".id", "LOrderHistoryKit_orderHistoryList_selector_ftr", value);
	}
	
	public void gotoPageFromPagePicker(String pgNum, boolean fromHeader) {
		if (fromHeader) {
			this.selectPagePicker(pgNum);
		} else {
			this.selectFooterPagePicker(pgNum);
		}
		this.waitLoading();
	}
	
	public List<String> getAllOrderDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "orderHist_date");
		List<String> dates = new ArrayList<String>();
		for (int i = 0; i < objs.length; i++) {
			dates.add(objs[i].text());
		}
		Browser.unregister(objs);
		return dates;
	}
	
	public boolean isOrderExist(String ordNum) {
		String text = this.getOrderListText();
		return text.contains(ordNum);
	}
	
	public void verifyOrderExist(String ordNum, boolean exp) {
		String msg = exp ? " " : " NOT ";
		if (exp != this.isOrderExist(ordNum)) {
			throw new ErrorOnPageException(ordNum + " should" + msg + "exist!");
		}
		logger.info(ordNum + " do" + msg + "exist!");
	}

	public String getLicOrdDetailsText(String ordNum) {
		return browser.getObjectText(Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression("OrderHistoryKit_\\d+_attrs", false), 
				".text", new RegularExpression("^Licence Order #: " + ordNum + ".*", false)));
	}
	
	public void verifyLicenseOrderDetails(boolean isValidDatesHide, String ordNum, String ordDate, PrivilegeInfo... privileges) {
		// the expect order details text
		String expDetails = "^Licence Order #: " + ordNum + "\\s*" + ordDate + ".*";
		String pricInfo = "";
		for (PrivilegeInfo pri : privileges) {
			if (StringUtil.notEmpty(pri.name)) {
				//Lesley[20140226]: update the details display due to product change
				expDetails += pricInfo;
				expDetails += "\\s*" + pri.name + "\\s*\\(" + pri.licenseYear + "\\)\\s*";
				if (StringUtil.notEmpty(pri.creationPrice)) {
					pri.creationPrice = new DecimalFormat("0.00").format(Double.valueOf(pri.creationPrice));
					if (pri.qty.equals("1")) {
						pricInfo = "Price:\\s*\\$" + pri.creationPrice; // Price: $8.60
					} else {
						Double totalPrice = Double.valueOf(pri.qty) * Double.valueOf(pri.creationPrice);
						pricInfo = "Price\\s*\\(" + pri.qty + "\\s*@\\s*\\$" + pri.creationPrice + "\\):\\s*\\$" + new DecimalFormat("0.00").format(totalPrice); //Price (2 @ $8.60): $17.20
					}
				}
			}
			
			expDetails += "\\s*Licence\\s*#\\s*" + pri.privilegeId;
			if (!isValidDatesHide) { //Lesley[20131030]: update per defect 54237
				expDetails +=  "\\s*:\\s*" + pri.validFromDate + ".*";
				if (StringUtil.notEmpty(pri.validToDate)) {
					expDetails += "-" + pri.validToDate + ".*";
				}
			}	 
			expDetails += "\\s*" + pri.status + "(\\s)?";
		}
		expDetails += pricInfo;
		
//		PrivilegeInfo pri = privileges[0];
//		if (StringUtil.notEmpty(pri.creationPrice)) {
//			privileges[0].creationPrice = new DecimalFormat("0.00").format(Double.valueOf(pri.creationPrice));
//			if (pri.qty.equals("1")) {
//				expDetails += "Price:\\s*\\$" + pri.creationPrice; // Price: $8.60
//			} else {
//				Double totalPrice = Double.valueOf(pri.qty) * Double.valueOf(pri.creationPrice);
//				expDetails += "Price\\s*\\(" + pri.qty + "\\s*@\\s*\\$" + pri.creationPrice + "\\):\\s*\\$" + new DecimalFormat("0.00").format(totalPrice); //Price (2 @ $8.60): $17.20
//			}
//		}
				
		if (!MiscFunctions.matchString("Order Details Info", this.getLicOrdDetailsText(ordNum), expDetails)) {
			throw new ErrorOnPageException(ordNum + " order details info is wrong on order history page!");
		}
		logger.info(ordNum + " order details info is correct on order history page!");
	}
	
	public String getPriValidDates(String ordNum, String priNum) {
		String info = browser.getObjectText(Property.atList(
				Property.toPropertyArray(".class", "Html.DIV", ".className", "groupcard VIEW", ".text", new RegularExpression("^Licence Order #: " + ordNum + ".*", false)), 
				Property.toPropertyArray(".class", "Html.DIV", ".className", "hf_license", ".text", new RegularExpression("^Licence # " + priNum + ".*", false))));
		String date = StringUtil.getSubString(info, ":"); // Licence # 948045 : Mon Jun 17 2013 07:37 PM
		return date;
	}
	
	public String getHuntInfoByPrivNameAndLy(String ordNum, String privName, String ly) {
		return browser.getObjectText(Property.atList(
				Property.toPropertyArray(".class", "Html.DIV", ".id", new RegularExpression("OrderHistoryKit_\\d+_attrs", false), 
						".text", new RegularExpression("^Permit Order #: " + ordNum + ".*", false)), 
				Property.toPropertyArray(".class", "Html.DIV", ".className", "orderItem", ".text", 
						new RegularExpression("^"+privName+" \\("+ly+"\\).*", false))));
	}
	
	public void verifyHuntInfo(String ordNum, PrivilegeInfo...privs) {
		boolean result = true;
		
		for (int i = 0; i < privs.length; i++) {
			PrivilegeInfo priv = privs[i];
			String itemInfo = this.getHuntInfoByPrivNameAndLy(ordNum, priv.name, priv.licenseYear);
			String expHuntInfo = priv.name + " \\(" + priv.licenseYear + "\\).*";
			for (int j = 0; j < priv.hunts.size(); j++) {
				HuntInfo hunt = priv.hunts.get(j);
				String info = "Hunt Information";
				//The display of the hunt information is configurable in related ui-options.xml,  <option name="huntInformation"> -> <option name="order-history" >
				info += "\\s*Hunt Description: " + hunt.getDescription() + 	
							"\\s*Hunt Code: " + hunt.getHuntCode() +
							"\\s*Species: " + hunt.getSpecie() + (StringUtil.notEmpty(hunt.getSpecieSubType()) ? " - "+hunt.getSpecieSubType() : "") + 
							(StringUtil.notEmpty(hunt.getWeaponDes()) ? "\\s*Weapon: "+hunt.getWeaponDes() : "");
				if (hunt.getDatePeriodInfo() != null) {
					String datePeridInfo = hunt.getDatePeriodInfo().getDatePeriodInfoForWeb(priv.licenseYear);
					info += "\\s*Date Period: " + datePeridInfo.replace("(", "\\(\\s*").replace(")", "\\)");
				}
				if (StringUtil.notEmpty(hunt.getHuntLocationInfo())) {
					info += "\\s*Location: "+hunt.getHuntLocationInfo();
				}
				if (hunt.getLocationInfo() != null) {
					String subLocInfo = hunt.getLocationInfo().getAllSubLocationInfo();
					if (StringUtil.notEmpty(subLocInfo))
						info += "\\s*Sub-Locations: " + subLocInfo.replace("(", "\\(").replace(")", "\\)");
				}
				info += "\\s*# of Tags: " + hunt.getNumOfTagQty();
				expHuntInfo += info + ".*";
			}
			result &= MiscFunctions.matchString("The hunt info of " + priv.name + " " + priv.licenseYear + " ordNum=" + ordNum, itemInfo, expHuntInfo);
		}
		
		if (!result) {
			throw new ErrorOnPageException("Hunt Info for the order #" + ordNum + " on order history page is wrong!");
		}
		logger.info("Hunt Info for the order #" + ordNum + " on order history page is correct!");
	}
	
	
	public String getOrderHistoryAttrs(String orderNum){
		return browser.getObjectText(orderHistoryAttrs(orderNum));
	}
	
	public void verifyOrderHistoryAttrs(String orderNum, String orderHistoryAttrsRegx){
		String orderHistoryAttrsFromUI = getOrderHistoryAttrs(orderNum);
		if(orderHistoryAttrsFromUI.matches(orderHistoryAttrsRegx)){
			logger.info("Sucessfully verify order history attributions for order:"+orderNum);
		}else throw new ErrorOnPageException("Order history attributions for order"+orderNum+" is wrong!", orderHistoryAttrsRegx, orderHistoryAttrsFromUI);
	}
	
	/**
	 * In Recent section, verify lottery application attributes, Show entered hunts and Hidden entered hunts links function
	 * @param orderNum
	 * @param recentOrderAttrsRegx
	 * @param enteredHunts
	 * @hasGroupMember lottery application has or doesn't have group member
	 */
	public void verifyLotteryAppHistoryAttrs(String orderNum, String orderHistoryAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember, boolean hasGroupMember){
		//At beginning, "Show entered hunts" link displays
		boolean result = MiscFunctions.matchString("Order History attributes", getOrderHistoryAttrs(orderNum), orderHistoryAttrsRegx);
		result &= MiscFunctions.compareResult("Show "+(hasHuntAndGroupMember?"Entered WMZs and Members":(hasGroupMember?"Group Members":"Entered WMZs"))+" link displays", true, 
				hasHuntAndGroupMember?isShowEnteredHuntsLinkAndMembersExisted(orderNum):(hasGroupMember?isShowGroupMumberLinkExisted(orderNum):isShowEnteredHuntsLinkExisted(orderNum)));
		result &= MiscFunctions.compareResult("Hidden "+(hasHuntAndGroupMember?"Entered WMZs and Members":(hasGroupMember?"Group Members":"Entered WMZs"))+" link doesn't display", false, 
				hasHuntAndGroupMember?isHiddenEnteredHuntsAndMembersLinkExisted(orderNum):(hasGroupMember?isHiddenGroupMembersLinkExisted(orderNum):isHiddenEnteredHuntsLinkExisted(orderNum)));

		//"Hidden Entered Hunts" link displays after click show entered hunts link
		if(hasHuntAndGroupMember){
			clickShowEnteredHuntsAndMembersLink(orderNum);
		}else if(hasGroupMember){
			clickShowGroupMembersLink(orderNum);
		}else clickShowEnteredHuntsLink(orderNum);
		
		result &= MiscFunctions.compareResult("Show "+(hasHuntAndGroupMember?"Entered WMZs and Members":(hasGroupMember?"Group Members":"Entered WMZs"))+" link doesn't display", false, 
				hasHuntAndGroupMember?isShowEnteredHuntsLinkAndMembersExisted(orderNum):(hasGroupMember?isShowGroupMumberLinkExisted(orderNum):isShowEnteredHuntsLinkExisted(orderNum)));
		result &= MiscFunctions.compareResult("Hidden "+(hasHuntAndGroupMember?"Entered WMZs and Members":(hasGroupMember?"Group Members":"Entered WMZs"))+" link displays", true, 
				hasHuntAndGroupMember?isHiddenEnteredHuntsAndMembersLinkExisted(orderNum):(hasGroupMember?isHiddenGroupMembersLinkExisted(orderNum):isHiddenEnteredHuntsLinkExisted(orderNum)));

		//Check entered hunts
		result &= MiscFunctions.compareResult("Entered hunts", enteredHunts, getEnteredHuntsText(orderNum));

		if(result){
			logger.info("Successfully verify lottery order attrs in order history page.");
		}else throw new ErrorOnPageException("Failed to verify lottery order attrs in order history page.");
	}
	
	public void verifyLotteryAppHistoryAttrs(String orderNum, String orderHistoryAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember){
		verifyLotteryAppHistoryAttrs(orderNum, orderHistoryAttrsRegx, enteredHunts, hasHuntAndGroupMember, false);
	}
	
	public void verifyLotteryAppHistoryAttrs(String orderNum, String orderHistoryAttrsRegx, String enteredHunts){
		verifyLotteryAppHistoryAttrs(orderNum, orderHistoryAttrsRegx, enteredHunts, false, false);
	}
	
	public void verifyLotteryName(String orderNum, String lotteryName){
		String orderHistoryAttr = getOrderHistoryAttrs(orderNum);
		if(orderHistoryAttr.contains(lotteryName)){
			logger.info("Successfully verify lotteryname:"+lotteryName+" when order number is "+orderNum);
		}else throw new ErrorOnPageException("Failed to verify lottery name when order number is "+orderNum, lotteryName, orderHistoryAttr);
	}

	public String getMsg(){
		return browser.getObjectText(msg());
	}
	
	public boolean isMsgExist(String msg){
		return browser.checkHtmlObjectExists(msg(msg));
	}
	
	public boolean isAcceptDeclineAwardMsgExist(String msg){
		return browser.checkHtmlObjectExists(acceptDeclineAward(msg));
	}
	
	public void verifyAcceptDeclineAwardMsgExist(String msg, boolean existed){
		boolean actualResult= isAcceptDeclineAwardMsgExist(msg);
		if(existed!=actualResult){
			throw new ErrorOnPageException("AcceptDeclineAwardMsg should "+(existed?"":"not ")+"exist.");
		}else logger.info("Sucessfully verify AcceptDeclineAwardMsg"+(existed?"":" exists.")+" doesn't exist.");
	}
	
	public void verifyMsg(String expected){
		String actual = getMsg();
		if(expected.equals(actual)){
			logger.info("Successfully verify message.");
		}else throw new ErrorOnPageException("Failed to verify message.", expected, actual);
	}
	
	
	public boolean isLicenceExist(String licenceName, String licenceNum){
		return browser.checkHtmlObjectExists(orderItemTitle(licenceName, licenceNum));
	}
	
	public void verifyLicenceExist(String licenceName, String licenceNum, boolean existed){
		boolean resultFromUI = isLicenceExist(licenceName, licenceNum);
		if(existed=resultFromUI){
			logger.info("Successfully verify licenceName:"+licenceName+" and licenceNum:"+licenceNum+(existed?" exists":"doesn't exist")+" in Order History page.");
		}else throw new ErrorOnPageException("Failed to verify licenceName:"+licenceName+" and licenceNum:"+licenceNum+(existed?" exists":"doesn't exist")+" in Order History page.");
	}
}
