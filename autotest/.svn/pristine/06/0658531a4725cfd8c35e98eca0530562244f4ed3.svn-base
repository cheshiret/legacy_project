package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.web.common.camping.UwpCampingPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author Sara Wang
 * @Date  Dec 17, 2012
 */
public class UwpMaMarketingSpotPage extends UwpCampingPage{
	private static UwpMaMarketingSpotPage _instance = null;

	public static UwpMaMarketingSpotPage getInstance() {
		if (null == _instance)
			_instance = new UwpMaMarketingSpotPage();

		return _instance;
	}

	public UwpMaMarketingSpotPage() {
	}

	private static String MA_PASS_SALE_SRC = "/marketing/spots/MA-marketing-spot.jpg";

	public boolean exists() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "maPassSale");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression(MA_PASS_SALE_SRC, false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}

	public boolean isMaMarketingSpotExisting(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "maPassSale");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression(MA_PASS_SALE_SRC, false));
		return browser.checkHtmlObjectExists(Property.atList(p1, p2));
	}
	
	public void clickMaMarketingSpot(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "maPassSale");
		Property[] p2 = Property.toPropertyArray(".class", "Html.IMG", ".src", new RegularExpression(MA_PASS_SALE_SRC, false));
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
}

