/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.common;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;

/**
 * @Description: For the widget with the title "Please Confirm".
 * 
 * @author Lesley Wang
 * @Date  Jul 20, 2012
 */
public class OrmsConfirmDialogWidget extends DialogWidget {
	private static OrmsConfirmDialogWidget _instance = null;
	
	private OrmsConfirmDialogWidget() {
		super("Please Confirm|Warning|Error");
	}
	
	public static OrmsConfirmDialogWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsConfirmDialogWidget();
		}
		
		return _instance;
	}
	
	public String getMessage() {
		return browser.getObjectText(".class","Html.SPAN",".className","messagebox_text").trim();
	}
}
