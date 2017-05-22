package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;


import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.license.DatePeriodLicenseYearInfo;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * 
 * @Description:
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date  Sep 27, 2012
 */
public class LicMgrAddDatePeriodLicenseYearWidget extends LicMgrDatePeriodLicenseYearDetailsCommonWidget {
	
	private static LicMgrAddDatePeriodLicenseYearWidget _instance = null;
	
	private LicMgrAddDatePeriodLicenseYearWidget() {}
	
	public static LicMgrAddDatePeriodLicenseYearWidget getInstance() {
		if(_instance == null) {
			_instance = new LicMgrAddDatePeriodLicenseYearWidget();
		}
		
		return _instance;
	}
	
	public void selectLicenseYear(String licenseYear) {
		browser.selectDropdownList(Property.toPropertyArray(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false)), licenseYear, true, getWidget()[0]);
	}
	
	public void setupDatePeriodLicenseYearInfo(DatePeriodLicenseYearInfo info) {
		selectLicenseYear(info.getLicenseYear());
		setDates(info.getDates());
	}
	
	public boolean isFromDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.fromDate_ForDisplay", false))[0], input);
	}
	
	public boolean isToDateTextFieldValid(String input[]) {
		return verifyAutomaticDateCorrection((IText)browser.getTextField(".id", new RegularExpression("DatePeriodLicenseYearDateRangeView-\\d+\\.toDate_ForDisplay", false))[0], input);
		
	}
	
	public List<String> getLicenseYearElements() {
		return browser.getDropdownElements(".id", new RegularExpression("DatePeriodLicenseYearView-\\d+\\.licenseYear", false));
	}
	
	public boolean verifyLicenseYearOption() {
		List<String> years = getLicenseYearElements();
		int expected = DateFunctions.getCurrentYear() - 1;//starts from [current year - 1]
		for(int i = 0; i < years.size(); i ++) {
			if(!years.get(i).equals(String.valueOf(expected))) {
				logger.error("License Year drop down list option is incorrect. Expected is: " + expected + ", but actual is: " + years.get(i));
				return false;
			}
			expected++;
		}
		
		logger.info("License Year drop down list option are all correct.");
		return true;
	}
}
