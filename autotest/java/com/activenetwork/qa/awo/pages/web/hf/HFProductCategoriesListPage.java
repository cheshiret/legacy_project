package com.activenetwork.qa.awo.pages.web.hf;

import com.activenetwork.qa.testapi.util.Property;

/**
 * @Description: 
 * @Preconditions:
 * @SPEC: 
 * @LinkSetUp:
 * @Task#: 
 * 
 * @author Lesley Wang
 * @Date  Feb 12, 2014
 */
public class HFProductCategoriesListPage extends HFHeaderBar {
	private static HFProductCategoriesListPage _instance = null;

	public static HFProductCategoriesListPage getInstance() {
		if (null == _instance)
			_instance = new HFProductCategoriesListPage();

		return _instance;
	}
	
	protected HFProductCategoriesListPage() {
	}
	
	/** Page Object Property Definition Begin */
	protected Property[] productCategoriesForm() {
		return Property.concatPropertyArray(this.form(),  ".id", "ProductCategoriesKit_form");
	}
	/** Page Object Property Definition End */
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(productCategoriesForm());
	}
}
