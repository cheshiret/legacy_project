package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * 
 * @Description: This page displays after click "Discount Passes" tab link in the left of Account panel
 * URL:memberDiscountPasses.do?mode=active
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 24, 2014
 */
public class UwpDiscountPassesPage extends UwpAccountPanel{
	static class SingletonHolder {
		protected static UwpDiscountPassesPage _instance = new UwpDiscountPassesPage();
	}

	protected UwpDiscountPassesPage() {
	}

	public static UwpDiscountPassesPage getInstance() {
		return SingletonHolder._instance;
	}

	/** Page Object Property Definition Begin */
	protected Property[] activePasses(){
		return Property.concatPropertyArray(div(), ".id", "activePasses");
	}

	protected Property[] activePassesTabSelected(){
		return Property.concatPropertyArray(div(), ".id", "activePasses", ".className", new RegularExpression(".*selected$", false));
	}

	protected Property[] expiredPasses(){
		return Property.concatPropertyArray(div(), ".id", "expiredPasses");
	}

	protected Property[] expiredPassesTabSelected(){
		return Property.concatPropertyArray(div(), ".id", "expiredPasses", ".className", new RegularExpression(".*selected$", false));
	}

	protected Property[] noresults(){
		return Property.concatPropertyArray(div(), ".className", "noresults");
	}

	protected Property[] nextLink(){
		return Property.concatPropertyArray(a(), ".id", "LCustomerPassesKit_passesList_next");
	}

	protected Property[] previousLink(){
		return Property.concatPropertyArray(a(), ".id", "LCustomerPassesKit_passesList_previous");
	}

	protected Property[] discountPassesListAttrs(){
		return Property.concatPropertyArray(div(), ".className", "sgrps", ".id", new RegularExpression("CustomerPassesKit_passesList_attrs", false));
	}

	protected Property[] discountPassesAttrs(){
		return Property.concatPropertyArray(div(), ".className", "attrs", ".id", new RegularExpression("CustomerPassesKit_\\d+_attrs", false));
	}

	protected Property[] discountPassesAttrs(String passName){
		return Property.concatPropertyArray(div(), ".className", "attrs", ".id", new RegularExpression("CustomerPassesKit_\\d+_attrs", false), ".text", new RegularExpression("^"+passName+".*", false));
	}
	
	protected Property[] discountPassesAttrs(String passName, String passNum){
		return Property.concatPropertyArray(div(), ".className", "attrs", ".id", new RegularExpression("CustomerPassesKit_\\d+_attrs", false), ".text", new RegularExpression("^"+passName+".*"+passNum+".*", false));
	}
	
	protected Property[] pageControlBar(){
		return Property.concatPropertyArray(div(), ".className", "listControl_hdr");
	}

	protected Property[] moreInfoSpan(){
		return Property.concatPropertyArray(span(), ".className", "toggleArrow_Passes", ".id", new RegularExpression("attr_\\d+", false), ".text", "More Info...");
	}
	
	protected Property[] lessInfoSpan(){
		return Property.concatPropertyArray(span(), ".className", "toggleArrow_Passes", ".id", new RegularExpression("attr_\\d+", false), ".text", "Less Info...");
	}
	
	protected Property[] hiddenPassAttrs(){
		return Property.concatPropertyArray(div(), ".className", "hiddenText", ".id", new RegularExpression("attrText_\\d+", false));
	}
	
	protected Property[] showPassAttrs(){
		return Property.concatPropertyArray(div(), ".className", "showAttrText", ".id", new RegularExpression("attrText_\\d+", false));
	}
	
	protected Property[] contractBar(){
		return Property.concatPropertyArray(div(), ".className", "contentpasshdr");
	}
	
	protected Property[] pageNumDDL(){
		return Property.toPropertyArray(".id", "LCustomerPassesKit_passesList_selector_hdr");
	}
	
	/** Page Object Property Definition End */
	public boolean exists() {
		return browser.checkHtmlObjectExists(activePasses()) && browser.checkHtmlObjectExists(expiredPasses());
	}

	public void clickActivePasses(){
		browser.clickGuiObject(activePasses());
	}

	public void clickExpiredPasses(){
		browser.clickGuiObject(expiredPasses());
	}

	public String getNoResultsMsg(){
		return browser.getObjectText(noresults());
	}

	public boolean isNoResultsMsgExist(){
		return browser.checkHtmlObjectExists(noresults());
	}

	public void clickNextLink(){
		browser.clickGuiObject(nextLink());
	}

	public void clickPreviousLink(){
		browser.clickGuiObject(previousLink());
	}

	public boolean isNextLinkEnable(){
		return browser.checkHtmlObjectEnabled(nextLink());
	}

	public boolean isPreviousLinkEnable(){
		return browser.checkHtmlObjectEnabled(previousLink());
	}

	public String getPageControlBarContent(){
		return browser.getObjectText(pageControlBar());
	}

	public void clickMoreInfo(String passName, String passNum){
		browser.clickGuiObject(Property.atList(discountPassesAttrs(passName, passNum), moreInfoSpan()));
	}
	
	public void clickLessInfo(String passName, String passNum){
		browser.clickGuiObject(Property.atList(discountPassesAttrs(passName, passNum), lessInfoSpan()));
	}
	
	public boolean isMoreInfoShow(String passName, String passNum){
		return browser.checkHtmlObjectExists(Property.atList(discountPassesAttrs(passName, passNum), hiddenPassAttrs()));
	}
	
