package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsImportGiftCardPopupPage extends HtmlPopupPage{
	
	private static OrmsImportGiftCardPopupPage _instance;

	protected OrmsImportGiftCardPopupPage() {
		super("title", new RegularExpression("Activate Gift Card.*",false));
	}
	
	public static OrmsImportGiftCardPopupPage getInstance(){
		if(null==_instance){
			_instance = new OrmsImportGiftCardPopupPage();
		}
		
		return _instance;
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public String getText(){
		return popup.text();
	}

}
