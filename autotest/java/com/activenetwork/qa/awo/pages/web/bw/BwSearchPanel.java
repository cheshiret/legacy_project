package com.activenetwork.qa.awo.pages.web.bw;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ILink;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author QA
 */
public class BwSearchPanel extends UwpPage {
	private int permitTypeCursor = 0;
	private static BwSearchPanel _instance = null;
	private final String[] permitTypes={"Day Use Motor","Day Use Motor to Canada","Overnight Hiking","Overnight Motor","Overnight Paddle"};

	public static BwSearchPanel getInstance() {
		if (null == _instance)
			_instance = new BwSearchPanel();

		return _instance;
	}

	/** Elements Properties Define Begin */
	protected Property[] getPermitTypeSelectListProp() {
		return Property.toPropertyArray(".class", "Html.Select", ".id", "permitTypeId");
	}
	
	protected Property[] getPermitTypeDescDivProp() {
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "permitTypeDescId");
	}
	
	protected Property[] getZoneSelectListProp() {
		return Property.toPropertyArray(".id", "zone");
	}
	
	protected Property[] getZoneLabelProp() {
		return Property.toPropertyArray(".id", "zone_label");
	}

	protected Property[] getEntranceTypeSelectListProp() {
		return Property.toPropertyArray(".id", "entranceType");
	}
	
	protected Property[] getEntranceTypeLabelProp() {
		return Property.toPropertyArray(".id", "entranceType_label");
	}

	protected Property[] getEntranceSelectListProp() {
		return Property.toPropertyArray(".id", "entrance");
	}
	
	protected Property[] getEntranceLabelProp() {
		return Property.toPropertyArray(".id", "entrance_label");
	}

	protected Property[] getEntranceStartDateProp() {
		return Property.toPropertyArray(".id", "entryStartDate");
	}
	
	protected Property[] getEntranceStartDateLabelProp() {
		return Property.toPropertyArray(".id", "lblForArrDate");
	}

	protected Property[] getGroupSizeDivShownProp() {
		return Property.toPropertyArray(".id", "groupSizeDivId", ".className", "hiddenoptions");
	}
	
	protected Property[] getGroupSizeFieldProp() {
		return Property.toPropertyArray(".id", "groupSize");
	}
	
	protected Property[] getGroupSizeLabelProp() {
		return Property.concatPropertyArray(label(), ".for", "groupSize");
	}
	
	protected Property[] getLengthOfStayDivShownProp() {
		return Property.toPropertyArray(".id", "lengthOfStayDivId", ".className", "hiddenoptions");
	}
	
	protected Property[] getLengthOfStayFieldProp() {
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "lengthOfStay");
	}
	
	protected Property[] getLengthOfStayLabelProp() {
		return Property.toPropertyArray(".class", "Html.Label", ".for", "lengthOfStay");
	}
	
	protected Property[] groupSizeDiv(){
		return Property.concatPropertyArray(div(), ".className", "hiddenoptions", "id","groupSizeDivId");
	}
	/** Elements Properties Define End */
	
	public boolean exists() {
		boolean isExisting;
		try{
//			isExisting=browser.checkHtmlObjectExists(".class", "Html.BUTTON", ".id","permitAvailabilitySearchButton")
			// change due to the page is changed when there is a permit type for Single Trip Itinerary
			isExisting=browser.checkHtmlObjectExists(getPermitTypeSelectListProp());
					
		}catch(NullPointerException e){
			isExisting= false;
		}
		return isExisting;
	}

	/**
	 * Select permit type by give type.
	 * @param type: permit type
	 * @param chooseAnyOfPermitType: true, if no specific permit type, but need one 
	 *                               false, if no specific one, also doesn't need one
	 */
	public void selectPermitType(String type, boolean chooseAnyOfPermitType) {
		if ((type == null || type.length() < 1) && chooseAnyOfPermitType) {
			type=permitTypes[permitTypeCursor];
			permitTypeCursor++;
		} 
		browser.selectDropdownList(getPermitTypeSelectListProp(), type, false);
		waitLoading(); // wait for dropdown list refresh
	}
	
	public void selectPermitType(String type) {
		if ((type == null || type.length() < 1)) {
			type=permitTypes[permitTypeCursor];
			permitTypeCursor++;
		} 
		browser.selectDropdownList(getPermitTypeSelectListProp(), type, false);
		waitLoading(); // wait for dropdown list refresh
	}

	/**
	 * Select permit type by index of type items.
	 * 
	 * @param index
	 *            - index of type
	 */
	public void selectPermitType(int index) {
		browser.selectDropdownList(getPermitTypeSelectListProp(), index, false);
	}

	/**
	 * Select entrance by entrance item index.
	 * 
	 * @param index
	 *            - index of entrance item
	 */
	public void selectEntrance(int index) {
		browser.selectDropdownList(getEntranceSelectListProp(), index, false);
	}

	/**
	 * Select entrance by given entrance name.
	 * 
	 * @param entrance
	 *            - entrance name
	 */
	public void selectEntrance(String entrance) {
		if (entrance != null && entrance.length() > 0) {
			browser.selectDropdownList(getEntranceSelectListProp(), entrance, false);
		}
		this.waitLoading();
	}

	/**
	 * Select district group by given set of index.
	 * 
	 * @param index
	 *            - a set index, Integer array
	 * @throws ItemNotFoundException
	 */
	public void selectDistrict(int[] index) throws ItemNotFoundException {
		RegularExpression value = new RegularExpression("dis-100.*", false);

		IHtmlObject[] obj = browser.getHtmlObject(".class",
				"Html.INPUT.checkbox", ".id", value);
		if (obj.length > 0) {
			if (index.length > obj.length)
				throw new ItemNotFoundException(
						"index is bigger than the number of check boxes");
			for (int i = 0; i < index.length; i++) {
				ICheckBox check = (ICheckBox) obj[index[i] - 1];
				String attr=check.getProperty(".checked");
				if (attr==null || !attr.equalsIgnoreCase("true"))
					check.click();
			}
		}
		Browser.unregister(obj);
	}

	/**
	 * Select set of district by given name array.
	 * 
	 * @param district
	 *            - a set name, String array
	 * @throws ItemNotFoundException
	 */
	public void selectDistrict(String[] district) throws ItemNotFoundException {
		if (district == null || district.length < 1) {
			int[] index = { 1, 2, 3, 4, 5 };
			selectDistrict(index);
			return;
		}

		int[] index = new int[district.length];

		for (int i = 0; i < district.length; i++) {
			if (district[i].equalsIgnoreCase("Gunflint District"))
				index[i] = 1;
			else if (district[i].equalsIgnoreCase("Kawishiwi District"))
				index[i] = 2;
			else if (district[i].equalsIgnoreCase("LaCroix District"))
				index[i] = 3;
			else if (district[i].equalsIgnoreCase("Laurentian District"))
				index[i] = 4;
			else if (district[i].equalsIgnoreCase("Tofte District"))
				index[i] = 5;
		}

		selectDistrict(index);
	}

	/**
	 * Check specific radio button and fill in entry date.
	 * 
	 * @param entryDate
	 *            - entry date
	 */
	public void setSpecificDate(String entryDate) {
		browser.selectRadioButton(".id", "rangeno");
		browser.setTextField(getEntranceStartDateProp(), entryDate, true, 0);
	}

	/**
	 * Fill in start and end date for range search.
	 * 
	 * @param startDate
	 *            - start date
	 * @param endDate
	 *            - end date
	 */
	
	public void setRangeDate(String startDate, boolean setAnyOfStartDate, String endDate, boolean setAnyOfSEndDate) {
		browser.selectRadioButton(".id", "rangeyes");

		if ((startDate == null || startDate.length()<1) &&setAnyOfStartDate) {
			startDate = DateFunctions.getDateStamp(false);
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		} else if ((endDate == null || endDate.length()<1) && setAnyOfSEndDate) {
			startDate = startDate.replaceAll("-", "/");
			endDate = DateFunctions.getDateAfterGivenDay(startDate, 14);
		}

		browser.setTextField(getEntranceStartDateProp(), startDate, false, 0);
		browser.setTextField(".id", "entryEndDate", endDate);
	}

	/**
	 * Fill in number of permits.
	 * 
	 * @param num
	 *            - number of permits
	 */
	public void setNumOfPermits(String num) {
		if (num == null || num.length() < 1)
			num = "1";

		browser.setTextField(".id", "numberOfPermits", num);
	}

	/** Click on button to do permit search */
	public void clickSearch() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id",
				"permitAvailabilitySearchButton");
	}

	protected void resetPermitTypeCursor() {
		permitTypeCursor=0;
	}
	
	/**
	 * Fill in all permit search criteria data.
	 * 
	 * @param permit
	 *            - permit info data
	 * @throws ItemNotFoundException
	 */
	public void setupSearchData(PermitInfo permit) throws ItemNotFoundException {
		resetPermitTypeCursor();
		this.selectPermitType(permit.permitType, permit.chooseAnyOfPermitType);
		
		if(!StringUtil.isEmpty(permit.entranceTyp)){
			this.selectEntranceType(permit.entranceTyp);
			browser.waitExists(LONG_SLEEP);
			browser.waitExists(Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^"+permit.entranceTyp+".*",false)));
		}
		Browser.sleep(2);
		this.selectEntrance(permit.entrance);
		if (permit.isDistrict) {
			this.selectDistrict(permit.district);
		}
		if (permit.isRange && !permit.issue) {
			this.setRangeDate(permit.startDate, permit.setAnyOfStartDate, permit.endDate, permit.setAnyOfEndDate);
		} else if (!permit.issue) {
			if(!StringUtil.isEmpty(permit.entranceTyp)&&this.isExitTrailSelected())
				this.setSpecificDate(permit.exitDate);
			else
				this.setSpecificDate(permit.entryDate);
		} else {
			permit.entryDate = DateFunctions.getToday();
			this.setSpecificDate(permit.entryDate);
		}

		this.setNumOfPermits(permit.personNum);
		if(isGroupSizeDisplayed()) {
			setGroupSize(permit.groupSize, permit.setAnyOfGroupSize);
		}
		
		this.setLengthOfStay(permit.numOfDays);
	}
	
	/**
	 * @param entranceTyp 
	 * 
	 */
	private void selectEntranceType(String entranceTyp) {
		if(entranceTyp.matches("(?i)Entry (Trail|Point)")){
			browser.selectRadioButton(".name", "trail", 0);
		}else{
			browser.selectRadioButton(".name", "trail", 1);
		}
	}
	
	public boolean isEntryTrailSelected(){
		IHtmlObject[] objs=browser.getRadioButton(".name", "trail",".id","existno");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find entry tral radio btn");
		}
		boolean flag=((IRadioButton)objs[0]).isSelected();
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean isExitTrailSelected(){
		IHtmlObject[] objs=browser.getRadioButton(".name", "trail",".id","existyes");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find entry tral radio btn");
		}
		boolean flag=((IRadioButton)objs[0]).isSelected();
		Browser.unregister(objs);
		return flag;
	}
	
	public boolean isGroupSizeDisplayed() {
		return browser.checkHtmlObjectExists(groupSizeDiv());
	}
	
	public String getGroupSizeLabel(){
		return browser.getObjectText(Property.atList(groupSizeDiv(), getGroupSizeLabelProp()));
	}
	
	public void verifyGroupSizeLabel(String groupSizeLabelRegx){
		String valueFromUI = getGroupSizeFieldLabel();
		if(valueFromUI.matches(groupSizeLabelRegx)){
			logger.info("Successfully verify group size label:"+valueFromUI);
		}else throw new ErrorOnPageException("Failed to verify group size label", groupSizeLabelRegx, valueFromUI);
	}
	
	/**
	 * Set group size
	 * @param size
	 * @param setAnyOfGroupSize true: set size=1 if no size is specific
	 *                          false: don't set group size if no none is specific 
	 */
	public void setGroupSize(String size, boolean setAnyOfGroupSize) {
		if((size==null || size.length()<1) && setAnyOfGroupSize)
			size="1";
		
		browser.setTextField(getGroupSizeFieldProp(), size, false, 0);
	}

	/**
	 * Set Length Of Stay value. The text field is shown only if the allocation method of the permit type is per stay or per unit/per stay.
	 * @param length
	 * @author Lesley Wang
	 * Mar 15, 2013
	 */
	public void setLengthOfStay(String length) {
		browser.setTextField(Property.atList(Property.toPropertyArray(".className", "hiddenoptions", ".id", "lengthOfStayDivId"), 
				getLengthOfStayFieldProp()), length);
	}
	
	/**
	 * Get inventory object if exists.
	 * @return
	 */
	public IHtmlObject[] getInventory() {
//		HtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text","A");
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("^A$|A [0-9]+", false));
		if (obj.length > 0)
			return obj;
		
		return null;
	}

	/**
	 * Wait for page to load after range searching.
	 * 
	 * @throws ItemNotFoundException
	 */
	public void rangeSearchWaitExists() throws ItemNotFoundException {
//		browser.searchObjectWaitExists(".class", "Html.DIV", ".id","permitGridContainer");
		browser.searchObjectWaitExists(".class", "Html.DIV", ".id","subnav");
	}
	
	/**
	 * Wait for page to load after specific searching.
	 * 
	 * @throws ItemNotFoundException
	 */
	public void specificSearchWaitExists() throws ItemNotFoundException {
		browser.searchObjectWaitExists(".class", "Html.TABLE", ".id",
				"shoppingitems");
	}
	
	public boolean isForItineraryPermit(){
		return browser.checkHtmlObjectExists(".id", "permitGridContainer");
	}
	
	/** Click on next 2 Week link */
	public void clickNext2Weeks() {
		browser.clickGuiObject(".class", "Html.A", ".id", "nextWeek");
	}
	
	/** Click on next 2 Week link */
	public void clickDateRangeAvailablity() {
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".id", "permitCalendar" /*, ".text",new RegularExpression( "Date Range\\W+Availablity",false)*/);
		browser.clickGuiObject(p);
	}

	/**
	 * Do a range search and go to permit details during a period.
	 * 
	 * @param maxLoop
	 *            - duration
	 * @throws ItemNotFoundException
	 */
	public void searchRangePermit(int maxLoop) throws ItemNotFoundException {
		int nextCount = 0;
		this.clickSearch();
		this.rangeSearchWaitExists();
		IHtmlObject[] obj = getInventory();

		while (obj == null) {
			this.clickNext2Weeks();
			nextCount++;
			if (nextCount > maxLoop && permitTypeCursor<permitTypes.length) {
				selectPermitType(permitTypes[permitTypeCursor]);
				permitTypeCursor++;
				nextCount = 0;
				clickSearch();
			} else if(permitTypeCursor>=permitTypes.length){
				throw new ItemNotFoundException("There is no permit inventory available in "+ (maxLoop * 14) + " days");
			}
			rangeSearchWaitExists();
			obj = getInventory();
		}

		((ILink) obj[0]).click();
		Browser.unregister(obj);
	}

	/**
	 * Retrieve entrance object if available.
	 * 
	 * @return
	 */
	public IHtmlObject[] getAvailableEntrance() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"See Details");
		if (objs.length > 0)
			return objs;

		Browser.unregister(objs);
		return null;
	}

	/**
	 * Specific search permit.
	 * 
	 * @throws ItemNotFoundException
	 */
	public void searchSpecificPermit() throws ItemNotFoundException {
		this.clickSearch();
		this.waitLoading();
		if(!isForItineraryPermit()){
			this.specificSearchWaitExists();
			IHtmlObject[] obj = getAvailableEntrance();

			while (obj == null) {
				try {
					this.selectPermitType(this.permitTypeCursor);
				} catch (Exception e) {
					throw new ItemNotFoundException(
							"There is no permit inventory available on the specific date.");
				}
				permitTypeCursor++;
				this.clickSearch();
				this.specificSearchWaitExists();
				obj = getAvailableEntrance();
			}

			((ILink) obj[0]).click();
			Browser.unregister(obj);
		}else logger.info("Add entrance to itinerary function not in this search permits method."); //Sara, 7/23/2013 previous this method only for normal permit, not itinerary permit, so will click available inventory.
	}

	/**
	 * do specific or range search for permit.
	 * 
	 * @param bd
	 *            - permit info data
	 */
	public void searchPermit(PermitInfo bd) {
		this.setupSearchData(bd);
		if (bd.isRange && !bd.issue) {
			searchRangePermit(bd.maxLoop);
		} else {
			searchSpecificPermit();
		}
	}

	/** Click on My Reservations & Account link */
	public void clickMyReservationAccount() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"My Reservations & Account");
	}

	/**
	 * Retrieve lottery inventory object if exists.
	 * 
	 * @return
	 */
	public IHtmlObject[] getLotteryInventory() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text","L");
		if (obj.length > 0)
			return obj;

		return null;
	}

	/**
	 * Search lottery during a period.
	 * 
	 * @param maxLoop
	 *            - duration
	 * @throws ItemNotFoundException
	 */
	public void searchLottery(int maxLoop, boolean isRange) throws ItemNotFoundException {
		int nextCount = 0;
		this.clickSearch();
		
		IHtmlObject[] obj = null;
		if(isRange) {
			this.rangeSearchWaitExists();
			obj = getLotteryInventory();

			while (obj == null) {
				this.clickNext2Weeks();
				nextCount++;
				if (nextCount > maxLoop) {
					try {
						this.selectPermitType(this.permitTypeCursor);
					} catch (Exception e) {
						throw new ItemNotFoundException(
								"There is no Lottery inventory available.");
					}
					permitTypeCursor++;
					nextCount = 0;
					this.clickSearch();
				}
				this.rangeSearchWaitExists();
				obj = getLotteryInventory();
			}
		} else {
			this.specificSearchWaitExists();
			obj = getAvailableEntrance();
		}
		
		((ILink) obj[0]).click();
		Browser.unregister(obj);
	}
	
	/**
	 * Get permit types from Looking for drop down list
	 * @return
	 */
	public List<String> getPermitTypes(){
		return browser.getDropdownElements(getPermitTypeSelectListProp());
	}
	
	/**
	 * Verify Permit Types
	 * @param expectedpermitTypes
	 */
	public void verifyPermitTypes(List<String> expectedpermitTypes){
		List<String> permitTypes = this.getPermitTypes();
		if(!permitTypes.get(0).equals("Select Permit Type")){
			throw new ErrorOnDataException("The first item of Looking for drop down list should be 'Selected Permit Type'");
		}
		
		permitTypes.remove(0);
		if (!permitTypes.equals(expectedpermitTypes)) {
			throw new ErrorOnDataException("Permit types in the list are wrong!", expectedpermitTypes.toString(), permitTypes.toString());
		}
		logger.info("---Successfully verify permit types!");
	}
	
	/**
	 * Get Looking for drop down value
	 * @return
	 */
	public String getPermitType(){
		return browser.getDropdownListValue(getPermitTypeSelectListProp(), 0);
	}
	
	/**
	 * Verify permit type
	 * @param permitType
	 */
	public void verifyPermitType(String permitType){
		if(!this.getPermitType().equals(permitType)){
			throw new ErrorOnDataException("The expect permit type should be "+permitType);
		}
	}
	
	/**
	 * Get permit type description
	 * @return
	 */
	public String getPermitTypeDiscription(){
		IHtmlObject[] objs = browser.getHtmlObject(getPermitTypeDescDivProp());
		String permitTypeDiscription = objs[0].text();
		
		Browser.unregister(objs);
		return permitTypeDiscription;
	}
	
	/**
	 * Get permit types description
	 * @return
	 */
	public String[] getAllPermitTypesDescription(){
		List<String> permitTypes = this.getPermitTypes();
		String[]permitTypesDiscription = new String[permitTypes.size()-1];
		for(int i=1; i<permitTypes.size(); i++){
			this.selectPermitType(permitTypes.get(i));
			permitTypesDiscription[i-1] = this.getPermitTypeDiscription();
		}
		return permitTypesDiscription;
	}
	
	public String getEntrance(){
		return browser.getDropdownListValue(getEntranceSelectListProp(), 0);
	}
	
	public void verifyEntrance(String expectedEntrance){
		logger.info("Verify entrance value.");
		String actualValue = this.getEntrance();
		if(!actualValue.equals(expectedEntrance)){
			throw new ErrorOnDataException("The expected Entrance should be "+expectedEntrance+", but the actual value is "+actualValue);
		}
	}
	
	public List<String> getAllEntrance(){
		return browser.getDropdownElements(getEntranceSelectListProp());
	}
	
	public void verifyAllEntrance(List<String> expectedEntrance){
		List<String> nORepeatableEntrance = new ArrayList<String>();
		List<String> entranceInPanel = this.getAllEntrance();
		if(!entranceInPanel.get(0).equals("Any Entrance")&&
				!entranceInPanel.get(0).equals("Any Trail") &&
				!entranceInPanel.get(0).equals("Any Destination Zone") &&
				!entranceInPanel.get(0).equals("Any Permit Zone") &&
				!entranceInPanel.get(0).equals("Any River")){
			throw new ErrorOnDataException("The first item of this drop down list shoud be: Any Entrance or Any Trail.");
		}
		
		for(int i=0; i<expectedEntrance.size()-1; i++){
			if(!expectedEntrance.get(i).equals(expectedEntrance.get(i+1))){
				nORepeatableEntrance.add(expectedEntrance.get(i));
			}
			if(i==expectedEntrance.size()-2 && !expectedEntrance.get(expectedEntrance.size()-2).equals(expectedEntrance.get(expectedEntrance.size()-1))){
				nORepeatableEntrance.add(expectedEntrance.get(expectedEntrance.size()-1));
			}
		}
		if(nORepeatableEntrance.size()==0){
			nORepeatableEntrance = expectedEntrance;
		}
		if(entranceInPanel.size()-1==nORepeatableEntrance.size()){
			for(int i=0; i<nORepeatableEntrance.size(); i++){
				if(!entranceInPanel.get(i+1).equals(nORepeatableEntrance.get(i))){
					throw new ErrorOnDataException("Expect entrance should be "+nORepeatableEntrance.get(i)+
							", but the actual value is "+entranceInPanel.get(i+1));
				}
			}
		}else throw new ErrorOnDataException("The length of compared list doesn't equal.");
	}
	
    /**
     * Verify Entrance display
     * @param entrances: all the active or inactive entrances
     * @param theActives: true: all the active should display, false: all the inactive should not display
     */
	public void verifyEntrancesDisplay(boolean theActives, List<String> entrances){
		logger.info("Start to verify all "+(theActives?"active":"inactive")+" entrances "+(theActives?"":"don't")+" display.");
		boolean result = true;
		
		//Remove the default trail options"- Please Select -"
		List<String> actualEntrances = this.getAllEntrance();
		if(actualEntrances.get(0).equals("Any Entrance")||
				actualEntrances.get(0).equals("Any Trail") ||
				actualEntrances.get(0).equals("Any Destination Zone") ||
				actualEntrances.get(0).equals("Any Permit Zone") ||
				actualEntrances.get(0).equals("Any River")){
			actualEntrances.remove(0);
		}

		if(theActives){
			//Verify all active options display
			result = result && MiscFunctions.compareResult("The size of Trail Heads", entrances.size(), actualEntrances.size());
			for(int i=0; i<actualEntrances.size(); i++){
				if(!entrances.toString().contains(actualEntrances.get(i))){
					result = false;
					logger.debug(i+" - active Trail Head ('"+actualEntrances.get(i)+"') doesn't display.");
					break;
				}
			}
		}else{
			//Verify all inactive options don't display
			for(int i=0; i<actualEntrances.size(); i++){
				if(entrances.toString().contains(actualEntrances.get(i))){
					result = false;
					logger.debug(i+" - inactive Trail Head ('"+actualEntrances.get(i)+"') displays.");
					break;
				}
			}
		}

		if(!result){
			throw new ErrorOnPageException("Not all "+(theActives?"active":"inactive")+" Entrances "+(theActives?"":"don't")+" display.. Please check details info from previous logs.");
		}else{
			logger.info("Successfully verify all "+(theActives?"active":"inactive")+" Entrances "+(theActives?"":"don't")+" display.");
		}
	}
	
	/**
	 * Verify specific entrance display
	 * @param entrance
	 * @param display: true:entrance display, false:doesn't display
	 */
	public void verifySpecificEntranceDisplay(String entrance, boolean display){
		logger.info("Verify the entrance (Name:"+entrance+") "+(display?"":"doesn't")+" display.");
		List<String> entrances = this.getAllEntrance();
	
		
		boolean result = entrances.toString().contains(entrance);

		if(display!=result){
			throw new ErrorOnPageException(entrances.toString()+" should "+(display?"":"don't ")+"contain the entrance:"+entrance);
		}
		logger.info("Successfully verify the entrance (Name:"+entrance+") "+(display?"":"don't")+" display.");
	}
	
	/**
	 * 
	 * @param isAvailablity
	 * @return
	 */
	public boolean isExitAvailablity(){
		return browser.checkHtmlObjectExists(".id", "entryExitDiv", ".className", new RegularExpression("^hiddenoptions$", false));
	}
	
	/**
	 * If the selected permit type has available exit, the Entry and Exit trail radio will display
	 * @param display        true: entry and exit trail radio should be display
	 *                       false: entry and exit trail radio should not be display
	 */
	public void isEntryAndExitTrailRadioDisplay(boolean display){
		boolean result = this.isExitAvailablity();
		if(result!=display){
			throw new ErrorOnPageException("Failed to verify Entry and Exit trail "+(display?"":"doesn't ")+"display.");
		}
		logger.info("Successfully verify Entry and Exit trail "+(display?"":"doesn't ")+"display.");
	}
	
	/**
	 * Check exit date displays
	 * @return
	 */
	public boolean exitDateDisplays(){
		Property[] p1 =Property.toPropertyArray(".class", "Html.DIV", ".id", "datesdiv");
		Property[] p2 =Property.toPropertyArray(".class", "Html.LABEL", ".id", "lblForArrDate", ".text", "Exit Date");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)); 
	}
	
	/**
	 * Verify whether exist date display or not 
	 * @param display  true: exit date should display
	 *                 false: exit date should not display
	 */
	public void verifyExitDateDisplay(boolean display){
		boolean result = this.exitDateDisplays();
		if(result!=display){
			throw new ErrorOnDataException("Failed to verify 'Exit Date' should "+(display?"":"not ")+"display.");
		}
		logger.info("Successfully verify 'Exit Date' "+(display?"displays":"doesn't display"));
	}
	
	public boolean isExitTrailDDListDisplays(){
		Property[] p =Property.toPropertyArray(".class", "Html.DIV", ".id", "entranceDiv", ".text", new RegularExpression("^Exit Trail.*", false));
		return browser.checkHtmlObjectExists(p); 
	}
	
	public void verifyExitTrailDDListDisplays(boolean display){
		boolean result = this.isExitTrailDDListDisplays();
		if(result!=display){
			throw new ErrorOnDataException("Failed to verify 'Exit Trail' drop down list should "+(display?"":"not ")+"display.");
		}
		logger.info("Successfully verify 'Exit Trail' drop down list"+(display?"displays":"doesn't display"));
	}
	
	public boolean isEntryTrailDDListDisplays(){
		Property[] p =Property.toPropertyArray(".class", "Html.DIV", ".id", "entranceDiv", ".text", new RegularExpression("^Entry Trail.*", false));
		return browser.checkHtmlObjectExists(p); 
	}
	
	public void verifyEntryTrailDDListDisplays(boolean display){
		boolean result = this.isEntryTrailDDListDisplays();
		if(result!=display){
			throw new ErrorOnDataException("Failed to verify 'Entry Trail' drop down list should "+(display?"":"not ")+"display.");
		}
		logger.info("Successfully verify 'Entry Trail' drop down list"+(display?"displays":"doesn't display"));
	}
	/**
	 * Check exit date displays
	 * @return
	 */
	public boolean entryDateDisplays(){
		Property[] p1 =Property.toPropertyArray(".class", "Html.DIV", ".id", "datesdiv");
		Property[] p2 =Property.toPropertyArray(".class", "Html.LABEL", ".id", "lblForArrDate", ".text", "Entry Date");
		return browser.checkHtmlObjectExists(Property.atList(p1, p2)); 
	}
	
	/**
	 * Verify whether entry date display or not 
	 * @param display  true: entry date should display
	 *                 false: entry date should not display
	 */
	public void verifyEntryDateDisplay(boolean display){
		boolean result = this.entryDateDisplays();
		if(result!=display){
			throw new ErrorOnDataException("Failed to verify 'Entry Date' should "+(display?"":"not ")+"display.");
		}
		logger.info("Successfully verify 'Entry Date' "+(display?"displays":"doesn't display"));
	}
	
	public String getZoneDropListLabel() {
		return browser.getObjectText(this.getZoneLabelProp());
	}
	
	public boolean isZoneDropListExist() {
		return browser.checkHtmlObjectExists(this.getZoneSelectListProp());
	}
	
	public String getZoneDropListValue() {
		return browser.getDropdownListValue(this.getZoneSelectListProp(), 0);
	}

	public List<String> getZoneDropListValues() {
		return browser.getDropdownElements(this.getZoneSelectListProp());
	}
	
	public String getEntranceTypeDropListLabel() {
		return browser.getObjectText(this.getEntranceTypeLabelProp());
	}
	
	public boolean isEntranceTypeDropListExist() {
		return browser.checkHtmlObjectExists(this.getEntranceTypeSelectListProp());
	}
	
	public String getEntranceTypeDropListValue() {
		return browser.getDropdownListValue(this.getEntranceTypeSelectListProp(), 0);
	}

	public List<String> getEntranceTypeDropListValues() {
		return browser.getDropdownElements(this.getEntranceTypeSelectListProp());
	}
	
	public String getEntranceDropListLabel() {
		return browser.getObjectText(this.getEntranceLabelProp());
	}
	
	public boolean isEntranceDropListExist() {
		return browser.checkHtmlObjectExists(this.getEntranceSelectListProp());
	}
	
	public String getStartDateFieldLabel() {
		return browser.getObjectText(this.getEntranceStartDateLabelProp());
	}
	
	public boolean isStartDateFieldExist() {
		return browser.checkHtmlObjectExists(this.getEntranceStartDateProp());
	}
	
	public String getStartDateFieldValue() {
		return browser.getTextFieldValue(this.getEntranceStartDateProp());
	}
	
	public String getStartDateFieldFormat() {
		IHtmlObject[] objs = browser.getHtmlObject(this.getEntranceStartDateProp());
		if(objs.length>1){
			throw new ObjectNotFoundException("Start date field can't be found.");
		}
		String value = objs[0].getProperty(".placeholder");
		Browser.unregister(objs);
		return value;
	}
	public String getGroupSizeFieldLabel() {
		return browser.getObjectText(this.getGroupSizeLabelProp());
	}
	
	public boolean isGroupSizeFieldDisplayed() {
		return browser.checkHtmlObjectDisplayed(this.getGroupSizeFieldProp());
	}
	
	public String getGroupSizeFieldValue() {
		return browser.getTextFieldValue(this.getGroupSizeFieldProp(), 0);
	}
	
	public String getLengthOfStayFieldLabel() {
		return browser.getObjectText(this.getLengthOfStayLabelProp());
	}
	
	public boolean isLengthOfStayFieldDisplayed() {
		return browser.checkHtmlObjectDisplayed(this.getLengthOfStayFieldProp());
	}
	
	public String getLengthOfStayFieldValue() {
		return browser.getTextFieldValue(this.getLengthOfStayFieldProp(), 0);
	}
}
