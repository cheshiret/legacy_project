/*
 * Created on Nov 22, 2009
 */
package com.activenetwork.qa.awo.keywords.orms;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LoginInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsHomePage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsLoginPage;
import com.activenetwork.qa.awo.pages.orms.common.OrmsSigninPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrCacheRefreshPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrDocumentFulfillmentPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrEFTInvoicingPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrEFTReturnPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrEFTTransmissionPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrEnvsPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrFinUtilPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrHomePage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrProcessDailyEFTPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrServiceDaemonsPage;
import com.activenetwork.qa.awo.pages.orms.systemManager.SysMgrTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.browser.IBrowser;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author vzhao
 */
public class SystemManager extends Orms{
	private static SystemManager _instance = null;

	public static SystemManager getInstance() {
	  if (null == _instance)
		_instance = new SystemManager();

	  return _instance;
	}
	
	/**
	 * The method execute the process that open the url and retrieve the build number.
	 * @param url - url
	 */
	public void invokeURL(String url) {
	  OrmsHomePage ormsHmPg = OrmsHomePage.getInstance();
	  OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
	  IBrowser browser = Browser.getInstance();

	  browser.closeAllBrowsers();
	  browser.open(url);
	  omSigninPg.waitLoading();
	
	  String bn = ormsHmPg.getBuildNum();
	  TestProperty.putProperty("orms.build", bn);
	  logger.info("Orms Build# is " + bn);
	}
	
	/**
	 * Log into System Manager, ends at Home page.
	 * @param login - log in data
	 * @param sysMgrUrl - url
	 */
	public void loginSystemManager(LoginInfo login){
	  OrmsSigninPage omSigninPg = OrmsSigninPage.getInstance();
	  OrmsLoginPage omLoginPg = OrmsLoginPage.getInstance();
	  SysMgrHomePage homePage=SysMgrHomePage.getInstance();
	  
	  logger.info("Login in System Manager with url=" + login.url);
	  invokeURL(login.url);
	  omSigninPg.login(login); // fill in user name and pass word
	  
	  omLoginPg.waitLoading();
	  omLoginPg.logIn(login); // select contract, station, etc.
	  homePage.waitLoading();
	}
	
	/**Sign out System Manager, method starts at header bar, ends at ORMS index of links page.*/
	public void logoutSystemManager(){
//	  SysMgrHomePage sysHome=SysMgrHomePage.getInstance();
//	  OrmsHomePage omHmPg = OrmsHomePage.getInstance();
	  
	  logger.info("Logout System Manager.");
      browser.close();
//	  sysHome.clickSignOut();
//	  omHmPg.waitExists();
	  
	}
	
	/**
	 * Refresh the system cache by executing certain queries.
	 * @param table - table in DB
	 * @param command - query command
	 */
	public void refreshSystemCache(String table, String command){
	  SysMgrHomePage sysHome=SysMgrHomePage.getInstance();
	  SysMgrCacheRefreshPage cacheReresh=SysMgrCacheRefreshPage.getInstance();
	  
	  logger.info("Refresh system cache.");
	  sysHome.clickCacheRefresh();
	  cacheReresh.waitLoading();
	  
	  cacheReresh.selectTable(table);
	  cacheReresh.setQueryCommand(command);
	  cacheReresh.clickExecuteRefresh();
	  cacheReresh.waitLoading();
	}
	
	public void gotoFinUtilPage(){
		SysMgrTopMenuPage topMenu=SysMgrTopMenuPage.getInstance();
		SysMgrFinUtilPage utilPg=SysMgrFinUtilPage.getInstance();
		
		logger.info("go to fin util page");
		topMenu.clickTab("Finance");
		topMenu.clickFinUtil();
		utilPg.waitLoading();
	}
	
	public void runDailyEFT(){
		SysMgrFinUtilPage utilPg=SysMgrFinUtilPage.getInstance();
		SysMgrProcessDailyEFTPage daily=SysMgrProcessDailyEFTPage.getInstance();
		
		utilPg.clickRunDailyEFT();
		daily.waitLoading();
		
		daily.selectUseCheckBox();
		daily.clickProcess();
		daily.waitLoading();
		
		if(!daily.getNote().equalsIgnoreCase("Daily EFT Process for provided chart of account has finished successfully")){
			throw new ErrorOnDataException("Process Daily EFT is not success!");
		}
	}
	
	public void runEFTInvoicing(){
		SysMgrFinUtilPage utilPg=SysMgrFinUtilPage.getInstance();
		SysMgrEFTInvoicingPage invoice=SysMgrEFTInvoicingPage.getInstance();
		
		utilPg.clickRunEFTInvoice();
		invoice.waitLoading();
		
		invoice.selectUseCheckBox();
		invoice.clickRun();
		invoice.waitLoading();
		
		if(!invoice.getNote().equalsIgnoreCase("EFT Invoicing Process for provided EFT Schedule has finished successfully")){
			throw new ErrorOnDataException("Run EFT invoicing is not success!");
		}
	}
	
