/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;

/**
 * @ScriptName LicMgrConfirmDialogWidget.java
 * @Date:Feb 14, 2011
 * @Description:
 * @author asun
 */
public class LicMgrConfirmDialogWidget extends DialogWidget {
	
	private static LicMgrConfirmDialogWidget instance = null;

	private LicMgrConfirmDialogWidget(boolean isTitleNull) {
		super("(Please Confirm)|Error" + (isTitleNull ? "|" : ""));
	}
	
	public static LicMgrConfirmDialogWidget getInstance() {
		return getInstance(false);
	}

	public static LicMgrConfirmDialogWidget getInstance(boolean isTitleNull) {
		if (instance == null) {
			instance = new LicMgrConfirmDialogWidget(isTitleNull);
		}
		return instance;
	}
	
	public String getMessage() {
		
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
	
	/**
	 * verify error message.
	 * @param expectedMsg
	 */
	public void verifyErroMsg(String expectedMsg){
		String msg = this.getMessage();
		if(!MiscFunctions.compareResult("Alert Message", expectedMsg, msg)){
			throw new ErrorOnPageException("Alert Message",expectedMsg,msg);
		}else{
			logger.info("Alert message math the expected message");
		}
	}
	
	public boolean checkContentIsExisting(String text){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TD", ".text", text);
	}
}
