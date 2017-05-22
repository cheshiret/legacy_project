package com.activenetwork.qa.awo.pages.orms;

import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class OrmsGeneralSignedInPage extends OrmsPage {
	static private OrmsGeneralSignedInPage _instance = null;

	protected OrmsGeneralSignedInPage() {
	}
	
	
	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsGeneralSignedInPage getInstance() {
		if (null == _instance) {
			_instance = new OrmsGeneralSignedInPage();
		}

		return _instance;
	}
	
	protected Property[] generalMenutable() {
		return Property.concatPropertyArray(table(), ".className",new RegularExpression("^menu_anchor|menu_anchor_no_background|table_anchor|menuContainer$",false));
	}
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(generalMenutable());
	}

}
