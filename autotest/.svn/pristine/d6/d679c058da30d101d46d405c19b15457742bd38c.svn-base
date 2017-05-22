/*
 * Created on Dec 28, 2009
 *
 */
package com.activenetwork.qa.awo.keywords.orms;

import com.activenetwork.qa.awo.datacollection.legacy.RequestInfo;
import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrEndCallPage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.callManager.CallMgrTopMenuPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsLaunchPadPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSigninPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsSiteListPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsSiteSearchPage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterAlertDialog;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterDecreaseSitePage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterIncreaseSitePage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterNewTicketPage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterSearchLocationsPage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterSearchPage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterSupportCasePage;
import com.activenetwork.qa.awo.pages.orms.supportCenter.SupportCenterTopMeunPage;

/**
 * @author szhou, eliang
 */
public class SupportCenter extends Orms {
	private static SupportCenter _instance = null;

	public static SupportCenter getInstance() {
		if (null == _instance)
			_instance = new SupportCenter();

		return _instance;
	}

	private SupportCenter() {

	}

	/**
	 * This method executes the work flow of logging into Launch pad page. The
	 * work flow opens a new browser to orms sign in page and ends at Launch pad
	 * page
	 * 
	 * @param login
	 *            - login information.
	 */
	public void loginLaunchPad(LoginInfo login) {
		OrmsSigninPage ormsSigninPg = OrmsSigninPage.getInstance();
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();

		logger.info("Login Launch Pad Page.");

		browser.closeAllBrowsers();
		browser.open(login.url);
		ormsSigninPg.waitLoading();
		ormsSigninPg.login(login.userName, login.password, login.envType);
		launchPadPg.waitLoading();
	}

	/**
	 * The method execute the method of logging support center from orms sign in
	 * page to support center home page
	 * 
	 * @param login
	 */
	public void loginSupportCenter(LoginInfo login) {
		logger.info("Login Support Center.");

		this.loginLaunchPad(login);
		this.loginSupportCenterFromLaunchPad(login);

	}

	/**
	 * This method used to log in support center from launch pad page
	 * 
	 * @param login
	 */
	public void loginSupportCenterFromLaunchPad(LoginInfo login) {
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
		
		logger.info("Login Support Center from launch pad page.");

		launchPadPg.selectContract(login.contract);
		launchPadPg.clickSupportCenter();
		
		SupportCenterSearchPage searchPg = SupportCenterSearchPage
				.getInstance();
		searchPg.waitLoading();
	}

	/**
	 * Login call manager from launch pad page
	 * 
	 * @param login
	 */
	public void loginCallManager(LoginInfo login) {
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();

		logger.info("Login Call Manager.");
		launchPadPg.selectContract(login.contract);
		launchPadPg.clickCallManager();

		cmHmPg.waitLoading();
		// cmHmPg.selectContract(login.contract);
		// cmHmPg.waitExists();
	}

	/**
	 * This method used to go to launch pad page from call manager
	 */
	public void gotoLaunchPadFromCM() {
		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
		CallMgrEndCallPage cmEndCallPg = CallMgrEndCallPage.getInstance();

		logger.info("go to launch page from Call Manager.");
		cmTopMenuPg.clickCancelCall();
		Object page = browser.waitExists(cmEndCallPg, cmHmPg);
		if (page == cmEndCallPg) {
			cmEndCallPg.selectOtherReason();// Make a change
			cmEndCallPg.setOtherReasonDescription("QA Automation Test");
			cmEndCallPg.clickOkToCancelCall();
			cmHmPg.waitLoading();
		}

		cmHmPg.clickLaunchPad();
		launchPadPg.waitLoading();
	}

	/**
	 * This method used to go to increase site inventory page
	 */
	public void gotoIncreaseSitePage() {
		SupportCenterTopMeunPage meunPg = SupportCenterTopMeunPage
				.getInstance();
		SupportCenterNewTicketPage ticketPg = SupportCenterNewTicketPage
				.getInstance();
		SupportCenterIncreaseSitePage increasePg = SupportCenterIncreaseSitePage
				.getInstance();

		logger.info("Go to increase site inventory page.");

		meunPg.clickNewTicket();
		ticketPg.waitLoading();
		ticketPg.selectRequestType("Increase Site Inventory");
		increasePg.waitLoading();
	}

	/**
	 * This method used to add a new increase site inventory request
	 * 
	 * @param request
	 */
	public void addIncreaseSiteInventoryRequest(RequestInfo request) {
		SupportCenterIncreaseSitePage increasePg = SupportCenterIncreaseSitePage
				.getInstance();
		SupportCenterSearchLocationsPage locationPg = SupportCenterSearchLocationsPage
				.getInstance();
		SupportCenterAlertDialog dialog = SupportCenterAlertDialog
				.getInstance();

		logger.info("Add increase site inventory request...");

		if (!"".equals(request.attachment)) {
			increasePg.selectAttachments(request.attachment);
		}
		if (!"".equals(request.contactName)) {
			increasePg.setContactName(request.contactName);
		}
		if (!"".equals(request.contactEmail)) {
			increasePg.setContactEmail(request.contactEmail);
		}
		increasePg.setContactPhone(request.phone);

		increasePg.clickAddOrRemove();
		locationPg
				.selectLocationByPrefix(request.locationPre, request.location);
		increasePg.waitLoading();

		increasePg.setStartDate(request.startDate);
		increasePg.setEndDate(request.endDate);
		increasePg.setAffectedSite(request.site);
		if (request.changeQuantityTo) {
			increasePg.clickChangeQuantityTo();
		}
		if (request.changeQuantityBy) {
			increasePg.clickChangeQuantityBy();
		}
		increasePg.setQuantity(request.quantity);
		increasePg.setReasonForChange(request.reason);
		increasePg.clickSubmit();
		dialog.waitLoading();
	}

