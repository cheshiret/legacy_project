package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
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
 * @Date  Apr 26, 2012
 */
public class VnuMgrRequestConfirmationLetterPage extends VnuMgrTopMenuPage {
	private static VnuMgrRequestConfirmationLetterPage _instance = null;
	
	private VnuMgrRequestConfirmationLetterPage() {}
	
	public static VnuMgrRequestConfirmationLetterPage getInstance() {
		if(null == _instance) {
			_instance = new VnuMgrRequestConfirmationLetterPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text", "Confirmation Letter Distribution method");
	}
	
	/**
	 * Select the specific way to deliver confirmation letter to customer
	 * @param method
	 */
	public void selectConfLetterDistributionMethod(String method) {
		if(method.contains("\\/")) {
			method = method.replace("\\/", "\\\\/");
		}
		IHtmlObject tds[] = browser.getHtmlObject(".class", "Html.TD", ".text", new RegularExpression(method + "$", false));
		if(tds.length < 1) {
			throw new ItemNotFoundException("Can't find confirmation letter distribution method - " + method);
		}
		IHtmlObject radios[] = browser.getRadioButton(new Property[]{new Property(".id", "method")}, tds[tds.length - 1]);
		if(radios.length < 1) {
			throw new ItemNotFoundException("Can't find radio button object by - " + method);
		}
		IRadioButton radioBtn = (IRadioButton)radios[0];
		if(!radioBtn.isSelected()) {
			radioBtn.select();
		}
		
		Browser.unregister(tds);
		Browser.unregister(radios);
	}
	
	public void selectEmailMethod() {
		selectConfLetterDistributionMethod("Email");
	}
	
	public void selectFilePrintMethod() {
		selectConfLetterDistributionMethod("File/Print");
	}
	
	public void selectAddToBatchMethod() {
		selectConfLetterDistributionMethod("Add to Batch");
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Ok");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
}
