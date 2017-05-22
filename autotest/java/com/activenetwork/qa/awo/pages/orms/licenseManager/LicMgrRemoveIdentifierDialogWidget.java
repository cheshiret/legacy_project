/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @ScriptName LicMgrRemoveIdentifierDialogWidget.java
 * @Date:Feb 12, 2011
 * @Description:
 * @author asun
 */
public class LicMgrRemoveIdentifierDialogWidget extends DialogWidget {
	
	private static LicMgrRemoveIdentifierDialogWidget instance=null;
	
	private LicMgrRemoveIdentifierDialogWidget(){
		super("Remove Identifier\\(s\\)");
	}
	
	public static LicMgrRemoveIdentifierDialogWidget getInstance(){
		if(instance==null){
			instance=new LicMgrRemoveIdentifierDialogWidget();
		}
		return instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text");
	}
}
