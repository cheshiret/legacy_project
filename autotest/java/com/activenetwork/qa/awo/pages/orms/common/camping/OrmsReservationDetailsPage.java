/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.CustPass;
import com.activenetwork.qa.awo.datacollection.legacy.CustType;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.VehicleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationDetailsCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jdu Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsReservationDetailsPage extends OrmsReservationDetailsCommonPage {

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsReservationDetailsPage _instance = null;

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationDetailsPage();
		}

		return _instance;
	}

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsReservationDetailsPage() {
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return getMainFrame() != 0 && browser.checkHtmlObjectExists(".class", "Html.TABLE", ".id", "ReservationDetails");
	}

	/**
	 * Select the customer pass type
	 *
	 * @param custPass
	 */
	public void selectCustPass(String custPass) {
		if(StringUtil.notEmpty(custPass)) {
			browser.selectDropdownList(".id", "passTypeId", custPass, 1);
		}
	}

	/**
	 * Set the pass number
	 *
	 * @param passNum
	 */
	public void setPassNum(String passNum) {
		IHtmlObject objs[] = browser.getTextField(".id", "passNumber");
		if(objs.length > 0) {
			((IText)objs[objs.length - 1]).setText(passNum);
		}
		Browser.unregister(objs);
	}

	/**
	 * Set the access pass note
	 *
	 * @param note
	 */
	public void setAccessPassNote(String note) {
		IHtmlObject objs[] = browser.getTextField(".id", "notes");
		if(objs.length > 0) {
			((IText)objs[objs.length - 1]).setText(note);
		}
		Browser.unregister(objs);
	}

	/**
	 * Set the access pass note
	 *
	 * @param note
	 */
	public void setAccessPassHolderName(String HolderName) {
		IHtmlObject objs[] = browser.getTextField(".id", "holderName");
		if(objs.length > 0) {
			((IText)objs[objs.length - 1]).setText(HolderName);
		}
		Browser.unregister(objs);
	}

	/**
	 * Set the access pass note
	 *
	 * @param note
	 */
	public void setAccessPassExpireDate(String date) {
		IHtmlObject objs[] = browser.getTextField(".id", "expiryDate_ForDisplay");
		if(objs.length > 0) {
			((IText)objs[objs.length - 1]).setText(date);
		}
		Browser.unregister(objs);
	}

	/**
	 * Get reservation details cell value
	 *
	 * @param row
	 *            --The row of the cell
	 * @param col
	 *            --The col of the cell
	 * @return---Get the cell value
	 */
	public String getReservationDetailsCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"ReservationDetails");

		String toReturn = ((IHtmlTable) objs[0]).getCellValue(row, col);
		Browser.unregister(objs);
		return toReturn;
	}

	/** Get site name in the reservation detail page */
	public String getSiteName() {
		// String toParse = getReservationDetailsCellValue(2,1);
		//
		// if(toParse.indexOf("Occupied Site#")!= -1){
		// // end = toParse.indexOf("Occupied Site#");
		// return
		// MiscFunctions.getSubstring(toParse,"Site Group# (Name)","Occupied Site#");
		// }else{
		// return
		// MiscFunctions.getSubstring(toParse,"Site# (Name)","Type of Use");
		// }
		String name = null;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id",
				"siteName");
		if (objs.length > 0)
			name = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return name;

	}

	/** Retrieve the site number from the page */
	public String getSiteNum() {

		String siteName = getSiteName();

		String[] temp = siteName.split("-");

		if (temp.length == 2) {
			return temp[0];
		}

		for (int i = 1; i < siteName.length(); i++) {
			String sub = siteName.substring(0, i);
			if (siteName.substring(i).indexOf(sub) != -1) {
				continue;
			} else {
				return siteName.substring(0, i - 1);
			}
		}
		return null;
	}

	/** Get Site ID */
	public String getSiteID() throws PageNotFoundException {
		String siteID = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id",
				"siteName");
		String s = objs[0].getProperty(".href").toString();
		Browser.unregister(objs);
		// note: href text is different between FM and CM
		// Sample FM href:
		// javascript:void(top.showMapFocusSite("BC6788AC-7C36-C3D7-FDC4-05BD1F22046E","1943"))
		// Sample CM href:
		// javascript:void(top.showParkMap("10341","1943","01ABA115-F266-D6FC-8329-F55C21A80A0E"))
		String[] tokens = RegularExpression.getMatches(s, "\"[0-9]+\"");
		if (tokens.length < 1)
			throw new PageNotFoundException("Failed to get siteID.");
		siteID = tokens[tokens.length - 1];
		siteID = siteID.replaceAll("\"", "");
		logger.info("Get siteID=" + siteID);
		return siteID;
	}

	/** get the occupied site name */
//	private String getOccupiedSite() {
//		String toParse = getReservationDetailsCellValue(2, 1);
//
//		int start = toParse.indexOf("Site# (Name)") + "Site# (Name)".length();
//		// Park LEWEY LAKE Area Sites 1 - 100 Site Group# (Name)
//		// CDEB40N-LEWECDDB40N Occupied Site# (Name) 006-LEWECDDB406 Type of Use
//		// OverNight Permit #
//		// Park ALLEGANY STATE PARK Area Group Camps Site# (Name)
//		// 010-REDCSATGROP10 Type of Use OverNight Permit # 736866
//		int end = toParse.indexOf("Type of Use");
//		return toParse.substring(start + 1, end).trim();
//	}

	/** Click res action button */
	protected void clickResActionButton(String buttonName) {
		if (buttonName.equalsIgnoreCase("OK")) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression("OK|Finish", false), true);
		} else {
			IHtmlObject[] frames=browser.getFrame("transaction");
			IHtmlObject top=(frames==null|frames.length<1)?null:frames[0];
			
			browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("^" + buttonName + "$", false), true, 0, top);
			
			Browser.unregister(frames);
		}
	}

	/** Click resaction button */
	private void clickResActionButton(RegularExpression buttonName) {
		browser.clickGuiObject(".class", "Html.A", ".text", buttonName, true);
	}

	/**Get cell value of the charges page*/
	public String getChargeCellValue(int row, int col){
		IHtmlObject [] objs = browser.getHtmlObject(".class", "Html.TABLE",".text", new RegularExpression("^POS Sale#.*", false));
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];

		String toReturn = table.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}

	// public void addUnit(CampingUnit cUnit){
	// addUnit(cUnit.typeOfUnit, cUnit.length, cUnit.depth, cUnit.quantity);;
	// }

	/** Check specific button or link exist or not */
	public boolean checkBottonOrLinkExists(String name) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", name);
	}

	/** Check specific button or link exist or not */
	public boolean checkBottonOrLinkExists(RegularExpression reg) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", reg);
	}

	public int campingUnitAdded() {
		ArrayList<Property[]> list = new ArrayList<Property[]>();
		list.add(Property.toPropertyArray(".class", "Html.TABLE", ".id",new RegularExpression("campingInfoContainer_CampingUnit_\\d+",false)));
		list.add(Property.toPropertyArray(".class","Html.IMG",".name",new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits-row:\\d+_delete",false)));

		IHtmlObject[] buttons = browser.getHtmlObject(list);

		int size = buttons.length;
		Browser.unregister(buttons);
		return size;
	}

	public boolean checkAddCampingUtitExists() {
		return checkBottonOrLinkExists(new RegularExpression("Add Camping Unit", false));
	}
	
	public void clickAddCampingUnit() {
		clickResActionButton(new RegularExpression("Add Camping Unit", false));
	}
	
//	/**
//	 * Click add unit button and input the quality of the unit
//	 *
//	 * @param qty
//	 *            ---The quantity of the unit
//	 * @param plant
//	 *            -- camping_unit_license
//	 */
//	public void addAnyUnit(int qty, String plate, String unitstate) {
//
//		if (qty < 1)
//			qty = 1;
//		if (this.checkBottonOrLinkExists(new RegularExpression("Add Camping Unit", false))) {
//			this.clickResActionButton(new RegularExpression("Add Camping Unit", false));
//			if (browser.getDropdownElements(".id", "campingUnitType").size() > 0) {// If
//				// the
//				// unit
//				// drop
//				// down
//				// has
//				// elements,
//				// then
//				// input
//				// qty
//				browser.setTextField(".id", "qty", Integer.toString(qty), 1);
//				try {
//					IHtmlObject[] objs = browser.getTextField(".id",
//							"camping_unit_license");
//					if (objs.length > 1 && plate != null && !plate.equals("")) {
//						((IText) objs[1]).setText(plate);
//					}
//					Browser.unregister(objs);
//
//					// HtmlObject[]
//					// stateObj=browser.getDropdownList(".id","camping_unit_state");
//					// if(stateObj.length>1&&unitstate!=null&&unitstate.length()>0){
//					// ((ISelect)stateObj[1]).select(unitstate);
//					// }
//
//					// Browser.unregister(stateObj);
//				} catch (Exception e) {
//
//				}
//			}
//		}
//		browser.clickGuiObject(".class", "Html.LABEL", ".text", "Pets Allowed");
//	}

	/**
	 * Change qty of unit
	 *
	 * @param qty
	 */
	public void changeNumOfUnit(int qty) {
//		if (this.getNumOfRemoveButton() < 4) {
//			//this.clickResActionButton(new RegularExpression("^ ?Add Unit ?", false));
//			this.clickResActionButton(new RegularExpression("Add Camping Unit\\(s\\)", false));
//		}

//		browser.setTextField(".id", "qty", Integer.toString(qty), 1);
		browser.setTextField(".id", new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits-row:\\d+_camping_unit_quantity",false), Integer.toString(qty), 0);
	}

	public int getNumOfRemoveButton() {
//		RegularExpression reg = new RegularExpression("^ ?Remove", false);
		RegularExpression reg = new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits-row:\\d+_delete", false);
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".id",
				reg);

		return obj.length;
	}

	/**
	 * Click add unit button and input the quality of the unit
	 *
	 * @param qty
	 *            ---The quaility of the unit
	 */
//	public void addAnyUnit(int qty) {
//		this.addAnyUnit(qty, null, null);
//	}

	/** Check whether "Fast CheckIn" radio button available */
	public boolean isFastCheckInAvailable() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", "fastCheckin");

		return browser.checkHtmlObjectExists(p);
	}

	/** Select the "Fast Check-In" radio button **/
	public void selectFastCheckIn() {
		browser.selectRadioButton(".id", "fastCheckin", ".value", "true");
	}

	/** select the "Regular Check-In" radio button **/
	public void selectRegularCheckIn() {
		browser.selectRadioButton(".id", "fastCheckin", ".value", "false");
	}

	/**
	 * Check the regular check in whether exists or not
	 */
	public boolean regularCheckInIsExist(){
		return browser.checkHtmlObjectExists(".id", "fastCheckin", ".value", "false");
	}

	/**
	 * This method firstly check if the checkin checkbox exists.
	 */
	public void selectCheckIn() {
		browser.selectCheckBox(".id", "checkIn");
	}

	/** If the checkbox  is selected, it return true,or return false */
	public boolean checkInSelected() {
		return browser.isCheckBoxSelected(".id", "checkIn");
	}

	public boolean checkOutSelected() {
		return browser.isCheckBoxSelected(".id", "checkOut");
	}

	/**
	 * Select the promo code from the dropdown list
	 *
	 * @param promoCode
	 */
	public void selectPromoCode(String promoCode) {
		browser.selectDropdownList(".id", "orderPromoCode", promoCode);
	}

	/**
	 * Check whether the checkin checkbox exist
	 *
	 * @return
	 */
	public boolean isCheckInObjectExist() {
		return browser.checkHtmlObjectExists(".id", "checkIn");
	}

	/**
	 * If the checkbox exists and checked, this method unchecks the checkbox.
	 * Otherwise, it returns.
	 */
	public void unselectCheckIn() {
		browser.unSelectCheckBox(".id", "checkIn");
	}

	/**
	 * check same as customer
	 */
	public void selectSameAsCustomer(){
		browser.selectCheckBox(".id", "isSameAsCustomer");
	}

	/**
	 * Uncheck the same as customer to to add primary occupant
	 */
	public void unselectSameAsCustomer() {
		browser.unSelectCheckBox(".id", "isSameAsCustomer");
	}

	/**
	 * check the Same As Customer is checked or not
	 * @return
	 *            true: is checked
	 *            false: not checked
	 */
	public boolean isCheckedSameAsCustomer() {
		return browser.isCheckBoxSelected(".id", "isSameAsCustomer");
	}
	/**
	 * This method checks if the checkin checkbox exists and is checked.
	 *
	 * @return true - the checkin checkbox exists and is checked false -
	 *         otherwise
	 */
	public boolean checkInIsEnabled() {
		return browser.isCheckBoxSelected(".id", "checkIn");
	}

	/** If checkout checkbox enabled, select checkout checkbox */
	public void selectCheckOut() {
			browser.selectCheckBox(".id", "checkOut");
	}

	/**If checkout check box selected*/
	public boolean isCheckOutSelected(){
		return browser.isCheckBoxSelected(".id", "checkOut");
	}

	public boolean isCheckOutExist(){
		return browser.checkHtmlObjectExists(".id", "checkOut");
	}

	/** Don't select checkout checkbox */
	public void unselectCheckOut() {
		browser.unSelectCheckBox(".id", "checkOut");
	}

	/** Check whether checkout is enabled */
	public boolean checkOutIsEnabled() {
		return browser.isCheckBoxSelected(".id", "checkOut");
	}

	/** Check whether checkin checkbox is availabel */
	public boolean isAbleToCheckIn() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", "checkIn");
	}

	/** Check whether checkout checkbox is available */
	public boolean isAbleToCheckOut() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", "checkOut");
	}

	/** Click on the "OK" link */
	public void clickOK() {
//		RegularExpression regx = new RegularExpression(".*validReservationDetailForOK.*", false);
		Property[] p = Property.toPropertyArray(".class", "Html.A",".text", "OK");
		browser.clickGuiObject(p);
	}

	/** Click cancel button at the bottom of reservation details*/
	public void clickCancel() {
		Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","res-details");
		Property[] p2=Property.toPropertyArray(".class","Html.A",".text","Cancel");
		browser.clickGuiObject(Property.atList(p1,p2),true,0);
	}

	/** Click add unit button */
