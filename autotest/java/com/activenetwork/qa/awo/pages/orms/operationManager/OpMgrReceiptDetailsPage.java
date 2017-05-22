/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.operationManager;

import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author win7_vm
 * @Date  Nov 1, 2012
 */
public class OpMgrReceiptDetailsPage extends OperationsManagerPage{
	private static OpMgrReceiptDetailsPage _instance = null;

	protected OpMgrReceiptDetailsPage() {

	}

	public static OpMgrReceiptDetailsPage getInstance() {
		if (null == _instance) {
			_instance = new OpMgrReceiptDetailsPage();
		}

		return _instance;
	}

	public boolean exists() {
		// using Receipt Actions as page mark
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("^( )*Receipt Actions", false));
	}
	
	public String getFacilityWithState(String parkName){
		return browser.getObjectText(".class", "Html.TD", ".text", new RegularExpression("^"+parkName, false));
	}
	

}
