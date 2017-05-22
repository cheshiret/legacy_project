/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.SiteInfoData;
import com.activenetwork.qa.awo.pages.orms.operationManager.OpMgrCommonTopMenuPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jdu
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsSiteListPage extends OpMgrCommonTopMenuPage {
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsSiteListPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsSiteListPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsSiteListPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsSiteListPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() { // use "Site# (Name)" label as page mark
//		Property[] p = new Property[3];
//		p[0] = new Property(".class", "Html.A");
//		p[1] = new Property(".text", new RegularExpression(" ?Go", false));
//		p[2] = new Property(".href", new RegularExpression(
//				"\"SearchResultSites.do\",.+\"scrollsearch\"", false));
		// p[2] = new Property(".href", new RegularExpression(
		// "\"SearchResultSites.do\",.+", false));
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class","Html.A",".className","anchor_sort",".text","Site# (Name)"));
	}

	/** Click "Add Selected Item To order" button */
	public void clickAddSelectedItemToOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Selected Items to Order", true);
	}

	/** Click Site Search button */
	public void clickSiteSearchInTrail() {
		// RegularExpression reg=new
		// RegularExpression(".*\"Trail\\.do\",.*\"sitesearchadvanced\".*",false);
		RegularExpression reg = new RegularExpression(
				".*\"Trail\\.do\",.*\"sitesearchadvanced\".*", false);
		browser.clickGuiObject(".class", "Html.A", ".href", reg);
	}

	/** Check Site Search button exist */
	public boolean checkSiteSearchInTrail() {
		RegularExpression reg = new RegularExpression(
				".*\"Trail\\.do\",.*\"sitesearchadvanced\".*", false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".href", reg);
	}

	/**
	 * Retrieve the site location information
	 * 
	 * @param name
	 *            - one of "Facility", "Region" and "Agency"
	 * @return
	 */
	public String getSiteLocationInfo(String name) {
		RegularExpression reg = new RegularExpression("Facility.*Region.*Agency",
				false);
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.TABLE");
		p[1] = new Property(".text", reg);
		IHtmlObject[] objs = browser.getHtmlObject(p);
		String toReturn = "";
		if (name.equalsIgnoreCase("Facility"))
			toReturn = ((IHtmlTable) objs[1]).getCellValue(0, 2);
		else if (name.equalsIgnoreCase("Region"))
			toReturn = ((IHtmlTable) objs[1]).getCellValue(0, 3);
		else if (name.equalsIgnoreCase("Agency"))
			toReturn = ((IHtmlTable) objs[1]).getCellValue(0, 4);
		else
			throw new ItemNotFoundException("Unknown field name: " + name);

		Browser.unregister(objs);

		return toReturn;
	}

	/** Select site */
	public void selectSite() {
		selectSite(null, null);
	}

	/**
	 * Select one site and input numofstay
	 * 
	 * @param numOfStay
	 */
	public void selectSite(String numOfStay) {
		selectSite(null, numOfStay);
	}

	/**
	 * Select one site and input
	 * 
	 * @param siteName
	 * @param numOfStay
	 */
	public void selectSite(String siteName, String numOfStay) {
		selectSite(siteName, null, numOfStay);
	}

	/**
	 * Select qty of one site
	 * 
	 * @param index
	 * @param qty
	 */
	public void selectQty(int index, String qty) {
		browser.selectDropdownList(".id", "qty_" + index, qty);
	}

	/**
	 * Check the site check box for a site
	 * 
	 * @param index
	 */
	public void selectCheckBox(int index) {
		browser.selectCheckBox(".id", "site_row_" + index);
	}

	/** Check whether sitecheckbox exist */
	private boolean isCheckBox() {
		RegularExpression reg = new RegularExpression("site_row_[0-9]+", false);
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", reg);
	}

	/** Add a specified site to the reservation order */
	public String selectSite(String siteNumber, String fromDate,
			String numOfStay) {
		if (isCheckBox()) {
			return selectSiteWithCheckBox(siteNumber, fromDate, numOfStay);
		} else {
			return selectSiteWithRadioButton(siteNumber, fromDate, numOfStay);
		}
	}

	public String getSiteID(String siteNumber) {
		IHtmlObject[] siteLink = browser.getHtmlObject(".class", "Html.A",
				".text", siteNumber);
		String siteID = RegularExpression.getMatches(siteLink[0].getProperty(
				".href").toString(), "\"[0-9]+\"")[0].replaceAll("\"", "");
		Browser.unregister(siteLink);
		return siteID;
	}

	public IHtmlObject[] getSiteListTable() {
		return browser.getTableTestObject(".class", "Html.TABLE", ".text",
				new RegularExpression("^\\W*Site# \\(Name\\).*", false));
	}

	/**
	 * Select one site with radio button
	 * 
	 * @param siteNumber
	 * @param fromDate
	 * @param numOfStay
	 * @return
	 */
	private String selectSiteWithRadioButton(String siteNumber,
			String fromDate, String numOfStay) {
		if (siteNumber == null || siteNumber.length() < 1) {
			return selectSiteWithRadioButton(0);
		}

		// RegularExpression siteReg = new
		// RegularExpression("\"SiteDetailsInv.do\"", false);
		// TestObject[] objs = browser.getGuiTestObject(".class",
		// "Html.A",".href", siteReg);
		// if (objs.length < 1)
		// throw new ItemNotFoundException("Failed to find the link for Site#"+
		// siteNumber);
		//
		// //find the index of site
		// int index = 0;
		// String siteID = "0000";
		// boolean found = false;
		// while (!found) {
		// if (objs[index].getProperty(".text").toString().equalsIgnoreCase(
		// siteNumber)) {
		// siteID = MiscFunctions.matches(objs[index].getProperty(".href")
		// .toString(), "\"[0-9]+\"")[0].replaceAll("\"", "");
		// found = true;
		// } else {
		// index++;
		// if (index >= objs.length) {
		// Browser.unregister(objs);
		// throw new ItemNotFoundException(
		// "Failed to find the link for Site#" + siteNumber);
		// }
		// }
		// }
		//
		// Browser.unregister(objs);
		//
		// //get the corresponding radio button
		// objs = browser.getGuiTestObject(".class",
		// "Html.INPUT.radio",".classIndex", index + "");
		// if (((IRadioButton) objs[0]).isSelected() == false)
		// ((IRadioButton) objs[0]).select();
		//
		// if (fromDate != null && fromDate.length() > 0)
		// browser.setTextField(".id", "row_" + index + "_date_start",fromDate);
		// if (numOfStay != null && numOfStay.length() > 0)
		// browser.setTextField(".id", "row_" + index + "_nights", fromDate);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				siteNumber);
		while (this.hasNext() && objs.length < 1) {
			this.gotoNext();
			objs = browser.getHtmlObject(".class", "Html.A", ".text",
					siteNumber);
		}
		if (objs.length < 1) {
			throw new RuntimeException("Site#" + siteNumber	+ " was not found.");
		}

		IHtmlObject[] tables = this.getSiteListTable();

		IHtmlTable table = (IHtmlTable) tables[0];

		int col = table.findColumn(0, "Site# (Name)");
		int row = table.findRow(col, siteNumber);
		if (col == -1) {
			throw new ItemNotFoundException(
					"Failed to find the column \"Site# \\(Name\\))\"");
		}

		if (row == -1) {
			throw new ItemNotFoundException("Failed to find the row for site#"
					+ siteNumber);
		}

		IHtmlObject[] siteLink = browser.getHtmlObject(".class", "Html.A",
				".text", table.getCellValue(row, col), table);
		String siteID = RegularExpression.getMatches(siteLink[0].getProperty(
				".href").toString(), "\"[0-9]+\"")[0].replaceAll("\"", "");
		IHtmlObject[] radio = browser.getHtmlObject(".class",
				"Html.INPUT.radio", ".id", "selected_site_index", table);
		((IRadioButton) radio[row - 1]).select();
		Browser.unregister(objs);
		Browser.unregister(radio);
		Browser.unregister(siteLink);
		Browser.unregister(tables);

		return siteID;
	}

	/**
	 * Select site with radio button
	 * 
	 * @param index
	 *            --- The index of the radio button, start from 0
	 * @return
	 */
	private String selectSiteWithRadioButton(int index) {
		// RegularExpression siteReg = new
		// RegularExpression("\"SiteDetailsInv.do\"", false);
		// TestObject[] objs = browser.getGuiTestObject(".class",
		// "Html.A",".href", siteReg);
		// if (objs.length < index + 1)
		// throw new ItemNotFoundException(
		// "Failed to find the link for Site at index" + index);
		//
		// //find the index of site
		// String siteID =
		// MiscFunctions.matches(objs[index].getProperty(".href").toString(),
		// "\"[0-9]+\"")[0].replaceAll("\"", "");
		//
		// //get the corresponding radio button
		// objs = browser.getGuiTestObject(".class",
		// "Html.INPUT.radio",".classIndex", index + "");
		// if (((IRadioButton) objs[0]).isSelected() == false)
		// ((IRadioButton) objs[0]).select();

		IHtmlObject[] tables = this.getSiteListTable();

		IHtmlTable table = (IHtmlTable) tables[0];

		int col = table.findColumn(0, "Site# (Name)");
		int row = index + 1;

		IHtmlObject[] siteLink = browser.getHtmlObject(".class", "Html.A",
				".text", table.getCellValue(row, col), table);
		String siteID = RegularExpression.getMatches(siteLink[0].getProperty(
				".href").toString(), "\"[0-9]+\"")[0].replaceAll("\"", "");
		IHtmlObject[] radio = browser.getHtmlObject(".class",
				"Html.INPUT.radio", ".id", "selected_site_index", table);
		((IRadioButton) radio[row]).select();
		Browser.unregister(radio);
		Browser.unregister(siteLink);
		Browser.unregister(tables);

		return siteID;
	}

	/**
	 * Select site with checkbox
	 * 
	 * @param siteNumber
	 * @param fromDate
	 * @param numOfStay
	 * @return
	 */
	private String selectSiteWithCheckBox(String siteNumber, String fromDate,
			String numOfStay) {
		// get site link object
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				siteNumber);
		String siteID = objs[0].getProperty(".href").toString().split("\"")[7];
		// String siteID=MiscFunctions.matches(text,
		// "\"[0-9]+\"")[0].replaceAll("\"", "");
		Browser.unregister(objs);

		// get corresponding checkbox
		objs = browser.getHtmlObject(".class", "Html.INPUT.checkbox", ".value",
				siteID);
		if (((ICheckBox) objs[0]).isSelected() == false)
			((ICheckBox) objs[0]).click();

		String index = RegularExpression.getMatches(objs[0].getProperty(".id")
				.toString(), "[0-9]+")[0];
		Browser.unregister(objs);

		// set arrive date if possible
		if (fromDate != null && fromDate.length() > 0)
			browser.setTextField(".id", "row_" + index + "_date_start",
					fromDate);

		// set night number if possible
		if (numOfStay != null && numOfStay.length() > 0)
			browser.setTextField(".id", "row_" + index + "_nights", numOfStay);

		return siteID;
	}

	/**
	 * Go to site detail page according to prdCD
	 * 
	 * @param prdCD
	 * @return
	 */
	public String goSiteDetail(String prdCD) {
		if (prdCD.equals("")) {
			return goFirstSiteDetail();
		} else {
			// TODO: there is a issue here, it will occur if the needed site is
			// on the page 2
			IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",
					".text", prdCD);
			while (objs.length < 1 && this.hasNext()) {
				this.gotoNext();
				objs = browser
						.getHtmlObject(".class", "Html.A", ".text", prdCD);
			}

			if (objs.length < 1) {
				Browser.unregister(objs);
				throw new RuntimeException("Site#" + prdCD
						+ " was not found. Please verify inventory");
			} else if (objs.length > 1) {
				Browser.unregister(objs);
				throw new RuntimeException("Ambigous objects recogintion.");
			}
			IHtmlObject guiObj = objs[0];
			String siteID = guiObj.getProperty(".href").toString().split("\"")[7];
			guiObj.click();

			Browser.unregister(objs);
			return siteID;
		}
	}

	/**
	 * select a parent site with multiple child sites to order. This is
	 * applicable for NSS and NSQ sites
	 * 
	 * @param siteNumber
	 * @param siteQty
	 * @return - the site ID for the given site number
	 */
	public String selectSitesToOrder(String siteNumber, String siteQty) {
		int rowNum = this.getRowNumOfSite(siteNumber);
		this.selectCheckBox(rowNum - 1);

		if (Integer.parseInt(siteQty) > 1) {
			this.selectQty(rowNum - 1, siteQty);
		}
		String siteID = this.getSiteID(siteNumber);
		this.clickAddSelectedItemToOrder();

		return siteID;
	}

	/**
	 * 
	 * @param index
	 * @return
	 */
	public String selectSiteToOrder(int index) {
		String siteNumber = this.getSiteNumber(index);
		String siteID = this.selectSite(siteNumber, null, null);
		this.clickAddSelectedItemToOrder();

		return siteID;
	}

	/**
	 * Add site to cart page
	 * 
	 * @param siteNumber
	 * @return--Return the selected ID
	 */
	public String selectSiteToOrder(String siteNumber) {
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();
		String siteID = this.selectSite(siteNumber, null, null);
		this.clickAddSelectedItemToOrder();

		if (confirm.exists()) {
			confirm.clickOK();
		}

		return siteID;
	}

	/**
	 * Select the first available site and go to site details page
	 * 
	 * @return
	 */
	public String goFirstSiteDetail() {
		RegularExpression regex = new RegularExpression("SiteDetailsInv.do",
				false);

		IHtmlObject[] objs = browser.getHtmlObject(".href", regex, ".class",
				"Html.A");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}

		IHtmlObject guiObj = objs[0];
		String siteID = guiObj.getProperty(".href").toString().split("\"")[7];
		guiObj.click();

		Browser.unregister(objs);

		return siteID;
	}

	/** Select the last site and go to site detail page */
	public String goLastSiteDetail() {
		RegularExpression regex = new RegularExpression("SiteDetailsInv.do",
				false);

		IHtmlObject[] objs = browser.getHtmlObject(".href", regex, ".class",
				"Html.A");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}

		IHtmlObject guiObj = objs[objs.length - 1];
		String siteID = guiObj.getProperty(".href").toString().split("\"")[7];
		guiObj.click();

		Browser.unregister(objs);

		return siteID;
	}

	public String getSiteNumber(int index) {
		RegularExpression regex = new RegularExpression("SiteDetailsInv.do",
				false);

		IHtmlObject[] objs = browser.getHtmlObject(".href", regex, ".class",
				"Html.A");
		if (objs.length < 1) {
			throw new RuntimeException("Object not found.");
		} else if (index >= objs.length) {
			throw new ItemNotFoundException("Index " + index
					+ " is out of boundary of" + (objs.length - 1));
		}

		IHtmlObject guiObj = objs[index];
		// MiscFunctions.dumpProperty(guiObj);

		String toReturn = guiObj.getProperty(".text").toString();

		Browser.unregister(objs);

		return toReturn;
	}

	/** Get last site ID */
	public String getLastSiteNumber() {
		RegularExpression regex = new RegularExpression("SiteDetailsInv.do",
				false);

		IHtmlObject[] objs = browser.getHtmlObject(".href", regex, ".class",
				"Html.A");
		if (objs.length < 1) {
			throw new RuntimeException("Object not found.");
		}

		IHtmlObject guiObj = objs[objs.length - 1];
		// MiscFunctions.dumpProperty(guiObj);

		String toReturn = guiObj.getProperty(".href").toString().split("\"")[7];

		Browser.unregister(objs);

		return toReturn;
		// javascript:invokeActionTarget(%20"SiteDetailsInv.do",%20"details",%20"SiteSearchWorker",%20"6074",%20"transaction"%20%20)
		// guiObj.click();
	}

	/**
	 * Get all site IDs
	 * 
	 * @return
	 */
	public List<String> getAllSiteIDs() {
		List<String> ids = new ArrayList<String>();
		do {
			List<String> data = getSiteIDs();
			ids.addAll(data);
		} while (gotoNext());

		return ids;
	}

	/**
	 * Get all site IDs in one page
	 * 
	 * @return
	 */
	public List<String> getSiteIDs() {
		RegularExpression regex = new RegularExpression("SiteDetailsInv.do",
				false);

		IHtmlObject[] objs = browser.getHtmlObject(".href", regex, ".class",
				"Html.A");
		List<String> v = new ArrayList<String>();

		for (int i = 0; i < objs.length; i++) {
			String id = objs[i].getProperty(".href").toString().split("\"")[7];
			v.add(id);
		}

		Browser.unregister(objs);
		return v;
	}

	/** Get All Site Number */
	public List<String> getAllSiteNumbers() {
		return this.getAllSiteAttributeValues("Site# (Name)");
	}

	/** Get First page Site Number */
	public List<String> getFirstPageSiteNumbers() {
		return this.getFistPageSiteAttributeValues("Site# (Name)");
	}

	/** Check whether "next" button exist */
	public boolean hasNext() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Next");
	}

	/**
	 * Check whether "Next" Button exist, if exist, click it
	 * 
	 * @return
	 */
	public boolean gotoNext() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Next");

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Check whether the page has previous button
	 * 
	 * @return
	 */
	public boolean hasPrevious() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Previous");
	}

	/**
	 * Check whether the page has first button
	 * 
	 * @return
	 */
	public boolean gotoFirst() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"First");

		boolean toReturn = false;
		if (objs.length > 0) {
			toReturn = true;
			objs[0].click();
		}
		Browser.unregister(objs);

		this.waitLoading();

		return toReturn;
	}

	/**
	 * Check whether get searched result
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id",
				"sitesearch.site.notfound");
	}

	/**
	 * Retrieve the site information for the given site name
	 * 
	 * @param siteName
	 *            - the site name to found
	 * @return - the List<String> of site information found
	 */
	public List<String> getSiteInfo(String siteName) {
		IHtmlObject[] objs = this.getSiteListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		int columnIndex = table.findColumn(0, "Site# \\(Name\\)");
		int rowIndex = table.findRow(columnIndex, siteName);
		List<String> siteInfo = table.getRowValues(rowIndex);
		Browser.unregister(objs);
		return siteInfo;

	}

	/**
	 * Retrieve the site information for the given row index
	 * 
	 * @param rowIndex
	 *            - the row index.
	 * @return - the List<String> of data found
	 */
	public List<String> getSiteInfo(int rowIndex) {
		IHtmlObject[] objs = this.getSiteListTableObject();

		List<String> siteInfo = ((IHtmlTable) objs[0]).getRowValues(rowIndex);
		Browser.unregister(objs);
		return siteInfo;
	}

	/**
	 * Navigate through all result pages to retrieve all values of the
	 * corresponding column.
	 * 
	 * @param columnName
	 *            - the column name to search
	 * @return - the List<String> of values found
	 */
	public List<String> getAllSiteAttributeValues(String columnName) {
		List<String> attributes = new ArrayList<String>();
		do {
			List<String> data = getSiteAttributeValues(columnName);
			attributes.addAll(data);
		} while (gotoNext());

		return attributes;
	}

	/**
	 * Navigate through first result page to retrieve all values of the
	 * corresponding column.
	 * 
	 * @param columnName
	 *            - the column name to search
	 * @return - the List<String> of values found
	 */
	public List<String> getFistPageSiteAttributeValues(String columnName) {
		List<String> attributes = new ArrayList<String>();
		List<String> data = getSiteAttributeValues(columnName);
		attributes.addAll(data);

		return attributes;
	}

	/**
	 * Retrieve all values of the corresponding column in the search result
	 * table of current page
	 * 
	 * @param table
	 *            - the ITable object to search from
	 * @param columnName
	 *            - the column name to search
	 * @return - the List<String> of data found
	 */
	public List<String> getSiteAttributeValues(IHtmlTable table,
			String columnName) {
		if (columnName.equalsIgnoreCase("Arrival"))
			return getArrivalDates();
		else if (columnName.matches("Nights|Days"))
			return getNights();

		int columnIndex = table.findColumn(0, columnName);
		List<String> data = table.getColumnValues(columnIndex);
		data.remove(0);

		return data;
	}

	/**
	 * Retrieve all values of the corresponding column in the search result
	 * table of current page
	 * 
	 * @param columnName
	 *            - the column name to search
	 * @return - the List<String> of data found
	 */
	public List<String> getSiteAttributeValues(String columnName) {
		if (columnName.equalsIgnoreCase("Arrival"))
			return getArrivalDates();
		else if (columnName.matches("Nights|Days"))
			return getNights();
		else if (columnName.matches("Area"))
			return getAreas();

		IHtmlObject[] objs = getSiteListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> data = getSiteAttributeValues(table, columnName);

		Browser.unregister(objs);

		return data;
	}

	public String getSiteValue(String siteNum, String colName) {
		IHtmlObject[] objs = getSiteListTableObject();

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(1, siteNum);
		int columnIndex = table.findColumn(0, colName);

		String value = table.getCellValue(row, columnIndex);
		Browser.unregister(objs);
		return value;
	}

	public String getSiteAviliableQty(String id) {
		IHtmlObject[] objs = browser
				.getHtmlObject(".class", "Html.A", ".id", id);
		String qty = objs[0].getProperty("qty");
		return qty;

	}

	/**
	 * Retrieve the site arrival date of all sites in the site list table
	 * 
	 * @return - the List<String> of arrival date string found
	 */
	public List<String> getArrivalDates() {
		List<String> dates = new ArrayList<String>();

		RegularExpression reg = new RegularExpression(
				"^row_[0-9]+_date_start$", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", reg);

		for (int i = 0; i < objs.length; i++)
			dates.add(objs[i].getProperty(".value").toString());

		Browser.unregister(objs);
		return dates;
	}

	/**
	 * Retrieve the site number of all sites in the site list table
	 * 
	 * @return - List<String> of site numbers found
	 */
	public List<String> getNights() {
		List<String> nights = new ArrayList<String>();

		RegularExpression reg = new RegularExpression("^row_[0-9]+_nights$",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", reg);

		for (int i = 0; i < objs.length; i++)
			nights.add(objs[i].getProperty(".value").toString());

		Browser.unregister(objs);
		return nights;
	}

	/**
	 * Retrieve the Area of all sites in the site list table
	 * 
	 * @return - List<String> of site Area found
	 */
	public List<String> getAreas() {
		List<String> areas = new ArrayList<String>();
		IHtmlObject[] objs = this.getSiteListTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[0];
		for (int i = 1; i < tableGrid.rowCount() - 1; i++) {
			areas.add(tableGrid.getColumnValues(8).get(i));
		}

		Browser.unregister(objs);
		return areas;
	}

	/**
	 * Parse the site list table and put the table information into a 2-d String
	 * array
	 * 
	 * @return - the 2-d String array contains the Site List table information
	 */
	public String[][] parseSiteListTable() {
		IHtmlObject[] objs = getSiteListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		int columnSize = table.columnCount();
		int rowSize = table.rowCount();
		String[][] siteTable = new String[rowSize][columnSize];

		for (int r = 0; r < rowSize; r++) {
			for (int c = 0; c < columnSize; c++) {
				Object value = table.getCellValue(r, c);
				if (value == null || value.toString().length() < 1)
					value = "null";

				siteTable[r][c] = value.toString();
			}
		}
		Browser.unregister(objs);

		return siteTable;
	}

	/**
	 * Retrieve the Site list table TestObject
	 * 
	 * @return - TestObject array found
	 */
	private IHtmlObject[] getSiteListTableObject() {
		RegularExpression reg = new RegularExpression(
				"^\\W*Site# \\(Name\\).*|^Site#.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", reg);
		return objs;
	}

	/**
	 * Verify the site search result with the siter search <param> criteria
	 * 
	 * @param criteria
	 */
	public void verifySiteSearchResult(SiteInfoData criteria) {
		if (criteria.parkName.length() > 0) {
			String pn = getSiteLocationInfo("Facility").toLowerCase();
			if (!pn.endsWith(criteria.parkName.toLowerCase()))
				throw new ItemNotFoundException("Actual " + pn
						+ " doesn't match the expected " + criteria.parkName);
		}

		if (criteria.arrivalDate.length() > 0) {
			List<String> dates = this.getArrivalDates();
			java.util.Date expected = DateFunctions
					.parseDateString(criteria.arrivalDate);
			for (int i = 0; i < dates.size(); i++) {
				java.util.Date actual = DateFunctions.parseDateString(dates
						.get(i).toString());

				if (actual.compareTo(expected) != 0)
					throw new ItemNotFoundException("#" + i
							+ " site's actual arrival date: " + dates.get(i)
							+ " is not the same date as the expected "
							+ criteria.arrivalDate);
			}
		}

		if (criteria.dayNightNum.length() > 0) {
			List<String> nights = this.getNights();
			for (int i = 0; i < nights.size(); i++) {
				if (!criteria.dayNightNum.equalsIgnoreCase(nights.get(i)
						.toString()))
					throw new ItemNotFoundException("#" + i
							+ " site's actual day/nights: " + nights.get(i)
							+ " is not the same as the expected "
							+ criteria.dayNightNum);
			}
		}

		IHtmlObject[] table = this.getSiteListTableObject();
		IHtmlTable tableGrid = (IHtmlTable) table[0];

		if (null != criteria.siteName && !criteria.siteName.equals("")) {
			List<String> sn = getSiteAttributeValues(tableGrid, "Site# (Name)");
			for (int i = 0; i < sn.size(); i++) {
				if (!sn.get(i).toString().toLowerCase().matches(
						criteria.siteName.toLowerCase()))
					throw new ItemNotFoundException("Actual siteName: " + sn
							+ " doesn't match the expected "
							+ criteria.siteName);
			}
		}

		// if(criteria.electricityHookup.length()>0 &&
		// !criteria.electricityHookup.equalsIgnoreCase("none")){
		if (null != criteria.electricityHookup
				&& !criteria.electricityHookup.equals("")
				&& !criteria.electricityHookup.equalsIgnoreCase("none")) {
			List<String> eh = getSiteAttributeValues(tableGrid, "Elec");
			for (int i = 0; i < eh.size(); i++) {
				int amp = StringUtil.getDigits(eh.get(i).toString());
				if (amp < Integer.parseInt(criteria.electricityHookup
						.toLowerCase()))
					throw new ItemNotFoundException("the #" + i
							+ " amp value: " + amp
							+ " is smaller than the expected "
							+ criteria.electricityHookup);
			}
		}

		// if(criteria.areaName.length()>0) {
		if (null != criteria.areaName && !criteria.areaName.equals("")) {
			List<String> an = this.getSiteAttributeValues(tableGrid, "Area");
			for (int i = 0; i < an.size(); i++) {
				if (criteria.areaName.equalsIgnoreCase(an.get(i).toString()))
					throw new ItemNotFoundException("the #" + i
							+ " site's area: " + an.get(i)
							+ " is different from the expected "
							+ criteria.areaName);
			}
		}

		Browser.unregister(table);
	}

	/**
	 * Get status message
	 * 
	 * @return
	 */
	public String getStatusMessages() {
		String msg = "";
		IHtmlObject[] objs = browser.getTableTestObject(".id", "statusMessages");
		if (objs.length > 0) {
			msg = objs[0].getProperty(".text").toString();
		}

		Browser.unregister(objs);
		return msg;
	}

	/**
	 * Check whether the searched result exist
	 * 
	 * @return
	 */
	public boolean isSearchResultEmpty() {
		IHtmlObject[] table = this.getSiteListTableObject();
		int row = ((IHtmlTable) table[0]).rowCount();
		Browser.unregister(table);

		return row < 2;
	}

	/**
	 * Set arrival date
	 * 
	 * @param arrival
	 */
	public void setArrival(String arrival) {
		arrival = arrival.replace("/", "-");
		RegularExpression reg = new RegularExpression(
				"siteSearch_date_start_ForDisplay|startDateSearch_ForDisplay",
				false);
		browser.setTextField(".id", reg, arrival);
	}

	/**
	 * Set departure date
	 * 
	 * @param departure
	 */
	public void setDeparture(String departure) {
		departure = departure.replace("/", "-");
		browser
				.setTextField(".id", "siteSearch_date_end_ForDisplay",
						departure);
	}

	/**
	 * Input site search night
	 * 
	 * @param nights
	 */
	public void setSiteSearchNights(String nights) {
		browser.setTextField(".id", "siteSearch_nights", nights);
	}

	/**
	 * Input site number
	 * 
	 * @param siteNum
	 */
	public void setSiteNumber(String siteNum) {
		browser.setTextField(".id", "siteNumbersSearch", siteNum);
	}

	/**
	 * Select area or loop
	 * 
	 * @param area
	 */
	public void selectAreaLoop(String area) {
		browser.selectDropdownList(".id", "loopID", area);
	}

	/** Select show reservablonly checkbox */
	public void selectShowReservableOnlyCheckBox() {
		browser.selectCheckBox(".id", "reservableOnlySearch");
	}

	/** don't select show reservablonly checkbox */
	public void deSelectShowReservableOnlyCheckBox() {
		browser.unSelectCheckBox(".id", "reservableOnlySearch");
	}

	/**
	 * This method used to check weekly price column exists
	 * 
	 * @return
	 */
	public boolean checkWeeklyPriceFlagExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Weekly ($)*");
	}

	/**
	 * This method used to check given note message displayed
	 * 
	 * @param note
	 * @return
	 */
	public boolean checkRateNotesExists(String note) {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				note);
	}

	/** Click Go button */
	public void clickGo() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("^(Go|Search)$", false));
		p[2] = new Property(".href", new RegularExpression(
				"\"SearchResultSites.do\",.+\"scrollsearch\"", false));
		browser.clickGuiObject(p);
	}

	/** Click Last button */
	public void clickLast() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Last", false));
		p[2] = new Property(".href", new RegularExpression(
				"\"SearchResultSites.do\",.+\"PagingWorker\"", false));
		browser.clickGuiObject(p);
	}

	/** Check Last button available */
	public boolean checkLast() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Last", false));
		p[2] = new Property(".href", new RegularExpression(
				"\"SearchResultSites.do\",.+\"PagingWorker\"", false));
		return browser.checkHtmlObjectExists(p);
	}

	/** Click Next button available */
	public void clickNext() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", new RegularExpression("Next", false));
		p[2] = new Property(".href", new RegularExpression(
				"\"SearchResultSites.do\",.+\"PagingWorker\"", false));
		browser.clickGuiObject(p);
	}

	public boolean checkAreaOrLoopExit() {
		return browser.checkHtmlObjectExists(".id", "loopID");
	}

	public String getDropDownListDefaultValue(String property) {
		return browser.getDropdownListValue(".id", property, 0);
	}

	public List<String> getAllLoopIDs() {
		return browser.getDropdownElements(".id", "loopID");
	}

	/**
	 * This method only special date's availability to compare with what we
	 * retrieve from web. it will only add site number, loop and two weeks site
	 * availability to returned List<String>.
	 * 
	 * @return siteInfo - site availability info
	 */
	public List<String> getSiteAvailability() {
		IHtmlObject[] objs = getSiteListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> siteInfo = new ArrayList<String>();
		String cell = "";
		int size = table.columnCount();
		for (int i = 1; i < size; i++) {
			if (siteInfo.size() < 16) {
				cell = table.getCellValue(1, i).toString();
				if (i == 1)
					siteInfo.add(cell);// get site num
				if (i == 8)
					siteInfo.add(cell);// get loop name
				if (i > 11)
					siteInfo.add(cell);// get two weeks from today
			} else {
				break;
			}
		}

		Browser.unregister(objs);
		return siteInfo;
	}

	/**
	 * if the site is not availabe, check whether the warning informaion
	 * displays.
	 * 
	 * @return
	 */
	public boolean isSiteAvaWarnExist() {
		return browser.checkHtmlObjectExists(".id",
				"Site(s) is not available for selected dates.");
	}

	/** Select "AddAsSplitStay" checkbox */
	public void selectAddAsSplitStay() {
		browser.selectCheckBox(".id", "AddAsSplitStay");
	}

	/** Click Find Split Stay button */
	public void clickFindSplitStay() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Find Split Stay");
	}

	/** Click the "Add Selected Items to Order" link */
	public void clickSelectedItemsToOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Selected Items to Order", true);
	}

	/**
	 * Check whether specific date is available or not
	 * 
	 * @param date
	 * @return
	 */
	public boolean checkSpecDateAvailable(String date) {
		boolean exist = false;
		IHtmlObject[] obj = browser.getHtmlObject(".id", date);

		if (obj.length > 0) {
			exist = true;
		}

		return exist;

	}

	/**
	 * Select spec date for one site in site list page
	 * 
	 * @param date
	 */
	public void selectSpecDate(String date) {
		browser.clickGuiObject(".id", date);
	}

	/**
	 * check whether the cancel button exist
	 * 
	 * @return
	 */
	public boolean isCancelbuttonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Cancel");
	}

	/**
	 * Get alert message in the site list page
	 * 
	 * @return
	 */
	public String getAlertMsgInSiteLstPg() {
		IHtmlObject[] obj = browser.getHtmlObject(".id",
				"sitesearch.site.notfound");
		String alertMess = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);

		return alertMess;
	}

	/**
	 * Get the rule message
	 * 
	 * @return rules warning message
	 */
	public String getRulesWarningMessage() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", new RegularExpression(
				"V-100002|V-100004|com.reserveamerica.common.BaseEx", false));
		String rulesMessage = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);

		return rulesMessage;
	}

	/**
	 * Get specific property value
	 * 
	 * @param rowNum
	 * @param propertyName
	 *            : title, src, href
	 * @param propertyValue
	 * 
	 * @return
	 */
	public String getImgAtrributeValue(int rowNum, String propertyName,
			String propertyValue) {
		IHtmlObject[] tables=browser.getTableTestObject(".text", new RegularExpression("Site.*\\(Name\\).*",false));
		String imgAttribTitleValue = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", "."
				+ propertyName, new RegularExpression(".*" + propertyValue
				+ ".*", false),tables[0]);
		imgAttribTitleValue = objs[rowNum].getProperty("." + propertyName)
				.toString();
		Browser.unregister(objs);
		return imgAttribTitleValue;
	}
	
