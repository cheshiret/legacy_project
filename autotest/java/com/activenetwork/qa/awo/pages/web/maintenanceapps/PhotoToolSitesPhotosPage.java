package com.activenetwork.qa.awo.pages.web.maintenanceapps;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: It is for photo tool site photos page
 * The page is shown after click "site photos" tab
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Feb 25, 2013
 */
public class PhotoToolSitesPhotosPage extends WebMaintenanceAppUserPanel {
	private static PhotoToolSitesPhotosPage _instance = null;

	public static PhotoToolSitesPhotosPage getInstance() {
		if (null == _instance)
			_instance = new PhotoToolSitesPhotosPage();

		return _instance;
	}
	
	public boolean exists() {
		Property[] p = Property.toPropertyArray(".class", "Html.FORM", ".id", "uploadPhotosForm", 
				".text", new RegularExpression("\\s+Select a Site.*", false));
		return browser.checkHtmlObjectExists(p);
	}
}

