package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.datacollection.legacy.ScheduleData;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author QA
 */
public class ResMgrSchedulerPage extends ResourceManagerPage {

	private static ResMgrSchedulerPage _instance = null;

	public static ResMgrSchedulerPage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrSchedulerPage();
		}

		return _instance;
	}

	protected ResMgrSchedulerPage() {
	}

	public boolean exists() {
		// use Schedule tab as pageMark
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Schedules");
		p[2] = new Property(".href", new RegularExpression(
				"\"ReportSchedulerListScheduled\\.do\"", false));

		return browser.checkHtmlObjectExists(p);
	}

	/** Click on Add New link. */
	public void clickAddNew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New");

	}

	/** Click on Go link. */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^(Go|Search)$", false));
	}

	/** Click on Run Now link. */
	public void clickRunNow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Run Now");
	}

	/** Click on Delete link. */
	public void clickDelete() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete");
	}

	/** Click on Activate link. */
	public void clickActivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Active");
	}

	/** Click on Deactivate link. */
	public void clickDeactivate() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Deactivate");
	}

	/** Click on Jobs Link,goto schedule Job page. */
	public void clickJobs() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Jobs");
	}

	/**
	 * Retrieve the schedule id.
	 * 
	 * @return - schedule ID
	 */
	public String getScheduleID() {
		RegularExpression reg = new RegularExpression(
				"Schedule ID .+ is successfully created", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SPAN",".text", reg);
		String text=null;
		if(objs!=null && objs.length>0){
			text = (String) objs[0].getProperty(".text");
			int i = text.indexOf("Schedule ID ");
			int j = text.indexOf(" is successfully created");
			text = text.substring(i + "Schedule ID ".length(), j);
		}else{
			throw new ErrorOnDataException("Schedule can not successfully created");
		}	
		Browser.unregister(objs);
		return text;
	}

	/**
	 * Select search type from drop down list
	 * 
	 * @param searchType
	 */
	public void selectSearchType(String searchType) {
		browser.selectDropdownList(".id", "SearchType", searchType);
	}

	/**
	 * Input search type value
	 * 
	 * @param searchValue
	 */
	public void setSearchValue(String searchValue) {
		browser.setTextField(".id", "SearchTypeValue", searchValue);
	}
	
	/**
	 * Select Date Range from drop down list
	 * 
	 * @param dateRange
	 */
	public void selectDateRange(String dateRange) {
		browser.selectDropdownList(".id", "DateRange", dateRange);
	}

	/**
	 * Input Start date
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		browser.setTextField(".id", "RangeStartDate_ForDisplay", startDate);
	}

	/**
	 * Input End Date
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		browser.setTextField(".id", "RangeEndDate_ForDisplay", endDate);
	}

	/**
	 * Select schedule status
	 * 
	 * @param status
	 */
	public void selectStatus(String status) {
		browser.selectDropdownList(".id", "Status", status);
	}

	/**
	 * Select given schedule type from drop down list
	 * 
	 * @param scheduleType
	 */
	public void selectScheduleType(String scheduleType) {
		browser.selectDropdownList(".id", "ScheduleType", scheduleType);
	}

	/**
	 * This method used to set all search criteria to default
	 * 
	 */
	public void clearAllSearchCriteria() {
		setSearchValue("");
		setStartDate("");
		setEndDate("");
		selectStatus("All");
		selectScheduleType("All");
	}

	/**
	 * This method used to search Schedule by schedule Id
	 * 
	 * @param scheduleId
	 */
	public void searchByScheduleId(String scheduleId) {
		clearAllSearchCriteria();
		selectSearchType("ScheduleID");
		setSearchValue(scheduleId);
		clickGo();
		waitLoading();
	}

	/**
	 * This method used to verify schedule Info correct
	 * 
	 * @param schedule
	 */
	public void verifyScheduleInfo(ScheduleData schedule) {
		logger.info("Start to Verify Schedule " + schedule.scheduleId);

		ScheduleData s2 = this.retrieveScheduleInfo(schedule.scheduleId);
		if (!schedule.scheduleId.equals(s2.scheduleId)) {
			throw new ErrorOnPageException(
					"Schedule ID Not Correct,Given ID is "
							+ schedule.scheduleId);
		}
		if (!schedule.scheduleName.equals(s2.scheduleName)) {
			throw new ErrorOnPageException(
					"Schedule Name Not Correct,Given Name is "
							+ schedule.scheduleName);
		}
		if (!schedule.scheduleType.equals(s2.scheduleType)) {
			throw new ErrorOnPageException(
					"Schedule Type Not Correct,Given type is "
							+ schedule.scheduleType);
		}
		if (!schedule.location.equals(s2.location)) {
			throw new ErrorOnPageException(
					"Schedule location Not Correct,Given location is "
							+ schedule.location);
		}
		if (!schedule.frequency.equals(s2.frequency)) {
			throw new ErrorOnPageException(
					"Schedule frequency Not Correct,Given frequency is "
							+ schedule.frequency);
		}
	}

	/**
	 * This method used to retrieve schedule info for given schedule Id
	 * 
	 * @param scheduleId
	 * @return-ScheduleData
	 */
	public ScheduleData retrieveScheduleInfo(String scheduleId) {
		searchByScheduleId(scheduleId);
		ScheduleData schedule = new ScheduleData();
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression(
						"Schedule ID Schedule Name.*", false));
		int rowCount = ((IHtmlTable) objs[0]).rowCount();
		if (rowCount < 2) {
			throw new ItemNotFoundException("Not Found Given Schedule "
					+ scheduleId);
		}
		schedule.scheduleId = ((IHtmlTable) objs[0]).getCellValue(1,
				getColNum("Schedule ID"));
		schedule.scheduleName = ((IHtmlTable) objs[0]).getCellValue(1,
				getColNum("Schedule Name"));
		schedule.scheduleType = ((IHtmlTable) objs[0]).getCellValue(1,
				getColNum("Schedule Type"));
		schedule.location = ((IHtmlTable) objs[0]).getCellValue(1,
				getColNum("Location"));
		schedule.frequency = ((IHtmlTable) objs[0]).getCellValue(1,
				getColNum("Frequency"));
		Browser.unregister(objs);

		return schedule;
	}

	/**
	 * Get column Num for specific Column Name
	 * 
	 * @param colName
	 * @return column Number
	 */
	public int getColNum(String colName) {
		RegularExpression rex = new RegularExpression(
				"Schedule ID Schedule Name.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		if (null != objs && objs.length > 0) {
			int colCounts = ((IHtmlTable) objs[0]).columnCount();
			for (int i = 0; i < colCounts; i++) {
				if (((IHtmlTable) objs[0]).getCellValue(0, i)
						.equalsIgnoreCase(colName)) {
					Browser.unregister(objs);
					return i;
				}
			}
		}
		Browser.unregister(objs);
		return -1;
	}

	/**
	 * Select check box of given schedule Id
	 * 
	 * @param scheduleId
	 */
	public void selectSchdeuleCheckBox(String scheduleId) {
		browser.selectCheckBox(".id", "checkbox_" + scheduleId);
	}

	
	public void activateSchedule(String scheduleId) {
		logger.info("Activate Schedule " + scheduleId);

		searchByScheduleId(scheduleId);
		selectSchdeuleCheckBox(scheduleId);
		this.clickActivate();
		waitLoading();
	}
	
	/**
	 * This method used to delete schedule for given schedule Id
	 * 
	 * @param scheduleId
	 */
	public void deleteSchedule(String scheduleId) {
		logger.info("Delete Schedule " + scheduleId);

		searchByScheduleId(scheduleId);
		selectSchdeuleCheckBox(scheduleId);
		clickDelete();
		waitLoading();
	}

	/**
	 * This method used to set up all kinds of search criterias
	 * 
	 * @param schedule
	 */
	public void setUpSearchCriteria(ScheduleData schedule) {
		this.clearAllSearchCriteria();

		if (schedule.searchBy != null && !schedule.searchBy.equals("")) {
			if (schedule.searchBy.equalsIgnoreCase("ScheduleID")) {
				selectSearchType("ScheduleID");
				setSearchValue(schedule.scheduleId);
			} else if (schedule.searchBy.equalsIgnoreCase("ScheduleName")) {
				selectSearchType("ScheduleName");
				setSearchValue(schedule.scheduleName);
			} else if (schedule.searchBy.equalsIgnoreCase("Location")) {
				selectSearchType("Location");
				setSearchValue(schedule.location);
			} else if (schedule.searchBy
					.equalsIgnoreCase("Created By (First Name)")) {
				selectSearchType("Created By (First Name)");
				setSearchValue(schedule.searchValue);
			} else if (schedule.searchBy
					.equalsIgnoreCase("Created By (Last Name)")) {
				selectSearchType("Created By (Last Name)");
				setSearchValue(schedule.searchValue);
			} else {
				throw new ItemNotFoundException("Unknown Search Type "
						+ schedule.searchBy);
			}
		}
		if (schedule.activeStatus != null && !schedule.activeStatus.equals("")) {
			selectStatus(schedule.activeStatus);
		}
		if (schedule.scheduleType != null && !schedule.scheduleType.equals("")) {
			selectScheduleType(schedule.scheduleType);
		}

		this.clickGo();
		this.waitLoading();
	}

	/**
	 * This method used to verify schedule info is same with given schedule info
	 * 
	 * @param schedule
	 */
	public void verifyScheduleList(ScheduleData schedule) {
		if (schedule.searchBy != null && !schedule.searchBy.equals("")) {
			if (schedule.searchBy.equalsIgnoreCase("ScheduleID")) {
				verifyScheduleInfo("Schedule ID", schedule.scheduleId);
			} else if (schedule.searchBy.equalsIgnoreCase("ScheduleName")) {
				verifyScheduleInfo("Schedule Name", schedule.scheduleName);
			} else if (schedule.searchBy.equalsIgnoreCase("Location")) {
				verifyScheduleInfo("Location", schedule.location);
			} else if (schedule.searchBy
					.equalsIgnoreCase("Created By (First Name)")) {
				verifyScheduleInfo("Created By", schedule.createUser);
			} else if (schedule.searchBy
					.equalsIgnoreCase("Created By (Last Name)")) {
				verifyScheduleInfo("Created By", schedule.createUser);
			}
		}
		if (schedule.activeStatus != null && !schedule.activeStatus.equals("")) {
			verifyScheduleInfo("Status", schedule.activeStatus);
		}
		if (schedule.scheduleType != null && !schedule.scheduleType.equals("")) {
			verifyScheduleInfo("Schedule Type", schedule.scheduleType);
		}
	}

	/**
	 * Verify Specific schedule In the Search List
	 * 
	 * @param id
	 *            -Schedule id
	 */
	public void verifySchedulerInSearchList(String id) {
		RegularExpression rex = new RegularExpression(
				"^Schedule ID Schedule Name.*", false);
		boolean found = false;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		int rowCount = ((IHtmlTable) objs[0]).rowCount();
		for (int i = 1; i < rowCount; i++) {
			if (((IHtmlTable) objs[0]).getCellValue(i, 1).equals(id)) {
				found = true;
				break;
			}
		}
		if (!found) {
			Browser.unregister(objs);
			throw new ItemNotFoundException("Not Found given Schedule " + id);
		}
		Browser.unregister(objs);

		logger.info("Schedule " + id + " in Search List.");
	}

	/**
	 * Verify specific column value is the same with given value
	 * 
	 * @param colName
	 *            column Name
	 * @param value
	 */
	public void verifyScheduleInfo(String colName, String value) {
		int colNum = getColNum(colName);
		RegularExpression rex = new RegularExpression(
				"^Schedule ID Schedule Name.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		int rowCount = ((IHtmlTable) objs[0]).rowCount();
		if (rowCount > 1) {
			for (int i = 1; i < rowCount; i++) {
				String tmp = ((IHtmlTable) objs[0]).getCellValue(i, colNum).trim();
				if (!tmp.equals(value)) {
					if (!tmp.equalsIgnoreCase("All")) {
						Browser.unregister(objs);
						logger.error("Schedule Info "
								+ tmp + " is not Correct! ");
						throw new ItemNotFoundException(tmp
								+ " is different with given value " + value);
					}
				}
			}
		} else {
			Browser.unregister(objs);
			throw new ItemNotFoundException("No Schedule Found.");
		}

		Browser.unregister(objs);
	}
}
