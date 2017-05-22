/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.pages.OrmsPage;
import com.activenetwork.qa.awo.pages.orms.common.camping.OrmsReservationFeesPage;
import com.activenetwork.qa.awo.vpData.VpFeeSchedule;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 */
public class OrmsFeeDetailsPage extends OrmsPage {

	/**
	 * Script Name : FldMgrFeeDetailPage Generated : Oct 22, 2004 2:34:00 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 *
	 * @since 2004/10/22
	 */

	// private List fees;
	// public final GuiTestObject pageMark=link_ok();
	private RegularExpression usefeeIdPattern = new RegularExpression(
			"usefee_(Daily/Nightly|Holliday)?(_[0-9]+)+", false);

//	private RegularExpression attrFeeIdPattern = new RegularExpression(
//			"attrfee_Daily/Nightly(_[0-9]+)+", false);
	private RegularExpression attrFeeIdPattern = new RegularExpression(
	"attrfee(_Daily/Nightly)?(_[0-9]+)+", false);

	private RegularExpression usefeeTaxIdPattern = new RegularExpression(
			"usefee_tax(_[0-9]+)+", false);

	private RegularExpression attriFeeTaxIdPattern = new RegularExpression(
			"attrfee_tax(_[0-9]+)+", false);

	private RegularExpression tranFeeTaxIdPattern = new RegularExpression(
			"tranfee_tax(_[0-9]+)+", false);

	private RegularExpression usefeePenaltyIdPattern = new RegularExpression(
			"usefee_penalty(_[0-9]+)+", false);

	private RegularExpression attrifeePenaltyIdPattern = new RegularExpression(
			"attrfee_penalty(_[0-9]+)+", false);

	// private RegularExpression attrfeeIdPattern=new
	// RegularExpression("usefee_Daily/Nightly(_[0-9]+)+",false);

	//tranfee_38505175__1328790865645_239957247
	//tranfee_38466452_discnt_1327047299414_rate_239834634|tranfee_137663928_18717177_137637839
	private RegularExpression transfeeIdPattern = new RegularExpression(
			"tranfee(_[0-9]+)|tranfee(_[0-9]+)+(__[0-9]+)(_[0-9]+)+", false);//this should only be used for transaction fee,discount should use another seperate pattern

	// private RegularExpression useFeeTaxIdPattern = new RegularExpression(
	// "usefee_tax(_[0-9]+)+", false);

	private RegularExpression useFeeDiscIdPattern = new RegularExpression(
			"usefee_discnt(_[0-9]+)+", false);

	private RegularExpression attriFeeDiscIdPattern = new RegularExpression(
			"attrfee_discnt(_[0-9]+)+", false);

	private RegularExpression transFeeDiscIdPattern = new RegularExpression(
			"tranfee(_[0-9]+)_discnt(_[0-9]+)+", false);

	private RegularExpression discRatePattern = new RegularExpression(
			"discount(_[0-9]+)+_rate(_[0-9]+)", false);

	private RegularExpression discRateIdPattern = new RegularExpression(
			"usefee_discnt(_[0-9]+)+_rate(_[0-9]+)", false);

	private RegularExpression useFeediscIdPattern = new RegularExpression(
            //"usefee_discnt(_[0-9]+)", false);
			//Change to identifier use fee amount and rate
			//Amount: usefee_discnt_1324789200000_239986666
			//Rate:   usefee_discnt_1324789200000_rate_239986666
			"usefee_discnt(_[0-9]+_[0-9]+)", false);

	private RegularExpression attriFeediscRateIdPattern = new RegularExpression(
			"attrfee_discnt(_[0-9]+)+_rate(_[0-9]+)", false);

	private RegularExpression tranFeediscRateIdPattern = new RegularExpression(
			"tranfee(_[0-9]+)_discnt(_[0-9]+)+_rate(_[0-9]+)", false);

	private RegularExpression tranFeediscIdPattern = new RegularExpression(
	      //"tranfee(_[0-9]+)_discnt(_[0-9]+)+", false);
		  //Change to identifier transaction fee amount and rate
		  //Amount id: tranfee_38445231_discnt_1324527347652_239986660
		  //Rate   id: tranfee_38445231_discnt_1324527347652_rate_239986660
			"tranfee(_[0-9]+)_discnt(_[0-9]+_[0-9]+)", false);

	protected RegularExpression ticketRateIdPattern = new RegularExpression(
			"ticketfee(_[0-9]+)+", false);

	protected RegularExpression itemTransFeePattern = new RegularExpression(
			"item_tranfee(_[0-9]+)+", false);

	protected RegularExpression itemRaFeePattern = new RegularExpression(
			"item_rafee(_[0-9]+)+", false);

	private RegularExpression permitFeePattern = new RegularExpression(
			"permitfee(_[0-9]+)+", false);

	private RegularExpression posFeePattern = new RegularExpression(
			"posfee(_[0-9]+)+", false);

	private RegularExpression permitPenaltyPattern = new RegularExpression(
			"permitfeepenalty(_[0-9]+)+", false);

	protected RegularExpression ordTransFeePattern = new RegularExpression(
			"order_tranfee(_[0-9]+)+", false);

	protected RegularExpression ordRaFeePattern = new RegularExpression(
			"order_rafee(_[0-9]+)+", false);

	private RegularExpression raFeePattern = new RegularExpression(
			//updated by Summer[2014/7/14],UI change
			"(rafee(_[0-9]+)+)|(RAFee-\\d+)", false);
	
