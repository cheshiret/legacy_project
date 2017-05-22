package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsOpeningCashBalanceConfirmationPage extends HtmlPopupPage {
	private static OrmsOpeningCashBalanceConfirmationPage _instance;
	
	protected OrmsOpeningCashBalanceConfirmationPage () {
		super("url",new RegularExpression("https?://.+/common/html/openingFloatConfirmation.html",false));
	}
	
	public static OrmsOpeningCashBalanceConfirmationPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsOpeningCashBalanceConfirmationPage();
		}
		
		return _instance;
	}
	
	public void clickYes() {
		popup.clickGuiObject(".class","Html.A",".text","Yes", true);
	}
	
	public void clickNo() {
		popup.clickGuiObject(".class","Html.A",".text","No", true);
	}
	
	public String getConfirmationMessage() {
		IHtmlObject objs[] = popup.getHtmlObject(".class", "Html.SPAN", ".id", "finsession.openingFloa.confirmation.message");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find confirmation message SPAN object.");
		}
		
		String msg = objs[0].text();
		Browser.unregister(objs);
		
		return msg;
	}
}
