/*
 * $Id: FldMgrFeeDetailPage.java 7090 2009-02-03 19:50:33Z i2k-net\raonqa $
 */

package com.activenetwork.qa.awo.pages.orms.common.camping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.OrmsConstants;
import com.activenetwork.qa.awo.datacollection.legacy.FeePenaltyData;
import com.activenetwork.qa.awo.datacollection.legacy.feeData.ReservationFeeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeesPage;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.awo.vpData.VpFeeSchedule;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * Reservation fees page
 *
 * @author CGuo,jdu
 */
public class OrmsReservationFeesPage extends OrmsFeesPage {

	/**
	 * Script Name : FldMgrFeeDetailPage Generated : Oct 22, 2004 2:34:00 PM
	 * Original Host : WinNT Version 5.1 Build 2600 (Service Pack 2)
	 *
	 * @since 2004/10/22
	 */
	private List<Object> fees;

	/**
	 * A handle to the unique Singleton instance.
	 */
	static private OrmsReservationFeesPage _instance = null;

	/**
	 * The constructor could be made private to prevent others from
	 * instantiating this class. But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	public OrmsReservationFeesPage() {
		fees = new ArrayList<Object>();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsReservationFeesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsReservationFeesPage();
		}

		return _instance;
	}

	/**
	 * The method used to check current page contain adjust fee input box
	 *
	 * @return
	 */
	public boolean checkAdjustFeeBoxExists() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression(
				".*fee(_[0-9]+)+", false));
	}

	/**
	 * Change transaction fee
	 *
	 * @param amounts
	 *            ---The transaction fee amount
	 */
	public String changeTransactionFee(double amounts) {

		String result = "Fail to change fee";

		RegularExpression regex = new RegularExpression("tranfee(_[0-9]+)+",
				false);
		IHtmlObject[] objs = browser.getTextField(".id", regex, ".class",
				"Html.INPUT.text");
		if (objs.length < 1) {
			throw new RuntimeException("object not founded");
		}

		IText guiObj = (IText) objs[0];

		String s = guiObj.getText();
		double fee = Double.parseDouble(s);

		fee += amounts;

		guiObj.setText(fee + "");

		result = "changed fee";

		refreshPage();
		browser.sync(2);
		setAdjustmentNotes("QA Automation");
		refreshPage();
		browser.sync(2);

		setAdjustmentNotes("QA Automation");
		this.clickOK();

		return result;
	}

	public String changeFees(String feeType, double amount,
			String scheduleID, String notes) {
		return changeFees(feeType,amount,scheduleID,notes,true);
	}
	
	/**
	 * change fees
	 * @param feeType
	 * @param amount
	 * @param scheduleID
	 * @param notes
	 * @param adjust if true, the new amount=currentAmount+amount, else, newAmount=amount
	 * @return
	 */
	public String changeFees(String feeType, double amount,
			String scheduleID, String notes, boolean adjust) {
		feeType = getFeeTypePattern(feeType);

		RegularExpression regex = new RegularExpression(feeType, false);

		// Find all Fee input fields
		IHtmlObject[] objs = browser.getTextField(".id", regex, ".class",
				"Html.INPUT.text");
		if (objs.length < 1) {
			Browser.unregister(objs);
			return "Failed - required fee type \"" + feeType + "\" not found";
		}
		IHtmlObject textField = (scheduleID == null
				|| scheduleID.length() < 1 ? objs[0] : filterByScheduleID(objs,
				scheduleID));

		if (textField == null) {
			Browser.unregister(objs);
			return "Failed - required fee type \"" + feeType
					+ "\" with scheduleID=" + scheduleID + "not found";
		}

		double currentAmount = Double.parseDouble(((IText)textField).getText());
		double newAmount = adjust?currentAmount + amount:amount;
		scheduleID = parseFeeScheduleID(textField.getProperty(".id").toString());

		((IText)textField).setText(newAmount);

		Browser.unregister(objs);
		refreshPage();
		browser.sync(2);

		setAdjustmentNotes(notes);

		return "changed fee for schedule#" + scheduleID;
	}

	protected String getFeeTypePattern(String feeType) {
		if (feeType.equalsIgnoreCase("use fee")) {
			feeType = "usefee_(Daily/Nightly|Weekly)|(Custom)?(_[0-9]+)+";// regular
//			feeType = "usefee_(Daily/Nightly|Weekly)|(Custom)(_[0-9]+)+";// regular
			// express
		} else if (feeType.equalsIgnoreCase("transaction fee")) {
			feeType = "tranfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("ra fee")) {
			feeType = "rafee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("use fee discount")) {
			feeType = "usefee_\\(?discnt\\)?(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("ticket fee")) {
			feeType = "ticketfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("transaction fee discount")) {
			feeType = "transactionfee_\\(?discnt\\)?(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("use fee penalty")) { // When cancel
			// reservation,it
			// will
			// cause
			// penalty
			// use fee.
			feeType = "usefee_(penalty)(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("permit fee penalty")) { // When
			// cancel
			// reservation,it
			// will
			// cause
			// penalty
			// permit
			// fee.
			feeType = "permitfeepenalty(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("attribute fee penalty")) { // When
			// cancel
			// reservation,it
			// will
			// cause
			// penalty
			// attribute
			// fee.
			feeType = "attrfee_(penalty)(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("pos fee")) {
			feeType = "posfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("pos fee discount")) {
			feeType = "discount(_[0-9]+){2}_rate_[0-9]+";
		} else if (feeType.equalsIgnoreCase("attribute fee")) {
			feeType = "attrfee_(Daily/Nightly|)(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("permit use fee")) {
			feeType = "permitfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("item ra fee")) {
			feeType = "item_rafee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("item transaction fee")) {
			feeType = "item_tranfee(_[0-9]+)+";
		} else if (feeType.equalsIgnoreCase("use fee tax")) {
			feeType = "usefee_tax_\\d+";
		} else if (feeType.equalsIgnoreCase("transaction fee tax")) {
			feeType = "tranfee_tax_\\d+";
		} else {
			throw new ItemNotFoundException("Unknown Fee Type");
		}

		return feeType;
	}

	protected IHtmlObject filterByScheduleID(IHtmlObject[] objs, String scheduleID) {
		for (int i = 0; i < objs.length; i++) {
			String feeScheduleID = parseFeeScheduleID(objs[i]
					.getProperty(".id").toString());

			if (feeScheduleID.equals(scheduleID)) {

				return objs[i];
			}
		}

		return null;
	}

	private String parseFeeScheduleID(String text) {
		// field id's are formatted like "item_rafee_1903429_1910092_794782"
		// the last number ("794782" in example) is the fee schedule id
		String[] feeSchedules = text.split("_");
		String feeScheduleID = feeSchedules[feeSchedules.length - 1];

		return feeScheduleID;
	}

	/**
	 * Change fee amount according to different fee schedule ID
	 *
	 * @param feeType
	 * @param expectedFee
	 * @param newFee
	 * @param feeSchID
	 *            ---The fee schedule ID that you want to change
	 * @param notes
	 */
	public String changeFees(String feeType, double expectedFee, double newFee,
			String feeSchID, String notes) {

		String result = "";
		boolean feeChanged = false;

		feeType = getFeeTypePattern(feeType);

		RegularExpression regex = new RegularExpression(feeType, false);

		// Find all Fee input fields
		IHtmlObject[] objs = browser.getTextField(".id", regex, ".class",
				"Html.INPUT.text");

		if (objs.length < 1) {
			throw new ItemNotFoundException("object not found");
		}

		IHtmlObject guiObj = filterByScheduleID(objs,
				feeSchID);

		if (guiObj == null) {
			result = "Fee Schedule ID does not match";
		} else {
			String s = guiObj.getProperty(".value").toString();
			double actualFee = Double.parseDouble(s);

			if (actualFee == expectedFee) {
				if (actualFee != newFee) {
					// change fee
					((IText)guiObj).setText(newFee + "");
					refreshPage();
					browser.sync(2);

					feeChanged = true;

					// reload found objects array
					Browser.unregister(objs);
					objs = browser.getHtmlObject(".id", regex, ".class",
							"Html.INPUT.text");
					if (objs.length < 1) {
						throw new RuntimeException("object not found");
					}
				}
			}
		}

		if (feeChanged) {// if fees have changed, add notes and click OK
			setAdjustmentNotes(notes);

			refreshPage();
			browser.sync(2);

			setAdjustmentNotes(notes);
			clickOK();

			result = "changed fee";

		} else {
			clickOK();
			result = "Skipped -fee has been already changed";
		}

		return result;
	}

	/**
	 * Change order item transaction fee amount
	 *
	 * @param amount
	 */
	public void changeItemTransactionFee(double amount) {
		this.changeFees(this.itemTransFeePattern, amount);
	}

	/**
	 *
	 * @return Transaction fee value from order item level
	 */
	public double getItemTransactionFeeValue() {
		return this.getFeeValues(this.itemTransFeePattern);
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
	 * @return transaction fee value fromm order level
	 */
	public double getOrdTransactionFeeValue() {
		return this.getFeeValues(this.ordTransFeePattern);
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
	 *
	 * @return RA fee value from order item level
	 */
	public double getItemrRaFeeValue() {
		return this.getFeeValues(this.itemRaFeePattern);
	}

	/**
	 * Change order ra fee amount
	 *
	 * @param amount
	 */
	public void changeRaFee(double amount) {
		this.changeFees(new RegularExpression(this.getFeeTypePattern("ra fee"),
				false), amount);
	}

	public void changeRaFee(String amount) {
		this.changeFee(new RegularExpression(this.getFeeTypePattern("ra fee"),
				false), amount);
	}

	public void changeFee(RegularExpression idPattern, String amount) {
		browser.setTextField(".id", idPattern, amount);
	}

	public void changeItemTransactionFee(String amount) {
		this.changeFee(this.itemTransFeePattern, amount);
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
	 * Retrieve Fee Value
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
	 * change Fee
	 *
	 * @param idPattern
	 * @param amount
	 */
	public void changeFees(RegularExpression idPattern, double amount) {
		IHtmlObject[] objs = browser.getTextField(".id", idPattern);
		if (objs.length > 0) {
			((IText) objs[0]).setText(amount + "",
					IText.Event.ONCHANGE);
		} else {
			Browser.unregister(objs);
			throw new ItemNotFoundException("Failed to find the fee field");
		}

		Browser.unregister(objs);
		// browser.setTextField(".id", idPattern, amount + "");
	}

	/** Retrieve the current use fee */
	public double getUseFee() {
		return this.getFeeValues(new RegularExpression(
				"usefee_{0,1}(Daily/Nightly|Weekly){0,1}(_[0-9]+)+", false));
	}

	/** Retrieve the current ra fee */
	public double getRAFee() {
		return this
				.getFeeValues(new RegularExpression("rafee(_[0-9]+)+", false));
	}

	/** Retrieve the current tran fee */
	public double getTranfee() {
		return this.getFeeValues(new RegularExpression("tranfee(_[0-9]+)+",
				false));
	}

	/** Click "Reservation Fee" text to refresh page */
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
	}

	/** return customerfees table object */
	public IHtmlObject[] getCustomerFeesTable() {
		IHtmlObject[] tables = browser.getTableTestObject(".text", new RegularExpression(
				"^Customer Fees.*", false));
		if(tables.length < 1){
			tables = browser.getTableTestObject(".text", new RegularExpression(
					"^Order Item.*Customer Fees.*", false));
		}
		return tables;
	}

	/**
	 * Get the customer fees
	 *
	 * @param type
	 *            - fee type: "Use Fee" or "Transaction Fee"
	 * @return
	 */
	public List<Object> getCustomerFees(String type) {
		boolean isNegativeNum = false;
		fees.clear();
		IHtmlObject[] objs = getCustomerFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];

		for (int row = 2; row < table.rowCount() - 2; row++) {
			VpFeeSchedule fee = new VpFeeSchedule();
			String feeType = table.getCellValue(row, 0).toString();
			if (type != null && !type.equals("") && feeType.indexOf(type) != -1) {
				fee.feeType = feeType;

				IHtmlObject tbody = table.getChildren()[0];
				IHtmlObject tr = (IHtmlObject) tbody.getChildren()[row];
				IHtmlTable feeInfoTable  = (IHtmlTable) browser.getHtmlObject(".class", "Html.TABLE", tr)[0];

				String feeAmount = feeInfoTable.getCellValue(0, 1).replaceAll("\\$", "");
				if (feeAmount.contains("(")|| feeAmount.contains(")")) {
					isNegativeNum = true;
				}

				fee.feeSchId = feeInfoTable.getCellValue(0, 5);

				if (feeType.indexOf("discount") != -1) {
					fee.feeType = "discount";
					fee.feeAmount = Double.parseDouble(feeAmount.replace("(", "").replace(")", "").trim());
				} else {
					if (isNegativeNum) {
						fee.feeAmount = -Double.parseDouble(feeAmount.replace("(", "").replace(")", "").trim());
					} else {
						fee.feeAmount = Double.parseDouble(feeAmount);
					}
				}

				String feeDate = feeInfoTable.getCellValue(0, 8);
				setStartAndEndDate(fee, feeDate);

				// fee.feeSchId =
				// temp[2].toString().substring(0,temp[2].indexOf("Daily")).trim();

				fees.add(fee);
				// [$27.00, $, 259, Nightly, Mar, 24,, 2005, -, Mar, 27,, 2005]
			} else if (type == null || type.equals("")) {
				fee.feeType = feeType;
				String[] temp = table.getCellValue(row, 1).toString()
						.split(" ");

				if (feeType.indexOf("discount") != -1) {
					fee.feeType = "discount";
					fee.feeAmount = Double.parseDouble(temp[0].trim()
							.substring(2).replace(')', ' ').trim());
					fee.feeAmount = -fee.feeAmount;
				} else {
					fee.feeAmount = Double.parseDouble(temp[0].trim()
							.substring(1));
				}
				fee.feeSchId = temp[2].toString().trim();
				fees.add(fee);
			}
		}

		Browser.unregister(objs);
		return fees;
	}

	private void setStartAndEndDate(VpFeeSchedule fee, String feeDate) {
		Matcher m = Pattern.compile("(.*)-(.*)\\(.*\\)").matcher(feeDate);
		if(m.find()) {
			fee.startDate = m.group(1).trim();
			fee.endDate = m.group(2).trim();
		}
	}

	/**
	 * Input param fee type, and then get the amount of the fee
	 *
	 * @param feeType
	 * @return
	 */
	public float getFeesAmount(String feeType) {
		float actualValue = 0;
		IHtmlObject[] objs = getCustomerFeesTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompare = tableGrid.getCellValue(row, 0).toString();

			if (toCompare.equalsIgnoreCase(feeType)) {
				String fee = tableGrid.getCellValue(row, 1).toString();
				int index = fee.indexOf("$");
				int lastIndex = fee.lastIndexOf("$");
				String money = fee.substring(index + 1, lastIndex);
				actualValue = Float.parseFloat(money.replace("(", "").replace(")", ""));
			}
		}
		Browser.unregister(objs);
		return actualValue;
	}

	/**
	 *
	 * check the fee schedule id on page existing or not. if "Unknown Fee Type"
	 * exception is throw,please add your fee type Pattern or make sure your fee
	 * type Pattern existing, if you have any problem, please let Alex Sun
	 * know.thanks
	 *
	 * @param feetype
	 * @param scheduleID
	 * @return
	 * @Return boolean
	 * @Throws
	 */
	public boolean checkFeeScheduleIDExist(String feetype, String scheduleID) {
		boolean flag = false;
		feetype = getFeeTypePattern(feetype);
		RegularExpression regex = new RegularExpression(feetype, false);
		
		// Find all Fee input fields
		IHtmlObject[] objs = browser.getTextField(".id", regex, ".class",
				"Html.INPUT.text");
		if (objs.length < 1) {
			Browser.unregister(objs);
			return false;
		}

		IHtmlObject textField = (scheduleID == null
				|| scheduleID.length() < 1 ? objs[0] : filterByScheduleID(objs,
				scheduleID));

		if (textField == null) {
			Browser.unregister(objs);
			return false;
		}
		flag = scheduleID.equalsIgnoreCase(parseFeeScheduleID(
				textField.getProperty(".id").toString()).trim());
		Browser.unregister(objs);
		return flag;
//		List<String> feeRowValues = getFeeInfoList(feetype);
//		if(feeRowValues.size() < 1) {
//			return false;
//		}
//		for(String value : feeRowValues) {
//			if(value.contains(scheduleID)) {
//				return true;
//			}
//		}
//		
//		return false;
	}

	/**
	 * Get the fee schedule ID
	 *
	 * @param feeType
	 *            -- fee's type
	 * @return -- schedule ID
	 */
	public String getFeeScheduleID(String feeType) {
		String feeShceduleID = "";
		IHtmlObject[] objs;
		if(feeType.contains("RA")){
			objs = getRAFeesTable();
		}else{
			objs = getCustomerFeesTable();
		}
		
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompare = tableGrid.getCellValue(row, 0).toString();
			if (toCompare.equalsIgnoreCase(feeType)
					&& feeType.equals("Penalty")) {
				String temp = tableGrid.getCellValue(row, 1).toString();
//				feeShceduleID = temp.split(" ")[3];
				feeShceduleID = temp.split(" ")[5];
			} else if (toCompare.equalsIgnoreCase(feeType)
					&& this.getBaseTaxRate(feeType) != 0.0) {
				String temp = tableGrid.getCellValue(row, 1).toString();
//				feeShceduleID = temp.split(" ")[3];
				feeShceduleID = RegularExpression.getMatches(temp.split("\\$")[2], "[0-9]{5,}")[0];
			} else if (toCompare.equalsIgnoreCase(feeType)
					&& this.getBaseTaxRate(feeType) == 0.0) {
				String temp = tableGrid.getCellValue(row, 1).toString();
//				feeShceduleID = temp.split(" ")[2];
				feeShceduleID = RegularExpression.getMatches(temp, "\\d{5,}")[0];//for fee schedule id at least more than 5 digits
			}
		}

		Browser.unregister(objs);
		return feeShceduleID;
	}

	/**
	 * Get the total base tax amount for a particular fee-type
	 *
	 * Methodology: 1) search for the feeType in the row labels 2) set flag to
	 * indicate search for keyword 'tax' 3) for each tax row parse out the tax
	 * rate from the appropriate column and add it to previous 4) stop iteration
	 * once a non-tax row is found 5) return total base tax rate for the
	 * particular fee type
	 *
	 * @param assocFee like 'Calculate Slip Tax (tax)'
	 * @return
	 */
	public double getBaseTaxAmount(String assocFee) {
		IHtmlObject[] objs = getCustomerFeesTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		int rows[] = tableGrid.findRows(0, assocFee);
		double totalAmount = 0.0;
		
		if(rows.length < 0){
			throw new ItemNotFoundException("Can't find row by given content "+assocFee);
		}

		String baseAmount = "";
		for(int i = 0; i<rows.length; i++){
			String feeInfoString = tableGrid.getCellValue(rows[i], 1).toString();
			java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\$[0-9]+[\\.][0-9]+");
			java.util.regex.Matcher m = p.matcher(feeInfoString);
			if (m.find()) {
				baseAmount = m.group(0);
			}
			baseAmount = baseAmount.replaceAll("[\\$]", "");
			totalAmount = totalAmount + Double.valueOf(baseAmount);
		}
		
		Browser.unregister(objs);
		return totalAmount;
	}

	/**
	 * Get the total base tax rate for a particular fee-type
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
		IHtmlObject[] objs = getCustomerFeesTable();
		IHtmlTable tableGrid = (IHtmlTable) objs[0];

		for (int row = 0; row < tableGrid.rowCount(); row++) {
			String toCompare = tableGrid.getCellValue(row, 0).toString();

			// set flag if fee type header row found
			if (toCompare.equals(assocFee)) {
				foundFeeHeaderRow = true;
			}

			if (foundFeeHeaderRow == true) {
				if ((toCompare.trim()).endsWith(TAX_ROW)) {// check if row
					// contains tax base
					// rate
					String baseRate = "";
					String feeInfoString = tableGrid.getCellValue(row, 1)
							.toString();

					// find tax rate in string with $ amount and other data as
					// well (e.g. 7.0%)
					java.util.regex.Pattern p = java.util.regex.Pattern
							.compile("\\b[0-9]+[\\.][0-9]+[\\%]");
					java.util.regex.Matcher m = p.matcher(feeInfoString);

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
		}
		Browser.unregister(objs);
		return totalBaseRate;
	}

	/**
	 * Retrive RA fee data
	 *
	 * @return---Add the fee data to one List
	 */
	public List<FeeRecord> getRAFeeData() {
		List<FeeRecord> feeRecords = new ArrayList<FeeRecord>();
		IHtmlObject[] objs = getRAFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		int size = table.rowCount();

		for (int i = 2; i < size; i++) {
			String type = table.getCellValue(i, 0).toString();
			if (type == null || type.length() < 1)
				continue;

//			FeeRecord fr = new FeeRecord();
//			String data = table.getCellValue(i, 1).toString();
//
//			fr.feeType = type;
//			String[] token = RegularExpression
//					.getMatches(data, "\\d+\\.\\d{1,2}");
//			fr.feeAmount = token[0];
//			if (token.length > 1)
//				fr.origRate = token[1];
//
//			token = RegularExpression.getMatches(data, "\\d{5,}");
//			if (token.length > 0)
//				fr.feeSchID = token[0];
//			if (token.length > 1)
//				fr.tresholdId = token[1];
//			
//			feeRecords.add(fr);
			
			FeeRecord fr = new FeeRecord();
			fr.feeType = type;
			
			String text = table.getCellValue(i, 1).toString();
			String tokens[] = text.split("\\s");
			if(tokens.length < 3) {
				continue;
			}
			IHtmlObject subTableObjs[] = browser.getTableTestObject(".text", new RegularExpression(tokens[4] + ".*" + tokens[6] + ".*" + tokens[7], false));
			if(subTableObjs.length < 2) {
				throw new ItemNotFoundException("Cannot find RA Fee sub table object.");
			}
			ITable subTable = (ITable)subTableObjs[subTableObjs.length - 1];
			
			fr.feeAmount = subTable.getCellValue(0, 1).replaceAll("\\$", StringUtil.EMPTY).trim();
			fr.feeSchID = subTable.getCellValue(0, 4).trim();
			fr.tresholdId = subTable.getCellValue(0, 5).trim();
			fr.origRate = subTable.getCellValue(0, 6).trim();
			fr.unit = subTable.getCellValue(0, 7).trim();
			
			feeRecords.add(fr);
			
			Browser.unregister(subTableObjs);
		}
		Browser.unregister(objs);
		return feeRecords;
	}

	/**
	 * Get customer fee amount by given fee name, such as "Use Fee", "Attribute Fee","TOTAL","Transaction Fee"
	 *
	 * @param type
	 * @return
	 */
	public double getCustomerFeeAmount(String type) {
		String value = this.getCustomerFeesData(type, false);
		int endindex = value.indexOf(".") + 3;
		value = value.substring(0, endindex);
		value = RegularExpression.getMatches(value, "\\d+\\.\\d+")[0];
		return Double.parseDouble(value);
	}

	/**
	 * get customer fee data info by given fee name in String format or regular expression format.
	 * @param type
	 * @param isRegExpression
	 * @return
	 */
	public double getCustomerFeeAmount(String type, boolean isRegExpression) {
		String value = this.getCustomerFeesData(type, isRegExpression);
		int endindex = value.indexOf(".") + 3;
		return Double.parseDouble(value.substring(0, endindex));
	}

	/**
	 * get customer fee data info by given fee name, such as "Use Fee", "Attribute Fee","TOTAL","Transaction Fee"
	 * @param type
	 * @return
	 */
	public String getCustomerFeesData(String type) {
		IHtmlObject[] objs = this.getCustomerFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		int row = table.findRow(0, type);
		String value = table.getCellValue(row, 1).toString().replaceAll("\\$",
				"").trim();
		value = value.replaceAll("\\(", "");
		value = value.replaceAll("\\)", "");

		Browser.unregister(objs);
		return value;
	}

	/**
	 * get customer fee data info by given fee name in String format or regular expression format.
	 * @param type
	 * @param isRegExpression
	 * @return
	 */
	public String getCustomerFeesData(String type, boolean isRegExpression) {
		IHtmlObject[] objs = this.getCustomerFeesTable();
		IHtmlTable table = (IHtmlTable) objs[0];
		RegularExpression pattern = null;
		int row = 0;

		if (isRegExpression){
			pattern = new RegularExpression(type, false);
		}

		if (null != pattern ){
			row = table.findRow(0, pattern);
		} else{
			row = table.findRow(0, type);
		}

		String value = table.getCellValue(row, 1).toString().replaceAll("\\$",
				"").trim();
		value = value.replaceAll("\\(", "");
		value = value.replaceAll("\\)", "");

		Browser.unregister(objs);
		return value;
	}

	/**
	 * Get total amount
	 *
	 * @return--the total amount
	 */
	public double getTotal() {
		return getCustomerFeeAmount("Total");
	}

	/**
	 * Get past paid amount
	 *
	 * @return
	 */
	public double getPastPaid() {
		return getCustomerFeeAmount("Past Paid");
	}

	/** Retrieve the current balance owing */
	public double getBalance() {
		return getCustomerFeeAmount("BALANCE");
	}

	/**
	 * Change RA fees amount
	 *
	 * @param feeSchID
	 * @param feeAmount
	 * @return
	 */
	public boolean changeRAFees(String feeSchID, String feeAmount) {
		boolean toReturn = false;
		try {
			browser.setTextField(".id", new RegularExpression("rafee_\\d_"
					+ feeSchID, false), feeAmount, true);
			toReturn = true;
		} catch (Exception e) {
			logger.warn(e);
		}
		return toReturn;
	}

	public List<ReservationFeeInfo> getCusFeesDetail() {
		return this.getCusFeesDetail(0);
	}
	
	/**
	 * This method goes to parse the Customer Fees table in Reservation Fees
	 * page; will return all cell values as a vector
	 *
	 * @return - cell value vector
	 */
	public List<ReservationFeeInfo> getCusFeesDetail(int index) {
		List<ReservationFeeInfo> fees = new ArrayList<ReservationFeeInfo>();
//		RegularExpression colReg = new RegularExpression(
//				"^\\W*Amount Change To.*", false);
		 RegularExpression colReg = new
		 RegularExpression(".*?Amount.*Change To.*Schedule.*", false);
		//		RegularExpression fieldReg = new RegularExpression(
		//				"^ ?\\(?\\$\\d+\\.\\d+\\)? \\$.*", false);
//		RegularExpression fieldReg = new RegularExpression(
//				"^\\W*\\(?\\$\\d+(,\\d+)?\\.\\d+\\)? \\$.*", false);
		RegularExpression fieldReg = new RegularExpression(
				"^.*?\\(?\\$\\d+(,\\d+)?\\.\\d+\\)? \\$.*", false);
		// RegularExpression fieldReg = new
		// RegularExpression("\\(?\\$\\d+\\.\\d+\\)? \\$.*", false);
		// RegularExpression fieldReg=new
		// RegularExpression("^\\$|\\(\\d+\\.\\d{2} \\$.*",false);

		IHtmlObject[] cusTab = this.getCustomerFeesTable();
		IHtmlObject[] cusColTab = browser.getHtmlObject(".class", "Html.TABLE",
				".text", colReg, cusTab[index]);
		IHtmlObject[] feeTab = browser.getHtmlObject(".class", "Html.TABLE",
				".text", fieldReg, cusTab[index]);
		IHtmlTable cusTableGrid = (IHtmlTable) cusTab[index];

		int colNum = ((IHtmlTable) cusColTab[cusColTab.length - 1]).columnCount();// get column
		// name table
		// col number
		// int rowNum2 = cusTableGrid.rowCount();// get column
		// name table col number
		int rowNum = 1; // use this counter to reset the sub fee table sequence

		for (int row = 0; row < cusTableGrid.rowCount(); row++) {
			ReservationFeeInfo feeInfo = new ReservationFeeInfo();
			String feeType = cusTableGrid.getCellValue(row, 0).toString();

			if (feeType.length() > 0 && !feeType.equals("Customer Fees")) {
				feeInfo.feeType = feeType;// clean up the blank row and header
				// row of main table

				for (int i = 0; i < colNum; i++) {
					String[] colName = new String[colNum];

					colName[i] = ((IHtmlTable) cusColTab[cusColTab.length - 1])
							.getCellValue(1, i).trim();

					if (colName[i].length() > 0) {
						if (colName[i].equals("Amount"))
							feeInfo.feeAmount = ((IHtmlTable) feeTab[rowNum])
						.getCellValue(0, i).toString().replaceAll(
								"[\\(\\$|\\)|\\$|\\,]", "");
						if (colName[i].equals("Base Rate") || colName[i].equals("Rate"))
							feeInfo.baseRate = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString().replaceAll(
											"[\\%]", "");
						if (colName[i].equals("Change To")){
							Property[] property = new Property[1];
							property[0] = new Property(".value", new RegularExpression("\\d+\\.\\d+", false));
							String changeToValue = browser.getTextFieldValue(property, feeTab[rowNum]);
							feeInfo.changeAmount = changeToValue;
						}
						if (colName[i].equals("Prev Adjusted"))
							feeInfo.preAdjust = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString();
						if (colName[i].equals("Schedule"))
							feeInfo.feeSchID = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString();
						if (colName[i].equals("Rate Unit"))
							feeInfo.rateUnit = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString();
						if (colName[i].equalsIgnoreCase("Applies To"))
							feeInfo.tranType = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString();
						if (colName[i].equals("Fee Dates"))
							feeInfo.feeDate = ((IHtmlTable) feeTab[rowNum])
									.getCellValue(0, i).toString();
					}
				}
				rowNum++;// control the sub fee table's number
				fees.add(feeInfo);
			}
		}

		Browser.unregister(cusTab);
		Browser.unregister(cusColTab);
		Browser.unregister(feeTab);
		return fees;
	}

	public String getRAFeeTotalAmount(){
		return browser.getTextFieldValue(".id","total_ra_feetotal");
	}

	/**
	 * Get discount rate in the fee detail page
	 *
	 * @param feeSchID
	 * @return
	 */

	public String getDisRate(String feeSchID) {
		return browser.getTextFieldValue(".id", new RegularExpression(
				usefeeDisctRateIdPattern + feeSchID, false));
	}

	/**
	 * Get the row of specify fee type
	 *
	 * @param feeType
	 * @return
	 */
	public int getFeeTypeRow(String feeType) {
		RegularExpression reg = new RegularExpression(
				"^Customer Fees\\W+Amount Change*", false);
		IHtmlObject[] feeTypeTable = browser.getHtmlObject(".class",
				"Html.TABLE", ".text", reg);
		int row = 0;

		for (int i = 0; i < ((IHtmlTable) feeTypeTable[0]).rowCount(); i++) {
			if (((IHtmlTable) feeTypeTable[0]).getCellValue(i, 0).toString()
					.matches(feeType + ".*")) {
				row = i;
			}
		}

		// int row = ((ITable)feeTypeTable[0]).findRow(0, feeType);
		Browser.unregister(feeTypeTable);
		return row - 2;
	}

	/**
	 * Check the row of specify fee type exists or not
	 *
	 * @param feeType
	 * @return
	 */
	public boolean checkFeeTypeRowExist(String feeType) {
		RegularExpression reg = new RegularExpression(
				"^Customer Fees Amount Change*", false);
		IHtmlObject[] feeTypeTable = browser.getHtmlObject(".class",
				"Html.TABLE", ".text", reg);
		int row = 0;

		for (int i = 0; i < ((IHtmlTable) feeTypeTable[0]).rowCount(); i++) {
			if (((IHtmlTable) feeTypeTable[0]).getCellValue(i, 0).toString()
					.matches(feeType + ".*")) {
				row = i;
			}
		}
		if (row != 0 && row >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public void clickCartLink() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cart");
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
		// ITable grid= (objs.length>1 ?
		// (ITable)objs[1]:(ITable)objs[0]);
		IHtmlTable grid = (IHtmlTable) objs[index];
		String amount = grid.getCellValue(0, colNum);
		Browser.unregister(objs);
		return amount;
	}

	public IHtmlObject[] getFeesTableByGivenFlag(String flag) {
		RegularExpression reg = new RegularExpression("^" + flag + "*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", reg);
		return objs;
	}

	public class FeeRecord {
		public String feeType = "";

		public String feeAmount = "";

		public String feeSchID = "";

		public String tresholdId = "";
		
		public String origRate = "";
		
		public String unit = "";
		
		public String transaction = "";
	}

	/**
	 * Get the total amount for specific fee type
	 *
	 * @param feeType
	 *            :Use Fee, Attribute Fee, Penalty, Transaction Fee ...
	 * @return
	 */
	public double getSpecificFeeTypeTotalAmount(String feeType) {
		logger.info("Return all amout about " + feeType);
//		List<Object> fees = new ArrayList<Object>();
		double feeAmount = 0.00;

//		fees = this.getCustomerFees(feeType);
//		if (fees.size() > 0) {
//			for (int i = 0; i < fees.size(); i++) {
//				VpFeeSchedule fee = (VpFeeSchedule) fees.get(i);
//				feeAmount = feeAmount + fee.feeAmount;
//			}
//		}
		
		List<ReservationFeeInfo> feeValue = getCusFeesDetail();
		for (int i = 0; i < feeValue.size(); i++) {
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (feeType.equals(feeInfo.feeType)) {
				feeAmount = feeAmount + Double.valueOf(feeInfo.feeAmount.trim());
			}
		}

		return feeAmount;
	}
	
	public String getFeeAmountByFeeTypeAndTransType(String style,String trans) {
		List<ReservationFeeInfo> feeValue = getCusFeesDetail();
		String fees ="";
		for (int i = 0; i < feeValue.size(); i++) {
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (style.equals(feeInfo.feeType) && trans.equals(feeInfo.tranType) ) {
				fees = feeInfo.feeAmount.trim();
				break;
			}
		}
		return fees;
	}

	public List<String> getFeeBySystemCalculate(String style) {
		List<ReservationFeeInfo> feeValue = getCusFeesDetail();
		List<String> feeCal = new ArrayList<String>();
		for (int i = 0; i < feeValue.size(); i++) {
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (style.equals(feeInfo.feeType)) {
				String fees = feeInfo.feeAmount.trim()
						+ feeInfo.feeSchID.trim() + feeInfo.rateUnit.trim()
						+ feeInfo.tranType.trim();
				feeCal.add(fees);
			}
		}
		return feeCal;
	}
	
	public List<ReservationFeeInfo> getFeeInfo(String style, int index) {
		List<ReservationFeeInfo> feeValue = getCusFeesDetail(index);
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!style.equals(feeInfo.feeType)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}
	
	public List<ReservationFeeInfo> getFeeInfo(String style){
		List<ReservationFeeInfo> feeValue = getCusFeesDetail();
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!style.equals(feeInfo.feeType)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}
	
	public List<ReservationFeeInfo> getAddToListTransFeeInfo(String style){
		List<ReservationFeeInfo> feeValue = getCusFeesDetail(1);
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!style.equals(feeInfo.feeType)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}
	
	public List<ReservationFeeInfo> getFromFeeInfo(String style){
		List<ReservationFeeInfo> feeValue = getCusFeesDetail(0);
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!style.equals(feeInfo.feeType)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}
	
	public List<ReservationFeeInfo> getToFeeInfo(String style){
		List<ReservationFeeInfo> feeValue = getCusFeesDetail(1);
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!style.equals(feeInfo.feeType)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}

	/**
	 * Get text of reservation details table
	 *
	 * @return
	 */
	public String getReservationTableInfo() {
		String reservationInfo = "";
		RegularExpression res = new RegularExpression(
				"^Reservation Reservation #.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", res);
		reservationInfo = objs[0].getProperty(".text");
		Browser.unregister(objs);

		return reservationInfo;
	}

	/**
	 * Get discount rate types
	 * @param feeType
	 * @return
	 */
	public List<String> getdiscRateType(String feeType){
		List<Object> discountList = this.getCustomerFees(feeType);
		List<Object> discountIDs = new ArrayList<Object>();
		List<String> discRateType = new ArrayList<String>();
		//Get discount IDs
		if (discountList.size() > 0) {
			for (int i = 0; i < discountList.size(); i++) {
				VpFeeSchedule fee = (VpFeeSchedule) discountList.get(i);
				discountIDs.add(fee.feeSchId);
			}
		}
		//Get discount rate according discount IDs
		for(int i=0; i<discountIDs.size(); i++){
			IHtmlObject[] objs = this.getCustomerFeesTable();
			IHtmlTable table = (IHtmlTable) objs[0];
			for (int row = 2; row < table.rowCount() - 2; row++) {
				String feeTypeString = table.getCellValue(row, 0).toString();
				if (feeType != null && !feeType.equals("") && feeTypeString.indexOf(feeType) != -1) {
					String feeInfo = table.getCellValue(row, 1).toString().trim();
					discRateType.add(feeInfo.split("\\$")[2].split(String.valueOf(discountIDs.get(i)))[0].trim());
				}
			}
		}
		return discRateType;
	}
	public List<String> getDiscRateType(){
		return this.getdiscRateType("discount");
	}
	public List<String> getPenaltyRateType(){
		return this.getdiscRateType("Penalty");
	}
	
	public String getRowValueByFeeType(String feeType){
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TR", ".text", new RegularExpression("^" + feeType + ".*",false));
		if(objs.length<1){
			throw new ItemNotFoundException("Did not get row object by fee type = " + feeType);
		}
		
		String text = objs[0].text();
		Browser.unregister(objs);
		return text;
	}
	
	public List<String> getFeeInfoList(String feeType){
		List<String> custFields = new ArrayList<String>();
		//comment below method due to customer fee object is not table object anymore;
//		HtmlObject[] objs = this.getCustomerFeesTable();
//		ITable table = (ITable) objs[0];
//		int row = table.findRow(0, feeType);
//		int columnNum = table.columnCount();
//		for(int i=0; i< columnNum; i++){
//			
//			custFields.add(table.getCellValue(row, i));
//		}
//		Browser.unregister(objs);
		IHtmlObject[] objs= null;
		objs = this.getCustomerFeesTable();//handled with Reservation Fees
		if(objs.length <1)
			objs = browser.getTableTestObject(".text", new RegularExpression(
				"^Order Item.*", false));//handled with Ticket order Fees
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];
		int row = table.findRow(0, feeType);
		int columnNum = table.columnCount();
		for(int i=0; i< columnNum; i++){
			custFields.add(table.getCellValue(row, i));
		}
		Browser.unregister(objs);
		return custFields;
	}
	
	public String getDiscountRate(String scheduleId){
		return getFeeValueViaScheduleId(Property.toPropertyArray(".id",new RegularExpression("ChangeDiscount-\\d+\\.discountRate",false)), scheduleId);
	}
	
	public String getDiscountAmount(String scheduleId){
		return getFeeValueViaScheduleId(Property.toPropertyArray(".id",new RegularExpression("ChangeDiscount-\\d+\\.discountChangeAmount",false)), scheduleId);
	}
	
	public String getAttributeFeeAmount(String scheduleId){
		return getFeeValueViaScheduleId(Property.toPropertyArray(".id",new RegularExpression("ChangeFee-\\d+\\.changeAmount",false)), scheduleId);
	}
	
	public String getUseFeeAmount(String scheduleId){
		return getFeeValueViaScheduleId(Property.toPropertyArray(".id",new RegularExpression("ChangeFee-\\d+\\.changeAmount",false)), scheduleId);
	}
	
	private String getFeeValueViaScheduleId(Property[] pro,String scheduleId){
		IHtmlObject[] tops = this.getRowViaFeeScheduleId(scheduleId);
		String value = browser.getTextFieldValue(pro,tops[0]);
		Browser.unregister(tops);
		return value;
	}
	
	private IHtmlObject[] getRowViaFeeScheduleId(String feeSchedId){
		return browser.getHtmlObject(".class","HTML.TR",".text",new RegularExpression(".*"+feeSchedId+".*", false));
	}
	
	public List<ReservationFeeInfo> getFeeInfoListByFeeScheduleId(String feeSchedId){
		List<ReservationFeeInfo> feeValue = getCusFeesDetail();
		for(int i = feeValue.size()-1; i >=0; i--){
			ReservationFeeInfo feeInfo = feeValue.get(i);
			if (!feeSchedId.equals(feeInfo.feeSchID)) {
				feeValue.remove(i);
			}
		}
		return feeValue;
	}
	
	public void verifyFeeScheduleIsExisting(String feeType,String feeScheduleID, boolean isExisting){
		List<ReservationFeeInfo> fees=this.getFeeInfo(feeType);
		
		if(StringUtil.isEmpty(feeScheduleID)){
			if(fees != null && fees.size() > 0){
				throw new ErrorOnPageException("Fee schedule for "+feeType+" should NOT exist!");
			}
		} else {
			logger.info("Verify Fee Schedule " +feeScheduleID + " is existing info.");
			List<String> scheduleList = new ArrayList<String>();
			for(int i=0;i<fees.size();i++){
				ReservationFeeInfo usefee=fees.get(i);
				scheduleList.add(usefee.feeSchID);
			}
			
			boolean actExisting = scheduleList.contains(feeScheduleID);
			if(isExisting != actExisting){
				throw new ErrorOnPageException(feeType+" Existing info not correct. Should "+(isExisting? "":"NOT ")+"exist.");
			}
		}
	}
	
	public void verifyScheduleAppliedOrNot(boolean expectExist, String expectScheduleID){
		IHtmlTable table = (IHtmlTable)this.getCustomerFeesTable()[0];
		String tableTexct = table.getProperty(".text");

		if(expectExist != tableTexct.contains(expectScheduleID)){
			throw new ErrorOnPageException(expectScheduleID+" should "+(expectExist?"":"NOT ")+"exist.");
		}
	}
	
	public String getOrderAmount(String specialtype){
		IHtmlObject[] objs = browser.getTableTestObject(".text", new RegularExpression("^Order Total.*", false));

		if(objs.length<1)
			throw new ItemNotFoundException("Could not find Order Total table on page.");
		
		IHtmlTable table = (IHtmlTable) objs[objs.length-1];

		List<String> feeType = table.getColumnValues(0);

		String amount = "";
		for (int row = 0; row < feeType.size(); row++) {

			String type = feeType.get(row);
			if (type.equalsIgnoreCase(specialtype)) {
				String fee = table.getCellValue(row, 1).toString();

				int index = fee.indexOf("$");
				int lastIndex = fee.lastIndexOf("$");
				amount = fee.substring(index, lastIndex).split("\\$")[1].trim().replaceAll("\\)", "");
				System.out.println("total amount:"+amount);
			}
		}
		Browser.unregister(objs);
		return amount;
	}
	
	/**
	 * This method is used to get order total amount by system calculate in the ticket order
	 *
	 * @return
	 */
	public String getOrderTotalAmount() {
		return getOrderAmount("TOTAL");
	}
	
	
	public String getOrderPastPaidAmount(){
		return getOrderAmount("Past Paid");
	}
	
	public String getOrderBalanceAmount(){
		return getOrderAmount("BALANCE");
	}
	
	public void changeUseFeeDisctRate(String disctSched, String rate) {
		changeFee(new RegularExpression(this.usefeeDisctRateIdPattern+disctSched, false), rate);
	}
	
	public void changeAttrFeeDisctRate(String disctSched, String rate) {
		changeFee(new RegularExpression(this.attrfeeDisctRateIdPattern+disctSched, false), rate);
	}
	
	public String getChangedToUsefeeDisctAmt(String disctSched) {
		return browser.getTextFieldValue(".id", new RegularExpression("usefee_discnt_[0-9]+_"+disctSched, false));
	}
	
	public String getChangedToAttrfeeDisctAmt(String disctSched) {
		return browser.getTextFieldValue(".id", new RegularExpression("attrfee_discnt_[0-9]+_"+disctSched, false));
	}
	
	public void changeMarinaRAFee(String newFee){
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("marinaOrderRAFeesFormBar-\\d+",false));
		browser.setTextField(".id",new RegularExpression("ChangeFee-\\d+.changeAmount", false),newFee,objs[0]);
		Browser.unregister(objs);
	}
	
	public List<String> getRATransactionFeesAmount() {
		IHtmlObject[] objs = this.getRAFeesTable();
		if(objs.length<1)
			throw new ItemNotFoundException("Could not find ra fee table on page.");
		List<String> amts = new ArrayList<String>();
		IHtmlTable rafeeTable = (IHtmlTable) objs[0];
		for (int row = 0; row < rafeeTable.rowCount(); row++) {
			String feeType = rafeeTable.getCellValue(row, 0).toString();
			if (feeType.length() > 0 && feeType.equals("RA Transaction Fee")) {
				String content = rafeeTable.getCellValue(row, 1).toString();
				String amt = content.substring(content.indexOf("$")+1, content.lastIndexOf("$")).trim();
				amts.add(amt);
			}
		}
		Browser.unregister(objs);
		return amts;
	}
	
	public void verifyFeePenaltyScheduleAndAmountForCalculateFeePenalty(FeePenaltyData feePenaltyData,BigDecimal expFeePenaltyAmount, String feeType, boolean peanltyIsExisting){
		logger.info("Verify "+ feeType + " penalty info.");
		
		List<ReservationFeeInfo> feePenaltyInfosFromUI = this.getFeeInfo(OrmsConstants.FEETYPE_NAME_PENALTY);
		
		if(!peanltyIsExisting){
			if(feePenaltyInfosFromUI.size()!=0){
				throw new ErrorOnPageException(feeType + " penalty info should be not existing.");
			}else logger.info(feeType + " Penalty Info is not existing.");
		}else {
			if(feePenaltyInfosFromUI.size()!=1){
				throw new ErrorOnPageException(feeType + " penalty info should be existing.");
			}
			
			boolean result=true;
			ReservationFeeInfo feePenaltyInfo = feePenaltyInfosFromUI.get(0);
			result &= MiscFunctions.compareResult(feeType + " Penalty Schedule", feePenaltyData.id,feePenaltyInfo.feeSchID);
			result &= MiscFunctions.compareResult(feeType + " Penalty Penalty Amount", expFeePenaltyAmount, new BigDecimal(feePenaltyInfo.feeAmount));
			if(!result){
				throw new ErrorOnPageException(feeType + " Penalty Info not correct, please check.");
			}else logger.info(feeType + " Penalty Info is correct");
		}
		
	}
	
	public BigDecimal getFeeAmountAfterDiscountForCalculateFeePenalty(String feeType, String discountName){
		logger.info("Get finally " + feeType + " amount from fee detail page.");
		
		List<ReservationFeeInfo> feeInfoFromUI = this.getFeeInfo(feeType);
		List<ReservationFeeInfo> feeDiscountInfoFromUI = this.getFeeInfo(discountName);
		
		if(feeInfoFromUI.size() <1 || feeDiscountInfoFromUI.size() !=1){
			throw new ErrorOnPageException(feeType + " and "  +  discountName + " info should be existing.");
		}
		
		BigDecimal feeInfoAmount = BigDecimal.ZERO.setScale(2);
		for(int i=0; i<feeInfoFromUI.size();i++){
			feeInfoAmount = feeInfoAmount.add(new BigDecimal(feeInfoFromUI.get(i).feeAmount));
		}
		
		BigDecimal finallyFeeAmount = feeInfoAmount
		                                   .subtract(new BigDecimal(feeDiscountInfoFromUI.get(0).feeAmount));
		
		return finallyFeeAmount;
	}
}
