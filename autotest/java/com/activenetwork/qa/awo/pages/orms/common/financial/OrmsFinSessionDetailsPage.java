/*
 * $Id: FinMgrFinSessionPage.java 1207 2005-09-29 14:07:26Z i2k-net\cguo $ 
 */

package com.activenetwork.qa.awo.pages.orms.common.financial;

import java.util.ArrayList;
import java.util.List;

import com.activenetwork.qa.awo.datacollection.legacy.FinSession;
import com.activenetwork.qa.awo.datacollection.legacy.FinSession.PaymentRecord;
import com.activenetwork.qa.awo.util.MiscFunctions;
import com.activenetwork.qa.testapi.ErrorOnPageException;
import com.activenetwork.qa.testapi.ItemNotFoundException;
import com.activenetwork.qa.testapi.interfaces.ITable;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.interfaces.html.IText;
import com.activenetwork.qa.testapi.util.Property;
import com.activenetwork.qa.testapi.util.RegularExpression;
import com.activenetwork.qa.testapi.util.StringUtil;


/**
 * TODO: enter class description
 * 
 * @author CGuo
 */
public class OrmsFinSessionDetailsPage extends OrmsFinancialsCommonPage {

	/**
	 * Script Name   : FinMgrFinSessionPage
	 * Generated     : Nov 22, 2004 4:18:09 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/11/22
	 */
  	
  	/**
	 * A handle to the unique Singleton instance.
	 */
	private static OrmsFinSessionDetailsPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	private OrmsFinSessionDetailsPage() {

	}

	/**
	 * Singleton pattern to get unique instance of current class
	 * @return
	 */
	public static OrmsFinSessionDetailsPage getInstance() {
		if (null == _instance)
			_instance = new OrmsFinSessionDetailsPage();

		return _instance;
	}

	/**
	 * Check if the associated web object exists
	 */
	public boolean exists() {
//		return browser.checkHtmlObjectExists(".text", "Financial Sessions", ".href",
//						new RegularExpression("javascript:invokeAction(.*\"SearchFinSession.do\",.*\"GetFinSessions\",.*\"FinSessionWorker\",.*\"OPEN\".*)", false));
		
		return browser.checkHtmlObjectExists(".id", "finSessionNote");
	}
	
	/**
	 * Set Cash Total On Hand Value
	 * @param totalOnHand
	 */
	public void setCashTotalOnHand(String totalOnHand) {
		browser.setTextField(".id", "totalHand_1_81052_80802", totalOnHand);
		refreshPage();
	}

	private String getTotalOnHandTextFieldId(String paymentType) {
		String idValue = "";
		if(paymentType.equalsIgnoreCase("Cash")) {
			idValue = "totalHand_1_81052_80802";
		} else if(paymentType.equalsIgnoreCase("Personal Check")) {
			idValue = "totalHand_2_81055_80802";
		} else if(paymentType.equalsIgnoreCase("Travellers Check")) {
			idValue = "totalHand_2_81056_80802";
		} else if(paymentType.equalsIgnoreCase("Money Order")) {
			idValue = "totalHand_2_81057_80802";
		} else if(paymentType.equalsIgnoreCase("Certified Check")) {
			idValue = "totalHand_2_81058_80802";
		}
		
		return idValue;
	}
	
	public boolean isTotalOnHandEditable(String paymentType) {
		return browser.checkHtmlObjectExists(".id", getTotalOnHandTextFieldId(paymentType));
	}
	
	public double getTotalOnHand(String paymentType) {
		String payTypeId = getTotalOnHandTextFieldId(paymentType);
		if(StringUtil.notEmpty(payTypeId)){
			String text = browser.getTextFieldValue(".id", payTypeId);
			if(!StringUtil.isEmpty(text)) {
				return Double.parseDouble(text);
			}
		}
		
		
		return 0;
	}
	
	/**
	 * Set Non Cash Total On Hand Value,this include Personal Check,MoneyOrder,etc
	 * @param nonCashTotalOnHand Store Non Cash Depositable Total On Hand
	 */
	public void setNonCashTotalOnHand(String[] nonCashTotalOnHand) {
		setPersonalCheckTotalOnHand(nonCashTotalOnHand[0]);
		setTravellersCheckTotalOnHand(nonCashTotalOnHand[3]);
		setMoneyOrderTotalOnHand(nonCashTotalOnHand[1]);
		setCertifiedCheckTotalOnHand(nonCashTotalOnHand[2]);
	}

