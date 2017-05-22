package com.activenetwork.qa.awo.pages.web.bw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.PermitAlternateLeaders;
import com.activenetwork.qa.awo.datacollection.legacy.PermitEmergencyContact;
import com.activenetwork.qa.awo.datacollection.legacy.PermitGroupMembers;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.AlertDialogPage;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.KeyInput;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * @author QA
 */
public class BwChangePermitDetailsPage extends UwpPage {

	private RegularExpression pageMark = new RegularExpression(
			"(Change|Update|Complete) Permit Information Permit #\\s*: [0-9]-[0-9]+", false);

	private static BwChangePermitDetailsPage _instance = null;

	public static BwChangePermitDetailsPage getInstance() {
		if (null == _instance)
			_instance = new BwChangePermitDetailsPage();

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", pageMark);
	}

	public boolean hasPersonTypes() {
		RegularExpression regex = new RegularExpression("Quantity Rate Total .*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", regex);
		boolean result = objs.length > 0;
		Browser.unregister(objs);
		return result;
	}

	public void changeGroupSize(int size) {
		String max = getMaxGroupSize();
		if (!StringUtil.isEmpty(max)&&size > Integer.parseInt(max))
			throw new PageNotFoundException("Size " + size
					+ " is bigger than the Max Group Size " + max);
		else if (size < 1)
			size = Integer.parseInt(max);

		IHtmlObject[] objs = browser.getTextField(".id", "groupsizeid");
		if (objs.length < 1) {
			Browser.unregister(objs);
			objs = browser.getTextField(".id", "qtyPersonsId");
		}
		((IText) objs[0]).setText(size);
		Browser.unregister(objs);
	}
	
