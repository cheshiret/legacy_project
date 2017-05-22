
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author cguo
 */
public class ResMgrTopMenuPage extends ResMgrCommomTopMenuPage {

	private static ResMgrTopMenuPage _instance = null;

	public static ResMgrTopMenuPage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrTopMenuPage();
		}

		return _instance;
	}

	protected ResMgrTopMenuPage() {
	}

	public boolean exists() {
		//use Home link as pageMark
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Home");
		p[2] = new Property(".id", new RegularExpression(
				"resmgr\\.menuleft\\.id\\.\\d", false));
		return browser.checkHtmlObjectExists(p);
	}

	
}
