package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;

public class UwpSelectDiscountPage  extends UwpPage {
	private static UwpSelectDiscountPage _instance = null;

	public static UwpSelectDiscountPage getInstance() {
		if (null == _instance)
			_instance = new UwpSelectDiscountPage();

		return _instance;
	}

	public UwpSelectDiscountPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "continueshop");
	}
	
	/**
	 * Click on Remove this link to remove holded items.
	 */
	public void clickRemoveThis() {
		browser.clickGuiObject(".id", "removethis");
	}
	
	/**
	 * Click on Change Dates link.
	 */
	public void clickChangeDates() {
		browser.clickGuiObject(".id", "changedates");
	}
	
	/**
	 * Click on Continue to Shopping Cart button in select discount page.
	 */
	public void clickContinueToShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
	
	/**
	 * Select the discount check box by given name.
	 * @param discountName - discount name
	 */
	public void selectDiscount(String discountName){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", discountName);//can not use Regular Express as some discount may have same name
		
		if(objs.length<1){
		  	throw new ItemNotFoundException("Failed to find discount '"+discountName+"'.");
		}else {
		  	String objID=objs[0].getProperty("htmlFor").toString();
		  	IHtmlObject[] radio=browser.getRadioButton(".id",objID);
		  	if(radio.length<1){
		  	  	throw new ErrorOnPageException("Discount radiobutton's id is not label's htmlFor property.");
		  	}else {
		  		((IRadioButton)radio[0]).isSelected();
//		  		System.out.println(radio[0].getProperty(".defaultChecked"));
//		  		if(radio[0].getProperty(".defaultChecked").equalsIgnoreCase("False")) //Update by Sara at 5/18/2013, Updated Selenium get this property valule as "null", not expected.
		  		if(!((IRadioButton)radio[0]).isSelected()){
		  			radio[0].click();
		  		}
		  		
		  	}
		  	Browser.unregister(radio);
		}
		Browser.unregister(objs);
	}
	
}
