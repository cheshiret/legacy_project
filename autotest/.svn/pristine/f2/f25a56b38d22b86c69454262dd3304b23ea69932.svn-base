package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;

public class OrmsHomePage extends OrmsPage {
	/**
	 * @param sHomePageLink_FieldManager2().Table_HtmlTable_1().
	 */

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsHomePage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsHomePage() {
	}
	
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsHomePage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsHomePage();
		}

		return _instance;
	}
	
	protected Property[] indexOfLinks() {
		return Property.concatPropertyArray(div(),".className","label_header",".text","Index of Links");
	}
	
	protected Property[] salesChannel() {
		return a();
	}
	
	protected Property[] salesChannel(SalesChannel name) {
		return Property.concatPropertyArray(salesChannel(), Property.toPropertyArray(".text",name.getName()));
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(indexOfLinks());

	}
	
	/**
	 * Click link by link name
	 * @param name
	 */
	public void clickLink(SalesChannel name) {
		browser.clickGuiObject(salesChannel(name),true);
	}
}