	/**
	 * Run EFT Transmission.
	 */
	public void runEFTTransmission(){
		logger.info("Run EFT Transmission.");
		SysMgrFinUtilPage utilPg = SysMgrFinUtilPage.getInstance();
		SysMgrEFTTransmissionPage transmissionPg = SysMgrEFTTransmissionPage.getInstance();
		
		utilPg.clickRunEFTTransmission();
		transmissionPg.waitLoading();
		
		transmissionPg.clickProcess();
		transmissionPg.waitLoading();
		
		if(!transmissionPg.getMsg().contains("EFT Transmission for provided chart of account has finished successfully")){
			throw new ErrorOnDataException("Run EFT Transmmision is not successful!");
		}
	}
	/**
	 * Go to service daemons page from system manager top menu page
	 */
	public void gotoServiceDaemonsPage(){
	  SysMgrTopMenuPage topMenu=SysMgrTopMenuPage.getInstance();
	  SysMgrEnvsPage envsPage=SysMgrEnvsPage.getInstance();
	  SysMgrServiceDaemonsPage daemonPage=SysMgrServiceDaemonsPage.getInstance();
	  
	  logger.info("Go to service daemons page from system manager top menu page");
	  topMenu.clickEnvsUnderUtilitesTab();
	  envsPage.waitLoading();
	  envsPage.clickServiceDaemonsTab();
	  daemonPage.waitLoading();
	}
	
	/**
	 * Get daemon running status
	 * @param daemonName
	 * @return
	 */
	public String getDaemonRunningStatus(String daemonName) {
		SysMgrServiceDaemonsPage sysMgrServicePage = SysMgrServiceDaemonsPage.getInstance();
		this.gotoServiceDaemonsPage();
		String daemonStatus = sysMgrServicePage.getDaemonStatus(daemonName);
		
		return daemonStatus;
	}
	
	/**
	 * Get daemon running time
	 * @param daemonName
	 * @return
	 */
	public String getDaemonRunningTime(String daemonName) {
		SysMgrServiceDaemonsPage sysMgrServicePage = SysMgrServiceDaemonsPage.getInstance();
		
		this.gotoServiceDaemonsPage();
		String daemonRunTime = sysMgrServicePage.getDaemonRunAt(daemonName).split("at")[1].trim();
		
		return daemonRunTime;
	}
	
	public void gotoDocFulfillmentPage(){
		SysMgrTopMenuPage topMenu = SysMgrTopMenuPage.getInstance();
		SysMgrDocumentFulfillmentPage docFulfillPg = SysMgrDocumentFulfillmentPage.getInstance();
		
		logger.info("go to Document Fulfillment page...");
		topMenu.clickUtilities();
		topMenu.clickStartDocFulfillmentTab();
		docFulfillPg.waitLoading();
	}
	
	/** Start Document Fulfillment in System Manager */
	public void startDocumentFulfillment() {
		SysMgrDocumentFulfillmentPage docFulfillPg = SysMgrDocumentFulfillmentPage.getInstance();
		
		logger.info("Start Document Fulfillment in system manager.");
		this.gotoDocFulfillmentPage();
		docFulfillPg.clickStart();
	}
	
	/**
	 * Run EFT Invoicing in system manager.
	 */
	public void runEFTInvoicing(LoginInfo loginSm){
		logger.info("Run EFT Invoicing in system manager.");
		// After 15 mins, run EFT Invoice
	    this.loginSystemManager(loginSm);
	    this.gotoFinUtilPage();
	    this.runDailyEFT();
	    this.gotoFinUtilPage();
	    this.runEFTInvoicing();
	    this.logoutSystemManager();
	}

	/**
	 * Run EFT Transmission in system manager.
	 */
	public void runEFTTransmission(LoginInfo loginSm){
		logger.info("Run EFT Transmission in system manager.");
	    this.loginSystemManager(loginSm);
	    this.gotoFinUtilPage();
	    this.runDailyEFT();
	    this.gotoFinUtilPage();
	    this.runEFTInvoicing();
	    this.gotoFinUtilPage();
	    this.runEFTTransmission();
	    this.logoutSystemManager();
	}
	
	/**
	 * Run EFT Return job.
	 */
	public void runEFTReturnJob(){
		logger.info("Run EFT Return Job.");
		SysMgrFinUtilPage utilPg = SysMgrFinUtilPage.getInstance();
		SysMgrEFTReturnPage returnPg = SysMgrEFTReturnPage.getInstance();
		
		utilPg.clickRunEFTReturn();
		returnPg.waitLoading();
		
		returnPg.clickOk();
		returnPg.waitLoading();
		
		if(!returnPg.getNote().contains("successfully")){
			throw new ErrorOnDataException("Run EFT Return job is not success!");
		}
	}
}
