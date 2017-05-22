package com.activenetwork.qa.awo.pages.orms.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.datacollection.legacy.CustPass;
import com.activenetwork.qa.awo.datacollection.legacy.CustType;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.ObjectNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.ISelect;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * 
 * @Description: this page is the common of OrmsCampingReservationDetailsPage
 *               and OrmsSlipReservationsDetailsPage
 * @Preconditions:
 * @SPEC:
 * @Task#:
 * 
 * @author qchen
 * @Date Oct 22, 2012
 */
public abstract class OrmsReservationDetailsCommonPage extends OrmsPage {
	/** Click Change Collection Status button */
	public void clickCollectionStatus() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Change Collection Status", true);
	}

	/** Click swapsite button */
	public void clickSwapSite() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Swap Sites", true);
	}

	/** Click on the "Date Change" button */
	public void clickDateChange() {
		clickResActionButton("Date Change");
	}

	/** Click on the "Cancel Res." button */
	public void clickCancelRes() {
		clickResActionButton("Cancel Res.");
	}

	public boolean cancelable() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Cancel Res.");
	}

	public boolean voidable() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Void");
	}

	/** Click on the "Void" button */
	public void clickVoid() {
		clickResActionButton("Void");
	}

	/** Click on the "No Show" button */
	public void clickNoShow() {
		clickResActionButton("No Show");
	}

	/** Click Replace Primary Occupant */
	public void clickReplacePrimaryOccupant() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Replace Primary Occupant");
	}

	/** Click Edit Primary Occupant */
	public void clickEditPrimaryOccupant() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Edit Primary Occupant");
	}

	/** Click on the "Add To Cart" button */
	public void clickAddToCart() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Add To Cart", false), true);
		// clickResActionButton("Add To Cart");
	}

	/**
	 * Click on "Cart" to return back to order cart
	 */
	public void clickCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cart");
	}

	/** Click on the "Finish" link */
	public void clickFinish() {
		clickResActionButton("Finish");
	}

	/** Click on the "Fees" button */
	public void clickFees() {
		IHtmlObject[] frames = getTransactionFrame();
		if (frames.length < 1)// handled with 3.04.02 Marina reservation details
								// page
			frames = browser.getHtmlObject(".class", "Html.DIV", ".id",
					"MarinaOrderDetailPage");
		if (frames.length < 1)// handled with 3.04.03 List Entry Details page
			frames = browser.getHtmlObject(".class", "Html.Table", ".text",
					new RegularExpression("^List Actions.*", false));
		browser.clickGuiObject(".class", "Html.A", ".text", "Fees", true, 0,
				frames[0]);
		Browser.unregister(frames);
	}

	public void clickCustomer() {
		browser.clickGuiObject(
				".class",
				"Html.A",
				".href",
				new RegularExpression(
						"javascript:invokeActionTarget\\( \"CallMgrCustomerDetails\\.do\".*",
						false));
	}

	/** Click on the "History" button */
	public void clickHistory() {
		clickResActionButton("History");
	}

	/** Void the reservation */
	public void voidReservation() {
		clickVoid();
	}

	/** Click Availability button */
	public void clickAvailability() {
		clickResActionButton("Availability");
	}

	/** Click notes and alert button */
	public void clickNotesAndAlert() {
		clickResActionButton("Notes & Alerts");
	}

	/** Click specific site link */
	public void clickSiteLink() {
		String siteName = getOccupiedSite();
		if (siteName != null && !siteName.equals("")) {
			clickResActionButton(siteName);
		} else {

			siteName = getSiteName();
			if (siteName != null && !siteName.equals("")) {
				clickResActionButton(siteName);
			}
		}
	}

	public void selectGroup() {
		// browser.selectRadioButton(".id", "orderRatetype", 1);
		browser.selectRadioButton(".id", "orderRateType", ".value", "2");
	}

	/** Select all POS charges to Reservation */
	public void selectAllChargePos() {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".name",
				"all_slct");
	}

	/** Check whether "removecustomerpass" exist */
	public boolean checkRemoveCustomerPassExist() {
		boolean result = false;
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				new RegularExpression(" ?Remove Customer Pass", false));
		if (obj.length > 1)
			result = true;
		Browser.unregister(obj);
		return result;
	}

	/** Check whether "addcustomerpass" button exist */
	public boolean checkAddCustomerPassExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				new RegularExpression(" ?Add Customer Pass", false));
	}

	/** Check Charges sub page exist */
	public boolean checkChargesPageExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"POS Sale#");
	}

	/** click add customer pass button */
	public void clickAddCustomerPass() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression(" ?Add Customer Pass", false), true);
	}

	/**
	 * Select the customer pass type
	 * 
	 * @param custPass
	 */
	public void selectCustPass(String custPass) {
		if (StringUtil.notEmpty(custPass)) {
			browser.selectDropdownList(".id", "passTypeId", custPass, 1);
		}
	}

	public void selectCustPass(String custPass, int idx) {
		if (StringUtil.notEmpty(custPass)) {
			browser.selectDropdownList(".id", "passTypeId", custPass, idx);
		}
	}

	/**
	 * Set the pass number
	 * 
	 * @param passNum
	 */
	public void setPassNum(String passNum) {
		browser.setTextField(".id", "passNumber", passNum, 1);
	}

	public void setPassNum(String passNum, int idx) {
		browser.setTextField(".id", "passNumber", passNum, idx);
	}

	public void setHolderName(String holderName, int idx) {
		browser.setTextField(".id", "holderName", holderName, idx);
	}

	/**
	 * Set the access pass note
	 * 
	 * @param note
	 */
	public void setAccessPassNote(String note) {
		browser.setTextField(".id", "notes", note, 1);
	}

	public void setCustomerPassNote(String note, int idx) {
		browser.setTextField(".id", "notes", note, idx);
	}

	/**
	 * Set the access pass note
	 * 
	 * @param note
	 */
	public void setAccessPassHolderName(String HolderName) {
		browser.setTextField(".id", "holderName", HolderName, 1);
	}

	/**
	 * Set the access pass note
	 * 
	 * @param note
	 */
	public void setAccessPassExpireDate(String date) {
		browser.setTextField(".id", "expiryDate_ForDisplay", date, 1);
	}

	public void setCustomerPassExpireDate(String date, int idx) {
		browser.setTextField(".name", "expiryDate_ForDisplay", date, idx);
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
	private String getOccupiedSite() {
		String toParse = getReservationDetailsCellValue(2, 1);

		int start = toParse.indexOf("Site# (Name)") + "Site# (Name)".length();
		// Park LEWEY LAKE Area Sites 1 - 100 Site Group# (Name)
		// CDEB40N-LEWECDDB40N Occupied Site# (Name) 006-LEWECDDB406 Type of Use
		// OverNight Permit #
		// Park ALLEGANY STATE PARK Area Group Camps Site# (Name)
		// 010-REDCSATGROP10 Type of Use OverNight Permit # 736866
		int end = toParse.indexOf("Type of Use");
		return toParse.substring(start + 1, end).trim();
	}

	/** Click resaction button */
	protected void clickResActionButton(String buttonName) {
		if (buttonName.equalsIgnoreCase("OK")) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression("OK|Finish", false), true);
		} else {
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression("^" + buttonName + "$", false), true);
		}

	}

	/** Click resaction button */
	private void clickResActionButton(RegularExpression buttonName) {
		browser.clickGuiObject(".class", "Html.A", ".text", buttonName, true);
	}

	/** Get cell value of the charges page */
	public String getChargeCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^POS Sale#.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

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
		list.add(Property.toPropertyArray(".class", "Html.TABLE", ".id",
				"CampingUnits"));
		list.add(Property.toPropertyArray(".text", "Remove"));

		IHtmlObject[] buttons = browser.getHtmlObject(list);

		int size = buttons.length - 1;
		Browser.unregister(buttons);
		return size;
	}

	/**
	 * Click add unit button and input the quality of the unit
	 * 
	 * @param qty
	 *            ---The quantity of the unit
	 * @param plant
	 *            -- camping_unit_license
	 */
	public void addAnyUnit(int qty, String plate, String unitstate) {

		if (qty < 1)
			qty = 1;
		if (this.checkBottonOrLinkExists(new RegularExpression("Add Unit",
				false))) {
			this.clickResActionButton(new RegularExpression("Add Unit", false));
			if (browser.getDropdownElements(".id", "campingUnitType").size() > 0) {// If
				// the
				// unit
				// drop
				// down
				// has
				// elements,
				// then
				// input
				// qty
				browser.setTextField(".id", "qty", Integer.toString(qty), 1);
				try {
					IHtmlObject[] objs = browser.getTextField(".id",
							"camping_unit_license");
					if (objs.length > 1 && plate != null && !plate.equals("")) {
						((IText) objs[1]).setText(plate);
					}
					Browser.unregister(objs);

					// HtmlObject[]
					// stateObj=browser.getDropdownList(".id","camping_unit_state");
					// if(stateObj.length>1&&unitstate!=null&&unitstate.length()>0){
					// ((ISelect)stateObj[1]).select(unitstate);
					// }

					// Browser.unregister(stateObj);
				} catch (Exception e) {

				}
			}
		}
		browser.clickGuiObject(".class", "Html.LABEL", ".text", "Pets Allowed");
	}

	/**
	 * Change qty of unit
	 * 
	 * @param qty
	 */
	public void changeNumOfUnit(int qty) {

		if (this.getNumOfRemoveButton() < 4) {
			// this.clickResActionButton(new RegularExpression("^ ?Add Unit ?",
			// false));
			this.clickResActionButton(new RegularExpression("Add Unit", false));
		}

		browser.setTextField(".id", "qty", Integer.toString(qty), 1);

	}

	public int getNumOfRemoveButton() {
		RegularExpression reg = new RegularExpression("^ ?Remove", false);
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				reg);

		return obj.length;
	}

	/**
	 * Click add unit button and input the quality of the unit
	 * 
	 * @param qty
	 *            ---The quaility of the unit
	 */
	public void addAnyUnit(int qty) {
		this.addAnyUnit(qty, null, null);
	}

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
	public boolean regularCheckInIsExist() {
		return browser.checkHtmlObjectExists(".id", "fastCheckin", ".value",
				"false");
	}

	/**
	 * This method firstly check if the checkin checkbox exists.
	 */
	public void selectCheckIn() {
		browser.selectCheckBox(".id", "checkIn");
	}

	/** If the checkbox is selected, it return true,or return false */
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
		browser.selectDropdownList(
				".id",
				new RegularExpression(
						"MarinaOrderProfileView-\\d+\\.currentPromoCode|orderPromoCode",
						false), promoCode);
	}

	public String getPromoCode() {
		return browser
				.getDropdownListValue(
						".id",
						new RegularExpression(
								"MarinaOrderProfileView-\\d+\\.currentPromoCode|orderPromoCode",
								false));
	}

	public List<String> getPromoCodeValues() {
		return browser
				.getDropdownElements(
						".id",
						new RegularExpression(
								"MarinaOrderProfileView-\\d+\\.currentPromoCode|orderPromoCode",
								false));
	}

	public boolean comparePromoCodeList(List<String> expected) {
		List<String> actual = this.getPromoCodeValues();

		if (StringUtil.isEmpty(actual.get(0).trim()))
			actual.remove(0);

		boolean result = true;
		if (expected.size() != actual.size())
			throw new ErrorOnPageException("Promo Code list size",
					expected.size(), actual.size());

		for (int i = 0; i < expected.size(); i++) {
			if (!actual.contains(expected.get(i))) {
				logger.error("Promotional Discount dropdown list shall contain option - "
						+ expected.get(i));
				result &= false;
			}
			logger.info("Promotional Discount dropdown list does contain option - "
					+ expected.get(i));
		}

		return result;
	}

	public void verifyPromoCodeList(List<String> expected) {
		if (!comparePromoCodeList(expected))
			throw new ErrorOnPageException(
					"Promotional Discount dropdown list options are not correct, please refer above log for details.");
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
	public void selectSameAsCustomer() {
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
	 * 
	 * @return true: is checked false: not checked
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

	/** If checkin check box selected */
	public boolean isCheckInSelected() {
		return browser.isCheckBoxSelected(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.checkin|checkIn", false));
	}

	/** If checkout checkbox enabled, select checkout checkbox */
	public void selectCheckOut() {
		browser.selectCheckBox(".id", "checkOut");
	}

	/** If checkout check box selected */
	public boolean isCheckOutSelected() {
		return browser.isCheckBoxSelected(".id", "checkOut");
	}

	public boolean isCheckOutExist() {
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
		// RegularExpression regx = new
		// RegularExpression(".*validReservationDetailForOK.*", false);
		Property[] p = Property.toPropertyArray(".class", "Html.A", ".text",
				"OK");
		browser.clickGuiObject(p);
	}

	public boolean isOkBtnEnable() {
		return browser
				.checkHtmlObjectEnabled(".class", "Html.A", ".text", "OK");
	}

	public boolean isApplyBtnEnable() {
		return browser.checkHtmlObjectEnabled(".class", "Html.A", ".text",
				"Apply");
	}

	/** Click cancel button */
	public void clickCancel() {
		this.clickResActionButton("Cancel");
	}

	/** Click add unit button */
	// public void clickAddUnit() {
	// this.clickResActionButton("Add Unit");
	// }

	public void removeCampingUnit(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
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

	public void selectCustomerTypeProofShown(int array) {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				"1_proofShown", array);
	}

	public boolean getCustomerTypeProofShown(int index) {
		return browser.isCheckBoxSelected(".id", "1_proofShown", index);
	}

	public void selectCustomerPassProofShown(int array) {
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

	public boolean isOtherOccSectionExist() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Other Occupants");
	}

	public boolean isVehicleInfoSectionExist() {
		return browser.checkHtmlObjectExists(".class", "Html.TD", ".text",
				"Vehicle Info");
	}

	/**
	 * Check the # of Other Occ is existed
	 * 
	 * @return true : exist
	 */
	public boolean isOtherOccNumExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "otherOccList_control");
	}

	public boolean isOtherOccMaxTotalAllowedExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				"Max Total Allowed");
	}

	public boolean isVehicleNumExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.text",
				".id", "VehicleList_control");
	}

	/**
	 * Get the default value of the # of Other Occ
	 * 
	 * @return The defaul value
	 */
	public int getDefaulOtherOccNum() {
		int defaultNum = -1;
		if (isOtherOccNumExist()) {
			IHtmlObject[] objs = browser.getHtmlObject(".class",
					"Html.INPUT.text", ".id", "otherOccList_control");
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
		if (!browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"reasonID"))
			return;

		if (reason != null && reason.length() > 0)
			browser.selectDropdownList("id", "reasonID", reason);
		else
			browser.selectDropdownList("id", "reasonID", 1);
	}

	public List<String> getChangeDateReasons() {
		return browser.getDropdownElements(".class", "Html.SELECT", ".id",
				"reasonID");
	}

	/** Check the "Change Dates Reason" button exists. */
	public boolean checkSelectReasonExist() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"reasonID");
	}

	/** Check the "Transfer Reason" label */
	public boolean checkTransferReason() {
		return browser.checkHtmlObjectExists(".class", "Html.LABEL", ".text",
				"Transfer Reason");
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
	 * Get specific reservation details sections string from one string to
	 * another string
	 * 
	 * @param fString
	 *            ---From which string
	 * @param lString
	 *            --- to which string
	 * @param selectionIndex
	 *            ---1:Reservation section ---2:Location section ---3:Invoice
	 *            section ---4:Customer section ---5:Note/Alerts section
	 * @return
	 */
	public String getReservationDetailsString(String fString, String lString,
			int selectionIndex, boolean isSpecialOpPage) {
		// for Transfer and Date Change an extra row is added to top of table..
		if (isSpecialOpPage) {
			selectionIndex = selectionIndex + 1;
		}

		String theString = this.getReservationDetailsCellValue(selectionIndex,
				1);
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
		return getReservationDetailsString("Reservation #", "Arrival",
				selectonIndex, isTransferOrDateChange);
	}

	/** Get arrive date */
	public String getArriveDate() {
		return getReservationString("Arrival", "Departure");
	}

	/** Get depart date */
	public String getDepartDate() {
		return getReservationString("Departure", "Nights");
	}

	public String getDaysDepartDate() {
		return getReservationString("Departure", "Days");
	}

	/** Get created date */
	public String getCreatedDate() {
		return getReservationString("Created Date", "Created By");
	}

	/** Get created date */
	public String getCreatedBy() {
		return getReservationString("Created By", "Price");
	}

	/** Get night number */
	public int getNightNum() {
		return Integer.parseInt(getReservationString("Nights", "Status"));
	}

	public int getDaysNightNum() {
		return Integer.parseInt(getReservationString("Days", "Status"));
	}

	/** Get park */
	public String getPark() {
		return getLocationString("Park", "Area");
	}

	/** Get Area */
	public String getArea() {
		return getLocationString("Area", "Site# (Name)");
	}

	/** Get Area */
	public String getTypeOfUse() {
		return getLocationString("Type of Use", "");
	}

	/** Get customer name */
	public String getCustName() {
		return getCustomerString("Name", "Phone");
	}

	/** Get customer phone */
	public String getCustPhone() {
		return getCustomerString("Phone", "Email");
	}

	/** Get customer Email */
	public String getCustEmail() {
		return getCustomerString("Email", "Phone Contact Preference");
	}

	/** Get customer Phone Contact Preference */
	public String getCustPhoneContractPreference() {
		return getCustomerString("Phone Contact Preference",
				"Preference Contact Time");
	}

	/** Get customer Preference Contact Time */
	public String getCustPreferenceContactTime() {
		return getCustomerString("Preference Contact Time", "");
	}

	/** Get occupant # */
	public String getOccupantNum() {
		String occupantNum = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				"otherOccList_control");
		if (objs.length > 0) {
			occupantNum = objs[0].getProperty(".text").toString();
		} else
			throw new ObjectNotFoundException("Object can't find.");

		return occupantNum;
	}

	/** Get vehicles # */
	public String getVehiclesNum() {
		String vehiclesNum = "";
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", "VehicleList_control");
		if (objs.length > 0) {
			vehiclesNum = objs[0].getProperty(".text").toString();
		} else
			throw new ObjectNotFoundException("Object can't find.");

		return vehiclesNum;
	}

	// protected String getAttributeByName(String name) {
	// IHtmlObject objs[] =
	// browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN",
	// ".className", "inputwithrubylabel", ".text", new RegularExpression("^" +
	// name, false)));
	// if(objs.length < 1) {
	// throw new ItemNotFoundException("Can't find attribute by Name - " +
	// name);
	// }
	//
	// String text = objs[0].text().replaceAll(name, StringUtil.EMPTY).trim();
	// Browser.unregister(objs);
	//
	// return text;
	// }
	protected Property[] reservationDetailsTable() {
		return Property.concatPropertyArray(this.table(), ".id", "MainForm");
	}

	protected Property[] labelSpan(String name) {
		return Property.toPropertyArray(".class", "Html.SPAN", ".className",
				"inputwithrubylabel", ".text", new RegularExpression(
						"^" + name, false));
	}

	protected String getAttributeByName(String name) {
		IHtmlObject[] frame = browser.getFrame("transaction");
		IHtmlObject objs[];
		if (frame.length > 0) {
			objs = browser.getHtmlObject(Property.toPropertyArray(".class",
					"Html.SPAN", ".className", "inputwithrubylabel", ".text",
					new RegularExpression("^" + name, false)), frame[0]);
		} else {
			objs = browser.getHtmlObject(Property.toPropertyArray(".class",
					"Html.SPAN", ".className", "inputwithrubylabel", ".text",
					new RegularExpression("^" + name, false)));
		}
		// IHtmlObject objs[] =
		// browser.getHtmlObject(Property.atList(this.reservationDetailsTable(),
		// this.labelSpan(name)));//Lesley[20131122]update due to many objects
		// with the same property exists
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - "
					+ name);
		}

		// String text = objs[0].text().split(name)[1].trim(); For the text
		// which is empty, the lenght of the array will be just 1 after split
		// String text = objs[0].text().replace(name, StringUtil.EMPTY).trim();
		String text = objs[0].text().replaceAll(name, StringUtil.EMPTY).trim();

		Browser.unregister(objs);

		return text;
	}
	
	public String getSlipReservationNum(){
		return this.getAttributeByName("Slip Reservation #");
	}

	/**
	 * Retrieve reservation status
	 * 
	 * @param String
	 *            ---return the status of the reservation
	 */
	public String getReservStatus() {
		return this.getAttributeByName("Order Status");
	}

	/**
	 * Get the error message if the reservation could not be void
	 * 
	 * @return error message
	 */
	public boolean checkErrorMessageExits() {
		return browser.checkHtmlObjectExists(".id", "NOTSET");
	}

	/**
	 * Get the error message if the reservation could not be void
	 * 
	 * @return error message
	 */
	public String[] getErrorMessages() {
		// HtmlObject[] obj = browser.getHtmlObject(".id", "NOTSET");
		IHtmlObject[] obj = browser.getHtmlObject(".className",
				new RegularExpression("message msgerror|message", false));
		if(obj.length < 1) {
			throw new ItemNotFoundException("Cannot find any error message.");
		}
		
		String msgs[] = new String[obj.length];
		for(int i = 0; i < obj.length; i ++) {
			msgs[i] = obj[i].getProperty(".text").toString();
		}
		
		Browser.unregister(obj);
		return msgs;
	}
	
	public String getErrorMessage() {
		String msgs[] = getErrorMessages();
		return msgs[msgs.length - 1];
	}

	public String getStatusMessage() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", new RegularExpression(
				"V-\\d+", false), ".className", "message");
		if (obj.length < 1) {
			throw new ItemNotFoundException("Did not found status message.");
		}
		String errorMessage = obj[0].text();

		Browser.unregister(obj);
		return errorMessage;
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
		return getReservationString("Order Status", "Created Date");
	}

	/**
	 * Get Zip
	 */
	public String getZipCode() {
		IHtmlObject[] objs = browser.getHtmlObject(".id", "occupantZipCode");
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
		// RegularExpression hrefreg = new RegularExpression(
		// ".*,\"chargePOSToRes\",.*", false);
		IHtmlObject[] topFrame = getTransactionFrame();
		if (topFrame != null && topFrame.length > 0) {// for field
														// manager,multiple
														// frame exist
			browser.clickGuiObject(".class", "Html.A", ".text", "Charge POS",
					false, 0, topFrame[0]);
		} else {
			browser.clickGuiObject(".class", "Html.A", ".text", "Charge POS");
		}

	}

	/** Click Request Refund button */
	public void clickRequestRefund() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Request Refund");
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
		if (item == null || item.length() < 1) {
			browser.selectDropdownList(".id", "campingUnitType", ".classIndex",
					index + "", 0);
		}

		else {
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

	public String getSelectedCampingUnitType(int index) {
		return browser.getDropdownListValue(".id", "campingUnitType", index);
	}

	public void selectCampingUnitType(String unitType, int index) {
		browser.selectDropdownList(".id", "campingUnitType", unitType, index);
	}

	public void setLengthBoxValue(String length, int index) {
		browser.setTextField(".id", "length", length, index);
	}

	public boolean isLengthBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Length", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getLength(int index) {
		IHtmlObject[] objs = browser.getTextField(".id", "length");
		String length = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return length;
	}

	public void setQtyBoxValue(String qtyNum, int index) {
		browser.setTextField(".id", "qty", qtyNum, index);
	}

	public boolean isQtyBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Qty", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getQtyValue() {
		IHtmlObject[] objs = browser.getTextField(".id", "qty");
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get any qty object.");
		}
		String qty = ((IText) objs[objs.length-1]).getText();
		Browser.unregister(objs);
		return qty;
	}

	public void setPlateBoxValue(String plateValue, int index) {
		browser.setTextField(".id", new RegularExpression(
				"camping_unit_license|license", false), plateValue, index);
	}

	public boolean isPlateBoxDisplay(int index) {
		if (getCampingUnitDivStyle("Plate", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public boolean isRequestRefundExist() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Request Refund");
	}

	public boolean isRequestRefundDisabled() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Request Refund");
		String abled = objs[0].getProperty(".disabled");
		Browser.unregister(objs);
		if (abled.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Check the Add Other Occupant button is abled
	 * 
	 * @return true: abled
	 */
	public boolean isAddOtherOccupantAble() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Add Other Occupant");
		String status = objs[0].getProperty(".disabled").trim();
		Browser.unregister(objs);

		if (status.equalsIgnoreCase("false")) {
			return true;
		} else {
			return false;
		}
	}

	public String getPlate(int index) {
		IHtmlObject objs[] = getCampingUnitTableObject();
		String text = browser
				.getTextFieldValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(CampingUnitVesselInfo-\\d+\\.stateId|camping_unit_license|license)|(campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:\\d+_camping_unit_license)",
										false)), index, objs[objs.length - 1]);
		Browser.unregister(objs);
		return text;
	}

	private IHtmlObject[] getCampingUnitTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(".id",
				new RegularExpression("CampingUnitsList|VehicleList|(campingInfoContainer_CampingUnit_\\d+)", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Cannot find camping unit table object.");
		}
		return objs;
	}

	public void selectStateValue(String state, int index) {
		IHtmlObject topObjs[] = getCampingUnitTableObject();
		browser.selectDropdownList(
				Property.toPropertyArray(
						".id",
						new RegularExpression(
								"CampingUnitVesselInfo-\\d+\\.stateId|camping_unit_state|state",
								false)), state, true, index,
				topObjs[topObjs.length - 1]);
		Browser.unregister(topObjs);
	}

	public boolean isStateDisplay(int index) {
		if (getCampingUnitDivStyle("State", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getSelectedState(int index) {
		// return browser.getDropdownListValue(".id", new
		// RegularExpression("camping_unit_state|state",false), index);
		IHtmlObject objs[] = getCampingUnitTableObject();
		String value = browser.getDropdownListValue(Property
				.toPropertyArray(".id", new RegularExpression(
						"(camping_unit_state|state)|(campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:\\d+_camping_unit_state)", false)), index,
				objs[objs.length - 1]);
		Browser.unregister(objs);

		return value;
	}

	public void selectMakeValue(String make, int index) {
		IHtmlObject objs[] = getCampingUnitTableObject();
		browser.selectDropdownList(
				Property.toPropertyArray(
						".id",
						new RegularExpression(
								"CampingUnitVesselInfo-\\d+\\.manufacturer|camping_unit_make|make",
								false)), make, true, index,
				objs[objs.length - 1]);
		Browser.unregister(objs);
	}

	public boolean isMakeDisplay(int index) {
		System.out.println(getCampingUnitDivStyle("Make", index));
		if (getCampingUnitDivStyle("Make", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getSelectedMake(int index) {
		// return browser.getDropdownListValue(".id", new
		// RegularExpression("CampingUnitVesselInfo-\\d+\\.manufacturer|camping_unit_make|make",
		// false), index);//camping_unit_make
		IHtmlObject objs[] = getCampingUnitTableObject();
		String text = browser
				.getDropdownListValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(CampingUnitVesselInfo-\\d+\\.manufacturer|camping_unit_make|make)|" +
										"(campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:\\d+_camping_unit_make)",
										false)), index, objs[objs.length - 1]);
		Browser.unregister(objs);
		return text;
	}

	public void setModelValue(String model, int index) {
		IHtmlObject objs[] = getCampingUnitTableObject();
		browser.setTextField(".id", new RegularExpression(
				"CampingUnitVesselInfo-\\d+\\.model|camping_unit_model|model",
				false), model, true, index, objs[objs.length - 1]);
		Browser.unregister(objs);
	}

	public boolean isModelDisplay(int index) {
		if (getCampingUnitDivStyle("Model", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getModelValue(int index) {
		// return browser.getTextFieldValue(".id", new
		// RegularExpression("CampingUnitVesselInfo-\\d+\\.model|camping_unit_model|model",
		// false),index);
		IHtmlObject objs[] = getCampingUnitTableObject();
		String text = browser
				.getTextFieldValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(CampingUnitVesselInfo-\\d+\\.model|camping_unit_model|model)|" +
										"(campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:\\d+_camping_unit_model)",
										false)), index, objs[objs.length - 1]);
		Browser.unregister(objs);
		return text;
	}

	public void selectColorValue(String color, int index) {
		IHtmlObject objs[] = getCampingUnitTableObject();
		browser.selectDropdownList(
				Property.toPropertyArray(
						".id",
						new RegularExpression(
								"(CampingUnitVesselInfo-\\d+\\.color|camping_unit_color|color)|" +
								"(campingInfoContainer_CampingUnit_\\d+_campingunits-row\\:\\d+_camping_unit_color)",
								false)), color, true, index,
				objs[objs.length - 1]);// camping_unit_color
		Browser.unregister(objs);
	}

	public boolean isColorDisplay(int index) {
		if (getCampingUnitDivStyle("Color", index).equalsIgnoreCase(
				"DISPLAY: none")) {
			return false;
		} else
			return true;
	}

	public String getSelectedColor(int index) {
		// return browser.getDropdownListValue(".id", new
		// RegularExpression("CampingUnitVesselInfo-\\d+\\.color|camping_unit_color|color",
		// false), index);//camping_unit_color
		IHtmlObject objs[] = getCampingUnitTableObject();
		String text = browser
				.getDropdownListValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"CampingUnitVesselInfo-\\d+\\.color|camping_unit_color|color",
										false)), index, objs[objs.length - 1]);
		Browser.unregister(objs);

		return text;
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
			String subtext = outerHtml
					.substring(outerHtml.indexOf("style=") + 7);
			style = subtext.substring(0, subtext.indexOf("\""));
		}
		return style;
	}

	public void setAllCampingUnitValue(ReservationInfo res, int index) {
		this.selectCampingUnitType(res.site.validCampingUnit, index);
		if (res.site.vehicleLenth != null && res.site.vehicleLenth.length() > 0) {
			this.setLengthBoxValue(
					new Integer(res.site.vehicleLenth).toString(), index);
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
	public void verifyCampingUnitInfoCorrect(ReservationInfo res, int index) {
		logger.info("Verify Camping Unit Info Correct.");

		if (!res.site.validCampingUnit.equalsIgnoreCase(this
				.getSelectedCampingUnitType(index))) {
			throw new ErrorOnDataException("Camping Unit Type Not Correct.");
		}
		if (res.site.vehicleLenth != null && res.site.vehicleLenth.length() > 0) {
			if (!res.site.vehicleLenth.equalsIgnoreCase(this.getLength(index))) {
				throw new ErrorOnDataException(
						"Camping Unit Length Not Correct.");
			}
		}
		if (res.site.validCampingUnitQty > 0) {
			if (!String.valueOf(res.site.validCampingUnitQty).equalsIgnoreCase(
					this.getQtyValue())) {
				throw new ErrorOnDataException("Camping Unit Qty Not Correct.");
			}
		}
		if (res.site.campingUnitPlate != null
				&& res.site.campingUnitPlate.length() > 0) {
			if (!res.site.campingUnitPlate.equalsIgnoreCase(this
					.getPlate(index))) {
				throw new ErrorOnDataException(
						"Camping Unit Plate Not Correct.");
			}
		}
		if (res.site.unitState != null && res.site.unitState.length() > 0) {
			if (!res.site.unitState.equalsIgnoreCase(this
					.getSelectedState(index))) {
				throw new ErrorOnDataException(
						"Camping Unit State Not Correct.");
			}
		}
		if (res.site.unitMake != null && res.site.unitMake.length() > 0) {
			if (!res.site.unitMake
					.equalsIgnoreCase(this.getSelectedMake(index))) {
				throw new ErrorOnDataException("Camping Unit Make Not Correct.");
			}
		}
		if (res.site.vehicleModel != null && res.site.vehicleModel.length() > 0) {
			if (!res.site.vehicleModel.equalsIgnoreCase(this
					.getModelValue(index))) {
				throw new ErrorOnDataException(
						"Camping Unit Model Not Correct.");
			}
		}
		if (res.site.unitColor != null && res.site.unitColor.length() > 0) {
			if (!res.site.unitColor.equalsIgnoreCase(this
					.getSelectedColor(index))) {
				throw new ErrorOnDataException(
						"Camping Unit Color Not Correct.");
			}
		}
	}

	/**
	 * This method used to select save to primary occupant profile check box
	 */
	public void selectSaveToOccProfileBox() {
		IHtmlObject[] objs = browser
				.getCheckBox(".id", "SaveCampUnitToProfile");
		((ICheckBox) objs[0]).select();
		Browser.unregister(objs);
	}

	/**
	 * This method used to deselect save to primary occupant profile check box
	 */
	public void deselectSaveToOccProfileBox() {
		IHtmlObject[] objs = browser
				.getCheckBox(".id", "SaveCampUnitToProfile");
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
		String unitType = browser.getDropdownListValue(".id",
				"campingUnitType", Integer.valueOf(index));

		// "campingUnitType", ".classIndex", index, 0); //UI changed

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
		browser.setTextField(".id", "otherOccList_control", num, 0,
				IText.Event.LOSEFOCUS);
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
	public void setNumOfVehicles(String num) {
		browser.setTextField(".id", "VehicleList_control", num, 0,
				IText.Event.LOSEFOCUS);
	}

	/**
	 * Retrieve the max allowed vehicles
	 * 
	 * @return
	 */
	public String getMaxAllowedVehicles() {
		IHtmlObject[] trObjs = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression(
						"^# of Vehicles( )?Max Allowed.*", false));
		if (trObjs.length < 1) {
			throw new ItemNotFoundException(
					"Did not get Vehicle info TR object.");
		}
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Max Allowed.*", false),
				trObjs[0]);
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not get Max Allowed Vehicle info DIV object.");
		}
		String text = objs[0].text().replaceAll("Max Allowed", "").trim();
		Browser.unregister(objs);
		Browser.unregister(trObjs);
		return text;
	}

	/**
	 * Retrieve the num of the vehicles
	 * 
	 * @return
	 */
	public String getNumofVehicles() {
		IHtmlObject[] vehicle = browser.getHtmlObject(".id",
				"VehicleList_control");
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
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Customer Type");
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
		IHtmlObject[] objs = browser.getDropdownList(".id", index
				+ "_proofType");
		((ISelect) objs[objectindex]).select(customertype);
		Browser.unregister(objs);
	}

	public void addCustomerType(String type, String id, String notes,
			boolean proofShown) {
		this.removeAllCustomerType();

		this.clickAddCustomerType();
		ajax.waitLoading();
		this.selectCustomerType(type, 1, 1);
		if (!StringUtil.isEmpty(id)) {
			this.setID(id, 1, 1);
		}
		if (!StringUtil.isEmpty(notes)) {
			this.setNote(notes, 1, 1);
		}
		if (proofShown) {
			this.selectProofShown(1);
		}
	}

	/** Add customer type(s) */
	public void addCustomerType(List<CustType> list) {
		removeAllCustomerType();// for update customer type, need to remove all selected customer type then add new
		for (int i = 0; i < list.size(); i++) {
			this.clickAddCustomerType();
			ajax.waitLoading();
			this.selectCustomerType(list.get(i).type, 1, i + 1);
			ajax.waitLoading();
			if (!StringUtil.isEmpty(list.get(i).id)) {
				this.setID(list.get(i).id, 1, i + 1);
			}
			if (!StringUtil.isEmpty(list.get(i).notes)) {
				this.setNote(list.get(i).notes, 1, i + 1);
			}
			if (list.get(i).proofShown) {
				this.selectCustomerTypeProofShown(i + 1);
			}
		}
	}

	public void addCustomerPass(String type, String num, String name,
			String notes, String expiryDate, boolean proofShown) {
		this.removeAllCustomerPass();

		this.clickAddCustomerPass();
		ajax.waitLoading();

		this.selectCustPass(type);
		ajax.waitLoading();
		if (!StringUtil.isEmpty(num)) {
			this.setPassNum(num);
		}
		if (!StringUtil.isEmpty(name)) {
			this.setHolderName(name, 1);
		}
		if (!StringUtil.isEmpty(notes)) {
			this.setNote(notes, 1, 1);
		}
		if (!StringUtil.isEmpty(expiryDate)) {
			this.setPassExpiryDate(expiryDate, 1);
		}
		if (proofShown) {
			this.selectProofShown(1);
		}
	}

	/** Add customer pass */
	public void addCustomerPass(List<CustPass> list) {
		removeAllCustomerPass();
		for (int j = 0; j < list.size(); j++) {
			this.clickAddCustomerPass();
			ajax.waitLoading();
		}

		for (int i = 0; i < list.size(); i++) {

			this.selectCustPass(list.get(i).passType, i + 1);
			ajax.waitLoading();
			this.setPassNum(list.get(i).passNum, i + 1);
			this.setHolderName(list.get(i).holderName, i + 1);
			if (StringUtil.notEmpty(list.get(i).notes)) {
				this.setCustomerPassNote(list.get(i).notes, i + 1);
			}
			if (StringUtil.notEmpty(list.get(i).expiryDate)) {
				this.setCustomerPassExpireDate(list.get(i).expiryDate, i + 1);
			}
			if (list.get(i).proofShown) {
				this.selectCustomerPassProofShown(i + 1);
			}

		}
	}

	/** Click the Add Other Occupant button */
	public void clickAddOtherOccupant() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Add Other Occupant", false));
	}

	/** Select the occupant type */
	public void selectOtherOccType(String occType, int index) {
		browser.selectDropdownList(".id", "otherOccType", occType, index);
	}

	/** Set the First Name of the other occupant type */
	public void setOtherOccFirstName(String fName, int index) {
		browser.setTextField(".id", "otherOccFName", fName, index);
	}

	/** Set the Last Name of the other occupant type */
	public void setOtherOccLastName(String lName, int index) {
		browser.setTextField(".id", "otherOccLName", lName, index);
	}

	/** Set the Qty of the other occupant type */
	public void setOtherOccQty(String qty, int index) {
		browser.setTextField(".id", "otherOccQty", qty, index);
	}

	/**
	 * Add other occupant types
	 * 
	 * @param list
	 */
	public void addOtherOccupantTypes(List<String[]> list) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "otherOccList");
		String value = "";
		if (objs.length > 0) {
			value = ((IHtmlTable) objs[0]).getProperty(".text");
		}

		if (!value.split("Occupant Type")[1].trim().split(" ")[0].trim()
				.equalsIgnoreCase("Qty")) {
			for (int i = 0; i < list.size(); i++) {
				this.clickAddOtherOccupant();
				this.selectOtherOccType(list.get(i)[0], i + 1);
				this.setOtherOccFirstName(list.get(i)[1], i + 1);
				this.setOtherOccLastName(list.get(i)[2], i + 1);
			}
		} else {
			for (int i = 0; i < list.size(); i++) {
				this.clickAddOtherOccupant();
				this.selectOtherOccType(list.get(i)[0], i + 1);

				if (list.get(i)[1] != null
						&& !"".equalsIgnoreCase(list.get(i)[1])) {
					this.setOtherOccQty(list.get(i)[1], i + 1);
				} else {
					this.setOtherOccQty("1", i + 1);
				}
			}
		}

		Browser.unregister(objs);
	}

	/** Check the default value of Qty */
	public void addAndCheckOtherOccDefaultValue(List<String[]> list) {
		int defaulValue = 1;
		for (int i = 0; i < list.size(); i++) {
			this.clickAddOtherOccupant();
			IHtmlObject[] objs = browser.getHtmlObject(".id", "otherOccQty");
			if (Integer.parseInt(objs[1].getProperty(".value")) != defaulValue) {
				Browser.unregister(objs);
				throw new ErrorOnPageException(
						"The default value of the Qty should be: "
								+ defaulValue);
			} else {
				Browser.unregister(objs);
			}
		}
	}

	/** Get the other occupant types row number */
	public int getOtherOccRowNum() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "otherOccType");
		int rowNum = objs.length;
		Browser.unregister(objs);

		if (rowNum > 0) {
			return rowNum - 1;
		} else {
			return -1;
		}
	}

	/**
	 * Verify the default other occupant types is the first option of the
	 * drop-down list
	 */
	public void checkDefaultOtherOccupantTypeDisplay() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id", "otherOccType");
		for (int i = 1; i < objs.length; i++) {
			ISelect list = (ISelect) objs[i];
			if (!objs[i].getProperty(".text").startsWith(
					list.getAllOptions().get(0))) {
				throw new ErrorOnDataException(
						"The default value of the other occupant types should be: "
								+ list.getAllOptions().get(0));
			}
		}
		Browser.unregister(objs);
	}

	/** Remove all other occupants */
	public void removeAllOtherOcc() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove");
		for (int i = 1; i < objs.length; i++) {
			browser.clickGuiObject(".class", "Html.A", ".text", "Remove", 1);
		}
	}

	/** Remove all other occupants */
	public void removeAllOtherOcc(int removeNum) {
		for (int i = 0; i < removeNum; i++) {
			browser.clickGuiObject(".class", "Html.A", ".text", "Remove", 1);
		}
	}

	/** Get the information of the Occupant Type Max Limit */
	public Map<String, String> getOccupantTypeMaxLimitInfo(String occuType) {
		Map<String, String> map = new HashMap<String, String>();
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^( )*\\(" + occuType + ".*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		String targetStr = table.getProperty(".text");
		String str = targetStr.substring(1, targetStr.length() - 1);
		String[] temp = str.split(":");
		map.put(temp[0], temp[1]);

		return map;
	}

	/** Remove all customer type */
	public void removeAllCustomerType() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Customer Type");
		for (int i = 1; i < objs.length; i++) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					"Remove Customer Type", i);
			ajax.waitLoading();
		}
	}

	/** remove customer pass */
	public void removeAllCustomerPass() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Remove Customer Pass");
		for (int i = 1; i < objs.length; i++) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					"Remove Customer Pass", i);
			ajax.waitLoading();
		}

		Browser.unregister(objs);
	}

	/** Get All options in Other Occupant Types list */
	public List<String> getAllOptionsOfOtherOcc() {
		this.clickAddOtherOccupant();
		IHtmlObject[] objs = browser.getDropdownList(".id", "otherOccType");
		ISelect dropdown = (ISelect) objs[1];
		List<String> list = dropdown.getAllOptions();
		Browser.unregister(objs);

		return list;
	}

	// public void selectCustomerType(String customertype, String item,int
	// objectindex) {
	//
	// }

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
		browser.setTextField(".id", "expiryDate_ForDisplay", date, index);
	}

	/**
	 * Get the customer type
	 * 
	 * @param index
	 *            - class index
	 * @return - customer type
	 */
	public String getCustomerType(int index) {
		IHtmlObject[] objs = browser.getDropdownList(".id", "1_proofType");
		String cusType = ((ISelect) objs[index]).getSelectedText();
		Browser.unregister(objs);
		return cusType;
	}

	public String getCustomerPassType(int index) {
		IHtmlObject objs[] = browser.getDropdownList(".id", "passTypeId");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Customer Pass Type drop down list object.");
		}

		String text = ((ISelect) objs[index]).getSelectedText();
		Browser.unregister(objs);

		return text;
	}

	public String getCustomerPassNumber(int index) {
		IHtmlObject objs[] = browser.getTextField(".id", "passNumber");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Customer Pass Number text field object.");
		}
		String text = ((IText) objs[index]).getText();
		Browser.unregister(objs);

		return text;
	}

	public String getHolderName(int idx) {
		return browser.getTextFieldValue(".id", "holderName", idx);
	}

	public String getEligibilityNotes(int index) {
		IHtmlObject objs[] = browser.getTextField(".id", "notes");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Customer Pass Note text feild object.");
		}
		String text = ((IText) objs[index]).getText();
		Browser.unregister(objs);

		return text;
	}

	public boolean getCustomerPassProofShown(int index) {
		return browser.isCheckBoxSelected(".id", "proofShown_for_display",
				index);
	}

	public String getExpiryDate(int index) {
		IHtmlObject objs[] = browser.getTextField(".name",
				"expiryDate_ForDisplay");
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Customer Pass Expiry Date field object.");
		}
		String text = ((IText) objs[index]).getText();
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
		IHtmlObject[] objs = browser.getHtmlObject(".id", "1_proofID");
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
		IHtmlObject[] objs = browser.getHtmlObject(".id", "1_proofNotes");
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
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "OccupantDetail");
		if (objs.length > 0) {
			IHtmlTable resTable = (IHtmlTable) objs[0];
			for (int i = 0; i < resTable.rowCount(); i++) {
				for (int j = 0; j < resTable.columnCount(); j++) {
					if (resTable.getCellValue(i, j).equals("Pet:")) {
						row = i;
						break;
					}
				}
			}
		} else
			throw new ObjectNotFoundException("Reservation table can't find.");
		return getSpecCellValue("Pets Allowed", "# Dog", row);
	}

	/** get the qty of occupants */
	public String getNumofOcc() {
		IHtmlObject[] obj = browser
				.getHtmlObject(".id", "otherOccList_control");
		String num = obj[0].getProperty(".value").toString();

		Browser.unregister(obj);
		return num;
	}

	/** Get the qty of unit */
	public String getNumofUnit() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "qty");
		String num = obj[1].getProperty(".value").toString();

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
	
	public boolean isAlertsTextExists(){
		return browser.checkHtmlObjectExists(".id", "alerts");
	}

	public String getAlertsText() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "alerts");
		String notes = obj[0].getProperty(".value").toString();

		return notes;
	}
	
	public String getNotesText() {
		IHtmlObject[] obj = browser.getHtmlObject(".id", "notes");
		String notes = obj[0].getProperty(".value").toString();

		return notes;
	}
	
	public boolean isNotesTextExists(){
		return browser.checkHtmlObjectDisplayed(".id", "notes");
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
	 * Get the occupant's last name
	 * 
	 * @param lastName
	 * */
	public String getLastName(int index) {
		String lastName = "";
		lastName = browser.getTextFieldValue(".id", new RegularExpression(
				"occupantLastName|otherOccLName", false), index);
		return lastName;
	}

	/**
	 * Get the occupant's first name
	 * 
	 * @return first name
	 */
	public String getFirstName(int index) {
		String firstName = "";
		firstName = browser.getTextFieldValue(".id", new RegularExpression(
				"occupantFirstName|otherOccFName", false), index);
		return firstName;
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
		Property[] s1 = Property.toPropertyArray(".class", "Html.TABLE", ".id",
				"VehicleInfo");
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
	public void clickSwipeCreditCard() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Swipe Card", true);
	}

	/**
	 * Select the Swipe Credit Card type
	 * 
	 * @param : index --- the option of the drop-down list
	 */
	public void selectCreditCardType(int index) {
		browser.selectDropdownList(
				".id",
				new RegularExpression(
						"CustomerPaymentMethodView-\\d+.paymentTypeCode|creditCardType",
						false), index);
	}

	public void selectCreditCardType(String type) {
		browser.selectDropdownList(
				".id",
				new RegularExpression(
						"CustomerPaymentMethodView-\\d+.paymentTypeCode|creditCardType",
						false), type);
	}

	/**
	 * Set the Credit Card Number
	 * 
	 * @param cardNum
	 *            --- the number of the credit card
	 */
	public void setCreditCardNum(String cardNum) {
		browser.setTextField(".id", new RegularExpression(
				"CustomerPaymentMethodView-\\d+\\.cardNumber", false), cardNum,
				true);
	}

	/**
	 * Set the Credit Card Month
	 * 
	 * @param mouth
	 *            --- the month of the credit card
	 */
	public void setCreditCardMonth(String month) {
		browser.setTextField(".id", new RegularExpression(
				"CustomerCreditCardView-\\d+.expiryMM", false), month, true);
	}

	/**
	 * Set the Credit Card Year
	 * 
	 * @param year
	 *            --- the year of the credit card
	 */
	public void setCreditCardYear(String year) {
		browser.setTextField(".id", new RegularExpression(
				"CustomerCreditCardView-\\d+.expiryYY", false), year, true);
	}

	/**
	 * Set the Holder Name
	 * 
	 * @param holderName
	 *            --- the holder of the credit card
	 */
	public void setCreditCardHolderName(String holderName) {
		browser.setTextField(".id", new RegularExpression(
				"CustomerCreditCardView-\\d+.nameOnCreditCard", false),
				holderName, true);
	}

	/**
	 * Set the Swipe Credit Card information
	 * 
	 * @param num
	 * @param month
	 * @param year
	 * @param holderName
	 */
	public void setSwipeInfo(int index, String cardNum, String month,
			String year, String holderName) {
		this.selectCreditCardType(index);
		this.setCreditCardNum(cardNum);
		this.setCreditCardMonth(month);
		this.setCreditCardYear(year);
		this.setCreditCardHolderName(holderName);
	}

	public void setSwipeInfo(String type, String cardNum, String month,
			String year, String holderName) {
		this.selectCreditCardType(type);
		this.waitLoading();
		this.setCreditCardNum(cardNum);
		this.setCreditCardMonth(month);
		this.setCreditCardYear(year);
		this.setCreditCardHolderName(holderName);
	}

	/**
	 * Go to Charges page
	 */
	public void gotoChargesSubPage() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Charges");
		waitLoading();
	}

	/**
	 * Click pos link to goto pos detail page
	 */
	public void clickPosGotoPosDetailPage(String posId) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^POS Sale#.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		for (int i = 0; i < table.rowCount(); i++) {
			if (posId.equalsIgnoreCase(table.getCellValue(i, 1).toString())) {
				browser.clickGuiObject(".class", "Html.A", ".text", posId);
				break;
			}
		}
		Browser.unregister(objs);
	}

	/**
	 * Click Add to Cart button in Reservation Detail main page
	 */
	public void clickAddToCartInMainPage() {
		browser.clickGuiObject(
				".class",
				"Html.A",
				".href",
				new RegularExpression(
						".*invokeActionValidateTarget.*forceAddToCart.*", false));
	}

	/**
	 * Click Add to Cart button in charges sub page
	 */
	public void clickAddToCartInChargesSubPage() {
		browser.clickGuiObject(
				".class",
				"Html.A",
				".href",
				new RegularExpression(
						".*validateChargeSelections.*addReservationChargesToCart.*transaction.*",
						false));
	}

	/**
	 * Get the Credit Card Number
	 * 
	 * @return Credit Card Number
	 */
	public String getCreditCardNum() {
		String cardNum = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "F_CardNumber");
		cardNum = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardNum;
	}

	/**
	 * Get the Credit Card Month
	 * 
	 * @return Credit Card Month
	 */
	public String getCreditCardMonth() {
		String cardMon = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "expiryMonth");
		cardMon = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardMon;
	}

	/**
	 * Get the Credit Card Year
	 * 
	 * @return Credit Card Year
	 */
	public String getCreditCardYear() {
		String cardYear = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "expiryYear");
		cardYear = objs[0].getAttributeValue(".value");
		Browser.unregister(objs);

		return cardYear;
	}

	/**
	 * Get the Credit Card Holder Name
	 * 
	 * @return Credit Card Holder Name
	 */
	public String getCreditCardHolderName() {
		String holderName = "";
		IHtmlObject[] objs = browser.getHtmlObject(".id", "F_CardHolder");
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

	public IHtmlObject[] getReservationTable() {
		return browser.getHtmlObject(".class", "Html.TABLE", ".id",
				"ReservationDetails");
	}

	/**
	 * Get text of reservation details table
	 * 
	 * @return
	 */
	public String getReservationTableInfo() {
		String reservationInfo = "";
		IHtmlObject[] objs = this.getReservationTable();
		reservationInfo = objs[0].getProperty(".text");
		Browser.unregister(objs);

		return reservationInfo;
	}

	/**
	 * Select primary occupant type
	 * 
	 * @param primaryOccType
	 */
	public void selectPrimaryOccType(String primaryOccType) {
		if (!browser.checkHtmlObjectExists(".id", "primaryOccType"))
			return;

		if (null != primaryOccType && primaryOccType.length() > 0) {
			browser.selectDropdownList(".id", "primaryOccType", primaryOccType);
		} else
			browser.selectDropdownList(".id", "primaryOccType", 1);
	}

	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}

	public void clickInvoiceNum() {
		browser.clickGuiObject(".class", "Html.A", ".id", "ordInvID");
	}

	/**
	 * Get the ada required message.
	 * 
	 * @String return the message.
	 */
	public String getAdaRequiredMessage() {
		String alertMsg = browser.getObjectText(".class", "Html.DIV", ".id",
				"NOTSET");
		return alertMsg;
	}

	/**
	 * get identity customer phone.
	 */
	public String getIdentityCustPhone() {
		return browser.getTextFieldValue(".id", "occupantPhone");
	}

	/**
	 * get Identity customer email.
	 */
	public String getIdentityCustEmail() {
		return browser.getTextFieldValue(".id", "occupantEmail");
	}

	/**
	 * get identity customer street address.
	 * 
	 * @return
	 */
	public String getIdentityCustStreetAddress() {
		return browser.getTextFieldValue(".id", "occupantAddress");
	}

	/**
	 * get Identity Customer city.
	 * 
	 * @return
	 */
	public String getIdentityCustCity() {
		return browser.getTextFieldValue(".id", "occupantCity");
	}

	/**
	 * get identity cust zip.
	 * 
	 * @return
	 */
	public String getIdentityCustZip() {
		return browser.getTextFieldValue(".id", "occupantZipCode");
	}

	/**
	 * get identity customer state.
	 * 
	 * @return
	 */
	public String getIdentityCustState() {
		return browser.getDropdownListValue(".id", "occupantState");
	}

	/**
	 * get identity customer country.
	 * 
	 * @return
	 */
	public String getIdentityCustCountry() {
		return browser.getDropdownListValue(".id", "occupantCountry");
	}

	/**
	 * get
	 * 
	 * @param text
	 * @return
	 */
	public boolean getDisabledArrtibuteByText(String text) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				text);
		if (null == objs || objs.length < 1) {
			throw new ErrorOnDataException("No element exist");
		}
		String value = objs[0].getProperty(".isDisabled");
		Browser.unregister(objs);
		if (value.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * get Replace primary occupant button disabled attribute.
	 * 
	 * @return
	 */
	public boolean getReplacePrimaryOccupantDisabledAttr() {
		return this.getDisabledArrtibuteByText("Replace Primary Occupant");
	}

	/**
	 * get edit primary occupant disabled attribute.
	 * 
	 * @return
	 */
	public boolean getEditPrimaryOccupantDisabledAttr() {
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				"Edit Primary Occupant");
	}

	/**
	 * verify replace primary occupant disabled.
	 */
	public void verifyReplacePriOccuEabled() {
		boolean isPass = this.getReplacePrimaryOccupantDisabledAttr();
		if (isPass) {
			throw new ErrorOnPageException(
					"Replace Primary Occupant should be enable");
		} else {
			logger.info("Replace Primary Occupant is enable");
		}
	}

	/**
	 * verify edit primary occupant disabled.
	 */
	public void verifyEditPrimaryOccupantDisable() {
		boolean isDisable = this.getEditPrimaryOccupantDisabledAttr();
		if (!isDisable) {
			throw new ErrorOnPageException(
					"Replace Edit Primary Occupant should be disable");
		} else {
			logger.info("Edit Primary Occupant is disable");
		}
	}

	/**
	 * verify edit primary occupant enable.
	 */
	public void verifyEditPrimaryOccupantEnabled() {
		boolean isDisable = this.getEditPrimaryOccupantDisabledAttr();
		if (isDisable) {
			throw new ErrorOnPageException(
					"'Edit Primary Occupant' should be enable");
		} else {
			logger.info("'Edit Primary Occupant' is enable");
		}
	}

	/**
	 * get the same as customer check box selected or not.
	 */
	public void verifySameAsCustomerCheckboxSelected() {
		boolean isSelect = browser
				.isCheckBoxSelected(".id", "isSameAsCustomer");
		if (!isSelect) {
			throw new ErrorOnPageException(
					"Same As Customer Checkbox should be selected");
		} else {
			logger.info("Same As Customer Checkbox is selected");
		}
	}

	/**
	 * get the same as customer check box selected or not.
	 */
	public void verifySameAsCustomerCheckboxUnSelected() {
		boolean isSelect = browser
				.isCheckBoxSelected(".id", "isSameAsCustomer");
		if (isSelect) {
			throw new ErrorOnPageException(
					"Same As Customer Checkbox should be UnSelected");
		} else {
			logger.info("Same As Customer Checkbox is UnSelected");
		}
	}

	private IHtmlObject[] getSlipInventoryTableObject() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not get Slip Inventory table object.");
		}
		return objs;
	}

	public List<String> getMarinaRateTypeColumnValues() {
		IHtmlObject[] objs = this.getSlipInventoryTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];

		int col = table.findColumn(0, "Marina Rate Type");
		List<String> values = table.getColumnValues(col);

		Browser.unregister(objs);
		values.remove(0);
		return values;
	}

	public void clickChargesTab() {
		browser.clickGuiObject(".class", "Html.SPAN", ".text", "Charges");
	}
	
	private IHtmlObject[] getPOSSaleListTableObject(){
		IHtmlObject[] objs = browser.getTableTestObject(Property.getPropertyArray(".id", new RegularExpression("grid_\\d+",false),
				".text",  new RegularExpression("^( )?POS Sale#( )?Order Status.*",false)));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not found Charge List Table object.");
		}
		
		return objs;
	}
	
	public boolean checkPOSSalesExists(String posSale){
		IHtmlObject[] objs = this.getPOSSaleListTableObject();
		boolean isExisting = browser.checkHtmlObjectDisplayed(Property.concatPropertyArray(a(), ".text",posSale), objs[objs.length-1]);
		Browser.unregister(objs);
		return isExisting;
	}
	
	private Property[] reprintPermit() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Reprint Permit");
	}
	
	public boolean isReprintPermitEnabled() {
		return browser.checkHtmlObjectExists(reprintPermit());
	}
	
	public void clickReprintPermit() {
		browser.clickGuiObject(reprintPermit());
	}
}
