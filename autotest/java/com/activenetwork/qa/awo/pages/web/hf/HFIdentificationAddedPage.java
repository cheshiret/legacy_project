package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author SWang
 * @Date  Mar 19, 2013
 */
public class HFIdentificationAddedPage extends HFHeaderBar {

	private static HFIdentificationAddedPage _instance = null;

	public static HFIdentificationAddedPage getInstance() {
		if (null == _instance)
			_instance = new HFIdentificationAddedPage();

		return _instance;
	}

	protected HFIdentificationAddedPage() {
	}

	protected Property[] purchaseALicense(){
		return Property.concatPropertyArray(a(), ".href", new RegularExpression("/productCategoriesList\\.do\\?prodType=license", false));
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectDisplayed(".id", "updateIdentifierPage");
	}
	
	public void clickGotoHomepage(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go to Home page");
	}
	
	public String getVerificationConfirmationMes(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "verificationConfirmationMessage");
	}
	
	public void clickPurchaseALicenceLink(){
		browser.clickGuiObject(purchaseALicense());
	}
	
	public void clickApplyForTheBigGameDrawLink(){
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("/productCategoriesList\\.do\\?prodType=lottery&topTabIndex=BigGameDraw", false));
	}
	
	public void clickGoToHomePageLnk(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "descriptionParagraph");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public void clickGoToHomePageLnkFromLeftSide(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".className", "leftSideLinks");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}
	
	public boolean isErrorMsgExist(String msg) {
		return browser.checkHtmlObjectExists(".classname", new RegularExpression("(message|error_item)", false), ".text", new RegularExpression(msg, false));
	}
	
	public void verifyErrorMsgExist(String msg, boolean isExist) {
		String info = isExist ? " " : " not ";
		if (this.isErrorMsgExist(msg) != isExist) {
			throw new ErrorOnPageException("The message: " + msg + " should " + info + "exist!");
		}
		logger.info("The message: " + msg + " does " + info + "exist!");
	}
}

