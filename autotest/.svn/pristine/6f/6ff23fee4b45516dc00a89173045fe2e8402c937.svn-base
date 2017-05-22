package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Mar 13, 2013
 */
public class OrmsEnterBoatLengthWidget extends DialogWidget {
	private static OrmsEnterBoatLengthWidget _instance = null;
	
	private OrmsEnterBoatLengthWidget() {
		super("Enter Boat Length");
	}
	
	public static OrmsEnterBoatLengthWidget getInstance() {
		if(_instance == null) {
			_instance = new OrmsEnterBoatLengthWidget();
		}
		
		return _instance;
	}
	
	public void setBoatLength(String length) {
		browser.setTextField(".id", new RegularExpression("MarinaBoatLengthDialog-\\d+\\.boatLength", false), length);
	}
}
