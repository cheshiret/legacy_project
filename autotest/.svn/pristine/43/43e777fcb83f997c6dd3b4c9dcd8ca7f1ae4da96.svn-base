package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Lotteries --- > Hunt Locations
 * @author pchen
 * @Date Nov 12, 2012
 */
public class LicMgrHuntLocationsListPage extends LicMgrLotteriesCommonPage{
	private static LicMgrHuntLocationsListPage _instance = null;
	
	protected LicMgrHuntLocationsListPage (){}
	
	public static LicMgrHuntLocationsListPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrHuntLocationsListPage();
		}
		
		return _instance;
	}
	
	private String prefix = "HuntLocationSearchCriteria-\\d+\\.";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE",".id","huntLocationListGrid");
	}
	
	public void clickAddHuntLocation(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add (Hunt )?Location", false), true);
	}
	
	public void selectStatus(String status){
		if(StringUtil.notEmpty(status)){
			browser.selectDropdownList(".id", new RegularExpression(prefix + "status",false), status);	
		}else{
			browser.selectDropdownList(".id", new RegularExpression(prefix + "status", false), 0);
		}
	}
	
	/**
	 * Select the check box before the given species
	 * @param species
	 */
	public void checkSpecies(String... species){
		for(String sp: species){
			IHtmlObject[] speciesDivs = browser.getDropdownList(".class", "Html.DIV",
					".text", sp);
			if(speciesDivs.length == 0){
				throw new ErrorOnPageException("Checkbox for " + sp + " does not exist in filters!");
			}
		    IHtmlObject top = speciesDivs[0];
		    browser.clickGuiObject(".class", "Html.INPUT", true, 0, top);
		    Browser.unregister(speciesDivs);
		}
	}
	
	public void clickGo(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Go", true);
	}

	public void clickHuntLocCode(String code) {
		browser.clickGuiObject(".class", "Html.A", ".text", code);
	}
}
