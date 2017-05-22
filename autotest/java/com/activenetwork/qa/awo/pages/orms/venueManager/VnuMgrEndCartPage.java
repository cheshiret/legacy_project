/*
 * Created on Aug 7, 2009
 *
 */
package com.activenetwork.qa.awo.pages.orms.venueManager;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;


/**
 * @author vzhao
 */
public class VnuMgrEndCartPage extends VenueManagerPage {
	/**
	 *  A handle to the unique Singleton instance.
	 */
	static private VnuMgrEndCartPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected VnuMgrEndCartPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public VnuMgrEndCartPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new VnuMgrEndCartPage();
		}

		return _instance;
	}

	private Property[] okToCancelCart(){
		return Property.toPropertyArray(".class", "Html.A", ".text", "OK to Cancel Cart");
	}
	
	/** Determine whether or not the VenueManager home page exists. */
	public boolean exists() {
		return browser.checkHtmlObjectExists(okToCancelCart());
	}

	/** Cancel cart by clicking on link OK to Cancel Cart.*/
	public void clickOkToCancelCart() {
		browser.clickGuiObject(okToCancelCart());
	}

	/** Click on link to quit cancelling cart. */
	public void clickDontCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Don't Cancel",false,0,browser.getFrame("transaction")[0]);
	}
}
