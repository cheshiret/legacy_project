package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
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
 * @Date  Oct 22, 2012
 */
public class OrmsSlipListPage extends OrmsPage {

	private static OrmsSlipListPage _instance = null;
	
	private OrmsSlipListPage() {}
	
	public static OrmsSlipListPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipListPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", new RegularExpression("SlipSearchResultBar", false));
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", new RegularExpression("(Slip)?Search(Slip)?Result", false));
	}
	
	
	public String getFacility() {
		return getAttributeValueByName("Facility");
	}
	
	public String getAgency() {
		return getAttributeValueByName("Agency");
	}
	
	public String getRegion() {
		return getAttributeValueByName("Region");
	}
	
	public String getCurrentSeason(){
		return getAttributeValueByName("Current Season");
	}
	
	private String slipResultPrefix = "SlipAvailability(Result|SearchCriteria)-\\d+\\.";
	
	private String slipSearchPrefix = "SlipAvailabilitySearchCriteria-\\d+\\.";
	
	protected Property[] arrivalTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "arrivalDate_ForDisplay", false));
	}
	protected Property[] departureTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "departureDate_ForDisplay", false));
	}
	protected Property[] nightsTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "units", false));
	}
	
	protected Property[] beginingTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "beginingDate_ForDisplay",false));
	}
	
	protected Property[] endingTextField() {  
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "endingDate_ForDisplay",false));
	}
	
	protected Property[] rangeNightsTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "rangeUnits",false));
	}
	
	protected Property[] boatLengthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "boatLength",false));
	}
	
	protected Property[] boatWidthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "boatWidth",false));
	}
	
	protected Property[] boatDepthTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "boatDepth",false));
	}
	
	protected Property[] slipNumbersTextField(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "slipNumbers",false));
	}
	protected Property[] dockDropdownList(){
		return Property.toPropertyArray(".id", new RegularExpression("(" + slipSearchPrefix + "dockName)|(DropdownExt-\\d+\\.selectedValue)", false));
	}
	protected Property[] showReservableOnlyCheckBox(){
		return Property.toPropertyArray(".id", new RegularExpression(slipSearchPrefix + "showReservableOnly", false));
	}
	protected Property[] departureDateTextField(){
		return Property.concatPropertyArray(input("text"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.units", false));
	}
	
	private boolean isObjectExist(Property[] properties){
		return browser.checkHtmlObjectExists(properties);
	}
	
	public void veifySearchCriteriaUI(boolean isBasicSearch){
		boolean passed = true;
		//These only shown under advance search
		passed &= MiscFunctions.compareResult("Begining text field", !isBasicSearch, isObjectExist(this.beginingTextField()));
		passed &= MiscFunctions.compareResult("Ending text field", !isBasicSearch, isObjectExist(this.endingTextField()));
		passed &= MiscFunctions.compareResult("Range of Nights", !isBasicSearch, isObjectExist(this.rangeNightsTextField()));
		//These only shown under basic search
		passed &= MiscFunctions.compareResult("Arrival text field", isBasicSearch, isObjectExist(this.arrivalTextField()));
		passed &= MiscFunctions.compareResult("Departure text field", isBasicSearch, isObjectExist(this.departureTextField()));
		passed &= MiscFunctions.compareResult("Nights", isBasicSearch, isObjectExist(this.nightsTextField()));
		
		
		passed &= MiscFunctions.compareResult("Boat length", true, isObjectExist(this.boatLengthTextField()));
		passed &= MiscFunctions.compareResult("Boat width", true, isObjectExist(this.boatWidthTextField()));
		passed &= MiscFunctions.compareResult("Boat depth", true, isObjectExist(this.boatDepthTextField()));

		passed &= MiscFunctions.compareResult("Slip Number", true, isObjectExist(this.slipNumbersTextField()));
		passed &= MiscFunctions.compareResult("Dock Area", true, isObjectExist(this.dockDropdownList()));
		
		if(!passed) {
			throw new ErrorOnPageException("Not all checkpoints are passed, please refer to log for details info.");
		} 
		logger.info("All checkpoints are PASSED for search criterial ui.");
	}
	
	public void setArrivalDate(String arrival) {               
		browser.setCalendarField(arrivalTextField(), arrival);
	}
	
	public void setArrivalOfSlip(String arrival) {
		browser.setCalendarField(".id", new RegularExpression(slipResultPrefix + "arrivalDate_ForDisplay", false), arrival);
	}
	
	private void setArrivalOfSlip(String arrival, int index) {
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		browser.setTextField(".id", new RegularExpression(slipResultPrefix + "arrivalDate_ForDisplay", false), arrival, true, index, table, IText.Event.LOSEFOCUS);
		Browser.unregister(objs);
	}
	
	public void clickWaitingListBySlipCode(String slipCode) {
		int index = this.getSlipRowNum(slipCode);
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(Transfer|Waiting) List", false), index);
	}
	
	private int getSlipRowNum(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode) - 1;
		Browser.unregister(objs);
		return rowNum;
	}
	
	public void setSlipArrival(String arrival, String slipCode){
		int rowNum = this.getSlipRowNum(slipCode);
		setArrivalOfSlip(arrival, rowNum);
		ajax.waitLoading();
	}
	
	private void setNightsOfSlip(String nights, int index){
		browser.setTextField(".id", new RegularExpression(slipResultPrefix + "units", false), nights, index , IText.Event.LOSEFOCUS);
	}
	
	public void setSlipNights(String nights, String slipCode){
		int rowNum = this.getSlipRowNum(slipCode);
		setNightsOfSlip(nights, rowNum);
		ajax.waitLoading();
	}
	
	public String getNightsOfSlip(int index){
		return browser.getTextFieldValue(".id", new RegularExpression(slipResultPrefix + "units", false), index);
	}
	
	public void setDepartureDate(String departure) {
		browser.setCalendarField(".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.departureDate_ForDisplay", false), departure);
	}
	
	public void setNights(int nights) {
		browser.setTextField(".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.units", false), String.valueOf(nights),0,IText.Event.LOSEFOCUS);
	}
	
	public void setMonth(int months) {
		browser.selectDropdownList(".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.units", false), String.valueOf(months), 0);
	}
	
	public void setBoatLength(double length) {
		if(Math.abs(length - 0.0) > 0.0001)
			browser.setTextField(boatLengthTextField(), String.valueOf(length));
		else
			browser.setTextField(boatLengthTextField(), StringUtil.EMPTY);
	}
	
	public void setBoatWidth(double width) {
		if(Math.abs(width - 0.0) > 0.0001)
			browser.setTextField(boatWidthTextField(), String.valueOf(width));
		else
			browser.setTextField(boatWidthTextField(), StringUtil.EMPTY);
	}
	
	public void setBoatDepth(double depth) {
		if(Math.abs(depth - 0.0) > 0.0001)
			browser.setTextField(boatDepthTextField(), String.valueOf(depth));
		else
			browser.setTextField(boatDepthTextField(), StringUtil.EMPTY);
	}
	
	public void setBoatLength(String length){
		browser.setTextField(boatLengthTextField(), length);
	}
	
	public void setBoatWidth(String width){
		browser.setTextField(boatWidthTextField(), width);
	}
	
	public void setBoatDepth(String depth){
		browser.setTextField(boatDepthTextField(), depth);
	}
	
	public void setBoatDemensions(String length, String width, String depth){
		this.setBoatLength(length);
		this.setBoatWidth(width);
		this.setBoatDepth(depth);
	}
	
	public void setSlipNumber(String number) {
		if(StringUtil.notEmpty(number))
			browser.setTextField(slipNumbersTextField(), number);
		else
			browser.setTextField(slipNumbersTextField(), StringUtil.EMPTY);
	}
	
	public void selectDockArea(String dock) {
		browser.selectDropdownList(dockDropdownList(), dock);
	}
	
	public boolean isShowReservableOnlyChecked(){
		return browser.isCheckBoxSelected(showReservableOnlyCheckBox());
	}
	
	public void selectShowReservableOnly() {
		browser.selectCheckBox(showReservableOnlyCheckBox());
	}
	
	public void unselectShowReservableOnly() {
		browser.unSelectCheckBox(showReservableOnlyCheckBox());
	}
	
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^Search$", false), true);
	}
	
	public void clickAllowRafting() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Allow Rafting", true);
	}
	
	public boolean isAllowRaftingButtonEnable(){
		boolean isEnable = true;
		boolean isExisting = browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Allow Rafting");
		if(isExisting){
			isEnable = browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", "Allow Rafting");
		}else{
			isEnable = false;
		}
		return isEnable;
	}
	
	public void clickCancelRafting(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel Rafting", true);
	}
	
	public void selectAllCheckboxes() {
		browser.selectCheckBox(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false));
	}
	
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	public boolean isSlipExists(String slipCode) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", slipCode);
	}
	
	public void clickSlipCodeLink(String slipCode) {
		browser.clickGuiObject(".class", "Html.A", ".text", slipCode, true);
	}
	
	public void clickFirstSlipCode(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".classname", "oddRow", ".name","e_Glow"));
		browser.clickGuiObject(".class", "Html.A", true, 0, objs[0]);
	}
	
	public String getErrorMessage(){
		String errMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",".id", new RegularExpression("NOTSET|V-100010|slip.search.errorcode_\\d+", false));
		for(int i=0; i<objs.length; i++){
			if(i!=0){
				errMsg += " ";
			}
			errMsg += objs[i].text().trim();
		}
		Browser.unregister(objs);
		return errMsg;
	}
	
	public boolean isTransientNightsTextFieldExists() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text", ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.units", false));
	}
	
	public boolean isLeaseMonthsDropdownListExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.units", false));
	}
	
	public boolean isSeasonDropDownListExists(){
		return browser.checkHtmlObjectDisplayed(".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonId", false) );
	}
	
	public boolean isNotOriginal(){
		return  this.isSeasonDropDownListExists()        //This is a seasonal slip 
				||browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.Table", ".text", new RegularExpression("(Transfer From.*)|(Check-in Slip Reservation)", false)));
	}
	
	public void selectSlipByCode(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression("^\\s?" + code, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find NSS Child slip(CD: " + code + ").");
		}
		
		RegularExpression reg = new RegularExpression("GenericGrid-\\d+\\.selectedItems", false);
													 //GenericGrid-\\d+\\.selectedItems
		// when original purchase, it is check box -- Nicole
		if(!this.isNotOriginal()){
			browser.selectCheckBox(Property.toPropertyArray(".id", reg), 0, true, objs[0]);
			ajax.waitLoading();
		} else if(this.isNotOriginal()){// when transfer or check-in(lease, NSS), it is a radio button -- Nicole
			browser.selectRadioButton(Property.toPropertyArray(".id", reg), true, 0, objs[0]);
			ajax.waitLoading();
		}
		Browser.unregister(objs);
	}
	
	public void selectSlipRadioButtonByCode(String code) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression("^\\s?" + code, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find NSS Child slip(CD: " + code + ").");
		}                                                                                                          
		browser.selectRadioButton(Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false)), true, 0, objs[0]);
		Browser.unregister(objs);
	}
	
	public boolean checkSlipRadioButtonIsExisting(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(code + ".*", false)));
		
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find slip(CD: " + code + ").");
		} 
		
		boolean isExisting = browser.checkHtmlObjectExists(Property.toPropertyArray(".id", new RegularExpression("GenericGrid-\\d+\\.selectedItems", false)), objs[0]);
		Browser.unregister(objs);
		return isExisting;
	}
	
	private IHtmlObject[] getSlipListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", new RegularExpression("grid_\\d+_LIST",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get slip list table object.");
		}
		return objs;
	}
	
	private List<String> getColumnListValues(String columnName){
		IHtmlObject[] objs = this.getSlipListTableObject();

		IHtmlTable table = (IHtmlTable)objs[0];
		int col = table.findColumn(0, columnName);
		List<String> columnValues = table.getColumnValues(col);
		columnValues.remove(0);
		Browser.unregister(objs);
		return columnValues;
	}
	
	public List<String> getSlipResultInfo(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode) ;
		int arrivalColumnNum = table.findColumn(0, "Arrival");
		int nightsColumnNum = table.findColumn(0, "Nights");
		List<String> result = table.getRowValues(rowNum);
		result.set(arrivalColumnNum, this.getArrivalDate(rowNum-1));
		result.set(nightsColumnNum, this.getNightsOfSlip(rowNum-1));
		Browser.unregister(objs);
		return result;
	}
	
	public String getTransferToSlipArrivalDate(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.arrivalDate", false)).replaceAll("Arrival", "").trim();
	}
	
	public String getTransferToSlipDepatureDate(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.departureDate", false)).replaceAll("Departure", "").trim();
	}
	
	public String getNightsOfTextField(){
		return browser.getTextFieldValue(".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.units", false));
	}
	
	public String getNights(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.units", false)).replaceAll("Nights", "").trim();
	}
	
	public String getDays(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.units", false)).replaceAll("Days", "").trim();
	}
	
	public boolean isDepartureDateTextFieldExisting(){
		return browser.checkHtmlObjectExists(departureDateTextField());
	}
	
	public boolean isBoatLengthTextFieldExisting(){
		return browser.checkHtmlObjectExists(boatLengthTextField());
	}
	
	public boolean isBoatWidthTextFieldExisting(){
		return browser.checkHtmlObjectExists(boatWidthTextField());
	}
	
	public boolean isBoatDepthTextFieldExisting(){
		return browser.checkHtmlObjectDisplayed(boatDepthTextField());
	}
	
	public List<String> getSlipNumListValues(){
		return this.getColumnListValues("Slip #(Name)");
	}
	
	public List<String> getDockAresListValues(){
		return this.getColumnListValues("Dock/Area");
	}
	
	public void setupSearchSlipInfo(SlipInfo slip){
		if(StringUtil.notEmpty(slip.getArrivalDate())){
			this.setArrivalDate(slip.getArrivalDate());
			ajax.waitLoading();
		}
		
		if(StringUtil.notEmpty(slip.getDepartureDate())){
			this.setDepartureDate(slip.getDepartureDate());
			ajax.waitLoading();
		}
		
		if(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE.equals(slip.getReservationType())){
			if(NumberUtil.isGreaterThanZero(slip.getMonths())) {
				this.setMonth(slip.getMonths());
			}
		} else if (OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT.equals(slip.getReservationType())){
			if(NumberUtil.isGreaterThanZero(slip.getNights())) {
				this.setNights(slip.getNights());
			}
		}
		ajax.waitLoading();
		
		if(StringUtil.notEmpty(slip.getCode())){
			this.setSlipNumber(slip.getCode());
		}
		
		if(StringUtil.notEmpty(slip.getParentDockArea())){
			this.selectDockArea(slip.getParentDockArea());
			ajax.waitLoading();
		}
		
		if(slip.getBoatLength() >= 0){
			this.setBoatLength(slip.getBoatLength());
		}
		
		if(slip.getBoatWidth() >= 0){
			this.setBoatWidth(slip.getBoatWidth());
		}
		
		if(slip.getBoatDepth() >= 0){
			this.setBoatDepth(slip.getBoatDepth());
		}
		
		//TODO setup other search criteria
	}
	
	public void searchSlip(SlipInfo slip,boolean isShowResOnly){
		this.setupSearchSlipInfo(slip);
		if(!isShowResOnly){
			this.unselectShowReservableOnly();
		}else{
			this.selectShowReservableOnly();
		}
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void verifySlipExistorNot(String expectValue, boolean shouldExist){
		List<String> actSlipListValue = this.getSlipNumListValues();
		if(shouldExist){
			if(!actSlipListValue.contains(expectValue)){
				throw new ErrorOnPageException("The Slip number list should contain " + expectValue);
			}
		} else {
			if(actSlipListValue.contains(expectValue)){
				throw new ErrorOnPageException("The Slip number list should NOT contain " + expectValue);
			}
		}
	}
	
	/**
	 * Verify dock name, and these docks don't have child dock
	 */
	public void verifyDockNameForNonParent(String entry){
		List<String> actualListValue = this.getDockAresListValues();
		boolean result = true;
		
		for(String actual:actualListValue){
			if(!actual.startsWith(entry)){
				result &= false;
				logger.error("The dock doesn't contain "+entry+" should be NOT in the search result!!");
			}
		}
		
		if(!result){
			throw new ErrorOnPageException("---Check logs above!!");
		}
	}
	
	/**
	 * Get Arrival Date in slip list record.
	 * NOT get the Arrival Date search criteria.
	 */
	public String getArrivalDate() {
		//SlipAvailabilitySearchCriteria-2106082238.arrivalDate_ForDisplay
		return browser.getTextFieldValue(".id", new RegularExpression(slipResultPrefix + "arrivalDate_ForDisplay", false));
	}
	
	public String getArrivalDateOfSearchCriteria(){
		return browser.getTextFieldValue(this.arrivalTextField());
	}
	
	public String getSlipNumberOfSearchCriteria(){
		return browser.getTextFieldValue(this.slipNumbersTextField());
	}
	
	public String getDockAreaOfSearchCriteria(){
		return browser.getDropdownListValue(this.dockDropdownList(), 0);
	}
	
	public boolean isShowReservalbeOnlySelected(){
		return browser.isCheckBoxSelected(this.showReservableOnlyCheckBox());
	}
	
	public String getArrivalDate(int index) {
		return browser.getTextFieldValue(".id", new RegularExpression("SlipAvailabilityResult-\\d+\\.arrivalDate_ForDisplay", false), index);
	}
		
	/**
	 * This method get the hight date for the special slip
	 * @param slipCode
	 * @return
	 */
	public List<String> getHightLightAvailabilityDates(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode) - 1;
		IHtmlObject[] tds = browser.getHtmlObject(".id", new RegularExpression("r_" + rowNum + "_c_\\d{1,2}",false));
		List<String> result = new ArrayList<String>();
		for(int i=0; i< tds.length; i++){
			if(StringUtil.notEmpty(tds[i].getAttributeValue("insl"))){
				result.add(table.getCellValue(0, 10+i +1).replaceAll("[SMTWF]", "").trim());
			}
		}
		Browser.unregister(tds);
		Browser.unregister(objs);
		return result;
	}
	
	/**
	 * Get the total available days for the slip search result
	 * @return
	 */
	public int getTotalAvailableDayCellOfGrid(){
		IHtmlObject[] dateObjs = browser.getHtmlObject(".class","Html.TD",".className", new RegularExpression("clndr day[SMWTF(SS)]",false));
		return dateObjs.length;
	}
	
	/**
	 * get the availability grid month(s), there 2 situations:
	 * 				----- 2 months: March-April 2013
	 * 				----- only 1 month: April 2013
	 * 
	 * @return 
	 * 				---- 2 months: {"March 2013", "April 2013"}
	 *				---- 1 month: {"April 2013"}
	 */
	public String[] getAvailabilityGridMonths() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "month");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find availability date DIV object.");
		}
		String text = objs[0].text();
		String years[] = RegularExpression.getMatches(text, "[0-9]{4}");
		String dates[] = text.replace(years[0], StringUtil.EMPTY).split("-");
		for(int i = 0; i < dates.length; i ++) {
			dates[i] = dates[i].trim() + " " + years[0];
		}
		Browser.unregister(objs);
		
		return dates;
	}
	
	public String getFirstDayOfGrid(){
		IHtmlObject[] dateObjs = browser.getHtmlObject(".class","Html.TD",".className", new RegularExpression("clndr day[SMWTF(SS)]",false));
		String date = dateObjs[0].text().replaceAll("[SMTWF]", "").trim();
		String month = this.getAvailabilityGridMonths()[0];
		return date + " " + month;
	}
	
	/**
	 * This method get the content that will shown when mouse over slip code 
	 * @param slipCode
	 * @return
	 */
	public String getMouseOverSlipCodeInfo(String slipCode){
		Property[] properties = new Property[2];
		properties[0] = new Property(".class", "Html.A");
		properties[1] = new Property(".text", slipCode);
		IHtmlObject[] objs = browser.getHtmlObject(properties);
		if(objs.length < 1){
			String proStr = "";
			for(Property p:properties){
				proStr += p + ",";
			}
			throw new ItemNotFoundException("Cannot find the object with properties(" + proStr + ").");
		}
		String content = objs[0].getAttributeValue("title");
		Browser.unregister(objs);
		return content;
	}
	
	/**
	 * This method get the content that will shown when mouse over date icon in the list grid
	 * @param slipCode
	 * @return
	 */
	public String getMouseOverDateIconContent(String slipCode, String date){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode);
