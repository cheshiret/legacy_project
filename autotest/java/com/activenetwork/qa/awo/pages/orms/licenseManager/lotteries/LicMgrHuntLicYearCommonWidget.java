package com.activenetwork.qa.awo.pages.orms.licenseManager.lotteries;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.LicenseYear;
import com.activenetwork.qa.awo.pages.orms.common.dialog.DialogWidget;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
/**
 * This is the widget has common method for add/edit hunt license year
 * @author pchen
 *
 */
public class LicMgrHuntLicYearCommonWidget extends DialogWidget {
	private static LicMgrHuntLicYearCommonWidget _instance = null;
	
	protected LicMgrHuntLicYearCommonWidget(String titleName) {
		super(titleName);
	}
	
	/** Page Object Property Begin */
	protected Property[] prodCategoryListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.productCategory", false));
	}
	
	protected Property[] assignedProdListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.productId", false));
	}
	
	protected Property[] locClassListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.locationClassId", false));
	}
	
	protected Property[] licYearListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+.licenseYear", false));
	}
	
	protected Property[] sellFromDateFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.sellFromDate_date_ForDisplay", false));
	}
	
	protected Property[] sellFromTimeFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.sellFromDate_time", false));
	}
	
	protected Property[] sellFromTimeAPMListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+.sellFromDate_ampm", false));
	}
	
	protected Property[] sellToDateFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.sellToDate_date_ForDisplay", false));
	}
	
	protected Property[] sellToTimeFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.sellToDate_time", false));
	}
	
	protected Property[] sellToTimeAPMListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.sellToDate_ampm", false));
	}
	
	protected Property[] creationUserSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.createUser", false));
	}
	protected Property[] creationLicationSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.createLocation", false));
	}
	protected Property[] creationDateTimeSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.createTime:LOCAL_TIME", false));
	}
	protected Property[] lastUpdatedUserSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.lastUpdateUser", false));
	}
	protected Property[] lastUpdatedLocationSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.lastUpdateLocation", false));
	}
	protected Property[] lastUpdatedDateTimeSpanProp(){
		return Property.toPropertyArray( ".id", new RegularExpression("ProductHuntLicenseYearView-\\d+\\.lastUpdateTime:LOCAL_TIME", false));
	}
	
	public String getProductCategory(){
		return browser.getDropdownListValue(this.prodCategoryListProp(), 0);
	}
	
	public String getAssignedProduct(){
		return browser.getDropdownListValue(this.assignedProdListProp(),0);
	}
	
	public List<String> getAssignedProductElement(){
		return browser.getDropdownElements(this.assignedProdListProp());
	}
	
	public String getLocationClass(){
		return browser.getDropdownListValue(this.locClassListProp(), 0);
	}
	
	public String getLicenseYear(){
		return browser.getDropdownListValue(this.licYearListProp(), 0);
	}
	
	public List<String> getLicenseYearElement(){
		return browser.getDropdownElements(this.licYearListProp());
	}
	
	public String getSellFromDateTime(){
		return browser.getTextFieldValue(this.sellFromDateFieldProp(), 0);
	}
	
	public String getSellFromHourMinute(){
		return browser.getTextFieldValue(this.sellFromTimeFieldProp(), 0);
	}
	
	public String getSellFromAPM(){
		return browser.getDropdownListValue(this.sellFromTimeAPMListProp(), 0);
	}
	
	public String getSellToDateTime(){
		return browser.getTextFieldValue(this.sellToDateFieldProp(), 0);
	}
	
	public String getSellToHourMinute(){
		return browser.getTextFieldValue(this.sellToTimeFieldProp(), 0);
	}
	
	public String getSellToAPM(){
		return browser.getDropdownListValue(this.sellToTimeAPMListProp(), 0);
	}
	
	/** Page Object Property END */
	
	public void selectProdCategory(String item) {
		browser.selectDropdownList(this.prodCategoryListProp(), item, true);
	}
	
	public void selectAssignedProd(String item) {
		if(StringUtil.notEmpty(item)){
			browser.selectDropdownList(this.assignedProdListProp(), item, true);
		}else{
			browser.selectDropdownList(this.assignedProdListProp(), 0);
		}
	}
	
	public void selectLocClass(String item) {
		browser.selectDropdownList(this.locClassListProp(), item, true);
	}
	
	public void selectLicYear(String item) {
		if(StringUtil.notEmpty(item)){
			browser.selectDropdownList(this.licYearListProp(), item, true);
		}else{
			browser.selectDropdownList(this.licYearListProp(), 0);
		}
	}
	
	public void setSellFromDate(String date) {
		browser.setTextField(this.sellFromDateFieldProp(), date, true, 0);
	}
	
	public void setSellFromTime(String time) {
		browser.setTextField(this.sellFromTimeFieldProp(), time, true, 0);
	}
	
	public void selectSellFromAPM(String apm) {
		browser.selectDropdownList(this.sellFromTimeAPMListProp(), apm, true);
	}
	
	public void setSellToDate(String date) {
		browser.setTextField(this.sellToDateFieldProp(), date, true, 0);
	}
	
	public void setSellToTime(String time) {
		browser.setTextField(this.sellToTimeFieldProp(), time, true, 0);
	}
	
	public void selectSellToAPM(String apm) {
		browser.selectDropdownList(this.sellToTimeAPMListProp(), apm, true);
	}
	
	public String getAttributeValue(Property[] properties){
		IHtmlObject[] objs = browser.getHtmlObject(properties);
		if(objs.length < 1){
			throw new ItemNotFoundException("Did not get the span object with properties: " +  properties.toString() );
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public String getCreateUser(){
		String text = this.getAttributeValue(this.creationUserSpanProp());
		String value = text.replace("Creation User", "");
		return value;
	}
	
	public String getCreateLocation(){
		String text = this.getAttributeValue(this.creationLicationSpanProp());
		String value = text.replace("Creation Location", "");
		return value;
	}
	
	public String getCreateDateTime(){
		String text = this.getAttributeValue(this.creationDateTimeSpanProp());
		String value = text.replace("Creation Date/Time", "");
		return value;
	}
	
	public String getLastUpdatedUser(){
		String text = this.getAttributeValue(this.lastUpdatedUserSpanProp());
		String value = text.replace("Last Updated User", "");
		return value;
	}
	
	public String getLastUpdatedLocation(){
		String text = this.getAttributeValue(this.lastUpdatedLocationSpanProp());
		String value = text.replace("Last Updated Location", "");
		return value;
	}
	
	public String getLastUpdatedDateTime(){
		String text = this.getAttributeValue(this.lastUpdatedDateTimeSpanProp());
		String value = text.replace("Last Updated Date/Time", "");
		return value;
	}
	//updated by summer, reason: ajax call during set date and time. Date:2014 06 24
	public void setHuntLicYearInfo(LicenseYear ly) {
		this.selectProdCategory(ly.prdCategory);
		ajax.waitLoading();
		this.selectAssignedProd(ly.assignedProd);
		this.selectLocClass(ly.locationClass);
		this.selectLicYear(ly.licYear);
		this.setSellFromDate(ly.sellFromDate);
		ajax.waitLoading();
		
		if (StringUtil.isEmpty(ly.sellFromTime)) { // Time must be after the current time
			ly.sellFromTime = DateFunctions.addTimeMinutes(DateFunctions.getCurrentTimeFormated(false), 1, "hh:mm", "hh:mm");
			ly.sellFromAmPm = DateFunctions.getCurrentAMPM();
		}
		this.setSellFromTime(ly.sellFromTime);
		ajax.waitLoading();
		this.selectSellFromAPM(ly.sellFromAmPm);
		ajax.waitLoading();
		this.setSellToDate(ly.sellToDate);
		ajax.waitLoading();
		this.setSellToTime(ly.sellToTime);
		ajax.waitLoading();
		this.selectSellToAPM(ly.sellToAmPm);
		ajax.waitLoading();
	}
	
	public boolean isProductCategoryEditable(){
		return browser.checkHtmlObjectEnabled(this.prodCategoryListProp());
	}
	
	public boolean isAssignedProductEditable(){
		return browser.checkHtmlObjectEnabled(this.assignedProdListProp());
	}
	
	public boolean isLocationClassEditable(){
		return browser.checkHtmlObjectEnabled(this.locClassListProp());
	}
	
	public boolean isLicenseYearEditable(){
		return browser.checkHtmlObjectEnabled(this.licYearListProp());
	}
	
	public boolean isSellFromDateEditable(){
		return browser.checkHtmlObjectEnabled(this.sellFromDateFieldProp());
	}
	
	public boolean isSellToDateEditable(){
		return browser.checkHtmlObjectEnabled(this.sellToDateFieldProp());
	}
	
	public boolean isSellFromHourMinuteEditable(){
		return browser.checkHtmlObjectEnabled(this.sellFromTimeFieldProp());
	}
	
	public boolean isSellFromAPMEditable(){
		return browser.checkHtmlObjectEnabled(this.sellFromTimeAPMListProp());
	}
	
	public boolean isSellToHourMinuteEditable(){
		return browser.checkHtmlObjectEnabled(this.sellToTimeFieldProp());
	}
	
	public boolean isSellToAPMEditable(){
		return browser.checkHtmlObjectEnabled(this.sellToTimeAPMListProp());
	}
}
