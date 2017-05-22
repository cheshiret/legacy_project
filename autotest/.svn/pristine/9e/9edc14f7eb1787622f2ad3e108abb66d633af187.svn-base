/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.pos;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 */
public class OrmsPOSSaleFeesPage extends OrmsPage {

	/**
	 * Script Name   : FldMgrFeeDetailPage
	 * Generated     : Oct 22, 2004 2:34:00 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/22
	 */
//	private List fees;

	//	public final GuiTestObject pageMark=link_ok();
	private RegularExpression usefeeIdPattern = new RegularExpression(
			"usefee_Daily/Nightly(_[0-9]+)+", false);

	private RegularExpression usefeePenaltyIdPattern = new RegularExpression(
			"usefee_penalty(_[0-9]+)+", false);

	//	private RegularExpression attrfeeIdPattern=new RegularExpression("usefee_Daily/Nightly(_[0-9]+)+",false);
	private RegularExpression transfeeIdPattern = new RegularExpression(
			"tranfee(_[0-9]+)+", false);

//	private RegularExpression useFeeTaxIdPattern = new RegularExpression(
//			"usefee_tax(_[0-9]+)+", false);

	private RegularExpression discIdPattern = new RegularExpression(
			"usefee_discnt(_[0-9]+)+", false);

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsPOSSaleFeesPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsPOSSaleFeesPage() {
//		fees = new ArrayList();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsPOSSaleFeesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsPOSSaleFeesPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression(".*Reservation Fees$",false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
	}

	/**
	 * Input Adjustment Notes
	 * @param note
	 */
	public void setAdjustmentNotes(String note) {
		browser.setTextArea(".id", "fee_adjustment_notes", note);
	}

	/**
	 * Retrieve transaction Fee
	 * @return
	 */
	public double getTransactionFee() {
		return this.getFeeValues(this.transfeeIdPattern);
	}

	/**
	 * Increase transaction Fee
	 * @param amounts
	 */
	public void increaseTransactionFee(double amounts) {
		double fee = this.getTransactionFee();

		fee += amounts;
		this.changeTransactionFee(fee);
	}

	/**
	 * change transaction Fee
	 * @param amounts
	 */
	public void changeTransactionFee(double amounts) {
		this.changeFees(this.transfeeIdPattern, amounts);
	}

	/**
	 * Change Reservation Fee
	 * @param feeType
	 * @param expectedFee
	 * @param newFee
	 * @param feeSchID
	 * @param notes
	 */
	public void changeResFees(String feeType, double expectedFee,
			double newFee, String feeSchID, String notes) {
		//	StatelessGuiSubitemTestObject ticketFeeTable = table_ticketItemTable();

		if (feeType.equals("use fee")) {
			feeType = "usefee_";
		} else if (feeType.equals("transaction fee")) {
			feeType = "tranfee_";
		} else if (feeType.equals("ra fee")) {
			feeType = "rafee_";
		} else if (feeType.equals("POS Fee")){
			feeType = "posfee_";		 
		}else {
			throw new ItemNotFoundException("Unknown Fee Type for Ticket Item");
		}
			
		RegularExpression regex = new RegularExpression(feeType, false);

		// Find all RA Transaction Fee input fields
		IHtmlObject[] objs = browser.getHtmlObject(".id", regex, ".type",
				"text");
		if (objs.length < 1) {
			throw new RuntimeException("object not found");
		}

		boolean feesChanged = false;

		for (int i = 0; i < objs.length; i++) {

			IHtmlObject guiObj = (IHtmlObject) objs[i];

			// field id's are formatted like "item_rafee_1903429_1910092_794782"
			//  the last number ("794782" in example) is the fee schedule id
			String[] feeSchedules = guiObj.getProperty(".id").toString().split(
					"_");
			String feeScheduleID = feeSchedules[feeSchedules.length - 1];

			if (!feeScheduleID.equals(feeSchID)) {
				//				throw new RuntimeException("Fee Schedule ID does not match");
				continue; // skip to next RA fee
			}

			String s = guiObj.getProperty(".value").toString();
			double actualFee = Double.parseDouble(s);

			// test ticket order: 4-1095

			if (actualFee == expectedFee) {
				// change fee
				//MiscFunctions.setText(guiObj, newFee);
				((IText)guiObj).setText(newFee);

				refreshPage();
				browser.sync(2);

				feesChanged = true;

				// reload found objects array
				Browser.unregister(objs);
				objs = browser.getHtmlObject(".id", regex, ".type", "text");
				if (objs.length < 1) {
					throw new RuntimeException("object not found");
				}

			}
		}

		if (feesChanged) {
			// if fees have changed, add notes and click OK
			this.setAdjustmentNotes(notes);

			refreshPage();
			browser.sync(2);

			this.clickOK();

		} else {
			throw new RuntimeException(
					"No matches for FeeScheduleID + ExpectedFeeRate found");
		}

	}

	/**
	 * Change Fees
	 * @param idPattern
	 * @param amount
	 */
	public void changeFees(RegularExpression idPattern, double amount) {
		browser.setTextField(".id", idPattern, amount + "");
	}

