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
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
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
public class OrmsSlipSearchPage extends OrmsPage {
	
	private static OrmsSlipSearchPage _instance = null;
	
	private OrmsSlipSearchPage() {}
	
	public static OrmsSlipSearchPage getInstance() {
		if(_instance == null) {
			_instance = new OrmsSlipSearchPage();
		}
		
		return _instance;
	}
	
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("^\\W*(Basic|Advanced)\\W*$", false));
	}
	
	private String prefix = "SlipAvailabilitySearchCriteria-\\d+\\.";
	                                    
	/**
	 * There are three slip reservation types on the page: Seasonal, Lease, Transient
	 * @return isEnabled
	 *         true  enable
	 *         false not enable
	 */
	public boolean isSlipReservationTypeEnabled() {
		boolean isEnabled = true;
		IHtmlObject objs[] = browser.getHtmlObject(".id", new RegularExpression(prefix + "slipReservationType", false));
		if(objs.length > 0) {
			for(int i=0; i<objs.length; i++){
				isEnabled = objs[i].isEnabled();
			}
		} else {
			return false;
		}
		
		return isEnabled;
	}
	
	
	private void selectSlipReservationType(int index) {
		browser.selectRadioButton(".id", new RegularExpression(prefix + "slipReservationType", false), index);
	}
	
	public void selectSeasonal() {
		selectSlipReservationType(0);
	}
	
	public void selectLease() {
		selectSlipReservationType(1);
	}
	
	public void selectTransient() {
		selectSlipReservationType(2);
	}
	
	public boolean isMarinaDropdownListExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "marinaID", false));
	}
	
	public String getMarinaDropdownListSelectedValue() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix + "marinaID", false));
	}
	
	public String getStateSelectedValue() {
		return browser.getDropdownListValue(".id", new RegularExpression(prefix + "state", false));
	}      
	
	public List<String> getStateElements(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix + "state", false));
	}
	
	public void selectMarina(String name) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "marinaID", false), name);
	}
	
	public void setMarina(String name) {
		browser.setTextField(".id", new RegularExpression(prefix + "marinaName", false), name);
	}
	
	public String getMarinaText() {
		return browser.getTextFieldValue(".id", new RegularExpression(prefix + "marinaName", false));
	}
	
	public String getMarinaTextInRenewalSection() {
		return browser.getTextFieldValue(".class","Html.SPAN",".text", new RegularExpression("^Marina.*", false));
	}
	
	public void selectTypeOfUse(String type) {
		browser.selectDropdownList(".id", new RegularExpression(prefix + "unitOfStay", false), type);
	}
	
	public boolean isArrivalDateTextFieldExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(prefix + "arrivalDate_ForDisplay", false));
	}
	
	public void setArrivalDate(String arrival) {
		setDateAndGetMessage((IText)browser.getTextField(".id", new RegularExpression(prefix + "arrivalDate_ForDisplay", false))[0], arrival);
	}
	
	public void setDepartureDate(String departure) {
		setDateAndGetMessage((IText)browser.getTextField(".id", new RegularExpression(prefix + "departureDate_ForDisplay", false))[0], departure);
	}
	
	public boolean isMonthsDropdownListExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id", new RegularExpression(prefix + "units", false));
	}
	
	public String getMonths(){
		return browser.getDropdownListValue(".id", new RegularExpression(prefix + "units", false));
	}
	
	public List<String> getMonthsListValue(){
		return browser.getDropdownElements(".id", new RegularExpression(prefix + "units", false));
	}
	
	public void selectMonths(int months) {                      
		browser.selectDropdownList(".id", new RegularExpression(prefix + "units", false), String.valueOf(months));
	}
	
	public void setNights(int nights) {
		browser.setTextField(".id", new RegularExpression(prefix + "units", false), String.valueOf(nights),0,IText.Event.LOSEFOCUS);
	}
	
	public void setBoatLength(Object length) {
		browser.setTextField(".id", new RegularExpression(prefix + "boatLength", false), String.valueOf(length));
	}
	
	public void setBoatWidth(Object width) {
		browser.setTextField(".id", new RegularExpression(prefix + "boatWidth", false), String.valueOf(width));
	}
	
	public void setBoatDepth(Object depth) {
		browser.setTextField(".id", new RegularExpression(prefix + "boatDepth", false), String.valueOf(depth));
	}
	
	public void setSlipNumber(String slipNum) {
		browser.setTextField(".id", new RegularExpression(prefix + "slipNumbers", false), slipNum);
	}
	
	public void setDockArea(String dock) {
		browser.setTextField(".id", new RegularExpression(prefix + "dockName", false), dock);
	}
	
	public void selectSlipType(String type) {
		if(StringUtil.isEmpty(type)){
			browser.selectDropdownList(".id", new RegularExpression(prefix + "slipType", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression(prefix + "slipType", false), type);
		}
	}
	
	public boolean isShowReservableOnlySelected(){
		return browser.isCheckBoxSelected(".id", new RegularExpression(prefix + "showReservableOnly", false));
	}
	
	public void selectShowReservableOnly() {
		browser.selectCheckBox(".id", new RegularExpression(prefix + "showReservableOnly", false));
	}
	
	public void unselectShowReservableOnly() {
		browser.unSelectCheckBox(".id", new RegularExpression(prefix + "showReservableOnly", false));
	}
	
	public void selectShowMode(int index){
		browser.selectRadioButton(".id", new RegularExpression(prefix + "seasonalSlipSearchFilter", false), index);
	}
	
	public boolean isAdvancedIsExisting(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", new RegularExpression("(\\s*)Advanced(Â)? >>\\s*",false));
	}
	
	public void clickAdcanced() {                                                        
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(\\s*)AdvancedÂ? >>\\s*",false), true);
	}
	
	public void clickBasic(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("(\\s)*<< Basic(\\s)*",false), true);
	}
	
	public void selectADAAccessible() {
		browser.selectCheckBox(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[104\\]\\:BOOLEAN_YESNO", false));
	}
	
	public void unSelectADAAccessible() {
		browser.unSelectCheckBox(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[104\\]\\:BOOLEAN_YESNO", false));
	}
	
	public void setBasePricingLength(int length) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8025\\]", false), String.valueOf(length));
	}
	
	public void selectChannelLatitudeDirection(String direction) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8001\\]", false), direction);
	}
	
	public void setChannelLatitudeDegree(int degree) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8002\\]", false), String.valueOf(degree));
	}
	
	public void setChannelLatitudeMinute(int minute) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8003\\]", false), String.valueOf(minute));
	}
	
	public void setChannelLatitudeSecond(int second) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8004\\]", false), String.valueOf(second));
	}
	
	public void selectChannelLongitudeDirection(String direction) {
		browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8005\\]", false), direction);
	}
	
	public void setChannelLongitudeDegree(int degree) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8006\\]", false), String.valueOf(degree));
	}
	
	public void setChannelLongitudeMinute(int minute) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8007\\]", false), String.valueOf(minute));
	}
	
	public void setChannelLongitudeSecond(int second) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8008\\]", false), String.valueOf(second));
	}
	
	public void setContractLength(int length) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8027\\]", false), String.valueOf(length));
	}
	
	public void setContractStartDate(int year) {
		browser.setTextField("", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[8028\\]", false), String.valueOf(year));
	}
	
	public void selectElectricityHookup(int hookup) {
		if(hookup == 0){// select the first one: "----"
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[218\\]", false), String.valueOf(hookup));
		}
	}
	
	public void selectFullHookup(int hookup) {
		if(hookup == 0){// select the first one: "----"
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[239\\]", false), 0);
		} else {
			browser.selectDropdownList(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[239\\]", false), String.valueOf(hookup));
		}
	}
	
	public void setMaximumNumberOfPeople(int maxNum) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false), String.valueOf(maxNum));
	}
	
	public void setMaximumNumberOfPeople(String maxNum) {
		browser.setTextField(".id", new RegularExpression("AttributeValuesWrapper-\\d+\\.attr\\[12\\]", false), maxNum);
	}
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("OK", false), true);
	}
	
	public boolean isSeasonDropdownListIsExisting(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.SELECT", ".id", 
				new RegularExpression(prefix + "seasonId",false));
	}
	
	public boolean isThreeSearchOptionsExist() {
		return browser.getRadioButton(".id", new RegularExpression(prefix + "seasonalSlipSearchFilter", false)).length >= 3;
	}
	
	public void selectSeason(String season){
		browser.selectDropdownList(".id", 
				new RegularExpression(prefix + "seasonId",false), season);
	}
	
	public void setBeginingDate(String date){
		browser.setCalendarField(".id", 
				new RegularExpression(prefix + "beginingDate_ForDisplay",false), date);
	}
	
	public void setEndingDate(String date){
		browser.setCalendarField(".id", 
				new RegularExpression(prefix + "endingDate_ForDisplay",false), date);
	}
	
	public void setNightsOfDateRange(String nights){
		browser.setTextField(".id", 
				new RegularExpression(prefix + "rangeUnits",false), nights,0,IText.Event.LOSEFOCUS);
	}
	
	public void selectSlipReservationType(String type) {
		if(isSlipReservationTypeEnabled()) {
			if(type.equals(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
				selectTransient();
			} else if(type.equals(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)) {
				selectLease();
			} else if(type.equals(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)) {
				selectSeasonal();
			} else throw new ItemNotFoundException("Unkown Slip Reservation Type - " + type);
			ajax.waitLoading();
		} else logger.debug("Don't need to select Slip Reservation Type.");
	}
	
	public String getSlipReservationType() {
		IHtmlObject objs[] = browser.getRadioButton(".id", new RegularExpression(prefix + "slipReservationType", false));
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find slip reservation type radio button object.");
		int index = -1;
		for(int i = 0; i < objs.length; i ++) {
			if(((IRadioButton)objs[i]).isSelected()) {
				index = i;
				break;
			}
		}
		
		Browser.unregister(objs);
		
		return index ==0 ? OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL : (index == 1 ? OrmsConstants.SLIP_RESERVATION_TYPE_LEASE : OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT);
	}
	
	private void selectorUnselectWeekendsOnly(boolean isSelect){
		RegularExpression reg = new RegularExpression(prefix + "weekendsOnly", false);
		if(isSelect){
			browser.selectCheckBox(".id", reg, true);
		} else {
			browser.unSelectCheckBox(".id", reg);
		}
	}
	
	public void selectWeekendsOnly(){
		this.selectorUnselectWeekendsOnly(true);
	}
	
	public void unselectWeekendsOnly(){
		this.selectorUnselectWeekendsOnly(false);
	}
	
	public void setupSlipSearchInfo(SlipInfo slip, boolean isAdvanced) {
		this.selectSlipReservationType(slip.getReservationType());
		
		//set Marina Name
		if(slip.getMarina() != null){//It may be need to set as empty, if(!StringUtil.isEmpty(slip.getMarina())) {
			if(isMarinaDropdownListExists()) {
				if(!getMarinaDropdownListSelectedValue().equalsIgnoreCase(slip.getMarina())) {
					selectMarina(slip.getMarina());
					ajax.waitLoading();
				}
			} else {
				setMarina(slip.getMarina());
			}
		}
		
		//Boat Dimensions
		if(NumberUtil.isGreaterThanZero(slip.getBoatLength())) {
			setBoatLength(slip.getBoatLength());
		} else if(NumberUtil.isEqualToZero(slip.getBoatLength())) {
			setBoatLength(StringUtil.EMPTY);
		}
		
		if(NumberUtil.isGreaterThanZero(slip.getBoatWidth())) {
			setBoatWidth(slip.getBoatWidth());
		}
		if(NumberUtil.isGreaterThanZero(slip.getBoatDepth())) {
			setBoatDepth(slip.getBoatDepth());
		}
		
		if(!StringUtil.isEmpty(slip.getCode())) {
			setSlipNumber(slip.getCode());
		}
		if(!StringUtil.isEmpty(slip.getParentDockArea())) {
			setDockArea(slip.getParentDockArea());
		}
		if(!StringUtil.isEmpty(slip.getType())) {
			selectSlipType(slip.getType());
		}
		
		if(slip.isShowReservableOnly()){
			if(isThreeSearchOptionsExist()){
				selectShowMode(0);
			}else{
				selectShowReservableOnly();
			}
		} else {
			if(isThreeSearchOptionsExist()){
				selectShowMode(2);
			}else{
				unselectShowReservableOnly();
			}
		}
		
		if(isAdvanced) {
			clickAdcanced();
			ajax.waitLoading();
			waitLoading();
			
			if(null != slip.getBeginingDate()){
				setBeginingDate(slip.getBeginingDate());
				ajax.waitLoading();
			}

			if(null != slip.getEndingDate()){
				setEndingDate(slip.getEndingDate());
				ajax.waitLoading();
			}
			
			if(null != slip.getNightsOfDateRange()){
				setNightsOfDateRange(slip.getNightsOfDateRange());
				ajax.waitLoading();
			}
			
			if(slip.isWeekendOnly()){
				selectWeekendsOnly();
			} else {
				unselectWeekendsOnly();
			}
			
			if(slip.isAccessibleSite()) {
				selectADAAccessible();
			} else {
				unSelectADAAccessible();
			}
			
			if(NumberUtil.isGreaterThanZero(slip.getBasePricingLength())) {
				setBasePricingLength(slip.getBasePricingLength());
			}
			if(!StringUtil.isEmpty(slip.getLatitudeChannelDirection())) {
				selectChannelLatitudeDirection(slip.getLatitudeChannelDirection());

				if(NumberUtil.isGreaterThanZero(slip.getLatitudeDegree())) {
					setChannelLatitudeDegree(slip.getLatitudeDegree());
				}

				if(NumberUtil.isGreaterThanZero(slip.getLatitudeMinute())) {
					setChannelLatitudeMinute(slip.getLatitudeMinute());
				}
				if(NumberUtil.isGreaterThanZero(slip.getLatitudeSecond())) {
					setChannelLatitudeSecond(slip.getLatitudeSecond());
				}
			}
			if(!StringUtil.isEmpty(slip.getLongitudeChannelDirection())) {
				selectChannelLongitudeDirection(slip.getLongitudeChannelDirection());

				if(NumberUtil.isGreaterThanZero(slip.getLongitudeDegree())) {
					setChannelLongitudeDegree(slip.getLongitudeDegree());
				}

				if(NumberUtil.isGreaterThanZero(slip.getLongitudeMinute())) {
					setChannelLongitudeMinute(slip.getLongitudeMinute());
				}
				if(NumberUtil.isGreaterThanZero(slip.getLongitudeSecond())) {
					setChannelLongitudeSecond(slip.getLongitudeSecond());
				}
			}
			if(NumberUtil.isGreaterThanZero(slip.getSeasonalContractLength())) {
				setContractLength(slip.getSeasonalContractLength());
			}
			if(NumberUtil.isGreaterThanZero(slip.getSeasonalContractStartDate())) {
				setContractStartDate(slip.getSeasonalContractStartDate());
			}
			if(NumberUtil.isGreaterThanZero(slip.getElectrictity())) {
				selectElectricityHookup(slip.getElectrictity());
			}
			if(NumberUtil.isGreaterThanZero(slip.getFull())) {
				selectFullHookup(slip.getFull());
			}
			if(NumberUtil.isGreaterThanZero(slip.getMaxNumOfPeople())) {
				setMaximumNumberOfPeople(slip.getMaxNumOfPeople());
			}
		}
		
		//Transient
		if(isArrivalDateTextFieldExists() && !isMonthsDropdownListExists()) {
			if(!StringUtil.isEmpty(slip.getTypeOfUse())) {
				selectTypeOfUse(slip.getTypeOfUse());
				ajax.waitLoading();
			}
			
			if(NumberUtil.isGreaterThanZero(slip.getNights())) {
				setNights(slip.getNights());
				ajax.waitLoading();
			}
			
			if(!StringUtil.isEmpty(slip.getArrivalDate())) {
				setArrivalDate(slip.getArrivalDate());
				ajax.waitLoading();
			}
			
			//Call and Operations Manager doesn't allow booking day use slip, so there is no Type Of Use available
			if(!getManagerName().matches("(Call|Operations) Manager") && !this.getTypeofUseSelectedValue().equalsIgnoreCase("Day")){
				if(!StringUtil.isEmpty(slip.getDepartureDate())) {
					setDepartureDate(slip.getDepartureDate());
					ajax.waitLoading();
				}
			}
		}
		
		//Lease
		if(isMonthsDropdownListExists()) {
			selectMonths(slip.getMonths());
			ajax.waitLoading();
			if(this.isArrivalDateTextFieldExists()){
				setArrivalDate(slip.getArrivalDate());
			}
			ajax.waitLoading();
		}
		
		//Seasonal
		if(isSeasonDropdownListIsExisting()){
			if(StringUtil.notEmpty(slip.getSeason())){
				selectSeason(slip.getSeason());
				ajax.waitLoading();
			}
		}
		
		//For 'Services' section
		if(slip.getServices() != null && slip.getServices().length > 0){
			this.selectServices(slip.getServices());
		}
	}
	
	public void setupSlipSearchInfo(SlipInfo slip) {
		setupSlipSearchInfo(slip, false);
	}
	
	public void advancedSearchSlip(SlipInfo slip){
		setupSlipSearchInfo(slip, true);
		clickOK();
		ajax.waitLoading();
	}
	
	public void searchSlip(SlipInfo slip) {
		setupSlipSearchInfo(slip);
		clickOK();
		ajax.waitLoading();
	}
	
	public void clickSiteSearchTab() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Site Search", true);
	}
	
	public String getSlipType(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression(prefix + "slipType",false));
	}
	
	public String getSlipNum(){
		IHtmlObject[] objs = browser.getTextField(".id", 
				new RegularExpression(prefix + "slipNumbers",false));
		String slipNum = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return slipNum;
	}
	
	public String getDockArea(){
		IHtmlObject[] objs = browser.getTextField(".id",
				new RegularExpression(prefix + "dockName",false));
		String dockArea = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return dockArea;
	}
	
	public boolean getReservableOnlyOption(){
		IHtmlObject[] objs = browser.getCheckBox(".id", 
				new RegularExpression(prefix + "showReservableOnly",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Reservable Only check box object.");
		}
		
		String value = objs[0].getProperty(".status");
		Browser.unregister(objs);
		return Boolean.parseBoolean(value);	
	}
	
	public String getArrivalDate(){
		IHtmlObject[] objs = browser.getTextField(".id", 
				new RegularExpression(prefix + "arrivalDate_ForDisplay",false));
		String arrivalDate = "";
		if(objs.length < 1) {
			objs =browser.getHtmlObject(".class", "Html.SPAN", ".id",new RegularExpression(prefix + "arrivalDate",false));
			arrivalDate = objs[0].getProperty(".text").split("Arrival")[1];
		} else {
			arrivalDate = ((IText)objs[0]).getText();
		}
		
		Browser.unregister(objs);
		return arrivalDate;
	}
	
	public String getDepartureDate(){
		IHtmlObject[] objs = browser.getTextField(".id", 
				new RegularExpression(prefix + "departureDate_ForDisplay",false));
		String departureDate = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return departureDate;
	}
	
	public String getNights(){
		IHtmlObject[] objs = browser.getTextField(".id", 
				new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.units",false));
		String departureDate = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return departureDate;
	}
	
	public String getTransferDate(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.arrivalDate", false)).replace("Transfer Date", "");
	}
	
	public String getStayNights(){
		return browser.getObjectText(".class","Html.SPAN",".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.units", false)).replace("Nights", "");
	}
	public String getDepartureDateForLease(){
		IHtmlObject[] objs = browser.getHtmlObject(".id", 
				new RegularExpression(prefix + "departureDate",false));
		String departureDate = objs[0].text().replace("Departure", "");
		Browser.unregister(objs);
		return departureDate;
	}
	
	public String getTypeofUseSelectedValue(){
		return browser.getDropdownListValue(".id", 
				new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.unitOfStay",false));                           
	}
	
	public String getMessage(){
		String errMsg = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".className", new RegularExpression("message msgerror|msgsuccess",false));
		for(int i=0; i<objs.length; i++){
			if(i!=0){
				errMsg += " ";
			}
			errMsg += objs[i].text().trim();
		}
		Browser.unregister(objs);
		return errMsg;
	}
	
	public boolean getWeekendsOnlyOption(){
		IHtmlObject[] objs = browser.getCheckBox(".id", 
				new RegularExpression(prefix + "weekendsOnly",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Weekends Only check box object.");
		}
		
		String value = objs[0].getProperty(".status");
		Browser.unregister(objs);
		return Boolean.parseBoolean(value);	
	}
	
	public List<String> getAttributeInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^Slip Attributes",false));
		
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Slip Attribute TR object");
		}
		
		IHtmlObject[] spanObject = browser.getTableTestObject(Property.toPropertyArray(".class", "Html.SPAN"), objs[0]);
		if(spanObject.length<1){
			throw new ItemNotFoundException("Did not get table object at Slip Attribute");
		}
		List<String> attributesList = new ArrayList<String>();
		for(int i=0; i<spanObject.length; i++){
			attributesList.add(spanObject[i].text().replaceAll(":", "").trim());
		}
		Browser.unregister(objs);
		Browser.unregister(spanObject);
		return attributesList;
	}
	
	public boolean checkServiceCheckBoxIsExistingByName(String name){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression(name,false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get service check box object by name = " + name);
		}
		
		IHtmlObject[] checkBoxObjs = browser.getHtmlObject(".class", "Html.INPUT.checkbox", objs[0]);
		boolean isExisting = true;
		if(checkBoxObjs.length<1){
			isExisting = false;
		}else isExisting = true;
		
		Browser.unregister(objs);
		Browser.unregister(checkBoxObjs);
		return isExisting;
	}
	
	public boolean isSlipResTypeSelectedMM(String type){
		return this.isSlipResTypeSelected(type, true);
	}
	
	public boolean isSlipResTypeSelectedCM(){
		return this.isSlipResTypeSelected(null, false);
	}

	/**
	 * 
	 * @param type -- Seasonal, Lease, Transient
	 * @return
	 */
	private boolean isSlipResTypeSelected(String type, boolean isMM){
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.slipReservationType",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Slip Reservation Type object... ");
		}
		
		int index = 0;
		if(isMM){
			if(type.equalsIgnoreCase("Seasonal")){
				index = 1;
			} else if(type.equalsIgnoreCase("Lease")){
				index = 2;
			} else if(type.equalsIgnoreCase("Transient")){
				index = 3;
			}
		} else {
			type = OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT; // Call Manager only has Transient
			index = 3;
		}
		
		if(index == 0){
			throw new ItemNotFoundException("Type " +type+" doesn't exist on the page");
		} else {
			String value=objs[index-1].getProperty("defaultChecked");
			value = StringUtil.notEmpty(value)?value:"false";
			return Boolean.valueOf(value);
		}
	}
	
	public boolean isLeaseSelected(){
		return isResTypeSelected(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE);
	}
	
	public boolean isTransientSelected(){
//		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.slipReservationType",false));
//		if(objs.length<1){
//			throw new ItemNotFoundException("Did not get Slip Reservation Type object... ");
//		}
//		String value=objs[objs.length-1].getProperty("defaultChecked");
//		return Boolean.valueOf(value);
		return isResTypeSelected(OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT);
	}
	
	public boolean isResTypeSelected(String reservationType){
		int index = -1;
		if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)){
			index = 0 ;
		}else if(reservationType.equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)){
			if(isResTypeExist(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL))
				index = 0;
			else
				index = 1;
		}else{
			if(isResTypeExist(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)&&isResTypeExist(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE))
				index = 2;
			else if(isResTypeExist(OrmsConstants.SLIP_RESERVATION_TYPE_SEASONAL)||isResTypeExist(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE))
				index = 1;
				else
					index = 0;
		}
		IHtmlObject[] objs = browser.getHtmlObject(".id",new RegularExpression("SlipAvailabilitySearchCriteria-\\d+.slipReservationType",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get Slip Reservation Type object... ");
		}
		String value=objs[index].getProperty("defaultChecked");
		return Boolean.valueOf(value);
	}
		 	
	public boolean isResTypeExist(String reservationType){
		return browser.checkHtmlObjectDisplayed(".class", "Html.LABEL", ".text", reservationType);
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

	public List<String> getTypeOfUseListValue(){
		return browser.getDropdownElements(".id", 
				new RegularExpression(prefix + "unitOfStay",false));
	}
	
	public List<String> getMarinaNameList(){
		return browser.getDropdownElements(".id", 
				new RegularExpression(prefix + "marinaID",false));
	}
	
	public List<String> getSeasonList(){
		return browser.getDropdownElements(".id", 
				new RegularExpression(prefix + "seasonId",false));
	}
	
	public String getBoatLength(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix + "boatLength", false));
	}
	
	public String getBoatWidth(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix + "boatWidth", false));
	}

	public String getBoatDepth(){
		return browser.getTextFieldValue(".id", new RegularExpression(prefix + "boatDepth", false));
	}
	
	public void verifyMarinaInfo(String marinaName, String typeOfUse){
		logger.info("Verify 'Marina' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Marina Name", marinaName, getMarinaDropdownListSelectedValue());
		if(StringUtil.notEmpty(typeOfUse)){
			result &= MiscFunctions.compareResult("Type of Use", typeOfUse, getTypeofUseSelectedValue());		
		}
		if(!result) {
			throw new ErrorOnPageException("'Marina' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Marina' section info is correct.");
	}
		
	public void verifyDateSpecificInfo(SlipInfo slip){
		logger.info("Verify 'Date Specific' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Arrival Date", slip.getArrivalDate(), getArrivalDate());
		if(slip.getReservationType().equalsIgnoreCase(OrmsConstants.SLIP_RESERVATION_TYPE_LEASE)){
			result &= MiscFunctions.compareResult("Depart Date", slip.getDepartureDate(), getDepartureDateForLease());		
			result &= MiscFunctions.compareResult("Months", String.valueOf(slip.getMonths()), getMonths());	
		}else{
			result &= MiscFunctions.compareResult("Depart Date", slip.getDepartureDate(), getDepartureDate());		
			result &= MiscFunctions.compareResult("Nights", slip.getNights(), Integer.parseInt(getNights()));	
		}
		if(!result) {
			throw new ErrorOnPageException("'Date Specific' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Date Specific' section info is correct.");
	}
	
	
	
	public void verifyBoatDimensionsInfo(String length, String width, String depth){
		logger.info("Verify 'Boat Dimensions' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult("Boat Length", Double.parseDouble(length), Double.parseDouble(getBoatLength()));
		result &= MiscFunctions.compareResult("Boat Width", Double.parseDouble(width), Double.parseDouble(getBoatWidth()));		
		result &= MiscFunctions.compareResult("Boat Depth", Double.parseDouble(depth), Double.parseDouble(getBoatDepth()));		
		if(!result) {
			throw new ErrorOnPageException("'Boat Dimensions' section info is NOT correct, please refer o log for details.");
		} else logger.info("'Boat Dimensions' section info is correct.");
	}
	
	public void moveFocus(){
		browser.clickGuiObject(".class", "Html.TD", ".text", "Slip Reservation Type" );
	}
	
	public void selectService(String service){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".text", new RegularExpression("^" + service +"$", false));
		if(objs.length < 1){
			throw new ErrorOnPageException("There is no such service:" + service);
		}
		browser.selectCheckBox(".id", new RegularExpression(prefix + "services_\\d+", false), 0, objs[0]);
	}
	
	public void selectServices(String... services){
		for(String ser:services){
			this.selectService(ser);
		}
	}
	
	public boolean isLabelInSlipSearchExist(String labelname){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TD", ".text", labelname);
	}
	
	public String[] getSlipReservationtoRenewSectionSlipInfo(){
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression( "Slip Reservation #.*Marina.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find Slip Reservation to Renew section table...");
		}
		
		IHtmlTable table=(IHtmlTable)objs[objs.length-1];
		String[] renewSlip=new String[7];
		renewSlip[0]=table.getCellValue(0, 1).split("Slip Reservation #")[1].trim();//Slip Reservation #
		renewSlip[1]=table.getCellValue(0, 3).split("Marina")[1].trim();//Marina
		renewSlip[2]=table.getCellValue(0, 5).split("Dock/Area")[1].trim();//Dock/Area
		renewSlip[3]=table.getCellValue(0, 7).split("Slip # \\(Name\\)")[1].trim();//Slip # (Name) 
		renewSlip[4]=table.getCellValue(0, 9).split("Arrival")[1].trim();//Arrival
		renewSlip[5]=table.getCellValue(0, 11).split("Departure")[1].trim();//Departure
		renewSlip[6]=table.getCellValue(0, 13).split("Months")[1].trim();//Months
		
		Browser.unregister(objs);
		return renewSlip;
		
	}
	
	public void setBoatDimension(String length, String width, String depth){
		//Boat Dimensions
		if(StringUtil.notEmpty(length)){
			setBoatLength(length);
		}
		if(StringUtil.notEmpty(width)){
			setBoatWidth(width);
		}
		if(StringUtil.notEmpty(depth)){
			setBoatDepth(depth);
		}
	}
		
	/**
	 * Get the attribute which use check box 
	 * If all the check box is unchecked it will return true, any of the check box is checked will return false
	 * @return
	 */
	public boolean isAttributeCheckBoxUnselect(){
		boolean unChecked = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression("^Slip Attributes", false));
		IHtmlObject[] checkBoxObjs = browser.getCheckBox(Property.toPropertyArray(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr.*", false)), objs[0]);
		for(int i=0; i<checkBoxObjs.length; i++){
			unChecked  &= !((ICheckBox)checkBoxObjs[i]).isSelected();
		}
		return unChecked;
	}
	
	/**
	 * Verify the attribute witch use drop down list
	 * If all the value is equal to expect value it will return true, any of the value is not equal to the expect value will return false
	 * @param expect
	 * @return
	 */
	public boolean isAttributeDropDownListValueIsExpect(String expect){
		boolean isExpect = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression("^Slip Attributes", false));
		IHtmlObject[] dropDownListObjs = browser.getDropdownList(Property.toPropertyArray(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr.*", false)), objs[0]);
		for(int i=0; i<dropDownListObjs.length; i++){
			String selectText = ((ISelect)dropDownListObjs[i]).getSelectedText() ;
			if(!selectText.equalsIgnoreCase(expect)){
				isExpect = false;
			}
		}
		return isExpect;
	}
	
	/**
	 * Verify the attribute witch use text field
	 * If all the value is equal to expect value it will return true, any of the value is not equal to the expect value will return false
	 * @param expect
	 * @return
	 */
	public boolean isAttributeTextFieldValueIsExpect(String expect){
		boolean isExpect = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",".text", new RegularExpression("^Slip Attributes", false));
		IHtmlObject[] textFieldObjs = browser.getTextField(Property.toPropertyArray(".id",new RegularExpression("AttributeValuesWrapper-\\d+\\.attr.*", false)), objs[0]);
		for(int i=0; i<textFieldObjs.length; i++){
			String text = ((IText)textFieldObjs[i]).getText() ;
			if(!text.equalsIgnoreCase(expect)){
				isExpect = false;
			}
		}
		return isExpect;
	}
	
	public List<String> dateSpecificAvailableValues(){
		List<String> values = new ArrayList<String>();
		IHtmlObject objs[] = browser.getHtmlObject(Property.concatPropertyArray(this.label(), ".for", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonRange\\d",false)));
		for(int i=0; i<objs.length; i++){
			values.add(objs[i].text());
		}
		Browser.unregister(objs);
		return values;
	}
	
	public void verifyValueForDateSpecific(String expectValue){
		boolean passed = true;
		if(expectValue.equalsIgnoreCase("This Year")&&!this.isThisYearSelected()){
			passed = false;
		}
		if(expectValue.equalsIgnoreCase("Next Year")&&!this.isNextYearSelected()){
			passed = false;
		}
		if(!passed){
			throw new ErrorOnPageException("The expect selected year:" + expectValue + " is not selected");
		}
	}
	
	protected Property[] thisYearRadioBtn() {
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonRange0",false));
	}
	
	protected Property[] nextYearRadioBtn() {
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonRange1",false));
	}
	
	private boolean isThisYearSelected(){
		return browser.isRadioButtonSelected(this.thisYearRadioBtn());
	}
	
	private boolean isNextYearSelected(){
		return browser.isRadioButtonSelected(this.nextYearRadioBtn());
	}
	
	public void setAsBasicSearch(){
		this.clickBasic();
		ajax.waitLoading();
		this.waitLoading();
	}
	
	public boolean isSlipAttributesSectionsShow(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TR", ".text", new RegularExpression("^Slip Attributes.*", false));
	}

	protected Property[] showReservableOnlyCheckBox() {
		return Property.concatPropertyArray(this.input("CHECKBOX"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.showReservableOnly",false));
	}
	
	protected Property[] showReservableOnlyRadioButton(){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonalSlipSearchFilter0",false));
	}
	
	protected Property[] showLotteryOnlyRadioButton(){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonalSlipSearchFilter1",false));
	}
	
	protected Property[] showAllRadioButton(){
		return Property.concatPropertyArray(this.input("RADIO"), ".id", new RegularExpression("SlipAvailabilitySearchCriteria-\\d+\\.seasonalSlipSearchFilter2",false));
	}
	
	public boolean isShowReservableOnlyCheckBoxExist(){
		return browser.checkHtmlObjectExists(this.showReservableOnlyRadioButton());
	}
	
	public List<String> getAvailableSearchOptions(){
		String sectionName = "Search Options";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^" + sectionName + ".*", false));
		String[] options = objs[0].text().replaceAll(" ", "").replace("SearchOptions", "").replace("Advanced>>","").split("Show");
		List<String> avaOpt = new ArrayList<String>();
		for(int i=0; i<options.length; i++){
			if(StringUtil.notEmpty(options[i])){
				avaOpt.add(options[i]);
			}
		}
		Browser.unregister(objs);
		return avaOpt;
	}
	
	public void verifySearchOptions(boolean ongoingLotteryExist){
		boolean passed = true;
		List<String> actOpt = this.getAvailableSearchOptions();
		if(ongoingLotteryExist){
			passed &= MiscFunctions.compareResult("There are three options", true,  isThreeSearchOptionsExist());
			passed &= MiscFunctions.compareResult("First option", "ReservableOnly",  actOpt.get(0));
			passed &= MiscFunctions.compareResult("Second option", "LotteryOnly",  actOpt.get(1));
			passed &= MiscFunctions.compareResult("Third option", "All",  actOpt.get(2));
				
		}else{
			passed &= MiscFunctions.compareResult("There are only options", 1,  actOpt.size());
			passed &= MiscFunctions.compareResult("It is check box", true,  isShowReservableOnlyCheckBoxExist());
			passed &= MiscFunctions.compareResult("It is check box", "ReservableOnly",   actOpt.get(0));
		}
		if(!passed){
			throw new ErrorOnPageException("The ui for search Options is correct!");
		}
	}
	
	
}
