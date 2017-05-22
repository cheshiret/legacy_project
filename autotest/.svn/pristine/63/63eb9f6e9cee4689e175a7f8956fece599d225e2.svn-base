/*
 * Created on Jan 18, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.financeManager.distribution;

import com.activenetwork.qa.awo.pages.orms.financeManager.FinanceManagerPage;
import com.activenetwork.qa.testapi.util.RegularExpression;


/**
 * @author Ssong
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class FinMgrConcessionaireMainPage extends FinanceManagerPage{

  /**
	 * A handle to the unique Singleton instance.
	 */
	static private FinMgrConcessionaireMainPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected FinMgrConcessionaireMainPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public FinMgrConcessionaireMainPage getInstance(){
		if (null == _instance) {
			_instance = new FinMgrConcessionaireMainPage();
		}

		return _instance;
	}

	/**
	 * check specific page mark display or not
	 */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class","Html.TABLE",".text",new RegularExpression("Concessionaire Code.*",false));
	}
	/**
	 * select search by value
	 * @param searchByValue
	 */
	public void selectSearchBy(String searchByValue){
		browser.selectDropdownList(".id", "concessionaire_search", searchByValue);
	}
	
	/**
	 * set search value
	 * @param searchVaule
	 */
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", "concessionaire_input", searchValue);
	}
	
	/**
	 * Click Select Button
	 *
	 */
	public void clickSelect() {
	  	browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false));
	}
	
	/**
	 * click Go button
	 */
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Go|Search",false));
	}
	
	/**
	 * Search Concessionaire by Concessionaire code 
	 * @param concessionName
	 */
	public void searchByConcessionaireCode(String concessionCode){
		this.selectSearchBy("Concessionaire Code");
		if(!concessionCode.equals("")){
			this.setSearchValue(concessionCode);
		}
		this.clickGo();
		this.waitLoading();
	}
}
