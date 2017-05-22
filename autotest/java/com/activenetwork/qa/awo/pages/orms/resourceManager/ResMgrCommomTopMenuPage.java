package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public abstract class ResMgrCommomTopMenuPage extends ResourceManagerPage{
	/** Click Home from top menu bar. */
	public void clickHome() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Home");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuleft\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}

	/** Click Bulletins from top menu bar. */
	public void clickBulletins() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Bulletins");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuright\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}

	/** Click Reports from top menu bar. */
	public void clickReports() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Reports");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuright\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}

	/** Click Report Recipients from top menu bar. */
	public void clickReportRecipients() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Report Recipients");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuright\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}

	/** Click Scheduler from top menu bar. */
	public void clickScheduler() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Scheduler");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuright\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}

	/** Click Sign-Out from top menu bar. */
	public void clickSignOut() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Sign-Out");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuright\\.id\\.\\d", false));
		browser.clickGuiObject(p);
	}
}
