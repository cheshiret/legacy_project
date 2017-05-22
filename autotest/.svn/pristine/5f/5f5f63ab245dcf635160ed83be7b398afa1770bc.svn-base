package com.activenetwork.qa.awo.pages.orms.common.marina;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.CustPass;
import com.activenetwork.qa.awo.datacollection.legacy.CustType;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.VehicleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipContractInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipReservationInfo.OtherOccupant;
import com.activenetwork.qa.awo.pages.orms.common.OrmsReservationDetailsCommonPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.ICheckBox;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
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
 * @Date Oct 22, 2012
 */
public class OrmsSlipReservationDetailsPage extends
		OrmsReservationDetailsCommonPage { // in the future, this page should
											// extends from
											// OrmsReservationsDetailsPage with
											// OrmsCampingReservationDetailsPage

	private static OrmsSlipReservationDetailsPage _instance = null;

	protected OrmsSlipReservationDetailsPage() {
	}

	public static OrmsSlipReservationDetailsPage getInstance() {
		if (_instance == null) {
			_instance = new OrmsSlipReservationDetailsPage();
		}

		return _instance;
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "content_res_detail_panel");
	}

	private static final String ORDER_ITEM_COLUMN_MARINA = "Marina";
	private static final String ORDER_ITEM_COLUMN_DOCK = "Dock";
	private static final String ORDER_ITEM_COLUMN_SLIP_NUM = "Slip #";
	private static final String ORDER_ITEM_COLUMN_ARRIVAL = "Arrival";
	private static final String ORDER_ITEM_COLUMN_DEPARTURE = "Departure";
	private static final String ORDER_ITEM_COLUMN_EXPECTED_DOCKING_DATE = "Expected Docking Date";
	private static final String ORDER_ITEM_COLUMN_NUM_OF_MONTHS = "# Months";
	// TODO
	private static final String ORDER_ITEM_COLUMN_MARINA_RATE_TYPE = "Marina Rate Type";
	private static final String ORDER_ITEM_COLUMN_RAFTING = "Rafting";
	private static final String ORDER_ITEM_COLUMN_ORDER_ITEM_STATUS = "Order Item Status";
	private static final String ORDER_ITEM_COLUMN_TYPE_OF_USE = "Type Of Use";

	public String getSlipReservationNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SPAN",
				".id", new RegularExpression(
						"MarinaOrderView-\\d+\\.orderName", false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Can't find Slip Reservation Num div object.");
		}

		String text = objs[0].text().split("Slip Reservation #")[1].trim();
		Browser.unregister(objs);

		return text;
	}

	// private String getAttributeByName(String name) {
	// // IHtmlObject objs[] =
	// browser.getHtmlObject(Property.toPropertyArray(".class", "Html.DIV",
	// ".className", "inputwithrubylabel", ".text", new RegularExpression("^" +
	// name, false)));
	// //Update for getting Max number of vehicle
	// IHtmlObject objs[] =
	// browser.getHtmlObject(Property.toPropertyArray(".class", "Html.SPAN",
	// ".className", "inputwithrubylabel", ".text", new RegularExpression("^" +
	// name, false)));
	// if(objs.length < 1) {
	// throw new ItemNotFoundException("Can't find attribute by Name - " +
	// name);
	// }
	//
	// // String text = objs[0].text().split(name)[1].trim(); For the text which
	// is empty, the lenght of the array will be just 1 after split
	// String text = objs[0].text().replace(name, "").trim();
	// Browser.unregister(objs);
	//
	// return text;
	// }

	public String getResNum() {
		return browser
				.getObjectText(
						".id",
						new RegularExpression(
								"MarinaOrderView-\\d+\\.orderName", false))
				.replace("Slip Reservation #", "").trim();
	}

	public List<String> getActionReasonListValue() {
		return browser.getDropdownElements(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.actionReason", false));
	}

	public boolean isActionReasonDropdownListExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.actionReason", false));
	}

	public void selectActionReason(String reason) {
		browser.selectDropdownList(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.actionReason", false),
				reason);
	}

	public void selectActionReason(int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.actionReason", false),
				index);
	}

	// Slip Contract
	public void clickContractID() {
		browser.clickGuiObject(".class", "Html.A", ".id",
				new RegularExpression("SlipContractView-\\d+\\.id", false));
	}

	// Slip Inventory

	public void setPermitNumber(String num) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.permitNumber", false), num);
	}

	public String getPermitNumber() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.permitNumber", false));
	}

	public void selectBoat(String boat) {
		browser.selectDropdownList(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false), boat);
	}

	public String getBoat() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatName", false));
	}

	public List<String> getAllBoats() {
		return browser.getDropdownElements(".id", new RegularExpression(
				"DropdownExt-\\d+\\.selectedValue", false));
	}

	public void selectSaveToCustomerProfile() {
		browser.selectCheckBox(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.saveVesselToPrimaryOccupant", false));
	}

	public void unselectSaveToCustomerProfile() {                  
		browser.unSelectCheckBox(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.saveVesselToPrimaryOccupant", false));
	}

	public void setBoatName(String name) {                  
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatName", false), name);
	}

	public String getBoatName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatName", false));
	}

	public void selectBoatType(String type) {
		browser.selectDropdownList(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.vesselType", false), type);
	}

	public String getBoatType() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.vesselType", false));
	}
	
	public List<String> getBoatTypeElements(){
		return browser.getDropdownElements(".id", new RegularExpression("OrderProfileMarinaVesselView-\\d+\\.vesselType", false));
	}

	public void setBoatLength(String length) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.length", false), length);
	}

	public String getBoatLength() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.length", false));
	}

	public void setBoatWidth(String width) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.width", false), width);
	}

	public String getBoatWidth() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.width", false));
	}

	public void setBoatDepth(String depth) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.depth", false), depth);
	}

	public String getBoatDepth() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.depth", false));
	}

	public void setRegistrationNum(String num) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"OrderProfileMarinaVesselView-\\d+\\.registrationNumber",
						false), num);
	}

	public String getRegistrationNum() {
		return browser
				.getTextFieldValue(
						".id",
						new RegularExpression(
								"OrderProfileMarinaVesselView-\\d+\\.registrationNumber",
								false));
	}

	public void selectBoatCategory(int index) {
		browser.selectDropdownList(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatCategory", false),
				index);
	}

	public void selectBoatCategory(String category) {
		browser.selectDropdownList(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatCategory", false),
				category);
	}

	public String getBoatCategory() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatCategory", false));
	}
	
	public List<String> getBoatCategoryElements(){
		return browser.getDropdownElements(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.boatCategory", false));
	}

	public void setCapacity(double capacity) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.capacity", false), String
				.valueOf(capacity));
	}

	public double getCapacity() {
		String value = browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.capacity", false));
		if (!StringUtil.isEmpty(value)) {
			return Double.parseDouble(value);
		}

		return Double.NaN;
	}

	public void setHorsePower(double power) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.horsePower", false),
				String.valueOf(power));
	}

	public double getHorsePower() {
		String value = browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.horsePower", false));
		if (!StringUtil.isEmpty(value)) {
			return Double.parseDouble(value);
		}

		return Double.NaN;
	}

	public void setYear(String year) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.year", false), year);
	}

	public String getYear() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.year", false));
	}

	public void setHullIdentification(String hullId) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.hullID", false), hullId);
	}

	public String getHullIdentification() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.hullID", false));
	}

	public void setModel(String model) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.model", false), model);
	}

	public String getModel() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.model", false));
	}

	public void setManufacturer(String manufacturer) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.manufacturer", false),
				manufacturer);
	}

	public String getManufacturer() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.manufacturer", false));
	}

	public void setMotorManufacturer(String motorManufacturer) {
		browser.setTextField(
				".id",
				new RegularExpression(
						"OrderProfileMarinaVesselView-\\d+\\.motorManufacturer",
						false), motorManufacturer);
	}

	public String getMotorManufacturer() {
		return browser
				.getTextFieldValue(
						".id",
						new RegularExpression(
								"OrderProfileMarinaVesselView-\\d+\\.motorManufacturer",
								false));
	}

	public void setConstruction(String construction) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.construction", false),
				construction);
	}

	public String getConstruction() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.construction", false));
	}

	public void setTrailerType(String type) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.trailerType", false), type);
	}

	public String getTrailerType() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.trailerType", false));
	}

	public void setTrailerLicenseNum(String num) {
		browser.setTextField(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.trailerLicense", false),
				num);
	}

	public String getTrailerLicenseNum() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"OrderProfileMarinaVesselView-\\d+\\.trailerLicense", false));
	}

	public void setCaptainFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainFirstName", false), fName);
	}

	public String getCaptainFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainFirstName", false));
	}

	public void setCaptainLastName(String lName) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainLastName", false), lName);
	}

	public String getCaptainLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainLastName", false));
	}

	public boolean isBoatInfoSectionExists() {
		return browser
				.checkHtmlObjectExists(".id", "MarinaOrderDetailBoatInfo");
	}

	public boolean isBoatPermitNumberSectionExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.permitNumber", false));
	}

	public boolean isBoatOwnerSectionExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.lastName", false));
	}

	public boolean isBoatOwnerSameAsCustomerSelected() {
		String id = getBoatOwnerSameAsCustomerCheckboxId();
		// return browser.isCheckBoxSelected(".id", new
		// RegularExpression("CheckboxExt-\\d+\\.checked", false));
		return browser.isCheckBoxSelected(".id", id);
	}
	
	public String getSameAsCustomerCheckboxIdInSpecialSection(String sectionName){
		// HtmlObject objs[] = browser.getHtmlObject(".class", "Html.LABEL",
		// ".text", "Same as customer");
		RegularExpression pattern = new RegularExpression(
				"Same as customer|Same as Primary Occupant", false);
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(Property
				.toPropertyArray(".class", "Html.TR", ".text",
						new RegularExpression("^" + sectionName, false)), Property
				.toPropertyArray(".class", "Html.LABEL", ".text", pattern)));

		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Boat Owner 'same as customer' lable object cannot find.");
		}
		String id = objs[0].getProperty(".for");
		Browser.unregister(objs);

		return id;
	}
	
	private String getPrimaryOccupantSameAsCustomerCheckboxId(){
		return getSameAsCustomerCheckboxIdInSpecialSection("Primary Occupant");
	}

	private String getBoatOwnerSameAsCustomerCheckboxId() {
		return getSameAsCustomerCheckboxIdInSpecialSection("Boat Owner");
	}
	
	public void selectPrimaryOccupantSameAsCustomer(){
		String id = getPrimaryOccupantSameAsCustomerCheckboxId();
		browser.selectCheckBox(".id", id);
	}
	
	public void unselectPrimaryOccupantSameAsCustomer(){
		String id = getPrimaryOccupantSameAsCustomerCheckboxId();
		browser.unSelectCheckBox(".id", id);
	}

	public void selectBoatOwnerSameAsCustomer() {
		String id = getBoatOwnerSameAsCustomerCheckboxId();
		// browser.selectCheckBox(".id", new
		// RegularExpression("CheckboxExt-\\d+\\.checked", false));
		browser.selectCheckBox(".id", id);
	}

	public void unselectBoatOwnerSameAsCustomer() {
		String id = getBoatOwnerSameAsCustomerCheckboxId();
		// browser.unSelectCheckBox(".id", new
		// RegularExpression("CheckboxExt-\\d+\\.checked", false));
		browser.unSelectCheckBox(".id", id);
	}

	public void setBoatOwnerLastName(String lName) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.lastName", false), lName);
	}

	public String getBoatOwnerLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.lastName", false));
	}

	public void setBoatOwnerFirstName(String fName) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.firstName", false), fName);
	}

	public String getBoatOwnerFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.firstName", false));
	}

	public void setBoatOwnerPhone(String phone) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.phone", false), phone);
	}

	public String getBoatOwnerPhone() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.phone", false));
	}

	public void setBoatOwnerWorkPhone(String workPhone) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.workPhone", false), workPhone);
	}

	public String getBoatOwnerWorkPhone() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.workPhone", false));
	}

	public void setBoatOwnerEmailAddress(String email) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.email", false), email);
	}

	public String getBoatOwnerEmailAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.email", false));
	}

	public void setBoatOwnerStreetAddress(String street) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.street", false), street);
	}

	public String getBoatOwnerStreetAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.street", false));
	}

	public void setBoatOwnerCity(String city) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.city", false), city);
	}

	public String getBoatOwnerCity() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.city", false));
	}

	public void setBoatOwnerZip(String zip) {
		browser.setTextField(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.zipCode", false), zip);
	}

	public String getBoatOwnerZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaVesselOwnerView-\\d+\\.zipCode", false));
	}

	public void selectBoatOwnerState(String state) {
		if (StringUtil.notEmpty(state)) {
			IHtmlObject[] objs = browser.getTableTestObject(".id",
					"MarinaOrderDetailBoatInfo");
			browser.selectDropdownList(
					".id",
					new RegularExpression(
							"(MarinaOrderView-\\d+\\.boatOwner\\.state|MarinaVesselOwnerView-\\d+\\.state)",
							false), state, true, objs[0]);
			Browser.unregister(objs);
		} else {
			this.selectBoatOwnerState(0);
		}
	}

	public String getBoatOwnerState() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		String state = browser
				.getDropdownListValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(MarinaOrderView-\\d+\\.boatOwner\\.state|MarinaVesselOwnerView-\\d+\\.state)",
										false)), 0, objs[0]);
		Browser.unregister(objs);
		return state;
	}

	public void selectBoatOwnerState(int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		browser.selectDropdownList(
				".id",
				new RegularExpression(
						"(MarinaOrderView-\\d+\\.boatOwner\\.state|MarinaVesselOwnerView-\\d+\\.state)",
						false), index, true, objs[0]);
		Browser.unregister(objs);
	}

	public void selectBoatOwnerCountry(String country) {
		if (StringUtil.notEmpty(country)) {
			IHtmlObject[] objs = browser.getTableTestObject(".id",
					"MarinaOrderDetailBoatInfo");
			browser.selectDropdownList(
					".id",
					new RegularExpression(
							"(MarinaOrderView-\\d+\\.boatOwner\\.country|MarinaVesselOwnerView-\\d+\\.country)",
							false), country, true, objs[0]);
			Browser.unregister(objs);
		} else {
			this.selectBoatOwnerCountry(0);
		}
	}

	public String getBoatOwnerCountry() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		String country = browser
				.getDropdownListValue(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(MarinaOrderView-\\d+\\.boatOwner\\.country|MarinaVesselOwnerView-\\d+\\.country)",
										false)), 0, objs[0]);
		Browser.unregister(objs);
		return country;
	}

	public void selectBoatOwnerCountry(int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		Browser.sleep(2);
		browser.selectDropdownList(
				".id",
				new RegularExpression(                              
						"(MarinaOrderView-\\d+\\.boatOwner\\.country|MarinaVesselOwnerView-\\d+\\.country)",
						false), index, true, objs[0]);
		Browser.unregister(objs);
	}

	public void setupBoatInfo(BoatInfo boat) {
		if (boat.isNew()) {
			selectBoat("New");
			Browser.sleep(10);
		} else {
			selectBoat(boat.getName());
			ajax.waitLoading();
			this.waitLoading();
		}
		if (boat.isSaveToCustomerProfile()) {
			selectSaveToCustomerProfile();
		}else{
			this.unselectSaveToCustomerProfile();
		}
		setBoatName(boat.getName());
		if (!StringUtil.isEmpty(boat.getBoatType())) {
			selectBoatType(boat.getBoatType());
		}
		setBoatLength(boat.getLength());
		setBoatWidth(boat.getWidth());
		setBoatDepth(boat.getDepth());

		if (!StringUtil.isEmpty(boat.getRegistrationNum())) {
			setRegistrationNum(boat.getRegistrationNum());
		} else {// James[20130708]: registration# becomes mandatory
			// Shane:this random value should put back to related variable,else
			// will failed when verify detail
			boat.setRegistrationNum(Math.round(Math.random() * 10000000) + "");
			setRegistrationNum(boat.getRegistrationNum());

		}

		if (!StringUtil.isEmpty(boat.getBoatCategory())) {
			selectBoatCategory(boat.getBoatCategory());
			ajax.waitLoading();
		}

		if (NumberUtil.isGreaterThanZero(boat.getCapacity())) {
			setCapacity(boat.getCapacity());
		} else {// James[20130708]: capacity becomes mandatory
				// Shane:this random value should put back to related
				// variable,else will failed when verify detail
			boat.setCapacity(Math.rint(Math.random() * 100));
			setCapacity(boat.getCapacity());
		}

		if (NumberUtil.isGreaterThanZero(boat.getHorsePower())) {
			setHorsePower(boat.getHorsePower());
		}
		if (!StringUtil.isEmpty(boat.getYear())) {
			setYear(boat.getYear());
		}
		if (!StringUtil.isEmpty(boat.getHullIndetification())) {
			setHullIdentification(boat.getHullIndetification());
		}
		if (!StringUtil.isEmpty(boat.getModel())) {
			setModel(boat.getModel());
		}
		if (!StringUtil.isEmpty(boat.getManufacturer())) {
			setManufacturer(boat.getManufacturer());
		}
		if (!StringUtil.isEmpty(boat.getMotorManufacturer())) {
			setMotorManufacturer(boat.getMotorManufacturer());
		}
		if (!StringUtil.isEmpty(boat.getConstruction())) {
			setConstruction(boat.getConstruction());
		}
		if (!StringUtil.isEmpty(boat.getTrailerType())) {
			setTrailerType(boat.getTrailerType());
		}
		if (!StringUtil.isEmpty(boat.getTrailerLicenseNum())) {
			setTrailerLicenseNum(boat.getTrailerLicenseNum());
		}
		if (!StringUtil.isEmpty(boat.getCaptainFirstName())) {
			setCaptainFirstName(boat.getCaptainFirstName());
		}
		if (!StringUtil.isEmpty(boat.getCaptainLastName())) {
			setCaptainLastName(boat.getCaptainLastName());
		}
		if (isBoatOwnerSectionExists()) {
			if (boat.isBoatOwnerSamesAsCustomer()) {
				selectBoatOwnerSameAsCustomer();
				ajax.waitLoading();
				// retrieve Boat Owner info
				boat = retrieveBoatOwnerIdentifyAdressInfo(boat);
			} else {
				if (isBoatOwnerSameAsCustomerSelected()) {
					unselectBoatOwnerSameAsCustomer();
					ajax.waitLoading();
				} else {
					clearBoatOwnerInfo();
				}
				setBoatOwnerLastName(boat.getBoatOwnerLastName());
				setBoatOwnerFirstName(boat.getBoatOwnerFirstName());
				if (!StringUtil.isEmpty(boat.getBoatOwnerPhone())) {
					setBoatOwnerPhone(boat.getBoatOwnerPhone());
				}
				if (!StringUtil.isEmpty(boat.getBoatOwnerWorkPhone())) {
					setBoatOwnerWorkPhone(boat.getBoatOwnerWorkPhone());
				}
				if (!StringUtil.isEmpty(boat.getBoatOwnerEmail())) {
					setBoatOwnerEmailAddress(boat.getBoatOwnerEmail());
				}
				setBoatOwnerStreetAddress(boat.getBoatOwnerStreetAddress());
				setBoatOwnerZip(boat.getBoatOwnerZip());
				selectBoatOwnerCountry(boat.getBoatOwnerCountry());
				ajax.waitLoading();
				setBoatOwnerCity(boat.getBoatOwnerCity());
				selectBoatOwnerState(boat.getBoatOwnerState());
				Browser.sleep(3);

			}
		}
	}

	public void clearBoatInfo() {
		
	}

	public void clearBoatOwnerInfo() {
		setBoatOwnerLastName(StringUtil.EMPTY);
		setBoatOwnerFirstName(StringUtil.EMPTY);
		setBoatOwnerPhone(StringUtil.EMPTY);
		setBoatOwnerWorkPhone(StringUtil.EMPTY);
		setBoatOwnerEmailAddress(StringUtil.EMPTY);
		setBoatOwnerStreetAddress(StringUtil.EMPTY);
		setBoatOwnerCity(StringUtil.EMPTY);
		setBoatOwnerZip(StringUtil.EMPTY);
		selectBoatOwnerState(0);
		selectBoatOwnerCountry(0);
	}

	public String getNumOfOtherOccupant() {
		return browser.getObjectText(".id", "otherOccList_control");
	}

	public int getNumOfOtherOccupantMaxTotalAllowed() {
		return Integer.parseInt(this.getAttributeByName("Max Total Allowed"));
	}

	public int getNumOfVehicleMaxAllowed() {
		return Integer.parseInt(this.getAttributeByName("Max Allowed"));
	}

	public boolean isBoatCaptainSectionExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainFirstName", false));
	}

	public String getBoatCaptainFirstName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainFirstName", false));
	}

	public String getBoatCaptainLastName() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.captainLastName", false));
	}

	public BoatInfo getBoatInfo() {
		BoatInfo boat = new Customer().new BoatInfo();

		// Boat Info section
		if (this.isBoatInfoSectionExists()) {
			boat.setName(getBoatName());
			String temp = getBoatType();
			if (!StringUtil.isEmpty(temp)) {
				boat.setBoatType(temp);
			}
			boat.setLength(getBoatLength());
			boat.setWidth(getBoatWidth());
			boat.setDepth(getBoatDepth());
			boat.setRegistrationNum(getRegistrationNum());
			boat.setBoatCategory(getBoatCategory());
			boat.setCapacity(getCapacity());
			boat.setHorsePower(getHorsePower());
			boat.setYear(getYear());
			boat.setHullIndetification(getHullIdentification());
			boat.setModel(getModel());
			boat.setManufacturer(getManufacturer());
			boat.setMotorManufacturer(getMotorManufacturer());
			boat.setConstruction(getConstruction());
			boat.setTrailerType(getTrailerType());
			boat.setTrailerLicenseNum(getTrailerLicenseNum());
		}
		// Captain section
		if (isBoatCaptainSectionExists()) {
			boat.setCaptainFirstName(getCaptainFirstName());
			boat.setCaptainLastName(getCaptainLastName());
		}
		if (this.isBoatOwnerSectionExists()) {
			// Boat Owner, Identity/Address sections
			boat = retrieveBoatOwnerIdentifyAdressInfo(boat);
		}
		return boat;
	}

	private BoatInfo retrieveBoatOwnerIdentifyAdressInfo(BoatInfo boat) {
		boat.setBoatOwnerSamesAsCustomer(isBoatOwnerSameAsCustomerSelected());
		boat.setBoatOwnerLastName(getBoatOwnerLastName());
		boat.setBoatOwnerFirstName(getBoatOwnerFirstName());
		boat.setBoatOwnerPhone(getBoatOwnerPhone());
		boat.setBoatOwnerWorkPhone(getBoatOwnerWorkPhone());
		boat.setBoatOwnerEmail(getBoatOwnerEmailAddress());
		boat.setBoatOwnerStreetAddress(getBoatOwnerStreetAddress());
		boat.setBoatOwnerCity(getBoatOwnerCity());
		boat.setBoatOwnerZip(getBoatOwnerZip());
		boat.setBoatOwnerState(getBoatOwnerState());
		boat.setBoatOwnerCountry(getBoatOwnerCountry());

		return boat;
	}

	public List<VehicleInfo> getVehicleInfos() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "VehicleList");
		List<VehicleInfo> infos = new ArrayList<VehicleInfo>();
		IHtmlTable grid = (IHtmlTable) objs[0];
		for (int i = 0; i < grid.rowCount() - 3; i++) {
			VehicleInfo vehicle = new Customer().new VehicleInfo();

			vehicle.setPlateNum(getPlate(i + 1));
			vehicle.setState(getSelectedState(i + 1));
			vehicle.setMake(getSelectedMake(i + 1));
			vehicle.setModel(getModelValue(i + 1));
			vehicle.setColor(getSelectedColor(i + 1));

			infos.add(vehicle);
		}

		Browser.unregister(objs);
		return infos;
	}

	public List<OtherOccupant> getOtherOccupants() {
		IHtmlObject[] objs = browser.getTableTestObject(".id", "otherOccList");

		List<OtherOccupant> occupants = new ArrayList<OtherOccupant>();
		IHtmlTable grid = (IHtmlTable) objs[0];
		for (int i = 0; i < grid.rowCount() - 3; i++) {
			OtherOccupant oc = new SlipReservationInfo().new OtherOccupant(
					getFirstName(i + 1), getLastName(i + 1));
			occupants.add(oc);
		}

		Browser.unregister(objs);
		return occupants;
	}

	/**
	 * 
	 * @param cust
	 * @param documentNum
	 * @return
	 */
	public boolean compareSlipReservationDetailsInfo(SlipReservationInfo res) {// If
																				// you
																				// want
																				// to
																				// compare
																				// Vehicle
																				// info,
																				// need
																				// to
																				// enhance
																				// this
																				// method
		boolean result = true;

		// Customer Type
		if (res.customer.custTypes != null && res.customer.custTypes.size() > 0) {
			if (!res.customer.custTypes.equals(this.getCustomerTypes())) {
				throw new ErrorOnPageException(
						"Customer Types Info Not correct.");
			}
		}

		// Customer Pass
		if (res.customer.custPasses != null
				&& res.customer.custPasses.size() > 0) {
			if (!res.customer.custPasses.equals(this.getCustomerPasses())) {
				throw new ErrorOnPageException(
						"Customer Passes Info Not correct.");
			}
		}

		if (StringUtil.notEmpty(res.promoCode)) {
			result &= MiscFunctions.compareResult("Promotional Discount",
					res.promoCode, getPromoCode());
		}

		// Boating Permit Number
		if (!StringUtil.isEmpty(res.customer.documentNum)) {
			result &= MiscFunctions.compareResult("Boating Permit Number",
					res.customer.documentNum, getPermitNumber());
		}

		// other occupants
		if (res.getOtherOccupants() != null
				&& res.getOtherOccupants().size() > 0) {
			List<OtherOccupant> actualOtherOccupants = this.getOtherOccupants();
			if (!MiscFunctions.compareResult("Other Occupant size", res
					.getOtherOccupants().size(), actualOtherOccupants.size())) {
				throw new ErrorOnPageException(
						"Other Occupant size is NOT correct.");
			}
			for (int i = 0; i < actualOtherOccupants.size(); i++) {
				result &= res.getOtherOccupants().get(i)
						.equals(actualOtherOccupants.get(i));
			}
		}

		// vehicle info
		if (res.customer.vehicleInfo != null
				&& res.customer.vehicleInfo.size() > 0) {
			List<VehicleInfo> actualVehicles = this.getVehicleInfos();
			if (!MiscFunctions.compareResult("Vehicle Info size",
					res.customer.vehicleInfo.size(), actualVehicles.size())) {
				throw new ErrorOnPageException(
						"Vehicle info size is NOT correct.");
			}
			for (int i = 0; i < actualVehicles.size(); i++) {
				result &= res.customer.vehicleInfo.get(i).equals(
						actualVehicles.get(i));
			}
		}

		// Boat Info includes Boat Info section, Captain section, Boat Owner
		// section and Identify/Address section
		if (res.customer.boat != null) {
			result &= compareBoatInfo(res.customer.boat);
		}

		return result;
	}

	public boolean compareBoatInfo(BoatInfo expectedBoat) {
		boolean result = true;

		BoatInfo actualBoat = getBoatInfo();
		// Boat Info
		result &= MiscFunctions.compareResult("Boat Name",
				expectedBoat.getName(), actualBoat.getName());
		if (!StringUtil.isEmpty(expectedBoat.getBoatType())) {
			result &= MiscFunctions.compareResult("Boat Type",
					expectedBoat.getBoatType(), actualBoat.getBoatType());
		}
		if (!StringUtil.isEmpty(expectedBoat.getLength())
				|| !StringUtil.isEmpty(actualBoat.getLength())) {
			result &= MiscFunctions.compareResult("Boat Length",
					Double.parseDouble(expectedBoat.getLength()),
					Double.parseDouble(actualBoat.getLength()));
		}
		if (!StringUtil.isEmpty(expectedBoat.getWidth())
				|| !StringUtil.isEmpty(actualBoat.getWidth())) {
			result &= MiscFunctions.compareResult("Boat Width",
					Double.parseDouble(expectedBoat.getWidth()),
					Double.parseDouble(actualBoat.getWidth()));
		}
		if (!StringUtil.isEmpty(expectedBoat.getDepth())
				|| !StringUtil.isEmpty(actualBoat.getDepth())) {
			result &= MiscFunctions.compareResult("Boat Depth",
					Double.parseDouble(expectedBoat.getDepth()),
					Double.parseDouble(actualBoat.getDepth()));
		}
		if (!StringUtil.isEmpty(expectedBoat.getRegistrationNum())) {
			result &= MiscFunctions.compareResult("Registration #",
					expectedBoat.getRegistrationNum(),
					actualBoat.getRegistrationNum());
		}
		if (!StringUtil.isEmpty(expectedBoat.getBoatCategory())) {
			result &= MiscFunctions.compareResult("Boat Category",
					expectedBoat.getBoatCategory(),
					actualBoat.getBoatCategory());
		}

		if (NumberUtil.isGreaterThanZero(expectedBoat.getCapacity())) {
			result &= MiscFunctions.compareResult("Capacity",
					expectedBoat.getCapacity(), actualBoat.getCapacity());
		}
		if (NumberUtil.isGreaterThanZero(expectedBoat.getHorsePower())) {
			result &= MiscFunctions.compareResult("Horse Power",
					expectedBoat.getHorsePower(), actualBoat.getHorsePower());
		}
		if (!StringUtil.isEmpty(expectedBoat.getYear())) {
			result &= MiscFunctions.compareResult("Year",
					expectedBoat.getYear(), actualBoat.getYear());
		}
		if (!StringUtil.isEmpty(expectedBoat.getHullIndetification())) {
			result &= MiscFunctions.compareResult("Hull Identification",
					expectedBoat.getHullIndetification(),
					actualBoat.getHullIndetification());
		}
		if (!StringUtil.isEmpty(expectedBoat.getModel())) {
			result &= MiscFunctions.compareResult("Model",
					expectedBoat.getModel(), actualBoat.getModel());
		}
		if (!StringUtil.isEmpty(expectedBoat.getManufacturer())) {
			result &= MiscFunctions.compareResult("Manufacturer",
					expectedBoat.getManufacturer(),
					actualBoat.getManufacturer());
		}
		if (!StringUtil.isEmpty(expectedBoat.getMotorManufacturer())) {
			result &= MiscFunctions.compareResult("Motor Manufacturer",
					expectedBoat.getMotorManufacturer(),
					actualBoat.getMotorManufacturer());
		}
		if (!StringUtil.isEmpty(expectedBoat.getConstruction())) {
			result &= MiscFunctions.compareResult("Construction",
					expectedBoat.getConstruction(),
					actualBoat.getConstruction());
		}
		if (!StringUtil.isEmpty(expectedBoat.getTrailerType())) {
			result &= MiscFunctions.compareResult("Trailer Type",
					expectedBoat.getTrailerType(), actualBoat.getTrailerType());
		}
		if (!StringUtil.isEmpty(expectedBoat.getTrailerLicenseNum())) {
			result &= MiscFunctions.compareResult("Trailer License #",
					expectedBoat.getTrailerLicenseNum(),
					actualBoat.getTrailerLicenseNum());
		}

		// Captain
		if (!StringUtil.isEmpty(expectedBoat.getCaptainFirstName())) {
			result &= MiscFunctions.compareResult("Captain First Name",
					expectedBoat.getCaptainFirstName(),
					actualBoat.getCaptainFirstName());
		}
		if (!StringUtil.isEmpty(expectedBoat.getCaptainLastName())) {
			result &= MiscFunctions.compareResult("Captain Last Name",
					expectedBoat.getCaptainLastName(),
					actualBoat.getCaptainLastName());
		}

		// Boat Owner, Identify/Address
		result &= MiscFunctions.compareResult("Boat Owner Last Name",
				expectedBoat.getBoatOwnerLastName(),
				actualBoat.getBoatOwnerLastName());
		result &= MiscFunctions.compareResult("Boat Owner First Name",
				expectedBoat.getBoatOwnerFirstName(),
				actualBoat.getBoatOwnerFirstName());

		if (!StringUtil.isEmpty(expectedBoat.getBoatOwnerPhone())) {
			result &= MiscFunctions.compareResult("Boat Owner Phone",
					expectedBoat.getBoatOwnerPhone(),
					actualBoat.getBoatOwnerPhone());
		}
		if (!StringUtil.isEmpty(expectedBoat.getBoatOwnerWorkPhone())) {
			result &= MiscFunctions.compareResult("Boat Owner Work Phone",
					expectedBoat.getBoatOwnerWorkPhone(),
					actualBoat.getBoatOwnerWorkPhone());
		}
		if (!StringUtil.isEmpty(expectedBoat.getBoatOwnerEmail())) {
			result &= MiscFunctions.compareResult("Boat Owner Email Address",
					expectedBoat.getBoatOwnerEmail(),
					actualBoat.getBoatOwnerEmail());
		}

		result &= MiscFunctions.compareResult("Boat Owner Street Address",
				expectedBoat.getBoatOwnerStreetAddress(),
				actualBoat.getBoatOwnerStreetAddress());
		result &= MiscFunctions.compareResult("Boat Owner City",
				expectedBoat.getBoatOwnerCity(), actualBoat.getBoatOwnerCity());
		result &= MiscFunctions.compareResult("Boat Owner Zip",
				expectedBoat.getBoatOwnerZip(), actualBoat.getBoatOwnerZip());
		result &= MiscFunctions.compareResult("Boat Owner State/Province",
				expectedBoat.getBoatOwnerState(),
				actualBoat.getBoatOwnerState());
		result &= MiscFunctions.compareResult("Boat Owner Country",
				expectedBoat.getBoatOwnerCountry(),
				actualBoat.getBoatOwnerCountry());

		return result;
	}

	private IHtmlObject[] getOrderItemActionTableObject() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.TABLE", ".id",
				new RegularExpression("FormBar_\\d+", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.TABLE",
				".text", new RegularExpression(
						"^(Transfer|Date Change|Check-Out|No Show)", false));
		IHtmlObject objs[] = browser.getHtmlObject(Property.atList(p1, p2));

		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Cannot find Order Item Action table object.");
		}

		return objs;
	}

	public List<CustType> getCustomerTypes() {
		IHtmlObject[] typeTbl = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "1_OrderProofTable");
		if (typeTbl.length < 1) {
			throw new ErrorOnPageException(
					"Cannot find any table about Customer Type");
		}

		IHtmlObject[] tbody = browser.getHtmlObject(".class", "Html.TBODY",
				typeTbl[0]);

		List<CustType> types = new ArrayList<CustType>();

		IHtmlObject[] trs = browser
				.getHtmlObject(".class", "Html.TR", tbody[1]);

		for (IHtmlObject tr : trs) {
			CustType type = new CustType();

			type.type = browser.getDropdownListValue(
					Property.toPropertyArray(".id", "1_proofType"), 0, tr);
			type.id = browser.getTextFieldValue(
					Property.toPropertyArray(".id", "1_proofID"), 0, tr);
			type.notes = browser.getTextFieldValue(
					Property.toPropertyArray(".id", "1_proofNotes"), 0, tr);

			IHtmlObject[] proofShown = browser.getCheckBox(
					Property.toPropertyArray(".id", "1_proofShown"), tr);

			type.proofShown = ((ICheckBox) proofShown[0]).isSelected();
			Browser.unregister(proofShown);

			types.add(type);
		}

		Browser.unregister(trs);
		Browser.unregister(tbody);
		return types;

	}

	public List<CustPass> getCustomerPasses() {
		IHtmlObject[] typeTbl = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "customerPassPanel");
		if (typeTbl.length < 1) {
			throw new ErrorOnPageException(
					"Cannot find any table about Customer Passes");
		}

		IHtmlObject[] tbody = browser.getHtmlObject(".class", "Html.TBODY",
				typeTbl[0]);

		List<CustPass> passes = new ArrayList<CustPass>();

		IHtmlObject[] trs = browser
				.getHtmlObject(".class", "Html.TR", tbody[1]);

		for (IHtmlObject tr : trs) {
			CustPass pass = new CustPass();

			pass.passType = browser.getDropdownListValue(
					Property.toPropertyArray(".id", "passTypeId"), 0, tr);
			pass.passNum = browser.getTextFieldValue(
					Property.toPropertyArray(".id", "passNumber"), 0, tr);
			pass.holderName = browser.getTextFieldValue(
					Property.toPropertyArray(".id", "holderName"), 0, tr);
			pass.notes = browser.getTextFieldValue(
					Property.toPropertyArray(".id", "notes"), 0, tr);
			pass.expiryDate = browser.getTextFieldValue(
					Property.toPropertyArray(".name", "expiryDate_ForDisplay"),
					0, tr);

			pass.proofShown = browser.isCheckBoxSelected(
					Property.toPropertyArray(".id", "proofShown_for_display"),
					tr);

			passes.add(pass);
		}

		Browser.unregister(trs);
		Browser.unregister(tbody);
		return passes;

	}

	public void clickTransfer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Transfer", true,
				0, getOrderItemActionTableObject()[0]);
	}

	public void clickRenew() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Renew");
	}
	
	public void clickReceipt() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Receipts");
	}

	public void clickUnDock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "UnDock", true, 0,
				getOrderItemActionTableObject()[0]);
	}

	public void clickDock() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Dock", true, 0,
				getOrderItemActionTableObject()[0]);
	}

	public void clickNoShow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "No Show");
	}

	public void clickUndoNoShow() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Undo No Show");
	}

	protected Property[] undoFulfillmentBtn() {
		return Property.toPropertyArray(".class", "Html.A", ".text",
				"Undo Fulfillment");
	}

	public void clickUndoFulfillment() {
		browser.clickGuiObject(undoFulfillmentBtn());
	}

	public boolean checkUndoFulfillmentBtnEnabled() {
		return browser.checkHtmlObjectEnabled(undoFulfillmentBtn());
	}

	private IHtmlObject[] getOrderItemTableObject() {
		IHtmlObject objs[] = browser
				.getTableTestObject(
						".id",
						new RegularExpression("grid_\\d+", false),
						".text",
						new RegularExpression(
								"Marina(\\s)?Dock(\\s)?(Slip Group #\\s)?Slip #(\\s)?Arrival(\\s)?Departure",
								false));
		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Cannot find Slip reservation order item table object.");
		}

		return objs;
	}

	public String getOrderItemStatus(SlipInfo slip) {
		IHtmlObject objs[] = this.getOrderItemTableObject();
		IHtmlTable table = (IHtmlTable) objs[0];

		String status = "";
		if (slip == null || table.rowCount() == 2) {
			int columnIndex = table.findColumn(0,
					ORDER_ITEM_COLUMN_ORDER_ITEM_STATUS);
			status = table.getCellValue(1, columnIndex);
		} else {
			// TODO get Order Item status in multiple records identified by slip
			// info
		}

		Browser.unregister(objs);
		return status;
	}

	public boolean isCheckInObjectExist() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				"MarinaOrderView-\\d+.profileView.checkin", false));
	}

	public void selectCheckIn() {
		browser.selectCheckBox(".id", new RegularExpression(
				"MarinaOrderView-\\d+.profileView.checkin$", false));
	}

	public void unselectCheckIn() {
		browser.unSelectCheckBox(".id", new RegularExpression(
				"MarinaOrderView-\\d+.profileView.checkin$", false));
		ajax.waitLoading();
	}

	public boolean isFastCheckInAvailable() {
		Property[] p = new Property[2];
		p[0] = new Property(".class", "Html.INPUT.radio");
		p[1] = new Property(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.regularCheckin", false));

		return browser.checkHtmlObjectExists(p);
	}

	public void selectFastCheckIn() {
		browser.selectRadioButton(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.regularCheckin", false),
				".value", "false");
	}

	public void selectRegularCheckIn() {
		browser.selectRadioButton(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.regularCheckin", false),
				".value", "true");
	}

	public void selectCheckOut() {
		browser.selectCheckBox(".id", new RegularExpression(
				"MarinaOrderView-\\d+\\.profileView\\.checkout", false));
	}

	public void clickCheckOut() {
		// browser.selectCheckBox(".id", new
		// RegularExpression("MarinaOrderView-\\d+\\.profileView\\.checkout",
		// false));
		this.clickResActionButton("Check-out");
	}

	/**
	 * This method is used to verify all the button in order action section are
	 * disabled
	 */
	public void checkAllButtonInOrderActionsDisabled() {
		boolean passed = true;
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".id", "headerFrom");
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", objs[0]);
		IHtmlObject[] objLinks = browser.getHtmlObject(".class", "Html.DIV",
				".className", "link", trs[0]);
		int size = objLinks.length;
		for (int i = 0; i < size; i++) {
			IHtmlObject button[] = browser.getHtmlObject(".class",
					"Html.BUTTON", objLinks[i]);
			if (button.length > 0) {
				passed = false;
				logger.error("The button '" + objLinks[i].text()
						+ "' is enabled!");
			}
		}
		Browser.unregister(objs);
		Browser.unregister(trs);
		Browser.unregister(objLinks);
		if (!passed) {
			throw new ErrorOnPageException(
					"Not all the button in order actions section is disabled, see the log above!");
		}
	}

	public String getSlipResStatus() {
		return this.getAttributeByName("Status");
	}

	public String getSlipResOrderStatus() {
		return this.getAttributeByName("Order Status");
	}

	public String getSlipResCreatedDate() {
		return this.getAttributeByName("Created Date");
	}

	public String getSlipResCreatedBy() {
		return this.getAttributeByName("Created By");
	}

	public String getSlipResPrice() {
		return this.getAttributeByName("Price");
	}

	public String getSlipResPaid() {
		return this.getAttributeByName("Paid");
	}

	public String getSlipResUnissuedRefund() {
		return this.getAttributeByName("Unissued Refund");
	}

	public String getSlipResConfirmationStatus() {
		return this.getAttributeByName("Confirmation Status");
	}

	public String getSlipResBalance() {
		return this.getAttributeByName("Balance");
	}

	public String getSlipResCollectionStatus() {
		return this.getAttributeByName("Collection Status");
	}

	public SlipReservationInfo getSlipReservationInfo() {
		SlipReservationInfo slipResInfo = new SlipReservationInfo();

		// Boat Info section
		slipResInfo.reservNum = getSlipReservationNum();
		slipResInfo.reservStatus = getSlipResStatus();
		slipResInfo.orderStatus = getSlipResOrderStatus();
		slipResInfo.setCreatedDate(getSlipResCreatedDate());
		slipResInfo.setCreatedBy(getSlipResCreatedBy());
		slipResInfo.orderPrice = getSlipResPrice();
		slipResInfo.orderPaid = getSlipResPaid();
		slipResInfo.setUnissuedRefund(getSlipResUnissuedRefund());
		slipResInfo.orderConfStatus = getSlipResConfirmationStatus();
		slipResInfo.orderBalance = getSlipResBalance();
		slipResInfo.setCollectionStatus(getSlipResCollectionStatus());
		return slipResInfo;
	}

	/**
	 * This method is used to verify the fields and its default value for slip
	 * reservation section
	 * 
	 * @param slipResInfo
	 */
	public void compareSlipReservationInfo(SlipReservationInfo slipResInfo) {
		boolean passed = true;
		SlipReservationInfo actualSlipRes = getSlipReservationInfo();
		passed &= MiscFunctions.compareResult("Slip Reservation #",
				slipResInfo.reservNum, actualSlipRes.reservNum);
		passed &= MiscFunctions.compareResult("Status",
				slipResInfo.reservStatus, actualSlipRes.reservStatus);
		passed &= MiscFunctions.compareResult("Order Status",
				slipResInfo.orderStatus, actualSlipRes.orderStatus);
		passed &= MiscFunctions.compareResult("Created Date",
				slipResInfo.getCreatedDate(), actualSlipRes.getCreatedDate());
		passed &= MiscFunctions.compareResult("Created By",
				slipResInfo.getCreatedBy(), actualSlipRes.getCreatedBy());
		passed &= MiscFunctions.compareResult("Price", slipResInfo.orderPrice,
				actualSlipRes.orderPrice);
		passed &= MiscFunctions.compareResult("Paid", slipResInfo.orderPaid,
				actualSlipRes.orderPaid);
		passed &= MiscFunctions.compareResult("Unissued Refund",
				slipResInfo.getUnissuedRefund(),
				actualSlipRes.getUnissuedRefund());
		passed &= MiscFunctions.compareResult("Confirmation Status",
				slipResInfo.orderConfStatus, actualSlipRes.orderConfStatus);
		passed &= MiscFunctions.compareResult("Balance",
				slipResInfo.orderBalance, actualSlipRes.orderBalance);
		passed &= MiscFunctions.compareResult("Collection Status",
				slipResInfo.getCollectionStatus(),
				actualSlipRes.getCollectionStatus());

		if (!passed) {
			throw new ErrorOnPageException(
					"Not all the value for slip reservation section is correct, see the log above!");
		}
	}

	public String getInvoiceNum() {
		return this.getAttributeByName("Invoice #");
	}

	public String getInvoiceNumofActiveCharges() {
		return this.getAttributeByName("# of Active Charges");
	}

	/**
	 * This method is used to verify the fields and its default value for
	 * invoice section
	 * 
	 * @param fieldsAndValues
	 */
	public void compareInvoiceInfo(Map<String, String> fieldsAndValues) {
		boolean passed = true;
		passed &= MiscFunctions.compareResult("Invoice Number",
				fieldsAndValues.get("Invoice #"), getInvoiceNum());
		passed &= MiscFunctions.compareResult("Number of active charges",
				fieldsAndValues.get("# of Active Charges"),
				getInvoiceNumofActiveCharges());
		if (!passed) {
			throw new ErrorOnPageException(
					"Some of the invoice information may not correct, please check the log above!");
		}
	}

	public String getCustName() {
		return this.getAttributeByName("Name");
	}

	public String getCustPhone() {
		return this.getAttributeByName("Phone");
	}

	public String getCustEmailAddress() {
		return this.getAttributeByName("Email Address");
	}

	public String getCustOranizationName() {
		return this.getAttributeByName("Organization Name");
	}

	public String getCustPhoneContactPreference() {
		return this.getAttributeByName("Phone Contact Preference");
	}

	public String getCustPreferenceContactTime() {
		return this.getAttributeByName("Preference Contact Time");
	}

	public String getCustStreetAddress() {
		return this.getAttributeByName("Street Address");
	}

	public String getCustCity() {
		return this.getAttributeByName("City");
	}

	public String getCustZip() {
		return this.getAttributeByName("Zip");
	}

	public String getCustStateOrProvince() {
		return this.getAttributeByName("State/Province");
	}

	public String getCustCountry() {
		return this.getAttributeByName("Country");
	}

	public Customer getCustomerInfo() {
		Customer cust = new Customer();

		// cust basic info
		cust.name = getCustName();
		cust.hPhone = getCustPhone();
		cust.email = getCustEmailAddress();
		cust.organization = getCustOranizationName();
		cust.phoneContact = getCustPhoneContactPreference();
		cust.contactTime = getCustPreferenceContactTime();
		// cust address info
		cust.address = getCustStreetAddress();
		cust.city = getCustCity();
		cust.zip = getCustZip();
		cust.state = getCustStateOrProvince();
		cust.country = getCustCountry();
		return cust;
	}

	/**
	 * This method is used to verify the fields and its value for customer
	 * section
	 * 
	 * @param cust
	 */
	public void compareCustomerInfo(Customer cust) {
		boolean passed = true;
		Customer actualCust = getCustomerInfo();
		// basic info table
		passed &= MiscFunctions.compareResult("Customer Name", cust.lName + ","
				+ cust.fName, actualCust.name);
		passed &= MiscFunctions.compareResult("Phone", cust.hPhone,
				actualCust.hPhone);
		passed &= MiscFunctions.compareResult("Email Address", cust.email,
				actualCust.email);
		passed &= MiscFunctions.compareResult("Organization Name",
				cust.organization, actualCust.organization);
		passed &= MiscFunctions.compareResult("Phone Contact Preference",
				cust.phoneContact, actualCust.phoneContact);
		passed &= MiscFunctions.compareResult("Preference Contact Time",
				cust.contactTime, actualCust.contactTime);
		// address info
		passed &= MiscFunctions.compareResult("Street Address", cust.address,
				actualCust.address);
		passed &= MiscFunctions.compareResult("City", cust.city,
				actualCust.city);
		passed &= MiscFunctions.compareResult("Zip", cust.zip, actualCust.zip);
		passed &= MiscFunctions.compareResult("State/Province", cust.state,
				actualCust.state);
		passed &= MiscFunctions.compareResult("Country", cust.country,
				actualCust.country);
		if (!passed) {
			throw new ErrorOnPageException(
					"Some of the customer information may not correct, please check the log above!");
		}
	}

	public boolean isCaptionFirstNameFieldEnabled() {
		return isPageObjectEnabled("MarinaOrderView-\\d+\\.captainFirstName");
	}

	public boolean isCaptionLastNameFieldEnabled() {
		return isPageObjectEnabled("MarinaOrderView-\\d+\\.captainLastName");
	}

	public boolean allFieldsNotEditableForCaption() {
		boolean notEditable = true;
		notEditable &= !isCaptionFirstNameFieldEnabled();
		notEditable &= !isCaptionLastNameFieldEnabled();
		return notEditable;
	}

	public void checkCaptionFielsEnabled() {
		boolean passed = true;
		// Make sure the text field is enabled
		passed &= isCaptionFirstNameFieldEnabled();
		passed &= isCaptionLastNameFieldEnabled();
		if (!passed) {
			throw new ErrorOnPageException(
					"Not all the fields for caption is enabled, please check the log above!");
		}
	}

	public void checkCaptionInfo(String fName, String lName) {
		boolean passed = true;
		// Check the content is correct
		passed &= MiscFunctions.compareResult("Caption First Name", fName,
				getCaptainFirstName());
		passed &= MiscFunctions.compareResult("Caption Last Name", lName,
				getCaptainLastName());
		if (!passed) {
			throw new ErrorOnPageException(
					"Some of the caption content may not correct, please check the log above!");
		}
	}

	private boolean isPageObjectEnabled(String idValue) {
		return browser.checkHtmlObjectEnabled(".id", new RegularExpression(
				idValue, false));
	}

	String boatOwnerPrefix = "MarinaVesselOwnerView-\\d+\\.";

	public boolean isBoatOwnerLNameFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "lastName");
	}

	public boolean isBoatOwnerFNameFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "firstName");
	}

	public boolean isBoatOwnerPhoneFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "phone");
	}

	public boolean isBoatOwnerWorkPhoneFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "workPhone");
	}

	public boolean isBoatOwnerEmailAddressFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "email");
	}

	public boolean isBoatOwnerStreetAddressFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "street");
	}

	public boolean isBoatOwnerCityFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "city");
	}

	public boolean isBoatOwnerZipFieldEnabled() {
		return isPageObjectEnabled(boatOwnerPrefix + "zipCode");
	}

	public boolean isBoatOwnerStateOrProvinceDropDownEnabled() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		IHtmlObject[] dropList = browser
				.getDropdownList(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(MarinaOrderView-\\d+\\.boatOwner\\.state|MarinaVesselOwnerView-\\d+\\.state)",
										false)), objs[0]);
		int num = dropList.length;
		boolean enable = dropList[num - 1].isEnabled();
		Browser.unregister(objs);
		Browser.unregister(dropList);
		return enable;
		// return isPageObjectEnabled(new
		// RegularExpression("(MarinaOrderView-\\d+\\.boatOwner\\.state|MarinaVesselOwnerView-\\d+\\.state)",
		// false));
	}

	public boolean isBoatOwnerCountryDropDownEnabled() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				"MarinaOrderDetailBoatInfo");
		IHtmlObject[] dropList = browser
				.getDropdownList(
						Property.toPropertyArray(
								".id",
								new RegularExpression(
										"(MarinaOrderView-\\d+\\.boatOwner\\.country|MarinaVesselOwnerView-\\d+\\.country)",
										false)), objs[0]);
		int num = dropList.length;
		boolean enable = dropList[num - 1].isEnabled();
		Browser.unregister(objs);
		Browser.unregister(dropList);
		return enable;
		// return
		// isPageObjectEnabled("MarinaOrderView-\\d+\\.boatOwner.country");
	}

	public boolean allFieldsNotEditableForBoatOwner() {
		boolean notEditable = true;
		notEditable &= !isBoatOwnerLNameFieldEnabled();
		notEditable &= !isBoatOwnerFNameFieldEnabled();
		notEditable &= !isBoatOwnerPhoneFieldEnabled();
		notEditable &= !isBoatOwnerWorkPhoneFieldEnabled();
		notEditable &= !isBoatOwnerEmailAddressFieldEnabled();

		notEditable &= !isBoatOwnerStreetAddressFieldEnabled();
		notEditable &= !isBoatOwnerCityFieldEnabled();
		notEditable &= !isBoatOwnerZipFieldEnabled();

		notEditable &= !isBoatOwnerStateOrProvinceDropDownEnabled();
		notEditable &= !isBoatOwnerCountryDropDownEnabled();
		return notEditable;
	}

	public void checkBoatOwnerFieldsEnabled() {
		boolean passed = true;
		passed &= isBoatOwnerLNameFieldEnabled();
		passed &= isBoatOwnerFNameFieldEnabled();
		passed &= isBoatOwnerPhoneFieldEnabled();
		passed &= isBoatOwnerWorkPhoneFieldEnabled();
		passed &= isBoatOwnerEmailAddressFieldEnabled();

		passed &= isBoatOwnerStreetAddressFieldEnabled();
		passed &= isBoatOwnerCityFieldEnabled();
		passed &= isBoatOwnerZipFieldEnabled();

		passed &= isBoatOwnerStateOrProvinceDropDownEnabled();
		passed &= isBoatOwnerCountryDropDownEnabled();
		if (!passed) {
			throw new ErrorOnPageException(
					"Some field of the boat owner is not exist or not enabled, please check the log above!");
		}
	}

	private String boatInfoPrefix = "OrderProfileMarinaVesselView-\\d+\\.";

	public void checkValueForBoatLength(String expectValue) {
		String boatLength = browser.getTextFieldValue(".id", boatInfoPrefix
				+ "length");
		if (!expectValue.equalsIgnoreCase(expectValue)) {
			throw new ErrorOnPageException(
					"Value for boat length is not correct", expectValue,
					boatLength);
		}
	}

	public boolean isBoatNameFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "boatName");
	}

	public boolean isBoatTypeDorpDownListEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "vesselType");
	}

	public boolean isBoatLengthFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "length");
	}

	public boolean isBoatWidthFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "width");
	}

	public boolean isBoatDepthFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "depth");
	}

	public boolean isBoatRegistrationNumFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "registrationNumber");
	}

	public boolean isBoatCategoryDorpDownListEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "boatCategory");
	}

	public boolean isBoatCapacityFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "capacity");
	}

	public boolean isBoatHorsePownerFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "horsePower");
	}

	public boolean isBoatYearFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "year");
	}

	public boolean isBoatHullIdentificationFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "hullID");
	}

	public boolean isBoatModelFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "model");
	}

	public boolean isBoatManufacturerFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "manufacturer");
	}

	public boolean isBoatMotorManufacturerFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "motorManufacturer");
	}

	public boolean isBoatConstructionFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "construction");
	}

	public boolean isBoatTrailerTypeFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "trailerType");
	}

	public boolean isBoatTrailerLicenseFieldEnabled() {
		return isPageObjectEnabled(boatInfoPrefix + "trailerLicense");
	}

	public boolean allFieldsEditableForBoatInfo() {
		boolean editable = true;
		editable &= isBoatNameFieldEnabled();
		editable &= isBoatLengthFieldEnabled();
		editable &= isBoatWidthFieldEnabled();
		editable &= isBoatDepthFieldEnabled();

		editable &= isBoatTypeDorpDownListEnabled();
		editable &= isBoatRegistrationNumFieldEnabled();
		editable &= isBoatCategoryDorpDownListEnabled();
		editable &= isBoatCapacityFieldEnabled();
		editable &= isBoatHorsePownerFieldEnabled();
		editable &= isBoatYearFieldEnabled();
		editable &= isBoatHullIdentificationFieldEnabled();
		editable &= isBoatModelFieldEnabled();
		editable &= isBoatManufacturerFieldEnabled();
		editable &= isBoatMotorManufacturerFieldEnabled();
		editable &= isBoatConstructionFieldEnabled();
		editable &= isBoatTrailerTypeFieldEnabled();
		editable &= isBoatTrailerLicenseFieldEnabled();

		return editable;
	}

	public boolean allFieldsNotEditableForBoatInfo() {
		boolean notEditable = true;
		notEditable &= !isBoatNameFieldEnabled();
		notEditable &= !isBoatLengthFieldEnabled();
		notEditable &= !isBoatWidthFieldEnabled();
		notEditable &= !isBoatDepthFieldEnabled();

		notEditable &= !isBoatTypeDorpDownListEnabled();
		notEditable &= !isBoatRegistrationNumFieldEnabled();
		notEditable &= !isBoatCategoryDorpDownListEnabled();
		notEditable &= !isBoatCapacityFieldEnabled();
		notEditable &= !isBoatHorsePownerFieldEnabled();
		notEditable &= !isBoatYearFieldEnabled();
		notEditable &= !isBoatHullIdentificationFieldEnabled();
		notEditable &= !isBoatModelFieldEnabled();
		notEditable &= !isBoatManufacturerFieldEnabled();
		notEditable &= !isBoatMotorManufacturerFieldEnabled();
		notEditable &= !isBoatConstructionFieldEnabled();
		notEditable &= !isBoatTrailerTypeFieldEnabled();
		notEditable &= !isBoatTrailerLicenseFieldEnabled();

		return notEditable;
	}

	public void checkFieldsEditableForBoatInfo() {
		boolean passed = true;
		passed = this.allFieldsEditableForBoatInfo();

		if (!passed) {
			throw new ErrorOnPageException(
					"Some field of the boat owner is not exist or not enabled, please check the log above!");
		}
	}

	public void checkBoatInfoValue(BoatInfo expectBoatInfo) {
		boolean passed = true;
		passed = compareBoatInfo(expectBoatInfo);
		if (!passed) {
			throw new ErrorOnPageException(
					"Some fields' value of the boat info may not correct, please check the log above!");
		}
	}

	public void checkBoatBelongToCustomer(String... custBoat) {
		boolean passed = true;
		List<String> boats = browser
				.getDropdownElements(".id", new RegularExpression(
						"DropdownExt-\\d+\\.selectedValue", false));
		for (int i = 0; i < custBoat.length; i++) {
			passed &= MiscFunctions.compareResult(i + "th boat record",
					custBoat[i], boats.get(i));
		}
		if (!passed) {
			throw new ErrorOnPageException(
					"The order or the content may not correct for cust boat!");
		}
	}

	private boolean checkButtonIsEnable(String name) {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text",
				name);
	}

	public boolean checkChargePOSButtonIsEnable() {
		return this.checkButtonIsEnable("Charge POS");
	}

	public boolean checkFeeButtonIsEnable() {
		return this.checkButtonIsEnable("Fee");
	}

	public boolean checkAdjustFeesToPastPaidButtonIsEnable() {
		return this.checkButtonIsEnable("Adjust Fees to Past Paid");
	}

	public boolean checkReprintPermitButtonIsEnable() {
		return this.checkButtonIsEnable("Reprint Permit");
	}

	public boolean checkReprintReceiptsButtonIsEnable() {
		return this.checkButtonIsEnable("Reprint Receipts");
	}

	public boolean checkReceiptsButtonIsEnable() {
		return this.checkButtonIsEnable("Receipts");
	}

	public boolean checkRequestConfLetterButtonIsEnable() {
		return this.checkButtonIsEnable("Request Conf Letter");
	}

	public boolean checkTransferButtonIsEnable() {
		return this.checkButtonIsEnable("Transfer");
	}

	public boolean checkRenewButtonIsEnable() {
		return this.checkButtonIsEnable("Renew");
	}

	public boolean checkDateChangeButtonIsEnable() {
		return this.checkButtonIsEnable("Date Change");
	}

	public boolean checkNoShowButtonIsEnable() {
		return this.checkButtonIsEnable("No Show");
	}

	public boolean checkUndoNoShowButtonIsEnable() {
		return this.checkButtonIsEnable("Undo No Show");
	}
	
	public boolean checkCheckOutButtonIsEnable() {
		return this.checkButtonIsEnable("Check-out");
	}

	private String getAttributeTextOfSectionObject(String sectionNameRegular,
			String attrName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression("^" + sectionNameRegular + ".*",
						false));

		if (objs.length < 1) {
			throw new ItemNotFoundException(
					"Did not get Change Reservation Dates section object.");
		}

		// IHtmlObject[] divObjs = browser.getHtmlObject(".class", "Html.DIV",
		// ".text", new RegularExpression("^" + attrName + ".*",false),objs[0]);
		IHtmlObject[] tdObjs = browser.getHtmlObject(".class", "Html.TD",
				".text", new RegularExpression("^" + attrName + ".*", false),
				objs[0]);
		if (tdObjs.length < 1) {
			throw new ItemNotFoundException("Did not get " + attrName
					+ " object at Change Reservation Dates section object.");
		}

		String text = tdObjs[tdObjs.length - 1].text().replaceAll(attrName, "")
				.trim();
		Browser.unregister(tdObjs);
		Browser.unregister(objs);
		return text;
	}

	private String getAttributeTextOfTransferFromSlipSectionObject(String name) {
		return getAttributeTextOfSectionObject("Transfer From Slip", name);
	}

	private String getAttributeTextOfChangeResDatesSectionObject(String name) {
		return getAttributeTextOfSectionObject("Change Reservation Dates", name);
	}

	public String getSlipResNumbOfTransFromSection() {
		return this
				.getAttributeTextOfTransferFromSlipSectionObject("Slip Reservation #");
	}

	public String getMarinaOfTransFromSection() {
		return this.getAttributeTextOfTransferFromSlipSectionObject("Marina");
	}

	public String getDockAreaOfTransFromSection() {
		return this
				.getAttributeTextOfTransferFromSlipSectionObject("Dock/Area");
	}

	public String getSlipNameOfTransFromSection() {
		return this
				.getAttributeTextOfTransferFromSlipSectionObject("Slip # \\(Name\\)");
	}

	public String getArrivalDateOfTransFromSection() {
		return this.getAttributeTextOfTransferFromSlipSectionObject("Arrival");
	}

	public String getDepartureDateOfTransFromSection() {
		return this
				.getAttributeTextOfTransferFromSlipSectionObject("Departure");
	}

	public String getNightsOfTransFromSection() {
		return this.getAttributeTextOfTransferFromSlipSectionObject("Nights");
	}

	public String getMonthssOfTransFromSection() {
		return this.getAttributeTextOfTransferFromSlipSectionObject("Months");
	}

	public String getSlipResNumbOfChangeResDatesSection() {
		return this
				.getAttributeTextOfChangeResDatesSectionObject("Slip Reservation #");
	}

	public String getMarinaOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Marina");
	}

	public String getDockAreaOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Dock/Area");
	}

	public String getSlipNameOfChangeResDatesSection() {
		return this
				.getAttributeTextOfChangeResDatesSectionObject("Slip # \\(Name\\)");
	}

	public String getArrivalDateOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Arrival");
	}

	public String getDepartureDateOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Departure");
	}

	public String getNightsOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Nights");
	}

	public String getMonthssOfChangeResDatesSection() {
		return this.getAttributeTextOfChangeResDatesSectionObject("Months");
	}

	public void clickSlipLink(String code, String name) {
		browser.clickGuiObject(".class", "Html.A", ".text",
				code + " - " + name, true);
	}
	
	public void clickSlipContractLink() {
		browser.clickGuiObject(".class", "Html.A", ".id",
				 new RegularExpression(
							"SlipContractView-\\d+\\.id", false));
	}

	public boolean getTransferButtonAvailable() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				"Transfer");
		if (objs.length > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void addOtherOccupants(List<String[]> otherOccupants) {
		for (int i = 0; i < otherOccupants.size(); i++) {
			clickAddOtherOccupant();
			this.setOtherOccFirstName(otherOccupants.get(i)[1], i + 1);
			this.setOtherOccLastName(otherOccupants.get(i)[2], i + 1);
		}
	}

	public void addVehicleInfos(List<VehicleInfo> vehicleInfos) {
		for (int i = 0; i < vehicleInfos.size(); i++) {
			clickAddVehicle();
			this.setPlateBoxValue(vehicleInfos.get(i).getPlateNum(), i + 1);
			this.selectStateValue(vehicleInfos.get(i).getState(), i + 1);
			this.selectMakeValue(vehicleInfos.get(i).getMake(), i + 1);
			this.setModelValue(vehicleInfos.get(i).getModel(), i + 1);
			this.selectColorValue(vehicleInfos.get(i).getColor(), i + 1);
		}
	}

	/**
	 * Add other occupant types
	 * 
	 * @param list
	 */
	public void addOtherOccupant(List<OtherOccupant> occupants) {
		for (int i = 0; i < occupants.size(); i++) {
			this.clickAddOtherOccupant();
			this.setOtherOccFirstName(occupants.get(i).getFirstName(), i + 1);
			this.setOtherOccLastName(occupants.get(i).getLastName(), i + 1);
		}
	}

	public void setupSlipResInfo(String actionReason, SlipReservationInfo res,
			boolean isCheckin) {
		if (isActionReasonDropdownListExists()) {
			if (!StringUtil.isEmpty(actionReason)) {
				selectActionReason(actionReason);
			} else {
				selectActionReason(1);
			}
		}

		// Customer Type
		if (!StringUtil.isEmpty(res.customer.type)) {
			addCustomerType(res.customer.type, res.customer.typeID,
					res.customer.typeNotes, res.customer.typeProofShown);
		}
		if (res.customer.custTypes.size() > 0) {
			addCustomerType(res.customer.custTypes);
		}

		// Customer Pass
		if (!StringUtil.isEmpty(res.customer.pass)) {
			addCustomerPass(res.customer.pass, res.customer.passNumber, null,
					null, null, false);
		}
		if (res.customer.custPasses.size() > 0) {
			addCustomerPass(res.customer.custPasses);
		}
		if (!StringUtil.isEmpty(res.promoCode)) {
			selectPromoCode(res.promoCode);
		}

		// other occupants
		if (res.getOtherOccupants() != null
				&& res.getOtherOccupants().size() > 0) {
			removeAllOtherOcc();
			this.waitLoading();
			addOtherOccupant(res.getOtherOccupants());
		}

		// # of Vehicles
		// Vehicle info
		if (res.customer.vehicleInfo != null
				&& res.customer.vehicleInfo.size() > 0) {
			addVehicleInfos(res.customer.vehicleInfo);
		}

		// Permit Number
		if (!StringUtil.isEmpty(res.customer.documentNum)) {
			setPermitNumber(res.customer.documentNum);
		}

		// Boat Info
		if (res.customer.boat != null && isBoatInfoSectionExists()) {
			setupBoatInfo(res.customer.boat);
		}

		// Check-in
		if (isCheckInObjectExist()) {
			if (isCheckin) {
				selectCheckIn();
			} else {
				unselectCheckIn();
			}
			ajax.waitLoading();

			// Credit Card info (Nicole 9/9)
			if (this.isCheckInSelected()) {
				if (res.isCollectCCInfo) {// collect Credit Card info on
											// reservation details page
					setSwipeInfo(res.creditCardType, res.creditCardNumber,
							res.creditCardMM, res.creditCardYY,
							res.creditCardHolderName);
				}
			}
		}
	}

	private String getAttributeValueByName(String name) {
		String nameRegex;
		if (name.contains("(") && name.contains(")")) {
			nameRegex = name.replace("(", "\\(").replace(")", "\\)");
		} else {
			nameRegex = name;
		}
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".className", "inputwithrubylabel",
				".text", new RegularExpression(nameRegex, false)));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - "
					+ name);
		}

		String text = objs[0].text().replace(name, StringUtil.EMPTY);
		Browser.unregister(objs);

		return text;
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
	 * 
	 * @param resNum
	 * @param transferFromSlip
	 */
	public void verifyTransferFromSlipInfo(String resNum,
			SlipInfo transferFromSlip) {
		logger.info("Verify 'Transfer From Slip' section info.");
		boolean result = true;
		result &= MiscFunctions.compareResult(
				"Transfer from Slip Reservation #", resNum,
				getSlipReservationNum());
		result &= MiscFunctions.compareResult("Transfer from Marina",
				transferFromSlip.getMarina(), getTransferFromMarina());
		result &= MiscFunctions
				.compareResult("Transfer from Dock/Area",
						transferFromSlip.getParentDockArea(),
						getTransferFromDockArea());
		result &= MiscFunctions.compareResult("Transfer from Slip # - Name",
				transferFromSlip.getCode() + "-" + transferFromSlip.getName(),
				getTransferFromSlipNumName());
		result &= MiscFunctions
				.compareResult("Transfer from Arrival Date",
						transferFromSlip.getArrivalDate(),
						getTransferFromArrivalDate());
		result &= MiscFunctions.compareResult("Transfer from Departure Date",
				transferFromSlip.getDepartureDate(),
				getTransferFromDepartureDate());
		if (transferFromSlip.getReservationType().equalsIgnoreCase(
				OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Transfer from Nights",
					transferFromSlip.getNights(),
					Integer.parseInt(getTransferFromNights()));
		} else {
			result &= MiscFunctions.compareResult("Transfer from Months",
					transferFromSlip.getMonths(),
					Integer.parseInt(getTransferFromMonths()));
		}

		if (!result) {
			throw new ErrorOnPageException(
					"'Transfer From Slip' section info is NOT correct, please refer o log for details.");
		} else
			logger.info("'Transfer From Slip' section info is correct.");
	}

	public List<List<String>> getSlipInventoryInfo() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Marina Dock Slip.*", false));

		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];

		List<List<String>> rowsInfo = new ArrayList<List<String>>();
		for (int i = 1; i < grid.rowCount(); i++) {
			rowsInfo.add(grid.getRowValues(i));
		}
		Browser.unregister(objs);

		return rowsInfo;
	}

	public boolean isTransferButtonDisabled() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Marina Dock Slip.*", false));

		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];
		
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "Transfer");
		boolean result=browser.checkHtmlObjectEnabled(property, grid);
		Browser.unregister(objs);
		return !result;
	}
	
	private boolean isButtonInSlipInventorySectionEnabled(String buttenText){
//		IHtmlObject[] objs = browser.getTableTestObject(".id",
//				new RegularExpression("grid_\\d+", false), ".text",
//				new RegularExpression("Marina Dock Slip.*", false));
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("FormBar_\\d+", false), ".text",
				new RegularExpression("^Slip Inventory.*", false));

		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];
		
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", buttenText);
		boolean result=browser.checkHtmlObjectEnabled(property, grid);
		Browser.unregister(objs);
		return result;
	}
	
	public boolean isNoShowButtonDisabled() {
		return !isButtonInSlipInventorySectionEnabled("No Show");
	}
	
	public boolean isUnNoShowButtonDisabled(){
		return !isButtonInSlipInventorySectionEnabled("Undo No Show");
	}
	
	public boolean isDateChangeButtonDisabled() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Marina Dock Slip.*", false));

		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];
		
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "Date Change");
		boolean result=browser.checkHtmlObjectEnabled(property, grid);
		Browser.unregister(objs);
		return !result;
	}
	
	public boolean isCheckOutButtonDisabled() {
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Marina Dock Slip.*", false));

		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];
		
		Property[] property = new Property[2];
		property[0] = new Property(".class", "Html.A");
		property[1] = new Property(".text", "Check - Out");
		boolean result=browser.checkHtmlObjectEnabled(property, grid);
		Browser.unregister(objs);
		return !result;
	}
	
	

	public String getOrderStatus() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"MarinaOrderView-\\d+\\.status:CB_TO_NAME",
								false)).replaceAll("Order Status", "").trim();
	}

	public String getProcessStatus() {
		// return browser.getObjectText(".class","Html.DIV",".id",new
		// RegularExpression("MarinaOrderView-\\d+\\.processStatus:CB_TO_NAME",false)).replaceAll("Status",
		// "").trim();
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"MarinaOrderView-\\d+\\.processStatus:CB_TO_NAME",
								false)).replaceAll("Status", "").trim();
	}

	public String getSlipContractID() {
		return this.getAttributeValueByName("Contract ID");
	}

	public String getSlipContractName() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"SlipContractView-\\d+\\.slipContractName:CB_TO_NAME",
								false)).replaceAll("Contract Name", "").trim();
	}

	public String getSlipContractStartDate() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"SlipContractView-\\d+\\.contractStartDate",
								false)).replaceAll("Start Date", "").trim();
	}

	public String getSlipContractEndDate() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"SlipContractView-\\d+\\.contractEndDate",
								false)).replaceAll("End Date", "").trim();
	}

	public String getSlipContractLocation() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"SlipContractView-\\d+\\.locationName", false))
				.replaceAll("Location", "").trim();
	}

	public String getSlipContractStatus() {
		return browser
				.getObjectText(
						".class",
						"Html.SPAN",
						".id",
						new RegularExpression(
								"SlipContractView-\\d+\\.slipContractStatus:CB_TO_NAME",
								false)).replaceAll("Status", "").trim();
	}

	public boolean isDateChangeButtonEnabled() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text",
				"Date Change");
	}

	public SlipContractInfo getSlipContractInfo() {
		SlipContractInfo contract = new SlipContractInfo();
		contract.setId(this.getSlipContractID());
		contract.setName(this.getSlipContractName());
		contract.setStartDate(this.getSlipContractStartDate());
		contract.setEndDate(this.getSlipContractEndDate());
		contract.setLocation(this.getSlipContractLocation());
		contract.setStatus(this.getSlipContractStatus());

		return contract;
	}

	public void verifySlipContractInfo(SlipContractInfo expected) {
		SlipContractInfo actual = this.getSlipContractInfo();
		if (!actual.equals(expected)) {
			throw new ErrorOnPageException("Slip Contract info is NOT correct.");
		} else
			logger.info("Slip Contract info is correct.");
	}

	public void verifyMarinaRateType(String expected) {
		List<String> marinaRateTypes = new ArrayList<String>();
		marinaRateTypes.add(expected);
		this.verifyMarinaRateType(marinaRateTypes);
	}

	public void verifyMarinaRateType(List<String> expected) {
		List<String> marinaRateTypes = this.getMarinaRateTypeColumnValues();
		if (marinaRateTypes.size() != expected.size()) {
			throw new ErrorOnPageException(
					"Marina Rate type size not correct.", expected.size(),
					marinaRateTypes.size());
		}

		boolean result = true;
		for (int i = 0; i < expected.size(); i++) {
			result &= MiscFunctions.compareResult("Marina Rate Type",
					expected.get(i), marinaRateTypes.get(i));
		}

		if (!result) {
			throw new ErrorOnPageException("Marina Rate type info not correct.");
		}
	}

	public void verifyOrderStatus(String expectStatus) {
		String status = this.getOrderStatus();
		if (!status.equalsIgnoreCase(expectStatus)) {
			throw new ErrorOnPageException("Order status is not correct",
					expectStatus, status);
		}
	}

	public void verifyResStatus(String expectStatus) {
		String status = this.getProcessStatus();
		if (!status.equalsIgnoreCase(expectStatus)) {
			throw new ErrorOnPageException("Reservation status is not correct",
					expectStatus, status);
		}
		logger.info("Reservation status is correct as " + expectStatus);
	}

	public void triggerPromoCodeChanges(String custType, String custPass,
			String boatCategory) {
		this.removeAllCustomerType();
		if (!StringUtil.isEmpty(custType)) {
			this.addCustomerType(custType, null, null, false);
		}

		this.removeAllCustomerPass();
		if (!StringUtil.isEmpty(custPass)) {
			this.addCustomerPass(custPass, null, null, null, null, false);
		}

		if (!StringUtil.isEmpty(boatCategory)) {
			this.selectBoatCategory(boatCategory);
		} else
			this.selectBoatCategory(0);
		ajax.waitLoading();
	}

	/**
	 * Get the error message
	 * 
	 * @return error message
	 */
	public String getErrorMessage() {
		IHtmlObject[] obj = browser
				.getHtmlObject(
						".id",
						new RegularExpression(
								"NOTSET|MarinaRuleCheckErrorCode_\\d+|MARINAORDERERRORCODE_\\d+|error.slip.res.det.chg_\\d+",
								false));
		String errorMessage = "";
		if (obj.length > 0) {
			errorMessage = obj[0].getProperty(".text").toString();
		}
		Browser.unregister(obj);
		return errorMessage;
	}

	public String getWarnMessage() {
		return browser.getObjectText(".id", new RegularExpression(
				"error.slip.res.det.chg_\\d+", false));
	}
	
	public boolean isSlipExist(String slip){
		IHtmlObject[] objs = browser.getTableTestObject(".id",
				new RegularExpression("grid_\\d+", false), ".text",
				new RegularExpression("Marina Dock Slip.*", false));
		IHtmlTable grid = (IHtmlTable) objs[objs.length - 1];
		
		boolean result=browser.checkHtmlObjectExists(".class", "Html.A", ".text", slip, grid);
		Browser.unregister(objs);
		return result;
	}
	
	public boolean isNoteAndAlertExist(){
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	public void clickAddNoteAndAlert(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Note/Alert");
	}
	
	public void clickMarinaName(String marinaName){
		browser.clickGuiObject(".class", "Html.A", ".text", marinaName);
	}
	
	public boolean isChangeReservationDatesExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TR", ".text", new RegularExpression( "Change Reservation Dates.*",false));
	}
	
	public boolean isChangeReservationDateReasonExist(){
		return browser.checkHtmlObjectDisplayed( ".id", new RegularExpression( "MarinaOrderView-\\d+\\.profileView.actionReason",false));
	}
	
	public List<String> getChangeDateReasonValue(){
		return browser.getDropdownElements( ".id", new RegularExpression( "MarinaOrderView-\\d+\\.profileView.actionReason",false));
	}
	
	public String[] getSlipInfoForChangeORRenew(){
		IHtmlObject[] objs=browser.getTableTestObject(".text", new RegularExpression( "Slip Reservation #.*Marina.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("Could not find change date section table...");
		}
		
		IHtmlTable table=(IHtmlTable)objs[objs.length-1];
		String[] changeSlip=new String[7];
		changeSlip[0]=table.getCellValue(0, 1).split("Slip Reservation #")[1].trim();//Slip Reservation #
		changeSlip[1]=table.getCellValue(0, 3).split("Marina")[1].trim();//Marina
		changeSlip[2]=table.getCellValue(0, 5).split("Dock/Area")[1].trim();//Dock/Area
		changeSlip[3]=table.getCellValue(0, 7).split("Slip # \\(Name\\)")[1].trim();//Slip # (Name) 
		changeSlip[4]=table.getCellValue(0, 9).split("Arrival")[1].trim();//Arrival
		changeSlip[5]=table.getCellValue(0, 11).split("Departure")[1].trim();//Departure
		changeSlip[6]=table.getCellValue(0, 13).split("Months")[1].trim();//Months
		
		Browser.unregister(objs);
		return changeSlip;
		
	}
	
	public boolean isSlipReservationtoRenewExist(){
		return browser.checkHtmlObjectDisplayed(".class", "Html.TR", ".text", new RegularExpression( "Slip Reservation to Renew.*",false));
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
