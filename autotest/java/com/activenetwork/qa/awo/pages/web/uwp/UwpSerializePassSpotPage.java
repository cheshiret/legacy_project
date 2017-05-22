package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
/**
 * 
 * @Description: This page is part of PLW home page
 * URL:such as PLW (NM): qa3-nm01.qa.reserveamerica.com/
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Apr 25, 2014
 */
public class UwpSerializePassSpotPage extends UwpHomePage{
		static class SingletonHolder {
			protected static UwpSerializePassSpotPage _instance = new UwpSerializePassSpotPage();}

		protected UwpSerializePassSpotPage() {}

		public static UwpSerializePassSpotPage getInstance() {
			return SingletonHolder._instance;
		}

		/** Page Object Property Definition Begin */
		protected Property[] annualPassImg(){
			return Property.concatPropertyArray(img(), ".title", "Annual Pass", ".src", new RegularExpression("/brands/nm/marketing/images/spot\\d+\\.jpg", false));
		}

		protected Property[] dayUsePassImg(){
			return Property.concatPropertyArray(img(), ".title", "Day Pass", ".src", new RegularExpression("/brands/nm/marketing/images/spot\\d+\\.jpg", false));
		}
		/** Page Object Property Definition End */
		
		public boolean exists() {
			return browser.checkHtmlObjectExists(annualPassImg()) && browser.checkHtmlObjectDisplayed(dayUsePassImg());
		}

		public void clickAnnualCampingPassImg(){
			browser.clickGuiObject(annualPassImg());
		}
		
		public void clickAnnualDayUseImg(){
			browser.clickGuiObject(dayUsePassImg());
		}
	}
