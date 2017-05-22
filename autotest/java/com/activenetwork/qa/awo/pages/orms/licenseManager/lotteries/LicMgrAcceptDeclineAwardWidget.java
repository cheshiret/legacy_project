package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
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
 * @Date  Mar 20, 2014
 */
public class LicMgrAcceptDeclineAwardWidget extends DialogWidget {

	private static LicMgrAcceptDeclineAwardWidget _instance = null;
	
	private LicMgrAcceptDeclineAwardWidget() {
		super("Accept Award(/Decline Award)?");
	}
	
	public static LicMgrAcceptDeclineAwardWidget getInstance() {
		if(_instance == null) _instance = new LicMgrAcceptDeclineAwardWidget();
		
		return _instance;
	}
	
	public void clickAcceptAward() {
		clickButtonByText("Accept Award");
	}
	
	public void clickDeclineAward() {
		clickButtonByText("Decline Award");
	}
	
	private Property[] cancel() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(cancel(), true, 0, getWidget()[0]);
	}
	
	public String getApplicationOrder(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("Application Order:.*",false));
		if(objs.length < 1){
			throw new ErrorOnPageException("Can not get the object for application order");
		}
		String order = objs[0].text().replace("Application Order:", "").trim();
		return order;
	}
}
