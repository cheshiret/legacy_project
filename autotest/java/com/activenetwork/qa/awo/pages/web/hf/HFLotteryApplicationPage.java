package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
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
 * @author SWang
 * @Date  Feb 11, 2014
 */
public class HFLotteryApplicationPage extends HFMyAccountPanel {
	
	private static HFLotteryApplicationPage _instance = null;

	public static HFLotteryApplicationPage getInstance() {
		if (null == _instance)
			_instance = new HFLotteryApplicationPage();

		return _instance;
	}
	
	protected HFLotteryApplicationPage() {
	}
	
	protected static String LABEL_STATUS = "Status:";
	
	/** Page Object Property Definition Begin */
	protected Property[] lotteryAppDIV(){
		return Property.concatPropertyArray(div(), ".id", "hfLotteryApplications");
	}
	
	protected Property[] noResults(){
		return Property.concatPropertyArray(div(), ".className", "noresults");
	}
	
	protected Property[] pageTitle(){
		return Property.concatPropertyArray(div(), ".id", "pagetitle");
	}
	
	protected Property[] lotteryAppAttrs(String orderNum){
		return Property.concatPropertyArray(div(), ".id", new RegularExpression("HFLotteryApplicationsKit_\\d+_attrs", false), ".text", new RegularExpression(".*"+orderNum+".*", false));
	}
	
	protected Property[] lotDetails(String label){
		return Property.concatPropertyArray(div(), ".className", "lot_details", ".text", new RegularExpression("^"+label+".*", false));
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
	
	protected Property[] hiddenEnteredHuntsLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Hide Entered (WMZs|Draws)", false));
	}
	
