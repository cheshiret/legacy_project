package com.activenetwork.qa.awo.pages.orms.common.popup;

import com.activenetwork.qa.testapi.page.HtmlPopupPage;

public class OrmsSwipeCardPopupPage extends HtmlPopupPage{

	public static OrmsSwipeCardPopupPage _instance;
	
	protected OrmsSwipeCardPopupPage (){
		super("title","Please Swipe Card");
	} 
	
	public static OrmsSwipeCardPopupPage getInstance(){
		if(null ==_instance){
			_instance=new OrmsSwipeCardPopupPage();
		}
		return _instance;
	}
	
	/**
	 * Click Cancel button
	 */
	public void clickCancel(){
		popup.clickGuiObject(".class", "Html.A",".text","Cancel");
	}
	
}
