package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * 
 * @Description: This page displays after click Active Passed tab In UwpDiscountPassesPage
 * URL:memberDiscountPasses.do?mode=active
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 24, 2014
 */
public class UwpActivePassesPage extends UwpDiscountPassesPage{
	static class SingletonHolder {
		protected static UwpActivePassesPage _instance = new UwpActivePassesPage();
	}

	protected UwpActivePassesPage() {
	}

	public static UwpActivePassesPage getInstance() {
		return SingletonHolder._instance;
	}
	
	/** Page Object Property Definition Begin */
	
	/** Page Object Property Definition End */
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(activePassesTabSelected());
	}
	
	public String getDiscountPassesListAttrsContent(){
		return browser.getObjectText(discountPassesListAttrs());
	}
	
	public boolean isPassNumExist(String passNum){
		return getDiscountPassesListAttrsContent().contains(passNum);
	}
	
	public void verifyPassNumExist(String passNum, boolean existed){
		boolean resultInUI = isNoResultsMsgExist()? false: isPassNumExist(passNum);
		if(resultInUI!=existed){
			throw new ErrorOnPageException("Failed to verify pass number "+(existed?"":"doesn't ")+"display.");
		}else logger.info("Successfully verify pass number "+(existed?"":"doesn't ")+"display.");
	}
}
