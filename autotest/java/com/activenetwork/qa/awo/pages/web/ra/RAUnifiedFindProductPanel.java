package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpUnifiedFindProductCommonPanel;
import com.activenetwork.qa.testapi.util.Property;

public class RAUnifiedFindProductPanel extends UwpUnifiedFindProductCommonPanel{
	static class SingletonHolder {
		protected static RAUnifiedFindProductPanel _instance = new RAUnifiedFindProductPanel();
	}

	protected RAUnifiedFindProductPanel() {
	}

	public static RAUnifiedFindProductPanel getInstance() {
		return SingletonHolder._instance;
	}
	
	protected Property[] unifSearchForm(){
		return Property.concatPropertyArray(form(), ".id", "unifSearchForm");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(unifSearchForm());
	}
}
