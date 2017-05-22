package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author 
 * @Date  Apr 23, 2014
 */
public class RecgovInteragencyPassSpotPage extends UwpCampingPage{
	static class SingletonHolder {
		protected static RecgovInteragencyPassSpotPage _instance = new RecgovInteragencyPassSpotPage();
	}

	protected RecgovInteragencyPassSpotPage() {
	}

	public static RecgovInteragencyPassSpotPage getInstance() {
		return SingletonHolder._instance;
	}
	
	protected Property[] interagencyPassSaleSpotImage(){
		return Property.concatPropertyArray(img(), ".src", new RegularExpression("/marketing/spots/\\d+ATBPass160x600.jpg", false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(interagencyPassSaleSpotImage());
	}

	public boolean isInteragencyPassSpotExisting(){
		return browser.checkHtmlObjectExists(interagencyPassSaleSpotImage());
	}
	
	public void clickInteragencyPassSpotImg(){
		browser.clickGuiObject(interagencyPassSaleSpotImage());
	}
}
