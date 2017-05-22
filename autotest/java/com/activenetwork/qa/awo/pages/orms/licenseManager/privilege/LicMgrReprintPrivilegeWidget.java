package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Apr 16, 2012
 */
public class LicMgrReprintPrivilegeWidget extends DialogWidget {

	private static LicMgrReprintPrivilegeWidget _instance = null;
	
	private LicMgrReprintPrivilegeWidget() {
		super("Reprint Privilege");
	}
	
	public static LicMgrReprintPrivilegeWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrReprintPrivilegeWidget();
		}
		return _instance;
	}
	
	/**
	 * select reprint reason
	 * @param reason
	 */
	public void selectReprintReason(String reason) {
		if(reason == null || reason.length() < 1) {
			browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderActionUIModel-\\d+\\.selectedReason", false), 1, true);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("PrivilegeOrderActionUIModel-\\d+\\.selectedReason", false), reason, true);
		}
	}
	
	public void setNotes(String notes) {
		browser.setTextField(".id", new RegularExpression("PrivilegeOrderActionUIModel-\\d+\\.notes", false), notes);
	}
	
	/**
	 * select printer
	 * @param printer
	 */
	public void selectPrinter(String printer) {
		browser.selectDropdownList(".id", new RegularExpression("PrinterType-\\d+\\.printerName", false), printer);
	}
	
	/**
	 * select check box to set a specific printer as default
	 */
	public void selectSetAsDefault() {
		browser.selectCheckBox(".id", new RegularExpression("PrinterType-\\d+\\.saveAsDefault", false));
	}
	
	public void clickPrint() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print");
	}
	
	public String getOrderNumber() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TABLE", ".id", "printingContainer");
		if(objs.length < 1) {
			throw new ErrorOnPageException("Can't find table object.");
		}
		String orderNum = ((IHtmlTable)objs[0]).getCellValue(0, 0);
		
		Browser.unregister(objs);
		return orderNum;
	}
	
	public void clickRetry() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Retry", true);
	}
	
	public void clickSuccess() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Success", true);
	}
	
	public void clickFailure() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Failure", true);
	}
	
	/**
	 * Check whether the Printer drop down list object exist or not
	 * @return
	 */
	public boolean checkPrinterDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("PrinterType-\\d+\\.printerName", false));
	}
	
	/**
	 * warning message - 'There is no Document to print.'
	 * @return
	 */
	public String getWarnMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "ReprintPrivilegeUI");
		String msg = objs[0].getProperty(".text").split("Close")[0].trim();
		
		Browser.unregister(objs);
		return msg;
	}
	
	public void clickClose() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Close", true);
	}
	
	public void processReprint() {
		processReprint("", "");
	}
	
	/**
	 * 
	 * @param reason
	 * @param note
	 */
	public void processReprint(String reason, String note) {
		logger.info("Process reprint.");
		
		selectReprintReason(reason);
		setNotes(note);
		clickOK();
		ajax.waitLoading();
		Browser.sleep(6);
		
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Success");
		if(objs.length > 0) {
			objs[0].click();
			ajax.waitLoading();
		} else {
			if(checkPrinterDropdownListExists()) {
				selectPrinter("Dummy Printer");
				clickPrint();
				ajax.waitLoading();
			}
			browser.waitExists(Property.toPropertyArray(".class", "Html.A", ".text", "Success"));
			ajax.waitLoading();
			clickSuccess();
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
}
