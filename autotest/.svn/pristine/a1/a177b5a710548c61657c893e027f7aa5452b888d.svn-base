/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.event;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description:
 * @Preconditions:
 * @LinkSetUp: 
 * @SPEC:
 * @Task#:
 * 
 * @author Vivian
 * @Date  Apr 23, 2014
 */
public class OrmsEventResSlipsSummaryPage extends OrmsPage {
	
	static private OrmsEventResSlipsSummaryPage _instance = null;
	
	protected OrmsEventResSlipsSummaryPage() {

	}
	
	static public OrmsEventResSlipsSummaryPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsEventResSlipsSummaryPage();
		}

		return _instance;
	}
	
	protected Property[] slipSummaryForEventDivPrp(){
		return Property.addToPropertyArray(div(), ".id", "MarinaReserveSlipsSummaryForEventPage");
	}
	
	protected Property[] okButtonPrp(){
		return Property.addToPropertyArray(a(),".text", "OK");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(this.slipSummaryForEventDivPrp());
	}
	
	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(this.okButtonPrp());
	}

}
