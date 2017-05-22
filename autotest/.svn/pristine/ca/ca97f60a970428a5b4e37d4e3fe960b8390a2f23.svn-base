/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.HFLotteryProduct;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @Description: HF Web Account Overview page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Feb 25, 2013
 */
public class HFAccountOverviewPage extends HFMyAccountPanel {
	private static HFAccountOverviewPage _instance = null;

	public static HFAccountOverviewPage getInstance() {
		if (null == _instance)
			_instance = new HFAccountOverviewPage();

		return _instance;
	}
	
	protected HFAccountOverviewPage() {
	}
	
	private final String HALID = "HAL ID";
	private final String NAME = "Name";
	private final String EMAIL = "Email Address";
	private final String PHONE = "Phone";
	private final String CUST_NUM = "Customer Number";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "newAccountOverview");
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] personalInfoDivProp() {
		return Property.toPropertyArray(".id", "personalInfo");
	}
	
	protected Property[] linkPropByText(String text) {
		return Property.toPropertyArray(".class", "Html.A", ".text", text);
	}
	
	protected Property[] currentLicensesDIVProp() {
		return Property.toPropertyArray(".id", "licenses");
	}
	
	protected Property[] currentLicensesDIVProp(String licenceName, String licenceNum) {
		return Property.toPropertyArray(".id", "licenses", ".text", new RegularExpression(".*"+licenceName+" Licence #: "+licenceNum+".*", false));
	}
	
	protected Property[] recentOrdersDIV() {
		return Property.toPropertyArray(".id", "recentOrders");
	}
	
	protected Property[] recentOrdersDIV(String licenceName, String licenceNum) {
		return Property.toPropertyArray(".id", "recentOrders", ".text", new RegularExpression(".*"+licenceName+" Licence #: "+licenceNum+".*", false));
	}
	
	protected Property[] lotteryDIVProp() {
		return Property.toPropertyArray(".id", "lotteries");
	}
	
	protected Property[] awardedLotteryTitleLink() {
		return Property.concatPropertyArray(this.a(), ".text", "Awarded Draw Applications");
	}
	
	protected Property[] awardedLotteryItemDiv() {
		return Property.concatPropertyArray(this.div(), ".className", "hfLottery_item");
	}
	
	protected Property[] awardedLotteryOrderSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "lot_value");
	}
	
	protected Property[] awardedLotteryOrderSpan(String ordNum) {
		return Property.concatPropertyArray(this.awardedLotteryOrderSpan(), ".text", ordNum);
	}
	
	protected Property[] awardedLotteryStatusSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "lot_status_green");
	}
	
	protected Property[] awardedLotteryHuntCodeSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "attr", ".text", new RegularExpression("^WMZ.*", false));
	}
	
	protected Property[] awardedLotteryHuntSpeciesSpan() {
		return Property.concatPropertyArray(this.span(), ".className", "attr", ".text", new RegularExpression("^Species.*", false));
	}
	
	protected Property[] acceptAwardedLink() {
		return Property.concatPropertyArray(this.a(), ".id", "acceptAwardOpener");
	}
	
	protected Property[] acceptAwardedDialog() {
		return Property.concatPropertyArray(this.div(), ".id", "modalPopLite-wrapper1");
	}
	
	protected Property[] acceptAwardedOKLink() {
		return Property.concatPropertyArray(this.a(), ".id", "acceptAwardButton");
	}
	
	protected Property[] moreApplicationsLink() {
		return Property.concatPropertyArray(this.a(), ".text", "More draw applications");
	}
	
	protected Property[] lotteryAppTab(){
		return Property.concatPropertyArray(a(), ".id", "lotteryApplications");
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
	
	protected Property[] showEnteredHuntsAndMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Show Entered (WMZs|Draws) and Members", false));
	}
	
	protected Property[] showGroupMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", "Show Group Members");
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
	
	protected Property[] lotteryOrderDIV(String orderNum){
		return Property.concatPropertyArray(div(), ".text", new RegularExpression("^(Big Game Draw Order|Draw & Undersubscribed Application Order) #: "+orderNum+".*", false));
	}
	
	protected Property[] licenceOrderDIV(String orderNum){
		return Property.concatPropertyArray(div(), ".text", new RegularExpression("^Licence Order #: "+orderNum+".*", false));
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
	/** Page Object Property Definition END */
	
	public boolean isShowEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), showEnteredHuntsLink()));
	}
	
	public boolean isShowEnteredHuntsLinkAndMembersExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public boolean isShowGroupMumberLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), showGroupMembersLink()));
	}
	
	public void clickShowEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), showEnteredHuntsLink()));
	}
	
	public void clickLotteryApp(){
		browser.clickGuiObject(lotteryAppTab());
	}
	
	public void clickShowEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public void clickShowGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), showGroupMembersLink()));
	}
	
	public boolean isHiddenEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public boolean isHiddenEnteredHuntsAndMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public boolean isHiddenGroupMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryOrderDIV(orderNum), hiddenGroupMembersLink()));
	}
	
	public void clickHiddenEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public void clickHiddenEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public void clickHiddenGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryOrderDIV(orderNum), hiddenGroupMembersLink()));
	}
	
	public String getEnteredHuntsText(String orderNum){
		return browser.getObjectText(Property.atList(lotteryOrderDIV(orderNum), showEnteredHuntsText()));
	}
	
	public void verifyEnteredHuntsText(String orderNum, String regx){
		String resultFromUI = getEnteredHuntsText(orderNum);
		if(resultFromUI.matches(regx)){
			logger.info("Successfully verify entered hunts text.");
		}else throw new ErrorOnPageException("Entered hunts text is wrong!", regx, resultFromUI);
	}
	
	public String getHALIDNum() {
		String text = getTopMsg();
		return RegularExpression.getMatches(text, "\\d+")[0];
	}
	
	/** Get the message on top of page when create an account successfully */
	public String getTopMsg() {
		return browser.getObjectText(".class", "Html.DIV", ".classname", "msg success");
	}
	
	/** Verify top message */
	public void verifyTopMsg(String expMsg) {
		String actMsg = this.getTopMsg();
		if (!actMsg.startsWith(expMsg)) {
			throw new ErrorOnPageException("Top message is wrong!", expMsg, actMsg);
		}
		logger.info("The top message is correct!");
	}
	
	/** Get personal info */
	public String getPersonalInfo() {
		return browser.getObjectText(".class", "HTML.DIV", ".id", "personalInfo");
	}
	
	/** Verify account email and name */
	public void verifyAccountEmailAndName(String email, String fName, String lName) {
		String personalInfo = this.getPersonalInfo();
		String expEmail = "Email Address: " + email;
		String expName = "Name: " + fName + " " + lName;
		if (!personalInfo.contains(expEmail) || !personalInfo.contains(expName)) {
			throw new ErrorOnPageException("The account email and name info are wrong!",  expEmail + ", " + expName, personalInfo);
		}
		logger.info("The account email and name are displayed correctly!");
	}
	
	public void clickUpdateLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Update");
	}
	
	public boolean isUpdateLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Update");
	}
	
	public boolean isViewLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "View");
	}
	
	public void clickAddIdentifierLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "add identification");
	}
	
	public String getPageTitleAndCaption() {
		return browser.getObjectText(".id", "pagetitle");
	}
	
	public void verifyPageTitleAndCaption(String expTitleReg) {
		String actTitle = this.getPageTitleAndCaption();
		if (!actTitle.matches(expTitleReg)) {
			throw new ErrorOnPageException("The page title and caption are wrong!", expTitleReg, actTitle);
		}
		logger.info("---Verify page title and catpion correctly!");
	}
	
	public void clickPersonalInfoLink() {
		browser.clickGuiObject(".class", "Html.A", ".className", "nameLink");
	}
	
	private IHtmlObject[] getPersonalInfoItemDivs(String itemName) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".className", "license_left", 
				".text", new RegularExpression("^" + itemName + ".*", false)));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find the div for the info " + itemName);
		}
		return objs;
	}
	
	private void clickPersonalInfoItemLink(String itemName) {
		IHtmlObject[] objs = this.getPersonalInfoItemDivs(itemName);
		browser.clickGuiObject(".class", "Html.A", false, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public void clickHALIDLink() {
		this.clickPersonalInfoItemLink(HALID);
	}
	
	public void clickCustNumberLink() {
		this.clickPersonalInfoItemLink(CUST_NUM);
	}
	
	public void clickNameLink() {
		this.clickPersonalInfoItemLink(NAME);
	}
	
	public void clickEmailAddrLink() {
		this.clickPersonalInfoItemLink(EMAIL);
	}
	
	public void clickPhoneNumLink() {
		this.clickPersonalInfoItemLink(PHONE);
	}
	
	/** Click the links on Personal Information section */
	public void clickCustInfoLink(String info) {
		browser.clickGuiObject(Property.atList(this.personalInfoDivProp(), this.linkPropByText(info)), true, 0);
	}
	
	private String getPersonalInfoItemValue(String itemName) {
		return browser.getObjectText(Property.atList(
				Property.toPropertyArray(".class", "Html.DIV", ".className", "license_left", ".text", new RegularExpression("^" + itemName + ".*", false)), 
				Property.toPropertyArray(".class", "Html.A")));
	}
	
	public String getPersonalHALID() {
		return this.getPersonalInfoItemValue(HALID);
	}
	
	public String getPersonalName() {
		return this.getPersonalInfoItemValue(NAME);
	}
	
	public String getPersonalEmail() {
		return this.getPersonalInfoItemValue(EMAIL);
	}
	
	public String getPersonalPhone() {
		return this.getPersonalInfoItemValue(PHONE).replaceAll(" ", "");
	}
	
	public String getPersonalNotice() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "hf_notice");
	}
	
	/** Verify Personal Information details */
	public void verifyPersonalInformation(String halID, String fName, String lName, String email, String phone, String notice) {
		boolean result = true;
		if (StringUtil.notEmpty(halID)) {
			result &= MiscFunctions.compareString("HAL ID in personal info section", halID, this.getPersonalHALID());
		}
		if (StringUtil.notEmpty(fName) &&  StringUtil.notEmpty(lName)) {
			result &= MiscFunctions.compareString("Name in personal info section", fName +" "+lName, this.getPersonalName());
		}
		if (StringUtil.notEmpty(email)) {
			result &= MiscFunctions.compareString("Email in personal info section", email, this.getPersonalEmail());
		}
		if (StringUtil.notEmpty(phone)) {
			result &= MiscFunctions.compareString("Phone in personal info section", phone, this.getPersonalPhone());
		}
		if (StringUtil.notEmpty(notice)) {
			result &= MiscFunctions.compareString("Notice in personal info section", notice, this.getPersonalNotice());
		}
		
		if (!result) {
			throw new ErrorOnPageException("Personal information is wrong. check logger error.");
		}
		logger.info("---Verify personal information correctly!");
	}
	
	public String getCurrentLicSectionText() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "licenses");
	}
	
	public String getRecentOrdersSectionText() {
		return browser.getObjectText(".class", "Html.DIV", ".id", "recentOrders");
	}
	
	private IHtmlObject[] getCurrentLicNameDivs() {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(
				Property.toPropertyArray(".class", "Html.DIV", ".id", "licenses"), 
				Property.toPropertyArray(".class", "Html.DIV", ".className", "name")));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find license name divs");
		}
		return objs;
	}
	
	public int getNumOfDisplayedLic() {
		IHtmlObject[] objs = this.getCurrentLicNameDivs();
		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public boolean isRecentOrderItmeExists(){
		return browser.checkHtmlObjectExists(".className", "orderHist_item");
	}
	
	public IHtmlObject[] getRecentOrderHistItemObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "orderHist_item");
		if(objs.length<1){
			throw new ObjectNotFoundException("Recent order number objects can't be found.");
		}
		return objs;
	}
	
	public IHtmlObject[] getRecentOrderItmeObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(".className", "orderItem", ".id", "orderItem");
		if(objs.length<1){
			throw new ObjectNotFoundException("Recent order number objects can't be found.");
		}
		return objs;
	} 
	
	public String[] getAllRecentOrderNums(){
		IHtmlObject[] objs = this.getRecentOrderHistItemObjs();
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
	
	public String[] getAllLicencesNum(){
		IHtmlObject[] objs = this.getRecentOrderItmeObjs();
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
	
	public String[] getAllRecentOrderPrivilegesNameAndLicenceYear(){
		IHtmlObject[] objs = this.getRecentOrderItmeObjs();
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
	
	public String getRecentOrderItemNotice(String orderNum){
//		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Licence Order #: "+orderNum+".*Price.*Licence # \\d+.*", false));
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Licence Order #: "+orderNum+".*Price.*", false));
		Property[] p2 = Property.toPropertyArray(".className", "order_item_notice");
		return browser.getObjectText(Property.atList(p1, p2)).trim();
	}
	
	public String getRecentOrderItemText(String orderNum){
		return browser.getObjectText(Property.atList(recentOrdersDIV(), licenceOrderDIV(orderNum))).trim();
	}
	
	public String getRecentLotteryOrderItemText(String orderNum){
		return browser.getObjectText(Property.atList(recentOrdersDIV(), lotteryOrderDIV(orderNum))).trim();
	}
	
	public void verifyRecentLotteryOrderItemText(String orderNum, String regx){
		String resultFromUI = getRecentLotteryOrderItemText(orderNum);
		if(resultFromUI.matches(regx)){
			logger.info("Sucessfully verify Recent lottery order item text for order:"+orderNum);
		}else throw new ErrorOnPageException("Recent lottery order item text for order"+orderNum+" is wrong!", regx, resultFromUI);
	}
	
	public String[] getRecentOrderItemsText(String[] orderNums){
		String[] texts = new String[orderNums.length];
		for(int i=0; i<orderNums.length; i++){
			texts[i] = getRecentOrderItemText(orderNums[i]);
		}
		return texts;
	}
	
	public boolean isMoreLicenseLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "More licences");
	}
	
	public void clickMoreLicenseLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "More licences");
	}
	
	public void clickCurrentLicLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Current Licences");
	}
	
	public boolean isMoreOrdersLinkExists(){
		Property p1[] = Property.toPropertyArray(".id", "recentOrders");
		Property p2[] = Property.toPropertyArray(".class", "Html.A", ".text", "More orders");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	public void clickMoreOrdersLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", "More orders");
	}
	
	/** Check if license number exist on Current Licenses Section on Account Overview page */
	public boolean isLicenseExist(String licNum) {
		return browser.checkHtmlObjectExists(Property.atList(this.currentLicensesDIVProp(), 
				Property.toPropertyArray(".class", "Html.A", ".text", licNum)));
	}
	
	public boolean isLicenceNameExistUnderCurrentLicences(String licName, String licenceNum){
		return browser.checkHtmlObjectExists(this.currentLicensesDIVProp(licName, licenceNum)); 
	}
	
	public boolean isLicenceNameExistUnderRecentOrders(String licName, String licenceNum){
		return browser.checkHtmlObjectExists(this.recentOrdersDIV(licName, licenceNum)); 
	}
	
	public void verifyLicenceNameExistUnderCurrentLicences(String licName, String licenceNum, boolean existed){
		boolean result = isLicenceNameExistUnderCurrentLicences(licName, licenceNum);
		if(result==existed){
			logger.info("Successfully verify licence name:"+licName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist")+" in current licence section.");
		}else throw new ErrorOnPageException("Failed to verify licence name :"+licName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist")+" in current licence section.");
	}
	
	public void verifyLicenceNameExistUnderUnderRecentOrders(String licName, String licenceNum, boolean existed){
		boolean result = isLicenceNameExistUnderCurrentLicences(licName, licenceNum);
		if(result==existed){
			logger.info("Successfully verify licence name:"+licName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist")+" in Recent orders section.");
		}else throw new ErrorOnPageException("Failed to verify licence name :"+licName+" and licenceNum:"+licenceNum+(existed?" exists":" doesn't exist")+" in Recent orders section.");
	}
	
	/** Verify the specific license numbers exist on Current Licenses Section on Account Overview page */
	public void verifyLicensesExist(String... licNums) {
		boolean result = true;
		for (String licNum : licNums) {
			if (!this.isLicenseExist(licNum)) {
				result &= false;
				logger.error(licNum + " doesn't exist on Account Overview page.");
			} else {
				result &= true;
				logger.info(licNum + " exists on Account Overview page.");
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Not all licenses exist on Account Overview page, check logger error above!");
		}
		logger.info("All licenses exist on Account Overview page!");
	}
	
	/** Check if order number exist on Recent Orders Section on Account Overview page */
	public boolean isOrdNumExist(String ordNum) {
		return browser.checkHtmlObjectExists(Property.atList(this.recentOrdersDIV(), 
				Property.toPropertyArray(".class", "Html.DIV", ".className", "orderHist_item", 
						".text", new RegularExpression(".*" + ordNum + ".*", false))));
	}
	
	/** Verify the specific order numbers exist on Recent Orders Section on Account Overview page */
	public void verifyOrdNumsExist(String... ordNums) {
		boolean result = true;
		for (String ordNum : ordNums) {
			if (!this.isOrdNumExist(ordNum)) {
				result &= false;
				logger.error(ordNum + " doesn't exist on Account Overview page.");
			} else {
				result &= true;
				logger.info(ordNum + " exists on Account Overview page.");
			}
		}
		
		if (!result) {
			throw new ErrorOnPageException("Not all orders exist on Account Overview page, check logger error above!");
		}
		logger.info("All orders exist on Account Overview page!");
	}
	
	public boolean checkLotteriesExisted(){
		return browser.checkHtmlObjectExists(lotteryDIVProp());
	}
	
	public void verifyNoLotteries(){
		if(checkLotteriesExisted()){
			throw new ErrorOnPageException("It should be no lotteries.");
		}else logger.info("Successfully verify no lotteries.");
	}
	
	public void clickAwardedLotteryAppTitle() {
		browser.clickGuiObject(this.awardedLotteryTitleLink());
	}
	
	public void clickAcceptInAwardedAppSection(int index) {
		browser.clickGuiObject(acceptAwardedLink(), index);
	}
	
	private int getAwardedLotteryOrdIndex(String ordNum) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.lotteryDIVProp(), this.awardedLotteryOrderSpan()));
		int index = -1;
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().equals(ordNum)) {
				index = i;
				break;
			}
		}
		if (index < 0) {
			throw new ErrorOnPageException("No awarded lottery order " + ordNum);
		}
		Browser.unregister(objs);
		return index;
	}
	
	public void clickAcceptInAwardedAppSection(String ordNum) {
		int index = this.getAwardedLotteryOrdIndex(ordNum);
		this.clickAcceptInAwardedAppSection(index);
	}
	
	public void waitAcceptAwardDialogShown() {
		browser.waitExists(acceptAwardedDialog());
	}
	
	public void clickOKOnAcceptAwardDialog() {
		browser.clickGuiObject(acceptAwardedOKLink());
	}
	
	public boolean isMoreApplicationsLinkExist() {
		return browser.checkHtmlObjectExists(Property.atList(lotteryDIVProp(),this.moreApplicationsLink()));
	}
	
	public void verifyMoreApplicationsLinkExist(boolean exp) {
		String msg = exp ? "" : "not";
		if (exp != this.isMoreApplicationsLinkExist()) {
			throw new ErrorOnPageException("More Applications link on Awarded Lottery section should " + msg + " exsit!");
		}
		logger.info("Successfully verify More Applications link on Awarded Lottery section " + msg + " exist on Awarded Lottery section!");
	}
	
	public void clickMoreApplicationsLink(){
		browser.clickGuiObject(Property.atList(lotteryDIVProp(),this.moreApplicationsLink()));
	}
	
	public boolean isAwardedLotteryAppExist(String ordNum) {
		return browser.checkHtmlObjectExists(Property.atList(this.lotteryDIVProp(), this.awardedLotteryOrderSpan(ordNum)));
	}
	
	public void verifyAwardedLotteryAppExist(String ordNum, boolean exp) {
		String msg = exp ? " exist" : " not exist";
		if (exp != this.isAwardedLotteryAppExist(ordNum)) {
			throw new ErrorOnPageException("Awarded Lottery Order "+ordNum+" should " + msg);
		}
		logger.info("Successfully verify awarded lottery order " + ordNum + msg);
	}
	
	private String getAwardedLotteryNameAndLY(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.awardedLotteryItemDiv(), this.span()));
		if (objs.length < 1 || objs.length <= index) {
			throw new ErrorOnPageException("Can't find the awarded lottery info");
		}
		String text = objs[index].text();
		Browser.unregister(objs);
		return text;
	}
	
	private String getAwardedLotteryOrderStatus(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.lotteryDIVProp(), this.awardedLotteryStatusSpan()));
		if (objs.length < 1 || objs.length <= index) {
			throw new ErrorOnPageException("Can't find the awarded lottery info");
		}
		String text = objs[index].text();
		Browser.unregister(objs);
		return text;
	}
	
	private String getAwardedLotteryHuntCode(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.lotteryDIVProp(), this.awardedLotteryHuntCodeSpan()));
		if (objs.length < 1 || objs.length <= index) {
			throw new ErrorOnPageException("Can't find the awarded lottery info");
		}
		String text = objs[index].text();
		Browser.unregister(objs);
		return text.split(":")[1].trim();
	}
	
	private String getAwardedLotteryHuntSpecies(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(this.lotteryDIVProp(), this.awardedLotteryHuntSpeciesSpan()));
		if (objs.length < 1 || objs.length <= index) {
			throw new ErrorOnPageException("Can't find the awarded lottery info");
		}
		String text = objs[index].text();
		Browser.unregister(objs);
		return text.split(":")[1].trim();
	}
	
	public void verifyAwardedLotteryAppInfo(String ordNum, HFLotteryProduct lottery) {
		boolean result = true;
		int index = this.getAwardedLotteryOrdIndex(ordNum);
		result &= MiscFunctions.compareString("Awarded lottery name and license year", lottery.getDescription()+" ("+lottery.getLicenseYear()+")", this.getAwardedLotteryNameAndLY(index));
		result &= MiscFunctions.compareString("Awarded lottery order status", lottery.getStatus(), this.getAwardedLotteryOrderStatus(index));
		result &= MiscFunctions.compareString("Awarded lottery order hunt code", lottery.getHuntCode(), this.getAwardedLotteryHuntCode(index));
		String expSpecies = "";
		for (String spe : lottery.getSpecies()) {
			expSpecies += spe + ",";
		}
		result &= MiscFunctions.containString("Awarded lottery order hunt species", expSpecies, this.getAwardedLotteryHuntSpecies(index));
	
		if (!result) {
			throw new ErrorOnPageException("Awarded lottery application info is wrong for " + ordNum);
		}
		logger.info("Succesfully verify awarded lottery application info for " + ordNum);
	}
	/**
	 * In Recent section, verify lottery application attributes, Show entered hunts and Hidden entered hunts links function
	 * @param orderNum
	 * @param recentOrderAttrsRegx
	 * @param enteredHunts
	 * @hasGroupMember lottery application has or doesn't have group member
	 */
	public void verifyRecentLotteryAppAttrs(String orderNum, String recentOrderAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember, boolean hasGroupMember){
		//At beginning, "Show entered hunts" link displays
		boolean result = MiscFunctions.matchString("Recent Lottery application attributes", getRecentLotteryOrderItemText(orderNum), recentOrderAttrsRegx);
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
			logger.info("Successfully verify lottery application attrs in account overview page recent section.");
		}else throw new ErrorOnPageException("Failed to verify lottery order attrs in account overview page recent section.");
	}
	
	public void verifyRecentLotteryAppAttrs(String orderNum, String recentOrderAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember){
		verifyRecentLotteryAppAttrs(orderNum, recentOrderAttrsRegx, enteredHunts, hasHuntAndGroupMember, false);
	}
	
	public void verifyRecentLotteryAppAttrs(String orderNum, String recentOrderAttrsRegx, String enteredHunts){
		verifyRecentLotteryAppAttrs(orderNum, recentOrderAttrsRegx, enteredHunts, false, false);
	}
	
	public String getMsg(){
		return browser.getObjectText(msg());
	}
	
	public boolean isMsgExist(String msg){
		return browser.checkHtmlObjectExists(Property.atList(lotteryDIVProp(), msg(msg)));
	}
	
	public boolean isAcceptDeclineAwardMsgExist(String msg){
		return browser.checkHtmlObjectExists(Property.atList(lotteryDIVProp(), acceptDeclineAward(msg)));
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
	
	public String getLotteriesDivContent(){
		if(checkLotteriesExisted()){
			return browser.getObjectText(lotteryDIVProp());
		}else return StringUtil.EMPTY;
	}
}
