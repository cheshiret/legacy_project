/*
 * Created on Nov 3, 2009
 */
package com.activenetwork.qa.awo.pages.orms.inventoryManager.facilityTour;

import com.activenetwork.qa.awo.pages.orms.inventoryManager.InventoryManagerPage;
import com.activenetwork.qa.testapi.ActionFailedException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author vzhao You can access this page from inventory manager top menu by
 *         select Tour Set-up
 */
public class InvMgrFacilityTourPage extends InventoryManagerPage {

	private static InvMgrFacilityTourPage _instance = null;

	public static InvMgrFacilityTourPage getInstance() {
		if (null == _instance) {
			_instance = new InvMgrFacilityTourPage();
		}
		return _instance;
	}

	protected InvMgrFacilityTourPage() {
	}

	public boolean exists() {
		// use Search button as pageMark
//		RegularExpression rex = new RegularExpression("\"Search\"", false);
		RegularExpression rex = new RegularExpression("#link", false); //Lesley[20131015]: update due to Search button href changed in 3.05.00
		return browser.checkHtmlObjectExists(".text", "Search", ".href", rex);
	}

	/**
	 * Select search status from dropdown list.
	 *
	 * @param status
	 */
	public void selectTourSearchStatus(String status) {
		browser.selectDropdownList(".id", "search_first_dropdown", status);
	}

	/**
	 * Select tour search type by given type.
	 *
	 * @param type
	 */
	public void selectTourSearchType(String type) {
		browser.selectDropdownList(".id", "search_second_dropdown", type);
	}

	/**
	 * Fill in tour search value.
	 *
	 * @param value
	 */
	public void setTourSearchValue(String value) {
		browser.setTextField(".id", "search_input", value);
	}


	/** Click on search to do tour searching. */
	public void clickTourSearch() {
//		RegularExpression rex = new RegularExpression("\"Search\"", false);
//		browser.clickGuiObject(".text", "Search", ".href", rex);
		browser.clickGuiObject(".class", "Html.A", ".text", "Search");
	}

	/** Click on Add New link to add a new tour. */
	public void clickTourAddNew() {
		RegularExpression rex = new RegularExpression("\"AddSite\"", false);
		browser.clickGuiObject(".text", "Add New Tour", ".href", rex);
	}

	public void clickComboTourAddNew(){
		RegularExpression rex = new RegularExpression("\"AddSite\"", false);
		browser.clickGuiObject(".text","Add New Combo Tour",".href", rex);
	}

	/**
	 * Search facility for given status by specific type and value.
	 *
	 * @param status
	 *            - tour status
	 * @param searchType
	 *            - search type
	 * @param searchValue
	 *            - search value
	 */
	public void searchTour(String status, String searchType,
			String searchValue) {
		this.selectTourSearchStatus(status);
		this.selectTourSearchType(searchType);
		this.setTourSearchValue(searchValue);
		this.clickTourSearch();
		this.waitLoading();
	}

	/**
	 * Go to tour details page by given tour code.
	 *
	 * @param code
	 *            - tour code
	 */
	public void selectTourByCode(String code) {
		browser.clickGuiObject(".class", "Html.A", ".text", code, true);
	}

	/**
	 * Go to tour details page by given tour name.
	 *
	 * @param name
	 *            - tour name
	 * @return
	 * @throws ItemNotFoundException
	 */
	public String selectTourByName(String name) throws ItemNotFoundException {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int codeCol = table.findColumn(0, "Tour Code");
		int nameCol = table.findColumn(0, "Tour Name");
		int row = table.findRow(nameCol, name);
		if (row < 0)
			throw new ItemNotFoundException("Facility " + name
					+ " was not found.");
		String code = table.getCellValue(row, codeCol).toString();
		Browser.unregister(objs);
		this.selectTourByCode(code);

		return code;
	}

	/**
	 * verify if the result list table is null,if it's null return true,or
	 * return false
	 *
	 * @return
	 */
	public boolean isResultListNull() {
		return !(browser.checkHtmlObjectExists(".id", "row_0_checkbox"));
	}