//	public void clickAddUnit() {
//		this.clickResActionButton("Add Unit");
//	}
	
	public boolean isAddCampingUnitsExists() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text", "Add Camping Unit(s)");	
	}
	
	public boolean isCampingUnitsDetailTableDisplay(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.DIV", ".text", new RegularExpression("# of Camping Units.*", false));
	}
	
	public void clickAddCampingUnits() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Camping Unit(s)");
	}

	public void removeCampingUnit(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}

	public void clickAddPets() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Pet(s)");
	}
	
	public void clickAddVehicles() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Vehicle(s)");
	}
	
	/**
	 * Click the Add Vehicle button
	 */
	public void clickAddVehicle() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add vehicle");
	}

	/***
	 * Click Adjust Fees To Past Paid button
	 */
	public void clickAdjustFeesToPastPaid() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Adjust Fees to Past Paid");
	}

	/**
	 * Select "ProofShown" checkbox
	 *
	 * @param array
	 *            ---if there are several proofshown checkbox, should specific
	 *            which one
	 */
	public void selectProofShown(int array) {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				"proofShown_for_display", array);
	}

	/**
	 * Check whether the Proof Shown checkbox available
	 *
	 * @return
	 */
	public boolean isProofShownExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", "proofShown_for_display");
	}

	/**
	 * Check the # of Other Occ is existed
	 * @return
	 *          true : exist
	 */
	public boolean isOtherOccNumExist(){
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",".id","otherOccList_control");
	}

	/**
	 * Get the default value of the # of Other Occ
	 * @return
	 * 			The defaul value
	 */
	public int getDefaulOtherOccNum() {
		int defaultNum = -1;
		if (isOtherOccNumExist()) {
			IHtmlObject[] objs = browser.getHtmlObject(".class","Html.INPUT.text", ".id", "otherOccList_control");
			defaultNum = Integer.parseInt(objs[0].getProperty(".defaultValue"));

			Browser.unregister(objs);
			return defaultNum;
		} else {
			return -1;
		}
	}

	/**
	 * Select reason
	 *
	 * @param reason
	 */
	public void selectReason(String reason) {
		if (!browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","reasonID"))
			return;

		if (reason!=null && reason.length()>0)
			browser.selectDropdownList("id", "reasonID", reason);
		else
			browser.selectDropdownList("id", "reasonID", 1);
	}
	
	public void selectOccupantType(String type){
		if (!browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","primaryOccType"))
			return;
		if (StringUtil.notEmpty(type)){
			browser.selectDropdownList("id", "primaryOccType", type);
		}
		else{
			if(browser.getDropdownElements(".id", "primaryOccType").size()>1){
				browser.selectDropdownList("id", "primaryOccType", 1);
			}
		}
	}

	public List<String> getChangeDateReasons(){
		return browser.getDropdownElements(".class", "Html.SELECT", ".id","reasonID");
	}

	/**Check the "Change Dates Reason" button exists.*/
	public boolean checkSelectReasonExist(){
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id","reasonID");
	}

	/**Check the "Transfer Reason" label*/
	public boolean checkTransferReason(){
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text","Transfer Reason");
	}

	/**
	 * Select specific item
	 *
	 * @param dropDownList
	 *            ---Drop down list object
	 * @param subItem
	 *            --The selected item name
	 */
	public void selectItem(ISelect dropDownList, String subItem) {
		if (subItem != null && subItem.length() > 0) {
			dropDownList.select(subItem);
		} else {
			dropDownList.select(1);
		}
	}

	/**
	 * Get specific reservation details sections string from one string to another string
	 *
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            --- to which string
	 * @param selectionIndex
	 *            ---1:Reservation section
	 *            ---2:Location section
	 *            ---3:Invoice section
	 *            ---4:Customer section
	 *            ---5:Note/Alerts section
	 * @return
	 */
	public String getReservationDetailsString(String fString, String lString, int selectionIndex, boolean isSpecialOpPage) {
		// for Transfer and Date Change an extra row is added to top of table..
		if(isSpecialOpPage){
			selectionIndex = selectionIndex+1;
		}

		String theString = this.getReservationDetailsCellValue(selectionIndex, 1);
		int sIndex = theString.indexOf(fString) + fString.length();
		if (lString != null && lString.length() > 0) {
			int lIndex = theString.indexOf(lString);
			return (theString.substring(sIndex, lIndex)).trim();
		} else {
			return (theString.substring(sIndex)).trim();
		}
	}

	/**
	 * Get 'reservation' section cell from one string to another string
	 *
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            ---To which String
	 * @return--- the retrieved string
	 */
	public String getReservationString(String fString, String lString) {
		return getReservationDetailsString(fString, lString, 1, false).trim();
	}

	/**
	 * Get 'Location' section cell from one string to another string
	 *
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            ---To which String
	 * @return--- the retrieved string
	 */
	public String getLocationString(String fString, String lString) {
		return getReservationDetailsString(fString, lString, 2, false).trim();
	}

	/**
	 * Get 'Invoice' section cell from one string to another string
	 *
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            ---To which String
	 * @return--- the retrieved string
	 */
	public String getInvoiceString(String fString, String lString) {
		return getReservationDetailsString(fString, lString, 3, false).trim();
	}

	/**
	 * Get 'Customer' section cell from one string to another string
	 *
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            ---To which String
	 * @return--- the retrieved string
	 */
	public String getCustomerString(String fString, String lString) {
		return getReservationDetailsString(fString, lString, 4, false).trim();
	}

	/**
	 * Get balance number
	 *
	 * @return--return balance number
	 */
	public String getBalanceNumber() {
		return getInvoiceString("Balance", "").substring(1);
	}

	/**
	 * Get reserv number
	 *
	 * @return---Return reserve number
	 */
	public String getReservNum() {
		return getReservationString("Reservation #", "Arrival");
	}

	/**
	 * Get the Collection Status
	 *
	 * @return ----Return the Collection Status
	 */
	public String getColletionStatus() {
		return getReservationString("CollectionStatus", "");
	}

	/**
	 * Get resereve number
	 *
	 * @param isTransferOrDateChange
	 *            -- for Transfer and Date Change an extra row is added to top
	 *            of table..
	 * @return
	 */
	public String getReservNum(int selectonIndex, boolean isTransferOrDateChange) {
		return getReservationDetailsString("Reservation #", "Arrival",selectonIndex,
				isTransferOrDateChange);
	}

	/** Get arrive date */
	public String getArriveDate() {
//		return getReservationString("Arrival", "Departure");
		return this.getAttributeByName("(Arrival)|(Entry Date)").replace(",", StringUtil.EMPTY);//[Shane]20131112,it will be entry date for NSQ site
	}

	/** Get depart date */
	public String getDepartDate() {
//		return getReservationString("Departure", "Nights");
		return this.getAttributeByName("Departure").replace(",", StringUtil.EMPTY);//Quentin[20131029] UI Changes
	}

	public String getDaysDepartDate(){
		return this.getAttributeByName("Departure").replace(",", StringUtil.EMPTY);//Quentin[20131029] UI Changes
	}

	/** Get created date */
	public String getCreatedDate() {
//		return getReservationString("Created Date", "Created By");
		return this.getAttributeByName("Created Date").replace(",", StringUtil.EMPTY);//Quentin[20131029] UI changes
	}

	/** Get created date */
	public String getCreatedBy() {
//		return getReservationString("Created By", "Price");
		return this.getAttributeByName("Created By");//Quentin[20131029] UI changes
	}

	/** Get night number */
	public int getNightNum() {
//		return Integer.parseInt(getReservationString("Nights", "Status"));
		return Integer.parseInt(this.getAttributeByName("Nights"));//Quentin[20131029] UI changes
	}

	public int getDaysNightNum(){
//		return Integer.parseInt(getReservationString("Days", "Status"));
		return Integer.parseInt(this.getAttributeByName("Days"));//Quentin[20131029] UI changes
	}

	/** Get park */
	public String getPark() {
//		return getLocationString("Park", "Area");
		return this.getAttributeByName("Park");//Quentin[20131029] UI changes
	}

	/** Get Area */
	public String getArea() {
//		return getLocationString("Area", "Site# (Name)");
		return this.getAttributeByName("Area");//Quentin[20131029] UI changes
	}

	/** Get Area */
	public String getTypeOfUse() {
//		return getLocationString("Type of Use", "");
		return this.getAttributeByName("Type of Use");//Quentin[20131029] UI changes
	}

	/** Get customer name */
	public String getCustName() {
//		return getCustomerString("Name", "Phone");
		return this.getAttributeByName("Name");//Quentin[20131029] UI changes
	}

	/** Get customer name in Call Manager due to there are two span with text "Name", and only need to get the second one's text
	 *  Not update the method getAttributeByName avoiding to affect other scenarios
	 *  Added by Lesley, 20131202
	 */
	public String getCustNameInCM() {
		IHtmlObject objs[] = browser.getHtmlObject(this.labelSpan("Name"));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute Name");
		}
		
		String text = objs[objs.length-1].text().replace("Name", StringUtil.EMPTY).trim();
		Browser.unregister(objs);
		
		return text;
	}
	
	/** Get customer phone */
	public String getCustPhone() {
//		return getCustomerString("Phone", "Email");
		return this.getAttributeByName("Phone");//Quentin[20131029] UI changes
	}

	/** Get customer Email */
	public String getCustEmail() {
//		return getCustomerString("Email", "Phone Contact Preference");
		return this.getAttributeByName("Email");//Quentin[20131029] UI changes
	}

	/** Get customer Phone Contact Preference */
	public String getCustPhoneContractPreference() {
//		return getCustomerString("Phone Contact Preference", "Preference Contact Time");
		return this.getAttributeByName("Phone Contact Preference");//Quentin[20131029] UI changes
	}

	/** Get customer Preference Contact Time */
	public String getCustPreferenceContactTime() {
//		return getCustomerString("Preference Contact Time", "");
		return this.getAttributeByName("Preference Contact Time");//Quentin[20131029] UI changes
	}

	/** Get occupant # */
	public String getOccupantNum(){
		String occupantNum = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "otherOccList_control");
		if(objs.length>0){
			occupantNum = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		return occupantNum;
	}

	/** Get vehicles # */
	public String getVehiclesNum(){
		String vehiclesNum = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "VehicleList_control");
		if(objs.length>0){
			vehiclesNum = objs[0].getProperty(".text").toString();
		}else throw new ObjectNotFoundException("Object can't find.");

		return vehiclesNum;
	}


	/**
	 * Retrieve reservation status
	 *
	 * @param String
	 *            ---return the status of the reservation
	 */
	public String getReservStatus() {
		String text = browser.getObjectText(".class", "Html.Span", ".text", new RegularExpression("^Status.*", false)).trim();
		return text.replaceAll("Status", StringUtil.EMPTY);
	}

	/**
	 * Get the error message if the reservation could not be void
	 *
	 * @return error message
	 */
	public boolean checkErrorMessageExits() {
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}

	public String getProcessStatus() {
		return getReservationString("Status", "Order Status");
	}

	/**
	 * Retrieve order status
	 *
	 * @return--Return the order status
	 */
	public String getOrderStatus() {
		String text = browser.getObjectText(".class", "Html.Span", ".text", new RegularExpression("^Order Status.*", false)).trim();
		return text.replaceAll("Order Status", StringUtil.EMPTY);
	}

	/**
	 * Get Zip
	 */
	public String getZipCode(){
		IHtmlObject [] objs = browser.getHtmlObject(".id", "occupantZipCode");
		String zip = objs[0].getProperty(".value").toString();

		Browser.unregister(objs);
		return zip;
	}

	/**
	 * Get reservation price
	 *
	 * @return price
	 */
	public String getPrice() {
		return getReservationString("Price", "Paid");
	}

	/**
	 * Get reservation paid
	 *
	 * @return paid
	 */
	public String getPaid() {
		return getReservationString("Paid", "Unissued Refund");
	}

	/**
	 * get unissued refund
	 *
	 * @return unissued refund
	 */
	public String getUnissuedRefund() {
		return getReservationString("Unissued Refund", "Confirmation Status");
	}

	/**
	 * get Confirmation status
	 *
	 * @return Confirmation status
	 */
	public String getConfirmationStatus() {
		return getReservationString("Confirmation Status", "Balance");
	}

	/**
	 * Retrieve the pay status
	 *
	 * @return--Return the pay status
	 */
	public String getPayStatus() {
		return getInvoiceString("Pay. Status", "Balance");
	}

	/**
	 * Get the balance
	 *
	 * @return balance
	 */
	public String getBalance() {
		return getReservationString("Balance", "CollectionStatus");
	}


	/** Click charge pos button */
	public void clickChargePOS() {
		RegularExpression hrefreg = new RegularExpression(
				".*,\"(chargePOSToRes|chargePOSToPermitOrder)\",.*", false);
		Property p[] = Property.toPropertyArray(".class", "Html.A", ".href", hrefreg);
		try{
			browser.clickGuiObject(p,true);
		} catch(Exception e) {
			//james[20130822] need to use a list to search as there are 2 "Charge POS" objects were found
			Property[] p1=Property.toPropertyArray(".class","Html.DIV",".id","MarinaOrderDetailPage");
			Property[] p2=Property.toPropertyArray(".class", "Html.A", ".text", "Charge POS");
			browser.clickGuiObject(Property.atList(p1,p2), true,0);
		}
	}

	/**Click Request Refund button*/
	public void clickRequestRefund(){
		browser.clickGuiObject(".class", "Html.A",".text","Request Refund");
	}

	/**
	 * Select Camping unit and set the qty
	 *
	 * @param item
	 * @param qty
	 * @param index
	 *            - 1 based
	 */
	public void selectCampingUnit(String item, String qty, int index) {
		if (index < 1)
			throw new ItemNotFoundException("Index is less than 1.");
		if (item == null || item.length() < 1)
			{
			    browser.selectDropdownList(".id", "campingUnitType", ".classIndex",index + "", 0);
			}
					
		else
			{
		        browser.selectDropdownList(".id", "campingUnitType", item, index);
		    }
		browser.setTextField(".id", "qty", qty, index);
	}

	public int getNumberOfCampingUnit() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "campingUnitType");

		int num = objs.length - 1;
		Browser.unregister(objs);
		return num;
	}
	
	public String getCampingUnitsInfo(int index){
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.Tr", 
				".id",new RegularExpression("campingInfoContainer_CampingUnit_\\d+._campingunits-row:\\d+$", false));
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD",
				".className", "campingDetailTDNoRight", trs[index-1]);
