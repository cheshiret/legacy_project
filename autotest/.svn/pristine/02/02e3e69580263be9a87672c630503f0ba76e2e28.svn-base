/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author Billy Zhang
 *
 */
public class OrmsReservationBillingPage extends OrmsPage {

	static private OrmsReservationBillingPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationBillingPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationBillingPage();
		}
		return _instance;
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^Event Actions.*", false));
		return browser.checkHtmlObjectExists(".id", "I_EventReservationOrderView.orderNumber");
	}
	
	/**
	 * Click the "Make Payment" button
	 */
	public void clickMakePayment(){
		browser.clickGuiObject(".class", "Html.A",".text","Make Payment", true);
	}
	
	/**
	 * check whether the reservations in the Bill Summary to date TAB has outstanding balance or not.
	 * @return true: the bill summary to date list has outstanding balance reservation; false: the bill summary to date list
	 * don't have any outstanding balance reservation.
	 */
	public boolean isMakePaymentAvailable(){
		boolean hasOutStandingBalanceRes = false;
		IHtmlObject[] objs = browser.getCheckBox(".id", "order");
		
		if (null == objs || objs.length<1){
			throw new ObjectNotFoundException("there is no reservation records in Bill Summary To Date List");
		}
		for (int i =0; i < objs.length; i++){
			if (((ICheckBox)objs[i]).isEnabled())  {
				hasOutStandingBalanceRes = true;
			}
		}
		Browser.unregister(objs);
		return hasOutStandingBalanceRes;
	}
	
	/**
	 * select check box for all reservations that have outstanding balance.
	 */
	public void selectAllCheckBox(){
		
		IHtmlObject[] objs = browser.getCheckBox(".id", "order");
		
		if (null == objs || objs.length<1){
			throw new ObjectNotFoundException("there is no reservation records in Bill Summary To Date List");
		}
		
		browser.selectCheckBox(".value", new RegularExpression("all",false));
		Browser.unregister(objs);
		}		
}
