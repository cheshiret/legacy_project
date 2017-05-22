package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * OrmsOpeningCashBalancePage class abstract the popup page for entering opening cash balance when login to Field Manager
 * @author jdu
 *
 */
public class OrmsOpeningCashBalancePage extends HtmlPopupPage {
	private static OrmsOpeningCashBalancePage _instance;
	
	protected OrmsOpeningCashBalancePage () {
		super("url",new RegularExpression("https?://.+/common/html/openingFloat\\..+",false));
	}
	
	public static OrmsOpeningCashBalancePage getInstance() {
		if(null==_instance) {
			_instance=new OrmsOpeningCashBalancePage();
		}
		
		return _instance;
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	public void setOpeningCashBalance(double balance) {
		popup.setTextField(".id","openingfloat",Double.toString(balance));
	}
	
	public void setOpeningCashBalance(String balance) {
		popup.setTextField(".id", "openingfloat", balance);
	}
	
	public String getErrorMessage() {
		IHtmlObject objs[] = popup.getHtmlObject(".class", "Html.DIV", ".id", "finsession.openingFloat.error");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find error message DIV object.");
		}
		
		String msg = objs[0].text();
		Browser.unregister(objs);
		
		return msg;
	}
}
