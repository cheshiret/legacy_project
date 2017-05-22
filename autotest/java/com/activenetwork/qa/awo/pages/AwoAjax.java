package com.activenetwork.qa.awo.pages;

import com.activenetwork.qa.testapi.page.Ajax;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class AwoAjax extends Ajax {
	protected Property[] ajaxIcon() {
		return Property.toPropertyArray(".class","Html.IMG",".alt","wait...",".src",new RegularExpression("common/images_skin1/wait.gif",false));
	}
	
	protected Property[] ajaxDIV() {
		return Property.toPropertyArray(".class","Html.DIV",".id","waitDiv");
	}
	
	protected AwoAjax(){}
	
	public static Ajax getInstance() {
		if(_instance==null) {
			_instance=new AwoAjax();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(ajaxIcon())||browser.checkHtmlObjectDisplayed(ajaxDIV());
	}

}
