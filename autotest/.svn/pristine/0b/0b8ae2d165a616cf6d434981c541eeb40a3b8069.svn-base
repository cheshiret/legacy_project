package com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: Document Fulfillment Retry Print widget, without title, shown after click Print on Print widget
 * 
 * @author Lesley Wang
 * @Date  Sep 11, 2013
 */
public class LicMgrDocFulfillRetryPrintWidget extends DialogWidget {
	private static LicMgrDocFulfillRetryPrintWidget instance = null;

	// The widget doesn't have title
	private LicMgrDocFulfillRetryPrintWidget() {
		super();
	}
	
	public static LicMgrDocFulfillRetryPrintWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrDocFulfillRetryPrintWidget();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] retryPrintBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Retry Printing Documents");
	}
	
	protected Property[] successBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Success");
	}
	
	protected Property[] failureBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Failure");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(this.retryPrintBtn());
	}
	
	public void clickSuccess() {
		browser.clickGuiObject(this.successBtn());
	}
	
	public void clickFailure() {
		browser.clickGuiObject(this.failureBtn());
	}
}