	protected Property[] showGroupMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", "Show Group Members");
	}
	
	protected Property[] hiddenEnteredHuntsAndMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", new RegularExpression("Hide Entered (WMZs|Draws) and Members", false));
	}
	
	protected Property[] hiddenGroupMembersLink(){
		return Property.concatPropertyArray(span(), ".id", new RegularExpression("enteredHunt_\\d+", false), ".text", "Hide Group Members");
	}
	
	protected Property[] purchaseLicenseLink(){
		return Property.concatPropertyArray(a(), ".id", "acceptAwardOpener", ".text", "Purchase License");
	}
	
	protected Property[] declineAwardLink(){
		return Property.concatPropertyArray(a(), ".text", "Decline Award");
	}
	
	protected Property[] lotteryWrapDIV(String label){
		return Property.concatPropertyArray(div(), ".className", "lotteryWrap", ".text", new RegularExpression("^"+label+".*", false));
	}
	
	protected Property[] listControlHeader(){
		return Property.concatPropertyArray(div(), ".className", "listControl_hdr");
	}
	
	protected Property[] previous(){
		return Property.concatPropertyArray(a(), ".id", "LHFLotteryApplicationsKit_lotteryList_previous");
	}
	
	protected Property[] next(){
		return Property.concatPropertyArray(a(), ".id", "LHFLotteryApplicationsKit_lotteryList_next");
	}
	
	protected Property[] lotteryAppListAttrs(){
		return Property.concatPropertyArray(div(), ".id", "HFLotteryApplicationsKit_lotteryList_attrs");
	}
	
	protected Property[] msg(){
		return Property.concatPropertyArray(div(), ".className", "msgImportant");
	}
	
	protected Property[] acceptDeclineAward(String msg){
		return Property.concatPropertyArray(div(), ".className", "acceptDeclineAward", ".text", new RegularExpression(msg, false));
	}
	
	protected Property[] msg(String msg){
		return Property.concatPropertyArray(div(), ".className", "msgImportant", ".text", new RegularExpression(msg, false));
	}
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(lotteryAppDIV());
	}
	
	public String getNoResultMsg(){
		return browser.getObjectText(noResults());
	}
	
	public void verifyNoResultMsg(String msg){
		String msgFromUI = getNoResultMsg();
		if(msg.equals(msgFromUI)){
			logger.info("Successfully verify no results message.");
		}else throw new ErrorOnPageException("No results message is wrong!", msg, msgFromUI);
	}
	
	public String getPageTitle(){
		return browser.getObjectText(pageTitle());
	}
	
	public String getLotteryAppAttrs(String orderNum){
		return browser.getObjectText(lotteryAppAttrs(orderNum));
	}
	
	public void verifyLotteryAppAttrs(String orderNum, String lotteryAppAttrsRegx){
		String lotteryAppAttrsFromUI = getLotteryAppAttrs(orderNum);
		if(lotteryAppAttrsFromUI.matches(lotteryAppAttrsRegx)){
			logger.info("Sucessfully verify lottery application attributions for order:"+orderNum);
		}else throw new ErrorOnPageException("Lottery application attributions for order"+orderNum+" is wrong!", lotteryAppAttrsRegx, lotteryAppAttrsFromUI);
	}
	
	public boolean isShowEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), showEnteredHuntsLink()));
	}
	
	public boolean isShowEnteredHuntsLinkAndMembersExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public boolean isShowGroupMumberLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), showGroupMembersLink()));
	}
	
	public void clickShowEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), showEnteredHuntsLink()));
	}
	
	public void clickShowGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), showGroupMembersLink()));
	}
	
	public void clickShowEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), showEnteredHuntsAndMembersLink()));
	}
	
	public boolean isHiddenEnteredHuntsLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public boolean isHiddenEnteredHuntsAndMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public boolean isHiddenGroupMembersLinkExisted(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), hiddenGroupMembersLink()));
	}
	
	public void clickHiddenEnteredHuntsLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), hiddenEnteredHuntsLink()));
	}
	
	public void clickHiddenEnteredHuntsAndMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), hiddenEnteredHuntsAndMembersLink()));
	}
	
	public void clickHiddenGroupMembersLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), hiddenGroupMembersLink()));
	}
	
	public String getEnteredHuntsText(String orderNum){
		return browser.getObjectText(Property.atList(lotteryAppAttrs(orderNum), showEnteredHuntsText()));
	}
	
	public String getLotteryAppStatus(String orderNum){
		return browser.getObjectText(Property.atList(lotteryAppAttrs(orderNum), lotteryWrapDIV(LABEL_STATUS))).split(":")[1].trim();
	}
	
	public boolean isLotteryAppExist(String orderNum){
		return browser.checkHtmlObjectExists((Property.atList(lotteryAppAttrs(orderNum), lotteryWrapDIV(LABEL_STATUS))));
	}
	
	public void verifyLotteryAppExist(String orderNum, boolean existed){
		boolean resultFromUI = isLotteryAppExist(orderNum);
		if(resultFromUI!=existed){
			throw new ErrorOnPageException("Failed to verify lottery application "+(existed?"exists":"doesn't exist"));
		}else logger.info("Successfully verify lottery application "+(existed?"exists":"doesn't exist"));
	}
	
	public boolean checkLotteryAppStatus(String orderNum, String status){
		String statusFromUI = getLotteryAppStatus(orderNum);
		return statusFromUI.equals(status);
	}
	
	public void verifyLotteryAppStatus(String orderNum, String status){
		String statusFromUI = getLotteryAppStatus(orderNum);
		if(checkLotteryAppStatus(orderNum, statusFromUI)){
			logger.info("Successfylly verify lottery app:"+orderNum+" staus:"+status);
		}else throw new ErrorOnPageException("Failed to verify lottery app:"+orderNum+" status.", status, statusFromUI);
	}
	
	public void verifyEnteredHuntsText(String orderNum, String regx){
		this.clickShowEnteredHuntsLink(orderNum);
		String resultFromUI = getEnteredHuntsText(orderNum);
		if(resultFromUI.matches(regx)){
			logger.info("Successfully verify entered hunts text.");
		}else throw new ErrorOnPageException("Entered hunts text is wrong!", regx, resultFromUI);
	}
	
	public void clickPurchaseLicenseLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), purchaseLicenseLink()));
	}
	
	public void clickDeclineAwardLink(String orderNum){
		browser.clickGuiObject(Property.atList(lotteryAppAttrs(orderNum), declineAwardLink()));
	}
	
	public void verifyLotteryAppAttrs(String orderNum, String lotteryAppAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember, boolean hasGroupMember){
		//At beginning, "Show entered hunts" link displays
		boolean result = MiscFunctions.matchString("Lottery application attributes", getLotteryAppAttrs(orderNum), lotteryAppAttrsRegx);
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
			logger.info("Successfully verify lottery order attrs in lottery application page.");
		}else throw new ErrorOnPageException("Failed to verify lottery order attrs in lottery application page.");
	}
	
	public void verifyLotteryAppAttrs(String orderNum, String lotteryAppAttrsRegx, String enteredHunts, boolean hasHuntAndGroupMember){
		verifyLotteryAppAttrs(orderNum, lotteryAppAttrsRegx, enteredHunts, hasHuntAndGroupMember, false);
	}
	
	public void verifyLotteryAppAttrs(String orderNum, String lotteryAppAttrsRegx, String enteredHunts){
		verifyLotteryAppAttrs(orderNum, lotteryAppAttrsRegx, enteredHunts, false, false);
	}
	
	public boolean isListControlHeaderExisted(){
		return browser.checkHtmlObjectExists(listControlHeader());
	}
	public int getTotalNum(){
		if(!isListControlHeaderExisted()){
			return 0;
		}else return Integer.valueOf(browser.getObjectText(listControlHeader()).split("of")[1].trim());
	}
	
	public String getResultLabel(){
		return browser.getObjectText(listControlHeader()).split("Next")[1].trim();
	}
	
	public void clickPrevious(){
		browser.clickGuiObject(previous());
	}
	
	public boolean isPreviousLinkExists(){
		return browser.checkHtmlObjectExists(previous());
	}
	
	public void clickNext(){
		browser.clickGuiObject(next());
	}
	
	public boolean isNextLinkExists(){
		return browser.checkHtmlObjectExists(next());
	}
	
	public String getLotteryAppListAttrs(){
		return browser.getObjectText(lotteryAppListAttrs());
	}
	
	public void actionPageControl(boolean isNext){
		logger.info("Click "+(isNext?"Next":"Previous")+" link");
		if(isNext){
			clickNext();
		}else{
			clickPrevious();
		}

		waitLoading();
	}
	
	public String generateSearchResultLabel(int pageNum){
		int searchResultNum = getTotalNum();
		String searchResultLabel = StringUtil.EMPTY;
		if(searchResultNum>(pageNum-1)*10){
			if(searchResultNum>=pageNum*10){
				searchResultLabel = Integer.valueOf((pageNum-1)*10+1)+"-"+pageNum*10+" of "+searchResultNum;
			}else{
				searchResultLabel = Integer.valueOf((pageNum-1)*10+1)+"-"+searchResultNum+" of "+searchResultNum;
			}
		}else{
			throw new ErrorOnDataException("Actual search result number is less than the expected.", 
					String.valueOf(pageNum*10), String.valueOf(searchResultNum));
		}

		return searchResultLabel;
	}
	
	public boolean isMsgExist(String orderNum, String msg){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), msg(msg)));
	}
	
	public boolean isMsgExist(String orderNum){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), msg()));
	}
	
	public boolean isAcceptDeclineAwardMsgExist(String orderNum, String msg){
		return browser.checkHtmlObjectExists(Property.atList(lotteryAppAttrs(orderNum), acceptDeclineAward(msg)));
	}
	
	public void verifyAcceptDeclineAwardMsgExist(String orderNum, String msg, boolean existed){
		boolean actualResult= isAcceptDeclineAwardMsgExist(orderNum, msg);
		if(existed!=actualResult){
			throw new ErrorOnPageException("AcceptDeclineAwardMsg should "+(existed?"":"not ")+"exist.");
		}else logger.info("Sucessfully verify AcceptDeclineAwardMsg"+(existed?"":" exists.")+" doesn't exist.");
	}
	
	public String getMsg(String orderNum){
		if(isMsgExist(orderNum)){
			return browser.getObjectText(Property.atList(lotteryAppAttrs(orderNum), msg()));
		}else return StringUtil.EMPTY;
	}
	
	public void verifyMsg(String orderNum, String expected){
		String actual = getMsg(orderNum);
		if(expected.equals(actual)){
			logger.info("Successfully verify message for order:"+orderNum);
		}else throw new ErrorOnPageException("Failed to verify message for order:"+orderNum, expected, actual);
	}
	
	public void clickUpdateChoice(){
		
	}
}