//		for(int i=0; i<objs.length; i++){
//			System.out.println(i+":"+objs[i].text());
//		}
		String txt = objs[2*(index-1) + 1].text();
		Browser.unregister(trs);
		Browser.unregister(objs);
		return txt;
	}

	public String getSelectedCampingUnitType(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.equipmentView|campingUnitType", false), index);//campingUnitType
	}

	public void selectCampingUnitType(String unitType, int index) {
		browser.selectDropdownList(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.equipmentView|campingUnitType", false), unitType, index);//campingUnitType
	}

	public void setLengthBoxValue(String length, int index) {
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.length|length", false), length, index);//length
	}

	public boolean isLengthBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Length", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getLength(int index) {
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.length|length", false));//length
		String length = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return length;
	}

	public void setQtyBoxValue(String qtyNum, int index) {
		browser.setTextField(".id", "qty", qtyNum, index);//qty
	}

	public boolean isQtyBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Qty", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getQtyValue() {
//		IHtmlObject[] objs = browser.getTextField(".id","qty");//qty
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("campingInfoContainer_CampingUnit_\\d+._campingunits-row:\\d+_camping_unit_quantity|qty", false));//qty
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any unit quantity object info.");
		}
		String qty = objs[objs.length-1].getProperty(".value");//Vivian[2014/04/10]
		Browser.unregister(objs);
		return qty;
	}

	public void setPlateBoxValue(String plateValue, int index) {
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+.license|camping_unit_license", false), plateValue, index);//camping_unit_license
	}

	public boolean isPlateBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Plate", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public boolean isRequestRefundExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A",".text","Request Refund");
	}

	public boolean isRequestRefundDisabled(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A",".text","Request Refund");
		String abled = objs[0].getProperty(".disabled");
		Browser.unregister(objs);
		if(abled.equals("true")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Check the Add Other Occupant button is abled
	 * @return
	 * 			true: abled
	 */
	public boolean isAddOtherOccupantAble() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", new RegularExpression("Add Occupant.*", false));
		String status = objs[0].getProperty(".disabled").trim();
		Browser.unregister(objs);

		if (status.equalsIgnoreCase("false")) {
			return true;
		} else {
			return false;
		}
	}

	

	/**
	 * This method used to get camping unit div style, to check whether it is
	 * display can be used to solve the hidden object problem
	 *
	 * @param text
	 * @return
	 */
	private String getCampingUnitDivStyle(String text, int index) {
		RegularExpression rex = new RegularExpression(text, false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", "campingUnitType",
				".text", rex);
		String outerHtml = objs[index].getProperty("outerHTML");
		Browser.unregister(objs);
		String style = "";
		if (outerHtml.contains("style")) {
			String subtext = outerHtml.substring(outerHtml.indexOf("style=")+ 7);
			style = subtext.substring(0, subtext.indexOf("\""));
		}
		return style;
	}

	public void setAllCampingUnitValue(ReservationInfo res, int index) {
		this.selectCampingUnitType(res.site.validCampingUnit, index);
		if (res.site.vehicleLenth != null && res.site.vehicleLenth.length() > 0) {
			this.setLengthBoxValue(new Integer(res.site.vehicleLenth)
					.toString(), index);
		}
		if (res.site.validCampingUnitQty > 0) {
			this.setQtyBoxValue(String.valueOf(res.site.validCampingUnitQty),
					index);
		}
		if (res.site.campingUnitPlate != null
				&& res.site.campingUnitPlate.length() > 0) {
			this.setPlateBoxValue(res.site.campingUnitPlate, index);
		}
		if (res.site.unitState != null && res.site.unitState.length() > 0) {
			this.selectStateValue(res.site.unitState, index);
		}
		if (res.site.unitMake != null && res.site.unitMake.length() > 0) {
			this.selectMakeValue(res.site.unitMake, index);
		}
		if (res.site.vehicleModel != null && res.site.vehicleModel.length() > 0) {
			this.setModelValue(res.site.vehicleModel, index);
		}
		if (res.site.unitColor != null && res.site.unitColor.length() > 0) {
			this.selectColorValue(res.site.unitColor, index);
		}
	}

	/**
	 * This method used to verify camping unit info is the same with given info
	 *
	 * @param res
	 * @param index
	 */
	public void verifyCampingUnitInfoCorrect(ReservationInfo res, int index){
		String value = "";
		logger.info("Verify Camping Unit Info Correct.");
		if (res.site.campingUnitPlate != null && res.site.campingUnitPlate.length() > 0) {
			value = this.getPlate(index);
			if (!res.site.campingUnitPlate.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException("Camping Unit Plate Not Correct as:"+ res.site.campingUnitPlate, res.site.campingUnitPlate, value);
			}
		}
		if (res.site.unitState != null && res.site.unitState.length() > 0) {
			value = this.getSelectedState(index);
			if (!res.site.unitState.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException(
						"Camping Unit State Not Correct.", res.site.unitState, value);
			}
		}
		if (res.site.unitMake != null && res.site.unitMake.length() > 0) {
			value = this.getSelectedMake(index);
			if (!res.site.unitMake.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException("Camping Unit Make Not Correct.", res.site.unitMake , value);
			}
		}
		if (res.site.vehicleModel != null && res.site.vehicleModel.length() > 0) {
			value = this.getModelValue(index);
			if (!res.site.vehicleModel.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException(
						"Camping Unit Model Not Correct.", res.site.vehicleModel, value);
			}
		}
		if (null != res.site.unitColor && res.site.unitColor.length() > 0) {
			value = this.getSelectedColor(index);
			if (!res.site.unitColor.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException(
						"Camping Unit Color Not Correct.", res.site.unitColor, value);
			}
		}
	}
	

	/**
	 * This method used to select save to primary occupant profile check box
	 */
	public void selectSaveToOccProfileBox() {
		IHtmlObject[] objs = browser.getCheckBox(".id", "SaveCampUnitToProfile");
		((ICheckBox) objs[0]).select();
		Browser.unregister(objs);
	}

	/**
	 * This method used to deselect save to primary occupant profile check box
	 */
	public void deselectSaveToOccProfileBox() {
		IHtmlObject[] objs = browser.getCheckBox(".id", "SaveCampUnitToProfile");
		((ICheckBox) objs[0]).deselect();
		Browser.unregister(objs);
	}

	/**
	 * This method used to select complete from primary occupant profile check
	 * box
	 */
	public void selectCompleteFromOccProfileBox() {
		IHtmlObject[] objs = browser.getCheckBox(".id",
				"CampingUnitsAutoComplete");
		((ICheckBox) objs[0]).select();
		Browser.unregister(objs);
	}

	public void clickCustomerName(String custName) {
		browser.clickGuiObject(".class", "Html.A", ".text", custName);
	}

	/**
	 * Get the exist unit type
	 *
	 * @param index
	 *            -- class index
	 * @return unit type
	 */
	public String getUnitType(String index) {
		String unitType = browser.getDropdownListValue(".id","campingUnitType",Integer.valueOf(index));
		
				//"campingUnitType", ".classIndex", index, 0);  //UI changed

		return unitType;
	}

	/**
	 * Get the number of the Unit
	 *
	 * @param index
	 * @return Unit number
	 */
	public String getUnitNum(int index) {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "qty", ".class",
				"Html.INPUT.text");
		String unitNum = obj[index].getProperty(".value").toString();
		Browser.unregister(obj);

		return unitNum;
	}

	/** Select autopringreceipt checkbox */
	public void selectAutoPrintReceipt() {
		browser.selectCheckBox(".id", "autoPrintReceipt");
	}

	/** Don't select autoprintreceipt checkbox */
	public void deSelectAutoPrintReceipt() {
		browser.unSelectCheckBox(".id", "autoPrintReceipt");
	}

	/**
	 * If plate is empty set plate number
	 *
	 * @param plateNum
	 * @return
	 */
	public boolean setPlateNumberIfEmpty(String plateNum) {
		boolean executed = false;
		IHtmlObject[] objs = browser.getTextField(".class", "Html.INPUT.text",
				".id", "license");
		for (int i = 1; i < objs.length; i++) {
			String text = ((IText) objs[i]).getText();
			if (text == null || text.length() < 1) {
				((IText) objs[i]).setText(plateNum);
				executed = true;
			}
		}

		Browser.unregister(objs);
		return executed;
	}

	public void selectVehicleState(String state) {
		browser.selectDropdownList(".id", "state", state, 1);
	}

	public void setVehicleModel(String model) {
		browser.setTextField(".id", "model", model, 1);
	}

	public void setVehicleInfo(String plateNum, String state, String model) {
		setPlateNumberIfEmpty(plateNum);
		selectVehicleState(state);
		setVehicleModel(model);
	}

	/**
	 * Get the plate number
	 *
	 * @return plate number
	 */
	public String getPlateNum() {
		// return browser.getTextFieldValue(".id", "license");
		String platNum = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.INPUT.text",
				".id", "license");
		for (int i = 0; i < objs.length; i++) {
			String temp = objs[i].getProperty(".value").toString();
			if (null != temp && !temp.equals("")) {
				platNum = temp;
			}
		}
		Browser.unregister(objs);

		return platNum;
	}

	/** Click apply link */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply");
	}

	/**
	 * Input the qty of occupants
	 *
	 * @param num
	 *            --the qty of occupant
	 */
	public void setNumOfOccupants(String num) {
//		browser.setTextField(".id", "otherOccList_control", num);
		browser.setTextField(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+_occupant-row:\\d_otherOccQty", false), num);
	}

	public void clickMaxAllowed() {
		browser.clickGuiObject(".class", "Html.LABEL", ".text",
				"Max Total Allowed");
	}

	public void setOtherOccupantName(String occupantName, int num) {
		IHtmlObject[] objs = browser.getTextField(".id", "otherOccFName");
		for (int i = num; i > 0; i--) {
			((IText) (objs[i])).setText(occupantName);
		}

		Browser.unregister(objs);
	}

	/** retrieve max allowed unit in the reservation detail page */
	public String getMaxAllowedUnit() {
		return getCampingUnitString("Allowed", "Complete", 0);
	}

	public boolean checkConfirmationMethod() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
				"Confirmation Method");
	}

	public boolean checkPhoneContact() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
				"Phone Contact Preference");
	}

	public boolean checkContactTime() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
				"Preference Contact Time");
	}

	/**
	 * Retrieve camping unit
	 *
	 * @param fString
	 * @param lString
	 * @param rowIndex
	 * @return
	 */
	public String getCampingUnitString(String fString, String lString,
			int rowIndex) {
		String theString = this.getCampingDetailsCellValue(rowIndex, 1);
		int sIndex = theString.indexOf(fString) + fString.length();
		if (lString != null && lString.length() > 0) {
			int lIndex = theString.indexOf(lString);
			return (theString.substring(sIndex, lIndex)).trim();
		} else {
			return (theString.substring(sIndex)).trim();
		}
	}

	/**
	 * Retrieve camping details cell value
	 *
	 * @param row
	 *            --The row of the cell
	 * @param col
	 *            --The row of the col
	 * @return
	 */
	public String getCampingDetailsCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",
				".id", "CampingUnits");

		String toReturn = ((IHtmlTable) objs[0]).getCellValue(row, col)
				.toString();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Input the qty of the vehicles
	 *
	 * @param num
	 *            --The qty of the vehicles
	 */
	public void setNumOfVehicles(int num) {
//		browser.setTextField(".id", "VehicleList_control", Integer.toString(num));
		browser.setTextField(".id", new RegularExpression("campingInfoContainer_Vehicle_\\d+_vehicle-row:\\d_numberofvehicle",false), Integer.toString(num));
	}

	/**
	 * Retrieve the max allowed vehicles
	 *
	 * @return
	 */
	public String getMaxAllowedVehicles() {
		return getVehicleString("Allowed", "Complete", 0);
	}

	/**
	 * Retrieve the num of the vehicles
	 *
	 * @return
	 */
	public String getNumofVehicles() {
		IHtmlObject[] vehicle = browser.getHtmlObject(".id",
				new RegularExpression("campingInfoContainer_Vehicle_\\d+_vehicle-row:\\d_numberofvehicle",false));
		return vehicle[0].getProperty(".value").toString();
	}

	/**
	 * Get the vehicle information
	 *
	 * @param fString
	 * @param lString
	 * @param rowIndex
	 *            --If there are several vehicles, should specific row index
	 * @return
	 */
	public String getVehicleString(String fString, String lString, int rowIndex) {
		String theString = this.getVehicleDetailsCellValue(rowIndex, 1);
		int sIndex = theString.indexOf(fString) + fString.length();
		if (lString != null && lString.length() > 0) {
			int lIndex = theString.indexOf(lString);
			return (theString.substring(sIndex, lIndex)).trim();
		} else {
			return (theString.substring(sIndex)).trim();
		}
	}

	/**
	 * Retrieve vehicle details cell value
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public String getVehicleDetailsCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",
				".id", "VehicleInfo");
		String toReturn = ((IHtmlTable) objs[0]).getCellValue(row, col);
		Browser.unregister(objs);
		return toReturn;
	}

	/** Click addcustomertype button */
	public void clickAddCustomerType() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Add Customer Type");
	}

	/**
	 * Select customer type
	 *
	 * @param customertype
	 *            ---The index of the customer type
	 * @param index
	 *            ---For specific ID
	 * @param objectindex
	 *            ---If there are several customer type drop down, should
	 *            specific which one
	 */
	public void selectCustomerType(String customertype, int index,
			int objectindex) {
		// browser.selectDropdownList(".id", index + "_proofType",
		// customertype,objectindex);
		IHtmlObject[] objs = browser.getDropdownList(".id", index + "_proofType");
		((ISelect) objs[objectindex]).select(customertype);
		Browser.unregister(objs);
	}

	/**Add customer type(s)*/
	public void addCostomerType(List<CustType> list){
		for(int i=0;i<list.size();i++){
			this.clickAddCustomerType();
			ajax.waitLoading();
			this.selectCustomerType(list.get(i).type, 1, i+1);
			ajax.waitLoading();
			this.setID(list.get(i).id, 1, i+1);
			this.setNote(list.get(i).notes, 1, i+1);
		}

	}

	/**Add customer pass*/
	public void addCustomerPass(List<CustPass> list){
		for(int i = 0; i<list.size();i++){
			this.clickAddCustomerPass();
			ajax.waitLoading();
			this.selectCustPass(list.get(i).passType);
			this.setPassNum(list.get(i).passNum);
		}
	}

	/**Click the Add Other Occupant button*/
	public void clickAddOtherOccupant(){
		browser.clickGuiObject(".class", "Html.A",".text", new RegularExpression("Add Occupant", false));
	}

	/**Select the occupant type*/
	public void selectOtherOccType(String occType, int index){
		browser.selectDropdownList(".id", "otherOccType", occType, index);
	}

	/**Set the First Name of the other occupant type*/
	public void setOtherOccFirstName(String fName, int index){
		browser.setTextField(".id", "otherOccFName", fName, index);
	}

	/**Set the Last Name of the other occupant type*/
	public void setOtherOccLastName(String lName, int index){
		browser.setTextField(".id", "otherOccLName", lName, index);
	}

	/**Set the Qty of the other occupant type*/
	public void setOtherOccQty(String qty, int index){
		browser.setTextField(".id", "otherOccQty", qty, index);
	}
	
	public boolean isOtherOccupantQtyExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+\\_otherOccQty", false));
	}
	
	public int getMaxOccupantsNum(){
		String preString = "# of Occupants";
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^" + preString +  ".*", false));
		String text = objs[0].text();
		String max = text.substring(preString.length(), text.indexOf("MAX")-1).trim();
		Browser.unregister(objs);
		return Integer.parseInt(max);
	}
	
	public int getMinOccupantsNum(){
		String preString = "# of Occupants";
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.DIV", ".text", new RegularExpression("^" + preString +  ".*", false));
		String text = objs[0].text();
		String[] tokens=RegularExpression.getMatches(text, "\\d+(?= MIN)");
		
		if(null == tokens || tokens.length == 0){
			tokens = new String[]{"0"};
		}
		Browser.unregister(objs);
		return Integer.parseInt(tokens[0]);
	}
	
	public void setOtherOccupantQty(String qty) {
		if(Integer.parseInt(qty)<0) {
			qty="0";
		}
		browser.setTextField(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+\\_otherOccQty", false), qty);
	}
	public String getOtherOccupantQty() {
		return browser.getTextFieldValue(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+\\_otherOccQty", false));
	}
	/**
	 * Add other occupant types
	 * @param list
	 */
	public void addOtherOccupant(List<String[]> list){
		for(int i = 0; i <list.size(); i++){
			this.clickAddOtherOccupant();
			this.selectOtherOccType(list.get(i)[0], i+1);
			this.setOtherOccFirstName(list.get(i)[1], i+1);
			this.setOtherOccLastName(list.get(i)[2], i+1);
		}
	}

	/**
	 * Add other occupant types
	 * @param list
	 */
	public void addOtherOccupantTypes(List<String[]> list){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",".id", "otherOccList");
		String value = "";
		if(objs.length > 0){
			value = ((IHtmlTable) objs[0]).getProperty(".text");
		}

		if(!value.split("Occupant Type")[1].trim().split(" ")[0].trim().equalsIgnoreCase("Qty")){
			for(int i = 0; i <list.size(); i++){
				this.clickAddOtherOccupant();
				this.selectOtherOccType(list.get(i)[0], i+1);
				this.setOtherOccFirstName(list.get(i)[1], i+1);
				this.setOtherOccLastName(list.get(i)[2], i+1);
			}
		}else{
			for(int i = 0; i<list.size(); i++){
				this.clickAddOtherOccupant();
				this.selectOtherOccType(list.get(i)[0], i+1);

				if(list.get(i)[1]!=null && !"".equalsIgnoreCase(list.get(i)[1])){
					this.setOtherOccQty(list.get(i)[1], i+1);
				} else {
					this.setOtherOccQty("1", i+1);
				}
			}
		}

		Browser.unregister(objs);
	}

	/**Check the default value of Qty*/
	public void addAndCheckOtherOccDefaultValue(List<String[]> list){
		int defaulValue = 1;
		for(int i =0; i <list.size(); i++){
			this.clickAddOtherOccupant();
			IHtmlObject[] objs = browser.getHtmlObject(".id", "otherOccQty");
			if(Integer.parseInt(objs[1].getProperty(".value"))!=defaulValue){
				Browser.unregister(objs);
				throw new ErrorOnPageException("The default value of the Qty should be: "+defaulValue);
			}else{
				Browser.unregister(objs);
			}
		}
	}

	/**Get the other occupant types row number*/
	public int getOtherOccRowNum(){
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SELECT",".id", "otherOccType");
		int rowNum = objs.length;
		Browser.unregister(objs);

		if(rowNum>0){
			return rowNum-1;
		}else{
			return -1;
		}
	}

	/**Verify the default other occupant types is the first option of the drop-down list*/
	public void checkDefaultOtherOccupantTypeDisplay() {
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.SELECT",".id", "otherOccType");
		for (int i = 1; i < objs.length; i++) {
			ISelect list = (ISelect) objs[i];
			if (!objs[i].getProperty(".text").startsWith(list.getAllOptions().get(0))) {
				throw new ErrorOnDataException("The default value of the other occupant types should be: "+ list.getAllOptions().get(0));
			}
		}
		Browser.unregister(objs);
	}

	/**Remove all other occupants*/
	public void removeAllOtherOcc(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A",".text","Remove");
		for(int i=1;i<objs.length;i++){
			browser.clickGuiObject(".class", "Html.A",".text","Remove",1);
		}
	}

	/**Remove all other occupants*/
	public void removeAllOtherOcc(int removeNum){
		for(int i=0;i<removeNum;i++){
			browser.clickGuiObject(".class", "Html.A",".text","Remove",1);
		}
	}

	/**Get the information of the Occupant Type Max Limit*/
	public Map<String, String> getOccupantTypeMaxLimitInfo(String occuType){
		Map<String,String> map=new HashMap<String,String>();
		IHtmlObject [] objs = browser.getTableTestObject(".text", new RegularExpression("^( )*\\("+occuType+".*", false));
		IHtmlTable table = (IHtmlTable)objs[0];
		
		String targetStr=table.getProperty(".text");
		String str = targetStr.substring(1, targetStr.length()-1);
		String[] temp=str.split(":");
		map.put(temp[0], temp[1]);

		return map;
	}

	/**Remove all customer type*/
	public void removeAllCustomerType(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A",".text","Remove Customer Type");
		for(int i=1;i<objs.length;i++){
			browser.clickGuiObject(".class", "Html.A",".text","Remove Customer Type",1);
			ajax.waitLoading();
		}
	}

	/**remove customer pass*/
	public void removeAllCustomerPass(){
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.A",".text","Remove Customer Pass");
		for(int i=1;i<objs.length;i++){
			browser.clickGuiObject(".class", "Html.A",".text","Remove Customer Pass",1);
			ajax.waitLoading();
		}

		Browser.unregister(objs);
	}

	/**Get All options in Other Occupant Types list*/
	public List<String> getAllOptionsOfOtherOcc(){
//		this.clickAddOtherOccupant();
		IHtmlObject[] objs = browser.getDropdownList(".id", "primaryOccType");
		ISelect dropdown = (ISelect) objs[0];
		List<String> list = dropdown.getAllOptions();
		Browser.unregister(objs);
		if(list.get(0).matches("Please Select.*")){
			list.remove(0);// remove 'Please Select'
		}
		

		return list;
	}

//	public void selectCustomerType(String customertype, String item,int objectindex) {
//
//	}

	/**
	 * Select customer pass
	 *
	 * @param passType
	 * @param objectIndex
	 */
	public void selectCustomerPass(String passType, int objectIndex) {
		IHtmlObject[] objs = browser.getDropdownList(".id", "passTypeId");
		((ISelect) objs[objectIndex]).select(passType);
		Browser.unregister(objs);
	}

	/**
	 * Set customer pass expiry date
	 *
	 * @param date
	 * @param index
	 */
	public void setPassExpiryDate(String date, int index) {
//		browser.setTextField(".id", "expiryDate_ForDisplay", date, index);
		browser.setTextField(".id", "expiryDate" + (index + 1) + "_ForDisplay", date);
	}

	/**
	 * Get the customer type
	 *
	 * @param index
	 *            - class index
	 * @return - customer type
	 */
	public String getCustomerType(String index) {
		// String cusType = browser.getDropdownListValue(".id", index +
		// "_proofType",".classIndex",index,0);
		IHtmlObject[] objs = browser
				.getDropdownList(".id", index + "_proofType");
		String cusType = ((ISelect) objs[Integer.parseInt(index)])
				.getSelectedText();
		Browser.unregister(objs);
		return cusType;
	}
	
	public String getCustomerPass(){		
		IHtmlObject objs[] = browser.getDropdownList(".id", "passTypeId");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Customer Pass Type drop down list object.");
		}
		
		String text = ((ISelect)objs[objs.length - 1]).getSelectedText();
		Browser.unregister(objs);
		
		return text;
	}

	public String getCustomerPassNumber(){		
		IHtmlObject objs[] = browser.getTextField(".id", "passNumber");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Customer Pass Number text field object.");
		}
		String text = ((IText)objs[objs.length - 1]).getText();
		Browser.unregister(objs);
		
		return text;
	}		

	public String getEligibilityNotes(){		
		IHtmlObject objs[] = browser.getTextField(".id", "notes");
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Customer Pass Note text feild object.");
		}
		String text = ((IText)objs[objs.length - 1]).getText();
		Browser.unregister(objs);
		
		return text;
	}		

	public String getExpiryDate() {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression("expiryDate(\\d+)?_ForDisplay", false));//expiryDate1_ForDisplay
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find Customer Pass Expiry Date field object.");
		}
		String text = ((IText)objs[objs.length - 1]).getText();
		Browser.unregister(objs);
		
		return text;
	}
	
	/**
	 * Input ID
	 *
	 * @param ID
	 * @param index
	 *            ---For specific ID
	 * @param array
	 *            ---For which ID text box
	 */
	public void setID(String ID, int index, int array) {
		browser.setTextField(".id", index + "_proofID", ID, array);
	}

	/**
	 * Get the customer ID
	 *
	 * @param index
	 *            - the index included in object ID
	 * @return - customer ID
	 */
	public String getCustID(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", index + "_proofID");
		String custID = objs[index].getProperty(".value").toString();
		Browser.unregister(objs);
		return custID;
	}

	/**
	 * Input note
	 *
	 * @param note
	 *            ---The note information
	 * @param index
	 *            --- For specific ID
	 * @param array
	 *            ---If there are several note text box, should specific which
	 *            one
	 */
	public void setNote(String note, int index, int array) {
		browser.setTextField(".id", index + "_proofNotes", note, array);
	}

	/**
	 * Get the customer notes
	 *
	 * @param index
	 *            - the index included in object ID
	 * @return - Eligibility Notes
	 */
	public String getCustNotes(int index) {
		IHtmlObject[] objs = browser.getHtmlObject(".id", index + "_proofNotes");
		String custNotes = objs[index].getProperty(".value").toString();
		Browser.unregister(objs);
		return custNotes;
	}

	/**
	 * Input the qty of cat
	 *
	 * @param num
	 */
	public void setCatNum(String num) {
		RegularExpression reg = new RegularExpression("^petqty_.*_601", false);
		browser.setTextField(".id", reg, num);
	}

	/**
	 * Get the cat number in details page
	 *
	 * @return cat number
	 */
	public String getCatNum() {
		RegularExpression reg = new RegularExpression("^petqty_.*_601", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", reg);
		String catNum = objs[0].getProperty(".value").toString();
		Browser.unregister(objs);

		return catNum;
	}

	/**
	 * Input the qty of dog
	 *
	 * @param num
	 */
	public void setDogNum(String num) {
		RegularExpression reg = new RegularExpression("^petqty_.*_600", false);
		browser.setTextField(".id", reg, num);
	}

	/**
	 * Get the dog number in details page
	 *
	 * @return dog number
	 */
	public String getDogNum() {
		RegularExpression reg = new RegularExpression("^petqty_.*_600", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", reg);
		String dogNum = objs[0].getProperty(".value").toString();
		Browser.unregister(objs);

		return dogNum;
	}

	/**
	 * Input the other pets's qty
	 *
	 * @param num
	 */
	public void setOtherNum(String num) {
		RegularExpression reg = new RegularExpression("^petqty_.*_603", false);
		browser.setTextField(".id", reg, num);
	}

	/**
	 * Get the other pet number in details page
	 *
	 * @return other pet number
	 */
	public String getOtherPetNum() {
		RegularExpression reg = new RegularExpression("^petqty_.*_603", false);
		IHtmlObject[] objs = browser.getHtmlObject(".id", reg);
		String otherPetNum = objs[0].getProperty(".value").toString();
		Browser.unregister(objs);

		return otherPetNum;
	}

	/**
	 * Input the qty of horse
	 *
	 * @param num
	 */
	public void setHorseNum(String num) {
		RegularExpression reg = new RegularExpression("^petqty_.*_602", false);
		browser.setTextField(".id", reg, num);
	}

	/**
	 * input the note of the pet
	 *
	 * @param note
	 */
	public void setPetNote(String note) {
		browser.setTextField(".id", "pet_note", note);
	}

	/**
	 * Retrieve the allowed pet num
	 *
	 * @return
	 */
	public String getAllowedPet() {
		int row = 0;
		IHtmlObject[] objs = browser.getHtmlObject(".class","Html.TABLE",".id","OccupantDetail");
		if(objs.length>0){
			IHtmlTable resTable = (IHtmlTable)objs[0];
			for(int i=0; i<resTable.rowCount(); i++){
				for(int j=0; j<resTable.columnCount(); j++){
					if(resTable.getCellValue(i, j).equals("Pet:")){
						row = i;
						break;
					}
				}
			}
		}else throw new ObjectNotFoundException("Reservation table can't find.");
		return getSpecCellValue("Pets Allowed", "# Dog", row);
	}

	public HashMap<String, String> getPetsInfo() {
		IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("^campingInfoContainer_Pets_\\d+_pets-row:\\d+$", false));
		if(trObjs.length < 1) throw new ItemNotFoundException("Cannot find Pet info TR object.");
		
		HashMap<String, String> pets = new HashMap<String, String>();
		String trId = "";
		String type = "";
		String num = "";
		for(int i = 0; i < trObjs.length; i ++) {
			//campingInfoContainer_Pets_1098524846_pets-row:1
			//campingInfoContainer_Pets_1098524846_pets-row:1_pettype
			//campingInfoContainer_Pets_1098524846_pets-row:1_numberofpets
			trId = trObjs[i].getProperty(".id").replace("\\:", "\\\\:");
			type = browser.getDropdownListValue(".id", new RegularExpression(trId + "_pettype", false));
			num = browser.getTextFieldValue(".id", new RegularExpression(trId + "_numberofpets", false));
			
			pets.put(type, num);
		}
		
		Browser.unregister(trObjs);
		return pets;
	}
	
	//Quentin[20140626] 3.06.01 ui changes
//	public List<VehicleInfo> getVehiclesInfo() {
//		IHtmlObject trObjs[] = browser.getHtmlObject(".class", "Html.TR", ".id", new RegularExpression("^campingInfoContainer_Vehicle_\\d+_vehicle-row\\:\\d+$", false));
//		if(trObjs.length < 1) throw new ItemNotFoundException("Cannot find Vehicle info TR object.");
//		
//		List<VehicleInfo> vehicles = new ArrayList<VehicleInfo>();
//		IHtmlObject spanObjs[] = null;
//		for(int i = 0; i < trObjs.length; i ++) {
//			VehicleInfo v = new Customer().new VehicleInfo();
//			
//			spanObjs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "label_inline", trObjs[i]);
//			if(!StringUtil.isEmpty(spanObjs[0].getProperty(".text"))) {
//				String makeModel = spanObjs[0].getProperty(".text");//Acura Model123
//				String mm[] = makeModel.split(" ");
//				
//				v.setMake(mm[0]);
//				v.setModel(mm[1]);
//			}
//			
//			String statePlateColor = spanObjs[1].getProperty(".text");
//			String spc[] = statePlateColor.split("-");//AL Plate123 - White
//			String sp[] = spc[0].split(" ");//AL Plate123
//			
//			v.setState(sp[0]);
//			v.setPlateNum(sp[1]);
//			if(spc.length > 1 && !StringUtil.isEmpty(spc[1])) {
//				v.setColor(spc[1].trim());
//			}
//			
//			vehicles.add(v);
//		}
//		
//		Browser.unregister(spanObjs);
//		Browser.unregister(trObjs);
//		return vehicles;
//	}
	
	private static final String vehicleIdPrefix = "campingInfoContainer_Vehicle_\\d+_vehicle-row:\\d+_";
	private Property[] vehiclePlate() {
		return Property.toPropertyArray(".id", new RegularExpression(vehicleIdPrefix + "license", false));
	}
	
	private Property[] vehicleState() {
		return Property.toPropertyArray(".id", new RegularExpression(vehicleIdPrefix + "state", false));
	}
	
	private Property[] vehicleMake() {
		return Property.toPropertyArray(".id", new RegularExpression(vehicleIdPrefix + "make", false));
	}
	
	private Property[] vehicleModel() {
		return Property.toPropertyArray(".id", new RegularExpression(vehicleIdPrefix + "model", false));
	}
	
	private Property[] vehicleColor() {
		return Property.toPropertyArray(".id", new RegularExpression(vehicleIdPrefix + "color", false));
	}
	
	public String getVehiclePlate(int index) {
		return browser.getTextFieldValue(vehiclePlate(), index);
	}
	
	public String getVehicleState(int index) {
		return browser.getDropdownListValue(vehicleState(), index);
	}
	
	public String getVehicleMake(int index) {
		return browser.getDropdownListValue(vehicleMake(), index);
	}
	
	public String getVehicleModel(int index) {
		return browser.getTextFieldValue(vehicleModel(), index);
	}
	
	public String getVehicleColor(int index) {
		return browser.getDropdownListValue(vehicleColor(), index);
	}
	
	public List<VehicleInfo> getVehiclesInfo() {
		IHtmlObject plateObjs[] = browser.getHtmlObject(vehiclePlate());
		if(plateObjs.length < 1) {
			throw new ItemNotFoundException("Cannot find Vehicle record.");
		}
		
		List<VehicleInfo> vehicles = new ArrayList<VehicleInfo>();
		for(int i = 0; i < plateObjs.length; i ++) {
			VehicleInfo v = new Customer().new VehicleInfo();
			
			v.setPlateNum(getVehiclePlate(i));
			v.setState(getVehicleState(i));
			v.setMake(getVehicleMake(i));
			v.setModel(getVehicleModel(i));
			v.setColor(getVehicleColor(i));
			
			vehicles.add(v);
		}
		
		Browser.unregister(plateObjs);
		return vehicles;
	}
	
	/** get the qty of occupants */
	public String getNumofOcc() {
		String num = "";
		if(browser.checkHtmlObjectExists(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+_otherOccQty", false))){
			IHtmlObject[] obj = browser.getTextField(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+_otherOccQty", false));
			for(int i=0; i<obj.length;i++){
				IText value=(IText)obj[i];
				if("".equals(num)){
					num =value.getText();
				}else{
					num =String.valueOf(Integer.valueOf(value.getText())+Integer.valueOf(num)) ;
				}
			}
		}else{
			IHtmlObject[] obj = browser.getTableTestObject(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+",false));
			IHtmlTable table=(IHtmlTable)obj[0];     
			num = String.valueOf(table.rowCount()-2);
			Browser.unregister(obj);
		}
		                                                        
		return num;
		//Quentin[20131114] add other occupant work flow has been changed
//		return browser.getTextFieldValue(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+\\_occupant-row:\\d+_otherOccQty", false));
	}

	/** Get the qty of unit */
	public String getNumofUnit(int index) {  
		IHtmlObject[] obj = browser.getHtmlObject(".id",new RegularExpression("campingInfoContainer_CampingUnit_\\d+_campingunits-row:\\d+_camping_unit_quantity",false));
		String num = obj[index-1].getProperty(".value").toString();

		return num;
	}

	/**
	 * Get spec cell value
	 *
	 * @param fString
	 * @param lString
	 * @param rowIndex
	 * @return
	 */
	public String getSpecCellValue(String fString, String lString, int rowIndex) {
		String theString = this.getDetailsCellValue(rowIndex, 1);
		int sIndex = theString.indexOf(fString) + fString.length();
		if (lString != null && lString.length() > 0) {
			int lIndex = theString.indexOf(lString);
			return (theString.substring(sIndex, lIndex)).trim();
		} else {
			return (theString.substring(sIndex)).trim();
		}
	}

	/**
	 * Get details cell value
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	public String getDetailsCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getTableTestObject(".class", "Html.TABLE",
				".id", "OccupantDetail");

		String toReturn = ((IHtmlTable) objs[0]).getCellValue(row, col)
				.toString();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * The method get nots and alerts
	 *
	 * @param index
	 *            ---0, get notes ---1, get alerts
	 * */
	public String getNotesAndAlerts(int index) {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "alerts");
		String notes = obj[index].getProperty(".value").toString();

		return notes;
	}

	/**
	 * This method is used to get current login user name in the right top page
	 *
	 * @return current login user name like:QA-Auto Test-Auto
	 */
	public String getCurrentUser() {
		String userName = "";
		RegularExpression rex = new RegularExpression("^QA-.+ Test-.+", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);

		if (null != objs && objs.length > 0) {
			String text = objs[0].text();
			userName = text
					.substring(text.indexOf(")") + 1, text.indexOf(" -"))
					.trim();
		}
		Browser.unregister(objs);
		return userName;
	}

	/** Get the qty of removecustomertype button */
	public int getRemoveCustomerTypeButton() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Customer Type");

		return objs.length;
	}

	/**
	 * Get the invoice number
	 *
	 * @return
	 */
	public String getInvoiceNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".id",
				"ordInvID");
		String invoiceNum = objs[0].text();
		Browser.unregister(objs);
		return invoiceNum;
	}

	public boolean removeAllVechicles() {
		Property[] s1 = Property.toPropertyArray(".class", "Html.TABLE",
				".id", "VehicleInfo");
		Property[] s2 = Property.toPropertyArray(".text", "Remove");

		IHtmlObject[] buttons = browser.getHtmlObject(Browser.atList(s1, s2));

		if (buttons.length > 1) {
			for (int i = buttons.length - 1; i > 0; i--) {
				buttons[i].click();
			}
		}
		Browser.unregister(buttons);
		buttons = browser.getHtmlObject(Browser.atList(s1, s2));
		Browser.unregister(buttons);
		return buttons.length < 2;
	}
	
	protected Property[] vehicleTable(){
		return Property.addToPropertyArray(table(), ".id", new RegularExpression("campingInfoContainer\\_Vehicle\\_\\d+", false));
	}
	
	protected Property[] removeBtn(){
		return Property.addToPropertyArray(img(), ".className", "ra_remove");
	}
	
	protected IHtmlObject[] getVehicleTable(){
		return browser.getHtmlObject(vehicleTable());
	}
	
	public void removeFirstVechile(){
		IHtmlObject[] objs = getVehicleTable();
		browser.clickGuiObject(removeBtn(),false,0,objs[0]);
		Browser.unregister(objs);	
		this.waitLoading();
	}

	/**
	 * Get the number of the proof shown checkbox
	 *
	 * @return
	 */
	public int getNumOfProofShownCheckBox() {
		IHtmlObject objs[] = browser.getHtmlObject(".class",
				"Html.INPUT.checkbox", ".id", "proofShown_for_display");
		return objs.length;
	}

	/**
	 * get the amount of reservations
	 *
	 * @return
	 */
	public int getTotalReservationAmount() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "MainForm");
		String text = "";
		int totalResAmount = 0;

		if (objs.length > 0) {
			int rowNum = ((IHtmlTable) objs[0]).findRow(0,
					new RegularExpression("Reservation \\d+ of \\d+", false));
			text = ((IHtmlTable) objs[0]).getCellValue(rowNum, 0);
			totalResAmount = Integer.valueOf(text.split("of")[1].split("OK")[0]
					.trim());
		}
		return totalResAmount;
	}

	/**
	 * Get Adjust Fee to Past Paid dialog
	 */
	public void getAdjustFeeToPastPaidDialog() {
		browser.getAlertDialog().text();

	}

	/**
	 * get the amount of current reservation
	 *
	 * @return
	 */
	public int getCurrentReservationAmount() {
		IHtmlObject objs[] = browser.getTableTestObject(".id", "MainForm");
		String text = "";
		int currentResAmount = 0;

		if (objs.length > 0) {
			int rowNum = ((IHtmlTable) objs[0]).findRow(0,
					new RegularExpression("Reservation \\d+ of \\d+", false));
			text = ((IHtmlTable) objs[0]).getCellValue(rowNum, 0);
			currentResAmount = Integer.valueOf(text.split("of")[0]
					.split("Reservation")[1].trim());
		}
		return currentResAmount;
	}

	/**
	 * Click the Swipe Credit Card button
	 */
	public void clickSwipeCreditCard(){
		browser.clickGuiObject(".class", "Html.A",".text","Swipe Card", true);
	}

	/**
	 *Select the Swipe Credit Card type
	 *@param : index --- the option of the drop-down list
	 */
	public void selectCreditCardType(int index){
		browser.selectDropdownList(".id", "creditCardType", index);
	}

	/**
	 * Set the Credit Card Number
	 * @param cardNum --- the number of the credit card
	 */
	public void setCreditCardNum(String cardNum){
		browser.setTextField(".id", "F_CardNumber", cardNum, true);
	}

	/**
	 * Set the Credit Card Month
	 * @param mouth --- the month of the credit card
	 */
	public void setCreditCardMonth(String month){
		browser.setTextField(".id", "expiryMonth", month, true);
	}

	/**
	 * Set the Credit Card Year
	 * @param year --- the year of the credit card
	 */
	public void setCreditCardYear(String year){
		browser.setTextField(".id", "expiryYear", year, true);
	}

	/**
	 * Set the Holder Name
	 * @param holderName --- the holder of the credit card
	 */
	public void setCreditCardHolderName(String holderName){
		browser.setTextField(".id", "F_CardHolder", holderName, true);
	}

	/**
	 * Set the Swipe Credit Card information
	 * @param num
	 * @param month
	 * @param year
	 * @param holderName
	 */
	public void setSwipeInfo(int index, String cardNum, String month, String year, String holderName){
		this.selectCreditCardType(index);
		this.setCreditCardNum(cardNum);
		this.setCreditCardMonth(month);
		this.setCreditCardYear(year);
		this.setCreditCardHolderName(holderName);
	}

	/**
	 * Go to Charges page
	 */
	public void gotoChargesSubPage(){
		browser.clickGuiObject(".class", "Html.SPAN",".text","Charges");//Quentin[20140403]
		waitLoading();
	}

	/**
	 * Click pos link to goto pos detail page
	 */
	public void clickPosGotoPosDetailPage(String posId){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^POS Sale#.*",false));
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];

		for(int i = 0; i<table.rowCount();i++){
			if(posId.equalsIgnoreCase(table.getCellValue(i, 1).toString())){
				browser.clickGuiObject(".class", "Html.A", ".text",posId);
				break;
			}
		}
		Browser.unregister(objs);
	}
	/**
	 * Click Add to Cart button in Reservation Detail main page
	 */
	public void clickAddToCartInMainPage() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*invokeActionValidateTarget.*forceAddToCart.*", false));
	}

	/**
	 * Click Add to Cart button in charges sub page
	 */
	public void clickAddToCartInChargesSubPage() {
		browser.clickGuiObject(".class", "Html.A", ".href", new RegularExpression(".*validateChargeSelections.*addReservationChargesToCart.*transaction.*", false));
	}

	/**
	 * Get the Credit Card Number
	 * @return  Credit Card Number
	 */
	public String getCreditCardNum(){
		String cardNum = "";
		IHtmlObject [] objs = browser.getHtmlObject(".id", "F_CardNumber");
		cardNum = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardNum;
	}

	/**
	 * Get the Credit Card Month
	 * @return Credit Card Month
	 */
	public String getCreditCardMonth(){
		String cardMon = "";
		IHtmlObject [] objs = browser.getHtmlObject(".id", "expiryMonth");
		cardMon = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardMon;
	}

	/**
	 * Get the Credit Card Year
	 * @return Credit Card Year
	 */
	public String getCreditCardYear(){
		String cardYear = "";
		IHtmlObject [] objs = browser.getHtmlObject(".id", "expiryYear");
		cardYear = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardYear;
	}

	/**
	 * Get the Credit Card Holder Name
	 * @return  Credit Card Holder Name
	 */
	public String getCreditCardHolderName(){
		String holderName = "";
		IHtmlObject [] objs = browser.getHtmlObject(".id", "F_CardHolder");
		holderName = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return holderName;
	}

	public void testMain(Object[] args) {
		try {
			if (this.exists())
				System.out.println("ok");
			else
				System.out.println("bad");
		} catch (Exception e) {

		}
	}

	public IHtmlObject[] getReservationTable(){
		return browser.getHtmlObject(".class","Html.TABLE",".id","ReservationDetails");
	}

	/**
	 * Get text of reservation details table
	 * @return
	 */
	public String getReservationTableInfo(){
		String reservationInfo = "";
		IHtmlObject [] objs = this.getReservationTable();
		reservationInfo = objs[0].getProperty(".text");
		Browser.unregister(objs);

		return reservationInfo;
	}


	/**
	 * Select primary occupant type
	 * @param primaryOccType
	 */
	public void selectPrimaryOccType(String primaryOccType){
		if(!browser.checkHtmlObjectExists(".id","primaryOccType"))
			return;

		if(null!=primaryOccType && primaryOccType.length()>0){
			browser.selectDropdownList(".id","primaryOccType", primaryOccType);
		}else
			browser.selectDropdownList(".id","primaryOccType", 1);
	}

	public void refreshPage(){
		browser.clickGuiObject(".class","Html.FORM",".id","e_Form");
	}

	public void clickInvoiceNum() {
		browser.clickGuiObject(".class", "Html.A", ".id","ordInvID");
	}

	/**
	 * Get the ada required message.
	 * @String return the message.
	 */
	public String getAdaRequiredMessage(){
		String alertMsg = browser.getObjectText(".class", "Html.DIV", ".id",
		"NOTSET");
		return alertMsg;
	}
	
	public String getMaximumNumValidateMessage(){
		IHtmlObject[] obj = browser.getHtmlObject(".class","Html.DIV",".className", new RegularExpression("message msgerror",false));
		String errorMessage = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);

		return errorMessage;
	}
	/**
	 * get identity customer phone.
	 */
	public String getIdentityCustPhone(){
		return browser.getTextFieldValue(".id", "occupantPhone");
	}
	/**
	 * get Identity customer email.
	 */
	public String getIdentityCustEmail(){
		return browser.getTextFieldValue(".id", "occupantEmail");
	}
	/**
	 * get identity customer street address.
	 * @return
	 */
	public String getIdentityCustStreetAddress(){
		return browser.getTextFieldValue(".id", "occupantAddress");
	}
	/**
	 * get Identity Customer city.
	 * @return
	 */
	public String getIdentityCustCity(){
		return browser.getTextFieldValue(".id", "occupantCity");
	}
	/**
	 * get identity cust zip.
	 * @return
	 */
	public String getIdentityCustZip(){
		return browser.getTextFieldValue(".id", "occupantZipCode");
	}
	/**
	 * get identity customer state.
	 * @return
	 */
	public String getIdentityCustState(){
		return browser.getDropdownListValue(".id", "occupantState");
	}
	/**
	 * get identity customer country.
	 * @return
	 */
	public String getIdentityCustCountry(){
		return browser.getDropdownListValue(".id", "occupantCountry");
	}
	/**
	 * compare the billing customer detail Info.
	 * @param cust
	 * @return
	 */
	public boolean compareBillingCustDetailInfo(Customer cust){
//		boolean isEqual = true;
//		String temp = this.getCustName().split(",")[1];
//		isEqual &= MiscFunctions.compareResult("Billing custoemr first name", cust.fName, temp);
//		temp = this.getCustName().split(",")[0];
//		isEqual &= MiscFunctions.compareResult("Billing custoemr last name", cust.lName, temp);
//		isEqual &= MiscFunctions.compareResult("Billing custoemr home phone", cust.hPhone.replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""), 
//				this.getCustPhone().replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""));
//		isEqual &= MiscFunctions.compareResult("Billing custoemr email", cust.email, this.getCustEmail());
//		isEqual &= MiscFunctions.compareResult("Billing custoemr phone contact prefence", cust.phoneContact, this.getCustPhoneContractPreference());
//		isEqual &= MiscFunctions.compareResult("Billing custoemr phone contact time", cust.contactTime,  this.getCustPreferenceContactTime());
//		return isEqual;
		//Lesley[20131202]: update due to get wrong customer name in Call Manager. Handle the special scenario in Call Manager
		return this.compareBillingCustDetailInfo(cust, false);
	}
	
	public boolean compareBillingCustDetailInfoInCM(Customer cust) {
		return this.compareBillingCustDetailInfo(cust, true);
	}
	
	public boolean compareBillingCustDetailInfo(Customer cust, boolean isCallMgr){
		boolean isEqual = true;
		String temp = "";
		if (isCallMgr) {
			temp = this.getCustNameInCM();
		} else {
			temp = this.getCustName();
		}
		String[] names = temp.split(",");
		isEqual &= MiscFunctions.compareResult("Billing custoemr first name", cust.fName, names[1]);
		isEqual &= MiscFunctions.compareResult("Billing custoemr last name", cust.lName, names[0]);
		isEqual &= MiscFunctions.compareResult("Billing custoemr home phone", cust.hPhone.replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""), 
				this.getCustPhone().replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""));
		isEqual &= MiscFunctions.compareResult("Billing custoemr email", cust.email, this.getCustEmail());
		isEqual &= MiscFunctions.compareResult("Billing custoemr phone contact prefence", cust.phoneContact, this.getCustPhoneContractPreference());
		isEqual &= MiscFunctions.compareResult("Billing custoemr phone contact time", cust.contactTime,  this.getCustPreferenceContactTime());
		return isEqual;
	}	
		
	/**
	 * compare Identity customer detail info.
	 * @param cust
	 * @return
	 */
	public boolean compareIdentityCustDetialInfo(Customer cust){
		boolean isEqual = true;
		isEqual &= MiscFunctions.compareResult("First name", cust.fName,  this.getFirstName(0));
		isEqual &= MiscFunctions.compareResult("Lirst name", cust.lName, this.getLastName(0));
		isEqual &= MiscFunctions.compareResult("phone contact", cust.hPhone.replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""),
				this.getIdentityCustPhone().replaceAll("\\s+", "").replaceAll("-", "").replaceAll("\\(", "").replaceAll("\\)", ""));
		isEqual &= MiscFunctions.compareResult("email", cust.email, this.getIdentityCustEmail());
		isEqual &= MiscFunctions.compareResult("address", cust.address, this.getIdentityCustStreetAddress());
		isEqual &= MiscFunctions.compareResult("city", cust.city, this.getIdentityCustCity());
		isEqual &= MiscFunctions.compareResult("zip", cust.zip,  this.getIdentityCustZip());
		isEqual &= MiscFunctions.compareResult("State", cust.state,  this.getIdentityCustState());
		isEqual &= MiscFunctions.compareResult("country", cust.country, this.getIdentityCustCountry());
		return isEqual;
	}
	/**
	 * get 
	 * @param text
	 * @return
	 */
	public boolean getDisabledArrtibuteByText(String text){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", text);
		if(null == objs ||objs.length<1){
			throw new ErrorOnDataException("No element exist");
		}
		String value = objs[0].getProperty(".isDisabled");
		Browser.unregister(objs);
		if(value.equals("true")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * get Replace primary occupant button disabled attribute.
	 * @return
	 */
	public boolean getReplacePrimaryOccupantDisabledAttr(){
		return this.getDisabledArrtibuteByText("Replace Primary Occupant");
	}
	/**
	 * get edit primary occupant disabled attribute.
	 * @return
	 */
	public boolean getEditPrimaryOccupantDisabledAttr(){
		return browser.checkHtmlObjectExists(".class", "Html.DIV", ".text", "Edit Primary Occupant");
	}
	
	/**
	 * verify primary occupant info.
	 * @param cust
	 */
	public void verifyIdentityCustInfo(Customer cust){
		boolean isPass = this.compareIdentityCustDetialInfo(cust);
		if(!isPass){
			throw new ErrorOnPageException("The primary Occupant infro error");
		}else{
			logger.info("The primary Occupant infro correct");
		}
	}
	/**
	 * verify billing customer info.
	 * @param cust
	 */
	public void verifyBillingCustInfo(Customer cust, boolean isCallMgr){
		boolean isPass = this.compareBillingCustDetailInfo(cust, isCallMgr);
		if(!isPass){
			throw new ErrorOnPageException("The billing infro error");
		}else{
			logger.info("The billing infro correct");
		}
	}
	
	public void verifyBillingCustInfo(Customer cust){
//		boolean isPass = this.compareBillingCustDetailInfo(cust);
//		if(!isPass){
//			throw new ErrorOnPageException("The billing infro error");
//		}else{
//			logger.info("The billing infro correct");
//		}
		//Lesley[20131202]:update due to get wrong customer name in Call Manager. Handle the special scenario in Call Manager
		this.verifyBillingCustInfo(cust, false);
	}
	
	public void verifyBillingCustInfoInCallManager(Customer cust){
		this.verifyBillingCustInfo(cust, true);
	}
	/**
	 * verify replace primary occupant disabled.
	 */
	public void verifyReplacePriOccuEabled(){
		boolean isPass = this.getReplacePrimaryOccupantDisabledAttr();
		if(isPass){
			throw new ErrorOnPageException("Replace Primary Occupant should be enable");
		}else{
			logger.info("Replace Primary Occupant is enable");
		}
	}
	/**
	 * verify edit primary occupant disabled.
	 */
	public void verifyEditPrimaryOccupantDisable(){
		boolean isDisable = this.getEditPrimaryOccupantDisabledAttr();
		if(!isDisable){
			throw new ErrorOnPageException("Replace Edit Primary Occupant should be disable");
		}else{
			logger.info("Edit Primary Occupant is disable");
		}
	}
	/**
	 * verify edit primary occupant enable.
	 */
	public void verifyEditPrimaryOccupantEnabled(){
		boolean isDisable = this.getEditPrimaryOccupantDisabledAttr();
		if(!isDisable){
			throw new ErrorOnPageException("'Edit Primary Occupant' should be enable");
		}else{
			logger.info("'Edit Primary Occupant' is enable");
		}
	}
	/**
	 * get the same as customer check box selected or not.
	 */
	public void verifySameAsCustomerCheckboxSelected(){
		boolean isSelect = browser.isCheckBoxSelected(".id", "isSameAsCustomer");
		if(!isSelect){
			throw new ErrorOnPageException("Same As Customer Checkbox should be selected");
		}else{
			logger.info("Same As Customer Checkbox is selected");
		}
	}
	
	/**
	 * get the same as customer check box selected or not.
	 */
	public void verifySameAsCustomerCheckboxUnSelected(){
		boolean isSelect = browser.isCheckBoxSelected(".id", "isSameAsCustomer");
		if(isSelect){
			throw new ErrorOnPageException("Same As Customer Checkbox should be UnSelected");
		}else{
			logger.info("Same As Customer Checkbox is UnSelected");
		}
	}
	
	public String getState(){
		return browser.getObjectText(".class", "Html.DIV", ".text", new RegularExpression("^State", false)).replaceAll("State", StringUtil.EMPTY);
	}
	
	/** Click on the "Transfer" button */
	public void clickTransfer() {
		clickResActionButton("Transfer");
	}
	
	public boolean isTransferEnable(){
		// Don't change Html.A to Html.DIV, otherwise many test cases will be failed!!!!!!!
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text", new RegularExpression("Transfer",false));
	}
	
	public boolean isTransferBtnEnable(){
		// This method was used for mid-stay transfer page verification
		return browser.checkHtmlObjectEnabled(".class", "Html.DIV", ".text", new RegularExpression("Transfer",false));
	}
	
	public void clickAddPet(){
		clickResActionButton(new RegularExpression("Add Pet", false));
	}

	/**Get the information of the Occupant Type Max Limit*/
	public Map<String, String> getOccupantTypeMaxLimitInfo(){
		Map<String,String> map=new HashMap<String,String>();
		
		IHtmlObject[] obj = browser.getTableTestObject(".id", new RegularExpression("campingInfoContainer_Occupant_\\d+",false));
		IHtmlTable table=(IHtmlTable)obj[0];  
		String targetStr=table.getCellValue(0, 0);
		String[] temp=targetStr.split(",")[0].split(":")[1].trim().split(" ");
		map.put(temp[1],temp[0]);
		return map;
	}
	
	public void clickRequestConfLetter() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Request Conf Letter");
	}
	
	protected Property[] voidBtn(){
		return Property.addToPropertyArray(a(), ".text", "Void");
	}
	
	public boolean isVoidBtnEndabled(){
		return browser.checkHtmlObjectEnabled(this.voidBtn());
	}
	
	public void verifyVoidBtnStatus(boolean enabled){
		if(enabled&&!this.isVoidBtnEndabled()){
			throw new ErrorOnPageException("The void button should be enabled!");
		}
		if(!enabled&&this.isVoidBtnEndabled()){
			throw new ErrorOnPageException("The void button should not be enabled!");
		}
	}
}