//	public String getImagAtrrituteValue(int rowNum,String propertyName,String propertyValue,String expectedPropertyName){
//		String imgAttribTitleValue = "";
//		HtmlObject[] objs = browser.getHtmlObject(".class", "Html.IMG", "."
//				+ propertyName, new RegularExpression(".*" + propertyValue
//				+ ".*", false));
//		imgAttribTitleValue = objs[rowNum].getProperty("." + expectedPropertyName)
//				.toString();
//		Browser.unregister(objs);
//		return imgAttribTitleValue;
//	}
	
	/**
	 * get the row number of site what need to be selected
	 * 
	 * @param - the site number of site want to select
	 * @return
	 */
	public int getRowNumOfSite(String siteNumber) {
		
		IHtmlObject tableObj[] = browser.getTableTestObject(".text",
				new RegularExpression(siteNumber, false));
		int rowNum = 0;

		if (tableObj.length > 0) {
			rowNum = ((IHtmlTable) tableObj[0]).findRow(1, siteNumber);
		}
		Browser.unregister(tableObj);

		return rowNum;
	}

	/**
	 * verify the Search Options in site search page
	 * 
	 * @param showOption
	 * @param defaultSelect
	 * @param i
	 *            : 0:showReservableOnly, 1:showReservableOrLotteryOnly,
	 *            2:showAll
	 */
	public void verifySearchOption(boolean showOption, boolean defaultSelect,
			int i) {
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", "reservableOnlySearch");
		String searchOption = "";

		if (i == 0) {
			searchOption = "Show Reservable Only";
		}
		if (i == 1) {
			searchOption = "Show Reservable/Lottery Only";
		}
		if (i == 2) {
			searchOption = "Show All";
		}

		if (showOption) {
			if (!objs[i].exists()) {
				throw new ErrorOnDataException("The radio button"
						+ searchOption + "should exit.");
			} else if (defaultSelect) {
				if (objs[i].getAttributeValue(".checked") == null) {
					throw new ErrorOnDataException("The radio button"
							+ searchOption + "should be selected.");
				}
			}
		} else if (objs.length > 0) {
			throw new ErrorOnDataException("The radio button" + searchOption
					+ "should not exit.");
		}
		Browser.unregister(objs);
	}

	/**
	 * Get the total number of walk up days of site
	 * 
	 * @return
	 */
	public int getSiteWalkInDays() {
		IHtmlObject objs[] = browser.getTableTestObject(".text",
				new RegularExpression("Site# \\(Name\\)", false));

		String flag = "";
		int amount = 0;
		for (int i = 0; i < 31; i++) {
			flag = ((IHtmlTable) objs[0]).getCellValue(1, 12 + i);
			if (flag.equalsIgnoreCase("w")) {
				amount += 1;
			}
		}

		return amount;
	}

	/**
	 * Search site at the Site List page
	 * 
	 * @param site
	 */
	public void searchSite(SiteInfoData site) {
		logger.info("Search site in Site List page.");

		this.setArrival(site.arrivalDate);
		this.setDeparture(site.departDate);
		// this.setSiteSearchNights(site.dayNightNum);
		this.setSiteNumber(site.siteNumber);
		this.selectAreaLoop(site.areaName);
		this.selectShowReservableOnlyCheckBox();
		this.clickGo();

//		this.waitExists();
	}

	/**
	 * Get a site's status of a specific day
	 * 
	 * @param arrivalDate
	 * @return
	 */
	public String getSiteStatus(String arrivalDate) {
//		HtmlObject objs[] = browser.getTableTestObject(".text",new RegularExpression("Site# (Name)", false));
		IHtmlObject objs[] = browser.getTableTestObject(".text",new RegularExpression("^Site.*", false));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateFunctions.parseDateString(arrivalDate));

		int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
		String weekDays[] = { "S", "M", "T", "W", "T", "F", "S" };

