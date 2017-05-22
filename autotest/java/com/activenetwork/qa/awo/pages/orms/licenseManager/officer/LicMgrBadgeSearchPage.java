package com.activenetwork.qa.awo.pages.orms.licenseManager.officer;

import java.util.List;

import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * This is the hunts list page in license manager, Admin(drop down list)-->Officer MGT --- > Badges
 * @author pchen
 * @Date November 30, 2012
 */
public class LicMgrBadgeSearchPage extends LicMgrOfficerMGTCommonPage{
	private static LicMgrBadgeSearchPage _instance = null;
	
	protected LicMgrBadgeSearchPage (){}
	
	public static LicMgrBadgeSearchPage getInstance(){
		if(null == _instance){
			_instance = new LicMgrBadgeSearchPage();
		}
		
		return _instance;
	}
	
	private String prefix = "BadgeSearchCriteria-\\d+\\.";
	 
	public boolean exists(){
		   return browser.checkHtmlObjectExists(".class", "Html.DIV",".id","badgeList");
	   }
	
	public void clickAddBadges(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Badges", true);
	}
	
	public void selectSearchType(String searchType){
		browser.selectDropdownList(".id", new RegularExpression(prefix+"searchType", false), searchType);
	}
	
	public void setSearchValue(String searchValue){
		browser.setTextField(".id", new RegularExpression(prefix+"searchValue", false), searchValue);
	}
	
	public void clickSearch(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Search", true);
	}
	
	/**
	 * The method used to get specific row values
	 * @param identifier
	 *            ----one cell value in your expected row
	 * @return
	 */
	public List<String> getRowInfoByBadgeNum(String identifier) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "badgeList_LIST");
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(2, identifier);
		if(row < 0){
			throw new ObjectNotFoundException("The row which badge# " + identifier + "is not found under colum:2 ");
		}
		List<String> values = table.getRowValues(row);
		Browser.unregister(objs);
		return values;
	}
	
	/**
	 * Search badge by badge number
	 * @param badgeNum
	 */
	public void searchBadgeByNum(String badgeNum){
		this.selectSearchType("Badge #");
		this.setSearchValue(badgeNum);
		this.clickSearch();
		ajax.waitLoading();
		this.waitLoading();
	}
}