	/**
	 * click 'Confirm Changes' to persist any change for an existing order
	 */
	public void clickConfirmChanges() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "continueshop");
	}

	/**
	 * Fill in emergency contracts first, last name and phone number.
	 * 
	 * @param emergencyContracts
	 *            - permit emergency contracts info
	 */
	public void setEmergencyContractsInfo(PermitEmergencyContact emergencyContracts) {
		this.setEmergencyContractFName(emergencyContracts.getfNames());
		this.setEmergencyContractLName(emergencyContracts.getlNames());
		this.setEmergencyContractPhone(emergencyContracts.getPhones());
	}
	
	/**
	 * setEmergencyContractsInfo Emergency contract elements(FName, LName, Phone)
	 * @param emergencyContractLable
	 * @param emergencyContractElementClassName
	 * @param emergencyContractElementValues
	 */
	private void setEmergencyContractElements(String emergencyContractElementClassName, String[] emergencyContractElementValues){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "emergencyContactTable");
		Property[] p2 = Property.toPropertyArray(".class", "Html.INPUT.text", ".name", emergencyContractElementClassName);
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Emergency contract first name object can't be found.");
		}
		for(int i=0; i<emergencyContractElementValues.length; i++){
			((IText) objs[i]).setText(emergencyContractElementValues[i]);
		}
		Browser.unregister(objs);
	}
	
	public void setEmergencyContractFName( String[] emergencyContractFName){
		this.setEmergencyContractElements("emergencyContactFirstName", emergencyContractFName);
	}
	
	public void setEmergencyContractLName(String[] emergencyContractLName){
		this.setEmergencyContractElements("emergencyContactLastName", emergencyContractLName);
	}
	
	public void setEmergencyContractPhone(String[] emergencyContractPhone){
		this.setEmergencyContractElements("emergencyContactPhone", emergencyContractPhone);
	}

	public void setWatercraftNum(int num) {
		browser.setTextField(".id", "numberofwatercraftid", num + "");
	}

	/**
	 * Retrieve the maximum group size.
	 * @return - maximum group size
	 */
	public String getMaxGroupSize() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.FORM",
				".id", "updatePermitReservationForm");
		String text = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);
		String[] maxs = RegularExpression.getMatches(text, "(Max: [0-9]+)");
		String maxSize = "";
		if(maxs!=null && maxs.length>0){
			maxSize=RegularExpression.getMatches(maxs[0], "[0-9]+")[0];
		}
		return maxSize;
	}

	/**Click on Confirm Or Issue button*/
	public void clickConfirmOrIssue() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "continueshop");
	}

	/**
	 * Verify issue order object exists.
	 * @return
	 */
	public boolean okForIssue() {
//		RegularExpression regex = new RegularExpression("Issue Permit Issued To .*", false);
//		HtmlObject[] objs = browser.getTableTestObject(".text", regex);
//		boolean toReturn = objs.length > 0;
//		Browser.unregister(objs);
		return browser.checkHtmlObjectExists(".class","Html.LABEL","for","issuedtoid");
	}

	/**
	 * Set up issue info by fixed value.
	 */
	public void setupIssueInfo() {
		setupIssueInfo(1, DateFunctions.getDateStamp(false), "ORMS 1234");
	}

	/**
	 * Set up issue info, select by item name given, fill in actual entry date and plate number.
	 * @param issueToIndex - item name want to select
	 * @param actualDate - actual entry date
	 * @param plate - plate number
	 */
	public void setupIssueInfo(String issueTo, String actualDate, String plate) {
		this.selectIssuePermitCheckBox();
		this.selectIssuedTo(issueTo);
		this.setActualEntryDate(actualDate);
		this.setVechiclePlateNumber(plate);
	}

	/**
	 * Set up issue info, select by index given, fill in actual entry date and plate number.
	 * @param issueToIndex - index of item want to select
	 * @param actualDate - actual entry date
	 * @param plate - plate number
	 */
	public void setupIssueInfo(int issueToIndex, String actualDate, String plate) {
		this.selectIssuePermitCheckBox();
		this.selectIssuedTo(issueToIndex);
		this.setActualEntryDate(actualDate);
		this.setVechiclePlateNumber(plate);
	}

	/**Check Issue Permit check box*/
	public void selectIssuePermitCheckBox() {
		browser.selectCheckBox(".name", "issuePermit");
	}

	/**
	 * Select Issue To in dropdown list by index.
	 * @param index - index of item want to select, start from 0
	 */
	public void selectIssuedTo(int index) {
		browser.selectDropdownList(".id", "issuedtoid", index);
	}

	/**
	 * Select Issue To in dropdown list by given item.
	 * @param item - item want to select
	 */
	public void selectIssuedTo(String item) {
		if (item == null || item.length() < 1)
			selectIssuedTo(1);
		browser.selectDropdownList(".id", "issuedtoid", item);
	}

	/**
	 * Fill in actual entry date.
	 * @param date - entry date
	 */
	public void setActualEntryDate(String date) {
		if (date == null || date.length() < 1)
			return;
		browser.setTextField(".id", "actualentrydateid", date);
	}

	/**
	 * Fill in vechicle plate number.
	 * @param plate - plate number
	 */
	public void setVechiclePlateNumber(String plate) {
		if (plate == null || plate.length() < 1)
			plate = "QA TESTER";

		browser.setTextField(".name", "vehiclePlateNum", plate);
	}

	public boolean checkShoppingItemsExist(){
		RegularExpression regx = new RegularExpression("^Item Details Transaction Details.*", false);
		return browser.checkHtmlObjectExists(".id", "shoppingitems", ".text", regx);
	}

	/**
	 * Verify whether shopping Items exist or not
	 * @param flag  --true: exist
	 *              --false: doesn't exist
	 */
	public void verifyShoppingItemsExist(boolean flag){
		boolean actualExist = this.checkShoppingItemsExist();
		if(actualExist!=flag){
			throw new ObjectNotFoundException("Shopping Items should "+(flag?"":"not")+" be exist.");
		}else{
			logger.info("Successfully verify Shopping Items "+(flag?"":"doesn't")+" exist.");
		}
	}

	public void setGuideFirstName(String guideFirstName) {
		browser.setTextField(".id", "firstnameid", guideFirstName);
	}

	public void setGuideLastName(String guideLastName) {
		browser.setTextField(".id", "lastnameid", guideLastName);
	}

	public void setGuideCardNum(String cardNum) {
		browser.setTextField(".id", "cardnumberid", cardNum);
	}

	public boolean checkGuideTripRadioExist(){
		return browser.checkHtmlObjectExists(".id", "guidedYes");
	}

	public void selectGuideYes(){
		browser.selectRadioButton(".id", "guidedYes");
	}

	public void selectGuideNo(){
		browser.selectRadioButton(".id", "guidedNo");
	}

	public void selectNonCommercialUseTypeAgreement() {
		browser.selectCheckBox(".id", "nonCommercialUseTypeAgreement");
	}

	public boolean checkGuideTripDropDownListExist(){
		return browser.checkHtmlObjectExists(".id", "typeofguidedtripid");
	}

	public void selectGuideTripOption(String guideTripOption){
		browser.selectDropdownList(".id", "typeofguidedtripid", guideTripOption);
	}

	/**
	 * Check the Non-Profit Organization check box exists
	 * 
	 * @return
	 */
	public boolean isNonProfitOrganizationExist() {
		if (browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",".id", "makeNP")) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Select the check box of Non-Profit Organization
	 */
	public void selectNoProfitOrganizationCheckBox() {
		browser.selectCheckBox(".id", "makeNP");
	}

	/**
	 * Unselect the check box of Non-Profit Organization
	 */
	public void unSelectNoProfitOrganizationCheckBox() {
		browser.unSelectCheckBox(".id", "makeNP");
	}

	/**
	 * Set the Non-Profit Organization name
	 * 
	 * @param orgName
	 */
	public void setNonProfitOrganizationName(String orgName) {
		if (orgName != null && orgName.length() > 0) {
			browser.setTextField(".id", "organizationnameid", orgName);
		} else {
			browser.setTextField(".id", "organizationnameid", "ReserveAmerica");
		}
	}

	/**
	 * Set the Non-Profit Organization tax id
	 * 
	 * @param taxId
	 */
	public void setNonProfitOrganizationTaxID(String taxId) {
		if (taxId != null && taxId.length() > 0) {
			browser.setTextField(".id", "taxidid", taxId);
		} else {
			browser.setTextField(".id", "taxidid", taxId);
		}
	}

	public boolean isTakeOutPointExist() {
		return browser.checkHtmlObjectExists(".id", "takeoutpointid");
	}

	public void selectTakeOutPoint(String takeOutpoint) {
		browser.selectDropdownList(".id", "takeoutpointid", takeOutpoint);
	}

	public boolean isLaunchPointExist() {
		return browser.checkHtmlObjectExists(".id", "launchpointid");
	}

	public void selectLaunchPoint(String launchPoint){
		browser.selectDropdownList(".id", "launchpointid", launchPoint);
	}

	public boolean isTrailheadDDListExist() {
		return browser.checkHtmlObjectExists(".id", "trailheadid");
	}

	public void selectTrailHead(String option){
		browser.selectDropdownList(".id", "trailheadid", option);
	}
	
	public String getDefaultTrailHead(){
		return browser.getDropdownListValue(".id", "trailheadid");
	}
	
	public List<String> getTrailHeadOptions(){
		return browser.getDropdownElements(".id", "trailheadid");
	}

	private void selectTrailHead(int i) {
		browser.selectDropdownList(".id", "trailheadid", i);
	}

	private List<String> getTrailHeads() {
		return browser.getDropdownElements(".id", "trailheadid");
	}

	public boolean isTravelMethodExist(){
		return browser.checkHtmlObjectExists(".id", "travelmethodid");
	}

	public void selectTravelMethod(String option){
		browser.selectDropdownList(".id", "travelmethodid", option);
	}

	public boolean isDeliveryMethodDDListExist() {
		return browser.checkHtmlObjectExists(".id", "permitdeliverymethodid");
	}

	public void selectDeliveryMethod(String deliveryMethod) {
		browser.selectDropdownList(".id", "permitdeliverymethodid", deliveryMethod);
	}

	/**
	 * check the Exit Point exist
	 * 
	 * @return true --- exist
	 */
	public boolean isExitPointExist() {
		return browser.checkHtmlObjectExists(".id", "exitpointid");
	}

	/**
	 * Select exit point drop down list
	 * 
	 * @param exitPiont
	 */
	public void selectExitPoint(String exitPiont) {
		if (null != exitPiont && exitPiont.length() < 0) {
			browser.selectDropdownList(".id", "exitpointid", exitPiont);
		} else {
			browser.selectDropdownList(".id", "exitpointid", 1);
		}
	}

	public boolean isGroupMemberExist(){
		return browser.checkHtmlObjectExists(".name", "groupMemberFirstName");		
	}

	/**
	 * Get group number size according to first name
	 * @return
	 */
	public int getGroupNumberSize(){
		int groupNumberSize = -1;
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text", ".name", "groupMemberFirstName");
		if(objs.length<0){
			throw new ObjectNotFoundException("Group number first name object can't be found.");
		}else{
			groupNumberSize = objs.length;
			for(int i = 0; i < objs.length; i ++) {
				if(((IText)objs[i]).getText().length() < 1) {
					groupNumberSize--;
				}
			}
		}

		Browser.unregister(objs);
		return groupNumberSize;
	}

	public void setGroupMembers(PermitGroupMembers groupMembers) {
		if (groupMembers == null || groupMembers.getSize() < 1)
			return;
		String[] s = { "groupMemberFirstName", "groupMemberLastName",
		"groupMemberComments" };

		// we expect maximum 3 group members
		for (int i = 0; i < s.length; i++) {
			IHtmlObject[] objs = browser.getHtmlObject(".class",
					"Html.INPUT.text", ".name", s[i]);
			int size = Math.min(objs.length, groupMembers.getSize());
			for (int j = 0; j < size; j++) {
				switch (i) {
				case 0:
					((IText) objs[j]).setText(groupMembers.getfNames()[j]);
					break;
				case 1:
					((IText) objs[j]).setText(groupMembers.getlNames()[j]);
					break;
				case 2:
					((IText) objs[j]).setText(groupMembers.getComments()[j]);
					break;
				}
			}
			Browser.unregister(objs);
		}
	}

	/**
	 * Fill in alternate leader first, last name and phone number.
	 * 
	 * @param alt
	 *            - permit alternate leader info
	 */
	public void setAlternateLeaders(PermitAlternateLeaders alt) {
		if (alt == null || alt.getSize() < 1)
			return;
		String[] s = { "altLeaderFirstName", "altLeaderLastName",
		"altLeaderPhone" };

		// we expect maximum 3 alternate leaders
		for (int i = 0; i < s.length; i++) {
			IHtmlObject[] objs = browser.getHtmlObject(".class",
					"Html.INPUT.text", ".name", s[i]);
			int size = Math.min(objs.length, alt.getSize());
			for (int j = 0; j < size; j++) {
				switch (i) {
				case 0:
					((IText) objs[j]).setText(alt.getfNames()[j]);
					break;
				case 1:
					((IText) objs[j]).setText(alt.getlNames()[j]);
					break;
				case 2:
					((IText) objs[j]).setText(alt.getPhones()[j]);
					break;
				}	
			}
			Browser.unregister(objs);
		}
	}

	public boolean checkExitDateExist(){
		return browser.checkHtmlObjectExists(".id", "exitdateid");
	}

	public void clickExitDate() {
		browser.clickGuiObject(".id", "exitdateid");
	}
	
	public void setExitDate(String date) {
		browser.setTextField(".id", "exitdateid", date);
	}

	public void refreshPage(){
		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("^Exit Date(\\*)?$", false));
		Browser.sleep(5);
	}

	public boolean checkExitDateForGroupSizeExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "exitDateDivId");
	}

	/**
	 * method used to wait Exit Date displayed in 'Group Size' section synchronized successfully
	 */
	public void waitForExitDateSync() {
		String setExitDate = browser.getTextFieldValue(".id", "exitdateid");
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".id", "exitDateDivId");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Exit Date DIV object.");
		}
		int counter = 0;
		String displayExitDate = "";
		do {
			displayExitDate = objs[0].getProperty(".text");
			if(DateFunctions.compareDates(setExitDate, displayExitDate) == 0) {
				logger.info("Exit Date displayed in 'Group Size' section is synchronized successfully.");
				break;
			}

			Browser.sleep(1);
			counter ++;

			objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "exitDateDivId");
		} while(counter < 10);

		if(DateFunctions.compareDates(setExitDate, displayExitDate) != 0) {
			throw new ErrorOnPageException("Exit Date displayed in 'Group Size' section is NOT synchronized.");
		}

		Browser.unregister(objs);
	}

	public void clickAddNewEntryDateLink(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "addEntryDateLinkDiv");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	/**
	 * The 'Entry Date' in 'Group Size' section
	 * @return
	 */
	public void setEntryDateUnderGroupSize(String entryDate,int index){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".className", "entryDateInfo", ".id", "entryRowId");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'entryDateInfo' object can't be found.");
		}

		Property[] p2 = Property.toPropertyArray(".id", "entrydateid");
		browser.setTextField(p2, entryDate, true, 0, objs[index]);

		Browser.unregister(objs);
	}

	public void setNumOfDaysUnderGroupSize(String numOfDays, int index){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".className", "entryDateInfo", ".id", "entryRowId");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'entryDateInfo' object can't be found.");
		}
		Property[] p = Property.toPropertyArray(".id", "#ofdaysid");
		browser.setTextField(p, numOfDays, true, 0, objs[index]);

		Browser.unregister(objs);
	}

	public void setEntryDateAndNumOfDaysUnderGroupSize(String[] entryDateUnderGroupSize, String[] NumOfDaysUnderGroupSize){
		logger.info("Set up entry date and number of days under group size.");
		if(!MiscFunctions.compareResult("The length of entry date and num of days", entryDateUnderGroupSize.length, NumOfDaysUnderGroupSize.length)){
			throw new ErrorOnDataException("The length of entry date is wrong!", String.valueOf(entryDateUnderGroupSize.length), String.valueOf(NumOfDaysUnderGroupSize.length));
		}

		for(int i=0; i<entryDateUnderGroupSize.length; i++){
			if(i!=0){
				this.clickAddNewEntryDateLink();
				this.waitForExitDateSync();
			}

			this.setEntryDateUnderGroupSize(entryDateUnderGroupSize[i], i);
			this.refreshPage();

			this.setNumOfDaysUnderGroupSize(NumOfDaysUnderGroupSize[i], i);
			this.refreshPage();
		}
	}

	public boolean checkActualEntryDateExist(){
		return browser.checkHtmlObjectExists(".id", "actualentrydateid");
	}

	public boolean isTripItineraryExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false));
	}

	/**
	 * Get the number of "Trip Itinerary"
	 * @return
	 */
	public int getTripItineraryRowCount(){
		int count = -1;

		IHtmlObject[] objs = browser.getHtmlObject(".id", "tripItineraryInfoRow");
		if(objs.length<=0){
			throw new ObjectNotFoundException("Trip Itinerary Info row object can't be found.");
		}else{
			count = objs.length;
		}

		Browser.unregister(objs);
		return count;
	}

	public void selectTripItineraryLocs(String[] tripItineraryLocs) {
		if (tripItineraryLocs == null || tripItineraryLocs.length < 1)
			return;
		for (int i = 0; i < tripItineraryLocs.length; i++) {
			browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), tripItineraryLocs[i], i);
		}
	}

	public void selectTripItineraryLoc(int index){
		browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), index);
	}

	public void selectTripItineraryLoc(String option){
		browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), option);
	}

	/**
	 * Check the field is group size
	 * 
	 * @return
	 */
	public boolean isGroupSizeTextField() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "groupsizeid");
	}

	public boolean isPersonTypesExist(){
		return browser.checkHtmlObjectExists(".id", "formSection", ".class", "Html.TABLE");
	}
	
	public boolean isNumberOfStock(){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "qtyStocksId");
	}

	public boolean isNumberOfPets(){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "qtyPetsId");
	}

	/**
	 * Set group size, if not provided, set to 2.
	 * 
	 * @param size
	 *            - group size
	 * @throws ItemNotFoundException
	 */
	public void setGroupSize(String size) throws ItemNotFoundException {
		if (size == null || size.length() < 1)
			size = "2";

		RegularExpression id = new RegularExpression("(groupsizeid\\d?)|(qtyPersonsId)", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",".id", id);
		if (objs.length < 1) {
			Browser.unregister(objs);
			browser.setTextField(".id", "qtyPersonsId", size);
		} else {
			((IText) objs[0]).setText(size);
			Browser.unregister(objs);
		} 
	}

	public void setGroupSize(String[] sizes) {
		RegularExpression id = new RegularExpression("(groupsizeid\\d?)|(qtyPersonsId)", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",".id", id);
		int size=Math.min(objs.length, sizes.length);
		if (size >0) {
			for(int i=0; i<size; i++){
				((IText) objs[i]).setText(sizes[i]);
			}
			Browser.unregister(objs);
		}
	}

	public void initializePersonNumsAndTypes(){
		   IHtmlObject[] objs = browser.getTextField(".id", "qtyPersonsId");
		   if(objs.length<1){
			   throw new ObjectNotFoundException("Object id=qtyPersonsId can't be found.");
		   }
		   for(int i=0; i<objs.length; i++){
			  ((IText) objs[i]).setText(StringUtil.EMPTY);
		   }
		   Browser.unregister(objs);
		}
	/**
	 * Get the DIVs have person types and person numbers
	 * Sample data
	 * Adult    XX
	 * Child    XX
	 * Interagency Access Pass XX
	 * @return
	 */
	public IHtmlObject[] getPersonTypesDIVObjs(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".id", "formSection");
		if(objs==null || objs.length<index){
			throw new ObjectNotFoundException("Can't find the the - "+index+" - table which id=formSection");
		}
		return objs;
	}
	
	/**
	 * Set specific person type size in Group Size section
	 * 
	 * @param memberTypes
	 *            -- person type
	 * @param memeberNums
	 *            -- person number
	 */
	public void setPersonNumsPersonType(String[] memberTypes, String[] memeberNums, int index) {
		String[] personTypes=getPersonTypes(); //All person types and numbers are same for each entrances added into itinerary
		if(memberTypes!=null && memberTypes.length>0 && (memeberNums==null || memeberNums.length<1)){
			memeberNums = new String[memberTypes.length];
			for(int i=0; i<memberTypes.length; i++){
				memeberNums[i] = "1";
			}
		}else{
			if (memberTypes == null || memberTypes.length == 0) {
				memberTypes = new String[1];
				memberTypes[0] = personTypes[0]; //take the 1st one
			}
			if (memeberNums == null || memeberNums.length == 0) {
				memeberNums = new String[1];
				memeberNums[0] = "3";
			}
		}

		IHtmlObject[] objs = getPersonTypesDIVObjs(index);
		for (int i = 0; i < personTypes.length; i++) {
			for (int j = 0; j < memberTypes.length; j++) {
				//if (memberTypes[j].equalsIgnoreCase(personTypes[i])) {
				if (personTypes[i].startsWith(memberTypes[j])) { //xxx Multi
					browser.setTextField(Property.toPropertyArray(".id", "qtyPersonsId"), memeberNums[j], true, i, objs[index]);
				}
			}
		}
		
		Browser.unregister(objs);
	}
	
	public void setPersonNumsPersonType(String[] memberTypes, String[] memeberNums) {
		setPersonNumsPersonType(memberTypes, memeberNums, 0);
	}

	public boolean isPersonTypeExist(){
		return browser.checkHtmlObjectExists(".id", "formSection", ".class", "Html.TABLE");
	}
	
	public boolean isAlternateLeaderInfoExist(){
		return browser.checkHtmlObjectExists(".className", "altLeaderFirstName");
	}
	
	public boolean isNumOfWatercraftExist(){
		return browser.checkHtmlObjectExists(".id", "numberofwatercraftid", ".class", "Html.DIV");
	}

	public String[] getPersonTypes() {
		List<String> personTypes=new ArrayList<String>();
		IHtmlObject[] objs = browser.getTableTestObject(".id", "formSection");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find the table which id=formSection");
		}
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".className", "personTypeInfo", objs[0]);
		if(trs==null || trs.length<1){
			throw new ObjectNotFoundException("Can't get TRs for person type.");
		}
		for(int i=0;i<trs.length;i++){
			personTypes.add(trs[i].text().replaceAll("(\\$|\\d+\\.\\d+)", "").replaceAll("Multi", "").trim());
		}
		Browser.unregister(trs,objs);

		return personTypes.toArray(new String[0]);
	}

	public IHtmlObject[] getPersonTypeInfoTRs(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "formSection");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find the table which id=formSection");
		}
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".className", "personTypeInfo", objs[0]);
		if(trs==null || trs.length<1){
			throw new ObjectNotFoundException("Can't get TRs for person type.");
		}

		Browser.unregister(objs);
		return trs;
	}

	public String[] getStockTypes() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "stockFormSection");
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> list = table.getColumnValues(0);
		Browser.unregister(objs);
		List<String> stockType=new ArrayList<String>();
		//filter
		int size=list.size();
		for(int i=0;i<size;i++) {
			String s=list.get(i);
			if(StringUtil.isEmpty(s) || s.matches("\\$\\d+\\.\\d{2}") || s.startsWith("Total")) {
				continue;
			} else {
				stockType.add(s);
			}
		}

		return stockType.toArray(new String[0]);
	}

	/**
	 * Set specific stock type size in Number of Stock section
	 * 
	 * @param stockTypes
	 *            -- stock type
	 * @param stockNums
	 *            -- stock number
	 */
	public void selectStocks(String[] stockTypes, String[] stockNums) {
		String[] stocksTypes=getStockTypes();
		if (stockTypes == null || stockTypes.length == 0) {
			stockTypes = new String[1];
			stockTypes[0] = stocksTypes[0]; //take the 1st one
		}
		if (stockNums == null || stockNums.length == 0) {
			stockNums = new String[1];
			stockNums[0] = "2";
		}

		for (int i = 0; i < stockTypes.length; i++) {//The stock types get from method parameter
			for (int j = 0; j < stocksTypes.length; j++) {//The stock types get from UI
				if (stocksTypes[j].equalsIgnoreCase(stockTypes[i])) {
					browser.setTextField(".id", "qtyStocksId", stockNums[j],	i);
					break;
				}
			}
		}
	}

	public String[] getPetTypes() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "petFormSection");
		IHtmlTable table = (IHtmlTable) objs[0];
		List<String> list = table.getColumnValues(0);
		Browser.unregister(objs);
		List<String> petTypes=new ArrayList<String>();
		//filter
		int size=list.size();
		for(int i=0;i<size;i++) {
			String s=list.get(i);
			if(StringUtil.isEmpty(s) || s.matches("\\$\\d+\\.\\d{2}") || s.startsWith("Total")) {
				continue;
			} else {
				petTypes.add(s);
			}
		}

		return petTypes.toArray(new String[0]);
	}

	/**
	 * Set specific stock type size in Number of Stock section
	 * 
	 * @param stockTypes
	 *            -- stock type
	 * @param stockNums
	 *            -- stock number
	 */
	public void selectPets(String[] petTypes, String[] petNums) {
		String[] petsTypes=getPetTypes();
		if (petTypes == null || petTypes.length == 0) {
			petTypes = new String[1];
			petTypes[0] = petsTypes[0]; //take the 1st one
		}
		if (petNums == null || petNums.length == 0) {
			petNums = new String[1];
			petNums[0] = "2";
		}

		for (int i = 0; i < petsTypes.length; i++) {
			for (int j = 0; j < petsTypes.length; j++) {
				if (petsTypes[j].equalsIgnoreCase(petsTypes[i])) {
					browser.setTextField(".id", "qtyPetsId", petNums[j],	i);
				}
			}
		}
	}

	/**
	 * Fill in water craft number.
	 * 
	 * @param num
	 *            - number of water craft
	 */
	public void setWatercraftNum(String num) {
		if (num == null || num.length() < 1)
			num = "1";

		browser.setTextField(".id", new RegularExpression("numberofwatercraftid|qtyWaterCraftsId",false), num);
	}

	public void setWatercraftNum(String key, String value) {
		IHtmlObject[] tables=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".className", "personTypeInfo",".text",new RegularExpression("^"+key+".*",false)));
		if(tables==null ||tables.length<1){
			throw new ObjectNotFoundException("Can't find 'watercraftFormSection' table");
		}
		browser.setTextField(".id", "qtyWaterCraftsId", value,tables[0]);
		Browser.unregister(tables);
	}

	public void setNumOfGuides(String num) {
		browser.setTextField(".id", "numberofguidesid", num);
	}

	public void clickBringingAnimalsYes(){
		browser.clickGuiObject(".id", "bringingAnimals0");
	}

	public void clickBringingAnimalsNo(){
		browser.clickGuiObject(".id", "bringingAnimals1");
	}

	public boolean isCollectPaymentInfo() {
		return browser.checkHtmlObjectExists(".id", "cardTypeId_null");
	}

	public void setReentryDate(String date) {
		browser.setTextField(".id", "re-entrydateid", date);
	}

	public boolean isEmergencyContract(){
		return browser.checkHtmlObjectExists(".name", "emergencyContactFirstName");		
	}

	public boolean isPermitIssuingStation(){
		boolean flag=true;
		if(this.isDeliveryMethodDDListExist()){
			IHtmlObject[] objs=browser.getHtmlObject(".id", "issuing_station_row");
			if(objs==null || objs.length<1){
				throw new ObjectNotFoundException("Can't find row for Issue Station");
			}
			if(objs[0].style("display").equalsIgnoreCase("none")){
				flag=false;
			}
		}else{
			flag=browser.checkHtmlObjectExists(".id", "permitissuingstationid");
		}
		return flag;
	}

	public void selectPermitIssuingStation(String issueStation){
		browser.selectDropdownList(".id", "permitissuingstationid", issueStation);
	}

	/**
	 * Check agreement check box.
	 */
	public void selectAgreementAccept() {
		browser.selectCheckBox(".name", "agreementAccepted");
	}

	/**
	 * Fill in all payment info.
	 * 
	 * @param pay
	 *            - payment data
	 */
	public void setupPaymentInfo(Payment pay) {
		this.selectCardType(pay.payType);
		this.setCardNumber(pay.creditCardNumber);
		this.setExpireMonth(pay.expiryMon);
		this.setExpireYear(pay.expiryYear);
		this.setCardHolderName(pay.cardHolder);
	}

	/**
	 * Select card type from dropdown list.
	 * 
	 * @param type
	 *            - card type
	 */
	public void selectCardType(String type) {
		browser.selectDropdownList(".name", "cardType", type);
	}

	/**
	 * Fill in card number.
	 * 
	 * @param num
	 *            - card number
	 */
	public void setCardNumber(String num) {
		browser.setTextField(".name", "cardNumber", num);
	}

	/**
	 * Fill in expire month.
	 * 
	 * @param mon
	 *            - expire month
	 */
	public void setExpireMonth(String mon) {
		browser.setTextField(".name", "expMonth", mon);
	}

	/**
	 * Fill in expire year.
	 * 
	 * @param year
	 *            - expire year
	 */
	public void setExpireYear(String year) {
		browser.setTextField(".name", "expYear", year);
	}

	/**
	 * Fill in card holder info, input name should be full name like 'FName
	 * LName'.
	 * 
	 * @param name
	 *            - full name
	 */
	public void setCardHolderName(String name) {
		RegularExpression regex = new RegularExpression("(firstName|lastName)",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".name", regex);
		int size = objs.length;
		((IText) objs[size - 2]).setText(name.split(" ", 2)[0]);
		((IText) objs[size - 1]).setText(name.split(" ", 2)[1]);
		Browser.unregister(objs);
	}


	/**
	 * Update permit reservation
	 * 
	 * @param permit
	 *            - permit info data
	 * @throws ItemNotFoundException
	 */
	public void updatePermitRes(PermitInfo permit){
		if(!StringUtil.isEmpty(permit.guideFirstName)){
			this.setGuideFirstName(permit.guideFirstName);
			this.setGuideLastName(permit.guideLastName);
			this.setGuideCardNum(permit.cardNum);
		}

		if(this.checkGuideTripRadioExist() && permit.isGuideTrip){
			this.selectGuideYes();
			this.selectNonCommercialUseTypeAgreement();
		}else {
			if(this.checkGuideTripDropDownListExist()){
				this.selectGuideTripOption(permit.typeOfGuidedTrip);
			}
		}

		if (isNonProfitOrganizationExist() && permit.organization.length() > 0) {
			selectNoProfitOrganizationCheckBox();
			setNonProfitOrganizationName(permit.organization);
			setNonProfitOrganizationTaxID(permit.organizationTaxID);
		}

		if(this.isTakeOutPointExist()&&!StringUtil.isEmpty(permit.takeOutpoint)){
			this.selectTakeOutPoint(permit.takeOutpoint);
		}

		if(this.isLaunchPointExist() && !StringUtil.isEmpty(permit.launchPoint)){
			this.selectLaunchPoint(permit.launchPoint);
		}

		if(this.isTrailheadDDListExist()&&!StringUtil.isEmpty(permit.trailhead)){
			if(this.getTrailHeads().contains(permit.trailhead)){
				this.selectTrailHead(permit.trailhead);
			}else{
				this.selectTrailHead(1);
			}
		}

		if(this.isTravelMethodExist()&&!StringUtil.isEmpty(permit.travelMethod)){
			this.selectTravelMethod(permit.travelMethod);
		}

		if(this.isDeliveryMethodDDListExist()&&!StringUtil.isEmpty(permit.deliveryMethod)){
			this.selectDeliveryMethod(permit.deliveryMethod);
		}

		if (this.isExitPointExist()) {
			this.selectExitPoint(permit.exitPoint);
		}

		if(this.isGroupMemberExist()){
			this.setGroupMembers(permit.groupMembers);
			permit.groupMembers.setSize(this.getGroupNumberSize());
		}

		this.setAlternateLeaders(permit.alterLeaders);

		//If the "Exit Date" is available, set up the value
		if(this.checkExitDateExist()){
			this.clickExitDate();
			browser.dismissDialogs(1, AlertDialogPage.getInstance(), 1);
			browser.inputKey(KeyInput.get(KeyInput.BACKSPACE));

			this.setExitDate(permit.exitDate);
			browser.dismissDialogs(1, AlertDialogPage.getInstance(), 1);
			waitLoading();

			this.refreshPage();

			if(this.checkExitDateForGroupSizeExist()){
				this.waitForExitDateSync();

				if(null!=permit.entryDateUnderGroupSize && permit.entryDateUnderGroupSize.length>0 &&
						null!=permit.NumOfDaysUnderGroupSize && permit.NumOfDaysUnderGroupSize.length>0){
					this.setEntryDateAndNumOfDaysUnderGroupSize(permit.entryDateUnderGroupSize, permit.NumOfDaysUnderGroupSize);
				}
			}
		}

		if(this.checkActualEntryDateExist()){
			if (StringUtil.isEmpty(permit.actualEntryDate)) {
				permit.actualEntryDate = permit.entryDate;
				logger.debug("Set actual entry date="+permit.actualEntryDate);
			}
			this.setActualEntryDate(permit.actualEntryDate);
		}

		if(this.isTripItineraryExist()){
			Browser.sleep(5);
			int tripItineraryRowCount = this.getTripItineraryRowCount();
			if(tripItineraryRowCount>1 && permit.tripItineraryLocations!=null&&permit.tripItineraryLocations.length>0){
				this.selectTripItineraryLocs(permit.tripItineraryLocations);
			}else{
				if(!StringUtil.isEmpty(permit.tripItineraryLocation)){
					this.selectTripItineraryLoc(permit.tripItineraryLocation);
				}else{
					this.selectTripItineraryLoc(1);//Select the second option
				}
			}
		}

		//Sara[20130731] For itinerary permit
		if(browser.checkHtmlObjectExists(".id", "stiTable")){
			if(isPersonTypesExist()){
				initializePersonNumsAndTypes();
				//* In "Itinerary Details" section, have person types for each entrances. In "Permit Information" section, no group size text field and no person types
				if(permit.entrancesForItineraryPermit[0].personTypes.length>0){
					for(int i=0; i<permit.entrancesForItineraryPermit.length; i++){
						setPersonNumsPersonType(permit.entrancesForItineraryPermit[i].personTypes, permit.entrancesForItineraryPermit[i].personNums, i);
					}
				}else{
					//* In "Itinerary Details" section, no person types and no groups sizes for each entrances, but in "Permit Information" section, has person types
					setPersonNumsPersonType(permit.personTypes, permit.personNums);
				}
			}else{
				if(isGroupSizeTextField()){
					//* In "Itinerary Details" section, have group size text fields but no person types for each entrances, but in "Permit Information" section, no group size text field and no person types
					if(StringUtil.notEmpty(permit.entrancesForItineraryPermit[0].groupSize)){
						String[] groupSizes = new String[permit.entrancesForItineraryPermit.length];
						for(int i=0; i<groupSizes.length; i++){
							groupSizes[i] = permit.entrancesForItineraryPermit[i].groupSize;
						}
						setGroupSize(groupSizes);
						//* In "Itinerary Details" section, no group size text fields and no person types for each entrance, but in "Permit Information" section, has group size text field without person types
					}else setGroupSize(permit.groupSize);
				}
			}
		}else{
			//For entry permit
			//* Only has group size text field
			if (isGroupSizeTextField()) {
				this.setGroupSize(permit.groupSize);
				//* Have person types and person number
			}else if (this.isPersonTypesExist()){
				this.setPersonNumsPersonType(permit.personTypes, permit.personNums);
				//* No group size text field, also no person type and number
			}else logger.info("This permit doesn't have group size and person types.");
		}

		if(isNumberOfStock() && null!=permit.stockTypes && permit.stockTypes.length>0){
			this.selectStocks(permit.stockTypes, permit.stockNums);
		}
		
		if(isNumberOfPets() && null!=permit.petTypes && permit.petTypes.length>0){
			this.selectPets(permit.petTypes, permit.petNums);
		}


		if(permit.waterCraftInfo==null||permit.waterCraftInfo.size()<1){
			this.setWatercraftNum(permit.waterCraftNum);
		}else{
			for(Map.Entry<String, String> e : permit.waterCraftInfo.entrySet()){
				this.setWatercraftNum(e.getKey(),e.getValue());
			}
		}

		if(!StringUtil.isEmpty(permit.guideNum)){
			this.setNumOfGuides(permit.guideNum);
		}

		if(permit.isBringingAnimals){
			this.clickBringingAnimalsYes();
		}else {
			this.clickBringingAnimalsNo();
		}

		if (permit.issue) {
			this.selectIssuePermitCheckBox();
			this.selectIssuedTo(permit.issueTo);
			this.setActualEntryDate(permit.actualEntryDate);
			this.setVechiclePlateNumber(permit.plateNum);
		}

		// Setup payment info if it exists.
		if (this.isCollectPaymentInfo()) {
			// fill in the payment info if collect payment info in lottery
			// application details page
			Payment pay = new Payment("Visa");
			setupPaymentInfo(pay);
		}

		this.setReentryDate(permit.entryDate);

		//Set up Emergency Contract info
		if(this.isEmergencyContract()){
			this.setEmergencyContractsInfo(permit.emergencyContrats);
		}

		//Select issue station
		if(this.isPermitIssuingStation()){
			this.selectPermitIssuingStation(permit.issueStation);
		}

		this.selectAgreementAccept();
	}
		
	/**
	 * get error massage.
	 * @return
	 */
	public String getErrorMsg(){
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".className", "msg error");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find error message DIV object.");
		}
		
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		
		return msg;
	}
	
	/**
	 * Verify top error message
	 * @param expMsg
	 */
	public void verifyErrorMsg(String expMsg){
		logger.info("Verify top error message.");
		String actMsg = this.getErrorMsg();
		if (!actMsg.equals(expMsg)) {
			throw new ErrorOnPageException("The error message is wrong!", expMsg, actMsg);
		}
		logger.info("Successfully verify error message: "+ expMsg);
	}
	
	/**
	 * Get trip itinerary
	 * @param row: 1, 2, 3...
	 * @return
	 */
	public List<String> getTripItinerarys(int row){
		return browser.getDropdownElements(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1),false));
	}
	
	/**
	 * Verify specific trip itinerary display
	 * @param tripItinerary
	 * @param display
	 * @param row
	 */
	public void verifySpecificTripItineraryOptionDisplay(String tripItinerary, boolean display, int row){
		logger.info("Verify the Trip Itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't")+" display in 'Update Permit Information' page");

		List<String> allTripItinerarys = this.getTripItinerarys(row);
		boolean result = allTripItinerarys.toString().contains(tripItinerary);

		if(display!=result){
			throw new ErrorOnPageException(allTripItinerarys.toString()+" should "+(display?"":"not")+" contain the trip itinerary:"+tripItinerary);
		}
		logger.info("Successfully verify the trip itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't ")+"display.");
	}
	
	/**
	 * Verify specific Trail Head display
	 * @param trailHead
	 * @param display
	 */
	public void verifySpecificTrailHeadOptionDisplay(String trailHead, boolean display){
		logger.info("Verify the Trail Head (Name:"+trailHead+") "+(display?"":"doesn't")+" display in 'Permit Order details' page");
		List<String> allTrailHead = this.getTrailHeads();
		boolean result = allTrailHead.toString().contains(trailHead);

		if(display!=result){
			throw new ErrorOnPageException(allTrailHead.toString()+" should "+(display?"":"not")+" contain the Trail Head:"+trailHead);
		}
		logger.info("Successfully verify the Trail Head (Name:"+trailHead+") "+(display?"":"don't ")+"display.");
	}
}
