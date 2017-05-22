package com.activenetwork.qa.awo.pages.web.uwp;

import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.web.OrderDetails;
import com.activenetwork.qa.awo.pages.UwpPage;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.util.RegularExpression;

public class UwpCampingLotteryDetailsPage2  extends UwpPage {

	private static UwpCampingLotteryDetailsPage2 _instance = null;

	public static UwpCampingLotteryDetailsPage2 getInstance() {
		if (null == _instance)
			_instance = new UwpCampingLotteryDetailsPage2();

		return _instance;
	}

	public UwpCampingLotteryDetailsPage2() {
	}

	public boolean exists() {
		return browser.checkHtmlObjectExists(".id", "backbutton");
	}
	
	/**
	 * Click on Continue to Shopping Cart button.
	 */
	public void clickContinueToShoppingCart() {
		browser.clickGuiObject(".id", "continueshop");
	}
	
	/**
	 * Click on Back button.
	 */
	public void clickBack() {
		browser.clickGuiObject(".id", "backbutton");
	}
	
	/**
	 * Select the equipment type.
	 * @param equi - equipment type
	 */
	public void selectEquipment(String equi) {
		browser.selectDropdownList(".id", "primaryequipmentid0", equi);
	}
	
	/**
	 * Set the Vehicle length.
	 * @param length - length of Vehicle
	 */
	public void setVehicleLength(String length) {
		browser.setTextField(".id", "equipmentlengthid0", length);
	}
	
	/**
	 * Fill the first occupant number.
	 * @param num - number of occupant
	 */
	public void setNumOfOccupants(String num) {
		browser.setTextField(".id", new RegularExpression("numberofoccupantsid0|numoccupants",false), num);
	}
	
	/**
	 * Fill in vehicle number.
	 * @param num - number of vehicle
	 */
	public void setNumOfVehicles(String num) {
		browser.setTextField(".id", "numberofvehiclesid0", num);
	}
	
	/**
	 * Select accept agreement check box.
	 */
	public void selectCheckBoxAgreementAccepted() {
		browser.selectCheckBox(".id", "aggrAcc");
	}
	
	/**
	 * Deselect accept agreement check box.
	 */
	public void deselectCheckBoxAgreementAccepted() {
		browser.unSelectCheckBox(".id", "aggrAcc");
	}
	
	/**
	 * Set up lottery order details in page two, verify whether or not collect credit card info.
	 * If yes, fill in cc payment info.
	 * @param od - order details data
	 * @param pay - payment data
	 */
	public void setPageTwoDetails(OrderDetails od, Payment pay, boolean isCollectCCPayment) {
		if(od.numOccupant !=null && od.numOccupant.length()>0)
			this.setNumOfOccupants(od.numOccupant);
		if(od.numVehicles !=null && od.numVehicles.length()>0)
			this.setNumOfVehicles(od.numVehicles);
		if(od.equipType !=null && od.equipType.length()>0)
			this.selectEquipment(od.equipType);
		if(od.equipLength !=null && od.equipLength.length()>0)
			this.setVehicleLength(od.equipLength);
		
		//verify whether or not need to fill in the cc payment field
		if(isCollectCCPayment) {
			setupPayment(pay);
		}
	}
	
	/**
	 * Verify whether or not fill in the cc payment info
	 * @return true - cc payment exists; false - cc payment not exists
	 */
	public boolean needCollectCCPaymentInfo(){
		IHtmlObject[] objs = browser.getHtmlObject(".id","ccInfo");
		
		if(objs.length<1)
			return false;
		Browser.unregister(objs);
		
		return true;
	}
	
	/**Select card type for payment info in page 2*/
	public void selectCardType(String type) {
		browser.selectDropdownList(".id","cardTypeId_null",type);
	}
	
	/**Select card number for payment info in page 2*/
	public void setCardNum(String num) {
		browser.setTextField(".id","cardnum_null",num);
	}
	
	/**Select expiry month for payment info in page 2*/
	public void setExpiryMonth(String month) {
		browser.setTextField(".id","expmonth_null",month);
	}
	
	/**Select expiry year for payment info in page 2*/
	public void setExpiryYear(String year) {
		browser.setTextField(".id","expyear_null",year);
	}
	
	/**Select first name of card holder for payment info in page 2*/
	public void setFirstName(String fName) {
		browser.setTextField(".id","fname_null",fName);
	}
	
	/**Select last name of card holder for payment info in page 2*/
	public void setLastName(String lName) {
		browser.setTextField(".id","lname_null",lName);
	}
	
	/**
	 * Fill in payment info
	 * @param pay - payment data
	 */
	public void setupPayment(Payment pay) {
		selectCardType(pay.payType);
		setCardNum(pay.creditCardNumber);
		setExpiryMonth(pay.expiryMon);
		setExpiryYear(pay.expiryYear);
		setFirstName(pay.fName);
		setLastName(pay.lName);
	}
}
