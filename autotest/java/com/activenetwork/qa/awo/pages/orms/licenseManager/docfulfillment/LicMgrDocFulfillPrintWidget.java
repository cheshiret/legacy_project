package com.activenetwork.qa.awo.pages.orms.licenseManager.docfulfillment;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description: Document Fulfillment print widget, without title. Admin -> Document Fulfillment -> Print Documents
 * 
 * @author Lesley Wang
 * @Date  Sep 11, 2013
 */
public class LicMgrDocFulfillPrintWidget extends DialogWidget {
	private static LicMgrDocFulfillPrintWidget instance = null;

	// The widget doesn't have title
	private LicMgrDocFulfillPrintWidget() {
		super();
	}
	
	public static LicMgrDocFulfillPrintWidget getInstance() {
		if (instance == null) {
			instance = new LicMgrDocFulfillPrintWidget();
		}
		return instance;
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] printDiv() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "DocumentPrinting");
	}
	
	protected Property[] printerList() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("PrinterType-\\d+\\.printerName", false));
	}
	
	protected Property[] printBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Print");
	}
	/** Page Object Property Definition END */
	
	@Override
	public boolean exists() {
		return super.exists() && browser.checkHtmlObjectExists(this.printBtn());
	}
	
	public void selectPrinter(String printer) {
		browser.selectDropdownList(this.printerList(), printer);
	}
	
	public void selectDummyPrinter() {
		selectPrinter("Dummy Printer");
	}
	
	public void clickPrint() {
		browser.clickGuiObject(this.printBtn());
	}
}
