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
public class InvMgrRedeemScheduleListPage extends
		InvMgrLoyaltyProgramCommonPage {
	private static InvMgrRedeemScheduleListPage _instance = null;

	private InvMgrRedeemScheduleListPage() {
	}

	public static InvMgrRedeemScheduleListPage getInstance() {
		if (_instance == null)
			_instance = new InvMgrRedeemScheduleListPage();
		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(redeemScheduleListTable());
	}

	private Property[] redeemScheduleListTable() {
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

	public void searchByRedeemScheduleId(String schID) {
		selectSearchType("Schedule ID");
		setSearchValue(schID);
		clickSearch();
		ajax.waitLoading();
		waitLoading();
	}

	public void selectScheduleCheckBox(String schdId) {
		browser.selectCheckBox(".value", schdId, true);
	}

	public boolean isRedeemScheduleActive(String scheduleID) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "LoyaltyProgramRedeemScheduleSearchResultGrid_LIST");

		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length - 1];
		if (tableGrid.rowCount() == 1) {
			throw new ErrorOnPageException(
					"Could not find any redeem schedule in schedule list.");
		}

		if (!isRedeemScheduleExists(scheduleID)) {
			throw new ErrorOnPageException("Could not find redeem schedule"
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

	public boolean isRedeemScheduleExists(String redeemScheduleID) {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				redeemScheduleID);
		if (objs.length > 0)
			toReturn = true;

		Browser.unregister(objs);
		return toReturn;
	}
}
