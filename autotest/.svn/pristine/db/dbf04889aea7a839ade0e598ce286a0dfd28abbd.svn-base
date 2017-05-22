package com.activenetwork.qa.awo.pages.orms.callManager;


import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class CallMgrVoidVoucherPaymentsBeforeTransfer extends CallManagerPage {
	private static CallMgrVoidVoucherPaymentsBeforeTransfer _instance;

	protected CallMgrVoidVoucherPaymentsBeforeTransfer() {

	}

	public static CallMgrVoidVoucherPaymentsBeforeTransfer getInstance() {
		if (null == _instance) {
			_instance = new CallMgrVoidVoucherPaymentsBeforeTransfer();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TEXTAREA",".id", "voidingReason");
	}
	
	/**Set the reason of voiding voucher*/
	public void setVoidingVoucher(String note) {
		browser.setTextField(".id", "voidingReason", note, true);
	}
	
	/**Click Cancel button*/
	public void clickCancel(){
		browser.clickGuiObject(".class", "Html.INPUT.button",".id","cancelButton");
	}
	
	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.INPUT.button",".id","OKButton");
	}
	
	/**Get the price of voucher payment*/
	public String getVoucherPrice (){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^The following payments.*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String price = table.getProperty(".text").split("\\$")[1].split(" ")[0].toString().trim();
		Browser.unregister(objs);
		
		return price;
	}
	
	/**Get the voucher ID*/
	public String getVoucerId() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^The following payments.*",false));
		IHtmlTable table = (IHtmlTable) objs[0];
		
		String id = table.getProperty(".text").split("Please")[0].split("from Voucher")[1].toString().replaceAll("[^0-9]", "");
		Browser.unregister(objs);
		
		return id;
	}
}
