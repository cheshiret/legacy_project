package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jul 16, 2014
 */
public class LicMgrReverseAppOrderRefundWidget extends DialogWidget {
	private static LicMgrReverseAppOrderRefundWidget _instance = null;
	
	private LicMgrReverseAppOrderRefundWidget() {
		super("Refund");
	}
	
	public static LicMgrReverseAppOrderRefundWidget getInstance() {
		if(_instance == null) _instance = new LicMgrReverseAppOrderRefundWidget();
		return _instance;
	}
	
	private Property[] ok() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "OK");
	}
	
	private Property[] cancel() {
		return Property.concatPropertyArray(a(), ".id", "Anchor", ".text", "Cancel");
	}
	
	public void clickOK() {
		browser.clickGuiObject(ok());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel());
	}
}
