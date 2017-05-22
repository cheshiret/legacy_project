package com.activenetwork.qa.awo.pages.web.ra;

import com.activenetwork.qa.awo.pages.web.uwp.UwpParkDetailsCommonPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class RAParkDetailsPage extends UwpParkDetailsCommonPage{
	static class SingletonHolder {
		protected static RAParkDetailsPage _instance = new RAParkDetailsPage();
	}

	protected RAParkDetailsPage() {
	}

	public static RAParkDetailsPage getInstance() {
		return SingletonHolder._instance;
	}
	
	protected Property[] cgroundName(){
		return Property.concatPropertyArray(span(), ".id", "cgroundName");
	}
	
	protected Property[] backToSearchResult(){
		return Property.concatPropertyArray(a(), ".className", "block", ".href", new RegularExpression("/unifSearchResults\\.do\\?contractCode=.*", false));
	}
	
	protected Property[] overview(){
		return Property.concatPropertyArray(div(), ".id", "overviewContainer");
	}
	
	public String getCampName(){
		return browser.getObjectText(cgroundName());
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(cgroundName()) && browser.checkHtmlObjectExists(backToSearchResult());
	}
	
	public String getOverviewDescription(){
		return browser.getObjectText(overview());
	}
}
