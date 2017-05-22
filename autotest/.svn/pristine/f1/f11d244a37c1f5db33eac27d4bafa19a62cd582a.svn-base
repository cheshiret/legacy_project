package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
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
public class LicMgrLotteryLicenseYearDetailsWidget extends DialogWidget {
	private static LicMgrLotteryLicenseYearDetailsWidget _instance = null;
	
	private LicMgrLotteryLicenseYearDetailsWidget() {
		super("Add|Edit (Privilege|Licence) Lottery Licen(s|c)e Year");
	}
	
	public static LicMgrLotteryLicenseYearDetailsWidget getInstance() {
		if(_instance == null) _instance = new LicMgrLotteryLicenseYearDetailsWidget();
		
		return _instance;
	}
	
	private Property[] id() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.id", false));
	}
	
	private Property[] status() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.active", false));
	}
	
	private Property[] locationClass() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.locationClassId", false));
	}
	
	private Property[] licenseYear() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.licenseYear", false));
	}
	
	private Property[] sellFromDate() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay", false));
	}
	
	private Property[] sellFromTime() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellFromDate_time", false));
	}
	
	private Property[] sellFromAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellFromDate_ampm", false));
	}
	
	private Property[] sellToDate() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellToDate_date_ForDisplay", false));
	}
	
	private Property[] sellToTime() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellToDate_time", false));
	}
	
	private Property[] sellToAMPM() {
		return Property.toPropertyArray(".id", new RegularExpression("PrivilegeLotteryLicenseYearView-\\d+\\.sellToDate_ampm", false));
	}
	
	public String getID() {
		return browser.getObjectText(id());
	}
	
	public void selectStatus(String status) {
		browser.selectDropdownList(status(), status);
	}
	
	public void selectLocationClass(String locClass) {
		browser.selectDropdownList(locationClass(), locClass);
	}
	
	public void selectLicenseYear(String licYear) {
		browser.selectDropdownList(licenseYear(), licYear);
	}
	
	public void setSellFromDate(String date) {
		browser.setCalendarField(sellFromDate(), date);
	}
	
	public void setSellFromTime(String time) {
		browser.setTextField(sellFromTime(), time);
	}
	
	public void selectSellFromAMPM(String ampm) {
		browser.selectDropdownList(sellFromAMPM(), ampm);
	}
	
	public void setSellToDate(String date) {
		browser.setCalendarField(sellToDate(), date);
	}
	
	public void setSellToTime(String time) {
		browser.setTextField(sellToTime(), time);
	}
	
	public void selectSellToAMPM(String ampm) {
		browser.selectDropdownList(sellToAMPM(), ampm);
	}
	
	public String getLocationClass() {
		return browser.getDropdownListValue(locationClass(), 0);
	}
	
	public String getLicenseYear() {
		return browser.getDropdownListValue(licenseYear(), 0);
	}
	
	public void setupLicenseYear(LicenseYear ly) {
		//Jane[2014-6-11]:Updated for setup script, for records which has license year already, we will just modify the records
		String locationClassUI = this.getLocationClass();
		String licenseYearUI = this.getLicenseYear();
		if(!StringUtil.isEmpty(ly.locationClass) && !ly.locationClass.equals(locationClassUI)) {
			this.selectLocationClass(ly.locationClass);
		}
		if(!StringUtil.isEmpty(ly.licYear) && !ly.licYear.equals(licenseYearUI)) {
			this.selectLicenseYear(ly.licYear);
		}
		if(!StringUtil.isEmpty(ly.sellFromDate)) {
			this.setSellFromDate(ly.sellFromDate);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(ly.sellFromTime)){
			this.setSellFromTime(ly.sellFromTime);
			ajax.waitLoading();
		} 
		if(!StringUtil.isEmpty(ly.sellFromAmPm)) {
			this.selectSellFromAMPM(ly.sellFromAmPm);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(ly.sellToDate)){
			this.setSellToDate(ly.sellToDate);
			ajax.waitLoading();
		} 
		if(!StringUtil.isEmpty(ly.sellToTime)) {
			this.setSellToTime(ly.sellToTime);
			ajax.waitLoading();
		}
		if(!StringUtil.isEmpty(ly.sellToAmPm)) {
			this.selectSellToAMPM(ly.sellToAmPm);
			ajax.waitLoading();
		}
	}
}
