package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpProductListCommonPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class RASlipListPage extends UwpProductListCommonPage{
	static class SingletonHolder {
		protected static RASlipListPage _instance = new RASlipListPage();
	}

	protected RASlipListPage() {
	}

	public static RASlipListPage getInstance() {
		return SingletonHolder._instance;
	}
	
	private Property[] bookNowProp(RegularExpression hrefRegx){
		return Property.concatPropertyArray(a(),".className", "book now",".href", hrefRegx);
	}

	public boolean checkSlipAvailable(String siteID) {
		RegularExpression href=new RegularExpression(".*siteId="+siteID+".*",false);
		boolean available= browser.checkHtmlObjectExists(bookNowProp(href));

		int count=0;
		while (!available && hasNextPage() && count<4) {
			clickNext();
			waitLoading();
			available = available && browser.checkHtmlObjectExists(bookNowProp(href));
			count++;
		}

		return available;
	}

	public void selectSelectLink(String slipID){
		browser.clickGuiObject(bookNowProp(new RegularExpression(".*siteId="+slipID+".*",false)));
	}
}