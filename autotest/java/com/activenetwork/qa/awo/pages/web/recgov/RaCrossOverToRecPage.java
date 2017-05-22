package com.activenetwork.qa.awo.pages.web.recgov;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.page.IPopupPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


public class RaCrossOverToRecPage extends UwpPage implements IPopupPage {
	private String attributeName;
	private Object value;
	private static RaCrossOverToRecPage _instance = null;

	public static RaCrossOverToRecPage getInstance() {
		if (null == _instance)
			_instance = new RaCrossOverToRecPage();

		return _instance;
	}

	protected RaCrossOverToRecPage() {
		browser = null;
		attributeName = "url";
		value = new RegularExpression(".*(switchBookingAction|memberSignIn|viewShoppingCart|memberSignOut|addSiteItem).*",false);
	}

	public boolean exists() {
		browser=Browser.getInstance().getBrowser(attributeName, value);
		return browser!=null;
	}

	/**wait for page to load*/
	public void waitForPageLoad(){
//		browser.searchObjectWaitExists(".type","submit",".id", "signinbtn");
		browser.searchObjectWaitExists(".type", "submit", ".id", "submitForm_submitForm");//Sara[12/2/2013]
	}
	
	/**
	 * Fill in user name.
	 * @param user - user account
	 */
	public void setUserName(String user) {
		browser.setTextField(".id", new RegularExpression("(userid)|(AemailGroup_(-)?\\d+)", false), user, true);
	}
	/**
	 * Fill in password.
	 * @param pwd - password
	 */
	public void setPassword(String pwd) {
		browser.setTextField(".id", new RegularExpression("(pw)|(ApasswrdGroup_(-)?\\d+)", false), pwd, true);
	}
	/**
	 * Click on Sign In button.
	 */
	public void clickSignIn() {
		browser.clickGuiObject(".id", new RegularExpression("signinbtn|submitForm_submitForm", false), true);
	}
	/**
	 * Fill in sign in info.
	 * @param user - user name
	 * @param pwd - password
	 */
	public void signIn(String user, String pwd) {
		this.setUserName(user);
		this.setPassword(pwd);
		this.clickSignIn();
	}
	
	/** Verify user can not log in when using a wrong account. */
	public boolean verifySignInFailed(){
	  	boolean toReturn=false;
	  	IHtmlObject[] objs = browser.getHtmlObject(".id","signinform");
	  	
	  	String content=objs[0].getProperty(".text");
	  	if(!content.matches(".*Unable to sign in.*")){
	  	  	throw new ErrorOnPageException("Unknown error.");
	  	}else
	  	  	toReturn=true;
	  	
	  	Browser.unregister(objs);
	  	return toReturn;
	}
	
	/**
	 * Wait for order details page to load.
	 */
	public void waitForOrderDetailsPg(){
		browser.searchObjectWaitExists(".type","submit",".id", "continueshop");
	}
	
	/**
	 * go to shopping cart by clicking the link "Items In Cart" or "Check Out Link":
	 */
	public void gotoCart() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("(Items In Cart: \\d+)|(Check Out Now)", false)); //Lesley[20131023]: Items in Cart changed to Check out Now in REC.gov in 3.05.00
	}
	
	/**
	 * Wait for shopping cart page to load.
	 */
	public void waitForShoppingCartPg(){
		browser.searchObjectWaitExists(".class","Html.A",".text", "Abandon This Cart");
	}
	
	/**
	 * Click on Abandon cart link.
	 */
	public void abandonThisCart() {
		browser.clickGuiObject(".class","Html.A",".text", "Abandon This Cart");
	}
	
	/**
	 * Wait for order details page to load.
	 */
	public void waitForSignOutPg(){
		browser.searchObjectWaitExists(".class", "Html.A", ".text", "Items In Cart: 0"); //Sara[20140220] per DEFECT-55017 
//		browser.searchObjectWaitExists(".class", "Html.Span", ".text", "Items: 0");
	}
	
	/**
	 * Click on Sign Out link to sign out your account.
	 */
	public void gotoSignOut() {
		browser.clickGuiObject(".id", "signIn", ".text",new RegularExpression("Sign Out|Log-Out", false) );
	}
	
	/**
	 * Wait for order details page to load.
	 */
	public void waitForHomePg(){
//		browser.searchObjectWaitExists(".id", "signIn", ".text", "Log-In");
		browser.searchObjectWaitExists(".id", "signIn", ".text", "Sign In or Sign Up");//Sara[12/2/2013]
	}
	
	/**Close recgov browser.*/
	public void closeBrowser(){
		browser.close();
	}
}
