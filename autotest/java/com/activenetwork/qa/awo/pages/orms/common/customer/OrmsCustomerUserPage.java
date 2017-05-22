package com.activenetwork.qa.awo.pages.orms.common.customer;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;

public class OrmsCustomerUserPage extends OrmsPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsCustomerUserPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCustomerUserPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCustomerUserPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCustomerUserPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		//		RegularExpression reg=new RegularExpression(".* (View/Update Customer Details|Add Customer)$",false);
//		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
//				"Add New User");
		return browser.checkHtmlObjectExists(".class", "Html.Div", ".text",
				"Add New User");
	}
	
	/**
	 * Click the Passes
	 */
	public void clickPasses(){
		browser.clickGuiObject(".class", "Html.A", ".text","Passes");
	}
	
	
}
