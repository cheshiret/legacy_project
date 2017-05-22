/*
 * $Id: UwpMemberStatusBar.java 6822 2008-12-02 16:48:26Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class UwpMemberStatusBar extends UwpPage {

	public static final int GOTO_SIGN_IN = 1;
	public static final int GOTO_SIGN_UP = 2;
	public static final int GOTO_SIGN_OUT = 3;

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private UwpMemberStatusBar _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	public UwpMemberStatusBar() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	public static UwpMemberStatusBar getInstance() throws PageNotFoundException { // NOTE: make 'public' to use
		if (null == _instance) {
			_instance = new UwpMemberStatusBar();
		}

		return _instance;
	}

	protected Property[] cartLink() {
		return Property.toPropertyArray(".id", "cartLink");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "signIn");
	}
	
	/**
	 * Click on Sign in link.
	 */
	public void gotoSignIn() {
//		browser.clickGuiObject(".id", "signIn", ".text", "Sign In");
//		browser.clickGuiObject(".id", "signIn", ".text", new RegularExpression("Sign In|Log-In", false),true); Update from 3.04.01 due to RA UI changed
		browser.clickGuiObject(".id", "signIn",true); // For REC, text=Log-In; for PLW, text=Sign In, for RA with new UI, text=Sign In or Sign Up
	}
	
	/**
	 * check the sign in button exist or not.
	 */
	public boolean checkSignInExist() {
//		return browser.checkHtmlObjectExists(".id", "signIn", ".text", new RegularExpression("(Sign |Log-)In", false));
		return browser.checkHtmlObjectExists(".id", "signIn"); //Update from 3.04.01 due to RA UI changed
	}
	
	/**
	 * Click on Sign Up link to create a new customer account.
	 */
	public void signUp() {
//		browser.clickGuiObject(".id", "signUp", ".text", new RegularExpression("Sign Up|Sign-Up", false));
//		browser.clickGuiObject(".id", new RegularExpression("signUp|signIn", false), 
//				".text", new RegularExpression("(Sign Up)|(Sign-Up)|(Sign In or Sign Up)", false));
		browser.clickGuiObject(".class", "Html.A", 
				".text", new RegularExpression("(Sign Up)|(Sign-Up)|(Sign In or Sign Up)", false));
	}
	
	/**
	 * Click on Sign Out link to sign out your account.
	 */
	public void signOut() {
		RegularExpression pattern=new RegularExpression("(Sign Out|Log-Out)",false);
		browser.clickGuiObject(".id", "signIn", ".text", pattern,true);
		logger.debug("Signed out.");
	}
	
	public boolean needToSignOut(){
		return browser.checkHtmlObjectExists(".id", "signIn", ".text", new RegularExpression("(Sign Out|Log-Out)",false));
	}
	
	/**
	 * Check the "Items In Cart:" link exists
	 * @return
	 */
	public boolean isItemsInCartLinkExists(){
//		HtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false));
//		boolean toReturn = false;
//		
//		if(objs.length > 0){
//			toReturn = true;
//		}
//		Browser.unregister(objs);
//		return toReturn;
		//Quentin[20130905] 'Items In Cart:' has been changed to 'Items:' and not clickable and replaced with 'Check Out Now'
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false)) || browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Check Out Now");
	}
	
	public void clickItemsInCartLink() {
//		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false));
		//Quentin[20130905] 'Items In Cart:' has been changed to 'Items:' and not clickable and replaced with 'Check Out Now'
		Property oldProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false));
		Property newProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", "Check Out Now");
		if(browser.checkHtmlObjectExists(oldProperty)) {
			browser.clickGuiObject(oldProperty);
		} else if(browser.checkHtmlObjectExists(newProperty)) {
			browser.clickGuiObject(newProperty);
		}
	}
	
	public String getNumOfItemsInCart() {
		String text = browser.getObjectText(this.cartLink());
		return text.split(":")[1].trim();
	}
}
