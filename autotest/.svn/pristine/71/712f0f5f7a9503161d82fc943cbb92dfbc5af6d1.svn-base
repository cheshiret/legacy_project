/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.systemManager;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * @Defects:
 * 
 * @author asun
 * @Date  Aug 31, 2011
 */
public abstract class SysMgrCommonTopMenuPage extends SystemManagerPage {

	/**Click on Home link.*/
	public void clickHome() {
		browser.clickGuiObject(".class", "Html.TextNode", ".text", "Home");
	}

	/**Click on Scheduler link.*/
	public void clickScheduler() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Scheduler");
	}

	/**Click on Active Users link.*/
	public void clickActiveUsers() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Active Users");
	}

	/**Click on System Monitor link.*/
	public void clickSystemMonitor() {
		browser.clickGuiObject(".class", "Html.A", ".text", "System Monitor");
	}

	/**Click on Servers link.*/
	public void clickServers() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Servers");
	}

	/**Click on Cache Refresh link.*/
	public void clickCacheRefresh() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cache Refresh");
	}

	public void clickTab(String option){
		browser.clickGuiObject(".class","Html.TD",".text",option);
	}
	
	public void clickUtilities(){
		this.clickTab("Utilities");
	}
	/**Click on Envs link.*/
	public void clickEnvs() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Envs");
	}
	
	public void clickEnvsUnderUtilitesTab(){
		this.clickUtilities();
		clickEnvs();
	}

	/** Click on Start Document Fulfillment tab under Utilities */
	public void clickStartDocFulfillmentTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Start Document Fulfillment");
	}
	
	/**Click on DB link.*/
	public void clickDB() {
		browser.clickGuiObject(".class", "Html.A", ".text", "DB");
	}

	/**Click on Fin Util link.*/
	public void clickFinUtil() {
		browser.clickGuiObject(".class", "Html.TD", ".text", "Fin Util");
	}

	/**Click on Payment Util link.*/
	public void clickEPaymentUtil() {
		browser.clickGuiObject(".class", "Html.A", ".text", "EPayment Util");
	}

	/**Click on Log4j link.*/
	public void clickLog4j() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Log4j");
	}

	/**Click on Occam link.*/
	public void clickOccam() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Occam");
	}

	/**Click on MarketingSpot Refresh link.*/
	public void clickMarketingSpotRefresh() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"MarketingSpot Refresh");
	}

	/**Click on JReport Server Console link.*/
	public void clickJReportServerConsole() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"JReport Server Console");
	}

	/**Click on JReport Admin Server Console link.*/
	public void clickJReportAdminServerConsole() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"JReport Admin Server Console");
	}

	/**Click on Report Execution link.*/
	public void clickReportExecution() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Report Execution");
	}

	/**Sign Out System Manager.*/
	public void clickSignOut() {
		browser.clickGuiObject(".class", "Html.TABLE", ".text", "Sign out");
	}
	
}
