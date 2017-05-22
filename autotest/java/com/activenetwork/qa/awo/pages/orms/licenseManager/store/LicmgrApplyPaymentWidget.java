package com.activenetwork.qa.awo.pages.orms.licenseManager.store;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author nding1
 * @Date  May 7, 2012
 */
public class LicmgrApplyPaymentWidget extends DialogWidget {

	private static LicmgrApplyPaymentWidget instance=null;
	
	private  LicmgrApplyPaymentWidget(){}
	
	public static LicmgrApplyPaymentWidget getInstance(){
		if(instance==null){
			instance=new LicmgrApplyPaymentWidget();
		}
		return instance;
	}
	
	public void setNote(String note){
		browser.setTextArea(".id", "paymentNote", note);
	}
	
	public String getPayAmount()
	{
		return browser.getHtmlObject(".id", "paymentAmount")[0].getProperty("value");
	}
	
}
