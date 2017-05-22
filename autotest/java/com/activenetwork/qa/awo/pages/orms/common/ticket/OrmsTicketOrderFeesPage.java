/*
 * Created on Apr 28, 2009
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.activenetwork.qa.awo.pages.orms.common.ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.activenetwork.qa.awo.datacollection.legacy.feeData.ReservationFeeInfo;
import com.activenetwork.qa.awo.pages.orms.common.OrmsFeesPage;
import com.activenetwork.qa.testapi.NotSupportedException;
import com.activenetwork.qa.testapi.PageNotFoundException;
import com.activenetwork.qa.testapi.interfaces.browser.Browser;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlObject;
import com.activenetwork.qa.testapi.interfaces.html.IHtmlTable;
import com.activenetwork.qa.testapi.util.RegularExpression;

/**
 * @author raonqa
 */
public class OrmsTicketOrderFeesPage extends OrmsFeesPage {

	/**
	 * Script Name   : FldMgrFeeDetailPage
	 * Generated     : Oct 22, 2004 2:34:00 PM
	 * Original Host : WinNT Version 5.1  Build 2600 (Service Pack 2)
	 *
	 * @since  2004/10/22
	 */
//	private static RALogger logger = RALogger.getInstance();

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
	static private OrmsTicketOrderFeesPage _instance = null;

	/**
	 * The constructor could be made private
	 * to prevent others from instantiating this class.
	 * But this would also make it impossible to
	 * create instances of Singleton subclasses.
	 */
	protected OrmsTicketOrderFeesPage() {
//		fees = new ArrayList();
	}

	/**
	 * @return The unique instance of this class.
	 */
	static public OrmsTicketOrderFeesPage getInstance()
			throws PageNotFoundException {
		if (null == _instance) {
			_instance = new OrmsTicketOrderFeesPage();
		}

		return _instance;
	}

	/** Determine if the associated web object exists */
	public boolean exists() {
		RegularExpression reg = new RegularExpression(".*Ticket Order Fees$",false);
		return browser.checkHtmlObjectExists(".class", "Html.TABLE", ".text",reg);
	}

//	/**Set adjustment notes */
//	public void setAdjustmentNotes(String note) {
//		browser.setTextArea(".id", "fee_adjustment_notes", note);
//	}

	/**Retrieven transaction Fee*/
	public double getTransactionFee() {
		return this.getFeeValues(this.transfeeIdPattern);
	}

	/**Increase Transaction Fee*/
	public void increaseTransactionFee(double amounts) {
		double fee = this.getTransactionFee();

		fee += amounts;
		this.changeTransactionFee(fee);
	}

