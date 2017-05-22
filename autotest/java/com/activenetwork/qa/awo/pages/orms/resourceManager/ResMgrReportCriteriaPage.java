package com.activenetwork.qa.awo.pages.orms.resourceManager;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.ReportData;
import com.activenetwork.qa.awo.pages.component.GroupSelectionComponent;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;


/**
 * @author QA
 */
public class ResMgrReportCriteriaPage extends ResourceManagerPage {

	private ResMgrReportCriteriaPage() {
	}

	private static ResMgrReportCriteriaPage instance = null;

	public static ResMgrReportCriteriaPage getInstance() {
		if (null == instance) {
			instance = new ResMgrReportCriteriaPage();
		}
		return instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".text", "OK", ".href",
				new RegularExpression("javascript:validate.*", false));
	}

	/**
	 * Select agency by given name.
	 * 
	 * @param agency
	 *            - agency name
	 */
	public void selectAgencyID(String agency) {
		if (null != agency && !"".equals(agency)) {
			browser.selectDropdownList(".id", "AgencyID", agency);
			waitLoading();
		}
	}

	/**
	 * Select region id.
	 * 
	 * @param region
	 *            - region id
	 */
	public void selectRegionID(String region) {
		if (null != region && !"".equals(region)) {
			browser.selectDropdownList(".id", "RegionID", region);
			waitLoading();
		}
	}

	/**
	 * Select report section.
	 * 
	 * @param section
	 *            - report section
	 */
	public void selectReportSection(String section) {
		if (null != section && !"".equals(section)) {
			browser.selectDropdownList(".id", "ReportSection", section);
			waitLoading();
		}
	}
	
	/**
	 * Select Include Distributed.
	 * 
	 * @param type
	 *            - Include Distributed
	 */
	public void selectIncludeDistributed(String type) {
		if (null != type && !"".equals(type)) {
			browser.selectDropdownList(".id", "IncludeGCPayments", type);
			waitLoading();
		}
	}
	/**
	 * Select park by given name.
	 * 
	 * @param park_name
	 *            - park name
	 */
	public void selectPark(String park_name) {
		if (null != park_name && !"".equals(park_name)) {
			browser.selectDropdownList(".id", new RegularExpression("(Park|FacilityID)",false), park_name);
			waitLoading();
		}
	}
	
	/**
	 * Select facilityHQ by given value.
	 * 
	 * @param park_name
	 *            - facilityHQID
	 */
	public void selectfacilityHQID(String park_name) {
		if (null != park_name && !"".equals(park_name)) {
			browser.selectDropdownList(".id", "FacilityHQID", park_name);
			waitLoading();
		}
	}

	/**
	 * Select FacilityID by given value.
	 * 
	 * @param park_name
	 *            - FacilityID
	 */
	public void selectFacilityID(String park_name) {
		if (null != park_name && !"".equals(park_name)) {
			browser.selectDropdownList(".id", "FacilityID", park_name);
			waitLoading();
		}
	}

	/**
	 * Select Facility ID by given value.
	 * 
	 * @param park_name
	 *            - Facility ID
	 */
	public void selectCallCenterID(String callCenter) {
		if (null != callCenter && !"".equals(callCenter)) {
			browser.selectDropdownList(".id", "CallCenterID", callCenter);
			waitLoading();
		}
	}

	/**
	 * Select operator by given value.
	 * 
	 * @param operator
	 *            - operator
	 */
	public void selectOperator(String operator) {
		if (null != operator && !"".equals(operator)) {
			browser.selectDropdownList(".id", "Operator", operator);
			waitLoading();
		}
	}

	/**
	 * Select product type.
	 * 
	 * @param productType
	 */
	public void selectProductType(String productType) {
		if (null != productType && !"".equals(productType)) {
			browser.selectDropdownList(".id", "ProductType", productType);
			waitLoading();
		}

	}

	/**
	 * Fill in start date.
	 * 
	 * @param startDate
	 */
	public void setStartDate(String startDate) {
		if (null != startDate && !"".equals(startDate)) {
//			browser.setTextField(".id", "EndDate_ForDisplay", "");
//			if(this.exists()){
//				refreshPage();
//				this.waitExists();
//			}
//			browser.setTextField(".id", "StartDate_ForDisplay", startDate);
//			this.waitExists();
//			if(this.exists()){
//				refreshPage();
//				this.waitExists();
//			}
			if(browser.checkHtmlObjectExists(".id", "EndDate_ForDisplay")){//Update by Sara at 5/15/2013, because for some report, such as "Campers Report", no End Date text field. So we need verify if the end date text field is existing. If yes, we can set it.
				setDateAndGetMessage((IText)browser.getTextField(".id", "EndDate_ForDisplay")[0], StringUtil.EMPTY);
			}
			setDateAndGetMessage((IText)browser.getTextField(".id", "StartDate_ForDisplay")[0], startDate);
		}
	}
	
	/**
	 * Fill in lottery name
	 * @param lotteryName
	 */
	public void setLotteryName(String lotteryName){
		if(null != lotteryName && !"".equals(lotteryName)){
			browser.setTextField(".id", "LotteryName", lotteryName);
			refreshPage();
		}
	}
	
	/**
	 * Fill in lottery schedule
	 * @param lotterySchedul
	 */
	public void setLotterySchedule(String lotterySchedule){
		if(null != lotterySchedule&& !"".equals(lotterySchedule)){
			browser.setTextField(".id", "LotterySched", lotterySchedule);
		}
	}

	public void refreshPage(){
//		ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
//		ResMgrReportCriteriaPage rmCriteriaPg = ResMgrReportCriteriaPage.getInstance();
//		browser.clickGuiObject(".class","Html.FORM",".id","e_Form");
//		browser.waitExists(alertPg,rmCriteriaPg);
		//browser.clickGuiObject(".class","Html.TD",".text","QA");
		browser.clickGuiObject(".class","Html.TD",".className","label_section");//updated by pzhu //this can only pass on RFT, not pass on Selenium, selenium need ".classname"

	}
	public void setTourDate(String tourDate) {
		if (null != tourDate && !"".equals(tourDate)) {
			browser.setTextField(".id", "TourDate_ForDisplay", tourDate,0,IText.Event.LOSEFOCUS);
		}
	}
	
	public void setOrderDate(String orderDate) {
		if (null != orderDate && !"".equals(orderDate)) {
			browser.setTextField(".id", "OrderDate_ForDisplay", orderDate,0,IText.Event.LOSEFOCUS);
		}
	}
	/**
	 * Get Start date
	 * 
	 * @return start date value
	 */
	public String getStartDate() {
		return browser.getTextFieldValue(".id", "StartDate_ForDisplay");
	}

	/** Click Start date label to refresh date value */
	public void refreshDateValue() {
		browser.clickGuiObject(".text", "Start Date");
	}

	/**
	 * Fill in end date.
	 * 
	 * @param endDate
	 */
	public void setEndDate(String endDate) {
		if (null != endDate && !"".equals(endDate)) {
			browser.setTextField(".id", "EndDate_ForDisplay", endDate);
			this.waitLoading();
			if(this.exists()){
				refreshPage();
			}
		}
	}
	
	public String setEndDateWithInvalidDate(String endDate) {
//		if (!StringUtil.isEmpty(endDate)) {
//			browser.setTextField(".id", "EndDate_ForDisplay", endDate);
//
//			new Thread(){//to handle alert dialog popped up for check invalid report date
//				ConfirmDialogPage alertPg = ConfirmDialogPage.getInstance();
//				
//				public void run(){
//					int i=0;
//					alertPg.setDismissible(false);
//					while(i<2){
//						try {
//							Thread.sleep(500);
//						} catch (InterruptedException e) {
//							logger.warn(e.getMessage());
//						}
//						if(alertPg.exists()){
//							TestProperty.putProperty("msg", alertPg.text());
//							alertPg.dismiss();
//							break;
//						}
//						i++;
//					}
//				};
//			}.start();
//			
//			
//			if(this.exists()){
//				refreshPage();
//			}
//		}
//		String alertMsg = TestProperty.getProperty("msg",null);
//		TestProperty.putProperty("msg","");//reset msg to null
//		return alertMsg;
		
		return setDateAndGetMessage((IText)browser.getTextField(".id", "EndDate_ForDisplay")[0], endDate);
	}

	/**
	 * Get End date
	 * 
	 * @return End date value
	 */
	public String getEndDate() {
		return browser.getTextFieldValue(".id", "EndDate_ForDisplay");
	}

	private Property[] reportFormat() {
		return Property.toPropertyArray(".id", "report_format");
	}
	
	/**
	 * Select report format.
	 * 
	 * @param format
	 *            - report format
	 */
	public void selectReportFormat(String format) {
		if (null != format && !"".equals(format)) {
			browser.selectDropdownList(".id", "report_format", format);
			waitLoading();
		}
	}

	public String getReportFormat() {
		return browser.getDropdownListValue(reportFormat(), 0);
	}

	public List<String> getReportFormatOptions() {
		return browser.getDropdownElements(reportFormat());
	}
	
	private Property[] deliveryMethod() {
		return Property.toPropertyArray(".id", "deliveryprotocolid");
	}
	
	/**
	 * Select delivery method.
	 * 
	 * @param method
	 *            - delivery method
	 */
	public void selectDeliveryMethod(String method) {
		if (null != method && !"".equals(method)) {
			browser.selectDropdownList(deliveryMethod(), method);
			waitLoading();
		}
	}

	public String getDeliveryMethod() {
		return browser.getDropdownListValue(deliveryMethod(), 0);
	}

	public List<String> getDeliveryMethodOptions() {
		return browser.getDropdownElements(deliveryMethod());
	}
	
	/**
	 * Select payment group.
	 * 
	 * @param payment
	 *            - payment group
	 */
	public void selectPaymentGroup(String payment) {
		if (null != payment && !"".equals(payment)) {
			browser.selectDropdownList(".id", "PaymentGroup", payment);
			waitLoading();
		}
	}

	/**
	 * Select location id.
	 * 
	 * @param location
	 *            - location id
	 */
	public void selectLocationID(String location) {
		if (null != location && !"".equals(location)) {
			browser.selectDropdownList(".id", "LocationID", location);
			waitLoading();
		}
	}
	
	/**
	 * Select location class.
	 * 
	 * @param location
	 *            - location id
	 */
	public void selectLocationClass(String locationClass) {
		if (null != locationClass && !"".equals(locationClass)) {
			browser.selectDropdownList(".id", "LocationClass", locationClass);
			waitLoading();
		}
	}
	
	/**
	 * Select Transaction Type.
	 * 
	 * @param typs
	 *            - list of transaction types.
	 */
	public void selectTransactionTypes(String[] types) {
		if ((null != types) && (types.length>0)) {
			IHtmlObject[] from = browser.getDropdownList(".id","TransactionType_MainList");
			IHtmlObject[] to = browser.getDropdownList(".id","TransactionType_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).add(types);
			waitLoading();
			Browser.unregister(from,to,add,remove);
		}
	}
	
	public void selectDisplayColumns(String[] columns){
		if ((null != columns) && (columns.length>0)) {
			IHtmlObject[] from = browser.getDropdownList(".id","DisplayColumns_MainList");
			IHtmlObject[] to = browser.getDropdownList(".id","DisplayColumns_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).add(columns);
			waitLoading();
			Browser.unregister(from,to,add,remove);
		}
	}
	
	public void selectSubTotalBy(String[] subTotalBy){
		if ((null != subTotalBy) && (subTotalBy.length>0)) {
			IHtmlObject[] from = browser.getDropdownList(".id","TransactionDetailSubTotalBy_MainList");
			IHtmlObject[] to = browser.getDropdownList(".id","TransactionDetailSubTotalBy_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[1], remove[1]).add(subTotalBy);
			waitLoading();
			Browser.unregister(from,to,add,remove);
		}
	}

	/**
	 * Fill in Fiscal Year.
	 * 
	 * @param Fiscal Year
	 */
	public void setFiscalYear(String year) {
		if (null != year && !"".equals(year)) {
			browser.setTextField(".id", "FiscalYear", year);
			this.waitLoading();			
		}
	}
	
	
	
	/**
	 * Select station id.
	 * 
	 * @param station
	 */
	public void selectStationID(String station) {
		if (null != station && !"".equals(station)) {
			browser.selectDropdownList(".id", "StationID", station);
			waitLoading();
		}
	}

	/**
	 * Select include ajustment.
	 * 
	 * @param inAdjsmnt
	 *            - include ajustment
	 */
	public void selectincludeAdjustments(String inAdjsmnt) {
		if (null != inAdjsmnt && !"".equals(inAdjsmnt)) {
			browser.selectDropdownList(".id", new RegularExpression("includeAdjustments|Adjusted Included", false), inAdjsmnt);
			waitLoading();
		}
	}
	
	public void selectIncludeRAFee(String raFee){
		if(!StringUtil.isEmpty(raFee)){
			browser.selectDropdownList(".id", "IncludeRAFeeAccount", raFee);
		}
	}

	/**
	 * Select yes or no flag.
	 * 
	 * @param yORn
	 *            - flag
	 */
	public void selectYesNoFlag(String yORn) {
		if (null != yORn && !"".equals(yORn)) {
			browser.selectDropdownList(".id", "YesNoFlag", yORn);
			waitLoading();
		}
	}

	/**
	 * Select collect issue location.
	 * 
	 * @param location
	 */
	public void selectCollectIssueLocation(String location) {
		if (null != location && !"".equals(location)) {
			browser.selectDropdownList(".id", "CollectIssueLocation", location);
			waitLoading();
		}
	}

	/**
	 * Select RA fee account.
	 * 
	 * @param raFee
	 */
	public void selectRAFeeAccount(String raFee) {
		if (null != raFee && !"".equals(raFee)) {
			browser.selectDropdownList(".id", "RAFeeAccount", raFee);
			waitLoading();
		}
	}

	/**
	 * Select whether or not include taxes.
	 * 
	 * @param inTaxes
	 *            - include taxes
	 */
	public void selectIncludeTaxes(String inTaxes) {
		if (null != inTaxes && !"".equals(inTaxes)) {
			browser.selectDropdownList(".id", "IncludeTaxes", inTaxes);
			waitLoading();
		}
	}

	/** Click OK button. */
	public void clickOk() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK");
	}

	/** Go to Home page. */
	public void clickHome() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Home");
	}

	// ==================below is new method======================

	/**
	 * Select agency location id.
	 * 
	 * @param agency
	 *            - agency name
	 */
	public void selectAgencyLocID(String agency) {
		if (null != agency && !"".equals(agency)) {
			browser.selectDropdownList(".id", "AgencyLocID", agency);
			waitLoading();
		}
	}

	/**
	 * Select region location id.
	 * 
	 * @param region
	 */
	public void selectRegionLocID(String region) {
		if (region != null && !"".equals(region)) {
			browser.selectDropdownList(".id", "RegionLocID", region);
			waitLoading();
		}
	}

	/**
	 * Select facility location id.
	 * 
	 * @param facility
	 */
	public void selectFacilityLocID(String facility) {
		if (null != facility && !"".equals(facility)) {
			browser.selectDropdownList(".id", "FacilityLocID", facility);
			waitLoading();
		}
	}

	/**
	 * Select payment group.
	 * 
	 * @param paymentGrp
	 *            - payment group
	 */
	public void selectPaymentGrp(String paymentGrp) {
		if (null != paymentGrp && !"".equals(paymentGrp)) {
			browser.selectDropdownList(".id", "PaymentGrp", paymentGrp);
			waitLoading();
		}
	}

	/**
	 * Select payment refund status.
	 * 
	 * @param paymentStatus
	 */
	public void selectPaymentRefundStatus(String paymentStatus) {
		if (null != paymentStatus && !"".equals(paymentStatus)) {
			browser.selectDropdownList(".id", "PaymentRefundStatus",
					paymentStatus);
			waitLoading();
		}
	}

	/**
	 * Select product group.
	 * 
	 * @param productGroup
	 */
	public void selectProductGroup(String productGroup) {
		if (productGroup != null && !"".equals(productGroup)) {
			browser.selectDropdownList(".id", "ProductGroup", productGroup);
			waitLoading();
		}
	}
	
	private Property[] productCategory() {
		return Property.toPropertyArray(".id", new RegularExpression("(ProductCategory)|(ProductType)", false));
	}
	
	/**
	 * Select product category.
	 * 
	 * @param productCategory
	 */
	public void selectProductCategory(String productCategory) {
		if (productCategory != null && !"".equals(productCategory)) {
			browser.selectDropdownList(".id", new RegularExpression("(ProductCategory)|(ProductType)", false), productCategory);
			waitLoading();
		}
	}

	public String getProductCategory() {
		return browser.getDropdownListValue(productCategory(), 0);
	}
	
	/**
	 * Fill in start time.
	 * 
	 * @param startTime_time
	 */
	public void setStartTime_time(String startTime_time) {
		if (null != startTime_time && !"".equals(startTime_time)) {
			browser.setTextField(".id", "StartTime_time", startTime_time);
			waitLoading();
		}
	}

	/**
	 * Fill in end time.
	 * 
	 * @param endTime_time
	 */
	public void setEndTime_time(String endTime_time) {
		if (null != endTime_time && !"".equals(endTime_time)) {
			browser.setTextField(".id", "EndTime_time", endTime_time);
			waitLoading();
		}
	}

	/**
	 * Select start time by am or pm.
	 * 
	 * @param startTime_ampm
	 */
	public void selectStartTime_ampm(String startTime_ampm) {
		if (null != startTime_ampm && !"".equals(startTime_ampm)) {
			browser.selectDropdownList(".id", "StartTime_ampm", startTime_ampm);
			waitLoading();
		}
	}

	/**
	 * Select end time by am or pm
	 * 
	 * @param endTime_ampm
	 */
	public void selectEndTime_ampm(String endTime_ampm) {
		if (null != endTime_ampm && !"".equals(endTime_ampm)) {
			browser.selectDropdownList(".id", "EndTime_ampm", endTime_ampm);
			waitLoading();
		}
	}

	/**
	 * Select recipient name.
	 * 
	 * @param recipient
	 */
	public void selectRecipient_Name(String recipient) {
		if (null != recipient && !"".equals(recipient)) {
			browser.selectDropdownList(".id", "Recipient Name", recipient);
			waitLoading();
		}
	}

	/**
	 * Select payment method.
	 * 
	 * @param paymentMethod
	 */
	public void selectpaymentMethods(String paymentMethod) {
		if (null != paymentMethod && !"".equals(paymentMethod)) {
			browser.selectDropdownList(".id", "paymentMethods", paymentMethod);
			waitLoading();
		}
	}

	/**
	 * Select refund status.
	 * 
	 * @param Refund_Status
	 */
	public void selectRefund_Status(String refund_Status) {
		if (null != refund_Status && !"".equals(refund_Status)) {
			browser.selectDropdownList(".id", "Refund Status", refund_Status);
			waitLoading();
		}
	}

	/**
	 * Select refund payment.
	 * 
	 * @param Refund_Payment
	 */
	public void selectRefund_Payment(String refund_Payment) {
		if (refund_Payment != null && !"".equals(refund_Payment)) {
			browser.selectDropdownList(".id", "Refund Payment", refund_Payment);
			waitLoading();
		}
	}

	/**
	 * Select report by location.
	 * 
	 * @param ReportByLocation
	 */
	public void selectReportByLocation(String reportByLocation) {
		if (reportByLocation != null && !"".equals(reportByLocation)) {
			browser.selectDropdownList(".id", new RegularExpression("ReportByLocation|POSReportBy", false), reportByLocation);
			waitLoading();
		}
	}

	public List<String> getStations(){
		return browser.getDropdownElements(".id", "StationID");
	}
	
	public void syncStationSelectingReportBy(String reportBy, String stationOption){
		this.selectReportByLocation(reportBy);
		List<String> stations = new ArrayList<String>();
		Timer timer=new Timer();
		boolean finishSync = false;
		
		do{
			stations.clear();
			stations = this.getStations();
			if(stations.contains(stationOption)){
				finishSync = true;
			}
		}while(!finishSync && timer.diffLong()<timeout*1000);
	}
	
	/**
	 * Select date type.
	 * @param dateType
	 */
	public void selectDateType(String dateType) {
		if (null != dateType && !"".equals(dateType)) {
			RegularExpression rex = new RegularExpression("BasedOn|DateType",
					false);
			browser.selectDropdownList(".id", rex, dateType);
			waitLoading();
		}
	}

	/**
	 * Select attribution fee type.
	 * 
	 * @param AttrFeeType
	 */
	public void selectAttrFeeType(String attrFeeType) {
		if (null != attrFeeType && !"".equals(attrFeeType)) {
			browser.selectDropdownList(".id", "AttrFeeType", attrFeeType);
			waitLoading();
		}
	}

	/**
	 * Fill in closure id.
	 * 
	 * @param ClosureID
	 */
	public void setClosureID(String closureID) {
		if (null != closureID && !"".equals(closureID)) {
			browser.setTextField(".id", "ClosureID", closureID);
		}
	}

	/**
	 * Select supress empty line.
	 * 
	 * @param SupressEmptyLine
	 */
	public void selectSupressEmptyLine(String supressEmptyLine) {
		if (null != supressEmptyLine && !"".equals(supressEmptyLine)) {
			browser.selectDropdownList(".id", new RegularExpression("Suppress ?Empty ?Line", false), //updated by pzhu, ("Suppress ?Empty ?Line") -->("Supress ?Empty ?Line")
					supressEmptyLine);
		}
	}

	/**
	 * Fill in maximum letters.
	 * 
	 * @param MaxLetters
	 */
	public void setMaxLetters(String maxLetters) {
		if (null != maxLetters && !"".equals(maxLetters)) {
			browser.setTextField(".id", "MaxLetters", maxLetters);
		}
	}

	/** Click Do Not Email link. */
	public void clickDoNotEmail() {
		browser.clickGuiObject(".id", "DoNotEmail", ".class",
				"Html.INPUT.checkbox");
	}

	/** Click Do Not fax link. */
	public void clickDoNotFax() {
		browser.clickGuiObject(".id", "DoNotFax", ".class",
				"Html.INPUT.checkbox");
	}

	/**
	 * Select whether or not by report type.
	 * 
	 * @param ByReportType
	 */
	public void selectByReportType(String byReportType) {
		if (null != byReportType && !"".equals(byReportType)) {
			browser.selectDropdownList(".id", "ByReportType", byReportType);
		}
	}

	/**
	 * Select whether or not by ticket type.
	 * 
	 * @param ByTicketType
	 */
	public void selectByTicketType(String byTicketType) {
		if (null != byTicketType && !"".equals(byTicketType)) {
			browser.selectDropdownList(".id", "ByTicketType", byTicketType);
			waitLoading();
		}
	}

	/**
	 * Select exceptions.
	 * 
	 * @param Exceptions
	 */
	public void selectExceptions(String exceptions) {

		if (null != exceptions && !"".equals(exceptions)) {
			browser.selectDropdownList(".id", "Exceptions", exceptions);
			waitLoading();
		}
	}

	/**
	 * Select unit of stay.
	 * 
	 * @param UnitOfStay
	 */
	public void selectUnitOfStay(String unitOfStay) {
		if (null != unitOfStay && !"".equals(unitOfStay)) {
			browser.selectDropdownList(".id", "UnitOfStay", unitOfStay);
			waitLoading();
		}
	}

	/**
	 * Select reservation type.
	 * 
	 * @param ReservationType
	 */
	public void selectReservationType(String reservationType) {
		if (null != reservationType && !"".equals(reservationType)) {
			browser.selectDropdownList(".id", "ReservationType",
					reservationType);
			waitLoading();
		}
	}

	/**
	 * Select voucher program.
	 * 
	 * @param VoucherProgram
	 */
	public void selectVoucherProgram(String voucherProgram) {
		if (null != voucherProgram && !"".equals(voucherProgram)) {
			browser.selectDropdownList(".id", "VoucherProgram", voucherProgram);
			waitLoading();
		}
	}

	private Property[] orderBy() {
		return Property.toPropertyArray(".id", "OrderBy");
	}
	
	/**
	 * Select order by.
	 * 
	 * @param OrderBy
	 */
	public void selectOrderBy(String orderBy) {
		if (null != orderBy && !"".equals(orderBy)) {
			browser.selectDropdownList(orderBy(), orderBy);
			waitLoading();
		}
	}
	
	public String getOrderBy() {
		return browser.getDropdownListValue(orderBy(), 0);
	}

	public List<String> getOrderByOptions() {
		return browser.getDropdownElements(orderBy());
	}

	/**
	 * Select area id.
	 * 
	 * @param AreaID
	 */
	public void selectAreaID(String areaID) {
		if (null != areaID && !"".equals(areaID)) {
			browser.selectDropdownList(".id", "AreaID", areaID);
			waitLoading();
		}
	}

	/**
	 * Select site number.
	 * 
	 * @param siteNum
	 */
	public void setSiteNum(String siteNum) {
		if (null != siteNum && !"".equals(siteNum)) {
			browser.setTextField(".id", "SiteNumber", siteNum);
			waitLoading();
		}
	}
	
	/**
	 * set reservation number
	 * @param resNum
	 */
	public void setReservationNumber(String resNum) {
		if(!StringUtil.isEmpty(resNum)) {
			browser.setTextField(".id", "ReservationNumber", resNum);
		}
	}

	/**
	 * Select whether or not show empty sites.
	 * 
	 * @param ShowEmptySites
	 */
	public void selectShowEmptySites(String showEmptySites) {
		if (null != showEmptySites && !"".equals(showEmptySites)) {
			browser.selectDropdownList(".id", "ShowEmptySites", showEmptySites);
			waitLoading();
		}
	}

	/**
	 * Select report location.
	 * 
	 * @param reportloc
	 */
	public void selectReportLoc(String reportloc) {
		if (reportloc != null && !"".equals(reportloc)) {
			browser.selectDropdownList(".id", "ReportLoc", reportloc);
		}
		waitLoading();
	}

	/**
	 * Select category.
	 * 
	 * @param category
	 */
	public void selectCategory(String category) {
		if (category != null && !"".equals(category)) {
			browser.selectDropdownList(".id", "Category", category);
			waitLoading();
		}
	}

	/**
	 * Select tour category
	 * 
	 * @param tourCategory
	 */
	public void selectTourCategory(String tourCategory) {
		if (tourCategory != null && !"".equals(tourCategory)) {
			browser.selectDropdownList(".id", "TourCategory", tourCategory);
			waitLoading();
		}
	}

	/**
	 * Select report type.
	 * 
	 * @param reporttype
	 */
	public void selectReportType(String reporttype) {
		if (reporttype != null && !"".equals(reporttype)) {
			browser.selectDropdownList(".id", "ReportType", reporttype);
			waitLoading();
		}
	}

	/**
	 * Select sort order.
	 * 
	 * @param sortorder
	 */
	public void selectSortOrder(String sortorder) {
		if (sortorder != null && !"".equals(sortorder))
			browser.selectDropdownList(".id", "SortOrder", sortorder);

	}

	/**
	 * Select report sub type.
	 * 
	 * @param reportsubtype
	 */
	public void selectReportSubType(String reportsubtype) {
		if (reportsubtype != null && !"".equals(reportsubtype)) {
			browser.selectDropdownList(".id", "ReportSubType", reportsubtype);
			waitLoading();
		}
	}

	/**
	 * Select balance type.
	 * 
	 * @param balancetype
	 */
	public void selectBalanceType(String balancetype) {
		if (balancetype != null && !"".equals(balancetype))
			browser.selectDropdownList(".id", "BalanceType", balancetype);
	}

	/**
	 * Select reservation status.
	 * 
	 * @param reservationstatus
	 */
	public void selectReservationStatus(String reservationstatus) {
		if (reservationstatus != null && !"".equals(reservationstatus))
			browser.selectDropdownList(".id", "ReservationStatus",
					reservationstatus);
	}

	/**
	 * Select order status.
	 * 
	 * @param orderstatus
	 */
	public void selectOrderStatus(String orderstatus) {
		if (orderstatus != null && !"".equals(orderstatus))
			browser.selectDropdownList(".id", "OrderStatus", orderstatus);
	}

	/**
	 * Select payment status.
	 * 
	 * @param paymentstatus
	 */
	public void selectPaymentStatus(String paymentstatus) {
		if (paymentstatus != null && !"".equals(paymentstatus))
			browser.selectDropdownList(".id", "PaymentStatus", paymentstatus);
	}

	/**
	 * Select collection status.
	 * 
	 * @param collectionstatus
	 */
	public void selectCollectionStatus(String collectionstatus) {
		if (collectionstatus != null && !"".equals(collectionstatus))
			browser.selectDropdownList(".id", "CollectionStatus",
					collectionstatus);
	}

	/**
	 * Select discount id.
	 * 
	 * @param discountid
	 */
	public void selectDiscountIDs(String discountid) {
		if (discountid != null && !"".equals(discountid))
			browser.selectDropdownList(".id", "DiscountIDs", discountid);
	}

	/**
	 * Select batch status.
	 * 
	 * @param batchstatus
	 */
	public void selectBatchStatus(String batchstatus) {
		if (batchstatus != null && !"".equals(batchstatus)) {
			browser.selectDropdownList(".id", "BatchStatus", batchstatus);
		}
	}

	/**
	 * Select location id no validate
	 * 
	 * @param locationIDnovalidate
	 */
	public void selectLocationIDNoValidate(String locationIDnovalidate) {
		if (locationIDnovalidate != null && !"".equals(locationIDnovalidate)) {
			browser.selectDropdownList(".id", "LocationIDNoValidate",
					locationIDnovalidate);
		}
	}

	/**
	 * Select customer type.
	 * 
	 * @param customertype
	 */
	public void selectCustomerType(String customertype) {
		if (customertype != null && !"".equals(customertype)) {
			browser.selectDropdownList(".id", "CustomerType", customertype);
		}
	}

	/**
	 * Select sort by.
	 * 
	 * @param sortby
	 */
	public void selectSortBy(String sortby) {
		if (sortby != null && !"".equals(sortby))
			browser.selectDropdownList(".id", "SortBy", sortby);
	}

	/**
	 * Select sales channel.
	 * 
	 * @param saleschannel
	 */
	public void selectSalesChannel(String saleschannel) {
		if (saleschannel != null && !"".equals(saleschannel))
			browser.selectDropdownList(".id", "SalesChannel", saleschannel);
	}

	public void selectCooperator(String cooperator) {
		if (cooperator != null && !"".equals(cooperator)) {
			browser.selectDropdownList(".id", "CooperatorID", cooperator);
			waitLoading();
		}
	}

	public void setDepositId(String depositId) {
		if (depositId != null && !"".equals(depositId)) {
			browser.setTextField(".id", "DepositID_2", depositId);
		}
	}

	public void selectDepositId(String depositId) {
		if (depositId != null && !"".equals(depositId)) {
			browser.selectDropdownList(".id", "DepositID_1", depositId);
			waitLoading();
		}
	}

	public void setFinSessionId(String id) {
		if (id != null && !"".equals(id)) {
			browser.setTextField(".id", "FinSessionId_2", id);
		}
	}

	public void selectFinSessionId(String id) {
		if (id != null && !"".equals(id)) {
			browser.selectDropdownList(".id", "FinSessionId_1", id);
			waitLoading();
		}
	}

	public void selectFrequency(String frequency) {
		if (frequency != null && !"".equals(frequency)) {
			browser.selectDropdownList(".id", "_DisbursementFrequencyUIName_",
					frequency);
			waitLoading();
		}
	}

	public void selectPeriod(String period) {
		if (period != null && !"".equals(period)) {
			browser.selectDropdownList(".id", "_DisbursementPeriodUIName_",
					period);
			waitLoading();
		}
	}

	public void selectRecipientType(String recipientType) {
		if (recipientType != null && !"".equals(recipientType)) {
			browser.selectDropdownList(".id", "RecipientType", recipientType);
			waitLoading();
		}
	}

	public void selectRevenueLocation(String revenueLoc) {
		if (revenueLoc != null && !"".equals(revenueLoc)) {
			browser.selectDropdownList(".id", "LWLocID", revenueLoc);
			waitLoading();
		}
	}

	public void selectInvoice(String invoice) {
		if (invoice != null && !"".equals(invoice)) {
			browser.selectDropdownList(".id", "Invoice", invoice);
		}
	}

	public void selectQuotaType(String quotaInterval) {
		if (quotaInterval != null && !"".equals(quotaInterval)) {
			browser.selectDropdownList(".id", "QuotaInterval", quotaInterval);
		}
	}

	public void selectTour(String tourId) {
		if (tourId != null && !"".equals(tourId)) {
			browser.selectDropdownList(".id", "TourID", tourId);
		}
	}

	public void selectTourName(String tourName) {
		if (tourName != null && !"".equals(tourName)) {
			browser.selectDropdownList(".id", "TourName", tourName);
		}
	}

	public void selectShowAllocation(String show) {
		if (show != null && !"".equals(show)) {
			browser.selectDropdownList(".id", "ShowAllocation", show);
		}
	}

	public void selectResidenceArea(String residenceArea) {
		if (residenceArea != null && !"".equals(residenceArea)) {
			browser.selectDropdownList(".id", "ResidenceArea", residenceArea);
			waitLoading();
		}
	}

	public void selectPermitEntrance(String permitEntrance) {
		if (permitEntrance != null && !"".equals(permitEntrance)) {
			browser.selectDropdownList(".id", "permitEntrance", permitEntrance);
		}
	}
	
	public void selectMonthYear(String monthYear) {
		if (monthYear != null && !"".equals(monthYear)) {
			browser.selectDropdownList(".id", "MonthYear", monthYear);
		}
	}

	public void selectDateGroup(String dateGroup) {
		if (dateGroup != null && !"".equals(dateGroup)) {
			browser.selectDropdownList(".id", "DateGroup", dateGroup);
		}
	}

	public void setRefundRequestId(String refundRequestId) {
		if (refundRequestId != null && !"".equals(refundRequestId)) {
			browser.setTextField(".id", "refundRequestId",
					refundRequestId);
		}
	}

	public void selectFLEXPORTMonthYear(String flMonthYear) {
		if (flMonthYear != null && !"".equals(flMonthYear)) {
			browser.selectDropdownList(".id", "FLEXPORTMonthYear", flMonthYear);
		}
	}

	public void selectCustLocation(String custLocation) {
		if (custLocation != null && !"".equals(custLocation)) {
			browser.selectDropdownList(".id", "CustLocation", custLocation);
			waitLoading();
		}
	}

	public void selectStateProvince(String state) {
		if (state != null && !"".equals(state)) {
			browser.selectDropdownList(".id", "StateProvince", state);
			waitLoading();
		}
	}
	
	public void selectGiftCardLocation(String giftCardLoc) {
		if (giftCardLoc != null && !"".equals(giftCardLoc)) {
			browser.selectDropdownList(".id", "GiftCardLocation", giftCardLoc);
			waitLoading();
		}
	}
	
	public void selectExcludePaymentGrp(String paymentGrp) {
		if (paymentGrp != null && !"".equals(paymentGrp)) {
			browser.selectDropdownList(".id", "ExcludePaymentGrp", paymentGrp);
			waitLoading();
		}
	}
	
	public void selectReservationNumber(String resNumber) {
		if (resNumber != null && !"".equals(resNumber)) {
			browser.selectDropdownList(".id", "ReservationNumber", resNumber);
			waitLoading();
		}
	}
	
	public void setTransmittalID(String transmittalID) {
		if (transmittalID != null && !"".equals(transmittalID)) {
			browser.setTextField(".id", "TransmittalID_2", transmittalID);
		}
	}
	/**
	 * select include gift card payments/ refunds.
	 * @param GcInclude
	 */
	public void selectRefundsIssueToGc(String GcInclude){
		if(GcInclude != null && !"".equals(GcInclude)){
			browser.selectDropdownList(".id", "RAFeeAccount", GcInclude);
		}
	}
	
	/**
	 * Set customer Number(MDWFP #) for "Customer Information Report"
	 * @param customerNumber
	 */
	public void setCustomerNumber(String customerNumber) {
		if (StringUtil.notEmpty(customerNumber)) {
			browser.setTextField(".id", "CustomerNumber", customerNumber);
		}
	}
	
	/**
	 * Select privilege product for "Lapsed Customer Report"
	 * @param privilegeProduct
	 */
	public void selectprivilegeProduct(String[] privilegeProduct) {
		IHtmlObject[] from;
		IHtmlObject[] to;
		if(privilegeProduct!=null&&privilegeProduct.length>0){
			from = browser.getDropdownList(".id","PrivilegeProduct_MainList");
			to = browser.getDropdownList(".id","PrivilegeProduct_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).add(privilegeProduct);
			Browser.unregister(from,to,add,remove);
		}
	}
	
	public void selectOtherPrivilegeProduct(String[] privilegeProduct) {
		IHtmlObject[] from;
		IHtmlObject[] to;
		if(privilegeProduct!=null&&privilegeProduct.length>0){
			from = browser.getDropdownList(".id","OtherPrivilegeProductsSold_MainList");
			to = browser.getDropdownList(".id","OtherPrivilegeProductsSold_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[1], remove[1]).add(privilegeProduct);
			Browser.unregister(from,to,add,remove);
		}
	}
	
	/**
	 * Select privilege product for "Alternate Proof of Residency Report"
	 * @param privilegeProduct
	 */
	public void selectAPRprivilegeProduct(String[] privilegeProductAPR){
		IHtmlObject[] from;
		IHtmlObject[] to;
		if(privilegeProductAPR!=null&&privilegeProductAPR.length>0){
			from = browser.getDropdownList(".id","Privilege_MainList");
			to = browser.getDropdownList(".id","Privilege_SelectedList_");

			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[0], remove[0]).add(privilegeProductAPR);
			Browser.unregister(from,to,add,remove);
		}
	}
	
    /**
     * Set up Privilege Held Start Date for "Lapsed Customer Report"
     * @param priHeldStartDate
     */
	public void setPrivilegeHeldStartDate(String priHeldStartDate) {
		if (StringUtil.notEmpty(priHeldStartDate)) {
			browser.setTextField(".id", "PrivilegeHeldStartDate_ForDisplay", priHeldStartDate);
		}
	}
	/**
	 * Set up Privilege Held End Date for "Lapsed Customer Report"
	 * @param priHeldEndDate
	 */
	public void setPrivilegeHeldEndDate(String priHeldEndDate) {
		if (StringUtil.notEmpty(priHeldEndDate)) {
			browser.setTextField(".id", "PrivilegeHeldEndDate_ForDisplay", priHeldEndDate);
		}
	}
	/**
	 * Set up Privilege Lapsed Start Date for "Lapsed Customer Report"
	 * @param priLapsedStartDate
	 */
	public void setPrivilegeLapsedStartDate(String priLapsedStartDate) {
		if (StringUtil.notEmpty(priLapsedStartDate)) {
			browser.setTextField(".id", "PrivilegeLapsedStartDate_ForDisplay", priLapsedStartDate);
		}
	}
	/**
	 * Set up Privilege Lapsed End Date for "Lapsed Customer Report"
	 * @param priLapsedEndDate
	 */
	public void setPrivilegeLapsedEndDate(String priLapsedEndDate) {
		if (StringUtil.notEmpty(priLapsedEndDate)) {
			browser.setTextField(".id", "PrivilegeLapsedEndDate_ForDisplay", priLapsedEndDate);
		}
	}
	/**
	 * Set up vehicle number or ID for "Vehicle Information Report"
	 * @param priLapsedEndDate
	 */
	public void setVehicleNum(String priLapsedEndDate) {
		if (StringUtil.notEmpty(priLapsedEndDate)) {
			browser.setTextField(".id", "vehicleIDOrNum", priLapsedEndDate);
		}
	}
	/**
	 * Set up certificationType for "Certification Renewal Report "
	 */
	public void setCertificationType(String certificationType) {
		if (StringUtil.notEmpty(certificationType)) {
			browser.selectDropdownList(".id", "CertificationType", certificationType);
		}
	}
	/**
	 * Set up all report criteria data.
	 * 
	 * @param rd
	 *            - reprot data
	 */
	public void setReportCriteria(ReportData rd) {
		selectContract(rd.contract);
		selectAgencyID(rd.agencyID);
		selectAgencyLocID(rd.agencyLocID);
		selectRegionID(rd.regionID);
		selectDistrictID(rd.districtID);
		selectRegionLocID(rd.regionLocID);
		selectLocationID(rd.locationID);
		selectLocationClass(rd.locationClass);//added for H&F
		selectTransactionTypes(rd.transactionTypes);//added for H&F
		setFiscalYear(rd.fiscalYear); //added for H&F
		selectGiftCardLocation(rd.giftCardLoc);
		selectReportLoc(rd.reportLoc);
		selectFrequency(rd.frequency);
		selectPeriod(rd.period);
		selectRecipientType(rd.recipientType);
		selectRecipient_Name(rd.recipient_name);
		selectStationID(rd.stationID);
		selectCallCenterID(rd.callCenterID);
		selectOperator(rd.operator);
		selectProductType(rd.productType);
		selectRefund_Status(rd.refund_Status);
		selectRefund_Payment(rd.refund_Payment);
		selectLotteryName(rd.lotteryName);
		selectLotterySchedule(rd.lotterySched);
		selectReportByLocation(rd.reportByLocation);
		selectTransactionDetailReportBy(rd.transactionDetailReportBy);
		selectPark(rd.park);
		selectfacilityHQID(rd.facilityHQID);
		selectFacilityID(rd.facilityID);
		selectFacilityLocID(rd.facilityLocID);
		selectPosReportBy(rd.posReportBy);
		selectRevenueLocation(rd.lWLocID);
		selectCooperator(rd.cooperatorID);
		selectTourCategory(rd.tourCategory);
		selectProductGroup(rd.productGroup);
		selectPosProducts(rd.posPrds);
		selectPromoCodes(rd.promoCodes);
		selectpaymentMethods(rd.paymentMethod);
		selectUnitOfStay(rd.unitOfStay);
		selectReservationType(rd.reservationType);
		selectVoucherProgram(rd.voucherProgram);
		selectOrderBy(rd.orderBy);
		selectAreaID(rd.areaID);
		setSiteNum(rd.siteNumber);
		setReservationNumber(rd.resNumber);
		selectSortOrder(rd.sortOrder);
		selectShowEmptySites(rd.showEmptySites);
		selectCategory(rd.category);
		selectReportSubType(rd.reportSubType);
		selectBalanceType(rd.balanceType);
		selectTour(rd.tourID);
		selectResidenceArea(rd.residenceArea);
		selectTourName(rd.tourName);
		selectShowAllocation(rd.showAllocation);
		this.selectDisplayColumns(rd.displayColumns);
		this.selectBreakAccountCodes(rd.breakAccountCode);
		this.selectSubTotalBy(rd.subTotalBy);
		selectReportByType(rd.reportByType);
		selectFixLengthType(rd.fixedLengthType);
		selectIncludeDOB(rd.includeDOB);
		selectIncludePrivilegeCode(rd.includePrivilegeCode);
		selectIncludePrivilegeDescription(rd.includePrivilegeDescription);
		selectDisplayPrivilegeDescription(rd.displayPrivDescription);
		selectIncludePrivilegeEffectiveToDate(rd.includePrivEffeciveToDate);
		selectIncludePrivilegeEffectiveFromDate(rd.includePrivEffectiveFromDate);
		selectIncludeDriverLicenseNumber(rd.includeDriverLicenseNum);
		selectIncludeHunterEducationNumber(rd.includeHunterEducationNum);
		selectCustomerAttribute(rd.customerAttribute);
		selectOtherPrivilegeProduct(rd.otherPrivilegeProducts);
		selectIncludePrivilegeActivities(rd.includePrivilegeActivity);
		selectIncludeAP(rd.includeAP);
		selectIncludeCPF(rd.includeCPF);
		
		// input date and time
		setStartDate(rd.startDate);
		this.waitLoading();
		setStartTime_time(rd.startTime);
		selectStartTime_ampm(rd.startTimeampm);
		setEndDate(rd.endDate);
		this.waitLoading();
		setEndTime_time(rd.endTime);
		selectEndTime_ampm(rd.endTimeampm);
		setTourDate(rd.tourDate);
//		moveFocusOutOfDateComponent();
		setOrderDate(rd.orderDate);
//		moveFocusOutOfDateComponent();
		
		selectPermitEntrance(rd.permitEntrance);
		selectExceptions(rd.exceptions);
		selectPaymentGroup(rd.paymentGroup);
		selectPaymentGrp(rd.paymentGroup);
		selectReservationStatus(rd.reservationStatus);
		selectOrderStatus(rd.orderStatus);
		selectPaymentRefundStatus(rd.paymentRefundStatus);
		selectPaymentStatus(rd.paymentStatus);
		selectCollectionStatus(rd.collectionStatus);
		selectDiscountIDs(rd.discountIDs);
		selectBatchStatus(rd.batchStatus);
		selectincludeAdjustments(rd.includeAdjustments);
		selectYesNoFlag(rd.yesNoFlag);
		selectIncludeRAFee(rd.includeRAFee);
		selectCollectIssueLocation(rd.collectIssueLocation);
		selectRAFeeAccount(rd.rAFeeAccount);
		selectGcPayments(rd.includeGcPayments);
		selectIncludeTaxes(rd.includeTaxes);
		selectAttrFeeType(rd.attrFeeType);
		selectSupressEmptyLine(rd.supressEmptyLine);
		setMaxLetters(rd.maxLetters);
		selectInvoice(rd.invoice);
		selectLocationIDNoValidate(rd.locationIDNoValidate);
		selectCustomerType(rd.customerType);
		selectSortBy(rd.sortBy);
		selectSalesChannel(rd.salesChannel);
		selectDepositId(rd.depositID_1);
		setDepositId(rd.depositID_2);
		selectFinSessionId(rd.finSessionId_1);
		setFinSessionId(rd.finSessionId_2);
		selectQuotaType(rd.quotaInterval);
		selectResidenceArea(rd.residenceArea);
		selectMonthYear(rd.monthYear);
		selectDateGroup(rd.dateGroup);
		setRefundRequestId(rd.refundRequestId);
		selectFLEXPORTMonthYear(rd.flMonthYear);
		selectCustLocation(rd.custLocation);
		selectStateProvince(rd.state);
		selectExcludePaymentGrp(rd.paymentGrp);
		selectReservationNumber(rd.resNumber);
		setTransmittalID(rd.transmittalID);
		if (rd.doNotEmail != null && rd.doNotEmail == "false") {
			clickDoNotEmail();
		}
		if (rd.doNotFax != null && rd.doNotFax == "false") {
			clickDoNotFax();
		}
		selectReportFormat(rd.reportFormat);
		selectDeliveryMethod(rd.deliveryMethod);
		selectDateType(rd.dateType);
		selectByReportType(rd.reportType);
		selectReportType(rd.reportType);
		selectByTicketType(rd.byTicketType);
		selectReportSection(rd.reportSection);
		selectIncludeDistributed(rd.includeDistributed);
		this.selectRefundsIssueToGc(rd.includeRefIssueGiftCard);
		
		setCustomerNumber(rd.customerNumber);
		selectprivilegeProduct(rd.privilegeProduct);
		selectAPRprivilegeProduct(rd.privilegeProductAPR);
		setPrivilegeHeldStartDate(rd.privilegeHeldStartDate);
		setPrivilegeHeldEndDate(rd.privilegeHeldEndDate);
		setPrivilegeLapsedStartDate(rd.privilegeLapsedStartDate);
		setPrivilegeLapsedEndDate(rd.privilegeLapsedEndDate);
		setVehicleNum(rd.vehicleID);
		setCertificationType(rd.certificationType);
	}

	/**
	 * @param posReportBy
	 */
	private void selectPosReportBy(String posReportBy) {
		if(!StringUtil.isEmpty(posReportBy)){
			browser.selectDropdownList(".id","POSReportBy",posReportBy);
		}
	}

	/**
	 * @param contract
	 */
	private void selectContract(String contract) {
		if(!StringUtil.isEmpty(contract)){
			browser.selectDropdownList(".id","GrossLocationID",contract);
		}
	}

	/**
	 * @param transactionDetailReportBy
	 */
	private void selectTransactionDetailReportBy(
			String transactionDetailReportBy) {
		if(!StringUtil.isEmpty(transactionDetailReportBy)){
			browser.selectDropdownList(".id","TransactionDetailReportBy",transactionDetailReportBy);
		}
		
	}

	/**
	 * 
	 */
	private void selectBreakAccountCodes(String flag) {
		if(!StringUtil.isEmpty(flag)){
			browser.selectDropdownList(".id","BreakAccountCodes", flag);
		}
		
	}

	/**
	 * @param districtID
	 */
	public void selectDistrictID(String districtID) {
		if(!StringUtil.isEmpty(districtID)){
			browser.selectDropdownList(".id", "DistrictID", districtID);
		}
		
	}

	public void selectReportByType(String reportByType){
		if(!StringUtil.isEmpty(reportByType)){
			browser.selectDropdownList(".id", "ReportByType", reportByType);
			this.waitLoading();
		}
	}
	
	public void selectFixLengthType(String fixLengthType){
		if(!StringUtil.isEmpty(fixLengthType)){
			browser.selectDropdownList(".id", "FixedLengthType", fixLengthType);
		}
	}
	
	public void selectIncludeDOB(String includeDOB){
		if(!StringUtil.isEmpty(includeDOB)){
			browser.selectDropdownList(".id", "IncludeDOB", includeDOB);
			this.waitLoading();
		}
	}
	
	public void selectIncludePrivilegeCode(String includePrivilegeCode){
		if(!StringUtil.isEmpty(includePrivilegeCode)){
			browser.selectDropdownList(".id", "IncludePrivilegeCode", includePrivilegeCode);
		}
	}
	
	public void selectIncludePrivilegeDescription(String includePrivilegeDescription){
		if(StringUtil.notEmpty(includePrivilegeDescription)){
			browser.selectDropdownList(".id","IncludePrivilegeDescription",includePrivilegeDescription);
			this.waitLoading();
		}
	}
	
	public void selectDisplayPrivilegeDescription(String displayPrivilegeDescription){
		if(StringUtil.notEmpty(displayPrivilegeDescription)){
			browser.selectDropdownList(".id","DisplayPrivilegeDescription",displayPrivilegeDescription);
		}
	}
	
	public void selectIncludePrivilegeEffectiveToDate(String includePrivilegeEffectiveToDate){
		if(StringUtil.notEmpty(includePrivilegeEffectiveToDate)){
			browser.selectDropdownList(".id","IncludePrivilegeEffectiveToDate",includePrivilegeEffectiveToDate);
		}
	}
	
	public void selectIncludePrivilegeEffectiveFromDate(String includePrivilegeEffectiveFromDate){
		if(StringUtil.notEmpty(includePrivilegeEffectiveFromDate)){
			browser.selectDropdownList(".id","IncludePrivilegeEffectiveFromDate",includePrivilegeEffectiveFromDate);
		}
	}
	
	public void selectIncludeDriverLicenseNumber(String includeDriverLicenseNumber){
		if(StringUtil.notEmpty(includeDriverLicenseNumber)){
			browser.selectDropdownList(".id","IncludeDriverLicenseNumber",includeDriverLicenseNumber);
		}
	}
	
	public void selectIncludeHunterEducationNumber(String includeHunterEducationNumber){
		if(StringUtil.notEmpty(includeHunterEducationNumber)){
			browser.selectDropdownList(".id","IncludeHunterEducationNumber",includeHunterEducationNumber);
		}
	}
	
	public void selectCustomerAttribute(String customerAttribute){
		if(StringUtil.notEmpty(customerAttribute)){
			browser.selectDropdownList(".id","CustomerAttribute",customerAttribute);
		}
	}
	
	public void selectIncludePrivilegeActivities(String includePrivilegeActivities){
		if(StringUtil.notEmpty(includePrivilegeActivities)){
			browser.selectDropdownList(".id","IncludePrivilegeActivities",includePrivilegeActivities);
		}
	}
	
	public void selectIncludeAP(String includeAP){
		if(StringUtil.notEmpty(includeAP)){
			browser.selectDropdownList(".id","IncludeAP",includeAP);
		}
	}
	
	public void selectIncludeCPF(String includeCPF){
		if(StringUtil.notEmpty(includeCPF)){
			browser.selectDropdownList(".id","IncludeCPF",includeCPF);
		}
	}
	
	/**
	 * @param promoCodes
	 */
	public void selectPromoCodes(String[] promoCodes) {
		if(promoCodes!=null&&promoCodes.length>0){
			IHtmlObject[] from = browser.getDropdownList(".id","PromoCode_MainList");
			IHtmlObject[] to = browser.getDropdownList(".id","PromoCode_SelectedList_");
			IHtmlObject[] add = browser.getHtmlObject(".class","Html.A",".text","Add>>");
			IHtmlObject[] remove = browser.getHtmlObject(".class","Html.A",".text","<<Remove");
			
			new GroupSelectionComponent((ISelect)from[0], (ISelect)to[0], add[1], remove[1]).add(promoCodes);
			Browser.unregister(from,to,add,remove);
		}
	}

	/**
	 * @param posPrds
	 */
	public void selectPosProducts(String[] posPrds) {
		if(posPrds!=null&&posPrds.length>0){
			for(String posPrd:posPrds){
				browser.setTextField(".id","autoCompleteProcut_MainList",posPrd);
				this.clickAdd(0);
			}
		}
	}
	
	private void clickAdd(int index){
		browser.clickGuiObject(".class","Html.A",".text","Add>>",index);
	}

	/**
	 * @param includeGcPayments
	 */
	private void selectGcPayments(String includeGcPayments) {
		if(!StringUtil.isEmpty(includeGcPayments)){
			browser.selectDropdownList(".id","IncludeGCPayments",includeGcPayments);
		}
	}

	// ==================below is new method======================

	/* Get the default value of the Agency dropdown list */
	public String getAgencyDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"AgencyID", 0);
	}

	/* Get the default value of the Region/State/Division dropdown list */
	public String getRegionDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"RegionID", 0);
	}

	public String getAllRegionOptions() {
		return this.getAllSelectionCriteria("RegionID");
	}

	/*
	 * Get the default value of the Forest/National Park/District/Area Office
	 * dropdown list
	 */
	public String getDistrictDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"DistrictID", 0);
	}

	/*
	 * Get the default value of the Project/Field Office/Ranger District
	 * dropdown list
	 */
	public String getProjectDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"ProjectID", 0);
	}

	/* Get the default value of the Facility HQ */
	public String getFacilityHQDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"FacilityHQID", 0);
	}

	/** Get park loc first option */
	public String getParkDefaultID() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"FacilityLocID", 0);
	}

	/**Get Park first option*/
	public String getFacilityID(){
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"FacilityID", 0);
	}
	public String getAllParks() {
		return this.getAllSelectionCriteria("FacilityID");
	}

	/* Get the first value in the section of Park dropdown list */
	public String getFirstOfParkDropdownList() {
		List<String> facilityIDs = browser.getDropdownElements(".class",
				"Html.SELECT", ".id", "FacilityID");
		return facilityIDs.get(0);
	}

	/**
	 * Get all Loop/Area Name
	 * 
	 * @return loopOrAreaNames_2
	 */
	public String getAllLoopOrAreaNames() {
		return this.getAllSelectionCriteria("AreaID");
	}

	/* Get the default value of the Area/Loop */
	public String getLoopOrAreaDefault() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",	"AreaID", 0);
	}

	/**
	 * Get all value of Reservation status list
	 * 
	 * @return
	 */
	public String getAllReservationStatus() {
		return this.getAllSelectionCriteria("ReservationStatus");
	}

	public boolean checkResStatusExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"ReservationStatus");
	}

	/* Get the default value of Reservation Status */
	public String getReservationStatusDefault() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"ReservationStatus", 0);
	}

	/**
	 * Get all value of Sort By list
	 * 
	 * @return
	 */
	public String getAllSortBy() {
		return this.getAllSelectionCriteria("SortBy");
	}

	/* Get the default value of Sort By */
	public String getSortByDefault() {
		return browser.getDropdownListValue(".class", "Html.SELECT", ".id",
				"SortBy", 0);
	}

	/**
	 * Get all value of Sort By list
	 * 
	 * @return
	 */
	public String getAllReportLayout() {
		return this.getAllSelectionCriteria("ReportLayout");
	}

	/**
	 * get one specific value of one object
	 * 
	 * @param propertyKey
	 * @param properValue
	 * @param specificProperty
	 * @return specific property value
	 */
	public String getObjectSpecificProperty(String propertyKey,
			String properValue, String specificProperty) {
		IHtmlObject[] objs = browser.getHtmlObject(propertyKey, properValue);
		String text = objs[0].getAttributeValue(specificProperty);
		Browser.unregister(objs);
		return text;
	}

	/**
	 * Get all value of Report Format list
	 * 
	 * @return
	 */
	public String getAllReportFormat() {
		return this.getAllSelectionCriteria("report_format");
	}

	/**
	 * Get all value of Delivery Method list
	 * 
	 * @return
	 */
	public String getAllDeliveryMethod() {
		return this.getAllSelectionCriteria("deliveryprotocolid");
	}

	public String getDefaultReportBy() {
		return browser.getDropdownListValue(".id", "UnDstrLocBase", 0);
	}

	public String getAllReportBy() {
		return this.getAllSelectionCriteria("UnDstrLocBase");
	}

	public String getDefaultIncludeDistributed() {
		return browser.getDropdownListValue(".id", "IncludeDistributed", 0);
	}

	public String getAllIncludeDistributedOptions() {
		return this.getAllSelectionCriteria("IncludeDistributed");
	}

	public String getDefaultReconcileStatus() {
		return browser.getDropdownListValue(".id", "IsReconciled", 0);
	}

	public String getAllReconcileStatus() {
		return this.getAllSelectionCriteria("IsReconciled");
	}

	public String getDefaultPaymentGrp() {
		return browser.getDropdownListValue(".id", "UnDstrPmtGrp", 0);
	}

	public String getAllPaymentGrps() {
		return this.getAllSelectionCriteria("UnDstrPmtGrp");
	}

	public String getDefaultReportFormat() {
		return browser.getDropdownListValue(".id", "report_format", 0);
	}

	public String getDefaultLotteryName() {
		return browser.getDropdownListValue(".id", "LotteryName", 0);
	}

	public void selectLotteryName(String lotteryName) {
		if (lotteryName != null && lotteryName.length() > 0) {
			browser.selectDropdownList(".id", "LotteryName", lotteryName);
			waitLoading();
		}
	}

	public String getDefaultLotterySchedule() {
		return browser.getDropdownListValue(".id", "LotterySched", 0);
	}

	public void selectLotterySchedule(String lotterySchd) {
		if (lotterySchd != null && lotterySchd.length() > 0) {
			browser.selectDropdownList(".id", "LotterySched", lotterySchd);
			waitLoading();
		}
	}

	public String getAllLotterySchedule() {
		List<String> groups = browser
				.getDropdownElements(".id", "LotterySched");
		StringBuffer group = new StringBuffer();
		for (int i = 0; i < groups.size(); i++) {
			group.append(groups.get(i) + "#");
		}

		return group.toString();
	}

	public String getDefaultOrderStatus() {
		return browser.getDropdownListValue(".id", "OrderStatus", 0);
	}

	public String getAllOrderStatus() {
		return this.getAllSelectionCriteria("OrderStatus");
	}

	public boolean checkOrderStatusExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"OrderStatus");
	}

	public String getAllOrderReservationStatus() {
		return this.getAllSelectionCriteria("OrderReservationStatus");
	}

	public String getAllBalanceType() {
		return this.getAllSelectionCriteria("BalanceType");
	}

	public String getAllPaymentStatus() {
		return this.getAllSelectionCriteria("PaymentStatus");
	}

	public boolean checkPaymenttatusExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"PaymentStatus");
	}

	public String getAllCollectionStatus() {
		return this.getAllSelectionCriteria("CollectionStatus");
	}

	public String getAllDateType() {
		return this.getAllSelectionCriteria("DateType");
	}

	public String getAllSelectionCriteria(String id) {
		List<String> groups = browser.getDropdownElements(".id", id);
		StringBuffer criterias = new StringBuffer();
		for (int i = 0; i < groups.size(); i++) {
			criterias.append(groups.get(i) + "#");
		}
		return criterias.toString();
	}

	public String getDefaultValueForDropDownList(String property) {
		return browser.getDropdownListValue(".id", property, 0);
	}

	public int getAllOrderResStatusNum() {
		List<String> groups = browser.getDropdownElements(".id",
				"OrderReservationStatus");
		return groups.size();
	}

	public List<String> getDefaultOrderResStatus() {
		IHtmlObject[] objs = browser.getDropdownList(".id",
				"OrderReservationStatus");
		ISelect list = (ISelect) objs[0];
		List<String> statuses = list.getSelectedTexts();
		Browser.unregister(objs);
		return statuses;
	}

	/**
	 * This method used to retrieve error message
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "NOTSET");
		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);

		return text;
	}

	public boolean checkObjectExist(String objId) {
		return browser.checkHtmlObjectExists(".id", objId);
	}

	/**
	 * Check Facility ID exits In SC Park Revenue Detail Report, it exits
	 * ".id"(FacilityID),but it just a property no real meaning.
	 */
	public boolean checkFacilityIDExist() {
		return browser.checkHtmlObjectExists(".id", "FacilityID", ".class",
				"Html.SELECT");
	}
	
	public List<String> getAllProductCategory(){
		return browser.getDropdownElements(".id", "ProductType");
	}
	
	public List<String> getAllIncludeLoops(){
		return browser.getDropdownElements(".id", "IncludeLoops");
	}
	
	public List<String> getSlipReservationType(){
		return browser.getDropdownElements(".id", "SlipReservationType");
	}
	
	public List<String> getIncludeRegistrations(){
		return browser.getDropdownElements(".id", "IncludeRegistrations");
	}
	
	public List<String> getIncludeInOutofState(){
		return browser.getDropdownElements(".id", "IncludeInOutOfState");
	}
	
	public List<String> getIncludeTotals(){
		return browser.getDropdownElements(".id", "IncludeTotals");
	}
	
	public String getProductCategoryValue(){
		return browser.getDropdownListValue(".id", "ProductType");
	}
	
	public String getIncludeLoopsValue(){
		return browser.getDropdownListValue(".id", "IncludeLoops");
	}

	public String getSlipReservationTypeValue(){
		return browser.getDropdownListValue(".id", "SlipReservationType");
	}
	
	public String getIncludeRegistrationsValue(){
		return browser.getDropdownListValue(".id", "IncludeRegistrations");
	}
	
	public String getIncludeInOutofStateValue(){
		return browser.getDropdownListValue(".id", "IncludeInOutOfState");
	}
	
	public String getIncludeTotalsValue(){
		return browser.getDropdownListValue(".id", "IncludeTotals");
	}
	
	public boolean isIncludeLooopsFieldExist(){
		return browser.checkHtmlObjectDisplayed(".id", "IncludeLoops");
	}
	
	public boolean isSlipReservationTypeExist(){
		return browser.checkHtmlObjectDisplayed(".id", "SlipReservationType");
	}
	
	public List<String> getToursElement() {
		return browser.getDropdownElements(".id", "TourID");
	}
	
	public boolean isRegionDpExist(){
		return browser.checkHtmlObjectExists(".id", "RegionID");
	}
	
	public boolean isForestDpExist(){
		return browser.checkHtmlObjectExists(".id", "DistrictID");
	}
	
	public boolean isRangerDistrictExist(){
		return browser.checkHtmlObjectExists(".id", "ProjectID");
	}
	
	public boolean isParkDpExist(){
		return browser.checkHtmlObjectExists(".id", "FacilityID");
	}
	
	public List<String> getParkElement(){
		return browser.getDropdownElements(".id", "FacilityID");
	}
	
	public boolean isStationDpExist(){
		return browser.checkHtmlObjectExists(".id", "StationID");
	}
	
	public boolean checkFacilityDropdownListExisted() {
		return browser.checkHtmlObjectDisplayed(".text", "Facility") || browser.checkHtmlObjectDisplayed(".id", "FacilityLocID");
	}
	
	private Property[] dockArea() {
		return Property.toPropertyArray(".id", "DockAreaID");
	}
	
	private Property[] marinaRateType() {
		return Property.toPropertyArray(".id", "MarinaRateType");
	}
	
	private Property[] includeRenewalNotification() {
		return Property.toPropertyArray(".id", "IncludeRenewalNotification");
	}
	
	private Property[] slipType() {
		return Property.toPropertyArray(".id", "SlipType");
	}
	
	public void selectIncludeRenewalNotification(String include) {
		browser.selectDropdownList(includeRenewalNotification(), include);
	}
	
	public String getIncludeRenewalNotification() {
		return browser.getDropdownListValue(includeRenewalNotification(), 0);
	}
	
	public List<String> getIncludeRenewalNotificationOptions() {
		return browser.getDropdownElements(includeRenewalNotification());
	}
	
	public void selectMarinaRateType(String rateType) {
		browser.selectDropdownList(marinaRateType(), rateType);
	}
	
	public String getMarinaRateType() {
		return browser.getDropdownListValue(marinaRateType(), 0);
	}
	
	public List<String> getMarinaRateTypeOptions() {
		return browser.getDropdownElements(marinaRateType());
	}
	
	private Property[] reportBy() {
		return Property.toPropertyArray(".id", "ReportBy");
	}
	
	public void selectReportBy(String by) {
		browser.selectDropdownList(reportBy(), by);
	}
	
	public String getReportBy() {
		return browser.getDropdownListValue(reportBy(), 0);
	}
	
	public List<String> getReportByOptions() {
		return browser.getDropdownElements(reportBy());
	}
	
	private Property[] product() {
		return Property.toPropertyArray(".id", "Product");
	}
	
	public void selectProduct(String product) {
		browser.selectDropdownList(product(), product);
	}
	
	public String getProduct() {
		return browser.getDropdownListValue(product(), 0);
	}
	
	public List<String> getProductOptions() {
		return browser.getDropdownElements(product());
	}
	
	public void selectDockArea(String dock) {
		browser.selectDropdownList(dockArea(), dock);
	}
	
	public String getDockArea() {
		return browser.getDropdownListValue(dockArea(), 0);
	}
	
	public void selectSlipType(String type) {
		browser.selectDropdownList(slipType(), type);
	}
	
	public String getSlipType() {
		return browser.getDropdownListValue(slipType(), 0);
	}
}
