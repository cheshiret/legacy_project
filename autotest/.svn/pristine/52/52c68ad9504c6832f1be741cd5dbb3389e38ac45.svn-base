/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.customer;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.CampingUnit;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.BoatInfo;
import com.activenetwork.qa.awo.datacollection.legacy.Customer.VehicleInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.LoyaltyProgram;
import com.activenetwork.qa.awo.datacollection.legacy.orms.ReservationInfo;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.MarinaBoat;
import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnDataException;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.page.ConfirmDialogPage;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;

/**
 * @author jdu
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsCustomerDetailsPage extends OrmsPage {

	/**
	 * Script Name : OrmsCustDetailPage Generated : Feb 9, 2005 2:24:14 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 * 
	 * @since 2005/02/09
	 */
	// public final GuiTestObject pageMark=link_viewUpdateCustomerDetails();
	/**
	 * A handle to the unique Singleton instance.
	 */
	private final String MARINA_BOAT = "Marina Boat";
	static private OrmsCustomerDetailsPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsCustomerDetailsPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsCustomerDetailsPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsCustomerDetailsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		// RegularExpression reg=new
		// RegularExpression(".* (View/Update Customer Details|Add Customer)$",false);
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Zip Code Lookup");
	}

	/** Click on the OK button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/** Click Customer Search */
	public void clickCustomerSearch() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Customer Search/List", true);
	}

	/** Click Customer's Reservation */
	public void clickCustomerReservation() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Customer's Reservations", true);
	}

	/** Click Customer's invoice */
	public void clickCustomerInvoice() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Customer's Invoices", true);
	}

	/** Click Customer's Voucher */
	public void clickCustomerVoucher() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Customer's Vouchers", true);
	}

	/** Click notes&alerts */
	public void clickCustomerNoteAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Notes & Alerts",
				true);
	}

	/** Click Add new customer */
	public void clickAddNewCustomer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add New Customer",
				true);
	}

	/** Click Apply button */
	public void clickApply() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply", true);
	}

	/** Click Cancel button */
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}

	/** Click Reset */
	public void clickReset() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reset", true);
	}

	/** Click Edit Notes/Alerts button */
	public void clickEditNoteAlert() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Edit Notes/Alerts", true);
	}

	/** Click Swap sites button */
	public void clickSwapSites() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Swap Sites", true);
	}

	/**
	 * Set customer information
	 * 
	 * @param customer
	 * */
	public void setCustInfo(Customer cust) {
		this.setCustInfo(cust, false, false);
	}

	/**
	 * Input phone number
	 * 
	 * @param phone
	 */
	public void setPhone(String phone) {
		IHtmlObject[] objs = browser.getTextField(".id", "phone");
		if (objs.length > 0) {
			((IText) objs[0])
					.setText(phone, IText.Event.ONCHANGE);
		} else {
			throw new ItemNotFoundException("Phone text field not found");
		}

		Browser.unregister(objs);
	}

	/**
	 * Input email address
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		IHtmlObject[] objs = browser.getTextField(".id", "email");
		if (objs.length > 0) {
			((IText) objs[0])
					.setText(email, IText.Event.ONCHANGE);
		} else {
			throw new ItemNotFoundException("Phone text field not found");
		}

		Browser.unregister(objs);
	}

	/**
	 * Input Cell Phone
	 * 
	 * @param cellphone
	 */
	public void setCellPhone(String cellphone) {
		browser.setTextField(".id", "cellphone", cellphone, true);
	}

	/**
	 * Input fax number
	 * 
	 * @param fax
	 */
	public void setFax(String fax) {
		browser.setTextField(".id", "fax", fax, true);
	}

	/***
	 * Input work Phone
	 * 
	 * @param workphone
	 */
	public void setWorkPhone(String workphone) {
		browser.setTextField(".id", "workphone", workphone, true);
	}

	/**
	 * Input first name
	 * 
	 * @param fName
	 */
	public void setFirstName(String fName) {
		browser.setTextField(".id", "firstname", fName, true);
	}

	/**
	 * Select dsalutation
	 * 
	 * @param item
	 */
	public void selectSalutation(String item) {
		browser.selectDropdownList(".id", "salutation", item);
	}

	/**
	 * Select middle name
	 * 
	 * @param middlename
	 */
	public void setMiddleName(String middlename) {
		browser.setTextField(".id", "middlename", middlename, true);
	}

	/**
	 * Input Last Name
	 * 
	 * @param lName
	 */
	public void setLastName(String lName) {
		browser.setTextField(".id", "lastname", lName, true);
	}

	/**
	 * Input Letters
	 * 
	 * @param letters
	 */
	public void setLetters(String letters) {
		browser.setTextField(".id", "letters", letters, true);
	}

	/**
	 * Input Zip
	 * 
	 * @param zip
	 */
	public void setZIP(String zip) {
		browser.setTextField(".id", "zip", zip, true);
	}
	
	public String getZIP() {
		return browser.getTextFieldValue(".id", "zip");
	}


	/**
	 * Input Address
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		browser.setTextField(".id", "address", address, true);
	}
	
	public String getAddress() {
		return browser.getTextFieldValue(".id", "address");
	}

	/**
	 * Input city town
	 * 
	 * @param city
	 */
	public void setCityTown(String city) {
		browser.setTextField(".id", "city", city, true);
	}
	
	public String getCityTown() {
		return browser.getTextFieldValue(".id", "city");
	}

	/**
	 * Select state
	 * 
	 * @param state
	 */
	public void selectState(String state) {
		browser.selectDropdownList(".id", "state", state, true);
	}
	
	public String getState() {
		return browser.getDropdownListValue(".id", "state");
	}

	/**
	 * Select country
	 * 
	 * @param country
	 */
	public void selectCountry(String country) {
		browser.selectDropdownList(".id", new RegularExpression("country|CustomerView-\\d+\\.countryCode",false), country);
		// browser.selectDropdownList(".id", "custOrgType", country);
	}
	
	public String getCountry() {
		return browser.getDropdownListValue(".id",  new RegularExpression("country|CustomerView-\\d+\\.countryCode",false));
	}

	/**
	 * Select preference
	 * 
	 * @param preference
	 */
	public void selectPreference(String preference) {
		browser.selectDropdownList(".id", "preference", preference);
	}

	/**
	 * Input organization Name
	 * 
	 * @param orgname
	 */
	public void setOrgName(String orgname) {
		browser.setTextField(".id", "custOrgName", orgname);
	}

	/**
	 * Input Pet information
	 * 
	 * @param name
	 * @param number
	 */
	public void setPets(String name, int number) {
		String id = "petqty_0_60";
		if (name.equalsIgnoreCase("cat"))
			id += "1";
		else if (name.equalsIgnoreCase("dog"))
			id += "0";
		else if (name.equalsIgnoreCase("horse"))
			id += "4";
		else
			id += "3";

		browser.setTextField(".id", id, number + "", true);
	}

	/**
	 * Input Pet Notes
	 * 
	 * @param note
	 */
	public void setPetNote(String note) {
		browser.setTextField(".id", "pet_note", note, true);
	}

	/**
	 * Slect Customer Type In and before orms 285, customer type is provided
	 * with check boxes After orms 285, customer type is a dropdown list
	 * 
	 * @param type
	 */
	public void selectCustomerType(String type) {
		// in 285, customer type is provided with check boxes
		// in 286, customer type is a dropdown list
		String value = "customer_type_" + type;
		if (browser.checkHtmlObjectExists(".id", value, ".class",
				"Html.INPUT.checkbox")) {
			browser.selectCheckBox(".id", value);
		} else {
			if (browser.getHtmlObject(".id", "customerType").length <= 1) {
				clickAddCustomerType();
			}
			browser.selectDropdownList(".id", "customerType", type);
		}
	}

	/**
	 * Click AddCustomerType button This button becomes available after Orms 286
	 */
	public void clickAddCustomerType() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						new RegularExpression("Add Customer Type", false));
	}

	/**
	 * UnSelect Customer Type
	 * 
	 * @param type
	 */
	public void unSelectCustomerType(String type) {
		String value = "customer_type_" + type;
		browser.unSelectCheckBox(".id", value);
	}

	/**
	 * Select customer pass
	 * 
	 * @param pass
	 */
	public void selectCustomerPass(String pass) {
		if (browser.getHtmlObject(".id", "passTypeId").length <= 1) {
			clickAddCustomerPass();
		}
		browser.selectDropdownList(".id", "passTypeId", pass);
	}

	/**
	 * Click Add Customer Pass button
	 */
	public void clickAddCustomerPass() {
		browser
				.clickGuiObject(".class", "Html.A", ".text",
						"Add Customer Pass");
	}

	public String getSelectedCampingUnitType(int index) {
		return browser.getDropdownListValue(".id",  new RegularExpression("CampingUnitVesselInfo-\\d+\\.equipmentView",false), 0);
	}

	public void selectCampingUnitType(String unitType, int index) {
		browser.selectDropdownList(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.equipmentView",false), unitType, index);
	}

	public int getNumberOfCampingUnit() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SELECT",
				".id",  new RegularExpression("CampingUnitVesselInfo-\\d+\\.equipmentView",false));

		int num = objs.length;
		Browser.unregister(objs);
		return num;
	}
	
	public void setLengthBoxValue(String length, int index) {
		browser.setTextField(".id",  new RegularExpression("CampingUnitVesselInfo-\\d+\\.length",false), length, index);
	}

	public String getLength(int index) {
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.length",false));
		String length = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return length;
	}

	public void setQtyBoxValue(String qtyNum, int index) {
		browser.setTextField(".id",  new RegularExpression("CampingUnitVesselInfo-\\d+\\.qty",false), qtyNum, index);
	}

	public String getQtyValue(int index) {
		IHtmlObject[] objs = browser.getTextField(".id",  new RegularExpression("CampingUnitVesselInfo-\\d+\\.qty",false));
		String qty = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return qty;
	}

	public void setPlateBoxValue(String plateValue, int index) {
		browser.setTextField(".id",new RegularExpression("CampingUnitVesselInfo-\\d+\\.license",false), plateValue, index);
	}

	public String getPlate(int index) {
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("(CampingUnitVesselInfo-\\d+\\.license)|(^license$)",false));
		String plate = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return plate;
	}

	public void selectStateValue(String state, int index) {
		browser.selectDropdownList(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.stateId", false), state, index);//camping_unit_state
	}

	public String getSelectedState(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("(CampingUnitVesselInfo-\\d+\\.stateId)|(^vstate$)", false), index);//camping_unit_state
	}

	public void selectMakeValue(String make, int index) {
		browser.selectDropdownList(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.manufacturer", false), make, index);//camping_unit_make
	}

	public String getSelectedMake(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("(CampingUnitVesselInfo-\\d+\\.manufacturer)|(make)", false), index);//camping_unit_make
	}

	public void setModelValue(String model, int index) {
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.model", false), model, index);//camping_unit_model
	}

	public String getModelValue(int index) {
		IHtmlObject[] objs = browser.getTextField(".id", new RegularExpression("(CampingUnitVesselInfo-\\d+\\.model)|(model)", false));//camping_unit_model
		String model = ((IText) objs[index]).getText();
		Browser.unregister(objs);
		return model;
	}

	public void selectColorValue(String color, int index) {
		browser.selectDropdownList(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.color", false), color, index);//camping_unit_color
	}

	public String getSelectedColor(int index) {
		return browser.getDropdownListValue(".id", new RegularExpression("(CampingUnitVesselInfo-\\d+\\.color)|(color)", false), index);//camping_unit_color
	}

	public void removeCampingUnit(int index) {
		System.out.println("len:"+browser.getHtmlObject(".class", "Html.A", ".text", "Remove").length);
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index+1);
	}

	public boolean checkConfirmationMethod() {
		return browser.checkHtmlObjectExists(".id", "NotificationMethod");
	}

	public boolean checkPhoneContact() {
		return browser.checkHtmlObjectExists(".id", "PhoneContactPref");
	}

	public boolean checkContactTime() {
		return browser.checkHtmlObjectExists(".id", "PrefContactTime");
	}

	public void selectConfirmationMethodValue(String method) {
		browser.selectDropdownList(".id", "NotificationMethod", method);
	}

	public void selectPhoneContactValue(String phone) {
		browser.selectDropdownList(".id", "PhoneContactPref", phone);
	}

	public void selectContactTimeValue(String time) {
		browser.selectDropdownList(".id", "PrefContactTime", time);
	}

	public String getConfirmationMethodValue() {
		String confirmValue = browser.getDropdownListValue("id",
				"NotificationMethod", 0);
		return confirmValue;
	}

	public String getPhoneContactValue() {
		String phoneValue = browser.getDropdownListValue("id",
				"PhoneContactPref", 0);
		return phoneValue;
	}

	public String getContactTimeValue() {
		String timeValue = browser.getDropdownListValue("id",
				"PrefContactTime", 0);
		return timeValue;
	}

	public String getCreateSuccMess() {
		IHtmlObject[] success = browser.getHtmlObject(".className", new RegularExpression("message msg.*",false));
		String succMessage = success[0].getProperty(".text");
		return succMessage;
	}

	public List<String> getConfirmationMethod() {
		List<String> dropdown = browser.getDropdownElements("id",
				"NotificationMethod");
		return dropdown;
	}

	public List<String> getContactTime() {
		List<String> dropdown = browser.getDropdownElements("id",
				"PrefContactTime");
		return dropdown;
	}

	public List<String> getPhoneContact() {
		List<String> dropdown = browser.getDropdownElements("id",
				"PhoneContactPref");
		return dropdown;
	}

	public IHtmlTable getMandatoryErrorMess() {
		IHtmlObject[] error = browser.getTableTestObject(".text",
				new RegularExpression("Home Phone is required.*", false));
		IHtmlTable errorTable = (IHtmlTable) error[0];
		return errorTable;
	}

	/**
	 * This method used to set all camping unit info
	 * 
	 * @param res
	 * @param index
	 */
	public void setAllCampingUnitValue(ReservationInfo res, int index) {
		this.selectCampingUnitType(res.site.validCampingUnit, index);
		ajax.waitLoading();
		if (res.site.vehicleLenth != null && res.site.vehicleLenth.length() > 0) {
			this.setLengthBoxValue(new Integer(res.site.vehicleLenth)
					.toString(), index);
		}
		if (res.site.validCampingUnitQty > 0) {
			this.setQtyBoxValue(Integer.valueOf(res.site.validCampingUnitQty)
					.toString(), index);
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
		if (null != res.site.unitColor && res.site.unitColor.length() > 0) {
			this.selectColorValue(res.site.unitColor, index);
		}
	}

	/**
	 * This method used to verify camping unit info is the same with given info
	 * 
	 * @param res
	 * @param index
	 */
	public void verifyCampingEquipmentInfoCorrect(ReservationInfo res, int index) {
		String value = "";
		logger.info("Verify Camping Equipmen Info Correct.");

		value = this.getSelectedCampingUnitType(index);
		if (!res.site.validCampingUnit.equalsIgnoreCase(value)) {
			throw new ErrorOnDataException("Camping Unit Type Not Correct.", res.site.validCampingUnit, value);
		}
		
		if (res.site.vehicleLenth != null && res.site.vehicleLenth.length() > 0) {
			value = this.getLength(index);
			if (!res.site.vehicleLenth.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException("Camping Unit Length Not Correct.", res.site.vehicleLenth, value);
			}
		}
		if (res.site.validCampingUnitQty > 0) {
			value = this.getQtyValue(index);
			if (!String.valueOf(res.site.validCampingUnitQty).equalsIgnoreCase(value)) {
				throw new ErrorOnDataException("Camping Unit Qty Not Correct.", String.valueOf(res.site.validCampingUnitQty) , value);
			}
		}
	}
	
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
			value = this.getModelValue(1);
			if (!res.site.vehicleModel.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException(
						"Camping Unit Model Not Correct.", res.site.vehicleModel, value);
			}
		}
		if (null != res.site.unitColor && res.site.unitColor.length() > 0) {
			value = this.getSelectedColor(1);
			if (!res.site.unitColor.equalsIgnoreCase(value)) {
				throw new ErrorOnDataException(
						"Camping Unit Color Not Correct.", res.site.unitColor, value);
			}
		}
	}

	/**
	 * Select promotion type
	 * 
	 * @param type
	 * @param byEmail
	 * @param byNewsLetter
	 * @throws ItemNotFoundException
	 */
	public void selectpromotiontype(String type, boolean byEmail,
			boolean byNewsLetter) throws ItemNotFoundException {
		String typeCode = "";
		if (type.equalsIgnoreCase("Reserve America"))
			typeCode = "6501";
		else if (type.equalsIgnoreCase("Reserve USA"))
			typeCode = "6502";
		else if (type.equalsIgnoreCase("SC"))
			typeCode = "6503";
		else if (type.equalsIgnoreCase("CO"))
			typeCode = "6504";
		else if (type.equalsIgnoreCase("WI"))
			typeCode = "6505";
		else if (type.equalsIgnoreCase("KY"))
			typeCode = "6506";
		else if (type.equalsIgnoreCase("NRSO"))
			typeCode = "6507";
		else if (type.equalsIgnoreCase("MS"))
			typeCode = "6508";
		else if (type.equalsIgnoreCase("ELS2"))
			typeCode = "6509";
		else if (type.equalsIgnoreCase("CUSTSURVEY"))
			typeCode = "6510";
		else if (type.equalsIgnoreCase("RI"))
			typeCode = "6511";
		else if (type.equalsIgnoreCase("NY"))
			typeCode = "6512";
		else if (type.equalsIgnoreCase("CA"))
			typeCode = "6513";
		else if (type.equalsIgnoreCase("NE"))
			typeCode = "6514";
		else if (type.equalsIgnoreCase("NH"))
			typeCode = "6515";
		else if (type.equalsIgnoreCase("BWCAW"))
			typeCode = "6516";
		else if (type.equalsIgnoreCase("NUEVO"))
			typeCode = "6517";
		else if (type.equalsIgnoreCase("LAM"))
			typeCode = "6518";
		else if (type.equalsIgnoreCase("LAMA"))
			typeCode = "6119";
		else if (type.equalsIgnoreCase("VA"))
			typeCode = "6520";
		else if (type.equalsIgnoreCase("ID"))
			typeCode = "6521";
		else
			throw new ItemNotFoundException("Unknown promotion type: " + type);

		String promoType = "promotion_" + typeCode;
		browser.selectCheckBox(".id", promoType);

		if (byEmail)
			browser.selectCheckBox(".id", "promotion_email_" + typeCode);

		if (byNewsLetter)
			browser.selectCheckBox(".id", "promotion_newletter_" + typeCode);

	}

	/**
	 * Set the textfields found in the Customer Search form (using the passed-in
	 * parameters)
	 */
	public void setCustInfo(Customer cust, boolean viewByPhone,
			boolean viewByEmail) throws PageNotFoundException {
		ConfirmDialogPage confirm = ConfirmDialogPage.getInstance();

		if (cust.hPhone != null && cust.hPhone.length() > 0) {
			this.setPhone(cust.hPhone);
			confirm.setDismissMethod(viewByPhone);
			browser.waitExists(this, confirm);
		}

		if (cust.email != null && cust.email.length() > 0) {
			this.setEmail(cust.email);
			confirm.setDismissMethod(viewByEmail);
			browser.waitExists(confirm, this);
		}

		if (cust.mPhone != null && cust.mPhone.length() > 0)
			this.setCellPhone(cust.mPhone);

		if (cust.bPhone != null && cust.bPhone.length() > 0)
			this.setWorkPhone(cust.bPhone);

		if (cust.fax != null && cust.fax.length() > 0)
			this.setFax(cust.fax);

		if (cust.salutation != null && cust.salutation.length() > 0)
			this.selectSalutation(cust.salutation);

		if (cust.fName != null && cust.fName.length() > 0)
			this.setFirstName(cust.fName);

		if (cust.mName != null && cust.mName.length() > 0)
			this.setMiddleName(cust.mName);

		if (cust.lName != null && cust.lName.length() > 0)
			this.setLastName(cust.lName);

		if (cust.letters != null && cust.letters.length() > 0)
			this.setLetters(cust.letters);

		if (cust.organization != null && cust.organization.length() > 0)
			this.setOrgName(cust.organization);
		if(StringUtil.notEmpty(cust.organizationType)){
			this.selectOrgnizationType(cust.organizationType);
		}

		if (cust.address != null && cust.address.length() > 0)
			this.setAddress(cust.address);

		if (cust.city != null && cust.city.length() > 0)
			this.setCityTown(cust.city);

		if (cust.country != null && cust.country.length() > 0){
			this.selectCountry(cust.country);
			ajax.waitLoading();
		}
		
		if (cust.state != null && cust.state.length() > 0)
			this.selectState(cust.state);

		if (cust.zip != null && cust.zip.length() > 0)
			this.setZIP(cust.zip);

		if (isConfirmationMethodListExisted() && cust.confirmationCus != null && cust.confirmationCus.length() > 0)
			this.selectConfirmationMethodValue(cust.confirmationCus);

		if (cust.phoneContact != null && cust.phoneContact.length() > 0)
			this.selectPhoneContactValue(cust.phoneContact);

		if (cust.contactTime != null && cust.contactTime.length() > 0)
			this.selectContactTimeValue(cust.contactTime);

		if (cust.isMulti) {
			IHtmlObject[] typeDropDown = browser.getHtmlObject(".id",
					"customerType");
			int typeLength = cust.custType.length;
			int dropLength = typeDropDown.length;

			if (typeLength > dropLength - 1) {
				for (int i = 0; i < typeLength + 1 - dropLength; i++) {
					browser
							.clickGuiObject(".class", "Html.A", ".text",
									new RegularExpression(
											"Add Customer Type", false));
				}
			} else if (typeLength < dropLength - 1) {
				for (int i = 0; i < dropLength - 1 - typeLength; i++) {
					// 1 hiden remove button, so click starting from 1
					browser.clickGuiObject(".class", "Html.A", ".text",
							new RegularExpression("Remove Customer Type",
									false), 1);
				}
			}
			// fill in the customer type after the given type number and
			// dropdown list fields number equal
			for (int j = 0; j < cust.custType.length; j++) {
				browser.selectDropdownList(".id", "customerType",
						cust.custType[j], j + 1);
			}
		} else {
			if (cust.type != null && cust.type.length() > 0) {
				if (this.getQtyOfRemoveCustomerType() < 2) {
					this.clickAddCustomerType();
				}
				browser.selectDropdownList(".id", "customerType", cust.type, 1);
			}
		}

		if (cust.pass != null && cust.pass.length() > 0) {
			if (this.getQtyOfRemoveCustomerPass() < 2) {
				browser.clickGuiObject(".class", "Html.A", ".text",
						new RegularExpression("Add Customer Pass", false));
			}
			browser.selectDropdownList(".id", "passTypeId", cust.pass, 1);
			ajax.waitLoading();
			
			if (cust.passNumber != null && !"".equals(cust.passNumber)) {
				browser.setTextField(".id", "passNumber", cust.passNumber, 1);//Quentin[20140626]
//				browser.setTextField(".id", "passNumber", cust.passNumber, 0);//Quentin[20131030] ui changes
			}
			if (!StringUtil.isEmpty(cust.passHolderName)) {
				browser.setTextField(".id", new RegularExpression("holderName", false),
						cust.passHolderName, 1);
			}
			if (cust.passExpiryDate != null && !"".equals(cust.passExpiryDate)) {
//				browser.setTextField(".id", new RegularExpression("expiryDate(\\d)?_ForDisplay", false),
//						cust.passExpiryDate, 1);
//				browser.setTextField(".id", new RegularExpression("expiryDate(\\d)?_ForDisplay", false),
//						cust.passExpiryDate, 0);//Quentin[20131030] ui changes
				browser.setTextField(".name", new RegularExpression("expiryDate(\\d)?_ForDisplay", false), cust.passExpiryDate, 1);//Quentin[20140626] ui changes
			}
		}
		if(null !=cust.campingUnitList && cust.campingUnitList.size()>0){
			this.setCustCampingUnit(cust.campingUnitList);
		} else {
			List<MarinaBoat> boats = getCampingUnitMarinaBoatInfos();
			if(boats.size() == 1 && boats.get(0).getBoatName().equalsIgnoreCase(StringUtil.EMPTY)) {
				this.removeAllCampingUnit();
			}
		}
		/*if(null !=cust.campingUnitList && cust.campingUnitList.size()>0){
			for(int i=0;i<cust.campingUnitList.size();i++){
				 this.clickAddCampingUnit();
				 this.selectCampingUnitType(cust.campingUnitList.get(i).typeOfUnit, i);	
				 ajax.waitLoading();
				if(null != cust.campingUnitList.get(i) && cust.campingUnitList.get(i).typeOfUnit.equals(MARINA_BOAT)){
				   this.setCampingUnitMarinaBoatInfo(cust.campingUnitList.get(i).marinaBoat, i);
				}else{
					this.setQty(cust.campingUnitList.get(i).quantity, i);
				}
			}
		}*/

		if (browser
				.checkHtmlObjectExists(
						".class",
						"Html.TABLE",
						".text",
						new RegularExpression(
								"^Promotions.+Promotion Type.+By Email.+By News Letter.*",
								false))) {
			if (cust.promType != "" && cust.promType != null)
				this.selectpromotiontype(cust.promType, cust.byEmail,
						cust.byNewsLetter);
		}
		
		// set up pet number.
		if(cust.numberOfDog > 0){
			this.setNumberOfPet("Dog", cust.numberOfDog.toString());
		}

		if(cust.numberOfCat > 0){
			this.setNumberOfPet("Cat", cust.numberOfCat.toString());
		}
		
		if(cust.numberOfHorse > 0){
			this.setNumberOfPet("Horse", cust.numberOfHorse.toString());
		}
		
		if(cust.numberOfOther > 0){
			this.setNumberOfPet("Other", cust.numberOfOther.toString());
		}

		if(!StringUtil.isEmpty(cust.petNotes)){
			this.setNumberOfPet("Pet Notes", cust.petNotes);
		}
		
		// set up vehicle plate number
		if(null != cust.vehicleInfo && cust.vehicleInfo.size()>0){
			for(int i=0; i<cust.vehicleInfo.size(); i++){
				this.clickAddVehicle();
				this.setVehiclePlate(i+1, cust.vehicleInfo.get(i).getPlateNum());
				this.setVehicleState(i+1, cust.vehicleInfo.get(i).getState());
				this.setVehicleMake(i+1, cust.vehicleInfo.get(i).getMake());
				this.setVehicleModel(i+1, cust.vehicleInfo.get(i).getModel());
				this.setVehicleColor(i+1, cust.vehicleInfo.get(i).getColor());
			}
		}
	}

	/**
	 * @param organizationType
	 */
	public void selectOrgnizationType(String organizationType) {
		browser.selectDropdownList(".id","custOrgType", organizationType);
		
	}

	/**
	 * Set number of pets.
	 * @param name like Dog, Cat, Horse, Pet Notes
	 * @param number
	 */
	public void setNumberOfPet(String name, String number){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD", ".text", name);
		browser.setTextField(".id", new RegularExpression("", false), number, objs[0]);
	}

	public int getQtyOfRemoveCustomerType() {
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.A", ".text",
				new RegularExpression("Remove Customer Type", false));
		int length = obj.length;
		Browser.unregister(obj);
		return length;
	}

	public int getQtyOfRemoveCustomerPass() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
