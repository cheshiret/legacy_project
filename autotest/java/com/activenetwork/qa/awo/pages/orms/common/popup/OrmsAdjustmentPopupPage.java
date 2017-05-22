package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;


public class OrmsAdjustmentPopupPage extends HtmlPopupPage {
	private static OrmsAdjustmentPopupPage _instance;
	
	protected OrmsAdjustmentPopupPage () {
		super("title","Adjustment Notes");
	}
	
	public static OrmsAdjustmentPopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsAdjustmentPopupPage();
		}
		
		return _instance;
	}
	public void clickOK() {
		popup.clickGuiObject(".class","Html.BUTTON",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.BUTTON",".text","Cancel");
	}
	
	public void setAdjustReason(String notes){
		//RegularExpression regTextbox = new RegularExpression("(reason)",false);
		popup.setTextArea(".id", "fee_adjustment_notes", notes);
		//popup.setTextField(".id", "fee_adjustment_notes", notes);
	}
}
