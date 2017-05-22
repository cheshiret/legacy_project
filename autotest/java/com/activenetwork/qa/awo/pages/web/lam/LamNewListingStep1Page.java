package com.activenetwork.qa.awo.pages.web.lam;

import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.util.Property;

public class LamNewListingStep1Page extends LamNewListingPage {
  	private static LamNewListingStep1Page _instance = null;
//  	private final String addressError="We cannot verify your attraction address. Please check and make sure your address information is correct.";
  	
  	public static LamNewListingStep1Page getInstance() {
  	  	if(null==_instance) {
  	  	  	_instance=new LamNewListingStep1Page();
  	  	}
  	  	
  	  	return _instance;
  	}
  	
  	protected LamNewListingStep1Page() {
  	  
  	}
  	
  	public boolean exists() {
  	  	return browser.checkHtmlObjectExists(".id","wysiwyg_div_attractionName");
  	}
  	
  	/**
  	 * Click to clear and start over
  	 */
  	public void clearAndStartOver() {
  	  	IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A", ".text","Clear and Start Over");
  	  	if(objs.length>0) {
  	  	  	((ILink) objs[0]).click();
  	  	  	this.waitLoading();
  	  	} 
  	  	Browser.unregister(objs);
  	}
  	
  	/**
  	 * Set the attraction name
  	 * @param name
  	 */
  	public void setAttractionName(String name) {
  		Property[] p=new Property[1];
        p[0]=new Property(".id", "wysiwygattractionName");
        browser.setIFrameTextField(p,name,true,0,null);
  	}
  	
  	/**
  	 * Select the category
  	 * @param category
  	 */
  	public void selectCategory(String category) {
  	  	browser.selectDropdownList(".name","category",category);
  	}
  	
  	/**
  	 * Set the phone
  	 * @param phone
  	 */
	public void setPhoneNumber(String phone) {
	  	browser.setTextField(".id","phoneNumber",phone);
	}
	
	/**
	 * Set street name
	 * @param name
	 */
  	public void setStreetName(String name) {
  	  	browser.setTextField(".id","streetName",name);
  	}
  	
  	/**
  	 * Set city or region
  	 * @param city
  	 */
  	public void setCityRegion(String city) {
  	  browser.setTextField(".id","cityRegion",city);
  	}
  	
  	/**
  	 * Select the state
  	 * @param state
  	 */
  	public void selectStateProvince(String state) {
	  	browser.selectDropdownList(".name","stateProvince",state);
	}
  	
  	/**
  	 * Set zipcode
  	 * @param zip
  	 */
  	public void setZipCode(String zip) {
  	  	browser.setTextField(".id","postalCode",zip);
  	}
  	
  	/**
  	 * Verify the address suggestion exists
  	 * @return
  	 */
  	public boolean addressSuggestionsExists() {
  	  	return browser.checkHtmlObjectExists(".id","addressSuggestions");
  	}
  	
  	/**
  	 * Get error message
  	 * @return - the error message
  	 */
  	public String getErrorMessage() {
	  	if(browser.checkHtmlObjectExists(".text","Terms and Conditions.")) {
	  	  	return "Some of the words/phrases used in Link Description: violate the Terms and Conditions.";
	  	} else if(addressSuggestionsExists()) {
  	  	  	return "Ambiguous address";
  	  	} else {
  	  	  	return "You must correct or complete the marked item(s)";
  	  	}
  	}
}