//				new RegularExpression("^\\W?Remove Customer Pass", false));
		new RegularExpression("Remove Customer Pass", false));
		int length = objs.length;
		Browser.unregister(objs);
		return length;
	}



	public String getHomePhone() {
		return browser.getTextFieldValue(".id", "phone");
	}
	
	private void setBoatName(String boatName,int index){
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.boatName",false), boatName, index);
	}
	
	public String getBoatName(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.boatName",false), index);
	}
	
	private void setBoatRegistrationNum(String regisNum,int index){
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.registrationNumber",false), regisNum, index);
	}
	
	public String getBoatRegistrationNum(int index){
		return browser.getTextFieldValue(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.registrationNumber",false), index);
	}
	
	public String getBoatType(int index){
		return browser.getDropdownListValue(".id",new RegularExpression("CampingUnitVesselInfo-\\d+\\.vesselType",false), index);
	}
	
	public String getBoatLength(int index){
		return browser.getTextFieldValue(".id",new RegularExpression("CampingUnitVesselInfo-\\d+\\.vesselLength",false), index);
	}
	
	public String getBoatWidth(int index){
		return browser.getTextFieldValue(".id",new RegularExpression("CampingUnitVesselInfo-\\d+\\.vesselWidth",false), index);
	}
	
	public String getBoatDepth(int index){
		return browser.getTextFieldValue(".id",new RegularExpression("CampingUnitVesselInfo-\\d+\\.vesselDepth",false), index);
	}
	
	private void setYear(String year,int index){
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.year",false),year,index);
	}
	
	public void setCampingUnitMarinaBoatInfo(MarinaBoat boat,int index){
		if(!StringUtil.isEmpty(boat.getBoatName())){
			this.setBoatName(boat.getBoatName(), index);
		}
		if(!StringUtil.isEmpty(boat.getRegistrationNum())){
			this.setBoatRegistrationNum(boat.getRegistrationNum(), index);
		}
		if(!StringUtil.isEmpty(boat.getYear())){
			this.setYear(boat.getYear(), index);
		}
	}
	
	public List<MarinaBoat> getCampingUnitMarinaBoatInfos(){
		List<MarinaBoat> boats = new ArrayList<MarinaBoat>();
		
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("CampingUnitVesselInfo_\\d+", false));
		
		for(int i=0;i<objs.length;i++){
			MarinaBoat boat = new MarinaBoat();
			boat.setBoatName(getBoatName(i));
			boat.setBoatType(getBoatType(i));
			boat.setLength(getBoatLength(i));
			boat.setWidth(getBoatWidth(i));
			boat.setDepth(getBoatDepth(i));
			boat.setRegistrationNum(getBoatRegistrationNum(i));
			boats.add(boat);			
		}
		Browser.unregister(objs);
		return boats;
	}
	/**
	 * get error message.
	 * @return
	 */
	public String getErrorMsg(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN", ".className", "message msgerror");
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		String errorMsg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return errorMsg;
	}
	/**
	 * click add camping unit.
	 */
	public void clickAddCampingUnit(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("Camping Unit/Equipment.*",false));
		if(objs.length<1){
			throw new ErrorOnPageException("No element exist");
		}
		//browser.getHtmlObject(.class", "Html.A", ".text", ".Add", top)
		browser.clickGuiObject(".class", "Html.A", ".text", "Add",true,0,objs[0]);
		ajax.waitLoading();
		Browser.unregister(objs);
	}
	
	/**
	 * set qty
	 * @param qty
	 * @param index
	 */
	public void setQty(String qty,int index){
		browser.setTextField(".id", new RegularExpression("CampingUnitVesselInfo-\\d+\\.qty", false), qty, index);
	}
	/**
	 * check camping unit info table exist or not.
	 * @return
	 */
	public boolean checkCampingUnitInfoTableExist(){
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text", new RegularExpression("Type of UnitTent.*",false));
	}
	/**
	 * verify camping unit info not exist.
	 */
	public void verifyCampingUnitInfoNotExist(){
		boolean isExist = this.checkCampingUnitInfoTableExist();
		if(isExist){
			throw new ErrorOnPageException("Capming Unit info should not exist");
		}
	}
	
	/**
	 * verify camping unit info exist.
	 */
	public void verifyCampingUnitInfoExist(){
		boolean isExist = this.checkCampingUnitInfoTableExist();
		if(!isExist){
			throw new ErrorOnPageException("Capming Unit info should exist");
		}
	}
	/**
	 * set customer camping unit.
	 * @param campingUnitList.
	 */
	public void setCustCampingUnit(List<CampingUnit> campingUnitList){
		this.removeAllCampingUnit();
	  for(int i=0;i<campingUnitList.size();i++){
			this.clickAddCampingUnit();
			this.selectCampingUnitType(campingUnitList.get(i).typeOfUnit, i);	
			ajax.waitLoading();
			if(null != campingUnitList.get(i) && campingUnitList.get(i).typeOfUnit.equals(MARINA_BOAT)){
				this.setCampingUnitMarinaBoatInfo(campingUnitList.get(i).marinaBoat, i);
			}else{
				this.setQty(campingUnitList.get(i).quantity, i);
			}
		}
	}
	
	public void removeAllCampingUnit(){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text", "Remove");
		for(int i=0; i<objs.length; i++){
			browser.clickGuiObject(".class", "Html.A", ".text", "Remove");
			ajax.waitLoading();
			this.waitLoading();
		}
	}
	/**
	 * update customer camping unit.
	 * @param campingUnitList
	 */
	public void updateCustCampingUnit(List<CampingUnit> campingUnitList){
		 for(int i=0;i<campingUnitList.size();i++){
				this.selectCampingUnitType(campingUnitList.get(i).typeOfUnit, i);	
				ajax.waitLoading();
				if(null != campingUnitList.get(i) && campingUnitList.get(i).typeOfUnit.equals(MARINA_BOAT)){
					this.setCampingUnitMarinaBoatInfo(campingUnitList.get(i).marinaBoat, i);
				}else{
					this.setQty(campingUnitList.get(i).quantity, i);
				}
			}
	}
	
	public void clickAddVehicle(){
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Add Vehicle", false), true);
	}
	
	public void clickRemoveVehicle(int index){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("Vehicles.*",false));
		browser.clickGuiObject(".class", "Html.A", ".text", new RegularExpression("Remove", false), true, index, objs[0]);
	}
	
	public void setVehiclePlate(int index, String plateNum){
		browser.setTextField(".id", "license", plateNum, true, index);
	}
	
	public void setVehicleState(int index, String state){
		if(StringUtil.isEmpty(state)){
			browser.selectDropdownList(".id", "vstate", 0, index);
		} else {
			browser.selectDropdownList(".id", "vstate", state, index);
		}
	}
	
	public void setVehicleMake(int index, String make){
		if(StringUtil.isEmpty(make)){
			browser.selectDropdownList(".id", "make", 0, index);
		} else {
			browser.selectDropdownList(".id", "make", make, index);
		}
	}
	
	public void setVehicleModel(int index, String model){
		browser.setTextField(".id", "model", model, true, index);
	}
	
	public void setVehicleColor(int index, String color){
		if(StringUtil.isEmpty(color)){
			browser.selectDropdownList(".id", "color", 0, index);
		} else {
			browser.selectDropdownList(".id", "color", color, index);
		}
	}
	
	public List<VehicleInfo> getVehicleInfos(){
		IHtmlObject[] objs = browser.getTableTestObject(".id","VehicleList");
		List<VehicleInfo> infos = new ArrayList<VehicleInfo>();
		IHtmlTable grid = (IHtmlTable)objs[0];
		
		for(int i=0;i<grid.rowCount()-3;i++){
			VehicleInfo vehicle = new Customer().new VehicleInfo();
			
			vehicle.setPlateNum(getPlate(i+1));
			vehicle.setState(getSelectedState(i+1));
			vehicle.setMake(getSelectedMake(i+1));
			vehicle.setModel(getModelValue(i+1));
			vehicle.setColor(getSelectedColor(i+1));
			
			infos.add(vehicle);
		}
		Browser.unregister(objs);
		return infos;
		
	}
	
	public String getFirstName(){
		return browser.getTextFieldValue(".id", "firstname");
	}
	
	public String getLastName(){
		return browser.getTextFieldValue(".id", "lastname");
	}
	
	public String getEmailAddress(){
		return browser.getTextFieldValue(".id", "email");
	}
	
	public String getCustOrganizationName(){
		return browser.getTextFieldValue(".id", "custOrgName");
	}
	
	public Customer getCustomerInfo() {
		Customer cust = new Customer();
		
		//cust basic info
		cust.fName = this.getFirstName().trim();
		cust.lName = this.getLastName().trim();
		cust.hPhone = this.getHomePhone().trim();
		cust.email = this.getEmailAddress().trim();
		cust.organization = getCustOrganizationName().trim();
		cust.phoneContact = this.getPhoneContactValue().trim();
		cust.contactTime = this.getContactTimeValue().trim();
		//cust address info 
		cust.address = this.getAddress().trim();
		cust.city = this.getCityTown().trim();
		cust.zip = this.getZIP().trim();
		cust.state = this.getState().trim();
		cust.country = this.getCountry().trim();
		return cust;
	}
	
	
	private Property[] customerPassLoyaltyProgram() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Customer Pass/Loyalty Program");
	}
	
	private Property[] enrollCustomer() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Enroll Customer");
	}
	
	private Property[] replaceCard() {
		return Property.toPropertyArray(".class", "Html.A", ".text", "Replace Card");
	}
	
	public boolean isCustomerPassLoyaltyProgramBtnExists() {
		return browser.checkHtmlObjectExists(customerPassLoyaltyProgram());
	}
	
	public void verifyCustomerPassLoyaltyProgramBtnExists() {
		if(!isCustomerPassLoyaltyProgramBtnExists()) throw new ErrorOnPageException("'Customer Pass/Loyalty Program' button shall exist.");
		logger.info("'Customer Pass/Loyalty Program' button exists.");
	}
	
	public void clickCustomerPassLoyaltyProgram() {
		browser.clickGuiObject(customerPassLoyaltyProgram());
	}
	
	public void clickEnrollCustomer() {
		browser.clickGuiObject(enrollCustomer());
	}
	
	public void clickReplaceCard(String name) {
		IHtmlObject objs[] = this.getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int rowIndex = table.findRow(table.findColumn(0, PROGRAM_NAME_COL), name);
		
		browser.clickGuiObject(replaceCard(), rowIndex - 1);
	}
	
	private Property[] loyaltyProgramTable() {
		return Property.toPropertyArray(".class", "Html.TABLE", ".text", new RegularExpression("^Program Name", false));
	}
	
	private IHtmlObject[] getLoyaltyProgramTableObject() {
		IHtmlObject objs[] = browser.getTableTestObject(loyaltyProgramTable());
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Loyalty Program section table object.");
		
		return objs;
	}
	
	private static final String PROGRAM_NAME_COL = "Program Name";
	private static final String PENDING_POINTS_COL = "Pending Points";
	private static final String EARNED_POINTS_COL = "Earned Points";
	private static final String NOTREDEEMABLE_COL = "NotRedeemable";
	private static final String CARD_NUMBER_COL = "Card Number";
	
	public List<LoyaltyProgram> getLoyaltyProgramsInfo() {
		IHtmlObject objs[] = getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		boolean isNonRedeemnalePointTypeColExist = table.findColumn(0, NOTREDEEMABLE_COL) > 0;
		List<LoyaltyProgram> programs = new ArrayList<LoyaltyProgram>();
		for(int i = 1; i < table.rowCount(); i ++) {
			List<String> values = table.getRowValues(i);
			
			LoyaltyProgram program = new LoyaltyProgram();
			program.setName(values.get(table.findColumn(0, PROGRAM_NAME_COL)));
			program.setPendingPoints(Integer.parseInt(values.get(table.findColumn(0, PENDING_POINTS_COL))));
			program.setEarnedPoints(Integer.parseInt(values.get(table.findColumn(0, EARNED_POINTS_COL))));
			if(isNonRedeemnalePointTypeColExist) {
				//TODO
			}
			program.setCardNumber(values.get(table.findColumn(0, CARD_NUMBER_COL)));
			
			programs.add(program);
		}
		
		return programs;
	}
	
	/**
	 * the loyalty program is unique within Contract,
	 * if try to create a duplicate name lp even with different location, system will blocks by displaying error msg:
	 * The entered Loyalty program Name already exists in the system. Please specify another Loyalty program Name.
	 * @param name
	 * @return
	 */
	public LoyaltyProgram getLoyaltyProgramInfo(String name) {
		IHtmlObject objs[] = getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		boolean isNonRedeemnalePointTypeColExist = table.findColumn(0, NOTREDEEMABLE_COL) > 0;
		int row = table.findRow(table.findColumn(0, PROGRAM_NAME_COL), name);
		List<String> values = table.getRowValues(row);
		LoyaltyProgram lp = new LoyaltyProgram();
		lp.setName(values.get(table.findColumn(0, PROGRAM_NAME_COL)));
		lp.setPendingPoints(Integer.parseInt(values.get(table.findColumn(0, PENDING_POINTS_COL))));
		lp.setEarnedPoints(Integer.parseInt(values.get(table.findColumn(0, EARNED_POINTS_COL))));
		if(isNonRedeemnalePointTypeColExist) {
			//TODO
		}
		lp.setCardNumber(values.get(table.findColumn(0, CARD_NUMBER_COL)));
		
		Browser.unregister(objs);
		return lp;
	}
	
	public boolean compareLoyaltyProgramInfo(LoyaltyProgram expected) {
		return compareLoyaltyProgramInfo(expected, false);
	}
	
	public boolean compareLoyaltyProgramInfo(LoyaltyProgram expected, boolean ignorePendingPoints) {
		LoyaltyProgram actual = this.getLoyaltyProgramInfo(expected.getName());
		
		boolean result = true;
		result &= MiscFunctions.compareResult(PROGRAM_NAME_COL, expected.getName(), actual.getName());
		if(!ignorePendingPoints) {
			result &= MiscFunctions.compareResult(PENDING_POINTS_COL, expected.getPendingPoints(), actual.getPendingPoints());
		}
		result &= MiscFunctions.compareResult(EARNED_POINTS_COL, expected.getEarnedPoints(), actual.getEarnedPoints());
		//TODO non-redeemable point type
		result &= MiscFunctions.compareResult(CARD_NUMBER_COL, expected.getCardNumber(), actual.getCardNumber());
		
		return result;
	}
	
	public void verifyLoyaltyProgramInfo(LoyaltyProgram expected) {
		verifyLoyaltyProgramInfo(expected, false);
	}
	
	public void verifyLoyaltyProgramInfo(LoyaltyProgram expected, boolean ignorePendingPoints) {
		if(!compareLoyaltyProgramInfo(expected, ignorePendingPoints)) throw new ErrorOnPageException("Loyalty program info is NOT correct.");
		logger.info("Loyalty program is correct.");
	}
	
	public int getNotRedeemablePoints(String program) {
		IHtmlObject objs[] = this.getLoyaltyProgramTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		
		int col = table.findColumn(0, PROGRAM_NAME_COL);
		int notRedeemableCol = table.findColumn(0, NOTREDEEMABLE_COL);
		int row = table.findRow(col, program);
		String points = table.getCellValue(row, notRedeemableCol);
		
		Browser.unregister(objs);
		return Integer.parseInt(points);
	}
	
	private Property[] loyaltyProgramTR(String programName) {
		return Property.concatPropertyArray(tr(), ".text", new RegularExpression("^" + programName, false));
	}
	
	private Property[] points() {
		return Property.concatPropertyArray(a(), ".text", new RegularExpression("\\d+", false));
	}
	
	private void clickPointsLink(int index, IHtmlObject obj) {
		browser.clickGuiObject(points(), true, index, obj);
	}
	
	public void clickNotRedemablePoints(String program) {
		IHtmlObject objs[] = this.getLoyaltyProgramTableObject();
		objs = browser.getHtmlObject(loyaltyProgramTR(program), objs[0]);
		if(objs.length < 1) throw new ItemNotFoundException("Cannot find Loyalty Program section TD by name: " + program);
		
		this.clickPointsLink(1, objs[0]);//0 - Pending Points, 1 - Earned Points, 2 - NotRedeemable
		Browser.unregister(objs);
	}
	
	public boolean isConfirmationMethodListExisted() {
		return browser.checkHtmlObjectDisplayed(".id", "NotificationMethod");
	}
}