	/**
	 * add a request with Support Case type TODO:1. confirm the dialog 2.request
	 * detail page wait exist
	 */
	public String addSupportCaseRequest(RequestInfo request) {
		SupportCenterSupportCasePage supportCasePg = SupportCenterSupportCasePage
				.getInstance();
		SupportCenterSearchLocationsPage searchLocPg = SupportCenterSearchLocationsPage
				.getInstance();
		SupportCenterAlertDialog alertPg = SupportCenterAlertDialog.getInstance();
		SupportCenterNewTicketPage scNewTicketPg = SupportCenterNewTicketPage.getInstance();
		SupportCenterTopMeunPage scTopMenuPg = SupportCenterTopMeunPage.getInstance();
		
		logger.info("add a request for support case:");
		scTopMenuPg.clickNewTicket();
		scNewTicketPg.waitLoading();
		scNewTicketPg.selectRequestType(request.type);
		supportCasePg.waitLoading();
		supportCasePg.selectProduct(request.product);
		supportCasePg.setSummary(request.summary);
		supportCasePg.setDescription(request.description);
		supportCasePg.clickAddOrRemove();
		searchLocPg.waitLoading();
		searchLocPg.setLocation(request.location);
//		searchLocPg.waitExists();
		searchLocPg.clickAdd();
		searchLocPg.waitLoading();
		ajax.waitLoading();
		searchLocPg.clickOkay();
		supportCasePg.waitLoading();
		supportCasePg.clickAddOrRemove();
		searchLocPg.waitLoading();
		searchLocPg.clickOkay();
		supportCasePg.waitLoading();
		supportCasePg.clickSubmit();
		alertPg.waitLoading();
		String reqNum = alertPg.getText().split(" ")[1].split(" ")[0].trim();
		alertPg.clickOK();
		return reqNum;
	}

	/**
	 * add a request with Decrease Site Inventory TODO:1. confirm the dialog
	 * 2.request detail page wait exist
	 */
	public void addDecreaseSiteInventoryRequest(RequestInfo request) {
		SupportCenterTopMeunPage scTopMenuPg = SupportCenterTopMeunPage
				.getInstance();
		SupportCenterNewTicketPage scNewTicketPg = SupportCenterNewTicketPage
				.getInstance();
		SupportCenterSearchLocationsPage searchLocPg = SupportCenterSearchLocationsPage
				.getInstance();
		SupportCenterDecreaseSitePage decreasePg = SupportCenterDecreaseSitePage
				.getInstance();
		SupportCenterAlertDialog alertPg = SupportCenterAlertDialog
				.getInstance();
		SupportCenterSearchPage searchPg = SupportCenterSearchPage.getInstance();

		logger.info("add a request for decrease site inventory:");
		scTopMenuPg.clickNewTicket();
		scNewTicketPg.waitLoading();
		scNewTicketPg.selectRequestType(request.type);
		decreasePg.waitLoading();
		decreasePg.setContactPhone(request.phone);
		decreasePg.clickAddOrRemove();
		searchLocPg.waitLoading();
		searchLocPg.setLocation(request.location);
		searchLocPg.waitLoading();
		searchLocPg.clickAdd();
		searchLocPg.waitLoading();
		searchLocPg.clickOkay();
		decreasePg.waitLoading();
		decreasePg.clickAddOrRemove();
		searchLocPg.waitLoading();
		searchLocPg.clickOkay();
		
		decreasePg.setStartDate(request.startDate);
		decreasePg.setEndDate(request.endDate);
		decreasePg.setAffectedSite(request.affectedSite);
		decreasePg.setQuantity(request.quantity);
		decreasePg.setReasonForChange(request.reason);
		decreasePg.clickSubmit();
		if (alertPg.exists()) {
			alertPg.clickOK();
		}
		searchPg.supportCenterSearchPageWaitExist();
	}

	/**
	 * goto launch pad
	 */
	public void gotoLaunchPad() {
		SupportCenterTopMeunPage topPg = SupportCenterTopMeunPage.getInstance();
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();

		topPg.gotoLaunchPad();
		launchPadPg.waitLoading();
	}
	
	/**
	 * goto search page from launch pad page
	 */
	public void gotoSearchPageFromLaunchPad(){
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
		SupportCenterSearchPage scSearchPg = SupportCenterSearchPage.getInstance();
		
		launchPadPg.clickSupportCenter();
		scSearchPg.waitLoading();
	}
	
	/**
	 * This method used to search site in the call manager
	 * 
	 * @param site
	 */
	public void searchSiteIntheCM(SiteInfoData site) {
		CallMgrHomePage cmHmPg = CallMgrHomePage.getInstance();
		OrmsSiteSearchPage cmSiteSchPg = OrmsSiteSearchPage.getInstance();
		CallMgrTopMenuPage cmTopMenuPg = CallMgrTopMenuPage.getInstance();
		OrmsSiteListPage cmSiteListPg = OrmsSiteListPage.getInstance();

		cmHmPg.clickCampingCall();
		cmTopMenuPg.waitLoading();

		if (!cmSiteSchPg.exists()) {
			cmTopMenuPg.clickSites();
			cmSiteSchPg.waitLoading();
		}

		cmSiteSchPg.searchSite(site);
		cmSiteListPg.waitLoading();
	}

	/** Logout support center */
	public void logoutSupportCenter() {
		OrmsLaunchPadPage launchPadPg = OrmsLaunchPadPage.getInstance();
		OrmsSigninPage ormsSgInPg = OrmsSigninPage.getInstance();

		launchPadPg.clickSignOut();
		ormsSgInPg.waitLoading();
	}

}
