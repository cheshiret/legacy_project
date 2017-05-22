/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  May 8, 2014
 */
public class OrmsRequestConfirmationLetterPage extends OrmsPage{
	private static OrmsRequestConfirmationLetterPage _instance = null;
	public OrmsRequestConfirmationLetterPage(){}
	
	public static OrmsRequestConfirmationLetterPage getInstance(){
		if(null == _instance){
			_instance = new OrmsRequestConfirmationLetterPage();
		}
		return _instance;
	}
	Property[] confirmationLetterDistributionMethodPrp(){
		return Property.addToPropertyArray(td(), ".text", "Confirmation Letter Distribution method");
	}
	
	Property[] okButtonPrp(){
		return  Property.addToPropertyArray(a(), ".text", "Ok");
	}
	
	Property[] cancelButtonPrp(){
		return  Property.addToPropertyArray(a(), ".text", "Cancel");
	}
	
	Property[] methodTDPrp(String method){
		return Property.addToPropertyArray(td(), ".text", new RegularExpression(method + "$", false));
	}
	
	Property[] methodRadioButtonPrp(){
		return Property.toPropertyArray(".id", "method");
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(this.confirmationLetterDistributionMethodPrp());
	}
	
	/**
	 * Select the specific way to deliver confirmation letter to customer
	 * @param method
	 */
	public void selectConfLetterDistributionMethod(String method) {
		if(method.contains("\\/")) {
			method = method.replace("\\/", "\\\\/");
		}
		IHtmlObject tds[] = browser.getHtmlObject(this.methodTDPrp(method));
		if(tds.length < 1) {
			throw new ItemNotFoundException("Can't find confirmation letter distribution method - " + method);
		}
		IHtmlObject radios[] = browser.getRadioButton(this.methodRadioButtonPrp(), tds[tds.length - 1]);
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
		browser.clickGuiObject(this.okButtonPrp());
	}
	
	public void clickCancel() {
		browser.clickGuiObject(this.cancelButtonPrp());
	}
	
	
}
