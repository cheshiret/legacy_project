package com.activenetwork.qa.awo.pages.orms.licenseManager.configuration.system;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.EFTScheduleInfo;
import com.activenetwork.qa.awo.pages.orms.licenseManager.common.configuration.LicMgrSystemConfigurationPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Dec 16, 2011
 */
public class LicMgrEFTSchedulesConfigurationPage extends LicMgrSystemConfigurationPage {
	
	private static LicMgrEFTSchedulesConfigurationPage _instance = null;
	
	protected LicMgrEFTSchedulesConfigurationPage() {}
	
	public static LicMgrEFTSchedulesConfigurationPage getInstance() {
		if(null == _instance) {
			_instance = new LicMgrEFTSchedulesConfigurationPage();
		}
		
		return _instance;
	}
	
	@Override
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", new RegularExpression("eftSchedule|eftschedulesearchresultlist", false));
	}
	
	private String ID_COL = "ID";
	private String STATUS_COL = "Status";
	private String NAME_COL = "Name";
	private String INVOICE_FREQUENCY_COL = "Invoice Frequency";
	private String START_MONTH_COL = "Start Month";
	private String START_DAY_COL = "Start Day";
	private String INVOICE_DAY_OFFSET_COL = "Invoice Day Offset";
	private String TRANSMISSION_DAY_OFFSET_COL = "Transmission Day Offset";
	private String NUM_OF_VENDORS_ACTIVE_OTHER_COL = "# of Vendors Active/Other";
	private String CREATION_USER_COL = "Creation User";
	private String CREATION_LOCATION_COL = "Creation Location";
	private String CREATION_DATE_TIME_COL = "Creation Date/Time";
	private String LAST_UPDATED_USER_COL = "Last Updated User";
	private String LAST_UPDATED_LOCATION_COL = "Last Updated Location";
	private String LAST_UPDATED_DATE_TIME_COL = "Last Updated Date/Time";
	
	/**
	 * Check EFT schedule exists or not identified by unique Frequency
	 * @param frequency
	 * @return
	 */
	public boolean checkEFTScheduelExists(String frequency) {
		IHtmlTable table = this.getTableObject(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false));
		List<String> frequencies = table.getColumnValues(table.findColumn(0, INVOICE_FREQUENCY_COL));
		for(String str : frequencies) {
			if(str.trim().equals(frequency)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean checkEFTScheduleExistByName(String name){
		IHtmlTable table = this.getTableObject(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false));
		return (table.findRow(2, name))>0;
	}
	
	/**
	 * Get EFT Schedule id identified by unique frequency.
	 * Because the system shall only allow schedules with an unique frequency for the entire Contract
	 * @param frequency
	 * @return
	 */
	public String getEFTScheduleID(String frequency) {
		//return this.getTableRowValue("eftSchedule", "EFT Frequency", frequency).get(0);
		return this.getTableRowValue(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false), INVOICE_FREQUENCY_COL, frequency).get(0);
	}
	
	public String getEFTScheduleIDViaName(String name) {
		return this.getTableRowValue(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false), NAME_COL, name).get(0);
	}
	
	/**
	 * Get a specific EFT schedule detail info
	 * @param id
	 * @return
	 */
	public EFTScheduleInfo getEFTScheduleById(String id) {
		IHtmlTable table = getTableObject(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false));
		List<String> list = this.getTableRowValue(new RegularExpression("eftSchedule|eftschedulesearchresultlist", false), "ID", id);
		
		EFTScheduleInfo schedule = new EFTScheduleInfo();
		schedule.id = list.get(table.findColumn(0, ID_COL));
		schedule.status = list.get(table.findColumn(0, STATUS_COL));
		schedule.name = list.get(table.findColumn(0, NAME_COL));
		schedule.frequency = list.get(table.findColumn(0, INVOICE_FREQUENCY_COL));
		schedule.startMonth = list.get(table.findColumn(0, START_MONTH_COL));
		schedule.startDay = list.get(table.findColumn(0, START_DAY_COL));
		schedule.invoiceDayOffset = list.get(table.findColumn(0, INVOICE_DAY_OFFSET_COL));
		schedule.transmissionDayOffset = list.get(table.findColumn(0, TRANSMISSION_DAY_OFFSET_COL));
		String temp = list.get(table.findColumn(0, NUM_OF_VENDORS_ACTIVE_OTHER_COL));
		schedule.numOfActiveVendors = Integer.parseInt(temp.split("/")[0].trim());
		schedule.numOfOtherVendors = Integer.parseInt(temp.split("/")[1].trim());
		schedule.creationUser = list.get(table.findColumn(0, CREATION_USER_COL));
		schedule.creationLocation = list.get(table.findColumn(0, CREATION_LOCATION_COL));
		schedule.creationDateTime = list.get(table.findColumn(0, CREATION_DATE_TIME_COL));
		schedule.lastUpdatedUser = list.get(table.findColumn(0, LAST_UPDATED_USER_COL));
		schedule.lastUpdatedLocation = list.get(table.findColumn(0, LAST_UPDATED_LOCATION_COL));
		schedule.lastUpdatedDateTime = list.get(table.findColumn(0, LAST_UPDATED_DATE_TIME_COL));
		
		return schedule;
	}
	
	/**
	 * Verify whether actual EFT schedule value matches with expected or not
	 * @param expectedSchedule
	 * @return
	 */
	public void verifyEFTScheduleInfo(EFTScheduleInfo expectedSchedule) {
		boolean result = true;
		EFTScheduleInfo actualSchedule = getEFTScheduleById(expectedSchedule.id);
		
		logger.info("Verify expected EFT schdule(ID#=" + expectedSchedule.id + ") with actual value.");
		if(!expectedSchedule.id.equals(actualSchedule.id)) {
			result &= MiscFunctions.compareResult("EFT Schedule - ID", expectedSchedule.id, actualSchedule.id);
		}
		if(!expectedSchedule.name.equals(actualSchedule.name)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Name", expectedSchedule.name, actualSchedule.name);
		}
		if(!expectedSchedule.frequency.equals(actualSchedule.frequency)) {
			result &= MiscFunctions.compareResult("EFT Schedule - EFT Frequency", expectedSchedule.frequency, actualSchedule.frequency);
		}
		if(!expectedSchedule.startMonth.equals(actualSchedule.startMonth)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Start Month", expectedSchedule.startMonth, actualSchedule.startMonth);
		}
		if(!expectedSchedule.startDay.equals(actualSchedule.startDay)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Start Day", expectedSchedule.startDay, actualSchedule.startDay);
		}
		if(!expectedSchedule.invoiceDayOffset.equals(actualSchedule.invoiceDayOffset)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Invoice Day Offset", expectedSchedule.invoiceDayOffset, actualSchedule.invoiceDayOffset);
		}
		if(!expectedSchedule.transmissionDayOffset.equals(actualSchedule.transmissionDayOffset)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Transmission Day Offset", expectedSchedule.transmissionDayOffset, actualSchedule.transmissionDayOffset);
		}
		if(!expectedSchedule.creationUser.equals(actualSchedule.creationUser)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Creation User", expectedSchedule.creationUser, actualSchedule.creationUser);
		}
		if(!expectedSchedule.creationLocation.equals(actualSchedule.creationLocation)) {
			result &= MiscFunctions.compareResult("EFT Schedule - Creation Location", expectedSchedule.creationLocation, actualSchedule.creationLocation);
		}
		String expectedCreationDateTime = DateFunctions.formatDate(expectedSchedule.creationDateTime, "E MMM d yyyy");
		if(!actualSchedule.creationDateTime.contains(expectedCreationDateTime)) {
			logger.error("EFT Schedule - Creation Date/Time doesn't match. Expected value is: " + expectedCreationDateTime + " but actual value is: " + actualSchedule.creationDateTime);
			result &= false;
		}
		
		if(!result) {
			throw new ErrorOnPageException("Actual EFT Schedule(ID#=" + actualSchedule.id + ") doesn't match with expected.");
		}
		logger.info("Actual EFT Schedule(ID#=" + expectedSchedule.id + ") really matches with expected.");
		
	}
}
