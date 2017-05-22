package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;

/**
 * 
 * @Description: Page title is "New Products Added". It will show after click Continue button in "Product Information Required" page
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Oct 17, 2013
 */
public class HFNewProductsAddedPage extends HFHeaderBar {
	private static HFNewProductsAddedPage _instance = null;

	public static HFNewProductsAddedPage getInstance() {
		if (null == _instance)
			_instance = new HFNewProductsAddedPage();

		return _instance;
	}
	
	protected HFNewProductsAddedPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] addDepProdFrom(){
		return Property.concatPropertyArray(form(), ".id", "AddDepProdKit_form");
	}
	
	protected Property[] continueBtn(){
		return Property.toPropertyArray(".id", "submitForm_submitForm", ".text", "Continue");
	}
	
	protected Property[] privName(){
		return Property.concatPropertyArray(span(), ".className", "groupLabel");
	}
	
	/** Page Object Property Definition End */
	public boolean exists() {
		return browser.checkHtmlObjectExists(addDepProdFrom());
	}
	
	public void clickContinue() {
		browser.clickGuiObject(continueBtn());
	}
	
	public String getPrivilegeName(){
		return browser.getObjectText(privName());
	}
	
	public void verifyPrivilegeName(String privName){
		String privNameFromUI = getPrivilegeName();
		if(privName.equals(privNameFromUI)){
			logger.info("Successfully verify privilege name - "+privName);
		}else throw new ErrorOnPageException("Failed to verify privilege name", privName, privNameFromUI);
	}
}
