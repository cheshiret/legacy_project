/*
 * $Id: UwpOrderDetailsPage.java 6832 2008-12-02 20:43:35Z i2k-net\raonqa $ 
 */

package com.activenetwork.qa.awo.pages.web.uwp;

import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.web.MemberProfile;
import com.activenetwork.qa.awo.datacollection.legacy.web.OrderDetails;
import com.activenetwork.qa.awo.util.AwoDatabase;
import com.activenetwork.qa.awo.util.DataBaseFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IRadioButton;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.TestProperty;

/**
 * @author jchen
 */
public class UwpOrderDetailsPage extends UwpHeaderBar {

	private String reSelectEqp = ".*(s|S)elect.*";
	private String reNoEqp = ".*(n|N)(o|O)(n|N)(e|E).*";
	private String reTent = ".*(T|t)(E|e)(N|n)(T|t).*";
	private static UwpOrderDetailsPage _instance = null;

	public static UwpOrderDetailsPage getInstance() {
		if (null == _instance)
			_instance = new UwpOrderDetailsPage();

		return _instance;
	}

	public UwpOrderDetailsPage() {
	}

	private static String LABEL_PRICE = "Price: \\$";
	private static String LABEL_DOMESTICSTANDARD = "Domestic Standard \\(average 1 - 2 weeks\\) \\$\\d+\\.\\d+ mailing fee";
	private static String LABEL_DOMESTICEXPEDITED = "Domestic Expedited \\(average 2 - 3 days\\) \\$\\d+\\.\\d+ mailing fee";
	private static String LABEL_ANNUALCAMPINGPASS = "Annual Camping Pass";
	
	protected Property[] buyInteragencyPass(){
		return Property.toPropertyArray(".id", "buypass");
	}

	protected Property[] labelProp(String labelReg){
		return Property.concatPropertyArray(label(), ".text", new RegularExpression(labelReg, false));
	}

	protected Property[] interagencyPassQuantity(){
		return Property.concatPropertyArray(div(), ".id", "quantity");
	}

	protected Property[] standardDeliveryMethod(){
		return Property.toPropertyArray(".id", "standardshipping");
	}

	protected Property[] standardDeliveryMethod(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}

	protected Property[] expeditedDeliveryMethod(){
		return Property.toPropertyArray(".id", "expeditedshipping");
	}

