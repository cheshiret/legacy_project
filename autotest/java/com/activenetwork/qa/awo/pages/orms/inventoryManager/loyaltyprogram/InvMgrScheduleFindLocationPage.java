/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.loyaltyprogram;

import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author szhou
 * @Date April 21, 2014
 */
public class InvMgrScheduleFindLocationPage extends
		InvMgrLoyaltyProgramCommonPage {
	private static InvMgrScheduleFindLocationPage _instance = null;

	private InvMgrScheduleFindLocationPage() {
	}

	public static InvMgrScheduleFindLocationPage getInstance() {
		if (_instance == null)
			_instance = new InvMgrScheduleFindLocationPage();
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(searchValue());
	}

	private Property[] searchValue() {
		return Property.toPropertyArray(".id", new RegularExpression("LoyaltyFeatureCoverageSearchCriteria-\\d+\\.searchValue",false));
	}
	
	public void selectSearchBy(String searchType) {
		browser.selectDropdownList(
				".id",
				new RegularExpression(
						"LoyaltyFeatureCoverageSearchCriteria-\\d+\\.searchFor",
						false), searchType);
	}

	public void setSearchValue(String value) {
		browser.setTextField(searchValue(), value);
	}

	public void selectLocationCategory(String category) {
		browser.selectDropdownList(".id", new RegularExpression(
				"LoyaltyFeatureCoverageSearchCriteria-\\d+\\.locationCategory",
				false), category);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Go|Search", false));
	}

	public void clickSelect() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Select", false));
	}

	public void searchByLocationName(String location, String locationCategory) {
		selectSearchBy("Location Name");
		if (!StringUtil.isEmpty(location)) {
			setSearchValue(location);
			waitLoading();
		}
		if (!StringUtil.isEmpty(locationCategory)) {
			selectLocationCategory(locationCategory);
		}
		clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public void selectLocation(String location){
		IHtmlObject[] objs = browser.getTableTestObject(".id","lotteryCoverageList_LIST");
		
		IHtmlTable grid = (IHtmlTable)objs[0];
		int expectRow = -1;
		int empty_row_num = 0;
		for(int i=1;i<grid.rowCount();i++){
			String value = grid.getCellValue(i, 2);
			if(value==null){
				empty_row_num++;
				continue;
			}
			if(value.equalsIgnoreCase(location)){
				expectRow = i;
				break;
			}
			if(location.contains("%")){
				expectRow=i;
				break;
			}
		}
		if(expectRow==-1){
			throw new ErrorOnPageException("Not Found Expected Location: "+location);
		}
		Browser.unregister(objs);
		browser.clickGuiObject(".class", "Html.A", ".text",new RegularExpression("Select",false),expectRow-1-empty_row_num);
	}

}
