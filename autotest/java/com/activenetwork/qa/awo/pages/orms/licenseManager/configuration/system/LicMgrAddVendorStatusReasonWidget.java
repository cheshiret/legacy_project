package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class LicMgrAddVendorStatusReasonWidget extends DialogWidget {
	private static LicMgrAddVendorStatusReasonWidget _instance = null;
	
	protected LicMgrAddVendorStatusReasonWidget() {}
	
	public static LicMgrAddVendorStatusReasonWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrAddVendorStatusReasonWidget();
		}
		
		return _instance;
	}
	
	public void selectVendorStatus(String status){
		browser.selectDropdownList(".id", 
				new RegularExpression("VendorStatusReasonView-\\d+\\.statusID",false), status);
	}
	
	public void setStatusReason(String reason){
		browser.setTextField(".id", 
				new RegularExpression("VendorStatusReasonView-\\d+\\.reason",false), reason);
	}
	
	public void setVendorStatusReason(String status, String reason){
		if(null != status && status.length() > 0){
			this.selectVendorStatus(status);
		}
		this.setStatusReason(reason);
	}
	
	
	public String getErrorMessage(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "NOTSET");
		if(objs.length < 1) {
			throw new ObjectNotFoundException("Can't find error message object.");
		}
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return msg;
	}

}
