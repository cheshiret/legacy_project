package com.activenetwork.qa.awo.pages.orms.licenseManager;

import java.util.List;
import java.util.Set;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.Harvest;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @Date  Feb 29, 2012
 */
public class LicMgrCustomerHarvestPage extends LicMgrCustomerDetailsPage {
	private static LicMgrCustomerHarvestPage _instance = null;
	
	private LicMgrCustomerHarvestPage() {}
	
	public static LicMgrCustomerHarvestPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrCustomerHarvestPage();
		}
		
		return _instance;
	}
	
	private static String LICENSE_YEAR_PRIVILEGE_COL = "License Year/Privilege";
	private static String CREATED_DATE_TIME_COL = "Created Date/Time";
	private static String HARVEST_NUMBER_COL = "Harvest Number";
	private static String STATUS_COL = "Status";
	private static String PRIVILEGE_NUMBER_COL = "Privilege Number";
	private static String SPECIES_COL = "Species";
	private static String SEASON_COL = "Season";
	private static String HUNT_LOCATION_COL = "Hunt Location";
	private static String ORDER_NUMBER_COL = "Order Number";
	private static String CONFIRMATION_NUMBER_COL = "Confirmation Number";
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.showCurrent", false));
	}
	
	public void selectShowCurrentRecordsOnly() {
		browser.selectCheckBox(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.showCurrent", false));
	}
	
	public void unselectShowCurrentRecordsOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.showCurrent", false));
	}
	
	/**
	 * Select the status value
	 * @param status
	 */
	public void selectStatus(String status) {
		if(null == status || status.length() < 1) {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.status", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.status", false), status);
		}
	}
	
	/**
	 * Select license year value
	 * @param licenseYear
	 */
	public void selectLicenseYear(String licenseYear) {
		if(null == licenseYear || licenseYear.length() < 1) {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.licenseYear", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.licenseYear", false), licenseYear);
		}
	}
	
	/**
	 * select species value
	 * @param species
	 */
	public void selectSpecies(String species) {
		if(null == species || species.length() < 1) {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.species", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.species", false), species);
		}
	}
	
	/**
	 * Select season value
	 * @param season
	 */
	public void selectHuntingSeason(String season) {
		if(null == season || season.length() < 1) {
			browser.selectDropdownList("", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.season", false), 0);
		} else {
			browser.selectDropdownList("", new RegularExpression("SearchCustomerHarvestReportCriteria-\\d+\\.season", false), season);
		}
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}
	
	/**
	 * Click corresponding 'Report Harvest' button identified privilege num
	 * @param privilegeNum
	 */
	public void clickReportHarvest(String privilegeNum) {
		Property topP[] = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(privilegeNum, false));
		Property property[] = Property.toPropertyArray(".class", "Html.A", ".text", "Report Harvest");
		browser.clickGuiObject(Property.atList(topP, property), true, 0);
	}
	
	/**
	 * Click harvest number link to pop up harvest report question widget to edit
	 * @param harvestNum
	 */
	public void clickHarvestNum(String harvestNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", harvestNum, true);
	}
	
	private IHtmlTable getHarvestTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".text", new RegularExpression("^License Year/privilege", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find 'Harvest Report List' table object.");
		}
		IHtmlTable table = (IHtmlTable)objs[0];
		
		return table;
	}
	
	private String getHarvestReportTRObjectId(String privilegeNum) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression(privilegeNum, false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find TR object by privilege number - " + privilegeNum);
		}
		String id = objs[0].getProperty(".id").trim();
		
		Browser.unregister(objs);
		return id;
	}
	
	/**
	 * Check whether a specific harvest report has been reported or not
	 * @param privilegeNum
	 * @return
	 */
	public boolean isHarvestReported(String privilegeNum) {
		Property topP[] = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(privilegeNum, false));
		Property property[] = Property.toPropertyArray(".class", "Html.SPAN", ".className", "expander");
		return browser.checkHtmlObjectExists(Property.atList(topP, property));
	}
	
	public void clickExpander(String privilegeNum) {
		Property topP[] = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(privilegeNum, false));
		Property property[] = Property.toPropertyArray(".class", "Html.SPAN", ".className", "expander");
		browser.clickGuiObject(Property.atList(topP, property), true, 0);
	}

	/**
	 * @param ordernum
	 * @return
	 */
	public Harvest getHarvestListInfoByOrderNum(String ordernum) {
		IHtmlTable table = getHarvestTableObject();
		
		int colIndex = table.findColumn(0, "Order Number");
		int rowIndex = table.findRow(colIndex, ordernum);
		List<String> rowValue = table.getRowValues(rowIndex);
		Harvest harvest = new Harvest();
		harvest.licenseYearPrivilege = rowValue.get(0);
		harvest.createdDateTime = rowValue.get(1);
		harvest.harvestNum = rowValue.get(2);
		harvest.status = rowValue.get(3);
		harvest.privilegeNum = rowValue.get(4);
		harvest.species = rowValue.get(5);
		harvest.season = rowValue.get(6);
		harvest.orderNum = rowValue.get(7);
		harvest.confirmationNum = rowValue.get(8).equalsIgnoreCase("Report Harvest ") ? null : rowValue.get(8);
		return harvest;
	}
	
	/**
	 * Get harvest list info identified by its corresponding privilege number
	 * @param privilegeNum
	 * @return
	 */
	public Harvest getHarvestListInfo(String privilegeNum) {
		IHtmlTable table = getHarvestTableObject();
		
		int colIndex = table.findColumn(0, "Privilege Number");
		int rowIndex = table.findRow(colIndex, privilegeNum);
		List<String> rowValue = table.getRowValues(rowIndex);
		
		Harvest harvest = new Harvest();
		harvest.licenseYearPrivilege = rowValue.get(table.findColumn(0, LICENSE_YEAR_PRIVILEGE_COL));
		harvest.createdDateTime = rowValue.get(table.findColumn(0, CREATED_DATE_TIME_COL));
		harvest.harvestNum = rowValue.get(table.findColumn(0, HARVEST_NUMBER_COL));
		harvest.status = rowValue.get(table.findColumn(0, STATUS_COL));
		harvest.privilegeNum = rowValue.get(table.findColumn(0, PRIVILEGE_NUMBER_COL));
		harvest.species = rowValue.get(table.findColumn(0, SPECIES_COL));
		harvest.season = rowValue.get(table.findColumn(0, SEASON_COL));
		harvest.orderNum = rowValue.get(table.findColumn(0, ORDER_NUMBER_COL));
		harvest.confirmationNum = rowValue.get(table.findColumn(0, CONFIRMATION_NUMBER_COL));
		harvest.confirmationNum = harvest.confirmationNum.equalsIgnoreCase("Report Harvest") ? null : harvest.confirmationNum;
		
		if(isHarvestReported(privilegeNum)) {
			this.clickExpander(privilegeNum);

			String parentTrId = this.getHarvestReportTRObjectId(privilegeNum);
			IHtmlObject childrenTRs[] = browser.getHtmlObject(".class", "Html.TR", ".className", new RegularExpression(".*child-of-" + parentTrId + " initialized.*", false));
			
			harvest.validationDateTime = childrenTRs[1].getChildren()[1].text().replace(",", StringUtil.EMPTY);
			harvest.dateOfKill = childrenTRs[2].getChildren()[1].text();
			String temp = childrenTRs[3].getChildren()[1].text();
			if(!temp.equals(harvest.confirmationNum)) {
				throw new ErrorOnPageException("Harvest confimation numbers are different, it in the list is: " + harvest.confirmationNum + ", but in detail is: " + temp);
			}
			harvest.reportUser = childrenTRs[4].getChildren()[1].text();
			harvest.reportApplication = childrenTRs[5].getChildren()[1].text();
			//put questions and answers
			for(int i = 0; i < childrenTRs.length; i ++) {
				temp = childrenTRs[i].getChildren()[3].text();
				if(temp.length() > 0) {
					harvest.questionAnswers.put(temp, childrenTRs[i].getChildren()[4].text());
				}
			}
			
			Browser.unregister(childrenTRs);
		}
		return harvest;
	}
	
	/**
	 * verify harvest details info is correct or not
	 * @param expectedHarvest
	 * @param actualHarvest
	 */
	public void verifyHarvestInfo(Harvest expectedHarvest, Harvest actualHarvest) {
		logger.info("Verify whether harvest report(Harvest#=" + actualHarvest.harvestNum + ") list info is correct or not.");
		
		boolean result = true;
		result &= MiscFunctions.compareResult("License Year/Privilege", expectedHarvest.licenseYearPrivilege.replaceAll("\\s", StringUtil.EMPTY), actualHarvest.licenseYearPrivilege.replaceAll("\\s", StringUtil.EMPTY));
		result &= MiscFunctions.compareResult("Created Date Time", expectedHarvest.createdDateTime, actualHarvest.createdDateTime.replace(",", " "));
		result &= MiscFunctions.compareResult("Status", expectedHarvest.status, actualHarvest.status);
		result &= MiscFunctions.compareResult("Privilege Number", expectedHarvest.privilegeNum, actualHarvest.privilegeNum);
		result &= MiscFunctions.compareResult("Species", expectedHarvest.species, actualHarvest.species);
		result &= MiscFunctions.compareResult("Season", expectedHarvest.season, actualHarvest.season);
		result &= MiscFunctions.compareResult("Order Number", expectedHarvest.orderNum, actualHarvest.orderNum);
		if(null != actualHarvest.confirmationNum) {
			result &= MiscFunctions.compareResult("Confirmation Number", expectedHarvest.confirmationNum, actualHarvest.confirmationNum);
		}
		//information detail info compare
		if(actualHarvest.validationDateTime.length() > 0) {//TODO
			String actualInfo = actualHarvest.validationDateTime.replaceAll(",", "");
			if(!actualInfo.contains(DateFunctions.formatDate(expectedHarvest.validationDateTime, "MMM dd yyyy"))){
				result &= false;
				logger.error("Validation Date and Time isn't correct. Expect is "+expectedHarvest.validationDateTime+", but actual is "+actualInfo);
			}
//			result &= MiscFunctions.compareResult("Validation Date and Time", expectedHarvest.validationDateTime, actualHarvest.validationDateTime);
			result &= MiscFunctions.compareResult("Date of Kill", expectedHarvest.dateOfKill, actualHarvest.dateOfKill);
			result &= MiscFunctions.compareResult("Harvest Report User", expectedHarvest.reportUser.replace(", ", ","), actualHarvest.reportUser.replace(", ", ","));
			result &= MiscFunctions.compareResult("Harvest Report Application", expectedHarvest.reportApplication, actualHarvest.reportApplication);
			
			//compare question&answer
			Set<String> expectedKeySet = expectedHarvest.questionAnswers.keySet();
			Set<String> actualKeySet = actualHarvest.questionAnswers.keySet();
			String expectQuestion, actualQuestion;
			for (int i = 0 ; i < actualKeySet.size(); i ++) {
				expectQuestion = (String)expectedKeySet.iterator().next();
				actualQuestion = (String)actualKeySet.iterator().next();
				result &= MiscFunctions.compareResult("Question " + i, expectQuestion, actualQuestion);
				result &= MiscFunctions.compareResult("Answer " + i, expectedHarvest.questionAnswers.get(expectQuestion), actualHarvest.questionAnswers.get(actualQuestion));
			}
			
		}
		
		if(!"".equals(expectedHarvest.harvestNum)) {//TODO
			if(!actualHarvest.harvestNum.matches(expectedHarvest.harvestNum)) {
				logger.error("Harvest number is wrong. Expected value is like: " + expectedHarvest.harvestNum + ", but actual value is: " + actualHarvest.harvestNum);
				result&=false;
			}
		}
		if(!result) {
			throw new ErrorOnPageException("The harvest details info are not all correct, please refer log for detail.");
		} else logger.info("The harvest info are correct.");
	}
	
	/**
	 * Set searching harvest record information
	 * @param harvest
	 */
	public void setSearchingHarvest(Harvest harvest){
		this.unselectShowCurrentRecordsOnly();
		ajax.waitLoading();
		
		if(!StringUtil.isEmpty(harvest.status)){
			this.selectStatus(harvest.status);
		}
		
		if(!StringUtil.isEmpty(harvest.licenseYearPrivilege)){
			this.selectLicenseYear(harvest.licenseYearPrivilege);
		}
		
		if(!StringUtil.isEmpty(harvest.species)){
			this.selectSpecies(harvest.species);
		}
		
		if(!StringUtil.isEmpty(harvest.season)){
			this.selectHuntingSeason(harvest.season);
		}
	}

	

}
