/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  Aug 12, 2011
 */
public class LicMgrChangingInventoryTypeConfirmWidget extends DialogWidget {
	private static LicMgrChangingInventoryTypeConfirmWidget instance = null;

	private LicMgrChangingInventoryTypeConfirmWidget() {
		super(new RegularExpression("The Inventory Type.*",false));
	}

	public static LicMgrChangingInventoryTypeConfirmWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrChangingInventoryTypeConfirmWidget();
		}
		return instance;
	}

	public String getMessage() {
		return browser.getObjectText(".class","Html.TABLE",".text",new RegularExpression("^The Inventory Type.*",false)).trim();
	}
}
