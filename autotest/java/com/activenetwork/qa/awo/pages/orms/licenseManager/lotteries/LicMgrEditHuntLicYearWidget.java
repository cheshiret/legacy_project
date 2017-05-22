package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is for edit hunt license year
 * @author phoebe
 *
 */
public class LicMgrEditHuntLicYearWidget extends LicMgrHuntLicYearCommonWidget {
	private static LicMgrEditHuntLicYearWidget _instance = null;
	
	protected LicMgrEditHuntLicYearWidget() {
		super("Edit Hunt Licen(s|c)e Year");
	}
	
	public static LicMgrEditHuntLicYearWidget getInstance(){
		if(null == _instance){
			_instance = new LicMgrEditHuntLicYearWidget();
		}
		
		return _instance;
	}
	
	protected Property[] newIdTextFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.TEXT", ".text", "New");
	}
	
	protected Property[] statusDropdownListProp(){
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.active", false));
	}
	
	
	public boolean isIdTextFieldEditable(){
		return browser.checkHtmlObjectEnabled(this.newIdTextFieldProp());
	}
	
	public boolean isStatusTextFieldEditable(){
		return browser.checkHtmlObjectDisplayed(this.statusDropdownListProp());
	}
	
	public void selectStatus(String status){
		browser.selectDropdownList(this.statusDropdownListProp(), status);
	}
	
	public String getStatus(){
		return browser.getDropdownListValue(this.statusDropdownListProp(), 0);
	}
	
	public LicenseYear getLicenseYearInfo(){
		LicenseYear ly = new LicenseYear();
		ly.status = this.getStatus();
		ly.prdCategory = this.getProductCategory();
		ly.assignedProd = this.getAssignedProduct();
		ly.locationClass = this.getLocationClass();
		ly.licYear = this.getLicenseYear();
		ly.sellFromDate = this.getSellFromDateTime();
		ly.sellFromTime = this.getSellFromHourMinute();
		ly.sellFromAmPm = this.getSellFromAPM();
		ly.sellToDate = this.getSellToDateTime();
		ly.sellToTime = this.getSellToHourMinute();
		ly.sellToAmPm = this.getSellToAPM();
		ly.createUser = this.getCreateUser();
		ly.createLocation = this.getCreateLocation();
		ly.createTime = this.getCreateDateTime();
		ly.lastUpdatedUser = this.getLastUpdatedUser();
		ly.lastUpdatedLocation = this.getLastUpdatedLocation();
		ly.lastUpdatedTime = this.getLastUpdatedDateTime();
		return ly;
	}
	
	public void verifyLicenseYearInfo(LicenseYear expLY){
		LicenseYear actLY = this.getLicenseYearInfo();
		boolean passed = true;
		passed &= MiscFunctions.compareResult("hunt license year status:", expLY.status,	actLY.status);
		passed &= MiscFunctions.compareResult("hunt license year product category:", expLY.prdCategory,	actLY.prdCategory);
		passed &= MiscFunctions.compareResult("hunt license year assigned product:", expLY.assignedProd,	actLY.assignedProd);
		passed &= MiscFunctions.compareResult("hunt license year location class:", expLY.locationClass,	actLY.locationClass);
		passed &= MiscFunctions.compareResult("hunt license year year:", expLY.licYear,	actLY.licYear);

		passed &= MiscFunctions.compareResult("hunt license year sell from date/time:", expLY.sellFromDate,	actLY.sellFromDate);
		passed &= MiscFunctions.compareResult("hunt license year sell from hour/minute:", expLY.sellFromTime,	actLY.sellFromTime);
		passed &= MiscFunctions.compareResult("hunt license year sell from AM/PM:", expLY.sellFromAmPm,	actLY.sellFromAmPm);
		passed &= MiscFunctions.compareResult("hunt license year sell to date/time:", expLY.sellToDate,	actLY.sellToDate);
		passed &= MiscFunctions.compareResult("hunt license year sell to hour/minute:", expLY.sellToTime,	actLY.sellToTime);
		passed &= MiscFunctions.compareResult("hunt license year sell to AM/PM:", expLY.sellToAmPm,	actLY.sellToAmPm);

		passed &= MiscFunctions.compareResult("hunt license year creation user:", expLY.createUser,	actLY.createUser.replaceAll(" ", ""));
		passed &= MiscFunctions.compareResult("hunt license year creation location:", expLY.createLocation,	actLY.createLocation);
		passed &= MiscFunctions.compareResult("hunt license year creation date/time:", expLY.createTime, 
				actLY.createTime.split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear()));
		
		passed &= MiscFunctions.compareResult("hunt license year last updated user:", expLY.lastUpdatedUser,	actLY.lastUpdatedUser);
		passed &= MiscFunctions.compareResult("hunt license year last updated  location:", expLY.lastUpdatedLocation,	actLY.lastUpdatedLocation);
		passed &= MiscFunctions.compareResult("hunt license year last updated  date/time:", expLY.lastUpdatedTime,	(StringUtil.notEmpty(actLY.lastUpdatedTime)?
				actLY.lastUpdatedTime.split(String.valueOf(DateFunctions.getCurrentYear()))[0] + String.valueOf(DateFunctions.getCurrentYear()):StringUtil.EMPTY));
		
		if(!passed){
			throw new ErrorOnPageException("Hunt license year information may not correct, please check the log above!");
		}
		logger.info("The information for hunt license year is correct!");
	}
	
	public void setHuntLicYearInfo(LicenseYear ly) {
		this.setSellFromDate(ly.sellFromDate);
		if (StringUtil.isEmpty(ly.sellFromTime)) { // Time must be after the current time
			ly.sellFromTime = DateFunctions.addTimeMinutes(DateFunctions.getCurrentTimeFormated(false), 1, "hh:mm", "hh:mm");
			ly.sellFromAmPm = DateFunctions.getCurrentAMPM();
		}
		this.setSellFromTime(ly.sellFromTime);
		this.selectSellFromAPM(ly.sellFromAmPm);
		this.setSellToDate(ly.sellToDate);
		this.setSellToTime(ly.sellToTime);
		this.selectSellToAPM(ly.sellToAmPm);
	}
	
}
