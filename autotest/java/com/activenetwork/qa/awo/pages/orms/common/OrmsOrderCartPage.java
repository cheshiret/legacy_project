/*
 * Created on Apr 27, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.Customer;
import com.activenetwork.qa.awo.datacollection.legacy.Payment;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Address;
import com.activenetwork.qa.awo.datacollection.legacy.orms.Voucher;
import com.activenetwork.qa.awo.datacollection.legacy.orms.marina.SlipInfo;
import com.activenetwork.qa.awo.pages.OrmsPage;
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
import com.activenetwork.qa.testapi.util.DateFunctions;
import com.activenetwork.qa.testapi.util.NumberUtil;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;
import com.activenetwork.qa.testapi.util.Timer;

/**
 * @author jdu
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class OrmsOrderCartPage extends OrmsPage {

	static private OrmsOrderCartPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private OrmsOrderCartPage() {
	}

	/**
	 * @return The unique instance of this class.
	 */
	public static OrmsOrderCartPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsOrderCartPage();
		}
		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.DIV");
		p[1] = new Property(".text", "Process Order");
		p[2] = new Property(".id", "ActionButton");
		return browser.checkHtmlObjectExists(p) && getMainFrame() <= 0;
	}

	public void waitExists(int sleep_before_check) {
		Browser.sleep(sleep_before_check);
		this.waitLoading();
	}

	/** CLick Change existing reservation */
	public void clickChangeExistingRes() {
		// james[20130828] in 3.05 this button is changed to Change Order->Site
		// Reservation
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Order");
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".id",
				new RegularExpression("changeOrderProductSelector(_\\d+)?",
						false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text",
				"Site Reservation");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
		ajax.waitLoading();
	}

	public void clickChangeSlipRes() {
		this.clickChangeOrder("Slip");
	}

	/** click Add reservation */
	public void clickAddSiteReservation() {
		// 3.05 build UI redesign,do this code branch
		this.clickAddOrder("Site");
	}

	/**
	 * 
	 * @param product
	 *            - can be Site, Slip, Permit and Ticket,. etc
	 */
	protected void clickAddOrder(String product) {
		RegularExpression idPattern = new RegularExpression(
				"addOrderProductSelector(_\\d+)?", false);
		IHtmlObject[] addOrdDiv = browser.getHtmlObject(".class", "Html.DIV",
				".id", idPattern);
		if (addOrdDiv.length > 0) { // This is for field/operation manager
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression(product + " (Reservation|Order)",
							false), true, 0, addOrdDiv[0]);
			Browser.unregister(addOrdDiv);
		} else {// This is for marina manager
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression("Add " + product
							+ " (Reservation|Order)", false));
		}

	}

	protected void clickChangeOrder(String siteOrSlip) {
		RegularExpression idPattern = new RegularExpression(
				"changeOrderProductSelector(_\\d+)?", false);
		IHtmlObject[] changeOrdDiv = browser.getHtmlObject(".class",
				"Html.DIV", ".id", idPattern);
		if (changeOrdDiv.length > 0) { // This is for operation manager
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression(siteOrSlip + " (Reservation|Order)",
							false), true, 0, changeOrdDiv[0]);
			Browser.unregister(changeOrdDiv);
		} else {// This is for marina manager
			browser.clickGuiObject(".class", "Html.A", ".text",
					new RegularExpression("Change " + siteOrSlip
							+ " (Reservation|Order)", false));
		}
	}

	/** Click Add Slip reservation */
	public void clickAddSlipReservation() {
		this.clickAddOrder("Slip");
	}

	/** Click Add Pos */
	public void clickAddPOS() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add POS Product");
	}

	/** Click on the cancellation link */
	public void clickCancelCart() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				new RegularExpression("Cancel Cart|Cancel Call", false));
		int index = 0;
		if (objs.length > 1) {
			index = 1;
		}
		Browser.unregister(objs);
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Cancel Cart|Cancel Call", false), index);
	}

	/** Click on the "OK" link to confirm cancellation */
	public void clickOkToCancelCart() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Ok to Cancel Cart");
	}

	public void clickOkToCancelCartOrCall() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Ok to Cancel (Cart|Call)", false));
	}

	/** Click on the "Process Order" link */
	public void clickProcessOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression(" ?Process Order ?", false));
	}

	/**
	 * Click Apply discount button
	 */
	public void clickApplyDiscount() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply Discount");
	}

	public void verifyChangeCustomerEnabled(boolean expected) {
		if (!MiscFunctions.compareResult("Change Customer button "
				+ (expected ? "shall be enabled" : "shall be NOT enabled"),
				expected, isChangeCustomerEnabled()))
			throw new ErrorOnPageException("Change Customer button "
					+ (expected ? "shall be enabled" : "shall be NOT enabled"));
	}

	public boolean isChangeCustomerEnabled() {
		return isButtonExist("Change Customer");
	}

	/** click Change customer */
	public void clickChangeCustomer() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Change Customer",
				true);
	}

	/** Click Credit card radio button */
	public void clickCreditCardRadioBtn() {
		browser.clickGuiObject(".id", "useCCInFile", 0);
	}

	/** Click Other perment radiobutton */
	public void clickOtherPaymentRadioBtn() {
		browser.clickGuiObject(".id", "useCCInFile", 1);
	}

	/** Check CVV exit or not */
	public boolean checkCVV() {
		IHtmlObject[] objs = browser.getTextField(".id", "F_CardCVV_0_0");
		boolean exists = false;
		if (objs.length > 0) {
			exists = objs[0].isEnabled();
		}
		Browser.unregister(objs);
		return exists;
	}

	public boolean checkPaymentDropDown() {
		return browser.checkHtmlObjectExists(".id", "paymentMethod_0_0");
	}

	public void selectPaymentMethods(String payType) {
		RegularExpression payMethod = new RegularExpression(
				"paymentMethod_\\d+_0", false);

		IHtmlObject[] objs = browser.getDropdownList(".id", payMethod);
		for (int i = 0; i < objs.length; i++) {
			ISelect d = (ISelect) objs[i];
			List<String> options = d.getAllOptions();
			// Shane: as there are some payment types in test case are not
			// correct
			// in order to reduce update case maintenance, add below change
			// logic
			options = StringUtil.convertListStrCase(options, false);
			payType = payType.toUpperCase();
			if (options.contains(payType))
				d.select(payType);
			else if (options.contains(payType + "*"))
				d.select(payType + "*");
			else
				throw new ItemNotFoundException("Option " + payType
						+ " is not available.");
		}

		Browser.unregister(objs);
	}

	public void setPaymentAmounts(String amount) {
		RegularExpression payAmount = new RegularExpression(
				"paymentAmount_\\d+_0", false);
		IHtmlObject[] objs = browser.getTextField(".id", payAmount);
		for (int i = 0; i < objs.length; i++) {
			IText d = (IText) objs[i];
			d.setText(amount);
		}
		Browser.unregister(objs);
	}

	public void setPaymentAmounts(String[] amounts) {
		RegularExpression payAmount = new RegularExpression(
				"paymentAmount_\\d+_0", false);
		IHtmlObject[] objs = browser.getTextField(".id", payAmount);
		int size = Math.min(amounts.length, objs.length);
		for (int i = 0; i < size; i++) {
			IText t = (IText) objs[i];
			t.setText(amounts[i]);
		}
		Browser.unregister(objs);
	}

	public void setCheckNumbers(String number) {
		if (number != null && !number.equals("")) {
			RegularExpression chqNum = new RegularExpression(
					"F_ChqNumber_\\d+_0", false);
			IHtmlObject[] objs = browser.getTextField(".id", chqNum);
			for (int i = 0; i < objs.length; i++) {
				IText d = (IText) objs[i];
				d.setText(number);
			}
			Browser.unregister(objs);
		}
	}

	public void setCheckDates(String date) {
		RegularExpression chqDate = new RegularExpression(
				"F_ChqDate_\\d+_0_ForDisplay", false);
		IHtmlObject[] objs = browser.getTextField(".id", chqDate);
		for (int i = 0; i < objs.length; i++) {
			IText d = (IText) objs[i];
			d.setText(date);
		}
		Browser.unregister(objs);
	}

	public void setCheckNames(String name) {
		RegularExpression chqName = new RegularExpression("F_ChqName_\\d+_0",
				false);
		IHtmlObject[] objs = browser.getTextField(".id", chqName);
		for (int i = 0; i < objs.length; i++) {
			IText d = (IText) objs[i];
			d.setText(name);
		}
		Browser.unregister(objs);
	}

	public void setCreditCards(String number, String expireMon,
			String expireYear, String cvv, String cardHolder) {
		RegularExpression idPattern = new RegularExpression(
				"^F_CardNumber_\\d+_0$", false);
		IHtmlObject[] objs = browser.getTextField(".id", idPattern);
		List<String> expireIDs = new ArrayList<String>();
		for (int i = 0; i < objs.length; i++) {
			IText t = (IText) objs[i];
			t.setText(number);
			String id =((IHtmlObject) t).getProperty(".id").replaceAll("CardNumber",
					"CardExpiry");
			expireIDs.add(id);

		}

		Browser.unregister(objs);

		for (int i = 0; i < expireIDs.size(); i++) {
			this.setExpireMonth(expireMon, expireIDs.get(i), i);
			this.setExpireYear(expireYear, expireIDs.get(i), i);
		}

		idPattern.set("^F_CardHolder_\\d+_0$", false);
		objs = browser.getTextField(".id", idPattern);
		for (int i = 0; i < objs.length; i++) {
			IText t = (IText) objs[i];
			t.setText(cardHolder);

		}
		Browser.unregister(objs);
		if (checkCVV() && cvv.length() > 0) {
			idPattern.set("F_CardCVV_\\d+_0", false);
			objs = browser.getTextField(".id", idPattern);
			for (int i = 0; i < objs.length; i++) {
				IText t = (IText) objs[i];
				t.setText(cvv);

			}
			Browser.unregister(objs);
			browser.clickGuiObject(".class", "Html.LABEL", ".text", "Currency");
		}
	}

	public void setExpireMonth(String text, int index) {
		setExpireMonth(text, "F_CardExpiry_0_0", index);
	}

	public void setExpireMonth(String text, String id, int index) {
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV",
				".text", "MM");
		if (topObjs.length < 1) {
			topObjs = browser.getHtmlObject(".class", "Html.SPAN", ".text",
					"MM");// Shane[20130929],for 305 build, it changed to SPAN
							// not a DIV
		}
		IHtmlObject[] expireYearObj = browser.getHtmlObject(".class",
				"Html.INPUT.text", ".id", id, topObjs[index]);
		((IText) expireYearObj[0]).setText(text);
	}

	public void setExpireYear(String text, String id, int index) {
		IHtmlObject[] topObjs = browser.getHtmlObject(".class", "Html.DIV",
				".text", "YY");
		if (topObjs.length < 1) {
			topObjs = browser.getHtmlObject(".class", "Html.SPAN", ".text",
					"YY");// Shane[20130929],for 305 build, it changed to SPAN
							// not a DIV
		}
		IHtmlObject[] expireYearObj = browser.getHtmlObject(".class",
				"Html.INPUT.text", ".id", id, topObjs[index]);
		((IText) expireYearObj[0]).setText(text);
	}

	public void setExpireYear(String text, int index) {
		setExpireYear(text, "F_CardExpiry_0_0", index);
	}

	public void setGiftCards(String number, String pin) {
		RegularExpression idPattern = new RegularExpression(
				"F_GiftCardNumber_\\d+_0", false);
		IHtmlObject[] objs = browser.getTextField(".id", idPattern);
		for (int i = 0; i < objs.length; i++) {
			IText t = (IText) objs[i];
			t.setText(number);
		}

		Browser.unregister(objs);

		idPattern.set("F_GiftCardPin_\\d+_0", false);
		objs = browser.getTextField(".id", idPattern);
		for (int i = 0; i < objs.length; i++) {
			IText t = (IText) objs[i];
			t.setText(pin);
		}

		Browser.unregister(objs);
	}

	public boolean useCCInFileExists() {
		return browser.checkHtmlObjectExists(".id", "useCCInFile");
	}

	public void selectUseCreditCardOnFile() {
		browser.selectRadioButton(".id", "useCCInFile", ".value", "1");
	}

	public void selectUseOtherPaymentMethods() {
		browser.selectRadioButton(".id", "useCCInFile", ".value", "0");
	}

	/**
	 * setup payment information
	 * 
	 * @param pay
	 * @throws PageNotFoundException
	 */
	public void setupPayment(Payment pay) throws PageNotFoundException {
		if (browser.checkHtmlObjectExists(".id", "useCCInFile")) {
			if (!pay.useCreditCardOnFile) {
				this.clickOtherPaymentRadioBtn();
			} else {
				this.clickCreditCardRadioBtn();
			}
			this.waitLoading();
		}

		if (paymentListExists()) {
			if (pay != null && !pay.payType.equals("")
					&& !pay.payType.equalsIgnoreCase("None")) {
				selectPaymentMethods(pay.payType);
				logger.info("Select payment type as " + pay.payType);
			} else {
				setPaymentAmounts("0");
				logger.info("Pay nothing for this order cart.");
				return;
			}

			RegularExpression creditCard = new RegularExpression(
					"(Visa)|(MasterCard)|(Discover)|(American Express)", false);
			if (pay.payType.equals("Money Order")) {
				setCheckNumbers(pay.checkNumber);
			} else if (pay.payType.equals("Certified Check")) {
				setCheckNumbers(pay.checkNumber);
			} else if (pay.payType.equals("Travellers Check")) {
				setCheckNumbers(pay.checkNumber);
			} else if (pay.payType.equals("Personal Check")) {
				setCheckNumbers(pay.checkNumber);
				setCheckDates(pay.checkDate);
				setCheckNames(pay.checkName);
			} else if (creditCard.match(pay.payType)) {
				if (useCCInFileExists()) {
					if (pay.useCreditCardOnFile) {
						selectUseCreditCardOnFile();
					} else {
						selectUseOtherPaymentMethods();
						setCreditCards(pay.creditCardNumber, pay.expiryMon,
								pay.expiryYear, pay.securityCode,
								pay.cardHolder);
					}
				} else {
					setCreditCards(pay.creditCardNumber, pay.expiryMon,
							pay.expiryYear, pay.securityCode, pay.cardHolder);
				}
			} else if ((pay.payType.equalsIgnoreCase("Gift Card")
					|| pay.payType.equalsIgnoreCase("GIFT 2U")
					|| pay.payType.contains("GIFTCARD") || pay.payType
						.contains("GIFT 4 PARKS")) && !pay.useCreditCardOnFile) {
				setGiftCards(pay.giftCardNum, pay.giftCardSecCode);
			} else if (pay.payType.equals(Payment.PAY_DEF_MON_ORDER)) {
				setCheckNumbers(pay.checkNumber);
			} else if (pay.payType.equals(Payment.PAY_DEF_PER_CHQ)) {
				setCheckNumbers(pay.checkNumber);
			}

			double amount_owings[] = this.getAmountOwings();
			String amountStrs[] = new String[amount_owings.length];

			for (int i = 0; i < amount_owings.length; i++) {
				double payAmount = amount_owings[i];

				if (!pay.amount.equals("")) {
					// if(pay.isPayMore) {//Quentin[20140220] change to get
					// change by paying more than amount owing
					payAmount = Double.parseDouble(pay.amount);
					// } else {
					// james[20140224] this logic was added to make sure we
					// don't pay more than blance owing, which may block
					// processing order cart if using credit card
					// after reviewed this logic in depth, I believe this logic
					// can be removed so that it is QA's responsibility to make
					// sure payment is correct.
					// payAmount=Math.min(amount_owing,
					// Double.parseDouble(pay.amount));
					// }
				} else if (pay.payInstruction != Payment.FULL) {
					double amount = amount_owings[i];// this.getPaymentAmount();
					switch (pay.payInstruction) {
					case Payment.HALF:
						amount = amount / 2;
						break;
					case Payment.ONETHIRD:
						amount = amount / 3;
						break;
					case Payment.ONEFORTH:
						amount = amount / 4;
						break;
					case Payment.ONEFIFTH:
						amount = amount / 5;
						break;
					case Payment.AMOUNTOWING:
						amount = this.getAmountOwing();
						break;
					case Payment.MINIMUM:
						if (isMinimumPayOrder()) {
							amount = this.getMinimumPaymentDueFM()
									.doubleValue();
						}
						break;
					}

					payAmount = amount;
				} // else pay full amount owing
				DecimalFormat df = new DecimalFormat("#.##");
				df.setRoundingMode(RoundingMode.HALF_UP);
				amountStrs[i] = df.format(payAmount);
			}

			setPaymentAmounts(amountStrs);

			// reset payment amount to empty
			pay.amount = "";
			pay.amount_1 = "";

			browser.clickGuiObject(".class", "Html.LABEL", ".text", "Currency");
		} else {
			logger.info("This order cart needs no payment.");
		}
	}

	/**
	 * Setup additional payment
	 * 
	 * @param pay
	 */
	public void setupAdditionalPayment(Payment pay) {
		if (browser.checkHtmlObjectExists(".id", "useCCInFile")) {
			this.clickOtherPaymentRadioBtn();
		}
		this.waitLoading();
		if (additionalPaymentListExists()) {
			if (pay != null && !pay.additionalPayType.equals("")
					&& !pay.additionalPayType.equalsIgnoreCase("None")) {
				browser.selectDropdownList(".id", "paymentMethod_0_1",
						pay.additionalPayType);
				logger.info("Select payment type as " + pay.additionalPayType);
			} else {
				browser.setTextField(".id", "paymentAmount_0_1", "0");
				logger.info("Pay nothing for this order cart.");
				return;
			}

			if (pay.additionalPayType.equals("Money Order")) {
				browser.setTextField(".id", "F_ChqNumber_0_1",
						pay.additionalCheckNumber);
			} else if (pay.additionalPayType.equals("Certified Check")) {
				browser.setTextField(".id", "F_ChqNumber_0_1",
						pay.additionalCheckNumber);
			} else if (pay.additionalPayType.equals("Travellers Check")) {
				browser.setTextField(".id", "F_ChqNumber_0_1",
						pay.additionalCheckNumber);
			} else if (pay.additionalPayType.equals("Personal Check")) {
				browser.setTextField(".id", "F_ChqNumber_0_1",
						pay.additionalCheckNumber);
				browser.setTextField(".id", "F_ChqDate_0_1_ForDisplay",
						pay.additionalCheckDate);
				browser.setTextField(".id", "F_ChqName_0_1",
						pay.additionalCheckName);
			} else if (pay.additionalPayType.equalsIgnoreCase("Visa")
					|| pay.additionalPayType.equalsIgnoreCase("MasterCard")
					|| pay.additionalPayType.equalsIgnoreCase("Discover")
					|| pay.additionalPayType
							.equalsIgnoreCase("American Express")) {
				browser.setTextField(".id", "F_CardNumber_0_1",
						pay.additionalCreditCardNumber);
				browser.setTextField(".id", "F_CardExpiry_0_1",
						pay.additionalExpiryMon, 0);
				browser.setTextField(".id", "F_CardExpiry_0_1",
						pay.additionalExpiryYear, 1);
				browser.setTextField(".id", "F_CardHolder_0_1",
						pay.additionalCardHolder);
			}

			if (!pay.additionalAmount.equals("")) {
				browser.setTextField(".id", "paymentAmount_0_1",
						pay.additionalAmount);
			}
			browser.clickGuiObject(".class", "Html.LABEL", ".text", "Currency");
		} else {
			logger.info("This order cart needs no payment.");
		}
	}

	/**
	 * Method used to determine if payment can be made on cart page
	 * 
	 * @return whether payment list exists
	 */
	public boolean paymentListExists() {
		boolean exists = false;

		int count = 0;
		int num = 0;
		while (!exists && count < 5) {
			IHtmlObject[] objs = browser.getDropdownList(".id",
					new RegularExpression("paymentMethod_\\d+_0", false));

			num = objs.length;
			exists = num > 0 && objs[0].isEnabled();
			Browser.unregister(objs);
			Timer.sleep(1000);
			count++;
		}

		if (exists) {
			logger.info("There are " + num + " payment(s) to setup");
		}

		return exists;
	}

	/**
	 * Get the payment list number
	 * 
	 * @return
	 */
	public int getPaymentListNum() {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SELECT",
				".id", new RegularExpression("paymentMethod_\\d+_0", false));

		int num = -1;
		if (objs.length > 0) {
			num = objs.length;
		}

		Browser.unregister(objs);
		return num;
	}

	/**
	 * check additional payment exit or not
	 * 
	 * @return
	 */
	public boolean additionalPaymentListExists() {
		return browser.checkHtmlObjectExists(".class", "Html.SELECT", ".id",
				"paymentMethod_0_1");
	}

	/**
	 * Apply discount for one spec Reservaion
	 * 
	 * @param resId
	 */
	public void applyDiscount(String resId, boolean isPermitOrSlip) {
		this.selectOrderCheckBox(resId, isPermitOrSlip);
		browser.clickGuiObject(".class", "Html.A", ".text", "Apply Discount");
		ajax.waitLoading();
	}

	public void selectOrderCheckBox(String resId) {
		this.selectOrderCheckBox(resId, false);
	}

	public String getResNum() {
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression(
				"SELECTED_ORDER.NEW_ORDERVIEW.0_[1-9]-[0-9]+", false));
		String id = objs[0].id();
		Browser.unregister(objs);
		return id.substring(id.lastIndexOf("-") - 1).trim();
	}

	public void selectOrderCheckBox(String resId, boolean isNotSiteRes) {
		this.selectOrUnselectOrderCheckBox(resId, isNotSiteRes, true);
	}

	public void unselectOrderCheckBox(String resId, boolean isNotSiteRes) {
		this.selectOrUnselectOrderCheckBox(resId, isNotSiteRes, false);
	}

	/**
	 * If the order is new order, the resId should be New-[1-9]+
	 * 
	 * @param resId
	 */
	private void selectOrUnselectOrderCheckBox(String resId,
			boolean isNotSiteRes, boolean isSelect) {
		String pattern = "SELECTED_ORDER";
		if (resId.matches("New(\\s)?-(\\s)?[0-9]+")) {
			int index = Integer.parseInt(RegularExpression.getMatches(resId,
					"[0-9]+")[0]);
			if (index > 0) {
				if (isNotSiteRes) {
					pattern = pattern + ".NEW_ORDERVIEW.0_NEW_ORDER_" + index;// SELECTED_ORDER.NEW_ORDERVIEW.0_NEW_ORDER_1
				} else {
					pattern = pattern + ".NEW_ORDER.0_" + (index - 1);
				}
			} else {
				throw new RuntimeException("resID#" + resId + " is not valid.");
			}
		} else if (resId.matches("([1-9]-[0-9]+|M[1-9]-[0-9]+|17-[0-9]+)")) {
			if (isNotSiteRes) {
				pattern = pattern + ".NEW_ORDERVIEW.0_" + resId;
			} else {
				pattern = pattern + ".OLD_ORDER." + resId;
			}
		} else if (resId
				.matches("(Advanced |Walk-up )Ticket Purchase \\(New - [1-9]\\)")) {
			int index = Integer.parseInt(RegularExpression.getMatches(resId,
					"[1-9]+")[0]);
			if (index > 0) {
				if (isNotSiteRes) {
					pattern = pattern + ".NEW_ORDERVIEW.0_" + "NEW_ORDER_"
							+ (index);
				}
			} else {
				throw new RuntimeException("resID#" + resId + " is not valid.");
			}
		} else if (resId.matches("Submit Lottery Entry")
				|| resId.matches("Purchase POS")) { // This is for lottery or
													// Purchase POS
			pattern = pattern + ".NEW_ORDERVIEW.0_NEW_ORDER_1";
		}
		// browser.selectCheckBox(".id", pattern);
		// SELECTED_ORDER.NEW_ORDERVIEW.0_NEW_ORDER_1
		if (isSelect) {
			browser.selectCheckBox(".id", pattern, ".type", "checkbox", true);
		} else {
			browser.unSelectCheckBox(".id", pattern, ".type", "checkbox");
		}
	}

	public void goFeeDetail(String resId) {
		this.goFeeDetail(resId, false);
	}

	/** Click on the "Fees" link-button */
	public void goFeeDetail(String resId, boolean isPermit) {
		selectOrderCheckBox(resId, isPermit);
		clickFeesButton();
	}

	public void clickFeesButton() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression(" ?Fees$", false));
		ajax.waitLoading();
	}

	/**
	 * Check is general public or not
	 * 
	 * @return
	 */
	public boolean isGeneralPublic() {
		// RegularExpression pattern = new
		// RegularExpression(".+ General Public$",
		// false);
		return browser.checkHtmlObjectExists(".class", "Html.SPAN", ".text",
				"General Public");
	}

	/**
	 * Select checkbox for later payment
	 * 
	 * @param index
	 */
	public void selectCheckboxForLaterPmt(int index) {
		browser.selectCheckBox(".id", new RegularExpression(
				"EXPECTED_PAYMENT.NEW_ORDER\\.0_[0-9]+", false), index);
	}

	/**
	 * UnSelect checkbox for Auto-Print Ticket
	 * 
	 * @param
	 */
	public void unSelectCheckboxForAutoPrintTicket() {
		browser.unSelectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				"AutoPrintTicket");
	}

	/**
	 * Select checkbox for Auto-Print Ticket
	 * 
	 * @param
	 */
	public void selectCheckboxForAutoPrintTicket() {
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id",
				"AutoPrintTicket", true);
	}

	/** Check AutoPrintTicket exist or not */
	public boolean checkAutoPrintTicketExist() {
		return browser.checkHtmlObjectExists(".class", "Html.INPUT.checkbox",
				".id", "AutoPrintTicket");
	}

	/** Check AutoPrintTicket select or unSelect */
	public boolean checkAutoPrintTicketSelect() {
		return browser.isCheckBoxSelected(".id", "AutoPrintTicket");
	}

	/** Cancel Call */
	public void cancelCall() {
		Property[] p = new Property[3];
		p[0] = new Property(".class", "Html.A");
		p[1] = new Property(".id", new RegularExpression(
				"callmgr\\.mainmenu\\.id\\.\\d", false));
		p[2] = new Property(".text", "Cancel Call");
		browser.clickGuiObject(p);
	}

	/** Go to reservation detail page */
	public void gotoResDetails() {
		RegularExpression pattern = new RegularExpression(".*(New - [1-9]).*",
				false);
		IHtmlObject[] resLinks = browser.getHtmlObject(".class", "Html.A",
				".text", pattern);
		if (resLinks.length < 1) {
			throw new ItemNotFoundException("can not find any new order items");
		}

		resLinks[0].click();
		Browser.unregister(resLinks);
	}

	public void clickSlipLink(String code, String name) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Slip: " + code
				+ "-" + name, true);
	}

	/** Get the occupant number */
	public String getOccupantNum() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Customer.*", false));
		String tableText = objs[0].getProperty(".text").toString();
		String num = tableText.split("# of Occupants: ")[1].split(" ")[0]
				.trim();

		Browser.unregister(objs);
		return num;
	}

	/**
	 * Select reservation
	 * 
	 * @param res
	 */
	public void selectReservation(String res) {
		String pattern = "SELECTED_ORDER.OLD_ORDER." + res;
		browser.selectCheckBox(".class", "Html.INPUT.checkbox", ".id", pattern);
	}

	/**
	 * Select First New Reservation
	 */
	public void selectFirstReservation() {
		String pattern = "SELECTED_ORDER.NEW_ORDER.*";
		IHtmlObject[] objs = browser.getCheckBox(".id", new RegularExpression(
				pattern, false));
		ICheckBox checkBox = (ICheckBox) objs[0];
		checkBox.select();
	}

	/** Click Res Charge POS */
	public void clickResChargePOS() {
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV", ".text",
				new RegularExpression("^\\W*Remove Order.*Charge POS$", false));
		Property[] p2 = Property.toPropertyArray(".class", "Html.A", ".text",
				"Charge POS");
		browser.clickGuiObject(Property.atList(p1, p2), true, 0);
	}

	/**
	 * Charge Res POS by resNum
	 * 
	 * @param resNum
	 */
	public void chargeResPOS(String resNum) {
		this.selectReservation(resNum);
		this.clickResChargePOS();
		ajax.waitLoading();
	}

	/**
	 * Charge POS to first reservation
	 * 
	 */
	public void chargeResPOS() {
		this.selectFirstReservation();
		this.clickResChargePOS();
	}

	public void chargePOSToRes(String... resNums) {
		chargePOSToRes(false, resNums);
	}

	public void chargePOSToRes(boolean isNotSite, String... resNums) {
		for (String temp : resNums) {
			this.selectOrderCheckBox(temp, isNotSite);
		}
		this.clickResChargePOS();
	}

	/** Click Negotiate price */
	public void clickNegotiatePrice() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Negotiate Price");
	}

	/** click Remove Item */
	public void clickRemoveItem() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Item");
	}

	/** Click Payoutstanding */
	public void clickPayOutstanding() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Pay Outstanding Balance");
	}

	/** Click Event ID */
	public void clickEventID() {
		browser.clickGuiObject(".class", "Html.A", ".href",
				new RegularExpression("\"showEventDetailsFromCart\"", false));
	}

	/**
	 * Get event ID
	 * 
	 * @return
	 */
	public String getEventID() {
		String result = "New";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".href",
				new RegularExpression("\"showEventDetailsFromCart\"", false));
		if (objs.length > 0)
			result = objs[0].getProperty(".text").toString();

		Browser.unregister(objs);
		return result;
	}

	/** Click Event Charege POS */
	public void clickEventChargePOS() {
		browser.clickGuiObject(".class", "Html.A", ".href",
				new RegularExpression("\"chargePOSToEventInOrderCart\"", false));
	}

	/** Click change pre existing reservation */
	public void clickChangePreExistingRes() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Change Pre-Existing Res.");
	}

	/** Get Payment Amount */
	public double getPaymentAmount() {
		String amount = browser.getTextFieldValue(".id", "paymentAmount_0_0");
		if (amount == null)
			return -1;
		return Double.parseDouble(amount.trim());
	}

	/** Modified Payment Amount */
	public void setPaymentAmount(String amount) {
		browser.setTextField(".id", "paymentAmount_0_0", amount);
	}

	public String getPaymentCurrency() {
		return getAttributeValueByName("Currency");
	}

	/** Get the action mark in the cart page, like "change dates" etc. */
	public boolean getActionMark(String actionMark) {
		String targetString = "";
		boolean toReturn = false;
		IHtmlObject[] tables = browser.getHtmlObject(".class", "Html.TABLE");
		for (int i = 0; i < tables.length; i++) {
			targetString = tables[i].getProperty(".text").toString();
			if (targetString.contains(actionMark)) {
				toReturn = true;
				break;
			}
		}

		Browser.unregister(tables);
		return toReturn;
	}

	/** Get the split stay reservations link */
	public void verifyIsSplitStayRes() {
		String msg = "";
		RegularExpression reg = new RegularExpression(
				"^ ?advanced reservation.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				reg);
		for (int i = 0; i < objs.length; i++) {
			if (!objs[i].getProperty(".text").toString().split("\\(")[1]
					.split("\\)")[0].matches("^New - [1-9]+[A-B]$")) {
				msg += "the format of '"
						+ objs[i].getProperty(".text").toString()
						+ "' is wrong ! \n";
			}
		}
		Browser.unregister(objs);
		if (msg.trim().length() > 0) {
			throw new ErrorOnPageException(msg);
		}

	}

	/** Get the Site info in split stay */
	public List<String> getSitesInfoInSplitStaryCart() {
		List<String> list = new ArrayList<String>();
		RegularExpression reg = new RegularExpression("^ ?Site\\:.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.A", ".text",
				reg);
		for (int i = 0; i < objs.length; i++) {
			list.add(objs[i].getProperty(".text").toString().split("\\:")[1]
					.trim());
		}
		return list;
	}

	public boolean checkClickAdditionalPaymentExisting() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Additional Payment");
	}

	/** Click Additional Payment */
	public void clickAdditionalPayment() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Additional Payment");
	}

	public void clickRemovePayment() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Payment");
	}

	public void removeAllAdditionalPayments() {
		while (browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Remove Payment")) {
			this.clickRemovePayment();
			this.waitLoading();
		}
	}

	/** Click Add Wildlife Order */
	public void clickAddWildlifeOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add Wildlife Order");
	}

	/** Click Add Ticket Order */
	public void clickAddTicketOrder() {
		Property p[] = Property.toPropertyArray(".class", "Html.A", ".text",
				"Add Ticket Order");
		if (browser.checkHtmlObjectExists(p)) {
			browser.clickGuiObject(p);
		} else {
			this.clickAddOrder("Ticket");
		}
	}

	/** Click Add Permit Order */
	public void clickAddPermitOrder() {
		// Quentin[20131025]
		Property p[] = Property.toPropertyArray(".class", "Html.A", ".text",
				"Add Permit Order");
		if (browser.checkHtmlObjectExists(p)) {
			browser.clickGuiObject(p);
		} else {
			this.clickAddOrder("Permit");
		}
	}

	/**
	 * The method used to add more privilege from order cart
	 */
	public void clickAddMorePrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Add More (Privileges|Licences)", false));
	}

	public void clickAddMoreConsumable() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Add More Consumables", true);
	}

	/** Click remove to remove corresponding privilege item */
	public void clickRemove(int index) {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", index);
	}

	public void clickRemoveOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove Order");
	}

	public void clickNoCharge() {
		browser.clickGuiObject(".class", "Html.A", ".text", "No Charge");
	}

	public boolean isNoChargeButtonExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"No Charge");
	}

	// /**
	// * Get Fee Type Row
	// *
	// * @param feetype
	// * this type is the first fee type
	// * @return
	// */
	// public int getFeeTypeRow(String feetype) {
	// // RegularExpression reg = new RegularExpression("^(Use Fee|" + feetype
	// // + ").*", false);
	// // waitExists();
	// // HtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);
	// // int row = ((ITable) feeTypeTable[0]).findRow(0, feetype);
	// // Browser.unregister(feeTypeTable);
	//
	// return getFeeTypeRow(feetype, feetype);
	// }

	/**
	 * Get Fee Type Row
	 * 
	 * @param feetype
	 * @return
	 */
	public int getFeeTypeRow(String feetype) {
		RegularExpression reg = new RegularExpression(
				"^(Use Fee|Adult|Interagency Access Pass|Youth|POS Fee|"
						+ feetype + ").*", false);

		IHtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);
		// MiscFunctions.dumpTable((ITable) feeTypeTable[0]);
		int row = 0;
		if (feeTypeTable.length > 0) {
			row = ((IHtmlTable) feeTypeTable[0]).findRow(0, feetype);
		}
		Browser.unregister(feeTypeTable);

		return row;
	}

	/**
	 * Get fee type cell value
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getFeeTypeCellValue(int row, int col) {
		RegularExpression reg = new RegularExpression("^Use Fee*", false);

		return getCellValue(reg, row, col);
	}

	/**
	 * Get fee amount value
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getFeeAmountValue(int row, int col) {
		RegularExpression reg = new RegularExpression("^Order Item Qty*", false);

		return getCellValue(reg, row, col);
	}

	/**
	 * Get fee amount for specific fee type
	 * 
	 * @param feeType
	 * @return fee amount
	 */
	public String getFeeAmountValue(String feeType) {
		IHtmlObject[] objs;
		String value;
		boolean subTableExists = browser.checkHtmlObjectExists(".class",
				"Html.TABLE", ".text", new RegularExpression(
						"^(Use Fee|Adult|Interagency Access Pass|Youth|POS Fee|"
								+ feeType + ").*", false));
		if (subTableExists && !feeType.equalsIgnoreCase("Minimum Payment Due")) {// have
																					// sub-table,need
																					// to
																					// parse
																					// separately
			RegularExpression regx = new RegularExpression(
					"^(\\$[0-9]*\\.[0-9]{2} +).*", false);
			objs = browser.getTableTestObject(".text", regx);
			int feeRow = getFeeTypeRow(feeType);
			if (feeRow < 0) {
				return null;
			}
			String amount = objs[0].getProperty(".text").replace("(", "")
					.replace(")", "");
			String[] amounts = amount.split(" ");
			if (feeType.equalsIgnoreCase("Subtotal")) {
				feeRow--;
			}
			String feeAmount = amounts[feeRow].replaceFirst("\\$", "");
			value = feeAmount;
		} else {
			RegularExpression reg = new RegularExpression(
					"^(Customer|Event).*", false);
			objs = browser.getTableTestObject(".text", reg);
			String tableText = objs[0].getProperty(".text").toString();
			RegularExpression feeTypePattern = new RegularExpression(feeType
					+ "\\s?\\(?\\$\\d*(,)?\\d+\\.\\d{2}\\)?", false);// want to
																		// get:
																		// Adult
																		// $3,587.12
			String[] tokens = feeTypePattern.getMatches(tableText);
			if (tokens.length < 1) {
				return null;
			}

			RegularExpression amountPattern = new RegularExpression(
					"\\d*(,)?\\d+\\.\\d{2}", false);// want to get: 3,587.12
			tokens = amountPattern.getMatches(tokens[0]);

			value = tokens[0].replaceAll(",", StringUtil.EMPTY).trim();
		}
		// if(MiscFunctions.isQA24()) {
		// RegularExpression reg=new RegularExpression("^Customer.*",false);
		// objs=browser.getTableTestObject(".text",reg);
		// String tableText=objs[0].getProperty(".text").toString();
		// //// RegularExpression feeTypePattern=new
		// RegularExpression(feeType+"\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)? ",false);
		// RegularExpression feeTypePattern=new
		// RegularExpression(feeType+".*\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)? ",false);
		// String[] tokens=feeTypePattern.getMatches(tableText);
		// RegularExpression amountPattern=new
		// RegularExpression("\\d+\\.\\d{2}",false);
		// tokens=amountPattern.getMatches(tokens[0]);
		//
		// value=tokens[0].trim();
		//
		// } else {
		// RegularExpression regx = new RegularExpression(
		// // "^(\\$[0-9]*\\.[0-9]{2} ?).*(\\$[0-9]*\\.[0-9]{2})", false);
		// "^(\\$[0-9]*\\.[0-9]{2} +).*", false);
		// objs = browser.getTableTestObject(".text", regx);
		// int feeRow = getFeeTypeRow(feeType);
		// if(feeRow<0){
		// return null;
		// }
		// String amount = objs[0].getProperty(".text").replace("(",
		// "").replace(
		// ")", "");
		// String[] amounts = amount.split(" ");
		// if (feeType.equalsIgnoreCase("Subtotal")) {
		// feeRow--;
		// }
		// String feeAmount = amounts[feeRow].replaceFirst("\\$", "");
		// value= feeAmount;
		// }

		Browser.unregister(objs);
		return value;
	}

	/**
	 * Get cell value
	 * 
	 * @param reg
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCellValue(RegularExpression reg, int row, int col) {
		String toReturn = "";
		IHtmlObject[] objs = null;
		objs = browser.getTableTestObject(".text", reg);
		IHtmlTable resDetail = (IHtmlTable) objs[objs.length - 1];
		toReturn = resDetail.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Get items value
	 * 
	 * @param reg
	 * @return
	 */
	public String getItemValue(RegularExpression reg) {
		// HtmlObject [] objs = browser.getTableTestObject(".text", new
		// RegularExpression("^Customer Name.*", false));
		// ITable table = (ITable) objs[1];
		// int row = table.findRow(0, reg);
		//
		// String toReturn = table.getCellValue(row, 0);
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Customer Name.*", false));
		IHtmlTable table = (IHtmlTable) objs[1];

		int row = table.findRow(0, 0, reg);

		if (row < 0) {
			row = table.findRow(0, 1, reg);
		}
		int col = table.findColumn(0, row, reg);

		String toReturn = table.getCellValue(row, col);
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * This method used to verify given fee type fee amount equals given amount
	 * 
	 * @param feeType
	 *            - fee type
	 * @param isCorreect
	 *            - true/false
	 * @param realAmount
	 *            - given amount
	 */
	public void verifyFeeAmountCorrect(String feeType, boolean isCorreect,
			String realAmount) {
		logger.info("Start to verify specific fee amount.");

		if (!isCorreect) {
			int feeRow = getFeeTypeRow(feeType);
			if (feeRow > 0) {
				throw new ItemNotFoundException("Given Fee Type " + feeType
						+ " should not displayed.");
			} else {
				return;
			}
		}
		String amount = getFeeAmountValue(feeType);
		DecimalFormat format = new DecimalFormat("0.00");
		String realFee = format.format(Double.parseDouble(realAmount));
		String actualFee = format.format(Double.parseDouble(amount));
		if (!MiscFunctions.compareResult(feeType + " amount", realFee,
				actualFee)) {
			throw new ErrorOnDataException("Fee amount is not Correct!",
					realFee, actualFee);
		}
	}

	/**
	 * This method used to check specific fee exists
	 * 
	 * @param feeType
	 * @return
	 */
	public boolean checkFeeTypeExists(String feeType) {

		// if(!MiscFunctions.isQA24()) {
		// RegularExpression reg=new
		// RegularExpression("^Customer.*Additional Payment$",false);
		RegularExpression reg = new RegularExpression(
				"^(Event(| )Event ID|Customer(| )Name).*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		String tableText = objs[0].getProperty(".text").toString();
		return RegularExpression.contains(tableText, feeType, false);
		// UI changes for qa3: All Fee Type and All Fee Amount
		// qa4: Fee Type and Fee Amount
		// return RegularExpression.contains(tableText,
		// " "+feeType+" $\\d+\\.\\d{2} ", false);

		// } else {
		// RegularExpression reg = new
		// RegularExpression("^(Use Fee|POS Fee|Adult).*",
		// false);
		// HtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
		// ".text", reg);
		// String text = objs[0].getProperty(".text").toString();
		// Browser.unregister(objs);
		// if (text.indexOf(feeType) != -1) {
		// return true;
		// } else {
		// return false;
		// }
		// }
	}

	/**
	 * @return Select voucher Button Exists or not
	 */
	public boolean checkSelectVoucherBtnExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Select Voucher");
	}

	/**
	 * @return Search Other Vouchers button exist
	 */
	public boolean checkSelectOtherVoucherBtnExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Search Other Vouchers");
	}

	/**
	 * Click Select Voucher Button
	 * 
	 */
	public void clickSelectVoucher() {
		if (checkSelectVoucherBtnExists()) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					"Select Voucher");
		}
	}

	public void clickAdjustFee() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Adjust Fees to Past Paid");
	}

	/**
	 * click Search Other Vouchers button
	 * 
	 */
	public void clickSelectOtherVoucher() {
		if (checkSelectOtherVoucherBtnExists()) {
			browser.clickGuiObject(".class", "Html.A", ".text",
					"Search Other Vouchers");
		}
	}

	/**
	 * Select voucher Id
	 * 
	 * @param voucherId
	 */
	public void selectVoucherId(String voucherId) {
		// browser.selectDropdownList(".id", "selectedVoucherPayment_0",
		// voucherId);
		List<String> options = browser.getDropdownElements(".id",
				"selectedVoucherPayment_0");
		for (String option : options) {
			if (option.contains(voucherId)) {
				browser.selectDropdownList(".id", "selectedVoucherPayment_0",
						option);
			}
		}
	}

	/**
	 * Select voucher
	 * 
	 * @param voucher
	 */
	public void selectVoucher(Voucher voucher) {
		browser.selectDropdownList(".id", "selectedVoucherPayment_0",
				voucher.voucherId + " " + ":" + " $" + voucher.amount);
	}

	/**
	 * This method is used to choose voucher as payment
	 * 
	 * @param voucherId
	 */
	public void selectVoucherAsPayment(String voucherId) {
		clickSelectVoucher();
		waitLoading();
		selectVoucherId(voucherId);
		waitLoading();
	}

	public String getRedeemedVoucherBalance() {
		Property[] divPro = new Property[2];
		divPro[0] = new Property(".class", "Html.SPAN");
		divPro[1] = new Property(".text", new RegularExpression(
				"^Balance(| )\\$.*", false));

		Property[] spanPro = new Property[1];
		spanPro[0] = new Property(".class", "Html.SPAN");
		List<Property[]> listPro = new ArrayList<Property[]>();
		listPro.add(divPro);
		listPro.add(spanPro);

		IHtmlObject[] spanObj = browser.getHtmlObject(listPro);
		if (spanObj.length < 1) {
			throw new ItemNotFoundException(
					"Did not found balance span object.");
		}
		String text = spanObj[0].text().replace("$", "")
				.replaceAll("Balance", StringUtil.EMPTY);

		Browser.unregister(spanObj);
		return text;
	}

	/**
	 * Check Voucher Radio Button is Checked or not
	 * 
	 * @return
	 */
	public boolean checkVoucherRadioChecked() {
		RegularExpression reg = new RegularExpression("voucher.*", false);
		IHtmlObject[] radioBoxs = browser.getRadioButton(".value", reg);// Vivian[2014/03/07]
		String checked = radioBoxs[0].getProperty(".checked").toString();
		Browser.unregister(radioBoxs);
		return Boolean.valueOf(checked).booleanValue();
	}

	/**
	 * Check Voucher Radio Button is Checked or not
	 * 
	 * @return
	 */
	public boolean checkRefundRadioChecked() {
		RegularExpression reg = new RegularExpression("refund.*", false);
		// IHtmlObject[] radioBoxs = browser.getHtmlObject(".id",
		// "refundTo_0_1",
		// ".value", reg);
		IHtmlObject[] radioBoxs = browser.getRadioButton(".value", reg);
		String checked = radioBoxs[0].getProperty(".checked");
		Browser.unregister(radioBoxs);

		if (checked == null)
			return false;
		else
			return Boolean.valueOf(checked).booleanValue();
	}

	/**
	 * Check Voucher Radio Exists
	 * 
	 * @return if exists,return true;else return false
	 */
	public boolean checkVoucherRadioExists() {
		RegularExpression reg = new RegularExpression("voucher.*", false);
		// IHtmlObject[] radioBoxs = browser.getHtmlObject(".id",
		// "refundTo_0_1",
		// ".value", reg);
		IHtmlObject[] radioBoxs = browser.getRadioButton(".value", reg);// Vivian[2014/03/07]
		if (radioBoxs != null && radioBoxs.length > 0) {
			Browser.unregister(radioBoxs);
			return true;
		} else {
			Browser.unregister(radioBoxs);
			return false;
		}
	}

	/**
	 * Check Voucher Radio Exists
	 * 
	 * @return if exists,return true;else return false
	 */
	public boolean checkRefundRadioExists() {
		RegularExpression reg = new RegularExpression("refund.*", false);
		IHtmlObject[] radioBoxs = browser.getRadioButton(".value", reg);
		if (radioBoxs != null && radioBoxs.length > 0) {
			Browser.unregister(radioBoxs);
			return true;
		} else {
			Browser.unregister(radioBoxs);
			return false;
		}
	}

	/**
	 * Select Voucher Radio Button
	 * 
	 */
	public void selectRadioVoucher() {
		if (checkVoucherRadioExists()) {
			if (!checkVoucherRadioChecked()) {
				RegularExpression reg = new RegularExpression("voucher.*",
						false);
				// browser.clickGuiObject(".id", "refundTo_0_1", ".value", reg);
				browser.selectRadioButton(".value", reg);// Vivian[2014/03/07]

			}
		}
	}

	/**
	 * Select Voucher Radio Button
	 * 
	 */
	public void selectRadioRefund() {
		if (checkRefundRadioExists()) {
			if (!checkRefundRadioChecked()) {
				RegularExpression reg = new RegularExpression("refund.*", false);
				// browser.clickGuiObject(".id", "refundTo_0_1", ".value", reg);
				browser.selectRadioButton(".value", reg);// Vivian[2014/03/07]
			}
		}
	}

	/**
	 * click Balance Inquiry link
	 */
	public void clickBalanceInquiry() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Balance Inquiry");
	}

	/**
	 * Set the gift card and input the card number and security code
	 * 
	 * @param cardNum
	 *            -- gift card number
	 * @param securityCode
	 *            -- security code
	 */
	public void payWithGiftCard(String cardNum, String securityCode,
			boolean isBalanceInquiry) {
		browser.searchObjectWaitExists(".class", "Html.SELECT", ".id",
				"paymentMethod_0_0");
		browser.selectDropdownList(".id", "paymentMethod_0_0", "Gift Card");
		browser.setTextField(".id", "F_GiftCardNumber_0_0", cardNum);
		browser.setTextField(".id", "F_GiftCardPin_0_0", securityCode);

		if (isBalanceInquiry == true) {
			clickBalanceInquiry();
		} else {
			clickProcessOrder();
		}
	}

	/**
	 * Get the warning message from order cart page
	 * 
	 * @return warning message
	 */
	public String getWaringMessage() {
		RegularExpression reg = new RegularExpression("^message.*", false);
		IHtmlObject[] obj = browser.getHtmlObject(".class", "Html.DIV",
				".className", reg);
		String warningMes = obj[0].getProperty(".text").toString();
		Browser.unregister(obj);
		return warningMes;
	}

	/*
	 * Get the Vouchers from the order cart page
	 * 
	 * @return vouchers amount
	 */
	public String getVoucherAmount() {
		IHtmlObject[] obj = this.getVoucherTable();
		String temp = obj[0].getProperty(".text").toString();
		String refundVoucher = temp.substring(temp
		// .indexOf("Refund Voucher Total Refund to New Voucher")
				.indexOf("Total Refund to New Voucher")
		// + "Refund Voucher Total Refund to New Voucher".length());
				+ "Total Refund to New Voucher".length());
		// Voucher ProgramREFUNDS CustomerLoadTest,Astra From: Order2-15262589
		// Payment122336574 Payment GroupCash Refund StatusApproved Refund
		// Amount($578.00) CurrencyUSD Refund Voucher Total Refund to New
		// Voucher ($578.00)
		Browser.unregister(obj);
		return refundVoucher;
	}

	private String getAttributeValueByName(String name) {
		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".className", "inputwithrubylabel",
				".text", new RegularExpression("^" + name, false)));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't find attribute by Name - "
					+ name);
		}

		String text = objs[0].text().replace(name, "").trim();
		Browser.unregister(objs);

		return text;
	}

	public String getVoucherID() {
		IHtmlObject divObjs[] = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".className", "inputwithrubylabel",
				".text", new RegularExpression("^Voucher ID", false)));
		String text = browser.getTextFieldValue(
				Property.toPropertyArray(".className", "readonly"), divObjs[0]);
		String id = text.split(":")[0].trim();

		Browser.unregister(divObjs);
		return id;
	}

	public String getVoucherAvailableAmount() {
		return getAttributeValueByName("Available Amount").replace("$", "")
				.trim();
	}

	public String getVoucherCurrency() {
		return getAttributeValueByName("Currency");
	}

	public String getVoucherCustomer() {
		return getAttributeValueByName("Customer");
	}

	/**
	 * get refund amount
	 * 
	 * @return refund amount
	 */
	public String getRefundAmount() {
		IHtmlObject[] obj = this.getVoucherTable();
		String temp = obj[0].getProperty(".text").toString();
		String refundAmount = temp.substring(temp.indexOf("Refund Amount")
				+ "Refund Amount".length(), temp.indexOf("Currency"));
		Browser.unregister(obj);
		return refundAmount;
	}

	private IHtmlObject[] getVoucherTable() {
		RegularExpression regx = new RegularExpression("^Voucher Program.*",
				false);
		IHtmlObject[] obj = browser.getTableTestObject(".text", regx);
		return obj;
	}

	public String getRefundStatus() {
		IHtmlObject[] obj = this.getVoucherTable();
		String temp = obj[0].getProperty(".text").toString();
		String refundStatus = temp.substring(
				temp.indexOf("Refund Status") + "Refund Status".length(),
				temp.indexOf("Refund Amount")).trim();
		Browser.unregister(obj);
		return refundStatus;
	}

	/**
	 * Get the refund information
	 * 
	 * @return refund amount from refund table
	 */
	public String getRefundInfo() {
		RegularExpression regx = new RegularExpression(
				"Total Refund.*Cash Refund available to be Issued immediately.*",
				false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", regx);
		String temp = objs[objs.length - 1].getProperty(".text").toString();
		String prefix = "Refunds Total Refund";
		String refund = temp
				.substring(
						temp.indexOf(prefix) + prefix.length(),
						temp.indexOf("Cash Refund available to be Issued immediately") - 1);
		// double refund = getItemTotalAmount("Total Refund");

		Browser.unregister(objs);
		return refund;
	}

	/**
	 * get the reservation information
	 * 
	 * @param resNum
	 *            reservation number
	 * @return
	 */
	public String getReservationInfo(String resNum) {
		RegularExpression reg = new RegularExpression(
				"Reservation \\#: |Lottery Application \\# : " + resNum, false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		String resInfo = objs[objs.length - 1].getProperty(".text").toString();
		Browser.unregister(objs);
		return resInfo;
	}

	/**
	 * Get the qty of the applied specific fee
	 * 
	 * @param feetype
	 * @return
	 */
	public int getFeeTypeQty(String feetype) {
		int qty = 0;
		RegularExpression reg = new RegularExpression("^(Use Fee|" + feetype
				+ ").*", false);
		IHtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);
		for (int i = 0; i < feeTypeTable.length; i++) {
			if (feeTypeTable[i].getProperty(".text").toString()
					.indexOf(feetype) != -1) {
				qty++;
			}
		}

		return qty;
	}

	private Property[] feeAmountSpan() {// cell_currency_right
		return Property.toPropertyArray(".class", "Html.SPAN", ".className",
				new RegularExpression("cell_currency_right(Positive)?", false));
	}

	public HashMap<String, Double> getAllFeeTypesAmountsOfPOSOrder() {
		IHtmlObject objs[] = browser.getTableTestObject(".text",
				new RegularExpression("Subtotal", false));
		if (objs.length < 1)
			throw new ItemNotFoundException(
					"Cannot find POS order fee type table object.");
		IHtmlTable grid = (IHtmlTable) objs[2];

		IHtmlObject spanObjs[] = browser.getHtmlObject(feeAmountSpan());
		if (spanObjs.length < 1)
			throw new ItemNotFoundException(
					"Cannot find POS order fee amount objects.");

		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();

		String type = "";
		for (int i = 0; i < grid.rowCount(); i++) {
			type = grid.getCellValue(i, 0).trim();
			if (!StringUtil.isEmpty(type)) {
				if (type.equalsIgnoreCase("Subtotal")) {
					type = "Order Total";
				}

				String amt = spanObjs[i].text();
				boolean isNegative = false;
				if (amt.contains("(")) {
					amt = amt.replaceAll("\\(", StringUtil.EMPTY).replaceAll(
							"\\)", StringUtil.EMPTY);
					isNegative = true;
				}
				amt = amt.substring(1);
				Double amount = Double.parseDouble(amt);
				typeAmounts.put(type, isNegative ? -amount : amount);
			}
		}

		return typeAmounts;
	}

	public HashMap<String, Double> getAllFeeTypesAmounts() {
		String tranName = this.getTransactionType();
		if (tranName.contains("Purchase POS")) {
			return this.getAllFeeTypesAmountsOfPOSOrder();
		}

		IHtmlObject[] objs = this.getOrderTable();

		// get all fee amounts in Items section
		IHtmlObject spanObjs[] = browser
				.getHtmlObject(feeAmountSpan(), objs[1]);
		if (spanObjs.length < 1)
			throw new ItemNotFoundException(
					"Cannot find Fee amount span objects.");
		List<String> amounts = new ArrayList<String>();
		for (int i = 0; i < spanObjs.length; i++) {
			amounts.add(spanObjs[i].text());
		}
		Browser.unregister(spanObjs);

		IHtmlTable grid = (IHtmlTable) objs[1];
		int rowCount = grid.rowCount();
		HashMap<String, Double> typeAmounts = new HashMap<String, Double>();
		String feeType = "";
		int colIndexes[];
		boolean found = false;

		for (int i = 0; i < amounts.size(); i++) {

			found = false;

			for (int j = 0; j < rowCount; j++) {
				colIndexes = grid.findColumns(0, j, amounts.get(i));

				if (colIndexes != null && colIndexes.length > 0) {
					for (int k = 0; k < colIndexes.length; k++) {
						feeType = grid.getCellValue(j, colIndexes[k] - 1);

						if (!StringUtil.isEmpty(feeType)
								&& !typeAmounts.containsKey(feeType)) {
							if (feeType.equalsIgnoreCase("Total Price")) {
								return typeAmounts;
							}

							String amt = amounts.get(i);
							boolean isNegative = false;
							if (amt.contains("(")) {
								amt = amt.replaceAll("\\(", StringUtil.EMPTY)
										.replaceAll("\\)", StringUtil.EMPTY);
								isNegative = true;
							}
							amt = amt.substring(1);
							Double amount = Double.parseDouble(amt);
							typeAmounts.put(feeType, isNegative ? -amount
									: amount);

							if (feeType.equalsIgnoreCase("Order Total")
									|| feeType.equalsIgnoreCase("Subtotal")) {
								return typeAmounts;
							} else {
								found = true;
								break;
							}
						}
					}
				}

				if (found) {
					break;
				}
			}
		}
		Browser.unregister(objs);

		return typeAmounts;
	}

	public List<String> getFeeTypes() {
		List<String> feeTypeList = new ArrayList<String>();
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];
		int endRow = grid.findRow(3, "Subtotal") - 1;
		if (endRow < 0) {
			endRow = grid.findRow(0, "Subtotal") - 1;
		}
		feeTypeList.add(grid.getCellValue(4, 4));
		for (int i = 5; i < endRow; i++) {
			if (StringUtil.notEmpty(grid.getCellValue(i, 3))) {
				feeTypeList.add(grid.getCellValue(i, 3));
			}
		}
		return feeTypeList;
	}

	/**
	 * Get the qty of the applied specific fee
	 * 
	 * @param feetype
	 * @param siteNum
	 * @return
	 */
	public int getFeeTypeQty(String feetype, String siteNum) {
		int qty = 0;
		RegularExpression reg = new RegularExpression("^Customer( |)Name.*",
				false);
		IHtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);

		if (feeTypeTable.length < 1) {
			throw new ObjectNotFoundException("Can't find order cart table.");
		}
		IHtmlTable table = (IHtmlTable) feeTypeTable[1];
		int row = table.findRow(1, "Site: " + siteNum + "-" + siteNum);
		int startRow = row - 1;
		int endRow = table.findRow(row, 1, "Subtotal") - 2;

		for (int i = startRow; i <= endRow; i++) {
			String temp = String.valueOf(table.getRowValues(i));
			if (temp.contains(feetype)) {
				qty++;
			}
		}
		Browser.unregister(feeTypeTable);
		return qty;
	}

	/**
	 * Get the warning message of the vaild message
	 * 
	 * @return warning message
	 */
	public String getWarningMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.DIV",
				".className", new RegularExpression("^message.*", false));
		String warningMes = objs[0].getProperty(".text").toString();
		Browser.unregister(objs);

		return warningMes;
	}

	/**
	 * Get the different fee value by different fee type
	 * 
	 * @param feeType
	 * @return
	 */
	public double getFeeValue(String feeType) {
		OrmsOrderCartPage fmOrdCartPg = OrmsOrderCartPage.getInstance();
		int row = 1;
		// double feeValue = 0.00;
		row = fmOrdCartPg.getFeeTypeRow(feeType);
		if (feeType.equals("Subtotal")) {
			row = row - 1;
		}
		String feeValue = fmOrdCartPg.getFeeAmountValue(feeType);
		// if (feeType.equals("Access Pass")) {
		// int i = fee[row].indexOf("$");
		// int j = fee[row].indexOf(")");
		// String temp = fee[row].substring(i + 1, j);
		// feeValue = Double.parseDouble(temp);
		// } else {
		// int i = fee[row].indexOf("$");
		// String temp = fee[row].substring(i + 1);
		// feeValue = Double.parseDouble(temp);
		// }

		return Double.parseDouble(feeValue);
	}

	public double getAmountOwing() {
		return getAmountOwings()[0];
	}

	/**
	 * Get the owing amount
	 * 
	 */
	public double[] getAmountOwings() {
		return getSummaryAmounts("Amount Owing");
	}

	/**
	 * The method used to get amount of Cash Refund available to be Issued
	 * immediately
	 * 
	 * @return
	 */
	public double getImmediateIssuedRefundAmount() {
		return getSummaryAmount("Cash Refund available to be Issued immediately");
	}

	public double getSummaryAmount(String itemName) {
		return getSummaryAmounts(itemName)[0];
	}

	/**
	 * Get the amount values in the summary section
	 * 
	 * @param itemName
	 *            - can be "Amount Owing",
	 *            "Total Price","Total Past Paid","Minimum Payment Due"
	 * @return
	 */
	public double[] getSummaryAmounts(String itemName) {
		RegularExpression reg = new RegularExpression(itemName, false);
		Property[] p1 = Property.toPropertyArray(".class", "Html.DIV",
				".className", "div_info");
		Property[] p2 = Property.toPropertyArray(".class", "Html.TR", ".text",
				reg);

		IHtmlObject[] objs = browser.getHtmlObject(Property.atList(p1, p2));

		if (objs.length < 1) {
			throw new ObjectNotFoundException("Can't find " + itemName);
		}

		double amounts[] = new double[objs.length];
		for (int i = 0; i < objs.length; i++) {

			String text = objs[i].getProperty(".text");

			String[] tokens = RegularExpression.getMatches(text,
					"(\\d+,)*\\d+\\.\\d+|--");

			if (tokens.length > 0 && tokens[0].contains("--")) {
				tokens[0] = "0.00";
			}

			amounts[i] = Double.parseDouble(tokens[0].replace(",",
					StringUtil.EMPTY));
		}

		return amounts;
	}

	public double getOrderTotalSum() {
		return getItemTotalAmount("Order Total");
	}

	public double getItemTotalAmount(String itemName) {
		IHtmlObject[] objs = this.getOrderTable();
		double amount = 0;

		String text = objs[0].getProperty(".text");
		Browser.unregister(objs);

		String reg = itemName + " +\\(?\\$\\d+\\.\\d+\\)?";
		String[] tokens = RegularExpression.getMatches(text, reg);
		for (String token : tokens) {
			String str = token.replaceAll((itemName + " +\\(?\\$" + "|\\)?"),
					"");
			amount += Double.parseDouble(str);
		}
		return amount;
	}

	public double getTotalVoucherAmount() {
		return getItemTotalAmount("Total Voucher Payment");
	}

	/**
	 * Get the total price
	 * 
	 */
	public BigDecimal getTotalPrice() {
		// james[20130711]: make changes to handle the total amount contains ","
		// like 1,144.14
		String total = this.getTotalPriceAmount().replaceAll(",", "");
		BigDecimal amount = new BigDecimal(total);
		return amount;
	}

	/**
	 * Get the total price
	 * 
	 */
	public String getTotalPriceAmount() {
		this.waitLoading();
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Total Price.*", false));
		String temp = objs[0].text();
		String total = temp.substring(temp.indexOf("Total Price")
				+ "Total Price".length(), temp.indexOf("Total Past Paid"));

		if (!total.equalsIgnoreCase("--")) {
			total = total.replace("$", "");
		}

		Browser.unregister(objs);
		return total.trim();
	}

	public String getPriceByFeeType(String feeType) {
		RegularExpression reg = new RegularExpression("^CustomerName.*", false);
		IHtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);

		if (feeTypeTable.length < 1) {
			throw new ObjectNotFoundException("Can't find order cart table.");
		}
		IHtmlTable table = (IHtmlTable) feeTypeTable[1];
		System.out.println("table content41:" + table.getCellValue(4, 1));
		System.out.println("table content42:" + table.getCellValue(4, 2));
		System.out.println("table content43:" + table.getCellValue(4, 3));
		System.out.println("table content53:" + table.getCellValue(5, 3));
		System.out.println("table content63:" + table.getCellValue(6, 3));
		System.out.println("table content64:" + table.getCellValue(6, 4));
		int row = table.findRow(4, feeType);
		System.out.println("row for " + feeType + ":" + row);
		int startRow = row;
		int endRow = table.findRow(row, 4, "Subtotal") - 2;
		System.out.println("row for subtotal:" + endRow);
		String temp = "";
		for (int i = startRow; i <= endRow; i++) {
			temp = String.valueOf(table.getRowValues(i));
			System.out.println("temp:" + temp);
			if (temp.contains(feeType)) {
				break;
			}
		}
		Browser.unregister(feeTypeTable);

		return temp;
	}

	/**
	 * Get the total price on the order cart in License Manager
	 * 
	 */
	public String getTotalPriceAmountInLM() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Customer Name.*", false));
		IHtmlTable totals = (IHtmlTable) objs[1];
		// int col = totals.findColumn(3, "Price");
		String total = "";
		for (int i = 0; i < totals.rowCount(); i++) {
			List<String> row = totals.getRowValues(i);
			if (row.contains("Total Price")) {
				for (String str : row) {
					if (str.matches("^\\$.*")) {
						total = str.replaceAll("\\$", StringUtil.EMPTY);
						break;
					}
				}
			}
		}
		Browser.unregister(objs);
		return total;
	}

	/**
	 * 
	 * get Total Minimum Payment Due amount (written based on FM order cart
	 * page)
	 * 
	 * @return
	 */
	public BigDecimal getMinimumPaymentDueFM() {

		// FldMgrHomePage fmHomePg = FldMgrHomePage.getInstance();
		IHtmlObject[] objs = browser
				.getHtmlObject(".class", "Html.TR", ".text",
						new RegularExpression("^Minimum Payment Due.*", false));

		if (objs == null || objs.length < 1) {
			throw new ObjectNotFoundException(
					"Can't find Mininum payment due tr object.");
		}

		String minimumPay = objs[0].text().replaceAll("\\$", "")
				.replaceAll("[A-Za-z]", "").trim();

		logger.info("Minimum Payment Due get from page is" + minimumPay);
		Browser.unregister(objs);
		return new BigDecimal(minimumPay);
	}

	/**
	 * check whether this order is a minimum payment allowed order or not.
	 * 
	 * @return: true: this is a minimum payment allowed order;
	 * 
	 */
	public boolean isMinimumPayOrder() {
		BigDecimal totalPrice = getTotalPrice();
		BigDecimal miniPayDue = getMinimumPaymentDueFM();
		if (totalPrice.compareTo(miniPayDue) == 0) {
			throw new ErrorOnDataException(
					"This order don't allow minimun payment.");
		} else {
			logger.info("This is a minimum payment allowed order.");
			return true;
		}

	}

	/**
	 * Get the total past paid
	 * 
	 */
	public BigDecimal getTotalPastPaid() {
		double pastPaid = this.getSummaryAmount("Total Past Paid");
		logger.info("The total past paid is " + pastPaid);
		// BigDecimal amount = new BigDecimal(pastPaid).setScale(2);
		// update by lesley, because the exception
		// "java.lang.ArithmeticException: Rounding necessary" is always thrown
		// if not set the rounding
		BigDecimal amount = new BigDecimal(pastPaid).setScale(2,
				RoundingMode.HALF_UP);
		return amount;
	}

	/**
	 * Get the minimum payment due
	 * 
	 */
	public double getMinimumPaymentDue() {
		return getSummaryAmount("Minimum Payment Due");
	}

	/**
	 * Get the minimum payment due
	 * 
	 */
	public BigDecimal getTotalRefund() {
		String total = "0";
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Total Refund.*", false));
		if (objs.length > 0) {
			// james[20130711]: old implementation could not safely get Total
			// Refund amount
			IHtmlTable table = (IHtmlTable) objs[objs.length - 1];
			RegularExpression pattern = new RegularExpression(
					"Total Refund \\(\\$\\d+\\.\\d{2}\\)", false);
			total = pattern.getMatches(table.text())[0].replaceAll(",", "");
			pattern = new RegularExpression("\\d+\\.\\d{2}", false);
			total = pattern.getMatches(total)[0];
			Browser.unregister(objs);
		}
		BigDecimal amount = new BigDecimal(total);
		return amount;
	}

	public String getNegotiatedWarningMsg() {
		IHtmlObject[] error = browser.getHtmlObject(".id", "NOTSET");
		String msg = error[0].getProperty(".text");
		return msg;
	}

	public void selectVoidReason() {
		browser.selectRadioButton(".id", "reason", 4);
	}

	public String getMiniRuleErrorMessage() {
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				new RegularExpression("V-100002|V-100004", false));
		String miniRuleMessage = objs[0].getProperty(".text");
		Browser.unregister(objs);

		return miniRuleMessage;
	}

	/**
	 * Get the error message for rules' validation
	 * 
	 * @return
	 */
	public String getErrorMessage() {
		IHtmlObject frame[] = browser.getFrame("order");
		Property property[] = new Property[1];
		property[0] = new Property(".id", "statusMessages");
		IHtmlObject objs[];
		if (frame.length > 0) {
			objs = browser.getTableTestObject(property, frame[0]);
		} else {
			objs = browser.getTableTestObject(property);
		}
		// HtmlObject objs[] = browser.getTableTestObject(property, frame[0]);
		String statusMsg = "";

		if (objs.length > 0) {
			statusMsg = ((IHtmlTable) objs[0]).getProperty(".text").trim();
		}
		Browser.unregister(objs);
		return statusMsg;
	}

	/**
	 * If the reservation charge pos information, get the reservation detail
	 * information of it
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getChargePosCellValue(int row, int col) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^Reservation #.*", false));
		IHtmlTable table = (IHtmlTable) objs[1];

		String toReturn = table.getCellValue(row, col).toString();
		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Get the text value of 'All or a portion of the payment will be mailed in
	 * by:' attribute
	 * 
	 * @return
	 */
	public String getPaymentReceivedDate() {
		IHtmlObject objs[] = browser
				.getHtmlObject(
						".class",
						"Html.INPUT.text",
						".id",
						new RegularExpression(
								"EXPECTED_PAYMENT_DATE.NEW(_PERMIT|_TOUR|)_ORDER.0_0_ForDisplay",
								false));
		String toReturn = "";

		if (objs.length > 0) {
			toReturn = (objs[0]).getProperty(".value");
		}

		Browser.unregister(objs);
		return toReturn;
	}

	/**
	 * Check whether a specific payment type exists in the payment method drop
	 * down list or not
	 * 
	 * @param paymentType
	 * @return
	 */
	public boolean checkPaymentTypeExists(String paymentType) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.SELECT",
				".id", new RegularExpression("paymentMethod_\\d+_0", false));
		List<String> options = new ArrayList<String>();

		boolean existent = false;
		if (objs.length > 0) {
			options = ((ISelect) objs[0]).getAllOptions();
		}
		for (String str : options) {
			if (str.trim().equalsIgnoreCase(paymentType)) {
				existent = true;
				break;
			}
		}

		Browser.unregister(objs);
		return existent;
	}

	/**
	 * Click Purchase Privilege button
	 */
	public void clickPurchasePrivilege() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Purchase (Privilege|Licence)", false));
	}

	/**
	 * Click Purchase Consumables button
	 */
	public void clickPurchaseConsumables() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Purchase Consumables");
	}

	/**
	 * Click Request Inspection button
	 */
	public void clickRequestInspection() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Request Inspection");
	}

	/**
	 * Select new vehicle
	 * 
	 * @param vehicle
	 */
	public void selectNewVehicle(String vehicle) {
		browser.selectDropdownList(".id", new RegularExpression(
				"VehicleSearchCriteria-\\d+\\.vehicleTypeIDForNewVehicle",
				false), vehicle, true);
	}

	/**
	 * Click Go button to search new vehicle
	 */
	public void clickGo() {
		browser.clickGuiObject(".class", "Html.A", ".href",
				new RegularExpression(".*OrderList\\.do", false));
	}

	/**
	 * Click Go button to add new vehicle for registration
	 */

	public void clickGoAddNewVehicleForRegistration() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Go");
	}

	public void clickVehicleInfoLink(String vehicleType, String hullIdSerialNum) {
		browser.clickGuiObject(".class", "Html.A", ".text", vehicleType + " "
				+ hullIdSerialNum);
	}

	/**
	 * Get the order table object for further verification
	 * 
	 * @return
	 */
	public IHtmlObject[] getOrderTable() {
		// System.out.println("Length:" + browser.getTableTestObject(".text",
		// new RegularExpression("^Customer.*", false)).length);
		return browser.getTableTestObject(".text", new RegularExpression(
				"^Customer|Event( )?Event ID.*", false));

		// ITable table = null;
		// if (objs.length > 1) {
		// table = (ITable) objs[1];
		// }
		//
		// return table;
	}

	public List<String> getOrderItemDetails() {
		List<String> details = new ArrayList<String>();
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TD",
				".className", "ocTable rubyTable label_tbodyClear");
		for (IHtmlObject o : objs) {
			if (null != o.text() && o.text().length() > 0) {
				details.add(o.text());
			}
		}
		Browser.unregister(objs);
		return details;
	}

	public boolean checkCustInfoOrVehicleInfoWhetherExistingAtItemPart(
			String info) {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class",
				"Html.TD", ".className", new RegularExpression(
						"(ocTable rubyTable )?label_tbodyClear", false),
				".text", new RegularExpression(info,false)));
	}

	/**
	 * Remove item in the cart page in License Manager
	 * 
	 * @param privilegeName
	 */
	public void removePrivItemInCartPg(String privilegeName) {
		// IHtmlObject[] objs = browser.getHtmlObject(".className",
		// "orderItemDetail", ".text", new RegularExpression(".*"
		// + privilegeName + ".*", false));
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression(".*" + privilegeName + ".*", false));
		browser.clickGuiObject(".class", "Html.A", ".text", "Remove", false, 0,
				objs[objs.length - 1]);// 30501 Nicole
		ajax.waitLoading();
		Browser.unregister(objs);
	}

	/**
	 * get an individual privilege item qty in cart
	 * 
	 * @param index
	 * @return
	 */
	public int getProductItemQtyInCart(int index) {
		RegularExpression pattern = new RegularExpression("^\\d+$", false);
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.TD", ".className", "label_tbodyClear", ".text",
				pattern));
		if (objs.length < index + 1) {
			throw new ItemNotFoundException("Failed to find product qty");
		}
		String qty = objs[index].text();

		Browser.unregister(objs);

		return Integer.parseInt(qty);
	}

	public void verifyProductItemQty(int index, int qty) {
		if (!MiscFunctions.compareResult("Product Item Qty", qty,
				this.getProductItemQtyInCart(index)))
			throw new ErrorOnPageException("Qty is not correct.");
	}

	public void verifyProductItemQty(int qty) {
		verifyProductItemQty(0, qty);
	}

	/**
	 * Get privilege total qty in order cart page
	 * 
	 * @param privilegeName
	 * @param licenseYear
	 * @return
	 */
	public int getPriviQtyInCartPg(String privilegeName, String licenseYear) {
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression(".*\\(" + licenseYear + "\\)"
						+ privilegeName + ".*", false));
		int qty = 0;
		if (objs.length > 1) {
			for (int i = 1; i < objs.length; i++) {
				String text = objs[i].getProperty(".text");

				String[] tempt = null;
				if (text.contains("State Fee")) {
					tempt = text.split("State Fee")[0].trim().split(" "); // get
																			// qty
																			// through
																			// state
																			// fee
																			// split
				} else if (text.contains("StateFee")) {
					tempt = text.split("StateFee")[0].trim().split(" ");
				} else {
					tempt = text.split(" ");
				}

				if (tempt != null && tempt.length > 0) {
					logger.info("tempt length:" + tempt.length);
					if (NumberUtil.isInteger(tempt[tempt.length - 1])) {
						qty = qty + Integer.parseInt(tempt[tempt.length - 1]);
					}
				}
			}
		} else {
			qty = 0;
			// throw new
			// ItemNotFoundException("Failed to get privilege qty for '"+privilegeName+"' of "+licenseYear);
		}

		Browser.unregister(objs);

		return qty;
	}

	/**
	 * update payment amount using total price in order cart in License Manager
	 */
	public void updatePaymentAmounts() {
		String amount = this.getTotalPriceAmountInLM();
		this.setPaymentAmount(amount);
	}

	/**
	 * The method used to get order transaction name,such as New
	 * reservation/duplicate privilege/transfer
	 * 
	 * @return
	 */
	public String getFirstTransactionName() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];
		String startText = grid.getCellValue(0, 0);
		String ordName = "";
		int tranNameRow = -1;
		if (startText.contains("Event")) {
			tranNameRow = 5;
		} else {
			tranNameRow = 4;
		}
		ordName = grid.getCellValue(tranNameRow, 1);

		// ordName = grid.getCellValue(4, 1);
		Browser.unregister(objs);
		return ordName;
	}

	/**
	 * The method used to get slip reservation number info
	 * 
	 * @return
	 */
	public String getSlipReservationNum() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];

		String resNum = grid.getCellValue(5, 0);
		if(resNum.matches(".*\\#(\\s)?\\:(\\s)?M\\d\\-\\d+")){
			resNum = resNum.split("\\:")[1].trim();
		}
		else if (!resNum.matches("M\\d\\-\\d+")) {
			resNum = grid.getCellValue(4, 1).split("\\:")[1].trim();
		}
		Browser.unregister(objs);
		return resNum;
	}

	/**
	 * The method used to get slip reservation location info
	 * 
	 * @return
	 */
	public String getSlipReservationLocationInfo() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];

		String location = grid.getCellValue(5, 0);
		if (StringUtil.isEmpty(location) || !location.startsWith("Marina:")) {
			location = grid.getCellValue(6, 0);
		}
		location = location.split(":")[1].trim();

		Browser.unregister(objs);
		return location;
	}

	public void setShippingAddress(String shippingAdd) {
		browser.setTextField(".id", new RegularExpression(
				"AddressView-\\d+.address", false), shippingAdd);
	}

	public String getShippingAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"AddressView-\\d+.address", false));
	}

	public void setShippingSuppAddress(String shippingSuppAdd) {
		browser.setTextField(".id", new RegularExpression(
				"AddressView-\\d+\\.supplemental", false), shippingSuppAdd);
	}

	public String getShippingSuppAddress() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"AddressView-\\d+\\.supplemental", false));
	}

	public void setShippingAddrZip(String shippingAddZip) {
		browser.setTextField(".id", new RegularExpression(
				"AddressView-\\d+.zipCode", false), shippingAddZip);
	}

	public String getShippingAddrZip() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"AddressView-\\d+.zipCode", false));
	}

	public void selectShippingAddrCountry(String shippingCountry) {
		browser.selectDropdownList(".id", new RegularExpression(
				"AddressView-\\d+.country", false), shippingCountry);
	}

	public String getShippingAddrCountry() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"AddressView-\\d+.country", false));
	}

	public String getShippingAddrStatus() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"AddressView-\\d+\\.verificationStatus\\.name", false));
	}

	public void setShippingAddrCity(String shippingCity) {
		browser.setTextField(".id", new RegularExpression(
				"AddressView-\\d+.city", false), shippingCity);
	}

	public String getShippingAddrCity() {
		return browser.getTextFieldValue(".id", new RegularExpression(
				"AddressView-\\d+.city", false));
	}

	public void selectShippingAddrState(String shippingState) {
		browser.selectDropdownList(".id", new RegularExpression(
				"AddressView-\\d+.state", false), shippingState);
	}

	public String getShippingAddrState() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"AddressView-\\d+.state", false));
	}

	public void selectShippingAddrCounty(String shippingCounty) {
		browser.selectDropdownList(".id", new RegularExpression(
				"AddressView-\\d+.county", false), shippingCounty);
	}

	public String getShippingAddrCounty() {
		return browser.getDropdownListValue(".id", new RegularExpression(
				"AddressView-\\d+.county", false));
	}

	public boolean verifyTransactionName(String info) {
		String actValue = this.getFirstTransactionName();
		if (actValue.contains("(New") && !info.contains("(New")) {
			actValue = actValue.split("\\(New")[0].trim();
		}
		if (info.equals(OrmsConstants.TRANNAME_CHARGE_POS)) {
			return actValue.contains(info);
		}
		if (!info.equals(actValue)) {
			logger.error("Transaction name should be '" + info
					+ "', but actually is '" + actValue + "'.");
			return false;
		} else {
			// logger.info("Transaction name is correct as: " + actValue);
			return true;
		}
	}

	public boolean verifySlipResvationOrderNum(String orderNumInfo) {
		String slipOrderNumInfo = this.getSlipReservationNum();
		if (!slipOrderNumInfo.contains(orderNumInfo)) {
			logger.error("Displayed Slip Reservation Order Number should be '"
					+ orderNumInfo + "', but actuallly is '" + slipOrderNumInfo
					+ "'.");
			return false;
		} else {
			logger.info("Displayed slip reservation order number is correct as:"
					+ orderNumInfo);
			return true;
		}
	}

	public void verifyFacility(String facility) {
		if (!this.compareFacility(facility))
			throw new ErrorOnPageException("Facility in NOT correct.");
	}

	public boolean compareFacility(String facilityName) {
		String facilityLocationInfo = this.getSlipReservationLocationInfo();
		String facility = facilityName.split(":").length > 1 ? facilityName
				.split(":")[1].trim() : facilityName.trim();
		if (!facilityLocationInfo.contains(facility)) {
			logger.error("Displayed  facility name of the location associated with the Slip Reservation Order should be '"
					+ facility
					+ "', but actuallly is '"
					+ facilityLocationInfo
					+ "'.");
			return false;
		} else {
			logger.info("Displayed facility name of the location associated with the Slip Reservation Order is correct as:"
					+ facility);
			return true;
		}
	}

	public boolean compareSlipOrderItemInfo(SlipInfo slip,
			String occupantLName, String occupantFName) {
		List<String> itemInfo = this.getOrderItemDetails();
		boolean result = true;
		result &= MiscFunctions.compareResult("Dock/Area",
				slip.getParentDockArea(), itemInfo.get(1).split(":")[1].trim());
		result &= MiscFunctions.compareResult("Slip", slip.getCode() + "-"
				+ slip.getName(), itemInfo.get(6).split(":")[1].trim());
		String checkInTime = "";
		if (!StringUtil.isEmpty(slip.getCheckInHour())) {
			String hour = Integer.parseInt(slip.getCheckInHour()) < 10 ? slip
					.getCheckInHour().split("0")[1].trim() : slip
					.getCheckInHour();
			checkInTime = hour + ":" + slip.getCheckInMinute() + " "
					+ slip.getCheckInAmPm();
			result &= MiscFunctions.compareResult("Check-In Time", checkInTime,
					itemInfo.get(9).split("Time:")[1].trim());
		}

		String checkOutTime = "";
		if (!StringUtil.isEmpty(slip.getCheckOutHour())) {
			String hour = Integer.parseInt(slip.getCheckOutHour()) < 10 ? slip
					.getCheckOutHour().split("0")[1].trim() : slip
					.getCheckOutHour();
			checkOutTime = hour + ":" + slip.getCheckOutMinute() + " "
					+ slip.getCheckOutAmPm();
			result &= MiscFunctions.compareResult("Check-Out Time",
					checkOutTime, itemInfo.get(10).split("Time:")[1].trim());
		}
		int occupantIndex = 0;
		int arrivalDateIndex = 0;
		int departureDateIndex = 0;
		int unitsIndex = 0;
		for (int i = 0; i < itemInfo.size(); i++) {
			if (itemInfo.get(i).contains("Occupant:")) {
				occupantIndex = i;
			} else if (itemInfo.get(i).contains("Arrive:")) {
				arrivalDateIndex = i;
			} else if (itemInfo.get(i).contains("Depart:")) {
				departureDateIndex = i;
			} else if (itemInfo.get(i).contains("Nights:")
					|| itemInfo.get(i).contains("Months:")) {
				unitsIndex = i;
			}
		}
		result &= MiscFunctions.compareResult("Occupant", occupantLName + ","
				+ occupantFName,
				itemInfo.get(occupantIndex).split(":")[1].trim());
		result &= MiscFunctions.compareResult("Arrival Date", slip
				.getArrivalDate(), itemInfo.get(arrivalDateIndex).split(":")[1]
				.trim());
		result &= MiscFunctions.compareResult("Departure Date", slip
				.getDepartureDate(), itemInfo.get(departureDateIndex)
				.split(":")[1].trim());

		if (slip.getReservationType().equalsIgnoreCase(
				OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Nights", slip.getNights(),
					Integer.parseInt(itemInfo.get(unitsIndex).split(":")[1]
							.trim()));
		} else if (slip.getReservationType().equalsIgnoreCase(
				OrmsConstants.SLIP_RESERVATION_TYPE_TRANSIENT)) {
			result &= MiscFunctions.compareResult("Months", slip.getMonths(),
					Integer.parseInt(itemInfo.get(unitsIndex).split(":")[1]
							.trim()));
		}
		return result;
	}

	public void verifySlipOrderItemInfo(SlipInfo slip, String occupantLName,
			String occupantFName) {
		if (!compareSlipOrderItemInfo(slip, occupantLName, occupantFName))
			throw new ErrorOnPageException(
					"Slip order item info is NOT correct.");
	}

	public void clickValidateButton() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Validate");
	}

	// public void selectCopyMaillingAddressCheckBox(){
	// browser.selectCheckBox(".id", new
	// RegularExpression("ShippingInfoView-\\d+.copyMailingAddress",false));
	// }

	public void setShippingAddressInfo(Address shippingAddress) {
		System.out.println(shippingAddress.address);
		if (null != shippingAddress.address
				&& shippingAddress.address.length() > 0) {
			this.setShippingAddress(shippingAddress.address);
		}

		if (null != shippingAddress.supplementalAddr
				&& shippingAddress.supplementalAddr.length() > 0) {
			this.setShippingSuppAddress(shippingAddress.supplementalAddr);
		}
		if (null != shippingAddress.country
				&& shippingAddress.country.length() > 0) {
			this.selectShippingAddrCountry(shippingAddress.country);
			ajax.waitLoading();
		}

		if (null != shippingAddress.zip && shippingAddress.zip.length() > 0) {
			this.setShippingAddrZip(shippingAddress.zip);
		}

		if (null != shippingAddress.state && shippingAddress.state.length() > 0) {
			this.selectShippingAddrState(shippingAddress.state);
			ajax.waitLoading();
		}

		if (null != shippingAddress.county
				&& shippingAddress.county.length() > 0) {
			this.selectShippingAddrCounty(shippingAddress.county);
			ajax.waitLoading();
		}

		if (null != shippingAddress.city && shippingAddress.city.length() > 0) {
			this.setShippingAddrCity(shippingAddress.city);
		}

		if (shippingAddress.isValidate) {
			this.clickValidateButton();
			ajax.waitLoading();
		}

	}

	public boolean verifyProductItemInfo(String info) {
		// List<String> itemDetails = this.getOrderItemDetails();
		// if(!itemDetails.get(0).contains(info)){
		// logger.error("Vehicle item info should be " + info
		// + ", but actually is " + itemDetails.get(0));
		// return false;
		// }else {
		// logger.info("Vehicle item info is correct.");
		// return true;
		// }
		return verifyProductItemInfo(info, 1);
	}

	/**
	 * Verify the info of the order item with the given index.
	 * 
	 * @param info
	 * @param index
	 * @return
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public boolean verifyProductItemInfo(String info, int index) {
		List<String> itemDetails = this.getOrderItemDetails();
		if (!itemDetails.get(index).contains(info)) {
			logger.error("Vehicle item info should be " + info
					+ ", but actually is " + itemDetails.get(0));
			return false;
		} else {
			logger.info("Vehicle item info is correct.");
			return true;
		}
	}

	public boolean verifyReversTransMarkInfo(String info) {
		boolean existing = browser.checkHtmlObjectExists(Property
				.toPropertyArray(".class", "Html.SPAN", ".className",
						"smallRedLabel", ".text", info));
		if (!existing) {
			logger.error("Expect '" + info + "' info should existing.");
		} else {
			logger.info("'" + info + "' existing");
		}

		return existing;
	}

	public boolean compareBillingCustomerInfo(Customer cust) {
		boolean result = true;
		String fullName = this.getCustName();
		String fName, lName = "";
		if (fullName.contains(",")) {
			String lfNames[] = fullName.split(",");
			fName = lfNames[1];
			lName = lfNames[0];
		} else {
			String flNames[] = fullName.split(" ");
			fName = flNames[0];
			lName = flNames[1];
		}

		result &= MiscFunctions.compareResult("Billing Customer Last Name",
				cust.lName, lName);
		result &= MiscFunctions.compareResult("Billing Customer First Name",
				cust.fName, fName);
		if (!StringUtil.isEmpty(cust.hPhone)) {
			result &= MiscFunctions.compareResult("Billing Customer Phone",
					cust.hPhone, this.getCustPhone());
		}
		if (!StringUtil.isEmpty(cust.zip)) {
			result &= MiscFunctions.compareResult("Billing Customer Zip",
					cust.zip, this.getCustZip());
		}
		return result;
	}

	public void verifyBillingCustomerInfo(Customer cust) {
		if (!compareBillingCustomerInfo(cust))
			throw new ErrorOnPageException(
					"Billing Customer info(Name, Phone, Zip) are not correct.");
	}

	public boolean verifyCustomerInfo(Customer cust, String fromOrTo) {
		String expValue;
		boolean result = true;

		if (null != fromOrTo && fromOrTo.length() > 0) {
			expValue = fromOrTo;
		} else {
			expValue = "";
		}

		if (!StringUtil.isEmpty(cust.businessName)) {
			expValue = cust.businessName + "(\\s)?\\(" + cust.custNum + "\\)";
		} else {
			if (StringUtil.isEmpty(cust.custNum)
					&& !StringUtil.isEmpty(cust.licenseNum)) {
				cust.custNum = cust.licenseNum;
			}
			expValue = expValue + cust.lName + "," + cust.fName;
			if (!StringUtil.isEmpty(cust.custNum)) {
				expValue += "(\\s)?\\(" + cust.custNum + "\\)";
			}
		}
		result &= this.checkCustInfoOrVehiclInfo(expValue);

		if (!StringUtil.isEmpty(cust.residencyStatus)) {
			result &= this.checkCustInfoOrVehiclInfo(cust.residencyStatus);
		}

		// customer address should default to Mailing Address.
		// If the mailing address doesn't exist, use customer's physical address
		if (!StringUtil.isEmpty(cust.mailingAddr.address)) {
			expValue = cust.mailingAddr.address;
			if (null != cust.mailingAddr.supplementalAddr
					&& cust.mailingAddr.supplementalAddr.length() > 0) {
				expValue = expValue + ", " + cust.mailingAddr.supplementalAddr;
			}

			result &= this.checkCustInfoOrVehiclInfo(expValue);
		}

		if (!StringUtil.isEmpty(cust.mailingAddr.city)) {
			String shortName = this.convertStateName(cust.mailingAddr.state);
			expValue = cust.mailingAddr.city + ", " + shortName + ", "
					+ cust.mailingAddr.zip;
			result &= this.checkCustInfoOrVehiclInfo(expValue);
		}

		return result;
	}

	private String convertStateName(String from) {
		String to = "";
		String stateLongName[] = new String[] { "Alabama", "New York",
				"Mississippi", "Saskatchewan" };
		String stateShortName[] = new String[] { "AL", "NY", "MS", "SK" };

		if (from.length() == 2) {
			for (int i = 0; i < stateShortName.length; i++) {
				if (from.equals(stateShortName[i])) {
					to = stateLongName[i];
					break;
				}
			}
		} else {
			for (int i = 0; i < stateLongName.length; i++) {
				if (from.equals(stateLongName[i])) {
					to = stateShortName[i];
					break;
				}
			}
		}
		if (to.length() < 1) {
			throw new ItemNotFoundException("State Name " + from);
		}

		return to;
	}

	public boolean checkCustInfoOrVehiclInfo(String info) {
		boolean existing = this
				.checkCustInfoOrVehicleInfoWhetherExistingAtItemPart(info);

		if (!existing) {
			logger.error("Expect '" + info + "' info should existing.");
		} else {
			logger.info("'" + info + "' existing");
		}

		return existing;
	}

	/**
	 * The method used to get order type, such as Privilege Sale(New - 1)
	 * 
	 * @return
	 */
	public String getFirstOrderType() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];

		String ordName = grid.getCellValue(5, 0);
		Browser.unregister(objs);
		return ordName;
	}

	/**
	 * Return value like: Transfer Same Facility - Same Value Void Order and so
	 * on...
	 * 
	 * @return
	 */
	public String getTransactionType() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable grid = (IHtmlTable) objs[1];

		String transactionType = grid.getCellValue(4, 1);
		Browser.unregister(objs);
		return transactionType;
	}

	/**
	 * Get all transaction names, customer names, and corresponding order total
	 * amounts
	 * 
	 * @return
	 */
	private Map<String, String> getAllTransactionsCustnamesOrderTotals() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", objs[1]);
		IHtmlObject checkboxes[] = null;
		Map<String, String> transactionCustOrerTotal = new TreeMap<String, String>();// sorting
		String transactionName = "";
		String custName = "";
		String orderTotal = "";
		String temp = "";
		for (int i = 0; i < trs.length; i++) {
			checkboxes = browser
					.getHtmlObject(
							".id",
							new RegularExpression(
									"SELECTED\\_ORDER\\.NEW\\_ORDERVIEW\\.\\d+\\_(NEW\\_ORDER_\\d+|[0-9]-\\d+)",
									false), trs[i]);
			if (checkboxes.length > 0) {
				transactionName = trs[i].getChildren()[1].text().trim();
				if (transactionName
						.equals(OrmsConstants.TRANNAME_TRANSFER_PRIVILEGE)) {
					custName = trs[i + 8].getChildren()[0].text().trim()
							.split(":")[1].trim();
				} else {
					custName = trs[i + 4].getChildren()[0].text().trim();
				}
			}
			temp = trs[i].text().trim();
			if (temp.contains("Order Total $")) {
				orderTotal = temp.split("Order Total")[1].trim();
			}
			if (transactionName.length() > 0 && orderTotal.length() > 0) {
				transactionCustOrerTotal.put(transactionName + "+" + custName,
						orderTotal);
			}
		}

		Browser.unregister(objs);
		Browser.unregister(trs);
		return transactionCustOrerTotal;
	}

	/**
	 * The method used to click reservation transaction name
	 * 
	 * @param tranName
	 */
	public void clickTransactionName(Object tranName) {
		browser.clickGuiObject(".class", "Html.A", ".text", tranName);
	}

	/**
	 * For single order item Get order level transaction fee amount
	 * 
	 */
	public String getOrderLevelTransFeeAmount() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("(Subtotal|Sub Total).*", false));
		if (objs.length < 0) {
			throw new ErrorOnPageException(
					"Could not get sub total value on page");
		}
		String temp = objs[0].text();

		temp = temp.substring(temp.lastIndexOf("Sub Total"));

		if (temp.lastIndexOf("Transaction Fee") < 0) {
			return null;// no order level transaction fee
		}

		String total = temp.substring(
				temp.lastIndexOf("Transaction Fee")
						+ "Transaction Fee".length(),
				temp.indexOf("Order Total")).replace("$", "");

		Browser.unregister(objs);
		return total.trim();
	}

	/**
	 * For multiple orderItems Get fee type row by item num
	 * 
	 * @param orderItem
	 * @return
	 */
	// private int getFeeTypeRowByItem(int orderItem) {
	// RegularExpression reg = new
	// RegularExpression("^(Adult|Youth|Transaction Fee|Subtotal|Interagency Access Pass).*",
	// false);
	//
	// HtmlObject[] feeTypeTable = browser.getTableTestObject(".text", reg);
	// // System.out.println("subtotal nums:"+feeTypeTable.length);
	// if(orderItem<0 || orderItem>=feeTypeTable.length){
	// throw new ItemNotFoundException();
	// }
	//
	// // MiscFunctions.dumpTable((ITable)feeTypeTable[orderItem]);
	// int row = ((ITable) feeTypeTable[orderItem]).findRow(0,
	// "Transactin Fee");
	// Browser.unregister(feeTypeTable);
	//
	// return row;
	// }
	
	public String getTaxValueByItem(int orderItem) {
		IHtmlObject[] objs;
		String value;
		// Comments codes of 'else' branch for upgrade QA3 as 3.02 build
		// if(MiscFunctions.isQA24()) {
		RegularExpression reg = new RegularExpression("^Customer.*", false);
		objs = browser.getTableTestObject(".text", reg);
		String tableText = objs[0].getProperty(".text").toString();
		// remove order level transaction fee
		RegularExpression tmpPattern = new RegularExpression(
				"Tax\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?\\s+(Order Total)\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?",
				false);
		String tmp[] = tmpPattern.getMatches(tableText);

		// define expression for order item level transaction fee
		RegularExpression feeTypePattern = new RegularExpression(
				"Tax\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?\\s?", false);
		String[] tokens = null;

		if (tmp.length > 0) {
			String str = tableText.substring(0, tableText.indexOf(tmp[0]));
			tokens = feeTypePattern.getMatches(str);
		} else {
			logger.info("There is no order level transaction fee on order cart page");
			tokens = feeTypePattern.getMatches(tableText);
		}

		if (tokens.length > 0 && orderItem <= tokens.length - 1) {
			RegularExpression amountPattern = new RegularExpression(
					"\\d+\\.\\d{2}", false);
			tokens = amountPattern.getMatches(tokens[orderItem]);
			value = tokens[0].trim();
		} else {
			logger.info("There is no order item level transaction fee on order cart page");
			return null;
		}
		
		Browser.unregister(objs);
		return value;
	}

	/**
	 * In one order, for multiple orderItems Get fee amount for specific fee
	 * type QA24 order cart page, need to be done
	 * 
	 * @param orderItem
	 * @return
	 */
	public String getTransFeeValueByItem(int orderItem) {
		IHtmlObject[] objs;
		String value;
		// Comments codes of 'else' branch for upgrade QA3 as 3.02 build
		// if(MiscFunctions.isQA24()) {
		RegularExpression reg = new RegularExpression("^Customer.*", false);
		objs = browser.getTableTestObject(".text", reg);
		String tableText = objs[0].getProperty(".text").toString();
		// remove order level transaction fee
		RegularExpression tmpPattern = new RegularExpression(
				"Transaction Fee\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?\\s+(Order Total)\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?",
				false);
		String tmp[] = tmpPattern.getMatches(tableText);

		// define expression for order item level transaction fee
		RegularExpression feeTypePattern = new RegularExpression(
				"Transaction Fee\\s?{0,1}\\(?\\$\\d+\\.\\d{2}\\)?\\s?", false);
		String[] tokens = null;

		if (tmp.length > 0) {
			String str = tableText.substring(0, tableText.indexOf(tmp[0]));
			tokens = feeTypePattern.getMatches(str);
		} else {
			logger.info("There is no order level transaction fee on order cart page");
			tokens = feeTypePattern.getMatches(tableText);
		}

		if (tokens.length > 0 && orderItem <= tokens.length - 1) {
			RegularExpression amountPattern = new RegularExpression(
					"\\d+\\.\\d{2}", false);
			tokens = amountPattern.getMatches(tokens[orderItem]);
			value = tokens[0].trim();
		} else {
			logger.info("There is no order item level transaction fee on order cart page");
			return null;
		}

		// } else {
		// RegularExpression regx = new RegularExpression(
		// "^(\\$[0-9]*\\.[0-9]{2} ?).*", false);
		// objs = browser.getTableTestObject(".text", regx);
		// // System.out.println("Table num for exp 0.00 value:"+objs.length);
		// // MiscFunctions.dumpTable((ITable)objs[0]);
		// // System.out.println("objs[0] text:"+objs[0].getProperty(".text"));
		// // MiscFunctions.dumpTable((ITable)objs[0]);
		// // System.out.println("objs[1] text:"+objs[1].getProperty(".text"));
		// // MiscFunctions.dumpTable((ITable)objs[1]);
		//
		// int feeRow = getFeeTypeRowByItem(orderItem);
		// if(feeRow<0){
		// return null;
		// }
		// String amount = objs[orderItem].getProperty(".text");
		// String[] amounts = amount.split(" ");
		// // if (feeType.equalsIgnoreCase("Subtotal")) {
		// // feeRow--;
		// // }
		// String feeAmount = amounts[feeRow].replaceFirst("\\$", "");
		// value= feeAmount;
		// }

		Browser.unregister(objs);
		return value;
	}

	/**
	 * 
	 * @param compareTransFeeAmount
	 * @param compareTotalPrice
	 */
	public void verifyTotalPriceInOrderCartPg(
			List<String> compareTransFeeAmount, BigDecimal compareTotalPrice) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		// get system calculate results from UI
		BigDecimal totalPrice = ordCartPg.getTotalPrice();
		logger.info("Get ticket total price in Order Cart page:" + totalPrice);

		List<String> transFeeAmount = new ArrayList<String>();
		String transFee0 = ordCartPg.getTransFeeValueByItem(0);
		if (transFee0 != null) {
			transFeeAmount.add(transFee0);
		}

		String transFee1 = ordCartPg.getOrderLevelTransFeeAmount();
		if (transFee1 != null) {
			transFeeAmount.add(transFee1);
		}

		// compare transaction fee amount
		if (!compare(transFeeAmount, compareTransFeeAmount)) {
			throw new ErrorOnDataException(
					"Transaction Fee Amount does not display correctly in Order Cart Page");
		} else {
			logger.info("Transaction Fee display correctly in Order Cart Page");
		}

		// compare total price
		if (compareTotalPrice.compareTo(totalPrice) != 0) {
			logger.error("Caculated total price in Order Cart page is wrong.");
			throw new ErrorOnDataException(
					"Caculated total price in Order Cart page is wrong!");
		}

		logger.info("Verify caculated total price in Order Cart page successfully");

	}

	/**
	 * 
	 * @param sys
	 * @param compare
	 * @return
	 */
	private boolean compare(List<String> sys, List<String> compare) {
		for (int i = 0; i < sys.size(); i++) {
			if (!compare.contains(sys.get(i))) {
				return false;
			}
		}
		return true;
	}

	public void verifyTimeDisplay(String checkinTime, String checkoutTime) {
		String checkin = "", checkout = "";

		checkin = getItemValue(new RegularExpression("^Check-in Time.*", false))
				.split(": ")[1];

		if (!checkin.equalsIgnoreCase("unknown") && checkin.length() == 7) {
			checkin = "0" + checkin.replaceAll(" ", "");
		}
		checkout = getItemValue(
				new RegularExpression("^Check-out Time.*", false)).split(": ")[1];

		if (!checkout.equalsIgnoreCase("unknown") && checkout.length() == 7) {
			checkout = "0" + checkout.replaceAll(" ", "");
			;
		}
		if (!checkinTime.equals(checkin)) {
			throw new ErrorOnDataException(
					"Check-in time's format and value is not correct",
					"Expect:" + checkinTime, ",Actual:" + checkin);
		}
		if (!checkoutTime.equals(checkout)) {
			throw new ErrorOnDataException(
					"Check-out time's format and value is not correct",
					"Expect:" + checkoutTime, ",Actual:" + checkout);
		}
	}

	/**
	 * 
	 * @param compareTransFeeAmount
	 * @param compareTotalPrice
	 */
	public void verifyTotalPriceForItemsInOrderCartPg(
			List<String> compareTransFeeAmount, BigDecimal compareTotalPrice) {
		OrmsOrderCartPage ordCartPg = OrmsOrderCartPage.getInstance();

		// get system calculate results from UI
		BigDecimal totalPrice = ordCartPg.getTotalPrice();
		logger.info("Get ticket total price in Order Cart page:" + totalPrice);

		// get 'From Order Item' trans Fee amount
		List<String> transFeeAmount = new ArrayList<String>();
		String transFrom = ordCartPg.getTransFeeValueByItem(0);
		if (transFrom != null) {
			transFeeAmount.add(transFrom);
		}

		// get 'To Order Item' trans Fee amount
		String transTo = ordCartPg.getTransFeeValueByItem(1);
		if (transTo != null) {
			transFeeAmount.add(transTo);
		}

		// get order level trans fee amount
		if (ordCartPg.getOrderLevelTransFeeAmount() != null) {
			transFeeAmount.add(ordCartPg.getOrderLevelTransFeeAmount());
		}

		// compare transaction fee amount
		if (!compare(transFeeAmount, compareTransFeeAmount)) {
			throw new ErrorOnDataException(
					"Transaction Fee Amount does not display correctly in Order Cart Page");
		} else {
			logger.info("Transaction Fee display correctly in Order Cart Page");
		}

		// compare total price
		if (compareTotalPrice.compareTo(totalPrice) != 0) {
			logger.error("Caculated total price in Order Cart page is wrong.");
			throw new ErrorOnDataException(
					"Caculated total price in Order Cart page is wrong!");
		}

		logger.info("Verify caculated total price in Order Cart page successfully");

	}

	public String[] getPrivilegesInOrderCart() {
		RegularExpression pattern = new RegularExpression("\\(\\d{4}\\).+",
				false);
		IHtmlObject[] objs = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text", pattern));
		if (objs.length < 1) {
			return new String[0];
		}
		String[] privs = new String[objs.length];
		for (int i = 0; i < objs.length; i++) {
			privs[i] = objs[i].text().trim();
		}
		Browser.unregister(objs);
		return privs;

	}

	/**
	 * Get all transaction types in order cart
	 * 
	 * @return
	 */
	public String[] getAllTransactionTypes() {
		Map<String, String> map = this.getAllTransactionsCustnamesOrderTotals();
		String transactions[] = new String[map.size()];
		for (Map.Entry<String, String> entry : map.entrySet()) {
			for (int i = 0; i < map.size(); i++) {
				transactions[i] = entry.getKey().split("\\+")[0].trim();
			}
		}
		return transactions;
	}

	public List<String> getAllTransactionTypeName() {
		List<String> allTransactionType = new ArrayList<>();

		IHtmlObject[] objs = this.getOrderTable();
		IHtmlObject[] trs = browser.getHtmlObject(".class", "Html.TR", objs[1]);
		IHtmlObject checkboxes[] = null;
		String transactionName = "";

		for (int i = 0; i < trs.length; i++) {
			checkboxes = browser
					.getHtmlObject(
							".id",
							new RegularExpression(
									"SELECTED\\_ORDER\\.NEW\\_ORDERVIEW\\.\\d+\\_(NEW\\_ORDER_\\d+|[0-9]-\\d+)",
									false), trs[i]);
			if (checkboxes.length > 0) {
				transactionName = trs[i].getChildren()[1].text().trim();
				transactionName = transactionName.replaceAll(
						"\\(New - \\d+\\)", StringUtil.EMPTY).trim();
				allTransactionType.add(transactionName);
			}
		}

		return allTransactionType;
	}

	/**
	 * Get order item price by corresponding transaction type and customer full
	 * name
	 * 
	 * @param transaction
	 *            - it should be 'Purchase Privilege', 'Duplicate Privilege',
	 *            etc.
	 * @param custLname
	 * @param custFname
	 * @return
	 */
	public double getOrderPriceByTransactionAndCustName(String transaction,
			String custLname, String custFname) {
		logger.info("Get order price by transaction:'" + transaction
				+ "', and customer name: '" + custLname + "," + custFname
				+ "'.");
		Map<String, String> map = this.getAllTransactionsCustnamesOrderTotals();
		double price = 0.00;
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getKey().contains(
					transaction + "+" + custLname + "," + custFname)) {
				price = Double.parseDouble(entry.getValue().split("\\$")[1]
						.trim());
				break;
			}
		}
		logger.info("Get the order price is: " + price);
		return price;
	}

	/**
	 * Get all transaction order price
	 * 
	 * @return
	 */
	public double[] getAllOrderPrices() {
		IHtmlObject[] objs = this.getOrderTable();
		IHtmlTable table = (IHtmlTable) objs[1];
		// get the row number of 'Order Total' which column number is 1
		int rows1[] = table.findRows(1, "Order Total");
		double orderPrices1[] = new double[rows1.length];
		for (int i = 0; i < rows1.length; i++) {
			orderPrices1[i] = Double.parseDouble(table
					.getCellValue(rows1[i], 2).split("\\$")[1].trim());
		}

		// get the row number of 'Order Total' which column number is 0
		int rows2[] = table.findRows(0, "Order Total");
		double orderPrices2[] = new double[rows2.length];
		for (int i = 0; i < rows2.length; i++) {
			orderPrices2[i] = Double.parseDouble(table
					.getCellValue(rows2[i], 1).split("\\$")[1].trim());
		}

		// copy the 2 type order total values into an array
		double orderPrices[] = new double[orderPrices1.length
				+ orderPrices2.length];
		System.arraycopy(orderPrices1, 0, orderPrices, 0, orderPrices1.length);
		System.arraycopy(orderPrices2, 0, orderPrices, orderPrices1.length,
				orderPrices2.length);

		Browser.unregister(table);
		return orderPrices;
	}

	public void selectCopyMailingAddressCheckBox() {
		browser.selectCheckBox(".id", new RegularExpression(
				"ShippingInfoView-\\d+\\.copyMailingAddress.*", false));
	}

	/**
	 * get the tour delivery method.
	 * 
	 * @return
	 */
	public String getTourDeveliyMethod(String tourName) {
		String value = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".className", "orderItemDetail");
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().equals(tourName)) {
				value = objs[i + 2].text();
				if (value
						.matches("[a-zA-Z]{3} [a-zA-Z]{3} [0-9]{1,2} [0-9]{4}.*")) {
					value = objs[i + 3].text();
				}
				if (value.matches("^Facility:.*")) {
					value = objs[i + 4].text();
				}
				break;
			}
		}
		Browser.unregister(objs);
		return value;
	}

	/**
	 * get the tour ticket type and delivery method and quantity.
	 * 
	 * @return
	 */
	public String getTourTicketTypeAndQuantity(String tourName) {
		String value = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".className", "orderItemDetail");
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().equals(tourName)) {
				value = objs[i + 2].text();
				break;
			}
		}
		Browser.unregister(objs);
		return value;
	}

	/**
	 * This method get the date info of tour,
	 * eg"Mon Nov 18 2013, 8:00 PM CST: 1 Adult, 2 Child (3 - 16)"
	 * 
	 * @param tourName
	 * @return
	 */
	public String getTourDetailItemInfo(String tourName) {
		String value = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".className", "orderItemDetail");
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().equals(tourName)) {
				for (int j = i; j < objs.length; j++) {
					if (objs[j].text().contains("CST")
							|| objs[j].text().contains("CDT")) {
						value = objs[j].text();
						break;
					}

				}
			}
			if (!"".equals(value)) {
				break;
			}
		}
		Browser.unregister(objs);
		return value;
	}

	/**
	 * This method get the date info of tour,
	 * eg"Mon Nov 18 2013, 8:00 PM CST: 1 Adult, 2 Child (3 - 16)"
	 * 
	 * @param tourName
	 * @return
	 */
	public String getDeliveryMethodItemInfo(String tourName) {
		String value = "";
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.SPAN",
				".className", "orderItemDetail");
		for (int i = 0; i < objs.length; i++) {
			if (objs[i].text().equals(tourName)) {
				value = objs[i + 4].text();
				if (!value.matches("\\(.*\\)")) {
					value = objs[i + 5].text();
				}
				break;
			}
		}
		Browser.unregister(objs);
		return value.replace("(", "").replace(")", "");
	}

	public void setAllRefund() {
		IHtmlObject[] objs = browser.getHtmlObject(".class",
				"Html.INPUT.radio", ".value", "refund_v");
		for (IHtmlObject o : objs) {
			o.click();
		}
		Browser.unregister(objs);
	}

	/**
	 * 
	 * @param pay
	 */
	public void setupAutoPrintTickets(Payment pay) {
		if (pay.ticketInfo.autoPrintTicketTurnOn) {
			if (!this.checkAutoPrintTicketExist()
					&& !pay.ticketInfo.unSelectAutoPrintTicket) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should exit.");
			} else if (!this.checkAutoPrintTicketSelect()) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should select as default.");
			}
			if (pay.ticketInfo.unSelectAutoPrintTicket) { // Unselect
															// "Auto-print Tickets"
															// checkbox
				this.unSelectCheckboxForAutoPrintTicket();
			}
		} else if (pay.ticketInfo.autoPrintTicketTurnOff) {
			if (this.checkAutoPrintTicketExist()) {
				throw new ObjectNotFoundException(
						"'Auto-Print Tickets' should not exit.");
			}
		}
	}

	/**
	 * Check if the refund info exists in the order cart page.
	 * 
	 * @return
	 * @author Lesley Wang
	 * @date May 14, 2012
	 */
	public boolean checkRefundExists() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Total Refund.*", false));
		return objs.length > 0;
	}

	/**
	 * Get total refund amount on Order cart page before process order
	 * 
	 * @return
	 * @author Lesley Wang
	 * @date May 16, 2012
	 */
	public BigDecimal getTotalRefundAmount() {
		double totalRefund = this.getSummaryAmount("Total Refund\\s*\\("); // avoid
																			// finding
																			// the
																			// TR
																			// 'Total
																			// Refund
																			// to
																			// New
																			// Voucher'
		BigDecimal amount = new BigDecimal(totalRefund).setScale(2,
				RoundingMode.HALF_UP);
		return amount;
	}

	/**
	 * Verify vehicle order item valid dates info
	 * 
	 * @param validFromDate
	 * @param validToDate
	 * @return
	 * @author Lesley Wang
	 * @date Jun 13, 2012
	 */
	public boolean verifyOrderItemValidDatesInfo(String validFromDate,
			String validToDate) {
		validFromDate = DateFunctions.formatDate(validFromDate,
				"EEE MMM d yyyy");
		validToDate = DateFunctions.formatDate(validToDate, "EEE MMM d yyyy");
		String info = "Valid: " + validFromDate + " to " + validToDate;
		IHtmlObject objs[] = browser.getHtmlObject(".class", "Html.TD",
				".text", info);
		boolean result = true;
		if (objs.length < 1) {
			logger.error("Vehicle valid dates info " + info
					+ "is not shown on Order Cart page!");
			result = false;
		} else {
			logger.info("Vehicle valid dates info is correct!");
		}
		Browser.unregister(objs);
		return result;
	}

	public boolean isButtonExist(String butName) {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				butName);
	}

	/**
	 * Compare total price
	 * 
	 * @param expectPrice
	 * @return
	 * @author Lesley Wang
	 * @date Jun 20, 2012
	 */
	public boolean compareTotalPrice(String expectPrice) {
		BigDecimal actPrice = this.getTotalPrice();
		return MiscFunctions.compareResult("Total Price", new BigDecimal(
				expectPrice), actPrice);
	}

	/**
	 * Verify the rule error message on order cart page
	 * 
	 * @param ruleName
	 *            : rule name
	 * @param ruleID
	 *            : rule id
	 * @param tourShortNm
	 *            :
	 * @param errMsgs
	 * @param actMsg
	 */
	public void verifyRuleErrMegs(String ruleName, String ruleID,
			String tourShortNm, String[] errMsgs, String actMsg) {
		String expErrMsgCommon = ruleName + " rule failed [rule cond. id="
				+ ruleID + ", " + "product.cd=" + tourShortNm + "]! ";
		String expMsg = "";
		for (int i = 0; i < errMsgs.length; i++) {
			expMsg += expErrMsgCommon + errMsgs[i];
		}

		if (!MiscFunctions.compareResult(ruleName + " error message", expMsg,
				actMsg)) {
			throw new ErrorOnPageException(
					"The error messages are wrong! Please check the logger error!");
		}
	}

	/** Click Split Order */
	public void clickSplitOrder() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Split Order");
	}

	/** This method split the first new order from current order cart */
	public void splitFirstReservationFromCart() {
		this.selectFirstReservation();
		this.clickSplitOrder();
		ajax.waitLoading();
		this.waitLoading();
	}

	public String getState(String parkName) {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TD",
				".text", new RegularExpression("^Park: " + parkName, false));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Can't facility name and state.");
		}
		return objs[0].getProperty(".text").replaceAll("Park: ",
				StringUtil.EMPTY);
	}

	private List<String> getOrderItemInfoByName(String name) {
		List<String> infos = new ArrayList<String>();

		IHtmlObject objs[] = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("^" + name + ":.*", false)));
		if (objs.length < 1) {
			throw new ItemNotFoundException("Did not get " + name
					+ " order item info.");
		}

		for (IHtmlObject o : objs) {
			infos.add(o.text().replaceAll(name + ":", "").trim());
		}

		Browser.unregister(objs);
		return infos;
	}

	public List<String> getDockAreaInfo() {
		return this.getOrderItemInfoByName("Dock/Area");
	}

	public List<String> getSlipInfo() {
		return this.getOrderItemInfoByName("Slip");
	}

	public List<String> getArriveDateInfo() {
		return this.getOrderItemInfoByName("Arrive");
	}

	public List<String> getDepartureDateInfo() {
		return this.getOrderItemInfoByName("Depart");
	}

	public List<String> getNightsInfo() {
		return this.getOrderItemInfoByName("Nights");
	}

	public List<String> getMonthsInfo() {
		return this.getOrderItemInfoByName("Months");
	}

	public String getBoatLengthInfo() {
		return getOrderItemInfoByName("Boat Length").get(0);
	}

	public String getBoatCategory() {
		return getOrderItemInfoByName("Boat Category").get(0);
	}

	public String getBoatName() {
		return getOrderItemInfoByName("Boat").get(0);
	}

	public String getRegistrationNum() {
		return getOrderItemInfoByName("Registration #").get(0);
	}

	public String getBoatLengthWidthAndHight() {
		return getOrderItemInfoByName("Length / Width / Depth").get(0);
	}

	public List<String> getListNameInfo() {
		return getOrderItemInfoByName("List");
	}

	public List<String> getListEntryTypeInfo() {
		return getOrderItemInfoByName("List Entry Type");
	}

	public List<String> getPreferredSlipInfo() {
		return getOrderItemInfoByName("Preferred Slip");
	}

	public List<String> getOrderColumnInfo() {
		IHtmlObject objs[] = this.getOrderTable();
		IHtmlTable table = (IHtmlTable) objs[1];

		List<String> orderList = table.getColumnValues(0);
		orderList.remove(StringUtil.EMPTY);
		return orderList;
	}

	public void verifyBoatLength(String expected) {
		String actual = getBoatLengthInfo();
		if (!NumberUtil.exactEquals(expected, actual)) {
			throw new ErrorOnPageException("Boat Length is NOT correct",
					expected, actual);
		} else
			logger.info("Boat length is correct as: " + expected);
	}

	public boolean checkChangeFromIsExisting() {
		return browser.checkHtmlObjectDisplayed(Property.toPropertyArray(
				".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text", "CHANGED FROM"));
	}

	public String getCustName() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression("^Name.*", false));
		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Can't find customer section TR object...");
		}
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Name.*", false), objs[0]);
		if (objs1.length < 1) {
			throw new ErrorOnPageException("Can't find customer name object...");
		}

		String name = objs1[0].getProperty(".text").split("Name")[1].trim();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return name;
	}

	public String getCustPhone() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression("^Name.*", false));
		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Can't find customer section TR object...");
		}
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Phone.*", false), objs[0]);
		if (objs1.length < 1) {
			throw new ErrorOnPageException(
					"Can't find customer phone object...");
		}

		String phone = objs1[0].getProperty(".text").split("Phone")[1].trim();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return phone;
	}

	public String getCustZip() {
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR",
				".text", new RegularExpression("^Name.*", false));
		if (objs.length < 1) {
			throw new ErrorOnPageException(
					"Can't find customer section TR object...");
		}
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.SPAN",
				".text", new RegularExpression("^Zip.*", false), objs[0]);
		if (objs1.length < 1) {
			throw new ErrorOnPageException("Can't find customer zip object...");
		}

		String zip = objs1[0].getProperty(".text").split("Zip")[1].trim();
		Browser.unregister(objs);
		Browser.unregister(objs1);
		return zip;
	}

	public String getTableText() {
		IHtmlTable table = (IHtmlTable) this.getOrderTable()[0];
		return table.text();
	}

	public void clickUseCreditCardOnFile() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("Use Credit Card On File", false));
	}

	public boolean checkUseCreditCardOnFileExist() {
		return browser.checkHtmlObjectDisplayed(".class", "Html.A", ".text",
				new RegularExpression("Use Credit Card On File", false));
	}

	public boolean isExpectPaymentCheckBoxExist(int index, int product) {
		boolean result;
		switch (product) {
		case 3:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT.NEW_ORDER.0_"
							+ index, false));
			break;
		case 4:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT.NEW_ORDERVIEW_ORDER.0_" + index,
							false));
			break;
		case 5:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT.NEW_PERMIT_ORDER.0_" + index,
							false));
			break;
		case 6:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT.NEW_TOUR_ORDER.0_" + index,
							false));
			break;
		case 50:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT.OLD_TOUR_ORDER.*|EXPECTED_PAYMENT.OLD_PERMIT_ORDER.*" ,
							false));
			break;
		
		
		default:
			result = true;
			break;
		}

		return result;
	}

	public boolean isExpectPaymentDateFieldExist(int index, int product) {
		boolean result;
		switch (product) {
		case 3:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT_DATE.NEW_ORDER.0_"
							+ index + "_ForDisplay", false));
			break;
		case 4:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT_DATE.NEW_ORDERVIEW_ORDER.0_" + index+ "_ForDisplay",
							false));
			break;
		case 5:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT_DATE.NEW_PERMIT_ORDER.0_" + index
									+ "_ForDisplay", false));
			break;
		case 6:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT_DATE.NEW_TOUR_ORDER.0_" + index
									+ "_ForDisplay", false));
			break;
		case 50:
			result = browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression(
							"EXPECTED_PAYMENT_DATE.OLD_TOUR_ORDER.\\d+\\-\\d+\\_ForDisplay|EXPECTED_PAYMENT_DATE.OLD_PERMIT_ORDER.\\d+\\-\\d+\\_ForDisplay" ,
							false));
			break;
		default:
			result = true;
			break;
		}

		return result;
	}

	public void verifyUseCreditCardOnFileExistOrNot(boolean expect) {
		boolean actual = this.checkUseCreditCardOnFileExist();

		if (actual != expect) {
			throw new ErrorOnPageException(
					"Use Credit Card On File button should "
							+ (expect ? " " : "NOT ") + "exist!!");
		}
	}

	/**
	 * Expect: 4111111111111111 Display on page is: 4111********1111
	 * 
	 * @param expect
	 * @return
	 */
	private boolean verifyCreditCardNumber(String expect) {
		String ccNumber = this.getCreditCardNumber();

		boolean result = true;
		result &= expect.substring(0, 4).equals(ccNumber.substring(0, 4));
		result &= expect.substring(expect.length() - 4).equals(
				ccNumber.substring(ccNumber.length() - 4));
		return result;
	}

	/**
	 * 
	 * Verfiy Credit Card Info after click Use Credit Card On File 1. all of
	 * these text fields should be disable 2. the value is the same as value set
	 * up on order details page
	 * 
	 * @param number
	 * @param mm
	 * @param yy
	 * @param holderName
	 * @param isEnable
	 */
	public void verifyCreditCardInfo(String number, String mm, String yy,
			String holderName, boolean isEnable) {
		boolean result = true;

		logger.info("Verify Credit Info on order cart page.");

		// 1. all of these text fields should be disable
		result &= (isEnable == this.isCreditCardNumEnable());
		result &= (isEnable == this.isCreditCardMMEnable());
		result &= (isEnable == this.isCreditCardYYEnable());
		result &= (isEnable == this.isCreditCardHolderNameEnable());

		// 2. the value is the same as value set up on order details page
		result &= verifyCreditCardNumber(number);
		result &= MiscFunctions.compareResult("Credit Card MM", mm,
				this.getCreditCardMM());
		result &= MiscFunctions.compareResult("Credit Card YY", yy,
				this.getCreditCardYY());
		result &= MiscFunctions.compareResult("Credit Card Holder Name",
				holderName, this.getCreditCardHolderName());

		if (!result) {
			throw new ErrorOnPageException("---Check logs above!!");
		}
	}

	public String getCreditCardNumber() {
		return browser.getTextFieldValue(".id",
				new RegularExpression("F_CardNumber_\\d+_\\d+", false)).trim();
	}

	public String getCreditCardMM() {
		IHtmlObject[] top = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".text", "MM"));
		return browser
				.getTextFieldValue(
						Property.toPropertyArray(".id", new RegularExpression(
								"F_CardExpiry_\\d+_\\d+", false)), top[0]);
	}

	public String getCreditCardYY() {
		IHtmlObject[] top = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".text", "YY"));
		return browser
				.getTextFieldValue(
						Property.toPropertyArray(".id", new RegularExpression(
								"F_CardExpiry_\\d+_\\d+", false)), top[0]);
	}

	public String getCreditCardHolderName() {
		return browser.getTextFieldValue(Property.toPropertyArray(".id",
				new RegularExpression("F_CardHolder_\\d+_\\d+", false)));
	}

	public boolean isCreditCardNumEnable() {
		return browser.checkHtmlObjectEnabled(".id", new RegularExpression(
				"F_CardNumber_\\d+_\\d+", false));
	}

	public boolean isCreditCardMMEnable() {
		IHtmlObject[] top = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".text", "MM"));
		return browser
				.checkHtmlObjectEnabled(
						Property.toPropertyArray(".id", new RegularExpression(
								"F_CardExpiry_\\d+_\\d+", false)), top[0]);
	}

	public boolean isCreditCardYYEnable() {
		IHtmlObject[] top = browser.getHtmlObject(Property.toPropertyArray(
				".class", "Html.SPAN", ".text", "YY"));
		return browser
				.checkHtmlObjectEnabled(
						Property.toPropertyArray(".id", new RegularExpression(
								"F_CardExpiry_\\d+_\\d+", false)), top[0]);
	}

	public boolean isCreditCardHolderNameEnable() {
		return browser.checkHtmlObjectEnabled(Property.toPropertyArray(".id",
				new RegularExpression("F_CardHolder_\\d+_\\d+", false)));
	}

	/**
	 * Remove order. Order ID on the page is like: Float-in Registration(New -
	 * 1) Just set 'New - 1' as parameter is OK.
	 * 
	 * @param orderID
	 */
	public void removeSlipOrderItem(String orderID) {
		this.selectOrderCheckBox(orderID, true);// non site
		this.clickRemoveOrder();
		ajax.waitLoading();
		this.waitLoading();
	}

	public void clickFristResLink() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression(".*New - 1\\)", false));
	}

	public void clickRedeemPoint() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression(".*Redeem Points", false));
	}

	/**
	 * Select option from drop down list first Click Search button on top menu.
	 */
	public void clickSearch(String option) {
		browser.selectDropdownList(".id", new RegularExpression(
				"field_search_dropdown", false), option);
		browser.clickGuiObject(".class", "Html.A", ".text",
				new RegularExpression("^Search", false));
	}

	private Property[] pointsEarned() {
		return Property.toPropertyArray(".class", "Html.TD", ".text",
				new RegularExpression("^Points Earned:(\\s)?\\d+", false));
	}

	public boolean isEarnedPointsExists() {
		return browser.checkHtmlObjectExists(pointsEarned());
	}

	public int getEarnedPoints() {
		return getEarnedPoints(null);
	}

	private Property[] siteNum(String num) {
		if (StringUtil.isEmpty(num))
			num = ".*";
		return Property.toPropertyArray(".class", "Html.A", ".text",
				new RegularExpression("^Site\\:(\\s)?" + num + "\\-", false));
	}

	public int getEarnedPoints(String product) {
		IHtmlObject objs[] = null;
		String text = "";
		if (browser.checkHtmlObjectExists(siteNum(product))) {// Site
			objs = browser.getHtmlObject(siteNum(null));
			if (objs.length < 1)
				throw new ErrorOnPageException(
						"Cannot find site number link object.");
			
			int index = 0;
			if(!StringUtil.isEmpty(product)) {
				for (int i = 0; i < objs.length; i++) {
					if (objs[i].text().contains("Site: " + product + "-")) {
						index = i;
						break;
					}
				}
			}

			objs = browser.getHtmlObject(pointsEarned());
			text = objs[index].text();
		} else {// POS
			IHtmlObject trObjs[] = null;
			if (!StringUtil.isEmpty(product)) {
				trObjs = browser.getHtmlObject(Property.concatPropertyArray(
						tr(), ".text", new RegularExpression(product, false)));
				if (trObjs.length < 1)
					throw new ItemNotFoundException("Cannot find Product - "
							+ product + " TR object.");
			}
			objs = browser.getHtmlObject(pointsEarned(),
					trObjs != null ? trObjs[trObjs.length - 2] : null);
			if (objs.length < 1)
				throw new ItemNotFoundException(
						"Cannot find Points Earned: TD object.");
			text = objs[0].text();
			Browser.unregister(trObjs);
		}

		text = RegularExpression.getMatches(text, "Points Earned:(\\s)?\\d+")[0];
		text = text.split(":")[1].trim();

		Browser.unregister(objs);
		return Integer.parseInt(text);
	}

	public boolean compareEarnedPoints(int expected) {
		return MiscFunctions.compareResult("Earned Points", expected,
				this.getEarnedPoints());
	}

	public boolean compareEarnedPoints(String product, int expected) {
		return MiscFunctions.compareResult("Earned Points of product - "
				+ product, expected, this.getEarnedPoints(product));
	}

	public void verifyEarnedPoints(int expected) {
		if (!compareEarnedPoints(expected))
			throw new ErrorOnPageException("Earned Points is NOT correct.");
	}

	public void verifyEarnedPoints(String product, int expected) {
		if (!compareEarnedPoints(product, expected))
			throw new ErrorOnPageException("Earned Points of product - "
					+ product + " is NOT correct.");
	}

	public void verifyEarnedPointsExists(boolean exists) {
		if (!MiscFunctions.compareResult("'Point Earned:' exists", exists,
				this.isEarnedPointsExists()))
			throw new ErrorOnPageException("'Earned Points' shall"
					+ (exists ? "" : " NOT") + " exist");
	}

	private Property[] pointsRedeemed() {
		return Property.toPropertyArray(".class", "Html.TD", ".text",
				new RegularExpression("Points Redeemed:(\\s)?\\d+$", false));
	}

	public boolean isRedeemedPointsExists() {
		return browser.checkHtmlObjectExists(pointsRedeemed());
	}

	public int getRedeemedPoints(String product) {
		IHtmlObject trObjs[] = null;
		if (!StringUtil.isEmpty(product)) {
			trObjs = browser.getHtmlObject(Property.concatPropertyArray(tr(),
					".text", new RegularExpression(product, false)));
			if (trObjs.length < 1)
				throw new ItemNotFoundException("Cannot find Product - "
						+ product + " TR object.");
		}
		IHtmlObject objs[] = browser.getHtmlObject(pointsRedeemed(),
				trObjs != null ? trObjs[trObjs.length - 2] : null);
		if (objs.length < 1)
			throw new ItemNotFoundException(
					"Cannot find Points Redeemed: TD object.");
		String text = objs[0].text();
		text = RegularExpression.getMatches(text, "Points Redeemed:(\\s)?\\d+")[0];
		text = text.split(":")[1].trim();

		Browser.unregister(objs);
		Browser.unregister(trObjs);
		return Integer.parseInt(text);
	}

	public int getRedeemedPoints() {
		return getRedeemedPoints(null);
	}

	public boolean compareRedeemedPoints(int expected) {
		return MiscFunctions.compareResult("Redeemed Points", expected,
				getRedeemedPoints());
	}

	public void verifyRedeemedPoints(int expected) {
		if (!compareRedeemedPoints(expected))
			throw new ErrorOnPageException("Redeemed Points is NOT correct.");
	}

	public void verifyRedeemedPointsExists(boolean exists) {
		if (!MiscFunctions.compareResult("'Point Redeemed:' exists", exists,
				this.isRedeemedPointsExists()))
			throw new ErrorOnPageException("'Redeemed Points' shall"
					+ (exists ? "" : " NOT") + " exist");
	}

	public double getRedeemAmount() {
		String feeType = "Redeem Amount";
		double amt = 0;
		HashMap<String, Double> feeTypesAmounts = getAllFeeTypesAmounts();
		if (feeTypesAmounts.containsKey(feeType)) {
			amt = feeTypesAmounts.get(feeType);
		}

		return -amt;
	}

	public boolean compareRedeemAmount(double expected) {
		return MiscFunctions.compareResult("Redeem Amount", expected,
				getRedeemAmount());
	}

	public void verifyRedeemAmount(double expected) {
		if (!compareRedeemAmount(expected))
			throw new ErrorOnPageException("Redeem Amount is NOT correct.");
	}

	private Property[] outfitterNum() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("Outfitter \\#\\:", false));
	}

	public String getOutfitterLicenseNum() {
		String text = browser.getObjectText(outfitterNum());
		text = text.split(":")[1].trim();

		return text;
	}

	public boolean compareOutfitterLicenseNum(String expected) {
		return MiscFunctions.compareResult("Outfitter License Num", expected,
				this.getOutfitterLicenseNum());
	}

	private Property[] authorizationCustomer() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("Authorization\\:", false));
	}

	public String getAuthorizationCustomer() {
		String text = browser.getObjectText(authorizationCustomer());
		text = text.split(":")[1].trim();

		return text;
	}

	public boolean compareAuthorizationCustomer(String fName, String lName,
			String halId) {
		String text = this.getAuthorizationCustomer();
		String actualHalID = text.split("\\(")[1].replaceAll("\\)",
				StringUtil.EMPTY);
		String names[] = text.split("\\(")[0].split(",");
		String actualLastName = names[0].trim();
		String actualFirstName = names[1].trim();

		boolean result = true;
		result &= MiscFunctions.compareResult(
				"Authorization Customer first name", fName, actualFirstName);
		result &= MiscFunctions.compareResult(
				"Authorization Customer last name", lName, actualLastName);
		result &= MiscFunctions.compareResult("Authorization Customer HAL ID",
				halId, actualHalID);

		return result;
	}

	private Property[] primaryPrivilegeHolder() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("Primary (Privilege|Licence) Holder\\:",
						false));
	}

	public String getPrimaryPrivilegeHolder() {
		String text = browser.getObjectText(primaryPrivilegeHolder());
		text = text.split(":")[1].trim();

		return text;
	}

	private Property[] primaryPrivilegeName() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("(Privilege|Licence)\\:", false));
	}

	public String getPrimaryPrivilegeName() {
		String text = browser.getObjectText(primaryPrivilegeName());
		text = text.split(":")[1].trim();

		return text;
	}

	public boolean comparePrimaryPrivilegeHolder(String fName, String lName,
			String halId) {
		String expected = fName + " " + lName + " (" + halId + ")";
		return MiscFunctions.compareResult("Primary Privilege Holder",
				expected, this.getPrimaryPrivilegeHolder());
	}

	public boolean comparePrimaryPrivilegeName(String code, String name,
			String privilegeNum) {
		String expected = code + "-" + name + " (" + privilegeNum + ")";
		return MiscFunctions.compareResult("Primary Privilege Name", expected,
				this.getPrimaryPrivilegeName());
	}

	private Property[] sealNums() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("Seal \\#\\(s\\)(\\s)?:", false));
	}

	public String[] getSealNums() {
		String text = browser.getObjectText(sealNums());
		text = text.split(":")[1].trim();
		String nums[] = text.split(",");
		return nums;
	}

	public boolean compareSealNums(String expectedNums[]) {
		boolean result = true;
		String actualNums[] = this.getSealNums();

		if (!MiscFunctions.compareResult("Seal Nums size", expectedNums.length,
				actualNums.length))
			throw new ErrorOnPageException("Seal Nums size is not correct.");

		for (int i = 0; i < expectedNums.length; i++) {
			result &= MiscFunctions.compareResult("Seal # - " + (i + 1),
					expectedNums[i], actualNums[i]);
		}

		return result;
	}

	private Property[] ledgerNums() {
		return Property.toPropertyArray(".class", "Html.TD", ".className",
				"ocTable rubyTable label_tbodyClear", ".text",
				new RegularExpression("Ledger \\#\\(s\\)(\\s)?:", false));
	}

	public String[] getLedgerNums() {
		String text = browser.getObjectText(ledgerNums());
		text = text.split(":")[1].trim();
		String nums[] = text.split(",");
		return nums;
	}

	public boolean compareLedgerNums(String expectedNums[]) {
		boolean result = true;
		String actualNums[] = this.getLedgerNums();

		if (!MiscFunctions.compareResult("Ledger Nums size",
				expectedNums.length, actualNums.length))
			throw new ErrorOnPageException("Ledger Nums size is not correct.");

		for (int i = 0; i < expectedNums.length; i++) {
			result &= MiscFunctions.compareResult("Ledger # - " + (i + 1),
					expectedNums[i], actualNums[i]);
		}

		return result;
	}
	
	public String getPermitTypeLabel(){
		return this.getItemValue(new RegularExpression("PermitType.*",false)).split(":")[0];
	}
	
	public String getEntryDateLabel(){
		return this.getItemValue(new RegularExpression("EntryDate.*",false)).split(":")[0];
	}
	
	public void clickAddAnotherActivity() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Add Another Activity", true);
	}
	
	public void clickActivityRegistration() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Activity Registration");
	}
	
	public void clickGiftCard(){
		browser.clickGuiObject(".class", "Html.A", ".text", "Gift Card");
	}
	
	public boolean isExpectPaymentCheckBoxExist(int index, boolean isPermit) {
		if (isPermit) {
			return browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT.NEW_PERMIT_ORDER.0_"
							+ index, false));
		} else {
			return browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT.NEW_ORDER.0_"
							+ index, false));
		}
	}
	
	public boolean isExpectPaymentDateFieldExist(int index, boolean isPermit) {
		if (isPermit) {
			return browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT.NEW_PERMIT_ORDER.0_" + index + "_ForDisplay",
							false));
		} else {
			return browser.checkHtmlObjectDisplayed(".id",
					new RegularExpression("EXPECTED_PAYMENT_DATE.NEW_ORDER.0_" + index + "_ForDisplay",
							false));
		}
	}
}
