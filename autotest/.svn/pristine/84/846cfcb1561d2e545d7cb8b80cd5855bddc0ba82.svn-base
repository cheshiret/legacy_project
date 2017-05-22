/**
 * 
 */
package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author asun
 * @Date  2012-4-1
 */
public class RecgovReservationPolicyPage extends RecgovCommonPage {

	private static RecgovReservationPolicyPage _instance=null;
	
	private RecgovReservationPolicyPage(){}
	
	public static RecgovReservationPolicyPage getInstance(){
		if(_instance==null){
			_instance=new RecgovReservationPolicyPage();
		}
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", new RegularExpression("Camping and Day Use Reservation Policies.*",false));
	}
	
	//Click "Recreation.gov"
	public void clickRecGovLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Recreation.gov");
	}


}
