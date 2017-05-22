package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * @author phoebeChen
 *
 * If make a slip reservation for customer who has a alert, this page shows when we want to go to the reservation detail page by clicking the orderNum
 */
public class OrmsReservationCustAlertPage extends OrmsPage {

	static private OrmsReservationCustAlertPage _instance = null;

	protected OrmsReservationCustAlertPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationCustAlertPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationCustAlertPage();
		}

		return _instance;
	}
	
	public boolean exists() {
//		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".className","message msgsuccess_alertbar",".id",new RegularExpression(".+alert(s)?$", false));
//		Property[] p2=Property.toPropertyArray(".class","Html.TD",".text","Reservation Information");
//		boolean exist= browser.checkHtmlObjectDisplayed(p1) && browser.checkHtmlObjectDisplayed(p2);
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".className","mainWrapper",".id",new RegularExpression("(.*alert(s)?|common-event)$",false));
		Property[] p2=Property.toPropertyArray(".class","Html.DIV",".className","message msgsuccess_alertbar",".id",new RegularExpression(".+alert(s)?$", false));
		boolean exist =browser.checkHtmlObjectDisplayed(Property.atList(p1,p2)) || browser.checkHtmlObjectDisplayed(Property.atList(p2));
		
		return exist;
	  }
	
	
	public void clickOK(){
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("^OK", false), true);
	}
	
	public String getSlipResAlertInfo(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Slip Reservation Alerts.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any Slip Reservation Alerts table object.");
		}
		String text = objs[objs.length-1].text();
		Browser.unregister(objs);
		return text;
	}
	
	public String getCustAlertInfo(){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Customer Alerts.*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any Slip Reservation Alerts table object.");
		}
		String text = objs[objs.length-1].text();
		Browser.unregister(objs);
		return text;
	}
}
