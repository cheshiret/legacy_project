package com.activenetwork.qa.awo.pages.orms.licenseManager.vehicle;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Feb 26, 2013
 */
public class LicMgrTransactionGroupsForChildVehiclesWidget extends DialogWidget {
	
	private static LicMgrTransactionGroupsForChildVehiclesWidget _instance = null;
	
	protected LicMgrTransactionGroupsForChildVehiclesWidget() {
		//James: title "Transaction Groups for Child Vehicles|" doesn't make any sense with "|" at the end. 
		//"|" will cause this widget matches for any widget of empty title and keeps failing sanity test LM_VehicleBoat
		super(new RegularExpression(".*Transactions.*", false));//super("Transaction Groups for Child Vehicles");
	}
	
	public static LicMgrTransactionGroupsForChildVehiclesWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrTransactionGroupsForChildVehiclesWidget();
		}
		
		return _instance;
	}
	
	public void selectTransaction(String transaction) {
		browser.selectRadioButton(".id", getTransactionRadioButtonIDByLabel(transaction));
	}
	
	private String getTransactionRadioButtonIDByLabel(String label) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL", ".text", label);
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find any Transaction radio button object which label is: " + label);
		}
		
		String forValue = objs[0].getProperty("for").trim();
		Browser.unregister(objs);
		
		return forValue;
	}
}
