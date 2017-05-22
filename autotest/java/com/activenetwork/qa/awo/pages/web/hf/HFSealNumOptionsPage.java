package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description: This page will display after click Continue button in HFNewProductsAddedPage
 * URL:http://hfXX-uwppl-torqaX.dev.activenetwork.com/privInvFulfillment.do
 * @Preconditions:
 * @LinkSetUp:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Jun 13, 2014
 */
public class HFSealNumOptionsPage extends HFHeaderBar {
	private static HFSealNumOptionsPage _instance = null;

	public static HFSealNumOptionsPage getInstance() {
		if (null == _instance)
			_instance = new HFSealNumOptionsPage();

		return _instance;
	}
	
	protected HFSealNumOptionsPage() {
	}
	
	protected static String PURCHASE_CHOICE_EMAIL = "Order my licence through the mail.*";
	protected static String PURCHASE_CHOICE_SEALNUM = "Enter my Seal #.*";
	
	protected Property[] privilegeFulfillmentKitForm(){
		return Property.concatPropertyArray(form(), ".id", "PrivilegeFulfillmentKit_form");
	}
	
	protected Property[] purchaseChoiceDiv(String choice){
		return Property.concatPropertyArray(div(), ".className", "control RADIOSET", ".text", new RegularExpression("^"+choice+".*", false));
	}
	
	protected Property[] choiceMethodRadio(){
		return Property.toPropertyArray(".id", new RegularExpression("LPrivilegeFulfillmentKit_methodsLayout_m_\\d+", false));
	}
	
	protected Property[] sealNum(){
		return Property.toPropertyArray(".id", new RegularExpression("Atag_\\d+_-\\d+", false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(privilegeFulfillmentKitForm());
	}
	
	public void clickContinue() {
		browser.clickGuiObject(".id", "submitForm_submitForm", ".text", "Continue");
	}
	
	public void clickPurchaseChoiceByEmail(){
		browser.clickGuiObject(Property.atList(purchaseChoiceDiv(PURCHASE_CHOICE_EMAIL), choiceMethodRadio()));
	}
	
	public void clickPurchaseChoiceBySealNum(){
		browser.clickGuiObject(Property.atList(purchaseChoiceDiv(PURCHASE_CHOICE_SEALNUM), choiceMethodRadio()));
	}
	
	public void setSealNum(String sealNum){
		browser.setTextField(sealNum(), sealNum);
	}
	
}

