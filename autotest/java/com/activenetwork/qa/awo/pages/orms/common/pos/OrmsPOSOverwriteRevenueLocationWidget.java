/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description: The dialog will be shown when change the product available location on POS Product Setup Details
 * if the revenue location is set to "A specific location".
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Lesley Wang
 * @Date  Aug 23, 2012
 */
public class OrmsPOSOverwriteRevenueLocationWidget extends DialogWidget {
	private static OrmsPOSOverwriteRevenueLocationWidget _instance = null;
	
	private OrmsPOSOverwriteRevenueLocationWidget() {
		super("Confirm Overwrite of Revenue Location");
	}
	
	public static OrmsPOSOverwriteRevenueLocationWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsPOSOverwriteRevenueLocationWidget();
		}
		
		return _instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