	protected Property[] expeditedDeliveryMethod(String id){
		return Property.toPropertyArray(".name", "passrb", ".id", id);
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "continueshop");
	}

	protected Property[] interagencyAnualPassDIV(){
		return Property.concatPropertyArray(div(), ".className", "content shop", ".text", new RegularExpression("^America The Beautiful - Interagency Annual Pass.*", false));
	}

	protected Property[] passDesc(){
		return Property.concatPropertyArray(div(), ".id", "passdesc");
	}

	protected Property[] annualDayUsePass(){
		return Property.toPropertyArray(".id", "pass1", ".value", new RegularExpression("AnnualDayUsePass;\\d+\\.\\d+", false));
	}
	
	protected Property[] annualCampingPass(){
		return Property.toPropertyArray(".id", "pass2");
	}
	
	protected Property[] annualCampingPass(String id){
		return Property.toPropertyArray(".id", id);
	}
	
	protected Property[] annualCampingPassInState(){
		return Property.toPropertyArray(".name", "pass2rb", ".value", new RegularExpression("AnnualCampingPassInState;\\d+\\.\\d+", false));
	}
	
	protected Property[] annualCampingPassOutState(){
		return Property.toPropertyArray(".name", "pass2rb", ".value", new RegularExpression("AnnualCampingPassOutState;\\d+\\.\\d+", false));
	}
	
	/**
	 * Click on Continue to Shopping Cart button.
	 */
	public void clickContinueToShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
	/**
	 * Select the equipment type.
	 * @param equi - equipment type
	 */
	public void selectEquipment(String equi) {
		browser.selectDropdownList(".id", "equip", equi);
	}
	/**
	 * Set the Vehicle length.
	 * @param length - length of Vehicle
	 */
	public void setVehicleLength(String length) {
		browser.setTextField(".name", "vehicleLength", length);
	}

	/** Select Family rate type radio button. */
	public void selectFamilyRate(){
		browser.selectRadioButton(".name","rateType",".value","Family");
	}

	/** Select Group rate type radio button. */
	public void selectGroupRate(){
		browser.selectRadioButton(".name","rateType",".value","Group");
	}

	/**
	 * Fill the first occupant number.
	 * @param num - number of occupant
	 */
	public void setNumOfOccupants(String num) {
		IHtmlObject[] objs = browser.getTextField(".id", "numoccupants");
		if (objs.length < 1) {
			Browser.unregister(objs);
			objs = browser.getTextField(".id", "qtypersonsid");
		}
		if (objs.length >0) {
			((IText)objs[0]).setText(num);
		}
		Browser.unregister(objs);
	}
	/**
	 * Fill in vehicle number.
	 * @param num - number of vehicle
	 */
	public void setNumOfVehicles(String num) {
		browser.setTextField(".id", "numvehicles", num);
	}
	/**
	 * Get occupants table object.
	 * @return - occupant table
	 */
	public IHtmlObject[] getOccupantsTable() {
		return browser.getTableTestObject(".id", "occupants");
	}
	/**
	 * Select accept agreement check box.
	 */
	public void selectCheckBoxAgreementAccepted() {
		browser.selectCheckBox(".id", new RegularExpression("agreement|continueshop", false)); //Sara[10/15/2013] "continueshop" for slip
	}
	/**
	 * Deselect accept agreement check box.
	 */
	public void deselectCheckBoxAgreementAccepted() {
		browser.unSelectCheckBox(".id", "agreement");
	}
	/**
	 * Click on Change Dates link.
	 */
	public void clickChangeDates() {
		browser.clickGuiObject(".class","Html.A",".id", "changedates");
	}
	/**
	 * Click on Remove this link to remove holded items.
	 */
	public void clickRemoveThis() {
		browser.clickGuiObject(".id", "removethis");
	}
	/**
	 * Click Make More Reservation to make another reservation.
	 */
	public void clickMakeMoreRes() {
		browser.clickGuiObject(".id", "reservemore");
	}
	/**
	 * Get the test object specified by given name.
	 * @param name - object name
	 * @return
	 */
	private IHtmlObject[] getObject(String name) {
		IHtmlObject[] foundTOs = null;
		if (name.equalsIgnoreCase("button_continueToShoppingCarts")) {
			foundTOs = browser.getHtmlObject(".class","Html.BUTTON",".id", "continueshop");
		} else if (name.equalsIgnoreCase("list_equip")) {
			foundTOs = browser.getDropdownList(".id", "equip");
		} else if (name.equalsIgnoreCase("text_vehicleLength")) {
			foundTOs = browser.getTextField(".name", "vehicleLength");
		} else if (name.equalsIgnoreCase("text_numberOfCampers")) {
			foundTOs = browser.getTextField(".id", "numoccupants");
			if (foundTOs.length < 1) {
				foundTOs = browser.getTextField(".id", "qtypersonsid");
			}
		} else if (name.equalsIgnoreCase("text_numberOfVehicles")) {
			foundTOs = browser.getTextField(".id", "numvehicles");
		} else if (name.equalsIgnoreCase("table_occupants")) {
			foundTOs = browser.getTableTestObject(".id","occupants");
		} else if (name.equalsIgnoreCase("checkBox_agreementAcceptedon")) {
			foundTOs = browser.getCheckBox(".id", "agreement");
		} else if (name.equalsIgnoreCase("link_changedates")) {
			foundTOs = browser.getHtmlObject(".id", "changedates");
		} else if (name.equalsIgnoreCase("link_removethis")) {
			foundTOs = browser.getHtmlObject(".id", "removethis");
		} else {
			throw new ItemNotFoundException("\""+name+"\" not found");
		}

		return foundTOs;
	}

	public void selectPrimaryOccupantMemeber() {
		browser.selectRadioButton(".id","primaryOccupant_Member");
	}

	public void selectPrimaryOccupantOther() {
		browser.selectRadioButton(".id","primaryOccupant_Other");
	}


	/**
	 * Fill in order details info.
	 * @param dtl - order details info
	 * @param isDiscount - whether there is discount want to be applied.
	 * @param isPromoDiscount - whether or not need to fill in promotion code discount
	 * @param isGroupRate - Is The rate type Group or Family ?
	 */
	public void setOrderDetails(OrderDetails dtl, String contract,boolean isDiscount, 
			boolean isPromoDiscount, boolean isGroupRate) {
		setEquipment(dtl.equipType, dtl.equipLength, dtl.numOccupant,dtl.numVehicles);

		if(StringUtil.isEmpty(dtl.primaryOccupant) || dtl.primaryOccupant.equalsIgnoreCase("memeber")) {
			selectPrimaryOccupantMemeber();
		} else if(dtl.primaryOccupant.equalsIgnoreCase("other")){
			selectPrimaryOccupantOther();
			setPrimaryOccupantInfo(dtl.other);
		} else {
			throw new ItemNotFoundException("Primary Occupant should be either memeber or other");
		}

		if(isGroupRate){// select the rate type radio button, family or group
			this.selectGroupRate();
		}

		if(isPromoDiscount){//set promotion code discount
			this.setPromoCode(dtl.promoCode);
		}

		if (isDiscount) {//Customer pass or type selected
			if(dtl.isMulDiscount){
				this.selectDiscounts(dtl.primaryOccupantPasses);// check multiple customer passes or types
			} else {
				this.selectDiscount(dtl.primaryOccupantPass);//check customer pass or type
			}
			if(needCustomerPass()) {//customer pass information needed for the selected discount
				if(dtl.customerPassNumber!=null && dtl.customerPassNumber.length()>0) {
					//customer pass information is provided
					setCustomerPassInfo(dtl.customerPassNumber,dtl.customerPassExpireMonth,
							dtl.customerPassExpireYear, dtl.primaryOccupantPass);
				} else {
					//discount is dynamically selected, by default the first oneL
					//customer pass info need to be retrieved from database
					setCustomerPassInfo(contract,dtl.primaryOccupantPass);
				}
			}
		}

		//Interagency annual pass
		if(StringUtil.notEmpty(dtl.interagencyAnnualPassQuantity)){
			selectBuyInteragencyPassCheckBox();
			selectInteragencyQuantity(dtl.interagencyAnnualPassQuantity);
			if(dtl.isExpeditedDeliveryMethod){
				selectExpeditedDeliveryMethod();
			}else selectStandardDeliveryMethod();
		}
		
		//Serialize pass
		if(isAnnualDayUsePassExist()){
			if(dtl.isAnnualDayUsePass){
				selectAnnualDayUsePass();
			}
			if(dtl.isAnnualCampingPass){
				selectAnnualCampingPass();
				if(dtl.isOutOfState){
					selectAnnualCampingPassOutOfState();
				}else selectAnnualCampingPassInState();
			}
		}
		
		this.selectCheckBoxAgreementAccepted();
	}

	private String getObjIDByLabel(String labelReg) {
		IHtmlObject[] objs = browser.getHtmlObject(labelProp(labelReg));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Can't find the div for " + labelReg);
		}
		String forValue = objs[0].getAttributeValue("for");
		Browser.unregister(objs);
		return forValue;
	}

	public void selectBuyInteragencyPassCheckBox(){
		browser.selectCheckBox(buyInteragencyPass());
	}

	public void selectInteragencyQuantity(String interagencyPassQuantity){
		browser.selectDropdownList(interagencyPassQuantity(), interagencyPassQuantity);
	}

	public String getInteragencyQuantity(){
		return browser.getDropdownListValue(interagencyPassQuantity(), 0);
	}

	public List<String> getInteragencyQuantities(){
		return browser.getDropdownElements(interagencyPassQuantity());
	}

	//	public void selectStandardDeliveryMethod(){
	//	browser.selectRadioButton(standardDeliveryMethod(), 0);
	//}

	public void selectStandardDeliveryMethod(){
		browser.clickGuiObject(standardDeliveryMethod(getObjIDByLabel(LABEL_DOMESTICSTANDARD)));
	}

	//public void selectExpeditedDeliveryMethod(){
	//	browser.selectRadioButton(expeditedDeliveryMethod(), 0);
	//}

	public void selectExpeditedDeliveryMethod(){
		browser.clickGuiObject(expeditedDeliveryMethod(getObjIDByLabel(LABEL_DOMESTICEXPEDITED)));
	}

	public String interagencyAnualPassDIVContent(){
		return browser.getObjectText(interagencyAnualPassDIV());
	}

	public String getPassDesc(){
		return browser.getObjectText(passDesc());
	}

	public String getPrice(){
		return getPassDesc().split(LABEL_PRICE)[1];
	}

	public void selectAnnualDayUsePass(){
		browser.selectCheckBox(annualDayUsePass());
	}
	
	public boolean isAnnualDayUsePassExist(){
		return browser.checkHtmlObjectExists(annualDayUsePass());
	}
	
	public void selectAnnualCampingPass(){
		browser.selectCheckBox(annualCampingPass(getObjIDByLabel(LABEL_ANNUALCAMPINGPASS)));
	}
	
	public void selectAnnualCampingPassInState(){
		browser.selectRadioButton(annualCampingPassInState(), 0);
	}
	
	public void selectAnnualCampingPassOutOfState(){
		browser.selectRadioButton(annualCampingPassOutState(), 0);
	}
	
	/**
	 * Fill in order details info.
	 * @param dtl - order details info
	 * @param isDiscount - whether there is discount want to be applied.
	 * @return
	 */
	public void setOrderDetails(OrderDetails dtl, String contract, boolean isDiscount) {
		setOrderDetails(dtl, contract, isDiscount, false, false);
	}

	/**
	 * Fill in order details without discount information.
	 * @param dtl - order details info
	 */
	public void setOrderDetails(OrderDetails dtl, String contract) {
		this.setOrderDetails(dtl, contract,false,false,false);
	}

	/**
	 * Fill in equipment type and length, occupant and vehicle number.
	 * @param equipType - equipment type
	 * @param equipLength - equipment length
	 * @param occNum - occupant number
	 * @param vehNum - vehicle number
	 */
	public void setEquipment(String equipType, String equipLength,
			String occNum, String vehNum) {
		// 1. check if Primary Equipment needs to be select or not.
		if (StringUtil.isEmpty(equipType))
			equipType = checkPrimaryEquipment();

		if (equipType != null)
			selectEquipType(equipType);

		// 2. check if the equipment length needs to be filled in or not.
		// 3. if yes, what's the restriction.

		if (equipLength == null || equipLength == "")
			equipLength = checkEqpLength();
		if (equipLength != null)
			this.setVehicleLength(equipLength);

		// 4. check the restriction of number of occupants
		String minOccNum=getMinOccuptants();
		if (null == occNum || occNum.equals("")|| Integer.parseInt(occNum)<Integer.parseInt(minOccNum))
			occNum = minOccNum;
		this.setNumOfOccupants(occNum);

		// 5. check the restriction of number of vehicles. if no input, we accept the default value: 0
		if (vehNum != null&&vehNum.length()>0)
			this.setNumOfVehicles(vehNum);
	}
	/**
	 * The user doesn't enter the type. Select a equipment type automatically
	 *  1. None
	 *  2. Tent
	 *  3. 1st option in the list which are neither "None" or "Tent
	 *  4. Return null, if the equipment type field doesn't exist or is disabled
	 * @return
	 */
	private String checkPrimaryEquipment() {
		IHtmlObject[] objs=getObject("list_equip");
		String equp=null;
		if(objs.length>0) {
			IHtmlObject list_equip=objs[0];
			if (list_equip.exists() && (list_equip.isEnabled())) { // if we have to select one. 1. select no equipment 2. select tent 3. select the first equipment in the list
				if (((String) (list_equip.getProperty(".text"))).matches(reSelectEqp)) {
					if (((String) (list_equip.getProperty(".text"))).matches(reNoEqp)) {
						equp= getEqpType(reNoEqp);
					} else if (((String) (list_equip.getProperty(".text"))).matches(reTent))
						equp= getEqpType(reTent);
					else {
						equp= getEqpType(null);
					}
				}
			}
		}
		Browser.unregister(objs);
		return equp;
	}
	/**
	 * Retrieve the exact equipment type by given type string.
	 * @param type - type string used for comparing to and get the exact type.
	 * @return - exact equipment type
	 */
	private String getEqpType(String type) {
		IHtmlObject[] objs=getObject("list_equip");
		ISelect list_equip = (ISelect)objs[0];
		String toReturn = null;
		List<String> options=list_equip.getAllOptions();

		for (int i = 0; i < options.size(); i++) {
			String option = options.get(i);
			if (type == null) {
				if (!option.matches(reSelectEqp) && !option.matches(reNoEqp))
					toReturn = option;
			} else {
				if (option.matches(type))
					toReturn =option;
			}
		}
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Select equipment type by given type.
	 * @param type - Type supposed to select
	 */
	private void selectEquipType(String type) {
		IHtmlObject[] objs=getObject("list_equip");
		ISelect list_equip = (ISelect)objs[0];
		if (list_equip != null	&& list_equip.isEnabled()) {
			list_equip.select(type);
		}

		Browser.unregister(objs);
	}

	/**
	 * The user doesn't enter the length. use 1 as the default value
	 * Return null, if the equipment length field doesn't exist or is disabled.
	 * @return
	 */
	private String checkEqpLength() {
		IHtmlObject text_vehicleLength = null;
		String toReturn = null;
		IHtmlObject[] foundTOs=null;
		try {
			//if(text_vehicleLength.exists() && text_vehicleLength.isEnabled())
			foundTOs = browser.getHtmlObject(".name",
					"vehicleLength");
			if (foundTOs.length == 1)
				text_vehicleLength = (IHtmlObject) foundTOs[0];

			if (text_vehicleLength != null && text_vehicleLength.isEnabled()) {
				text_vehicleLength.click();
				toReturn = "1";
			}
			Browser.unregister(foundTOs);
		} catch (Exception e) {
			toReturn = null;
		} finally {
			Browser.unregister(foundTOs);
		}
		return toReturn;
	}

	/**
	 * Retrieve the minimum occupants number.
	 * @return - number of minimum occupants
	 */
	private String getMinOccuptants() {
		IHtmlObject[] objs=browser.getTableTestObject(".id", new RegularExpression("(table_)?occupants", false)); //("table_occupants"); //Sara[8/28/2013]
		IHtmlTable tableGrid =(IHtmlTable) objs[0];
		String toReturn = "1";

		for (int i = 0; i < tableGrid.rowCount(); i++) {
			String rowText;
			try {
				rowText = tableGrid.getCellValue(i, 1).toString();
			} catch (Exception e) {
				continue;
			}

			if (rowText.matches(".*min.*[0-9]+.*max.*[0-9]+.*")) {
				String[] strs = RegularExpression.getMatches(rowText, "[0-9]+");
				if (Integer.parseInt(strs[0]) > 0)
					toReturn = strs[0];
			}
		}
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Verify whether or not need to fill in customer pass discount field.
	 * @return
	 */
	public boolean needCustomerPass() {
		boolean need=false;
		RegularExpression pattern=new RegularExpression("cpassnum_\\d+",false);
		IHtmlObject[] objs=browser.getHtmlObject(".id",pattern);
		if(objs.length>0) {
			for(int i=0;i<objs.length;i++) {
				int width=Integer.parseInt(objs[i].getProperty(".offsetWidth").toString());

				if(width>0) {
					need=true;
					break;
				}
			}	
		}
		return need;
	}

	/**
	 * Select None discount radio button.
	 * @return
	 */
	public boolean selectNoneDiscountOption() {
		boolean noneExists=false;
		int index=-1;
		String[] discounts=getAllDiscountOptions();

		if(discounts==null || discounts.length<1) {
			return true; //no discount avaiLABEL, implies None discount option
		}

		for(int i=0;i<discounts.length;i++) {
			if(discounts[i].equalsIgnoreCase("none")) {
				noneExists=true;
				index=i;
				break;
			}
		}
		if(noneExists) {
			selectDiscountOption(index);
		}
		return noneExists;
	}

	/**
	 * Select special discount by given discount index.
	 * @param index - index of discount
	 * @return - checked discount name
	 */
	public String selectDiscountOption(int index) {
		String[] passes=getAllDiscountOptions();
		if(passes==null) {
			throw new ItemNotFoundException("No discount found");
		} else if (index>=passes.length) {
			throw new ItemNotFoundException("Index "+index+" is bigger than the number of passes "+passes.length);
		}
		RegularExpression pattern=new RegularExpression("discount.?\\d+",false);
		browser.selectRadioButton(".id",pattern,index);

		return passes[index];
	}

	/**
	 * Fill in the pass number.
	 * @param passNumber
	 * @param passName - customer pass name
	 */
	public void setPassNumber(String passNumber, String passName) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", passName);
		String index=objs[0].getProperty("htmlFor").toString().replaceAll("cpass","");

		browser.setTextField(".id", "cpassnum_" + index, passNumber, false, 0);
		Browser.unregister(objs);
	}

	/**
	 * set expire month.
	 * @param mon - expire month
	 * @param passName - customer pass name
	 */
	public void setExpireMonth(String mon, String passName) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", passName);
		String index=objs[0].getProperty("htmlFor").toString().replaceAll("cpass", "");

		browser.setTextField(".id", "cpassexpm_" + index, mon, false, 0);
		Browser.unregister(objs); 
	}

	/**
	 * set expire year.
	 * @param year - expire year
	 * @param passName - customer pass name
	 */
	public void setExpireYear(String year, String passName) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", passName);
		String index=objs[0].getProperty("htmlFor").toString().replaceAll("cpass", "");

		browser.setTextField(".id", "cpassexpy_" + index, year, false, 0);
		Browser.unregister(objs);
	}

	/**
	 * Fill in all customer pass info by data retrieved from DB
	 * @param contract - contract code
	 * @param passName - customer pass name
	 */
	public void setCustomerPassInfo(String contract, String passName) {
		String[] passInfo=getCustomerPassInfo(passName,contract);
		setPassNumber(passInfo[0],passName);
		if(null !=passInfo[1] && passInfo[1].length()>0 && !passInfo[1].equalsIgnoreCase("null")) {
			int[] tokens=DateFunctions.getDateComponents(passInfo[1]);

			setExpireMonth(tokens[0]+"", passName);
			setExpireYear(tokens[2]+"", passName);
		}
	}

	/**
	 * Fill in all customer pass info by data provided by user
	 * @param passNumber
	 * @param expMon
	 * @param expYear
	 * @param passName
	 */
	public void setCustomerPassInfo(String passNumber, String expMon, String expYear, String passName) {
		setPassNumber(passNumber, passName);
		setExpireMonth(expMon, passName);
		setExpireYear(expYear, passName);
	}

	/**
	 * retrieve the customer pass number and expire date, if there is no pass number, then will insert one
	 * to DB, set the expire date to a year later.
	 * @param passName
	 * @param contract
	 * @return
	 */
	public String[] getCustomerPassInfo(String passName, String contract) {
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		String schema=DataBaseFunctions.getSchemaName(contract,TestProperty.getProperty("target_env"));
		db.resetSchema(schema);

		String query="select p.pass_number,to_char(p.expiry_date,'yyyy-mm-dd') as expiry_date from d_ref_cust_pass p, d_ref_cust_pass_type t where p.pass_type_id=t.id and UPPER(t.name)=UPPER('"+passName+"') and t.active_ind=1";
		String[] colNames={"PASS_NUMBER","EXPIRY_DATE"};

		List<String[]> result=db.executeQuery(query,colNames);	

		if(result.size()<1) {
			String expireDate=DateFunctions.getDateAfterToday(365);
			expireDate=DateFunctions.formatDate(expireDate,"yyyy-MM-dd");
			String query1="select id from d_ref_cust_pass_type where UPPER(name)=UPPER('"+passName+"')";

			String passTypeId=db.executeQuery(query1,"id",0);

			String query2="insert into d_ref_cust_pass(id,pass_type_id,pass_number,expiry_date,active_ind,import_id,create_date,create_user,last_modify_date,last_modify_user) values (get_sequence('d_ref_cust_pass'), "+passTypeId+",'12345', to_date('"+expireDate+"','yyyy-mm-dd'), '1', null, CURRENT_DATE,3,CURRENT_DATE,3)";
			db.executeUpdate(query2);

			result=db.executeQuery(query,colNames);
		}
		return (String[])result.get(0);
	}

	/**
	 * Retrieve all discounts name to a string array.
	 * @return - discounts name array
	 */
	public String[] getAllDiscountOptions() {
		IHtmlObject[] objs=browser.getHtmlObject(".class","Html.LABEL",".id","occupants");

		String[] discounts=null;
		int index=9999;

		//all labels which are not a discount option
		//#0 Number of Occupants
		//#1 Number of Vehicles
		//#2 Astra LoadTest
		//#3 Other
		//#4 First Name
		//#5 Last Name
		//#6 Address
		//#7 Address
		//#8 City
		//#9 State/Province
		//#10 Zip/Postal code
		//#11 Country
		//#12 Phone Number
		//#13 Email Address (For Reservation Reminders)
		// the last one is always "Email Address (For Reservation Reminders)"
		//find the index
		for(int i=0;i<objs.length;i++) {
			if(objs[i].getProperty(".text") !=null && objs[i].getProperty(".text").toString().equalsIgnoreCase("Email Address (For Reservation Reminders)")) {
				index=i+1;
			}
		}

		if(index<objs.length) {
			discounts=new String[objs.length-index];
			for(int i=index;i<objs.length;i++) {
				discounts[i-index]=objs[i].getProperty(".text").toString();
				if(discounts[i-index].endsWith(":")) {
					discounts[i-index]=discounts[i-index].substring(0,discounts[i-index].length()-1);
				}
			}
		}

		return discounts;
	}

	public String[] getAllAvaiLABELCustomerPasses(String contract) {
		String[] custPasses=null;
		AwoDatabase db = (AwoDatabase) AwoDatabase.getInstance();
		String schema=DataBaseFunctions.getSchemaName(contract,TestProperty.getProperty("target_env"));
		db.resetSchema(schema);

		String query="select id,name from d_ref_cust_pass_type where active_ind=1 order by id";

		List<String> ids=db.executeQuery(query,"id");
		if(ids.size()>0) {
			custPasses=new String[ids.size()];
		} else {
			return null;
		}

		for(int i=0;i<ids.size();i++ ){
			custPasses[i]=ids.get(i).toString();
		}

		return custPasses;
	}

	/**
	 * Retrieve the discount index by name.
	 * @param discountName - discount name
	 * @return - discount index
	 */
	public int getDiscountIndex(String discountName) {
		String[] discounts=getAllDiscountOptions();

		int index=-1;
		for(int i=0;discounts!=null && i<discounts.length;i++) {
			if(discounts[i].trim().equalsIgnoreCase(discountName)) {
				index=i;
				break;
			}
		}

		return index;
	}

	/**
	 * Select the discount check box by given name.
	 * @param discountName - discount name
	 */
	public void selectDiscount(String discountName) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", discountName);//can not use Regular Express as some discount may have same name

		if(objs.length<1){
			throw new ItemNotFoundException("Failed to find discount '"+discountName+"'.");
		}else {
			String objID=objs[0].getProperty("htmlFor").toString();
			IHtmlObject[] disCheckBox=browser.getCheckBox(".id",objID);
			if(disCheckBox.length<1){
				throw new ErrorOnPageException("Discount checkbox's id is not label's htmlFor property.");
			}else {
				//		  		if(disCheckBox[0].getProperty(".defaultChecked").equalsIgnoreCase("False"))
				//		  			disCheckBox[0].click();
				((ICheckBox)disCheckBox[0]).select();
			}
			Browser.unregister(disCheckBox);
		}
		Browser.unregister(objs);
	}

	/**
	 * Check multiple customer passes or types.
	 * @param discounts - discounts array
	 * @param isMulti - Need to check more than one customer pass or type
	 */
	public void selectDiscounts(String[] discounts) {
		if(discounts.length<1){
			throw new ItemNotFoundException("Discounts array has not been initialized...");
		} else {
			for(int i=0; i<discounts.length; i++){
				this.selectDiscount(discounts[i]);
			}
		}
	}

	/**
	 * Verify whether or not the given discount has been selected automatically.
	 * @param discountName - discount name, case sensitive
	 * @return True - self-selected; false - Not selected
	 */
	public boolean isDiscountSelfSelected(String discountName){
		boolean toReturn = false;
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.LABEL", ".text", discountName);//can not use Regular Express as some discount may have same name
		if(objs.length<1){
			throw new ItemNotFoundException("Failed to find discount '"+discountName+"'.");
		}else {
			String objID=objs[0].getProperty("htmlFor").toString();
			IHtmlObject[] disCheckBox=browser.getCheckBox(".id",objID);
			if(disCheckBox.length<1){
				throw new ErrorOnPageException("Discount checkbox's id is not label's htmlFor property.");
			}else {
				if(disCheckBox[0].getProperty(".defaultChecked").equalsIgnoreCase("true"))
					toReturn = true;
			}
			Browser.unregister(disCheckBox);
		}
		Browser.unregister(objs);

		return toReturn;
	}

	/**
	 * Retrieve arrival date.
	 * @return - retrieved arrival date
	 */
	public String getArrivalDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id","campname");
		String date = objs[0].getProperty(".text").toString().split("Arrival:")[1].
				split("Departure:")[0].trim();
		Browser.unregister(objs);
		return date;
	}
	/**
	 * Retrieve department date.
	 * @return - department date
	 */
	public String getDepartDate() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.DIV",".id","campname");
		String date = objs[0].getProperty(".text").toString().split("Departure:")[1].
				split("Length of stay:")[0].trim();
		Browser.unregister(objs);
		return date;
	}
	/**
	 * Retrieve the length of stay.
	 * @return - length of stay
	 */
	public String getLengthOfStay() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".id", "lenghstay");
		String length = objs[0].getProperty(".text").toString().split(
				" \\(nights\\)")[0];

		Browser.unregister(objs);
		return length;
	}
	/**
	 * go to shopping cart by clicking the link Items In Cart:
	 */
	public void gotoCart() {
		//		browser.clickGuiObject(".class", "Html.A", ".text",
		//				new RegularExpression("Items In Cart: \\d+", false));
		//Quentin[20130905] For current new RA.com UI design, 'Items In Cart:' is changed to 'Items:' and not clickable, replaced with 'Check Out Now' link

		Property oldProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", new RegularExpression("Items In Cart: \\d+", false));
		Property newProperty[] = Property.toPropertyArray(".class", "Html.A", ".text", "Check Out Now");
		if(browser.checkHtmlObjectExists(oldProperty)) {
			browser.clickGuiObject(oldProperty);
		} else if(browser.checkHtmlObjectExists(newProperty)) {
			browser.clickGuiObject(newProperty);
		}
	}
	/**
	 * Go to shopping cart page by clicking Continue to shopping cart link.
	 */
	public void gotoShoppingCart() {
		IHtmlObject[] objs=getObject("button_continueToShoppingCarts");
		objs[0].click();
		Browser.unregister(objs);
	}

	/**
	 * Fill in all kind of occupants type with given number, if not provided, 1 is default
	 * @param od - order details data
	 */
	public void setAllOccupants(OrderDetails od) {
		IHtmlObject[] objs=browser.getTextField(".id","qtypersonsid");
		IHtmlObject[] label=browser.getHtmlObject(".class","Html.LABEL","for","qtypersonsid");

		String type="";
		for (int i=0; i<label.length; i++) {
			type=label[i].getProperty(".text").toString().split("\\(")[0].trim();
			if(type.equalsIgnoreCase("Adult")){//add other occupants when want to use this methods
				if(null!=od.numAdult && od.numAdult.length()>0){
					((IText)objs[i]).setText(od.numAdult);
				}else {//just set default to 1, if all occu total large than max, should be a defect
					((IText)objs[i]).setText("1");
				}
			}else if(type.equalsIgnoreCase("Senior")){
				if(null!=od.numSenior && od.numSenior.length()>0){
					((IText)objs[i]).setText(od.numSenior);
				}else {
					((IText)objs[i]).setText("1");
				}
			}else if(type.equalsIgnoreCase("Children")){
				if(null!=od.numChildren && od.numChildren.length()>0){
					((IText)objs[i]).setText(od.numChildren);
				}else {
					((IText)objs[i]).setText("1");
				}
			}
		}
		Browser.unregister(objs,label);
	}

	/**
	 * Select the equipment type and fill in the length if necessary.
	 * @param equipType - equipment type
	 * @param equipLength - equipement length
	 */
	public void setEquipment(String equipType, String equipLength) {
		// 1. check if Primary Equipment needs to be select or not.
		if (null == equipType || equipType.equals(""))
			equipType = checkPrimaryEquipment();

		if (equipType != null)
			selectEquipType(equipType);

		// 2. check if the equipment length needs to be filled in or not.
		// 3. if yes, what's the restriction.
		if (null == equipLength || equipLength.length()<1)
			equipLength = checkEqpLength();
		if (equipLength != null && equipLength.length()>0)
			this.setVehicleLength(equipLength);
	}

	/**
	 * Fill in the vehicle number, it accept default value 0 if not provided.
	 * @param vehNum - number of vehicle 
	 */
	public void setVehicleNum(String vehNum) {
		if (vehNum != null && vehNum.length()>0)
			this.setNumOfVehicles(vehNum);
	}

	/**
	 * Fill in promotion code discount.
	 * @param code - promotion code
	 */
	public void setPromoCode(String code){
		browser.setTextField(".id", "promocode", code);
	}

	/**
	 * Method getCustPassValidationMsg
	 * @return
	 */
	public String getCustPassValidationMsg() {
		IHtmlObject objs[] = browser.getHtmlObject(".id", "occupants");
		String msg = "";
		if(objs.length > 0) {
			msg = objs[0].getProperty(".text").trim();
		}
		Browser.unregister(objs);
		return msg;
	}

	/**Check the special Pass pos*/
	public void checkSpecialPassPOS() {
		browser.selectCheckBox(".id", "pass");
	}

	/**
	 * Check the minimum donation amount radio button and get the amount
	 * @return minimum amount
	 */
	public String checkMinFixedDonationAmnt() {
		//assume the first radio button whose value is not "0" and "-1" contain the min value
		RegularExpression value=new RegularExpression("^[1-9]\\d*",false);
		IHtmlObject[] objs=browser.getRadioButton(".name", "donation",".value",value);
		((IRadioButton)objs[0]).select();
		String amnt = objs[0].getProperty(".value");
		Browser.unregister(objs);
		return amnt;
	}

	/**
	 * Check the maximum donation amount radio button and get the max amount.
	 * @return - maximum amount
	 */
	public String checkMaxFixedDonationAmnt() {
		RegularExpression value=new RegularExpression("^[1-9]\\d*",false);
		IHtmlObject[] objs=browser.getRadioButton(".name", "donation",".value",value);
		((IRadioButton)objs[objs.length-1]).select();
		String amnt = objs[objs.length-1].getProperty(".value");
		Browser.unregister(objs);

		return amnt;
	}

	/**Check and fill in the amount for Other donation option.*/
	public void checkOtherDonationAmnt(String amount) {
		browser.selectRadioButton(".name", "donation", ".value","-1");
		browser.setTextField(".id","amount", amount);
	}

	/**Check no donation option.*/
	public void checkNoDonationAmnt() {
		browser.selectRadioButton(".name", "donation", ".value","0");
	}

	/**
	 * Verify whether there is donation field html section in order details page.
	 * @return true - exist
	 */
	public boolean needDonation() {
		return browser.checkHtmlObjectExists(".class","Html.INPUT.radio",".name","donation");
	}

	//	/**
	//	 * Check the "Items In Cart:" link exists
	//	 * @return
	//	 */
	//	public boolean isItemsInCartLinkExists(){
	//		HtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false));
	//		boolean toReturn = false;
	//		
	//		if(objs.length > 0){
	//			toReturn = true;
	//		}
	//		Browser.unregister(objs);
	//		return toReturn;
	//	}
	//	
	//	/**
	//	 * Click the "Items In Cart:" link to go to shopping cart page directly, and this link will display at some pages, such as Site Detail, Order Details, etc.
	//	 */
	//	public void clickItemsInCartLinkToShoopingcartPg() {
	//		HtmlObject objs[] = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Items In Cart:.*", false));
	//		
	//		if(objs.length > 0){
	//			objs[0].click();
	//		}
	//		Browser.unregister(objs);
	//	}

	public String getPassOfferInfo(String title)
	{
		IHtmlObject[] divs = browser.getHtmlObject(".className", "content shop", ".text", new RegularExpression("^"+title, false));
		String info = divs[0].text();

		Browser.unregister(divs);
		return info;
	}
	/**
	 * click other check box.
	 */
	public void selectOtherRadioButton(){
		browser.selectRadioButton(".id", "primaryOccupant_Other");
	}
	/**
	 * set first name.
	 * @param fName
	 */
	public void setFirstName(String fName){
		browser.setTextField(".id", "fname", fName);
	}
	/**
	 * set last name.
	 * @param lName
	 */
	public void setLastName(String lName){
		browser.setTextField(".id", "lname", lName);
	}
	/**
	 * address
	 * @param address
	 */
	public void setAddress(String address){
		browser.setTextField(".id", "address", address);
	}
	/**
	 * set city.
	 * @param city
	 */
	public void setCity(String city){
		browser.setTextField(".id", "city", city);
	}
	/**
	 * select state.
	 * @param state
	 */
	public void selectState(String state){
		browser.selectDropdownList(".id", "stateProvince", state);
	}

	/**
	 * set zip
	 * @param zip
	 */
	public void setZip(String zip){
		browser.setTextField(".id", "zip", zip);
	}
	/**
	 * select  country.
	 * @param country
	 */
	public void selectCountry(String country){
		browser.selectDropdownList(".id", "country", country);
	}
	/**
	 * set phone number.
	 * @param phone
	 */
	public void setPhoneNumber(String phone){
		browser.setTextField(".id", "hphone", phone);
	}

	public void selectPassOffer(String offer)
	{
		IHtmlObject[] tbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", "contributions");
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".text",new RegularExpression("^"+offer,false),tbl[tbl.length-1]);
		if(div.length>0){
			browser.selectCheckBox(".id", new RegularExpression("pass\\d+", false), 0, div[0]);
		}else throw new ObjectNotFoundException("Can't find any pass DIV.");
		
		Browser.unregister(div);
		Browser.unregister(tbl);

	}
	public void selectPassSubOffer(String subOffer)
	{
		IHtmlObject[] tbl = browser.getHtmlObject(".class", "Html.TABLE", ".id", "contributions");
		IHtmlObject[] div = browser.getHtmlObject(".class", "Html.DIV", ".text",new RegularExpression("^"+subOffer,false),tbl[0]);
		browser.selectRadioButton(Property.toPropertyArray(".name", "pass2rb"), true, 0, div[0]);
		Browser.unregister(div);
		Browser.unregister(tbl);

	}
	/**
	 * set email.
	 * @param email
	 */
	public void setEmail(String email){
		browser.setTextField(".id", "email", email);
	}
	/**
	 * set Primary Occ
	 * @param cust
	 */
	public void setPrimaryOccupantInfo(Customer cust){
		this.selectOtherRadioButton();
		this.setFirstName(cust.fName);
		this.setLastName(cust.lName);
		this.setAddress(cust.address);
		this.setCity(cust.city);
		this.selectState(cust.state);
		this.setZip(cust.zip);
		this.selectCountry(cust.country);
		this.setPhoneNumber(cust.hPhone);
		this.setEmail(cust.email);
	}

	public void setPrimaryOccupantInfo(MemberProfile cust){
		this.selectOtherRadioButton();
		this.setFirstName(cust.firstName);
		this.setLastName(cust.lastName);
		this.setAddress(cust.address);
		this.setCity(cust.city);
		this.selectState(cust.state);
		this.setZip(cust.postalCode);
		this.selectCountry(cust.country);
		this.setPhoneNumber(cust.homePhone);
		this.setEmail(cust.email);
	}

}