	/**
	 * Get Fee Value
	 * @param idPattern
	 * @return
	 */
	public double getFeeValues(RegularExpression idPattern) {
		String s = browser.getTextFieldValue(".id", idPattern);
		double fee = Double.parseDouble(s);

		return fee;
	}

	/**
	 * 
	 * @param amount
	 */
	public void changeUseFee(double amount) {
		RegularExpression usefeeIdPattern = new RegularExpression("usefee_Daily/Nightly(_[0-9]+)+", false);
		this.changeFees(usefeeIdPattern, amount);
	}

	/** Increment the use fee by the indicated amount */
	public void increaseUseFee(double amount) {
		double fee = getUseFeeValue();

		fee += amount;
		this.changeUseFee(fee);
	}

	/** Retrieve the current use fee */
	public double getUseFeeValue() {
		return this.getFeeValues(usefeeIdPattern);
	}

	/**Click Fram to refresh page*/
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}

	/**
	 * Retrieve discoutn fee value
	 * @return
	 */
	public double getDiscFeeValue() {
		return this.getFeeValues(this.discIdPattern);
	}

	/**
	 * Increase discount fee
	 * @param amounts
	 */
	public void increaseDiscFee(double amounts) {
		RegularExpression idPattern = new RegularExpression("usefee_discnt.*",
				false);
		double fee = this.getDiscFeeValue();

		fee += amounts;
		this.changeFees(idPattern, fee);
	}

	/**
	 * Retrieven use fee penalety
	 * @return
	 */
	public double getUseFeePenaltyValue() {
		return this.getFeeValues(this.usefeePenaltyIdPattern);
	}

	/**
	 * Increase use fee penalty
	 * @param amount
	 */
	public void increaseUseFeePenalty(double amount) {
		double fee = this.getUseFeePenaltyValue();

		fee += amount;
		this.changeFees(this.usefeePenaltyIdPattern, fee);
	}

	/**
	 * chenge use fee penalty
	 * @param amount
	 */
	public void changeUseFeePenalty(double amount) {
		this.changeFees(this.usefeePenaltyIdPattern, amount);
	}

	/**
	 * Change Discount Fee amount
	 * @param amounts
	 */
	public void changeDiscFee(double amounts) {
		this.changeFees(this.discIdPattern, amounts);
	}

	/**
	 * Get the total bas tax rate for a particular fee-type
	 * 
	 * Methodology: 
	 * 1) search for the feeType in the row labels
	 * 2) set flag to indicate search for keyword 'tax'
	 * 3) for each tax row parse out the tax rate from the appropriate column and add it to previous
	 * 4) stop iteration once a non-tax row is found
	 * 5) return total base tax rate for the particular fee type
	 * 
	 * @param assocFee
	 * @return
	 */
	public double getBaseTaxRate(String assocFee) {
		final String TAX_ROW = "(tax)";

		boolean foundFeeHeaderRow = false;
		double totalBaseRate = 0.0;
		RegularExpression pattern = new RegularExpression(
				"^Customer Fees Amount Change To.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", pattern);
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompare = tableGrid.getCellValue(row, 0).toString();

			if (foundFeeHeaderRow == true) {
				if ((toCompare.trim()).endsWith(TAX_ROW)) // check if row contains tax base rate
				{
					String baseRate = "";
					String feeInfoString = tableGrid.getCellValue(row, 1).toString();

					// find tax rate in string with $ amount and other data as well (e.g. 7.0%)					
					Pattern p = Pattern.compile("\\b[0-9]+[\\.][0-9]+[\\%]");
					Matcher m = p.matcher(feeInfoString);

					while (m.find()) {
						baseRate = m.group(0);
						break;
					}

					baseRate = baseRate.replaceAll("[\\%]", "");
					totalBaseRate += Double.parseDouble(baseRate);
				} else // found all base rates; exit loop
				{
					foundFeeHeaderRow = false;
					break; // EXIT LOOP
				}
			}

			// set flag if fee type header row found
			if (toCompare.equals(assocFee)) {
				foundFeeHeaderRow = true;
			}
		}

		return totalBaseRate;
	}

	/**Click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/**
	 * Retrieven total amount
	 * @return
	 */
	public double getTotal() {
		RegularExpression pattern = new RegularExpression("total_feetotal",false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Retrieven pas paid amount
	 * @return
	 */
	public double getPastPaid() {
		RegularExpression pattern = new RegularExpression("past_paidtotal",false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Retrieve balance displayed
	 * @return
	 */
	public double getBalanceDisplay() {
		RegularExpression pattern = new RegularExpression("balancetotal", false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Change RA fee
	 * @param feeSchID
	 * @param feeAmount
	 */
	public void changeRAFee(String feeSchID, String feeAmount) {
		String patternStr = "rafee_[0-9]+_"
				+ (feeSchID.length() > 0 ? feeSchID : "[0-9]+");

		RegularExpression pattern = new RegularExpression(patternStr, false);
		this.changeFees(pattern, Double.parseDouble(feeAmount));
	}

	public void testMain(Object[] args) {

	}

}