	/** Click on Activate link. */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Activate", false));
	}

	/** Click on Deactivate link. */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	public void clickComboTour() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Combo Tours");
	}

	public void selectCombo(int index) {
		browser.selectCheckBox(".id", "row_" + index + "_checkbox");
	}

	public int getComboRow(String combo) {
		IHtmlObject[] comboTable = browser.getTableTestObject(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^Tour Code Tour Name Tour Type.*", false));
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

	/**
	 * Active the tour
	 *
	 * @param tourName
	 */
	public void activeTour(String tourName) {
		logger.info("Activate tour - tourCode#" + tourName + ".");
		selectCombo(getComboRow(tourName) - 1);
		clickActivate();
		waitLoading();
	}

	/**
	 * Deactivate a tour by tour name
	 * @param tourName
	 */
	public void deactiveTour(String tourName){
		selectCombo(getComboRow(tourName) - 1);
		this.clickDeactivate();
		waitLoading();
	}

	/**
	 * judge if the tour named by this tour name is avtive or not.
	 * @param tourName
	 * @return
	 */
	public boolean isThisTourActive(String tourName){
		boolean flag=false;
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int col = table.findColumn(0, "Active");
		int row=getComboRow(tourName);

		String active=table.getCellValue(row, col);
		flag=(active.equals("Yes"))?true:false;
		return flag;
	}

	public boolean isTourCodeExist(String tourCode){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", tourCode);
	}

	public void clickTourCode(String tourCode) {
		browser.clickGuiObject(".class", "Html.A", ".text", tourCode);
	}

	/**
	 * if no result,you can get the message through this method
	 *
	 * @return
	 */
	public String getNoMatchMessage() {
		String msg = "";
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id",
				"Messages");
		if (objs.length > 0) {
			msg = objs[0].text();
		}
		Browser.unregister(objs);
		return msg;
	}

	/**
	 * verify if the search flow is successful and no error situation
	 */
	public void verifyTourSearchFlowSuccessOrnot() {
		boolean flag = false;
		String msg = "No Matches Found";
		InvMgrFacilityTourPage searchtour = InvMgrFacilityTourPage
				.getInstance();

		logger.info("Verify wether error ocurred druing tour search ~!");

		if (msg.equalsIgnoreCase(searchtour.getNoMatchMessage())
				&& searchtour.isResultListNull()) {
			flag = true;
		} else if ("".equals(searchtour.getNoMatchMessage())
				&& !(searchtour.isResultListNull())) {
			flag = true;
		}
		if (flag == false) {
			throw new ActionFailedException("Search Error Occured ~! ");
		}
	}

	/**
	 * judge if the status of all record in search list is "Active".
	 *
	 * @return
	 */
	public boolean isAllResultActive() {
		boolean flag = false;

		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int activeCol = table.findColumn(0, "Active");

		int row = table.findRow(activeCol, "No");
		if (row < 0) {
			flag = true;
		}
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * judge if the status of all record in search list is "InActive".
	 *
	 * @return
	 */
	public boolean isAllResultInActive() {
		boolean flag = false;

		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int activeCol = table.findColumn(0, "Active");

		int row = table.findRow(activeCol, "Yes");
		if (row < 0) {
			flag = true;
		}
		Browser.unregister(objs);
		return flag;
	}

	/**
	 * judge if the tour is existent in current page.
	 *
	 * @param status
	 *            (Active/Inactive/All)
	 * @param feild
	 *            (Tour Code/Name/Type,Description)
	 * @param value
	 * @return
	 */
	public boolean isThisTourExist(String status, String feild, String value) {
		boolean flag = false;
		String isActive = "No";

		if (!this.isResultListNull()) {
			IHtmlObject[] objs = browser.getTableTestObject(".text",
					new RegularExpression("^Tour Code.*", false));
			IHtmlTable table = (IHtmlTable) objs[0];

			int col1 = table.findColumn(0, feild);
			int row1 = table.findRow(col1, value);
			if (!status.equalsIgnoreCase("All")) {
				isActive = (status.equalsIgnoreCase("Active")) ? "Yes" : "No";
				int col2 = table.findColumn(0, "Active");
				int row2 = table.findRow(col2, isActive);

				// Because code and name are unique in their column.
				if (feild.equalsIgnoreCase("Tour Code")
						|| feild.equalsIgnoreCase("Tour Name")) {
					// when status is Active or InActive,Field is tour code/name
					if (row1 == row2 && row1 > 0) {
						flag = true;
					}
				} else {
					// when status is not all,and field is tour type or
					// description
					boolean feild_flag = true;// if the value of all row of this
					// column are
					// same,it's true
					boolean status_flag = false;// the value of all row of this
					// column are
					// same according to "Status",it's true
					for (int i = 1; i < table.rowCount(); i++) {
						row1 = table.findRow(col1, value);
						if (row1 < 0) {
							feild_flag = false;
						}
					}
					if (status.equalsIgnoreCase("Active")) {
						if (this.isAllResultActive()) {
							status_flag = true;
						}
					}
					if (feild_flag == true && status_flag == true) {
						flag = true;
					}
				}
			} else {
				// when status is all
				if (feild.equalsIgnoreCase("Tour Code")
						|| feild.equalsIgnoreCase("Tour Name")) {
					// when status is All,Field is tour code/name
					if (row1 > 0) {
						flag = true;
					}
				} else {
					// when status is All,and field is tour type or description

					boolean feild_flag = true;// if the value of all row of this
					// column are
					// same,it's true

					for (int i = 1; i < table.rowCount(); i++) {
						row1 = table.findRow(col1, value);
						if (row1 < 0) {
							feild_flag = false;
						}
					}
					if (feild_flag == true) {
						flag = true;
					}
				}
			}
			Browser.unregister(objs);
		}
		return flag;
	}

	public boolean isSameType_Desc(String type) {
		boolean flag = false;
		String othertype = "";

		String[] types = new String[] { "Hike", "Historic Tour", "Movie Tour",
				"Cave Tour", "Boat Tour", "Symposium" };
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Tour Code.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		int col = table.findColumn(0, "Tour Type");

		for (String s : types) {
			if (!s.equalsIgnoreCase(type)) {
				othertype = s;
			}
		}
		int row = table.findRow(col, othertype);
		if (row < 0) {
			flag = true;
		}
		Browser.unregister(objs);

		return flag;
	}

	/**
	 * verify if the next button is disabled
	 *
	 * @return true-disabled,false- not disabled
	 */
	public boolean isNextDisabled() {
		boolean isdisabled = false;
		isdisabled = !browser.checkHtmlObjectExists(".class", "Html.A",
				".text", "Next");
		return isdisabled;
	}

	public void clickNextbutton() {
		browser.clickGuiObject(".class", ".DIV", ".text", "Next");
	}

	public boolean isFirstButtonDisabled(){
		boolean isdisabled = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", ".DIV", ".text",
				"First");
		if (objs.length > 0) {
			IHtmlObject obj = objs[0];

			isdisabled = !Boolean.parseBoolean(obj.getProperty("isDisabled"));
		}
		Browser.unregister(objs);
		return isdisabled;
	}

	public void clickFirstButton(){
		browser.clickGuiObject(".class", "Html.DIV",".text","First");
	}

	public static void main(String[] args){
		InvMgrFacilityTourPage page=InvMgrFacilityTourPage.getInstance();
		System.out.println(page.isThisTourActive("Auto-test"));
	}

	public void clickTour() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Tours");
	}

	public String getTourCodeByName(String tourName) {
		IHtmlObject[] tables=browser.getTableTestObject(".text", new RegularExpression("^ ?Tour Code ?Tour Name.*",false));
		if(tables==null || tables.length<1){
			throw new ObjectNotFoundException("Can't find Toour list table.");
		}
		IHtmlTable table=(IHtmlTable)tables[0];
		int row=table.findRow(2, tourName);
		String code=table.getCellValue(row, 1);
		Browser.unregister(tables);
		return code;
	}

}
