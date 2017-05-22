package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
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
 * @Date  Mar 12, 2012
 */
public class LicMgrCustomerInputDateOfBirthWidget extends DialogWidget {
	
	private static LicMgrCustomerInputDateOfBirthWidget _instance = null;
	
	private LicMgrCustomerInputDateOfBirthWidget() {
		widgetProperty=new Property[3];
		widgetProperty[0]=new Property(".class","Html.DIV");
		widgetProperty[1]=new Property(".className","ui-dialog ui-widget ui-widget-content ui-corner-all ui-draggable ui-resizable");
		widgetProperty[2]=new Property(".aria-labelledby", "ui-dialog-title-_customer_dob_dialog");
	}
	
	public static LicMgrCustomerInputDateOfBirthWidget getInstance() {
		if(null == _instance) {
			_instance = new LicMgrCustomerInputDateOfBirthWidget();
		}
		
		return _instance;
	}
	
	public String getMessage() {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Date of Birth is required for", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find message object.");
		}

		String text = objs[0].text();
		Browser.unregister(objs);
		
		return text;
	}
	
	/**
	 * Set date of birth value for VERIFIED identifier which is systemre required
	 * @param dob
	 */
	public void setDateOfBirth(String dob) {
		browser.setTextField(".id", new RegularExpression("CustomerDOBDialog-\\d+\\.dateOfBirth_ForDisplay", false), dob, true);
	}
}
