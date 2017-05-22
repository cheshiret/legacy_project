package com.activenetwork.qa.awo.pages.orms.inventoryManager.dockslip;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.util.NumberUtil;
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
 * @Date  Sep 11, 2012
 */
public abstract class InvMgrSlipDetailsCommonPage extends InvMgrDockSlipCommonPage {
	protected Property[] okButtonAProp(){
		return Property.concatPropertyArray(a(), ".text","OK");
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "marinaSlipDetailUI");
	}
	
	public String getSlipID() {
		return browser.getObjectText(".id", new RegularExpression("SlipView-\\d+\\.id", false)).split("Slip ID")[1].trim();
	}
	
	public String getSlipCode() {
		return browser.getObjectText(".id", new RegularExpression("SlipView-\\d+\\.slipCode", false)).split("Slip Code")[1].trim();
	}
	
	public String getSlipName() {
		return browser.getObjectText(".id", new RegularExpression("SlipView-\\d+\\.name", false)).replace("Slip Name","").trim();
	}
	
	public String getParentDockArea() {
		return browser.getObjectText(".id", new RegularExpression("SlipView-\\d+\\.dock", false)).split("Parent Dock/Area")[1].trim();
	}
	
	public String getSlipType() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipView-\\d+\\.slipType", false));
	}
	
	public void clickNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts", true);
	}
	
	public void clickAddNotesAlerts() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Notes/Alerts", true);
	}
	
	public void clickDuplicateThisSlip() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Duplicate this Slip", true);
	}
	
	public void clickDeleteThisSlip() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Delete this Slip", true);
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel");
	}
	
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}
	
	/**
	 * ----------------------------------------------------Slip Information----------------------------------------------------
	 */
	
	public boolean isSlipRelationTypeDropdownListEnabled() {
		IHtmlObject objs[] = browser.getDropdownList(".id", new RegularExpression("SlipView-\\d+\\.relationType", false));
		if(objs.length < 1) {
			return false;
		}
		boolean enabled = ((ISelect)objs[0]).isEnabled();
		Browser.unregister(objs);
		
		return enabled;
	}
	
	public void selectSlipRelationType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("SlipView-\\d+\\.relationType", false), type);
	}
	
	public void selectNSSGroup(String group) {
		browser.selectDropdownList(".id", new RegularExpression("SlipView-\\d+\\.parentProductID", false), group);
	}
	
	public void setSlipCode(String code) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.slipCode", false), code);
	}
	
	public void setSlipName(String name) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.name", false), name);
	}
	
	public void selectSlipType(String type) {
		browser.selectDropdownList(".id", new RegularExpression("SlipView-\\d+\\.slipTypeID", false), type);
	}
	
	public void setDescription(String dscr) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.description", false), dscr);
	}
	
	public void selectParentDockArea(String parentDock) {
		browser.selectDropdownList(".id", new RegularExpression("SlipView-\\d+\\.dockID", false), parentDock);
	}
	
	public void setSlipLength(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.lengthStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void setSlipWidth(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.widthStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void setSlipDepth(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.depthStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void setMinBoatLength(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.minBoatLengthStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void setMaxBoatLength(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.maxBoatLengthStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void setBoatSpacing(int ft) {
		browser.setTextField(".id", new RegularExpression("SlipView-\\d+\\.boatSpacingStr:ZERO_TO_NULL", false), String.valueOf(ft));
	}
	
	public void selectWebVisible(boolean visible) {
		browser.selectDropdownList(".id", new RegularExpression("SlipView-\\d+\\.webReservable", false), visible ? "Y" : "N");
	}
	
	public String getSlipRelationType() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipView-\\d+\\.relationType", false));
	}
	
	public String getDescription() {
		return browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.description", false));
	}
	
	public int getSlipLength() {
		return Integer.parseInt(browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.lengthStr\\:ZERO_TO_NULL", false)));
	}
	
	public int getSlipWidth() {
		return Integer.parseInt(browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.widthStr\\:ZERO_TO_NULL", false)));
	}
	
	public int getSlipDepth() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.depthStr\\:ZERO_TO_NULL", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	public int getMinBoatLength() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.minBoatLengthStr\\:ZERO_TO_NULL", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	public int getMaxBoatLength() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.maxBoatLengthStr\\:ZERO_TO_NULL", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public int getBoatSpacing() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("SlipView-\\d+\\.boatSpacingStr\\:ZERO_TO_NULL", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public boolean getWebVisible() {
		return browser.getDropdownListValue(".id", new RegularExpression("SlipView-\\d+\\.webReservable", false)).equals("Y") ? true : false;
	}
	
	/**
	 * ----------------------------------------------------Slip Attributes----------------------------------------------------
	 */
	
	public void selectAccessibleSite(boolean accessible) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[104\\]\\:BOOLEAN_YESNO", false), accessible ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public void setPeopleMaximumNum(int num) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false), String.valueOf(num));
	}
	
	public void setVehicleMaximumNum(int num) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[9\\]", false), String.valueOf(num));
	}
	
	public void selectCheckInHour(String hour) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_hour", false), hour);
	}
	
	public void selectCheckInMinute(String min) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_minute", false), min);
	}
	
	public void selectCheckInAmPm(String amPm) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_ampm", false), amPm);
	}
	
	public void selectCheckOutHour(String hour) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_hour", false), hour);
	}
	
	public void selectCheckOutMinute(String min) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_minute", false), min);
	}
	
	public void selectCheckOutAmPm(String amPm) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_ampm", false), amPm);
	}
	
	public void selectElectricity(int electricity) {
		if(browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false))){
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false), String.valueOf(electricity));
		}
	}
	
	public void selectWaterHookup(boolean has) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[32\\]\\:BOOLEAN_YESNO", false), has ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public void selectSewerHookup(boolean has) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[33\\]\\:BOOLEAN_YESNO", false), has ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	public void selectFullHookup(int full) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[239\\]", false), String.valueOf(full));
	}
	
	/**
	 * 
	 * @param direction - 'North' or 'South'
	 */
	public void selectLatitudeChannelDirection(String direction) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8001\\]", false), direction);
	}
	
	public void setLatitudeDegree(int degree) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8002\\]", false), String.valueOf(degree));
	}
	
	public void setLatitudeMinute(int minute) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8003\\]", false), String.valueOf(minute));
	}
	
	public void setLatitudeSecond(int second) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8004\\]", false), String.valueOf(second));
	}
	
	public void selectLongitudeChannelDirection(String direction) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8005\\]", false), direction);
	}
	
	public void setLongitudeDegree(int degree) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8006\\]", false), String.valueOf(degree));
	}
	
	public void setLongitudeMinute(int minute) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8007\\]", false), String.valueOf(minute));
	}
	
	public void setLongitudeSecond(int second) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8008\\]", false), String.valueOf(second));
	}
	
	public void setBasePricingLength(int length) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8025\\]", false), String.valueOf(length));
	}
	
	public void setSeasonalContractLength(int length) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false), String.valueOf(length));
	}
	
	public void setSeasonalContractStartDate(int startYear) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false), String.valueOf(startYear));
	}
	
	public void setSeasonalContractLength() {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false), StringUtil.EMPTY);
	}
	
	public void setSeasonalContractStartDate() {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false), StringUtil.EMPTY);
	}
	
	public void selectBoatRequired(boolean required) {
		selectBoatRequired(required ? OrmsConstants.YES_STATUS : OrmsConstants.NO_STATUS);
	}
	
	/**
	 * 
	 * @param required - 'Yes' or 'No'
	 */
	public void selectBoatRequired(String required) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8045\\]\\:BOOLEAN_YESNO", false), required);
	}
	
	public void selectTypeOfUse(String type) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[102\\]", false), type);
	}
	
	public boolean isAccessibleSiteFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[104\\]\\:BOOLEAN_YESNO", false));
	}
	
	public boolean getAccessibleSite() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[104\\]\\:BOOLEAN_YESNO", false)).equals(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public boolean isPeopleMaximumNumFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false));
	}
	
	public int getPeopleMaximumNum() {
		return Integer.parseInt(browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false)));
	}
	
	public int getPeopleMaximumNumOfChild(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false));
		String text = objs[objs.length-1].text().replaceAll("Maximum #", "").trim();
		Browser.unregister(objs);
		if(StringUtil.notEmpty(text)){
			return Integer.parseInt(text);
		}else return 0;
	}
	
	public boolean isVehicleMaximumNumFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[9\\]", false));
	}
	
	public int getVehicleMaximumNum() {
		return Integer.parseInt(browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[9\\]", false)));
	}
	
	public int getVehicleMaximumNumOfChild() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[9\\]", false));
		String text = objs[0].text().replaceAll("Maximum #", "").trim();
		Browser.unregister(objs);
		if(StringUtil.notEmpty(text)){
			return Integer.parseInt(text);
		}else return 0;
	}
	
	public boolean isCheckInHourFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_hour", false));
	}
	
	public String getCheckInHour() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_hour", false));
	}
	
	public boolean isCheckInTimeFieldExists(){
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("^Checkin Time.*",false));
	}
	
	public String getCheckInTime(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Checkin Time.*",false));
		String text = objs[0].text().replaceAll("Checkin Time", "").trim();
		Browser.unregister(objs);
		return text;
	}
	
	public boolean isCheckInMinuteFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_minute", false));
	}
	
	public String getCheckInMinute() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_minute", false));
	}
	
	public boolean isCheckInAmPmFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_ampm", false));
	}
	
	public String getCheckInAmPm() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[15\\]\\:TIME_ampm", false));
	}
	
	public boolean isCheckOutHourseFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_hour", false));
	}
	
	public String getCheckOutHour() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_hour", false));
	}
	
	public boolean isCheckOutTimeFieldExists(){
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text", new RegularExpression("^Checkout Time.*",false));
	}
	
	public String getCheckOutTime(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^Checkout Time.*",false));
		String text = objs[0].text().replaceAll("Checkout Time", "").trim();
		Browser.unregister(objs);
		return text;
	}
	
	public boolean isCheckOutMinuteFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_minute", false));
	}
	
	public String getCheckOutMinute() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_minute", false));
	}
	
	public boolean isCheckOutAmPmFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_ampm", false));
	}
	
	public String getCheckOutAmPm() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[16\\]\\:TIME_ampm", false));
	}
	
	public boolean isElectricityFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false));
	}
	
	public int getElectricity() {
		return Integer.parseInt(browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false)));
	}
	
	public boolean isWaterHookupFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[32\\]\\:BOOLEAN_YESNO", false));
	}
	
	public boolean getWaterHookup() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[32\\]\\:BOOLEAN_YESNO", false)).equals(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public boolean isSewerHookupFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[33\\]\\:BOOLEAN_YESNO", false));
	}
	
	public boolean getSewerHookup() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[33\\]\\:BOOLEAN_YESNO", false)).equals(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public boolean isFullHookupFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[239\\]", false));
	}
	
	public int getFullHookup() {
		return Integer.parseInt(browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[239\\]", false)));
	}
	
	public boolean isLatitudeChannelDirectionFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8001\\]", false));
	}
	
	/**
	 * 
	 * @return - 'North' or 'South'
	 */
	public String getLatitudeChannelDirection() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8001\\]", false));
	}
	
	public boolean isLatitudeDegreeFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8002\\]", false));
	}
	
	public int getLatitudeDegree() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8002\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	public boolean isLatitudeMinuteFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8003\\]", false));
	}
	
	public int getLatitudeMinute() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8003\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public boolean isLatitudeSecondFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8004\\]", false));
	}
	
	public int getLatitudeSecond() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8004\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	public boolean isLongitudeChannelDirectionFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8005\\]", false));
	}
	
	public String getLongitudeChannelDirection() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8005\\]", false));
	}
	
	public boolean isLongitudeDegreeFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8006\\]", false));
	}
	
	public int getLongitudeDegree() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8006\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public boolean isLongitudeMinuteFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8007\\]", false));
	}
	
	public int getLongitudeMinute() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8007\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		return 0;
	}
	
	public boolean isLongitudeSecondFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8008\\]", false));
	}
	
	public int getLongitudeSecond() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8008\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public boolean isBasePricingLengthFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8025\\]", false));
	}
	
	public int getBasePricingLength() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8025\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public int getBasePricingLengthOfChild() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8025\\]", false));
		String text = objs[0].text().replaceAll("Base Pricing Length", "").trim();
		if(!StringUtil.isEmpty(text)) {
			return Integer.parseInt(text);
		}
		
		return 0;
	}
	
	public boolean isSeasonalContractLengthFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false));
	}
	
	public int getSeasonalContractLength() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public int getSeasonalContractLengthOfChild() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false));
		String text = objs[0].text().replaceAll("Contract Length \\(yrs\\)", "").trim();
		if(!StringUtil.isEmpty(text)) {
			return Integer.parseInt(text);
		}
		
		return 0;
	}
	
	public boolean isSeasonalContractStartDateFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false));
	}
	
	public int getSeasonalContractStartDate() {
		String str = browser.getTextFieldValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false));
		if(!StringUtil.isEmpty(str)) {
			return Integer.parseInt(str);
		}
		
		return 0;
	}
	
	public int getSeasonalContractStartDateOfChild() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false));
		String text = objs[0].text().replaceAll("Contract Start Date \\(yr\\)", "").trim();
		if(!StringUtil.isEmpty(text)) {
			return Integer.parseInt(text);
		}
		
		return 0;
	}
	
	public boolean isBoatRequiredFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8045\\]\\:BOOLEAN_YESNO", false));
	}
	
	public boolean getBoatRequired() {
		return getBoatRequiredStr().equals(OrmsConstants.YES_STATUS) ? true : false;
	}
	
	public String getBoatRequiredStr() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8045\\]\\:BOOLEAN_YESNO", false));
	}
	
	public boolean isTypeofUseFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[102\\]", false));
	}
	
	public String getTypeOfUse() {
		return browser.getDropdownListValue(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[102\\]", false));
	}
	
	public void setupSlipInfo(SlipInfo slip) {
		//slip information
		if(isSlipRelationTypeDropdownListEnabled()) {
			if(!StringUtil.isEmpty(slip.getRelationType())) {
				selectSlipRelationType(slip.getRelationType());
				ajax.waitLoading();
				waitLoading();
			}
		}
		if(!StringUtil.isEmpty(slip.getNssGroup())) {
			selectNSSGroup(slip.getNssGroup());
			ajax.waitLoading();
			waitLoading();
		}
		if(!StringUtil.isEmpty(slip.getCode())) {
			setSlipCode(slip.getCode());
		}
		if(!StringUtil.isEmpty(slip.getName())) {
			setSlipName(slip.getName());
		}
		if(!StringUtil.isEmpty(slip.getType())) {
			if(!slip.getType().equals(this.getSlipType())){
				selectSlipType(slip.getType());
				ajax.waitLoading();
				waitLoading();
			}
		}
		if(!StringUtil.isEmpty(slip.getDescription())) {
			setDescription(slip.getDescription());
		}
		if(!StringUtil.isEmpty(slip.getParentDockArea())) {
			selectParentDockArea(slip.getParentDockArea());
		}
		
		if(!StringUtil.isEmpty(slip.getRelationType()) && !slip.getRelationType().equalsIgnoreCase("NSS Child")) {
			if(NumberUtil.isGreaterThanZero(slip.getLength())) {
				setSlipLength(slip.getLength());
			}
			if(NumberUtil.isGreaterThanZero(slip.getWidth())) {
				setSlipWidth(slip.getWidth());
			}
			if(NumberUtil.isGreaterThanZero(slip.getDepth())) {
				setSlipDepth(slip.getDepth());
			}
			if(NumberUtil.isGreaterThanZero(slip.getMinBoatLength())) {
				setMinBoatLength(slip.getMinBoatLength());
			}
			if(NumberUtil.isGreaterThanZero(slip.getMaxBoatLength())) {
				setMaxBoatLength(slip.getMaxBoatLength());
			}
			if(NumberUtil.isGreaterThanZero(slip.getBoatSpacing())) {
				setBoatSpacing(slip.getBoatSpacing());
			}
			selectWebVisible(slip.isWebVisible());
			//for slip type is 'slip group' not have below attributes
			if(slip.getType().equalsIgnoreCase("Full attributes")){
				//slip attributes
				selectAccessibleSite(slip.isAccessibleSite());
				if(NumberUtil.isGreaterThanZero(slip.getMaxNumOfPeople())) {
					setPeopleMaximumNum(slip.getMaxNumOfPeople());
				}
				if(NumberUtil.isGreaterThanZero(slip.getMaxNumOfVehicle())) {
					setVehicleMaximumNum(slip.getMaxNumOfVehicle());
				}
				//check in/out times
				if(!StringUtil.isEmpty(slip.getCheckInHour())) {
					selectCheckInHour(slip.getCheckInHour());
				}
				if(!StringUtil.isEmpty(slip.getCheckInMinute())) {
					selectCheckInMinute(slip.getCheckInMinute());
				}
				if(!StringUtil.isEmpty(slip.getCheckInAmPm())) {
					selectCheckInAmPm(slip.getCheckInAmPm());
				}
				if(!StringUtil.isEmpty(slip.getCheckOutHour())) {
					selectCheckOutHour(slip.getCheckOutHour());
				}
				if(!StringUtil.isEmpty(slip.getCheckOutMinute())) {
					selectCheckOutMinute(slip.getCheckOutMinute());
				}
				if(!StringUtil.isEmpty(slip.getCheckOutAmPm())) {
					selectCheckOutAmPm(slip.getCheckOutAmPm());
				}
				//hookups
				if(NumberUtil.isGreaterThanZero(slip.getElectrictity())) {
				 selectElectricity(slip.getElectrictity());
				}
				selectWaterHookup(slip.isWaterHookup());
				selectSewerHookup(slip.isSewerHookup());
				if(NumberUtil.isGreaterThanZero(slip.getFull())) {
					selectFullHookup(slip.getFull());
				}
				
				if(NumberUtil.isGreaterThanZero(slip.getBasePricingLength())) {
					setBasePricingLength(slip.getBasePricingLength());
				}
				
				if(NumberUtil.isGreaterThanZero(slip.getSeasonalContractLength())) {
					setSeasonalContractLength(slip.getSeasonalContractLength());
				}
				if(NumberUtil.isGreaterThanZero(slip.getSeasonalContractStartDate())) {
					setSeasonalContractStartDate(slip.getSeasonalContractStartDate());
				}
//				if(slip.isBoatRequired()) {
//					selectBoatRequired(slip.isBoatRequired());
//				}
				if(!StringUtil.isEmpty(slip.getTypeOfUse())) {
					selectTypeOfUse(slip.getTypeOfUse());
				}
			}
			
		}
		//latitude
		if(!StringUtil.isEmpty(slip.getLatitudeChannelDirection())) {
			selectLatitudeChannelDirection(slip.getLatitudeChannelDirection());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLatitudeDegree())) {
			setLatitudeDegree(slip.getLatitudeDegree());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLatitudeMinute())) {
			setLatitudeMinute(slip.getLatitudeMinute());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLatitudeSecond())) {
			setLatitudeSecond(slip.getLatitudeSecond());
		}
		//longitude
		if(!StringUtil.isEmpty(slip.getLongitudeChannelDirection())) {
			selectLongitudeChannelDirection(slip.getLongitudeChannelDirection());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLongitudeDegree())) {
			setLongitudeDegree(slip.getLongitudeDegree());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLongitudeMinute())) {
			setLongitudeMinute(slip.getLongitudeMinute());
		}
		if(NumberUtil.isGreaterThanZero(slip.getLongitudeSecond())) {
			setLongitudeSecond(slip.getLongitudeSecond());
		}
	}
	
	public SlipInfo getSlipInfo() {
		SlipInfo slip = new SlipInfo();
		//slip information
		slip.setRelationType(getSlipRelationType());
		slip.setCode(getSlipCode());
		slip.setName(getSlipName());
		slip.setType(getSlipType());
		slip.setDescription(getDescription());
		slip.setParentDockArea(getParentDockArea());
		slip.setLength(getSlipLength());
		slip.setWidth(getSlipWidth());
		slip.setDepth(getSlipDepth());
		slip.setMinBoatLength(getMinBoatLength());
		slip.setMaxBoatLength(getMaxBoatLength());
		slip.setBoatSpacing(getBoatSpacing());
		slip.setWebVisible(getWebVisible());
		
		//slip attribute
		if(isAccessibleSiteFieldExists()) {
			slip.setAccessibleSite(getAccessibleSite());
		}
		if(slip.getRelationType().equalsIgnoreCase("NSS Child")){
			if(isPeopleMaximumNumFieldExists()) {
				slip.setMaxNumOfPeople(getPeopleMaximumNumOfChild());
			}
			if(isVehicleMaximumNumFieldExists()) {
				slip.setMaxNumOfVehicle(getVehicleMaximumNumOfChild());
			}
			
			if(isCheckInTimeFieldExists()) {
				String checkInTime = getCheckInTime();
				slip.setCheckInHour(checkInTime.split(":")[0]);
				slip.setCheckInMinute(checkInTime.split(":")[1].split(" ")[0]);
				slip.setCheckInAmPm(checkInTime.split(" ")[1]);
			}
			
			if(isCheckOutTimeFieldExists()){
				String checkOutTime = getCheckOutTime();
				slip.setCheckOutHour(checkOutTime.split(":")[0]);
				slip.setCheckOutMinute(checkOutTime.split(":")[1].split(" ")[0]);
				slip.setCheckOutAmPm(checkOutTime.split(" ")[1]);
			}
			
			if(isBasePricingLengthFieldExists()) {
				slip.setBasePricingLength(getBasePricingLengthOfChild());
			}
			if(isSeasonalContractLengthFieldExists()) {
				slip.setSeasonalContractLength(getSeasonalContractLengthOfChild());
			}
			if(isSeasonalContractStartDateFieldExists()) {
				slip.setSeasonalContractStartDate(getSeasonalContractStartDateOfChild());
			}
		}else{
			if(isPeopleMaximumNumFieldExists()) {
				slip.setMaxNumOfPeople(getPeopleMaximumNum());
			}
			if(isVehicleMaximumNumFieldExists()) {
				slip.setMaxNumOfVehicle(getVehicleMaximumNum());
			}
			if(isCheckInHourFieldExists()) {
				slip.setCheckInHour(getCheckInHour());
			}
			if(isCheckInMinuteFieldExists()) {
				slip.setCheckInMinute(getCheckInMinute());
			}
			if(isCheckInAmPmFieldExists()) {
				slip.setCheckInAmPm(getCheckInAmPm());
			}
			if(isCheckOutHourseFieldExists()) {
				slip.setCheckOutHour(getCheckOutHour());
			}
			if(isCheckOutMinuteFieldExists()) {
				slip.setCheckOutMinute(getCheckOutMinute());
			}
			if(isCheckOutAmPmFieldExists()) {
				slip.setCheckOutAmPm(getCheckOutAmPm());
			}
			if(isBasePricingLengthFieldExists()) {
				slip.setBasePricingLength(getBasePricingLength());
			}
			if(isSeasonalContractLengthFieldExists()) {
				slip.setSeasonalContractLength(getSeasonalContractLength());
			}
			if(isSeasonalContractStartDateFieldExists()) {
				slip.setSeasonalContractStartDate(getSeasonalContractStartDate());
			}
		}
		
		if(isElectricityFieldExists()) {
			slip.setElectrictity(getElectricity());
		}
		if(isWaterHookupFieldExists()) {
			slip.setWaterHookup(getWaterHookup());
		}
		if(isSewerHookupFieldExists()) {
			slip.setSewerHookup(getSewerHookup());
		}
		if(isFullHookupFieldExists()) {
			slip.setFull(getFullHookup());
		}
		if(isLatitudeChannelDirectionFieldExists()) {
			slip.setLatitudeChannelDirection(getLatitudeChannelDirection());
		}
		if(isLatitudeDegreeFieldExists()) {
			slip.setLatitudeDegree(getLatitudeDegree());
		}
		if(isLatitudeMinuteFieldExists()) {
			slip.setLatitudeMinute(getLatitudeMinute());
		}
		if(isLatitudeSecondFieldExists()) {
			slip.setLatitudeSecond(getLatitudeSecond());
		}
		if(isLongitudeChannelDirectionFieldExists()) {
			slip.setLongitudeChannelDirection(getLongitudeChannelDirection());
		}
		if(isLongitudeDegreeFieldExists()) {
			slip.setLongitudeDegree(getLongitudeDegree());
		}
		if(isLongitudeMinuteFieldExists()) {
			slip.setLongitudeMinute(getLongitudeMinute());
		}
		if(isLongitudeSecondFieldExists()) {
			slip.setLongitudeSecond(getLongitudeSecond());
		}
		
//		if(isBoatRequiredFieldExists()) {
//			slip.setBoatRequired(getBoatRequired());
//		}
		if(isTypeofUseFieldExists()) {
			slip.setTypeOfUse(getTypeOfUse());
		}
		
		return slip;
	}
	
	public boolean compareSlipDetailInfo(SlipInfo expected) {
		SlipInfo actual = getSlipInfo();
		
		boolean result = true;
		//slip information
		result &= MiscFunctions.compareResult("Slip Relation Type", expected.getRelationType(), actual.getRelationType());
		result &= MiscFunctions.compareResult("Slip Code", expected.getCode(), actual.getCode());
		result &= MiscFunctions.compareResult("Slip Name", expected.getName(), actual.getName());
		result &= MiscFunctions.compareResult("Slip Type", expected.getType(), actual.getType());
		result &= MiscFunctions.compareResult("Description", expected.getDescription(), actual.getDescription());
		result &= MiscFunctions.compareResult("Parent Dock/Area", expected.getParentDockArea(), actual.getParentDockArea());
		result &= MiscFunctions.compareResult("Slip Lenth", expected.getLength(), actual.getLength());
		result &= MiscFunctions.compareResult("Slip Width", expected.getWidth(), actual.getWidth());
		result &= MiscFunctions.compareResult("Slip Depth", expected.getDepth(), actual.getDepth());
		result &= MiscFunctions.compareResult("Min Boat Length", expected.getMinBoatLength(), actual.getMinBoatLength());
		result &= MiscFunctions.compareResult("Max Boat Length", expected.getMaxBoatLength(), actual.getMaxBoatLength());
		result &= MiscFunctions.compareResult("Boat Spacing", expected.getBoatSpacing(), actual.getBoatSpacing());
		result &= MiscFunctions.compareResult("Web Visible", expected.isWebVisible(), actual.isWebVisible());
		
		//slip attributes
		result &= MiscFunctions.compareResult("ADA Features", expected.isAccessibleSite(), actual.isAccessibleSite());
		result &= MiscFunctions.compareResult("Max Num of People", expected.getMaxNumOfPeople(), actual.getMaxNumOfPeople());
		result &= MiscFunctions.compareResult("Max Num of Vehicle", expected.getMaxNumOfVehicle(), actual.getMaxNumOfVehicle());
		result &= MiscFunctions.compareResult("Check In Hour", expected.getCheckInHour(), actual.getCheckInHour());
		result &= MiscFunctions.compareResult("Check In Minute", expected.getCheckInMinute(), actual.getCheckInMinute());
		result &= MiscFunctions.compareResult("Check In AmPm", expected.getCheckInAmPm(), actual.getCheckInAmPm());
		result &= MiscFunctions.compareResult("Check Out Hour", expected.getCheckOutHour(), actual.getCheckOutHour());
		result &= MiscFunctions.compareResult("Check Out Minute", expected.getCheckOutMinute(), actual.getCheckOutMinute());
		result &= MiscFunctions.compareResult("Check Out AmPm", expected.getCheckOutAmPm(), actual.getCheckOutAmPm());
		result &= MiscFunctions.compareResult("Eletricity Hookup", expected.getElectrictity(), actual.getElectrictity());
		result &= MiscFunctions.compareResult("Water Hookup", expected.isWaterHookup(), actual.isWaterHookup());
		result &= MiscFunctions.compareResult("Sewer Hookup", expected.isSewerHookup(), actual.isSewerHookup());
		result &= MiscFunctions.compareResult("Full Hookup", expected.getFull(), actual.getFull());
		result &= MiscFunctions.compareResult("Latitude Channel Direction", expected.getLatitudeChannelDirection(), actual.getLatitudeChannelDirection());
		result &= MiscFunctions.compareResult("Latitude Degree", expected.getLatitudeDegree(), actual.getLatitudeDegree());
		result &= MiscFunctions.compareResult("Latitude Minute", expected.getLatitudeMinute(), actual.getLatitudeMinute());
		result &= MiscFunctions.compareResult("Latitude Second", expected.getLatitudeSecond(), actual.getLatitudeSecond());
		result &= MiscFunctions.compareResult("Longitude Channel Direction", expected.getLongitudeChannelDirection(), actual.getLongitudeChannelDirection());
		result &= MiscFunctions.compareResult("Longitude Degree", expected.getLongitudeDegree(), actual.getLongitudeDegree());
		result &= MiscFunctions.compareResult("Longitude Minute", expected.getLongitudeMinute(), actual.getLongitudeMinute());
		result &= MiscFunctions.compareResult("Longitude Second", expected.getLongitudeSecond(), actual.getLongitudeSecond());
		result &= MiscFunctions.compareResult("Base Pricing Length", expected.getBasePricingLength(), actual.getBasePricingLength());
		result &= MiscFunctions.compareResult("Seasonal Contract Length", expected.getSeasonalContractLength(), actual.getSeasonalContractLength());
		result &= MiscFunctions.compareResult("Seasonal Contract Start Date", expected.getSeasonalContractStartDate(), actual.getSeasonalContractStartDate());
//		result &= MiscFunctions.compareResult("Boat Required", expected.isBoatRequired(), actual.isBoatRequired());
		result &= MiscFunctions.compareResult("Type of Use", expected.getTypeOfUse(), actual.getTypeOfUse());
		
		return result;
	}
	
	public String duplicateSlip(SlipInfo slip) {
		clickDuplicateThisSlip();
		ajax.waitLoading();
		waitLoading();
		
		setupSlipInfo(slip);
		
		clickApply();
		ajax.waitLoading();
		waitLoading();
		
		String id = getSlipID();
		
		return id;
	}
	
	public boolean isErrorMessageExists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public String getSuccessMsg(){
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgsuccess");
	}
	
	public boolean isOKButtonEnabled(){
		return browser.checkHtmlObjectExists(this.okButtonAProp());
	}
}
