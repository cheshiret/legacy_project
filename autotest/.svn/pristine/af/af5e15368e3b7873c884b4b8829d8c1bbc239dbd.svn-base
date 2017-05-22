package com.activenetwork.qa.awo.pages.orms.licenseManager;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
/**
 * @ScriptName LicMgrDeactiveIdentifierWidget.java
 * @Date:May 11, 2011
 * @Description:
 * @author Sara Wang
 */
public class LicMgrDeactiveIdentifierWidget extends DialogWidget {
	private static LicMgrDeactiveIdentifierWidget instance=null;

	private LicMgrDeactiveIdentifierWidget(){
		super("Deactivate Identifier");
	}

	public static LicMgrDeactiveIdentifierWidget getInstance(){
		if(instance==null){
			instance=new LicMgrDeactiveIdentifierWidget();
		}
		return instance;
	}


	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text");
	}
}
