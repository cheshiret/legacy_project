/*
 * Created on Mar 5, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class InvMgrComboTourPage extends InventoryManagerPage {
	private static InvMgrComboTourPage _instance = null;

	public static InvMgrComboTourPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrComboTourPage();
		}
		return _instance;
	}

	protected InvMgrComboTourPage() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Add New Combo Tour");
	}

	public void clickAddNewComboTour() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add New Combo Tour");
	}

	public void clickCombo(String comboCode) {
		browser.clickGuiObject(".class", "Html.A", ".text", comboCode);
	}

	public int getComboRow(String combo) {
		IHtmlObject[] comboTable = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^( )*Tour Code Tour Name Tour Type.*", false));
		IHtmlTable comboTableGrid = (IHtmlTable) comboTable[0];
		int row = 0;
		for (int i = 0; i < comboTableGrid.rowCount(); i++) {
			for (int j = 0; j < comboTableGrid.columnCount(); j++) {
				if (comboTableGrid.getCellValue(i, j) != null) {
					if (comboTableGrid.getCellValue(i, j).toString().equals(
							combo)) {
						row = i;
						break;
					}
				}
			}
		}
		Browser.unregister(comboTable);
		return row;
	}

	public void selectCombo(int index) {
		browser.selectCheckBox(".id", "row_" + index + "_checkbox");
	}

	public void clickActive() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Activate", false));
	}
	
	public void clickDeactive(){
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Deactivate", false));
	}

	public void selectSearchStatus(String status) {
        browser.selectDropdownList(".id", "search_first_dropdown", status);
	}
	
	public void selectSearchType(String type){
		browser.selectDropdownList(".id", "search_second_dropdown", type);
	}

	public void setSearchValue(String value) {
		browser.setTextField(".id", "search_input", value);
	}
	
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}
	
	public void activeCombo(String combo) {
		logger.info("Activate tour - tourCode#" + combo + ".");
		selectCombo(getComboRow(combo) - 1);
		clickActive();
		waitLoading();
	}
	
	public void searchComboTour(String status, String searchBy, String searchValue) {
		selectSearchStatus(status);
		selectSearchType(searchBy);
		setSearchValue(searchValue);
		clickSearch();
	}
	
	public boolean isComboTourExist(String tourCode){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", tourCode);
	}
}