	public boolean isLessInfoShow(String passName, String passNum){
		return browser.checkHtmlObjectExists(Property.atList(discountPassesAttrs(passName, passNum), showPassAttrs()));
	}
	
	public void synPasses(String currentValue){
		String previousValue = this.getPageControlBarContent();
		long maxWaitTime=OrmsConstants.FILE_DIALOG_LONG_SLEEP*4;

		boolean isChanged=false;
		Timer time = new Timer();

		do{
			isChanged = currentValue.equals(previousValue);

		}while(time.diffLong() < maxWaitTime && isChanged);
		if(isChanged) {
			throw new ItemNotFoundException("Syn passes timed out in "+maxWaitTime+" ms");
		}
	}

	public void navigatePage(boolean isNext){
		String currentValue = this.getPageControlBarContent();

		if(isNext){
			this.clickNextLink();
		}else{
			this.clickPreviousLink();
		}

		this.synPasses(currentValue);
	}

	public IHtmlObject[] getDiscountPassesObjs(){
		IHtmlObject[] objs = browser.getHtmlObject(discountPassesAttrs());
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find any discount passes attrs.");
		}
		return objs;
	}
	public List<String> getAllDiscountPasses(){
		List<String> passes = new ArrayList<String>();
		boolean nextLinkEnable = false;
		do{
			if(nextLinkEnable){
				navigatePage(true);
			}
			IHtmlObject[] objs = getDiscountPassesObjs();
			for(int i=0; i<objs.length; i++){
				passes.add(objs[i].text());
			}
			nextLinkEnable = isNextLinkEnable();
		}while(nextLinkEnable);

		return passes;
	}

	public boolean isContractBarExist(){
		return browser.checkHtmlObjectExists(contractBar());
	}
	
	public void verifyNoContractBar(){
		if(isContractBarExist()){
			throw new ErrorOnPageException("Contract bar should not display.");
		}else logger.info("Successfully verify contract bar display.");
	}
	
	public String getContractBar(){
		return browser.getObjectText(contractBar());
	}
	
	public String getContractBar(int index){
		IHtmlObject[] objs = browser.getHtmlObject(contractBar());
		if(objs.length<index){
			throw new ObjectNotFoundException("Can't find contract bar object.");
		}
		String value = objs[index].text();
		Browser.unregister(objs);
		return value;
	}
	
	public void selectPageNum(String pageNum){
		String currentValue = this.getPageControlBarContent();
        browser.selectDropdownList(pageNumDDL(), pageNum);
		this.synPasses(currentValue);
	}
	
	public void verifyContractBar(String pageNum, String contract){
		selectPageNum(pageNum);
		String contractFromUI = getContractBar();
		if(contractFromUI.equals(contract)){
			logger.info("Contract bar is right as '"+contract+"' in page "+pageNum);
		}else throw new ErrorOnPageException("Failed to verify contract bar in page "+pageNum, contractFromUI, contract);
	}
	
	public void verifyContractBar(String contract, int index){
		String contractFromUI = getContractBar(index);
		if(contractFromUI.equals(contract)){
			logger.info("Contract bar is right as '"+contract+"'");
		}else throw new ErrorOnPageException("Failed to verify contract bar", contractFromUI, contract);
	}
	
	public void verifyNoContractBar(String pageNum){
		selectPageNum(pageNum);
		if(isContractBarExist()){
			throw new ErrorOnPageException("Contract bar should not display.");
		}else logger.info("Successfully verify contract bar display.");
	}
	
	public void verifyContractBar(String contract){
		String contractFromUI = getContractBar();
		if(contractFromUI.equals(contract)){
			logger.info("Contract bar is right as '"+contract);
		}else throw new ErrorOnPageException("Failed to verify contract bar", contractFromUI, contract);
	}
	
	public void verifyPasses(List<String> expectedPasses){
		List<String> passesFromUI = getAllDiscountPasses();
		boolean result = true;
		for(int i=0; i<expectedPasses.size(); i++){
			result = MiscFunctions.matchString(i+" - discount pass info", passesFromUI.toString(), expectedPasses.get(i));
		}
		result &= MiscFunctions.matchString("Passes sorting", passesFromUI.toString(), MiscFunctions.removeBracket(expectedPasses.toString()));
		if(!result){
			throw new ErrorOnPageException("Failed to verify all passes info, please check the details from previous log.");
		}else logger.info("Successfully verify all passes info.");
	}
	
	public void verifyMoreAndLessInfoSpan(String passName, String passNum){
		boolean result = MiscFunctions.compareResult("More info span displays at beginning", true, isMoreInfoShow(passName, passNum));
		result &= MiscFunctions.compareResult("Less info span doesn't display at beginning", false, isLessInfoShow(passName, passNum));
		clickMoreInfo(passName, passNum);
		result &= MiscFunctions.compareResult("More info span doesn't display after click its ", false, isMoreInfoShow(passName, passNum));
		result &= MiscFunctions.compareResult("Less info span displays after click 'More info' span", true, isLessInfoShow(passName, passNum));
		clickLessInfo(passName, passNum);
		if(!result){
			throw new ErrorOnPageException("Failed to verify More info and Less info span for pass which name:"+passName+", passNum:"+passNum);
		}else logger.info("Successfully verify More info and Less info span for pass which name:"+passName+", passNum:"+passNum);
	}
}
