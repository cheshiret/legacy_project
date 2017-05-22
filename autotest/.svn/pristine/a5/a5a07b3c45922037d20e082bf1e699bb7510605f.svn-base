/**
 * 
 */
package com.activenetwork.qa.awo.pages.orms.licenseManager.privilege;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @ScriptName LicMgrPrivilegeLicenseYearPage.java
 * @Date:Mar 7, 2011
 * @Description:
 * @author asun
 */
public class LicMgrPrivilegeLicenseYearPage extends
		LicMgrPrivilegeProductDetailsPage {

	private static LicMgrPrivilegeLicenseYearPage instance = null;

	private LicMgrPrivilegeLicenseYearPage() {
	}

	public static LicMgrPrivilegeLicenseYearPage getInstance() {
		if (instance == null) {
			instance = new LicMgrPrivilegeLicenseYearPage();
		}
		return instance;
	}

	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id",
				"privilegeLicenseYearGrid");
	}

	public void clickAddLicenseYear() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Licen(s|c)e Year", false)); //"Add License Year");
	}
	
	public IHtmlObject[] getPrivilegeLicenseYearGrid(){
		return browser.getTableTestObject(".id",
		"privilegeLicenseYearGrid");
	}

	public boolean isThisYearExist(String year) {
		boolean flag = true;

		IHtmlObject[] objs = this.getPrivilegeLicenseYearGrid();
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the license year table");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(3, year);
		if (row < 0) {
			flag = false;
		}
		Browser.unregister(objs);
		return flag;
	}

	public List<String> getColumnValue(int col) {
		List<String> values = new ArrayList<String>();
		IHtmlObject[] objs = getPrivilegeLicenseYearGrid();
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the license year table");
		}
		IHtmlTable table = (IHtmlTable) objs[0];
		values = table.getColumnValues(col);

		Browser.unregister(objs);
		return values;
	}

	public LicenseYear getLicenseYearInfo(String locClass, String year) {
		LicenseYear ly = new LicenseYear();
		IHtmlObject[] objs = getPrivilegeLicenseYearGrid();
		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the license year table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = -1;
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 2).equalsIgnoreCase(locClass)) {
				if (table.getCellValue(i, 3).equalsIgnoreCase(year)) {
					row = i;
					break;
				}
			}
		}
		if (row < 0) {
			throw new ItemNotFoundException("No license year " + year
					+ " is found");
		}

		ly.id = table.getCellValue(row, 0);
		ly.status = table.getCellValue(row, 1);
		ly.locationClass = table.getCellValue(row, 2);
		ly.licYear = table.getCellValue(row, 3);
		String sellfrom = table.getCellValue(row, 4).trim();
		if (sellfrom != null && !"".equals(sellfrom)) {
			ly.sellFromDate = sellfrom.split(" ")[0] + " "
					+ sellfrom.split(" ")[1] + " " + sellfrom.split(" ")[2]
					+ " " + sellfrom.split(" ")[3];
			ly.sellFromTime = sellfrom.split(" ")[4] + " "
					+ sellfrom.split(" ")[5].trim();
		}
		String sellto = table.getCellValue(row, 5).trim();
		if (sellto != null && !"".equals(sellto)) {
			ly.sellToDate = sellto.split(" ")[0] + " " + sellto.split(" ")[1]
					+ " " + sellto.split(" ")[2] + " " + sellto.split(" ")[3];
			ly.sellToTime = sellto.split(" ")[4] + " " + sellto.split(" ")[5].trim();
		}
		
		String validfrom = table.getCellValue(row, 6); // Lesley[20130828]: update due to no valid from or to dates column when the privilege product group is Inventory 
		if (validfrom != null) {
			validfrom = validfrom.trim();

			if (validfrom != null && !"".equals(validfrom) && !" ".equals(validfrom)) {
				ly.validFromDate = validfrom.split(" ")[0] + " "
						+ validfrom.split(" ")[1] + " " + validfrom.split(" ")[2]
						+ " " + validfrom.split(" ")[3];
				ly.validFromTime = validfrom.split(" ")[4] + " "
						+ validfrom.split(" ")[5].trim();
			}
		}
		
		String validto = table.getCellValue(row, 7); 
		if (validto != null ) {
			validto = validto.trim();
			if (validto != null && !"".equals(validto) && !" ".equals(validto)) {
				ly.validToDate = validto.split(" ")[0] + " "
						+ validto.split(" ")[1] + " " + validto.split(" ")[2] + " "
						+ validto.split(" ")[3];
				ly.validToTime = validto.split(" ")[4] + " "
						+ validto.split(" ")[5].trim();
			}
		}

		Browser.unregister(objs);
		return ly;
	}

	public boolean compareLicenseYearRecord(LicenseYear expectedInfo) {
		// replaced getLicenseYearInfoByID from getLicenseYearInfo 
		// cause for Inactive license year, there are so many license year with same location class and year
		// id is unique
		LicenseYear actualInfo = this.getLicenseYearInfoByID(expectedInfo.id);
		boolean result = true;

		if (!expectedInfo.id.equals(actualInfo.id)) {
			logger.error("Expected id is '" + expectedInfo.id + "'.");
			logger.error("Actual id is '" + actualInfo.id + "'.");
			result &= false;
		}
		if (!expectedInfo.status.equals(actualInfo.status)) {
			logger.error("Expected status is not'" + actualInfo.status + "'.");
			result &= false;
		}
		if (!expectedInfo.locationClass
				.equals(actualInfo.locationClass)) {
			logger.error("Expected locationClass is not'" + actualInfo.locationClass + "'.");
			result &= false;
		}
		if (!expectedInfo.licYear.equals(actualInfo.licYear)) {
			logger.error("Expected licYear is not'" + actualInfo.licYear + "'.");
			result &= false;
		}
		if (DateFunctions.compareDates(expectedInfo.sellFromDate,
				actualInfo.sellFromDate) != 0) {
			logger.error("Expected sellFromDate is '" + expectedInfo.sellFromDate + "'.");
			logger.error("Actual sellFromDate is '" + actualInfo.sellFromDate + "'.");
			result &= false;
		}
		if (!(expectedInfo.sellFromTime + " " + expectedInfo.sellFromAmPm)
				.equals(actualInfo.sellFromTime)) {
			logger.error("Expected sellFromTime is '" + expectedInfo.sellFromTime + " " + expectedInfo.sellFromAmPm + "'.");
			logger.error("Actual sellFromTime is '" + actualInfo.sellFromTime + "'.");
			result &= false;
		}
		if (DateFunctions.compareDates(expectedInfo.sellToDate,
				actualInfo.sellToDate) != 0) {
			logger.error("Expected sellToDate is '" + expectedInfo.sellToDate + "'.");
			logger.error("Actual sellToDate is '" + actualInfo.sellToDate + "'.");
			result &= false;
		}
		if (!(expectedInfo.sellToTime + " " + expectedInfo.sellToAmPm).equals(actualInfo.sellToTime)) {
			logger.error("Expected sellToTime is '" + expectedInfo.sellToTime + " " + expectedInfo.sellToAmPm + "'.");
			logger.error("Actual sellToTime is '" + actualInfo.sellToTime + "'.");
			result &= false;
		}
		if(expectedInfo.validFromDate.length() > 0) {
			if (DateFunctions.compareDates(expectedInfo.validFromDate, actualInfo.validFromDate) != 0) {
				logger.error("Expected validFromDate is not'" + actualInfo.validFromDate + "'.");
				result &= false;
			}
			if (!(expectedInfo.validFromTime + " " + expectedInfo.validFromAmPm).equals(actualInfo.validFromTime)) {
				logger.error("Expected validFromTime is not'" + actualInfo.validFromTime + "'.");
				result &= false;
			}
		}
		if(expectedInfo.validToDate.length() > 0) {
			if (DateFunctions.compareDates(expectedInfo.validToDate, actualInfo.validToDate) != 0) {
				logger.error("Expected validToDate is not'" + actualInfo.validToDate + "'.");
				result &= false;
			}
			if (!(expectedInfo.validToTime + " " + expectedInfo.validToAmPm).equals(actualInfo.validToTime)) {
				logger.error("Expected validToTime is not'" + actualInfo.validToTime + "'.");
				result &= false;
			}
		}

		return result;
	}

	public void clickLicenseYear(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}

	public void unSelectShowCurrentRecordsOnly() {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.showCurrentRecordsOnly",
				false);
		browser.unSelectCheckBox(".id", regx);
	}

	public void selectStatus(String status) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.status", false);
		browser.selectDropdownList(".id", regx, status);
	}

	public void selectLicenseYear(String year) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.licenseYear", false);

		browser.selectDropdownList(".id", regx, year);
	}

	public void selectLicenseYear(int index) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.licenseYear", false);

		browser.selectDropdownList(
				new Property[] { new Property(".id", regx) }, index, false);
	}

	public void selectLocationClass(String loc) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.locationClassID", false);

		browser.selectDropdownList(".id", regx, loc);
	}

	public void selectLocationClass(int index) {
		RegularExpression regx = new RegularExpression(
				"PrivilegeLicenseYearFilter-\\d+\\.locationClassID", false);
		browser.selectDropdownList(
				new Property[] { new Property(".id", regx) }, index, false);
	}

	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	/**
	 * This method used to check license year exist for given conditions by
	 * check license year id is blank or not
	 * 
	 * @param locClass
	 * @param year
	 * @return license year id
	 */
	public String getLicenseYearId(String locClass, String year) {
		IHtmlObject[] objs = getPrivilegeLicenseYearGrid();
		IHtmlTable grid = (IHtmlTable) objs[0];
		String id = "";
		for (int i = 0; i < grid.rowCount(); i++) {
			if (grid.getCellValue(i, 2).equalsIgnoreCase(locClass)) {
				if (grid.getCellValue(i, 3).equalsIgnoreCase(year)) {
					id = grid.getCellValue(i, 0);
					break;
				}
			}
		}
		Browser.unregister(objs);
		return id;
	}
	
	/**
	 * This method used to check license year exist for given conditions by
	 * check license year id is blank or not
	 * 
	 * @param status
	 * @param locClass
	 * @param year
	 * @return license year id
	 */
	public String getLicenseYearId(String status, String locClass, String year) {
		IHtmlObject[] objs = getPrivilegeLicenseYearGrid();
		IHtmlTable grid = (IHtmlTable) objs[0];
		String id = "";
		for (int i = 0; i < grid.rowCount(); i++) {
			if (grid.getCellValue(i, 1).equalsIgnoreCase(status) && grid.getCellValue(i, 2).equalsIgnoreCase(locClass)&&grid.getCellValue(i, 3).equalsIgnoreCase(year)) {
					id = grid.getCellValue(i, 0);
					break;
			}
		}
		Browser.unregister(objs);
		return id;
	}
	
	public void verifyLicenseYearExist(String status, String locClass, String year){
		if(this.getLicenseYearId(status, locClass, year).trim().length()>0){
			logger.info("verify the specific license year info exist successful.");
		}else{
			throw new ErrorOnPageException("verify Licenese Year exist failed in given value, Status:" + status + "; location class:" + locClass + "; license year:" + year);
		}
	}
	
	public List<String> getPrivilegeLicenseYearColumn(String colName){
		List<String> colNames = new ArrayList<String>();
		IHtmlObject[] objs = getPrivilegeLicenseYearGrid();
		
		IHtmlTable table = (IHtmlTable)objs[0];
		int colNum = table.findColumn(0, colName);

		colNames = table.getColumnValues(colNum);
		colNames.remove(0);// remove title.
		Browser.unregister(objs);
		return colNames;
	}
	
	public List<String> getLicenseYearIds(){
		return this.getPrivilegeLicenseYearColumn("ID");
	}

	public List<Boolean> isLicenseYearColumnExist(List<String> columnNames){
		List<Boolean> results = new ArrayList<Boolean>();
		int index = -1; 
		IHtmlObject objs[] = getPrivilegeLicenseYearGrid();
		IHtmlTable table = (IHtmlTable)objs[0];
		for(int i=0; i<columnNames.size(); i++){
			index = table.findColumn(0, columnNames.get(i));
			if(index!=-1){
				results.add(true);
			}else results.add(false);
		}

		Browser.unregister(objs);
		return results;
	}
	
	/**
	 * verify license year exist
	 * @param ly
	 * @return
	 */
	public boolean verifyLicenseYearExist(LicenseYear ly){
		IHtmlObject objs[] = getPrivilegeLicenseYearGrid();

		IHtmlTable table = (IHtmlTable)objs[0];
		boolean flag = false;
		
		//should verify if there is no document under this now
		//then return false

		//int iDColNum = table.findColumn(0, "ID");
		int statusColNum = table.findColumn(0, "Status");
		int locationClassColNum = table.findColumn(0, "Location Class");
		int licenseYearColNum = table.findColumn(0, "License Year");
		if(licenseYearColNum==-1){
			licenseYearColNum = table.findColumn(0, "Licence Year");
		}

		logger.info("Start checking whether the given license year exist in the license year list page or not with License Year Status as "+ly.status+", License Location as "+ly.locationClass+", and" +
				"License Year as "+ly.licYear);
		for (int i = 1; i < table.rowCount(); i ++){
			if( 
					//table.getCellValue(i, iDColNum).equals(ly.id)&&
					table.getCellValue(i, statusColNum).equals(ly.status) &&
					table.getCellValue(i, locationClassColNum).equals(ly.locationClass) && 
					table.getCellValue(i, licenseYearColNum).equals(ly.licYear)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	public String getActiveIDViaGiveLicenseYearID(LicenseYear ly){
		IHtmlObject objs[] = this.getPrivilegeLicenseYearGrid();

		IHtmlTable table = (IHtmlTable)objs[0];
		String lyID = "";
		for (int i = 1; i < table.rowCount(); i ++){
			if (table.getCellValue(i, 0).compareTo(ly.id)>0
					&& table.getCellValue(i, 1).equals("Active") &&
					table.getCellValue(i, 2).equals(ly.locationClass)&&
					table.getCellValue(i, 3).equals(ly.licYear)){
				lyID = table.getCellValue(i, 0);
				break;
			}
		}

		Browser.unregister(objs);
		return lyID;
	}
	
	public String getStatusByLicenseYearID(String id){
		IHtmlObject objs[] = this.getPrivilegeLicenseYearGrid();

		IHtmlTable table = (IHtmlTable)objs[0];
		String status = "";
		for (int i = 1; i < table.rowCount(); i ++){
			if (table.getCellValue(i, 0).equals(id)){
				status = table.getCellValue(i, 1);
				return status;
			}
		}

		Browser.unregister(objs);
		return status;
	}
	
	public LicenseYear getLicenseYearInfoByID(String id){
		LicenseYear ly = new LicenseYear();
		IHtmlObject objs[] = this.getPrivilegeLicenseYearGrid();

		if (objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't found the license year table");
		}

		IHtmlTable table = (IHtmlTable) objs[0];
		int row = -1;
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, 0).equalsIgnoreCase(id)) {
					row = i;
					break;
			}
		}
		if (row < 0) {
			throw new ItemNotFoundException("No license year for id:" + id
					+ " is found");
		}

		ly.id = table.getCellValue(row, 0);
		ly.status = table.getCellValue(row, 1);
		ly.locationClass = table.getCellValue(row, 2);
		ly.licYear = table.getCellValue(row, 3);
		String sellfrom = table.getCellValue(row, 4);
		if (sellfrom != null && !"".equals(sellfrom)) {
			ly.sellFromDate = sellfrom.split(" ")[0] + " "
					+ sellfrom.split(" ")[1] + " " + sellfrom.split(" ")[2]
					+ " " + sellfrom.split(" ")[3];
			ly.sellFromTime = sellfrom.split(" ")[4] + " "
					+ sellfrom.split(" ")[5];
		}
		String sellto = table.getCellValue(row, 5);
		if (sellto != null && !"".equals(sellto)) {
			ly.sellToDate = sellto.split(" ")[0] + " " + sellto.split(" ")[1]
					+ " " + sellto.split(" ")[2] + " " + sellto.split(" ")[3];
			ly.sellToTime = sellto.split(" ")[4] + " " + sellto.split(" ")[5];
		}
		String validfrom = table.getCellValue(row, 6);

		if (validfrom != null && !"".equals(validfrom) && !" ".equals(validfrom)) {
			ly.validFromDate = validfrom.split(" ")[0] + " "
					+ validfrom.split(" ")[1] + " " + validfrom.split(" ")[2]
					+ " " + validfrom.split(" ")[3];
			ly.validFromTime = validfrom.split(" ")[4] + " "
					+ validfrom.split(" ")[5];
		}
		String validto = table.getCellValue(row, 7);
		if (validto != null && !"".equals(validto) && !" ".equals(validto)) {
			ly.validToDate = validto.split(" ")[0] + " "
					+ validto.split(" ")[1] + " " + validto.split(" ")[2] + " "
					+ validto.split(" ")[3];
			ly.validToTime = validto.split(" ")[4] + " "
					+ validto.split(" ")[5];
		}

		Browser.unregister(objs);
		return ly;
	}
}
