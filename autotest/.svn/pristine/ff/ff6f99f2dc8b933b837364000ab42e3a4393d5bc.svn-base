package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 *
 * On line Help page class
 */

public class UwpOnLineHelpPage extends UwpPage {
	private static UwpOnLineHelpPage _instance = null;

	public static UwpOnLineHelpPage getInstance() {
		if (null == _instance)
			_instance = new UwpOnLineHelpPage();

		return _instance;
	}

	public UwpOnLineHelpPage() {
	}

	public boolean exists() {
		return false;
//		return browser.checkTestObjectExists(browser
//				.getOnlineSupportSiteDocument(), 0);
	}
	/**
	 * Show left content navigation pane.
	 */
	public void showContentNav() {
		browser.clickGuiObject(".id", "btntoc");
	}
	/**
	 * Show left Search navigation pane.
	 */
	public void showSearchNav() {
		browser.clickGuiObject(".id", "btnfts");
	}
	/**
	 * Show left Index navigation pane.
	 */
	public void showIndexNav() {
		browser.clickGuiObject(".id", "btnidx");
	}
	/**
	 * Show left Glossary navigation pane.
	 */
	public void showGlossaryNav() {
		browser.clickGuiObject(".id", "btnglo");
	}
	/**
	 * Get the web site base url base on special reg pattern.
	 * @return - image's url
	 */
	private String getImgBaseUrl() {
		RegularExpression regSrc = new RegularExpression(
				"htt(p|ps)://.+\\.qa\\.reserveamerica\\.com/help/.+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG",
				".src", regSrc);
		String returnStr = ((String) objs[0].getProperty(".src"));
		Browser.unregister(objs);

		String baseUrl = returnStr.replaceAll("help.+", "");

		return baseUrl;
	}
	/**
	 * Retrieve the src value of the image located on the central of page.
	 * @return - image's src
	 */
//	private String baseSrc() {
//		String src = getImgBaseUrl() + "help/images/menu_items.gif";
//		return src;
//	}
	/**
	 * Click on Search Campgrounds.
	 */
//	public void clickOnSearchCampgrounds() {
//		String href = getImgBaseUrl() + "help/Campground_Search.htm";
//		browser.clickImageArea(baseSrc(), href, false, 0, browser
//				.getHtmlDocument());
//	}
//	/**
//	 * Click on Reservations link.
//	 */
//	public void clickOnReservations() {
//		String href = getImgBaseUrl() + "help/Reservations.htm";
//		browser.clickImageArea(baseSrc(), href, false, 0, browser
//				.getHtmlDocument());
//	}
//	/**
//	 * Click on Site Availability link.
//	 */
//	public void clickOnSiteAvailability() {
//		String href = getImgBaseUrl() + "help/Site_Availability.htm";
//		browser.clickImageArea(baseSrc(), href, false, 0, browser
//				.getHtmlDocument());
//	}
//	/**
//	 * Click on Become Member link.
//	 */
//	public void clickOnBecomeMember() {
//		String href = getImgBaseUrl() + "help/Member1.htm";
//		browser.clickImageArea(baseSrc(), href, false, 0, browser
//				.getHtmlDocument());
//	}
//	/**
//	 * Click on My Account link.
//	 */
//	public void clickOnMyAccount() {
//		String href = getImgBaseUrl() + "help/MyAccount.htm";
//		browser.clickImageArea(baseSrc(), href, false, 0, browser
//				.getHtmlDocument());
//	}
	/**
	 * Clicking About link from left navigation bar.
	 */
	public void gotoAboutByLeftNav() {
		browser.clickGuiObject(".id", "B_0");
		Browser.sleep(2);
	}
	/**
	 * Click on Using Recreation Area tab.
	 */
	public void goToUsingRecreationAreaTab() {
		browser.clickGuiObject(".id", "B_3");
		Browser.sleep(2);
	}
	/**
	 * Click on First Step In Booking Recreation Area link.
	 */
	public void gotoFirstStepInBookingRecreationArea() {
		browser.clickGuiObject(".id", "I_4");
	}
	/**
	 * Click on Last Step In Booking Recreation Area link.
	 */
	public void gotoLastStepInBookingRecreationArea() {
		browser.clickGuiObject(".id", "I_10");
	}
	/**
	 * Click on Using Camping tab.
	 */
	public void goToUsingCampingTab() {
		browser.clickGuiObject(".id", "B_11");
		Browser.sleep(2);
	}
	/**
	 * Click on First Step In Booking Camping link.
	 */
	public void gotoFirstStepInBookingCamping() {
		browser.clickGuiObject(".id", "I_12");
	}
	/**
	 * Click on Last Step In Booking Camping link.
	 */
	public void gotoLastStepInBookingCamping() {
		browser.clickGuiObject(".id", "I_18");
	}
	/**
	 * Click on Using Tour tab.
	 */
	public void goToUsingTourTab() {
		browser.clickGuiObject(".id", "B_19");
		Browser.sleep(2);
	}
	/**
	 * Click on First Step In Booking Tour link.
	 */
	public void gotoFirstStepInBookingTour() {
		browser.clickGuiObject(".id", "I_20");
	}
	/**
	 * Click on Last Step In Booking Tour link.
	 */
	public void gotoLastStepInBookingTour() {
		browser.clickGuiObject(".id", "I_25");
	}
	/**
	 * Go to Completing Your Order tab.
	 */
	public void goToCompletingOrder() {
		browser.clickGuiObject(".id", "B_26");
		Browser.sleep(2);
	}
	/**
	 * Go to First step in Completing Order process.
	 */
	public void gotoFirstStepInCompletingOrder() {
		browser.clickGuiObject(".id", "I_27");
	}
	/**
	 * Go to last step in Completing Order process.
	 */
	public void gotoLastStepInCompletingOrder() {
		browser.clickGuiObject(".id", "I_28");
	}
	/**
	 * Go to next step page by clicking on Next Step link.
	 */
	public void gotoNextStep() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next Step");
	}
	/**
	 * Click the link on Rec.gov online help home page.
	 */
	public void clickOnFindingFacility() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Finding a Facility");
	}
	/**
	 * Click on Finding a Recreation Area link.
	 */
	public void cilckOnFindingRecreationArea() {
		browser.clickGuiObject(".class", "Html.A", ".text","Finding a Recreation Area");
	}	
	/**
	 * Click on Finding a Tour link.
	 */
	public void clickOnFindingTour() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Finding a Tour");
	}
	/**
	 * Click on Sign In as a Member link.
	 */
	public void clickOnSignInAsMember() {
		browser.clickGuiObject(".class", "Html.A", ".text","Sign In as a Member");
	}
	/**
	 * Go to rec.gov help welcome page.
	 */
	public void gotoRecHelpWelcomePg() {
		browser.clickGuiObject(".class", "Html.A", ".id", "I_1");
	}
	/**
	 * Go to rec.gov help home page.
	 */
	public void gotoRecHelpHomePg() {
		browser.clickGuiObject(".class", "Html.A", ".id", "I_2");
	}
	/**
	 * Wait for page to load by waiting link Finding a Facility.
	 */
	public void clickRecHelpHomeWaitExists() {
		browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Finding a Facility");
	}
	/**
	 * Retrieve page text.
	 * @return - retrieved page text
	 */
	public String getPageText() {
		String pText = "";
		RegularExpression regTitle = new RegularExpression("Step [0-9].*",false);
		IHtmlObject[] htmlDocument = browser.getHtmlObject(".class",
				"Html.HtmlDocument", ".title", regTitle);
		pText = htmlDocument[0].getProperty(".text").toString();

		Browser.unregister(htmlDocument);
		return pText;
	}
	/**
	 * Check whether link 'Sign In a Registered Member' displays.
	 * @return
	 */
	public boolean checkMemberSignInDisplay() {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.HtmlDocument", 
		    	".title","Sign In as a Registered Member");
		if (objs.length > 0)
			toReturn = true;

		Browser.unregister(objs);
		return toReturn;
	}
	/**
	 * Go to welcome page by left navigation pane.
	 */
	public void goToWelcomePgByLeftNav() {
		browser.clickGuiObject(".id", "I_0");
	}
	/**
	 * Go to camping search help by left navigation pane.
	 */
	public void goToCampSearchHelpByLeftNav() {
		browser.clickGuiObject(".id", "B_1");
	}
	/**
	 * Go to reservation help from left navigation.
	 */
	public void goToReservationHelpByLeftNav() {
		browser.clickGuiObject(".id", "B_6");
	}
	/**
	 * Go to book site help from left navigation bar.
	 */
	public void goToBookSiteHelpByLeftNav() {
		browser.clickGuiObject(".id", "B_9");
	}
	/**
	 * Go to shopping cart from left navigation bar.
	 */
	public void goToShoopingCartHelpByLeftNav() {
		browser.clickGuiObject(".id", "B_17");
	}
	/**
	 * Go to left navigation member service help page.
	 */
	public void goToMemberServiceHelpByLeftNav() {
		browser.clickGuiObject(".id", "B_20");
	}
	/**
	 * Click on FAQ link.
	 */
	public void goToFAQByLeftNav() {
		browser.clickGuiObject(".id", "B_27");
	}
	/**
	 * set the search criteria into the keyword text field
	 * @param searchCriteria the search criteria which you want to search
	 */
	public void setKeywordField(String searchCriteria) {
		browser.setTextField(".name", "keywordField", searchCriteria);
	}
	/**
	 * Click on Go to Next Topic link.
	 */
	public void goToNextTopic() {
		String src = getImgBaseUrl() + "help/r02.gif";
		browser.clickGuiObject(".src", src);
	}
	/**
	 * Click on Go to Previous Topic link.
	 */
	public void goToPreviousTopic() {
		String src = getImgBaseUrl() + "help/r01.gif";
		browser.clickGuiObject(".src", src);
	}
	/**
	 * Click on Go button in search navigation pane.
	 */
	public void clickGoInSearchNav() {
		String src = getImgBaseUrl() + "help/go.gif";
		browser.clickGuiObject(".src", src);
	}
	/**
	 * Close the left navigation pane.
	 */
	public void hideNavComponent() {
		String src = getImgBaseUrl() + "help/close.gif";
		browser.clickGuiObject(".src", src);
	}
}
