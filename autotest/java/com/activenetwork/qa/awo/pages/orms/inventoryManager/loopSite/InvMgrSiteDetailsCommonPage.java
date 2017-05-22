package com.activenetwork.qa.awo.pages.orms.inventoryManager.loopSite;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InvMgrTopMenuPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Aug 28, 2013
 */
abstract class InvMgrSiteDetailsCommonPage extends InvMgrTopMenuPage {
	
	/**
	 * Get siteID in the site detail page
	 * 
	 * @return
	 */
	public String getSiteID() {
		RegularExpression reg = new RegularExpression("Site\\W+Site ID.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		String siteId = ((IHtmlTable) objs[0]).getCellValue(0, 2).split(
				"Site ID")[1].trim();
		Browser.unregister(objs);
		return siteId;
	}
	
	public String getSiteCode() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "SiteView.siteCode");
		String siteCode = objs[0].getProperty(".value").toString();

		Browser.unregister(objs);
		return siteCode;
	}

	public String getSiteName() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "SiteView.name");
		String siteName = objs[0].getProperty(".value").toString();

		Browser.unregister(objs);
		return siteName;
	}
	
	public String getParentLoop() {
		return browser.getDropdownListValue(".id", "SiteView.parentID", 0);
	}

	public String getSiteType() {
		return browser.getDropdownListValue(".id", "SiteView.relationType", 0);
	}
}
