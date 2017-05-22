package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.page.HtmlPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsBalancePopupPage extends HtmlPopupPage {
	private static OrmsBalancePopupPage _instance;
	
	protected OrmsBalancePopupPage () {
		super("title",new RegularExpression("(b|B)alance|activeworks \\| outdoors Field Manager",false));
	}
	
	public static OrmsBalancePopupPage getInstance() {
		if(null==_instance) {
			_instance=new OrmsBalancePopupPage();
		}
		
		return _instance;
	}
	
	public void clickOK() {
		popup.clickGuiObject(".class","Html.A",".text","OK");
	}
	
	public void clickCancel() {
		popup.clickGuiObject(".class","Html.A",".text","Cancel");
	}
	
	/**
	 * Get popup message
	 * @return popup message
	 */
	public String getPopUpMessage(){
	  IHtmlObject[] obj=popup.getHtmlObject(".class","Html.BODY");
	  String popupMessage=obj[0].getProperty(".text").toString();
	  Browser.unregister(obj);
	  return popupMessage;
	}
}