//		Property[] properties = new Property[3];
//		properties[0] = new Property(".class", "Html.TD");
//		properties[1] = new Property(".text", "A");
//		properties[2] = new Property(".date", date);
		IHtmlObject[] tds = browser.getHtmlObject(".class", "Html.TD",".date", date, table);
		IHtmlObject[] divObjs = browser.getHtmlObject(".class","Html.DIV", tds[rowNum-1]);
		if(divObjs.length < 1){
			throw new ItemNotFoundException("Cannot find the object under row for date:" + date);
		}
		String content = divObjs[0].getAttributeValue("title");
		Browser.unregister(objs);
		Browser.unregister(tds);
		Browser.unregister(divObjs);
		return content;
	}
	
	public String getMouseOverDateIconContent(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode);
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR",".name","e_Glow", table);
		Property[] properties = new Property[2];
		properties[0] = new Property(".class", "Html.DIV");
		properties[1] = new Property(".text", "A");
		IHtmlObject[] divObjs = browser.getHtmlObject(properties, trs[rowNum - 1]);
		if(divObjs.length < 1){
			String proStr = "";
			for(Property p:properties){
				proStr += p + ",";
			}
			throw new ItemNotFoundException("Cannot find the object with properties(" + proStr + ").");
		}
		String content = divObjs[0].getAttributeValue("title");
		Browser.unregister(objs);
		Browser.unregister(trs);
		Browser.unregister(divObjs);
		return content;
	}
	
	
	/**
	 * This method get the QTY numbers for special slip
	 * @param slipCode
	 * @return
	 */
	public List<String> getQtyFromDropDownList(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode);
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR",".name","e_Glow", table);
		Property[] properties = new Property[1];
		properties[0] = new Property(".id", new RegularExpression(slipResultPrefix + "selectedQuantityStr",false));
		Browser.unregister(objs);
		Browser.unregister(trs);
		return browser.getDropdownElements(properties, trs[rowNum - 1]);
	}
	
	/**
	 * This method is used to check that the default value for qty
	 * @param defaultValue
	 */
	public void verifyQtyDefaultValues(String defaultValue){
		boolean passed = true;
		IHtmlObject[] qtyObjs = browser.getHtmlObject(".id", new RegularExpression(slipResultPrefix + "selectedQuantityStr",false));
		int number = qtyObjs.length;
		for(int i=0; i<number; i++){
			String value = browser.getDropdownListValue(".id", new RegularExpression(slipResultPrefix + "selectedQuantityStr",false), i);
			if(!value.equalsIgnoreCase(defaultValue)){
				logger.error("The " + i + "th qty default value is not correct! expected:" + defaultValue + ", but acturally is:" + value);
				passed = false;
			}
		}
		Browser.unregister(qtyObjs);
		if(!passed){
			throw new ErrorOnPageException("Not all the default value for Qty is:" + defaultValue + ", please check the log above!");
		}
	}
	
	/**
	 * This method is used to check all the drop down list for Qty is enabled
	 */
	public void verifyQtyDropdownListEnabled(){
		boolean passed = true;
		IHtmlObject[] qtyObjs = browser.getHtmlObject(".id", new RegularExpression(slipResultPrefix + "selectedQuantityStr",false));
		int number = qtyObjs.length;
		for(int i=0; i<number; i++){
			if(!qtyObjs[i].isEnabled()){
				logger.error("The " + i + "th drop down list for qty is not enabled!");
				passed = false;
			}
		}
		Browser.unregister(qtyObjs);
		if(!passed){
			throw new ErrorOnPageException("Not all the drop down list for Qty is enabled, please check the log above!");
		}
	}
	
	public boolean qtyDropdownListExist(){
		IHtmlObject[] qtyObjs = browser.getHtmlObject(".id", new RegularExpression(slipResultPrefix + "selectedQuantityStr",false));
		if(qtyObjs.length < 1){
			return false;
		}else{
			return true;
		}
	}
	
	public void verifySlipListInfo(SlipInfo slip) {
		boolean passed = true;
		List<String> slipResult = this.getSlipResultInfo(slip.getCode());
		passed &= MiscFunctions.compareResult("Slip code:", slip.getCode(),	slipResult.get(1));
		if(slip.getMinBoatLength() == -1){
			passed &= MiscFunctions.compareResult("Min Boat Length:", StringUtil.EMPTY, slipResult.get(2));
		}else{
			passed &= MiscFunctions.compareResult("Min Boat Length:", String.valueOf(slip.getMinBoatLength()), slipResult.get(2).replace("ft", "").trim());
		}
		if(slip.getMaxBoatLength() == -1){
			passed &= MiscFunctions.compareResult("Max Boat Length:", StringUtil.EMPTY, slipResult.get(3));
		}else{
			passed &= MiscFunctions.compareResult("Max Boat Length:", String.valueOf(slip.getMinBoatLength()), slipResult.get(3).replace("ft", "").trim());
		}
		passed &= MiscFunctions.compareResult("Length:", String.valueOf(slip.getLength()), slipResult.get(4).replace("ft", "").trim());
		passed &= MiscFunctions.compareResult("Width:", String.valueOf(slip.getWidth()), slipResult.get(5).replace("ft", "").trim());
		passed &= MiscFunctions.compareResult("Depth:", String.valueOf(slip.getDepth()), slipResult.get(6).replace("ft", "").trim());
		
		passed &= MiscFunctions.compareResult("Dock/Area:", slip.getParentDockArea(), slipResult.get(7));
		passed &= MiscFunctions.compareResult("Arrival:", slip.getArrivalDate(), slipResult.get(8));
		passed &= MiscFunctions.compareResult("Nights:", String.valueOf(slip.getNights()), slipResult.get(9));
		
		if(!passed){
			throw new ErrorOnPageException("Some of the field content may not correct, please check the log above!");
		}
	}
	
	public void clickOverrideRules(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Override Rule\\(s\\)", false));
	}
	
	private String getAttributeValueByName(String name) {
		String nameRegex;
		if(name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else {
			nameRegex = name;
		}
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression(nameRegex, false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - " + name);
		}
		
		String text = objs[0].text().replace(name, StringUtil.EMPTY);
		Browser.unregister(objs);
		
		return text;		
	}
	
	public String getSlipReservationNum() {
		return getAttributeValueByName("Slip Reservation #");
	}
	
	public String getTransferFromMarina() {
		return getAttributeValueByName("Marina");
	}
	
	public String getTransferFromDockArea() {
		return getAttributeValueByName("Dock/Area");
	}
	
	public String getTransferFromSlipNumName() {
		return getAttributeValueByName("Slip # (Name)");
	}
	
	public String getTransferFromArrivalDate() {
		return getAttributeValueByName("Arrival");
	}
	
	public String getTransferFromDepartureDate() {
		return getAttributeValueByName("Departure");
	}
	
	public String getTransferFromNights() {
		return getAttributeValueByName("Nights");
	}

	public String getTransferFromMonths() {
		return getAttributeValueByName("Months");
	}
	
	/**
	 * verify the transfer from slip reservation details info
	 * @param resNum
	 * @param transferFromSlip
	 */
	public void verifyTransferFromSlipInfo(String resNum, SlipInfo transferFromSlip) {
		logger.info("Verify 'Transfer From Slip' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Transfer from Slip Reservation #", resNum, getSlipReservationNum());
		result &= MiscFunctions.compareResult("Transfer from Marina", transferFromSlip.getMarina(), getTransferFromMarina());
		result &= MiscFunctions.compareResult("Transfer from Dock/Area", transferFromSlip.getParentDockArea(), getTransferFromDockArea());
		result &= MiscFunctions.compareResult("Transfer from Slip # - Name", transferFromSlip.getCode() + "-" + transferFromSlip.getName(), getTransferFromSlipNumName());
		result &= MiscFunctions.compareResult("Transfer from Arrival Date", transferFromSlip.getArrivalDate(), getTransferFromArrivalDate());
		result &= MiscFunctions.compareResult("Transfer from Departure Date", transferFromSlip.getDepartureDate(), getTransferFromDepartureDate());
		if(transferFromSlip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Transfer from Nights", transferFromSlip.getNights(), Integer.parseInt(getTransferFromNights()));
		} else {
			result &= MiscFunctions.compareResult("Transfer from Months", transferFromSlip.getMonths(), Integer.parseInt(getTransferFromMonths()));
		}
		
		if(!result) {
			throw new ErrorOnPageException("'Transfer From Slip' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Transfer From Slip' section info is correct.");
	}
	
	public List<String> getTypeofSelectItem(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", new RegularExpression("GenericGrid-\\d+.selectedItems", false));
		if(objs.length<1){
			throw new ItemNotFoundException("Can't find select items!");
		}
		
		List<String> types = new ArrayList<String>();
		for(int i=0; i<objs.length; i++){
			types.add(objs[i].getProperty("type"));
		}
		return types;
	}
	
	/**
	 * 
	 * @param expectValue should be:
	 *        checkbox (original purchase)
	 *        radio (transfer)
	 */
	public void verifyTypeOfSelectItem(String expectValue){
		List<String> types = getTypeofSelectItem();
		
		List<String> typeList = new ArrayList<String>();
		// remove duplicate value
		for(int i=0; i<types.size(); i++){
			if(!typeList.contains(types.get(i))){
				typeList.add(types.get(i));
			}
		}
		
		if(typeList.size() != 1){
			throw new ErrorOnPageException("The select item should be "+expectValue+", but now it has other select items!");
		} else {
			if(!typeList.get(0).equalsIgnoreCase(expectValue)){
				throw new ErrorOnPageException("The select item should be "+expectValue+", but not "+typeList.get(0));
			}
		}
	}
	
	public void clickMarinaResultsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Marina Results", false));
	}
	
	public void clickMarinaDetailsLink(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Marina Details", false));
	}
	
	public void clickFacilityNameLink(String facilityName){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression(facilityName, false));
	}
	
	protected Property[] map(){
		return Property.toPropertyArray(".class","Html.A",".text","Map");
	}
	
	public boolean checkMapButtonEnabled(){
		return browser.checkHtmlObjectEnabled(map());
	}
	
	/**
	 * Get slip status for special date
	 * @param slipCode
	 * @return
	 */
	public String getSlipStatus(String slipCode){
		IHtmlObject[] objs = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode);
		int colNum = table.findColumn(0, "Availability");
		if(colNum < 0){
			colNum = 8;
		}
		String status = table.getCellValue(rowNum, colNum);
		return status;
	}
	
	public boolean isStatusALink(String slipName, String status){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Heml.SPAN", ".id", "Status_" + slipName);
		boolean exist = browser.checkHtmlObjectExists(".class", "Html.A", ".text", status, objs[0]);
		Browser.unregister(objs);
		return exist;
	}
	
	public boolean isCheckBoxExistForSlip(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(".*"+code+".*", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find NSS Child slip(CD: " + code + ").");
		}
		RegularExpression reg = new RegularExpression("GenericGrid-\\d+\\.selectedItems", false);
		// when original purchase, it is check box 
		boolean exist = browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".id", reg), objs[0]);
		Browser.unregister(objs);
		return exist;
	}
	
	public boolean isRadioBtnExistForSlip(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(".*"+code+".*", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find slip(CD: " + code + ").");
		}
		boolean exist = browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class", "Html.INPUT.RADIO"), objs[0]);
		Browser.unregister(objs);
		return exist;
	}
	
	public boolean isAddToListBtnExist(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(".*"+code+".*", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find slip(CD: " + code + ").");
		}
		boolean exist = browser.checkHtmlObjectDisplayed(Property.toPropertyArray(".class", "Html.A", ".text","Add to List"), objs[0]);
		Browser.unregister(objs);
		return exist;
	}
	
	public void selectQtyFromDropDownList(String slipCode, String value){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".name", "e_Glow", ".text", new RegularExpression(".*"+slipCode+".*", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find NSS Child slip(CD: " + slipCode + ").");
		}
		RegularExpression reg = new RegularExpression("SlipAvailabilityResult-\\d+\\.selectedQuantityStr", false);
		browser.selectDropdownList(".id", reg, value, true, objs[0]);
		ajax.waitLoading();
		Browser.unregister(objs);
	}

	public void selectSeason(String season) {
		browser.selectDropdownList(".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonId", false), season);
	}
	
	public String getTrailBarContent(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TABLE", ".id", "_trail_bar_");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find trail bar table object.");
		}
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public List<String> getWalkinDates(String slipCode) {
		IHtmlObject objs[] = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode) - 1;
		IHtmlObject[] tds = browser.getHtmlObject(".id", new RegularExpression("r_" + rowNum + "_c_\\d{1,2}",false));
		if(tds.length < 1) throw new ItemNotFoundException("Cannot find Slip(CD=" + slipCode + ") search list date grid.");
		
		List<String> result = new ArrayList<String>();
		for(int i = 0; i < tds.length; i ++) {
			if(tds[i].text().equalsIgnoreCase("W")) {
				result.add(tds[i].getProperty(".date"));
			}
		}
	
		Browser.unregister(tds);
		Browser.unregister(objs);
		return result;
	}
	
	public String getSlipGridStatus(String slipCode, String date) {
		IHtmlObject objs[] = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode) - 1;
		IHtmlObject[] tds = browser.getHtmlObject(".id", new RegularExpression("r_" + rowNum + "_c_\\d{1,2}",false));
		if(tds.length < 1) throw new ItemNotFoundException("Cannot find Slip(CD=" + slipCode + ") search list date grid.");
		
		String status = "";
		for(int i = 0; i < tds.length; i ++) {
			if(DateFunctions.compareDates(tds[i].getProperty(".date"), date) == 0) {
				status = tds[i].text();
				break;
			}
		}
		
		Browser.unregister(tds);
		Browser.unregister(objs);
		
		return status;
	}
	
	public String getAvailabilityBySlipCode(String slipCode){
		return getSpecificColValueBySlipCode(slipCode, "Availability");
	}
	
	public void verifyAvailability(String expect, String slipCode){
		String actual = getAvailabilityBySlipCode(slipCode);
		if(!MiscFunctions.compareResult("Availability of slip "+slipCode, expect, actual)){
			throw new ErrorOnPageException("Availability of slip isn't correct!");
		}
	}
	
	private String getSpecificColValueBySlipCode(String slipCode,String colName){
		IHtmlObject objs[] = this.getSlipListTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowNum = table.findRow(1, slipCode);
		
		int colNum = table.findColumn(0, colName);
		if(colName.equalsIgnoreCase("Availability") && colNum == -1){
			int count = table.columnCount();
			colNum = count-1;
		}

		String tmp = table.getCellValue(rowNum, colNum);
		Browser.unregister(objs);
		return tmp;
	}
	
	public String getNextAvailableDate(String slipCode){
		return getSpecificColValueBySlipCode(slipCode, "Next Avail Date");
	}
	
	public boolean isFlagAsRaftingCheckBoxEnable(){
		return browser.checkHtmlObjectEnabled(Property.concatPropertyArray(input("checkbox"), 
				".id",new RegularExpression("SlipAvailabilitySearchCriteria-1\\d+\\.rafting",false)));
	}
	
	public boolean isFlagAsRaftingCheckBoxSelected(){
		IHtmlObject[] objs = browser.getHtmlObject(Property.concatPropertyArray(input("checkbox"), 
				".id",new RegularExpression("SlipAvailabilitySearchCriteria-1\\d+\\.rafting",false)));
		System.out.println(objs.length);
		return browser.isCheckBoxSelected(Property.concatPropertyArray(input("checkbox"), 
				".id",new RegularExpression("SlipAvailabilitySearchCriteria-1\\d+\\.rafting",false)));
	}
	
	public void searchSlipBySlipCode(String slipCode){
		this.setSlipNumber(slipCode);
		this.clickGo();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void clickTransferList(String code){
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR",".text",  new RegularExpression(".*" + code+".*", false)));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find NSS Child slip(CD: " + code + ").");
		}
		
		browser.clickGuiObject(".class", "Html.A", ".text", "Transfer List", false, 0, objs[0]);
	}
	
	public boolean isSlipReservationtoRenewExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TR", ".text", new RegularExpression( "Slip Reservation to Renew.*",false));
	}
	
	public String[] getSlipReservationtoRenewSectionSlipInfo(){
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression( "Slip Reservation #.*Marina.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find Slip Reservation to Renew section table...");
		}
		
		IHtmlTable table=(IHtmlTable)objs[objs.length-1];
		String[] renewSlip=new String[7];
		renewSlip[0]=table.getCellValue(0, 3).split("Slip Reservation #")[1].trim();//Slip Reservation #
		renewSlip[1]=table.getCellValue(0, 5).split("Marina")[1].trim();//Marina
		renewSlip[2]=table.getCellValue(0, 7).split("Dock/Area")[1].trim();//Dock/Area
		renewSlip[3]=table.getCellValue(0, 9).split("Slip # \\(Name\\)")[1].trim();//Slip # (Name) 
		renewSlip[4]=table.getCellValue(0, 11).split("Arrival")[1].trim();//Arrival
		renewSlip[5]=table.getCellValue(0, 13).split("Departure")[1].trim();//Departure
		renewSlip[6]=table.getCellValue(0, 15).split("Months")[1].trim();//Months
		
		Browser.unregister(objs);
		return renewSlip;
		
	}
}