	/**Change Transaction Fee*/
	public void changeTransactionFee(double amounts) {
		this.changeFees(this.transfeeIdPattern, amounts);
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
	 * Retrieve fee value
	 * @param idPattern
	 * @return
	 */
	public double getFeeValues(RegularExpression idPattern) {
		String s = browser.getTextFieldValue(".id", idPattern);
		double fee = Double.parseDouble(s);

		return fee;
	}

	/**
	 * Change use Fee
	 * @param amount
	 */
	public void changeUseFee(double amount) {
		RegularExpression usefeeIdPattern = new RegularExpression(
				"usefee_Daily/Nightly(_[0-9]+)+", false);
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

	/**
	 * Retrieve discount fee value
	 * @return
	 */
	public double getDiscFeeValue() {
		return this.getFeeValues(this.discIdPattern);
	}

	/**
	 * Increase Discount Fee
	 * @param amounts
	 */
	public void increaseDiscFee(double amounts) {
		RegularExpression idPattern = new RegularExpression("usefee_discnt.*",false);
		double fee = this.getDiscFeeValue();

		fee += amounts;
		this.changeFees(idPattern, fee);
	}

	/**
	 * Retrieve use fee penalty value
	 * @return
	 */
	public double getUseFeePenaltyValue() {
		return this.getFeeValues(this.usefeePenaltyIdPattern);
	}

	/**
	 * Increase use Fee Penalty
	 * @param amount
	 */
	public void increaseUseFeePenalty(double amount) {
		double fee = this.getUseFeePenaltyValue();

		fee += amount;
		this.changeFees(this.usefeePenaltyIdPattern, fee);
	}

	/**
	 * change use Fee Penalty
	 * @param amount
	 */
	public void changeUseFeePenalty(double amount) {
		this.changeFees(this.usefeePenaltyIdPattern, amount);
	}

	/**
	 * change discount fee
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

	/**click OK button*/
	public void clickOK() {
		browser.clickGuiObject(".class", "Html.A", ".text", "OK", true);
	}

	/**
	 * retrieve total fee
	 * @return
	 */
	public double getTotal() {
		RegularExpression pattern = new RegularExpression("total_feetotal",false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Retrieve past paid
	 * @return
	 */
	public double getPastPaid() {
		RegularExpression pattern = new RegularExpression("past_paidtotal",false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Retrieve displayed balance
	 * @return
	 */
	public double getBalanceDisplay() {
		RegularExpression pattern = new RegularExpression("balancetotal", false);
		return this.getFeeValues(pattern);
	}

	/**
	 * Change RA Fee
	 * @param feeSchID
	 * @param feeAmount
	 */
	public void changeRAFee(String feeSchID, String feeAmount) {
		String patternStr = "rafee_[0-9]+_"+ (feeSchID.length() > 0 ? feeSchID : "[0-9]+");

		RegularExpression pattern = new RegularExpression(patternStr, false);
		this.changeFees(pattern, Double.parseDouble(feeAmount));
	}

	public void testMain(Object[] args) {

	}
	
	public String getSubTotal(String type){
		String total="";
		IHtmlObject[] objs = null;
		
		if(type.equalsIgnoreCase("Customer Fees")){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Customer Fees.*", false));
		} else if(type.equalsIgnoreCase("Order Customer Fees")){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Order Customer Fees.*", false));
		}else{
			throw new NotSupportedException("Subtotal fee type shoule be 'Customer Fees' or 'Order Customer Fees'");
		}
		
		IHtmlTable table = (IHtmlTable) objs[1];
		List<String> feeTypes = table.getColumnValues(0);

		for (int row = 0; row < feeTypes.size(); row++) {
			String feeType = feeTypes.get(row);
			if (feeType.equalsIgnoreCase("SUBTOTAL")) {
				String temp = table.getCellValue(row, 1).toString();
				int index = temp.indexOf("$");
				int lastIndex = temp.lastIndexOf("$");
				total = temp.substring(index, lastIndex).split("\\$")[1];
			}
		}
		
		return total;
	}
	
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
			}
		}
		Browser.unregister(objs);
		return amount;
	}
	
	/**
	 * This method is used to get fees info by system calculate in ticket fee details page
	 * @param feeTarget 0:Order Level 1: Order Item Level
	 * @param feeType 'Ticket Fee' or 'Transaction Fee'
	 * @param appliedTypes for 'Ticket Fee', it should be null;
	 * 					   for 'Transaction Fee', it could be 'Add New Ticket', 'Walk-up Ticket Purchase' and so on
	 * @return
	 */
	public List<ReservationFeeInfo> getFeeInfoBySystemCalculate(int feeTarget, String feeType, List<String> appliedTypes) {
		IHtmlObject[] objs = null;
		
		if(feeTarget==1){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Customer Fees.*", false));
		} else if(feeTarget==0){
			objs = browser.getTableTestObject(".text",
					new RegularExpression("Order Customer Fees.*", false));
		}else{
			throw new NotSupportedException("Fee level shoule be 'Order Item level' or 'Order level'");
		}
		
		IHtmlTable table = (IHtmlTable) objs[1];

		List<ReservationFeeInfo> feeCal = new ArrayList<ReservationFeeInfo>();
		List<String> feeTypes = table.getColumnValues(0);

		for (int row = 0; row < feeTypes.size(); row++) {
			ReservationFeeInfo feeInfo = new ReservationFeeInfo();
			String type = feeTypes.get(row);
			if (feeType.equalsIgnoreCase(type)) {
				feeInfo.feeType = type;
				
				String fee = table.getCellValue(row, 1).toString();
								
				int index = fee.indexOf("$");
				int lastIndex = fee.lastIndexOf("$");
				String amount = fee.substring(index + 2, lastIndex).split("\\$")[1];
				feeInfo.feeAmount = amount;
				
				if(appliedTypes==null){
					//treated as ticket fees, no applied type				
					String scheID = fee.substring(lastIndex).split(" ")[1];
					feeInfo.feeSchID = scheID;						
				}else{
					//treated as transaction fees, applied types could be "Add New Ticket","Walk-up Ticket Purchase" and so on								
					for(int i=0;i<appliedTypes.size();i++){					
						if(fee.indexOf(appliedTypes.get(i))>=0){
							String scheID = fee.substring(lastIndex).split(appliedTypes.get(i))[0].split(" ")[1];
							feeInfo.feeSchID = scheID;
						}
					}
				}
				feeCal.add(feeInfo);								
			}
		}
		Browser.unregister(objs);
		return feeCal;
	}
	
}
