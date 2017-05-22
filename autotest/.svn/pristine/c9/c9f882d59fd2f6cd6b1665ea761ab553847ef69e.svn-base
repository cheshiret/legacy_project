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
public class InvMgrEarnScheduleListPage extends InvMgrLoyaltyProgramDetailsCommonPage {
	private static InvMgrEarnScheduleListPage _instance = null;

	private InvMgrEarnScheduleListPage() {
	}

	public static InvMgrEarnScheduleListPage getInstance() {
		if (_instance == null)
			_instance = new InvMgrEarnScheduleListPage();
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(earnScheduleListTable());
	}

	private Property[] earnScheduleListTable() {
		return Property.toPropertyArray(".id",
				"LoyaltyProgramRedeemScheduleSearchResultGrid");
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");
	}

	/** Click on Active link. */
	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activate");
	}

	public void clickDeactive() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	public void selectSearchType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"LoyaltyFeatureScheduleSearchCriteria-\\d+\\.searchBy", false),
				type);
	}

	public void setSearchValue(String value) {
		browser.setTextField(".id", new RegularExpression(
				"LoyaltyFeatureScheduleSearchCriteria-\\d+\\.searchByValue",
				false), value);
	}

	public void searchByEarnScheduleId(String schID) {
		selectSearchType("Schedule ID");
		setSearchValue(schID);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}

	public void selectScheduleCheckBox(String schdId) {
		browser.selectCheckBox(".value", schdId, true);
	}

	public boolean isEarnScheduleActive(String scheduleID) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "LoyaltyProgramRedeemScheduleSearchResultGrid_LIST");

		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
		if (tableGrid.rowCount() == 1) {
			throw new ErrorOnPageException(
					"Could not find any earn schedule in schedule list.");
		}

		if (!isEarnScheduleExists(scheduleID)) {
			throw new ErrorOnPageException("Could not find earn schedule"
					+ scheduleID + " in schedule list.");
		}

		for (int i = 1; i < tableGrid.rowCount(); i++) {
			String id = tableGrid.getCellValue(i, 1);
			String status = tableGrid.getCellValue(i, 2);
			if (id.equals(scheduleID) && status.equalsIgnoreCase("Yes")) {
				toReturn = true;
				break;
			}
		}

		Browser.unregister(objs);

		return toReturn;
	}
	
	public boolean isEarnScheduleExists(String earnScheduleID) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				earnScheduleID);
		if (objs.length > 0)
			toReturn = true;

		Browser.unregister(objs);
		return toReturn;
	}

}