	private RegularExpression changeFeePattern = new RegularExpression("ChangeFee-\\d+.changeAmount", false);
	
	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsFeeDetailsPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsFeeDetailsPage() {
		// fees = new ArrayList();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsFeeDetailsPage getInstance() throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsFeeDetailsPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text",
				"Cancel");
	}

	/**
	 * Input adjustment notes
	 *
	 * @param note
	 */
	public void setAdjustmentNotes(String note) {
		browser.setTextArea(".id", new RegularExpression("(fee_adjustment_notes|OrderFeeView-\\d+\\.adjustmentNotes)", false), note); //"fee_adjustment_notes", note);
	}

	/**
	 * Retrieve transaction Fee
	 *
	 * @return
	 */
	public double getTransactionFee() {
		return this.getFeeValues(this.transfeeIdPattern);
	}

	/**
	 * Get system adjustment
	 *
	 * @param index
	 *            : 0:use fee system adjustment 1:Tax system adjustment
	 * @return
	 */
	public double getSystemAdjustment(int index) {
		double systemAdjust = 0.00;
		IHtmlObject[] objs = browser.getHtmlObject(".id", "tranfee__0");
		if (objs.length > 0) {
			systemAdjust = Double
					.parseDouble(objs[index].getProperty(".value"));
			Browser.unregister(objs);
		} else
			throw new ItemNotFoundException("Item can't find.");

		return systemAdjust;
	}

	/** Get total transaction Fees amount **/
	public double getTotalTransactionFees() {
		double totalTransactionFees = 0.00;
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", this.transfeeIdPattern);
		int length = objs.length / 2;
		for (int i = 0; i < length; i = i + 2) {
			totalTransactionFees = totalTransactionFees
					+ Double.parseDouble(objs[i].getProperty(".value"));
		}
		return totalTransactionFees;
	}

	/**
	 * Increase Transaction fee
	 *
	 * @param amounts
	 */
	public void increaseTransactionFee(double amounts) {
		double fee = this.getTransactionFee();

		fee += amounts;
		this.changeTransactionFee(fee);
	}

	/**
	 * Change transaction Fee
	 *
	 * @param amounts
	 */
	public void changeTransactionFee(double amounts) {
		browser.setTextField(".id", this.transfeeIdPattern,String.valueOf(amounts));
//		this.changeFees(this.transfeeIdPattern, amounts);
	}

	/**
	 * Change order item transaction fee amount
	 *
	 * @param amount
	 */
	public void changeItemTransactionFee(double amount) {
		this.changeFees(this.itemTransFeePattern, amount);
	}

	public void changeItemTransactionFee(String amount) {
		this.changeFees(this.itemTransFeePattern, amount);
	}

	/**
	 * Change order item ra fee amount
	 *
	 * @param amount
	 */
	public void changeItemRaFee(double amount) {
		this.changeFees(this.itemRaFeePattern, amount);
	}

	/**
	 * Change order transaction fee amount
	 *
	 * @param amount
	 */
	public void changeOrdTransactionFee(double amount) {
		this.changeFees(this.ordTransFeePattern, amount);
	}

	/**
	 *
	 * @return transaction fee value from order level
	 */
	public double getOrdTransactionFeeValue() {
		return this.getFeeValues(this.ordTransFeePattern);
	}

	/**
	 * The method used to change ra fee value from order level
	 *
	 * @param amount
	 */
	public void changeOrdRaFee(double amount) {
		this.changeFees(this.ordRaFeePattern, amount);
	}

	/**
	 *
	 * @return RA Fee value form order level
	 */
	public double getOrdRaFeeValue() {
		return this.getFeeValues(this.ordRaFeePattern);
	}

	/**
	 * Change permit fee amount
	 *
	 * @param amount
	 */
	public void changePermitFee(double amount) {
		this.changeFees(this.permitFeePattern, amount);
	}

	public void changePermitFee(String amount){
		this.changeFees(this.permitFeePattern, amount);
	}

	/**
	 *
	 * @return permit fee value
	 */
	public double getPermitFeeValue() {
		return this.getFeeValues(this.permitFeePattern);
	}

	/**
	 * Change POS fee amount
	 *
	 * @param amount
	 */
	public void changePosFee(double amount) {
		this.changeFees(this.posFeePattern, amount);
	}

	public void changePosFee(String amount) {
		this.changeFees(posFeePattern, amount);
	}

	/**
	 *
	 * @return POS fee value
	 */
	public double getPosFeeValue() {
		return this.getFeeValues(this.posFeePattern);
	}
	
	/**
	 * For POS fee detail page, the table should display as below
	 * 'Barcode' 'Item Name' 'Qty' 'Unit Price'...
	 * Get Qty between Item Name and Unit Price
	 * @param name
	 * @return
	 */
	public String getPosQuantityByPosName(String name){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE", ".text", new RegularExpression("^\\W*"+name+".*", false));
		if (objs.length < 1) {
			throw new ErrorOnPageException("Could not find table by pos name "+name);
		} 
		String content = objs[objs.length-1].text();
		content = content.substring(content.indexOf(name)+name.length(), content.indexOf("$"));
		String str = content.trim();
		Browser.unregister(objs);
		return str;
	}

	/**
	 * Change discount base rate
	 *
	 * @param amount
	 */
	public void changeDiscountRate(double amount) {
		this.changeFees(this.discRatePattern, amount);
	}

	public void changeUseFeeDiscountRate(double amount) {
		this.changeFees(this.discRateIdPattern, amount);
	}

	public void changeUseFeeDiscountAmount(double amount) {
		this.changeFees(this.useFeediscIdPattern, amount);
	}

	public void changeAttriFeeDiscountRate(double amount) {
		this.changeFees(this.attriFeediscRateIdPattern, amount);
	}

	public void changeTransFeeDiscountRate(double amount) {
		this.changeFees(this.tranFeediscRateIdPattern, amount);
	}

	public void changeTransFeeDiscountAmount(double amount) {
		this.changeFees(this.tranFeediscIdPattern, amount);
	}

	/**
	 * Get discount or addition discount rate when use fee just has one discount
	 * or(and) one addition discount
	 *
	 * @param isAdditionDiscount
	 *            : the discount is addition discount
	 * @return
	 */
	public double getuseFeeDiscountRate(boolean isAdditionDiscount) {
		double useFeeDiscountRate = 0.0;
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", this.discRateIdPattern);
		if (isAdditionDiscount && objs.length >= 2) {
			useFeeDiscountRate = Double.parseDouble(objs[1]
					.getProperty(".value"));
		} else {
			useFeeDiscountRate = Double.parseDouble(objs[0]
					.getProperty(".value"));
		}
		Browser.unregister(objs);
		return useFeeDiscountRate;
	}

	/** Get use fee discount rate */
	public double getUseFeeDiscountRate() {
		return this.getuseFeeDiscountRate(false);
	}

	/** Get use fee addition discount rate */
	public double getUseFeeAdditionDiscountRate() {
		return this.getuseFeeDiscountRate(true);
	}

	/** Get use fee discount fee value */
	public double getUseFeeDiscFeeValue() {
		return this.getFeeValues(this.useFeeDiscIdPattern);
	}

	/**
	 *
	 * @return discount base rate
	 */
	public double getDiscountRate() {
		return this.getFeeValues(this.discRatePattern);
	}

	/**
	 * Change permit penalty amount
	 *
	 * @param amount
	 */
	public void changePermitPenalty(double amount) {
		this.changeFees(this.permitPenaltyPattern, amount);
	}

	/**
	 *
	 * @return permit penalty amount
	 */
	public double getPermitPenaltyValue() {
		return this.getFeeValues(this.permitPenaltyPattern);
	}

	/**
	 * Change ticket fee amount
	 *
	 * @param amount
	 */
	public void changeTicketFee(double amount) {
		this.changeFees(ticketRateIdPattern, amount);
	}

	public void changeTicketFee(String amount) {
		this.changeFees(ticketRateIdPattern, amount);
	}

	public void changeRaFee(String amount) {
		this.changeFees(raFeePattern, amount);
	}

	public void changeRaFee(double amount) {
		this.changeFees(raFeePattern, amount);
	}

	/**
	 *
	 * @return RA Fee amount
	 */
	public double getRAFee() {
		return this.getFeeValues(this.raFeePattern);
	}

	public String getErrorMsg() {
//		HtmlObject[] objs = browser.getHtmlObject(".className", "message msgerror", ".class", "Html.SPAN");
		IHtmlObject[] objs = browser.getHtmlObject(".className", "message msgerror");
		String msg = objs[0].getProperty(".text");
		Browser.unregister(objs);
		return msg;
	}

	/** Check error message exists or not */
	public boolean checkErrorMsg() {
		return browser.checkHtmlObjectExists(".className", "message msgerror",
				".class", "Html.SPAN");
	}

	/**
	 * Get RA fees table object
	 *
	 * @return--Return the table object
	 */
	public IHtmlObject[] getRAFeesTable() {
		return browser.getTableTestObject(".text", new RegularExpression(
				"^RA Fees", false));
	}
	
	public boolean isRAFeesSectionExist(){
		boolean exist = false;
		IHtmlObject[] objs = this.getRAFeesTable();
		if(objs.length >0){
			exist = true;
		}
		Browser.unregister(objs);
		return exist;
	}

	public IHtmlObject[] getOrderItemTable() {
		return browser.getTableTestObject(".text", new RegularExpression(
				"^Order Item.*", false));
	}

	public String getRAFeeText() {
		IHtmlObject[] objs = this.getRAFeesTable();
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}

	/**
	 * Change Reservation fee
	 *
	 * @param feeType
	 * @param expectedFee
	 * @param newFee
	 * @param feeSchID
	 * @param notes
	 */
	public void changeResFees(String feeType, double expectedFee,
			double newFee, String feeSchID, String notes) {
		// StatelessGuiSubitemTestObject ticketFeeTable =
		// table_ticketItemTable();

		if (feeType.equals("use fee")) {
			feeType = "usefee_";
		} else if (feeType.equals("transaction fee")) {
			feeType = "tranfee_";
		} else if (feeType.equals("ra fee")) {
			feeType = "rafee_";
		} else {
			throw new ItemNotFoundException("Unknown Fee Type for Ticket Item");
		}

		RegularExpression regex = new RegularExpression(feeType, false);

		// Find all RA Transaction Fee input fields
		IHtmlObject[] objs = browser.getTextField(".id", regex);
		if (objs.length < 1) {
			throw new RuntimeException("object not found");
		}

		boolean feesChanged = false;

		for (int i = 0; i < objs.length; i++) {

			IHtmlObject guiObj = (IHtmlObject) objs[i];

			// field id's are formatted like "item_rafee_1903429_1910092_794782"
			// the last number ("794782" in example) is the fee schedule id
			String[] feeSchedules = guiObj.getProperty(".id").toString().split(
					"_");
			String feeScheduleID = feeSchedules[feeSchedules.length - 1];

			if (!feeScheduleID.equals(feeSchID)) {
				// throw new RuntimeException("Fee Schedule ID does not match");
				continue; // skip to next RA fee
			}

			String s = guiObj.getProperty(".value").toString();
			double actualFee = Double.parseDouble(s);

			// test ticket order: 4-1095

			if (actualFee == expectedFee) {
				// change fee
				// MiscFunctions.setText(guiObj, newFee);
				((IText)guiObj).setText(String.valueOf(newFee));

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
	 * change Fee
	 *
	 * @param idPattern
	 * @param amount
	 */
	public void changeFees(RegularExpression idPattern, double amount) {
		IHtmlObject[] objs = browser.getTextField(".id", idPattern);
		if (objs.length > 0) {
			((IText) objs[objs.length-1]).setText(amount + "",
					IText.Event.ONCHANGE);
			ajax.waitLoading();
		} else {
			Browser.unregister(objs);
			throw new ItemNotFoundException("Failed to find the fee field");
		}

		Browser.unregister(objs);
		// browser.setTextField(".id", idPattern, amount + "");
	}

	public void changeFees(RegularExpression idPattern, String amount) {
		browser.setTextField(".id", idPattern, amount);
	}

	public void changeFees(RegularExpression idPattern, BigDecimal amount, int index) {
		browser.setTextField(".id", idPattern, String.valueOf(amount), index);
	}
	
	/**
	 * Retrieven Fee Value
	 *
	 * @param idPattern
	 * @return
	 */
	public double getFeeValues(RegularExpression idPattern) {
		String s = browser.getTextFieldValue(".id", idPattern);
		double fee = Double.parseDouble(s);

		return fee;
	}

	/**
	 * Change Use Fee
	 *
	 * @param amount
	 */
	public void changeUseFee(double amount) {
		RegularExpression usefeeIdPattern = new RegularExpression(
				"usefee_(Daily/Nightly|Holliday)?(_[0-9]+)+", false);
		this.changeFees(usefeeIdPattern, amount);
	}

	public void changeUseFee(String amount) {
		this.changeFees(usefeeIdPattern, amount);
	}

	public void changeUseFee(BigDecimal amount) {
		this.changeFees(changeFeePattern, amount, 0);
	}
	
	public void changeAttributeFee(BigDecimal amount) {
		this.changeFees(changeFeePattern, amount, 2);
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

	/**
	 * Get use fee tax amount, change to amount or use fee discount tax
	 * amount,change to amount
	 *
	 * @param discTaxExist
	 *            : the discount tax section exist or not
	 * @return
	 */
	public String getUseFeeTax(boolean discTaxExist) {
		double useFeeTaxAmount = 0.0;
		double useFeeTaxNewAmount = 0.0;
		double useFeeDiscountTaxAmount = 0.0;
		double useFeeDiscountTaxNewAmount = 0.0;

		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.usefeeTaxIdPattern);
		if(objs.length == 1){
			useFeeTaxNewAmount = Double.parseDouble(objs[0]
					.getProperty(".value"));
			useFeeTaxAmount = Double.parseDouble(objs[0].getProperty(".value"));
		}
		if (objs.length >= 2) {
			useFeeTaxNewAmount = Double.parseDouble(objs[0]
					.getProperty(".value"));
			useFeeTaxAmount = Double.parseDouble(objs[1].getProperty(".value"));
		}
		if (objs.length >= 4 && discTaxExist) {
			useFeeDiscountTaxNewAmount = Double.parseDouble(objs[2]
					.getProperty(".value"));
			useFeeDiscountTaxAmount = Double.parseDouble(objs[3]
					.getProperty(".value"));
		}
		Browser.unregister(objs);
		return String.valueOf(useFeeTaxNewAmount) + " "
				+ String.valueOf(useFeeTaxAmount) + " "
				+ String.valueOf(useFeeDiscountTaxNewAmount) + " "
				+ String.valueOf(useFeeDiscountTaxAmount);
	}

	/** Get use fee tax change to amount **/
	public double getUseFeeTax() {
		return Double.parseDouble(this.getUseFeeTax(false).split(" ")[0]);
	}

	/** Get use fee tax amount **/
	public double getUseFeeTaxAmount() {
		return Double.parseDouble(this.getUseFeeTax(false).split(" ")[1]);
	}

	/** Get use fee discount tax change to amount **/
	public double getUseFeeDiscountTax() {
		return Double.parseDouble(this.getUseFeeTax(true).split(" ")[2]);
	}

	/** Get use fee discount tax amount **/
	public double getUseFeeDiscountTaxAmount() {
		return Double.parseDouble(this.getUseFeeTax(true).split(" ")[3]);
	}

	/**
	 * Get use fee tax amount, change to amount or attribute fee discount tax
	 * amount,change to amount
	 *
	 * @param discTaxExist
	 *            : the discount tax section exist or not
	 * @return
	 */
	public String getAttriFeeTax(boolean discTaxExist) {
		double attriFeeTaxAmount = 0.0;
		double attriFeeTaxNewAmount = 0.0;
		double attriFeeDiscountTaxAmount = 0.0;
		double attriFeeDiscountTaxNewAmount = 0.0;

		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.attriFeeTaxIdPattern);
		if(objs.length ==1){
			attriFeeTaxAmount = Double.parseDouble(objs[0].getProperty(".value"));
		}
		if (objs.length >= 2) {
			attriFeeTaxAmount = Double.parseDouble(objs[0]
					.getProperty(".value"));
			attriFeeTaxNewAmount = Double.parseDouble(objs[1]
					.getProperty(".value"));
		}
		if (objs.length >= 4 && discTaxExist) {
			attriFeeDiscountTaxAmount = Double.parseDouble(objs[2]
					.getProperty(".value"));
			attriFeeDiscountTaxNewAmount = Double.parseDouble(objs[3]
					.getProperty(".value"));
		}
		Browser.unregister(objs);
		return String.valueOf(attriFeeTaxAmount) + " "
				+ String.valueOf(attriFeeTaxNewAmount) + " "
				+ String.valueOf(attriFeeDiscountTaxAmount) + " "
				+ String.valueOf(attriFeeDiscountTaxNewAmount);
	}

	/** Get attribute fee tax change to amount **/
	public double getAttributeFeeTax() {
		return Double.parseDouble(this.getAttriFeeTax(false).split(" ")[0]);
	}

	/** Get attribute fee tax amount **/
	public double getAttriFeeTaxAmount() {
		return Double.parseDouble(this.getAttriFeeTax(false).split(" ")[1]);
	}

	/** Get attribute fee discount tax change to amount **/
	public double getAttriFeeDiscountTax() {
		return Double.parseDouble(this.getAttriFeeTax(true).split(" ")[2]);
	}

	/** Get attribute fee discount tax amount **/
	public double getAttriFeeDiscountTaxAmount() {
		return Double.parseDouble(this.getAttriFeeTax(true).split(" ")[3]);
	}

	/** Get transaction fee tax change to amount **/
	public double getTransFeeTax() {
		double transFeeTaxAmount = 0.0;
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.tranFeeTaxIdPattern);
		transFeeTaxAmount = Double.parseDouble(objs[0].getProperty(".value"));
		Browser.unregister(objs);
		return transFeeTaxAmount;
	}

	/** Get transaction fee tax amount **/
	public double getTransFeeTaxAmount() {
		double transFeeTaxNewAmount = 0.0;
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.tranFeeTaxIdPattern);
		transFeeTaxNewAmount = Double
				.parseDouble(objs[1].getProperty(".value"));
		Browser.unregister(objs);
		return transFeeTaxNewAmount;
	}

	public void changeAttributeFee(double amount) {
		this.changeFees(this.attrFeeIdPattern, amount);
	}

	public double getAttributeFee() {
		return this.getFeeValues(this.attrFeeIdPattern);
	}

	public double getTransactionFeeTax() {
		return this.getFeeValues(tranFeeTaxIdPattern);
	}

	/** Refresh page by click form */
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}

	/** Refresh page by click form */
	public void refreshFeeDetailsPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		this.waitLoading();
	}

	/**
	 * The method used to retrieve all order item table text
	 *
	 * @param index
	 * @return
	 */
	public String getAllOrdItemFeesText(int index) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("^Order Item", false));
		String text = objs[index].text();
		Browser.unregister(objs);
		return text;
	}

	/** Get use fee discount fee value */
	public double getDiscFeeValue() {
		return this.getFeeValues(this.useFeeDiscIdPattern);
	}

	/** Get attribute fee discount fee value */
	public double getAttriDiscFeeValue() {
		return this.getFeeValues(this.attriFeeDiscIdPattern);
	}

	/** Get attribute fee discount rate value */
	public double getAttriDiscFeeRate() {
		return this.getFeeValues(this.attriFeediscRateIdPattern);
	}

	/** Get transaction fee discount fee value */
	public double getTransDiscFeeValue() {
		return this.getFeeValues(this.transFeeDiscIdPattern);
	}

	/** Get transaction fee discount fee value */
	public double getTransDiscFeeRate() {
		return this.getFeeValues(this.tranFeediscRateIdPattern);
	}

	/**
	 *
	 * @return ticket fee value
	 */
	public double getTicketFeeValue() {
		return this.getFeeValues(this.ticketRateIdPattern);
	}

	/**
	 *
	 * @return Transaction fee value from order item level
	 */
	public double getItemTransactionFeeValue() {
		return this.getFeeValues(this.itemTransFeePattern);
	}

	/**
	 *
	 * @return RA fee value from order item level
	 */
	public double getItemrRaFeeValue() {
		return this.getFeeValues(this.itemRaFeePattern);
	}

	/**
	 * Retrieven discount fee rate value
	 *
	 * @return
	 */
	public double getDiscFeeRateValue() {
		return this.getFeeValues(this.discRateIdPattern);
	}

	/**
	 * Increase Discount Fee
	 *
	 * @param amounts
	 */
	public void increaseDiscFee(double amounts) {
		RegularExpression idPattern = new RegularExpression("^usefee_discnt.*",
				false);
		double fee = Math.abs(this.getDiscFeeValue());

		fee += amounts;
		this.changeFees(idPattern, fee);
	}

	/**
	 * Retrieve use fee penalty value
	 *
	 * @return
	 */
	public double getUseFeePenaltyValue() {
		return this.getFeeValues(this.usefeePenaltyIdPattern);
	}

	public double getAttriFeePenaltyValue() {
		return this.getFeeValues(this.attrifeePenaltyIdPattern);
	}

	/**
	 * The method used to return new changed amount
	 *
	 * @param flag
	 * @param colNum
	 * @return
	 */
	public String getChangedAmount(String flag, int index, int colNum) {
		IHtmlObject[] objs = this.getFeesTableByGivenFlag(flag);
		 IHtmlTable grid= (objs.length>1 ?
		 (IHtmlTable)objs[index]:(IHtmlTable)objs[0]);
//		ITable grid = (ITable) objs[index];
		String amount = grid.getCellValue(0, colNum);
		Browser.unregister(objs);
		return amount;
	}

	public IHtmlObject[] getFeesTableByGivenFlag(String flag) {
		RegularExpression reg = new RegularExpression("^\\W*" + flag + "*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		return objs;
	}

	/**
	 * the method used to get adjust fee input box number
	 *
	 * @return
	 */
	public int getAdjustFeeInputBoxNum() {
//		HtmlObject[] objs = getTransactionFrame();
		IHtmlObject[] objs1 = browser.getHtmlObject(".class", "Html.INPUT.text",".id",new RegularExpression(".*fee.*\\d+$", false));
		int num = objs1.length;
//		Browser.unregister(objs);
		Browser.unregister(objs1);
		return num;
	}

	/**
	 * the method used to get adjustment notes
	 *
	 * @return adjustment notes value
	 */
	public String getAdjustmentNotes() {
		IHtmlObject[] objs = browser
				.getHtmlObject(".id", "fee_adjustment_notes");
		return objs[0].getProperty(".text");
	}

	/**
	 * The method used to check current page contain adjust fee input box
	 *
	 * @return
	 */
	public boolean checkAdjustFeeBoxExists() {
		Property[] p = new Property[3];
		p[0] = new Property(".id", new RegularExpression(".*fee(_[0-9]+)+",
				false));
		p[1] = new Property(".disabled", "false");
		p[2] = new Property(".class", "Html.INPUT.text");
		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * The method used to check tax can be adjusted or not
	 *
	 * @return
	 */
	public boolean checkTaxCanBeAdjusted() {
		Property[] p = new Property[3];
		p[0] = new Property(".id", new RegularExpression(".*tax(_[0-9]+)+",
				false));
		p[1] = new Property(".disabled", "false");
		p[2] = new Property(".class", "Html.INPUT.text");
		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * The method used to check discount amount can be adjusted or not
	 *
	 * @return
	 */
	public boolean checkUseFeeDiscAmountCanBeAdjusted() {
		boolean canBeAdjusted = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.useFeeDiscIdPattern, ".class", "Html.INPUT.text");
		if (objs[0].getProperty(".disabled").equals("false")) {
			canBeAdjusted = true;
		}
		Browser.unregister(objs);
		return canBeAdjusted;
	}

	/**
	 * The method used to check discount amount can be adjusted or not
	 *
	 * @return
	 */
	public boolean checkAttriFeeDiscAmountCanBeAdjusted() {
		boolean canBeAdjusted = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.attriFeeDiscIdPattern, ".class", "Html.INPUT.text");
		if (objs[0].getProperty(".disabled").equals("false")) {
			canBeAdjusted = true;
		}
		Browser.unregister(objs);
		return canBeAdjusted;
	}

	/**
	 * The method used to check discount amount can be adjusted or not
	 *
	 * @return
	 */
	public boolean checkTransFeeDiscAmountCanBeAdjusted() {
		boolean canBeAdjusted = false;
		IHtmlObject[] objs = browser.getHtmlObject(".id",
				this.transFeeDiscIdPattern, ".class", "Html.INPUT.text");
		if (objs[0].getProperty(".disabled").equals("false")) {
			canBeAdjusted = true;
		}
		Browser.unregister(objs);
		return canBeAdjusted;
	}

	/**
	 *
	 * @return discount can be adjusted or not
	 */
	public boolean checkDiscountCanBeAdjusted() {
		Property[] p = new Property[3];
		p[0] = new Property(".id", this.useFeeDiscIdPattern);
		p[1] = new Property(".disabled", "false");
		p[2] = new Property(".class", "Html.INPUT.text");

		return browser.checkHtmlObjectExists(p);
	}

	/**
	 * Get the penalty ID
	 *
	 * @return penalty ID
	 */
	public String getPenaltyID() {
		IHtmlObject[] objs = browser
				.getHtmlObject(".class", "Html.TABLE", ".text",
						new RegularExpression("Use Fee/Cancellation.*", false));
		IHtmlObject[] cells = browser.getHtmlObject(".class", "Html.SPAN", ".className", "orderItemDetail", objs[1]);	// Embedded table

		String penaltyID = cells[2].text();
		Browser.unregister(cells);
		Browser.unregister(objs);

		return penaltyID;
	}

	public String getScheduleID(String feeValue) {
		String scheduleID = "";
		String[] temp = new String[10];
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", new RegularExpression("^" + "\\" + feeValue + ".*",
						false));
		if (objs.length > 1) {
			temp = objs[1].getProperty(".text").toString().split(" ");
		} else {
			temp = objs[0].getProperty(".text").toString().split(" ");
		}
		Browser.unregister(objs);
		for (int i = 0; i < temp.length; i++) {
			if (temp[i].matches("[0-9]+")) {
				scheduleID = temp[i];
				break;
			}
		}

		return scheduleID;
	}

	/** return customerfees table object */
	public IHtmlObject[] getCustomerFeesTable() {
		return browser.getTableTestObject(".text", new RegularExpression(
				"(^Customer Fees.*)|(^Order Item.*Customer Fees.*)", false));
	}

	public String getCustomerFeesText() {
		IHtmlObject[] objs = this.getCustomerFeesTable();
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}

	public String getCustomerFeesData(String type) {
		IHtmlObject[] objs = this.getCustomerFeesTable();
		IHtmlObject[] rows = browser.getHtmlObject(".class","Html.TR",".text",new RegularExpression("^POS Fee.*",false),objs[0]);
		IHtmlObject[] tables = browser.getHtmlObject(".class","Html.TABLE",rows[0]);
		IHtmlTable table = (IHtmlTable) tables[0];
		String value = table.getCellValue(0, 1).replaceAll("\\$",
		"").trim()+" "+table.getCellValue(0, 2).trim();;

		Browser.unregister(objs,rows,tables);
		return value;
	}

	/**
	 * Increase use fee penalty
	 *
	 * @param amount
	 */
	public void increaseUseFeePenalty(double amount) {
		double fee = this.getUseFeePenaltyValue();

		fee += amount;
		this.changeFees(this.usefeePenaltyIdPattern, fee);
	}

	/**
	 * Change use fee penalty
	 *
	 * @param amount
	 */
	public void changeUseFeePenalty(double amount) {
		this.changeFees(this.usefeePenaltyIdPattern, amount);
	}

	public void changeAttiFeePenalty(double amount) {
		this.changeFees(this.attrifeePenaltyIdPattern, amount);
	}

	/**
	 * chanage discount Fee
	 *
	 * @param amounts
	 */
	public void changeDiscFee(double amounts) {
		this.changeFees(this.useFeeDiscIdPattern, amounts);
	}

	/**
	 * Get the total bas tax rate for a particular fee-type
	 *
	 * Methodology: 1) search for the feeType in the row labels 2) set flag to
	 * indicate search for keyword 'tax' 3) for each tax row parse out the tax
	 * rate from the appropriate column and add it to previous 4) stop iteration
	 * once a non-tax row is found 5) return total base tax rate for the
	 * particular fee type
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

			// set flag if fee type header row found
			if (toCompare.equals(assocFee)) {
				foundFeeHeaderRow = true;
			}

			if (foundFeeHeaderRow == true) {
				if ((toCompare.trim()).endsWith(TAX_ROW)
						|| toCompare.trim().equalsIgnoreCase("Penalty")) // check
				// if
				// row
				// contains
				// tax
				// base
				// rate
				{
					String baseRate = "";
					String feeInfoString = tableGrid.getCellValue(row, 1)
							.toString();

					if (toCompare.trim().equalsIgnoreCase("Penalty")) {
						String[] temp = feeInfoString.split(" ");
						baseRate = temp[2];
						totalBaseRate += Double.parseDouble(baseRate);
						break;
					}

					// find tax rate in string with $ amount and other data as
					// well (e.g. 7.0%)
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

			// if (toCompare.equals(assocFee)) {
			// foundFeeHeaderRow = true;
			// }
		}

		return totalBaseRate;
	}

	/** click OK button */
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", false);
	}

	public void clickReset() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Reset", false);
	}

	public void clickResDetailTab() {
		browser.clickGuiObject(".class", "Html.A", ".text",
				"Reservation Detail", false);
	}

	/**
	 * Get total fee
	 *
	 * @return
	 */
	public double getTotal() {
		RegularExpression pattern = new RegularExpression("total_feetotal",
				false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Get total RA fee
	 *
	 * @return
	 */
	public double getTotalRaFee() {
		RegularExpression pattern = new RegularExpression("total_ra_feetotal",
				false);
		return this.getFeeValues(pattern);
	}

	/**
	 * get past paid fee
	 *
	 * @return
	 */
	public double getPastPaid() {
		RegularExpression pattern = new RegularExpression("past_paidtotal",
				false);
		return this.getFeeValues(pattern);
	}

	/**
	 * get balance displayed
	 *
	 * @return
	 */
	public double getBalanceDisplay() {
		RegularExpression pattern = new RegularExpression("balancetotal", false);
		return this.getFeeValues(pattern);
	}

	/**
	 * This method is used to get fee by system calculate in the permit order
	 *
	 * @param style
	 * @return
	 */
	public List<String> getFeeBySystemCalculateInPermitOrder(String style) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Customer Fees.*", false));
		IHtmlTable table = (IHtmlTable) objs[1];

		List<String> feeCal = new ArrayList<String>();
		List<String> feeType = table.getColumnValues(0);

		String pattern = new RegularExpression("([0-9]* |[0-9]*)", false)
				.toString();
		String tranpattern = new RegularExpression(
				"Nov|Dec|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct", false)
				.toString();
		String space = new RegularExpression(
				"\\s+", false)
				.toString();
		for (int i = 0; i < feeType.size(); i++) {
			String type = feeType.get(i);
			if (style.equalsIgnoreCase(type)) {
				List<String> fee = table.getRowValues(i);
				String fees = "";
				if ("Transaction Fee".equals(type)) {
					String[] types = fee.get(1).split(tranpattern)[0]
							.split("\\$");
					fees = types[4].replace(" ", "").trim();
					if (!"All".equals(types[0].split("Per")[0].replaceAll(
							pattern, "").trim())) {
						fees = types[0].split("Per")[0].replaceAll(pattern, "")
								.trim()
								+ fees;
					}
				} else {
					if (fee.get(1).contains("Multi")) {
						fees = fee.get(1).split("\\$")[3].split(space)[0].trim()
								+ fee.get(1).split("\\$")[3].split(space)[1]
										.trim()
								+ fee.get(1).split("\\$")[0].replaceAll(
										pattern, "").trim();
						fees = fees.replaceAll("Multi", "");
					} else {
						fees = fee.get(1).split("\\$")[4].split(space)[0].trim()
								+ fee.get(1).split("\\$")[4].split(space)[1]
										.trim();
					    if(fee.get(1).split("\\$")[0].contains("Per Permit")){
					    	fees=fees+fee.get(1).split("\\$")[0].replaceAll(
									pattern, "").replace("All", "").trim();
					    }else{
					    	fees=fees+ fee.get(1).split("\\$")[0].replaceAll(
									pattern, "").trim();
					    }
								
					}
				}
				fees = fees.replaceAll(",", "");
				feeCal.add(fees);
			}
		}
		return feeCal;
	}

//	/**
//	 * This method is used to get fees by system calculate for the ticket order
//	 * @param feeLevel: could be 'Order Item Level' or 'Order Level'
//	 * @param feeType: could be 'Ticket Fee' or 'Transaction Fee'
//	 * @param appliedTypes: null for 'Ticket Fee', for 'Transaction Fee', it could be 'Add New Ticket',
//	 * 		'Walk-up Ticket Purchase' and so on
//	 * @return
//	 */
//	public List<ReservationFeeInfo> getFeeInfoBySystemCalculateInTicketOrder(String feeLevel, String feeType, List<String> appliedTypes) {
//		HtmlObject[] objs = null;
//
//		if(feeLevel.equalsIgnoreCase("Order Item Level")){
//			objs = browser.getTableTestObject(".text",
//					new RegularExpression("Customer Fees.*", false));
//		} else if(feeLevel.equalsIgnoreCase("Order Level")){
//			objs = browser.getTableTestObject(".text",
//					new RegularExpression("Order Customer Fees.*", false));
//		}else{
//			throw new NotSupportedException("Fee level shoule be 'Order Item level' or 'Order level'");
//		}
//
//		ITable table = (ITable) objs[1];
//
//		List<ReservationFeeInfo> feeCal = new ArrayList<ReservationFeeInfo>();
//		List<String> feeTypes = table.getColumnValues(0);
//
//		for (int row = 0; row < feeTypes.size(); row++) {
//			ReservationFeeInfo feeInfo = new ReservationFeeInfo();
//			String type = feeTypes.get(row);
//			if (feeType.equalsIgnoreCase(type)) {
//				feeInfo.feeType = type;
//
//				String fee = table.getCellValue(row, 1).toString();
//
//				int index = fee.indexOf("$");
//				int lastIndex = fee.lastIndexOf("$");
//				String amount = fee.substring(index + 2, lastIndex).split("\\$")[1];
//				feeInfo.feeAmount = amount.replaceAll("\\s{1,}", "");
//
//				if(appliedTypes==null){
//					//treated as ticket fees, no applied type
//					String scheID = fee.substring(lastIndex).split("\\s{1,}")[1];
//					feeInfo.feeSchID = scheID;
//				}else{
//					//treated as transaction fees, applied types could be "Add New Ticket","Walk-up Ticket Purchase" and so on
//					for(int i=0;i<appliedTypes.size();i++){
//						if(fee.indexOf(appliedTypes.get(i))>=0){
//							String scheID = fee.substring(lastIndex).split(appliedTypes.get(i))[0].split("\\s{1,}")[1];
//							feeInfo.feeSchID = scheID.replaceAll("\\s{1,}", "");
//							feeInfo.tranType=appliedTypes.get(i);
//						}
//					}
//				}
//				feeCal.add(feeInfo);
//			}
//		}
//		Browser.unregister(objs);
//		return feeCal;
//	}

	/**
	 * This method is used to get order total amount by system calculate in the ticket order
	 *
	 * @return
	 */
	public String getOrderTotalAmount() {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Order Totals.*", false));

		IHtmlTable table = (IHtmlTable) objs[1];

		List<String> feeType = table.getColumnValues(0);

		String amount = "";
		for (int row = 0; row < feeType.size(); row++) {

			String type = feeType.get(row);
			if (type.equalsIgnoreCase("TOTAL")) {
				String fee = table.getCellValue(row, 1).toString();

				int index = fee.indexOf("$");
				int lastIndex = fee.lastIndexOf("$");
				amount = fee.substring(index, lastIndex).split("\\$")[1];
				System.out.println("total amount:"+amount);
			}
		}
		Browser.unregister(objs);
		return amount;
	}

	/**
	 * This method is used to get fee by system calculate
	 *
	 * @param style
	 * @return
	 */
	public List<String> getFeeBySystemCalculate(String style) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Customer Fees.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> feeCal = new ArrayList<String>();
		List<String> feeType = table.getColumnValues(0);
		String pattern = new RegularExpression(
				"([0-9]*|Negotiated)(Daily/Nightly|Weekly|Monthly|Custom)",
				false).toString();
		String tranpattern = new RegularExpression(
				"Nov|Dec|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct", false)
				.toString();
		for (int i = 0; i < feeType.size(); i++) {
			String type = feeType.get(i);
			if (style.equalsIgnoreCase(type)) {
				List<String> fee = table.getRowValues(i);
				String temp = fee.get(1).split(" ")[3].replaceAll(pattern, "");
				String fees = "";
				if ("Transaction Fee".equals(type)) {
					String[] types = fee.get(1).split(tranpattern)[0]
							.split(" ");
					String app = "";
					for (int j = 4; j < types.length; j++) {
						app += types[j] + " ";
					}
					fees = fee.get(1).split(" ")[1].split("\\$")[1]
							+ fee.get(1).split(" ")[3] + app.trim();
				} else {
					fees = fee.get(1).split(" ")[1].split("\\$")[1]
							+ fee.get(1).split(" ")[3].replaceAll(temp, "");
				}
				feeCal.add(fees);
			}
		}
		Browser.unregister(objs);
		return feeCal;
	}

	/**
	 * This method is used to get discount by system calculate
	 *
	 * @param style
	 * @param rate
	 *            : 1:flat 2:percent
	 * @return
	 */
	public List<String> getDiscountBySystemCalculateInRes(String style) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Customer Fees.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> feeCal = new ArrayList<String>();
		List<String> feeType = table.getColumnValues(0);
		String pattern = new RegularExpression("(flat|%) (per unit|per stay)",
				false).toString();
		String timepattern = new RegularExpression(
				"Nov|Dec|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct", false)
				.toString();
		for (int i = 0; i < feeType.size(); i++) {
			String type = feeType.get(i);
			if (style.equalsIgnoreCase(type.trim())) {
				List<String> fee = table.getRowValues(i);
				String[] temps = fee.get(1).split("\\(");
				String fees = temps[1].split("\\$")[1].replaceAll("\\)", "")
						.trim()
						+ " "
						+ temps[1].split("\\$")[2].split(timepattern)[0]
								.replaceAll(pattern, "").trim();
				feeCal.add(fees);
			}
		}
		Browser.unregister(objs);
		return feeCal;
	}

	/**
	 * This method is used to get penalty by system calculate The List elements
	 * type like: "feeAmount feeSchedulId"
	 *
	 * @return
	 */
	public List<String> getPenaltyBySystemCalculateInRes() {
		OrmsReservationFeesPage ormsResFeePg = OrmsReservationFeesPage
				.getInstance();
		List<String> penaltyReturn = new ArrayList<String>();
		String penalty = "";

		List<Object> penaltyList = ormsResFeePg.getCustomerFees("Penalty");

		for (int i = 0; i < penaltyList.size(); i++) {
			VpFeeSchedule fee = (VpFeeSchedule) penaltyList.get(i);
			penalty = new BigDecimal(fee.feeAmount).setScale(2,
					BigDecimal.ROUND_HALF_UP)
					+ " " + fee.feeSchId;
			penaltyReturn.add(penalty);
		}

		return penaltyReturn;
	}

	/**
	 * This method is used to get transaction fee by system calculate The List elements
	 * type like: "feeAmount feeSchedulId"
	 *
	 * @return
	 */
	public List<String> getTransactionFeeBySystemCalculateInRes() {
		OrmsReservationFeesPage ormsResFeePg = OrmsReservationFeesPage
				.getInstance();
		List<String> transReturn = new ArrayList<String>();
		String trans = "";

		List<Object> transList = ormsResFeePg.getCustomerFees("Transaction Fee");

		for (int i = 0; i < transList.size(); i++) {
			VpFeeSchedule fee = (VpFeeSchedule) transList.get(i);
			trans = new BigDecimal(fee.feeAmount).setScale(2,
					BigDecimal.ROUND_HALF_UP)
					+ " " + fee.feeSchId;
			transReturn.add(trans);
		}

		return transReturn;
	}

	public List<String> getDiscountBySystemCalculateInPOS(String style) {
		IHtmlObject[] objs = browser.getTableTestObject(".text",
				new RegularExpression("Customer Fees.*", false));
		IHtmlTable table = (IHtmlTable) objs[0];

		List<String> feeCal = new ArrayList<String>();
		List<String> feeType = table.getColumnValues(0);
		String timepattern = new RegularExpression(
				"Nov|Dec|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct", false)
				.toString();
		for (int i = 0; i < feeType.size(); i++) {
			String type = feeType.get(i);
			if (style.equalsIgnoreCase(type.trim())) {
				List<String> fee = table.getRowValues(i);
				String[] temps = fee.get(1).split("\\(");
				String fees = temps[2].split("\\$")[1].replaceAll("\\)", "").trim()
						+ temps[3].split("\\)")[1].split(timepattern)[0]
								.replace(" ", "").trim();
				feeCal.add(fees);
			}
		}
		Browser.unregister(objs);
		return feeCal;
	}

	/**
	 * change RA Fee by fee ID and Fee amount
	 *
	 * @param feeSchID
	 * @param feeAmount
	 */
	public void changeRAFee(String feeSchID, String feeAmount) {
		String patternStr = "rafee_[0-9]+_"
				+ (feeSchID.length() > 0 ? feeSchID : "[0-9]+");

		RegularExpression pattern = new RegularExpression(patternStr, false);
		this.changeFees(pattern, Double.parseDouble(feeAmount));
	}

	/**
	 * Get the permit penalty fee schedule ID
	 *
	 * @return -- schedule ID
	 */
	public String getPermitPenaltyFeeScheduleID() {
		String feeShceduleID = "";
		IHtmlObject[] objs = getCustomerFeesTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[objs.length-1];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompare = tableGrid.getCellValue(row, 0).toString();
			if (toCompare.equalsIgnoreCase("Penalty")) {
				String temp = tableGrid.getCellValue(row, 1).toString().replaceAll("\\s+", " ");
				feeShceduleID = temp.split(" ")[7]; // split temp, the schedule
				// info is 770012588Use
				// int end = getScheduleInfo.length();
				// feeShceduleID = getScheduleInfo.substring(0, end-3);
				break;
			}
		}

		Browser.unregister(objs);
		return feeShceduleID;
	}

	/**
	 * Get permit RA threshold schedule ID
	 *
	 * @return threshold Schedule ID
	 */
	public String getPermitOrSiteRAThresholdScheduleID(boolean isSite) {
		String threshSchdID = "";
		IHtmlObject[] raTab = this.getRAFeesTable();
		IHtmlTable raTableGrid = (IHtmlTable) raTab[0];

		for (int row = 0; row < raTableGrid.rowCount(); row++) {
			String toCompare = raTableGrid.getCellValue(row, 0).toString();
			if (toCompare.equalsIgnoreCase("RA Transaction Fee")) {
				String temp = raTableGrid.getCellValue(row, 1).replaceAll("\\s+", " ");
				// String getScheduleInfo =
				// temp.split(" ")[5].split("[A-Z].*")[0];
				if(isSite){
					threshSchdID = temp.split(" ")[3];
				}else{
					threshSchdID = temp.split(" ")[8];
				}
				
				break;
			}
		}

		return threshSchdID;
	}

	/**
	 * Get ticket RA threshold schedule ID
	 *
	 * @return
	 */
	public String getTicketRAThresholdScheduleID() {
		String threshSchdID = "";
		IHtmlObject[] raTab = this.getOrderItemTable();
		
		Property[] raRowPro = new Property[2];
		raRowPro[0] = new Property(".class","Html.TR");
		raRowPro[1] = new Property(".text",new RegularExpression("^RA Transaction Fee.*",false));
		IHtmlObject[] raRowObjs = browser.getHtmlObject(raRowPro, raTab[1]);
		if(raRowObjs.length<1){
			throw new ItemNotFoundException("Did not found ra transaction fee row object.");
		}
		
		Property[] raTabRowPro = new Property[1];
		raTabRowPro[0] =  new Property(".class","Html.TABLE");
		IHtmlObject[] raTableObjs = browser.getHtmlObject(raTabRowPro, raRowObjs[0]);
		if(raTableObjs.length<1){
			throw new ItemNotFoundException("Did not found the table which covered in ra transaction fee row object.");
		}
		
		IHtmlTable raRowTable= (IHtmlTable) raTableObjs[0];
		threshSchdID = raRowTable.getCellValue(0, 11);

		return threshSchdID;
	}

	public String getCustomFeeCell(int row, int col) {
		String customFeeCell = "";
		IHtmlObject[] objs = this.getCustomerFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		customFeeCell = table.getCellValue(row, col);

		Browser.unregister(objs);
		return customFeeCell;
	}

	public void setAllFeesAmountAsZero() {
		IHtmlObject objs[] = browser.getTextField(".id", new RegularExpression(".*fee_.*(_\\d+)+", false));
		if(objs.length < 1) {
			throw new ItemNotFoundException("Can't find any fee amount text field.");
		}
		
		for(int i = 0; i < objs.length; i ++) {
			((IText)objs[i]).setText(0);
			refreshFeeDetailsPage();
		}
		
		Browser.unregister(objs);
	}
	
	public void testMain(Object[] args) {

	}
	
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getReservationBaseRate(RegularExpression feeName ){
		int COLUMN_BASERATE = 3; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String baseRate = tbl.getCellValue(0, COLUMN_BASERATE);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return baseRate;		
	}
	
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getReservationAmountValue(RegularExpression feeName ){
		int COLUMN_AMOUNT = 1; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String amount = tbl.getCellValue(0, COLUMN_AMOUNT);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return amount;		
	}
	
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getPOSBaseRate(RegularExpression feeName ){
		int COLUMN_BASERATE = 6; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String baseRate = tbl.getCellValue(0, COLUMN_BASERATE);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return baseRate;		
	}
	
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getPOSAmountValue(RegularExpression feeName ){
		int COLUMN_AMOUNT = 8; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String amount = tbl.getCellValue(0, COLUMN_AMOUNT);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return amount;		
	}
	
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getPermitBaseRate(RegularExpression feeName ){
		int COLUMN_BASERATE = 4; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String baseRate = tbl.getCellValue(0, COLUMN_BASERATE);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return baseRate;		
	}
	/**
	 * @param: feeName: text in the line of Fees.
	 * */
	public String getPermitAmountValue(RegularExpression feeName ){
		int COLUMN_AMOUNT = 6; 
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", feeName);
		IHtmlObject[] tbls = browser.getHtmlObject(".class", "Html.TABLE", objs[0]);
		
		IHtmlTable tbl = (IHtmlTable)tbls[0];
		String amount = tbl.getCellValue(0, COLUMN_AMOUNT);
				
		Browser.unregister(tbls);
		Browser.unregister(objs);
		return amount;		
	}

	public double getLastFeeValues(RegularExpression idPattern) {
		IHtmlObject[] objs=browser.getHtmlObject(".class", "Html.INPUT.text", ".id", idPattern);
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find text field by id "+idPattern);
		String s = ((IText)objs[objs.length-1]).getText();
		double fee = Double.parseDouble(s);
		Browser.unregister(objs);
		return fee;
	}
	
	public double getLastAttributeFee() {
		return this.getLastFeeValues(this.attrFeeIdPattern);
	}
	
	/**
	 * This method used to verify a kind of fee type has been applied to the order
	 * @param type
	 */
	public void verifyFeeTypeIsApplied(String type) {
		IHtmlObject[] objs = this.getCustomerFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		if( -1 == table.findRow(0, type))
		{
			throw new ErrorOnPageException("The fee type:" + type + " is not applied!");
		}
	    logger.info("The fee type:" + type + " has been applied!");
		Browser.unregister(objs);
	}
		
	public String getOrderItemInfo(String itemType){
		IHtmlObject[] objs = this.getOrderItemTable();
		IHtmlObject[] spans = browser.getHtmlObject(".class","Html.SPAN",".text",new RegularExpression("^" + itemType + ".*",false),objs[0]);
		if(spans.length < 1){
			throw new ErrorOnPageException("Can not found the spans for item type:" + itemType);
		}
		String content = spans[0].text().replace(itemType, "");
		return content;
	}
	
	public String getTourDateItemInfo(){
		return this.getOrderItemInfo("Tour Date");
	}
	
	public boolean checkDiscountNameIsExists(String discountName){
		return browser.checkHtmlObjectDisplayed(Property.addToPropertyArray(td(), ".text", new RegularExpression("\\(D\\) "  + discountName +".*",false)));
	}
	
	public boolean checkScheduleIsExists(String schdID){
		return browser.checkHtmlObjectDisplayed(Property.addToPropertyArray(td(), ".text", new RegularExpression(schdID +".*",false)));
	}
	
}