	/**
	 * Set personal Check amount Total On Hand
	 * @param nonCashTotalOnHand
	 */
	public void setPersonalCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81055_80802", nonCashTotalOnHand);
		refreshPage();
	}

	/**
	 * Set Money Order Amount Total On Hand
	 * @param nonCashTotalOnHand
	 */
	public void setMoneyOrderTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81057_80802",
				nonCashTotalOnHand);
		refreshPage();
	}

	/**
	 * Set Certified Check Amount Total On Hand
	 * @param nonCashTotalOnHand
	 */
	public void setCertifiedCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81058_80802",
				nonCashTotalOnHand);
		refreshPage();
	}

	/**
	 * Set Travellers Check Total On Hand
	 * @param nonCashTotalOnHand
	 */
	public void setTravellersCheckTotalOnHand(String nonCashTotalOnHand) {
		browser.setTextField(".id", "totalHand_2_81056_80802",
				nonCashTotalOnHand);
		refreshPage();
	}

	public void setOpeningFloat(String amount) {//openingFloat_193465583
		browser.setTextField(".id", new RegularExpression("openingFloat_\\d+", false), amount, 0, IText.Event.LOSEFOCUS);
	}
	
	public double getOpeningFloat() {
		return Double.parseDouble(browser.getTextFieldValue(".id", new RegularExpression("openingFloat_\\d+", false)));
	}
	
	public boolean isOpeningFloatEditable() {
		return browser.checkHtmlObjectExists(".id", new RegularExpression("openingFloat_\\d+", false));
	}
	
	/**
	 * After set Total On Hand Amount,Click other space to refresh Page
	 *
	 */
	public void refreshPage() {
		browser.clickGuiObject(".class", "Html.FORM", ".id", "e_Form");
		waitLoading();
	}

	/**
	 * Input Note Info
	 * @param note
	 */
	public void setNote(String note) {
		browser.setTextArea(".id", "finSessionNote", note);
	}

	/**
	 * Click Close Fin Session Button
	 *
	 */
	public void clickCloseSession() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Close Session");
	}

	/**
	 * This method get the financial session detail after click one finSession
	 * @return Financial Session
	 */
	public FinSession getFinSessionDetail() {
		RegularExpression rex = new RegularExpression(
				"^Fin Session.*", false);
		IHtmlObject[] objs = browser.getHtmlObject(".class", "Html.TABLE",
				".text", rex);
		String text = objs[0].getProperty(".text").toString();

		FinSession fs = new FinSession();
		fs.finSessID = text.substring(text.indexOf("Session ID") + 10,
				text.indexOf("Deposit")).trim();
		fs.openUser = text.substring(text.indexOf("Station") + 7,
				text.indexOf("Status")).trim();
		fs.transactions = Integer.parseInt(text.substring(text.indexOf("Transaction") + 11,
				text.indexOf("Net")).trim());
		fs.totalIncludeNonDepositables = Double.parseDouble(text.substring(
				text.indexOf("Non-Depositables") + 16,
				text.indexOf("Open Date")).trim().replaceAll("\\$|,", StringUtil.EMPTY).replaceAll("\\(|\\)", StringUtil.EMPTY));
		fs.openDate = text.substring(text.indexOf("Open Date Time") + 14,
				text.indexOf("Opening")).trim();
		fs.status = text.substring(text.indexOf("Status") + 6,
				text.indexOf("#")).trim();
		fs.netTotal = Double.parseDouble(text.substring(text.indexOf("Net Total") + 9,
				text.indexOf("Total Inc")).trim().replaceAll("\\$|,", StringUtil.EMPTY).replaceAll("\\(|\\)", StringUtil.EMPTY));

		Browser.unregister(objs);
		return fs;
	}

	/**
	 * Check FinSession Summary Page Opened
	 * @return Opened or not
	 */
	public boolean checkFinSessionSummaryExistsAndOpen() {
		return browser.checkHtmlObjectExists(".text", "Fin Session Summary",
				".className", "selected");
	}

	/**
	 * Check two Fin Session equals
	 * @param fs1 Fin Session1
	 * @param fs2 Fin Session2
	 */
	public void compareTwoFinSession(FinSession fs1, FinSession fs2) {
		logger.info("Verify Financial Session Detail!");
		if (!fs1.finSessID.equals(fs2.finSessID)) {
			logger.error("Two Fin Session ID " + fs1.finSessID + " And "
					+ fs2.finSessID + " not Equals!");
			throw new ItemNotFoundException("Two Fin Session ID not Equals!");
		}
		if (!fs1.openUser.equals(fs2.openUser)) {
			logger.error("Two Open User " + fs1.openUser + " And "
					+ fs2.openUser + " not Equals!");
			throw new ItemNotFoundException("Two Open User not Equals!");
		}
		if (!fs1.openDate.equals(fs2.openDate)) {
			logger.error("Two Open Date " + fs1.openDate + " And "
					+ fs2.openDate + " not Equals!");
			throw new ItemNotFoundException("Two Open Date not Equals!");
		}
		if (!fs1.status.equals(fs2.status)) {
			logger.error("Two Status " + fs1.status + " And " + fs2.status
					+ " not Equals!");
			throw new ItemNotFoundException("Two Status not Equals!");
		}
		if (fs1.transactions != fs2.transactions) {
			logger.error("Two transactions# " + fs1.transactions + " And "
					+ fs2.transactions + " not Equals!");
			throw new ItemNotFoundException("Two transactions# not Equals!");
		}
		if (!MiscFunctions.compareResult("Net Total", fs1.netTotal, fs2.netTotal)) {
			logger.error("Two netTotal " + fs1.netTotal + " And "
					+ fs2.netTotal + " not Equals!");
			throw new ItemNotFoundException("Two netTotal not Equals!");
		}
		if (!MiscFunctions.compareResult("Total Incl. Non-Depositables", fs1.totalIncludeNonDepositables, fs2.totalIncludeNonDepositables)) {
			logger.error("Two totalNonDepositable " + fs1.totalIncludeNonDepositables
					+ " And " + fs2.totalIncludeNonDepositables + " not Equals!");
			throw new ItemNotFoundException(
					"Two totalNonDepositable not Equals!");
		}
	}
	
	public String getOpenUser(){
		String text = browser.getObjectText(".class","Html.SPAN",".text",new RegularExpression("^Opening User.*", false));
		return text.replaceAll("Opening User", "").trim();
	}

	/**
	 * Verify a financial session summary info correct
	 * @param fs Financial Session
	 */
	public void verifyFinSessSummaryInfo(FinSession fs) {
		logger.info("Verify Fin Session Summary Info!");
		RegularExpression rex = new RegularExpression("Payment Group Payment Type # Payments.*", false);
		IHtmlObject[] objs = browser.getTableTestObject(".text", rex);
		String text = objs[0].getProperty(".text").toString();
		String finSessTotal = "";
		
		finSessTotal = "$"+ text.substring(text.lastIndexOf("Total") + 5).trim().split(" ")[0];
		
		if(String.valueOf(fs.totalIncludeNonDepositables).indexOf(",")!=-1) {
		  	fs.totalIncludeNonDepositables = Double.parseDouble(String.valueOf(fs.totalIncludeNonDepositables).replaceAll(",",""));
		}
		Browser.unregister(objs);
		MiscFunctions.compareResult("Fin Session Total Is Not Correct In Summary Info", fs.totalIncludeNonDepositables+"", finSessTotal);
	}
	
	public void clickCancel() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Cancel", true);
	}
	
	public boolean isSaveExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Save");
	}
	
	public void clickSave() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save", true);
	}
	
	public boolean isSaveNoteExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Save Note");
	}
	
	public void clickSaveNote() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Save Note", true);
	}
	
	public boolean isTransactionsTabExists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".text", "Transactions", ".href", new RegularExpression("FinSessionTrans\\.do", false)));
	}
	
	public void clickTransactionsTab() {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text", "Transactions", ".href", new RegularExpression("FinSessionTrans\\.do", false)));
	}
	
	public boolean isVoucherTransactionsTaxExists() {
		return browser.checkHtmlObjectExists(Property.toPropertyArray(".class", "Html.A", ".text", "Voucher Transactions", ".href", new RegularExpression("FinSessionVouchers\\.do", false)));
	}
	
	public void clickVoucherTransactionsTab() {
		browser.clickGuiObject(Property.toPropertyArray(".class", "Html.A", ".text", "Voucher Transactions", ".href", new RegularExpression("FinSessionVouchers\\.do", false)));
	}
	
	public boolean isPrintSessionSummaryReportButtonExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Print Session Summary Report");
	}
	
	public void clickPrintSessionSummaryReport() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Session Summary Report");
	}
	
	public boolean isPrintSessionDetailReportButtonExists() {
		return browser.checkHtmlObjectExists(".class", "Html.A", ".text", "Print Session Detail Report");
	}
	
	public void clickPrintSessionDetailReport() {
		browser.clickGuiObject(".class", "Html.A", ".text", "Print Session Detail Report");
	}
	
	private static final String PAYMENT_GROUP_COL = "Payment Group";
	private static final String PAYMENT_TYPE_COL = "Payment Type";
	private static final String NUM_OF_PAYMENTS_COL = "# Payments";
	private static final String NUM_OF_REFUNDS_COL = "# Refunds";
	private static final String OPENING_FLOAT_COL = "Opening Float";
	private static final String CHANGE_TENDERED_COL = "Change Tendered";
	private static final String TOTAL_AMOUNT_COL = "Total Amount";
	private static final String TOTAL_ON_HOLD_COL = "Total on Hand";
	private static final String ADJUSTMENT_COL = "Adjustment";
	private static final String CLOSING_FLOAT_COL = "Closing Float";
	private static final String FLOAT_ADJUSTMENT_COL = "Float Adjustment";
	
	private IHtmlObject[] getFinSessionSummaryTableObject() {
		//IHtmlObject objs[] = browser.getTableTestObject(".className", "listView", ".text", new RegularExpression("^Payment Group", false));
		IHtmlObject objs[] = browser.getTableTestObject(".className", "searchResult");//Quentin[20131012] 3.05 UI changes
		if(objs.length < 1) {
			throw new ItemNotFoundException("Cannot find fin session summary details table object.");
		}
		
		return objs;
	}
	
	private String getAttributeValueByName(String name) {
		String divText = browser.getObjectText(Property.toPropertyArray(".class", "Html.SPAN", ".className", "inputwithrubylabel", ".text", new RegularExpression("^" + name, false)));
		String value = divText.replace(name, StringUtil.EMPTY).trim();
		
		return value;
	}
	
	public String getFinSessionID() {
		return getAttributeValueByName("Fin Session ID");
	}
	
	public String getDepositID() {
		return getAttributeValueByName("Deposit ID");
	}
	
	public String getOpenUserStation() {
		return getAttributeValueByName("Open User/Station");
	}
	
	public String getStatus() {
		return getAttributeValueByName("Status");
	}
	
	public int getNumOfTransactions() {
		return Integer.parseInt(getAttributeValueByName("# Transaction"));
	}
	
	public double getNetTotal() {
		return Double.parseDouble(getAttributeValueByName("Net Total").replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
	}
	
	public double getTotalIncludeNonDepositables() {
		return Double.parseDouble(getAttributeValueByName("Total Incl. Non-Depositables").replace("$", StringUtil.EMPTY).replaceAll(",", StringUtil.EMPTY));
	}
	
	public String getOpenDateTime() {
		String text = getAttributeValueByName("Open Date Time");
		return RegularExpression.getMatches(text, "[a-zA-Z]{3} [0-9]{1,2}, [0-9]{4}")[0];
	}
	
	public String getOpeningUser() {
		return getAttributeValueByName("Opening User");
	}
	
	public String getCloseDateTime() {
		String text = getAttributeValueByName("Close Date Time");
		String[] str = RegularExpression.getMatches(text, "[a-zA-Z]{3} [0-9]{1,2}, [0-9]{4}");
		return str.length>0?str[0]:"";
	}
	
	public String getCloseUser() {
		return getAttributeValueByName("Close User");
	}
	
	public String getDepositDateTime() {
		return getAttributeValueByName("Deposit Date Time");
	}
	
	public String getDepositUser() {
		return getAttributeValueByName("Deposit User");
	}
	
	public double getTotalAmount(String paymentType) {
		return getPaymentRecordInfo(paymentType).getTotalAmount();
	}
	
	public void verifyFinSessionCloseInfo() {
		
	}
	
	public void verifyPaymentRecordInfo(PaymentRecord expected) {
		if(!comparePaymentRecordInfo(expected)) {
			throw new ErrorOnPageException("Payment Record(Type=" + expected.getPaymentType() + ") info is incorrect, please refer to log for details.");
		} else logger.info("Payment Record(Type=" + expected.getPaymentType() + ") info is correct.");
	}
	
	public boolean comparePaymentRecordInfo(PaymentRecord expected) {
		PaymentRecord actual = null;
		if(expected.getPaymentGroup().matches("Redeemed|Issued")) {//Voucher payment record
			actual = getVoucherPaymentRecord(expected.getPaymentGroup());
		} else {//normal payment record
			actual = getPaymentRecordInfo(expected.getPaymentType());
		}
		
		return expected.equals(actual);
	}
	
	
	/**
	 * 
	 * @param paymentType
	 * @return
	 */
	public PaymentRecord getPaymentRecordInfo(String paymentType) {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		List<String> columnNames = table.getRowValues(0);
		int paymentGroupColIndex = table.findColumn(0, PAYMENT_GROUP_COL);
		int paymentTypeColIndex = table.findColumn(0, PAYMENT_TYPE_COL);
		int numOfPaymentsColIndex = table.findColumn(0, NUM_OF_PAYMENTS_COL);
		int numOfRefundsColIndex = table.findColumn(0, NUM_OF_REFUNDS_COL);
		
		int openingFloatColIndex = -1;
		if(columnNames.contains(OPENING_FLOAT_COL)) {
			openingFloatColIndex = table.findColumn(0, OPENING_FLOAT_COL);
		}
		int changeTenderedColIndex = table.findColumn(0, CHANGE_TENDERED_COL);
		int totalAmountColIndex = table.findColumn(0, TOTAL_AMOUNT_COL);
		int totalOnHandColIndex = table.findColumn(0, TOTAL_ON_HOLD_COL);
		int adjustmentColIndex = table.findColumn(0, ADJUSTMENT_COL);
		
		
		int closingFloatColIndex = -1;
		if(columnNames.contains(CLOSING_FLOAT_COL)) {
			closingFloatColIndex = table.findColumn(0, CLOSING_FLOAT_COL);//as there is a hidden td
		}
		int floatAdjustmentColIndex = -1;
		if(columnNames.contains(FLOAT_ADJUSTMENT_COL)) {
			floatAdjustmentColIndex = table.findColumn(0, FLOAT_ADJUSTMENT_COL);
		}
		int rowIndex = table.findRow(paymentTypeColIndex, paymentType);
		List<String> values = table.getRowValues(rowIndex);
		int count = values.size();
		if(floatAdjustmentColIndex > -1){//as there is a hidden td
			if(count > floatAdjustmentColIndex+1){
				floatAdjustmentColIndex = floatAdjustmentColIndex+1;
				if(closingFloatColIndex >-1){
					closingFloatColIndex = closingFloatColIndex+1;
				}
			}
		}
		
		PaymentRecord pr = new FinSession().new PaymentRecord();
		pr.setPaymentGroup(values.get(paymentGroupColIndex));
		pr.setPaymentType(paymentType);
		pr.setNumOfPayments(Integer.parseInt(values.get(numOfPaymentsColIndex)));
		pr.setNumOfRefunds(Integer.parseInt(values.get(numOfRefundsColIndex)));
		if(isOpeningFloatEditable()) {//opening float is displayed as text field
			pr.setOpeningFloat(getOpeningFloat());
		} else {//opening float is displayed as text
			if(openingFloatColIndex > -1) {
				pr.setOpeningFloat(Double.parseDouble(values.get(openingFloatColIndex)));
			}
		}
		pr.setChangeTendered(Double.parseDouble(values.get(changeTenderedColIndex)));
		pr.setTotalAmount(Double.parseDouble(values.get(totalAmountColIndex)));
		if(isTotalOnHandEditable(paymentType)) {//total on hand is displayed as text field
			pr.setTotalOnHand(getTotalOnHand(paymentType));
		} else {//total on hand is displayed as text
			pr.setTotalOnHand(Double.parseDouble(values.get(totalOnHandColIndex)));
		}
		String temp = values.get(adjustmentColIndex);
		pr.setAdjustment(StringUtil.isEmpty(temp) ? 0 : Double.parseDouble(temp.replaceAll("\\(|\\)", StringUtil.EMPTY)));
		if(closingFloatColIndex > -1) {
			temp = values.get(closingFloatColIndex);
			pr.setClosingFloat(StringUtil.isEmpty(temp) ? 0 : Double.parseDouble(temp));
		}
		if(floatAdjustmentColIndex > -1) {
			if(count <= floatAdjustmentColIndex){
				floatAdjustmentColIndex = floatAdjustmentColIndex-1;
			}
			temp = values.get(floatAdjustmentColIndex);
			pr.setFloatAdjustment(StringUtil.isEmpty(temp) ? 0 : Double.parseDouble(temp));
		}
		
		Browser.unregister(objs);
		
		return pr;
	}
	
	public PaymentRecord getRedeemedVoucherPaymentRecordInfo() {
		return getVoucherPaymentRecord("Redeemed");
	}
	
	public PaymentRecord getIssuedVoucherPaymentRecordInfo() {
		return getVoucherPaymentRecord("Issued");
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public PaymentRecord getVoucherPaymentRecord(String type) {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int paymentGroupColIndex = table.findColumn(0, PAYMENT_GROUP_COL);
		int numOfPaymentsColIndex = table.findColumn(0, NUM_OF_PAYMENTS_COL);
		int numOfRefundsColIndex = table.findColumn(0, NUM_OF_REFUNDS_COL);
		int totalAmountColIndex = table.findColumn(0, TOTAL_AMOUNT_COL);
		
		int rowIndex = table.findRow(paymentGroupColIndex, type);
		List<String> values = table.getRowValues(rowIndex);
		PaymentRecord pr = new FinSession().new PaymentRecord();
		pr.setPaymentGroup(type);
		String temp = values.get(numOfPaymentsColIndex);
		if(!StringUtil.isEmpty(temp)) {
			pr.setNumOfPayments(Integer.parseInt(temp));
		}
		temp = values.get(numOfRefundsColIndex);
		if(!StringUtil.isEmpty(temp)) {
			pr.setNumOfRefunds(Integer.parseInt(temp));
		}
		String totalAmountText = values.get(totalAmountColIndex);
		boolean isNegative = false;
		if(totalAmountText.contains("(")) {//Issued
			isNegative = true;
		}
		totalAmountText = totalAmountText.replaceAll("\\(|\\)|\\$|,", StringUtil.EMPTY);
		double amount = Double.parseDouble(totalAmountText);
		if(isNegative) {
			amount = -amount;
		}
		pr.setTotalAmount(amount);
		
		Browser.unregister(objs);
		return pr;
	}
	
	public FinSession getFinSessionInfo() {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		FinSession fs = new FinSession();
		
		//fin session main info
		fs.finSessID = getFinSessionID();
		fs.depositID = getDepositID();
		fs.openUser = getOpenUserStation();//TODO user or station
		fs.status = getStatus();
		fs.transactions = getNumOfTransactions();
		fs.netTotal = getNetTotal();
		fs.totalIncludeNonDepositables = getTotalIncludeNonDepositables();
		fs.openDate = getOpenDateTime();
		fs.openUser = getOpenUser();
		fs.closeDate = getCloseDateTime();
		fs.closeUser = getCloseUser();
		fs.depositDate = getDepositDateTime();
		fs.depositUser = getDepositUser();
		
		//fin session summary sub page info
		List<String> paymentGroups = table.getColumnValues(0);
		List<String> rowValues = new ArrayList<String>();
		
		
		int paymentTypeColIndex = table.findColumn(0, PAYMENT_TYPE_COL);
		int numOfPaymentsColIndex = table.findColumn(0, NUM_OF_PAYMENTS_COL);
		int numOfRefundsColIndex = table.findColumn(0, NUM_OF_REFUNDS_COL);
		int openingFloatColIndex = table.findColumn(0, OPENING_FLOAT_COL);
		int changeTenderedColIndex = table.findColumn(0, CHANGE_TENDERED_COL);
		int totalAmountColIndex = table.findColumn(0, TOTAL_AMOUNT_COL);
		int totalOnHandColIndex = table.findColumn(0, TOTAL_ON_HOLD_COL);
		int adjustmentColIndex = table.findColumn(0, ADJUSTMENT_COL);
		int closingFloatColIndex = table.findColumn(0, CLOSING_FLOAT_COL);
		
		for(int i = 1; i < paymentGroups.size(); i ++) {
			if(StringUtil.isEmpty(paymentGroups.get(i))) {
				continue;
			}
			if(paymentGroups.get(i).matches("Cash|Non Cash Depositable|Non Depositable")) {
				rowValues = table.getRowValues(i);
				
				PaymentRecord pr = fs.new PaymentRecord();
				pr.setPaymentGroup(paymentGroups.get(i));
				pr.setPaymentType(rowValues.get(paymentTypeColIndex));
				pr.setNumOfPayments(Integer.parseInt(rowValues.get(numOfPaymentsColIndex)));
				pr.setNumOfRefunds(Integer.parseInt(rowValues.get(numOfRefundsColIndex)));
				if(StringUtil.isEmpty(rowValues.get(openingFloatColIndex)) ){
					pr.setOpeningFloat(this.getOpeningFloat());
				}else{
					pr.setOpeningFloat(Double.parseDouble(rowValues.get(openingFloatColIndex)));
				}
				pr.setChangeTendered(StringUtil.isEmpty(rowValues.get(changeTenderedColIndex)) ? 0 : Double.parseDouble(rowValues.get(changeTenderedColIndex)));
				pr.setTotalAmount(StringUtil.isEmpty(rowValues.get(totalAmountColIndex)) ? 0 : Double.parseDouble(rowValues.get(totalAmountColIndex)));
				if(StringUtil.isEmpty(rowValues.get(totalOnHandColIndex)) ){
					pr.setTotalOnHand(this.getTotalOnHand(pr.getPaymentType()));
				}else{
					pr.setTotalOnHand(Double.parseDouble(rowValues.get(totalOnHandColIndex)));
				}
			
				pr.setAdjustment(StringUtil.isEmpty(rowValues.get(adjustmentColIndex)) ? 0 : Double.parseDouble(rowValues.get(adjustmentColIndex).replaceAll("\\(|\\)", StringUtil.EMPTY)));
				
				fs.paymentRecords.add(pr);
			} else if(paymentGroups.get(i).equalsIgnoreCase("Sub-total Available for Deposit")) {
				rowValues = table.getRowValues(i);
				fs.subTotalAvailableForDepositTotalAmount = Double.parseDouble(rowValues.get(totalAmountColIndex));
				fs.subTotalAvailableForDepositTotalOnHand = Double.parseDouble(rowValues.get(totalOnHandColIndex).replaceAll("\\(|\\)", ""));
				fs.subTotalAvailableForDepositAdjustment = Double.parseDouble(rowValues.get(adjustmentColIndex).replaceAll("\\(|\\)", StringUtil.EMPTY));
				fs.subTotalAvailableForDepositClosingFloat = StringUtil.isEmpty(rowValues.get(closingFloatColIndex)) ? 0 : Double.parseDouble(rowValues.get(closingFloatColIndex));
			} else if(paymentGroups.get(i).equalsIgnoreCase("Total Other")) {
				rowValues = table.getRowValues(i);
				fs.totalOtherTotalAmount = Double.parseDouble(rowValues.get(totalAmountColIndex));
				fs.totalOtherTotalOnHand = Double.parseDouble(rowValues.get(totalOnHandColIndex));
			} else if(paymentGroups.get(i).equalsIgnoreCase("Fin Session Total")) {
				fs.finSessionTotalOnHand = Double.parseDouble(table.getCellValue(i, totalOnHandColIndex).replaceAll("\\(|\\)", ""));
			}
		}
		
		System.out.println(fs.paymentRecords.size());
		System.out.println(fs.paymentRecords.size());
		System.out.println(fs.paymentRecords.get(0).getOpeningFloat());
		System.out.println(fs.paymentRecords.get(0).getTotalOnHand());
		
		Browser.unregister(objs);
		return fs;
	}
	
	public void verifyFinSessionInfo(FinSession expected) {
		if(!compareFinSessionInfo(expected)) {
			throw new ErrorOnPageException("Fin Session(ID=" + expected.finSessID + ") summary info is incorrect, please refer to log for details.");
		} else logger.info("Fin Session(ID=" + expected.finSessID + ") summary info is correct.");
	}
	
	public boolean compareFinSessionInfo(FinSession expected) {
		FinSession actual = getFinSessionInfo();
		
		boolean result = true;
		//fin session main info
		result &= MiscFunctions.compareResult("Fin Session ID", expected.finSessID, actual.finSessID);
		result &= MiscFunctions.compareResult("Deposit ID", expected.depositID, actual.depositID);
		result &= MiscFunctions.compareResult("Open User/Station", expected.openUser, actual.openUser);
		result &= MiscFunctions.compareResult("Status", expected.status, actual.status);
		result &= MiscFunctions.compareResult("# Transactions", expected.transactions, actual.transactions);
		result &= MiscFunctions.compareResult("Net Total", expected.netTotal, actual.netTotal);
		result &= MiscFunctions.compareResult("Total Incl. Non-Depositables", expected.totalIncludeNonDepositables, actual.totalIncludeNonDepositables);
		result &= MiscFunctions.compareResult("Open Date Time", expected.openDate, actual.openDate);
		result &= MiscFunctions.compareResult("Opening User", expected.openUser, actual.openUser);
		result &= MiscFunctions.compareResult("Close Date Time", expected.closeDate, actual.closeDate);
		result &= MiscFunctions.compareResult("Close User", expected.closeUser.replace(", ", ","), actual.closeUser.replace(", ", ","));
		result &= MiscFunctions.compareResult("Deposit Date Time", expected.depositDate, actual.depositDate);
		result &= MiscFunctions.compareResult("Deposit User", expected.depositUser, actual.depositUser);
		
		//fin session summary info
		List<PaymentRecord> expectedRecords = expected.paymentRecords;
		List<PaymentRecord> actualRecords = actual.paymentRecords;
		if(!MiscFunctions.compareResult("Payment records size", expected.paymentRecords.size(), actual.paymentRecords.size())) {
			return false;
		}
		for(int i = 0; i < expectedRecords.size(); i ++) {
			for(int j = 0; j < actualRecords.size(); j ++) {
				if(expectedRecords.get(i).getPaymentType().equalsIgnoreCase(actualRecords.get(j).getPaymentType())) {
					logger.info("Verify Payment Record info for Payment Type - " + expectedRecords.get(i).getPaymentType());
					result &= expectedRecords.get(i).equals(actualRecords.get(j));
				}
			}
		}
		
		return result;
	}
	
	public List<String> getPaymentsNums() {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, NUM_OF_PAYMENTS_COL);
		List<String> results = table.getColumnValues(colIndex);
		results.remove("# Payments");
		
		Browser.unregister(objs);
		return results;
	}
	
	public int getPaymentsNum(String paymentType) {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, PAYMENT_TYPE_COL);
		int rowIndex = table.findRow(colIndex, paymentType);
		
		List<String> nums = getPaymentsNums();
		int num = Integer.parseInt(nums.get(rowIndex - 1));
		
		Browser.unregister(objs);
		return num;
	}
	
	public List<String> getRefundsNums() {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, NUM_OF_REFUNDS_COL);
		List<String> results = table.getColumnValues(colIndex);
		results.remove("# Refunds");
		
		Browser.unregister(objs);
		return results;
	}
	
	public int getRefundsNum(String paymentType) {
		IHtmlObject objs[] = getFinSessionSummaryTableObject();
		IHtmlTable table = (IHtmlTable)objs[0];
		int colIndex = table.findColumn(0, PAYMENT_TYPE_COL);
		int rowIndex = table.findRow(colIndex, paymentType);
		
		List<String> nums = getRefundsNums();
		int num = Integer.parseInt(nums.get(rowIndex - 1));
		
		Browser.unregister(objs);
		return num;
	}
	
	private Property[] getPaymentsRefundsLinkProperties(String num) {
		return Property.toPropertyArray(".class", "Html.A", ".text", num, ".href", new RegularExpression("searchTransactions|invokeAction.*", false));
	}
	
	public boolean isNumsOfPaymentsDisplayedAsLink() {
		List<String> nums = getPaymentsNums();
		boolean isLink = true;
		for(String num : nums) {
			if(!StringUtil.isEmpty(num)) {
				isLink &= browser.checkHtmlObjectExists(getPaymentsRefundsLinkProperties(num));
			}
		}
		
		return isLink;
	}
	
	public boolean isNumOfPaymentsDisplayedAsLink(String paymentType) {
		int num = getPaymentsNum(paymentType);
		return browser.checkHtmlObjectExists(getPaymentsRefundsLinkProperties(String.valueOf(num)));
	}
	
	public boolean isNumOfRefundsDisplayedAsLink(String paymentType) {
		int num = getRefundsNum(paymentType);
		return browser.checkHtmlObjectExists(getPaymentsRefundsLinkProperties(String.valueOf(num)));
	}
	
	public boolean isNumsOfRefundsDisplayedAsLink() {
		List<String> nums = getRefundsNums();
		boolean isLink = true;
		for(String num : nums) {
			if(!StringUtil.isEmpty(num)) {
			     isLink &= browser.checkHtmlObjectExists(getPaymentsRefundsLinkProperties(num));
			}
		}
		
		return isLink;
	}
	
	public boolean isErrorMessageExists() {
		return !StringUtil.isEmpty(getErrorMessage());
	}
	
	public String getErrorMessage() {
		return browser.getObjectText(".class", "Html.DIV", ".className", "message msgerror");
	}
	
	public void selectRemainingPasses(String... passNums){
		IHtmlObject[] objs = browser.getTableTestObject(".id",new RegularExpression("grid_\\d+",false),".text",new RegularExpression("Pass Number.*",false));
		ITable grid = (ITable)objs[0];
		for(String num:passNums){
			int row = grid.findRow(1, num);
			browser.selectCheckBox(".id","selectedPasses",row-1);
		}
	}
	
	public void clickTotalVarianceNum(int num){
		IHtmlObject[] top = browser.getHtmlObject(".class","Html.TR",".text",new RegularExpression("Total Variance.*",false));
		browser.clickGuiObject(".class","Html.A",".text",String.valueOf(num),false,0,top[0]);
		Browser.unregister(top);
	}
	
	public String getTotalPassSoldNum(){
		return browser.getObjectText(".class","Html.TR",".text",new RegularExpression("Total Passes Sold",false)).replaceAll("Total Passes Sold", "").trim();
	}
	
	public String getTotalPassRemainNum(){
		return browser.getObjectText(".class","Html.TR",".text",new RegularExpression("Total Passes Remaining",false)).replaceAll("Total Passes Remaining", "").trim();
	}
}
