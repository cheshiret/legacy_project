package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
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
 * @author qchen
 * @Date  Sep 3, 2013
 */
public class LicMgrLotteryLicenseYearsPage extends LicMgrLotteryProductCommonPage {
	private static LicMgrLotteryLicenseYearsPage _instance = null;
	
	private LicMgrLotteryLicenseYearsPage() {}
	
	public static LicMgrLotteryLicenseYearsPage getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryLicenseYearsPage();
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(licenseYearListTable());
	}
	
	private Property[] addLicenseYear() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Add Licen(s|c)e Year", false));
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.status", false));
	}
	
	private Property[] licenseYear() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.licenseYear", false));
	}
	
	private Property[] locationClass() {
		return Property.toPropertyArray(".id", new RegularExpression("LicenseYearFilter-\\d+\\.locationClassID", false));
	}
	
	private Property[] go() {
		return Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}
	
	private Property[] licenseYearListTable() {
		return Property.toPropertyArray(".id", "productLicenseYearGrid");
	}
	
	public void clickAddLicenseYear() {
		browser.clickGuiObject(addLicenseYear());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void selectLicenseYear(String licenseYear) {
		browser.selectDropdownList(licenseYear(), licenseYear);
	}
	
	public void selectLocationClass(String locClass) {
		browser.selectDropdownList(locationClass(), locClass);
	}
	
	public void clickGo() {
		browser.clickGuiObject(go());
	}
	
	private static final String ID_COL = "ID";
	private static final String STATUS_COL = "Status";
	private static final String LOCATION_CLASS_COL = "Location Class";
	private static final String LICENSE_YEAR_COL = "License Year";
	private static final String LICENCE_YEAR_COL = "Licence Year";
	private static final String SELL_FROM_DATE_TIME_COL = "Sell From Date/Time";
	private static final String SELL_TO_DATE_TIME_COL = "Sell To Date/Time";
	
	private IHtmlObject[] getLicenseYearListTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(licenseYearListTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find License Year list table object.");
		
		return objs;
	}
	
	
	public String getLicenseYearId(String status, String locClass, String year) {
		IHtmlObject[] objs = getLicenseYearListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		String id = "";
		int statusColIndex = table.findColumn(0, STATUS_COL);
		int locClassColIndex = table.findColumn(0, LOCATION_CLASS_COL);
		int licYearColIndex = table.findColumn(0, LICENSE_YEAR_COL);
		if(licYearColIndex<1){//Sara[20140210] The column name is 'Licence Year' for SK, but is "License Year" for MS
			licYearColIndex = table.findColumn(0, LICENCE_YEAR_COL);
		}
		int idColIndex = table.findColumn(0, ID_COL);
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, statusColIndex).equalsIgnoreCase(status) && table.getCellValue(i, locClassColIndex).equalsIgnoreCase(locClass) && table.getCellValue(i, licYearColIndex).equalsIgnoreCase(year)) {
				id = table.getCellValue(i, idColIndex);
				break;
			}
		}
		
		Browser.unregister(objs);
		return id;
	}
	
	public LicenseYear getLicenseYearInfo(String locClass, String year) {
		IHtmlObject[] objs = getLicenseYearListTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];
		
		LicenseYear ly = new LicenseYear();
		int row = -1;
		int locClassColIndex = table.findColumn(0, LOCATION_CLASS_COL);
		int licYearColIndex = table.findColumn(0, LICENSE_YEAR_COL);
		for (int i = 0; i < table.rowCount(); i++) {
			if (table.getCellValue(i, locClassColIndex).equalsIgnoreCase(locClass) && table.getCellValue(i, licYearColIndex).equalsIgnoreCase(year)) {
				row = i;
				break;
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
		if(!StringUtil.isEmpty(sellfrom)) {
			ly.sellFromDate = sellfrom.split(" ")[0] + " "
					+ sellfrom.split(" ")[1] + " " + sellfrom.split(" ")[2]
					+ " " + sellfrom.split(" ")[3];
			ly.sellFromTime = sellfrom.split(" ")[4] + " "
					+ sellfrom.split(" ")[5].trim();
		}
		String sellto = table.getCellValue(row, 5).trim();
		if(!StringUtil.isEmpty(sellto)) {
			ly.sellToDate = sellto.split(" ")[0] + " " + sellto.split(" ")[1]
					+ " " + sellto.split(" ")[2] + " " + sellto.split(" ")[3];
			ly.sellToTime = sellto.split(" ")[4] + " " + sellto.split(" ")[5].trim();
		}
		
		String validfrom = table.getCellValue(row, 6);
		if (validfrom != null) {
			validfrom = validfrom.trim();

			if(!StringUtil.isEmpty(validfrom)) {
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
			if(!StringUtil.isEmpty(validto)) {
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
	
	public void clickLicenseYearID(String id) {
		browser.clickGuiObject(".class", "Html.A", ".text", id, true);
	}
	
	public boolean verifyLicenseYearExist(LicenseYear ly){
		IHtmlObject objs[] = getLicenseYearListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		boolean flag = false;
		
		int statusColNum = table.findColumn(0, STATUS_COL);
		int locationClassColNum = table.findColumn(0, LOCATION_CLASS_COL);
		int licenseYearColNum = table.findColumn(0, LICENSE_YEAR_COL);

		logger.info("start checking whether the given license year exist in the license year list page or not with License Year Status as "+ly.status+", License Location as "+ly.locationClass+", and" +
				"License Year as "+ly.licYear);
		for (int i = 1; i < table.rowCount(); i ++){
			if( 
					table.getCellValue(i, statusColNum).equals(ly.status) &&
					table.getCellValue(i, locationClassColNum).equals(ly.locationClass) && 
					table.getCellValue(i, licenseYearColNum).equals(ly.licYear)){
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	public void verifyLicenseYearExist(String status, String locClass, String year) {
		if(this.getLicenseYearId(status, locClass, year).trim().length()>0){
			logger.info("verify the specific license year info exist successful.");
		}else{
			throw new ErrorOnPageException("verify Licenese Year exist failed in given value, Status:" + status + "; location class:" + locClass + "; license year:" + year);
		}
	}
}