//		String dateFlag = arrivalDate.split("/")[1].trim() + " "	+ weekDays[weekIndex - 1];
		
//     need to modify for test case support center
		String dateFlag = arrivalDate.split("/")[1].trim() + weekDays[weekIndex - 1];

		String tableHeader = "";
		int counter = 0;
		for (int i = 0; i < 31; i++) {
			tableHeader = ((IHtmlTable) objs[0]).getCellValue(0, 12 + i);
			if (tableHeader.length() > 0
					&& tableHeader.equalsIgnoreCase(dateFlag)) {
				counter = i;
				break;
			}
		}

		String toReturn = ((IHtmlTable) objs[0]).getCellValue(1, 12 + counter);

		Browser.unregister(objs);
		return toReturn;
	}
	
	public void verifyAvailablityMarkWithQty(String arrivalDate, String daynum, String mark, String toQty, String totalQty){
		int startQty = 0;
		int j = 0;
		startQty = Integer.parseInt(totalQty)-Integer.parseInt(toQty);
		for(int i = 0; i <startQty;i++){
			selectQty(0, String.valueOf(Integer.parseInt(toQty)+1+i));
			while(j<Integer.parseInt(daynum)){
				if(!getSiteStatus(arrivalDate).equalsIgnoreCase("r")){
					throw new ItemNotFoundException("The site status is wrong");
				}
				j++;
				arrivalDate = DateFunctions.getDateAfterGivenDay(arrivalDate, j);
			}
		}
	}
	
	public boolean checkWarnMsgExist(){
		return browser.checkHtmlObjectExists(".id", "sitesearch.site.notfound");
	}
	
	public String getWarnMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "sitesearch.site.notfound");
		String warningMes = objs[0].text().trim();
		
		Browser.unregister(objs);
		return warningMes;
	}
	
	public void verifyWarnMes(String warnMsg){
		if(!this.getWarnMsg().equals(warnMsg)){
			throw new ErrorOnDataException("Expect warning message should be "+warnMsg);
		}
	}
	/**
	 * get image title value.
	 * @param siteNumber
	 * @return the title value.
	 */
	public String getImageTitleAttributeValue(String siteNumber){
		int rowNum = this.getRowNumOfSite(siteNumber);
		String value = this.getImgAtrributeValue(rowNum-1, "title","Site Type:");
		return value;
	}
	
	/**
	 * get src value.
	 * @param siteNumber
	 * @return the src value.
	 */
	public String getImagegSrcAttributeValue(String siteNumber){
		int rowNum = this.getRowNumOfSite(siteNumber);
//		String value = this.getImgAtrributeValue(rowNum, "src","common/images_skin1");
		String value = this.getImgAtrributeValue(rowNum, "src","common/images_skin");
		return value;
	}
	
	public String getState(){
		return browser.getObjectText(".class", "Html.SPAN", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
	
	public void clickFacility(String parkName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(parkName, false));
	}
	
	public void clickSiteLink(String siteNum){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(siteNum, false));
	}
	
	public void clickViewMap() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("View Map", false));
	}
	
	public boolean isSiteExists(String siteNum) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", siteNum);
	}
}
