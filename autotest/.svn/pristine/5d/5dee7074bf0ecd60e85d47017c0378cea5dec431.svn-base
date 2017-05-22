/*
 * Created on Feb 3, 2010
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.resourceManager;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author QA
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ResMgrSchedulerDetailStepTwoPage extends ResourceManagerPage {
	
	static private ResMgrSchedulerDetailStepTwoPage _instance = null;
	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	static public ResMgrSchedulerDetailStepTwoPage getInstance() {
		if (null == _instance) {
			_instance = new ResMgrSchedulerDetailStepTwoPage();
		}

		return _instance;
	}

	private ResMgrSchedulerDetailStepTwoPage(){}
	
	/** Determine if the Resource Manager Scheduler Detail Page exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text","Step 2 of 3: Enter Report Parameters & Recipient Information");
	}
	
	/**
	 * Select Distribution Coverage Location ID from dropdown list.
	 * @param DistLocation - Location ID name
	 */
	public void selectDistLocation(String DistLocation) {
		browser.selectDropdownList(".id", "LocationID", DistLocation);
	}
	
	/**
	 * Select Frequency from dropdown list.
	 * @param Frequency - Frequency name
	 */
	public void selectFrequency(String frequency) {
		browser.selectDropdownList(".id", "_DisbursementFrequencyUIName_", frequency);
	}
	
	/**
	 * Select Disbursement Period (Not to be used in Scheduler) from dropdown list.
	 * @param Period
	 */
	public void selectDisbursementPeriod(String Period) {
		browser.selectDropdownList(".id", "_DisbursementPeriodUIName_", Period);
	}
	
	/**
	 * Select Date Type from dropdown list.
	 * @param DateType - Date Type name
	 */
	public void selectDateType(String DateType) {
	  if(DateType!=null&&!DateType.equals("")){
	    browser.selectDropdownList(".id", "DynaStatic", DateType,true);
	    waitLoading();
	  }
	}
	
	 /**select radio about RunDateStartDate */
	public void selectOnRunDateRadio() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "runDateStartDate");
		p[2] = new Property(".value", "On Run Day");
		browser.selectRadioButton(p,0);
	}
	
	/**select radio about RunDateStartDate */
	public void selectBeforOrAfterRadio() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "runDateStartDate");
		browser.selectRadioButton(p,1);
	}

	/**
	 * set the day before or after run date
	 * @param numberOfStartDate
	 */
	public void setNumberOfStartDate(String PeriodOnDay) {
		browser.setTextField(".id", "numberOfStartDate", PeriodOnDay, true);
	}
	
	/**
	 * Select BeforeAfterStartDate from dropdown list.
	 * @param BeforeAfterStartDate - Date Type name
	 */
	public void selectBeforeAfterStartDate(String BeforeAfterStartDate) {
		browser.selectDropdownList(".class","Html.SELECT",".id","BeforeAfterStartDate",BeforeAfterStartDate);
	}
	
	/**
	 * Select Recipient Type from dropdown list.
	 * @param RecipientType - Recipient Type name
	 */
	public void selectRecipientType(String RecipientType) {
	  if(RecipientType!=null&&!RecipientType.equals("")){
	    browser.selectDropdownList(".id", "RecipientType", RecipientType);
	    waitLoading();
	  }
	}
	
	/**
	 * Select Recipient from dropdown list.
	 * @param Recipient 
	 */
	public void selectRecipient(String Recipient) {
		browser.selectDropdownList(".id", "Recipient Name", Recipient);
	}
	
	/**
	 * Select Agency from dropdown list.
	 * @param AgencyID 
	 */
	public void selectAgency(String AgencyID) {
	  if(AgencyID!=null&&!AgencyID.equals("")){
	    browser.selectDropdownList(".id", "AgencyID", AgencyID);
	    waitLoading();
	  }
	}
	
	/**
	 * Select State from dropdown list.
	 * @param RegionID 
	 */
	public void selectState(String RegionID) {
	  if(RegionID!=null&&!RegionID.equals("")){
		browser.selectDropdownList(".id", "RegionID", RegionID);
	  }
	}
	
	/**
	 * Select District from dropdown list.
	 * @param DistrictID 
	 */
	public void selectDistrict(String DistrictID) {
	  if(DistrictID!=null&&!DistrictID.equals("")){
	    browser.selectDropdownList(".id", "DistrictID", DistrictID);
	  }
	}
	
	/**
	 * Select Field Office from dropdown list.
	 * @param ProjectID 
	 */
	public void selectFieldOffice(String ProjectID) {
	  if(ProjectID!=null&&!ProjectID.equals("")){
		browser.selectDropdownList(".id", "ProjectID", ProjectID);
	  }
	}
	
	/**
	 * Select Park from dropdown list.
	 * @param FacilityID 
	 */
	public void selectFacilityID(String FacilityID) {
	  if(FacilityID!=null&&!FacilityID.equals("")){
		browser.selectDropdownList(".id", "FacilityID", FacilityID);
		waitLoading();
	  }
	}
	
	public void selectFacilityHQID(String FacilityHQID)
	{
	  if(FacilityHQID!=null&&!FacilityHQID.equals("")){
		browser.selectDropdownList(".id", "FacilityHQID", FacilityHQID);
		waitLoading();
	  }
	}
	
	public void selectFacilityLOCID(String FacilityLocID)
	{
	  if(FacilityLocID!=null&&!FacilityLocID.equals("")){
		browser.selectDropdownList(".id", "FacilityLocID", FacilityLocID);
		waitLoading();
	  }
	}
	
	public void selectLocationId(String LocationID)
	{
	  if(LocationID!=null&&!LocationID.equals("")){
		browser.selectDropdownList(".id", "LocationID", LocationID);
		waitLoading();
	  }
	}
	/**
	 * Select Payment Group dropdown list.
	 * @param paymentMethods 
	 */
	public void selectPaymentGroup(String paymentMethods) {
	  if(paymentMethods!=null&&!paymentMethods.equals("")){
	    browser.selectDropdownList(".id", "paymentMethods", paymentMethods);
	  }
	}
	
	public void setStartDate(String startDate)
	{
	  	browser.setTextField(".id","StartDate_ForDisplay",startDate,0,IText.Event.LOSEFOCUS);
//	  	this.moveFocusOutOfDateComponent();
	}
	
	public void setEndDate(String endDate)
	{
	  	browser.setCalendarField(".id","EndDate_ForDisplay",endDate);
//	  	this.moveFocusOutOfDateComponent();
	}
	
	/**
	 * Add new email address
	 * @param newEmailAddr
	 */
	public void addNewEmailAddr(String newEmailAddr) {
		browser.setTextField(".id", "__SchedulerEmailManuBaseRecipients", newEmailAddr);
		this.clickAddForNew();
		this.waitLoading();
		
	}
		
	/** Click on Add for new */
	public void clickAddForNew() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add>>");
		p[2] = new Property(".href", new RegularExpression(".*AddManualRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	/** Click on Add for selecting from email list */
	public void clickAddForSelectingList() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "Add>>");
		p[2] = new Property(".href", new RegularExpression(".*AddAutoRecipient.*",false));
		browser.clickGuiObject(p);
	}
	
	/** Click on Remove after adding for new */
	public void clickRemoveAfterAddForNew() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "<<Remove");
		p[2] = new Property(".href", new RegularExpression("^javascript.*RemoveManualRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	/** Click on Remove after selecting from email list */
	public void clickRemoveAfterSelectList() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".text", "<<Remove");
		p[2] = new Property(".href", new RegularExpression("^javascript.*RemoveAutoRecipient.*",true));
		browser.clickGuiObject(p);
	}
	
	/**
	 * Select one email address from email address list for adding.
	 * @param EmailAddress 
	 */
	public void selectEmailAddressForAdd(String EmailAddress) {
		browser.selectDropdownList(".id", "__SchedulerEmailAutoBaseRecipients", EmailAddress);
		this.clickAddForSelectingList();
		this.waitLoading();
	}
	
	/**
	 * 
	 * @param SchedulerEmailSubject
	 */
	public void setSchedulerEmailSubject(String SchedulerEmailSubject) {
		if(!StringUtil.isEmpty(SchedulerEmailSubject))
		{
			browser.setTextField(".id", "_SchedulerEmailSubject", SchedulerEmailSubject, true);
		}
		
	}
	/** select the email compress checkbox */
	public void selectEmailCompressCheckbox(){
		browser.selectCheckBox(".class","Html.INPUT.checkbox",".id","_SchedulerEmailCompress");
	}
	
	/**
	 * Fax Recipient Information --To
	 * @param FaxTo
	 */
	public void setFaxTo(String FaxTo) {
		browser.setTextField(".id", "__SchedulerFaxTo", FaxTo, true);
		
	}
	
	/**
	 * Fax Recipient Information --From
	 * @param FaxFrom
	 */
	public void setFaxFrom(String FaxFrom) {
		browser.setTextField(".id", "__SchedulerFaxFrom", FaxFrom, true);
		
	}
	
	/**
	 * Fax Recipient Information --Fax Number
	 * @param FaxNumber
	 */
	public void setFaxNumber(String FaxNumber) {
		browser.setTextField(".id", "__SchedulerFaxNumber", FaxNumber, true);
		
	}
	
	/** Click on Previous */
	public void clickPrevious() {
		browser.clickGuiObject(".class", "Html.A", ".text", "<<Previous", true);
	}
	
	/** Click on Next */
	public void clickNext() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Next>>", true);
	}
	
	/** Click on Cancel */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	public void selectCooperatorID(String CooperatorID)
	{
	  	if(CooperatorID!=null&&!CooperatorID.equals("")){
	  	  browser.selectDropdownList(".id","CooperatorID",CooperatorID);
	  	  waitLoading();
	  	}
	}
	
	public void selectInvoice(String Invoice)
	{
	  	if(Invoice!=null&&!Invoice.equals("")){
	  	  browser.selectDropdownList(".id","Invoice",Invoice);
	  	}
	}
	
	public void selectLocationIDNoValidate(String LocationIDNoValidate)
	{
	  	if(LocationIDNoValidate!=null&&!LocationIDNoValidate.equals("")){
	  	  browser.selectDropdownList(".id","LocationIDNoValidate",LocationIDNoValidate);
	  	}
	}
	
	public void selectLotteryName(String LotteryName)
	{
	  	if(LotteryName!=null&&!LotteryName.equals("")){
	  	  browser.selectDropdownList(".id","LotteryName",LotteryName);
	  	  waitLoading();
	  	}
	}
	
	public void selectLotterySched(String LotterySched)
	{
	  	if(LotterySched!=null&&!LotterySched.equals("")){
	  	  browser.selectDropdownList(".id","LotterySched",LotterySched);
	  	  waitLoading();
	  	}
	}
	
	public void selectCallCenterID(String CallCenterID)
	{
	  	if(CallCenterID!=null&&!CallCenterID.equals("")){
	  	  browser.selectDropdownList(".id","CallCenterID",CallCenterID);
	  	  waitLoading();
	  	}
	}
	
	public void setSiteNumber(String SiteNumber)
	{
	  	if(SiteNumber!=null&&!SiteNumber.equals("")){
	  	  browser.setTextField(".id","SiteNumber",SiteNumber);
	  	}
	}
	
	public void selectQuotaInterval(String QuotaInterval)
	{
	  	if(QuotaInterval!=null&&!QuotaInterval.equals("")){
	  	  browser.selectDropdownList(".id","QuotaInterval",QuotaInterval);
	  	  waitLoading();
	  	}
	}
	
	private void selectContract(String contract) {
		if(!StringUtil.isEmpty(contract)){
			browser.selectDropdownList(".id","GrossLocationID",contract);
		}
	}
	
	public void setReportParameters(ReportData rd)
	{
	  	selectRecipientType(rd.recipientType);
	  	selectContract(rd.contract);
	  	selectAgency(rd.agencyID);
	  	selectLocationId(rd.locationID);
	  	this.selectOrderBy(rd.orderBy);
	  	this.selectShowEmptySites(rd.showEmptySites);
	  	selectFacilityID(rd.facilityID);
	  	selectFacilityHQID(rd.facilityHQID);
	  	selectFacilityLOCID(rd.facilityLocID);
	  	selectCooperatorID(rd.cooperatorID);
	  	selectInvoice(rd.invoice);
	  	selectLocationIDNoValidate(rd.locationIDNoValidate);
	  	selectLotteryName(rd.lotteryName);
	  	selectLotterySched(rd.lotterySched);
	  	selectCallCenterID(rd.callCenterID);
	  	setSiteNumber(rd.siteNumber);
	  	selectQuotaInterval(rd.quotaInterval);
	}
	
	
	public void selectOrderBy(String orderBy)
	{
		if(!StringUtil.isEmpty(orderBy))
		{
			browser.selectDropdownList(".id", "OrderBy", orderBy);
			this.waitLoading();
		}
	}
	
	public void selectShowEmptySites(String show)
	{
		if(!StringUtil.isEmpty(show))
		{
			browser.selectDropdownList(".id", "ShowEmptySites", show);
			this.waitLoading();
		}
	}
	/**
	 * This method used to input info for report and recipient
	 * @param rd
	 */
	public void enterInfoForStepTwo(ReportData rd,String scheduleType)
	{
	  	logger.info("Step 2: Enter Report Parameters & Recipient Information.");
	  	
	  	setReportParameters(rd);
	  	selectDateType(rd.dateType);
	  	if(rd.dateType.equalsIgnoreCase("Specified Dates")){
	  	  setStartDate(rd.startDate);
	  	  setEndDate(rd.endDate);
	  	}
	  	if(scheduleType.equalsIgnoreCase("Single Instance")){
		  	if(rd.recipient_name!=null&&!rd.recipient_name.equals("")){
		  	  addNewEmailAddr(rd.recipient_name);  
		  	}
		  	if(rd.recipientFromList!=null&&!rd.recipientFromList.equals("")){
		  	  selectEmailAddressForAdd(rd.recipientFromList);
		  	}
	  	}
	  	
	}
}
