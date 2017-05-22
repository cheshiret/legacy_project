package com.activenetwork.qa.awo.pages.web.bw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.PermitAlternateLeaders;
import com.activenetwork.qa.awo.datacollection.legacy.PermitEmergencyContact;
import com.activenetwork.qa.awo.datacollection.legacy.PermitGroupMembers;
import com.activenetwork.qa.awo.datacollection.legacy.orms.EntranceInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.PermitInfo;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
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
public class BwPermitOrderDetailsPage extends UwpPage {

	private static BwPermitOrderDetailsPage _instance = null;

	public static BwPermitOrderDetailsPage getInstance() {
		if (null == _instance)
			_instance = new BwPermitOrderDetailsPage();

		return _instance;
	}

	private Property[] perPersonTypeTotalPriceProp = Property.toPropertyArray(".id", new RegularExpression("totalPricePerPersonType\\d+", false));

	public boolean exists() {
		RegularExpression regex = new RegularExpression(
				"^(Permit Order Details|Permit Information|Lottery Application)", false);
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text",
				regex);
	}

	public Property[] groupSizePropInPermitInfoSection(){
		return Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Permit Information ?Group Size.*", false));
	}
	
	public Property[] groupSizeTextFieldProp(){
		return Property.toPropertyArray(".class", "Html.INPUT.text", ".id", "groupsizeid");
	}
	
	public Property[] groupSizeProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".id", "groupSizeDiv");
	}
	
	public Property[] alertsSectionIDProp(){
		return Property.toPropertyArray(".id", "alertsSection");
	}
	
	public Property[] noteHeaderProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "noteHeader");
	}
	
	public Property[] noteTextProp(){
		return Property.toPropertyArray(".class", "Html.DIV", ".className", "noteText");
	}
	
	public Property[] permitInfomationDiv(){
		return Property.concatPropertyArray(div(), ".className", "content shop", ".text", new RegularExpression("^Permit Information.*", false));
	}
	
	public Property[] fixedSpan(String labelRegx){
		return Property.concatPropertyArray(span(), ".text", new RegularExpression(labelRegx, false));
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
			for(IHtmlObject o:objs)
				((IText) o).setText(size);
			Browser.unregister(objs);
		} 
		//		else {	//commented out as it breaks sanity please use setGroupSize(String[] sizes) instead
		//			for(int i=0; i<objs.length; i++){
		//				((IText) objs[i]).setText(size);
		//			}
		//			Browser.unregister(objs);
		//		}
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
		} else throw new ObjectNotFoundException("Object can't be found.");
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

	/**
	 * Table for Person types and numbers 
	 * @return
	 */
	public boolean isPersonTypesExist(){
		return browser.checkHtmlObjectExists(".id", "formSection", ".class", "Html.TABLE");
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
	
	public IHtmlObject[] getPersonTypeTRsObjs(IHtmlObject obj){
		IHtmlObject[] trs=browser.getHtmlObject(".class", "Html.TR", ".className", "personTypeInfo", obj);
		if(trs==null || trs.length<1){
			throw new ObjectNotFoundException("Can't get TRs for person type.");
		}

		Browser.unregister(obj);
		return trs;
	}
	
	public IHtmlObject[] getPersonTypeTRsObjs(int index){
		IHtmlObject[] objs = getPersonTypesDIVObjs(index);
		IHtmlObject[] trs=getPersonTypeTRsObjs(objs[0]);
		Browser.unregister(objs);
		return trs;
	}
	
	public IHtmlObject[] getPersonTypeTRsObjs(){
		return getPersonTypeTRsObjs(0);
	}
	
	public String[] getPersonTypes(int index) {
		List<String> personTypes=new ArrayList<String>();
		IHtmlObject[] objs = getPersonTypesDIVObjs(index);

		IHtmlObject[] trs=getPersonTypeTRsObjs(objs[index]);
		for(int j=0;j<trs.length;j++){
			personTypes.add(trs[j].text().replaceAll("(\\$|\\d+\\.\\d+)", "").trim());
		}

		Browser.unregister(trs, objs);
		return personTypes.toArray(new String[0]);
	}
	
	public String[] getPersonTypes() {
		return getPersonTypes(0);
	}
	
	/**
	 * Get person type object which is with quantity info
	 * @return
	 */
	public IHtmlObject[] getPersonTypeObjectsWithQuantity(){
		List<IHtmlObject> personTypeInfoObjectsList = new ArrayList<IHtmlObject>(Arrays.asList(this.getPersonTypeTRsObjs()));
		//		List<Integer> removedObjsIndex = new ArrayList<Integer>();
		List<IHtmlObject> returnPersonTypeInfoObjectsList = new ArrayList<IHtmlObject>();

		IHtmlObject[] personQtyObjects;
		for(int i=0; i<personTypeInfoObjectsList.size(); i++){
			personQtyObjects = browser.getHtmlObject(".id", "qtyPersonsId", personTypeInfoObjectsList.get(i));
			if(personQtyObjects==null||personQtyObjects.length<1){
				throw new ObjectNotFoundException("Can't find TRs for person Qty objects.");
			}
			//			if(StringUtil.isEmpty(personQtyObjects[0].getProperty(".value").trim())){
			//				removedObjsIndex.add(i);
			//			}
			Browser.unregister(personQtyObjects);
		}

		for(int i=0; i<personTypeInfoObjectsList.size(); i++){
			//			if(!removedObjsIndex.toString().contains(String.valueOf(i))){
			returnPersonTypeInfoObjectsList.add(personTypeInfoObjectsList.get(i));
			//			}
		}
		return (IHtmlObject[]) returnPersonTypeInfoObjectsList.toArray(new IHtmlObject[0]);
	}

	public IHtmlObject[] getPersonTypeRateObjs(IHtmlObject topObj){
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(".id", "ratePerPerson"), topObj);
		if(null==objs || objs.length<1){
			throw new ObjectNotFoundException("Person type rate amount object can't be found.");
		}

		return objs;
	}

	/**
	 * Get person rate info for specific person types
	 * @param personTypes
	 * @return
	 */
	public BigDecimal[] getPersonRateAmounts(String[] personTypes){
		this.setPersonNumsPersonType(personTypes, null);
		IHtmlObject[] trs=this.getPersonTypeObjectsWithQuantity();

		if(personTypes.length!=trs.length){
			throw new ErrorOnPageException(personTypes.length+" person type should be with quantity, but actual the object with quantity is "+trs.length);
		}

		List<BigDecimal> personRateAmounts=new ArrayList<BigDecimal>();
		IHtmlObject[] objs = null;
		String rateAmount = "";
		for(int j=0;j<trs.length;j++){//Adult/Youth $ 17.07
			objs = this.getPersonTypeRateObjs(trs[j]);
			rateAmount = objs[0].text();
			if(rateAmount.contains("Multi")){
				personRateAmounts = new ArrayList<BigDecimal>(Arrays.asList(this.getPersonTotalRateAmounts(personTypes)));
				break;
			}else{
				personRateAmounts.add(new BigDecimal(rateAmount.split("\\$")[1].trim()));
			}
		}
		Browser.unregister(trs);
		return personRateAmounts.toArray(new BigDecimal[0]);
	}

	/**
	 * Get person total rate info for specific person types
	 * @param personTypes
	 * @return
	 */
	public BigDecimal[] getPersonTotalRateAmounts(String[] personTypes){
		List<BigDecimal> personTotalRateAmounts=new ArrayList<BigDecimal>();
		IHtmlObject[] trs=this.getPersonTypeObjectsWithQuantity();

		if(personTypes.length!=trs.length){
			throw new ErrorOnPageException("The length of person types is different from actual and expected.",
					String.valueOf(personTypes.length), String.valueOf(trs.length));
		}

		for(int i=0; i<personTypes.length; i++){
			IHtmlObject[] tds = browser.getTextField(perPersonTypeTotalPriceProp, trs[i]);
			if(tds==null || tds.length<1){
				throw new ObjectNotFoundException("Can't get TDs for person type --- "+personTypes[i]);
			}

			personTotalRateAmounts.add(new BigDecimal(tds[0].text().split("\\$")[1].trim()));
			Browser.unregister(tds);
		}

		Browser.unregister(trs);
		return personTotalRateAmounts.toArray(new BigDecimal[0]);
	}

	/**
	 * Get sub total amount for person type
	 * @return
	 */
	public BigDecimal getSubTotalFeeAmount(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".id", "calcTotal");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't get subtotal info object.");
		}

		String subTotalAmount = objs[0].text().split("\\$")[1].trim();

		Browser.unregister(objs);
		return new BigDecimal(subTotalAmount);
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

		for (int i = 0; i < stockTypes.length; i++) {//Stock types from method parameter
			for (int j = 0; j < stocksTypes.length; j++) {//Stock types from UI
				if (stocksTypes[j].equalsIgnoreCase(stockTypes[i])) {
					browser.setTextField(".id", "qtyStocksId", stockNums[j],	i);
					break;
				}
			}
		}
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

	public boolean isEmergencyContract(){
		return browser.checkHtmlObjectExists(".name", "emergencyContactFirstName");		
	}

	/**
	 * Verify Emergency Contract label exists
	 * @param emergencyContractLabel
	 * @return
	 */
	public void verifyEmergencyContractLabelExist(String emergencyContractLabel){
		Property[] p = Property.toPropertyArray(".class", "Html.TR", ".text", 
				new RegularExpression("^"+emergencyContractLabel.replace("(", "\\(").replace(")", "\\)")+".*", false));
		if(!MiscFunctions.compareResult("Emergency Contract Label exist", true, browser.checkHtmlObjectExists(p))){
			throw new ErrorOnPageException("Whether Emergency contract label exists or not is wrong!");
		}
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
		this.setEmergencyContractPhone( emergencyContracts.getPhones());
	}

	/**
	 * Set Emergency contract elements(FName, LName, Phone)
	 * @param emergencyContractLable
	 * @param emergencyContractElementClassName
	 * @param emergencyContractElementValues
	 */
	private void setEmergencyContractElements(String emergencyContractElementClassName, String[] emergencyContractElementValues){
		logger.info("Set emergency contract element - "+emergencyContractElementClassName);
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

	public void setEmergencyContractFName(String[] emergencyContractFName){
		this.setEmergencyContractElements("emergencyContactFirstName", emergencyContractFName);
	}
	public void setEmergencyContractLName(String[] emergencyContractLName){
		this.setEmergencyContractElements("emergencyContactLastName", emergencyContractLName);
	}
	public void setEmergencyContractPhone(String[] emergencyContractPhone){
		this.setEmergencyContractElements("emergencyContactPhone", emergencyContractPhone);
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
	 * Check issue permit check box.
	 */
	public void selectIssuePermitCheckBox() {
		browser.selectCheckBox(".name", "issuePermit");
	}

	/**
	 * Select issue to item by index.
	 * 
	 * @param index
	 *            - index of item
	 */
	public void selectIssuedTo(int index) {
		browser.selectDropdownList(".id", "issuedtoid", index);
	}

	/**
	 * Select issue to by item value.
	 * 
	 * @param item
	 *            - item value
	 */
	public void selectIssuedTo(String item) {
		if (item == null || item.length() < 1)
			selectIssuedTo(1);
		else
			browser.selectDropdownList(".id", "issuedtoid", item);
	}

	/**
	 * Fill in actual entry date.
	 * 
	 * @param date
	 *            - entry date
	 */
	public void setActualEntryDate(String date) {
		if (date == null || date.length() < 1)
			date = DateFunctions.getDateStamp(false);

		browser.setTextField(".id", "actualentrydateid", date);
	}

	/**
	 * Fill in vechicle plate number, if null, set to QA TESTER.
	 * 
	 * @param plate
	 *            - plate number
	 */
	public void setVechiclePlateNumber(String plate) {
		if (plate == null || plate.length() < 1)
			plate = "QA TESTER";

		browser.setTextField(".name", "vehiclePlateNum", plate);
	}

	/**
	 * Check agreement check box.
	 */
	public void selectAgreementAccept() {
		browser.selectCheckBox(".name", "agreementAccepted");
	}

	/** Click on button to go to shopping cart page. */
	public void clickContinueToShoppingCart() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "continueshop");
	}

	/**
	 * click 'Confirm Changes' to persist any change for an existing order
	 */
	public void clickConfirmChanges() {
		browser.clickGuiObject(".class", "Html.BUTTON", ".id", "continueshop");
	}

	/** Click on Find Group Leader link. */
	public void clickFindGroupLeader() {
		browser
		.clickGuiObject(".class", "Html.A", ".text",
				"Find Group Leader");
	}

	/** Click on Create Group Leader link. */
	public void clickCreateGroupLeader() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Create Group Leader");
	}

	public void refreshPage(){
		browser.clickGuiObject(".class", "Html.TD", ".text", new RegularExpression("^Group Leader.*", false));
		//		browser.clickGuiObject(".class", "Html.SPAN", ".text", new RegularExpression("^Exit Date(\\*)?$", false));
		Browser.sleep(10);
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
			throw new ErrorOnPageException("Exit Date displayed in 'Group Size' section is NOT sunchronized.");
		}

		Browser.unregister(objs);
	}

	/**
	 * Fill in all order details fields.
	 * 
	 * @param permit
	 *            - permit info data
	 * @throws ItemNotFoundException
	 */
	public void setupOrderDetails(PermitInfo permit) throws ItemNotFoundException {
		//TODO
		if(!StringUtil.isEmpty(permit.guideFirstName)){
			this.setGuideFirstName(permit.guideFirstName);
			this.setGuideLastName(permit.guideLastName);
			this.setGuideCardNum(permit.cardNum);
		}

		if(this.checkGuideTripRadioExist() && permit.isGuideTrip){
			this.selectGuideYes();
			if(!StringUtil.isEmpty(permit.parkId) && MiscFunctions.isPermitNonCommercialCheckBoxExisting(permit.parkId)){
				this.selectNonCommercialUseTypeAgreement();
			}
		}else {
			if(this.checkGuideTripDropDownListExist()){
				this.selectGuideTripOption(permit.typeOfGuidedTrip);
			}
		}

		if (isNonProfitOrganizationExist() && permit.organization.length() > 0) {
			checkNonProfitOrganization();
			setNonProfitOrganizationName(permit.organization);
			setNonProfitOrganizationTaxID(permit.organizationTaxID);
		}

		if(this.isTakeOutPointExist()&&!StringUtil.isEmpty(permit.takeOutpoint)){
			this.selectTakeOutPoint(permit.takeOutpoint);
		}

		if(this.isLaunchPointExist() && !StringUtil.isEmpty(permit.launchPoint)){

			this.selectLaunchPoint(permit.launchPoint);
		}

		if(this.isTrailheadDDListExist()){
			if(!StringUtil.isEmpty(permit.trailhead) && this.getTrailHeads().contains(permit.trailhead)){
				this.selectTrailHead(permit.trailhead);
			}else{
				this.selectTrailHead(1);
			}
		}

		if(this.isTravelMethodExist()&&!StringUtil.isEmpty(permit.travelMethod)){
			this.selectTravelMethod(permit.travelMethod);
		}else{
			this.selectTravelMethod(1);
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

		//		if (StringUtil.isEmpty(permit.exitDate)	|| DateFunctions.compareDates(permit.actualEntryDate,permit.exitDate) < 0) {
		//			if (StringUtil.isEmpty(permit.actualEntryDate)) {
		//				permit.actualEntryDate = permit.entryDate;
		//				logger.debug("Set actual entry date="+permit.actualEntryDate);
		//			}
		//			permit.exitDate = DateFunctions.getDateAfterGivenDay(permit.actualEntryDate, 1);
		//			logger.debug("Set exit date="+permit.exitDate);
		//		}

		//If the "Exit Date" is available, set up the value
		if(this.checkExitDateExist()){
			this.clickExitDate();
			browser.dismissDialogs(1, AlertDialogPage.getInstance(), 1);
			browser.inputKey(KeyInput.get(KeyInput.BACKSPACE));

			this.setExitDate(permit.exitDate);
			browser.dismissDialogs(1, AlertDialogPage.getInstance(), 1);
			waitLoading();

			//			this.dismissCalendar();  //updated by pzhu
			//			this.waitExists();
			this.refreshPage();

			if(this.checkExitDateForGroupSizeExist()){
				this.waitForExitDateSync();

				if(null!=permit.entryDateUnderGroupSize && permit.entryDateUnderGroupSize.length>0 &&
						null!=permit.NumOfDaysUnderGroupSize && permit.NumOfDaysUnderGroupSize.length>0){
					this.setEntryDateAndNumOfDaysUnderGroupSize(permit.entryDateUnderGroupSize, permit.NumOfDaysUnderGroupSize);
				}
				//			this.waitExists();
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
			int tripItineraryRowCount = this.getTripItineraryRows();
			if(tripItineraryRowCount==1){
				if(!StringUtil.isEmpty(permit.tripItineraryLocation)){
					this.selectTripItineraryLoc(permit.tripItineraryLocation);
				}else{
					this.selectTripItineraryLoc(1);//Select the second option
				}
			}else{
				if(permit.tripItineraryLocations!=null&&permit.tripItineraryLocations.length>0){
					this.selectTripItineraryLocs(permit.tripItineraryLocations);
				}else{
					this.selectTripItineraryLocs(1);//For all drop down list, select the second one
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
//						} else if(isGroupSizeDIV()){//has entry date and #of days under group size div
//							this.setEntryDateUnderGroupSize(permit.entryDate, 0);
//							this.setNumOfDaysUnderGroupSize(permit.numOfDays, 0);
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
		if(this.isEmergencyContract() && permit.emergencyContrats != null){
			this.setEmergencyContractsInfo(permit.emergencyContrats);
		}

		//Select issue station
		if(isDeliveryMethodDDListExist() && getDeliveryMethod().equalsIgnoreCase("Online")){
              logger.info("No permit issue station when delivery method is Online.");
		}else if(this.isPermitIssuingStation() && this.isPermitIssuingStationDisplayed()){
				if(!StringUtil.isEmpty(permit.issueStation)){
					this.selectPermitIssuingStation(permit.issueStation);
				}else if(this.getNumOfPermitIssuingStationOptions()==1){
					this.selectPermitIssuingStation(0);
				}else{
					this.selectPermitIssuingStation(1);
				}
			}

		this.selectAgreementAccept();
	}

	public String getDeliveryMethod() {
		return browser.getDropdownListValue(".id", "permitdeliverymethodid");
	}
	
	public boolean isPermitIssuingStation(){
		return browser.checkHtmlObjectExists(".id", "permitissuingstationid");
	}

	public boolean isPermitIssuingStationDisplayed() {
		boolean result;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".id", "issuing_station_row");
		if (objs.length < 1) {
			result = false;
		} else {
			String display = objs[0].getAttributeValue("style");
			result = display == null ? false : !display.equalsIgnoreCase("display: none");
		}
		Browser.unregister(objs);
		return result;
	}

	public void selectPermitIssuingStation(String issueStation){
		browser.selectDropdownList(".id", "permitissuingstationid", issueStation);
	}

	public int getNumOfPermitIssuingStationOptions(){
		return browser.getDropdownElements(".id", "permitissuingstationid").size();
	}



	public void selectPermitIssuingStation(int index){
		browser.selectDropdownList(".id", "permitissuingstationid", index);
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

	private void selectTrailHead(int i) {
		browser.selectDropdownList(".id", "trailheadid", i);
	}

	public List<String> getTrailHeads() {
		return browser.getDropdownElements(".id", "trailheadid");

	}

	public void setNumOfGuides(String num) {
		browser.setTextField(".id", "numberofguidesid", num);
	}

	public void setWatercraftNum(String key, String value) {

		IHtmlObject[] tables=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.TR", ".className", "personTypeInfo",".text",new RegularExpression("^"+key+".*",false)));
		if(tables==null ||tables.length<1){
			throw new ObjectNotFoundException("Can't find 'watercraftFormSection' table");
		}
		browser.setTextField(".id", "qtyWaterCraftsId", value,tables[0]);
		Browser.unregister(tables);
	}

	public boolean isLaunchPointExist() {
		return browser.checkHtmlObjectExists(".id", "launchpointid");
	}

	public void selectLaunchPoint(String launchPoint){
		browser.selectDropdownList(".id", "launchpointid", launchPoint);
	}

	public void selectTakeOutPoint(String takeOutpoint) {
		browser.selectDropdownList(".id", "takeoutpointid", takeOutpoint);
	}

	public boolean isTakeOutPointExist() {
		return browser.checkHtmlObjectExists(".id", "takeoutpointid");
	}

	public void selectDeliveryMethod(String deliveryMethod) {
		browser.selectDropdownList(".id", "permitdeliverymethodid", deliveryMethod);
	}

	private boolean isDeliveryMethodDDListExist() {
		return browser.checkHtmlObjectExists(".id", "permitdeliverymethodid");
	}

	public boolean isTrailheadDDListExist() {
		return browser.checkHtmlObjectExists(".id", "trailheadid");
	}

	public void selectTrailHead(String option){
		browser.selectDropdownList(".id", "trailheadid", option);
	}

	public String getTrailHeadValue(){
		return browser.getDropdownListValue(".id", "trailheadid");
	}

	public boolean isTripItineraryExist(){
		return browser.checkHtmlObjectExists(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false));
	}

	public void selectTripItineraryLoc(String option){
		browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), option);
	}

	public void selectTripItineraryLoc(int index){
		browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), index);
	}

	public String getTripItineraryLoc(int index){
		return browser.getDropdownListValue(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), index);
	}

	/**
	 * Get trip itinerary
	 * @param row: 1, 2, 3...
	 * @return
	 */
	public List<String> getTripItinerarys(int row){
		return browser.getDropdownElements(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1),false));
	}

	public void selectTripItineraryLocs(String[] tripItineraryLocs) {
		if (tripItineraryLocs == null || tripItineraryLocs.length < 1)
			return;
		for (int i = 0; i < tripItineraryLocs.length; i++) {
			browser.selectDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false), tripItineraryLocs[i], i);
		}
	}

	public void selectTripItineraryLocs(int index) {
		IHtmlObject[] objs = browser.getDropdownList(".id", new RegularExpression("tripitinerarypoint[0-9]+id",false));

		if(objs==null ||objs.length<1){
			throw new ObjectNotFoundException("'tripitinerarypoint' objects can't be found.");
		}

		for(int i=0; i<objs.length; i++){
			((ISelect) objs[i]).select(index);
		}

		Browser.unregister(objs);
	}

	public boolean isTravelMethodExist(){
		return browser.checkHtmlObjectExists(".id", "travelmethodid");
	}

	public void selectTravelMethod(String option){
		browser.selectDropdownList(".id", "travelmethodid", option);
	}

	private void selectTravelMethod(int i) {
		browser.selectDropdownList(".id", "travelmethodid", i);
	}


	public void setReentryDate(String date) {
		browser.setTextField(".id", "re-entrydateid", date);
	}

	public boolean isCollectPaymentInfo() {
		return browser.checkHtmlObjectExists(".id", "cardTypeId_null");
	}

	public void setExitDate(String date) {
		browser.setTextField(".id", "exitdateid", date);
		new Thread(){
			AlertDialogPage alertPg = AlertDialogPage.getInstance();

			public void run(){
				int i=0;
				alertPg.setDismissible(false);
				while(i<5){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						logger.warn(e.getMessage());
					}
					if(alertPg.exists()){
						alertPg.dismiss();
						break;
					}
					i++;
				}
			};
		}.start();
	}

	public void clickExitDate(){
		browser.clickGuiObject(".id", "exitdateid");
	}

	public void setExitDateToSyncTripItinerary(String exitDate, int tripItineraryRow){
		this.setExitDate(exitDate);
		this.refreshPage();
		this.waitForTripItinerarySync(tripItineraryRow);
	}

	public boolean checkExitDateExist(){
		return browser.checkHtmlObjectExists(".id", "exitdateid");
	}

	public boolean checkExitDateForGroupSizeExist(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".id", "exitDateDivId");
	}

	public boolean checkActualEntryDateExist(){
		return browser.checkHtmlObjectExists(".id", "actualentrydateid");
	}

	public boolean checkGuideTripRadioExist(){
		return browser.checkHtmlObjectExists(".id", "guidedYes");
	}

	public boolean checkGuideTripDropDownListExist(){
		return browser.checkHtmlObjectExists(".id", "typeofguidedtripid");
	}

	public int getNumOfExitDateForGroupSize(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "exitDateDivId");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Exit date object under group size can't be found.");
		}

		int numOfExitDate = objs.length; 

		Browser.unregister(objs);
		return numOfExitDate;
	}

	public void selectGuideTripOption(String guideTripOption){
		browser.selectDropdownList(".id", "typeofguidedtripid", guideTripOption);
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

	public void setGuideFirstName(String guideFirstName) {
		browser.setTextField(".id", "firstnameid", guideFirstName);
	}

	public void setGuideLastName(String guideLastName) {
		browser.setTextField(".id", "lastnameid", guideLastName);
	}

	public void setGuideCardNum(String cardNum) {
		browser.setTextField(".id", "cardnumberid", cardNum);
	}

	public void clickBringingAnimalsYes(){
		browser.clickGuiObject(".id", "bringingAnimals0");
	}

	public void clickBringingAnimalsNo(){
		browser.clickGuiObject(".id", "bringingAnimals1");
	}

	public boolean checkGuideInfoExist(){
		return browser.checkHtmlObjectExists(".id", "guideInfoId");
	}

	public void verifyGuideInfoExist(boolean flag){
		boolean actualExisted = this.checkGuideInfoExist();
		if(actualExisted!=flag){
			throw new ErrorOnDataException("Guide info should "+(flag?"":"not")+" be existed.");
		}
	}
	/**
	 * Check the field is group size
	 * 
	 * @return
	 */
	public boolean isGroupSizeTextField() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", new RegularExpression("groupsizeid.*",false));
	}

	public boolean isGroupSizeDIV() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV",
				".id", "groupSizeDiv");
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
	 * Fill in lottery details info.
	 * 
	 * @param lottery
	 *            - permit info data
	 * @throws ItemNotFoundException
	 */
	public void setupLotteryDetails(PermitInfo lottery) throws ItemNotFoundException {
		if(this.isPersonTypesExist()){
			this.setPersonNumsPersonType(lottery.personTypes, lottery.personNums);
		}else{
			this.setGroupSize(lottery.groupSize);
		}
		this.setWatercraftNum(lottery.waterCraftNum);
		this.setAlternateLeaders(lottery.alterLeaders);
		this.selectPermitType();
		this.refreshPage();
		this.selectLotteryEntrance();
		this.selectLotteryPermitType();
		this.waitLoading();
		this.setEntryDate(lottery.actualEntryDate);
		this.setExitDate(DateFunctions.getDateAfterGivenDay(lottery.actualEntryDate, 1));
		this.selectExitPoint(lottery.exitPoint);
		if(this.isEmergencyContract() && lottery.emergencyContrats != null){
			this.setEmergencyContractsInfo(lottery.emergencyContrats);
		}

		if(isLotteryPreferredChoiced()){
			this.setupLotteryPreferredChoice(lottery);
		}
		if(isLotteryAlternativeChoiced()){
			this.setupLotteryAlternativeChoice(lottery);
		}

		if(this.isTripItineraryExist()){
			int tripItineraryRowCount = this.getTripItineraryRows();
			if(tripItineraryRowCount==1){
				if(!StringUtil.isEmpty(lottery.tripItineraryLocation)){
					this.selectTripItineraryLoc(lottery.tripItineraryLocation);
				}else{
					this.selectTripItineraryLoc(1);//Select the second option
				}
			}else{
				if(lottery.tripItineraryLocations!=null&&lottery.tripItineraryLocations.length>0){
					this.selectTripItineraryLocs(lottery.tripItineraryLocations);
				}else{
					this.selectTripItineraryLocs(1);//For all drop down list, select the second one
				}
			}
		}
		this.selectAgreementAccept();
	}

	public void setupLotteryPreferredChoice(PermitInfo lottery){
		if(isLotteryPermitTypeExist() && !StringUtil.isEmpty(lottery.permitType)){
			this.selectLotteryPermitType(lottery.permitType);
			this.refreshPage();
		}
		if(isLotteryEntranceExist() && !StringUtil.isEmpty(lottery.entrance)){
			this.selectLotteryEntrance(lottery.entrance);
			this.refreshPage();
		}
		if(isLotteryEntryDateExist() && !StringUtil.isEmpty(lottery.entryDate)){
			this.setLotteryEntryDate(lottery.entryDate);
			this.refreshPage();
		}
		if(isLotteryTrailHeadExist() && !StringUtil.isEmpty(lottery.trailhead)){
			this.selectLotteryTrailHead(lottery.trailhead);
		}
		if(isLotteryGroupSizeExist() && !StringUtil.isEmpty(lottery.groupSize)){
			this.setLotteryGroupSize(lottery.groupSize);
		}
		if(isLotteryExitPointExist() && !StringUtil.isEmpty(lottery.exitPoint)){
			this.selectLotteryExitPoint(lottery.exitPoint);
		}
		if(isLotteryExitDateExist() && !StringUtil.isEmpty(lottery.exitDate)){
			if(!this.checkExitDateReadOnly(false)){
				this.setLotteryExitDate(lottery.exitDate);
				this.refreshPage();
			}
		}
	}

	public void setupLotteryAlternativeChoice(PermitInfo lottery){
		if(!StringUtil.isEmpty(lottery.alternativePermitType)){
			if(isLotteryAlternativePermitTypeExist() && !StringUtil.isEmpty(lottery.alternativePermitType)){
				this.selectLotteryAlternativePermitType(lottery.alternativePermitType);
				this.refreshPage();
			}
			if(isLotteryAlternativeEntranceExist() && !StringUtil.isEmpty(lottery.alternativeEntrance)){
				this.selectLotteryAlternativeEntrance(lottery.alternativeEntrance);
				this.refreshPage();
			}
			if(isLotteryAlternativeEntryDateExist() && !StringUtil.isEmpty(lottery.alternativeEntryDate)){
				this.setLotteryAlternativeEntryDate(lottery.alternativeEntryDate);
				this.refreshPage();
			}
			if(isLotteryAlternativeTrailHeadExist() && !StringUtil.isEmpty(lottery.alternativeTrailHead)){
				this.setLotteryAlternativeTrailHead(lottery.trailhead);
			}
			if(isLotteryAlternativeGroupSizeExist() && !StringUtil.isEmpty(lottery.alternativeGroupSize)){
				this.setLotteryAlternativeGroupSize(lottery.alternativeGroupSize);
			}
			if(isLotteryAlternativeExitPointExist() && !StringUtil.isEmpty(lottery.alternativeExitPoint)){
				this.selectLotteryAlternativeExitPoint(lottery.alternativeExitPoint);
			}
			if(isLotteryAlternativeExitDateExist() && !StringUtil.isEmpty(lottery.alternativeExitDate)){
				if(!this.checkExitDateReadOnly(true)){
					this.setLotteryAlternativeExitDate(lottery.alternativeExitDate);
					this.refreshPage();
				}
			}
		}
	}

	public boolean checkAlternativeChoiceExist(){
		return browser.checkHtmlObjectExists(".id", "altchoice1");
	}

	/** Check agreement check box. */
	public void selectAgreementCheckbox() {
		browser.selectCheckBox(".id", "aggrAcc");
	}

	/**
	 * This method will set entry date for all entryDate fields
	 * 
	 * @param date
	 */
	public void setEntryDate(String date) {
		IHtmlObject[] objs = browser.getTextField(".name", "entryDate");
		for (int i = 0; i < objs.length; i++)
			((IText) objs[i]).setText(date);
		Browser.unregister(objs);
	}

	public boolean isLotteryPreferredChoiced(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Preferred Choice.*", false));
		return browser.checkHtmlObjectExists(p1);
	}

	public IHtmlObject[] getPreferredChoiceTable(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Preferred Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Preferred Choice' table can't be found.");
		}
		return objs;
	}
	public IHtmlObject[] getLotterPreferredChoiceDDLObjs(String namePropValue){
		IHtmlObject[] table = this.getPreferredChoiceTable();
		IHtmlObject[] objs = browser.getDropdownList(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Preferred choice drop down list objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public IHtmlObject[] getLotterPreferredChoiceTextObjs(String namePropValue){
		IHtmlObject[] table = this.getPreferredChoiceTable();
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Preferred choice text objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public boolean isLotteryPreferredChoiceObjsExist(String namePropValue){
		IHtmlObject[] table = this.getPreferredChoiceTable();
		boolean returnValue = browser.checkHtmlObjectExists(".name", namePropValue, table[0]);
		Browser.unregister(table);
		return returnValue;
	}
	public void setLotteryPreferredChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs(namePropValue);
		((IText) objs[0]).setText(elementvalue);
		Browser.unregister(objs);
	}
	public void selectLotteryPreferredChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs(namePropValue);
		((ISelect) objs[0]).select(elementvalue);
		Browser.unregister(objs);
	}
	public String getLotterPreferredChoiceDDLElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs(namePropValue);
		String elementValue = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);
		return elementValue;
	}
	public String getLotterPreferredChoiceTextElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs(namePropValue);
		String elementValue = ((IText) objs[0]).getText();
		Browser.unregister(objs);
		return elementValue;
	}

	public void selectLotteryPermitType(String permitType){
		this.selectLotteryPreferredChoiceElement("permitType", permitType);
	}
	public boolean isLotteryPermitTypeExist(){
		return isLotteryPreferredChoiceObjsExist("permitType");
	}
	public void selectLotteryEntrance(String entrance){
		this.selectLotteryPreferredChoiceElement("entrance", entrance);
	}
	public boolean isLotteryEntranceExist(){
		return isLotteryPreferredChoiceObjsExist("entrance");
	}
	public void setLotteryEntryDate(String entryDate){
		this.setLotteryPreferredChoiceElement("entryDate", entryDate);
	}
	public boolean isLotteryEntryDateExist(){
		return isLotteryPreferredChoiceObjsExist("entryDate");
	}
	public void selectLotteryTrailHead(String trailHead){
		this.selectLotteryPreferredChoiceElement("trailHeadID", trailHead);
	}
	public boolean isLotteryTrailHeadExist(){
		return isLotteryPreferredChoiceObjsExist("trailHeadID");
	}
	public void setLotteryGroupSize(String groupSize){
		this.setLotteryPreferredChoiceElement("groupSizePref", groupSize);
	}
	public boolean isLotteryGroupSizeExist(){
		return isLotteryPreferredChoiceObjsExist("groupSizePref");
	}
	public void selectLotteryExitPoint(String exitPoint){
		this.selectLotteryPreferredChoiceElement("exitPoint", exitPoint);
	}
	public boolean isLotteryExitPointExist(){
		return isLotteryPreferredChoiceObjsExist("exitPoint");
	}
	public void setLotteryExitDate(String exitDate){
		this.setLotteryPreferredChoiceElement("exitDate", exitDate);
	}
	public boolean isLotteryExitDateExist(){
		return isLotteryPreferredChoiceObjsExist("exitDate");
	}
	public String getLotteryExitDate(){
		return this.getLotterPreferredChoiceTextElement("exitDate");
	}

	public String getExitDate(){
		String value = browser.getTextFieldValue(".id", "exitdateid");
		new Thread(){
			AlertDialogPage alertPg = AlertDialogPage.getInstance();

			public void run(){
				int i=0;
				alertPg.setDismissible(false);
				while(i<5){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						logger.warn(e.getMessage());
					}
					if(alertPg.exists()){
						alertPg.dismiss();
						break;
					}
					i++;
				}
			};
		}.start();
		return value;
	}

	public List<String> getLotteryExitPoints(){
		IHtmlObject[] objs = this.getLotterPreferredChoiceDDLObjs("exitPoint");
		List<String> elementValues = ((ISelect) objs[0]).getAllOptions();
		Browser.unregister(objs);
		return elementValues;
	}
	public void verifyLotteryExitPoints(List<String> exitPoints){
		List<String> exitPointFromUI = this.getLotteryExitPoints();
		MiscFunctions.compareStringList("Exit points", exitPoints, exitPointFromUI);
	}
	public boolean checkLotteryExitDateReadOnly(){
		IHtmlObject[] objs = this.getLotterPreferredChoiceTextObjs("exitDate");
		IText text = (IText)objs[0];
		boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}

	public void verifyLotteryExitDateReadOnly(){
		if(!this.checkLotteryExitDateReadOnly()){
			throw new ErrorOnDataException("Failed to verify 'Preferred Choice' exit date is read only.");
		}
		logger.info("Successfully verify 'Preferred Choice' exit date is read only.");
	}

	public boolean isLotteryAlternativeChoiced(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Alternative Choice.*", false));
		return browser.checkHtmlObjectExists(p1);
	}

	public IHtmlObject[] getAlternativeChoiceTable(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Alternative Choice.*", false));
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Alternative Choice' table can't be found.");
		}
		return objs;
	}
	public IHtmlObject[] getLotterAlternativeDDLChoiceObjs(String namePropValue){
		IHtmlObject[] table = this.getAlternativeChoiceTable();
		IHtmlObject[] objs = browser.getDropdownList(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Alternative choice drop down list objects can't be found.");
		}
		Browser.unregister(table);
		return objs;
	}
	public IHtmlObject[] getLotterAlternativeChoiceTextObjs(String namePropValue){
		IHtmlObject[] table = this.getAlternativeChoiceTable();
		IHtmlObject[] objs = browser.getTextField(Property.toPropertyArray(".name", namePropValue), table[0]);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Lottery Alternative choice text objects can't be found.");
		}
		return objs;
	}
	public boolean isLotteryAlternativeChoiceObjsExist(String namePropValue){
		IHtmlObject[] table = this.getAlternativeChoiceTable();
		boolean returnValue = browser.checkHtmlObjectExists(".name", namePropValue, table[0]);
		Browser.unregister(table);
		return returnValue;
	}
	public void setLotteryAlternativeChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs(namePropValue);
		((IText) objs[0]).setText(elementvalue);
		Browser.unregister(objs);
	}
	public void selectLotteryAlternativeChoiceElement(String namePropValue, String elementvalue){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs(namePropValue);
		((ISelect) objs[0]).select(elementvalue);
		Browser.unregister(objs);
	}
	public String getLotteryAlternativeChoiceDDLElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs(namePropValue);
		String elementValue = ((ISelect) objs[0]).getSelectedText();
		Browser.unregister(objs);
		return elementValue;
	}
	public String getLotteryAlternativeChoiceTextElement(String namePropValue){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs(namePropValue);
		String elementValue = ((IText) objs[0]).getText();
		Browser.unregister(objs);
		return elementValue;
	}

	public void selectLotteryAlternativePermitType(String permitType){
		this.selectLotteryAlternativeChoiceElement("permitType", permitType);
	}
	public void waitForLotteryAlternativeEntranceSync(String entrance){
		browser.searchObjectWaitExists(".id", "entranceid1", ".text", new RegularExpression(".*"+entrance+".*", false));
	}
	public void selectLotteryAlternativePermitTypeToSyncEntrance(String permitType, String entrance){
		this.selectLotteryAlternativePermitType(permitType);
		this.waitForLotteryAlternativeEntranceSync(entrance);
	}
	public boolean isLotteryAlternativePermitTypeExist(){
		return isLotteryAlternativeChoiceObjsExist("permitType");
	}
	public void selectLotteryAlternativeEntrance(String entrance){
		this.selectLotteryAlternativeChoiceElement("entrance", entrance);
	}
	public boolean isLotteryAlternativeEntranceExist(){
		return isLotteryAlternativeChoiceObjsExist("entrance");
	}
	public void setLotteryAlternativeEntryDate(String entryDate){
		this.setLotteryAlternativeChoiceElement("entryDate", entryDate);
	}
	public boolean isLotteryAlternativeEntryDateExist(){
		return isLotteryAlternativeChoiceObjsExist("entryDate");
	}
	public void setLotteryAlternativeTrailHead(String trailHead){
		this.selectLotteryAlternativeChoiceElement("trailHeadID", trailHead);
	}
	public boolean isLotteryAlternativeTrailHeadExist(){
		return isLotteryAlternativeChoiceObjsExist("trailHeadID");
	}
	public void setLotteryAlternativeGroupSize(String groupSize){
		this.setLotteryAlternativeChoiceElement("groupSizePref", groupSize);
	}
	public boolean isLotteryAlternativeGroupSizeExist(){
		return isLotteryAlternativeChoiceObjsExist("groupSizePref");
	}
	public void selectLotteryAlternativeExitPoint(String exitPoint){
		this.selectLotteryAlternativeChoiceElement("exitPoint", exitPoint);
	}
	public boolean isLotteryAlternativeExitPointExist(){
		return isLotteryAlternativeChoiceObjsExist("exitPoint");
	}
	public void setLotteryAlternativeExitDate(String exitDate){
		this.setLotteryAlternativeChoiceElement("exitDate", exitDate);
	}
	public boolean isLotteryAlternativeExitDateExist(){
		return isLotteryAlternativeChoiceObjsExist("exitDate");
	}
	public String getLotteryAlternativeExitDate(){
		return this.getLotteryAlternativeChoiceTextElement("exitDate");
	}
	public boolean checkLotteryAlternativeExitDateReadOnly(){
		IHtmlObject[] objs = this.getLotterAlternativeChoiceTextObjs("exitDate");
		IText text = (IText)objs[0];
		boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}

	public void verifyLotteryAlternativeExitDateReadOnly(){
		if(!this.checkLotteryAlternativeExitDateReadOnly()){
			throw new ErrorOnDataException("Failed to verify 'Alternative Choice 1' exit date is read only.");
		}
		logger.info("Successfully verify 'Alternative Choice 1' exit date is read only.");
	}

	public List<String> getLotteryAlternativeExitPoints(){
		IHtmlObject[] objs = this.getLotterAlternativeDDLChoiceObjs("exitPoint");
		List<String> elementValues = ((ISelect) objs[0]).getAllOptions();
		Browser.unregister(objs);
		return elementValues;
	}

	public void verifyLotteryAlternativeExitPoints(List<String> exitPoints){
		List<String> exitPointFromUI = this.getLotteryAlternativeExitPoints();
		MiscFunctions.compareStringList("Lottery alternative Exit points", exitPoints, exitPointFromUI);
	}

	public void setPreferredExitDateToSyncTripItinerary(String exitDate, int tripItineraryRow){
		logger.info("Setup 'Preferred Choice' exit date to sync trip itinerary.");
		this.setLotteryExitDate(exitDate);
		this.refreshPage();
		this.waitForTripItinerarySync(tripItineraryRow);
	}
	public boolean checkExitDateReadOnly(boolean alternativeChoice){
		IHtmlObject[] objs = browser.getTextField(".name", "exitDate");
		if(objs.length<1)
			throw new ObjectNotFoundException("The object with name: ('exitDate') can't be found.");

		IText text  = null;
		if(!alternativeChoice){
			text = (IText)objs[0];
		}else{
			if(objs.length>=2){
				text = (IText)objs[1];
			}else throw new ObjectNotFoundException("Alternative Choice object(the value of .name " +
					"property is:'exitDate') can't be found.");
		}
		boolean readOnly = text.readOnly();
		Browser.unregister(objs);
		return readOnly;
	}

	/**
	 * The 'Entry Date' in 'Group Size' section
	 * @return
	 */
	public String getEntryDateUnderGroupSize(int index){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".className", "entryDateInfo", ".id", "entryRowId");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'entryDateInfo' object can't be found.");
		}
		Property[] p2 = Property.toPropertyArray(".id", "entrydateid");
		String entryDate = browser.getTextFieldValue(p2, objs[index]);

		Browser.unregister(objs);
		return entryDate;
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

	/**
	 * The '# of Days' in 'Group Size' section
	 * @return
	 */
	public String getNumOfDaysUnderGroupSize(int index){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".className", "entryDateInfo", ".id", "entryRowId");
		IHtmlObject[] objs = browser.getHtmlObject(p1);
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'entryDateInfo' object can't be found.");
		}
		Property[] p2 = Property.toPropertyArray(".id", "#ofdaysid");
		String numOfDays = browser.getTextFieldValue(p2, objs[index]);

		Browser.unregister(objs);
		return numOfDays;
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

	/**
	 * The 'Exit Date' in 'Group Size' section
	 * @return
	 */
	public String getExitDateUnderGroupSize(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV", ".id", "exitDateDivId");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'Exit Date' object can't be found.");
		}

		String exitDate = objs[index].text();

		Browser.unregister(objs);
		return exitDate;
	}

	/**
	 * Select all permit type for first item, or select all the same as the
	 * value has selected by first permit field if not 'Please Select'.
	 */
	public void selectPermitType() {
		IHtmlObject[] objs = browser.getDropdownList(".name", "permitType");
		if (objs.length > 0) {
			//			String text = objs[0].getProperty(".value").toString();
			String text = ((ISelect) objs[0]).getSelectedText();
			if (text.equalsIgnoreCase("- Please Select -")) {
				for (int i = 0; i < objs.length; i++)
					((ISelect) objs[i]).select(1);
			} else {
				for (int i = 1; i < objs.length; i++)
					((ISelect) objs[i]).select(text);
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Select lottery Entrance type by index.
	 * 
	 * @param index
	 *            - index of permit Entrance items
	 */
	public void selectLotteryEntrance(int index) {
		browser.selectDropdownList(".name", "entrance", index);
	}

	/**
	 * Select all lottery entrance for first item, or select all the same as the
	 * value has selected by first lottery entrance field if not 'Please
	 * Select'.
	 */
	public void selectLotteryEntrance() {
		IHtmlObject[] objs = browser.getDropdownList(".name", "entrance");
		if (objs.length > 0) {
			String text = ((ISelect) objs[0]).getSelectedText();
			if (text.equalsIgnoreCase("- Please Select -")) {
				for (int i = 0; i < objs.length; i++)
					((ISelect) objs[i]).select(1);
			} else {
				for (int i = 1; i < objs.length; i++)
					((ISelect) objs[i]).select(text);
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Select lottery permit type by index.
	 * 
	 * @param index
	 *            - index of permit type items
	 */
	public void selectLotteryPermitType(int index) {
		browser.selectDropdownList(".name", "permitType", index);
	}

	/**
	 * Select all lottery type for first item, or select all the same as the
	 * value has selected by first lottery type field if not 'Please Select'.
	 */
	public void selectLotteryPermitType() {
		IHtmlObject[] objs = browser.getDropdownList(".name", "permitType");
		if (objs.length > 0) {
			//			String text = objs[0].getProperty(".value").toString();
			String text = ((ISelect) objs[0]).getSelectedText();
			if (text.equalsIgnoreCase("- Please Select -")) {
				for (int i = 0; i < objs.length; i++)
					((ISelect) objs[i]).select(1);
			} else {
				for (int i = 1; i < objs.length; i++)
					((ISelect) objs[i]).select(text);
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Retrieve the maximum group size.
	 * 
	 * @return - maximum group size
	 */
	public int getMaxGroupSize() {

		String text = this.getTotalGroupSizeLabel();
		String[] maxs = RegularExpression.getMatches(text, "\\(Max ?: ?\\d+\\)");
		int maxSize = Integer.parseInt(RegularExpression.getMatches(maxs[0],
				"[0-9]+")[0]);

		return maxSize;
	}

	/**
	 * Get fee rate type in group size section
	 * @return
	 */
	public String getFeeRateTypeInGroupSizeSection(){
		Property[] p1=Property.toPropertyArray(".class", "Html.TD", ".id", "addEntryDateLink");
		Property[] p2=Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("\\* Rate.*",false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find 'Rate' DIV");
		}

		String value=objs[0].text().split("\\(")[1].replace(")", "").trim();
		Browser.unregister(objs);
		return value;
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
	 * Verify whether the payment field is available to fill in.
	 * 
	 * @return
	 */
	public boolean needSetupPayment() {
		boolean toReturn = false;
		IHtmlObject[] objs = browser.getRadioButton(".id", "payCCOnFile");
		if (objs.length < 1) {
			Browser.unregister(objs);
			objs = browser.getDropdownList(".name", "cardType");
			toReturn = objs.length > 0;
		} else {
			toReturn = objs[0].getProperty(".checked").toString()
					.equalsIgnoreCase("false");
		}
		Browser.unregister(objs);
		return toReturn;
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
	 * Click the 'Remove This Reservation' link to remove the current
	 * reservation from order detail page
	 */
	public void clickRemoveThisRes() {
		browser.clickGuiObject(".href", new RegularExpression(
				"checkedShoppingCartItems", false));
	}

	/**
	 * Retrieve the error or warning message displayed on Permit Order Details
	 * page
	 * 
	 * @return
	 */
	public String getTopErrorMsg() {
		Property[] p1 = Property.toPropertyArray(".id", 
				new RegularExpression("(permitLotteryOrderDetailsForm|permitOrderDetailsForm)", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", "msg error");
		IHtmlObject[] topMsgDiv = browser.getHtmlObject(Property.atList(p1, p2));
		if(topMsgDiv==null ||topMsgDiv.length<1){
			throw new ObjectNotFoundException("Can't find top msg DIV");
		}
		String text = topMsgDiv[0].text();
		Browser.unregister(topMsgDiv);
		return text;
	}

	/**
	 * Verify top error message
	 * @param errorMes_Expected
	 */
	public void verifyTopErrorMes(String errorMes_Expected){
		logger.info("Verify top error message.");
		String errorMes_Actual = this.getTopErrorMsg();
		if(!MiscFunctions.compareResult("Top Error Message", errorMes_Expected.replaceAll("\\s*", ""), errorMes_Actual.replaceAll("\\s*", ""))){
			throw new ErrorOnPageException("Top error message is wrong.");
		}
		logger.info("Successfully verify top error message:"+errorMes_Expected);
	}

	public boolean isTopErrorMsgExist() {
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".className", "msg error");
	}

	public void verifyTopErrorMsgExist(boolean isExist) {
		String msg = isExist ? " " : " NOT ";
		if (isExist != this.isTopErrorMsgExist()) {
			throw new ErrorOnPageException("Top Error message should " + msg + " exist!");
		}
		logger.info("Top error message do " + msg + " exist!");
	}

	public String getPersonTypePerStayErrorMes(){
		Property[] p = Property.toPropertyArray(".class", "Html.DIV", ".id", "personTypePerStayError", ".className", "msg error");
		IHtmlObject[] errorMesgDiv = browser.getHtmlObject(p);
		if(errorMesgDiv==null ||errorMesgDiv.length<1){
			throw new ObjectNotFoundException("Can't find person type per stay error msg DIV");
		}
		String text = errorMesgDiv[0].text();
		Browser.unregister(errorMesgDiv);
		return text;
	}

	/**
	 * Get Emergency Contract error message
	 * @return
	 */
	public String getEmergencyContractErrorMes(){
		logger.info("Get Emergency Contract error message");
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "emergencyContactTable");
		Property[] p2 = Property.toPropertyArray(".class", "Html.DIV", ".className", new RegularExpression("msg error( hidden-phone)?", false)); //"msg error");
		IHtmlObject[] errorMesgDiv = browser.getHtmlObject(Property.atList(p1, p2));
		if(errorMesgDiv==null ||errorMesgDiv.length<1){
			throw new ObjectNotFoundException("Can't find emergency contract error msg DIV");
		}
		String text = errorMesgDiv[0].text();
		Browser.unregister(errorMesgDiv);
		return text;
	}

	/**
	 * Verify Emergency Contract error message
	 * @param errorMes_Expected
	 */
	public void verifyEmergencyContractErrorMes(String errorMes_Expected){
		String errorMes_Actual = this.getEmergencyContractErrorMes();
		if(!MiscFunctions.compareResult("Emergency Contract error message", errorMes_Expected, errorMes_Actual)){
			throw new ErrorOnPageException("The Emergency Contract error message is wrong!");
		}
		logger.info("Successfully verify Emergency Contract error message:"+errorMes_Expected);
	}

	/**
	 * Check the Non-Profit Organization check box exists
	 * 
	 * @return
	 */
	public boolean isNonProfitOrganizationExist() {
		boolean toReturn = false;

		if (browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", "makeNP")) {
			toReturn = true;
		}

		return toReturn;
	}

	/**
	 * Select the check box of Non-Profit Organization
	 */
	public void checkNonProfitOrganization() {
		browser.selectCheckBox(".id", "makeNP");
	}

	/**
	 * Unselect the check box of Non-Profit Organization
	 */
	public void unCheckNonProfitOrganization() {
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
		new Thread(){
			AlertDialogPage alertPg = AlertDialogPage.getInstance();

			public void run(){
				int i=0;
				alertPg.setDismissible(false);
				while(i<5){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						logger.warn(e.getMessage());
					}
					if(alertPg.exists()){
						alertPg.dismiss();
						break;
					}
					i++;
				}
			};
		}.start();

		if (null != exitPiont && exitPiont.length() < 0) {
			browser.selectDropdownList(".id", "exitpointid", exitPiont);
		} else {
			browser.selectDropdownList(".id", "exitpointid", 1);
		}
	}

	/** Deselect exit point drop down. */
	public void deselectExitPiont() {
		browser.selectDropdownList(".id", "exitpointid", 0);
	}

	/**
	 * Get facility name in "Applying for" section
	 * @return
	 */
	public String getFacility(){
		return browser.getDropdownListValue(".id", "facilityid");
	}

	/**
	 * Verify facility name
	 * @param facilityName: Expected facility name
	 */
	public void verifyFacilityName(String facilityName){
		String nameFromUI = this.getFacility();
		if (!nameFromUI.equals(facilityName)) {
			throw new ErrorOnDataException("The facility name is incorrect.", facilityName, nameFromUI);
		}
		logger.info("Successfully verify facility name:"+facilityName);
	}

	/**
	 * Check if the the "Facility" drop down list of "Applying for" section is disabled 
	 * @return
	 */
	public boolean checkFacilityDisabled(){
		boolean disabled = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "facilityid");
		if(objs.length<0){
			throw new ObjectNotFoundException("'Appling for' object can't be found. ");
		}else {
			if(objs[0].getProperty(".disabled").equals("true")){
				disabled = true;
			}	
		}

		Browser.unregister(objs);
		return disabled;
	}

	public void verifyFacilityDisabled(){
		boolean result = this.checkFacilityDisabled();
		if(!result){
			throw new ErrorOnDataException("Failed to verify the facility name in 'Applying for' section should be disabled.");
		}
		logger.info("Successfully verify the facility name in 'Applying for' section is disabled.");
	}

	public String getLotteryPreferencesContent(){
		return browser.getObjectText(".class", "Html.DIV", ".id", "lotteryprefs");
	}

	public String getPermitInformationContent(){
		Property[] p =Property.toPropertyArray(".class", "Html.DIV", ".className", "content shop", ".text",
				new RegularExpression("^Permit Information.*", false));
		return browser.getObjectText(p);
	}

	/**
	 * Wait for the number of trip itinerary row display
	 * @param row: 1,2,3...
	 */
	public void waitForTripItinerarySync(int row){
		Property[] p = Property.toPropertyArray(".id", new RegularExpression("tripitinerarypoint"+String.valueOf(row-1),false));
		browser.waitExists(p);
	} 

	/**
	 * Get the number of "Trip Itinerary"
	 * @return
	 */
	public int getTripItineraryRows(){
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

	/**
	 * Verify Trip Itinerary rows
	 * @param expectedRows
	 */
	public void verifyTripItineraryRows(int expectedRows){
		int actualRows = this.getTripItineraryRows();
		if(expectedRows!=actualRows){
			throw new ErrorOnPageException("Trip Itinerary rows is wrong!", String.valueOf(expectedRows), String.valueOf(actualRows));
		}
		logger.debug("Successfully verify Trip Itinerary rows:"+actualRows);
	}


	/**
	 * Get Name of the Group Leader: Last name first name
	 * If group leader is a cooperator, system only display the last name
	 * @return
	 */
	public String getGroupLeaderName(){
		String groupLeaderName = "";

		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Group Leader.*", false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Group leader table can't be found.");
		}
		groupLeaderName = objs[0].text().split("Group Leader")[1].trim();

		Browser.unregister(objs);
		return groupLeaderName;
	}

	/**
	 * Get Destination zone, permit type and entry date information
	 * @return
	 */
	public String getDestinationZonePermitTypeAndEntryDate(){
		String permitInfo, destinationZone, permitType, entryDate = "";

		IHtmlObject[] objs = browser.getTableTestObject(".className", "permitInfo");
		if(objs.length<1){
			throw new ObjectNotFoundException("Permit info table can't be found.");
		}

		permitInfo = objs[0].text();
		destinationZone = permitInfo.split("Destination Zone:")[1].split("Permit Type:")[0].trim();
		permitType = permitInfo.split("Permit Type:")[1].split("Entry Date:")[0].trim();
		entryDate = permitInfo.split("Entry Date:")[1].trim();

		Browser.unregister(objs);
		return destinationZone+","+permitType+","+entryDate;
	}

	public String getTotalNumberOfStock(){
		String totalNumOfStock = "";

		IHtmlObject[] objs = browser.getHtmlObject(".className", "totals", ".text", new RegularExpression("^Total Number of Stock.*", false));
		if(objs.length<1){
			throw new ObjectNotFoundException("Total Number of Stock can't be found.");
		}
		objs[0].text();
		totalNumOfStock = objs[0].text().split("Total Number of Stock \\(Max:")[1].split("\\)")[0].trim();

		Browser.unregister(objs);
		return totalNumOfStock;
	}

	/**
	 * @return
	 */
	public boolean isCreateGroupLeaderLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Create Group Leader");
	}

	/**
	 * @return
	 */
	public boolean isFindGroupLeaderLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Find Group Leader ");
	}

	public String getGroupSizeErrorMsg() {
		IHtmlObject[] objs=browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV", ".id", "personTypeError",".className","msg error"));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find Error message DIV");
		}
		String message=objs[0].text();
		Browser.unregister(objs);
		return message;
	}

	public boolean checkGroupSizeExist() {
		Property[] p1=Property.toPropertyArray(".class", "Html.DIV", ".text", new RegularExpression("^Permit Information",false));
		Property[] p2=Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^Group Size.*",false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null || objs.length<1){
			return false;
		}
		Browser.unregister(objs);
		return true;
	}

	public boolean checkIncreaseGroupSizeLinkExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", new RegularExpression("^Increase Group Size.*",false));
	}

	public String getTotalGroupSizeLabel() {
		Property[] p1=Property.toPropertyArray(".class", "Html.TR", ".className", "totals");
		Property[] p2=Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Total Group size.*",false));
		IHtmlObject[] objs=browser.getHtmlObject(Property.atList(p1,p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Can't find 'Total Group size' td");
		}

		String value=objs[0].text();
		Browser.unregister(objs);
		return value;
	}

	public String getTotalGroupSize() {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.SPAN", ".id", "totalGroupSize");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Cann't find total group size span");
		}
		String val=objs[0].text();
		Browser.unregister(objs);
		return val;
	}

	public void clickAddNewEntryDateLink(){
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id", "addEntryDateLinkDiv");
		Property[] p2 = Property.toPropertyArray(".class", "Html.A");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	public boolean waitForEntryDateUnderGroupSize(int index){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".className", "entryDateInfo");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("'entryDateInfo' object can't be found.");
		}
		Property[] p = Property.toPropertyArray(".id", "entrydateid");

		boolean returnBoolean = browser.checkHtmlObjectExists(p, objs[index]);

		Browser.unregister(objs);
		return returnBoolean;
	}

	/**
	 * Verify 'Entry Date' under 'Group size' section
	 * @param entryDate_Expected
	 * @param index: the index of expected entry date
	 */
	public void verifyEntryDateUnderGroupSize(String entryDate_Expected, int index) {
		logger.info("Verify 'Entry Date' under 'Group Size' in 'permit Order Details' page");
		String entryDate_Actual =  this.getEntryDateUnderGroupSize(index);
		if(!StringUtil.isEmpty(entryDate_Expected)){
			entryDate_Expected = DateFunctions.formatDate(entryDate_Expected, "E MMM dd yyyy");
		}
		if(!entryDate_Expected.equals(entryDate_Actual)){
			throw new ErrorOnPageException("Entry date is wrong 'under 'Group size' in 'permit Order Details' page!", entryDate_Expected, entryDate_Actual);
		}
		logger.info("Successfully verify 'Entry date' as '"+entryDate_Expected+" 'under 'Group size' in 'permit Order Details' page.");
	}

	/**
	 * Verify '# of Days' under 'Group size' section
	 * @param numOfDays_Expected
	 * @param index
	 */
	public void verifyNumOfDaysUnderGroupSize(String numOfDays_Expected, int index){
		logger.info("Verify '# of Days' under 'Group size' section");
		String numOfDays_Actual = this.getNumOfDaysUnderGroupSize(index);
		if(!numOfDays_Expected.equals(numOfDays_Actual)){
			throw new ErrorOnPageException("# of Days is wrong under 'Group size' section!", numOfDays_Expected, numOfDays_Actual);
		}
		logger.info("Successfully verify '# of Days' as '"+numOfDays_Expected+"' under 'Group size' section.");
	}

	/**
	 * Verify 'Exit Date' under 'Group size' section
	 * @param exitDate_Expected
	 * @param index
	 */
	public void verifyExitDateUnderGroupSize(String exitDate_Expected, int index){
		logger.info("Verify 'Exit Date' under 'Group size' section");
		String exitDate_Actual =  this.getExitDateUnderGroupSize(index);
		exitDate_Actual = DateFunctions.formatDate(exitDate_Actual, "E MMM d yyyy");
		if(!StringUtil.isEmpty(exitDate_Expected)){
			exitDate_Expected = DateFunctions.formatDate(exitDate_Expected, "E MMM d yyyy");
		}
		if(!exitDate_Expected.equals(exitDate_Actual)){
			throw new ErrorOnPageException("Exit Date is wrong under 'Group size' section!", exitDate_Expected, exitDate_Actual);
		}
		logger.info("Successfully verify 'Exit Date' as '"+exitDate_Expected+"' under 'Group size' section.");
	}

	/**
	 * Verify exit date
	 * @param exitDate_Expected
	 */
	public void verifyExitDate(String exitDate_Expected){
		logger.info("Verify 'Exit Date'");
		String exitDate_Actual =  this.getExitDate();
		if(!StringUtil.isEmpty(exitDate_Expected)){
			exitDate_Expected = DateFunctions.formatDate(exitDate_Expected, "E MMM d yyyy");
		}
		if(!exitDate_Expected.equals(exitDate_Actual)){
			throw new ErrorOnPageException("Exit Date is wrong!", exitDate_Expected, exitDate_Actual);
		}
		logger.info("Successfully verify 'Exit Date' as '"+exitDate_Expected+"'.");
	}

	/**
	 * Verify the link Increase Group Size is not shown
	 */
	public void verifyNoIncreaseGroupSizeLink() {
		if(this.checkIncreaseGroupSizeLinkExist()){
			throw new ErrorOnPageException("There should not be 'Increase Group Size' link");
		}
		logger.info("There is no 'Increase Group Size' link shown!");
	}

	/**
	 * Verify the label total group size without max number is shown 
	 */
	public void verifyNoTotalGroupSizeLabel() {
		if (this.checkTotalGroupSizeLabelExist()) {
			throw new ErrorOnPageException("The Total Group Size Lable should NOT be shown!");
		}
		logger.info("The Total Group Size Label is not shown!");
	}

	/**
	 * Verify total group size number in Permit Order Details page
	 * @param groupSize
	 */
	public void verifyTotalGroupSizeNum(String groupSize) {
		String totalGroupSize = null;

		if (this.checkTotalGroupSizeLabelExist()) {
			totalGroupSize = this.getTotalGroupSize();
		} else {
			totalGroupSize = this.getGroupSizeValueWhenNoPerTypes();
		}

		if(!groupSize.equals(totalGroupSize)){
			throw new ErrorOnPageException("Total Group Size Num is wrong in order details page !", 
					groupSize, totalGroupSize);
		}
		logger.info("Total Group Size Num is correct in order details page !");
	}

	/**
	 * Verify group size error message
	 * @param errMsg
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void verifyGroupSizeErrorMeg(String errMsg) {
		String err = this.getGroupSizeErrMsgWhenNoPerTypes();
		if (!err.equalsIgnoreCase(errMsg)) {
			throw new ErrorOnPageException("Group Size Error Message is wrong!", errMsg, err);
		}
		logger.info("The Group Size Error message is correct!");
	}

	/**
	 * Verify the group size max number where there are no person types
	 * @param max
	 * @author Lesley Wang
	 * @date Jun 29, 2012
	 */
	public void verifyMaxGroupSizeNum(String max) {
		String maxSize = this.getMaxGroupSizeNumWhenNoPerTypes();
		if(!maxSize.equals(max)){
			throw new ErrorOnPageException("Max Group Size Num is wrong!", max, maxSize);
		}
		logger.info("Max Group Size Num is correct!");
	}

	/**
	 * Get Max Group Size number when there is no label Total Group Size
	 * @return
	 * @author Lesley Wang
	 * @date Jun 28, 2012
	 */
	public String getMaxGroupSizeNumWhenNoPerTypes() {
		String text = browser.getObjectText(".class", "Html.SPAN", 
				".text", new RegularExpression("^\\(Max:", false));
		return StringUtil.getSubstring(text, "(Max:", ")");
	}

	public String getGroupSizeErrMsgWhenNoPerTypes() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", 
				new RegularExpression("^Group Size", false));
		if (objs == null || objs.length < 1) {
			throw new ErrorOnPageException("The Group Size Table row doesn't exist!");
		}

		IHtmlObject[] objs2 = browser.getHtmlObject(".class", "Html.DIV", ".classname", 
				"msg error", objs[0]);
		if (objs2 == null || objs2.length < 1) {
			throw new ErrorOnPageException("The Error Message is not shown!");
		}

		String text = objs2[0].text();
		Browser.unregister(objs, objs2);
		return text;
	}

	/**
	 * Get Group Size Value in the first text box 'groupsizeid'
	 * @return
	 * @author Lesley Wang
	 * @date Jun 28, 2012
	 */
	public String getGroupSizeValueWhenNoPerTypes() {
		return browser.getTextFieldValue(".id", "groupsizeid");
	}

	/**
	 * Check Total Group Size Label exists
	 * @return
	 * @author Lesley Wang
	 * @date Jun 28, 2012
	 */
	public boolean checkTotalGroupSizeLabelExist() {
		Property[] p1=Property.toPropertyArray(".class", "Html.TR", ".className", "totals");
		Property[] p2=Property.toPropertyArray(".class", "Html.TD", ".text", new RegularExpression("^Total Group size.*",false));
		return browser.checkHtmlObjectExists(Property.atList(p1,p2));
	}

	/**
	 * Get Emergency contracts Table content
	 * @return
	 */
	public String getEmergencyContractTableContent(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "emergencyContactTable");
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Emergency Contract Table can't be found.");
		}

		String content = objs[0].text();
		Browser.unregister(objs);
		return content;
	}

	/**
	 * Verify Emergency contract table content
	 * @param content_Expected
	 */
	public void verifyEmergencyContractTableContent(String content_Expected){
		logger.info("Verify Emergency contract table content.");
		String content_Actual = this.getEmergencyContractTableContent().replaceAll("\\s+", "");
		content_Expected = content_Expected.replaceAll("\\s+", "");
		if(!MiscFunctions.compareResult("Emergency Contract Table", content_Expected, content_Actual)){
			throw new ErrorOnPageException("Emergency Contract Table content is wrong.");
		}
		logger.info("Successfully verify Emergency contract table content:"+content_Expected);
	}

	/**
	 * Get Emergency contract elements(FNames, LNames, Phones)
	 */
	private String[] getEmergencyContractElements(String emergencyContractElementClassName){
		logger.info("Get Emergency contract elements - "+emergencyContractElementClassName);


		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id", "emergencyContactTable");
		Property[] p2 = Property.toPropertyArray(".class", "Html.INPUT.text", ".name", emergencyContractElementClassName);
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Emergency contract first name object can't be found.");
		}

		String[] elementValues = new String[objs.length];
		for(int i=0; i<objs.length; i++){
			elementValues[i]= ((IText) objs[i]).getText();
		}
		Browser.unregister(objs);
		return elementValues;
	}

	public String[] getEmergencyContractFNames(){
		return this.getEmergencyContractElements("emergencyContactFirstName");
	}
	public String[] getEmergencyContractLNames(){
		return this.getEmergencyContractElements("emergencyContactLastName");
	}
	public String[] getEmergencyContractPhones(){
		return this.getEmergencyContractElements("emergencyContactPhone");
	}

	/**
	 * Get Emergency contracts info
	 * @return
	 */
	public PermitEmergencyContact getEmergencyContractInfo(){
		PermitEmergencyContact emergencyContracts = new PermitEmergencyContact();
		emergencyContracts.setfNames(this.getEmergencyContractFNames());
		emergencyContracts.setlNames(this.getEmergencyContractLNames());
		emergencyContracts.setPhones(this.getEmergencyContractPhones());

		return emergencyContracts;
	}

	/**
	 * Verify Emergency Contracts info
	 * @param emergencyContracts_Expected
	 */
	public void verifyEmergencyContractInfo(PermitEmergencyContact emergencyContracts_Expected){
		logger.info("Verify Emergency Contract FName, LName and Phone info.");
		PermitEmergencyContact emergencyContracts_Actual = this.getEmergencyContractInfo();
		boolean result = true;

		for(int i=0; i<emergencyContracts_Expected.getSize(); i++){
			result &= MiscFunctions.compareResult("The value of emergency contract first name:"+i, emergencyContracts_Expected.getfNames()[i], emergencyContracts_Actual.getfNames()[i]);
			result &= MiscFunctions.compareResult("The value of emergency contract last name:"+i, emergencyContracts_Expected.getlNames()[i], emergencyContracts_Actual.getlNames()[i]);
			result &= MiscFunctions.compareResult("The value of emergency contract phone:"+i, emergencyContracts_Expected.getPhones()[i], emergencyContracts_Actual.getPhones()[i]);	
		}

		if(!result){
			throw new ErrorOnPageException("Something wrong with emergency contract info, please check detail info with previous logs.");
		}else{
			logger.info("Successfully verify Emergency Contract FName, LName and Phone info.");
		}
	}

	/**
	 * Get the number of Emergency Contract rows
	 * @param emergencyContractLabel: Setup in Inventory Manager
	 * @return
	 */
	public int getNumOfEmergencyContractRows(String emergencyContractLabel){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression("^"+emergencyContractLabel.replace("(", "\\(").replace(")", "\\)")+".*", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.INPUT.text", ".name", "emergencyContactFirstName");
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));
		if(objs==null || objs.length<1){
			throw new ObjectNotFoundException("Emergency Contract First Name object can't be found.");
		}

		int rowsNum = objs.length;
		Browser.unregister(objs);
		return rowsNum;
	}

	/**
	 * Verify the number of Emergency Contract rows
	 * @param emergencyContractLabel: Setup in Inventory Manager
	 * @param rows_Expected
	 */
	public void verifyNumOfEmergencyContractRows(String emergencyContractLabel, int rows_Expected){
		int rows_Actual = this.getNumOfEmergencyContractRows(emergencyContractLabel);
		if(!MiscFunctions.compareResult("Number of Emergency Contracts rows", rows_Expected, rows_Actual)){
			throw new ErrorOnPageException("The number of Emergency Contracts rows is wrong!");
		}
		logger.info("Successfully verify the number of Emergency Contracts rows:"+rows_Expected);
	}

	/**
	 * Verify default Trail Head value
	 * @param expectedDefaultValue
	 */
	public void verifySelectedTrailheadValue(String expectedDefaultValue){
		logger.info("Verify default Trail Head option in 'Trailhead' drop down list in 'Permit Order details' page");

		String actualDefaultValue = this.getTrailHeadValue();
		if(!expectedDefaultValue.equals(actualDefaultValue)){
			throw new ErrorOnPageException("Default Trail Head value is wrong!", expectedDefaultValue, actualDefaultValue);
		}
		logger.info("Successfully verify default Trail Head Value:"+actualDefaultValue);
	}

	/**
	 * Verify Trail Head display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param theActives: true: all the active should display, false: all the inactive should not display
	 */
	public void verifyTrailHeadDDListOptions(boolean isActive, List<String> trailHeads){
		logger.info("Start to verify all "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't")+" display.");

		List<String> actualTrailHeads = this.getTrailHeads();
		//Remove the default trail options"- Please Select -"
		actualTrailHeads.remove("- Please Select -");

		if(isActive && trailHeads.size()!=actualTrailHeads.size()){
			throw new ErrorOnPageException("The size of Trail Heads", trailHeads.size(), actualTrailHeads.size());
		}
		//Verify all active options display, the inactive don't display
		for(int i=0; i<actualTrailHeads.size(); i++){
			if(isActive!=trailHeads.toString().contains(actualTrailHeads.get(i))){
				throw new ErrorOnPageException("Not all "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't")+" display.. Please check details info from previous logs.");
			}
		}

		logger.info("Successfully verify all "+(isActive?"active":"inactive")+" Trail Heads "+(isActive?"":"don't")+" display.");
	}

	/**
	 * Verify Trip Itinerary display
	 * @param TrailHeads: all the active or inactive Trail Heads
	 * @param isActive: true:all active options display, false:the inactive don't display
	 * @param row:1:the first row of trip itinerary drop down list, 2:the second row of trip itinerary drop down list...
	 */
	public void verifyTripItinerarysDDListOptions(boolean isActive, List<String> tripItinerarys, int row){
		logger.info("Start to verify all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't")+" display.");

		List<String> actualvalues = this.getTripItinerarys(row);
		//Remove the default trail options"- Please Select -"
		actualvalues.remove("- Please Select -");

		if(isActive && tripItinerarys.size()!=actualvalues.size()){
			throw new ErrorOnPageException("The size of  Trip Itinerary is wrong!", tripItinerarys.size(), actualvalues.size());
		}
		//Verify all active options display, the inactive don't display
		for(int i=0; i<actualvalues.size(); i++){//UI:Alpine Lake (Triple Divide -Yosemite), DB:Alpine Lake  (Triple Divide -Yosemite)
			if(isActive!=tripItinerarys.toString().replaceAll("\\s+", " ").contains(actualvalues.get(i))){
				logger.info("ALL Trip Itineraries from DB:"+tripItinerarys.toString());
				logger.info("Trip Itinerary from UI:"+actualvalues.get(i));
				throw new ErrorOnPageException("Not all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't")+" display.");
			}
		}

		logger.info("Successfully verify all "+(isActive?"active":"inactive")+" Trip Itinerary "+(isActive?"":"don't")+" display.");
	}

	/**
	 * Verify specific trip itinerary display
	 * @param tripItinerary
	 * @param display
	 * @param row
	 */
	public void verifySpecificTripItineraryOptionDisplay(String tripItinerary, boolean display, int row){
		logger.info("Verify the Trip Itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't")+" display in 'Permit Order details' page");
		List<String> allTripItinerarys = this.getTripItinerarys(row);
		boolean result = allTripItinerarys.toString().contains(tripItinerary);

		if(display!=result){
			throw new ErrorOnPageException(allTripItinerarys.toString()+" should "+(display?"":"doesn't ")+"contain the trip itinerary:"+tripItinerary);
		}
		logger.info("Successfully verify the trip itinerary (Name:"+tripItinerary+") "+(display?"":"doesn't")+" display.");
	}

	/**
	 * Specific trail head options display 
	 * @param trailHead
	 * @param display
	 */
	public void verifySpecifiedTrailHeadOptionDisplays(String trailHead, boolean display){
		logger.info("Verify the Trail Head (Name:"+trailHead+") "+(display?"":"doesn't")+" display in 'Permit Order details' page");
		List<String> allTrailHead = this.getTrailHeads();
		boolean result = allTrailHead.toString().contains(trailHead);

		if(display!=result){
			throw new ErrorOnPageException(allTrailHead.toString()+" should "+(display?"":"n't ")+"contain the Trail Head:"+trailHead);
		}
		logger.info("Successfully verify the Trail Head (Name:"+trailHead+") "+(display?"":"don't")+" display.");
	}

	/**
	 * Get permit elements from the top of permit order details page
	 * @param permitInfoTitle: Destination Zone, Permit Type, Entry Date, Exit Date, Length Of Stay
	 * @return
	 */
	private String getPermitRelatedFromInfoTable(String permitInfoTitle){
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".className", "permitInfo");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TR", ".text", new RegularExpression(permitInfoTitle+".*", false));
		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find element objects from permit information table.");
		}

		String value = objs[0].text().split(":")[1].trim();
		Browser.unregister(objs);
		return value;
	}

	public String getExitDateFromPermitInfoTable(){
		return this.getPermitRelatedFromInfoTable("Exit Date");
	}
	public void verifyExitDateInPermitInfoTable(String expectedValue){
		String actualValue = this.getExitDateFromPermitInfoTable();
		if(DateFunctions.compareDates(expectedValue, actualValue)!=0){
			throw new ErrorOnDataException("Failed to verify exit date in permit info table in permit order details page.", expectedValue, actualValue);
		}
		logger.info("Successfully verify exit date in permit info table in permit order details page.");
	}

	public String getLengthOfStayFromPermitInfoTable(){
		return this.getPermitRelatedFromInfoTable("Length Of Stay");
	}
	public void verifyLengthOfStayInPermitInfoTable(String expectedValue){
		String actualValue = this.getLengthOfStayFromPermitInfoTable();
		if(!expectedValue.equals(actualValue)){
			throw new ErrorOnDataException("Failed to verify length of stay in permit info table in permit order details page.", expectedValue, actualValue);
		}
		logger.info("Successfully verify length of stay in permit info table in permit order details page.");
	}

	public void verifyChangeItineraryExist(boolean isExisted){
		String info = isExisted ? " " : " not ";
		boolean resultFromUI = browser.checkHtmlObjectExists(".class", "Html.A", ".href", new RegularExpression("^/entranceSingleTripSearch\\.do.*", false));
		if(resultFromUI!=isExisted){
			throw new ErrorOnPageException("The change itinerary link should " + info + "exist!");
		}else logger.info("The change itinerary link does " + info + "exist!");
	}

	public void clickChangeItinerary() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression("^/entranceSingleTripSearch\\.do.*", false));
	}

	/**
	 * Get itinerary details section information, contain "Dates of Stay", "entrances" and "Group Size", no person types related
	 * Sample
	 * Fri Aug 02 2013-Sat Aug 03 2013 (2 days) 01-Rockbound Lake 3 
	 * Sun Aug 04 2013-Mon Aug 05 2013 (2 days) 03-Genevieve 3 
	 * @return
	 */
	public List<String[]> getItineraryDetailInfoAtOrderLevel(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "stiTable");
		if(objs.length<1){
			throw new ObjectNotFoundException("Itinerary detail table can't be found in permit order details page.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		List<String[]> records = new ArrayList<String[]>();
		String[] record = null;

		for(int i=1; i<table.rowCount(); i++){
			record = new String[table.columnCount()];
			for(int j=0; j<record.length; j++){
				record[j] = table.getCellValue(i, j);

			}
			records.add(record);
		}

		Browser.unregister(objs);
		return records;
	}

	/**
	 * Verify itinerary details section information, contain "Dates of Stay", "entrances" and "Group Size", no person types related
	 * @param entrances
	 */
	public void verifyItineraryDetailsNoPersonTypes(EntranceInfo... entrances){
		boolean passed = true;
		String depatureDate = "";

		List<String[]> records = getItineraryDetailInfoAtOrderLevel();
		if(entrances.length!=records.size()){
			throw new ErrorOnPageException("Itinerary records length is wrong!", entrances.length, records.size());
		}

		for(int i=0; i<records.size(); i++){
			String[] record = records.get(i);
			if(entrances[i].useType.startsWith("night")){
				depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)), "E MMM dd yyyy");
			}else{
				depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i].entryDate, Integer.valueOf(entrances[i].numOfDays)-1), "E MMM dd yyyy");
			}
			passed &= MiscFunctions.compareResult("Dates of Stay", DateFunctions.formatDate(entrances[i].entryDate, "E MMM dd yyyy")+"-"+depatureDate+" ("+entrances[i].numOfDays+" "+entrances[i].useType+")", record[0]);
			passed &= MiscFunctions.compareResult("Entrance code and name", entrances[i].entranceCode+"-"+entrances[i].entranceName, record[1]);
			if(StringUtil.notEmpty(entrances[0].groupSize)){
				if(entrances[0].groupSizeEditable){
					passed &= MiscFunctions.compareResult("Group size", StringUtil.EMPTY, record[2]);
				}else{
					passed &= MiscFunctions.compareResult("Group size", entrances[i].groupSize, record[2]);
				}
			}
			if(!passed){
				throw new ErrorOnPageException("Failed to verify itinerary detail for entrance, code="+entrances[i].entranceCode+", name="+entrances[i].entranceName+" in permit order details page.");
			}
			logger.info(i+" - Itinerary details info is correct in permit order details page.");
		}
	}

	/**
	 * Get itinerary details section information, contain "Dates of Stay", "entrances" and "Group Size", also person types related
	 * Sample
	 * Fri Aug 02 2013-Sat Aug 03 2013 (2 days) 01-Rockbound Lake 3 
	 * Adult
	 * Child
	 * Sun Aug 04 2013-Mon Aug 05 2013 (2 days) 03-Genevieve 3 
	 * Adult
	 * Child
	 * @return
	 */
	public List<String[]> getItineraryDetailsWithPersonTypes(){
		IHtmlObject[] objs = browser.getTableTestObject(".id", "stiTable");
		if(objs.length<1){
			throw new ObjectNotFoundException("Itinerary detail table can't be found in permit order details page.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		List<String[]> records = new ArrayList<String[]>();
		String[] record = null;

		for(int i=1; i<table.rowCount(); i++){
			if(i%2==0){
				record = new String[2];
				record[0] = table.getCellValue(i, 1);
				record[1] = table.getCellValue(i, 2);
			}else{
				record = new String[table.columnCount()];
				for(int j=0; j<record.length; j++){
					record[j] = table.getCellValue(i, j);
				}
			}
			records.add(record);
		}

		Browser.unregister(objs);
		return records;
	}
	
	/**
	 * Verify itinerary details section information, contain "Dates of Stay", "entrances" and "Group Size", also person types related
	 * @param entrances
	 * @param personTypes
	 */
	public void verifyItineraryDetailsWithPersonTypes(EntranceInfo[] entrances, String personTypes){
		boolean hasGroupSize = StringUtil.notEmpty(entrances[0].groupSize);
		boolean passed = true;
		String depatureDate = "";

		List<String[]> records = getItineraryDetailsWithPersonTypes();
		if(entrances.length*2!=records.size()){
			throw new ErrorOnPageException("Itinerary records length is wrong!", entrances.length*2, records.size());
		}

		for(int i=0; i<records.size(); i++){
			String[] record = records.get(i);
			if(i%2!=0){
				passed &= MiscFunctions.compareResult("Person types", personTypes, record[0]);
			}else{
				if(entrances[i/2].useType.startsWith("night")){
					depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i/2].entryDate, Integer.valueOf(entrances[i/2].numOfDays)), "E MMM dd yyyy");
				}else{
					depatureDate = DateFunctions.formatDate(DateFunctions.getDateAfterGivenDay(entrances[i/2].entryDate, Integer.valueOf(entrances[i/2].numOfDays)-1), "E MMM dd yyyy");
				}
				passed &= MiscFunctions.compareResult("Dates of Stay", DateFunctions.formatDate(entrances[i/2].entryDate, "E MMM dd yyyy")+"-"+depatureDate+" ("+entrances[i/2].numOfDays+" "+entrances[i/2].useType+")", record[0]);
				passed &= MiscFunctions.compareResult("Entrance code and name", entrances[i/2].entranceCode+"-"+entrances[i/2].entranceName, record[1]);
				if(hasGroupSize){
					passed &= MiscFunctions.compareResult("Group size", entrances[i/2].groupSize, record[2]);
				}
			}
			if(!passed){
				throw new ErrorOnPageException(i+" - Failed to verify itinerary detail for entrance in permit order details page.");
			}
			logger.info(i+" - Itinerary details info is correct in permit order details page.");
		}
	}
	
	/**
	 * Get group size and related person types under permit information section
	 * It will return person types and column names, such as: [{Quantity}, {Adult}, {Child}, {Interagency Access Pass}]
	 * @return
	 */
	public List<String[]> getGroupSizeWithPersonTypes(){
		IHtmlObject[] topObjs = browser.getHtmlObject(Property.atList(groupSizePropInPermitInfoSection(), groupSizeProp()));
		IHtmlObject[] objs = null;
		int startIndex = 0;
		int endIndex = 0;
		String totalGroupSizeRow = "Total Group Size";
		boolean isTotalGroupSizeRow = false;

		if(topObjs.length<1){
			throw new ObjectNotFoundException("Group size DIV can't ve found.");
		}else{
			objs = browser.getTableTestObject(Property.toPropertyArray(".id", "formSection"), topObjs[0]);
		}

		if(objs.length<1){
			throw new ObjectNotFoundException("Group size DIV can't be found in permit order details page.");
		}

		IHtmlTable table = (IHtmlTable)objs[0];
		List<String[]> records = new ArrayList<String[]>();
		String[] record = null;

		for(int i=0; i<table.rowCount(); i++){
			if(i==0){ //For group size column names
				startIndex = 1;
				endIndex = table.columnCount();
			}else{ //For person types 
				startIndex = 0;
				endIndex = 1;
			}
			record = new String[endIndex-startIndex];
			for(int j=startIndex; j<endIndex; j++){
				isTotalGroupSizeRow = table.getCellValue(i, j).startsWith(totalGroupSizeRow);
				if(isTotalGroupSizeRow){
					break;
				}else record[j-startIndex] = table.getCellValue(i, j);
			}
			
			if(isTotalGroupSizeRow){
				break;
			}else records.add(record);
		}

		records.remove(1);
		Browser.unregister(objs);
		return records;
	}

	/**
	 * Verify groups size and related person types info under permit information section
	 * @param colNames, such as {Quantity}
	 * @param personTypes, such as {Adult, Child}
	 */
	public void verifyGroupSizeWithPersonTypes(String[] colNames, String[] personTypes){
		boolean passed  = true;
		String[] record = null;
		List<String[]> records = getGroupSizeWithPersonTypes();
		if(records.get(0).length!=colNames.length){
			throw new ErrorOnPageException("Group size div column count is wrong", colNames.length, records.get(0).length);
		}

		for(int i=0; i<records.size(); i++){
			record = records.get(i);
			for(int j=0; j<record.length; j++){
				if(i==0){
					passed &= MiscFunctions.compareResult("Group size DIV colnum value", colNames[j], record[j]);
				}else{
					passed &= MiscFunctions.compareResult("Person type", personTypes[i-1], record[j]);
				}
			}

			if(!passed){
				throw new ErrorOnPageException(i+" - Failed to verify group size DIV info in permit order details page.");
			}
			logger.info(i+" - Group size DIV info is correct in permit order details page.");
		}
	}
	
	/**
	 * Verify group size text field exists, which is without person types
	 * @param isExisted true: group size text field exists
	 *                  false: group size text field doesn't exist
	 */
	public void verifyOnlyHasGroupSizeInPermitInfoSection(boolean isExisted){
		String info = isExisted ? " " : " not ";
		boolean resultFromUI = browser.checkHtmlObjectExists(Property.atList(groupSizePropInPermitInfoSection(), groupSizeTextFieldProp()));

		if(resultFromUI!=isExisted){
			throw new ErrorOnPageException("The group size text field should " + info + "exist!");
		}else logger.info("The group size text field does " + info + "exist!");
	}
	
	/**
	 * Verify no group size text field and person types in "Permit information" section
	 */
	public void verifyNoGroupSizeAndPersonTypesInPermitInfoSection(){
		boolean resultFromUI = browser.checkHtmlObjectExists(Property.atList(groupSizePropInPermitInfoSection(), groupSizeProp())) || browser.checkHtmlObjectExists(Property.atList(groupSizePropInPermitInfoSection(), groupSizeTextFieldProp()));
	    if(resultFromUI){
	    	throw new ErrorOnPageException("Should no group size text field and person types in permit order details page.");
	    }
	    logger.info("Successfully verify no group size and person types in permit order details page.");
	}
	
	/**
	 * Get alerts
	 * Sample
	 * Facility
     * Automation testing for important info of Desolation Wilderness Permit park
     * Automation testing for Alert of Desolation Wilderness Permit park
     * Destination Zone
     * Automation testing for important info of Rockbound Lake entrance
     * Automation testing for Alert of Rockbound Lake entrance
	 * @return
	 */
	public List<String[]> getAlerts(){
		List<String[]> alertsList = new ArrayList<String[]>();
		List<String> alerts = new ArrayList<String>();
		String notHeaderClassName = "noteHeader";
		String noteTextClassName = "noteText";

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(alertsSectionIDProp(), Property.toPropertyArray(".class", "Html.DIV")));
		if(objs.length<1){
			throw new ObjectNotFoundException("Can't find child DIV under Alerts section.");
		}

		for(IHtmlObject obj: objs){
			boolean isNoteHeader = obj.getProperty("className").equals(notHeaderClassName);
			boolean isNoteText = obj.getProperty("className").equals(noteTextClassName);
            if(isNoteHeader){
            	alertsList.add(alerts.toArray(new String[alerts.size()]));
            	alerts = new ArrayList<String>();
            }
            if(isNoteHeader || isNoteText){
                alerts.add(obj.text().trim());
            }
            
		}
		alertsList.add(alerts.toArray(new String[alerts.size()]));
		alertsList.remove(0);
		return alertsList;
	}
	
	public void verifyAlerts(List<String[]> alertsList){
		List<String[]> alertsListFromUI = getAlerts();
		boolean passed = true;

		for(int i=0; i<alertsList.size(); i++){
			if(alertsList.get(i).length!=alertsListFromUI.get(i).length){
				passed &= MiscFunctions.compareResult(i+"- length", alertsList.get(i).length, alertsListFromUI.get(i).length);
			}else{
				for(int j=0; j<alertsList.get(i).length; j++){
					passed &= MiscFunctions.compareResult("Alerts", alertsList.get(i)[j], alertsListFromUI.get(i)[j]);
				}
			}

			if(!passed){
				throw new ErrorOnDataException(i+" - Alerts are wrong!");
			}
			logger.info(i+" - Alerts are correct!");
		}
	}
	
	public boolean isPermitTypeFixedLabelExisted(String labelRegx){
		return browser.checkHtmlObjectExists(Property.atList(permitInfomationDiv(), fixedSpan(labelRegx)));
	}
	
	public void verifyPermitTypeLabelExist(String labelRegx, boolean existed){
		boolean resultFromUI = isPermitTypeFixedLabelExisted(labelRegx);
		if(resultFromUI==existed){
			logger.info("Successfully verify permit type label:"+labelRegx+(existed?" exists":" doesn't exist"));
		}else throw new ErrorOnPageException("Failed to verify permit type label:"+labelRegx+(existed?" exists":" doesn't exist"));
	}
	
}
