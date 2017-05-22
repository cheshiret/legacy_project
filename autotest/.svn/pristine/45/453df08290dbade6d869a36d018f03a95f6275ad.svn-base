package com.activenetwork.qa.awo.pages.orms.common.marina;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.util.Property;
/**
 * @author phoebe
 * @Date  Jan 06, 2014
 */
public class OrmsListEntryHistoryPage extends OrmsPage{
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsListEntryHistoryPage _instance = null;

	private OrmsListEntryHistoryPage() {

	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsListEntryHistoryPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsListEntryHistoryPage();
		}

		return _instance;
	}
	
	public boolean exists() {
		 return browser.checkHtmlObjectExists(retrunToListEntryDetailsButton());
	}
	
	protected Property[] retrunToListEntryDetailsButton() {  
		return Property.toPropertyArray(".class", "Html.A",".text","Return To List Entry Details");
	}
	
	public void clickReturnToListEntryDetails(){
		browser.clickGuiObject(this.retrunToListEntryDetailsButton());
	}
}
